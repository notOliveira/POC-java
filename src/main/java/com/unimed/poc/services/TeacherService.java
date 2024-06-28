package com.unimed.poc.services;

import com.unimed.poc.exceptions.TeacherNotFoundException;
import com.unimed.poc.models.Teacher;
import com.unimed.poc.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository TeacherRepository) {
        this.teacherRepository = TeacherRepository;
    }

    public Flux<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Mono<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .switchIfEmpty(Mono.error(new TeacherNotFoundException("Teacher with ID " + id + " not found")));
    }

    public Mono<Teacher> createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Mono<Teacher> updateTeacher(@PathVariable Long id, Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Mono<Void> deleteTeacher(Long id) {
        return teacherRepository.deleteById(id);
    }
}