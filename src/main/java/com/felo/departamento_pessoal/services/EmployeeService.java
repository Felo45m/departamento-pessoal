package com.felo.departamento_pessoal.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.felo.departamento_pessoal.model.entities.Employee;
import com.felo.departamento_pessoal.repositories.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	public Employee findById(UUID employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		return employee.orElseThrow(() -> new ObjectNotFoundException("Funcionário não encontrado", employeeId));
	}
	
	public Employee insertEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public void deleteEmployee(UUID employeeId) {
		try {
			employeeRepository.deleteById(employeeId);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Funcionário não encontrado", employeeId);
		}
	}
	
	public Employee updateEmployee (UUID employeeId, Employee employee) {
		try {
			Employee entity = employeeRepository.getReferenceById(employeeId);
			updateData(entity, employee);
			return employeeRepository.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ObjectNotFoundException("Funcionário não encontrado", employeeId);
		}
	}

	private void updateData(Employee entity, Employee employee) {
		entity.setName(employee.getName());
		entity.setBirthdate(employee.getBirthdate());
		entity.setCpf(employee.getCpf());
		entity.setGender(employee.getGender());
		entity.setOffice(employee.getOffice());
		entity.setRegistration(employee.getRegistration());
		entity.setStatus(employee.getStatus());
		
	}
	
}
