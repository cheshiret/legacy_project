package com.activenetwork.qa.awo.pages.orms.supportCenter;

import java.util.List;

import com.activenetwork.qa.testapi.util.Property;

public abstract class SupportCenterChangeSitePage extends SupportCenterPage {
	protected Property[] newTicket_LocationAddOrRemoveButton() {
		return Property.toPropertyArray(".class","FlexButton","id","buLocations");
	}
	
	protected Property[] newTicket_ChangeQuantityByButton() {
		return Property.toPropertyArray(".class","FlexButton","id","buLocations");
	}
	
	protected Property[] newTicket_ChangeQuantityToButton() {
		return Property.toPropertyArray(".class","FlexButton","automationName","Change Quantity To");
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
	
	protected Property[] newTicket_ContactEmailAddressText() {
		return Property.toPropertyArray(".class","FlexTextArea","id","tiEmail");
	}
	
	protected Property[] newTicket_ContactNameText() {
		return Property.toPropertyArray(".class","FlexTextArea","id","tiName");
	}

	protected Property[] newTicket_ContactPhoneNumberText() {
		return Property.toPropertyArray(".class","FlexTextArea","id","tiPhone");
	}
	
	protected Property[] newTicket_InventoryEndDateCalendar() {
		return Property.toPropertyArray(".class","FlexDateField","id","dfEnd");
	}
	
	protected Property[] newTicket_InventoryStartDateCalendar() {
		return Property.toPropertyArray(".class","FlexDateField","id","dfStart");
	}
	
	protected Property[] newTicket_QuantityText() {
		return Property.toPropertyArray(".class","FlexTextArea","id","tiQuantity");
	}

	protected Property[] newTicket_ReasonForChangeText() {
		return Property.toPropertyArray(".class","FlexTextArea","id","taReason");
	}
	
	protected Property[] newTicket_AffectedSiteComboBox() {
		return Property.toPropertyArray(".class","FlexComboBox","id","acSite");
	}
	
	public void clickAddOrRemove() {
		flex.clickFlexObject(newTicket_LocationAddOrRemoveButton());
	}
	
	public void clickChangeQuantityBy() {
		flex.clickFlexObject(newTicket_ChangeQuantityByButton());
	}
	
	public void clickChangeQuantityTo() {
		flex.clickFlexObject(newTicket_ChangeQuantityToButton());
	}
	
	public void clickReset() {
		flex.clickFlexObject(newTicket_ResetButton());
	}

	public void clickSubmit() {
		flex.clickFlexObject(newTicket_SubmitButton());
	}
	
	public void selectAttachments(String option) {
		flex.selectComboBox(newTicket_AttachmentsComboBox(),option);
	}
	
	public void setContactEmail(String text) {
		flex.setTextArea(newTicket_ContactEmailAddressText(),text);
	}
	
	public void setContactName(String text) {
		flex.setTextArea(newTicket_ContactNameText(), text);
	}
	
	public void setContactPhone(String text) {
		flex.setTextArea(newTicket_ContactPhoneNumberText(), text);
	}
	
	public void setEndDate(String text) {
		flex.selectCalendar(newTicket_InventoryEndDateCalendar(), text);
	}
	
	public void setQuantity(String text) {
		flex.setTextArea(newTicket_QuantityText(), text);
	}
	
	public void setReasonForChange(String text) {
		flex.setTextArea(newTicket_ReasonForChangeText(), text);
	}
	
	public void setStartDate(String text) {
		flex.selectCalendar(newTicket_InventoryStartDateCalendar(), text);
	}
	
	public List<String> getAffectedSite() {
		List<String> value = flex.getComboxBoxValues(newTicket_AffectedSiteComboBox());
		return value;
	}
	
	public void setAffectedSite(String text) {
		flex.setComboBox(newTicket_AffectedSiteComboBox(), text);
	}
	
	public String getAffectedSiteValue() {
		String value=flex.getComboBoxValue(newTicket_AffectedSiteComboBox());
		return value;
	}
}
