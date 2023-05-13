package edu.project.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.jobportal.entity.Resume;

public interface ResumeRepo extends JpaRepository<Resume, Long>{

}
