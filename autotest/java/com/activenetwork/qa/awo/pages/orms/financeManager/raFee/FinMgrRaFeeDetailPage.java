/*
 * Created on Jan 11, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.raFee;

import java.math.BigDecimal;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.feeData.RaFeeInfo;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.NotSupportedException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrRaFeeDetailPage extends FinanceManagerPage {
  
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRaFeeDetailPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrRaFeeDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrRaFeeDetailPage getInstance() {
	  if (null == _instance) {
		_instance = new FinMgrRaFeeDetailPage();
	  }
		return _instance;
	}

	/**
	 * check RA Fee detail page exists or not
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("RA Fee (Schedule )?Details", false));
	}
	
	public void clickCancelRAFee()
	{
		browser.clickGuiObject(".class", "HTML.A", ".text", "Cancel RA Fee");
	}
	
	public void clickVoidRAFee()
	{
		browser.clickGuiObject(".class", "HTML.A", ".text", "Void RA Fee");
	}
	
	public void clickOK()
	{
		browser.clickGuiObject(".class", "HTML.SPAN", ".text", "OK");
	}
	
	public void setCancelVoidReason(String reason){
		browser.checkHtmlObjectExists(".id", "ra_fee_reason");
		browser.setTextArea(".id", "ra_fee_reason", reason);
	}
	/**
	 * Retrive Ra fee schedule info from page.
	 * @return-RaFeeScheduleData
	 */
	public RaFeeScheduleData getRaFeeSchedule() {
	  RaFeeScheduleData	feeSchd = new RaFeeScheduleData();
	  IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Actions Void RA Fee Cancel RA Fee.*", false));
	  String temp = objs[0].getProperty(".text").toString();
	  Browser.unregister(objs);

	  feeSchd.product = temp.substring(temp.indexOf("Product")+"Product".length(), temp.indexOf("Revenue Location")).trim();
	  feeSchd.location = temp.substring(temp.indexOf("Revenue Location")+"Revenue Location".length(), temp.indexOf("Sales Channel")).trim();
	  feeSchd.salesChannel = temp.substring(temp.indexOf("Sales Channel")+"Sales Channel".length(), temp.indexOf("Transaction Type")).trim();
	  feeSchd.tranType = temp.substring(temp.indexOf("Transaction Type")+"Transaction Type".length(), temp.indexOf("Transaction Occurrence")).trim();
	  
	  feeSchd.feeId = temp.substring(temp.indexOf("RA Fee Schedule ID")+"RA Fee Schedule ID".length(), temp.indexOf("Unit")).trim();
	  feeSchd.unitOption = temp.substring(temp.indexOf("Unit")+"Unit".length(), temp.indexOf("Number of Unit")).trim();
	  feeSchd.baseRate = temp.substring(temp.indexOf("Rate")+"Rate".length(), temp.indexOf("Account")).trim().replaceFirst("\\$","");
	  feeSchd.acctCode = temp.substring(temp.indexOf("Account")+"Account".length(),temp.indexOf("Threshold Schedule ID")).trim();

	  if(temp.contains("License Year")) //for RA fee of license manager
	  {
		  feeSchd.tranOccur = temp.substring(temp.indexOf("Transaction Occurrence")+"Transaction Occurrence".length(), temp.indexOf("Location Class")).trim();
		  feeSchd.locationClass = temp.substring(temp.indexOf("Location Class")+"Location Class".length(),temp.indexOf("License Year")).trim();
		  feeSchd.licenseYearOfCurrentRAFee = temp.substring(temp.indexOf("License Year")+"License Year".length(),temp.indexOf("Pricing Info")).trim();
	  }else{//for RA fee of others.
		  feeSchd.tranOccur = temp.substring(temp.indexOf("Transaction Occurrence")+"Transaction Occurrence".length(), temp.indexOf("Pricing Info")).trim();	  
	  }
	  return feeSchd;
	}
	
	/**
	 * Get Order number from page
	 * @return
	 */
	public String getOrdNum() {
	  IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Actions Void RA Fee Cancel RA Fee.*", false));
	  String temp = objs[0].getProperty(".text").toString();
	  Browser.unregister(objs);
	  
	  return temp.substring(temp.indexOf("Order")+"Order".length(), temp.indexOf("Product")).trim();//updated by pzhu
	}
	
	/**
	 * This method used to verify price is correct.
	 *
	 */
	public void verifyPriceCorrect() {
	  IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Actions Void RA Fee Cancel RA Fee.*", false));
	  String temp = objs[0].getProperty(".text").toString();
	  Browser.unregister(objs);
	  String price = temp.substring(temp.indexOf("Price") + "Price".length(), temp.indexOf("Date/Time")).trim().replaceFirst("\\$","");//updated by pzhu, delete blank space behind Price
	  String unit = temp.substring(temp.indexOf("Unit") + "Unit".length(), temp.indexOf("Number of Unit")).trim();//updated by pzhu, delete blank space behind Unit
	  String rate = temp.substring(temp.indexOf("Rate") + "Rate".length(), temp.indexOf("Account")).trim().replaceFirst("\\$","");//updated by pzhu, delete blank space behind Rate

	  if(unit.equalsIgnoreCase("Transaction")) {
	    if(!price.equals(rate)) {
	      throw new ItemNotFoundException("Price "+price+" not correct!");
	    }
	  } else if (unit.equalsIgnoreCase("Unit")) {
	    String unitNum = temp.substring(temp.indexOf("Number of Unit") + "Number of Unit".length(),temp.indexOf("Rate")).trim();//updated by pzhu, delete blank space behind Number of Unit
	    if(!(Math.abs(Double.parseDouble(price)-Double.parseDouble(rate)*Double.parseDouble(unitNum))<0.0000001)) {
	      throw new ItemNotFoundException("Price " + price + " not correct!");
	    }
	  } else {
	    throw new ItemNotFoundException("Unknown Unit"+unit);
	  }
	  
	}
	
	/**
	 * This method used to verify Ra Fee Details Info
	 * @param resNum
	 * @param schedule
	 */
	public void verifyRaFeeDetail(String resNum,RaFeeScheduleData schedule) {
	  	logger.info("Start to verify RA Fee Detail.");
	  	
	  	if(!resNum.equalsIgnoreCase(getOrdNum())) {
	  	  throw new ItemNotFoundException("Order Number " + getOrdNum() + " not Correct.");
	  	}
	  	verifyPriceCorrect();
	  	RaFeeScheduleData feeSchd = getRaFeeSchedule();
	  	if(!schedule.product.equalsIgnoreCase(feeSchd.product)) {
	  	  throw new ItemNotFoundException("Prodcut " + feeSchd.product + " not Correct. Expect one is:"+schedule.product);
	  	}
	  	if(!schedule.location.equalsIgnoreCase(feeSchd.location)) {
	  	  throw new ItemNotFoundException("Revenue Location " + feeSchd.location + " not Correct. Expect one is:"+schedule.location);
	  	}
	  	if(!schedule.salesChannel.equalsIgnoreCase(feeSchd.salesChannel)) {
	  	  throw new ItemNotFoundException("Sales Channel " + feeSchd.salesChannel + " not Correct. Expect one is:"+schedule.salesChannel);
	  	}
	  	if(!schedule.tranType.equalsIgnoreCase(feeSchd.tranType)) {
	  	  throw new ItemNotFoundException("Transaction Type " + feeSchd.tranType + " not Correct. Expect one is:"+schedule.tranType);
	  	}
	  	if(!schedule.tranOccur.equalsIgnoreCase(feeSchd.tranOccur)) {
	  	  throw new ItemNotFoundException("Transaction Occurrence " + feeSchd.tranOccur + " not Correct. Expect one is:"+schedule.tranOccur);
	  	}
	  	if(!schedule.feeId.equalsIgnoreCase(feeSchd.feeId)) {
	  	  throw new ItemNotFoundException("Fee Schedule ID " + feeSchd.feeId + " not Correct. Expect one is:"+schedule.feeId);
	  	}
	  	if(!schedule.unitOption.equalsIgnoreCase(feeSchd.unitOption)) {
	  	  throw new ItemNotFoundException("Unit " + feeSchd.unitOption + " not Correct. Expect one is:"+schedule.unitOption);
	  	}
	  	if(!MiscFunctions.compareResult("Rate", schedule.baseRate.replaceAll("\\$", ""), feeSchd.baseRate)){
	  		
//	  	}
//	  	if(!schedule.baseRate.equalsIgnoreCase(feeSchd.baseRate)) {
	  	  throw new ItemNotFoundException("Rate " + feeSchd.baseRate + " not Correct. Expect one is:"+schedule.baseRate);
	  	}
//	  	if(!schedule.acctCode.equalsIgnoreCase(feeSchd.acctCode)) {
//	  	  throw new ItemNotFoundException("Account " + feeSchd.acctCode + " not Correct. Expect one is:"+schedule.acctCode);
//	  	}
	  	if(!feeSchd.acctCode.contains(schedule.acctCode)) {
		  	  throw new ItemNotFoundException("Account " + feeSchd.acctCode + " not Correct. Expect one is:"+schedule.acctCode);
		 }
	}
	
	public String getReasonForCancelOrVoid(){
		return browser.getTextAreaValue(".id","reason");
	}
	
	public void compareReasonFroCancelOrVoidInfo(String expNote){
		String value = this.getReasonForCancelOrVoid();
		boolean result = MiscFunctions.compareResult("Cancellation Note", expNote, value);
		
		if(!result){
			throw new ErrorOnPageException("The cancellation reason is not correct.");
		}
	}
	
	public String getUnit(){
		return this.getTextOfDIV("Unit");
	}
	
	public String getNumberOfUnit(){
		return this.getTextOfDIV("Number of Unit");
	}
	
	public String getRate(){
		return this.getTextOfDIV("Rate");
	}
	
	public String getThresholdSchdID(){
		return this.getTextOfDIV("Threshold Schedule ID");
	}
	
	public String getThresholdCount(){
		return this.getTextOfDIV("Threshold Count");
	}
	
	public String getPrice(){
		return this.getTextOfDIV("Price");
	}
	
	private String getTextOfDIV(String name){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^" + name + ".*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not the DIV Object, name = " + name);
		}
		IHtmlObject topObj = name.equalsIgnoreCase("Price") ? objs[objs.length  - 1] : objs[0];
		IHtmlObject labelObjs[] = browser.getHtmlObject(".class", "Html.LABEL", topObj);
		if(labelObjs.length < 1) {
			throw new ItemNotFoundException("Cannot found Label object.");
		}
		String label = labelObjs[0].text();
		String text = topObj.text().replaceFirst(label, StringUtil.EMPTY).trim();
		
		Browser.unregister(objs);
		Browser.unregister(labelObjs);
		Browser.unregister(topObj);
		return text;
	}
	
	public RaFeeInfo getRAFeePricingInfo(){
		RaFeeInfo raFeeInfo = new RaFeeInfo();
		
		raFeeInfo.setPrice(this.getPrice().replaceAll("(\\$)|,|\\(|\\)", ""));
		raFeeInfo.setUnit(this.getUnit());
		raFeeInfo.setNumberOfUnit(this.getNumberOfUnit());
		raFeeInfo.setRaFeeRate(this.getRate().replaceAll("\\$", ""));
		raFeeInfo.setThresholdScheduleID(this.getThresholdSchdID());
		raFeeInfo.setThresholdCount(this.getThresholdCount());
		
		return raFeeInfo;
	}
	
	public void compareRAFeePricingInfo(RaFeeInfo expRaFeeInfo){
		RaFeeInfo actRaFeeInfo = this.getRAFeePricingInfo();
		boolean result = true;
		
		if(expRaFeeInfo.getPrice().trim().length()>0){
			result &= MiscFunctions.compareResult("Price", 
					new BigDecimal(expRaFeeInfo.getPrice()), new BigDecimal(actRaFeeInfo.getPrice()));
		}else {
			result &= MiscFunctions.compareResult("Price", 
					expRaFeeInfo.getPrice(), actRaFeeInfo.getPrice());
		}
		result &= MiscFunctions.compareResult("Unit", 
				expRaFeeInfo.getUnit(), actRaFeeInfo.getUnit());
		
		result &= MiscFunctions.compareResult("Number of Unit", 
				expRaFeeInfo.getNumberOfUnit(), actRaFeeInfo.getNumberOfUnit());
		
		if(expRaFeeInfo.getRaFeeRate().trim().length()>0){
			result &= MiscFunctions.compareResult("Rate", 
					new BigDecimal(expRaFeeInfo.getRaFeeRate()), new BigDecimal(actRaFeeInfo.getRaFeeRate()));
		}else {
			result &= MiscFunctions.compareResult("Rate", 
					expRaFeeInfo.getRaFeeRate(), actRaFeeInfo.getRaFeeRate());
		}
		
		if(null !=expRaFeeInfo.getThresholdScheduleID()){
			result &= MiscFunctions.compareResult("Threshold Schedule ID", 
					expRaFeeInfo.getThresholdScheduleID(),  actRaFeeInfo.getThresholdScheduleID());
		}
		
		if(null != expRaFeeInfo.getThresholdCount()){
			result &= MiscFunctions.compareResult("Threshold Count",
					expRaFeeInfo.getThresholdCount(), actRaFeeInfo.getThresholdCount());
		}		
		
		if(!result){
			throw new ErrorOnDataException("The RA Fee pricing info not correct, please check.");
		}				
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A",".text","Cancel");
	}
	
	public String getAssignLoop() {
		return browser.getDropdownListValue(".id", "assignment_loop", 0);
	}
	
	public void cancelVoidRAFee(String action,String reason){
		if(action.equalsIgnoreCase("Cancel RA Fee")){
			clickCancelRAFee();
		}else if(action.equalsIgnoreCase("Void RA Fee")){
			clickVoidRAFee();
		}else{
			throw new NotSupportedException("Unknown action "+action);
		}
		this.waitLoading();
		setCancelVoidReason(reason);
		clickOK();
	}
}
