package edu.project.jobportal.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class JobNotFoundBySkill extends RuntimeException {
	private String message;
}
