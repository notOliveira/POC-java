package com.unimed.poc.controllers;

import com.unimed.poc.models.Classroom;
import com.unimed.poc.models.Student;
import com.unimed.poc.services.ClassroomService;
import com.unimed.poc.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final ClassroomService classroomService;

    @Autowired
    public StudentController(StudentService studentService, ClassroomService classroomService) {
        this.studentService = studentService;
        this.classroomService = classroomService;
    }

    @GetMapping("/")
    public Flux<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/create")
    public Mono<Student> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Student>> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
//                .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting student")));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Student>> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
//                .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating student")));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteStudent(@PathVariable("id") Long id) {
        return studentService.deleteStudent(id);
    }

    @PostMapping("/classrooms")
    public Flux<Classroom> getStudentClassrooms(@PathVariable Long id) {
        return classroomService.getAllClassrooms();
    }
}