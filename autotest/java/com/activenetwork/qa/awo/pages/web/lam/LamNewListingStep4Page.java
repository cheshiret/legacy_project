package com.activenetwork.qa.awo.pages.web.lam;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;


public class LamNewListingStep4Page extends LamNewListingPage {
  	private static LamNewListingStep4Page _instance=null;
  	
  	public static LamNewListingStep4Page getInstance() {
  	  	if(null==_instance) {
  	  	  	_instance=new LamNewListingStep4Page();
  	  	}

  	  	return _instance;
  	}
  	
  	protected LamNewListingStep4Page() {
  	  
  	}
  	
  	public boolean exists() {
  	  	return browser.checkHtmlObjectExists(".id","mapviewport") && browser.checkHtmlObjectExists(".text","Save Creation");
  	}
  	
  	/**
  	 * Click to save the new creation
  	 */
  	public void clickSaveCreation() {
  	  	browser.clickGuiObject(".text","Save Creation");
  	}
  	
	/**
	 * Verify if the 3 "Target Campgrounds" icons display.  
	 * @return
	 */
	public boolean verifyGoogleMapDisplay(){  		
		//  		return browser.checkHtmlObjectExists(".class","Html.IMG", ".id", "mtgt_unnamed_0");
		boolean display = false;
//		IHtmlObject[] objs =  browser.getHtmlObject(".class","Html.IMG", ".src", 
//				new RegularExpression(".*images/maps/markerA.png$",false));
//		if(objs.length!=3){
//			logger.info("It should have 3 'A' img icons in Step4.");
//		}else{
//			display = true;
//			logger.info("Google Map successfully displays!");
//		}

		IHtmlObject[] objs =  browser.getHtmlObject(".class","Html.IMG", ".src", 
				new RegularExpression("images/maps/markerAttraction\\.png$",false));;
		if(objs.length!=1){
			logger.info("It should have 1 identified 'Attraction Location' img icons in Step4.");
		}else{
			display = true;
			logger.info("Google Map successfully displays!");
		}
		
		Browser.unregister(objs);
		return display;
	}
}
