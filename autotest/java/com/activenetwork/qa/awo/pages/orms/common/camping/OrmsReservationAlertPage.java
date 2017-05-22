/*
 * Created on Apr 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.camping;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author raonqa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsReservationAlertPage extends OrmsPage {

	static private OrmsReservationAlertPage _instance = null;

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsReservationAlertPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsReservationAlertPage();
		}

		return _instance;
	}

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsReservationAlertPage() {
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		boolean flag1=browser.checkHtmlObjectExists(".class", "Html.DIV", ".id","resalert");
	    boolean flag2=browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text","Reservation Notes & Alerts");
	    boolean flag3=browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "custalert");
	    return flag1||flag2||flag3;
	}

	/**Click add new button*/
	public void clickAddNew() {
		IHtmlObject[] frame = browser.getFrame("transaction");		
		if(frame.length > 0){
			browser.clickGuiObject(".class", "Html.A", ".text", "Add New", true, 0, frame[0]);
		}else{
			throw new ObjectNotFoundException("Frame 'transaction' does not found!");
		}		
		Browser.unregister(frame);
	}
	
	/**Select note and alert type*/
	public void selectype(String type) {
		browser.selectDropdownList(".class", "Html.SELECT", ".id",
				"noteAlertType", type, true);
	}
	/**Input the start date*/
	public void setStartDate(String startdate) {
		browser.setTextField(".id", "noteAlertStartDate_ForDisplay", startdate,true);
		browser.clickGuiObject(".text", "Reservation Notes & Alerts", true);
	}

	/**Input the End date*/
	public void setEndDate(String enddate) {
		browser.setTextField(".id", "noteAlertEndDate_ForDisplay", enddate,true);
		browser.clickGuiObject(".text", "Reservation Notes & Alerts", true);
	}

	/**Input note and alert*/
	public void setNoteAndAlert(String note) {
		browser.setTextArea(".id", "noteAlertText", note, true);
	}

	/**select active checkbox*/
	public void selectActive() {
		browser.selectCheckBox(".id", "noteAlertActive");
	}

	/**Unselect active checkbox*/
	public void unSelectActive() {
		browser.unSelectCheckBox(".id", "noteAlertActive");
	}

	/**Select applications that the note and alert apply*/
	public void selectApplication(String application) {
		browser.selectCheckBox(".id", application);
	}
	
	/**don't select some applications*/
	public void unSelectApplication(String application) {
		browser.unSelectCheckBox(".id", application);
	}

	/**Select call center application*/
	public void selectCallCenter() {
		selectApplication("noteAlertAppName_5");
	}

	/**Don't select call center application*/
	public void unSelectCallCenter() {
		unSelectApplication("noteAlertAppName_5");
	}

	/**Select operation manager application*/
	public void selectOperationManager() {
		selectApplication("noteAlertAppName_10");
	}

	/**Don't select operation manager application*/
	public void unSelectOperationManager() {
		unSelectApplication("noteAlertAppName_10");
	}

	/**Select marina manager application*/
	public void selectMarinaManager() {
		selectApplication("noteAlertAppName_17");
	}

	/**Don't select marina manager*/
	public void unSelectMarinaManager() {
		unSelectApplication("noteAlertAppName_17");
	}

	/**Select Field Manager application*/
	public void selectFieldManager() {
		selectApplication("noteAlertAppName_6");
	}

	/**Don't select Field Manager application*/
	public void unSelectFieldManager() {
		unSelectApplication("noteAlertAppName_6");
	}

	/**Select Golf Manager application*/
	public void selectGolfManager() {
		selectApplication("noteAlertAppName_18");
	}

	/**Don't select Golf Manager application*/
	public void unSelectGolfManager() {
		selectApplication("noteAlertAppName_18");
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	/**Click Cancel button*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
    /**Get the table object of noteAlert list*/	
	public IHtmlObject[] getAlertNoteTable() {
		return browser.getTableTestObject(".text", new RegularExpression("^Note/Alert ID Start Date End Date.*", false));
	}
	/**Click the first one in noteAlert list */
	public void clickFirstNoteAlertInList() {
		IHtmlObject[] frame = browser.getFrame("transaction");
		if(frame.length > 0){
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("\\d+",false), true, 0, frame[0]);
		}else{
			throw new ObjectNotFoundException("Frame 'transaction' does not found!");
		}		
		Browser.unregister(frame);
	}
}
