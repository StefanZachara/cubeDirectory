package org.drklingmann.carddirectory.domain.entities.game;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.drklingmann.carddirectory.domain.entities.market.CardModel;

@javax.persistence.Entity
@Table(name = "card_in_set",
	   uniqueConstraints=
	   	@UniqueConstraint(columnNames ={"setId","cardId"}))
public class CardInSet implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="cardId")
	private Card card;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="setId")
	private Set set;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cardInSet")
	private Collection<CardModel> cardModelCollection;
	
    @Column(length=31)
	String rarity;
    
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public Collection<CardModel> getCardModelCollection() {
		return cardModelCollection;
	}

	public void setCardModelCollection(Collection<CardModel> cardModelCollection) {
		this.cardModelCollection = cardModelCollection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
