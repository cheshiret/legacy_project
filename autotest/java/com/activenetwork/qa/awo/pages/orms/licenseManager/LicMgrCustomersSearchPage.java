package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Customer.CleanUpSwitch;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrCustomersSearchPage extends LicMgrCommonTopMenuPage {

	private static LicMgrCustomersSearchPage _instance = null;

	public static LicMgrCustomersSearchPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrCustomersSearchPage();
		}

		return _instance;
	}

	protected LicMgrCustomersSearchPage() {
	}

	public RegularExpression searchByRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.searchBy", false);
	public RegularExpression searchByValueRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.searchByValue", false);
	public RegularExpression receiptIDRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.receiptID", false);
	public RegularExpression orderNumberRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.orderNumber", false);
	public RegularExpression customerClassIDRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.customerClassID", false);
	public RegularExpression countyRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.county", false);
	public RegularExpression countryRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.country", false);
	public RegularExpression statusIDRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.statusID", false);
	public RegularExpression stateRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.state", false);
	public RegularExpression searchTypeStateIdRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.searchTypeStateId", false);
	public RegularExpression inventoryTypeIdRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.inventoryTypeId", false);
	public RegularExpression inventoryNumberRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.inventoryNumber", false);
	public RegularExpression includeAreaCodeRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.includeAreaCode", false);

	public RegularExpression lNameRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.lastName", false);
	public RegularExpression fNameRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.firstName", false);
	public RegularExpression mNameRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.middleName", false);
	public RegularExpression bNameRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.businessName", false);
	public RegularExpression phoneNumRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.phoneNumber", false);
	public RegularExpression physicalAddressRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.physicalAddress", false);
	public RegularExpression supplementalAddressRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.supplementalAddress", false);
	public RegularExpression cityRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.city", false);
	public RegularExpression zipCodeRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.zipCode", false);
	public RegularExpression dateOfBirthRegx = new RegularExpression(
			"HFCustomerSearchCriteria-\\d+\\.dateOfBirth_ForDisplay|_ForDisplay", false);
	public RegularExpression custNumRegx = new RegularExpression("^[0-9]+$",
			false);

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id",
				"profileList_LIST");
		// return browser.checkHtmlObjectExists(".id", searchByRegx); this page
		// mark also exist from home page
		// return checkLastTrailValueIs("Customer Search/List");
	}

	/** click 'Add Customer' button to add customer */
	public void clickAddCustomer() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Customer");
	}

	/**
	 * The method used to select Identifier/Certification/Education Type
	 * 
	 * @param licenseType
	 */
	public void selectLicenseType(String licenseType) {
		browser.selectDropdownList(".id", searchByRegx, licenseType);
	}

	public boolean isLicenseTypeExist(String licenseType) {
		List<String> options = browser.getDropdownElements(".id", searchByRegx);
		return options.contains(licenseType);
	}
	/**
	 * The method used to set Identifier/Certification/Education value
	 * 
	 * @param idNum
	 */
	public void setLicenseNum(String idNum) {
		browser.setTextField(Property.toPropertyArray(".class", "Html.INPUT.text", ".id", searchByValueRegx), idNum);
	}

	/**
	 * The method used to set receiptNum
	 * 
	 * @param receiptNum
	 */
	public void setReceiptNum(String receiptNum) {
		browser.setTextField(".id", receiptIDRegx, receiptNum);
	}

	/**
	 * The method used to set orderNum
	 * 
	 * @param orderNum
	 */
	public void setOrderNum(String orderNum) {
		browser.setTextField(".id", orderNumberRegx, orderNum);
	}

	/**
	 * The method used to select customer class
	 * 
	 * @param custClass
	 */
	public void selectCustClass(String custClass) {
		browser.selectDropdownList(".id", customerClassIDRegx, custClass, true);
	}

	public void selectCustClass(int index) {
		browser.selectDropdownList(".id", customerClassIDRegx, index, true);
		ajax.waitLoading();
	}

	/**
	 * The method used to select county
	 * 
	 * @param county
	 */
	public void selectCounty(String county) {
		browser.selectDropdownList(".id", countyRegx, county, true);
		ajax.waitLoading();
	}

	/**
	 * The method used to select county
	 * 
	 * @param index
	 */
	public void selectCounty(int index) {
		browser.selectDropdownList(".id", countyRegx, index, true);
		ajax.waitLoading();
	}

	/**
	 * The method used to select country
	 * 
	 * @param country
	 */
	public void selectCountry(String country) {
		browser.selectDropdownList(".id", countryRegx, country, true);
		ajax.waitLoading();
	}

	/**
	 * The method used to select empty country
	 * 
	 * @param country
	 */
	public void selectCountry(int index) {
		browser.selectDropdownList(".id", countryRegx, index, true);
		ajax.waitLoading();
	}

	public void selectStatus(String status) {
		browser.selectDropdownList(".id", statusIDRegx, status, true);
		ajax.waitLoading();
	}

	public void selectStatus(int index) {
		browser.selectDropdownList(".id", statusIDRegx, index, true);
	}

	public void selectState(String state) {
		browser.selectDropdownList(".id", stateRegx, state, true);
		ajax.waitLoading();
	}

	public void selectState(int index) {
		browser.selectDropdownList(".id", stateRegx, index, true);
		ajax.waitLoading();
	}

	/**
	 * The method used to select specific license type state
	 * 
	 * @param state
	 */
	public void selectLicenseTypeState(String state) {
		browser.selectDropdownList(".id", searchTypeStateIdRegx, state, true);
		ajax.waitLoading();
	}

	public void setDocNum(String docNum) {
		browser.setTextField(".id", new RegularExpression("HFCustomerSearchCriteria-\\d+\\.docNumber", false), docNum, true);
	}
	
	/**
	 * The method used to select specific license type state
	 * 
	 * @param index
	 */
	public void selectLicenseTypeState(int index) {
		browser.selectDropdownList(".id", searchTypeStateIdRegx, index, true);
		ajax.waitLoading();
	}

	/**
	 * The method used to select inventory type
	 * 
	 * @param inventoryType
	 */
	public void selectInventoryType(String inventoryType) {
		browser.selectDropdownList(".id", inventoryTypeIdRegx, inventoryType,
				true);
		ajax.waitLoading();
	}

	/**
	 * The method used to select inventory type
	 * 
	 * @param index
	 */
	public void selectInventoryType(int index) {
		browser.selectDropdownList(".id", inventoryTypeIdRegx, index, true);
		ajax.waitLoading();
	}

	/**
	 * The method used to select inventory number
	 * 
	 * @param inventoryTNum
	 */
	public void selectInventoryNum(String inventoryTNum) {
		browser.setTextField(".id", inventoryNumberRegx, inventoryTNum, true);
		ajax.waitLoading();
	}

	/**
	 * The method used to select include Area Code check box
	 * 
	 * @param includeAreaCode
	 */
	public void selectIncludeAreaCode() {
		browser.selectCheckBox(".id", includeAreaCodeRegx);
	}

	public void unSelectIncludeAreaCode() {
		browser.unSelectCheckBox(".id", includeAreaCodeRegx);
	}

	/** set last name */
	public void setLastName(String lName) {
		browser.setTextField(".id", lNameRegx, lName);
	}

	/** set first name */
	public void setFirstName(String fName) {
		browser.setTextField(".id", fNameRegx, fName);
	}

	/** set Middle name */
	public void setMiddleName(String mName) {
		browser.setTextField(".id", mNameRegx, mName);
	}

	/** set business name */
	public void setBusinessName(String bName) {
		browser.setTextField(".id", bNameRegx, bName);
	}

	/** set phone number */
	public void setPhoneNumber(String pNumber) {
		browser.setTextField(".id", phoneNumRegx, pNumber);
	}

	/** set physical address */
	public void setPhysicalAddress(String physicalAddress) {
		browser.setTextField(".id", physicalAddressRegx, physicalAddress);
	}

	/** set supplemental address */
	public void setSupplementalAddress(String supplementalAddress) {
		browser.setTextField(".id", supplementalAddressRegx,
				supplementalAddress);
	}

	/** set city */
	public void setCity(String city) {
		browser.setTextField(".id", cityRegx, city);
	}

	/** set zip */
	public void setZip(String zip) {
		browser.setTextField(".id", zipCodeRegx, zip);
	}

	/** set date of birth */
	public void setDateOfBirth(String dateOfBirth) {
		browser.setTextField(".id", dateOfBirthRegx, dateOfBirth);
	}

	/** Get date of birth */
	public String getDateOfBirth() {
		return browser.getTextFieldValue(".id", dateOfBirthRegx);
	}

	public List<String> getCustomerClassValue() {
		return browser.getDropdownElements(".id", customerClassIDRegx);
	}

	public List<String> getSearchTypeValue() {
		return browser.getDropdownElements(".id", searchByRegx);
	}

	public String getCustomerClassDefaultValue() {
		return browser.getDropdownListValue(".id", customerClassIDRegx);
	}

	/** click search button to do search customer */
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("^Search$", false));
	}

	public void clickCustomerFirstNumer() {
		browser.clickGuiObject(".class", "Html.A", ".text", custNumRegx, true,
				0);
	}
	
	public void clickCustomerByName(String lName, String fName) {
		IHtmlObject objs[] = this.getCustomerProfileListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int colIndex = table.findColumn(0, "Customer / Business Name");
		int rowIndex = table.findRow(colIndex, new RegularExpression(lName + ", " + fName, false));
		String id = table.getCellValue(rowIndex, 0);
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}

	public void selectFirstCustomer() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Select", true, 0);
	}

	public void clickCustomerNumber(String customerNumber) {
		browser.clickGuiObject(".class", "Html.A", ".text", customerNumber,
				true);
	}

	/** click privilege tab */
	public void clickPrivilegesTab() {
		Property property[] = new Property[3];
		property[0] = new Property(".class", "Html.A");
		property[1] = new Property(".text", new RegularExpression("(Privileges|Licences)",
				false));
		property[2] = new Property(".href", "#/SearchPrivilege");

		/*
		 * property[2] = new Property(".id", new RegularExpression(
		 * "CustomerMgrTabs_\\d+", false));
		 */
		/*
		 * HtmlObject[] objs = browser.getHtmlObject(property);
		 * System.out.println(objs.length);
		 */
		browser.clickGuiObject(property);
		ajax.waitLoading();
	}

	public void searchCustomer(String lName, String fName) {
		this.setFirstName(fName);
		this.setLastName(lName);
		this.selectState(0);
		this.selectCountry(0);
		this.clickSearch();
		ajax.waitLoading();
	}
	
	public void searchCustByBusinessName(String businessName){
		this.setBusinessName(businessName);
		this.clickSearch();
		ajax.waitLoading();
	}
	
	public void searchCustByDOB(String dateofBirth){
		this.setDateOfBirth(dateofBirth);
		this.clickSearch();
		ajax.waitLoading();
	}

	/**
	 * The method used to search customer by Identifier/Certification/Education
	 * 
	 * @param identifierType
	 *            -Identifier/Certification/Education type
	 * @param identifierNum
	 *            -Identifier/Certification/Education value
	 * @param customerClass
	 *            -organization/individual
	 */
	public void searchCustomerByIdnetifier(String identifierType,
			String identifierNum, String customerClass) {
		this.clearDefaultCountryValue();
		this.selectCustClass(customerClass);
		ajax.waitLoading();
		this.selectLicenseType(identifierType);
		ajax.waitLoading();
		this.setLicenseNum(identifierNum);
		this.selectStatus(0);
		this.clickSearch();
		ajax.waitLoading();
	}

	private void clearDefaultCountryValue(){
		this.selectCountry(0);
		ajax.waitLoading();
	}
	
	public void searchCust(Customer cust, boolean gotoCustDetails) {
		setSearchCriteria(cust);
		clickSearch();
		ajax.waitLoading();
		if(!gotoCustDetails){
			this.waitLoading();
		}
	}

	public void searchCust(Customer cust) {
		searchCust(cust, false);
	}
	
	public void setSearchCriteria(Customer cust) {
		if (cust.licenseType.length() < 1
				&& cust.identifier.identifierType.length() > 0) {
			cust.licenseType = cust.identifier.identifierType;
		}
		if (cust.licenseNum.length() < 1
				&& cust.identifier.identifierNum.length() > 0) {
			cust.licenseNum = cust.identifier.identifierNum;
		}
		if (null != cust.licenseType && cust.licenseType.length() > 0) {
			this.selectLicenseType(cust.licenseType);
			ajax.waitLoading();
		}
		if (null != cust.licenseNum && cust.licenseNum.length() > 0) {
			this.setLicenseNum(cust.licenseNum);
		}
		if (null != cust.licenseState && cust.licenseState.length() > 0) {
			this.selectLicenseTypeState(cust.licenseState);
		}
		if(StringUtil.notEmpty(cust.documentNum)){
			this.setDocNum(cust.documentNum);
		}
		if (null != cust.inventoryType && cust.inventoryType.length() > 0) {
			this.selectInventoryType(cust.inventoryType);
		}
		if (null != cust.inventoryNum && cust.inventoryNum.length() > 0) {
			this.selectInventoryNum(cust.inventoryNum);
		}
		if (null != cust.customerClass && cust.customerClass.length() > 0) {
			this.selectCustClass(cust.customerClass);
			ajax.waitLoading();
		}
		if (null != cust.status && cust.status.length() > 0) {
			this.selectStatus(cust.status);
		}
		if (null != cust.receiptNum && cust.receiptNum.length() > 0) {
			this.setReceiptNum(cust.receiptNum);
		}
		if (null != cust.orderNum && cust.orderNum.length() > 0) {
			this.setOrderNum(cust.orderNum);
		}
		if (null != cust.lName && cust.lName.length() > 0) {
			this.setLastName(cust.lName);
		}
		if (null != cust.fName && cust.fName.length() > 0) {
			this.setFirstName(cust.fName);
		}
		if (null != cust.mName && cust.mName.length() > 0) {
			this.setMiddleName(cust.mName);
		}
		if (null != cust.businessName && cust.businessName.length() > 0) {
			this.setBusinessName(cust.businessName);
		}
		if (null != cust.dateOfBirth && cust.dateOfBirth.length() > 0) {
			this.setDateOfBirth(cust.dateOfBirth);
		}

		if (cust.includeAreaCode) {
			this.selectIncludeAreaCode();
		} else {
			this.unSelectIncludeAreaCode();
		}

		if (null != cust.hPhone && cust.hPhone.length() > 0) {
			this.setPhoneNumber(cust.hPhone);
		} else if (null != cust.bPhone && cust.bPhone.length() > 0) {
			this.setPhoneNumber(cust.bPhone);
		} else {
			this.setPhoneNumber(cust.mPhone);
		}
		
//		this.selectIncludeAreaCode();

		if (null != cust.address && cust.address.length() > 0) {
			this.setPhysicalAddress(cust.address);
		}
		if (null != cust.supplementalAddress
				&& cust.supplementalAddress.length() > 0) {
			this.setSupplementalAddress(cust.supplementalAddress);
		}
		if (null != cust.city && cust.city.length() > 0) {
			this.setCity(cust.city);
		}
		this.selectCountry(0);//select blank to clear default value 
		if (null != cust.country && cust.country.length() > 0) {
			this.selectCountry(cust.country);
			ajax.waitLoading();
		}
		this.selectState(0);//select blank to clear default value 
		if (null != cust.state && cust.state.length() > 0) {
			this.selectState(cust.state);
			ajax.waitLoading();
		}
		if (null != cust.county && cust.county.length() > 0) {
			this.selectCounty(cust.county);
			ajax.waitLoading();
		}
		if (null != cust.zip && cust.zip.length() > 0) {
			this.setZip(cust.zip);
		}
	}

	public void clearUpSearchCriteria(CleanUpSwitch clearupList) {
		if (clearupList.isCleanLicenseType) {
			this.selectLicenseType("MDWFP #");
			ajax.waitLoading();
		}
		if (clearupList.isCleanLicenseNumber) {
			this.setLicenseNum("");
		}
		if (clearupList.isCleanLicenseTypeState) {
			this.selectLicenseTypeState(0);
		}
		if (clearupList.isCleanInventoryType) {
			this.selectInventoryType(0);
		}
		if (clearupList.isCleanInventoryNum) {
			this.selectInventoryNum("");
		}
		if (clearupList.isCleanCustClass) {
			this.selectCustClass("Individual");
			ajax.waitLoading();
		}
		if (clearupList.isCleanStatus) {
			this.selectStatus("Active");
		}
		if (clearupList.isCleanReceiptNum) {
			this.setReceiptNum("");
		}
		if (clearupList.isCleanOrderNum) {
			this.setOrderNum("");
		}
		if (clearupList.isCleanLastName) {
			this.setLastName("");
		}
		if (clearupList.isCleanFirstName) {
			this.setFirstName("");
		}
		if (clearupList.isCleanMiddleName) {
			this.setMiddleName("");
		}
		if (clearupList.isCleanBusinessName) {
			this.setBusinessName("");
		}
		if (clearupList.isCleanDateOfBirth) {
			this.setDateOfBirth("");
		}
		if (clearupList.isCleanPhoneNumber) {
			this.setPhoneNumber("");
		}
		if (clearupList.isCleanIncludeAreaCode) {
			this.selectIncludeAreaCode();
		}
		if (clearupList.isCleanPhysicalAddress) {
			this.setPhysicalAddress("");
		}
		if (clearupList.isCleanSupplementalAddress) {
			this.setSupplementalAddress("");
		}
		if (clearupList.isCleanCity) {
			this.setCity("");
		}
		if (clearupList.isCleanCounty) {
			this.selectCounty(0);
		}
		if (clearupList.isCleanState) {
			this.selectState(0);
		}
		if (clearupList.isCleanZip) {
			this.setZip("");
		}
		if (clearupList.isCleanCountry) {
			this.selectCountry(0);
		}
	}

	public IHtmlObject[] getCustomerProfileListTable() {
		return browser.getHtmlObject(".class", "Html.TABLE", ".id",
				"profileList_LIST");
	}

	/**
	 * Get customer table row number and default number is 1
	 * 
	 * @return
	 */
	public int getCusttableRowNum() {
		IHtmlObject[] objs = this.getCustomerProfileListTable();
		int row = -1;
		if (objs.length > 0) {
			IHtmlTable table = (IHtmlTable) objs[0];
			row = table.rowCount();
		} else {
			throw new ObjectNotFoundException("Object can't find.");
		}
		
		Browser.unregister(objs);
		return row;
	}

	/**
	 * Get warning message
	 * 
	 * @return
	 */
	public String getWarnMes() {
		String warnMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if (objs.length > 0) {
			warnMes = objs[0].getProperty(".text");
		}// else throw new ObjectNotFoundException("Object can't find.");

		Browser.unregister(objs);
		return warnMes;
	}

	/**
	 * If "Next" button is available, click next button in customer search page.
	 */
	public boolean gotoNext() {
		IHtmlObject[] top = browser.getTableTestObject(".id", new RegularExpression("pagingbar_\\d+", false));
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				new RegularExpression("Next", false),top[0]);

		boolean toReturn = false;
		if (objs.length > 0 ) {
			toReturn = true;
			objs[0].click();
			ajax.waitLoading();
		}
		Browser.unregister(objs);
		Browser.unregister(top);
		ajax.waitLoading();
		return toReturn;
	}

	/**
	 * Retrieve customer info in customer list page
	 * 
	 * @return
	 */
	public List<List<String>> getAllCustomersinfoInCustList() {
		List<List<String>> customerinfo = new ArrayList<List<String>>();
		List<String> customerinforow = new ArrayList<String>();
		do {
			IHtmlObject[] customertable = this.getCustomerProfileListTable();
			IHtmlTable cusTableGrid = (IHtmlTable) customertable[0];

			if (cusTableGrid.rowCount() > 1) {
				for (int row = 1; row < cusTableGrid.rowCount(); row++) {
					customerinforow.clear();
					for (int col = 0; col < cusTableGrid.columnCount(); col++) {
						customerinforow
								.add(cusTableGrid.getCellValue(row, col));
					}
					customerinfo.add(customerinforow);
				}
				Browser.unregister(customertable);
			}
			// else throw new
			// ErrorOnDataException("No customer info is retrived!");
		} while (this.gotoNext());

		return customerinfo;
	}

	/**
	 * get column number
	 * 
	 * @param colName
	 * @return
	 */
	public int getColNumIndex(String colName) {
		int colNum = 0;
		IHtmlObject[] customertable = this.getCustomerProfileListTable();
		if (customertable.length > 0) {
			IHtmlTable cusTableGrid = (IHtmlTable) customertable[0];
			colNum = cusTableGrid.findColumn(0, colName);
		} else
			throw new ObjectNotFoundException("Object can't find.");

		return colNum;
	}

	public List<String> getColValue(String colName) {
		IHtmlObject[] customertable = this.getCustomerProfileListTable();
		List<String> values = null;
		if (customertable.length > 0) {
			IHtmlTable cusTableGrid = (IHtmlTable) customertable[0];
			int colNum = cusTableGrid.findColumn(0, colName);
			values = cusTableGrid.getColumnValues(colNum);

		} else
			throw new ObjectNotFoundException("Object can't find.");

		return values;
	}

	/**
	 * Verify customer profile searching result for specific search criteria
	 * 
	 * @param customerinfo
	 *            : List<List<String>>
	 * @param customerinforow
	 *            : List<String>
	 * @param searchCriteria
	 * @param colName
	 */
	public void verifySearchCustomerProfileResult(
			List<List<String>> customerinfo, String searchCriteria,
			String colName) {
		List<String> customerinforow = new ArrayList<String>();
		int columnIndex = 0;
		if (null != searchCriteria && searchCriteria.length() > 0) {
			columnIndex = this.getColNumIndex(colName);
			if (customerinfo.size() >= 1) {
				for (int i = 0; i < customerinfo.size(); i++) {
					customerinforow = customerinfo.get(i);
					// The colName is changed.
					if (colName.equals("Last Name")
							|| colName.equals("First Name")
							|| colName.equals("Middle")
							|| colName.equals("Business Name")
							|| colName
									.equalsIgnoreCase("Customer / Business Name")
							|| colName.equals("Address")
							|| colName.equals("Supp Address")
							|| colName.equals("Phone")
							|| colName.equals("ZIP/Postal")) {
						if (colName.equals("Phone")) {
							searchCriteria.replaceAll("[^0-9]+", "");
						}
						if (!(customerinforow.get(columnIndex).trim()
								.toLowerCase()).contains(searchCriteria.trim()
								.toLowerCase())) {
							throw new ItemNotFoundException(
									customerinforow.get(columnIndex)
											+ " doesn't match search criteria:"
											+ searchCriteria);
						}
					} else {
						if (!customerinforow.get(columnIndex).toString()
								.equals(searchCriteria)) {
							throw new ItemNotFoundException(
									customerinforow.get(columnIndex)
											+ " doesn't match search criteria:"
											+ searchCriteria);
						}
					}
				}
			} else {
				throw new ItemNotFoundException("No matched " + colName
						+ " found");
			}
		}
	}

	/**
	 * verify searched customer information in the customer search page match
	 * the search criteria
	 * 
	 * @param cust
	 **/
	public void verifySearchCustomerProfileResult(Customer cust) {
		List<List<String>> customerinfo = new ArrayList<List<String>>();
		customerinfo.clear();
		customerinfo = this.getAllCustomersinfoInCustList();

		// if(null!=cust.licenseNum && cust.licenseNum.length()>0){
		// this.verifySearchCustomerProfileResult(customerinfo, cust.licenseNum,
		// "MDWFP #");//Customer #
		// }
		if (null != cust.status && cust.status.length() > 0) {
			this.verifySearchCustomerProfileResult(customerinfo, cust.status,
					"Status");// Status
		}

		/*
		 * if(cust.customerClass.equals("Individual")){ String nameCombin =
		 * cust.lName+", "+cust.fName; if (nameCombin.equals(", ")) { nameCombin
		 * = cust.mName; } else { nameCombin +=" "+cust.mName; }
		 * 
		 * if(null!=nameCombin && nameCombin.length()>3){
		 * this.verifySearchCustomerProfileResult
		 * (customerinfo,nameCombin,"Customer / Business Name"); }else{
		 * if(null!=cust.businessName && cust.businessName.length()>0) {
		 * this.verifySearchCustomerProfileResult
		 * (customerinfo,cust.businessName,"Customer / Business Name"); } } }
		 */
		if (null != cust.lName && cust.lName.length() > 0) {
			this.verifySearchCustomerProfileResult(customerinfo, cust.lName,
					"Customer / Business Name");// Last Name
		}
		if (null != cust.fName && cust.fName.length() > 0) {
			this.verifySearchCustomerProfileResult(customerinfo, cust.fName,
					"Customer / Business Name");// First Name
		}
		if (null != cust.mName && cust.mName.length() > 0) {
			this.verifySearchCustomerProfileResult(customerinfo, cust.mName,
					"Customer / Business Name");// Middle Name
		}
		if (null != cust.businessName && cust.businessName.length() > 0) {
			this.verifySearchCustomerProfileResult(customerinfo,
					cust.businessName, "Customer / Business Name");// Business
																	// name
		}
		if (null != cust.dateOfBirth && cust.dateOfBirth.length() > 0) {
			this.verifySearchCustomerProfileResult(customerinfo,
					cust.dateOfBirth, "Date Of Birth");// dateOfBirth
		}
		if (null != cust.hPhone && cust.hPhone.length() > 0) {
			this.verifySearchCustomerProfileResult(customerinfo, cust.hPhone,
					"Phone");// Phone Number
		} else if (null != cust.hPhone && cust.hPhone.length() > 0) {
			this.verifySearchCustomerProfileResult(customerinfo, cust.bPhone,
					"Phone");// Business Number
		} else if (null != cust.hPhone && cust.hPhone.length() > 0) {
			this.verifySearchCustomerProfileResult(customerinfo, cust.mPhone,
					"Phone");// Mobile Number
		}
		if (null != cust.address && cust.address.length() > 0) {
			this.verifySearchCustomerProfileResult(customerinfo, cust.address,
					"Address");// Address
		}
		if (null != cust.supplementalAddress
				&& cust.supplementalAddress.length() > 0) {
			this.verifySearchCustomerProfileResult(customerinfo,
					cust.supplementalAddress, "Supp Address");// Supp Address
		}
		if (null != cust.city && cust.city.length() > 0) {
			this.verifySearchCustomerProfileResult(customerinfo, cust.city,
					"City/Town");// City/Town
		}
		if (null != cust.state && cust.state.length() > 0) {
			this.verifySearchCustomerProfileResult(customerinfo, cust.state,
					"State");// State/Province
		}
		if (null != cust.zip && cust.zip.length() > 0) {
			this.verifySearchCustomerProfileResult(customerinfo, cust.zip,
					"ZIP/Postal");// Zip/Postal
		}
		if (null != cust.country && cust.country.length() > 0) {
			this.verifySearchCustomerProfileResult(customerinfo, cust.country,
					"Country");// Country
		}
	}

	public void refreshPage() {
		browser.clickGuiObject(".class", "Html.FORM", ".id", "e_Form");
	}

	/**
	 * Verify given customer number in the search List
	 * 
	 * @param custNum
	 * @param inCustList
	 *            --true: given customer should be in customer list --false:
	 *            given customer shouldn't be in customer list
	 */
	public void verifyKnownCustNumInSearchList(String custNum,
			boolean inCustList) {
		logger.info("Verify Given Customer " + custNum + " In Search List.");
		boolean found = false;
		do {
			IHtmlObject[] objs = this.getCustomerProfileListTable();
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			for (int i = 1; i < tableGrid.rowCount(); i++) {
				if (tableGrid.getCellValue(i, 0).toString().equals(custNum)) {
					found = true;
					break;
				}
			}
			Browser.unregister(objs);
		} while (found == false && gotoNext());

		if (inCustList && !found) {
			throw new ItemNotFoundException("Customer with number = " + custNum
					+ " should be in customer list ...");
		} else if (!inCustList && found) {
			throw new ItemNotFoundException("Customer with number = " + custNum
					+ " shouldn't be in customer list ...");
		} else {
			logger.info("Found Customer " + custNum + " Successful.");
		}
	}

	/**
	 * if invaild date is change into an vaild date or null string, Return true;
	 * or return false
	 * 
	 * @param invaildDates
	 *            : an array of invaild dates
	 * @param index
	 * @param propertys
	 */
	protected boolean isInvaildDateParsedProperlyByDateComponent(
			String[] invaildDates, int index, Property... propertys) {
		for (int i = 0; i < invaildDates.length; i++) {
//			browser.setTextField(propertys, invaildDates[i], true, index);
			setDateAndGetMessage((IText)browser.getTextField(propertys)[index], invaildDates[i]);
			String valForChangableDate = browser.getTextFieldValue(propertys,
					index);

			if (!DateFunctions.isValidDate(valForChangableDate)
					&& valForChangableDate.trim().length() > 0) {
				logger.info("Failed to parse Invaild date:" + invaildDates[i]);
				return false;
			}

		}
		return true;
	}

	public void verifyOnDateOfBirth(String[] invalidDates, String expectedMsg) {
		Property pro[] = new Property[2];
		pro[0] = new Property(".class", "Html.INPUT.text");
		pro[1] = new Property(".id", this.dateOfBirthRegx);
		boolean result = this.isInvaildDateParsedProperlyByDateComponent(
				invalidDates, 0, pro);
		if (!result) {
			this.clickSearch();
			ajax.waitLoading();
			if (!this.getWarnMes().trim().equals(expectedMsg))
				throw new ErrorOnDataException("Date of birth is invalidate.");
		}
	}

	public void verifyErrorMessWhenSelectEmptyCustomerClass(String expectMsg) {
		List<String> custClassOptions = new ArrayList<String>();
		custClassOptions = browser.getDropdownElements(".id",
				this.customerClassIDRegx);
		for (int i = 0; i < custClassOptions.size(); i++) {
			if (custClassOptions.get(i) == null
					|| custClassOptions.get(i).length() < 0) {
				this.selectCustClass(0);
				String actualMsg = this.getWarnMes();
				if (!actualMsg.equals(expectMsg)) {
					throw new ErrorOnDataException(
							"The actual error message: '" + actualMsg
									+ "' is not match the expect message: '"
									+ expectMsg + "'");
				}
			}
		}
	}

	/**
	 * Check the specific customer in customer list
	 * 
	 * @param custNum
	 * @param inList
	 *            --true: the specific customer should be in customer list
	 *            --false: the specific customer shouldn't be in customer list
	 */
	public void checkCustomerInCustomerList(String custNum, boolean inList) {
		List<List<String>> customerinfo = new ArrayList<List<String>>();
		int count = 0;
		customerinfo.clear();
		customerinfo = this.getAllCustomersinfoInCustList();
		if (customerinfo.size() > 0) {
			if (inList) {
				for (int i = 0; i < customerinfo.size(); i++) {
					if (custNum.contains(customerinfo.get(i).get(0))) {
						count++;
					}
				}
			} else {
				for (int i = 0; i < customerinfo.size(); i++) {
					if (!custNum.contains(customerinfo.get(i).get(0))) {
						count++;
					}
				}
			}
			if (count != 1) {
				throw new ErrorOnDataException(
						"Failed check customer in customer list!");
			}

		} else
			throw new ObjectNotFoundException("No customer info is found!");

	}

	public boolean checkWarMesExist() {
		return browser.checkHtmlObjectExists(".id", "NOTSET");
	}
	
	/**
	 * Get warning message
	 * 
	 * @return warning message
	 */
	public String getErrorMsg() {
		String warnMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if (objs.length > 0) {
			warnMes = objs[0].getProperty(".text");
		} else {
			throw new com.activenetwork.qa.testapi.ObjectNotFoundException("Object can't find.");
		}

		Browser.unregister(objs);
		return warnMes;
	}
}
