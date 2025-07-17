package org.tuyetdang.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.tuyetdang.Enums.Gender;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    long studentId;

    @Column(length = 100)
    String fullName;

    Gender gender;

    LocalDate yob;

    double gpa;

    int enrollmentYear;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "majorId")
    Major major;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schoolId")
    School school;

    public Student(String fullName, Gender gender, LocalDate yob, double gpa, int enrollmentYear) {
        this.fullName = fullName;
        this.gender = gender;
        this.yob = yob;
        this.gpa = gpa;
        this.enrollmentYear = enrollmentYear;
    }
}
