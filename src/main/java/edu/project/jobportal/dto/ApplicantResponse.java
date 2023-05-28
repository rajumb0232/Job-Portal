package edu.project.jobportal.dto;

import edu.project.jobportal.entity.Resume;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ApplicantResponse {
	private long applicantId;
	private String applicantName;
	private String applicantEmail;
	private long applicantPhNo;

	private Resume resume;
}
