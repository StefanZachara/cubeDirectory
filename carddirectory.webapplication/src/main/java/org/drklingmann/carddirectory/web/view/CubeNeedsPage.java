package org.drklingmann.carddirectory.web.view;

import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.drklingmann.carddirectory.domain.entities.cube.CardWithSaturationAndUse;
import org.drklingmann.carddirectory.service.CardService;
import org.drklingmann.carddirectory.web.BasePage;
import org.drklingmann.carddirectory.web.component.CubeNeedsPanel;

public class CubeNeedsPage extends BasePage {

	private static final long serialVersionUID = 1L;

	@SpringBean
	private CardService cardService;
	
	private List<CardWithSaturationAndUse> cardUseList;
	
	private CubeNeedsPanel needsPanel = new CubeNeedsPanel("cardList",cardUseList);

//	public CubeNeedsPage() {
////		if(sorted)
////			cardUseList = cardService.getNeededCardsSortedByUse();
////		else
//			cardUseList = cardService.getNeededCardsNotSorted();
//		initGui();
//	}
	
	public CubeNeedsPage(List<CardWithSaturationAndUse> cardUseList) {
//		if(sorted)
//			cardUseList = cardService.getNeededCardsSortedByUse();
//		else
		super(cardUseList);
			this.cardUseList = cardUseList;
		initGui();
	}

	private void initGui() {
		add(needsPanel);
		
	}

	@Override
	protected CubeNeedsPanel getListPanel(List<CardWithSaturationAndUse> cardUseList) {
		return needsPanel;
	}
}
