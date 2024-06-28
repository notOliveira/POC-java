package com.unimed.poc.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Student {

    @Id
    private Long id;
    private String name;
    private String email;
}
