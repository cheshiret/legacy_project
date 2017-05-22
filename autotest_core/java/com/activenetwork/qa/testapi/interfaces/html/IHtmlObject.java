package com.activenetwork.qa.testapi.interfaces.html;

import com.activenetwork.qa.testapi.interfaces.IGuiObject;

public interface IHtmlObject extends IGuiObject {
	public String getAttributeValue(String name);
	
	public String tag();
	
	public String text();
	
	public String id();
	
	public String name();
	
	public String type();
	
	public String title();
	
	public String style(String name);
	
	public String className();
	
	public IHtmlObject[] getChildren();
	
	public IHtmlObject getParent();
}
