package edu.project.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.jobportal.dto.ProjectDto;
import edu.project.jobportal.entity.Resume;
import edu.project.jobportal.service.ProjectService;
import edu.project.jobportal.util.responseStructure;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping
	public ResponseEntity<responseStructure<Resume>> saveProject(
			@RequestParam long applicantId, @RequestBody ProjectDto projectDto){
		return projectService.saveProject(applicantId, projectDto);
	}
}