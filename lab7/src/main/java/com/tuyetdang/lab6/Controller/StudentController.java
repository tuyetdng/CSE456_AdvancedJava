package com.tuyetdang.lab6.Controller;

import com.tuyetdang.lab6.Dto.StudentRequestDto;
import com.tuyetdang.lab6.Entity.Student;
import com.tuyetdang.lab6.Mapper.StudentMapper;
import com.tuyetdang.lab6.Service.MajorService;
import com.tuyetdang.lab6.Service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final MajorService majorService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "index";
    }
    @GetMapping("/student/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);

        StudentRequestDto studentDto = new StudentRequestDto();
        studentDto.setName(student.getName());
        studentDto.setYob(student.getYob());
        studentDto.setGpa(student.getGpa());
        studentDto.setMajorId(student.getMajor().getMajorId());

        model.addAttribute("studentId", id);
        model.addAttribute("student", studentDto);
        model.addAttribute("majors", majorService.getMajors());

        return "edit_student";
    }

    @PostMapping("/student/update/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") StudentRequestDto studentDto) {
        studentService.updateStudent(id, studentDto);
        return "redirect:/";
    }
    @GetMapping("/student/create")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new StudentRequestDto());
        model.addAttribute("majors", majorService.getMajors());
        return "create_student";
    }

    @PostMapping("/student/create")
    public String createStudent(@ModelAttribute("student") StudentRequestDto studentDto) {
        studentService.createStudent(studentDto);
        return "redirect:/";
    }

    @PostMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/";
    }

}
