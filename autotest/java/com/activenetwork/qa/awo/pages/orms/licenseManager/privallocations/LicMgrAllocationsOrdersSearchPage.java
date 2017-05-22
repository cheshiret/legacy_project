package com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrPrivAllocationsCommonPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @Description: Outfitter Allocations List page: Admin -> Privilege Allocations -> Allocation Orders
 * 
 * @author Jane
 * @Date  May 6, 2014
 */
public class LicMgrAllocationsOrdersSearchPage extends
		LicMgrPrivAllocationsCommonPage {
	
	private static LicMgrAllocationsOrdersSearchPage instance=null;
	
	protected LicMgrAllocationsOrdersSearchPage(){}
	
	public static LicMgrAllocationsOrdersSearchPage getInstance(){
		if(instance == null){
			instance=new LicMgrAllocationsOrdersSearchPage();
		}
		return instance;
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] allocationOrderTable() {
		return Property.concatPropertyArray(table(), ".id", "allocationOrderList_LIST");
	}
	
	protected Property[] allocationTypeDropdownList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("HFAllocationOrderSearchCriteria-\\d+\\.allocationType", false));
	}
	
	protected Property[] licenseYearDropdownList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("HFAllocationOrderSearchCriteria-\\d+\\.licenseYear", false));
	}
	
	protected Property[] outfitterDropdownList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("HFAllocationOrderSearchCriteria-\\d+\\.outfitterID", false));
	}
	
	protected Property[] orderNumText() {
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("HFAllocationOrderSearchCriteria-\\d+\\.orderNumber", false));
	}
	
	protected Property[] privilegeNumText() {
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("HFAllocationOrderSearchCriteria-\\d+\\.privilegeNumber", false));
	}
	
	protected Property[] custOrdNumText() {
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("HFAllocationOrderSearchCriteria-\\d+\\.custOrderNumber", false));
	}
	
	protected Property[] custPrivilegeNumText() {
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("HFAllocationOrderSearchCriteria-\\d+\\.custPrivilegeNumber", false));
	}
	
	protected Property[] custNumText() {
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("HFAllocationOrderSearchCriteria-\\d+\\.customerNumber", false));
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.custOrdNumText());
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void selectAllocationType(String alloType) {
		browser.selectDropdownList(allocationTypeDropdownList(), alloType);
	}
	
	public void selectLicenseYear(String licyear) {
		browser.selectDropdownList(licenseYearDropdownList(), licyear);
	}
	
	public void selectOutfitter(String outfitter) {
		browser.selectDropdownList(outfitterDropdownList(), outfitter);
	}
	
	public void setOrderNum(String ordNum) {
		browser.setTextField(orderNumText(), ordNum);
	}
	
	public void setLicenseNum(String licNum) {
		browser.setTextField(privilegeNumText(), licNum);
	}
	
	public void setCustOrdNum(String custOrdNum) {
		browser.setTextField(custOrdNumText(), custOrdNum);
	}
	
	public void setCustLicNum(String custLicNum) {
		browser.setTextField(custPrivilegeNumText(), custLicNum);
	}
	
	public void setCustNum(String custNum) {
		browser.setTextField(custNumText(), custNum);
	}
	
	public IHtmlObject[] getAllocationOrderTable() {
		IHtmlObject[] objs = browser.getHtmlObject(allocationOrderTable());
		if(objs.length<1)
			throw new ItemNotFoundException("Could not get allocation order table on page.");
		
		return objs;
	}
	
	public void searchByCustNum(String alloType, String licYear, String outfitter, String custNum) {
		selectAllocationType(alloType);
		ajax.waitLoading();
		selectLicenseYear(licYear);
		selectOutfitter(outfitter);
		setCustNum(custNum);
		clickGo();
		ajax.waitLoading();
	}
	
	public List<String> getOrderNums() {
		IHtmlObject[] objs = getAllocationOrderTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> ordNums = table.getColumnValues(table.findColumn(0, "Order #"));
		Browser.unregister(objs);
		return ordNums;
	}
	
	public List<String> getCustOrderNums() {
		IHtmlObject[] objs = getAllocationOrderTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> ordNums = table.getColumnValues(table.findColumn(0, "Cust Order #"));
		Browser.unregister(objs);
		return ordNums;
	}
}
