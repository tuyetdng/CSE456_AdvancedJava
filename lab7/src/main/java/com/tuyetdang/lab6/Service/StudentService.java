package com.tuyetdang.lab6.Service;

import com.tuyetdang.lab6.Dto.StudentRequestDto;
import com.tuyetdang.lab6.Entity.Major;
import com.tuyetdang.lab6.Entity.Student;
import com.tuyetdang.lab6.Mapper.StudentMapper;
import com.tuyetdang.lab6.Repository.MajorRepository;
import com.tuyetdang.lab6.Repository.StudentRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class StudentService {
    StudentRepository studentRepository;
    MajorRepository majorRepository;
    StudentMapper studentMapper;

    public Student createStudent(StudentRequestDto studentRequestDto) {
        Student student = studentMapper.toEntity(studentRequestDto);

        Major major = majorRepository.findById(studentRequestDto.getMajorId())
                .orElseThrow(() -> new RuntimeException("Major not found"));
        student.setMajor(major);

        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, StudentRequestDto studentRequestDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        studentMapper.updateStudent(student, studentRequestDto);

        if (studentRequestDto.getMajorId() != null) {
            Major major = majorRepository.findById(studentRequestDto.getMajorId())
                    .orElseThrow(() -> new RuntimeException("Major not found with id: " + studentRequestDto.getMajorId()));
            student.setMajor(major);
        }

        return studentRepository.save(student);
    }

   public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
