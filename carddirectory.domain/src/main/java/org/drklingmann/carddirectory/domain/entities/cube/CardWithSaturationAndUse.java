package org.drklingmann.carddirectory.domain.entities.cube;

import java.io.Serializable;

import org.drklingmann.carddirectory.domain.entities.game.Color;
import org.drklingmann.carddirectory.domain.entities.market.ModelPrice;

public class CardWithSaturationAndUse implements Serializable, Comparable<CardWithSaturationAndUse>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3664672294395350287L;
	private String name;
	private String saturation;
	private Integer saturationNumber;
	private Integer use;
	private ModelPrice price;
	private Color color;
	private String rarity;
	public CardWithSaturationAndUse(String name, Integer use, ModelPrice price,
			Color color, String rarity) {
		super();
		this.name = name;
		this.use = use;
		this.price = price;
		this.color = color;
		this.rarity = rarity;
	}
	public CardWithSaturationAndUse() {
		super();
	}
	public Integer getSaturationNumber() {
		return saturationNumber;
	}
	public void setSaturationNumber(Integer saturationNumber) {
		this.saturationNumber = saturationNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSaturation() {
		return saturation;
	}
	public void setSaturation(String saturation) {
		this.saturation = saturation;
	}
	public Integer getUse() {
		return use;
	}
	public void setUse(Integer use) {
		this.use = use;
	}
	public ModelPrice getPrice() {
		return price;
	}
	public void setPrice(ModelPrice price) {
		this.price = price;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	@Override
	public int compareTo(CardWithSaturationAndUse o) {
		if(getSaturationNumber() < o.getSaturationNumber())
			return 1;
		else if (getSaturationNumber() == o.getSaturationNumber())
			return 0;
		else
			return -1;
	}
	public String getRarity() {
		return rarity;
	}
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	
}
