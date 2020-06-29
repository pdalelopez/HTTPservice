package com.example.HttpServiceG.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id @GeneratedValue Long id;
    String name;
    JobPosition position;
    int salary;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JobPosition getPosition() {
        return position;
    }

    public void setPosition(JobPosition position) {
        this.position = position;
        this.salary = position.getSalary();
    }

    public int getSalary() {
        return salary;
    }

}
