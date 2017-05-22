/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.supportCenter;

import com.activenetwork.qa.testapi.util.Property;

/**
 * @author szhou
 * @Date  Jul 26, 2011
 */
public class SupportCenterSupportCasePage extends SupportCenterPage {
	private static SupportCenterSupportCasePage _instance = null;

	public static SupportCenterSupportCasePage getInstance() {
		if (_instance == null) {
			_instance = new SupportCenterSupportCasePage();
		}
		return _instance;
	}

	protected Property[] newTicket_LocationAddOrRemoveButton() {
		return Property.toPropertyArray(".class","FlexButton","id","buLocations");
	}
	
	protected Property[] newTicket_ResetButton() {
		return Property.toPropertyArray(".class","FlexButton","id","buReset");
	}
	
	protected Property[] newTicket_SubmitButton() {
		return Property.toPropertyArray(".class","FlexButton","id","buSubmit");
	}
	
	protected Property[] newTicket_AttachmentsComboBox() {
		return Property.toPropertyArray(".class","FlexComboBox","id","cbAttachments");
	}
	
	protected Property[] newTicket_ProductComboBox() {
		return Property.toPropertyArray(".class","FlexComboBox","id","cbProduct");
	}
	
	protected Property[] newTicket_DescriptionText() {
		return Property.toPropertyArray(".class","FlexTextArea","id","taDescription");
	}
	
	protected Property[] newTicket_SummaryText() {
		return Property.toPropertyArray(".class","FlexTextArea","id","taSummary");
	}
	
	protected Property[] newTicket_SupportCaseLabel() {
		return Property.toPropertyArray(".class","FlexLabel","id","lblHeader");
	}
	
	public void clickAddOrRemove() {
		flex.clickFlexObject(newTicket_LocationAddOrRemoveButton());	
	}

	public void clickReset() {
		flex.clickFlexObject(newTicket_ResetButton());
	}

	public void clickSubmit() {
		flex.clickFlexObject(newTicket_SubmitButton());
	}

	public void selectAttachments(String option) {
		flex.selectComboBox(newTicket_AttachmentsComboBox(), option);
	}

	public void selectProduct(String option) {
		flex.selectComboBox(newTicket_ProductComboBox(), option);
	}

	public void setDescription(String text) {
		flex.setTextArea(newTicket_DescriptionText(), text);
	}

	public void setSummary(String text) {
		flex.setTextArea(newTicket_SummaryText(), text);
	}

	@Override
	public boolean exists() {
		return flex.checkFlexObjectExists(newTicket_SupportCaseLabel());
	}
}
