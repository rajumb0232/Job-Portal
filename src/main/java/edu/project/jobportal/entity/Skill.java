package edu.project.jobportal.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long skillId;
	private String skillName;
	
	@ManyToMany(mappedBy = "skills")
	@JsonIgnore
	private List<Job> jobs;
	
	@ManyToMany(mappedBy = "skills")
	@JsonIgnore
	private List<Resume> resumes;
}
