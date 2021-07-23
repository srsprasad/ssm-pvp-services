package org.ssm.app.pvp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.ssm.app.pvp.data.SchoolMetadata;
import org.ssm.app.pvp.service.SchoolDataService;

@RestController
public class SchoolController {

	@Autowired
	private SchoolDataService alumniService;
	
	@CrossOrigin(origins = {"http://srsprasad.github.io", "https://srsprasad.github.io"})
	@GetMapping(path="/school/{includeStudents}/{includeTeachers}", produces = "application/json")
	public ResponseEntity<SchoolMetadata> get(@PathVariable("includeStudents") boolean includeStudents, 
												@PathVariable("includeTeachers") boolean includeTeachers) {
		System.out.println("Selected Options:");
		System.out.println("Include Students: " + includeStudents);
		System.out.println("Include Teachers: " + includeTeachers);
		
		SchoolMetadata schoolMetadata = processSchoolData(includeStudents, includeTeachers, null);
		schoolMetadata.setGroupChoiceData(alumniService.schoolMetadataByGroupType());
		return new ResponseEntity<>(schoolMetadata, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = {"http://srsprasad.github.io", "https://srsprasad.github.io"})
	@GetMapping(path="/school/{includeStudents}/{includeTeachers}/{regions}", produces = "application/json")
	public ResponseEntity<SchoolMetadata> get(@PathVariable("includeStudents") boolean includeStudents, 
												@PathVariable("includeTeachers") boolean includeTeachers, 
												@PathVariable("regions") String[] regions) {
				
		SchoolMetadata schoolMetadata = processSchoolData(includeStudents, includeTeachers, regions);
		schoolMetadata.setGroupChoiceData(alumniService.schoolMetadataByGroupType());
		return new ResponseEntity<>(schoolMetadata, HttpStatus.OK);
	}
	
	private SchoolMetadata processSchoolData(boolean includeStudents, boolean includeTeachers, String[] regions) {
		SchoolMetadata schoolMetadata = new SchoolMetadata();
		schoolMetadata.setBartChartData(alumniService.processBarChartData(includeStudents, includeTeachers, regions));
		return schoolMetadata;
	}
}
