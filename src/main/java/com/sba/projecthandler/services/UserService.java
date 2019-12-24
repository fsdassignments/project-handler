package com.sba.projecthandler.services;

import java.util.List;

import com.sba.projecthandler.errors.UserNotFoundException;
import com.sba.projecthandler.model.User;

public interface UserService {
	List<User> list();
	
	int save(User user) throws Exception;
	
	User update(Integer id, User user) throws UserNotFoundException;
	
	User get(int id) throws UserNotFoundException;
}
