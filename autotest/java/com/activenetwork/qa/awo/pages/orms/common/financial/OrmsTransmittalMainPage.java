package com.activenetwork.qa.awo.pages.orms.common.financial;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsTransmittalMainPage extends OrmsPage{
	protected OrmsTransmittalMainPage() {
	}

	private static OrmsTransmittalMainPage _instance = null;

	public static OrmsTransmittalMainPage getInstance() {
		if (null == _instance)
			_instance = new OrmsTransmittalMainPage();
		return _instance;
	}
	
	/**
	 * It is a DIV on the top of page, 'Create Transmittal' part.
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("transmittalDetailForm", false));
	}
	
	public void clickTransSummary(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Transmittal Summary", false));
	}
	
	public void clickAddDeposit(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add Deposits", false));
	}
	
	public String getTransmittalID(){
		return browser.getTextFieldValue(".id", new RegularExpression("TransmittalSummaryFormAdaptor.transmittalId", false));
//		return browser.getObjectText(".id", new RegularExpression("TransmittalSummaryFormAdaptor.transmittalId", false));
	}
}
