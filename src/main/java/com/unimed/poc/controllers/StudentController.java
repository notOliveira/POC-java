package com.unimed.poc.controllers;

import com.unimed.poc.models.Student;
import com.unimed.poc.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public Flux<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/")
    public Mono<Student> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/{id}")
    public Mono<Student> getStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public Mono<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteStudent(@PathVariable("id") Long id) {
        return studentService.deleteStudent(id);
    }
}