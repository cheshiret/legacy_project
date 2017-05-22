package com.activenetwork.qa.awo.pages.orms.common.financial;

import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsTransmittalSummaryPage extends OrmsTransmittalMainPage{
	protected OrmsTransmittalSummaryPage() {
	}

	private static OrmsTransmittalSummaryPage _instance = null;

	public static OrmsTransmittalSummaryPage getInstance() {
		if (null == _instance)
			_instance = new OrmsTransmittalSummaryPage();
		return _instance;
	}
	
	/**
	 * Page will refresh after set up total adjust and click other field
	 * @param adjust
	 */
	public void setTransmittalCost(String cost){
		browser.setTextField(".id", new RegularExpression("transmittalCost", false), cost);
		this.clickForRefresh();
		browser.waitExists();
	}
	
	private void clickForRefresh(){
		browser.clickGuiObject(".id", new RegularExpression("transmittalTraceNumber", false));
	}
	
	public void setTraceNum(String traceNum){
		browser.setTextField(".id", new RegularExpression("transmittalTraceNumber", false), traceNum);
	}
	
	public void setNotes(String notes){
		browser.setTextArea(".id", new RegularExpression("transmittalNote", false), notes);
	}
	
	public void clickCreateTransmittal(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Create Transmittal", false));
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Cancel", false));
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(".class", "Html.DIV", ".className", new RegularExpression("message msgerror", false));
	}
}
