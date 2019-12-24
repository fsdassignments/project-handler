package com.sba.projecthandler.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sba.projecthandler.errors.UserNotFoundException;
import com.sba.projecthandler.model.User;
import com.sba.projecthandler.repositories.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Override
	public List<User> list() {
		return userRepository.findAll();
	}

	@Transactional
	@Override
	public int save(User user) throws Exception {
		user = userRepository.save(user);
		return user.getUserId();
	}

	@Transactional
	@Override
	public User update(Integer id, User user) throws UserNotFoundException {
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public User get(int id) throws UserNotFoundException {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

}
