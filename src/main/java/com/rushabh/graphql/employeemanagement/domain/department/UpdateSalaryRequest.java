package com.rushabh.graphql.employeemanagement.domain.department;

public class UpdateSalaryRequest {

    private Long employeeId;
    private Long salary;

    public UpdateSalaryRequest() {
        super();
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
