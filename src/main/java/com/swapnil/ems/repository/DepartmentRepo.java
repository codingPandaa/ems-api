package com.swapnil.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapnil.ems.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

}
