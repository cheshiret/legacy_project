package com.activenetwork.qa.awo.pages.orms.common.customer;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsCustomerPassDetailsPage extends OrmsCustomerPassDetailsCommonPage{
	
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsCustomerPassDetailsPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsCustomerPassDetailsPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsCustomerPassDetailsPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsCustomerPassDetailsPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		//		RegularExpression reg=new RegularExpression(".* (View/Update Customer Details|Add Customer)$",false);
		return browser.checkHtmlObjectExists(".id", new RegularExpression("CustomerPassView.passType(|_readOnlyDisabled)", false));
	}
	
	public boolean waitForCreatePassFinished() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("Create/Modify Details", false));
		
		int count = 0;
		while(objs.length < 1 && count < 6) {
			objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("Create/Modify Details", false));
			count ++;
		}
		
		boolean exists = objs.length > 1 ? true : false;
		
		Browser.unregister(objs);
		return exists;
	}
	
	/**
	 * Select the pass name
	 * @param passName
	 */
	public void selectPassName(String passName){
		browser.selectDropdownList(".id","CustomerPassView.passType",passName);
	}
	
	public String getPassName(){
		return browser.getDropdownListValue(".id","CustomerPassView.passType_readOnlyDisabled", 0);
	}
	/**
	 * Set the pass number
	 * @param passNum
	 */
    public void setPassNumber(String passNum){
    	IHtmlObject[] objs = browser.getHtmlObject(".id", "CustomerPassView.passNumber");
    	if(objs.length>0){
    		if(objs.length==2){//Object's length is 2 in QA4 environment
    			browser.setTextField(".id", "CustomerPassView.passNumber", passNum, 1);
    		}else{
    	    	browser.setTextField(".id", "CustomerPassView.passNumber", passNum);
    		}
    	}
    	Browser.unregister(objs);
    }
    
    public String getPassNum(){
    	return browser.getTextFieldValue(".id", "CustomerPassView.passNumber");
    }
    public void setExpiryDate(String date){
    	if(browser.checkHtmlObjectExists(".id", "CustomerPassView.expiryDate_ForDisplay")){
    		browser.setTextField(".id", "CustomerPassView.expiryDate_ForDisplay", date);
    	}
    }
    
    public String getExpiryDate(){
    	return browser.getTextFieldValue(".id", new RegularExpression("CustomerPassView.expiryDate(|_ForDisplay)", false));
    }
    
    public void selectStatus(String status){
    	browser.selectDropdownList(".id", "CustomerPassView.status", status);
    }
    
    public String getStatus(){
    	return browser.getDropdownListValue(".id", "CustomerPassView.status", 0);
    }
    
    public String getCreateDate(){
    	return browser.getTextFieldValue(".id","CustomerPassView.createDate");
    }
    
    public String getCreateUser(){
    	return browser.getTextFieldValue(".id","CustomerPassView.createUser");
    }
    
    public String getOrderNum(){
    	return browser.getTextFieldValue(".id","CustomerPassView.order.orderNumber");
    }
    
    public String getPassID(){
    	return browser.getTextFieldValue(".id", "CustomerPassView.id");
    }
    
    public void clickOK(){
    	browser.clickGuiObject(".class","Html.A",".text","OK");
    }
    
    public void clickCancel(){
    	browser.clickGuiObject(".class","Html.A",".text","Cancel");
    }
    
    public void clickApply(){
    	browser.clickGuiObject(".class","Html.A",".text","Apply");
    }
    
    public void clickSpace(){
    	browser.clickGuiObject(".class","Html.LABEL",".text","Expiry Date");
    }
    
    public void setupCustomerPassInfo(Customer cust){
    	this.selectPassName(cust.pass);
    	ajax.waitLoading();
    	this.selectStatus(cust.passStatus);
    	this.setExpiryDate(cust.passExpiryDate);
    	this.clickSpace();
    	this.setPassNumber(cust.passNumber);
    }
    
    public String getErrorMessage(){
    	IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".id","statusMessages");
    	String errorMsg=objs[0].getProperty(".text");
    	
    	Browser.unregister(objs);
    	return errorMsg;
    }
    
    public boolean isFulfillmentDisable(){
    	IHtmlObject[] objs=browser.getTextField(".id","CustomerPassView.fulfillmentStatusDesc");
    	boolean result=Boolean.valueOf(objs[0].getProperty("isDisabled"));
    	
    	Browser.unregister(objs);
    	return result;
    }
    
    public boolean isOrderNumDisable(){
    	IHtmlObject[] objs=browser.getTextField(".id","CustomerPassView.order.orderNumber");
    	boolean result=Boolean.valueOf(objs[0].getProperty("isDisabled"));
    	
    	Browser.unregister(objs);
    	return result;
    }
    
    public boolean isCustNameDisable(){
    	IHtmlObject[] objs=browser.getTextField(".id","CustomerPassView.customerName");
    	boolean result=Boolean.valueOf(objs[0].getProperty("isDisabled"));
    	
    	Browser.unregister(objs);
    	return result;
    }
    
    public boolean isCustAddrDisable(){
    	IHtmlObject[] objs=browser.getTextField(".id","CustomerPassView.customerAddress");
    	boolean result=Boolean.valueOf(objs[0].getProperty("isDisabled"));
    	
    	Browser.unregister(objs);
    	return result;
    }
}
