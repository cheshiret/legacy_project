/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.license;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Jul 23, 2012
 */

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsRequestHFConfirmLetterPage extends LicMgrCommonTopMenuPage {
	private static OrmsRequestHFConfirmLetterPage _instance = null;

	protected OrmsRequestHFConfirmLetterPage() {

	}

	public static OrmsRequestHFConfirmLetterPage getInstance() {
		if (_instance == null) {
			_instance = new OrmsRequestHFConfirmLetterPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return (
				(browser.checkHtmlObjectExists(".class", "Html.A", ".text","Request H&F Conf. Letter"))
				&&(browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^Billing Customer.*", false)))
				);
	}
	
	public void selectEmailMethod()	{		
		browser.selectRadioButton(".value", "Deliver");
	}
	
	public void selectOnlineMethod() {		
		browser.selectRadioButton(".value", "ShowOnScreen");
	}
	
	public void selectBatchMethod() {		
		browser.selectRadioButton(".value", "QueueForBatch");
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void selectDistributionMethod(String method){
		if(method.equalsIgnoreCase("email")){
			this.selectEmailMethod();
		}else if(method.equalsIgnoreCase("online")){
			selectOnlineMethod();
		}else if(method.equalsIgnoreCase("Batch")){
			selectBatchMethod();
		}else{
			throw new ItemNotFoundException("Unknown distribution method-"+method);
		}
	}
	
	/**
	 * Get privilege attribute value
	 * @param attributeName-- the attribute name
	 * @return the input text value.
	 */
	private String getConfLetterAttributeValueByName(String attributeName){
		String attributeValue = "";
		Property property[] = new Property[3];
		IHtmlObject divObjs[] = null;
		
		property[0] = new Property(".class", "Html.SPAN");
		property[1] = new Property(".className", "inputwithrubylabel");
		property[2] = new Property(".text", new RegularExpression("^" + attributeName, false));
		divObjs = browser.getHtmlObject(property);

		if(divObjs.length > 0){
			if(divObjs[0].getProperty(".text").length()>attributeName.length()){
				attributeValue = divObjs[0].getProperty(".text").split(attributeName)[1].trim();
			}else{
				attributeValue = "";
			}
			
		}
		return attributeValue;
	}
	/**
	 * get customer name.
	 * @return
	 */
	public String getCustName(){
		return this.getConfLetterAttributeValueByName("Name");
	}
	/**
	 * get customer email.
	 * @return
	 */
	public String getCustEmail(){
		return this.getConfLetterAttributeValueByName("Email");
	}
	/**
	 * get customer street address.
	 * @return
	 */
	public String getCustStreetAddress(){
	   return this.getConfLetterAttributeValueByName("Street Address");	
	}
	/**
	 * get customer city.
	 * @return
	 */
	public String getCustCity(){
		return this.getConfLetterAttributeValueByName("City");	
	}
	/**
	 * get customer state/province.
	 * @return
	 */
	public String getCustState(){
		return this.getConfLetterAttributeValueByName("State/Province");	
	}
	/**
	 * get customer Zip/Postal Code.
	 * @return
	 */
	public String getCustZip(){
		return this.getConfLetterAttributeValueByName("Zip/Postal Code");	
	}
	/*
	 * get customer country.
	 */
	public String getCustCountry(){
		return this.getConfLetterAttributeValueByName("Country");	
	}
	/**
	 * compare confirm letter customer info.
	 * @param cust
	 * @return
	 */
	public boolean compareConfLetterCustInfo(Customer cust){
		boolean isEqual = true;
		isEqual &= MiscFunctions.compareResult("Customer name",cust.lName + ","+cust.fName, this.getCustName());
		isEqual &= MiscFunctions.compareResult("Customer email",cust.email, this.getCustEmail());
		isEqual &= MiscFunctions.compareResult("Customer street address",cust.physicalAddr.address, this.getCustStreetAddress());
		isEqual &= MiscFunctions.compareResult("Customer city",cust.physicalAddr.city, this.getCustCity());
		isEqual &= MiscFunctions.compareResult("Customer state",cust.physicalAddr.state, this.getCustState());
		isEqual &= MiscFunctions.compareResult("Customer zip",cust.physicalAddr.zip, this.getCustZip());
		isEqual &= MiscFunctions.compareResult("Customer country",cust.physicalAddr.country, this.getCustCountry());
		return isEqual;
	}
	
}
