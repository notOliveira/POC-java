package com.unimed.poc.repositories;

import com.unimed.poc.models.Teacher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends ReactiveCrudRepository<Teacher, Long> {

}
