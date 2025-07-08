package Model.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    String id;
    String firstName;
    String lastName;
    int yearOfBirth;
    double gpa;
}
