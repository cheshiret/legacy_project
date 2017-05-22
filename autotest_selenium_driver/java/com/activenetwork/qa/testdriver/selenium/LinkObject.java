package com.activenetwork.qa.testdriver.selenium;

import org.jsoup.nodes.Element;

import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.interfaces.html.ILink;

class LinkObject extends HtmlObject implements ILink{

	public LinkObject(Element element, String... handler) {
		super(element,handler);
	}

	@Override
	public String href() {
		try {
			return element.attr("href");
		} catch (Exception e) {
			throw new ActionFailedException(e);
		}
	}

}
