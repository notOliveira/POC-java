package com.unimed.poc.services;

import com.unimed.poc.exceptions.ClassroomNotFoundException;
import com.unimed.poc.models.Classroom;
import com.unimed.poc.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public Flux<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Mono<Classroom> getClassroomById(Long id) {
        return classroomRepository.findById(id)
                .switchIfEmpty(Mono.error(new ClassroomNotFoundException("Classroom with ID " + id + " not found")));
    }

    public Mono<Classroom> createClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public Mono<Classroom> updateClassroom(Long id, Classroom classroom) {
        classroom.setId(id);
        return classroomRepository.save(classroom);
    }

    public Mono<Void> deleteClassroom(Long id) {
        return classroomRepository.deleteById(id);
    }

    public Flux<Classroom> getClassroomsByStudentId(Long studentId) {
        return classroomRepository.findAll()
                .filter(classroom -> classroom.getStudents().stream()
                        .anyMatch(student -> student.getId().equals(studentId)));
    }
}
