package com.unimed.poc.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import java.util.List;

@Getter
@Setter
public class Classroom {
    @Id
    public long id;
    public String name;
    public Teacher teacher;
    public List<Student> students;
}
