package org.tuyetdang.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Subject {
    @Id
    @Column(length = 10)
    String code;
    @Column(length = 50)
    String name;
    @Column(length = 255)
    String description;
    int credits;
    int studyHours;
}
