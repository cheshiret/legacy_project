package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class InvMgrSeasonSlipsPage extends InvMgrSeasonDetailPage {
	private static InvMgrSeasonSlipsPage _instance = null;

	protected InvMgrSeasonSlipsPage() {
	}

	public static InvMgrSeasonSlipsPage getInstance() {
		if (_instance == null) {
			_instance = new InvMgrSeasonSlipsPage();
		}
		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.Table", ".id",
				"slipsGrid_LIST");
	}

	public void selectAll() {
		browser.selectCheckBox(".name", "all_slct");
	}

	public void unselectAll() {
		browser.unSelectCheckBox(".name", "all_slct");
	}

	public void clickAssign() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Assign");
	}

	public void clickUnassign() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Unassign");
	}

	public void selectShow(String text) {
		browser.selectDropdownList(".id",
				"MarinaSeasonSlipSearchCriteria.status", text);
	}

	public void selectDockArea(String option) {
		browser.selectDropdownList(".id",
				"MarinaSeasonSlipSearchCriteria.dockID", option);
	}

	public void selectSlipPeriodType(String type) {
		browser.selectDropdownList(".id",
				"MarinaSeasonSlipSearchCriteria.slipReservationType", type);
	}

	public void selectShared(String option) {
		browser.selectDropdownList(".id",
				"MarinaSeasonSlipSearchCriteria.shared", option);
	}

	public void setSlipNumber(String num) {
		browser.setTextField(".id",
				"MarinaSeasonSlipSearchCriteria.slipNumber", num);
	}

	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}

	public void searchUnAssignedSlips(String dock) {
		this.selectShow("Unassigned Slips");
		this.selectDockArea(dock);
		this.selectSlipPeriodType("All");
		this.selectShared("All");

		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchSlipsByDock(String dock) {
		this.selectShow("Assigned or Not");
		this.selectDockArea(dock);
		this.selectSlipPeriodType("All");
		this.selectShared("All");

		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchSlipsByAllField(SeasonData season){
		this.setSlipNumber(season.m_Slip_searchslipnum);
		this.selectShow(season.m_searchStatus);
		this.selectDockArea(season.m_Slip_searchDock);
		this.selectSlipPeriodType(season.m_Slip_searchResPeriodType);
		this.selectShared(season.m_Slip_searchShared);

		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}

	public void searchSlipBySlipNum(String slipNum, String dock) {
		this.searchSlipBySlipNum(slipNum, dock, "Assigned or Not");
	}
	
	public void searchSlipBySlipNum(String slipNum,String dock,String assignOrNot){
		this.setSlipNumber(slipNum);
		this.selectShow(assignOrNot);
		this.selectDockArea(dock);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}

	public String getMessage() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if (objs.length < 1) {
			throw new ErrorOnPageException("can not find message object");
		}
		String message = objs[0].getProperty(".text");
		return message;
	}

	private IHtmlObject getSlipTable() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "slipsGrid_LIST");
		if (objs.length < 1)
			throw new ItemNotFoundException("Could not find slip table.");
		return objs[objs.length - 1];
	}

	public void selectSlipCheckBoxBySlipID(String slipID) {
		browser.clickGuiObject(".type", "checkbox", ".value", slipID);
	}
	
	public void assignSlipByNumber(String[] num, String[] type, String[] shared, String parent) {
		assignSlipByNumber(num, type, shared, parent, false);
	}

	public void assignSlipByNumber(String[] num, String[] type, String[] shared, String parent, boolean ignoreAssigned) {
		if (num.length != type.length || num.length != shared.length)
			throw new ErrorOnDataException("Slip number, type and shared length should be same. Num is: " + num.length + ", type is: " + type.length + ", shared is: " + shared.length);
		
		for (int i = 0; i < num.length; i++) {
			if(ignoreAssigned) {
				boolean assgined=this.isSlipAssignedToSeason(parent, num[i].trim());
				if(assgined)
					continue;
				else
					this.selectSlip(num[i].trim(), type[i].trim(), shared[i].trim());
			} else {
				this.searchSlipBySlipNum(num[i], parent, "Unassigned Slips");
				this.selectSlip(num[i].trim(), type[i].trim(), shared[i].trim());
			}
			
			this.clickAssign();
			ajax.waitLoading();
			this.waitLoading();
		}
	}
	
	public boolean isSlipListInSlipTable(){
		IHtmlTable table = (IHtmlTable) this.getSlipTable();
	
		if(table.rowCount()>1){
			return true;
		}else{
			return false;
		}
	}
	
	public void verifySlipColInfo(String colname, String value){
		PagingComponent turningPage = new PagingComponent();
		List<String> vaules=new ArrayList<String>();
		do {
			IHtmlTable table = (IHtmlTable) this.getSlipTable();
			int numCol = table.findColumn(0, colname);
			for(int i=1;i<table.rowCount();i++){
				vaules.add(table.getCellValue(i, numCol));
			}
		} while (turningPage.clickNext());
		
		for(String actual:vaules){
			if(!actual.contains(value)){
				throw new ErrorOnPageException(colname,value,actual);
			}
		}
		
	}

	private String getSlipIdByNum(String slipNum){
		IHtmlTable table = (IHtmlTable) this.getSlipTable();
		
		int rowNum = table.findRow(2, slipNum);
		return table.getCellValue(rowNum, 1);
	}
	
	public void selectSlipCheckBox(String num){
		String slipID = getSlipIdByNum(num);
		browser.selectCheckBox(".id",new RegularExpression("GenericGrid-\\d+.selectedItems", false),".value",slipID);
	}

	public void selectSlip(String num, String type, String shared) {
		String slipID = getSlipIdByNum(num);
		
		if(StringUtil.isEmpty(slipID)||!slipID.matches("\\d+")){
			throw new ItemNotFoundException("Slip Num '"+num+"' Not Found.");
		}
		
		IHtmlObject[] trs = browser.getHtmlObject(".class", "Html.TR",".text", new RegularExpression("^" + slipID + ".*", false));
		if (trs.length < 1)
			throw new ItemNotFoundException("Could not find TR for "+ slipID);
		Property[] properties = new Property[1];
		properties[0] = new Property("id", new RegularExpression(
				"DropdownExt-\\d+\\.selectedValue", false));
		if (StringUtil.notEmpty(type)) {
			browser.selectDropdownList(properties, type, true, 0, trs[0]);
		}
		if (StringUtil.notEmpty(shared)) {
			browser.selectDropdownList(properties, shared, true, 1, trs[0]);
		}
		browser.selectCheckBox(".id",new RegularExpression("GenericGrid-\\d+.selectedItems", false),".value",slipID);
		Browser.unregister(trs);
	}
	
 	public String getAssignStatus(){
 		IHtmlObject[] obj=browser.getTableTestObject(".text", new RegularExpression("^Slip ID.*", false));
   	  	String status = ((IHtmlTable)obj[0]).getCellValue(1, 7).trim();
   	  	Browser.unregister(obj);
   	  	return status;
   	}
	
	 public boolean isSlipExistUnderSeason(String slipNum, String dock) {
		 boolean exist = false;
		 this.searchSlipBySlipNum(slipNum, dock);
		 IHtmlObject[] objs=browser.getTableTestObject(".text", new RegularExpression("^Slip ID.*", false));
		 if(((IHtmlTable)objs[0]).rowCount()>1){
			 exist = true;
		 }
		 Browser.unregister(objs);
 	     return exist;
 	 }
	 
	 public boolean isSlipAssignedToSeason(String dock, String slipCode) {
		 searchSlipBySlipNum(slipCode, dock);
		 IHtmlObject obj = getSlipTable();
		 IHtmlTable table = (IHtmlTable)obj;
		 int slipNumColIndex = table.findColumn(0, "Slip Number");
		 int colIndex = table.findColumn(0, "Assigned");
		 int rowIndex = table.findRow(slipNumColIndex, slipCode);
		 String assignedStatus = table.getCellValue(rowIndex, colIndex);
		 
		 return assignedStatus.equalsIgnoreCase(OrmsConstants.YES_STATUS);
	 }
	 
}
