package edu.project.jobportal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto {
	private long projectId;
	private String projectTitle;
	private String projectDescription;
	private String projectSiteURL;
}
