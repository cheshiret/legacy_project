package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:In lottery hunt selection page with group leader application, click continue button will go to this page
 * URL:hfxx-uwppl-torqa3.dev.activenetwork.com/lotteryGroupMembers.do?lotId=901
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jun 5, 2014
 */
public class HFLotteryAddGroupMembersPage extends HFHeaderBar {
	static class SingletonHolder {
		protected static HFLotteryAddGroupMembersPage _instance = new HFLotteryAddGroupMembersPage();
	}

	protected HFLotteryAddGroupMembersPage() {
	}

	public static HFLotteryAddGroupMembersPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] addMoreMembers(){
		return Property.concatPropertyArray(div(), ".id", "LLotteryGroupMemberKit_membersLayout_membersGroup_trigger");
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
		return Property.concatPropertyArray(div(), ".id", new RegularExpression("removeItem_m_"+index+"_\\d+", false));//index from 1
	}
	
	protected Property[] skipThisStepLink(){
		return Property.concatPropertyArray(a(), ".href", "lotteryGroupMembers.do?skip=true");
	}
	
	protected Property[] winNum(){
	    return Property.toPropertyArray(".className", "TextBoxRenderer m", ".id", new RegularExpression("Am_\\d+_\\d+_m_-\\d+", false));
	}
	
	protected Property[] winNum(int index){
	    return Property.toPropertyArray(".className", "TextBoxRenderer m", ".id", new RegularExpression("Am_"+index+"_\\d+_m_-\\d+", false));//index from 1
	}
	
	protected Property[] dateOfBirth(){
	    return Property.toPropertyArray(".className", "DateOfBirthRenderer placeholder placeholder", ".id", new RegularExpression("Am_\\d+_\\d+_m_-\\d+", false));
	}
	
	protected Property[] dateOfBirth(int index){                                                                         
	    return Property.toPropertyArray(".className", "DateOfBirthRenderer placeholder placeholder", ".id", new RegularExpression("Am_"+index+"_\\d+_m_(-)?\\d+", false));//index starts from 1
	}
	
	protected Property[] dateOfBirth_year(int index){
	    return Property.toPropertyArray(".id", new RegularExpression("Am_"+index+"_\\d+_m_-\\d+_year", false));//index starts from 1
	}
	
	protected Property[] dateOfBirth_month(int index){
	    return Property.toPropertyArray(".id", new RegularExpression("Am_"+index+"_\\d+_m_-\\d+_month", false));//index starts from 1
	}
	
	protected Property[] dateOfBirth_day(int index){
	    return Property.toPropertyArray(".id", new RegularExpression("Am_"+index+"_\\d+_m_-\\d+_day", false));//index starts from 1
	}
	
	protected Property[] lotteryControl(){
		return Property.concatPropertyArray(div(), ".className", "privileges control");
	}
	
	protected Property[] h2() {
		return Property.toPropertyArray(".class", "Html.h2");
	}
	/** Page Object Property Definition End */
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(addMoreMembers());
	}
	
	public void clickAddMoreMembers(){
		browser.clickGuiObject(addMoreMembers());
	}
	
	public void clickContinueBtn(){
		browser.clickGuiObject(continueBtn());
	}
	
	public void waitForSuccessMsg(){
		browser.searchObjectWaitExists(msgSuccess(), SLEEP);
	}
	
	public void clickSkipThisStep(){
		browser.clickGuiObject(skipThisStepLink());
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
//		browser.setTextField(dateOfBirth(index), dob);
		String[] dateOfBirth = dob.split("-");
		browser.setTextField(dateOfBirth_year(index), dateOfBirth[0]);
		browser.setTextField(dateOfBirth_month(index), dateOfBirth[1]);
		browser.setTextField(dateOfBirth_day(index), dateOfBirth[2]);
	}
	
	public void setDOB(String dob, int index){
		browser.setTextField(dateOfBirth(index), dob);
	}
	
	public void setDateOfBirth(String dob){
		browser.setTextField(dateOfBirth(), dob);
	}
	
	public String getLotteryName(){
		return browser.getObjectText(Property.atList(lotteryControl(), h2()));
	}
	
	public void verifyLotteryName(String lotteryName){
		String lotteryNameFromUI = getLotteryName();
		if(lotteryNameFromUI.equalsIgnoreCase(lotteryName)){
			logger.info("Successfully verify lottery name:"+lotteryName+" in Lottery Added Members page");
		}else throw new ErrorOnPageException("Failed to verify lottery name in Lottery Added Members pag", lotteryName, lotteryNameFromUI);
	}
	
	/**
	 * 
	 */
	public void goBackToPreviousPg() {
		// TODO Auto-generated method stub
		
	}
}
