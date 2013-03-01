package org.drklingmann.carddirectory.web.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;
import org.drklingmann.carddirectory.domain.entities.cube.CardWithSaturationAndUse;
import org.drklingmann.carddirectory.domain.entities.game.Set;
import org.drklingmann.carddirectory.service.CardService;
import org.drklingmann.carddirectory.service.ParserService;
import org.drklingmann.carddirectory.web.BasePage;
import org.drklingmann.carddirectory.web.component.CubeNeedsPanel;

public class CardsInSetPage extends BasePage {

	private static final long serialVersionUID = 1L;
	@SpringBean
	private CardService cardService;
	@SpringBean
	private ParserService parserService;
//	
//	private List<Card> cardList;
	
	private List<CardWithSaturationAndUse> cardUseList;
	
	private CubeNeedsPanel needsPanel = new CubeNeedsPanel("cards", cardUseList);
	
	public CardsInSetPage(Set set) {
		super(new ArrayList<CardWithSaturationAndUse>());
//		cardList = cardService.findAllFromSet(set.getName());
//		cardUseList = cardService.findAllCubeNeedsFromSet(set.getName());
		cardUseList = cardService.findAllCubeNeedsFromSetSorted(set.getName());
		initGui(set);
	}

	private void initGui(Set set) {
		add(new GetNewCardsForm("importCards", set));
		add(new Label("set", set.getName()));
		
		add(needsPanel);
//		add(new PropertyListView<Card>("cards",cardList){
//			@Override
//			protected void populateItem(ListItem<Card> item) {
//				item.add(new Label("name"));
//				item.add(new Label("color"));
//			}
//		});
//		//add(new Form<>);
	}
	
	private class GetNewCardsForm extends Form<ValueMap> {
		private static final long serialVersionUID = 1L;
		private Set set;

		public GetNewCardsForm(String id, Set set) {
			super(id, new CompoundPropertyModel<ValueMap>(new ValueMap()));
			this.set = set;
			add(new NumberTextField<Integer>("initial").setType(Integer.class).setRequired(true));
			add(new NumberTextField<Integer>("final").setType(Integer.class).setRequired(true));
		}
		@Override
		protected void onSubmit() {
			ValueMap values = getModelObject();
			parserService.insertCardsFromSet(set.getName(), (Integer)values.get("initial"), (Integer)values.get("final"));
			
//			cardList = cardService.findAll();
		}
	}

	@Override
	protected CubeNeedsPanel getListPanel(List<CardWithSaturationAndUse> cardUseList) {
		return needsPanel;
	}
}
