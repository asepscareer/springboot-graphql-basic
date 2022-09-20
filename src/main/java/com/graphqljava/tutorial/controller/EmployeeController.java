package com.graphqljava.tutorial.controller;

import com.graphqljava.tutorial.domain.Employee;
import com.graphqljava.tutorial.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@SchemaMapping("Employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @Cacheable(value = "employees")
    @QueryMapping
    public List<Employee> allEmployee() {
        return repository.findAll();
    }

    @Cacheable(value = "employees", key = "#id", unless = "#result == null")
    @QueryMapping
    public Employee employeeById(@Argument String id) {
        return repository.findById(id).orElse(null);
    }

    @CacheEvict(value = "employees", allEntries = true)
    @MutationMapping
    public Employee addEmployee(@Argument String id, @Argument String name) {
        if (repository.existsById(id)) {
            return null;
        } else {
            Employee employee = new Employee(id, name);
            return repository.save(employee);
        }
    }

    @Caching(
            evict = {@CacheEvict(value = "employees", allEntries = true)},
            put = {@CachePut(value = "employees", key = "#id")}
    )
    @MutationMapping
    public Employee updateEmployee(@Argument String id, @Argument String name) {
        if (!repository.existsById(id)) {
            return null;
        } else {
            Employee employee = new Employee(id, name);
            return repository.save(employee);
        }
    }

    @Caching(evict = {
            @CacheEvict(value = "employees", key = "#id"),
            @CacheEvict(value = "employees", allEntries = true)}
    )
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

