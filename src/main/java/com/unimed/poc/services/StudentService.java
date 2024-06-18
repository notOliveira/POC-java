package com.unimed.poc.services;

import com.unimed.poc.models.Student;
import com.unimed.poc.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return studentRepository.findById(id);
    }

    public Mono<Student> createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Mono<Student> updateStudent(Long id, Student student) {
        student.setId(id); // Ensure the correct ID is set for update
        return studentRepository.save(student);
    }

    public Mono<Void> deleteStudent(Long id) {
        return studentRepository.deleteById(id);
    }
}