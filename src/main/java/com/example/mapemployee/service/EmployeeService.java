package com.example.mapemployee.service;
import com.example.mapemployee.model.Employee;
import java.util.Collection;
public interface EmployeeService {
    Employee add (String firstName, String lastName, Integer department, double salary);
    Employee remove (String firstName, String lastName);
    Employee find (String firstName, String lastName);
    Collection<Employee> findAll();

     Collection<Employee> getAll();

}

