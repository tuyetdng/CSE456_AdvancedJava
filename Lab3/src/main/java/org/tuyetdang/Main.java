package org.tuyetdang;

import jakarta.persistence.EntityManager;
import org.tuyetdang.Entity.Major;
import org.tuyetdang.Entity.School;
import org.tuyetdang.Entity.Student;
import org.tuyetdang.Enums.Gender;
import org.tuyetdang.Repository.MajorRepository;
import org.tuyetdang.Repository.SchoolRepository;
import org.tuyetdang.Repository.StudentRepository;
import org.tuyetdang.Service.MajorService;
import org.tuyetdang.Service.SchoolService;
import org.tuyetdang.Service.StudentService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        SchoolService schoolService = new SchoolService(new SchoolRepository());
        MajorService majorService = new MajorService(new MajorRepository());
        StudentService studentService = new StudentService(new StudentRepository());

        School school1 = new School("EIU", "Binhduong");
        School school2 = new School("TDMU", "Binhduong");

        Major major1 = new Major("IT");
        Major major2 = new Major("Business");
        Major major3 = new Major("CIT");

        Student student1 = new Student("Nguyen A", Gender.MALE, LocalDate.of(2000, 1, 1), 3.7, 2025);
        Student student2 = new Student("Nguyen B", Gender.FEMALE, LocalDate.of(2001, 1, 1), 3.7, 2025);
        Student student3 = new Student("Nguyen C", Gender.MALE, LocalDate.of(2002, 1, 1), 3.7, 2025);
        Student student4 = new Student("Nguyen D", Gender.MALE, LocalDate.of(2003, 1, 1), 3.7, 2025);

        school2.addMajor(major1);
        school2.addMajor(major2);
        school2.addMajor(major3);

        major3.addStudent(student1);
        major2.addStudent(student2);
        major2.addStudent(student3);
        major3.addStudent(student4);

        schoolService.addSchool(school1);
        schoolService.addSchool(school2);

//        majorService.addMajor(major1);
//        majorService.addMajor(major2);
//        majorService.addMajor(major3);

//        studentService.addStudent(student1);
//        studentService.addStudent(student2);
//        studentService.addStudent(student3);
//        studentService.addStudent(student4);

        System.out.println("List of Schools:");
        for (School school : schoolService.getAllSchools()) {
            System.out.println(school.toString());
        }

        System.out.println("List of Majors:");
        for (Major major : majorService.getAllMajors()) {
            System.out.println(major.toString());
        }

        System.out.println("List of Students:");
        for (Student student : studentService.getAllStudents()) {
            System.out.println(student.toString());
        }

        System.out.println("Update School with ID 2:");
        School schoolToUpdate = schoolService.getSchoolById(2);
        schoolService.updateSchool(schoolToUpdate);
        System.out.println("Updated School: " + schoolService.getSchoolById(2).toString());

        System.out.println("Update Major with ID 2:");
        Major majorToUpdate = majorService.getMajorById(2);
        majorService.updateMajor(majorToUpdate);
        System.out.println("Updated Major: " + majorService.getMajorById(2).toString());

        System.out.println("Update Student with ID 2:");
        Student studentToUpdate = studentService.getStudentById(2);
        studentService.updateStudent(studentToUpdate);
        System.out.println("Updated Student: " + studentService.getStudentById(2).toString());

        System.out.println("Delete School with ID 1:");
        schoolService.deleteSchool(1);
        System.out.println("After deletion school with id 1: ");
        for (School school : schoolService.getAllSchools()) {
            System.out.println(school.toString());
        }

        System.out.println("Delete Major with ID 1:");
        majorService.deleteMajor(1);
        System.out.println("After deletion major with id 1: ");
        for (Major major : majorService.getAllMajors()) {
            System.out.println(major.toString());
        }

        System.out.println("Delete Student with ID 1:");
        studentService.deleteStudent(1);
        System.out.println("After deletion student with id 1: ");
        for (Student student : studentService.getAllStudents()) {
            System.out.println(student.toString());
        }
    }
}