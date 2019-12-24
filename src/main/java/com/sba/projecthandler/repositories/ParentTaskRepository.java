package com.sba.projecthandler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.projecthandler.model.ParentTask;


public interface ParentTaskRepository extends JpaRepository<ParentTask, Integer> {

}
