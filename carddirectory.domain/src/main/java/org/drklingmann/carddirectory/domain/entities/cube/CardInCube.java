package org.drklingmann.carddirectory.domain.entities.cube;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.drklingmann.carddirectory.domain.entities.game.Card;
import org.drklingmann.carddirectory.domain.entities.market.CardModel;

@javax.persistence.Entity
@Table(name = "card_in_cube",
	   uniqueConstraints=
		@UniqueConstraint(columnNames ={"cubeId","cardId"}))
public class CardInCube implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="cubeId")
	private Cube cube;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="cardId")
	private Card card;
	
	@ManyToOne(optional = true)
	private CardModel concreteCard;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Cube getCube() {
		return cube;
	}

	public void setCube(Cube cube) {
		this.cube = cube;
	}

	public CardModel getConcreteCard() {
		return concreteCard;
	}

	public void setConcreteCard(CardModel concreteCard) {
		this.concreteCard = concreteCard;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
