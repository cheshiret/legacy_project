package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ApplicationStatusCheck;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrVendorAddVendorPage extends LicMgrCommonTopMenuPage{
	private static LicMgrVendorAddVendorPage instance=null;
	private String prefixReg1 = "^ContactView-[0-9]*.";

	private LicMgrVendorAddVendorPage(){
	}

	public static LicMgrVendorAddVendorPage getInstance() {
		if(instance ==null) {
			instance = new LicMgrVendorAddVendorPage();
		}
		return instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A",".text","Add Contact");
	}

	/**set application received date*/
	public void setApplicationReceivedDate(String date){
		browser.setTextField(".id", new RegularExpression("VendorView-\\d+\\.vendorApp.appReceivedDate_ForDisplay",false), date, 0);
	}

	public void loseFocusFromAppReceivedDateField() {
		browser.clickGuiObject(".class", "Html.TD", ".text", "Application Info");
	}

	/**get application received date*/
	public String getApplicationReceivedDate(){
		return browser.getTextFieldValue(".id", new RegularExpression("VendorView-\\d+\\.vendorApp.appReceivedDate_ForDisplay",false));
	}

	/**set application name*/
	public void setApplicationName(String name){
		browser.setTextField(".id", new RegularExpression("VendorView-\\d+\\.vendorApp.applicantName",false), name, 0);
	}

	/**set application phone*/
	public void setApplicationPhone(String phoneNum){
		browser.setTextField(".id", new RegularExpression("VendorView-\\d+\\.vendorApp.applicantPhone",false), phoneNum, 0);
	}

	/**set application email*/
	public void setApplicationEmail(String appEmail){
		browser.setTextField(".id", new RegularExpression("VendorView-\\d+\\.vendorApp.applicantEmail",false), appEmail, 0);
	}

	/**set # of stores Requeseted*/
	public void setNumOfSotreRequeseted(String storeNum){
		browser.setTextField(".id", new RegularExpression("VendorView-\\d+\\.vendorApp.numOfStoresRequested:ZERO_TO_NULL",false), storeNum, 0);
	}

	/**set equipment per store requested*/
	public void setEquipmentRequeseted(String equi){
		browser.setTextField(".id", new RegularExpression("VendorView-\\d+\\.vendorApp.equipPerStoreRequested:ZERO_TO_NULL",false), equi, 0);
	}

	/**set vendor number*/
	public void setVendorNumber(String vendorNum){
		browser.setTextField(".id", new RegularExpression("VendorView-\\d+\\.vendorNum",false), vendorNum, 0);
	}

	/**set vendor number*/
	public void setVendorName(String vendorName){
		browser.setTextField(".id", new RegularExpression("VendorView-\\d+\\.vendorName",false), vendorName, 0);
	}

	/**set owner name*/
	public void setOwnerName(String ovnerName){
		browser.setTextField(".id", new RegularExpression("VendorView-\\d+\\.ownerName",false), ovnerName, 0);
	}

	/**select vendor type*/
	public void selectVendorType(String vendorType){
		browser.selectDropdownList(".id", new RegularExpression("VendorView-\\d+\\.type",false), vendorType);
	}

	public void setTaxID(String taxID){
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5036\\]",false), taxID);
	}

	public void selectTaxIDType(String taxIDType){
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5037\\]",false), taxIDType);
	}

	/**set physical address*/
	public void setPhysicalAddress(String add){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.address",false), add, 0);
	}

	public void setPhysicalSuppAddress(String add){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.supplemental",false), add, 0);
	}

	/**set physical city town*/
	public void setPhysicalCityTown(String cityTown){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.city",false), cityTown, 0);

	}

	/**select physical state province*/
	public void selectPhysicalStateProvince(String stateProvince){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.state",false), stateProvince,0);
	}

	public void deSelectPhyState(){
		browser.selectDropdownList(".id",new RegularExpression("AddressView-\\d+\\.state", false), 0, 0);
	}

	/**select physical county*/
	public void selectPhysicalCounty(String county){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.county",false), county);
	}

	public void deSelectPhyCounty(){
		browser.selectDropdownList(".id",new RegularExpression("AddressView-\\d+\\.county", false), 0,0);
	}

	/**set physical zip postal*/
	public void setPhysicalZip(String zip){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.zipCode",false), zip, 0);
	}

	/**select physical country*/
	public void selectPhysicalCountry(String country){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.country",false), country, 0);
	}

	public void deSelectPhyCountry(){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.country",false), 0, 0);
	}

	/**set mailing address*/
	public void setMailingAdd(String add){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.address",false), add, 1);
	}

	public void setMailingSuppAddress(String add){
		browser.setTextField(".id",  new RegularExpression("AddressView-\\d+\\.supplemental",false), add,1);
	}

	/**set mailing city town*/
	public void setMailingCityTown(String city){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.city",false), city, 1);
	}

	/**select mailing state province*/
	public void selectMailingStateProvince(String state){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.state",false), state,1);
	}

	public void deSelectMailingState(){
		browser.selectDropdownList(".id",new RegularExpression("AddressView-\\d+\\.state", false), 0,1);
	}

	/**select mailing county*/
	public void selectMailingCounty(String county){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.county",false), county,1);
	}

	public void deSelectMailingCounty(){
		browser.selectDropdownList(".id",new RegularExpression("AddressView-\\d+\\.county", false), 0,1);
	}

	/**set mailing Zip postal*/
	public void setMailingZip(String zip){
		logger.info("set mailling zip: " + zip );
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.zipCode",false), zip, 1);
	}

	/**select mailing country*/
	public void selectMailingCountry(String country){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.country",false), country,1);
	}

	public void deSelectMailingCountry(){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.country",false), 0,1);
	}

	/**select contact type*/
	public void selectContactType(String type, int index){
		browser.selectDropdownList(".id", new RegularExpression("ContactView-\\d+\\.contactType",false), type,index);
	}

	public boolean isContactRemoveButtonOfIndexExist(int index){
		boolean isExist;
		IHtmlObject[] objs = browser.getHtmlObject(".id", "contact_table");
		if(objs.length<1){
			throw new ItemNotFoundException("Contact table object not found.");
		}

		Property[] p = new Property[2];
		p[0] = new Property(".class","Html.A");
		p[1] = new Property(".text","Remove");
		IHtmlObject[] removeObjs = browser.getHtmlObject(p, objs[0]);

		if(index < removeObjs.length){
			isExist = true;
		}else {
			isExist = false;
		}

		Browser.unregister(removeObjs);
		Browser.unregister(objs);
		return isExist;
	}

	/**
	 *
	 * @param i
	 * @return
	 */
	public String getContactType(int i){
		return browser.getDropdownListValue(".id",new RegularExpression(prefixReg1+"contactType", false),i);
	}

	/**
	 *
	 * @param i
	 * @return
	 */
	public boolean getPrimary(int i){
		IHtmlObject objs[] = browser.getRadioButton(".id",new RegularExpression(prefixReg1+"primary", false));

		if(objs.length < 1) {
			throw new ObjectNotFoundException("There is no radio button which id = prefixReg1+"+"primary");
		}

		if(objs.length-1<i){
			throw new ObjectNotFoundException("The num of radio button you search is less than "+i);
		}

		boolean selected = ((IRadioButton)objs[i]).isSelected();

		Browser.unregister(objs);
		return selected;
	}

	public String getContactFirstName(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"firstName", false),i);
	}

	public String getContactMidName(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"middleName", false),i);
	}

	public String getContactLastName(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"lastName", false),i);
	}

	public String getContactSuffix(int i){
		return browser.getDropdownListValue(".id",new RegularExpression(prefixReg1+"suffix", false),i);
	}

	public String getBusinessPhone(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"businessPhone", false),i);
	}

	public String getHomePhone(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"homePhone", false),i);
	}

	public String getMobilePhone(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"mobilePhone", false),i);
	}

	public String getFax(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"fax", false),i);
	}

	public String getEmail(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"email", false),i);
	}

	public String getWebsite(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"website", false),i);
	}

	public List<Contacts> getContactsInfo(){
		List<Contacts> list = new ArrayList<Contacts>();
		IHtmlObject objs[] = browser.getRadioButton(".id", new RegularExpression(prefixReg1+"primary", false));

		if(objs.length < 1) {
			throw new ObjectNotFoundException("There is no contract id = prefixReg1"+"primary");
		}
		int num = objs.length;

		for(int i=0;i<num;i++){
			Contacts contact = new Contacts();
			contact.contactType=getContactType(i);
			contact.isPrimary=getPrimary(i);
			contact.firstName=getContactFirstName(i);
			contact.midName=getContactMidName(i);
			contact.lastName=getContactLastName(i);
			contact.suffix=getContactSuffix(i);
			contact.businessPhone=getBusinessPhone(i);
			contact.homePhone=getHomePhone(i);
			contact.mobilePhone=getMobilePhone(i);
			contact.fax=getFax(i);
			contact.email=getEmail(i);
			contact.website=getWebsite(i);

			list.add(contact);
		}

		Browser.unregister(objs);
		return list;
	}

	public int getContactIndex(Contacts contact){
		List<Contacts> contactList = this.getContactsInfo();
		int index = -1;
		for(int i=0; i<contactList.size();i++){
			if(contactList.get(i).contactType.equals(contact.contactType) &&
					String.valueOf(contactList.get(i).isPrimary).equals(String.valueOf(contact.isPrimary)) &&
					contactList.get(i).firstName.equals(contact.firstName) &&
					contactList.get(i).midName.equals(contact.midName) &&
					contactList.get(i).lastName.equals(contact.lastName) &&
					contactList.get(i).suffix.equals(contact.suffix) &&
					contactList.get(i).businessPhone.equals(contact.businessPhone) &&
					contactList.get(i).homePhone.equals(contact.homePhone) &&
					contactList.get(i).mobilePhone.equals(contact.mobilePhone) &&
					contactList.get(i).fax.equals(contact.fax) &&
					contactList.get(i).email.equals(contact.email) &&
					contactList.get(i).website.equals(contact.website)){
				index = i;
				break;
			}
		}

		return index;
	}

	public List<String> getNotificationEmail(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("EmailDynamicTableView-\\d+\\.emailAddress",false));

		if(objs.length<1){
			throw new ObjectNotFoundException("Notifiction object not found.");
		}
		List<String> notificationEmails = new ArrayList<String>();
		for(int i=0; i<objs.length; i++){
			String email = browser.getTextFieldValue(".id", new RegularExpression("EmailDynamicTableView-\\d+\\.emailAddress",false), i);
			notificationEmails.add(email);
		}
		Browser.unregister(objs);
		return notificationEmails;
	}

	public int getNotificationEmailIndex(String notificationEmail){
		List<String> emials = this.getNotificationEmail();
		int index = -1;
		for(int i=0; i<emials.size();i++){
			if(emials.get(i).equals(notificationEmail)){
				index = i;
				break;
			}
		}
		return index;
	}

	public void removeContactInfoByIndex(int index){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "contact_table");
		if(objs.length<1){
			throw new ItemNotFoundException("Contact table object not found.");
		}

		Property[] p = new Property[2];
		p[0] = new Property(".class","Html.A");
		p[1] = new Property(".text","Remove");
		browser.clickGuiObject(p, true, index, objs[0]);
		Browser.unregister(objs);
	}

	public void selectPrimary(int index){
		browser.selectRadioButton(".id", new RegularExpression("ContactView-\\d+\\.primary",false), index);
	}

	/**set contact first name*/
	public void setContactFirstName(String fname,int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.firstName",false), fname,index);
	}

	public void setContactMiddleName(String midName,int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.middleName",false), midName, index);
	}

	/**set the contact last name*/
	public void setContactLastName(String lname,int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.lastName",false), lname,index);
	}

	public void selectContactSuffix(String suffix, int index){
		browser.selectDropdownList(".id", new RegularExpression("ContactView-\\d+\\.suffix",false), suffix,index);
	}

	/**set business phone*/
	public void setContactBusinessPhone(String phone,int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.businessPhone",false), phone,index);
	}

	public void setContactHomePhone(String phone, int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.homePhone",false), phone, index);
	}

	public void setContactMobilePhone(String phone, int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.mobilePhone",false), phone, index);
	}

	/**get the vendor types*/
	public List<String> getVendorTypes(){
		return browser.getDropdownElements(".id",new RegularExpression("VendorView-\\d+\\.type",false));
	}

	/**uncheck the mailing address checkbox*/
	public void uncheckMailingAdd(){
		browser.unSelectCheckBox(".id", new RegularExpression("AddressView-\\d+\\.sameAddress",false));
	}

	/**check the mailing address checkbox*/
	public void checkMaillingAdd(){
		browser.selectCheckBox(".id", new RegularExpression("AddressView-\\d+\\.sameAddress",false));
	}

	/**set contract fax*/
	public void setContactFax(String fax,int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.fax",false),fax,index);
	}

	/**set contact email*/
	public void setContactEmail(String email, int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.email",false),email,index);
	}

	public void setContactWebsite(String website, int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.website",false), website, index);
	}

	public void selectSpecifyDefaults(String specifyDefault){
		browser.selectDropdownList(".id", new RegularExpression("VendorFinancialConfigView-\\d+\\.defaultFinConfigID",false),specifyDefault);
	}

	public void clickFillValues(){
		browser.clickGuiObject(".class", "Html.A",".text","Fill Values");
	}

	public void selectEFTType(String eftType){
		browser.selectDropdownList(".id", new RegularExpression("VendorFinancialConfigView-\\d+\\.eftType",false), eftType);
	}

	public void selectEFTSchedule(String eftSchedule){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Invoice Schedule.*",false));
		browser.selectDropdownList(".id", new RegularExpression("DropdownExt-\\d+.selectedValue",false), eftSchedule, false, objs[0]);
		Browser.unregister(objs);
	}
	
	public void selectEFTInvoiceFrequency(String invFre){
		/*HtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Invoice Frequency.*",false));
		browser.selectDropdownList(".id", new RegularExpression("DropdownExt-\\d+.selectedValue",false), invFre, false, objs[0]);
		ajax.waitLoading();
		Browser.unregister(objs);*/
		browser.selectDropdownList(".id", new RegularExpression("VendorFinancialConfigView-\\d+\\.eftFrequency",false), invFre);
	}

	public void selectFailedEFTEnforcement(String failedEFTEnforcement){
		browser.selectDropdownList(".id", new RegularExpression("VendorFinancialConfigView-\\d+\\.eftFailureEnforcement",false), failedEFTEnforcement);
	}
	
	public void unSelectFailedEFTEnforcement(){
		browser.selectDropdownList(".id", new RegularExpression("VendorFinancialConfigView-\\d+\\.eftFailureEnforcement",false), 0);
	}

	public void setVoidReturnChargeDays(String days){
		browser.setTextField(".id", new RegularExpression("VendorFinancialConfigView-\\d+\\.voidRtnChargeDays",false), days);
	}

	public void selectAutoReturnVoidedDocuments(String value){
		browser.selectDropdownList(".id", new RegularExpression("VendorFinancialConfigView-\\d+\\.rtnVoidedDoc",false), value);
	}

	public String getReportNotificationCheckBoxIDValue(String notification){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", notification);

		if(objs.length<1){
			throw new ItemNotFoundException("Notification '" + notification + "' SPAN object is nt found.");
		}

		Property[] p = new Property[1];
		p[0] = new Property(".class","Html.INPUT.checkbox");

		IHtmlObject[] checkBoxObjs = browser.getHtmlObject(p, objs[0]);
		if(checkBoxObjs.length<1){
			throw new ItemNotFoundException("Notification " + notification + " check box object isn't found.");
		}

		String value = checkBoxObjs[0].getProperty(".id");
		Browser.unregister(checkBoxObjs);
		Browser.unregister(objs);
		return value;
	}

	public void selectReportNotification(String notification){
		String value = this.getReportNotificationCheckBoxIDValue(notification);
		browser.selectCheckBox(".id", value);
	}

	public void unSelectReportNotification(String notification){
		String value = this.getReportNotificationCheckBoxIDValue(notification);
		browser.unSelectCheckBox(".id", value);
	}

	public void setReportNotification(HashMap<String,Boolean> reportNotification){
		for(Entry<String, Boolean> e: reportNotification.entrySet()){
			if(e.getValue()){
				this.selectReportNotification(e.getKey());
			}else {
				this.unSelectReportNotification(e.getKey());
			}
		}
	}

	public void clickAddReportNotificationEmail(){
		browser.clickGuiObject(".class", "Html.A",".text","Add");
	}

	public boolean isReportNotificationRemoveButtonOfIndexExist(int index){
		boolean isExist;
		IHtmlObject[] objs = browser.getHtmlObject(".id", "finconfig_email_table");
		if(objs.length<1){
			throw new ItemNotFoundException("Report notificaion email table object not found.");
		}

		Property[] p = new Property[2];
		p[0] = new Property(".class","Html.A");
		p[1] = new Property(".text","Remove");
		IHtmlObject[] removeObjs = browser.getHtmlObject(p, objs[0]);

		if(index < removeObjs.length){
			isExist = true;
		}else {
			isExist = false;
		}

		Browser.unregister(removeObjs);
		Browser.unregister(objs);
		return isExist;
	}

	public void removeReportNotificationEmialInfoByIndex(int index){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "finconfig_email_table");
		if(objs.length<1){
			throw new ItemNotFoundException("Report notificaion email table object not found.");
		}

		Property[] p = new Property[2];
		p[0] = new Property(".class","Html.A");
		p[1] = new Property(".text","Remove");
		browser.clickGuiObject(p, true, index, objs[0]);
		Browser.unregister(objs);
	}

	public void setReportNotificationEmailByIndex(String email, int index){
		browser.setTextField(".id", new RegularExpression("EmailDynamicTableView-\\d+\\.emailAddress",false), email, index);
	}

	public IHtmlObject[] getTRObjectByStatusCheck(String status){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR",".text",
				new RegularExpression(".*" + status + ".*",false));

		if(objs.length<1){
			throw new ItemNotFoundException(status+ " Status Check Row object not found.");
		}

		return objs;
	}

	public int getAppStatusCheckTRObjectIndexByStatusCheck(String status){
		int row = -1;
		IHtmlObject[] objs = browser.getTableTestObject(".id","appChecksTable");
		if(objs.length<1){
			throw new ItemNotFoundException("Application checks table object not found.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		row = table.findRow(1, new RegularExpression(".*" + status + ".*",false));
		Browser.unregister(objs);
		return row;
	}

	public void selectByPassCheckByStatus(String status){
		int index = this.getAppStatusCheckTRObjectIndexByStatusCheck(status);

		browser.selectCheckBox(".id",
				new RegularExpression("VendorApplicationCheckStatusView-\\d+\\.bypassCheck", false), index);

	}

	public void unSelectByPassCheckByStatus(String status){
		int index = this.getAppStatusCheckTRObjectIndexByStatusCheck(status);

		browser.unSelectCheckBox(".id",
				new RegularExpression("VendorApplicationCheckStatusView-\\d+\\.bypassCheck", false), index);
	}

	public void setDateCompletedByStatus(String status, String dateCompleted){
		int index = this.getAppStatusCheckTRObjectIndexByStatusCheck(status);

		browser.setTextField(".id",
				new RegularExpression("VendorApplicationCheckStatusView-\\d+\\.completedDate_ForDisplay", false), dateCompleted,index);
	}

	public void setCompletedByStatus(String status,String completedBy){
		int index = this.getAppStatusCheckTRObjectIndexByStatusCheck(status);

		browser.setTextField(".id",
				new RegularExpression("VendorApplicationCheckStatusView-\\d+\\.completedBy", false), completedBy, index);
	}

	public void setCommentsByStatus(String status,String comments){
		int index = this.getAppStatusCheckTRObjectIndexByStatusCheck(status);

		browser.setTextArea(".id",
				new RegularExpression("VendorApplicationCheckStatusView-\\d+\\.completedBy", false), comments, true,index);
	}

	/**click ok button*/
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A",".text","OK");
	}

	/**click cancel button*/
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A",".text","Cancel");
	}

	/**click apply button*/
	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A",".text","Apply");
		ajax.waitLoading();
	}

	/**click save button*/
	public void clickSave(){
		browser.clickGuiObject(".class","Html.A",".text","Save");
		ajax.waitLoading();
	}

	/**click add Contract*/
	public void clickAddContract(){
		browser.clickGuiObject(".class", "Html.A",".text","Add Contact");
	}

	/**click remove button to remove the contacts*/
	public void clickRemoveContact(int index){
		browser.clickGuiObject(".class", "Html.A",".text","Remove",index);
		ajax.waitLoading();
	}

	public void clickValidateOfPhysicalAdd(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate", 0);
	}

	public void clickValidateOfMailingAdd(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate", 1);
	}

	/**set all specified vendor information to add a vendor except the mailing address*/
	public void setSpecifiedVendorInfoWithoutMailing(VendorInfo vendor){
		this.setApplicationReceivedDate(vendor.appReceivedDate);
		this.setApplicationName(vendor.appName);
		this.setApplicationPhone(vendor.appPhone);
		this.setApplicationEmail(vendor.appEmail);
		this.setNumOfSotreRequeseted(vendor.requestStoreNum);
		this.setEquipmentRequeseted(vendor.requestStoreEquipmentNum);
		this.setVendorNumber(vendor.number);
		this.setVendorName(vendor.name);
		this.setOwnerName(vendor.ownerName);
		this.selectVendorType(vendor.vendorType);
		this.setPhysicalAddress(vendor.phyAddress);
		this.setPhysicalCityTown(vendor.phyCityTown);

		if(null ==vendor.phyCountry || vendor.phyCountry.length()==0){
			this.selectPhysicalCountry(vendor.phyCountry);
			ajax.waitLoading();
			this.setPhysicalZip(vendor.phyZipPostal);
		}else if(null == vendor.phyStateProvince || vendor.phyStateProvince.length() ==0){
			this.selectPhysicalCountry(vendor.phyCountry);
			ajax.waitLoading();
			this.selectPhysicalStateProvince(vendor.phyStateProvince);
			ajax.waitLoading();
			this.setPhysicalZip(vendor.phyZipPostal);
		}else if(null == vendor.phyCounty || vendor.phyCounty.length()==0){
			this.selectPhysicalCountry(vendor.phyCountry);
			ajax.waitLoading();
			this.selectPhysicalStateProvince(vendor.phyStateProvince);
			ajax.waitLoading();
			this.selectPhysicalCounty(vendor.phyCounty);
			ajax.waitLoading();
			this.setPhysicalZip(vendor.phyZipPostal);
		} else{
			this.selectPhysicalCountry(vendor.phyCountry);
			ajax.waitLoading();
			this.selectPhysicalStateProvince(vendor.phyStateProvince);
			ajax.waitLoading();
			this.selectPhysicalCounty(vendor.phyCounty);
			ajax.waitLoading();
			this.setPhysicalZip(vendor.phyZipPostal);
		}

//		this.selectContactType(vendor.contactType,);
//		this.setContactFirstName(vendor.contactFirstName);
//		this.setContactLastName(vendor.contactLastName);
//		this.setContactBusinessPhone(vendor.contactBusinessPhone);
		if(vendor.contactType.length()==0 || vendor.contactType==null){
			this.selectContactType(vendor.contactType,0);
		}
		this.addContractInfo(vendor, 0, false);
	}

	/**set all specified vendor information to add a vendor with the mailing address*/
	public void setSpecifiedVendorInfoWithMailing(VendorInfo vendor){
		this.setApplicationReceivedDate(vendor.appReceivedDate);
		this.setApplicationName(vendor.appName);
		this.setApplicationPhone(vendor.appPhone);
		this.setApplicationEmail(vendor.appEmail);
		this.setNumOfSotreRequeseted(vendor.requestStoreNum);
		this.setEquipmentRequeseted(vendor.requestStoreEquipmentNum);
		this.setVendorNumber(vendor.number);
		this.setVendorName(vendor.name);
		this.setOwnerName(vendor.ownerName);
		this.selectVendorType(vendor.vendorType);
		this.setPhysicalAddress(vendor.phyAddress);
		this.setPhysicalCityTown(vendor.phyCityTown);
		this.selectPhysicalStateProvince(vendor.phyStateProvince);
		ajax.waitLoading();
		this.selectPhysicalCounty(vendor.phyCounty);
		ajax.waitLoading();
		this.setPhysicalZip(vendor.phyZipPostal);
		this.selectPhysicalCountry(vendor.phyCountry);
		ajax.waitLoading();

		this.uncheckMailingAdd();
		ajax.waitLoading();

		this.setMailingAdd(vendor.mailingAddress);
		this.setMailingCityTown(vendor.mailingCityTown);

		if( null == vendor.mailingCountry || vendor.mailingCountry.length()==0){
			this.selectMailingCountry(vendor.mailingCountry);
			ajax.waitLoading();
			this.setMailingZip(vendor.mailingZipPostal);
		}else if(null == vendor.mailingStateProvince || vendor.mailingStateProvince.length() == 0){
			this.selectMailingCountry(vendor.mailingCountry);
			ajax.waitLoading();
			this.selectMailingStateProvince(vendor.mailingStateProvince);
			ajax.waitLoading();
			this.setMailingZip(vendor.mailingZipPostal);
		}else if(null == vendor.mailingCounty || vendor.mailingCounty.length() ==0){
			this.selectMailingCountry(vendor.mailingCountry);
			ajax.waitLoading();
			this.selectMailingStateProvince(vendor.mailingStateProvince);
			ajax.waitLoading();
			this.selectMailingCounty(vendor.mailingCounty);
			ajax.waitLoading();
			this.setMailingZip(vendor.mailingZipPostal);
		}else{
			this.selectMailingCountry(vendor.mailingCountry);
			ajax.waitLoading();
			this.selectMailingStateProvince(vendor.mailingStateProvince);
			ajax.waitLoading();
			this.selectMailingCounty(vendor.mailingCounty);
			ajax.waitLoading();
			this.setMailingZip(vendor.mailingZipPostal);
		}

		this.addContractInfo(vendor, 0, false);
	}

	/**add a vender without mailing address*/
	public void addVendorWithoutMailingAdd(VendorInfo vendor){
		this.setSpecifiedVendorInfoWithoutMailing(vendor);
		this.clickOK();
	}

	/**add a vender with mailing address*/
	public void addVendorWithMailingAdd(VendorInfo vendor){
		this.setSpecifiedVendorInfoWithMailing(vendor);
		this.clickOK();
	}

	public void setupVendorInfo(VendorInfo vendor){
		//setup application info
		this.setupVendorAppInfo(vendor);
		//setup vendor info
		this.setupVendorBasicInfo(vendor);

		//setup physical address info
		this.setupVendorPhyAddressInfo(vendor);

		//setup mailing address info
		this.setupVendorMailingAddressInfo(vendor);

		//setup contact info
		this.setupVendorContactInfo(vendor.contacts, vendor.removedContacts);

		if(isETFAvailable()){
			this.setupVendorEFTInfo(vendor);
		}

		if(this.isApplicationStatusChecksExist()){
			//set application status check info
			this.setupVendorAppStatusCheckInfo(vendor.applicationStatusCheck);
		}
	}

	public boolean isETFAvailable() {
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".id","ConsumableProductsPanelList");
	}

	public boolean isApplicationStatusChecksExist(){
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".id", "appChecksTable");
	}

	public void setupVendorAppInfo(VendorInfo vendor){
		this.setApplicationReceivedDate(vendor.appReceivedDate);
		this.loseFocusFromAppReceivedDateField();
		this.setApplicationName(vendor.appName);
		this.setApplicationPhone(vendor.appPhone);
		if(null != vendor.appEmail && vendor.appEmail.length()>0){
			this.setApplicationEmail(vendor.appEmail);
		}
		this.setNumOfSotreRequeseted(vendor.requestStoreNum);
		this.setEquipmentRequeseted(vendor.requestStoreEquipmentNum);
	}

	public void setupVendorBasicInfo(VendorInfo vendor){
		this.setVendorNumber(vendor.number);
		this.setVendorName(vendor.name);
		this.setOwnerName(vendor.ownerName);
		this.selectVendorType(vendor.vendorType);

		//setup vendor attribute info
		if(null != vendor.taxID && vendor.taxID.length()>0){
			this.setTaxID(vendor.taxID);
		}
		if(null != vendor.taxIDType && vendor.taxIDType.length()>0){
			this.selectTaxIDType(vendor.taxIDType);
		}
	}

	public void setupVendorPhyAddressInfo(VendorInfo vendor){
		this.setPhysicalAddress(vendor.phyAddress);
		if(null != vendor.phySuppAddress && vendor.phySuppAddress.length()>0){
			this.setPhysicalSuppAddress(vendor.phySuppAddress);
		}
		this.setPhysicalCityTown(vendor.phyCityTown);
		if(vendor.phyCountry.trim().length()>0){
			this.selectPhysicalCountry(vendor.phyCountry);
		}else {
			this.deSelectPhyCountry();
		}
		ajax.waitLoading();

		if(vendor.phyStateProvince.trim().length()>0){
			this.selectPhysicalStateProvince(vendor.phyStateProvince);
		}else {
			this.deSelectPhyState();
		}
		ajax.waitLoading();

		if(vendor.phyCounty.trim().length()>0){
			this.selectPhysicalCounty(vendor.phyCounty);
		}else {
			this.deSelectPhyCounty();
		}
		ajax.waitLoading();
		this.setPhysicalZip(vendor.phyZipPostal);

		if(vendor.isValidatePhysicalAdd){
			this.clickValidateOfPhysicalAdd();
			ajax.waitLoading();
		}
	}

	public void setupVendorMailingAddressInfo(VendorInfo vendor){
		if(!vendor.isMailingAddSameAsPhysicalAdd){
			this.uncheckMailingAdd();
			ajax.waitLoading();

			this.setMailingAdd(vendor.mailingAddress);
			if(null != vendor.mailingSuppAddress && vendor.mailingSuppAddress.length()>0){
				this.setMailingSuppAddress(vendor.mailingSuppAddress);
			}
			this.setMailingCityTown(vendor.mailingCityTown);
			if(vendor.mailingCountry.trim().length()>0){
				this.selectMailingCountry(vendor.mailingCountry);
			}else {
				this.deSelectMailingCountry();
			}
			ajax.waitLoading();

			if(vendor.mailingStateProvince.trim().length()>0){
				this.selectMailingStateProvince(vendor.mailingStateProvince);
			}else {
				this.deSelectMailingState();
			}
			ajax.waitLoading();

			this.setMailingZip(vendor.mailingZipPostal);

			if(vendor.mailingCounty.trim().length()>0){
				this.selectMailingCounty(vendor.mailingCounty);
			}else {
				this.deSelectMailingCounty();
			}
			ajax.waitLoading();

			if(vendor.isValidateMailingAdd){
				this.clickValidateOfMailingAdd();
				ajax.waitLoading();
			}
		}else {
			this.checkMaillingAdd();
			ajax.waitLoading();
		}
	}

	public void setupVendorContactInfo(List<Contacts> contacts,List<Contacts> removedContacts){
		for(int i=0; i<contacts.size(); i++){
			if(0 != i && !this.isContactRemoveButtonOfIndexExist(i)){//Check the remove button of contact whether existing
				this.clickAddContract();
				ajax.waitLoading();
			}
			if(contacts.get(i).isPrimary){
				this.selectPrimary(i);
				ajax.waitLoading();
			}
			this.selectContactType(contacts.get(i).contactType, i);

			this.setContactFirstName(contacts.get(i).firstName, i);
			if(null != contacts.get(i).midName && contacts.get(i).midName.length()>0){
				this.setContactMiddleName(contacts.get(i).midName, i);
			}
			this.setContactLastName(contacts.get(i).lastName, i);
			if(null != contacts.get(i).suffix && contacts.get(i).suffix.length()>0){
				this.selectContactSuffix(contacts.get(i).suffix, i);
			}
			if(null != contacts.get(i).businessPhone && contacts.get(i).businessPhone.length()>0){
				this.setContactBusinessPhone(contacts.get(i).businessPhone, i);
			}
			if(null != contacts.get(i).homePhone && contacts.get(i).homePhone.length()>0){
				this.setContactHomePhone(contacts.get(i).homePhone, i);
			}
			if(null != contacts.get(i).mobilePhone && contacts.get(i).mobilePhone.length()>0){
				this.setContactMobilePhone(contacts.get(i).mobilePhone, i);
			}
			if(null != contacts.get(i).fax && contacts.get(i).fax.length()>0){
				this.setContactFax(contacts.get(i).fax, i);
			}
			if(null != contacts.get(i).email && contacts.get(i).email.length()>0){
				this.setContactEmail(contacts.get(i).email, i);
			}
			if(null != contacts.get(i).website && contacts.get(i).website.length()>0){
				this.setContactWebsite(contacts.get(i).website, i);
			}
		}
		//whether remove contacts info, if need to remove, base on remove contact info, could remove contacts info
		if(null != removedContacts && removedContacts.size()>0){
			for(int i=0; i<removedContacts.size(); i++){
				int index = this.getContactIndex(removedContacts.get(i));
				if(index != -1){
					this.removeContactInfoByIndex(index);
					ajax.waitLoading();
					contacts.remove(removedContacts.get(i));
				}else {
					throw new ErrorOnPageException("Expect removed contact not exists.");
				}
			}
		}
	}

	public void setupVendorAppStatusCheckInfo(List<ApplicationStatusCheck> applicationStatusCheck){
		if(null != applicationStatusCheck && applicationStatusCheck.size()>0){
			for(int i=0; i<applicationStatusCheck.size(); i++){
				int index = this.getAppStatusCheckTRObjectIndexByStatusCheck(applicationStatusCheck.get(i).statusCheck);
				if(applicationStatusCheck.get(i).byPassChecked){
					this.selectBypassCheck(index);
				}else {
					this.unselectBypassCheck(index);
				}
				if(null != applicationStatusCheck.get(i).dateCompleted){
					this.setDateCompleted(applicationStatusCheck.get(i).dateCompleted, index);
					this.clickSpace();
				}
				if(null != applicationStatusCheck.get(i).completedBy){
					this.setCompletedBy(applicationStatusCheck.get(i).completedBy, index);
				}
				if(null != applicationStatusCheck.get(i).comments){
					this.setApplicationComments(applicationStatusCheck.get(i).comments, index);
				}
			}
		}
	}
	private void clickSpace(){
		browser.clickGuiObject(".class", "Html.TD", ".text", "Application Status Checks");
	}
	
	/*
	 * select invoice schedule.
	 */
	public void selectInvoiceSchedule(String invoiceSchdule){
		browser.selectDropdownList(".id", new RegularExpression("VendorFinancialConfigView-\\d+\\.eftScheduleView",false), invoiceSchdule);
	}
	public void setupVendorEFTInfo(VendorInfo vendor){
		if(null != vendor.specifyDefault && vendor.specifyDefault.length()>0){
			this.selectSpecifyDefaults(vendor.specifyDefault);
			ajax.waitLoading();
		}
		if(vendor.isFillValues){
			this.clickFillValues();
			ajax.waitLoading();
		}
		// vendor.finConfigInfo will never be null but its attributes may be null
		if(null != vendor.finConfigInfo && !vendor.isFillValues){
			this.selectEFTType(vendor.finConfigInfo.eftType);
			this.selectEFTInvoiceFrequency(vendor.finConfigInfo.invoiceFrequency);
			ajax.waitLoading();
		
			this.selectInvoiceSchedule(vendor.finConfigInfo.invoiceSchedule);
			ajax.waitLoading();

			/*if(null != vendor.finConfigInfo.eftSchedule && vendor.finConfigInfo.eftSchedule.length()>0){
				this.selectEFTSchedule(vendor.finConfigInfo.eftSchedule);
			}*/
			if(null != vendor.finConfigInfo.failedEFTEnforcement && vendor.finConfigInfo.failedEFTEnforcement.length()>0){
				this.selectFailedEFTEnforcement(vendor.finConfigInfo.failedEFTEnforcement);
			}
			this.setVoidReturnChargeDays(vendor.finConfigInfo.voidReturnChargeDays);
			this.selectAutoReturnVoidedDocuments(vendor.finConfigInfo.autoReturnVoidedDoc);
			if(null != vendor.finConfigInfo.rptNotification && vendor.finConfigInfo.rptNotification.size()>0){
				this.setReportNotification(vendor.finConfigInfo.rptNotification);
			}
			if(null != vendor.finConfigInfo.reportEmails && vendor.finConfigInfo.reportEmails.size()>0){
				for(int i=0; i<vendor.finConfigInfo.reportEmails.size(); i++){
					if(0 != i && !isReportNotificationRemoveButtonOfIndexExist(i)){
						this.clickAddReportNotificationEmail();
						ajax.waitLoading();
					}
					this.setReportNotificationEmailByIndex(vendor.finConfigInfo.reportEmails.get(i),i);
				}
			}
		}
		//whether remove report notification email info, if need to remove, base on notification info, could remove report notification email info
		if(null != vendor.removeRepNotifiEmails && vendor.removeRepNotifiEmails.size()>0){
			for(int i=0; i<vendor.removeRepNotifiEmails.size(); i++){
				int index = this.getNotificationEmailIndex(vendor.removeRepNotifiEmails.get(i));
				if(index != -1){
					this.removeReportNotificationEmialInfoByIndex(index);
					ajax.waitLoading();
					vendor.finConfigInfo.reportEmails.remove(vendor.removeRepNotifiEmails.get(i));
				}else {
					throw new ErrorOnPageException("Expect removed notification email not exists.");
				}
			}
		}
	}

	/**get error message*/
	public String getErrorMsg(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		String errorMsg = objs[0].getProperty(".text");

		Browser.unregister(objs);
		return errorMsg;
	}

	/**verify error or warning message*/
	public void verifyErrorMsg(String expectedValue){
		this.clickApply();
		ajax.waitLoading();

		if(!this.getErrorMsg().equalsIgnoreCase(expectedValue)){
			throw new ErrorOnPageException("The exception message '"+ expectedValue + "' not found, actual message is "+getErrorMsg());
		}
	}

	/**select Bypass Check*/
	public void selectBypassCheck(int index){
		browser.selectCheckBox(".id", new RegularExpression("VendorApplicationCheckStatusView-\\d+\\.bypassCheck",false),index);
	}

	/**un-select Bypass Check*/
	public void unselectBypassCheck(int index){
		browser.unSelectCheckBox(".id", new RegularExpression("VendorApplicationCheckStatusView-\\d+\\.bypassCheck",false),index);
	}

	/**check Bypass Check*/
	public void checkBypassCheck(String bypassCheck,int index){
		if(bypassCheck.equalsIgnoreCase("true")){
			this.selectBypassCheck(index);
		}else{
			this.unselectBypassCheck(index);
		}
	}

	/**set Date Completed*/
	public void setDateCompleted(String date,int index){
		browser.setTextField(".id", new RegularExpression("VendorApplicationCheckStatusView-\\d+\\.completedDate_ForDisplay",false), date, index);
	}

	/**get Date Completed*/
	public String getDateCompleted(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("VendorApplicationCheckStatusView-\\d+\\.completedDate_ForDisplay",false),index);
	}

	/**set Date Completed by*/
	public void setCompletedBy(String author,int index){
		browser.setTextField(".id", new RegularExpression("VendorApplicationCheckStatusView-\\d+\\.completedBy",false), author, index);
	}

	/**set application status comments*/
	public void setApplicationComments(String comments, int index){
		browser.setTextArea(".id", new RegularExpression("VendorApplicationCheckStatusView-\\d+\\.comments",false), comments, true, index);
	}

	/**set application check information*/
	public void setSpecifiedApplicationChecksInfo(VendorInfo vendor){
		IHtmlObject [] objs = browser.getHtmlObject(".id", new RegularExpression("VendorApplicationCheckStatusView-\\d+\\.bypassCheck",false));
		for(int i = 0; i<objs.length-2; i++){
			this.checkBypassCheck(vendor.bypassCheck,i);
			this.setDateCompleted(vendor.dateCompleted,i);
			this.setCompletedBy(vendor.completedBy,i);
			this.setApplicationComments(vendor.applicationCheckComment,i);
		}
	}

	public void addContractInfo(VendorInfo vendor, int index, boolean addOrNo){
		if (addOrNo){
			this.clickAddContract();
			ajax.waitLoading();
		}
		this.selectContactType(vendor.contactType,index);
		this.setContactFirstName(vendor.contactFirstName,index);
		this.setContactLastName(vendor.contactLastName,index);
		this.setContactBusinessPhone(vendor.contactBusinessPhone,index);
		this.setContactFax(vendor.contactFax, index);
		this.setContactEmail(vendor.contactEmail, index);
	}

	public String getWarningMessage(){
		String message="";
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id", "NOTSET");

		if(objs.length<1){
			return "";
		}
		if (objs.length > 1) {
			for (IHtmlObject obj : objs) {
				message += obj.getProperty(".text");
			}
			Browser.unregister(objs);
			return message;
		}
		message=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return message;
	}

}

