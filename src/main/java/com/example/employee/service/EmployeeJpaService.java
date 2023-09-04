/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 */

// Write your code here
package com.example.employee.service;

import java.util.ArrayList;
import java.util.List;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeJpaRepository;
import com.example.employee.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeJpaService implements EmployeeRepository{

    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

	@Override
	public ArrayList<Employee> getAllEmployees() {
		List<Employee> employeeList = employeeJpaRepository.findAll();
		ArrayList<Employee> employees = new ArrayList<>(employeeList);
		return employees;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		employeeJpaRepository.save(employee);

		return employee;
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		try{
			Employee employee = employeeJpaRepository.findById(employeeId).get();
			return employee;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Employee updateEmployee(Employee employee, int employeeId) {
		try{
			Employee updatedEmployee = employeeJpaRepository.findById(employeeId).get();
			if(employee.getEmployeeName() != null){
				updatedEmployee.setEmployeeName(employee.getEmployeeName());
			}
			if(employee.getEmail() != null){
				updatedEmployee.setEmail(employee.getEmail());
			}
			if(employee.getDepartment() != null){
				updatedEmployee.setDepartment(employee.getDepartment());
			}
			employeeJpaRepository.save(updatedEmployee);
			return updatedEmployee;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public ArrayList<Employee> deleteEmployee(int employeeId) {
		
		try{
			employeeJpaRepository.deleteById(employeeId);
			return getAllEmployees();
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

}
