package com.tuyetdang.lab6.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long studentId;
    String name;
    int yob;
    double gpa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "majorId")
    Major major;


    public Student(String name, int yob, double gpa) {
        this.name = name;
        this.yob = yob;
        this.gpa = gpa;
    }
}
