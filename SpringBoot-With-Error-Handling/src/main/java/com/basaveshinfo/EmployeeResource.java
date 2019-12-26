package com.basaveshinfo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class EmployeeResource {

	@Autowired
	EmployeeRepo employeeRepo;
	

	@RequestMapping("/testerror")
	public void errorHandle() {
		//throw new RuntimeException("Something went wrong");
		throw new NullPointerException("Input resquest body is null");
	}

	
	@RequestMapping("/addemployee")
	public Employee addOneEmp(@RequestBody Employee emp) {
		
		System.out.println("*******" +emp);
		
		if(emp == null) {
			throw new NullPointerException("Input resquest body is null");
		}
		return employeeRepo.save(emp);
	}
	
	@RequestMapping("/addemployees")
	public void addEmployee() {
		employeeRepo.save(new Employee("Basavesh",75000,28));
		employeeRepo.save(new Employee("Suresh",70000,35));
		employeeRepo.save(new Employee("Ramesh",65000,38));
		employeeRepo.save(new Employee("Arun",78000,30));
		employeeRepo.save(new Employee("Karthik",50000,25));
		employeeRepo.save(new Employee("Akash",25000,29));
		employeeRepo.save(new Employee("Pavan",15000,40));
		employeeRepo.save(new Employee("Varun",35000,34));
		
		
	}
	
	@RequestMapping("/employees")
	public ResponseEntity<List<Employee>> getAll(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("ResponseName", "get all employees");
		return new ResponseEntity<List<Employee>>(employeeRepo.findAll(), headers, HttpStatus.OK);
		
	}
	
	@RequestMapping("/employees/{id}")
	public Optional<Employee> getEmployee(@PathVariable("id") int id) {
		return employeeRepo.findById(id);
	}
	
	
}
