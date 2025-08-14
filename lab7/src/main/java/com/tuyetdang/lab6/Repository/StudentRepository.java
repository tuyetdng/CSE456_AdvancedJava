package com.tuyetdang.lab6.Repository;

import com.tuyetdang.lab6.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
