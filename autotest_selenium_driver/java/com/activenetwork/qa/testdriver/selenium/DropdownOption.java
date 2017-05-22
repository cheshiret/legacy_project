package com.activenetwork.qa.testdriver.selenium;

import org.jsoup.nodes.Element;

import com.activenetwork.qa.testapi.interfaces.html.IOption;

class DropdownOption extends HtmlObject implements IOption{

	public DropdownOption(Element element,String handler) {
		super(element,handler);
	}

}
