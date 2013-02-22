package org.drklingmann.carddirectory.service;

import org.drklingmann.carddirectory.domain.entities.users.User;

public interface UserService {
	
	User findByLogin(String login);

	long size();
	
}
