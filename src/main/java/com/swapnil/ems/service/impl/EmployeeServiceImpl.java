package com.swapnil.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.swapnil.ems.dto.EmployeeDto;
import com.swapnil.ems.entity.Department;
import com.swapnil.ems.entity.Employee;
import com.swapnil.ems.exception.ResourceNotFoundException;
import com.swapnil.ems.mapper.EmployeeMapper;
import com.swapnil.ems.repository.DepartmentRepo;
import com.swapnil.ems.repository.EmployeeRepo;
import com.swapnil.ems.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepo employeeRepo;

	private DepartmentRepo departmentRepo;

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {

		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		
		Department department = departmentRepo.findById(employeeDto.getDepartmentId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Department does not exist with id: " + employeeDto.getDepartmentId()));
		
		employee.setDepartment(department);
		
		Employee savedEmployee = employeeRepo.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with id : " + employeeId));
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepo.findAll();
		return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with id : " + employeeId));
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setEmail(employeeDto.getEmail());
		
		Department department = departmentRepo.findById(employeeDto.getDepartmentId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Department does not exist with id: " + employeeDto.getDepartmentId()));
		
		employee.setDepartment(department);
		
		Employee updatedEmployee = employeeRepo.save(employee);
		return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with id : " + employeeId));
		employeeRepo.deleteById(employeeId);
	}

}
