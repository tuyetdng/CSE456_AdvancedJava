package org.tuyetdang.Entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 3)
    long schoolId;
    @Column(length = 100, nullable = false)
    String schoolName;
    @Column(length = 100)
    String location;
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    List<Major> majors = new ArrayList<>();

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    List<Student> students = new ArrayList<>();

    public void addMajor(Major major) {
        majors.add(major);
        major.setSchool(this);
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setSchool(this);
    }
    public School(String schoolName, String location) {
        this.schoolName = schoolName;
        this.location = location;
    }
}
