// Write your code here
package com.example.employee.repository;
import com.example.employee.model.Employee;
import java.util.ArrayList;

public interface EmployeeRepository{
    ArrayList<Employee> getAllEmployees();
    Employee addEmployee(Employee employee);
    Employee getEmployeeById(int employeeId);
    Employee updateEmployee(Employee employee, int employeeId);
    ArrayList<Employee> deleteEmployee(int employeeId);
}