package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderSearchCommonPage;
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
 * @Date  Sep 2, 2013
 */
public class LicMgrApplicationOrderSearchPage extends LicMgrOrderSearchCommonPage {
	
	private static LicMgrApplicationOrderSearchPage _instance = null;
	private LicMgrApplicationOrderSearchPage() {}
	public static LicMgrApplicationOrderSearchPage getInstance() {
		if(_instance == null) _instance = new LicMgrApplicationOrderSearchPage();
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(applicationOrderListTable());
	}
	
	private Property[] searchType() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.searchTypeID", false));
	}
	
	private Property[] searchValue() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.searchValue", false));
	}
	
	private Property[] applicationStatus() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.lotteryOrderStatus", false));
	}
	
	private Property[] productCode() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.productCode", false));
	}
	
	private Property[] licenseYear() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.licenseYear", false));
	}
	
	private Property[] verificationStatus() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.verificationStatus", false));
	}
	
	private Property[] choiceType() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.choiceType", false));
	}
	
	private Property[] huntName() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.huntDescription", false));
	}
	
	private Property[] allowedApplicant() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.applicantType", false));
	}
	
	private Property[] groupID() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.groupNumber", false));
	}
	
	private Property[] orderFromDate() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.createFromDate_ForDisplay", false));
	}
	
	private Property[] orderToDate() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.createToDate_ForDisplay", false));
	}
	
	private Property[] residencyStatus() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.residencyStatus", false));
	}
	
	private Property[] salesLocation() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.salesLocation", false));
	}
	
	private Property[] operatorFirstName() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.firstName", false));
	}
	
	private Property[] operatorLastName() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryOrderSearchCriteria-\\d+\\.lastName", false));
	}
	
	private Property[] search() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Search");
	}
	
	private Property[] applicationOrderListTable() {
		return Property.toPropertyArray(".id", "applicationOrderList_LIST");
	}
	
	@Override
	public void setupOrderSearchCriteria(Object object) {
		//TODO
	}
	
	public void clickOrderNum(String ordNum) {
		browser.clickGuiObject(".class", "Html.A", ".text", ordNum);
	}
	
	public void selectSearchType(String type) {
		browser.selectDropdownList(searchType(), type);
	}
	
	public void setSearchValue(String value) {
		browser.setTextField(searchValue(), value);
	}
	
	public void selectApplicationStatus(String status) {
		browser.selectDropdownList(applicationStatus(), status);
	}
	
	public void setProductCode(String code) {
		browser.setTextField(productCode(), code);
	}
	
	public void setLicenseYear(String year) {
		browser.setTextField(licenseYear(), year);
	}
	
	public void selectVerificationStatus(String status) {
		browser.selectDropdownList(verificationStatus(), status);
	}
	
	public void selectChoiceType(String type) {
		browser.selectDropdownList(choiceType(), type);
	}
	
	public void setHuntName(String name) {
		browser.setTextField(huntName(), name);
	}
	
	public void selectAllowedApplicant(String applicant) {
		browser.selectDropdownList(allowedApplicant(), applicant);
	}
	
	public void setGroupID(String id) {
		browser.setTextField(groupID(), id);
	}
	
	public void setOrderFromDate(String fromDate) {
		browser.setCalendarField(orderFromDate(), fromDate);
	}
	
	public void setOrderToDate(String toDate) {
		browser.setCalendarField(orderToDate(), toDate);
	}
	
	public void selectResidencyStatus(String status) {
		browser.selectDropdownList(residencyStatus(), status);
	}
	
	public void setSalesLocation(String salesLocation) {
		browser.setTextField(salesLocation(), salesLocation);
	}
	
	public void setOperatorFirstName(String fName) {
		browser.setTextField(operatorFirstName(), fName);
	}
	
	public void setOperatorLastName(String lName) {
		browser.setTextField(operatorLastName(), lName);
	}
	
	public void clickSearch() {
		browser.clickGuiObject(search());
	}
	
	public void searchApplicationOrder(String ordNum) {
		this.selectSearchType("Order #");
		this.setSearchValue(ordNum);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
}
