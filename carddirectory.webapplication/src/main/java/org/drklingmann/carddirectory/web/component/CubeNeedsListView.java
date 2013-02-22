package org.drklingmann.carddirectory.web.component;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.drklingmann.carddirectory.domain.entities.cube.CardWithSaturationAndUse;
import org.drklingmann.carddirectory.service.CardService;

public class CubeNeedsListView extends ListView<CardWithSaturationAndUse> {

	/**
	 * 
	 */
	@SpringBean
	private CardService cardService;

	private static final long serialVersionUID = -6802657688945852853L;

	public CubeNeedsListView(String id,
			List<? extends CardWithSaturationAndUse> list) {
		super(id, list);
	}

	@Override
	protected void populateItem(ListItem<CardWithSaturationAndUse> item) {
		final CardWithSaturationAndUse card = item.getModelObject();
		item.add(new AttributeModifier("bgcolor", card.getSaturation()));
		item.add(new Label("name", card.getName()));
		item.add(new Label("color", card.getColor().toString()));
		item.add(new Label("rarity", card.getRarity()));
		if(card.getPrice()!=null)
			item.add(new Label("price", card.getPrice().getLow().toString()));
		else
			item.add(new Label("price", ""));
		if(card.getUse()!=null)
			item.add(new Label("use", card.getUse().toString()));
		else
			item.add(new Label("use", ""));
		item.add(new AddCardToMyCubeForm("addCardToMyCube", card.getName()));
	}
	private class AddCardToMyCubeForm extends Form<String> {
		private static final long serialVersionUID = 1L;
		private String cardName;

		public AddCardToMyCubeForm(String id, String cardName) {
			super(id);
			this.cardName = cardName;
		}
		@Override
		protected void onSubmit() {
			cardService.insertCardToCube(cardName,"CubeStefana");
		}
	}

}
