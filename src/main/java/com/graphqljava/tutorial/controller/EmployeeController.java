package com.graphqljava.tutorial.controller;

import com.graphqljava.tutorial.domain.Employee;
import com.graphqljava.tutorial.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@SchemaMapping ("Employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;


    @QueryMapping
    public List<Employee> allEmployee() {
        return repository.findAll();
    }

    @QueryMapping
    public Employee employeeById(@Argument String id) {
        return repository.findById(id).orElse(null);
    }

    @MutationMapping
    public Employee addEmployee(@Argument String id, @Argument String name) {
        if (repository.existsById(id)) {
            return null;
        } else {
            Employee employee = new Employee(id, name);
            return repository.save(employee);
        }
    }

    @MutationMapping
    public Employee updateEmployee(@Argument String id, @Argument String name) {
        if (!repository.existsById(id)) {
            return null;
        } else {
            Employee employee = new Employee(id, name);
            return repository.save(employee);
        }
    }

    @MutationMapping
    public String deleteEmployee(@Argument String id) {
        if (!repository.existsById(id)) {
            return null;
        } else {
            repository.deleteById(id);
            return "Success";
        }
    }

}

