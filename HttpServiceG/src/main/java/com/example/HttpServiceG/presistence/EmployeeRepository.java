package com.example.HttpServiceG.presistence;

import com.example.HttpServiceG.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
