package com.unimed.poc.services;

import com.unimed.poc.exceptions.StudentNotFoundException;
import com.unimed.poc.models.Classroom;
import com.unimed.poc.models.Student;
import com.unimed.poc.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Flux<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Mono<Student> getStudentById(Long id) {
        return studentRepository.findById(id)
                .switchIfEmpty(Mono.error(new StudentNotFoundException("Student with ID " + id + " not found")));
    }

    public Mono<Student> createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Mono<Student> updateStudent(@PathVariable Long id, Student student) {
        return studentRepository.save(student);
    }

    public Mono<Void> deleteStudent(Long id) {
        return studentRepository.deleteById(id);
    }
}