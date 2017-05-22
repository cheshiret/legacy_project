package com.activenetwork.qa.testdriver.selenium;

import org.jsoup.nodes.Element;

import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.interfaces.html.IFrame;

class FrameObject extends HtmlObject implements IFrame {

	public FrameObject(Element element,String... handler) {
		super(element,handler);
	}

	@Override
	public boolean containsText(Object value) {
		try {
			String text=RuntimeUtil.parsePropertyValue(value);
			
			return element.text().contains(text);
		} catch (Exception e) {
			throw new ActionFailedException(e);
		}
	}

}
