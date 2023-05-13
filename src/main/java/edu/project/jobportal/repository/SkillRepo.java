package edu.project.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.jobportal.entity.Skill;

public interface SkillRepo extends JpaRepository<Skill, Long>{

}
