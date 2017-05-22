package com.activenetwork.qa.awo.pages.web.lam;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;


public class LamNewListingStep2Page extends LamNewListingPage {
	private static LamNewListingStep2Page _instance=null;

	public static LamNewListingStep2Page getInstance() {
		if(null==_instance) {
			_instance=new LamNewListingStep2Page();
		}

		return _instance;
	}

	protected LamNewListingStep2Page() {

	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id","mapviewport") && browser.checkHtmlObjectExists(".text","Next Step");
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
//			logger.info("It should have 3 'A' img icons in Step2.");
//		}else{
//			display = true;
//			logger.info("Google Map successfully displays!");
//		}

		IHtmlObject[] objs =  browser.getHtmlObject(".class","Html.IMG", ".src", 
				new RegularExpression("/images/maps/markerAttraction\\.png$",false));
		if(objs.length!=2){
			logger.info("It should have 2 'Attraction Location' img icons in Step2.");
		}else{
			display = true;
			logger.info("Google Map successfully displays!");
		}
		
		Browser.unregister(objs);
		return display;
	}
}
