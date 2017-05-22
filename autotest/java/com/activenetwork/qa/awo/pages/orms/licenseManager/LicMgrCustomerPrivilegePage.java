package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemDetailPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * ScriptName: LicMgrCustomerPrivilegePage
 * Description:
 * @date: Mar 14, 2011
 * @author qchen
 *
 */
public class LicMgrCustomerPrivilegePage extends LicMgrCustomerDetailsPage {

	private static LicMgrCustomerPrivilegePage _instance = null;

	private LicMgrCustomerPrivilegePage() {}

	public static LicMgrCustomerPrivilegePage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrCustomerPrivilegePage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.licenseYearFilterList());
	}

	protected Property[] licenseYearFilterList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression("CustomerPrivilegeInstanceSearchCriteria-\\d+\\.licenseYear", false));
	}
	
	protected Property[] privilegeListGridTable(){
		return Property.concatPropertyArray(table(), ".id", new RegularExpression("CustomerPrivilegetListGrid|grid_\\d+_LIST", false));
	}
	
	public void selectLicenseYear(String licenseYear) {
		browser.selectDropdownList(this.licenseYearFilterList(), licenseYear);
	}

	public boolean isLicenseYearOptionExist(String licenseYear) {
		return browser.dropdownListContains(this.licenseYearFilterList(), licenseYear);
	}
	
	/**
	 * Get the check box id by check box label name
	 * @param checkBoxLabelName
	 * @return
	 */
	private String getCheckBoxIdValue(String checkBoxLabelName) {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.LABEL", ".className", "trailing",".text",checkBoxLabelName));

		if(objs.length == 0) {
			throw new ItemNotFoundException("Cannot find LABEL "+checkBoxLabelName);
		}


		String checkBoxIdValue = objs[0].getProperty("for").trim();

		Browser.unregister(objs);
		return checkBoxIdValue;
	}

	public void checkActive() {
		browser.selectCheckBox(".id", getCheckBoxIdValue("Active"));
	}

	public void uncheckActive() {
		browser.unSelectCheckBox(".id", getCheckBoxIdValue("Active"));
	}

	public void checkExpired() {
		browser.selectCheckBox(".id", getCheckBoxIdValue("Expired"));
	}

	public void uncheckExpired() {
		browser.unSelectCheckBox(".id", getCheckBoxIdValue("Expired"));
	}

	public void checkInvalid() {
		browser.selectCheckBox(".id", getCheckBoxIdValue("Invalid"));
	}

	public void uncheckInvalid() {
		browser.unSelectCheckBox(".id", getCheckBoxIdValue("Invalid"));
	}

	public void checkReversed() {
		browser.selectCheckBox(".id", getCheckBoxIdValue("Reversed"));
	}

	public void uncheckReversed() {
		browser.unSelectCheckBox(".id", getCheckBoxIdValue("Reversed"));
	}

	public void checkRevoked() {
		browser.selectCheckBox(".id", getCheckBoxIdValue("Revoked"));
	}

	public void uncheckRevoked() {
		browser.unSelectCheckBox(".id", getCheckBoxIdValue("Revoked"));
	}

	public void checkTransferred() {
		browser.selectCheckBox(".id", getCheckBoxIdValue("Transferred"));
	}

	public void uncheckTransferred() {
		browser.unSelectCheckBox(".id", getCheckBoxIdValue("Transferred"));
	}

	public void checkVoided() {
		browser.selectCheckBox(".id", getCheckBoxIdValue("Voided"));
	}

	public void uncheckVoided() {
		browser.unSelectCheckBox(".id", getCheckBoxIdValue("Voided"));
	}

	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Go", false));
	}

	public void resetSearchCriteria() {
		uncheckActive();
		uncheckExpired();
		uncheckInvalid();
		uncheckReversed();
		uncheckRevoked();
		uncheckTransferred();
		uncheckVoided();
	}

	/**
     * Click first privilege item in customer privilege page
     * @return
     */
    public String clickFirstPrivilege() {
		IHtmlObject itemsLsitTable[] = browser.getTableTestObject(".id", new RegularExpression("CustomerPrivilegetListGrid|grid_\\d+_LIST", false));
		String privilegeNumber = "";
		if(itemsLsitTable.length > 0) {
			privilegeNumber = ((IHtmlTable)itemsLsitTable[0]).getCellValue(1, 0).trim();
		}

		browser.clickGuiObject(".class", "Html.A", ".text", privilegeNumber, true);
		Browser.unregister(itemsLsitTable);
		return privilegeNumber;
	}

    /**
     * Click privilege number link
     * @param priNum
     */
    public void clickPrivilege(String priNum) {
		browser.clickGuiObject(".class", "Html.A", ".text" , priNum, true);
		ajax.waitLoading();
    }

    /**
     * Goto first privilege item's detail page from CustomerDetail.Privileges tab
     * @return
     */
    public String gotoFirstPrivilgeDetailPage(String licenseYear, String[]searchCriteria) {
    	LicMgrPrivilegeItemDetailPage privilegeDetailPg = LicMgrPrivilegeItemDetailPage.getInstance();

    	if(!this.exists()){
    		this.clickPrivilegesTab();
    	}
    	
		resetSearchCriteria();
//		clickGo();
//    	ajax.waitLoading();
		searchPrivilege(licenseYear, searchCriteria);

		String privilegeNum = clickFirstPrivilege();
		privilegeDetailPg.waitLoading();

		return privilegeNum;
	}

    /**
     * Goto privilege detail page by clicking privilege number link
     * @param privilegeNum
     * @return
     */
    public void gotoPrivilegeDetailPage(String privilegeNum,String year) {
    	LicMgrPrivilegeItemDetailPage privilegeDetailPg = LicMgrPrivilegeItemDetailPage.getInstance();

    	if(!this.exists()){
    		clickPrivilegesTab();
    		ajax.waitLoading();
    	}
		resetSearchCriteria();
		if(year!=null && !"".equals(year)){
			this.selectLicenseYear(year);
		}
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
		clickPrivilege(privilegeNum);
		privilegeDetailPg.waitLoading();
    }

    /**
     * Search privilege by given search criteria
     * @param privilegeInfo
     */
    public void searchPrivilege(String searchLicYear, String[] searchStatus){
//    	String message = this.getWarnMsg().trim();
//    	if(StringUtil.notEmpty(searchLicYear) && !"No Privileges were found.".equalsIgnoreCase(message)){
    	if(StringUtil.notEmpty(searchLicYear) && this.isLicenseYearOptionExist(searchLicYear)){ //Lesley[20131112]: update due to no message object sometimes
        	this.selectLicenseYear(searchLicYear);
    	}
    	
    	if (searchStatus!=null) { // fix the issue when send null value in the method invalidateAllPrivilegesPerCustomer(Customer customer, String operateReason, String operateNote)
    		for(int i=0;i<searchStatus.length;i++){
        		if(!"".equals(searchStatus[i])){
        			browser.selectCheckBox(".id", getCheckBoxIdValue(searchStatus[i]));
        		} else {
        			browser.unSelectCheckBox(".id", getCheckBoxIdValue(searchStatus[i]));
        		}
        	}
    	}
    	this.clickGo();
    	ajax.waitLoading();
    }

    /**
     * Get privilege ID list.
     */
    public List<String> getAllPrivilegeIDList(){
		List<String> privilegeIDList = new ArrayList<String>();
		boolean isNextAvailable = false;
		
    	do{
    		if(isNextAvailable){
    			this.clickNext();
    			ajax.waitLoading();
    		}
    		IHtmlObject[] objs = null;
    		objs = browser.getTableTestObject(".id", new RegularExpression("CustomerPrivilegetListGrid|grid_\\d+_LIST", false));
    		if(null == objs || objs.length < 0){
    			throw new ErrorOnPageException("Can't find the table.");
    		}
    		IHtmlTable table = (IHtmlTable)objs[0];
    		MiscFunctions.dumpTable(table);
    		if(table.rowCount()>1){
    			for(int i=1;i<table.rowCount();i++){
    				privilegeIDList.add(table.getCellValue(i, 0));
    			}
    		}
    		
    		isNextAvailable = this.isNextAvailable();
    	}while (isNextAvailable);

    	return privilegeIDList;
    }
    
    public IHtmlObject[] getPrivilegeListGridObjs(){
    	IHtmlObject[] objs = browser.getTableTestObject(privilegeListGridTable());
    	if(objs.length<1){
    		throw new ObjectNotFoundException("Failed to find privilege list grid objects.");
    	}
    	return objs;
    }
    
    public boolean checkColExisted(String colName){
    	IHtmlObject[] objs = getPrivilegeListGridObjs();
    	ITable table = (ITable) objs[0];
    	List<String> tableColNames = table.getRowValues(0);
    	Browser.unregister(objs);
    	return tableColNames.toString().contains(colName);
    }
    
    public boolean isNextAvailable(){
    	return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Next");
    }
    
    public void clickNext(){
    	browser.clickGuiObject(".class", "Html.A", ".text", "Next");
    }

    /**
     * Get privilege ID list by name
     */
    public List<String> getPrivilegeIDListByName(String privilegeName){
//    	HtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+_LIST", false));
    	IHtmlObject[] objs = getPrivilegeListGridObjs();
    	IHtmlTable table = (IHtmlTable)objs[0];
    	List<String> privilegeIDList = new ArrayList<String>();
    	if(table.rowCount()>1){
        	for(int i=1;i<table.rowCount();i++){
        		if(privilegeName.equals((table.getCellValue(i, 3)).replaceAll("\\s*", ""))){//updated by pzhu, for format of 'table.getCellValue(i, 2)' always like '989 - UnlessAllParamPriOnFile', there are blank spaces!!!!
            		privilegeIDList.add(table.getCellValue(i, 0));
        		}
        	}
    	}
    	return privilegeIDList;
    }
    
    public String getFirstPrivDocumentNum(){
    	IHtmlObject[] objs = getPrivilegeListGridObjs();
    	IHtmlTable table = (IHtmlTable)objs[0];
    	String documentNum = table.getCellValue(1, 1);
    	Browser.unregister(objs);
    	return documentNum;
    }
    /**
     * get privilege list row info
     * @return
     */
    public List<List<String>> getPrivilegeListRowInfo(){
    	List<List<String>> privliegeList = new ArrayList<List<String>>();
    	IHtmlObject[] objs = getPrivilegeListGridObjs();
    	IHtmlTable table = (IHtmlTable)objs[0];
    	for(int i =1;i<table.rowCount();i++){
    		privliegeList.add(table.getRowValues(i));
    	}
    	return privliegeList;
    }
    /**
	 * compare purchase privilege valid date
	 * @param expectedValidFromDate
	 * @param expectedValidToDate
	 * @param index 0 -- original, 1 -- renew
	 * @return
	 */
    public boolean comparePrivilegeValidDate(String privilegeName,String expectedValidFromDate,String expectedValidToDate,int index){
		boolean pass = true;
		List<List<String>> list = this.getTheSamePrivlegeRowInfo(privilegeName);
		if (list.size() < 1) {
			throw new ErrorOnPageException("No privilege info exist");
		}

		if(index == 0){
			index = list.size() - 1;
		} else if(index == 1){
			index = 0;
		}

		String validDate = list.get(index).get(6);
		int halflength = validDate.length() / 2;
		String validFromDate = validDate.substring(0, halflength).trim();
		String validToDate = validDate
				.substring(halflength, validDate.length()).trim();

		pass &= MiscFunctions.compareResult("Purchach privileg valid from date", expectedValidFromDate.trim(), validFromDate);
		pass &= MiscFunctions.compareResult("Purchach privileg valid to date", expectedValidToDate.trim(), validToDate);
		return pass;
	}
    
    
    public List<List<String>> getTheSamePrivlegeRowInfo(String privilegeName){
    	List<List<String>> array= new ArrayList<List<String>>();
		List<List<String>> list = this.getPrivilegeListRowInfo();
		if(list.size()<1){
			throw new ErrorOnPageException("No privilege info exist");
		}
		for(int i=0;i<list.size();i++){
			String name = list.get(i).get(3);
			name = name.replace(" ", "");
			if(name.equals(privilegeName)){
				array.add(list.get(i));
			}
		}
		return array;
    }
    
    /**
	 * compare purchase privilege valid date.
	 * @param expectedValidFromDate
	 * @param expectedValidToDate
	 * @return
	 */
	public boolean comparePurcasePrivilegeValidDate(String privilegeName,String expectedValidFromDate,String expectedValidToDate){
		return comparePrivilegeValidDate(privilegeName,expectedValidFromDate,expectedValidToDate,0);
	}
	/**
	 * compare renewal privilege valid date.
	 * @param expectedValidFromDate
	 * @param expectedValidToDate
	 * @return
	 */
	public boolean compareRenewalPrivilegeValidDate(String privilegeName,String expectedValidFromDate,String expectedValidToDate){
		return comparePrivilegeValidDate(privilegeName,expectedValidFromDate,expectedValidToDate,1);
	}
}


