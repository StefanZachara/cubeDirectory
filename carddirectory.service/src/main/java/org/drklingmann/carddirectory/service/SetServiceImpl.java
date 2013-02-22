package org.drklingmann.carddirectory.service;

import java.util.List;

import org.drklingmann.carddirectory.domain.entities.game.Set;
import org.drklingmann.carddirectory.domain.repository.game.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "setService")
@Transactional(rollbackFor = Exception.class)
public class SetServiceImpl implements SetService {

	@Autowired
	private SetRepository setrepo;
	
	@Override
	public void persist(Set s) {
		setrepo.save(s);
	}
	
	@Override
	public List<Set> findAll() {
		return setrepo.findAll();
	}

}
