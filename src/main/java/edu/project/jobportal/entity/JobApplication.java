package edu.project.jobportal.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JobApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobApplicationId;
	private LocalDateTime jobApplicationDateTime;

	@ManyToOne
	private Job job;

	@ManyToOne
	private Applicant applicant;

	@OneToOne
	private Resume resume;
}
