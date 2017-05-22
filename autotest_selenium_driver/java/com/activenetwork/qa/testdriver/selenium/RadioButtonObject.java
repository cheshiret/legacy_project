package com.activenetwork.qa.testdriver.selenium;

import org.jsoup.nodes.Element;

import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;

class RadioButtonObject  extends HtmlObject implements IRadioButton {

	public RadioButtonObject(Element element,String... handler) {
		super(element,handler);
	}

	@Override
	public void deselect() {
		getWebElement().clear();
	}

	@Override
	public boolean isSelected() {
		return getWebElement().isSelected();
	}

	@Override
	public void select() {
		if(!isSelected()) {
			getWebElement().click();
		}
	}
}
