package com.rushabh.graphql.employeemanagement.service;

import com.rushabh.graphql.employeemanagement.domain.department.AddEmployeeRequest;
import com.rushabh.graphql.employeemanagement.domain.department.UpdateSalaryRequest;
import com.rushabh.graphql.employeemanagement.entity.Department;
import com.rushabh.graphql.employeemanagement.entity.Employee;
import com.rushabh.graphql.employeemanagement.exception.EmployeeManagementException;

import java.util.List;

public interface IEmployeeService {

    List<Employee> getEmployeesByName(String name) throws EmployeeManagementException;

    List<Employee> getAllEmployees() throws EmployeeManagementException;

    Employee updateEmployeeSalary(UpdateSalaryRequest updateSalaryRequest) throws EmployeeManagementException;

    Employee addEmployee(AddEmployeeRequest addEmployeeRequest) throws EmployeeManagementException;

    List<Department> getAllDepartments() throws EmployeeManagementException;

}
