package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**  
 * @Description:  pos suppier search page.
 * @Preconditions:  
 * @SPEC:  
 * @Task#: Auto-972
 * @author jwang8  
 * @Date  Mar 27, 2012   
 */
public class InvMgrPOSSupplierSearchPage extends InvMgrCommonTopMenuPage{

	public static InvMgrPOSSupplierSearchPage instance = null;
	
	private InvMgrPOSSupplierSearchPage(){};
	
	public static InvMgrPOSSupplierSearchPage getInstance(){ 
		if(null == instance){
			instance = new InvMgrPOSSupplierSearchPage();
		}
		return instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("POSSupplierSearchCriteria-\\d+\\.supplierId|SupplierMasterListSearchCriteria-\\d+\\.supplierId",false));
	}
	/**
	 * select pos supplier status.
	 * @param status -  the status of pos supplier.
	 */
	public void selectStatus(String status){
		RegularExpression reg =new RegularExpression(
				"SupplierMasterListSearchCriteria-\\d+\\.activationStatus|POSSupplierSearchCriteria-\\d+\\.assignStatus",
				false);
		browser.selectDropdownList(".id", reg, status);
		if (status.trim().length() == 0){
			browser.selectDropdownList(".id",reg,0);
		}
	}
	/**
	 * set pos supplier id.
	 * @param id -  the id of pos supplier.
	 */
	public void setSupplierId(String id){
		browser.setTextField(".id", new RegularExpression("POSSupplierSearchCriteria-\\d+\\.supplierId|SupplierMasterListSearchCriteria-\\d+\\.supplierId",false), id);
	}
	/**
	 * set pos suppier name.
	 * @param name -  the pos suppier name.
	 */
	public void setSuppierName(String name){
		browser.setTextField(".id", new RegularExpression("SupplierMasterListSearchCriteria-\\d+\\.supplierName|POSSupplierSearchCriteria-\\d+\\.supplierName",false), name);
	}
	/**
	 * Click go button.
	 */
	public void clickGoButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	/**
	 * click add supplier
	 */
	public void clickAddSupplier(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Supplier");
	}
	/**
	 * click activate selected suppliers.
	 */
	public void clickActivateSelectedSuppliers(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate Selected Suppliers");
	}
	/**
	 * click Deactivate selected suppliers.
	 */
	public void clickDeactivateSelectedSuppliers(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate Selected Suppliers");
	}
	/**
	 * click supplerId.
	 */
	public void clickSupplierId(String supplierId){
		browser.clickGuiObject(".class", "Html.A", ".text", supplierId);
	}
	/**
	 * Select the supplier check box.
	 * @param index -  the index of check box.
	 */
	public void selectSupplierCheckBox(int index){
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems",false), index);
	}
	/**
	 * get the supplier table
	 * @return ITable -  the table.
	 *//*
	public HtmlObject[] getSupplierTable(){
		HtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+\\_LIST||PosSupplierList_LIST",false));
		if(objs.length<1){
			throw new ErrorOnDataException("There is no supplier table was found");
		}
		return objs;
	}*/
	
	/**
	 * select all the check box.
	 */
	public void selectAllCheckBox(){
		browser.selectCheckBox(".name", "all_slct");
	}
	
	public void selectSupplierCheckBox(String id) {
		IHtmlObject objs[] = this.getPosSupplierTable();
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find POS Supplier table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(1, id);
		this.selectSupplierCheckBox(row-1);
		
		Browser.unregister(objs);
	}
	
	/**
	 * get the supplier check box.
	 * @return ITable -  the table.
	 */
	public void selectSupplierCheckBox(String[] supplierIdList, boolean isSelectAll){
		if(isSelectAll) {
			this.selectAllCheckBox();
		} else {
			for(int i = 0; i < supplierIdList.length; i ++) {
				selectSupplierCheckBox(supplierIdList[i]);
			}
		}
	}
	
	/**
	 * select check box.
	 * @param supplierIdList
	 */
	public void selectSupplierCheckBox(String[] supplierIdList){
		this.selectSupplierCheckBox(supplierIdList, false);
	}
	
	//TODO Keyword
	/**
	 * active the pos supplier.
	 * @param supplierIdList -  the supplier id list.
	 */
	/*public String activeSupplier(String... supplierIdList){
		String errorMessage = "";
		this.selectSupplierCheckBox(supplierIdList);
		this.clickActivateSelectedSuppliers();
		ajax.waitLoading();
		
		if(this.checkErrorMessageExist()){
			errorMessage = this.getErrorMessage();
		}
		return errorMessage;
	}*/
	/***
	 * get POs supplier status.
	 * @param supplierId
	 * @return
	 */
	public String getPosSupplierStatus(String supplierId){
		IHtmlObject[] objs = this.getPosSupplierTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(1, supplierId);
		String status = table.getCellValue(row, 2);
		Browser.unregister(objs);
		return status;
	}
	
	public String getSupplierIdByName(String supplierName){
		IHtmlTable table = (IHtmlTable)this.getPosSupplierTable()[0];
		int rowNum = table.findRow(3, supplierName);
		if(rowNum < 0){
			throw new ItemNotFoundException("Can't find supplier by name "+supplierName);
		}
		List<String> rowList = table.getRowValues(rowNum);
		return rowList.get(1);// return supplier ID
	}
	
	/**
	 * Get supplier ID
	 * @return
	 */
	public List<String> getSupplierID(){
		IHtmlTable table = (IHtmlTable)this.getPosSupplierTable()[0];
		String supplierID = "";
		List<String> supplierIDList = new ArrayList<String>();
		for(int i=1; i<table.rowCount(); i++){
			supplierID = table.getCellValue(i, 1);
			supplierIDList.add(supplierID);
		}
		Browser.unregister(this.getPosSupplierTable());
		return supplierIDList;
	}
	
	/**
	 * verify the pos supplier status.
	 * @param supplierId -  the supplier id.
	 * @param status -  active(Yes or No).
	 */
	public void verifyPosSupplierStatus(String supplierId,String status) {
		String actualStatus = this.getPosSupplierStatus(supplierId);
		status = status.equals(OrmsConstants.ACTIVE_STATUS) ? OrmsConstants.YES_STATUS : OrmsConstants.NO_STATUS;
		if(!actualStatus.equals(status)){
			throw new ErrorOnPageException("POS Supplier status is wrong.", status, actualStatus);
		} else logger.info("POS Supplier status is correct.");
	}
	
	/**
	 * set pos supplier search criteria.
	 * @param searchSupplier -  the search pos supplier info.
	 */
	public void setPosSupplierSearchCriteria(PosSupplier searchSupplier ){
		if(searchSupplier.searchByStatus.length()>0){
			this.selectStatus(searchSupplier.searchByStatus);
		}
		if(searchSupplier.id.length()>0){
			this.setSupplierId(searchSupplier.id);
		}
		if(searchSupplier.name.length()>0){
			this.setSuppierName(searchSupplier.name);
		}
	}
	//TODO
	/**
	 * Search POS supplier by Id.
	 * @param supplierId
	 */
	public void searchPosSupplierById(String supplierId){
		this.clearUpSearchCriteria();
		this.setSupplierId(supplierId);
		this.clickGoButton();
		ajax.waitLoading();
		this.waitLoading();
	}
	/**
	 * Search POs supplier by name.
	 * @param name
	 */
	public void searchPosSupplierByName(String name){
		this.clearUpSearchCriteria();
		this.setSuppierName(name);
//		this.clickGoButton();
		this.clickSearch();
		ajax.waitLoading();
	}
	/**
	 * search pos supplier.
	 * @param searchSupplier -  the search pos supplier info.
	 */
	public void searchPosSupplier(PosSupplier searchSupplier){
		this.setPosSupplierSearchCriteria(searchSupplier);
		this.clickGoButton();
		ajax.waitLoading();
		this.waitLoading();
	}
	/** 
 	 * Click the next button
 	 * */
 	public boolean clickNext(){
 		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
 				new RegularExpression("Next", false));

 		boolean toReturn = false;
 		if (objs.length > 0) {
 			toReturn = true;
 			objs[0].click();
 		}
 		Browser.unregister(objs);
 		ajax.waitLoading();
 		return toReturn;
 	}
	/**
 	 * Get the privilege list info.
 	 * @return List<List<String>>-- all the searched privilege info.
 	 */
 	public List<List<String>> getPosSupplierListInfo(){
 		List<List<String>> posSupplierInfo = new ArrayList<List<String>>();
 		List<String> supplierRowInfo = new ArrayList<String>();
 		do{
 			IHtmlObject[] objs = getPosSupplierTable();
 			if(objs.length<1) {
 				throw new ErrorOnDataException(
				"Can't find the specific POS supplier");
 			}
 			IHtmlTable table =(IHtmlTable)objs[0];
 			if(table.rowCount()>1){
 				for(int i = 1;i<table.rowCount();i++){
 					supplierRowInfo = table.getRowValues(i);
 					posSupplierInfo.add(supplierRowInfo);
 				}
 			}else{
 				throw new ErrorOnPageException(
				"No specific POS supplier info found.");
 			}
 		   Browser.unregister(objs);
 		}while(this.clickNext());
 		
 		return posSupplierInfo;
 	}
 	
	/**
 	 * Get the column index.
 	 */
 	public int getColIndex(String colName) {
 		int colNum = 0;
 		IHtmlObject[] objs = getPosSupplierTable();
 		if (objs.length > 0) {
 			IHtmlTable cusTableGrid = (IHtmlTable) objs[0];
 			colNum = cusTableGrid.findColumn(0, colName);
 		} else throw new ObjectNotFoundException("Object can't find.");

 		Browser.unregister(objs);
 		return colNum;
 	}
 	
 	/**
 	 * verify  pos supplier search result.
 	 * @param posSupplierListInfo -  the list of pos supplier info.
 	 * @param searchCriteria -  search criteria.
 	 * @param colName - the colName.
 	 */
 	public void verifyPosSupplierSearchResult(List<List<String>> posSupplierListInfo, String searchCriteria, String colName){
		int colIndex = this.getColIndex(colName);
		verifyPosSupplierSearchResult(posSupplierListInfo, searchCriteria, colIndex);
 	}
 	
 	/**
 	 * verify  pos supplier search result.
 	 * @param posSupplierListInfo -  the list of pos supplier info.
 	 * @param searchCriteria -  search criteria.
 	 * @param colName - the colName.
 	 */
 	public void verifyPosSupplierSearchResult(List<List<String>> posSupplierListInfo, String searchCriteria, int colIndex){
 		List<String> supplierRowInfo = new ArrayList<String>();
 		if(searchCriteria.length()>0){
 			if(posSupplierListInfo.size()>=1){
 				for(int i=0;i<posSupplierListInfo.size();i++){
 					supplierRowInfo = posSupplierListInfo.get(i);
 						String cellText = supplierRowInfo.get(colIndex).trim();
 						String expectedValue = searchCriteria.trim();
 						if(!cellText.equals(expectedValue)){
 							throw new ErrorOnPageException(searchCriteria + " - actual value: '" + cellText + "' doesn't match expected value: '" + expectedValue + "'.");
 					}
 				}
 			}
 		}
 	}
 	
 	public boolean isAssignSelectedSuppliersButtonExists() {
 		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Assign Selected Suppliers");
 	}
 	
 	/**
 	 * verify the pos supplier search result.
 	 * @param supplier -  the search pos suppler info.
 	 */
 	public void verifyPosSupplierSearchResult(PosSupplier supplier){
 		List<List<String>> posSupplierList = this.getPosSupplierListInfo();
 		if(supplier.status.length()>0){
 			if(isAssignSelectedSuppliersButtonExists()) {
 				this.verifyPosSupplierSearchResult(posSupplierList, supplier.assigned ? OrmsConstants.YES_STATUS : OrmsConstants.NO_STATUS, "Assigned");
 			} else {
 				this.verifyPosSupplierSearchResult(posSupplierList, (supplier.status.equals(OrmsConstants.ACTIVE_STATUS)||supplier.status.equals(OrmsConstants.YES_STATUS)) ? OrmsConstants.YES_STATUS : OrmsConstants.NO_STATUS, "Active");
 			}
 		}
 		if(supplier.id.length()>0){
 			this.verifyPosSupplierSearchResult(posSupplierList, supplier.id, "Supplier ID");
 		}
 		if(supplier.name.length()>0){
 			this.verifyPosSupplierSearchResult(posSupplierList, supplier.name, "Supplier Name");
 		}
 	}
 	/**
 	 * clear up search criteria.
 	 */
 	public void clearUpSearchCriteria(){
 		this.selectStatus("");
 		this.setSupplierId("");
 		this.setSuppierName("");
 	}
 	/**
 	 * get pos supplier table;
 	 * @return
 	 */
 	private IHtmlObject[] getPosSupplierTable(){
 		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+_LIST|PosSupplierList_LIST",false),".text",new RegularExpression(" ?Supplier ID.*",false));
		if(objs==null||objs.length<1){
			throw new ObjectNotFoundException("Can't find the specific POS supplier list table");
		}
		return objs;
 	}
 	
 	/**
 	 * compare POS Supplier list info
 	 * @param supplier
 	 * @return
 	 */
 	public boolean comparePOSSupplierListInfo(PosSupplier supplier) {
 		IHtmlObject objs[] = getPosSupplierTable();
 		if(objs.length < 1) {
 			throw new ItemNotFoundException("Can't find POS Supplier table object.");
 		}
 		IHtmlTable table = (IHtmlTable)objs[0];
 		
		int row = table.findRow(1, supplier.id);
		List<String> supplierInfo = table.getRowValues(row);
		boolean pass = true;
		if(null == supplierInfo || supplierInfo.size() < 1) {
			logger.error("The supplier row info are not exist");
			return false;
		}
		
		pass &= MiscFunctions.compareResult("POS Supplier ID", supplier.id, supplierInfo.get(1));
		pass &= MiscFunctions.compareResult("POS Supplier Status", supplier.status, supplierInfo.get(2).equals(OrmsConstants.YES_STATUS) ? OrmsConstants.ACTIVE_STATUS : OrmsConstants.INACTIVE_STATUS);
		pass &= MiscFunctions.compareResult("POS Supplier Name", supplier.name, supplierInfo.get(3));
		pass &= MiscFunctions.compareResult("POS Supplier Order Address - Address", supplier.orderAddress.address, supplierInfo.get(4));
		pass &= MiscFunctions.compareResult("POS Supplier Order Address - City/Town", supplier.orderAddress.city, supplierInfo.get(5));
		pass &= MiscFunctions.compareResult("POS Supplier Order Address - Country", supplier.orderAddress.country, supplierInfo.get(6));
		pass &= MiscFunctions.compareResult("POS Supplier Order Address - State", supplier.orderAddress.state, supplierInfo.get(7));
		pass &= MiscFunctions.compareResult("POS Supplier Order Address - Zip/Postal", supplier.orderAddress.zip, supplierInfo.get(8));
		
		Browser.unregister(objs);
		return pass;
 	}
 	
 	/**
 	 * Verify POS supplier Cancel
 	 * @param supplier
 	 */
 	public void verifyPosSupplierNotExist(PosSupplier supplier){
 		if(checkPOSSupplierSetUpExist(supplier)){
 	 	 	throw new ErrorOnPageException("Canel add POS supplier process failed");
 	 	 }
 	 	 logger.info("Cancel POS supplier adding process success.");
 	}
 	
 	public boolean checkPOSSupplierSetUpExist(PosSupplier supplier){
 		IHtmlObject[] objs=this.getPosSupplierTable();
 		if(objs==null || objs.length<1){
              throw new ObjectNotFoundException("Can't find POS supplier set up list Table.");
 		}
 		IHtmlTable table= (IHtmlTable)objs[0];
 		for(int i = 0;i< table.rowCount();i++){
 				if(table.getCellValue(i, 3).equals(supplier.name)&& table.getCellValue(i,8).equals(supplier.paymentAddress.zip)){
 	 	 			return true;
 	 	 		}
 		}
 		Browser.unregister(objs);
 		return false;
 	}
 	/**
 	 * get error message.
 	 * @return
 	 */
 	  public String getErrorMessage() {
 			return browser.getObjectText(".id", "NOTSET");
 		}
 	    /**
 	     * Check error message.
 	     * @return -  the error message exist or not.
 	     */
// 	    public boolean checkErrorMessageExist(){
//	    	HtmlObject[] objs= browser.getHtmlObject(".id", "NOTSET");
// 	    	return browser.checkHtmlObjectExists(".id", "NOTSET");
// 	    }
 	    /**
 	     * Verify POS supplier assign info value.
 	     * @param supplierId
 	     * @param expectedValue
 	     */
 	    public void verifyPosSupplierAssignValue(String supplierId, boolean expectedValue){
 	    	IHtmlTable table  = (IHtmlTable)this.getPosSupplierTable()[0];
 			int row = table.findRow(1, supplierId);
 			List<String> supplierInfo = table.getRowValues(row);
 			String valOnPage = supplierInfo.get(2);
 			boolean actual = valOnPage.equals(OrmsConstants.YES_STATUS) ? true : false;
 			if(actual != expectedValue) {
 				throw new ErrorOnPageException("POS Supplier assigned value is wrong, expected is: " + expectedValue + ", but actual is: " + actual);
 			}else{
 				logger.info("POS supplier assign info are correct");
 			}
 			Browser.unregister(this.getPosSupplierTable());
 	    }
 	    /*
 	     * Click assign selected supplier button.
 	     */
 	    public void clickAssignSelectSupplier(){
 	        browser.clickGuiObject(".class", "Html.A",".text","Assign Selected Suppliers");
 	    }
 	    /**
 	     * Click Unassign select supplier.
 	     */
 	    public void clickUnassignSelectedSupplier(){
 	    	  browser.clickGuiObject(".class", "Html.A",".text","Unassign Selected Suppliers");
 	    }
 	    /**
 	     * assign POS supplier.
 	     * @param supplierIdList
 	     */
 	  /*  public String assignPosSuppplier(String...supplierIdList){
 	    	String toReturn = "";
 	    	this.selectSupplierCheckBox(supplierIdList);
 	    	this.clickAssignSelectSupplier();
 	    	ajax.waitLoading();
 	    	if(this.checkErrorMessageExist()){
 	    		toReturn = this.getErrorMessage();
 	    	}
 	    	return toReturn;
 	    }*/
 		/**
 		 * From InvMgrPosSuppierSearchPage to InvMgrPosSuppierSearchPage.
 		 * active POS supplier.
 		 * @param supplierIdList
 		 */
 		public void activePosSupplier(String... supplierIdList){
 			this.selectSupplierCheckBox(supplierIdList);
 			this.clickActivateSelectedSuppliers();
 			ajax.waitLoading();
 			this.waitLoading();
 		}
 		/**
 		 * assign pos supplier
 		 * @param supplierIdList
 		 */
 		public void assignPosSupplier(String...supplierIdList){
 			this.selectSupplierCheckBox(supplierIdList);
 			this.clickAssignSelectSupplier();
 		    ajax.waitLoading();
 		    this.waitLoading();
 		}
}
