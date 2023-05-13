package edu.project.jobportal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDto {
	private long jobId;
	private String jobTitle;
	private String jobDiscription;
	private String company;
	private double salary;
}
