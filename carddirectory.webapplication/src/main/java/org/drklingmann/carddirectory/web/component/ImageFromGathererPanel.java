package org.drklingmann.carddirectory.web.component;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.drklingmann.carddirectory.domain.entities.game.Card;

public class ImageFromGathererPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7066659447990574080L;

	public ImageFromGathererPanel(String name, IModel<Card> model) {
		super(name);
		add(new ExternalImage("cardImage", model));
	}
	
	private class ExternalImage extends WebComponent {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1778845321407808816L;

		public ExternalImage(String id, IModel<Card> model) {
	        super(id);
	        String name = model.getObject().getName();
	        add(AttributeModifier.replace("src", new Model<String>("http://gatherer.wizards.com/Handlers/Image.ashx?size=small&type=card&name=" + name + "&options=")));
	        setVisible(!(name==null || name.equals("")));
	    }
	   
	    @Override
	    protected void onComponentTag(ComponentTag tag) {
	            super.onComponentTag(tag);
	            checkComponentTag(tag, "img");
	    }
		
	}

}
