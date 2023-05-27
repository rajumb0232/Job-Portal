package edu.project.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.jobportal.dto.ResumeDto;
import edu.project.jobportal.entity.Resume;
import edu.project.jobportal.service.ResumeService;
import edu.project.jobportal.util.responseStructure;

@RestController
@RequestMapping("/resume")
public class ResumeController {
	
	@Autowired
	private ResumeService resumeService;
	
	@PostMapping
	public ResponseEntity<responseStructure<Resume>> saveResume
	(@RequestParam long applicantId, @RequestBody ResumeDto resumeDto){
		return resumeService.saveResume(applicantId, resumeDto);
	}
	
	@PutMapping
	public ResponseEntity<responseStructure<Resume>> updateResume
	(@RequestParam long applicantId, @RequestBody ResumeDto resumeDto){
		return resumeService.saveResume(applicantId, resumeDto);
	}
	
	@GetMapping
	public ResponseEntity<responseStructure<Resume>> getResume(
			@RequestParam long resumeId){
		return resumeService.getResumeById(resumeId);
	}
}
