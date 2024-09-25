package com.felo.departamento_pessoal.controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felo.departamento_pessoal.model.entities.Employee;
import com.felo.departamento_pessoal.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/funcionarios")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			
			errors.put(fieldName, errorMessage);			
		});
		
		return errors;
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> List = employeeService.findAll();
		return ResponseEntity.ok().body(List);
	}
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<Employee> findById(@PathVariable UUID employeeId) {
		Employee employee = employeeService.findById(employeeId);
		return ResponseEntity.ok().body(employee);
	}
	
	@PostMapping
	public ResponseEntity<Employee> insertEmployee(@RequestBody @Valid Employee employee) {
		employee = employeeService.insertEmployee(employee);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("{employeeId}")
					.buildAndExpand(employee.getEmployeeId())
					.toUri();
		return ResponseEntity.created(uri).body(employee);
	}
	
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable UUID employeeId) {
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Employee> updateEmployee(@PathVariable UUID employeeId, @RequestBody @Valid Employee employee) {
		employee = employeeService.updateEmployee(employeeId, employee);
		return ResponseEntity.ok().body(employee);
	}
}
