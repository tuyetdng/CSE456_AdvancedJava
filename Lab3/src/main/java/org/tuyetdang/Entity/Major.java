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
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 3)
    long majorId;

    @Column(length = 100, nullable = false)
    String majorName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schoolId")
    School school;

    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
        student.setMajor(this);
        student.setSchool(school);
    }

    public Major(String majorName) {
        this.majorName = majorName;
    }
}
