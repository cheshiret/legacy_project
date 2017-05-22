package com.activenetwork.qa.awo.pages.orms.common.lottery;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo.lotteryChoice;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class OrmsLotteryApplicationDetailsPage extends OrmsPage {
	private static OrmsLotteryApplicationDetailsPage _instance = null;//new PermitMgrOrderDetailsPage();

	public OrmsLotteryApplicationDetailsPage() {

	}

	public static OrmsLotteryApplicationDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsLotteryApplicationDetailsPage();
		}
		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text","Lottery Application Detail")|| browser.checkHtmlObjectExists(".id", "LotteryOrderActions");
//		return browser.checkHtmlObjectExists(".id", "LotteryOrderActions");//this id from permit lottery detail page
	}
	
	public void clickHistory(){
		browser.clickGuiObject(".class","Html.A",".text","History");
	}
	
	public void clickAccept(){
		browser.clickGuiObject(".class","Html.A",".text","Accept");
	}
	
	public void clickRevoke(){
		browser.clickGuiObject(".class","Html.A",".text","Revoke");
	}
	
	public void clickFee(){
		IHtmlObject[] frames=getTransactionFrame();
		browser.clickGuiObject(".class","Html.A",".text","Fees",false,0,frames[0]);
		Browser.unregister(frames);
	}
	
	public void clickVoid(){
		browser.clickGuiObject(".class","Html.A",".text","Void");
	}
	
	public void clickContinue(){
		browser.clickGuiObject(".class","Html.A",".text","Continue");
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	public void clickAddToCart(){
		browser.clickGuiObject(".class","Html.A",".text","Add to Cart");
	}
	
	public void clickViewPermitOrder(){
		browser.clickGuiObject(".class", "Html.A",".text","View Permit Order");
	}
	
	public String getLotteryInfo(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^Lottery Application.+ Lottery Name",false));
		String temp=objs[1].text();
		
		Browser.unregister(objs);
		return temp;
	}
	
	public void changeArea(String area){
		browser.selectDropdownList(".id","pref_2522_0",area);
		ajax.waitLoading();
	}
	
	public void changeArrivalDate(String aDate){
		browser.setTextField(".id", "pref_2520_0_ForDisplay", aDate);
	}
	
	public void changeStayLength(String length){
		browser.setTextField(".id", "pref_2315_0", length);
	}
	
	public void changeOtherOcc(String occ){
		browser.setTextField(".id", "pref_2528_0",occ);
	}
	
	public void changeVehicles(String vel){
		browser.setTextField(".id", "pref_2529_0", vel);
	}
	
	public String getVehicles(){
		String vehicles=browser.getTextFieldValue(".id","pref_2529_0");
		return vehicles;
	}
	
	public String getOtherOcc(){
		String otherOcc=browser.getTextFieldValue(".id","pref_2528_0");
		return otherOcc;
	}
	
	public String getCurrentArrivalDate(){
		String arrivalDate=browser.getTextFieldValue(".id","pref_2520_0_ForDisplay");
		return arrivalDate;
	}
	
	public void selectPaymentType(String type){
		if(browser.checkHtmlObjectExists(".id", "creditCardType")){
			browser.selectDropdownList(".id", "creditCardType", type);
		}
	}
	
	public void setCreditNum(String num){
		if(browser.checkHtmlObjectExists(".id", "creditCardNumber")){
			browser.setTextField(".id", "creditCardNumber", num);
		}
	}
	
	public void setCreditMonth(String month){
		if(browser.checkHtmlObjectExists(".id", "expiryMonth")){
			browser.setTextField(".id", "expiryMonth", month);
		}
	}
	
	public void setCreditYear(String year){
		if(browser.checkHtmlObjectExists(".id", "expiryYear")){
			browser.setTextField(".id", "expiryYear", year);
		}
	}
	
	public void setCreditName(String name){
		if(browser.checkHtmlObjectExists(".id", "cardHolderName")){
			browser.setTextField(".id", "cardHolderName", name);
		}
	}
	
	public void setPayment(Payment pay){
		String payTypeShortName = "";
		if(pay.payType.equalsIgnoreCase("MasterCard")) {
			payTypeShortName = "MAST";
		} else if(pay.payType.equalsIgnoreCase("Visa")) {
			payTypeShortName = "VISA";
		}
		this.selectPaymentType(payTypeShortName);
		this.setCreditNum(pay.creditCardNumber);
		this.setCreditMonth(pay.expiryMon);
		this.setCreditYear(pay.expiryYear);
		this.setCreditName(pay.cardHolder);
	}
	public String getCurrentArea(){
		String curArea=browser.getDropdownListValue(".id","pref_2522_0",0);
		return curArea;
	}
	
	public String getStayLength(){
		String length=browser.getTextFieldValue(".id","pref_2315_0");
		return length;
	}
	/**Get the error message*/
	public String getIncorrectSettingErrorMessage(){
		IHtmlObject[] objs=browser.getHtmlObject(".id","com.reserveamerica.system.order.api.DataSpecificationViolationEx");
		String errorMessage=objs[0].text();
		
		Browser.unregister(objs);
		return errorMessage;
	}
	
	public void setTotalGroupSize(String totalGroupSize){
		browser.setFileField(".id", "totalGroupSize", totalGroupSize);
	}
	
	public void setTicketLotteryInfo(List<lotteryChoice> choices){
		String willCall = "Will Call";
		String printAtHome = "Print at Home";
		String mailOut = "Mail Out";
		

		int size = choices.size();

		if(choices.size()<1)
		{
			throw new ErrorOnDataException("lotteryChoice cannot < 1.....");
		}

		lotteryChoice c =choices.get(0); 
		int typeSize = c.types.length;//types for each choices should be equal(defined in lottery program, max choices!!!)
		for(int i=0; i<typeSize; i++)
		{
			IHtmlObject[] tds = browser.getHtmlObject(".class", "Html.TD", ".text", c.types[i]);
			int len=tds.length;//len=choices.size()
			
			if(len<1)
			{
				throw new ErrorOnPageException("Cannot find tickect type-->"+c.types[i]);
			}
			
			for(int j=0;j<len;j++)
			{
				IHtmlObject top = (IHtmlObject)tds[j].getParent();
				browser.setTextField(".id", new RegularExpression("LotteryTicketTypeView-\\d+\\.qtyStr",false), choices.get(j).typeNums[i], top);
				tds[j].click();//refresh the page, populate ajaxSynchronize.
				ajax.waitLoading();
			}
			Browser.unregister(tds);
		}
		
		for(int m=0; m<size; m++)
		{
			browser.selectDropdownList(".id", new RegularExpression("TicketLotteryPreferenceView-\\d+\\.date",false), choices.get(m).tourDate, m);
			ajax.waitLoading();
			browser.selectDropdownList(".id", new RegularExpression("TicketLotteryPreferenceView-\\d+\\.tourTimeBean",false), choices.get(m).tourTime, m);
			//ajax.waitLoading();
			if(choices.get(m).deliveryMethod.equalsIgnoreCase(willCall))
			{
				browser.selectRadioButton(".id", "Pref_1-DeliveryMethod_3");
			}else if(choices.get(m).deliveryMethod.equalsIgnoreCase(printAtHome))
			{
				browser.selectRadioButton(".id", "Pref_1-DeliveryMethod_2");
			}else if(choices.get(m).deliveryMethod.equalsIgnoreCase(mailOut))
			{
				browser.selectRadioButton(".id", "Pref_1-DeliveryMethod_1");
			}	
			ajax.waitLoading();
		}
	
	
		

		
	}
	
	public void setTicketQuantity(String value, int index)
	{
		
	}
	
	public String getErrorMessage(){
//		HtmlObject[] objs=browser.getHtmlObject(".id","NOTSET");
		IHtmlObject[] objs=browser.getHtmlObject(".id","statusMessages");  //changed to 'statusMessages' from build 3.03.00.243
		String errorMessage=objs[0].text();
		
		Browser.unregister(objs);
		return errorMessage;
	}
	
	public String getErrorMessageDiv(){
//		HtmlObject[] objs=browser.getHtmlObject(".id","NOTSET");
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV", ".className", "message msgerror");  //changed to 'statusMessages' from build 3.03.00.243
		String errorMessage=objs[0].text();
		
		Browser.unregister(objs);
		return errorMessage;
	}
	
	private String getSpanValue(String title){
		String text = browser.getObjectText(".class", "Html.SPAN", ".text", new RegularExpression("^"+title, false));//Sara[11/29/2013] DIV is changed to SPAN
		return StringUtil.getSubString(text, title);
	}

	public String getStatus(){
		return this.getSpanValue("Status");
	}
	
	public void clickChangeCCInfo(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Change Credit Card Info", false));
	}

	public void changeCCInfoInLotteryAppDetailPg(Payment pay){
		this.clickChangeCCInfo();
		this.setPayment(pay);
	}
	
	public String getPaymentType(){
		Property p[] = new Property[3];
		p[0] = new Property(".class", "Html.SPAN");//Sara[11/29/2013] Div
		p[1] = new Property(".className", new RegularExpression("inputwithrubylabel readonly", false));
		p[2] = new Property(".text", "Payment Type");

		Property p1[] = new Property[1];
		p1[0] = new Property(".className", "readonly");
		IHtmlObject[] objs = browser.getHtmlObject(p);
		String text = browser.getTextFieldValue(p1, objs[0]);
		return text;
	}
	
	public String getHolderName(){
		Property p[] = new Property[3];
		p[0] = new Property(".class", "Html.SPAN");//Sara[11/29/2013] Div
		p[1] = new Property(".className", new RegularExpression("inputwithrubylabel readonly", false));
		p[2] = new Property(".text", "Credit Card Holder's Name");

		Property p1[] = new Property[1];
		p1[0] = new Property(".className", "readonly");
		IHtmlObject[] objs = browser.getHtmlObject(p);
		String holder = browser.getTextFieldValue(p1, objs[0]);
		return holder.trim();
	}
}
