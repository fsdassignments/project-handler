package com.sba.projecthandler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.projecthandler.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
