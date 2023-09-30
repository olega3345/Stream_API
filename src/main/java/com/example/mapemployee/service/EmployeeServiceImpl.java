package com.example.mapemployee.service;


import com.example.mapemployee.exceptions.EmployeeNotFoundException;
import com.example.mapemployee.exceptions.EmployeeAlreadyAddedException;
import com.example.mapemployee.exceptions.InvalidInputException;
import com.example.mapemployee.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {
     private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName, Integer department, double salary) {

        validateInput(firstName, lastName);

        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        return employees.put(employee.getFullName(), employee);

    }

    @Override
    public Employee remove(String firstName, String lastName) {
        validateInput(firstName, lastName);

        if (employees.containsKey(getFullName(firstName, lastName))) {
           return employees.remove(getFullName(firstName, lastName));
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
      validateInput(firstName, lastName);

        if (employees.containsKey(getFullName(firstName, lastName))) {
            return employees.get(getFullName(firstName, lastName));
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    @Override
    public Collection<Employee> getAll() {
        return employees.values();
    }

    public String getFullName(String firstName, String lastName) {
        return (firstName + lastName).toLowerCase();
    }

    private void validateInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName)));
        throw new InvalidInputException();
    }
}

