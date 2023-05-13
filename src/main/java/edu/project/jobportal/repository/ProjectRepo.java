package edu.project.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.jobportal.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {

}
