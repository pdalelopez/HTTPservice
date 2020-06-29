package com.example.HttpServiceG.domain;

public enum JobPosition {
    boss(3000),
    manager(2000),
    inter(0),
    technician(1000);

    private int salary;

    private JobPosition( int salary)
    {
        this.salary =salary;
    }

    public int getSalary()
    {
        return salary;
    }
}
