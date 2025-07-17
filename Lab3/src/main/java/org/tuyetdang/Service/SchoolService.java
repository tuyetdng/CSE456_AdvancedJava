package org.tuyetdang.Service;

import org.tuyetdang.Entity.School;
import org.tuyetdang.Repository.SchoolRepository;

import java.util.List;

public class SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public void addSchool(School school) {
        schoolRepository.create(school);
    }

    public void updateSchool(School school) {
        schoolRepository.update(school);
    }

    public School getSchoolById(long id) {
        return schoolRepository.findById(id);
    }

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public School getSchoolByName(String name) {
        return schoolRepository.findByName(name);
    }

    public void deleteSchool(long id) {
        schoolRepository.delete(id);
    }
}
