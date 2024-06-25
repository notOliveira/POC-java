package com.unimed.poc.repositories;

import com.unimed.poc.models.Classroom;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends ReactiveCrudRepository<Classroom, Long> {

}
