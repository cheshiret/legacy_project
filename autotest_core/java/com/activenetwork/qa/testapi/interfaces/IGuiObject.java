package com.activenetwork.qa.testapi.interfaces;

/**
 * A GuiObject provides generic access to objects in the software under test. 
 * @author jdu
 *
 */
public interface IGuiObject {
	public void click();
	
	public void doubleClick();
	
	public boolean exists();
	
	public String getProperty(String name);
	
	public boolean isEnabled();
	
	public boolean isVisible();
	
	public void unregister();
	
	public void hover();
}
