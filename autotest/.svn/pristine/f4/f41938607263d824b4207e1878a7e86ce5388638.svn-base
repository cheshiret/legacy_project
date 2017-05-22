package com.activenetwork.qa.awo.pages;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.page.HtmlMainPage;

public abstract class JiraPage extends HtmlMainPage {
	
	public void waitExist(){
		
		int count = 0;
		while(!this.exists()){
			Browser.sleep(1);
			count++;
			if(count>SLEEP){
				throw new ItemNotFoundException("Can not found....");
			}
		}
	}
}
