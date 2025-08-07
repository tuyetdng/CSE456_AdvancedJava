package com.tuyetdang.lab6.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRequestDto {
    String name;
    int yob;
    double gpa;
    Long majorId;
}
