package edu.project.jobportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.jobportal.entity.Resume;
import edu.project.jobportal.repository.ResumeRepo;

@Repository
public class ResumeDao {

	@Autowired
	private ResumeRepo resumeRepo;

	public Resume saveResume(Resume resume) {
		return resumeRepo.save(resume);
	}

	public void deleteResume(Resume resume) {
		resumeRepo.delete(resume);
	}
}
