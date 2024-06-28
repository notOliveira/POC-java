package com.unimed.poc.repositories;

import com.unimed.poc.models.Classroom;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ClassroomRepository extends ReactiveCrudRepository<Classroom, Long> {
    @Query("SELECT * FROM Classroom c WHERE EXISTS (SELECT 1 FROM Classroom_Student cs WHERE c.id = cs.classroom_id AND cs.student_id = :studentId)")
    Flux<Classroom> findByStudentId(Long studentId);
}
