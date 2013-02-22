package org.drklingmann.carddirectory.domain.repository.user;

import org.drklingmann.carddirectory.domain.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByLogin(String login);
}
