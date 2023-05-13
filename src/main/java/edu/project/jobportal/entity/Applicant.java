package edu.project.jobportal.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long applicantId;
	private String applicantName;
	private String applicantEmail;
	private String applicantPassword;
	private long applicantPhNo;

	@OneToMany
	private List<JobApplication> jobApplications;

	@OneToOne
	private Resume resume;
}
