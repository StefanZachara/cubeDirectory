package org.drklingmann.carddirectory.domain.entities.market;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@javax.persistence.Entity
@Table(name = "model_price")
public class ModelPrice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	private CardModel cardModel;
	
	@Column(precision=12, scale=2)
	private Float low;
	
	@Column(precision=12, scale=2)
	private Float mid;
	
	@Column(precision=12, scale=2)
	private Float high;
	
	@Temporal(TemporalType.DATE)
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CardModel getCardModel() {
		return cardModel;
	}

	public void setCardModel(CardModel cardModel) {
		this.cardModel = cardModel;
	}

	public Float getLow() {
		return low;
	}

	public void setLow(Float low) {
		this.low = low;
	}

	public Float getMid() {
		return mid;
	}

	public void setMid(Float mid) {
		this.mid = mid;
	}

	public Float getHigh() {
		return high;
	}

	public void setHigh(Float high) {
		this.high = high;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
