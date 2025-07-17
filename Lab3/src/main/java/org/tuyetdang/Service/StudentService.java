package org.tuyetdang.Service;

import org.tuyetdang.Entity.Student;
import org.tuyetdang.Repository.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(Student student) {
        repository.create(student);
    }

    public void updateStudent(Student student) {
        repository.update(student);
    }

    public Student getStudentById(long id) {
        return repository.findById(id);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public List<Student> getStudentsByName(String name) {
        return repository.findByName(name);
    }

    public void deleteStudent(long id) {
        repository.delete(id);
    }

}
