package com.example.HttpServiceG.controller;

import com.example.HttpServiceG.domain.Employee;
import com.example.HttpServiceG.domain.JobPosition;
import com.example.HttpServiceG.presistence.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

import static com.example.HttpServiceG.domain.JobPosition.boss;

@RestController
public class EmployeeController {

    @Autowired
    public EmployeeRepository employeeRepository;

    EmployeeController (EmployeeRepository employeeRepository)
    {
        this.employeeRepository=employeeRepository;
    }

    @GetMapping("/test")
    public String save ()
    {
        Employee employee= new Employee();
        employee.setName("xavi");
        employee.setPosition(boss);

        employeeRepository.save(employee);

        return "registro prueba creado";
    }

    @GetMapping("/Employee")
    public List<Employee> getAll ()
    {
        return employeeRepository.findAll();
    }

    @GetMapping("/Employee/{position}")
    public List<Employee> getByPosition (@PathVariable JobPosition position)
    {
        List<Employee> byPositionList= new ArrayList<>();
        for (Employee employee: employeeRepository.findAll()) {
            if (employee.getPosition().equals(position)) {
                byPositionList.add(employee);
            }
        }

        return byPositionList;
    }

    @PostMapping("/Employee")

    public Employee save (@RequestBody Employee employee)
    {
        return employeeRepository.save(employee);
    }

    @PutMapping ("/Employee/{id}")
    public Employee update (@RequestBody Employee employee, @PathVariable Long id)
    {

        return employeeRepository.findById(id)
                .map(currentEmployee ->
                {
                    currentEmployee.setName(employee.getName());
                    currentEmployee.setPosition(employee.getPosition());
                return employeeRepository.save(currentEmployee);
                })
                .orElseGet(()->
                {
                    employee.setId(id);
                    return employeeRepository.save(employee);
                });
    }

    @DeleteMapping ("/delete/{id}")
    public String delete (@PathVariable Long id)

    {
        employeeRepository.deleteById(id);
        return "the employee #= "+ id+ " was successful deleted";
    }

}
