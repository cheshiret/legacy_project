
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author jchen
 */
public class UwpHeaderBar extends UwpMemberStatusBar {
	private static UwpHeaderBar _instance = null;

	public static UwpHeaderBar getInstance() {
		if (null == _instance)
			_instance = new UwpHeaderBar();

		return _instance;
	}

	protected UwpHeaderBar() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "topnav");
//		return browser.checkHtmlObjectExists(".class","Html.A",".id", "MyAccount");
	}
	
	/**
	 * Click on camping spot link to go to campground search page
	 */
	public void gotoFindCampSpot() {
		//Sept 17, 2013 Chevy Wong - Update due to plan your trip topTabIndex change to Search in QA1
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".id", new RegularExpression("CampingSpot|Search|home",false), 
				".href", new RegularExpression(".*tti=(CampingSpot|Search|home)$",false)));
	}
	
	/**
	 * Go to my resevation account page.
	 */
	public void gotoMyReservationsAccount() {
//		RegularExpression pattern=new RegularExpression("/checkAccountStatus\\.do\\?topTabIndex=MyAccount",false);
		RegularExpression pattern=new RegularExpression("/checkAccountStatus\\.do(\\?tti=MyAccount)?",false);//Lesley[20140116]update due to link href changed
		browser.clickGuiObject(".class","Html.A",".href", pattern);
	}
	
	/**
	 * Click on Find Tour link to go to tour park list page.
	 */
	public void gotoTourParkList() {
	  	IHtmlObject[] tour=browser.getHtmlObject(".class","Html.A",".id", new RegularExpression("FindTour",false));
	  	
	  	if(tour.length<1)//The link id is 'Home' in HC and AN plws and '' in NE private label
	  	  	tour=browser.getHtmlObject(".id",new RegularExpression("(Home|tours)",false),
	  	  	    						".text",new RegularExpression("(Find|) Tours",false));
	  	tour[0].click();
	  	Browser.unregister(tour);
	}
	
	/**
	 * Go to wildness permit page.
	 */
	public void gotoWildnessPermit() {
		browser.clickGuiObject(".id", new RegularExpression("Permits|Search", true));
	}
	
	/**
	 * Go to search by map(the link "View our map" under "HELP & FAQ" section) in home page
	 */
	public void gotoCampgroudByMap() {
//		RegularExpression pattern=new RegularExpression("topTabIndex=CampgroundMap",false);
//		RegularExpression pattern=new RegularExpression("tti=CampgroundMap",false);//Lesley[20140116]update due to href changed
		RegularExpression pattern=new RegularExpression(".*(tti|topTabIndex)=CampgroundMap", false);//Sara[20140219]update due to href changed
		browser.clickGuiObject(".class", "Html.A", ".href", pattern, true);
	}
	
	/**
	 * this is legacyCampgroundByMap which is only available for PLWs
	 */
	public void legacyGotoCampgroundByMap() {
		RegularExpression pattern=new RegularExpression("tti=CampgroundMap",false);//Lesley[20140116]update due to href changed
		browser.clickGuiObject(".class", "Html.A", ".href", pattern, true);
	}
	
	/**
	 * Go to recreation area search home page.
	 */
	public void gotoRecreationSearch() {
		browser.clickGuiObject(".id", "RecreationArea");
	}
	
	/**
	 * Click Help link in home page to go to online help page.
	 */
	public void gotoOnlineSupportSite() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Help...");
	}
	
	public boolean isUnifiedSearch(){
		return browser.checkHtmlObjectExists(".class","Html.Form",".id", "unifSearchForm");
	}
	
	public boolean isUnifiedSearchExist(){
		return browser.checkHtmlObjectExists(".class","Html.FORM",".id", "unifSearchForm");
	}
	
	/**
	 * Click on Home link to go to REC home page
	 */
	public void clickHomeLink() {
		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".id",new RegularExpression("banner|topnav|footerLogo|header",false));
		RegularExpression pattern=new RegularExpression("/(welcome\\.do.*)?$|/recgovHome\\.do\\?topTabIndex=Home",false);
		Property[] p2=Property.toPropertyArray(".class","Html.A",".href", pattern);
		
		browser.searchObjectWaitExists(Property.atList(p1,p2));
	  	browser.clickGuiObject(Property.atList(p1,p2),true,0);
	}
	
	/**
	 * Click on "EXPLORE AND MORE" link to go to REC explore and more page
	 */
	public void gotoExploreAndMore(){
		browser.clickGuiObject(".id", "Explore");
	}

	
	public void clickShareOurDataLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Share Our Data");
	}
	
	public void clickRecGovLogo(){
		browser.clickGuiObject(".class", "Html.IMG", ".id", "headerGraphic");
	}

	public void clickContractUs() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Contact Us");
	}
	
	public void clickAboutUs() {
		RegularExpression pattern=new RegularExpression("^About Us$",false);
		browser.clickGuiObject(".class", "Html.A", ".text", pattern);
	}

	/**
	 * "New Look! New Features!" in Rec.Gov
	 */
	public void clickNewLookFeatures(){
		browser.clickGuiObject(".class", "Html.A", ".title", "About New Look!");
	}
}
