package edu.project.jobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.entity.Skill;

public interface ApplicantRepo extends JpaRepository<Applicant, Long>{
	
	@Query(value = "select s from Skill s where s.skillName=?1")
	public Optional<Skill> getSkillBySkillName(String skill);

}
