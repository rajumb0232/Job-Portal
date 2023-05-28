package edu.project.jobportal.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.ApplicantDao;
import edu.project.jobportal.dao.ProjectDao;
import edu.project.jobportal.dao.ResumeDao;
import edu.project.jobportal.dto.ProjectDto;
import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.entity.Project;
import edu.project.jobportal.entity.Resume;
import edu.project.jobportal.exception.ApplicantNotfoundByIdException;
import edu.project.jobportal.exception.ProjectNotFoundByIdException;
import edu.project.jobportal.exception.ResumeNotFoundByIdException;
import edu.project.jobportal.util.responseStructure;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private ApplicantDao applicantDao;
	@Autowired
	private ResumeDao resumeDao;
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<responseStructure<Resume>> saveProject(long applicantId, ProjectDto projectDto){
		Applicant applicant = applicantDao.getApplicant(applicantId);
		if(applicant!=null) {
			Resume resume = applicant.getResume();
			if(resume!=null) {
				List<Project> exProjects = resume.getProjects();
				Project project = this.modelMapper.map(projectDto, Project.class);
				project = projectDao.saveProject(project);
				exProjects.add(project);
				resumeDao.saveResume(resume);
				responseStructure<Resume> responseStructure = new responseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Project added successfully!!");
				responseStructure.setData(resume);
				return new ResponseEntity<responseStructure<Resume>>(responseStructure, HttpStatus.CREATED);
			}else {
				throw new ResumeNotFoundByIdException("Failed to add Projects!!");
			}
		}else {
			throw new ApplicantNotfoundByIdException("Failed to add Projects!!");
		}
	}

	public ResponseEntity<responseStructure<Project>> getproject(long projectId) {
		Optional<Project> optional = projectDao.getProjectById(projectId);
		if(optional.isPresent()) {
			responseStructure<Project> structure = new responseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Project found.");
			structure.setData(optional.get());
			return new ResponseEntity<responseStructure<Project>>(structure, HttpStatus.FOUND);
		}else
			throw new ProjectNotFoundByIdException("Failed to find project!!");
	}

	public ResponseEntity<responseStructure<Project>> updateProject(long projectId, ProjectDto projectDto) {
		Optional<Project> optional = projectDao.getProjectById(projectId);
		if(optional.isPresent()) {
			Project project = this.modelMapper.map(projectDto, Project.class);
			project.setProjectId(projectId);
			project = projectDao.saveProject(project);
			responseStructure<Project> structure = new responseStructure<>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Project updated successfully.");
			structure.setData(optional.get());
			return new ResponseEntity<responseStructure<Project>>(structure, HttpStatus.OK);
		}else
			throw new ProjectNotFoundByIdException("Failed to update Project!!");
	}

	public ResponseEntity<responseStructure<Project>> deleteProject(long projectId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/*
	 * create methods for updating, deleting and fetching the project object.
	 * */
}
