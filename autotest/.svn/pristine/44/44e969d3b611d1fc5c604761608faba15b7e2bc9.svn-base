package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * Access this page: Click 'Vendors' link from top menu bar.
 * @author vzhao
 *
 */
public class LicMgrVendorSearchListPage  extends LicMgrCommonTopMenuPage {
	private static LicMgrVendorSearchListPage instance=null;
	private String prefixReg = "^VendorSearchCriteria-[0-9]*.";

	private LicMgrVendorSearchListPage(){
	}

	public static LicMgrVendorSearchListPage getInstance() {
		if(instance ==null) {
			instance = new LicMgrVendorSearchListPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV",".id",new RegularExpression("grid_\\d+_and_pagingBar",false));
	}

	public void setVendorNumber(String num) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"vendorNumber", false), num);
	}

	public void setVendorName(String name) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"vendorName", false), name);
	}

	public void selectVendorStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"vendorStatusID", false), status,0);
	}

	public void selectVendorSearchBy(String value){
		browser.selectDropdownList(".id", 
				new RegularExpression("AttributeComboBox-\\d+\\.selectedAttrDef",false), value);
	}

	public void deselectVendorSearchBy(){
		browser.selectDropdownList(".id", 
				new RegularExpression("AttributeComboBox-\\d+\\.selectedAttrDef",false), 0);
	}

	public void setVendorSearchByValue(String vaule){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "vendor.searchvendorattributeid-attrDefDropdown");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found vendor search value label object.");
		}
		Property[] p = new Property[1];
		p[0] = new Property(".class","Html.INPUT.text");

		browser.setTextField(p, vaule, true, 0, objs[0]);
		Browser.unregister(objs);
	}

	public void deselectVendorStatus() {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"vendorStatusID", false), 0);
	}

	public void setStoreID(String id) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"storeID", false), id);
	}

	public void setStoreName(String name) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"storeName", false), name);
	}

	public void selectStoreStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"storeStatusID", false), status);
	}

	public void selectStoreSearchBy(String searchBy){
		browser.selectDropdownList(".id", 
				new RegularExpression("AttributeComboBox-\\d+\\.selectedAttrDef",false), searchBy,1);
	}

	public void deselectStoreSearchBy(){
		browser.selectDropdownList(".id", 
				new RegularExpression("AttributeComboBox-\\d+\\.selectedAttrDef",false), 0,1);
	}

	public void setStoreSearchByValue(String value){		
		IHtmlObject[] objs = browser.getHtmlObject(".id", "vendor.searchstoreattributeid-attrDefDropdown");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found store search value label object.");
		}
		Property[] p = new Property[1];
		p[0] = new Property(".class","Html.INPUT.text");

		browser.setTextField(p, value, true, 0, objs[0]);
		Browser.unregister(objs);
	}

	public void deselectStoreStatus() {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"storeStatusID", false), 0);
	}

	public void setBankAccount(String account) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"bankAccountNumber", false), account);
	}

	public void setBondNumber(String num) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"bondNumber", false), num);
	}

	public void setStoreAddress(String addr) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"storeAddress", false), addr);
	}

	public void setStoreSupAddress(String addr) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"storeSupAddress", false), addr);
	}

	public void setStoreCity(String city) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"storeCity", false), city);
	}

	public void selectStoreCounty(String county) {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"storeCounty", false), county);
	}

	public void deselectStoreCounty() {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"storeCounty", false), 0);
	}

	public void selectStoreState(String state) {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"storeState", false), state);
	}

	public void deselectStoreState() {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"storeState", false), 0);
	}

	public void setStoreZip(String zip) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"storeZip", false), zip);
	}

	public void selectStoreCountry(String country) {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"storeCountry", false), country);
	}

	public void deselectStoreCountry() {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"storeCountry", false), 0);
	}

	public void clickSearch() {
		browser.clickGuiObject(".class","Html.A",".text","Search");
	}

	public void clickAddVendor() {
		browser.clickGuiObject(".class","Html.A",".text","Add Vendor");
	}

	public boolean checkAddVendorExist(){
		return browser.checkHtmlObjectExists(".class","Html.A",".text","Add Vendor");
	}
	/**
	 * Click store name to goto store detail page
	 * @param storeName
	 */
	public void clickStoreNameLink(String storeName) {
		browser.clickGuiObject(".class", "Html.A", ".text", storeName, true);
	}

	/**
	 * Click vendor number link to goto vendor detail page
	 * @param vendorNum
	 */
	public void clickVendorNumLink(String vendorNum) {
		browser.clickGuiObject(".class", "Html.A", ".text", vendorNum, true);
	}

	public void setSearchCriteria(VendorInfo vendorInfo, StoreInfo storeInfo, String bankAccountNum, String bondNum){
		if(null != vendorInfo){
			this.setVendorNumber(vendorInfo.number);
			this.setVendorName(vendorInfo.name);
			if(null != vendorInfo.status && vendorInfo.status.trim().length() >0){
				this.selectVendorStatus(vendorInfo.status);
			}else {
				this.deselectVendorStatus();
			}
			if(null != vendorInfo.vendorSearchBy && vendorInfo.vendorSearchBy.trim().length() >0){
				this.selectVendorSearchBy(vendorInfo.vendorSearchBy);
				ajax.waitLoading();
				if(null != vendorInfo.vendorSearchByValue && vendorInfo.vendorSearchByValue.trim().length()>0){
					this.setVendorSearchByValue(vendorInfo.vendorSearchByValue);
				}
			}else {
				this.deselectVendorSearchBy();
				ajax.waitLoading();
			}
		}

		if(null != storeInfo){
			this.setStoreID(storeInfo.storeID);
			this.setStoreName(storeInfo.storeName);
			if(null!= storeInfo.status && storeInfo.status.trim().length()>0){
				this.selectStoreStatus(storeInfo.status);
			}else {
				this.deselectStoreStatus();
			}
			if(null != storeInfo.storeSearchBy && storeInfo.storeSearchBy.trim().length()>0){
				this.selectStoreSearchBy(storeInfo.storeSearchBy);
				ajax.waitLoading();
				this.setStoreSearchByValue(storeInfo.storeSearchByValue);
			}else {
				this.deselectStoreSearchBy();
				ajax.waitLoading();
			}

			this.setStoreAddress(storeInfo.physicalAddress.address);
			this.setStoreSupAddress(storeInfo.physicalAddress.supplementalAddr);
			this.setStoreCity(storeInfo.physicalAddress.city);
			if(null != storeInfo.physicalAddress.country && storeInfo.physicalAddress.country.trim().length()>0){
				this.selectStoreCountry(storeInfo.physicalAddress.country);
			}else {
				this.deselectStoreCountry();
			}
			ajax.waitLoading();
			if(null != storeInfo.physicalAddress.state && storeInfo.physicalAddress.state.trim().length()>0){
				this.selectStoreState(storeInfo.physicalAddress.state);
			}else {
				this.deselectStoreState();
			}
			ajax.waitLoading();				
			if(null != storeInfo.physicalAddress.county && storeInfo.physicalAddress.county.trim().length()>0){
				this.selectStoreCounty(storeInfo.physicalAddress.county);
			}else{
				this.deselectStoreCounty();
			}
			ajax.waitLoading();
			this.setStoreZip(storeInfo.physicalAddress.zip);
		}

		if(null != bankAccountNum){
			this.setBankAccount(bankAccountNum);
		}

		if(null != bondNum){
			this.setBondNumber(bondNum);
		}
	}

	public boolean checkVendorInfoIsExistByVendorNum(String vendorNum){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", vendorNum);
	}

	public boolean checkLastButtonWhetherUseable(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Last");
	}

	public void clickNextButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Next");
	}

	public boolean checkVendorInfoIsExistInListPg(String vendorNum) {
		logger.info("Verify vendor whether exist in the search results");

		if (this.checkVendorInfoIsExistByVendorNum(vendorNum)) {
			return true;
		}
		while (this.checkLastButtonWhetherUseable()) {
			this.clickNextButton();
			ajax.waitLoading();
			this.waitLoading();
			if (this.checkVendorInfoIsExistByVendorNum(vendorNum)) {
				return true;
			}
		}
		return false;
	}

	public List<String> getVendorInfoByVendorNumber(String vendorNum){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+",false));

		if(objs.length<1){
			throw new ItemNotFoundException("Vendor List table object not found.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(0, vendorNum);
		List<String> rowValue = table.getRowValues(row);
		Browser.unregister(objs);
		return rowValue;
	}

	public List<String> getAgentInfoByAgentNameAndID(String agentName, String agentID){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+",false));

		if(objs.length<1){
			throw new ItemNotFoundException("Vendor List table object not found.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(1, new RegularExpression(agentName + " ?\\(" + agentID + "\\)", false));
		List<String> rowValue = table.getRowValues(row);
		Browser.unregister(objs);
		return rowValue;
	}

	public String getVendorStatusByVendorNum(String vendorNum){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+",false));

		if(objs.length<1){
			throw new ItemNotFoundException("Vendor List table object not found.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];

		int row = table.findRow(0, vendorNum);
		int col = table.findColumn(0, "Status");
		String status = table.getCellValue(row, col);

		Browser.unregister(objs);
		return status;
	}

	public void verifyVendorStatusFromVendorList(String expectStauts, String vendorNum){
		logger.info("Verify vendor status from vendor list.");

		String actualValue = this.getVendorStatusByVendorNum(vendorNum);
		if(!actualValue.equals(expectStauts)){
			throw new ErrorOnPageException("In list expect status should be " + expectStauts 
					+ ", but actually is " + actualValue);
		}		
	}

	/**
	 * Check if the agent belongs to vendor or not
	 * @param vendorNum
	 * @param agentName
	 * @param agentID
	 * @return
	 */
	public boolean verifyAgentIsBelongToVendor(String vendorNum, String agentName, String agentID){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+",false));

		if(objs.length<1){
			throw new ItemNotFoundException("Vendor List table object not found.");
		}

		//get vendor TR object
		Property[] p1 = new Property[2];
		p1[0] = new Property(".class", "Html.TR");
		p1[1] = new Property(".text", new RegularExpression("^" + vendorNum, false));
		IHtmlObject vendorTRObjs[] = browser.getHtmlObject(p1, objs[0]);
		if(vendorTRObjs.length<1){
			throw new ItemNotFoundException("Vendor list table vendor(Vendor#=" + vendorNum + ") row object not found.");
		}

		//get agent TR object
		Property[] p2 = new Property[2];
		p2[0] = new Property(".class", "Html.TR");
		p2[1] = new Property(".text", new RegularExpression(agentName + " ?\\(" + agentID + "\\)", false));
		IHtmlObject agentTRObjs[] = browser.getHtmlObject(p2, objs[0]);
		if(agentTRObjs.length<1){
			throw new ItemNotFoundException("Vendor list table agent(Agent ID=" + agentID + ") row object not found.");
		}

		String vendorID = vendorTRObjs[0].getProperty(".id");
		String storeClass = agentTRObjs[0].getProperty(".className");

		Browser.unregister(objs);
		Browser.unregister(vendorTRObjs);
		Browser.unregister(agentTRObjs);

		return storeClass.contains(vendorID) ? true : false;
	}

	public void verifyAgentListInfo(StoreInfo storeInfo){
		logger.info("Verify Agent info form vendor list.");
		boolean result = true;
		List<String> acutalValue = this.getAgentInfoByAgentNameAndID(storeInfo.storeName, storeInfo.storeID);

		if(acutalValue.get(0).trim().length()!=0){
			result &= false;
			logger.error("Expect first column of agent info should be null, but actually is " + acutalValue.get(0).trim());
		}
		String expectStoreInfo = storeInfo.storeName + "(" + storeInfo.storeID + ")";
		if(!acutalValue.get(1).equals(expectStoreInfo)){
			result &= false;
			logger.error("Expect store name should be " + expectStoreInfo
					+ ", but actually is " + acutalValue.get(1));
		}

		if(!acutalValue.get(2).equals(storeInfo.status)){
			result &= false;
			logger.error("Expect store status should be " + storeInfo.status
					+ ", but actually is " + acutalValue.get(2));
		}

		if(!acutalValue.get(3).equals(storeInfo.physicalAddress.address)){
			result &= false;
			logger.error("Expect store physical address should be " + storeInfo.physicalAddress.address
					+ ", but actually is " + acutalValue.get(3));
		}

		if(!acutalValue.get(4).trim().equals(storeInfo.physicalAddress.supplementalAddr.trim())){
			result &= false;
			logger.error("Expect store physical supplement address should be " + storeInfo.physicalAddress.supplementalAddr
					+ ", but actually is " + acutalValue.get(4));
		}

		if(!acutalValue.get(5).equals(storeInfo.physicalAddress.city)){
			result &= false;
			logger.error("Expect store physical city town should be " + storeInfo.physicalAddress.city
					+ ", but actually is " + acutalValue.get(5));
		}

		if(!acutalValue.get(6).equals(storeInfo.physicalAddress.state)){
			result &= false;
			logger.error("Expect store physical state should be " + storeInfo.physicalAddress.state
					+ ", but actually is " + acutalValue.get(6));
		}

		if(!acutalValue.get(7).equals(storeInfo.physicalAddress.county)){
			result &= false;
			logger.error("Expect store physical county should be " + storeInfo.physicalAddress.county
					+ ", but actually is " + acutalValue.get(7));
		}

		if(!acutalValue.get(8).equals(storeInfo.physicalAddress.zip)){
			result &= false;
			logger.error("Expect store physical zip postal should be " + storeInfo.physicalAddress.zip
					+ ", but actually is " + acutalValue.get(8));
		}

		if(!result){
			throw new ErrorOnPageException("Agent info from vendor list are not correct, please check errro log.");
		}		
	}

	public void verifyVendorListInfo(VendorInfo expVendor){
		boolean result = true;
		logger.info("Verify vendor info from vendor list.");
		List<String> actualValue = this.getVendorInfoByVendorNumber(expVendor.number);

		if(!actualValue.get(0).equals(expVendor.number)){
			result &= false;
			logger.error("Expect vendor number should be " + expVendor.number 
					+ ", but actually is " + actualValue.get(0));
		}

		if(!actualValue.get(1).equals(expVendor.name)){
			result &= false;
			logger.error("Expect vendor name should be " + expVendor.name 
					+ ", but actually is " + actualValue.get(1));
		}

		if(!actualValue.get(2).equals(expVendor.status)){
			result &= false;
			logger.error("Expect vendor status should be " + expVendor.status 
					+ ", but actually is " + actualValue.get(2));
		}

		if(!actualValue.get(3).equals(expVendor.phyAddress)){
			result &= false;
			logger.error("Expect vendor physical address should be " + expVendor.phyAddress 
					+ ", but actually is " + actualValue.get(3));
		}

		if(!actualValue.get(4).trim().equals(expVendor.phySuppAddress.trim())){
			result &= false;
			logger.error("Expect vendor physical supplyment address should be " + expVendor.phySuppAddress 
					+ ", but actually is " + actualValue.get(4));
		}

		if(!actualValue.get(5).equals(expVendor.phyCityTown)){
			result &= false;
			logger.error("Expect vendor physical city town should be " + expVendor.phyCityTown 
					+ ", but actually is " + actualValue.get(5));
		}

		if(!actualValue.get(6).equals(expVendor.phyStateProvince)){
			result &= false;
			logger.error("Expect vendor physical state province should be " + expVendor.phyStateProvince 
					+ ", but actually is " + actualValue.get(6));
		}

		if(!actualValue.get(7).equals(expVendor.phyCounty)){
			result &= false;
			logger.error("Expect vendor physical county should be " + expVendor.phyCounty 
					+ ", but actually is " + actualValue.get(8));
		}

		if(!actualValue.get(8).equals(expVendor.phyZipPostal)){
			result &= false;
			logger.error("Expect vendor physical zip/postal should be " + expVendor.phyZipPostal 
					+ ", but actually is " + actualValue.get(8));
		}

		if(!result){
			throw new ErrorOnPageException("Vendor info from vendor list are not correct, please check errro log.");
		}
	}

}
