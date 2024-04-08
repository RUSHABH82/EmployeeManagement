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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class RestV1Controller {

    private final IEmployeeService employeeService;

    public RestV1Controller(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("employee")
    List<Employee> getEmployeesByName(@RequestParam(defaultValue = "") String name) throws EmployeeManagementException {
        return employeeService.getEmployeesByName(name);
    }

    @GetMapping("department")
    List<Department> getAllDepartment() throws EmployeeManagementException {
        return employeeService.getAllDepartments();
    }

    @PutMapping("employee")
    Employee updateSalary(@RequestBody UpdateSalaryRequest updateSalaryRequest) throws EmployeeManagementException {
        return employeeService.updateEmployeeSalary(updateSalaryRequest);
    }

    @PostMapping
    Employee addEmployee(@RequestBody AddEmployeeRequest addEmployeeRequest) throws EmployeeManagementException {
        return employeeService.addEmployee(addEmployeeRequest);
    }

}
