package com.activenetwork.qa.testdriver.selenium;

import org.jsoup.nodes.Element;

import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;

class CheckBoxObject extends HtmlObject implements ICheckBox {

	public CheckBoxObject(Element element, String... handler) {
		super(element,handler);
	}

	@Override
	public void deselect() {
		if(getWebElement().isSelected()){
			click();
		}
	}

	@Override
	public boolean isSelected() {
		return getWebElement().isSelected();
	}

	@Override
	public void select() {
		if(!getWebElement().isSelected()){
			click();
		}
	}

}
