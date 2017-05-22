package com.activenetwork.qa.awo.pages.web.hf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * URL:.../privilegeHostHunters.do
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Aug 1, 2014
 */
public class HFHunterHostLicensePage extends HFHeaderBar {
	static class SingletonHolder {
		protected static HFHunterHostLicensePage _instance = new HFHunterHostLicensePage();
	}

	protected HFHunterHostLicensePage() {
	}

	public static HFHunterHostLicensePage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] pageTitle(){
		return Property.concatPropertyArray(div(), ".id", "pagetitle");
	}
	
	protected Property[] h1(){
		return Property.toPropertyArray(".class", "Html.h1");
	}
	
	protected Property[] caption(){
		return Property.concatPropertyArray(span(), ".className", "caption");
	}
	
	protected Property[] addAnotherHunter(){
		return Property.concatPropertyArray(div(), ".id", "LPrivilegeHostHuntersKit_membersLayout_membersGroup_trigger");
	}
	
	protected Property[] continueBtn(){
		return Property.toPropertyArray(".id", "submitForm_submitForm");
	}
	
	protected Property[] msgSuccess(){
		return Property.concatPropertyArray(div(), ".className", "msg success");
	}
	
	protected Property[] remove(){
		return Property.concatPropertyArray(div(), ".id", new RegularExpression("removeItem_m_\\d+_\\d+", false));
	}
	
	protected Property[] remove(int index){
		return Property.concatPropertyArray(div(), ".id", new RegularExpression("removeItem_m_"+index+"_\\d+", false));//index starts from 1
	}
	
	protected Property[] back(){
		return Property.concatPropertyArray(a(), ".href", new RegularExpression("/privilegePurchaseDetails.do?privId=\\d+", false));
	}
	
	protected Property[] winNum(){
	    return Property.toPropertyArray(".className", "TextBoxRenderer m", ".id", new RegularExpression("Am\\d+_\\d+_\\d+", false));
	}
	
	protected Property[] winNum(int index){
	    return Property.toPropertyArray(".className", "TextBoxRenderer m", ".id", new RegularExpression("Am_"+index+"_\\d+_\\d+", false));//index starts from 1
	}
	
	protected Property[] dateOfBirth_year(){
	    return Property.toPropertyArray(".id", new RegularExpression("Am_\\d+_\\d+_-\\d+_year", false));
	}
	
	protected Property[] dateOfBirth_year(int index){
	    return Property.toPropertyArray(".id", new RegularExpression("Am_"+index+"_\\d+_-\\d+_year", false));//index starts from 1
	}
	
	protected Property[] dateOfBirth_month(int index){
	    return Property.toPropertyArray(".id", new RegularExpression("Am_"+index+"_\\d+_-\\d+_month", false));//index starts from 1
	}
	
	protected Property[] dateOfBirth_day(int index){
	    return Property.toPropertyArray(".id", new RegularExpression("Am_"+index+"_\\d+_-\\d+_day", false));//index starts from 1
	}
	
	protected Property[] privilegesControl(){
	    return Property.concatPropertyArray(div(), ".className", "privileges control");
	}
	
	protected Property[] h2(){
		return Property.toPropertyArray(".class", "Html.h2");
	}
	
	protected Property[] underLineGrey(){
		return Property.concatPropertyArray(div(), ".className", "underlineGrey");
	}
	
	protected Property[] enterDirective(){
		return Property.concatPropertyArray(div(), ".className", "enterDirective");
	}
	
	protected Property[] errorMsg(String errorMsgRegx){
		return Property.concatPropertyArray(div(), ".className", "error_item", ".text", new RegularExpression(errorMsgRegx, false));
	}
	
	protected Property[] successfulMsg(String successfulMsgRegx){
		return Property.concatPropertyArray(div(), ".className", "msg success", ".text", new RegularExpression(successfulMsgRegx, false));
	}
	
	protected Property[] memberName(){
		return Property.concatPropertyArray(div(), ".className", "memberName");
	}
	
	protected Property[] hhInfo(){
		return Property.concatPropertyArray(div(), ".className", "hhInfo");
	}
	
	protected Property[] hhTitle(){
		return Property.concatPropertyArray(span(), ".className", "hhTitle");
	}
	
	protected Property[] hhItems(){
		return Property.concatPropertyArray(div(), ".className", "hhItems");
	}
	
	protected Property[] hhItem(){
		return Property.concatPropertyArray(span(), ".className", "hhItem");
	}
	
	/** Page Object Property Definition End */
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(addAnotherHunter());
	}
    
	public String getPageTitle(){
		return browser.getObjectText(Property.atList(pageTitle(), h1()));
	} 
	
	public String getPageDescription(){
		return browser.getObjectText(Property.atList(pageTitle(), caption()));
	}
	
	public String getprivilegeName(){
		return browser.getObjectText(Property.atList(privilegesControl(), h2()));
	}

	public String getLicenceYear(){
		return browser.getObjectText(Property.atList(privilegesControl(), underLineGrey()));
	}
	
	public String getEnterDerective(){
		return browser.getObjectText(enterDirective());
	}
	
	public void clickAddAnotherHunter(){
		browser.clickGuiObject(addAnotherHunter());
	}
	
	public boolean isAddAnotherHunterLinkExist(){
		IHtmlObject[] objs = browser.getHtmlObject(addAnotherHunter());
		boolean existed = true;
		
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find Add another hunter objects");
		}else {
			existed = objs[0].style("visibility").trim().equalsIgnoreCase("visible");
		}
		
		Browser.unregister(objs);
		return existed;
	}
	
	public void clickContinueBtn(){
		browser.clickGuiObject(continueBtn());
	}
	
	public void waitForSuccessMsg(){
		browser.searchObjectWaitExists(msgSuccess(), SLEEP);
	}
	
	public void clickBack(){
		browser.clickGuiObject(back());
	}
	
	public boolean isRemoveLinkExist(){
		IHtmlObject[] objs = browser.getHtmlObject(remove());
		boolean existed = true;
		
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find Remove objects");
		}else {
			existed = objs[0].style("display").trim().equalsIgnoreCase("BLOCK");
		}
		
		Browser.unregister(objs);
		return existed;
	}
	
	public void clickRemove(){
		browser.clickGuiObject(remove());
	}
	
	public void clickRemove(int index){
		browser.clickGuiObject(remove(index));
	}
	
	public void waitForRemove(int index){
		browser.searchObjectWaitExists(remove(index), SLEEP);
	}
	
	public void setWinNum(String winNum, int index){
		browser.setTextField(winNum(index), winNum);
	}
	
	public void setWinNum(String winNum){
		browser.setTextField(winNum(), winNum);
	}
	
	public void setDateOfBirth(String dob, int index){
		String year = StringUtil.EMPTY;
		String month = StringUtil.EMPTY;
		String day = StringUtil.EMPTY;
		
		if(StringUtil.notEmpty(dob)){
			String[] dateOfBirth = dob.split("-");
			year = dateOfBirth[0];
			month = dateOfBirth[1];
			day = dateOfBirth[2];
		}
		
		browser.setTextField(dateOfBirth_year(index), year);
		browser.setTextField(dateOfBirth_month(index), month);
		browser.setTextField(dateOfBirth_day(index), day);
	}
	
	public void setDateOfBirth(String dob){
		setDateOfBirth(dob, 0);
	}
	
	public void setHunters(List<Customer> hunters){
		if (null != hunters && hunters.size() > 0) {
			for (int i = 0; i < hunters.size(); i++) {
				clickAddAnotherHunter();
				waitForRemove(i + 1);
				setWinNum(hunters.get(i).custNum, i + 1);
				setDateOfBirth(hunters.get(i).dateOfBirth, i + 1);
			}
		}
	}
	
	public void setHunter(Customer hunter){
		setHunters(Arrays.asList(hunter));
	}
	
	public int getNumOfHunterHost(){
		IHtmlObject[] objs = browser.getHtmlObject(dateOfBirth_year());
		int number;
		
		if(objs.length<1){
			throw new ErrorOnPageException("Can't find any hunter host objects.");
		}else number = objs.length;
		
		Browser.unregister(objs);
		return number;
	}
	
	public int getNumOfRemoveLink(){
		IHtmlObject[] objs = browser.getHtmlObject(remove());
		int number;
		
		if(objs.length<1){
			throw new ErrorOnPageException("Can't find any remove link.");
		}else number = objs.length;
		
		Browser.unregister(objs);
		return number;
	}
	
	public boolean isErrorMsgExist(String errorMsgRegx){
		return browser.checkHtmlObjectExists(errorMsg(errorMsgRegx));
	}
	
	public boolean isSuccessfulMsgExist(String successfulMsgRegx){
		return browser.checkHtmlObjectExists(successfulMsg(successfulMsgRegx));
	}
	
	public String getHunterName(){
		return browser.getObjectText(memberName());
	}
	
	public boolean checkHunterName(String fName, String mName, String lName){
		return getHunterName().equals(fName+" "+(StringUtil.notEmpty(mName)?mName+" ":" ")+lName);
	}
	
	public boolean isHunterNameObjsExist(){
		return browser.checkHtmlObjectExists(memberName());
	}
	
	public String getHunterHostInfoTitle(){
		return browser.getObjectText(Property.atList(hhInfo(), hhTitle()));
	}
	
	public String getHunterHostItem(){
		return browser.getObjectText(Property.atList(hhItems(), hhItem()));
	}
	
	public List<String> getHunterHostItems(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(hhItems(), hhItem()));
		List<String> hunterHostItems = new ArrayList<String>();
		
		if(objs.length<1){
			throw new ItemNotFoundException("Can't find any hunter host item objs");
		}else {
			for(int i=0; i<objs.length; i++){
				hunterHostItems.add(objs[i].text());
			}
		}
		Browser.unregister(objs);
		return hunterHostItems;
	}
	
	public boolean checkHunterHostItems(List<Customer> hunters){
		List<String> fromUI = getHunterHostItems();
		boolean result = true; 
		
		for(int i=0; i<hunters.size(); i++){
			Customer cus = hunters.get(i);
			result &= MiscFunctions.compareString(i+"-Hunter Host Item", fromUI.get(i), cus.fName+" "+(StringUtil.notEmpty(cus.mName)?cus.mName+" ":" ")+cus.lName);
		}
		
		return result;
	}
	
	public void verifyHunterHostItems(List<Customer> hunters){
		if(!checkHunterHostItems(hunters)){
			throw new ErrorOnPageException("Failed to verify hunter host items.");
		}else logger.info("Successfully verify hunter host items.");
	}
}
