
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author QA
 */
public class UwpHomePage extends UwpPage {
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private UwpHomePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected UwpHomePage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	public static UwpHomePage getInstance() throws PageNotFoundException { //NOTE: set to 'public' to use
		if (null == _instance) {
			_instance = new UwpHomePage();
		}

		return _instance;
	}
	
	/** HC and AN PLWs home page is tour list page.*/
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
			"^(homesearchbtn)$|^(btnDiv)$|^(searchcgrounds)$|^(homelayout)$|^(mainFeature)$|^(acm-contentarea)$^(permitWelcome)&|",false));
//		return browser.checkHtmlObjectExists(".id", new RegularExpression("^(homesearchbtn)$|^(btnDiv)$", false));
	}
	
	/**
	 * Wait for home which user has not been logged in
	 * @return
	 */
	public void homeBeforeSignIn(){
		browser.searchObjectWaitExists(".id","signIn",".text","Sign In");
	}
	
	/**
	 * Click on Campground Directory link in home page.
	 */
	public void gotoCampgroundDirectory() {
		//browser.clickGuiObject(".class", "Html.A", ".text",
		//	"Campground Directory");
		// update by Lesley Wang
//		browser.clickGuiObject(".class", "Html.A", ".id",
//			"campgroundDirectory");
		
		//Quentin[20130910] UWP new ui change
		Property oldProperty[] = Property.toPropertyArray(".class", "Html.A", ".id", "campgroundDirectory");
		Property newProperty[] = Property.toPropertyArray(".class", "Html.A", ".href", "/campgroundDirectory.do");
		if(browser.checkHtmlObjectExists(oldProperty)) {
			browser.clickGuiObject(oldProperty);
		} else if(browser.checkHtmlObjectExists(newProperty)) {
			browser.clickGuiObject(newProperty);
		}
	}
	
	public void gotoAccountPage() {
		RegularExpression pattern=new RegularExpression("topTabIndex=MyAccount|/checkAccountStatus\\.do",false);//Sara[20140221] changed in rec.gov
		browser.clickGuiObject(".class","Html.A",".href",pattern);
	}
	
	public void clickHome(){
		browser.clickGuiObject(".class", "Html.A", ".text", "HOME");
	}
	
	/**
	 * Get top navigation 
	 * Such as: Home, Map, Explore and More, Get Involved, Share Our Data, My Account
	 * @return
	 */
	public String getTopNavigationInfo(){
		logger.info("Get top navigation");
		String topNavString = "";
		
		IHtmlObject[] topNav = browser.getHtmlObject(".id", "topnav");
		if(topNav!=null && topNav.length>0){
			topNavString = topNav[0].getProperty(".text");
		}else{
			throw new ObjectNotFoundException("Top nevigation object can't be found.");
		}
		
		Browser.unregister(topNav);
		return topNavString;
	}

	/**
	 * Get tab bar info below the top navigation bar
	 * Such as: Log-In    Sign-UpHelp/FAQ
	 * @return
	 */
	public String getTabBarInfo(){
		logger.info("Get tab bat which is below the top navigation bar.");
		String tabBarString = "";
		
		IHtmlObject[] tabBar = browser.getHtmlObject(".class", "Html.DIV", ".className", "tabsbar");
		if(tabBar!=null && tabBar.length>0){
			tabBarString = tabBar[0].getProperty(".text");
		}else{
			throw new ObjectNotFoundException("Tab bar object can't be found.");
		}
		
		Browser.unregister(tabBar);
		return tabBarString;
	}

	/**
	 * Get footer info in the page bottom
	 * Such as: Reservation Policies   General Rules   Accessibility   Privacy Policy   Disclaimers   FOIA   USA.gov
	 * @return
	 */
	public String getFooterInfo(){
		logger.info("Get footer info in the page bottom.");
		String footerString = "";
		
		IHtmlObject[] footer = browser.getHtmlObject(".id", "footer");
		if(null!=footer && footer.length>0){
			footerString = footer[0].getProperty(".text");
		}else{
			throw new ObjectNotFoundException("Footer object can't be found.");
		}
		
		Browser.unregister(footer);
		return footerString;
	}

	
	public void clickLoginLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Log-In");
	}
	
	public void clickSigUpLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("(Sign(-| )Up)|(Sign In or Sign Up)",false));
	}
	
	public void clickHelpFAQLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Help/FAQ");
	}

	public void clickMyAccountLink() {
		browser.clickGuiObject(".class","Html.A",".text","My Account");
	}
	
	public void clickReservationPoliviesLink() {
		browser.clickGuiObject(".class","Html.A",".text","Reservation Policies");
	}
	
	public void clickGeneralRulesLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "General Rules");
	}
	
	public void clickAccessibility(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Accessibility");
	}
	
	public void clickPrivacyPolicy(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Privacy Policy");
	}
	
	public void clickDisclaimers(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Disclaimers");
	}
	
	public void clickPartners(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Partners");
	}
	
	public void clickFOIA(){
		browser.clickGuiObject(".class", "Html.A", ".text", "FOIA");
	}
}
