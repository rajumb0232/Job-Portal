package edu.project.jobportal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantDto {
	private long applicantId;
	private String applicantName;
	private String applicantEmail;
	private long applicantPhNo;
}
