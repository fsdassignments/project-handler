package com.sba.projecthandler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.projecthandler.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
