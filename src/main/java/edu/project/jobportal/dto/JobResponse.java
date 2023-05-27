package edu.project.jobportal.dto;

import java.time.LocalDateTime;
import java.util.List;

import edu.project.jobportal.entity.Employer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobResponse {
	private long jobId;
	private String jobTitle;
	private String jobDiscription;
	private String company;
	private double salary;
	private LocalDateTime jobCreateDatetime;

	private List<String> skills;
	
	private Employer employer;
}
