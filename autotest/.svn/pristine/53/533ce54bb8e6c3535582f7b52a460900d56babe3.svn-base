package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**  
 * @Description:  product supplier setup page.
 * @Preconditions:  
 * @SPEC:  
 * @Task#: Auto-972
 * @author jwang8  
 * @Date  Mar 29, 2012   
 */
public class InvMgrPOSSupplierProductAssignmentListPage extends InvMgrPOSSupplierCommonPage {

	public static InvMgrPOSSupplierProductAssignmentListPage _instance = null;

	private InvMgrPOSSupplierProductAssignmentListPage() {
	}

	public static InvMgrPOSSupplierProductAssignmentListPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrPOSSupplierProductAssignmentListPage();
		}
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("SupplierPOSProductAssignmentList_LIST",false));
	}
	/**
	 * set product name.
	 * @param productName - pos name.
	 */
	public void setProductName(String productName){
		browser.setTextField(".id", new RegularExpression("SupplierPOSProductAssignmentSearchCriteria-\\d+\\.productName",false), productName);
	}
	/**
	 * set search supplier product code.
	 * @param proCode
	 */
	public void setSearchSupplierProCode(String proCode){
		browser.setTextField(".id", new RegularExpression("SupplierPOSProductAssignmentSearchCriteria-\\d+\\.supplierPrdCode",false),proCode);
	}
	/**
	 * click go button.
	 */
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	/**
	 * click assign select pos products button.
	 */
	public void clickAssignSelectedPosProducts(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Assign Selected POS Products");
	}
	/**
	 * click unassign select pos products button.
	 */
	public void clickUnassignSelectPosProduct(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Unassign Selected POS Products");
	}
	/**
	 * select assignment status.
	 * @param status - ths assignment status.
	 */
	public void selectAssignmentStatus(String status){
		RegularExpression reg =new RegularExpression(
				"SupplierPOSProductAssignmentSearchCriteria-\\d+\\.productAssignmentStatus",
				false);
		browser.selectDropdownList(".id", reg, status);
		if (status.trim().length() == 0){
			browser.selectDropdownList(".id",reg,0);
		}
	}
	/**
	 * set product group.
	 * @param group - product group.
	 */
	public void setProductGroup(String group){
		browser.setTextField(".id", new RegularExpression("SupplierPOSProductAssignmentSearchCriteria-\\d+\\.productGroup", false), group);
	}
	/**
	 * set supplier product code.
	 * @param productCode -  supplier product code.
	 */
	
	public void setSupplierProuductCode(String productCode){
		browser.setTextField(".id", new RegularExpression("SupplierPOSProductAssignmentSearchCriteria-\\d+\\.supplierPrdCode", false), productCode);
	}
	/**
	 * get the supplier table
	 * @return ITable -  the table.
	 */
	public IHtmlObject[] getPosTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "SupplierPOSProductAssignmentList_LIST");
		if(objs.length < 1) {
			throw new ItemNotFoundException("There is no pos table was found");
		}
		return objs;
	}
	/**
	 * get the supplier check box.
	 * @return ITable -  the table.
	 */
	public void selectSupplierCheckBox(String[] posIdList){
		IHtmlObject objs[] = getPosTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		for(int i = 0;i<posIdList.length;i++){
			int row = table.findRow(2, posIdList[i]);
			this.selectSupplierCheckBox(row-1);
			this.clickUnassignSelectPosProduct();
			ajax.waitLoading();
			this.waitLoading();
		}
		
		Browser.unregister(objs);
	}
	
	/**
	 * set supplier unit cost.
	 * @param unitCost - unit cost.
	 * @param index - index of unit cost.
	 */
	public void setSupplierUnitCost(String unitCost,int index){
		browser.setTextField(".id", new RegularExpression("SupplierPOSProductAssignmentSearchView-\\d+\\.supplierUnitCost",false), unitCost,index);
	}
	/**
	 * set supplier product code.
	 * @param prCode- product code.
	 * @param index- index.
	 */
	public void setSupplierProductCode(String prCode,int index){
		browser.setTextField(".id", new RegularExpression("SupplierPOSProductAssignmentSearchView-\\d+\\.supplierPrdCode",false), prCode,index);
	}
	/**
	 * set supplier unit cost.
	 * @param posIdList - the list of pos id.
	 * @param unitCostList - the list of cost list.
	 */
	public void setSupplierUnitCost(String[] posIdList,String[] unitCostList,String[] proCode){
		IHtmlTable table = (IHtmlTable)this.getPosTable()[0];
		for(int i = 0;i<unitCostList.length;i++){
			int row = table.findRow(2, posIdList[i]);
			this.setSupplierUnitCost(unitCostList[i], row-1);
			this.setSupplierProductCode(proCode[i], row -1);
			this.selectSupplierCheckBox(row -1);
		}
		Browser.unregister(this.getPosTable());
	}
	
	/**
	 * select check box.
	 * @param isSelectAll - select all the check box
	 * @param index- select one of check box
	 */
	public void selectSuppierChedkBox(boolean isSelectAll, int index){
		if(isSelectAll){
			browser.selectCheckBox(".name", "all_slct");
		}else{
			browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems",false), index);
		}
	}
	/**
	 * select supplier check box.
	 * @param index
	 */
	public void selectSupplierCheckBox(int index){
		this.selectSuppierChedkBox(false,index);
	}
	
	/**
	 * Search pos by product name.
	 * @param posName - pos name.
	 */
	public void searchPosByName(String posName){
		this.cleanUpSearchCriteria();
		this.setProductName(posName);
		this.clickGo();
		ajax.waitLoading();
	}
	/**
	 * search by product code.
	 * @param proCode
	 */
	public void searchBySupplierProductCode(String proCode){
		this.cleanUpSearchCriteria();
		this.setSearchSupplierProCode(proCode);
		this.clickGo();
		ajax.waitLoading();
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
  	  public boolean checkErrorMessageExist(){
  		  return browser.checkHtmlObjectExists(".id", "NOTSET");
      }

  	  /**
  	   * 
  	   * @param posIdList
  	   * @param unitCostList
  	   * @param productCode
  	   * @return
  	   */
  	  public List<String> assignSelectedPOSProductToSupplier(String[] posIdList, String[] unitCostList, String[] productCode) {
		if(posIdList.length != unitCostList.length && posIdList.length!= productCode.length && unitCostList.length != productCode.length){
			throw new ErrorOnPageException("The length of array are not the same.");
		}
		
		List<String> errorMsgs = new ArrayList<String>();
		String temp = "";
		for (int i = 0; i < posIdList.length; i++) {
			temp = assignSelectedPOSProductToSupplier(posIdList[i], unitCostList[i], productCode[i]);
			errorMsgs.add(temp);
		}
		
		return errorMsgs;
	}
	
	/**
	 * 
	 * @param posId
	 * @param unitCost
	 * @param productCode
	 * @return
	 */
	public String assignSelectedPOSProductToSupplier(String posId, String unitCost, String productCode){
		IHtmlObject objs[] = getPosTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int row = table.findRow(2, posId);
		this.setSupplierUnitCost(unitCost, row-1);
		this.setSupplierProductCode(productCode, row -1);
		this.selectSupplierCheckBox(row-1);
		this.clickAssignSelectedPosProducts();
		ajax.waitLoading();
		this.waitLoading();
		
		String toReturn = "";
		if(this.checkErrorMessageExist()) {
			toReturn = this.getErrorMessage();
		}
		this.waitLoading();
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	/**
	 * get POS assigned value.
	 * @param posId -  the POS ID.
	 * @return
	 */
	public String getPosAssignedValue(String posId){
		IHtmlTable table = (IHtmlTable)this.getPosTable()[0];
		int row = table.findRow(2, posId);
		String assignedValue = table.getCellValue(row, 1);
		Browser.unregister(this.getPosTable());
		return assignedValue;
	}
	
	public String getPosAssignedValueByName(String posName){
		IHtmlTable table = (IHtmlTable)this.getPosTable()[0];
		int row = table.findRow(3, posName);
		String assignedValue = table.getCellValue(row, 1);
		Browser.unregister(this.getPosTable());
		return assignedValue;
	}
	
	/**
	 * verify pos assign status.
	 * @param posId -  pos product id.
	 * @param assignStatus - 
	 */
	public void verifyPosAssignedStatus(String posId, String assignValue){
		String actualAssignedValue = this.getPosAssignedValue(posId);
		if(!assignValue.equals(actualAssignedValue)){
			throw new ErrorOnPageException("Pos assigned to Supplier status is wrong.", assignValue, actualAssignedValue);
		}
	}
	/**
	 * verify pos assign status.
	 * @param posId -  pos product id.
	 * @param assignStatus - 
	 */
	public boolean verifyPosAssignedStatusByName(String posName, String assignValue){
		String actualAssignedValue = this.getPosAssignedValueByName(posName);
		if(!assignValue.equals(actualAssignedValue)){
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * unassign pos product.
	 * @param posId
	 */
	public void unassignSelectedPosProduct(String posId){
		String[] posIdList = new String[]{posId};
		this.selectSupplierCheckBox(posIdList);
	}
	/**
	 * unassign select POS product.
	 * @param posIdList
	 */
	public void unassignSelectPosProduct(String[] posIdList){
		this.selectSupplierCheckBox(posIdList);
		this.clickUnassignSelectPosProduct();
		ajax.waitLoading();
	}
	/**
	 * set Search criteria.
	 * @param posInfo - the search pos info.
	 */
	public void setSearchCriteria(POSInfo posInfo){
		if(!StringUtil.isEmpty(posInfo.searchByAssignStatus)) {
			this.selectAssignmentStatus(posInfo.searchByAssignStatus);
		}
		if(!StringUtil.isEmpty(posInfo.product)) {
			this.setProductName(posInfo.product);
		}
		if(!StringUtil.isEmpty(posInfo.productGroup)) {
			this.setProductGroup(posInfo.productGroup);
		}
		if(!StringUtil.isEmpty(posInfo.supplierProductCode)) {
		    this.setSupplierProuductCode(posInfo.supplierProductCode);
		}
	}
	
	/**
	 * search pos suppler assignment.
	 */
	
	public void searchSupplierPOSAssignment(POSInfo posInfo){
		this.setSearchCriteria(posInfo);
		this.clickGo();
		ajax.waitLoading();
	}
	
	/**
	 * clean up search criteria.
	 */
	public void cleanUpSearchCriteria(){
		this.selectAssignmentStatus("");
		this.setProductName("");
		this.setProductGroup("");
		this.setSupplierProuductCode("");
	}
	
	/** 
 	 * Click the next button
 	 * */
 	public boolean clickNext(){
 		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Next");

 		boolean toReturn = false;
 		if (objs.length > 0) {
 			toReturn = true;
 			objs[0].click();
 			ajax.waitLoading();
 		}
 		
 		Browser.unregister(objs);
 		return toReturn;
 	}
	
	/**
 	 * Get the privilege list info.
 	 * @return List<List<String>>-- all the searched privilege info.
 	 */
 	public List<List<String>> getPosSupplierAssignmentListInfo(){
 		List<List<String>> posSupplierAssignInfo = new ArrayList<List<String>>();
 		List<String> posSupplierAssignRowInfo = new ArrayList<String>();
 		do{
 			IHtmlObject[] objs = browser.getTableTestObject(".id", "SupplierPOSProductAssignmentList_LIST");
 			if(objs.length<1){
 				throw new ErrorOnDataException(
				"Can't find the specific pos info");
 			}
 			IHtmlTable table =(IHtmlTable)objs[0];
 			if(table.rowCount()>1){
 				for(int i = 1;i<table.rowCount();i++){
 					posSupplierAssignRowInfo = table.getRowValues(i);
 					posSupplierAssignInfo.add(posSupplierAssignRowInfo);
 				}
 			}else{
 				throw new ErrorOnDataException(
				"No pos info is retrived!");
 			}
 		   Browser.unregister(objs);
 		}while(this.clickNext());
 		
 		return posSupplierAssignInfo;
 	}
 	
 	/**
 	 * Get the column index.
 	 * @param - col name.
 	 */
 	public int getColIndex(String colName) {
 		int colNum = 0;
 		IHtmlObject[] objs = browser.getTableTestObject(".id", "SupplierPOSProductAssignmentList_LIST");
 		if (objs.length > 0) {
 			IHtmlTable cusTableGrid = (IHtmlTable) objs[0];
 			colNum = cusTableGrid.findColumn(0, colName);
 		} else
 			throw new ObjectNotFoundException("Object can't find.");

 		Browser.unregister(objs);
 		return colNum;
 	}
 	
 	/**
 	 * verify  pos supplier search result.
 	 * @param posSupplierListInfo -  the list of pos supplier info.
 	 * @param searchCriteria -  search criteria.
 	 * @param colName - the colName.
 	 */
 	public void verifyPosSupplierAssignmentSearchResult(List<List<String>> posSupplierAssignListInfo, String searchCriteria, String colName){
 		List<String> posSupplierAssignRowInfo = new ArrayList<String>();
 		if(searchCriteria.length() > 0) {
 			int colIndex = this.getColIndex(colName);
 			String cellText, expectedValue;
 			if(posSupplierAssignListInfo.size() > 0) {
 				for(int i = 0; i < posSupplierAssignListInfo.size(); i++) {
 					posSupplierAssignRowInfo = posSupplierAssignListInfo.get(i);
					cellText = posSupplierAssignRowInfo.get(colIndex).trim();
					expectedValue = searchCriteria.trim();
					if(!cellText.equals(expectedValue)) {
 						throw new ErrorOnPageException(searchCriteria + " - actual value: " +  cellText+ " doesn't match expected: " + expectedValue);
 					}
 				}
 			}
 		}
 	}
 	
 	/**
 	 * verify the pos supplier search result.
 	 * @param supplier -  the search pos suppler info.
 	 */
 	public void verifyPosSupplierAssignmentSearchResult(POSInfo posInfo){
 		List<List<String>> posSupplierList = this.getPosSupplierAssignmentListInfo();
 		
 		if(posInfo.assignToSupplier.length()>0){
 			this.verifyPosSupplierAssignmentSearchResult(posSupplierList, posInfo.assignToSupplier, "Assigned");
 		}
 		if(posInfo.product.length()>0){
 			this.verifyPosSupplierAssignmentSearchResult(posSupplierList, posInfo.product, "Product Name");
 		}
 		if(posInfo.productGroup.length()>0){
 			this.verifyPosSupplierAssignmentSearchResult(posSupplierList, posInfo.productGroup, "Product Group");
 		}
 		if(posInfo.supplierProductCode.length()>0){
 			this.verifyPosSupplierAssignmentSearchResult(posSupplierList, posInfo.supplierProductCode, "Supplier Product Code");
 		}
 	}
 	/**
 	 * click product id
 	 * @param posId - the pos id.
 	 */
 	public void clickProductId(String posId){
 		browser.clickGuiObject(".class", "Html.A", ".text", posId);
 	}
 	
 	public POSInfo getPOSInfo(String id) {
 		IHtmlObject objs[] = browser.getTableTestObject(".id","SupplierPOSProductAssignmentList_LIST");
 		if(objs.length < 1) {
 			throw new ItemNotFoundException("Can't find Assignment table object.");
 		}
 		IHtmlTable table = (IHtmlTable)objs[0];
 		int row = table.findRow(2, id);
 		
 		if(row == -1) {
 			throw new ItemNotFoundException("Can't find Assignment identified by POS id - " + id);
 		}
 		 List<String> rowValue = table.getRowValues(row);
 		 
 		 POSInfo pos = new POSInfo();
 		 pos.assignToSupplier = rowValue.get(1);
 		 pos.productID = rowValue.get(2);
 		 pos.product = rowValue.get(3);
 		 pos.productDescription = rowValue.get(4);
 		 pos.productGroup = rowValue.get(5);
 		 pos.supplierProductCode = rowValue.get(6);
 		 pos.unitCost = rowValue.get(7).split("\\$")[1].trim();
 		 
 		 Browser.unregister(objs);
 		 return pos;
 	}
 	
 	/**
 	 * Compare the assign pos list info.
 	 * @param expected
 	 */
 	public boolean compareSupplierPOSAssignmentListInfo(POSInfo expected) {
 		POSInfo actual = getPOSInfo(expected.productID);
 		
 		boolean pass = true;
 		pass &= MiscFunctions.compareResult("POS Assigned Status", expected.assignToSupplier, actual.assignToSupplier);
 		pass &= MiscFunctions.compareResult("POS Product ID", expected.productID, actual.productID);
 		pass &= MiscFunctions.compareResult("POS Product Name", expected.product, actual.product);
 		pass &= MiscFunctions.compareResult("POS Product Description", expected.productDescription, actual.productDescription);
 		pass &= MiscFunctions.compareResult("POS Product Group", expected.productGroup, actual.productGroup);
 		pass &= MiscFunctions.compareResult("POS Supplier Product Code", expected.supplierProductCode, actual.supplierProductCode);
 		pass &= MiscFunctions.compareResult("POS Supplier Unit Cost", expected.unitCost, actual.unitCost);
 		
 		return pass;
 	}
	
	/**
	 * Assign POS to POS Supplier by Name
	 * @param posName
	 * @param unitCost
	 * @param productCode
	 */
	public void assignSelectedPOSProductByName(String posName, String unitCost, String productCode){
		IHtmlObject objs[] = getPosTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int row = table.findRow(3, posName);
		this.setSupplierUnitCost(unitCost, row-1);
		if(!StringUtil.isEmpty(productCode)) {
			this.setSupplierProductCode(productCode, row -1);
		}
		this.selectSupplierCheckBox(row-1);
		
		this.clickAssignSelectedPosProducts();
		ajax.waitLoading();
		this.waitLoading();
		
		Browser.unregister(objs);
	}
}
