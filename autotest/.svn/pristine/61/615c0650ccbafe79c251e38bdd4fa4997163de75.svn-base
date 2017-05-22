package com.activenetwork.qa.awo.pages.web.lam;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LamPaymentPage extends LamTopMenuBar {
  	private static LamPaymentPage _instance=null;
  	
  	public static LamPaymentPage getInstance() {
  	  	if(null==_instance) {
  	  	  	_instance=new LamPaymentPage();
  	  	}
  	  	
  	  	return _instance;
  	}
  	
  	protected LamPaymentPage() {
  	  
  	}
  	
  	public boolean exists() {
  	  	return browser.checkHtmlObjectExists(".text","Accept terms and conditions and activate");
  	}
  	
  	public String getNewListID() {
  	  	IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A", ".text","Go to My Listings detail");
  	  	String href=objs[0].getProperty(".href").toString();
  	  	
  	  	//http://lam2.qa.reserveamerica.com/updateListing.do?currentStep=1&listId=105212309
  	  	String[] s=RegularExpression.getMatches(href,"listId=\\d+");
  	  	String id=s[0].substring(7);
  	  	
  	  	Browser.unregister(objs);
  	  	
  	  	return id;
  	}
  	
  	public void setCardType(String type){
  		browser.selectDropdownList(".id", "cardTypeId_null", type);  		
  	}
  	
  	public void setCardNumber(String num){
  		browser.setTextField(".id", "cardnum_null", num);
  	}
  	
  	public void setExpiryMonth(String month){
  		browser.setTextField(".id", "expmonth_null", month);
  	}
  	
  	public void setExpiryYear(String year){
  		browser.setTextField(".id", "expyear_null", year);
  	}
  	
  	public void setFirstName(String fname){
  		browser.setTextField(".id", "fname_null", fname);
  	}
  	
  	public void setLastName(String lname){
  		browser.setTextField(".id", "lname_null", lname);
  	}
  	
  	public void setPaymentInfo(Payment pay){
  		setCardType(pay.payType);
  		setCardNumber(pay.creditCardNumber);
  		setExpiryMonth(pay.expiryMon);
  		setExpiryYear(pay.expiryYear);
  		setFirstName(pay.fName);
  		setLastName(pay.lName);  		
  	}
  	
  	public void clickAcceptBtn(){
  		browser.clickGuiObject(".id", "search",true);
  	}
}
