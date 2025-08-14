package com.tuyetdang.lab6.Service;
import com.tuyetdang.lab6.Entity.Major;
import com.tuyetdang.lab6.Repository.MajorRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class MajorService {
    MajorRepository majorRepository;

    public List<Major> getMajors() {
        return majorRepository.findAll();
    }
}

