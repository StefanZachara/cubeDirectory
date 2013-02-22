package org.drklingmann.carddirectory.utiltempclassess;

import java.io.Serializable;

import org.drklingmann.carddirectory.domain.entities.game.Color;
import org.drklingmann.carddirectory.domain.entities.game.Set;

public class FilterResults implements Serializable {
	private static final long serialVersionUID = 1L;
	private Set set = new Set();
	private Color color;
	private Float minPrice;
	private Float maxPrice;
	private Integer minUse;
	private Integer maxUse;
	private String rarity;
	
	public Set getSet() {
		return set;
	}
	public void setSet(Set set) {
		this.set = set;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Float getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}
	public Float getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Integer getMinUse() {
		return minUse;
	}
	public void setMinUse(Integer minUse) {
		this.minUse = minUse;
	}
	public Integer getMaxUse() {
		return maxUse;
	}
	public void setMaxUse(Integer maxUse) {
		this.maxUse = maxUse;
	}
	public String getRarity() {
		return rarity;
	}
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	
	
	
}
