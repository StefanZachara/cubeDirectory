package org.drklingmann.carddirectory.domain.entities.cube;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.drklingmann.carddirectory.domain.entities.game.Card;

@javax.persistence.Entity
@Table(name = "cube_card_use",
	   uniqueConstraints=
	   	@UniqueConstraint(columnNames ={"version","cardId"}))
public class CubeCardUse implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="cardId")
	private Card card;
	 
    @Column(nullable=false)
    private Integer version;
	
    @Column(nullable=false)
    private Integer cardUse;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getCardUse() {
		return cardUse;
	}

	public void setCardUse(Integer cardUse) {
		this.cardUse = cardUse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
