package org.drklingmann.carddirectory.domain.entities.game;

public enum Color {
	COLORLESS ("Colorless"),
	WHITE ("White"),
	BLUE ("Blue"),
	BLACK ("Black"),
	RED ("Red"),
	GREEN ("Green"),
	MULTICOLOR ("Multicolor"),
	HYBRID ("Hybrid"),
	ARTIFACT ("Ärtifact"),
	LAND ("Land");
	
	private final String name;
	Color(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
}
