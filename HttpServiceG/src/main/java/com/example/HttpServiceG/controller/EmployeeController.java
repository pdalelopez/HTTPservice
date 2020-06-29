package com.example.HttpServiceG.controller;

import com.example.HttpServiceG.domain.Employee;
import com.example.HttpServiceG.domain.JobPosition;
import com.example.HttpServiceG.presistence.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
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

    @GetMapping("/testpost")
    public String save ()
    {
        Employee employee= new Employee();
        employee.setId(36897L);
        employee.setName("pedro");
        employee.setPosition(boss);

        employeeRepository.save(employee);

        return "primer registro";
    }

    @GetMapping("/Employee")
    public List<Employee> getAll ()
    {
        return employeeRepository.findAll();
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

    @DeleteMapping ("/delete")
    public void delete (@PathVariable Long id)

    {
        employeeRepository.deleteById(id);
    }

}
