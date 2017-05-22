package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


public class LicMgrVendorBondsSubPage extends LicMgrVendorDetailsPage {
	private static LicMgrVendorBondsSubPage instance=null;
	
	private LicMgrVendorBondsSubPage() {};
	
	public static LicMgrVendorBondsSubPage getInstance() {
		if(null == instance) {
			instance = new LicMgrVendorBondsSubPage();
		}
		return instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.A",".text","Add Bond");
	}

	public void clickAddBond() {
		browser.clickGuiObject(".class","Html.A",".text","Add Bond");
	}
	
	public void clickChangeAssignments() {
		browser.clickGuiObject(".class","Html.A",".text","Change Agent/Bond Assignments");
	}
	
	public void clickViewAssignments() {
		browser.clickGuiObject(".class","Html.A",".text","View Agent/Bond Assignments");
	}
	
	public void selectBondIsserForSearch(String issuer){
		if(issuer.equalsIgnoreCase("null"))
			browser.selectDropdownList(".id", new RegularExpression("VendorBondSearchCriteria-[0-9]*.bondIssuerID",false), 0);
		else
			browser.selectDropdownList(".id", new RegularExpression("VendorBondSearchCriteria-[0-9]*.bondIssuerID",false),issuer,true);
	}
	
	/**
	 * Get the bond id identified by bond detail information
	 * @param bond
	 * @return
	 */
	public String getBondIDByBondInfo(VendorBondInfo bond) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "vendorBondGrid");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("There is not a table which id = vendorBondGrid");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		String bondIssuer, bondType, bondNum;
		int counter = -1;
		for(int i = 1; i < table.rowCount(); i ++) {
			bondIssuer = table.getCellValue(i, 3);
			bondType = table.getCellValue(i, 5);
			bondNum = table.getCellValue(i, 6);
			if(bondIssuer.startsWith(bond.issuer)
					&& bondType.equals(bond.type)
					&& bondNum.equals(bond.bondNum)
					&& bond.status.equalsIgnoreCase(table.getCellValue(i, 2))) {
				counter = i;
				break;
			}
		}
		
		if(counter == -1) {
			throw new ObjectNotFoundException("Can't find the bond id accoring to bond info.");
		}
		
		String id = table.getCellValue(counter, 1);
		
		Browser.unregister(objs);
		return id;
	}
	
	public void selectBondByID(String id){
		IHtmlObject objs[] = browser.getTableTestObject(".id", "vendorBondGrid");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("There is not a table which id = vendorBondGrid");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		String bondID;
		int counter = -1;
		
		for(int i = 0; i < table.rowCount(); i ++) {
			bondID = table.getCellValue(i, 1);
			if(bondID.equals(id)) {
				counter = i-1;
				break;
			}
		}
		
		if(counter == -1) {
			throw new ObjectNotFoundException("Can't find the bond id");
		}
		
		Browser.unregister(table);
		Browser.unregister(objs);
		logger.info("select bond ID: "+id);
		browser.selectCheckBox(".id", "vendor_bond_id_" + counter);
	}
	
	public void deactiveBondByID(String id){
		logger.info("Deactivate bond by ID: "+id);
		
		showAllRecordsInList();
		selectBondByID(id);
		browser.clickGuiObject(".text","Deactivate",true);
		ajax.waitLoading();
		this.waitLoading();		
	}
	
	public void clickBondId(String id) {
		browser.clickGuiObject(".class","Html.A",".text",id,true);
	}
	
	/**
	 * Get all bonds records from bond sub page
	 * @param activeOnly -- only get active bond records
	 * @return
	 */
	public List<VendorBondInfo> getAllBonds(boolean activeOnly){
		List<VendorBondInfo> list = new ArrayList<VendorBondInfo>();
		
		IHtmlObject objs[] = browser.getTableTestObject(".id", "vendorBondGrid");
		if(objs.length < 1) {
			throw new ItemNotFoundException("There is not a table which id = vendorBondGrid");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		for(int i = 1 ; i < table.rowCount(); i ++) {
			if(activeOnly && table.getCellValue(0, 0).contains("Deactivate")){
				continue;
			}
			
			VendorBondInfo bond = new VendorBondInfo();
			bond.id = table.getCellValue(i, 1);
			bond.status = table.getCellValue(i, 2);
			bond.issuer = table.getCellValue(i, 3);
			bond.contact = table.getCellValue(i, 4);
			bond.type = table.getCellValue(i, 5);
			bond.bondNum = table.getCellValue(i, 6);
			bond.bondAmount = table.getCellValue(i, 7);
			bond.assignedAgentsNum = table.getCellValue(i, 8);
			bond.effectiveFrom = table.getCellValue(i, 9);
			bond.effectiveTo = table.getCellValue(i, 10);
			
			list.add(bond);
		}
		
		Browser.unregister(table);
		Browser.unregister(objs);
		
		return list;
	}
	
	public boolean isSelectCurrentRecordsOnly(){
		return browser.isCheckBoxSelected(".id", new RegularExpression("VendorBondSearchCriteria-[0-9]*.includeCurrentRecordsOnly",false));
	}
	public void selectCurrentRecordsOnly(){
		browser.selectCheckBox(".id", new RegularExpression("VendorBondSearchCriteria-[0-9]*.includeCurrentRecordsOnly",false));
	}
	
	public void unSelectCurrentRecordsOnly(){
		browser.unSelectCheckBox(".id", new RegularExpression("VendorBondSearchCriteria-[0-9]*.includeCurrentRecordsOnly",false));
	}
	
	public void selectIncludeActive(){
		browser.selectCheckBox(".id", new RegularExpression("VendorBondSearchCriteria-[0-9]*.includeActive",false));
	}
	
	public void unSelectincludeactive(){
		browser.unSelectCheckBox(".id", new RegularExpression("VendorBondSearchCriteria-[0-9]*.includeActive",false));
	}
	
	public void selectIncludeInActive(){
		browser.selectCheckBox(".id", new RegularExpression("VendorBondSearchCriteria-[0-9]*.includeInActive",false));
	}
	
	public void unSelectIncludeInactive(){
		browser.unSelectCheckBox(".id", new RegularExpression("VendorBondSearchCriteria-[0-9]*.includeInActive",false));
	}
	
	public void selectTypeBond(){
		browser.selectCheckBox(".id", new RegularExpression("VendorBondSearchCriteria-[0-9]*.includeTypeBond",false));
	}
	
	public void unSelectTypeBond(){
		browser.unSelectCheckBox(".id", new RegularExpression("VendorBondSearchCriteria-[0-9]*.includeTypeBond",false));
	}
	
	public void selectTypeLetterOfCredit(){
		browser.selectCheckBox(".id", new RegularExpression("VendorBondSearchCriteria-[0-9]*.includeTypeLetterOfCredit",false));
	}
	
	public void unSelectTypeLetterOfCredit(){
		browser.unSelectCheckBox(".id", new RegularExpression("VendorBondSearchCriteria-[0-9]*.includeTypeLetterOfCredit",false));
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void clickNumOfAssignedAgents(String id){
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TR",".text",new RegularExpression(id+".*", false));
		browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("\\d+",false),true,1,objs[0]);
		Browser.unregister(objs);
	}
	
	public VendorBondInfo getBondInfoByID(String id) {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "vendorBondGrid");
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't found the vendor bond table");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		VendorBondInfo bond = new VendorBondInfo();
		int i = 1;
		for(i = 1 ; i < table.rowCount(); i ++) {
			if(table.getCellValue(i, 1).equals(id)) {
				bond.id = table.getCellValue(i, 1);
				bond.status = table.getCellValue(i, 2);
				bond.issuer = table.getCellValue(i, 3);
				bond.contact = table.getCellValue(i, 4);
				bond.type = table.getCellValue(i, 5);
				bond.bondNum = table.getCellValue(i, 6);
				bond.bondAmount = table.getCellValue(i, 7);
				bond.assignedAgentsNum = table.getCellValue(i, 8);
				bond.effectiveFrom = table.getCellValue(i, 9);
				bond.effectiveTo = table.getCellValue(i, 10);
				break;
			}	
		}
		
		if(i==table.rowCount()){
			throw new ObjectNotFoundException("Can't found the vendor bond info by ID");
		}

		Browser.unregister(objs);
		return bond;
	}
	
	public void verifyBondInList(VendorBondInfo bondInfo) {
		logger.info("Verify Bond info in list.");
		
		VendorBondInfo comapredBondInfo = getBondInfoByID(bondInfo.id);
		
		if(!comapareBondInfo(bondInfo, comapredBondInfo)){
			throw new ErrorOnPageException("Error on bond id="+bondInfo.id+" in bond list, please check log for more details");
		}
		
	}
	
	public boolean comapareBondInfo(VendorBondInfo bondInfo, VendorBondInfo comparedInfo){
		boolean flag = true;
		
		if(!comparedInfo.status.equals(bondInfo.status)) {
			logger.error("Bond status display error! The expect value is:" + bondInfo.status + ", But actual is: " + comparedInfo.status);
			flag &= false;
		}
		
		if(!comparedInfo.issuer.startsWith(bondInfo.issuer)) {
			logger.error("Bond issuer display error! The expect value is:" + bondInfo.issuer + "But actual is: " + comparedInfo.issuer);
			flag &= false;
		}
		
		if(!comparedInfo.type.equals(bondInfo.type)) {
			logger.error("Bond type display error! The expect value is:" + bondInfo.type + "But actual is: " + comparedInfo.type);
			flag &= false;
		}
		
		if(!comparedInfo.bondNum.equals(bondInfo.bondNum)) {
			logger.error("Bond num display error! The expect value is:" + bondInfo.bondNum + "But actual is: " + comparedInfo.bondNum);
			flag &= false;
		}
		
		if(!comparedInfo.bondAmount.replaceAll("\\$", "").split("\\.")[0].equals(bondInfo.bondAmount.split("\\.")[0])) {
			logger.error("Bond amount display error! The expect value is:" + bondInfo.bondAmount);
			flag &= false;
		}
		
		if(comparedInfo.assignedAgentsNum.length()>1 &&
          !comparedInfo.assignedAgentsNum.equals(bondInfo.assignedAgentsNum)) {
			logger.error("Bond assignedAgentsNum display error! The expect value is:" + bondInfo.assignedAgentsNum);
			flag &= false;
		}
		
		flag &= MiscFunctions.compareResult("Bond effective From date", comparedInfo.effectiveFrom, bondInfo.effectiveFrom);
		flag &= MiscFunctions.compareResult("Bond effective To date", comparedInfo.effectiveTo, bondInfo.effectiveTo);
		
		if(comparedInfo.creationDateTime.length()>1 &&
		   !comparedInfo.creationDateTime.substring(0, 15).equalsIgnoreCase(bondInfo.creationDateTime.substring(0, 15))){
			logger.error("Bond creationDateTime date display error! The expect value is:" + bondInfo.creationDateTime + "but the actual is: " + comparedInfo.creationDateTime.substring(0, 15));
			flag &= false;
		}
		
		if(comparedInfo.creationUser.length()>1 &&
		  !comparedInfo.creationUser.replaceAll("\\s+", "").equalsIgnoreCase(bondInfo.creationUser.replaceAll("\\s+", ""))){
			logger.error("Bond creationUser date display error! The expect value is: " + bondInfo.creationUser + "but the actual is: " + comparedInfo.creationUser);
			flag &= false;
		}
		
		if(comparedInfo.lastModifiedDateTime.length()>1 &&
		   !comparedInfo.lastModifiedDateTime.substring(0, 15).equalsIgnoreCase(bondInfo.lastModifiedDateTime.substring(0, 15))){
			logger.error("Bond lastModifiedDateTime date display error! The expect value is:" + bondInfo.lastModifiedDateTime);
			flag &= false;
		}
		
		if(comparedInfo.lastModifiedUser.length()>1 &&
		   !comparedInfo.lastModifiedUser.replaceAll("\\s+", StringUtil.EMPTY).equalsIgnoreCase(bondInfo.lastModifiedUser.replaceAll("\\s+", StringUtil.EMPTY))){
			logger.error("Bond lastModifiedUser date display error! The expect value is:" + bondInfo.lastModifiedUser);
			flag &= false;
		}
		
		return flag;
	}
	
	/**
	 * Show all records in bond list
	 */
	public void showAllRecordsInList(){
		unSelectCurrentRecordsOnly();
		ajax.waitLoading();
		
		selectBondIsserForSearch("null");
		selectIncludeActive();
		selectIncludeInActive();
		unSelectTypeBond();
		unSelectTypeLetterOfCredit();
		
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * Set up search filter criteria
	 * @param isCurrentRecOnly
	 * @param issuer
	 * @param includeActive
	 * @param includeInActive
	 * @param includeBond
	 * @param includeLetterOfCredit
	 */
	public void setupSearchCriteria(boolean isCurrentRecOnly, String issuer, boolean includeActive, boolean includeInActive, boolean includeBond, boolean includeLetterOfCredit){
		LicMgrVendorBondsSubPage bondPg = LicMgrVendorBondsSubPage.getInstance();
		if(!bondPg.isSelectCurrentRecordsOnly() && isCurrentRecOnly){
			bondPg.selectCurrentRecordsOnly();
			ajax.waitLoading();	
		}else if(bondPg.isSelectCurrentRecordsOnly() && !isCurrentRecOnly){
			bondPg.unSelectCurrentRecordsOnly();    
			ajax.waitLoading();
		}	
			
		bondPg.selectBondIsserForSearch(issuer);
		if(issuer.length()<0){
			bondPg.selectBondIsserForSearch(issuer);
		}
		
		if(includeActive){
			bondPg.selectIncludeActive();
		}else{
			bondPg.unSelectincludeactive();
		}
		
		if(includeInActive){
			bondPg.selectIncludeInActive();
		}else{
			bondPg.unSelectIncludeInactive();
		}
		
		if(includeBond){
			bondPg.selectTypeBond();
		}else{
			bondPg.unSelectTypeBond();
		}
		
		if(includeLetterOfCredit){
			bondPg.selectTypeLetterOfCredit();
		}else{
			bondPg.unSelectTypeLetterOfCredit();
		}
		
		bondPg.clickGo();
		ajax.waitLoading();
	}

	public void selectAllBonds() {
		browser.selectCheckBox(".name", "all_slct");
	}

	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate", true);
	}
	
	public void showActiveRecords(){
		unSelectCurrentRecordsOnly();
		ajax.waitLoading();
		
		selectBondIsserForSearch("null");
		selectIncludeActive();
		unSelectIncludeInactive();
		unSelectTypeBond();
		unSelectTypeLetterOfCredit();
		
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
}
