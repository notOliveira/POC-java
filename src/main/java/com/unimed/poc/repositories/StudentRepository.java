package com.unimed.poc.repositories;

import com.unimed.poc.models.Student;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {

    // Método para buscar alunos por ID da sala de aula
    @Query("SELECT s.* FROM student s INNER JOIN classroom_student cs ON s.id = cs.student_id WHERE cs.classroom_id = :classroomId")
    Flux<Student> findByClassroomId(Long classroomId);
}
