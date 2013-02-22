package org.drklingmann.carddirectory.web.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.drklingmann.carddirectory.domain.entities.game.Color;
import org.drklingmann.carddirectory.utiltempclassess.FilterResults;

import com.googlecode.wicket.jquery.ui.form.button.AjaxButton;
import com.googlecode.wicket.jquery.ui.panel.JQueryFeedbackPanel;

public class CardsFilterForm extends Form<FilterResults> {

	private static final long serialVersionUID = 1L;

	static final List<String> RARITIES = Arrays.asList(
			"Common",
			"Uncommon",
			"Rare",
			"Mythic Rare",
			"Special"
			);
	
	public CardsFilterForm(String id, FilterResults filter) {
		super(id,new CompoundPropertyModel<FilterResults>(filter));
		final FeedbackPanel feedback = new JQueryFeedbackPanel("feedback");
		this.add(feedback.setOutputMarkupId(true));
		final DropDownChoice<Color> colorDropDown = new DropDownChoice<Color>("color", Arrays.asList(Color.values()));
		colorDropDown.setChoiceRenderer(new IChoiceRenderer<Color>(){
			private static final long serialVersionUID = 1L;

			@Override
			public Object getDisplayValue(Color color) {
				return color.getName();
			}

			@Override
			public String getIdValue(Color color, int index) {
				// TODO Auto-generated method stub
				return color.getName();
			}
			
		});
		this.add(colorDropDown);
		final DropDownChoice<String> rarityDropDown = new DropDownChoice<String>("rarity", RARITIES);
		this.add(rarityDropDown);
		
		final NumberTextField<Float> minPriceField = new NumberTextField<Float>("minPrice");
		minPriceField.setMinimum(new Float(0));
		minPriceField.setMaximum(new Float(100));
		this.add(minPriceField);
		final NumberTextField<Float> maxPriceField = new NumberTextField<Float>("maxPrice");
		maxPriceField.setMinimum(new Float(0));
		maxPriceField.setMaximum(new Float(10000));
		this.add(maxPriceField);
		
		final NumberTextField<Integer> minUseField = new NumberTextField<Integer>("minUse");
		minUseField.setMinimum(new Integer(0));
		minUseField.setMaximum(new Integer(100));
		this.add(minUseField);
		final NumberTextField<Integer> maxUseField = new NumberTextField<Integer>("maxUse");
		maxUseField.setMinimum(new Integer(0));
		maxUseField.setMaximum(new Integer(100));
		this.add(maxUseField);
		
		final AutoCompleteSetNameField setTextField = new AutoCompleteSetNameField("set");
		this.add(setTextField);
		
		final IFormValidator priceValidator = new AbstractFormValidator(){
			private static final long serialVersionUID = 1L;

			@Override
			public FormComponent<?>[] getDependentFormComponents() {
				return new FormComponent<?>[] {minPriceField, maxPriceField};
			}

			@Override
			public void validate(Form<?> form) {
				System.out.println("val1");
				if(minPriceField.getInput().length()>0&&maxPriceField.getInput().length()>0
						&&new Float(minPriceField.getInput())>=new Float(maxPriceField.getInput())){
					System.out.println("val2");
						error(minPriceField,"error.minPrice.toHigh");
				}
				
			}
			
		};
		this.add(priceValidator);
		final IFormValidator useValidator = new AbstractFormValidator(){
			private static final long serialVersionUID = 1L;

			@Override
			public FormComponent<?>[] getDependentFormComponents() {
				return new FormComponent<?>[] {minUseField, maxUseField};
			}

			@Override
			public void validate(Form<?> form) {
				if(minUseField.getInput().length()>0&&maxUseField.getInput().length()>0
					&&new Integer(minUseField.getInput())>=new Integer(maxUseField.getInput())){
						error(minUseField,"error.minUse.toHigh");
				}
				
			}
			
		};
		this.add(useValidator);
		
		this.add(new AjaxButton("search", this) {

			private static final long serialVersionUID = 1L;
			
			@Override
			public void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> unused) {
				FilterResults filter = (FilterResults) getMe().getModelObject();
				if(filter.getSet()!=null){
					System.out.println(String.format("Set chosen is: %s (id %d)", filter.getSet().getName(), filter.getSet().getId()));
					info(String.format("Set chosen is: %s (id %d)", filter.getSet().getName(), filter.getSet().getId()));
				} else {
					System.out.println("Input was: " + setTextField.getInput());
					warn("Sth went wrong!");
					info("Input was: " + setTextField.getInput());
				}
				target.add(feedback);
			}
		});
	}
	
	private Form<FilterResults> getMe(){
		return this;
	}

}
