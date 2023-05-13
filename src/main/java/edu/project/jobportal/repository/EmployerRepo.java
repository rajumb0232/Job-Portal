package edu.project.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.jobportal.entity.Employer;

public interface EmployerRepo extends JpaRepository<Employer, Long>{

}
