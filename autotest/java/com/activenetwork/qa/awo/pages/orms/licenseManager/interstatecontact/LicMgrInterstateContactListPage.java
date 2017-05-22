/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.interstatecontact;

import java.util.Arrays;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InterstateContactInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  May 24, 2012
 */



public class LicMgrInterstateContactListPage extends LicMgrCommonTopMenuPage {
	private static LicMgrInterstateContactListPage _instance = null;
	
	public static LicMgrInterstateContactListPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrInterstateContactListPage();
		}

		return _instance;
	}

	protected LicMgrInterstateContactListPage() {

	}
	@Override
	public boolean exists() {
		return (browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("InterStateContactSearchCriteria-\\d+\\.spId", false)));
		}
	
	

	public void selectState(String state)	{
		browser.selectDropdownList(".class", "Html.SELECT", ".id", new RegularExpression("InterStateContactSearchCriteria-\\d+\\.spId", false), state);
	
	}
	
	public void selectStatus(String status)	{
		browser.selectDropdownList(".class", "Html.SELECT", ".id", new RegularExpression("InterStateContactSearchCriteria-\\d+\\.active", false), status);		
	
	}
	
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public void clickAddInterstateContact(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Interstate Contact");
	}
	
	public void clickContactByID(String id)
	{
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	public void searchInterstateContact(String state, String status){
		
		this.selectState(state);
		this.selectStatus(status);
		this.clickSearch();
		ajax.waitLoading();
		
	}
	
	public InterstateContactInfo getFirstRecodeOfInterstateContact() {
		InterstateContactInfo contactInfo = new InterstateContactInfo();

		int row = 1;// we only get first record in this method.
		
		IHtmlObject[] objs = browser.getTableTestObject(".id", "iscvGrid_LIST");		
		IHtmlTable contactListTable = (IHtmlTable) objs[0];	
		
		
		contactInfo.setId(contactListTable.getCellValue(row, contactListTable.findColumn(0, "ID")));
		contactInfo.setStatus(contactListTable.getCellValue(row, contactListTable.findColumn(0, "Status")));
		contactInfo.setState(contactListTable.getCellValue(row, contactListTable.findColumn(0, "Interstate State")));
		
		String email = contactListTable.getCellValue(row, contactListTable.findColumn(0, "Contact Email"));
		if(email!=null)
		{
			String[] emails = email.split("\\s");
			contactInfo.setEmails(Arrays.asList(emails));
		}
	
		IHtmlObject[] creationAndModifyDetails = getCreateAndModifyDetails(row-1);//sub table in TBODY, so index begin with 0;
		
		if(creationAndModifyDetails.length>0) //table[0] and table[1] contains info of "Creation Details"
		{
			IHtmlTable creationDetailsTable = (IHtmlTable)creationAndModifyDetails[0];//"Creation Details"
			contactInfo.setCreateDate(creationDetailsTable.getCellValue(0, 0));
			contactInfo.setCreateUser(creationDetailsTable.getCellValue(1, 0));
		}
		
		if(creationAndModifyDetails.length>=3)//table[2] and table[3] contains info of "Creation Details"
		{
			IHtmlTable ModifyDetailsTable = (IHtmlTable)creationAndModifyDetails[2];//"Last Modified Details"
			contactInfo.setLastModifyDate(ModifyDetailsTable.getCellValue(0, 0));
			contactInfo.setLastModifyUser(ModifyDetailsTable.getCellValue(1, 0));
		}
		
		Browser.unregister(creationAndModifyDetails);
		Browser.unregister(objs);
		
		return contactInfo;
	}
	
	
	/**
	 * @param state
	 * @param active
	 */
	public InterstateContactInfo searchAndGetFirstRecodeOfInterstateContact(String state, String status) {
		InterstateContactInfo contactInfo = new InterstateContactInfo();
		this.selectState(state);
		this.selectStatus(status);
		this.clickSearch();
		ajax.waitLoading();
		
		int row = 1;// we only get first record in this method.
		
		IHtmlObject[] objs = browser.getTableTestObject(".id", "iscvGrid_LIST");		
		IHtmlTable contactListTable = (IHtmlTable) objs[0];	
		
		
		contactInfo.setId(contactListTable.getCellValue(row, contactListTable.findColumn(0, "ID")));
		contactInfo.setStatus(contactListTable.getCellValue(row, contactListTable.findColumn(0, "Status")));
		contactInfo.setState(contactListTable.getCellValue(row, contactListTable.findColumn(0, "Interstate State")));
		
		String email = contactListTable.getCellValue(row, contactListTable.findColumn(0, "Contact Email"));
		if(email!=null)
		{
			String[] emails = email.split("\\s");
			contactInfo.setEmails(Arrays.asList(emails));
		}
	
		IHtmlObject[] creationAndModifyDetails = getCreateAndModifyDetails(row-1);//sub table in TBODY, so index begin with 0;
		
		if(creationAndModifyDetails.length>0) //table[0] and table[1] contains info of "Creation Details"
		{
			IHtmlTable creationDetailsTable = (IHtmlTable)creationAndModifyDetails[0];//"Creation Details"
			contactInfo.setCreateDate(creationDetailsTable.getCellValue(0, 0));
			contactInfo.setCreateUser(creationDetailsTable.getCellValue(1, 0));
		}
		
		if(creationAndModifyDetails.length>=3)//table[2] and table[3] contains info of "Creation Details"
		{
			IHtmlTable ModifyDetailsTable = (IHtmlTable)creationAndModifyDetails[2];//"Last Modified Details"
			contactInfo.setLastModifyDate(ModifyDetailsTable.getCellValue(0, 0));
			contactInfo.setLastModifyUser(ModifyDetailsTable.getCellValue(1, 0));
		}
		
		Browser.unregister(creationAndModifyDetails);
		Browser.unregister(objs);
		
		return contactInfo;
		
	}
	
	public IHtmlObject[] getCreateAndModifyDetails(int row){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "iscvGrid_LIST");		
		IHtmlObject[] tbody = browser.getHtmlObject(".class", "Html.TBODY",objs[0]);
		IHtmlObject[] TR = browser.getHtmlObject(".class", "Html.TR",tbody[0]);

		//get "Creation Details" and "Last Modified Details"
		Property[] p = {new Property(".text", new RegularExpression("[a-zA-Z]{1,3}+\\s+[a-zA-Z]{1,3}+\\s+\\d{1,2}+\\s+\\d{1,4}+\\s+\\d{1,2}:\\d{1,2}+\\s+(AM|PM)", false))};//Sun May 27 2012 10:23 PM
		IHtmlObject[] subTable = browser.getTableTestObject(p,TR[row]);
		
		Browser.unregister(objs);
		Browser.unregister(tbody);
		Browser.unregister(TR);
		return subTable;
	}
	
	

	
}
