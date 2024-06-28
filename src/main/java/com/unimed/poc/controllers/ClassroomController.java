package com.unimed.poc.controllers;

import com.unimed.poc.models.Classroom;
import com.unimed.poc.services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    public Mono<Classroom> getClassroomById(@PathVariable Long id) {
        return classroomService.getClassroomById(id);
    }

    @GetMapping
    public Flux<Classroom> getAllClassrooms() {
        return classroomService.getAllClassrooms();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Classroom> createClassroom(@RequestBody Classroom classroom) {
        return classroomService.createClassroom(classroom);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Classroom>> updateClassroom(@PathVariable Long id, @RequestBody Classroom classroom) {
        return classroomService.updateClassroom(id, classroom)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteClassroom(@PathVariable Long id) {
        return classroomService.deleteClassroom(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/students/{studentId}")
    public Flux<Classroom> getClassroomsByStudentId(@PathVariable Long studentId) {
        return classroomService.getClassroomsByStudentId(studentId);
    }
}
