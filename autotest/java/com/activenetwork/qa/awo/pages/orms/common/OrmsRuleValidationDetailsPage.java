package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.OrmsPage;
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
 * @Date  Aug 28, 2013
 */
public class OrmsRuleValidationDetailsPage extends OrmsPage {
	private static OrmsRuleValidationDetailsPage _instance = null;
	
	private OrmsRuleValidationDetailsPage() {}
	
	public static OrmsRuleValidationDetailsPage getInstance() {
		if(_instance == null) _instance = new OrmsRuleValidationDetailsPage();
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(attributeDetails());
	}
	
	private Property[] location() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "ruleLocation");
	}
	
	private Property[] siteType() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "sitetype");
	}
	
	private Property[] site() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "site");
	}
	
	private Property[] effectiveDate() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "effectivedate");
	}
	
	private Property[] startDate() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "startdate");
	}
	
	private Property[] endDate() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "enddate");
	}
	
	private Property[] div(String label) {
		return Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression("^" + label, false));
	}
	
	private Property[] attributeDetails() {
		return Property.toPropertyArray(".id", "attributedetails");
	}
	
	private Property[] comments() {
		return Property.toPropertyArray(".id", "comments");
	}
	
	private Property[] ok() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "OK");
	}
	
	
	public String getLocation() {
		return browser.getObjectText(location()).split("Location")[1].trim();
	}
	
	public String getSiteType() {
		return browser.getObjectText(siteType()).split("Site Type")[1].trim();
	}
	
	public String getSite() {
		return browser.getObjectText(site()).split("Site")[1].trim();
	}
	
	public String getEffectiveDate() {
		return browser.getObjectText(effectiveDate()).split("Effective Date")[1].trim();
	}
	
	public String getStartDate() {
		return browser.getObjectText(startDate()).split("Start Date")[1].trim();
	}
	
	public String getEndDate() {
		return browser.getObjectText(endDate()).split("End Date")[1].trim();
	}
	
	private String getAttributeValueByLabelName(String label) {
		return browser.getObjectText(div(label)).split(label)[1].trim();
	}
	
	public String getSalesChannel() {
		return this.getAttributeValueByLabelName("Sales Channel");
	}
	
	public String getSeasonType() {
		return this.getAttributeValueByLabelName("Season Type");
	}
	
	public String getInOutState() {
		return this.getAttributeValueByLabelName("In/Out State");
	}
	
	public String getCustomerType() {
		return this.getAttributeValueByLabelName("Customer Type");
	}
	
	public String getCustomerPass() {
		return this.getAttributeValueByLabelName("Customer Pass");
	}
	
	public String getMember() {
		return this.getAttributeValueByLabelName("Member");
	}
	
	public String getPaymentType() {
		return this.getAttributeValueByLabelName("Payment Type");
	}
	
	public String getAttributeDetails() {
		return browser.getTextAreaValue(attributeDetails());
	}
	
	public String getComments() {
		return browser.getTextAreaValue(comments());
	}
	
	public void clickOK() {
		browser.clickGuiObject(ok());
	}
}
