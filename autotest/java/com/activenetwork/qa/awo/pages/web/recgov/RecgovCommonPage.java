package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.KeyInput;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 *
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#: AUTO-680
 *
 * @author SWang
 * @Date  Jul 27, 2011
 */
public abstract class RecgovCommonPage extends UwpPage {
    
	//Logo
	public boolean checkBannerExist(){
		return browser.checkHtmlObjectExists(".id", "banner");
	}

	//Include Home, EXPLORE AND MORE, GET INVOLVED, SHARE OUR DATA, MY ACCOUNT
	public boolean checkTopNavigationExist(){
		return browser.checkHtmlObjectExists(".id", "topnav");
	}

	//Include Log-In and Sign-UP
	public boolean checkTabBarExist(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "tabsbar");
	}

	public boolean checkFooterExist(){
		return browser.checkHtmlObjectExists(".id", "footer");
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
	
	public String get4RiverFooterInfo(){
		IHtmlObject[] objs=browser.getTableTestObject(".className", "visitorsPermit");
		if(objs==null ||objs.length<1){
			throw new ObjectNotFoundException("Can't find Visitor Permit Table");
		}
		
		IHtmlTable table=(IHtmlTable)objs[0];
		int row=table.findRow(0, new RegularExpression("Issuing Officers signature",false));
	    String footor=table.getCellValue(row+1, 0);
	    Browser.unregister(objs);
	    return footor;
	}

	public void inputHomeKey(){
		browser.inputKey(KeyInput.get(KeyInput.HOME));
	}
	public void inputEndKey(){
		browser.inputKey(KeyInput.get(KeyInput.END));
	}
	public void inputLeftKey(){
		browser.inputKey(KeyInput.get(KeyInput.ARROW_LEFT));
	}
	public void inputRightKey(){
		browser.inputKey(KeyInput.get(KeyInput.ARROW_RIGHT));
	}
	public void inputDeleteKey(){
		browser.inputKey(KeyInput.get(KeyInput.DELETE));
	}
	public void inputBackspaceKey(){
		browser.inputKey(KeyInput.get(KeyInput.BACKSPACE));
	}
	public void inputUpKey(){
		browser.inputKey(KeyInput.get(KeyInput.ARROW_UP));
	}
	public void inputDownKey(){
		browser.inputKey(KeyInput.get(KeyInput.ARROW_DOWN));
	}
	public void inputBlackKey(){
		browser.inputKey(KeyInput.get(KeyInput.BACKSPACE));
	}
	public void inputEnterKey(){
		browser.inputKey(KeyInput.get(KeyInput.ENTER));
	}
	public void inputEscKey(){
		logger.info("Sending ESC key..");
		browser.inputKey(KeyInput.get(KeyInput.ESC));
	}

	public void inputChar(char charactor){
		if(charactor==' '){
			browser.inputKey(KeyInput.get(" "));
		}else{
			browser.inputKey(KeyInput.get(String.valueOf(charactor)));
		}
	}

	protected boolean isTextFieldHighLighted(int index,Property... p){
		return isTextFieldHighLighted(index,p,(IHtmlObject)null);
	}

	protected boolean isTextFieldHighLighted(String key,Object value){
		Property p=new Property(key,value);
		return isTextFieldHighLighted(0,new Property[]{p},(IHtmlObject)null);
	}

	protected boolean isTextFieldHighLighted(Property... p){
		return isTextFieldHighLighted(0,p,(IHtmlObject)null);
	}

	protected boolean isTextFieldHighLighted(Property[] p,IHtmlObject top){
		return isTextFieldHighLighted(0,p,(IHtmlObject)null);
	}

	protected boolean isTextFieldHighLighted(int index,Property[] p,IHtmlObject top){
		IHtmlObject[] objs=browser.getTextField(p,top);
		boolean isHighLighted=false;
		if(objs!=null && objs.length>0){
			IHtmlObject obj=objs[index];
			String className=obj.getProperty(".className");
			if(className.startsWith("s highlightedfield")){
				isHighLighted=true;
			}
		}else{
			throw new ObjectNotFoundException("Can't find object");
		}

		return isHighLighted;
	}

	/**
	 * Capitalize String
	 * @param data
	 * @return
	 */
	public String capitalizeString(String data){
		logger.info("Capitalize String: "+data);
		int length = data.split(" ").length;
		String capitalizeString = "";
		for(int i=0; i<length; i++){
			capitalizeString = capitalizeString +" "+data.split(" ")[i].substring(0, 1).toUpperCase()+data.split(" ")[i].substring(1).toLowerCase();
		}
		return capitalizeString.trim();
	}
	
	/**
	 * Is result filters displaying
	 * @return
	 */
	public boolean isResultFiltersDisplaying(){
		Property[] p1 = Property.toPropertyArray(".className", "resultsFilters", ".id", "resultsFilters");
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "filterCategory");
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}
	
	/**
	 * Verify no result filter display
	 */
	public void verifyNoResultFilter(){
		if(this.isResultFiltersDisplaying()){
			throw new ErrorOnPageException("Result filters should not display");
		}
		logger.info("Successfully verify Result filters doesn't display");
	}
}
