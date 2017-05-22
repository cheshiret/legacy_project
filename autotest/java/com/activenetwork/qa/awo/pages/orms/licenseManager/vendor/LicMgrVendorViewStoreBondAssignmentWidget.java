package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrVendorViewStoreBondAssignmentWidget extends DialogWidget {
	private static LicMgrVendorViewStoreBondAssignmentWidget _instance=null;
	private String prefixReg = "^VendorBondStoreAssignmentSearchCriteria-[0-9]*.";
	
	protected LicMgrVendorViewStoreBondAssignmentWidget() {}
	
	public static LicMgrVendorViewStoreBondAssignmentWidget getInstance() {
		if(null==_instance) {
			_instance=new LicMgrVendorViewStoreBondAssignmentWidget();
		}
		return _instance;
	}
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(".id", "search_table");
	}
	
	public void checkShowCurrentRecords() {
		browser.selectCheckBox(".id",new RegularExpression(prefixReg+"includeCurrentRecordsOnly", false));
	}
	
	public void uncheckShowCurrentRecords() {
		browser.unSelectCheckBox(".id",new RegularExpression(prefixReg+"includeCurrentRecordsOnly", false));
	}
	
	public void checkActive() {
		browser.selectCheckBox(".id",new RegularExpression(prefixReg+"includeActive", false));
	}
	
	public void uncheckActive() {
		browser.unSelectCheckBox(".id",new RegularExpression(prefixReg+"includeActive", false));
	}
	
	public void checkInactive() {
		browser.selectCheckBox(".id",new RegularExpression(prefixReg+"includeInactive", false));
	}
	
	public void uncheckInactive() {
		browser.unSelectCheckBox(".id",new RegularExpression(prefixReg+"includeInactive", false));
	}
	
	//because Store, Bond and explicit server drop down list objects' id is not unique
	public void selectStore(String item) {
		Property[] pro = new Property[1];
		pro[0] = new Property(".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue",false));
		IHtmlObject[] objs = browser.getHtmlObject(".id","Dialog001");
		browser.selectDropdownList(pro,item,true,0,objs[0]);
		Browser.unregister(objs);
	}
	
	//bond drop down is 3rd obj
	public void selectBond(String item) {
		Property[] pro = new Property[1];
		pro[0] = new Property(".id", new RegularExpression("^DropdownExt-[0-9]*.selectedValue",false));
		IHtmlObject[] objs = browser.getHtmlObject(".id","Dialog001");
		browser.selectDropdownList(pro,item,false,1,objs[0]);
		Browser.unregister(objs);
	}

	public void searchAssignments(String status,String agent,String bondNum,String bondIssuer){
		this.uncheckShowCurrentRecords();
		ajax.waitLoading();
		if(status.equalsIgnoreCase("active")){
			this.checkActive();
		}
		if(status.equalsIgnoreCase("inactive")){
			this.checkInactive();
		}
		if(status.equalsIgnoreCase("both")){
			this.checkActive();
			this.checkInactive();
		}
		this.selectStore(agent);
		this.selectBond("Bond #: "+bondNum+" Issuer: "+bondIssuer);
		this.clickGo();
		ajax.waitLoading();
	}
	
	public void clickGo() {
		clickButtonByText("Go");
	}
	
	public void leaveViewBondsPg(){
		clickOK();
		ajax.waitLoading();
	}
	
	public void verifyAssignments(String bondRex,List<String> valuesExpect){
		IHtmlObject[] objs = browser.getTableTestObject(".id","storeAssignmentGrid");
		IHtmlTable table = (IHtmlTable) objs[0];
		
		int rowNum = table.findRow(4, new RegularExpression(bondRex,false));

		List<String> valuesUI = table.getRowValues(rowNum);

		if(valuesUI.size()!=valuesExpect.size()){
			throw new ErrorOnPageException("Column Size not correct from UI,Expect:"+valuesExpect.size()+":Actual:"+valuesUI.size());
		}
		for(int i=0;i<valuesExpect.size();i++){
			if(i==0||i==3||i==5){
				if(!valuesUI.get(i).replaceAll("\r\n", " ").replace(", ", ",").matches(valuesExpect.get(i))){
					throw new ErrorOnPageException("Column Value for column"+i+"("+valuesUI.get(i)+") not correct. Actual is: '" + valuesUI.get(i).replaceAll("\r\n", " ") + "', but Expected is: '" + valuesExpect.get(i) + "'");
				}
			}else{
				if(!valuesUI.get(i).replaceAll("\r\n", " ").equals(valuesExpect.get(i))){
					throw new ErrorOnPageException("Column Value for column"+i+"("+valuesUI.get(i)+") not correct. Actual is: '" + valuesUI.get(i).replaceAll("\r\n", " ") + "', but Expected is: '" + valuesExpect.get(i) + "'");
				}
			}
		}
		Browser.unregister(objs);
	}
	/**
	 * Parse the store/bond assignment table, retrieve the cell value for column(expColName), by given
	 * column name(knownColName) and value(knownCellValue).
	 * This method did not handle the issue when the given cell value is not unique in given column, will
	 * get the first match row's expected column' cell value.
	 * @param expColName - the cell column name which you want to retrieve the cell value from
	 * @param knownColName - given cell value' column name 
	 * @param knownCellValue - given cell value
	 * @return cell value for expected column
	 */
	public String getCellValueFromAssignmentTable(String expColName, String knownColName, String knownCellValue) {
		IHtmlObject[] objs = browser.getTableTestObject(".id","storeAssignmentGrid");
		IHtmlTable table = (IHtmlTable) objs[0];
		
		String toReturn = "";
		int expColNum = 0;
		boolean isFound = false;//whether find the given value in given column
		
		if(expColName.equalsIgnoreCase("Assign ID")) {
			expColNum = 0;
		} else if(expColName.equalsIgnoreCase("Assign Status")) {
			expColNum = 1;
		} else if(expColName.equalsIgnoreCase("Agent ID")) {
			expColNum = 2;
		} else if(expColName.equalsIgnoreCase("Agent Name & Address")) {
			expColNum = 3;
		} else if(expColName.equalsIgnoreCase("Bond")) {
			expColNum = 4;
		} else if(expColName.equalsIgnoreCase("Assign Details/ Last Modify Details")) {
			expColNum = 5;
		} else {
			throw new ItemNotFoundException("Expect column name '"+expColName+"' not found!");
		}
		
		if(knownColName.equalsIgnoreCase("Assign ID")) {
			for(int j=1; j<table.rowCount(); j++) {
				if(knownCellValue.equalsIgnoreCase(table.getCellValue(j, 0))) {
					toReturn = table.getCellValue(j, expColNum);
					isFound = true;
					break;
				}
			}
		} else if(knownColName.equalsIgnoreCase("Assign Status")) {
			for(int j=1; j<table.rowCount(); j++) {
				if(knownCellValue.equalsIgnoreCase(table.getCellValue(j, 1))) {
					toReturn = table.getCellValue(j, expColNum);
					isFound = true;
					break;
				}
			}
		} else if(knownColName.equalsIgnoreCase("Store ID")) {
			for(int j=1; j<table.rowCount(); j++) {
				if(knownCellValue.equalsIgnoreCase(table.getCellValue(j, 2))) {
					toReturn = table.getCellValue(j, expColNum);
					isFound = true;
					break;
				}
			}
		} else if(knownColName.equalsIgnoreCase("Store Name & Address")) {
			for(int j=1; j<table.rowCount(); j++) {
				if(knownCellValue.equalsIgnoreCase(table.getCellValue(j, 3))) {
					toReturn = table.getCellValue(j, expColNum);
					isFound = true;
					break;
				}
			}
		} else if(knownColName.equalsIgnoreCase("Bond")) {
			for(int j=1; j<table.rowCount(); j++) {
				if(knownCellValue.equalsIgnoreCase(table.getCellValue(j, 4))) {
					toReturn = table.getCellValue(j, expColNum);
					isFound = true;
					break;
				}
			}
		} else if(knownColName.equalsIgnoreCase("Assign Details/ Last Modify Details")) {
			for(int j=1; j<table.rowCount(); j++) {
				if(knownCellValue.equalsIgnoreCase(table.getCellValue(j, 5))) {
					toReturn = table.getCellValue(j, expColNum);
					isFound = true;
					break;
				}
			}
		} else {
			throw new ItemNotFoundException("Given cell olumn name '"+knownColName+"' not found!");
		}
		
		if(!isFound){
			throw new ItemNotFoundException("Given cell value '"+knownCellValue+
					"' did not find in given column.");
		}
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	public String getAssignIdByAgentName(String agent) {
		IHtmlObject[] objs = browser.getTableTestObject(".id","storeAssignmentGrid");
		IHtmlTable table = (IHtmlTable) objs[0];
		String assignId = "";
		int rowNum = table.findRow(3, new RegularExpression(agent,false));
		if(rowNum > 0){
			assignId = table.getCellValue(rowNum, 0);
		}
		Browser.unregister(objs);
		return assignId;
	}
}
