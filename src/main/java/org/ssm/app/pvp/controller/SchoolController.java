package org.ssm.app.pvp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.ssm.app.pvp.data.FilterData;
import org.ssm.app.pvp.data.SchoolMetadata;
import org.ssm.app.pvp.service.SchoolDataService;

@RestController
public class SchoolController {

	@Autowired
	private SchoolDataService alumniService;
	
	@CrossOrigin(origins = {"http://srsprasad.github.io", "http://localhost:4200", "https://localhost:4200"})
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
	
	@CrossOrigin(origins = {"http://srsprasad.github.io", "http://localhost:4200", "https://localhost:4200"})
	@GetMapping(path="/school/{includeStudents}/{includeTeachers}/{regions}", produces = "application/json")
	public ResponseEntity<SchoolMetadata> get(@PathVariable("includeStudents") boolean includeStudents, 
												@PathVariable("includeTeachers") boolean includeTeachers, 
												@PathVariable("regions") String[] regions) {
				
		SchoolMetadata schoolMetadata = processSchoolData(includeStudents, includeTeachers, regions);
		schoolMetadata.setGroupChoiceData(alumniService.schoolMetadataByGroupType());
		return new ResponseEntity<>(schoolMetadata, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = {"http://srsprasad.github.io", "http://localhost:4200", "https://localhost:4200"})
	@GetMapping(path="/school/vibhag/all", produces="application/json")
	public ResponseEntity<List<FilterData>> getVibhagList() {
		return new ResponseEntity<>(this.alumniService.getVibhagList(), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = {"http://srsprasad.github.io", "http://localhost:4200", "https://localhost:4200"})
	@GetMapping(path="/school/district/all", produces="application/json")
	public ResponseEntity<List<FilterData>> getDistrictList() {
		return new ResponseEntity<>(this.alumniService.getDistrictList(), HttpStatus.OK);
	}
	
	private SchoolMetadata processSchoolData(boolean includeStudents, boolean includeTeachers, String[] regions) {
		SchoolMetadata schoolMetadata = new SchoolMetadata();
		schoolMetadata.setBartChartData(alumniService.processBarChartData(includeStudents, includeTeachers, regions));
		return schoolMetadata;
	}
	
}
