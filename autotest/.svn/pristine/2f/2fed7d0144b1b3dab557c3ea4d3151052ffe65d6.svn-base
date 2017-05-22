package com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.DockInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 10, 2012
 */
public class InvMgrDockDetailsPage extends InvMgrDockDetailsCommonPage {
	
	private static InvMgrDockDetailsPage _instance = null;
	
	private InvMgrDockDetailsPage() {}
	
	public static InvMgrDockDetailsPage getInstance() {
		if(_instance == null) {
			_instance = new InvMgrDockDetailsPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("DockView-\\d+\\.parentID", false));
	}
	
	private String prefix = "DockView-\\d+\\.";
	public void clickNotesAlerts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Notes & Alerts", true);
	}
	
	public void clickAddNotesAlerts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Notes/Alerts", true);
	}
	
	public void clickDeleteThisDockArea() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete this Dock/Area", true);
	}
	
	public void setDockAreaName(String name) {
		browser.setTextField(".id", new RegularExpression(prefix+"dockName", false), name);
	}
	
	public void selectParent(String parent) {
		if(!StringUtil.isEmpty(parent)) {
			browser.selectDropdownList(".id", new RegularExpression(prefix+"parentID", false), parent);
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefix+"parentID", false), 0);
		}
	}
	
	public void setDescription(String dscr) {
		browser.setTextField(".id", new RegularExpression(prefix+"description", false), dscr);
	}
	
	public void setupDockInfo(DockInfo dock) {
		if(null != dock.getName()){
			setDockAreaName(dock.getName());
		}
		if(null != dock.getParent()) {
			selectParent(dock.getParent());
		}
		if(null != dock.getDescription()){
			setDescription(dock.getDescription());
		}
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	public String getParent(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"parentID", false));
	}

	public void verifyDockDetailInfo(DockInfo expectDockInfo){
		boolean result = true;
		result &= MiscFunctions.compareString("Name", expectDockInfo.getName(), this.getDockAreaName());
		result &= MiscFunctions.compareString("Parent", expectDockInfo.getParent(), this.getParent());
		result &= MiscFunctions.compareString("Description", expectDockInfo.getDescription(), this.getDescription());
		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
	}
	
	public List<String> getAvailableParentsFromDropDownlist() {
		return browser.getDropdownElements(".id", new RegularExpression(prefix+"parentID", false));
	}
}
