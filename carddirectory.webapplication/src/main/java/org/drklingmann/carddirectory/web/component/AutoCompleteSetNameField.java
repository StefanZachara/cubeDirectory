package org.drklingmann.carddirectory.web.component;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.drklingmann.carddirectory.domain.entities.game.Set;
import org.drklingmann.carddirectory.service.SetService;

import com.googlecode.wicket.jquery.ui.form.autocomplete.AutoCompleteTextField;
import com.googlecode.wicket.jquery.ui.renderer.TextRenderer;

public class AutoCompleteSetNameField extends AutoCompleteTextField<Set> {

	private static final long serialVersionUID = 1L;

	List<Set> dbSets;
	
	@SpringBean
	SetService setService;
	
	public AutoCompleteSetNameField(String id) {
		super(id, new TextRenderer<Set>("name"), Set.class);
		this.dbSets=setService.findAll();
	}
	@Override
	protected List<Set> getChoices(String input) {
		System.out.println("In getChoices with "+input);
		List<Set> sets = new ArrayList<Set>();
		int count = 0;
		for (Set set : dbSets) {
			if(set.getName().toLowerCase().contains(input.toLowerCase())){
				sets.add(set);
				if(++count == 10) break;
			}
		}
		return sets;
	}

}
