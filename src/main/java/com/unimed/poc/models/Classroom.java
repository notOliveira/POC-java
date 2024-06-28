package com.unimed.poc.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Classroom {
    @Id
    private Long id;
    private String name;
    private Teacher teacher;
    private List<Student> students;
}
