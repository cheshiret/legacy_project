package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class OrmsRefundRequestsPage extends OrmsPage{
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsRefundRequestsPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsRefundRequestsPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsRefundRequestsPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsRefundRequestsPage();
		}

		return _instance;
	}
	
	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text", new RegularExpression("^Refund Request.*",false));
	}
	
	/**Check the Request Reason is existed*/
	public boolean isRequestReasonExsited(){
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id","RefundRequestDetailView.reason");
	}
	
	/**Select the Request Refund reason*/
	public void setRequestReason(String reason){
		if(isRequestReasonExsited()){
			if(reason.length()<0||reason ==null){
				browser.selectDropdownList(".class", "Html.SELECT", ".id","RefundRequestDetailView.reason","Other");
			}else{
				browser.selectDropdownList(".class", "Html.SELECT", ".id","RefundRequestDetailView.reason",reason);
			}
		}
	}
	
	/**Set the Amount*/
	public void setAmount(String amount){
		browser.setTextField(".id","RefundRequestDetailView.amount",amount);
	}
	
	/**Set the Request Details*/
	public void setRequestDetails(String details){
		browser.setTextArea(".id", "RefundRequestDetailView.details",details);
	}
	
	/**Click the Cancel button*/
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	/**Click the Cancel button*/
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	/**Click the Return to Reservation Details button*/
	public void clickReturnToReservationDetails(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Return to Reservation Details");
	}
	
//	/**Get the Request Reason*/
//	public String getRequesetReason(){
//		HtmlObject[] objs = browser.getHtmlObject(".class","Html.INPUT.text",".id", "na");
//		String reason = objs[2].getProperty(".text").toString();
//		Browser.unregister(objs);
//		return reason;
//	}
	
//	/**Get the Request Details*/
//	public String getRequesetDetails(){
//		HtmlObject[] objs = browser.getHtmlObject(".class","Html.TEXTAREA");
//		String details = objs[0].getProperty(".text");
//		Browser.unregister(objs);
//		return details;
//	}
	
//	/**Get the Amount*/
//	public String getRequesetAmount(){
//		HtmlObject[] objs = browser.getHtmlObject(".class","Html.INPUT.text",".id", "na");
//		String amount = objs[3].getProperty(".text").toString().split("\\$")[1].trim();
//		Browser.unregister(objs);
//		return amount;
//	}
	
	/**check the Request Details*/
	public boolean checkRequesetDetails(String requestDetails){
		return browser.checkHtmlObjectExists(".class","Html.TEXTAREA",".text", requestDetails);
	}
	
	/**check the Request Reason*/
	public boolean checkRequesetReason(String requestReason){
		return browser.checkHtmlObjectExists(".class","Html.INPUT.text",".value", requestReason);
	}
	
	/**Check request Amount*/
	public boolean checkRequesetAmount(String requestAmount){
		return browser.checkHtmlObjectExists(".class","Html.INPUT.text",".value", "$"+requestAmount);
	}
	
	public String getRequestRefundID(){
		Property p1 = new Property(".class", "Html.SPAN");
		Property p2 = new Property(".className", "inputwithrubylabel");
		Property p3 = new Property(".text", new RegularExpression("^()*Refund Request ID", false));
		return browser.getObjectText(p1, p2, p3).replaceAll("Refund Request ID", StringUtil.EMPTY).trim();
	}
	
}
