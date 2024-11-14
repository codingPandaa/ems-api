package com.swapnil.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapnil.ems.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
}
