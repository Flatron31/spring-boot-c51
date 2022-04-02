package com.example.springbootc51.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Value cannot be null")
    @Pattern(regexp = "^[-+]?[0-9]*[.,]?[0-9]+(?:[eE][-+]?[0-9]+)?$", message = "\n" +
            "Invalid value")
    private String value1;

    @NotNull (message = "Value cannot be null")
    @Pattern(regexp = "^[-+]?[0-9]*[.,]?[0-9]+(?:[eE][-+]?[0-9]+)?$", message = "\n" +
            "Invalid value")
    private String value2;

    @NotNull (message = "Operation cannot be null")
    private String operation;

    private double result;



}
