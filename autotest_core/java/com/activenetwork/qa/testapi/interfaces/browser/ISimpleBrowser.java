package com.activenetwork.qa.testapi.interfaces.browser;

import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.KeyInput;

public interface ISimpleBrowser extends ISearch,IClick,IDropdownSelect,ISetText,IToggleSelect, ISynchronize {
	public boolean exists();
	
	public void waitExists();
	
	public void waitExists(int timeout);
	
	public void close();
		
	public String text();
	
	public String title();
	
	public String url();
	
	public boolean sync();
	
	public boolean sync(int timeout);
	
	public void inputKey(KeyInput... keys);

	public IHtmlObject[] getFrame(String idValue);
	
	public void catchScreenShot(String fullName);
	
	public void closeAllBrowsers();
	
	public String getDriverName();
}
