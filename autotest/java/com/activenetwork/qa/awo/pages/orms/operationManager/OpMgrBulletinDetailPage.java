/*
 * Created on Oct 20, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.operationManager;

import com.activenetwork.qa.awo.datacollection.legacy.orms.BulletinInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;

/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OpMgrBulletinDetailPage extends OperationsManagerPage {

	static private OpMgrBulletinDetailPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OpMgrBulletinDetailPage()
	{}
	static public OpMgrBulletinDetailPage getinstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OpMgrBulletinDetailPage();
		}
		return _instance;
	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {

		return browser.checkHtmlObjectExists(".class", "Html.A", ".text","Apply");
	}

	/**
	 * Input headline
	 * @param headline
	 */
	public void setHeadLine(String headline) {
		browser.setTextField(".id", "headline", headline, true);
	}

	/**
	 * Input bulletin detail information
	 * @param bulletin
	 */
	public void setBulletin(String bulletin) {
		browser.setTextArea(".id", "bulletin", bulletin, true);
	}

	/**
	 * Select priority
	 * @param priority
	 */
	public void selectPriority(String priority) {
		browser.selectDropdownList(".id", "priority", priority, true);
	}

	/**
	 * select active checkbox
	 */
	public void selectCurrentActive() {
		browser.selectCheckBox(".id", "active");
	}

	/**
	 * Set start date
	 * @param startdate
	 */
	public void setStartDate(String startdate) {
		browser.setTextField(".id", "start_date_ForDisplay", startdate, true);
	}

	/**
	 * Set end date
	 * @param enddate
	 */
	public void setEndDate(String enddate) {
		browser.setTextField(".id", "end_date_ForDisplay", enddate, true);
	}

	/**
	 * Select show at sublocation
	 */
	public void selectShowAtSubLocation() {
		browser.selectCheckBox(".id", "show_sublocations");
	}

	/**
	 * Select application
	 * @param mgr
	 */
	public void selectApplication(String mgr) {
		String[] application = mgr.split(",");
		for (int i = 0; i < application.length; i++) {
			browser.selectCheckBox(".id", "application_" + mgr);
		}
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	/**Click Apply button*/
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	/**
	 * Set bulletin details
	 * @param bulle---The data colletion of bulletin info
	 */
	public void setBulletinDetail(BulletinInfo bulle) {
		if (bulle.headline.length()>0) {
			setHeadLine(bulle.headline);
		}

		if (bulle.bulletin.length()>0) {
			setBulletin(bulle.bulletin);
		}

		if (bulle.priority.length()>0) {
			selectPriority(bulle.priority);
		}

		if (bulle.currentactive) {
			selectCurrentActive();
		}

		if (bulle.application.length()>0) {
			selectApplication(bulle.application);
		}

		if (bulle.startdate.length()>0) {
			System.out.println("bulle.startdate:"+bulle.startdate);
			setStartDate(bulle.startdate);
		}

		if (bulle.enddate.length()>0) {
			System.out.println("bulle.enddate:"+bulle.enddate);
			setEndDate(bulle.enddate);
		}

	}
	
	public String[] getWarningMessages() {
		String[] noteMessage = null;
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id", "NOTSET");
		if(objs.length > 0){
			noteMessage = new String[objs.length];
			for(int i=0; i < objs.length; i++){
				noteMessage[i] = objs[i].getProperty(".text");
			}
			
		}		
		Browser.unregister(objs);
		return noteMessage;
	}
	
	/*get content of headline of bulletin*/
	public String getHeadline() {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".id", "headline"));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find headline object.");
		}
        String text = objs[0].getAttributeValue("value");
		Browser.unregister(objs);
		
		return text;
	}
	/*get content of bulletin*/
	public String getBulleContent() {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".id", "bulletin"));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find headline object.");
		}

		String text = objs[0].text();
		Browser.unregister(objs);
		
		return text;
	}
	/*This method set up the basic information fo bulletin and click apply*/	
	public void setBulletinInfoAndClickApply(BulletinInfo bulletin) {		
		this.setBulletinDetail(bulletin);
		this.clickApply();
		ajax.waitLoading();
		waitLoading();		
	}
}
