package com.unimed.poc.repositories;

import com.unimed.poc.models.Teacher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TeacherRepository extends ReactiveCrudRepository<Teacher, Long> {
    // Método para buscar professor por ID
    Mono<Teacher> findById(Long id);
}
