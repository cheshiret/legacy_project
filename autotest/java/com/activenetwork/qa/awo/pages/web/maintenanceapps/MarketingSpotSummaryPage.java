/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.maintenanceapps;

import java.util.List;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: It is for marketing spot summary page. 
 * The page is shown after sign in with a valid account and select role and location.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Dec 16, 2012
 */
public class MarketingSpotSummaryPage extends WebMaintenanceAppUserPanel {
	private static MarketingSpotSummaryPage _instance = null;

	public static MarketingSpotSummaryPage getInstance() {
		if (null == _instance)
			_instance = new MarketingSpotSummaryPage();

		return _instance;
	}
	
	protected MarketingSpotSummaryPage() {
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text", 
				new RegularExpression("^Marketing Spot Summary.*", false));
	}
	
	public void clickAddNewSpotButton(){
		browser.clickGuiObject(".id", "addNewSpot");
	}
	
	public List<String> getMarketingSpots(){
		IHtmlObject[] objs= browser.getTableTestObject(".className", "dataGrid");
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> values = table.getColumnValues(1);
		
		Browser.unregister(objs);
		return values;
	}
	
	public boolean isMarketingSpotsExisting(String spot){
		List<String> spots = this.getMarketingSpots();
		if(spots.toString().contains(spot)){
			return true;
		}else{
			return false;
		}
	}
}
