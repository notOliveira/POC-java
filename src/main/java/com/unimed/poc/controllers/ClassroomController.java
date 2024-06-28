package com.unimed.poc.controllers;

import com.unimed.poc.models.Classroom;
import com.unimed.poc.services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping("/")
    public Flux<Classroom> getAllClassrooms() {
        return classroomService.getAllClassrooms();
    }

    @PostMapping("/")
    public Mono<Classroom> createClassroom(@RequestBody Classroom classroom) {
        return classroomService.createClassroom(classroom);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Classroom>> getClassroomById(@PathVariable Long id) {
        return classroomService.getClassroomById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(e -> Mono.error(new RuntimeException("Error getting classroom")));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Classroom>> updateClassroom(@PathVariable Long id, @RequestBody Classroom classroom) {
        return classroomService.updateClassroom(id, classroom)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(e -> Mono.error(new RuntimeException("Error updating classroom")));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteClassroom(@PathVariable("id") Long id) {
        return classroomService.deleteClassroom(id);
    }

    @GetMapping("/student/{studentId}")
    public Flux<Classroom> findClassroomsByStudentId(@PathVariable Long studentId) {
        return classroomService.getClassroomsByStudentId(studentId);
    }
}
