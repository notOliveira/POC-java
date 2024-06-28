package com.unimed.poc.services;

import com.unimed.poc.exceptions.ClassroomNotFoundException;
import com.unimed.poc.models.Classroom;
import com.unimed.poc.models.Student;
import com.unimed.poc.models.Teacher;
import com.unimed.poc.repositories.ClassroomRepository;
import com.unimed.poc.repositories.StudentRepository;
import com.unimed.poc.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.classroomRepository = classroomRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    public Flux<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Mono<Classroom> getClassroomById(Long id) {
        return classroomRepository.findById(id)
                .switchIfEmpty(Mono.error(new ClassroomNotFoundException("Classroom with ID " + id + " not found")))
                .flatMap(classroom -> populateTeacherAndStudents(classroom));
    }

    private Mono<Classroom> populateTeacherAndStudents(Classroom classroom) {
        Mono<Teacher> teacherMono = classroom.getTeacher() != null ?
                teacherRepository.findById(classroom.getTeacher().getId()) :
                Mono.just(new Teacher());  // handle case where teacher is null or not found

        return teacherMono
                .flatMap(teacher -> {
                    classroom.setTeacher(teacher);
                    return populateStudents(classroom);
                });
    }

    private Mono<Classroom> populateStudents(Classroom classroom) {
        return studentRepository.findByClassroomId(classroom.getId())
                .collectList()
                .map(students -> {
                    classroom.setStudents(students);
                    return classroom;
                });
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
        return classroomRepository.findByStudentId(studentId)
                .switchIfEmpty(Flux.error(new ClassroomNotFoundException("No classrooms found for student ID: " + studentId)));
    }
}
