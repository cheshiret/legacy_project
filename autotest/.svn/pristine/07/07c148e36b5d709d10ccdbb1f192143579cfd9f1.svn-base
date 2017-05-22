package com.activenetwork.qa.awo.util;

import com.activenetwork.qa.testapi.interfaces.browser.BrowserPlugin;

public class AwoBrowserPlugin extends BrowserPlugin {

	public static void init() {
		if(_instance==null) {
			_instance=new AwoBrowserPlugin();
		}
	}
	
	protected AwoBrowserPlugin(){};
	
	@Override
	public void recordPageTiming(String pageName, int time) {
		DataBaseFunctions.recordPageTime(pageName, TestDriverUtil.getTool(),time);
		
	}

}
