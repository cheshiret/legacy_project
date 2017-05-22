/*
 * Created on Dec 7, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author Jdu
 *
 * Abstract the Orms Park Details page
 */
public class OrmsParkDetailsPage extends OrmsPage {
  	private static OrmsParkDetailsPage _instance;
  	
  	private OrmsParkDetailsPage() {
  	  
  	}
  	
  	public static OrmsParkDetailsPage getInstance() {
  	  	if(null==_instance) {
  	  	  	_instance=new OrmsParkDetailsPage();
  	  	}
  	  	
  	  	return _instance;
  	}
  	
  	public boolean exists() {
  	  	RegularExpression pattern=new RegularExpression("\"SearchResultSites\\.do\",.*\"searchFromParkDetail\",.*\"SiteSearchWorker\"",false);
  	  	
  	  	return browser.checkHtmlObjectExists(".text", "Search", ".href", pattern) || browser.checkHtmlObjectExists(".text", "Search", ".onClick", new RegularExpression("MarinaDetailsPage", false));
  	}
  	
  	public void setParkName(String name) {
  	  	browser.setTextField(".id","park",name,true);
  	}
  	
  	public void setArrival(String arrival) {
  	  	browser.setTextField(".id","date_specific_date_start_ForDisplay",arrival,true);
  	}
  	
  	public void setDeparture(String depart) {
  	  	browser.setTextField(".id","date_specific_date_end_ForDisplay",depart,true);
  	}
  	
  	public void setBeginning(String begin) {
  	  	browser.setTextField(".id","date_range_date_start_ForDisplay",begin,true);
  	}
  	
  	public void setEnding(String end) {
  	  	browser.setTextField(".id","date_range_date_end_ForDisplay",end,true);
  	}
  	
  	public void setNights(String nights) {
  	  	RegularExpression pattern=new RegularExpression("date_(range|specific)_nights",false);
  	  
  	  	browser.setTextField(".id",pattern,nights,true);
  	}
  	
  	public void clickGo() {
  	  	RegularExpression pattern=new RegularExpression(".*\"SearchResultSites\\.do\",.*\"searchFromParkDetail\",.*\"SiteSearchWorker\".*",false);
  	  	RegularExpression  pattern1=new RegularExpression("^(Go|Search)$", false);
  	  	browser.clickGuiObject(".text",pattern1,".href",pattern,true);
  	}
  	
  	public String getMessage() {
  		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id","NOTSET");
  		String text=objs[0].text();
  		Browser.unregister(objs);
  	  	return text;
  	}
  	
  	public void setSpecificSearchCriteria(String parkName,String arrival,String depart,String nights) {
  	  	if(parkName!=null && parkName.length()>0) {
  	  	  	setParkName(parkName);
  	  	}
  	  	
  	  	if(arrival!=null && arrival.length()>0) {
  	  	  	setArrival(arrival);
  	  	}
  	  	
  	  	if(depart!=null && depart.length()>0) {
  	  	  	setDeparture(depart);
  	  	}
  	  	
  	  	if(nights!=null && nights.length()>0) {
  	  	  	setNights(nights);
  	  	}
  	}
  	
  	public void setRangeSearchCriteria(String parkName,String begin,String end,String nights) {
	  	if(parkName!=null && parkName.length()>0) {
	  	  	setParkName(parkName);
	  	}
	  	
	  	if(begin!=null && begin.length()>0) {
	  	  	setBeginning(begin);
	  	}
	  	
	  	if(end!=null && end.length()>0) {
	  	  	setEnding(end);
	  	}
	  	
	  	if(nights!=null && nights.length()>0) {
	  	  	setNights(nights);
	  	}
	}
  	
  	public void searchSites(SiteInfoData site) {
  	  	if(site.advanced) {
  	  	  	setRangeSearchCriteria(site.parkName,site.rangeStart,site.rangeEnd,site.dayNightNum);
  	  	} else {
  	  	  	setSpecificSearchCriteria(site.parkName,site.arrivalDate,site.departDate,site.dayNightNum);
  	  	}
  	  	
  	  	clickGo();
  	}
  	
  	public String getAlias(){
  		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("Park:.*", false));
  		IHtmlTable resDetail = (IHtmlTable) objs[0];
  		String alias=resDetail.getCellValue(1, 1);
  		Browser.unregister(objs);
  		return alias;
  	}
  	
  	public void clickParkResult(){
  		browser.clickGuiObject(".class","Html.A",".text","Park Results",true);
  	}
  	
  	public void clickImportantInformation(){
		browser.clickGuiObject(".id","summary_70033_tab_2_title");
  	}

  	public boolean checkImportantInformationTextIsExist(String info){
  		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text",new RegularExpression("( |)" + info,false));
  	}
  	
	/**
	 * Get value of state drop down list in left top of page.
	 * @return
	 */
	public String getState(){
		return browser.getDropdownListValue(".id", "locationstate");
	}
	
	public void clickMap(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Map");
	}
}
