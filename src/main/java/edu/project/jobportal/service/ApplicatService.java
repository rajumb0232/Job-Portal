package edu.project.jobportal.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.ApplicantDao;
import edu.project.jobportal.dao.JobApplicationDao;
import edu.project.jobportal.dao.ProjectDao;
import edu.project.jobportal.dao.ResumeDao;
import edu.project.jobportal.dto.ApplicantDto;
import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.entity.JobApplication;
import edu.project.jobportal.entity.Project;
import edu.project.jobportal.entity.Resume;
import edu.project.jobportal.exception.ApplicantNotfoundByIdException;
import edu.project.jobportal.util.responseStructure;

@Service
public class ApplicatService {

	@Autowired
	private ApplicantDao applicantDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ResumeDao resumeDao;
	@Autowired
	private JobApplicationDao jobApplicationDao;
	@Autowired
	private ProjectDao projectDao;

	public ResponseEntity<responseStructure<ApplicantDto>> saveApplicant(Applicant applicant) {

		applicant = applicantDao.addApplicant(applicant);
		ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);
		responseStructure<ApplicantDto> responseStructure = new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Applicant added successfully.");
		responseStructure.setData(applicantDto);
		return new ResponseEntity<responseStructure<ApplicantDto>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<responseStructure<ApplicantDto>> getApplicantById(int applicantId) {
		Applicant applicant = applicantDao.getApplicant(applicantId);
		if (applicant != null) {
			ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);
			responseStructure<ApplicantDto> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Applicant Found.");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<responseStructure<ApplicantDto>>(responseStructure, HttpStatus.FOUND);
		}
		throw new ApplicantNotfoundByIdException("Failed to find Applicant!!");
	}

	public ResponseEntity<responseStructure<ApplicantDto>> updateApplicant(int applicantId, Applicant applicant) {
		Applicant exApplicant = applicantDao.getApplicant(applicantId);
		if (exApplicant != null) {
			applicant.setApplicantId(exApplicant.getApplicantId());
			applicant.setJobApplications(exApplicant.getJobApplications());
			applicant.setResume(exApplicant.getResume());
			applicantDao.addApplicant(applicant);
			ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);
			responseStructure<ApplicantDto> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Applicant updated successfully.");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<responseStructure<ApplicantDto>>(responseStructure, HttpStatus.OK);
		}
		throw new ApplicantNotfoundByIdException("Failed to update Applicant!!");

	}

	public ResponseEntity<responseStructure<ApplicantDto>> deleteApplicant(int applicantId) {
		Applicant applicant = applicantDao.getApplicant(applicantId);
		
		if (applicant != null) {
			/*Before deleting the applicant the applicant is to null in all the
			  jobApplications later the applicant is delete the applicant */
			for (JobApplication jobApplication : applicant.getJobApplications()) {
				jobApplication.setApplicant(null);
				// createJobApplication() method used to update
				jobApplicationDao.createJobApplication(jobApplication);
			}
			applicantDao.deleteApplicant(applicant);
			Resume resume = applicant.getResume();
			/*After the applicant is deleted the the resume linked to the applicant
			 * should be deleted*/
			if(resume!=null) {
				/*Before deleting the resume the skills should be set to null
				 * in the resume.*/
				resume.setSkills(null);
				// saveResume() method used to update
				resumeDao.saveResume(resume);
				for (Project project : applicant.getResume().getProjects()) {
					projectDao.deleteProject(project);
				}
					resumeDao.deleteResume(resume);
			}


			ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);
			responseStructure<ApplicantDto> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Applicant deleted successfully.");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<responseStructure<ApplicantDto>>(responseStructure, HttpStatus.OK);
		}
		throw new ApplicantNotfoundByIdException("Failed to delete Applicant!!");

	}
}
