package com.sba.projecthandler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sba.projecthandler.convertors.UserConverter;
import com.sba.projecthandler.errors.UserNotFoundException;
import com.sba.projecthandler.services.UserService;
import com.sba.projecthandler.vo.UserVO;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserVO>> list() {
		return ResponseEntity.ok().body(UserConverter.toDtos(userService.list()));
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserVO> get(@PathVariable("id") int id) throws UserNotFoundException {
		return ResponseEntity.ok().body(UserConverter.toDto(userService.get(id)));
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserVO> save(@RequestBody UserVO user) throws Exception {
		int id = userService.save(UserConverter.toPojo(user));
		user.setUserId(id);
		return ResponseEntity.ok().body(user);
	}
}
