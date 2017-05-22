package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author jchen
 */
public class UwpTourListPage extends UwpPage {

	private RegularExpression buttonRE = new RegularExpression("(book now)|(book next)", false);
	
	private RegularExpression tourlinks = new RegularExpression(".*tourDetails.do\\?.*", false);

	private static UwpTourListPage _instance = null;

	public static UwpTourListPage getInstance() {
		if (null == _instance)
			_instance = new UwpTourListPage();

		return _instance;
	}

	public UwpTourListPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".className", buttonRE);
	}

	/**
	 * Go to tour details page by index
	 * @param i - index of tour on page, start from 1
	 * @return - tour name
	 */
	public String gotoTourDetails(int i) {
		IHtmlObject[] objs = browser.getHtmlObject(".className", buttonRE);
		IHtmlObject[] objs1 = browser.getHtmlObject(".href", tourlinks);

		IHtmlObject gto = (IHtmlObject) objs[i - 1];
		String tourName = (String) objs1[i * 2 - 1].getProperty(".text");
		tourName = tourName.trim();
		gto.click();
		Browser.unregister(objs);
		Browser.unregister(objs1);
		return tourName;
	}

	/**
	 * Go to tour details page by given tour name.
	 * @param tName - tour name
	 * @return - selected tour name
	 */
	public String gotoTourDetails(String tName) {
		IHtmlObject[] objs = browser.getHtmlObject(".className", buttonRE);
		IHtmlObject[] objs1 = browser.getHtmlObject(".href", tourlinks);

		String tourName = null;
		for (int i = 0; i < objs.length; i++) {
			tourName = (String) objs1[i * 2 + 1].getProperty(".text");
			tourName = tourName.trim();

			if (tourName.equalsIgnoreCase(tName)) {
				objs[i].click();
				return tourName;
			}
		}
		Browser.unregister(objs);
		Browser.unregister(objs1);
		return tourName;
	}
	
	public void clickTourName(String parkID, String tourID){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("/tourDetails\\.do\\?.*parkId="+parkID+"&tourId="+tourID+".*", false));
	}
	
	/**
	 * Get the first tour's availability in tour list page.
	 * @return - button text
	 */
	public String getTourAvailability(){
	  	IHtmlObject[] objs = browser.getHtmlObject(".className", buttonRE);
	  	String toReturn = objs[0].getProperty(".text");
	  	
	  	Browser.unregister(objs);
	  	return toReturn;
	}
	
	/**
	 * Get the first tour's descriptive status text.
	 * @return - descriptive text
	 */
	public String getAvailDescriptiveText(){
	  	IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Tour Search Results:.*", false));
	  	String text = ((IHtmlTable)objs[0]).getCellValue(3,0);
	  	text = text.split(getTourAvailability())[0];
	  	
	  	Browser.unregister(objs);
	  	return text;
	}
	
	public List<String> getAllEnterDateTours(){
		List<String> enterDateTours = new ArrayList<String>();
		int count = 0;

		do{
			if(this.checkNext() && count!=0){
				this.clickNext();
				this.waitLoading();
			}

			Property[] p1 = Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("^Enter Date.*", false));
			IHtmlObject[] tops=browser.getHtmlObject(p1);
			if(tops==null ||tops.length<1){
				throw new ObjectNotFoundException("Can't find \"Enter Date\" tour park");
			}
			Property[] p2 = Property.toPropertyArray(".class", "Html.A");
			IHtmlObject[] items;
			for(IHtmlObject o:tops){
				items=browser.getHtmlObject(p2,o);
				if(items==null||items.length<2){
					throw new ObjectNotFoundException("Can't find tour name");
				}
				enterDateTours.add(items[1].text());
				Browser.unregister(items);

			}
			Browser.unregister(tops);
			count++;
		}while(this.checkNext());

		return enterDateTours;
	}
	
	/**Check Next existed*/
	public boolean checkNext(){
		Property[] p = Property.toPropertyArray(".class", "Html.A", ".id", "resultNext", ".href", 
				new RegularExpression("^/tourSearchResult\\.do\\?.*", false));
		return browser.checkHtmlObjectExists(p);
	}
	
	/**Click Next button*/
	public void clickNext(){
		Property[] p = Property.toPropertyArray(".class", "Html.A", ".id", "resultNext", ".href", 
				new RegularExpression("^/tourSearchResult\\.do\\?.*", false));//(".*qa\\.reserveamerica\\.com:5101/tourSearchResult\\.do\\?.*", false));
		browser.clickGuiObject(p);
	}
	
	public List<String> getAllSoldAtParkTours(){
		List<String> soldAtParkTours = new ArrayList<String>();
		Property[] p1 = Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("^Sold at Park.*", false));
		IHtmlObject[] tops=browser.getHtmlObject(p1);
		if(tops==null ||tops.length<1){
			throw new ObjectNotFoundException("Can't find \"Sold at Park\" tour park");
		}
		Property[] p2 = Property.toPropertyArray(".class", "Html.TD");
		IHtmlObject[] items;
		for(IHtmlObject o:tops){
			items=browser.getHtmlObject(p2,o);
			if(items==null||items.length<2){
				throw new ObjectNotFoundException("Can't find tour name");
			}
			soldAtParkTours.add(items[1].text());
			Browser.unregister(items);
		}
		
        Browser.unregister(tops);
		return soldAtParkTours;
	}
	
	public boolean checkProductPhotoExist(String tourName, String description){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression(".*"+tourName+".*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".title", description);
		Property[] p3 = Property.toPropertyArray(".class", "Html.IMG", ".alt", description);
		return browser.checkHtmlObjectExists(Property.atList(p1, p2)) && browser.checkHtmlObjectExists(Property.atList(p1, p3));
	}
	
}
