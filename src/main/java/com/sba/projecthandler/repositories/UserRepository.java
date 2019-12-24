package com.sba.projecthandler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.projecthandler.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
