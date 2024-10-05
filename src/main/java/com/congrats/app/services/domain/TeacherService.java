package com.congrats.app.services.domain;

import com.congrats.app.models.entities.TeacherEntity;
import com.congrats.app.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<TeacherEntity> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<TeacherEntity> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    public TeacherEntity saveTeacher(TeacherEntity teacher) {
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public TeacherEntity updateTeacher(Long id, TeacherEntity teacherDetails) {
        return teacherRepository.findById(id).map(teacher -> {
            teacher.setName(teacherDetails.getName());
            teacher.setEmail(teacherDetails.getEmail());
            teacher.setPhone(teacherDetails.getPhone());
            teacher.setAddress(teacherDetails.getAddress());
            teacher.setBirthdate(teacherDetails.getBirthdate());
            return teacherRepository.save(teacher);
        }).orElseGet(() -> {
            teacherDetails.setId(id);
            return teacherRepository.save(teacherDetails);
        });
    }
}