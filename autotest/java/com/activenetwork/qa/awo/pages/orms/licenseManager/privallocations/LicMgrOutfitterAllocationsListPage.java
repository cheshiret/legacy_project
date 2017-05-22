package com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrPrivAllocationsCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: Outfitter Allocations List page: Admin -> Privilege Allocations -> Outfitter Allocations
 * 
 * @author Lesley Wang
 * @Date  Sep 26, 2013
 */
public class LicMgrOutfitterAllocationsListPage extends
		LicMgrPrivAllocationsCommonPage {
private static LicMgrOutfitterAllocationsListPage instance=null;
	
	protected LicMgrOutfitterAllocationsListPage(){}
	
	public static LicMgrOutfitterAllocationsListPage getInstance(){
		if(instance == null){
			instance=new LicMgrOutfitterAllocationsListPage();
		}
		return instance;
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] outfitterAllocationsTable() {
		return Property.concatPropertyArray(table(), ".id", "allocationGrid_LIST");
	}
	
	protected Property[] addOutfitterAllocationsLink() {
		return Property.concatPropertyArray(a(), ".text", "Add Outfitter Allocations");
	}
	
	protected Property[] allocationTypeDropdownList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("HFAllocationSearchCriteria-\\d+\\.allocationType", false));
	}
	
	protected Property[] licenseYearDropdownList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("HFAllocationSearchCriteria-\\d+\\.licenseYear", false));
	}
	
	protected Property[] outfitterDropdownList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("HFAllocationSearchCriteria-\\d+\\.outfitterID", false));
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.outfitterAllocationsTable());
	}
	
	public void clickAddOutfitterAllocations() {
		browser.clickGuiObject(this.addOutfitterAllocationsLink());
	}
	
	public void selectAllocationType(String alloType) {
		browser.selectDropdownList(allocationTypeDropdownList(), alloType);
	}
	
	public void selectLicYear(String licYear) {
		browser.selectDropdownList(licenseYearDropdownList(), licYear);
	}
	
	public void selectOutfitter(String outfitter) {
		browser.selectDropdownList(outfitterDropdownList(), outfitter);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public IHtmlObject[] getAllocationTable() {
		IHtmlObject[] objs = browser.getHtmlObject(outfitterAllocationsTable());
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find outfitter allocation table on page.");
		return objs;
	}
	
	public void searchOutfitterAllocations(String alloType, String licYear, String outfitter) {
		selectAllocationType(alloType);
		ajax.waitLoading();
		selectLicYear(licYear);
		selectOutfitter(outfitter);
		clickGo();
		ajax.waitLoading();
	}
	
	public String getSoldNum() {
		String qty = "";
		IHtmlObject[] objs = getAllocationTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		qty = table.getCellValue(1, table.findColumn(0, "Sold"));
		
		Browser.unregister(objs);
		return qty;
	}
}
