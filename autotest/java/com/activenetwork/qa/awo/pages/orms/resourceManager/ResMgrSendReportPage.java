/*
 * $Id: ResMgrSendReportPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */
package com.activenetwork.qa.awo.pages.orms.resourceManager;

import com.activenetwork.qa.testapi.util.Property;

/**
 * @author cguo
 */
public class ResMgrSendReportPage extends ResourceManagerPage {

	private static ResMgrSendReportPage instance = null;

	private ResMgrSendReportPage() {
	}

	public static ResMgrSendReportPage getInstance() {
		if (null == instance) {
			instance = new ResMgrSendReportPage();
		}
		return instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id",
				"recipientSelectedList");
	}

	/**
	 * Select delivery protocol by given value.
	 * @param protocol - protocol name
	 */
	public void selectDeliveryProtocol(String protocol) {
		browser.selectDropdownList(".id", "deliveryprotocolid", protocol);
	}

	/**
	 * Input email subject
	 * @param subject
	 */
	public void setEmailSubject(String subject){
		if(subject!=null&&!subject.equals("")){
			browser.setTextField(".id", "emailSubject", subject);
		}
	}
	
	/**
	 * Select the recipient from dropdown list.
	 * @param recipient - recipient
	 */
	public void selectRecipient(String recipient) {
		browser.selectDropdownList(".id", "recipientSelectedList", recipient);
	}

	/** Click on OK to send report. */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public boolean checkDefaultRecipientFilterCorrect(String charactor){
		boolean spanExist = browser.checkHtmlObjectExists(Property.addToPropertyArray(span(),".text",charactor));
		boolean aNotExist = !browser.checkHtmlObjectExists(Property.addToPropertyArray(a(),".text",charactor));
		return spanExist&&aNotExist;
	}

	/**
	 * Send report by select protocol and recipient.
	 * @param protocol - protocol mame
	 * @param recipient - recipient name
	 */
	public void sendReport(String protocol, String recipient) {
		this.selectDeliveryProtocol(protocol);
		this.selectRecipient(recipient);

		this.clickOK();
	}
}
