package com.example.springbootc51.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "OPERATIONS")
public class Operation {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull (message = "Value cannot be null")
    @Pattern(regexp = "^[-+]?[0-9]*[.,]?[0-9]+(?:[eE][-+]?[0-9]+)?$", message = "\n" +
            "Invalid value")
    private String value1;

    @NotNull (message = "Value cannot be null")
    @Pattern(regexp = "^[-+]?[0-9]*[.,]?[0-9]+(?:[eE][-+]?[0-9]+)?$", message = "\n" +
            "Invalid value")
    private String value2;

    @NotNull (message = "Operation cannot be null")
    private String action;

    private double result;

    public Operation() {
    }

    public Operation(String value1, String value2, String action) {
        this.value1 = value1;
        this.value2 = value2;
        this.action = action;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String operation) {
        this.action = operation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                ", action='" + action + '\'' +
                ", result=" + result +
                '}';
    }
}
