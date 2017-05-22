package com.activenetwork.qa.awo.pages.orms.common.customer;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.Property;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Apr 17, 2014
 */
public abstract class OrmsCustomerPassDetailsCommonPage extends OrmsPage {
	
	private Property[] passID() {
		return Property.toPropertyArray(".id", "CustomerPassView.id");
	}
	
	private Property[] passFileID() {
		return Property.toPropertyArray(".id", "CustomerPassView.fileID");
	}
	
	private Property[] passNumber() {
		return Property.toPropertyArray(".id", "CustomerPassView.passNumber");
	}
	
	private Property[] passName() {
		return Property.toPropertyArray(".id", "CustomerPassView.passType.name");
	}
	
	private Property[] expiryDate() {
		return Property.toPropertyArray(".id", "CustomerPassView.expiryDate");
	}
	
	private Property[] passDetailsTab() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Pass Details");
	}
	
	private Property[] pointsTab() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Points");
	}
	
	public String getPassID() {
		return browser.getTextFieldValue(passID());
	}
	
	public String getPassFileID() {
		return browser.getTextFieldValue(passFileID());
	}
	
	public String getPassNumber() {
		return browser.getTextFieldValue(passNumber());
	}
	
	public String getPassName() {
		return browser.getTextFieldValue(passName());
	}
	
	public String getExpiryDate() {
		return browser.getTextFieldValue(expiryDate());
	}
	
	public void clickPassDetailsTab() {
		browser.clickGuiObject(passDetailsTab());
	}
	
	public void clickPointsTab() {
		browser.clickGuiObject(pointsTab());
	}
}
