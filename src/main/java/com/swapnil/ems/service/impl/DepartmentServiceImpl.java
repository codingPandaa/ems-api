package com.swapnil.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.swapnil.ems.dto.DepartmentDto;
import com.swapnil.ems.entity.Department;
import com.swapnil.ems.exception.ResourceNotFoundException;
import com.swapnil.ems.mapper.DepartmentMapper;
import com.swapnil.ems.repository.DepartmentRepo;
import com.swapnil.ems.service.DepartmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepo departmentRepo;

	@Override
	public DepartmentDto createDepartment(DepartmentDto departmentDto) {

		Department department = DepartmentMapper.mapToDepartment(departmentDto);
		Department updatedDepartment = departmentRepo.save(department);
		return DepartmentMapper.mapToDepartmentDto(updatedDepartment);
	}

	@Override
	public DepartmentDto getDepartmentById(Long departmentId) {

		Department department = departmentRepo.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Department does not exists with the given id: " + departmentId));
		return DepartmentMapper.mapToDepartmentDto(department);
	}

	@Override
	public List<DepartmentDto> getAllDepartments() {

		List<Department> departments = departmentRepo.findAll();
		return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department))
				.collect(Collectors.toList());
	}

	@Override
	public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {

		Department department = departmentRepo.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Department does not exists with the given id: " + departmentId));
		department.setDepartmentName(updatedDepartment.getDepartmentName());
		department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());
		Department savedDepartment = departmentRepo.save(department);
		return DepartmentMapper.mapToDepartmentDto(savedDepartment);
	}

	@Override
	public void deleteDepartment(Long departmentId) {

		Department department = departmentRepo.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Department does not exists with the given id: " + departmentId));
		departmentRepo.deleteById(departmentId);

	}

}
