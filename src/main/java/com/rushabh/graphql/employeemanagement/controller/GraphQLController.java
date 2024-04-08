package com.rushabh.graphql.employeemanagement.controller;

import com.rushabh.graphql.employeemanagement.domain.department.AddEmployeeRequest;
import com.rushabh.graphql.employeemanagement.domain.department.UpdateSalaryRequest;
import com.rushabh.graphql.employeemanagement.entity.Department;
import com.rushabh.graphql.employeemanagement.entity.Employee;
import com.rushabh.graphql.employeemanagement.exception.EmployeeManagementException;
import com.rushabh.graphql.employeemanagement.service.IEmployeeService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GraphQLController {

    private final IEmployeeService employeeService;

    public GraphQLController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @QueryMapping
    List<Employee> getEmployeesByName(@Argument String name) throws EmployeeManagementException {
        return employeeService.getEmployeesByName(name);
    }

    @QueryMapping
    List<Department> getAllDepartment() throws EmployeeManagementException {
        return employeeService.getAllDepartments();
    }

    @QueryMapping
    List<Employee> allEmployee() throws EmployeeManagementException {
        return employeeService.getAllEmployees();
    }

    @MutationMapping
    Employee updateSalary(@Argument UpdateSalaryRequest updateSalaryRequest) throws EmployeeManagementException {
        return employeeService.updateEmployeeSalary(updateSalaryRequest);
    }

    @MutationMapping
    Employee addEmployee(@Argument AddEmployeeRequest addEmployeeRequest) throws EmployeeManagementException {
        return employeeService.addEmployee(addEmployeeRequest);
    }

}
