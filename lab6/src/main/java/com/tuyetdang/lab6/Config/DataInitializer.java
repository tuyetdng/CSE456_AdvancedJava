package com.tuyetdang.lab6.Config;

import com.tuyetdang.lab6.Entity.Major;
import com.tuyetdang.lab6.Entity.Student;
import com.tuyetdang.lab6.Repository.MajorRepository;
import com.tuyetdang.lab6.Repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(MajorRepository majorRepository, StudentRepository studentRepository) {
        return args -> {
            if (majorRepository.count() == 0 && studentRepository.count() == 0) {
                Major it = new Major("IT");
                Major business = new Major("BSS");

                majorRepository.saveAll(List.of(it, business));

                Student s1 = new Student("Nguyen Van A", 2001, 3.5);
                Student s2 = new Student("Tran Thi B", 2002, 3.8);
                Student s3 = new Student("Le Van C", 2000, 3.2);

                s1.setMajor(it);
                s2.setMajor(it);
                s3.setMajor(business);

                studentRepository.saveAll(List.of(s1, s2, s3));

                System.out.println("Student and Major data initialized successfully.");
            }
        };
    }
}
