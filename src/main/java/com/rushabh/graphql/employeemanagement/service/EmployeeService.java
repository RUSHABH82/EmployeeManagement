package com.rushabh.graphql.employeemanagement.service;

import com.rushabh.graphql.employeemanagement.constance.EmployeeManagementConst;
import com.rushabh.graphql.employeemanagement.domain.department.AddEmployeeRequest;
import com.rushabh.graphql.employeemanagement.domain.department.UpdateSalaryRequest;
import com.rushabh.graphql.employeemanagement.entity.Department;
import com.rushabh.graphql.employeemanagement.entity.Employee;
import com.rushabh.graphql.employeemanagement.exception.EmployeeManagementException;
import com.rushabh.graphql.employeemanagement.repository.DepartmentRepository;
import com.rushabh.graphql.employeemanagement.repository.EmployeeRepository;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }


    @Override
    public List<Employee> getEmployeesByName(String name) throws EmployeeManagementException {
        try {
            return employeeRepository.findAllByNameContains(name);
        } catch (Exception e) {
            LOGGER.error(
                    EmployeeManagementConst.CLS_MET_ERROR, EmployeeService.class, "getEmployeesByName",
                    ExceptionUtils.getStackTrace(e));
            throw new EmployeeManagementException("Cannot get employees by name, Try again later!");
        }
    }

    @Override
    public List<Employee> getAllEmployees() throws EmployeeManagementException {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            LOGGER.error(
                    EmployeeManagementConst.CLS_MET_ERROR, EmployeeService.class, "getEmployeesByName",
                    ExceptionUtils.getStackTrace(e));
            throw new EmployeeManagementException("Cannot get employees by name, Try again later!");
        }
    }

    @Override
    public Employee updateEmployeeSalary(UpdateSalaryRequest updateSalaryRequest) throws EmployeeManagementException {
        try {
            Employee employee = getEmployeeById(updateSalaryRequest.getEmployeeId());
            employee.setSalary(updateSalaryRequest.getSalary());
            return employeeRepository.save(employee);
        } catch (EmployeeManagementException emException) {
            throw emException;
        } catch (Exception e) {
            throw new EmployeeManagementException("Employee salary cannot update. Try again!");
        }
    }

    @Override
    public Employee addEmployee(AddEmployeeRequest addEmployeeRequest) throws EmployeeManagementException {
        try {
            Employee employee = generateEmployeeFromRequest(addEmployeeRequest);
            return employeeRepository.save(employee);
        } catch (EmployeeManagementException emException) {
            throw emException;
        } catch (Exception e) {
            throw new EmployeeManagementException("Employee cannot add. Try again!");
        }
    }


    @Override
    public List<Department> getAllDepartments() throws EmployeeManagementException {
        try {
            return departmentRepository.findAll();

        } catch (Exception e) {
            LOGGER.error(EmployeeManagementConst.CLS_MET_ERROR, EmployeeService.class, "getEmployeesByName",
                    ExceptionUtils.getStackTrace(e));
            throw new EmployeeManagementException("Cannot get all departments, Try again later!");
        }
    }

    private Employee getEmployeeById(Long id) throws EmployeeManagementException {
        return employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeManagementException("Employee Not found by given Id .".concat(String.valueOf(id)),
                        HttpStatus.BAD_REQUEST));
    }

    private Department getDepartmentById(Long id) throws EmployeeManagementException {
        return departmentRepository.findById(id).orElseThrow(
                () -> new EmployeeManagementException("Department Not found by given Id .".concat(String.valueOf(id)),
                        HttpStatus.BAD_REQUEST));
    }


    private Employee generateEmployeeFromRequest(AddEmployeeRequest addEmployeeRequest)
            throws EmployeeManagementException {
        Employee employee = new Employee();
        employee.setName(addEmployeeRequest.getName());
        employee.setSalary(addEmployeeRequest.getSalary());
        employee.setDepartment(getDepartmentById(addEmployeeRequest.getDepartmentId()));
        return employee;
    }
}
