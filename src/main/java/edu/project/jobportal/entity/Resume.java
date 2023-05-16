package edu.project.jobportal.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long resumeId;
	private String summary;
	private String qualification;
	private String university;
	private String socialProfile1;
	private String socialProfile2;
	private String socialProfile3;
	private String certification;

	@OneToOne(mappedBy = "resume")
	@JsonIgnore
	private Applicant applicant;

	@ManyToMany
	private List<Skill> skills;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Project> projects;

}
