package com.project.pma.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.pma.dao.EmployeeRepository;
import com.project.pma.entities.Employee;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {
	
	@Autowired
	EmployeeRepository empRepo;
	
	

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		System.out.println("Entered validation method...");
		
		Employee emp = empRepo.findByEmail(value);
		
		if(emp != null) {
			return false;
		}
		else
			return true;
	
	}

}
