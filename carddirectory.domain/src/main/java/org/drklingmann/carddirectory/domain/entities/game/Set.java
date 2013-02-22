package org.drklingmann.carddirectory.domain.entities.game;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "wizards_set")
public class Set implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4348256407263448144L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "set")
	private Collection<CardInSet> cardInSetCollection;
	
    @Column(length=255, nullable=false, unique=true)
    private String name;
    
    @Column(length=7, nullable=false)
    private String mcshort;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMcshort() {
		return mcshort;
	}

	public void setMcshort(String mcshort) {
		this.mcshort = mcshort;
	}

	public Collection<CardInSet> getCardInSetCollection() {
		return cardInSetCollection;
	}

	public void setCardInSetCollection(Collection<CardInSet> cardInSetCollection) {
		this.cardInSetCollection = cardInSetCollection;
	}

}
