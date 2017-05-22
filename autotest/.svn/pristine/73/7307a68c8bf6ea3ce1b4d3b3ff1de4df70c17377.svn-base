/*
 * $Id: InvMgrLoopDetailsPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite;

import com.activenetwork.qa.awo.datacollection.legacy.LoopInfoData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author jdu
 */
public class InvMgrLoopDetailsPage extends InventoryManagerPage {

	/**
	 * Script Name   : LoopDetailsPage
	 * Generated     : Oct 3, 2006 11:04:55 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2006/10/03
	 */

	private static InvMgrLoopDetailsPage _instance = null;

	public static InvMgrLoopDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrLoopDetailsPage();
		}

		return _instance;
	}

	protected InvMgrLoopDetailsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Loop/Area Details");
	}

	/**
	 * Fill in loop name.
	 * @param name - loop name
	 */
	public void setLoopName(String name) {
		browser.setTextField(".id", "LoopView.name", name);
	}

	/**
	 * Fill in description.
	 * @param description
	 */
	public void setDescription(String description) {
		browser.setTextArea(".id", "LoopView.description", description);
	}

	/** Click on OK link. */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("OK|Ok",false));
	}

	/** Click on Cancel link. */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/** Click on Loop/Area Details link. */
	public void clickLoopAreaDetails() {
		browser.clickGuiObject(".class", "Html.A", ".text",
						"Loop/Area Details");
	}

	/** Click on Loop's Sites link. */
	public void clickLoopSites() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Loop's Sites");
	}

	/**
	 * Select parent.
	 * @param parent
	 */
	public void selectParent(String parent) {
		browser.selectDropdownList(".id", "LoopView.parentID", parent);
	}

	/** Click on Delete this Loop/Area link. */
	public void clickDeleteThisLoopArea() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Delete this Loop/Area");
	}

	/**
	 * Update loop information.
	 * @param ld - loopinfo data
	 */
	public void updateLoopInfo(LoopInfoData ld) {
		if(null!=ld){
			if(null!=ld.loopName && ld.loopName.length()>0){
				this.setLoopName(ld.loopName);
			}
	        if(null!=ld.parent && ld.parent.length()>0){
	    		this.selectParent(ld.parent);
	        }
	        if(null!=ld.description && ld.description.length()>0){
	    		this.setDescription(ld.description);
	        }
		}
		this.clickOK();
	}

	/**
	 * Add a new loop.
	 * @param ld - loopinfodata
	 */
	public void addLoop(LoopInfoData ld) {
		this.updateLoopInfo(ld);
	}
	
	/**
	 * Click the button 'View Change Request Items'
	 */
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}
	
	/**
	 * Check button 'View Change Request Items' exist or not
	 * @return
	 */
	public boolean checkViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", rex);
	}
	
    /**
	 * Get warning message
	 * @return
	 */
	public String getWarningMessage(){
		String warningMessage = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0){
			warningMessage =objs[0].getProperty(".text").toString();
		}else throw new ObjectNotFoundException("Object can't find.");
		
		Browser.unregister(objs);
		return warningMessage;
	}
}
