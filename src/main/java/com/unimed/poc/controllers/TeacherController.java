package com.unimed.poc.controllers;

import com.unimed.poc.models.Teacher;
import com.unimed.poc.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/")
    public Flux<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PostMapping("/")
    public Mono<Teacher> createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Teacher>> getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
//                .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting student")));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Teacher>> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        return teacherService.updateTeacher(id, teacher)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
//                .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating student")));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTeacher(@PathVariable("id") Long id) {
        return teacherService.deleteTeacher(id);
    }
}