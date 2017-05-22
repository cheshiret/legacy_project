package com.activenetwork.qa.awo.pages.web.uwp;


import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author jchen
 */
public class UwpTourShoppingCartPage extends UwpPage {

	private static UwpTourShoppingCartPage _instance = null;

	public static UwpTourShoppingCartPage getInstance() {
		if (null == _instance)
			_instance = new UwpTourShoppingCartPage();

		return _instance;
	}

	public UwpTourShoppingCartPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "chkout");
	}
	
	/**
	 * Retrieve the total amount in cart.
	 * @return - total amount
	 */
	public String getTotalAmount() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.FORM", ".id", "shoppingCartForm");
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		String[] tokens = RegularExpression.getMatches(text, "\\$[0-9]+\\.[0-9]+");
		String total = tokens[tokens.length - 1];
		total = total.replaceAll("\\$", "");

		return total;
	}
	
	/**
	 * Click check out cart button.
	 */
	public void clickCheckOutCart() {
		browser.clickGuiObject(".id", "chkout", true);
	}
	
	/**
	 * Get the number of items in shopping cart.
	 * @return - number of items
	 */
	public int getNumOfItems() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "ckitems");

		int num = objs.length;
		Browser.unregister(objs);

		return num;
	}
	
	/**
	 * Retrieve the text in tour shopping cart page.
	 * @return - text in shopping cart
	 */
	public String getTextInShoppingCart() {
		IHtmlObject[] obj = browser.getTableTestObject(".id", "table1");

		String content = (String) obj[0].getProperty(".text");
		Browser.unregister(obj);

		return content;
	}
	
	public boolean checkDeliveryMethodExist(String method_name,String tourName){
		boolean isExisting=false;
		IHtmlObject[] tables=browser.getTableTestObject(".className", "items",".id","table1");
		if(tables==null||tables.length<1){
			throw new ObjectNotFoundException("Can't find Shopping cart table");
		}
		IHtmlTable table=(IHtmlTable)tables[0];
		int row = table.findRow(2, new RegularExpression("Tour: ?"+tourName.replace("(", "\\(").replace(")", "\\)"),false))+1;
		String value=table.getCellValue(row, 1);
		if(StringUtil.isEmpty(value)){
			isExisting=false;
		}else {
			isExisting=value.trim().equals("("+method_name+")");
		}
		Browser.unregister(tables);
		return isExisting;
	}
	
	public String getDeliveryTranFee(String method){
		Property[] p1=Property.toPropertyArray(".class", "Html.TR", ".className", "totalarea sum");
		Property[] p2=Property.toPropertyArray(".class", "Html.DIV", ".className","subtotal",".text", new RegularExpression("^"+method+".*\\d+\\.\\d{2}",false));
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
	    if(objs==null || objs.length<1){
	    	throw new ObjectNotFoundException("Can't find Delivery transaction fee in SubTotal.");
	    }
	    String val=objs[0].text().replaceAll(method, "").trim();
	    Browser.unregister(objs);
	    return val;
	}
	
	public boolean isDeliveryTranFeeExist(){
        IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.TR", ".className", "totalarea sum");
        if(objs==null || objs.length<1){
        	throw new ErrorOnPageException("Can't find total row..");
        }
        boolean isExist=false;
        String val=objs[0].text();
        if(val.matches(".*((Will Call)|(Print at Home)|(Mail Out)).*")){
        	isExist=true;
        }
        Browser.unregister(objs);
        return isExist;
 
	}
	
	public void verifyDeliveryTransFee(String method,String expectedFee){
		logger.info("Verify Delivery trans fee in Shopping cart page;");
		double feeOnPage=Double.parseDouble(getDeliveryTranFee(method));
		double expectedFeeVal=Double.parseDouble(expectedFee);
		if(feeOnPage-expectedFeeVal!=0){
			throw new ErrorOnPageException("Delivery method trans fee is wrong.",expectedFee,String.valueOf(feeOnPage));
		}
	}

	/**
	 * Verify delivery method existing or not in shopping cart page
	 * @param method_name
	 * @param tourName
	 * @param isExisting
	 */
	public void verifyDeliveryMethodExistingOrNot(String method_name,String tourName,boolean isExisting) {
		logger.info("Verify delivery method:"+method_name+" for tour "+tourName+(isExisting?"":" is not "+"existing"));
		if(checkDeliveryMethodExist(method_name,tourName)!=isExisting){
			throw new ErrorOnPageException( "delivery method:"+method_name+" for tour "+tourName+" should"+(isExisting?"":" not ")+" exist");
		}
	}

	/**
	 * Click "Make More Reservations" link
	 */
	public void clickMakeMoreReservationsLink(){
		browser.clickGuiObject(".id", "reservemore", ".text", "Make More Reservations");
	}

	public boolean checkContinueShoppingExist(){
		return browser.checkHtmlObjectExists(".id", "reservemore", ".text", new RegularExpression("Continue shopping", false));
	}

	public void clickContinueShoppinLink(){
		browser.clickGuiObject(".id", "reservemore", ".text", new RegularExpression("Continue shopping", false));
	}
	  
	public void verifyNoDeliveryTranFeeInShoppingCart() {
		logger.info("Verify there is no Delivery Tran fee.");
		if(this.isDeliveryTranFeeExist()){
			throw new ErrorOnPageException("There should not be Delivery Trans fee");
		}
	}

	public List<String> getAllDeliveryMethods() {
		List<String> methods=new ArrayList<String>();
		IHtmlObject[] tables=browser.getTableTestObject(".id", "table1",".className","items");
		if(tables==null||tables.length<1){
			throw new ObjectNotFoundException("Can't find shopping cart table");
		}
		IHtmlTable table=(IHtmlTable)tables[0];
		List<String> values=table.getColumnValues(1);
		for(String val:values){
			if(val.matches("^\\(.*\\)$")){
				methods.add(val);
			}
		}
		Browser.unregister(tables);
		return methods;
	}
}
