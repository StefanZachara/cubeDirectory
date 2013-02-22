package org.drklingmann.carddirectory.domain.entities.market;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.drklingmann.carddirectory.domain.entities.game.CardInSet;

@javax.persistence.Entity
@Table(name = "card_model")
public class CardModel implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5151360622829689113L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cardModel")
	private Collection<ModelPrice> priceCollection;
	
	@ManyToOne(optional = false)
	private CardInSet cardInSet;
	
	@Column(length=63)
	private String printing;
	
	@Column(unique=true)
	private String mcLink;
	
	private String imageLink;
	
	private String comment;
	
	private String borderType;
	
	private Boolean signed;
	
	private Boolean markedWOTC;
	
	private String quality;
	
	private Boolean foil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CardInSet getCardInSet() {
		return cardInSet;
	}

	public void setCardInSet(CardInSet cardInSet) {
		this.cardInSet = cardInSet;
	}

	public String getPrinting() {
		return printing;
	}

	public void setPrinting(String printing) {
		this.printing = printing;
	}

	public String getMcLink() {
		return mcLink;
	}

	public void setMcLink(String mcLink) {
		this.mcLink = mcLink;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getBorderType() {
		return borderType;
	}

	public void setBorderType(String borderType) {
		this.borderType = borderType;
	}

	public Boolean getSigned() {
		return signed;
	}

	public void setSigned(Boolean signed) {
		this.signed = signed;
	}

	public Boolean getMarkedWOTC() {
		return markedWOTC;
	}

	public void setMarkedWOTC(Boolean markedWOTC) {
		this.markedWOTC = markedWOTC;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public Boolean getFoil() {
		return foil;
	}

	public void setFoil(Boolean foil) {
		this.foil = foil;
	}

	public Collection<ModelPrice> getPriceCollection() {
		return priceCollection;
	}

	public void setPriceCollection(Collection<ModelPrice> priceCollection) {
		this.priceCollection = priceCollection;
	}
}
