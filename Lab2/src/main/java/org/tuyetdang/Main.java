package org.tuyetdang;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tuyetdang.Entity.Lecturer;
import org.tuyetdang.Entity.Student;
import org.tuyetdang.Entity.Subject;

import java.util.List;

@SpringBootApplication
public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1-mysql-masterapp");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        //Student
        Student s1 = new Student("2131200054", "Dang Thi Anh Tuyet", 3.5, 2003);
        Student s2 = new Student("2131200055", "Tuyet Dang", 3.6, 2003);
        Student s3 = new Student("2131200056", "Dang Thi", 3.3, 2003);
        Student s4 = new Student("2131200057", "Anh Tuyet", 3.7, 2003);
        insertStudent(s1);
        insertStudent(s2);
        insertStudent(s3);
        insertStudent(s4);

        Student foundStudent = getStudentById("2131200054");
        System.out.println("Found Student: " + foundStudent.toString());

        List<Student> allStudents = getAllStudents();
        StringBuilder sb = new StringBuilder();
        for(Student student : allStudents) {
            sb.append(student.toString()).append("\n");
        }
        System.out.println(sb.toString());

        List<Student> studentsByGpa = getStudentsByGpa(3.5);
        StringBuilder sbGpa = new StringBuilder();
        for(Student student : studentsByGpa) {
            sbGpa.append(student.toString()).append("\n");
        }
        System.out.println("Students with GPA greater than 3.5:\n" + sbGpa.toString());

        Student updateStudent = new Student("2131200054", "Dang Thi Anh Tuyet", 3.8, 2003);
        updateStudentById("2131200054", updateStudent);
        System.out.println("Updated Student: " + getStudentById("2131200054").toString());

        deleteStudent("2131200057");

        //Lecturer
        Lecturer l1 = new Lecturer("1001", "Huu Thao", 6000.0, 1990);
        Lecturer l2 = new Lecturer("1002", "Minh Ngoc", 6000.0, 1990);
        Lecturer l3 = new Lecturer("1003", "Thanh Huy", 6000.0, 1990);

        insertLecturer(l1);
        insertLecturer(l2);
        insertLecturer(l3);

        Lecturer foundLecturer = getLecturerById("1001");
        System.out.println("Found Lecturer: " + foundLecturer.toString());

        List<Lecturer> allLecturers = getAllLecturers();
        StringBuilder sbLecturers = new StringBuilder();
        for (Lecturer lecturer : allLecturers) {
            sbLecturers.append(lecturer.toString()).append("\n");
        }
        System.out.println("All Lecturers:\n" + sbLecturers.toString());

        Lecturer updateLecturer = new Lecturer("1001", "Huu Thao", 6500.0, 1990);
        updateLecturerById("1001", updateLecturer);
        System.out.println("Updated Lecturer: " + getLecturerById("1001").toString());

        deleteLecturer("1003");

        //Subject
        Subject sub1 = new Subject("CSE101", "Computer Science", "Computer Science", 3, 40);
        Subject sub2 = new Subject("CSE102", "Data Structures", "Data Structures", 3, 40);
        Subject sub3 = new Subject("CSE103", "Algorithms", "Algorithms", 3, 40);

        insertSubject(sub1);
        insertSubject(sub2);
        insertSubject(sub3);

        Subject foundSubject = getSubjectById("CSE101");
        System.out.println("Found Subject: " + foundSubject.toString());

        List<Subject> allSubjects = getAllSubjects();
        StringBuilder sbSubjects = new StringBuilder();
        for (Subject subject : allSubjects) {
            sbSubjects.append(subject.toString()).append("\n");
        }
        System.out.println("All Subjects:\n" + sbSubjects.toString());

        Subject updateSubject = new Subject("CSE101", "Computer Science", "Computer Science Updated", 4, 50);
        updateSubjectById("CSE101", updateSubject);
        System.out.println("Updated Subject: " + getSubjectById("CSE101").toString());

        deleteSubject("CSE103");

        em.close();
        emf.close();

    }

    //Student
    public static void insertStudent(Student s) {
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
        System.out.println("Inserted student: " + s);
    }

    public static Student getStudentById(String id) {
        em.getTransaction().begin();
        Student s = em.find(Student.class, id);
        em.getTransaction().commit();
        if (s != null) {
            return s;
        } else {
            throw new EntityNotFoundException("Student with id " + id + " not found");
        }
    }

    public static List<Student> getAllStudents() {
        em.getTransaction().begin();
        List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        em.getTransaction().commit();
        return students;
    }

    public static List<Student> getStudentsByGpa(double gpa) {
        em.getTransaction().begin();
        List<Student> students = em.createQuery("SELECT s FROM Student s WHERE s.gpa > :gpa", Student.class)
                .setParameter("gpa", gpa)
                .getResultList();
        em.getTransaction().commit();
        return students;
    }

    public static void updateStudentById(String id, Student s) {
        em.getTransaction().begin();
        Student existingStudent = em.find(Student.class, id);
        if (existingStudent != null) {
            existingStudent.setName(s.getName());
            existingStudent.setGpa(s.getGpa());
            existingStudent.setYob(s.getYob());
            em.merge(existingStudent);
        } else {
            throw new EntityNotFoundException("Student with id " + id + " not found");
        }
        em.getTransaction().commit();
    }

    public static void deleteStudent(String id) {
        em.getTransaction().begin();
        Student s = em.find(Student.class, id);
        if (s != null) {
            em.remove(s);
            System.out.println("Deleted student: " + s);
        } else {
            throw new EntityNotFoundException("Student with id " + id + " not found");
        }
        em.getTransaction().commit();
    }

    //Lecturer
    public static void insertLecturer(Lecturer l) {
        em.getTransaction().begin();
        em.persist(l);
        em.getTransaction().commit();
        System.out.println("Inserted Lecturer: " + l);
    }

    public static Lecturer getLecturerById(String id) {
        em.getTransaction().begin();
        Lecturer l = em.find(Lecturer.class, id);
        em.getTransaction().commit();
        if (l != null) {
            return l;
        } else {
            throw new EntityNotFoundException("Lecturer with id " + id + " not found");
        }
    }

    public static List<Lecturer> getAllLecturers() {
        em.getTransaction().begin();
        List<Lecturer> lecturers = em.createQuery("SELECT l FROM Lecturer l", Lecturer.class).getResultList();
        em.getTransaction().commit();
        return lecturers;
    }

    public static void updateLecturerById(String id, Lecturer lecturer) {
        em.getTransaction().begin();
        Lecturer existingLecturer = em.find(Lecturer.class, id);
        if (existingLecturer != null) {
            existingLecturer.setName(lecturer.getName());
            existingLecturer.setSalary(lecturer.getSalary());
            existingLecturer.setYob(lecturer.getYob());
            em.merge(existingLecturer);
        } else {
            throw new EntityNotFoundException("Lecturer with id " + id + " not found");
        }
        em.getTransaction().commit();
    }

    public static void deleteLecturer(String id) {
        em.getTransaction().begin();
        Lecturer lecturer = em.find(Lecturer.class, id);
        if (lecturer != null) {
            em.remove(lecturer);
            System.out.println("Deleted Lecturer: " + lecturer);
        } else {
            throw new EntityNotFoundException("Lecturer with id " + id + " not found");
        }
        em.getTransaction().commit();
    }

    //Subject
    public static void insertSubject(Subject subject) {
        em.getTransaction().begin();
        em.persist(subject);
        em.getTransaction().commit();
        System.out.println("Inserted Subject: " + subject);
    }

    public static Subject getSubjectById(String code) {
        em.getTransaction().begin();
        Subject l = em.find(Subject.class, code);
        em.getTransaction().commit();
        if (l != null) {
            return l;
        } else {
            throw new EntityNotFoundException("Subject with code " + code + " not found");
        }
    }

    public static List<Subject> getAllSubjects() {
        em.getTransaction().begin();
        List<Subject> subjects = em.createQuery("SELECT l FROM Subject l", Subject.class).getResultList();
        em.getTransaction().commit();
        return subjects;
    }

    public static void updateSubjectById(String code, Subject subject) {
        em.getTransaction().begin();
        Subject existingSuject = em.find(Subject.class, code);
        if (existingSuject != null) {
            existingSuject.setName(subject.getName());
            existingSuject.setDescription(subject.getDescription());
            existingSuject.setStudyHours(subject.getStudyHours());
            existingSuject.setCredits(subject.getCredits());
            em.merge(existingSuject);
        } else {
            throw new EntityNotFoundException("Subject with code " + code + " not found");
        }
        em.getTransaction().commit();
    }

    public static void deleteSubject(String code) {
        em.getTransaction().begin();
        Subject subject = em.find(Subject.class, code);
        if (subject != null) {
            em.remove(subject);
            System.out.println("Deleted Subject: " + subject);
        } else {
            throw new EntityNotFoundException("Subject with code " + code + " not found");
        }
        em.getTransaction().commit();
    }
}