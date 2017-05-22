package com.activenetwork.qa.awo.pages.component;

import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;

/**
 * This class is created to generally handle the Group Selection feature in AWO.
 * The Group Selection UI usually contains two dropdown lists from/to and add/remove buttons.
 * Generally, we can select the options in the "from" dropdown list, after clicking the add button, 
 * the options will be added to the "to" dropdown list.
 * 
 * On the opposite, if we select options in the "to" dropdown list, after clicking the remove button, 
 * the selected options will be moved back to the "from" dropdown list.
 * 
 * @author jdu
 *
 */
public class GroupSelectionComponent {
	ISelect from;
	ISelect to;
	
	IHtmlObject add;
	IHtmlObject remove;
	
	public GroupSelectionComponent(ISelect from, ISelect to, IHtmlObject add, IHtmlObject remove) {
		this.from=from;
		this.to=to;
		this.add=add;
		this.remove=remove;
	}
	
	public void add(String... options) {
		from.select(options);
		add.click();
	}
	
	public void remove(String... options) {
		to.select(options);
		remove.click();	
		
	}
	
	public boolean fromHas(String option) {
		return from.getAllOptions().contains(option);
	}
	
	public boolean toHas(String option) {
		return to.getAllOptions().contains(option);
	}
}
