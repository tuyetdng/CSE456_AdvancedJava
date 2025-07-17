package org.tuyetdang.Service;

import org.tuyetdang.Entity.Major;
import org.tuyetdang.Repository.MajorRepository;

import java.util.List;

public class MajorService {
    private final MajorRepository majorRepository;

    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    public void addMajor(Major major) {
        majorRepository.create(major);
    }

    public void updateMajor(Major major) {
        majorRepository.update(major);
    }

    public Major getMajorById(long id) {
        return majorRepository.findById(id);
    }

    public List<Major> getAllMajors() {
        return majorRepository.findAll();
    }

    public List<Major> getMajorsByName(String name) {
        return majorRepository.findByName(name);
    }

    public void deleteMajor(long id) {
        majorRepository.delete(id);
    }

}
