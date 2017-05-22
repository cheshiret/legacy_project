package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ApplicationStatusCheck;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Dec 5, 2011
 */
public class LicMgrVendorApplicationPage extends LicMgrCommonTopMenuPage {

	private static LicMgrVendorApplicationPage instance=null;
	private String prefixReg = "^VendorView-[0-9]*.";
	
	private LicMgrVendorApplicationPage(){
	}
	
	public static LicMgrVendorApplicationPage getInstance() {
		if(instance ==null) {
			instance = new LicMgrVendorApplicationPage();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV",".id","vendorApplicationUI");
	}
	
	public String getAppReceivedDate(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefixReg+"vendorApp.appReceivedDate",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("There is not a DIV which id = "+prefixReg+"vendorApp.appReceivedDate");
		}
		String s = objs[0].getProperty(".text").replaceAll("Application Received Date", "").trim();
		Browser.unregister(objs);
		return s;
	}

	public String getApplicantName(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefixReg+"vendorApp.applicantName",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("There is not a DIV which id = "+prefixReg+"vendorApp.applicantName");
		}
		String s = objs[0].getProperty(".text").replaceAll("Applicant Name", "").trim();
		Browser.unregister(objs);
		return s;
	}
	
	public String getApplicantPhone(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefixReg+"vendorApp.applicantPhone",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("There is not a DIV which id = "+prefixReg+"vendorApp.applicantPhone");
		}
		String s = objs[0].getProperty(".text").replaceAll("Applicant Phone", "").trim();
		Browser.unregister(objs);
		return s;
	}
	
	public String getApplicantEmail(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefixReg+"vendorApp.applicantEmail",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("There is not a DIV which id = "+prefixReg+"vendorApp.applicantEmail");
		}
		String s = objs[0].getProperty(".text").replaceAll("Applicant Email", "").trim();
		Browser.unregister(objs);
		return s;
	}
	
	public String getNumOfAgentsRequested(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefixReg+"vendorApp.numOfStoresRequested",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("There is not a DIV which id = "+prefixReg+"vendorApp.numOfStoresRequested");
		}
		String s = objs[0].getProperty(".text").replaceAll("# of Agents Requested", "").trim();
		Browser.unregister(objs);
		return s;
	}
	
	public String getEquipmentPerAgentRequested(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefixReg+"vendorApp.equipPerStoreRequested",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("There is not a DIV which id = "+prefixReg+"vendorApp.equipPerStoreRequested");
		}
		String s = objs[0].getProperty(".text").replaceAll("Equipment Per Agent Requested", "").trim();
		Browser.unregister(objs);
		return s;
	}
	
	public String getAppCreationDate(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefixReg+".createDate",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("There is not a DIV which id = "+prefixReg+"vendorApp.createDate");
		}
		String s = objs[0].getProperty(".text").replaceAll("Creation Date", "").trim();
		Browser.unregister(objs);
		return s;
	}
	
	public String getAppCreationUser(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefixReg+"createUser.name",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("There is not a DIV which id = "+prefixReg+"createUser.name");
		}
		String s = objs[0].getProperty(".text").replaceAll("Creation User", "").trim();
		Browser.unregister(objs);
		return s;
	}
	
	public String getVendorNumber(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefixReg + ".vendorNum",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Did not found vendor number object.");
		}
		String s = objs[0].getProperty(".text").replaceAll("Vendor #", "").trim();
		Browser.unregister(objs);
		return s;
	}
	
	public String getVendorStatus(){
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.DIV", ".text", new RegularExpression("^Status.*",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Did not found vendor status object.");
		}
		String s = objs[0].getProperty(".text").replaceAll("Status", "").trim();
		Browser.unregister(objs);
		return s;
	}
	
	public String getVendorName(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefixReg + ".vendorName",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Did not found vendor number object.");
		}
		String s = objs[0].getProperty(".text").replaceAll("Vendor Name", "").trim();
		Browser.unregister(objs);
		return s;
	}
	
	public String getOwnerName(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefixReg + ".ownerName",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Did not found Owner Name object.");
		}
		String s = objs[0].getProperty(".text").replaceAll("Owner Name", "").trim();
		Browser.unregister(objs);
		return s;
	}
	
	public String getVendorType(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefixReg + ".typeNam.*",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Did not found vendor type object.");
		}
		String s = objs[0].getProperty(".text").replaceAll("Vendor Type", "").trim();
		Browser.unregister(objs);
		return s;
	}
	
	public String getVendorCreationDate(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefixReg + ".createDate.*",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Did not found vendor creation date object.");
		}
		String s = objs[0].getProperty(".text").replaceAll("Creation Date", "").trim();
		Browser.unregister(objs);
		return s;
	}
	
	public String getVendorCreationUser(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefixReg + ".createUser.name",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Did not found vendor creation user object.");
		}
		String s = objs[0].getProperty(".text").replaceAll("Creation User", "").trim();
		Browser.unregister(objs);
		return s;
	}
	
	public boolean getByPassCheckedInfoByIndex(int index){
		IHtmlObject[] objs = browser.getCheckBox(".id", new RegularExpression("CheckboxExt-\\d+\\.checked",false));
		if(objs.length<1 && objs.length<index){
			throw new ItemNotFoundException("Did not found ByPass check the " + index + " Object.");
		}
		
		boolean isSelected = ((ICheckBox)objs[index]).isSelected();
		Browser.unregister(objs);
		
		return isSelected;		
	}
	
	public String getStatusCheckInfoByIndex(int index){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "appStatus");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found application status check table.");
		}
		IHtmlTable appTable = (IHtmlTable)objs[0];
		String vaule = appTable.getCellValue(index, 1).replaceAll("Status Check", "").trim();
		
		Browser.unregister(objs);
		
		return vaule;
	}
	
	public String getDateCompletedByIndex(int index){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("VendorApplicationCheckStatusView-\\d+\\.completedDate",false), index);
	}
	
	public String getCompletedByByIndex(int index){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("VendorApplicationCheckStatusView-\\d+\\.completedBy",false), index);
	}
	
	public String getCompletedCommentByIndex(int index){
		IHtmlObject[] objs = browser.getTextArea(".id", 
				new RegularExpression("VendorApplicationCheckStatusView-\\d+\\.comments", false));
		if(objs.length<1 && objs.length<index){
			throw new ItemNotFoundException("Did not found the " + index + " application complete comments object.");
		}
		String text = objs[index].getProperty(".value");
		return text;
	}
	
	public List<ApplicationStatusCheck> getAppStatusCheckInfo(){
		List<ApplicationStatusCheck> appStatusChecks = new ArrayList<ApplicationStatusCheck>();
		IHtmlObject[] objs = browser.getTableTestObject(".id", "appStatus");
		
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found application status check table.");
		}
		IHtmlTable appTable = (IHtmlTable)objs[0];
		int count = appTable.rowCount();
		for(int i=0; i<count; i++){
			ApplicationStatusCheck appStatusCheckInfo = new ApplicationStatusCheck();
			appStatusCheckInfo.byPassChecked = this.getByPassCheckedInfoByIndex(i);
			appStatusCheckInfo.statusCheck = appTable.getCellValue(i, 1).replaceAll("Status Check", "").trim();
			appStatusCheckInfo.dateCompleted = this.getDateCompletedByIndex(i);
			appStatusCheckInfo.completedBy = this.getCompletedByByIndex(i);
			appStatusCheckInfo.comments = this.getCompletedCommentByIndex(i);
			
			appStatusChecks.add(appStatusCheckInfo);
		}
		
		Browser.unregister(objs);
		return appStatusChecks;
	}
	
	public boolean compareVendorApplicationInfo(VendorInfo expVendor){
		boolean result = true;
		String actualValue = "";
		
		actualValue = this.getVendorNumber();
		if(!actualValue.equalsIgnoreCase(expVendor.number)){
			result &= true;
			logger.error("Expect vendor number should be " + expVendor.number 
					+ ", but acutally is " + actualValue);
		}
		
		actualValue = this.getVendorStatus();
		if(!actualValue.equalsIgnoreCase(expVendor.status)){
			result &= true;
			logger.error("Expect vendor status should be " + expVendor.status 
					+ ", but acutally is " + actualValue);
		}
		
		actualValue = this.getVendorName();
		if(!actualValue.equalsIgnoreCase(expVendor.name)){
			result &= true;
			logger.error("Expect vendor name should be " + expVendor.name 
					+ ", but acutally is " + actualValue);
		}
		
		actualValue = this.getOwnerName();
		if(!actualValue.equalsIgnoreCase(expVendor.ownerName)){
			result &= true;
			logger.error("Expect vendor owner name should be " + expVendor.ownerName 
					+ ", but acutally is " + actualValue);
		}
		
		actualValue = this.getVendorCreationDate();
		if(expVendor.vendorCreationDate.trim().length()>0){
			expVendor.vendorCreationDate = DateFunctions.formatDate(expVendor.vendorCreationDate, "E MMM d yyyy");
		}
		if(!actualValue.equalsIgnoreCase(expVendor.vendorCreationDate)){
			result &= true;
			logger.error("Expect vendor creation date should be " + expVendor.vendorCreationDate 
					+ ", but acutally is " + actualValue);
		}
		
		actualValue = this.getVendorCreationUser();
		if(!actualValue.equalsIgnoreCase(expVendor.vendorCreationUser)){
			result &= true;
			logger.error("Expect vendor creation user should be " + expVendor.vendorCreationUser 
					+ ", but acutally is " + actualValue);
		}
		
		actualValue = this.getAppReceivedDate();
		if(expVendor.appReceivedDate.trim().length()>0){
			expVendor.appReceivedDate = DateFunctions.formatDate(expVendor.appReceivedDate, "E MMM d yyyy");
		}
		if(!actualValue.equalsIgnoreCase(expVendor.appReceivedDate)){
			result &= true;
			logger.error("Expect application received date should be " + expVendor.appReceivedDate 
					+ ", but acutally is " + actualValue);
		}
		
		actualValue = this.getApplicantName().trim();
		if(!actualValue.equalsIgnoreCase(expVendor.appName.trim())){
			result &= true;
			logger.error("Expect application name should be " + expVendor.appName 
					+ ", but acutally is " + actualValue);
		}
		
		actualValue = StringUtil.formatPhoneNumToJustNumbers(this.getApplicantPhone());
		if(!actualValue.equalsIgnoreCase(expVendor.appPhone)){
			result &= true;
			logger.error("Expect application phone should be " + expVendor.appPhone 
					+ ", but acutally is " + actualValue);
		}
		
		actualValue = this.getApplicantEmail();
		if(!actualValue.equalsIgnoreCase(expVendor.appEmail)){
			result &= true;
			logger.error("Expect application email should be " + expVendor.appEmail 
					+ ", but acutally is " + actualValue);
		}
		
		actualValue = this.getNumOfAgentsRequested();
		if(!actualValue.equalsIgnoreCase(expVendor.requestStoreNum)){
			result &= true;
			logger.error("Expect number of agents requested should be " + expVendor.requestStoreNum 
					+ ", but acutally is " + actualValue);
		}
		
		actualValue = this.getEquipmentPerAgentRequested();
		if(!actualValue.equalsIgnoreCase(expVendor.requestStoreEquipmentNum)){
			result &= true;
			logger.error("Expect equipment per agent requested should be " + expVendor.requestStoreEquipmentNum 
					+ ", but acutally is " + actualValue);
		}
		
		actualValue = this.getAppCreationDate();
		if(expVendor.appCreationDate.trim().length()>0){
			expVendor.appCreationDate = DateFunctions.formatDate(expVendor.appCreationDate, "E MMM d yyyy");
		}
		if(!actualValue.equalsIgnoreCase(expVendor.appCreationDate)){
			result &= true;
			logger.error("Expect application creation date should be " + expVendor.appCreationDate 
					+ ", but acutally is " + actualValue);
		}
		
		actualValue = this.getAppCreationUser();
		if(!actualValue.contains(expVendor.appCreationUser)){
			result &= true;
			logger.error("Expect application creation date should be " + expVendor.appCreationUser 
					+ ", but acutally is " + actualValue);
		}
		
		List<ApplicationStatusCheck> actualAppStatusChecks = new ArrayList<ApplicationStatusCheck>();
		actualAppStatusChecks = this.getAppStatusCheckInfo();
		if(actualAppStatusChecks.size() != expVendor.applicationStatusCheck.size()){
			result &= false;
			logger.error("Expect application status check info have " + expVendor.applicationStatusCheck.size() 
					+ " records, but acutally have " + actualAppStatusChecks.size() + " records.");
		}else {
			for(int i=0; i<expVendor.applicationStatusCheck.size(); i++){
				if(actualAppStatusChecks.get(i).byPassChecked){
					if(!expVendor.applicationStatusCheck.get(i).byPassChecked){
						result &= false;
						logger.error(i + " Record expect byPass checked should be checked, but acutally is not.");
					}
				}else {
					if(expVendor.applicationStatusCheck.get(i).byPassChecked){
						result &= false;
						logger.error(i + " Record expect byPass checked should not be checked, but acutally is checked.");
					}
				}
				
				if(!actualAppStatusChecks.get(i).statusCheck.equals(expVendor.applicationStatusCheck.get(i).statusCheck)){
					result &= false;
					logger.error(i + " Record expect application status check should be " + expVendor.applicationStatusCheck.get(i).statusCheck
							+ ", but acutally is " + actualAppStatusChecks.get(i).statusCheck);
				}
				
				if(expVendor.applicationStatusCheck.get(i).dateCompleted.trim().length()>0){
					expVendor.applicationStatusCheck.get(i).dateCompleted = 
						DateFunctions.formatDate(expVendor.applicationStatusCheck.get(i).dateCompleted, "E MMM d yyyy");
				}
				if(!actualAppStatusChecks.get(i).dateCompleted.equals(expVendor.applicationStatusCheck.get(i).dateCompleted)){
					result &= false;
					logger.error(i + " Record expect application complete date should be " + expVendor.applicationStatusCheck.get(i).dateCompleted
							+ ", but acutally is " + actualAppStatusChecks.get(i).dateCompleted);
				}
				
				if(!actualAppStatusChecks.get(i).completedBy.equals(expVendor.applicationStatusCheck.get(i).completedBy)){
					result &= false;
					logger.error(i + " Record expect application complete by should be " + expVendor.applicationStatusCheck.get(i).completedBy
							+ ", but acutally is " + actualAppStatusChecks.get(i).completedBy);
				}
				
				if(!actualAppStatusChecks.get(i).comments.equals(expVendor.applicationStatusCheck.get(i).comments)){
					result &= false;
					logger.error(i + " Record expect application check stauts comments by should be " + expVendor.applicationStatusCheck.get(i).comments
							+ ", but acutally is " + actualAppStatusChecks.get(i).comments);
				}
			}
		}
		
		return result;
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
}
