package org.ssm.app.pvp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssm.app.pvp.data.StudentServiceResponse;
import org.ssm.app.pvp.service.StudentDataService;

@RestController
public class StudentController {

	@Autowired
	private StudentDataService studentDataService;
	
	@CrossOrigin(origins = {"http://srsprasad.github.io", "http://localhost:4200", "https://localhost:4200"})
	@GetMapping(path = "/student/all", produces = "application/json")
	public ResponseEntity<StudentServiceResponse> getAll() {
		return new ResponseEntity<StudentServiceResponse>(studentDataService.getAllStudentRegistrationData(), HttpStatus.OK);
	}
}
