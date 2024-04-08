package com.rushabh.graphql.employeemanagement.repository;

import com.rushabh.graphql.employeemanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
