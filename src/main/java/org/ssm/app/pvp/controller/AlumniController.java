package org.ssm.app.pvp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.ssm.app.pvp.data.AlumniMetadata;
import org.ssm.app.pvp.service.AlumniService;

@RestController
public class AlumniController {

	@Autowired
	private AlumniService alumniService;
	
	@GetMapping(path="/alumni/{includeStudents}/{includeTeachers}", produces = "application/json")
	public ResponseEntity<AlumniMetadata> get(@PathVariable("includeStudents") boolean includeStudents, 
												@PathVariable("includeTeachers") boolean includeTeachers) {
		System.out.println("Selected Options:");
		System.out.println("Include Students: " + includeStudents);
		System.out.println("Include Teachers: " + includeTeachers);
		
		AlumniMetadata alumniMetadata = processAlumniData(includeStudents, includeTeachers, null);
		alumniMetadata.setGroupChoiceData(alumniService.alumniMetadataByGroupType());
		return new ResponseEntity<>(alumniMetadata, HttpStatus.OK);
	}
	
	@GetMapping(path="/alumni/{includeStudents}/{includeTeachers}/{regions}", produces = "application/json")
	public ResponseEntity<AlumniMetadata> get(@PathVariable("includeStudents") boolean includeStudents, 
												@PathVariable("includeTeachers") boolean includeTeachers, 
												@PathVariable("regions") String[] regions) {
				
		AlumniMetadata alumniMetadata = processAlumniData(includeStudents, includeTeachers, regions);
		alumniMetadata.setGroupChoiceData(alumniService.alumniMetadataByGroupType());
		return new ResponseEntity<>(alumniMetadata, HttpStatus.OK);
	}
	
	private AlumniMetadata processAlumniData(boolean includeStudents, boolean includeTeachers, String[] regions) {
		AlumniMetadata alumniMetadata = new AlumniMetadata();
		alumniMetadata.setBartChartData(alumniService.processBarChartData(includeStudents, includeTeachers, regions));
		return alumniMetadata;
	}
}
