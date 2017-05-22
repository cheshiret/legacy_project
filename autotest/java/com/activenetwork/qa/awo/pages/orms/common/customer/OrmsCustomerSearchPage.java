/*
 * Created on Apr 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.customer;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author jdu
 *
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsCustomerSearchPage extends OrmsPage {

	/**
	 * Script Name : OrmsCustSchPage Generated : Oct 26, 2004 5:14:11 PM
	 * Original Host : WinNT Version 5.1 Build 2600 (Service Pack 2)
	 *
	 * @since 2004/10/26
	 */

	// private Vector allCusts;

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsCustomerSearchPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsCustomerSearchPage() {
		// allCusts = new Vector();
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsCustomerSearchPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsCustomerSearchPage();
		}

		return _instance;
	}
	
	protected Property[] cellPhoneTextFieldProp(){
		return Property.concatPropertyArray(input("text"), ".id","cellphone");
	}
	
	protected Property[] workPhoneTextFieldProp(){
		return Property.concatPropertyArray(input("text"), ".id","workphone");
	}
	
	protected Property[] phonePreferenceDropDownPro(){
		return Property.concatPropertyArray(input(".select"), ".id","PhoneContactPref");
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Find Existing Customer");
	}

	/** unselect "IncludeAreaCode" textfield */
	public void deSelectIncludeAreaCode() {
		browser.unSelectCheckBox(".id", "phoneIncludeareAcode");
	}

	/** Enter data into the "First Name" textfield */
	public void setFirstName(String firstName) {
		browser.setTextField(".id", "firstname", firstName);
	}

	/** Enter data into the "Last Name" textfield */
	public void setLastName(String lastName) {
		browser.setTextField(".id", "lastname", lastName);
	}

	public void setPhone(String phone) {
		setPhone(phone,true);
	}
	/** Enter data into the "Phone" textfield */
	public void setPhone(String phone, boolean includeAreaCode) {
		browser.setTextField(".id", "phone", phone);
		if(includeAreaCode) {
			selectIncludeAreaCode();
		} else {
			deSelectIncludeAreaCode();
		}
	}

	public void selectIncludeAreaCode() {
		browser.selectCheckBox(".id","phoneIncludeareAcode");
	}

	/** Enter data into the "Zip" textfield */
	public void setZip(String zip) {
		browser.setTextField(".id", "zip", zip);
	}

	/** Enter data into the "Email" textfield */
	public void setEmail(String email) {
		browser.setTextField(".id", "email", email);
	}

	/** Enter data into the "Organization Name" textfield */
	public void setOrgName(String orgname) {
		browser.setTextField(".id", "custOrgName", orgname);
	}

	/** Click on the "Find Existing Customer" button */
	public void clickFindExistingCust() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Find Existing Customer", true);
	}

	/** Click on the "Add New Customer" button */
	public void clickAddNewCust() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New Customer",
				true);
	}

	/**
	 * Wait until the search results show up
	 *
	 * @throws ItemNotFoundException
	 */
	public void searchWaitExists() throws ItemNotFoundException {this.waitLoading();
		browser.searchObjectWaitExists(".class", "Html.A", ".text", "Select");
	}

	/**
	 * Set the 'Email' field and click on 'Find Existing Customer' button
	 *
	 * @param email
	 */
	public void searchCust(String email) {
		setEmail(email);
		clickFindExistingCust();
	}

	public void searchCust(String fName, String lName) {
		this.setFirstName(fName);
		this.setLastName(lName);
		clickFindExistingCust();

	}

	public IHtmlObject[] getCustomerTable(){
		return browser.getHtmlObject(".class", "Html.TABLE", ".text",
				new RegularExpression("Last Name First Name Middle Organization.*", false));
	}

	/**
	 * Set the "First Name" and "Last Name" fields using the passed-in
	 * parameters, then click on the "Find Existing Customer" button
	 *
	 * @param firstName
	 * @param lastName
	 * @param email
	 *            - this parameter cannot be empty
	 */
	public void searchCust(String firstName, String lastName, String email) {
		if (email == null || email.length() < 1) {
			// we require email account is mandatory for this method
			// because we will use alt_xxxxx@reserveamerica.com to search for
			// customer
			throw new ItemNotFoundException(
					"An email account is mandatory for searching a customer.");
		}
		searchCust(firstName, lastName, "", "", email, "", true);
	}

	public void setAddress(String address){
		browser.setTextField(".id", "address", address);
	}
	
	public void setCity(String city){
		browser.setTextField(".id", "city", city);
	}

	public void setState(String state){
		if(StringUtil.isEmpty(state)){
			browser.selectDropdownList(".id", "state", 0);// select blank
		} else {
			browser.selectDropdownList(".id", "state", state);	
		}
	}

	public void setCountry(String country){
		if(StringUtil.isEmpty(country)){
			browser.selectDropdownList(".id", "country", 0);// select blank
		} else {
			browser.selectDropdownList(".id", "country", country);
		}
	}
	
	public void setCustType(String custType){
		if(StringUtil.isEmpty(custType)){
			browser.selectDropdownList(".id", "ui.customer_type", "All");// select All option
		} else {
			browser.selectDropdownList(".id", "ui.customer_type", custType);
		}
	}
	
	public void setCustPass(String custPass){
		if(StringUtil.isEmpty(custPass)){
			browser.selectDropdownList(".id", "ui.customer_pass", "All");// select All option
		} else {
			browser.selectDropdownList(".id", "ui.customer_passe", custPass);
		}
	}
	
	public void setOrgType(String orgType){
		if(StringUtil.isEmpty(orgType)){
			browser.selectDropdownList("id", "custOrgType", "All");// select All option
		} else {
			browser.selectDropdownList("id", "custOrgType", orgType);
		}
	}
	
	public void setPlateNum(String plate){
		browser.setTextField("id", "vehiclePlateNumber", plate);
	}
	
	public void setMake(String make){
		if(StringUtil.isEmpty(make)){
			browser.selectDropdownList(".id", "vehicleMake", 0);
		} else {
			browser.selectDropdownList(".id", "vehicleMake", make);
		}
	}
	
	public void setModel(String model){
		browser.setTextField("id", "vehicleModel", model);
	}
	
	public void setColor(String color){
		if(StringUtil.isEmpty(color)){
			browser.selectDropdownList(".id", "vehicleColor", 0);
		} else {
			browser.selectDropdownList(".id", "vehicleColor", color);
		}
	}
	
	public void setCampingUnit(String campingUnit){
		if(StringUtil.isEmpty(campingUnit)){
			browser.selectDropdownList(".id", "campingUnit", 0);
		} else {
			browser.selectDropdownList(".id", "campingUnit", campingUnit);
		}
	}
	
	public void setPetType(String petType){
		if(StringUtil.isEmpty(petType)){
			browser.selectDropdownList(".id", "pet", 0);
		} else {
			browser.selectDropdownList(".id", "pet", petType);
		}
	}
	
	public void clickAdvaced(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Advanced Search");
	}
	
	
	public void setSearchCriteria(Customer cust){
		if(cust.isAdvancedSearch){
			this.clickAdvaced();
			ajax.waitLoading();
			this.waitLoading();
		}
		// basic search info
		if(!StringUtil.isEmpty(cust.lName)){
			setLastName(cust.lName);
		}
		
		if(!StringUtil.isEmpty(cust.fName)){
			setFirstName(cust.fName);
		}
		
		if(!StringUtil.isEmpty(cust.hPhone)){
			setPhone(cust.hPhone);
		} else if(!StringUtil.isEmpty(cust.mPhone)){
			setPhone(cust.mPhone);
		} else if(!StringUtil.isEmpty(cust.bPhone)){
			setPhone(cust.bPhone);
		}

		if(!StringUtil.isEmpty(cust.zip)){
			setZip(cust.zip);
		}
		
		if(!StringUtil.isEmpty(cust.email)){
			setEmail(cust.email);
		}
		
		if(!StringUtil.isEmpty(cust.organization)){
			setOrgName(cust.organization);
		}
		
		setOrgType(cust.organizationType);
		
		if(!StringUtil.isEmpty(cust.hPhone) || !StringUtil.isEmpty(cust.mPhone) || !StringUtil.isEmpty(cust.bPhone)){
			if (cust.areacode == false) {
				deSelectIncludeAreaCode();
			} else {
				if(!StringUtil.isEmpty(cust.hPhone)){
					selectIncludeAreaCode();
				}
			}
		}
		
		setCustType(cust.searchCustType);
		setCustPass(cust.searchCustPass);
		
		// Address info
		if(!StringUtil.isEmpty(cust.address)){
			setAddress(cust.address);
		}

		if(!StringUtil.isEmpty(cust.city)){
			setCity(cust.city);
		}

		setState(cust.state);
		setCountry(cust.country);
		
		// vehicle info
		if(null != cust.searchVehicle){
			String text = cust.searchVehicle.getPlateNum();
			if(!StringUtil.isEmpty(text)){
				setPlateNum(text);
			}
			
			text = cust.searchVehicle.getMake();
			if(!StringUtil.isEmpty(text)){
				setMake(text);
			}

			text = cust.searchVehicle.getModel();
			if(!StringUtil.isEmpty(text)){
				setModel(text);
			}
			
			text = cust.searchVehicle.getColor();
			if(!StringUtil.isEmpty(text)){
				setColor(text);
			}
		}
		
		// camping unit
		setCampingUnit(cust.searchCampingUnit);
		
		// pet
		setPetType(cust.searchPetType);
	}

	/**
	 * Setup search criteria for searching a customer
	 *
	 * @param firstName
	 * @param lastName
	 * @param phone
	 * @param zip
	 * @param email
	 * @param orgname
	 * @param areacode
	 */
	public void setSearchCriteria(String firstName, String lastName,
			String phone, String zip, String email, String orgname,
			boolean areacode) {
		if (lastName != null && lastName.length() > 0) {
			setLastName(lastName);
		}

		if (firstName != null && firstName.length() > 0) {
			setFirstName(firstName);
		}

		if (phone != null && phone.length() > 0) {
			setPhone(phone);
		}

		if (zip != null && zip.length() > 0) {
			setZip(zip);

		}
		if (email != null && email.length() > 0) {
			setEmail(email);

		}

		if (orgname != null && orgname.length() > 0) {
			setOrgName(orgname);

		}
		if (phone != null && phone.length() > 0 && areacode == false) {
			deSelectIncludeAreaCode();
		}
	}
	
	/**
	 * Set each search field using the passed-in parameters, then click on
	 * the "Find Existing Customer" button
	 *
	 * @param cust
	 */
	public void searchCust(Customer cust) {
		setSearchCriteria(cust);
		clickFindExistingCust();
		this.waitLoading();
	}
	
	/**
	 * Set the "First Name", "Last Name", "Phone","Zip", "Email" and
	 * "Organization Name" fields using the passed-in parameters, then click on
	 * the "Find Existing Customer" button
	 *
	 * @param firstName
	 * @param lastName
	 * @param phone
	 * @param zip
	 * @param email
	 * @param orgname
	 * @param areacode
	 */
	public void searchCust(String firstName, String lastName, String phone,
			String zip, String email, String orgname, boolean areacode) {

		setSearchCriteria(firstName, lastName, phone, zip, email, orgname,
				areacode);
		clickFindExistingCust();
		this.waitLoading();
	}


	/**
	 * In the customer search result list, click on the customer last name
	 * specified by the parameter
	 *
	 * @return
	 */
	public void gotoCustDetail(String selectLastName)
			throws PageNotFoundException {
		browser.clickGuiObject(".class", "Html.A", ".text", selectLastName,
				true);
	}
	
	public int getCustomerIndex(String createdBy) {
		IHtmlObject[] tables=this.getCustomerTable();
		if(tables==null || tables.length<1){
			throw new ObjectNotFoundException("Can't find customer table");
		}
		IHtmlTable table;
		table=(IHtmlTable)tables[0];
		
		int row=table.findRow(10, createdBy);
		Browser.unregister(tables);
		return row;
	}
	
	
	public void selectCust(String lName, String createdBy) throws ItemNotFoundException {
		int index = 0;
		if(!StringUtil.isEmpty(createdBy)){
			index = this.getCustomerIndex(createdBy)-1;
		}
		selectCust(lName,index);
	}
	
	/**
	 * On the customer search results page, click on the "Select" button for the
	 * specified last name
	 */
	public void selectCust(String lName, int index) throws ItemNotFoundException {
		if (StringUtil.isEmpty(lName)) {
			logger.warn("Customer name is not provided. select the 1st customer.");
			browser.clickGuiObject(".class","Html.A",".text", "Select");
		} else {
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
					lName);

			String href = objs[index].getProperty(".href").toString().replaceAll(
					"\\%20", "");
			String custId = RegularExpression.getMatches(href, "[0-9]+")[0];
			Browser.unregister(objs);

			RegularExpression reg = new RegularExpression(".*\"" + custId + "\".*",
					false);
			IHtmlObject selectObjs[] = browser.getHtmlObject(".text", "Select", ".href", reg);
			if(selectObjs.length > 0) {
				selectObjs[0].click();
			} else {
				browser.clickGuiObject(".class", "Html.A", ".text", "Select");
			}
			Browser.unregister(selectObjs);
		}
	}

	/**
	 * On the customer search results page, click on the "Select" button for the
	 * specified last name
	 */
	public void selectCust(String lName) throws ItemNotFoundException {
//		this.selectCust(lName, "RA");
		this.selectCust(lName,0);
	}

	public void selectCustToCustDetailPg(String lName) {
		browser.clickGuiObject(".class", "Html.A", ".text", lName,0);
	}

	public void selectCustToCustDetailPg(String lName, String createdBy) {
		int index = this.getCustomerIndex(createdBy)-1;
		browser.clickGuiObject(".class", "Html.A", ".text", lName, index);
	}
	/**
	 * Get searched customer cell value
	 *
	 * @param row
	 * @param col
	 * @return
	 */
	public String getSearchResultCellValue(int row, int col) {
		IHtmlObject[] objs = this.getCustomerTable();
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find customer table object.");
		}
		String toReturn = ((IHtmlTable) objs[objs.length - 1]).getCellValue(row, col)
				.toString();
		Browser.unregister(objs);
		return toReturn;

	}

	/**
	 * If "Next" button is avaliable, click next button in customer search page.
	 *
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Next");
//				new RegularExpression("Next", false));//update due to regular expression would click 'Find Next Available' on home page

		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}
		Browser.unregister(objs);

		this.waitLoading();

		return toReturn;
	}

	/**
	 * Retrieve customer info
	 *
	 * @return
	 */
	public List<List<String>> retriveCustomerinfo() {
		List<List<String>> customerinfo = new ArrayList<List<String>>();
		List<String> customerinforow = new ArrayList<String>();
		do {
			IHtmlObject[] customertable = this.getCustomerTable();
			IHtmlTable cusTableGrid = (IHtmlTable) customertable[customertable.length-1];

			for (int row = 1; row < cusTableGrid.rowCount(); row++) {
				customerinforow = cusTableGrid.getRowValues(row);
				customerinfo.add(customerinforow);
			}
			Browser.unregister(customertable);
		} while (gotoNext());

		return customerinfo;
	}

	/**
	 * Get table col count
	 *
	 * @return
	 */
	public int getTableColCount() {
		IHtmlObject[] customertable = this.getCustomerTable();

		IHtmlTable cusTableGrid = (IHtmlTable) customertable[0];
		int colCount = cusTableGrid.columnCount();

		Browser.unregister(customertable);
		return colCount;
	}

	/**
	 * Get table row count
	 *
	 * @return
	 */
	public int getTableRowCount() {
		IHtmlObject[] objs = this.getCustomerTable();
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find customer table object.");
		}
		IHtmlTable cusTableGrid = (IHtmlTable)objs[0];
		int rowCount = cusTableGrid.rowCount();

		Browser.unregister(objs);
		return rowCount;
	}

	/**
	 * Get col num by col name
	 *
	 * @param colName
	 * @return
	 */
	public int getColNum(String colName) {
		IHtmlObject[] objs = this.getCustomerTable();
		if(objs.length>0){
			IHtmlTable table = (IHtmlTable)objs[objs.length-1];
			int colcount = getTableColCount();
			for (int col = 0; col < colcount; col++) {
				if (table.getCellValue(0, col).toString().trim().equalsIgnoreCase(
						colName)) {
					Browser.unregister(objs);
					return col;
				}
			}
		}else throw new ObjectNotFoundException("Customer table can't find.");


		return -1;
	}

	/**
	 * Get the customer's last name by email
	 *
	 * @param mail
	 *            - customer email
	 * @return lastName - customer last name
	 */
	public String getCustLNameByEmail(String mail) {
		IHtmlObject[] customertable = this.getCustomerTable();
		IHtmlTable cusTableGrid = (IHtmlTable) customertable[0];

		String lastName = "";
		int lastNameCol = 0;
		int emailCol = 0;
		for (int col = 0; col < cusTableGrid.columnCount(); col++) {
			String cell = cusTableGrid.getCellValue(0, col);
			if (null != cell && cell.equalsIgnoreCase("Last Name")) {
				lastNameCol = col;
			}
			if (null != cell && cell.equalsIgnoreCase("Email")) {
				emailCol = col;
			}
		}

		for (int row = 1; row < cusTableGrid.rowCount(); row++) {
			if (cusTableGrid.getCellValue(row, emailCol).equalsIgnoreCase(mail)) {
				if (cusTableGrid.getCellValue(0, lastNameCol).equalsIgnoreCase(
						"Last Name")) {
					lastName = cusTableGrid.getCellValue(row, lastNameCol);
					break;
				}
			}
		}
		Browser.unregister(customertable);
		return lastName;
	}
	
	public String getCustomerPhoneByEmail(String email){
		IHtmlObject[] objs = this.getCustomerTable();
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find customer table.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(7, email);
		
		if(row < 0){
			throw new ItemNotFoundException("Can't find phone info with email:"+email);
		}
		String value = table.getCellValue(row, 5);
		Browser.unregister(objs);
		return value;
	}
	
	/**
	 * Get column value by column name.
	 * @param columnName
	 * @return
	 */
	public List<String> getColunmValueByName(String columnName){
		IHtmlObject[] objs = this.getCustomerTable();
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find customer table.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int col = table.findColumn(0, columnName);
		if(col < 0){
			throw new ItemNotFoundException("Can't find column with name:"+columnName);
		}
		List<String> list = table.getColumnValues(col);
		list.remove(0);
		return list;
	}
	
	public String getMessage(){
		IHtmlObject objs[] = browser.getHtmlObject(".id", "customer.search.notfound");
		String msg = "";
		if(objs.length > 0) {
			msg = objs[0].text().trim();
		}
		
		Browser.unregister(objs);
		return msg;
	}

	public boolean customerExist(Customer cust) {
		boolean exist = false;
		this.searchCust(cust);
		IHtmlObject[] objs = this.getCustomerTable();
		IHtmlTable cusTableGrid = (IHtmlTable) objs[0];
		if(cusTableGrid.rowCount() > 1){
			exist = true;
		}
		Browser.unregister(objs);
		return exist;
	}
	
}
