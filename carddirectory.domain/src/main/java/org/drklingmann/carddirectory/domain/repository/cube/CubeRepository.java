package org.drklingmann.carddirectory.domain.repository.cube;

import org.drklingmann.carddirectory.domain.entities.cube.Cube;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CubeRepository extends JpaRepository<Cube, Long>{
	Cube findByName(String name);
}
