package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrPrivilegeQuantityControlPage extends LicMgrPrivilegeProductDetailsPage{
	
	private static LicMgrPrivilegeQuantityControlPage _instance = null;

	private String prefix = "PrivilegeProductQuantityControlSearchCriteria|ProductQuantityControlSearchCriteria-\\d+\\.";
	private static RegularExpression statusRegx=new RegularExpression("PrivilegeProductQuantityControlSearchCriteria|ProductQuantityControlSearchCriteria-\\d+\\.statusID",false);
	private static RegularExpression locClassRegx=new RegularExpression("PrivilegeProductQuantityControlSearchCriteria|ProductQuantityControlSearchCriteria-\\d+\\.locationClassID",false);
	protected LicMgrPrivilegeQuantityControlPage(){}
	
	public static LicMgrPrivilegeQuantityControlPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrPrivilegeQuantityControlPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add Quantity Control") && super.exists();
	}
	
	public void search(boolean currentOnly,String status,String locClass){
		if(currentOnly){
			this.selectShowCurrentReordsOnly();
			
		}else{
			this.setQuantityControlSearchInfo(status, locClass);
		}
		this.clickGo();
		ajax.waitLoading();
	}
	
	public void clickAddQuantityControl() {         
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Quantity Control");
	}
	
	public String getQuantityControlID(String locationClass){
		String quantityControlID = "";
		IHtmlObject[] objs = browser.getTableTestObject(".id", "privilegeProductQuantityControlGrid");
		
		if(objs.length<1){
			throw new ObjectNotFoundException("Privilege Product Quantity Control Table not Found.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int counter = -1;
		String status, locClass;
		for(int i=1; i<table.rowCount(); i++){
			status = table.getCellValue(i, 1).trim();
			locClass = table.getCellValue(i, 2).trim();		
		
			if(status.equals("Active") 
					&& locClass.equals(locationClass.replaceAll(" - ", "-"))){
				counter = i;
				break;
			}
		}
		
		if(counter>-1){
			quantityControlID = table.getCellValue(counter, 0);
		}
		Browser.unregister(objs);
		return quantityControlID;
	}
	
	public String getQuantityControlID(String locationClass, String multiSalesAllowance){
		String quantityControlID = "";
		IHtmlObject[] objs = browser.getTableTestObject(".id", "privilegeProductQuantityControlGrid");
		
		if(objs.length<1){
			throw new ObjectNotFoundException("Privilege Product Quantity Control Table not Found.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int counter = -1;
		String status,locClass,multiSalesAllow;
		for(int i=1; i<table.rowCount(); i++){
			status = table.getCellValue(i, 1).trim();
			locClass = table.getCellValue(i, 2).trim();			
			multiSalesAllow = table.getCellValue(i, 3).trim();
		
			if(status.equals("Active") 
					&& locClass.equals(locationClass.replaceAll(" - ", "-"))
					&& multiSalesAllow.equals(multiSalesAllowance)){
				counter = i;
				break;
			}
		}
		
		if(counter>-1){
			quantityControlID = table.getCellValue(counter, 0);
		}
		Browser.unregister(objs);
		return quantityControlID;		
	}
	
	/**
	 * Check whether a specific quantity control record exists or not
	 * @param quantityControl
	 * @return
	 */
	public boolean checkQuantityControlRecordExists(QuantityControlInfo quantityControl) {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "privilegeProductQuantityControlGrid");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Privilege Product Quantity Control Table not Found.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		String status,locClass,multiSalesAllow;
		for(int i=1; i<table.rowCount(); i++) {
			status = table.getCellValue(i, 1).trim();
			locClass = table.getCellValue(i, 2).trim();			
			multiSalesAllow = table.getCellValue(i, 3).trim();
		
			if(status.equals(quantityControl.status) 
					&& locClass.equals(quantityControl.locationClass.replaceAll(" - ", "-"))
					&& multiSalesAllow.equals(quantityControl.multiSalesAllowance)) {
				return true;
			}
		}
		
		Browser.unregister(objs);
		return false;
	}
	
	public void clickQuantityControlID(String quantityControlID){
		browser.clickGuiObject(".class", "Html.A", ".text", quantityControlID, true);
	}
	
	public void uncheckShowCurrentRecordsOnly(){
		browser.unSelectCheckBox(".id", 
				new RegularExpression("^"+prefix+"showCurrentRecordsOnly",false));
		ajax.waitLoading();
	}
	
	public void selectShowCurrentReordsOnly(){
		browser.selectCheckBox(".id", 
				new RegularExpression("^"+prefix+"showCurrentRecordsOnly",false));
		ajax.waitLoading();
	}
	
	public boolean checkShowCurrentRecordsOnlyIsChecked(){
		return browser.isCheckBoxSelected(".id", 
				new RegularExpression("^"+prefix+"showCurrentRecordsOnly",false));
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(".id", 
				new RegularExpression("^"+prefix+"statusID",false), status);
	}
	
	public void selectLocationClass(String locClass){
		if(locClass.trim().length()<1){
			browser.selectDropdownList(".id", 
					new RegularExpression("^"+prefix+"locationClassID",false),0);
		
		}else{
			browser.selectDropdownList(".id", 
				new RegularExpression("^"+prefix+"locationClassID",false),locClass);	
		}
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text","Go");
	}
	
	public void setQuantityControlSearchInfo(String status, String locationClass){
		if(this.checkShowCurrentRecordsOnlyIsChecked()){
			this.uncheckShowCurrentRecordsOnly();
		}
		this.selectStatus(status);
		this.selectLocationClass(locationClass);
	}
	
	public List<String> getLocationClassValuesForDropDownList(){
		return browser.getDropdownElements(".id", locClassRegx);
	}
	
	
	public List<String> getStatusForDDList(){
		return browser.getDropdownElements(".id", statusRegx);
	}
	
	public List<String> getAllLocationClass(){
		List<String> list=this.getColumnValues("Location Class");
		list.remove(0);
		return list;
	}
	
	public List<String> getColumnValues(String colName){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "privilegeProductQuantityControlGrid");
		if(objs.length<1){
			throw new ObjectNotFoundException("Privilege Product Quantity Control Table not Found.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int col = table.findColumn(0, colName);
		List<String> columnValues = table.getColumnValues(col);
		
		Browser.unregister(objs);
		return columnValues;
	}
	
	public List<String> getColumnNames(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "privilegeProductQuantityControlGrid");
		if(objs.length<1){
			throw new ObjectNotFoundException("Privilege Product Quantity Control Table not Found.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> columnNames = table.getRowValues(0);
		
		Browser.unregister(objs);
		return columnNames;
	}
	
	/**
	 * Get the displayed quantity control records count
	 * @return
	 */
	public int getQuantityControlRecordsCount() {
		this.uncheckShowCurrentRecordsOnly();
		this.selectStatus("All");
		this.clickGo();
		IHtmlObject objs[] = browser.getTableTestObject(".id", "privilegeProductQuantityControlGrid");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find Quantity Control Record table object.");
		}
		int count = ((IHtmlTable)objs[0]).rowCount() - 1;
		
		Browser.unregister(objs);
		return count;
	}
	
	/**
	 * Get the displayed quantity control records ids
	 * @return
	 */
	public List<String> getQuantityControlIDs() {
		List<String> pricingIDs = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".id", "privilegeProductQuantityControlGrid");
		
		Property[] p = new Property[2];
		p[0] = new Property(".text",new RegularExpression("^\\d+",false));
		p[1] = new Property(".class","Html.A");
		IHtmlObject[] idObjs = browser.getHtmlObject(p, objs[0]);
		
		for(int i = 0; i < idObjs.length; i ++) {
			pricingIDs.add(idObjs[i].text());
		}
		
		Browser.unregister(objs);
		Browser.unregister(idObjs);
		return pricingIDs;
	}
	
	public void clickLocationClass(){
		browser.clickGuiObject(".class", "Html.A", ".text","Location Class",true);
	}
	
	/**
	 * Compare the actual quantity control with expected
	 * @param quantityControl
	 * @return
	 */
	public boolean compareQuantityControlListInfo(QuantityControlInfo quantityControl) {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "privilegeProductQuantityControlGrid");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Privilege Product Quantity Control Table not Found.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];

		int rowIndex = table.findRow(0, quantityControl.id);
		String status = table.getCellValue(rowIndex, table.findColumn(0, "Status"));
		String locationClass = table.getCellValue(rowIndex, table.findColumn(0, "Location Class"));
		String multisalesAllowance = table.getCellValue(rowIndex, table.findColumn(0, "Multi-Sales Allowance"));
		String maxQuantityPerTransaction = table.getCellValue(rowIndex, table.findColumn(0, "Max Quantity Per Transaction"));
		String maxAllowed = table.getCellValue(rowIndex, table.findColumn(0, "Max Allowed"));
		String replacementMaxAllowed = table.getCellValue(rowIndex, table.findColumn(0, "Replacement Max Allowed"));
		
		boolean result = true;
		if(!status.equals(quantityControl.status)) {
			logger.error("The expceted quantity control Status is " + quantityControl.status + ", but actual value is " + status);
			result &= false;
		}
		if(!locationClass.equals(quantityControl.locationClass.replace(" - ", "-"))) {
			logger.error("The expceted quantity control Location Class is " + quantityControl.locationClass + ", but actual value is " + locationClass);
			result &= false;
		}
		if(!multisalesAllowance.equals(quantityControl.multiSalesAllowance)) {
			logger.error("The expceted quantity control Multi-Sales Allowance is " + quantityControl.multiSalesAllowance + ", but actual value is " + multisalesAllowance);
			result &= false;
		}
		if(Integer.parseInt(maxQuantityPerTransaction) != Integer.parseInt(quantityControl.maxQuantityPerTran)) {
			logger.error("The expceted quantity control Status is " + quantityControl.maxQuantityPerTran + ", but actual value is " + maxQuantityPerTransaction);
			result &= false;
		}
		if(Integer.parseInt(maxAllowed) != Integer.parseInt(quantityControl.maxAllowed)) {
			logger.error("The expceted quantity control Status is " + quantityControl.maxAllowed + ", but actual value is " + maxAllowed);
			result &= false;
		}
		if(Integer.parseInt(replacementMaxAllowed) != Integer.parseInt(quantityControl.replacementMaxAllowed)) {
			logger.error("The expceted quantity control Status is " + quantityControl.replacementMaxAllowed + ", but actual value is " + replacementMaxAllowed);
			result &= false;
		}
		Browser.unregister(objs);
		
		return result;
	}
}
