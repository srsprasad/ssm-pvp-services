package org.ssm.app.pvp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssm.app.pvp.data.StudentData;
import org.ssm.app.pvp.service.StudentDataService;

@RestController
public class StudentController {

	@Autowired
	private StudentDataService studentDataService;
	
	@GetMapping(path = "/student/all", produces = "application/json")
	public ResponseEntity<List<StudentData>> getAll() {
		return new ResponseEntity<List<StudentData>>(studentDataService.getAllStudentRegistrationData(), HttpStatus.OK);
	}
}
