package com.felo.departamento_pessoal.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.felo.departamento_pessoal.model.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID employeeId;
	
	private Long registration;
	
	private String name;
	
	private Status status;
	
	private String cpf;
	
	private Date birthdate;
	
	private String gender;
	
	private String office;

}
