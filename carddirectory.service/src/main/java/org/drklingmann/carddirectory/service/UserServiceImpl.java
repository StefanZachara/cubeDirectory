package org.drklingmann.carddirectory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.drklingmann.carddirectory.domain.entities.users.User;
import org.drklingmann.carddirectory.domain.repository.user.UserRepository;

@Service(value = "userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	public User findByLogin(String login) {
		return userRepo.findByLogin(login);
	}

	public long size() {
		return userRepo.count();
	}

}
