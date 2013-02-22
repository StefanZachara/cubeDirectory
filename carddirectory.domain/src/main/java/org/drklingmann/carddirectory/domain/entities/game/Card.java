package org.drklingmann.carddirectory.domain.entities.game;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.drklingmann.carddirectory.domain.entities.market.ModelPrice;

@javax.persistence.Entity
@Table(name = "card")
public class Card implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5866272151047354475L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "card")
	private Collection<CardInSet> cardInSetCollection;
	
    @Column(length=255, nullable=false, unique=true)
    private String name;
 
    @Column(nullable=false)
    private Color color;
    
    @OneToOne
    @JoinColumn(name = "minPrice_id")
    private ModelPrice price;
//
//	private Float minPrice;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<CardInSet> getCardInSetCollection() {
		return cardInSetCollection;
	}

	public void setCardInSetCollection(Collection<CardInSet> cardInSetCollection) {
		this.cardInSetCollection = cardInSetCollection;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
//
//	public Float getMinPrice() {
//		return minPrice;
//	}
//
//	public void setMinPrice(Float minPrice) {
//		this.minPrice = minPrice;
//	}
    
    public ModelPrice getPrice() {
		return price;
	}

	public void setPrice(ModelPrice price) {
		this.price = price;
	}

}
