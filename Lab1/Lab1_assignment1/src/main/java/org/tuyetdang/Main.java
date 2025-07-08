package org.tuyetdang;

import Model.Entity.Course;
import Model.Entity.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Student student = new Student("001", "Tuyet", "Dang", 2003, 3.5);

        System.out.println(student.toString());
        System.out.println("Student in JSON: " + mapper.writeValueAsString(student));
        String str = """
                {"id":"002","firstName":"Anh","lastName":"Thi","yearOfBirth":2003,"gpa":3.6}""";
        System.out.println("Student in JSON to Object: " + mapper.readValue(str, Student.class).toString());

        Course course = Course.builder()
                .idCourse("1313")
                .name("Math")
                .credit(4)
                .hours(30)
                .build();
        System.out.println(course.toString());
        System.out.println("Course in JSON: " + mapper.writeValueAsString(course));

    }
}