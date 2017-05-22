package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 11, 2013
 */
public class LicMgrLotteryQuantityControlsPage extends LicMgrLotteryProductDetailsPage {
	private static LicMgrLotteryQuantityControlsPage _instance = null;
	
	private LicMgrLotteryQuantityControlsPage() {}
	
	public static LicMgrLotteryQuantityControlsPage getInstance() {
		if(_instance == null) _instance = new LicMgrLotteryQuantityControlsPage();
		
		return _instance;
 	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add Quantity Control") && super.exists();
	}
	
	private static String prefix = "PrivilegeProductQuantityControlSearchCriteria|ProductQuantityControlSearchCriteria-\\d+\\.";
	private static RegularExpression statusRegx=new RegularExpression(prefix + "statusID",false);
	private static RegularExpression locClassRegx=new RegularExpression(prefix + "locationClassID",false);
	
	private static final String ID_COL = "ID";
	private static final String STATUS_COL = "Status";
	private static final String LOCATION_CLASS_COL = "Location Class";
	private static final String MULTI_SALES_ALLOWANCE_COL = "Multi-Sales Allowance";
	private static final String MAX_QUANTITY_PER_TRANSACTION_COL = "Max Quantity Per Transaction";
	private static final String MAX_ALLOWED_COL = "Max Allowed";
	
	public void search(String status, String locClass){
		this.setQuantityControlSearchInfo(status, locClass);
		this.clickGo();
		ajax.waitLoading();
	}
	
	public void clickAddQuantityControl() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Quantity Control");
	}
	
	private IHtmlObject[] getQuantityControlTableObject() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "privilegeProductQuantityControlGrid");
		
		if(objs.length<1) throw new ObjectNotFoundException("Lottery Product Quantity Control Table not Found.");
		
		return objs;
	}
	
	public String getQuantityControlID(String locationClass){
		IHtmlObject objs[] = getQuantityControlTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		String quantityControlID = "";
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
		IHtmlObject objs[] = getQuantityControlTableObject();
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
		IHtmlObject objs[] = getQuantityControlTableObject();
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
	
	public void selectStatus(String status){
		browser.selectDropdownList(".id", 
				new RegularExpression("^"+prefix+"statusID",false), status);
	}
	
	public void selectStatus(int index) {
		browser.selectDropdownList(".id", new RegularExpression("^" + prefix + "statusID", false), index);
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
		List<String> list=this.getColumnValues(LOCATION_CLASS_COL);
		list.remove(0);
		return list;
	}
	
	public List<String> getColumnValues(String colName){
		IHtmlObject objs[] = getQuantityControlTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int col = table.findColumn(0, colName);
		List<String> columnValues = table.getColumnValues(col);
		
		Browser.unregister(objs);
		return columnValues;
	}
	
	public List<String> getColumnNames(){
		IHtmlObject objs[] = getQuantityControlTableObject();
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
		this.selectStatus(0);
		this.clickGo();
		IHtmlObject objs[] = getQuantityControlTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int count = table.rowCount() - 1;
		
		Browser.unregister(objs);
		return count;
	}
	
	/**
	 * Get the displayed quantity control records ids
	 * @return
	 */
	public List<String> getQuantityControlIDs() {
		IHtmlObject objs[] = getQuantityControlTableObject();
		
		List<String> pricingIDs = new ArrayList<String>();
		
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
		browser.clickGuiObject(".class", "Html.A", ".text",LOCATION_CLASS_COL,true);
	}
	
	/**
	 * Compare the actual quantity control with expected
	 * @param quantityControl
	 * @return
	 */
	public boolean compareQuantityControlListInfo(QuantityControlInfo quantityControl) {
		IHtmlObject objs[] = getQuantityControlTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];

		int rowIndex = table.findRow(0, quantityControl.id);
		String status = table.getCellValue(rowIndex, table.findColumn(0, STATUS_COL));
		String locationClass = table.getCellValue(rowIndex, table.findColumn(0, LOCATION_CLASS_COL));
		String multisalesAllowance = table.getCellValue(rowIndex, table.findColumn(0, MULTI_SALES_ALLOWANCE_COL));
		String maxQuantityPerTransaction = table.getCellValue(rowIndex, table.findColumn(0, MAX_QUANTITY_PER_TRANSACTION_COL));
		String maxAllowed = table.getCellValue(rowIndex, table.findColumn(0, MAX_ALLOWED_COL));
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Quantity Control - Status", quantityControl.status, status);
		result &= MiscFunctions.compareResult("Quantity Control - Location Class", quantityControl.locationClass.replace(" - ", "-"), locationClass);
		result &= MiscFunctions.compareResult("Quantity Control - Multi-Sales Allowance", quantityControl.multiSalesAllowance, multisalesAllowance);
		result &= MiscFunctions.compareResult("Quantity Control - Max Quantity Per Transaction", quantityControl.maxQuantityPerTran, maxQuantityPerTransaction);
		result &= MiscFunctions.compareResult("Quantity Control - Max Allowed", quantityControl.maxAllowed, maxAllowed);
		Browser.unregister(objs);
		
		return result;
	}
}
