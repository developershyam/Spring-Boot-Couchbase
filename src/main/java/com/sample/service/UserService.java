package com.sample.service;

import com.sample.data.model.User;

public interface UserService {

	User addUser(User user);
	User getUser(Long id);
	User updateUser(User user);
	void deleteUser(Long id);
}
