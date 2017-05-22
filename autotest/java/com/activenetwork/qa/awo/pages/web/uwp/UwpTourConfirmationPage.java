package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author jchen
 */
public class UwpTourConfirmationPage extends UwpPage {

	private static UwpTourConfirmationPage _instance = null;

	public static UwpTourConfirmationPage getInstance() {
		if (null == _instance)
			_instance = new UwpTourConfirmationPage();

		return _instance;
	}

	public UwpTourConfirmationPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "backhome");
	}

	/**
	 * Retrieve the reservation number.
	 * @return - reservation number
	 */
	public String getResNum() {
		return this.getResNums()[0];
	}
	
	public String[] getResNums() {
		String[] resNums = null;
		RegularExpression resvRE = new RegularExpression(".*tourReservationDetail.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".href", resvRE);
		
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find tour number objects.");
		}
		
		resNums = new String[objs.length];
		for(int i=0; i<objs.length; i++){
			resNums[i] = objs[i].getProperty(".text");
		}
		
		Browser.unregister(objs);
		return resNums;
	}
	
	/**
	 * Go to reservation details page.
	 * @return - reservation number
	 */
	public void clickReservationNum(String resNum_Excepted) {
		int matchedResNum = 0;
		
		RegularExpression resvRE = new RegularExpression(".*tourReservationDetail.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".href", resvRE);
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("Can't find any reservation number objects.");
		}
		
		for(int i=0; i<objs.length; i++){
			String resNum_Actual = objs[i].getProperty(".text");
			if(resNum_Actual.equals(resNum_Excepted)){
				((ILink)objs[i]).click();
				matchedResNum++;
				break;
			}
		}
		
		if(matchedResNum!=1){
			throw new ErrorOnPageException("No matched reservation number can be found.");
		}
		Browser.unregister(objs);
	}
	
	public String clickReservationNum() {
		RegularExpression resvRE = new RegularExpression(".*tourReservationDetail.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".href", resvRE);
		String resvNum = (String) objs[0].getProperty(".text");
		((ILink)objs[0]).click();
		Browser.unregister(objs);
		return resvNum;
	}

	/**
	 * Retrieve the paid amount.
	 * @return - amount paid
	 */
	public String getAmountPaid() {
		IHtmlObject[] objs = browser.getHtmlObject(".className", "br totalarea");
		String text = objs[0].getProperty(".text").toString();
		String[] tokens = RegularExpression.getMatches(text, "\\$[0-9]+\\.[0-9]+");
		String totalpaid = tokens[tokens.length - 2];
		totalpaid = totalpaid.replaceAll("\\$", "");
		Browser.unregister(objs);
		return totalpaid.trim();
	}

	/**
	 * Verify whether the order has been proceed sucessful.
	 */
	public void verifySuccess() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "shophdr");
		String returnStr = ((String) objs[0].getProperty(".text"));
		Browser.unregister(objs);

		if (!returnStr.matches(UwpSchConstants.PASS))
			throw new ItemNotFoundException(
					"The order processing may not be complete.");
	}

	/**
	 * Retrieve the number of reservations in confirmation page.
	 * @return - number of reservations
	 */
	public int getNumOfReservations() {
		RegularExpression resvRE = new RegularExpression(
				".*tourReservationDetail.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".href", resvRE);

		int resNum = objs.length;
		Browser.unregister(objs);

		return resNum;
	}

	/**
	 * Retrieve text on confrimation page.
	 * @return - text on page
	 */
	public String getTextInConfirmPage() {
		IHtmlObject[] obj = browser.getHtmlObject(".id", "shoppingitems1");

		String content = (String) obj[0].getProperty(".text");
		Browser.unregister(obj);

		return content;
	}
	
	/**
	* Retrieve the number of contracts which the order items belong to.
	 * @return - number of contracts
	 */
	public int numberOfContracts() {
		IHtmlObject[] objs = browser.getHtmlObject(".className","partition");
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	public boolean checkDeliveryMethodExist(String method_name,String tourName){
		boolean isExisting=false;
		IHtmlObject[] tables=browser.getTableTestObject(".id","shoppingitems1");
		if(tables==null||tables.length<1){
			throw new ObjectNotFoundException("Can't find Shopping cart table");
		}
		IHtmlTable table=(IHtmlTable)tables[0];
		tourName=tourName.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		int row = table.findRow(2, new RegularExpression("Tour: ?"+tourName,false))+1;
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
		Property[] p1=Property.toPropertyArray(".class", "Html.TR", ".className", "br totalarea");
		Property[] p2=Property.toPropertyArray(".class", "Html.DIV", ".className","subtotal",".text", new RegularExpression("^"+method+".*\\d+\\.\\d{2}",false));
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
	    if(objs==null || objs.length<1){
	    	throw new ObjectNotFoundException("Can't find Delivery method in SubTotal.");
	    }
	    String val=objs[0].text().replaceAll(method, "").trim();
	    Browser.unregister(objs);
	    return val;
	}
	
	public boolean isDeliveryTranFeeExist(String method){
        IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.TR", ".className", "br totalarea");
        if(objs==null || objs.length<1){
        	throw new ErrorOnPageException("Can't find total row..");
        }
        boolean isExist=false;
        String val=objs[0].text();
        if(StringUtil.isEmpty(method)){
	        if(val.matches(".*((Will Call)|(Print at Home)|(Mail Out)).*")){
	        	isExist=true;
	        }
        }else{
        	if(val.contains(method)){
        		isExist=true;
        	}
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
	
	
    
	public void verifyDeliveryMethodExistingOrNot(String method_name,String tourName,boolean isExisting) {
		logger.info("Verify delivery method:"+method_name+" for tour "+tourName+(isExisting?"":" is not "+"existing"));
		if(checkDeliveryMethodExist(method_name,tourName)!=isExisting){
			throw new ErrorOnPageException( "delivery method:"+method_name+" for tour "+tourName+" should"+(isExisting?"":" not ")+" exist");
		}
	}

	public void verifyNoDeliveryTranFee(String method) {
		logger.info("Verify There is no Delivery transaction fee.");
		if(this.isDeliveryTranFeeExist(method)){
			throw new ErrorOnPageException("There should not be Delivery transaction fee.");
		}
	}
	/**
	 * verify no any method existing
	 */
	public void verifyNoDeliveryTranFee() {
		verifyNoDeliveryTranFee((String)null);
	}

	public void verifyBottomBtnText(String text) {
		logger.info("verify button which of text is '"+text+"' existing");
		if(!checkBottomBtnExistByText(text)){
			throw new ErrorOnPageException("Can't find button which of text is '"+text+"'");
		}
	}
	
	public boolean checkBottomBtnExistByText(String text){
		return browser.checkHtmlObjectExists(".class", "Html.Button", ".text", new RegularExpression("^\\s?"+text,false));
	}

	/**
	 * "Verify 'Continue to home' link exists"
	 */
	public void verifyContinueToHomeLinkExist() {
		logger.info("Verify 'Continue to home' link exists");
		if(!isContinueToHomeLinkExist()){
			throw new ErrorOnPageException("Can't find 'Continue to home' link");
		}
	}

    public boolean isContinueToHomeLinkExist(){
    	return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Continue to home");
    }
    
    /**
	 * "Verify 'Continue to Recreation.gov home' link exists"
	 */
	public void verifyContinueToRecHomeLinkExist() {
		logger.info("Verify 'Continue to Recreation.gov home' link exists");
		if(!isContinueToRecHomeLinkExist()){
			throw new ErrorOnPageException("Can't find 'Continue to Recreation.gov home' link");
		}
	}

    public boolean isContinueToRecHomeLinkExist(){
    	return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Continue to Recreation.gov home");
    }

	
	
}
