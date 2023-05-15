package edu.project.jobportal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeDto {
	private long resumeId;
	private String summary;
	private String qualification;
	private String university;
	private String socialProfile1;
	private String socialProfile2;
	private String socialProfile3;
	private String certification;
}
