package com.graphqljava.tutorial.repository;

import com.graphqljava.tutorial.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {}
