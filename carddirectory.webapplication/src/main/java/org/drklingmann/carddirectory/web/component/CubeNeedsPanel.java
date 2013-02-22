package org.drklingmann.carddirectory.web.component;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.drklingmann.carddirectory.domain.entities.cube.CardWithSaturationAndUse;

public class CubeNeedsPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7885034416828739877L;

	public CubeNeedsPanel(String id, List<CardWithSaturationAndUse> cardUseList) {
		super(id);
		add(new CubeNeedsListView("cardList", cardUseList));
	}

}
