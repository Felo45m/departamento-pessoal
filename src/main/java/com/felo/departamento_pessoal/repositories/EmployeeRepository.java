package com.felo.departamento_pessoal.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felo.departamento_pessoal.model.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID>{

}
