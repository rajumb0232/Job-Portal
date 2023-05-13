package edu.project.jobportal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeDto {
	private long resumeId;
	private String summary;
	private String Qualification;
	private String University;
	private String socialProfile1;
	private String socialProfile2;
	private String socialProfile3;
	private String certification;
}
