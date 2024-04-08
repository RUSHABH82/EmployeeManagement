package com.rushabh.graphql.employeemanagement.repository;

import com.rushabh.graphql.employeemanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByNameContains(String name);
}
