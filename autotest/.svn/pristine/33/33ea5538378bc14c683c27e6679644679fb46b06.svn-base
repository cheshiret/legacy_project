package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear.batchadd;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearBatchAddPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Prerequisit: 
 * 1: there is one privilege product Code "AT0", named "AutoPriForBatchAddLicense" setup.
 * 1: there is one active license year(current year+3) info for this Privilege product.
 * 2: there is no license year info for the new license year we want to batch add based on the value of(current year+13);
 * @Description: verify ValidFromDate and ValidFromTime (error message display)for Batch Add License Year search changes
 * @Preconditions:
 * 1.License Info of privilege: Sell from&to date/time and Valid from&to date/time MUST has been set up.(NOT BLANK!!)
 * @SPEC: Add License Year for Privileges in Batch
 * @Task#:AUTO-725
 * 
 * @author bzhang
 * @Date  Aug 23, 2011
 */
public class VerifyValidDateTime extends LicenseManagerTestCase {
	private LicenseYear ly = new LicenseYear();
	private LicenseYear temply = new LicenseYear();
	private LicenseYear temply2 = new LicenseYear();

	private String  errorMsg11, errorMsg12, errorMsg13;
	private LicMgrPrivilegeLicenseYearBatchAddPage priLicYearBatchAddPg = LicMgrPrivilegeLicenseYearBatchAddPage.getInstance();
	private boolean pass = true;
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeLicenseYearBatchAddPageFromTopMenu();
			
		//1 Valid From Date & Time entry is not a valid date & time
		this.verifyLicenseYearWarningMsg(ly, "ValidFromTime", "abcd", errorMsg11);
		
		//2 Valid To Date & Time entry is not a valid date & time.
		this.verifyLicenseYearWarningMsg(ly, "ValidToTime", "abcd", errorMsg12);
		
		//3 Valid From Date & Time is greater than the entry for Valid To Date & Time.
		lm.searchPrivilegeLicenseYearOnBatchAddPg(ly);
		priLicYearBatchAddPg.editLicenseYearInfo(temply2);
		this.selectUpdateRecords(true);
		this.verifyWarningMsg(errorMsg13); 
		
		if(!pass){
			throw new ErrorOnPageException("the warning message verification failured, please refer to log file for more details info");
		}
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//new added privileges info
		privilege.status = "Active";
		privilege.code = "AT0";
		privilege.name = "AutoPriForBatchAddLicense";
		privilege.displayCategory = "Freshwater Fishing";
		privilege.displaySubCategory = "Annual";
		privilege.customerClasses = new String[2];
		privilege.customerClasses[0] = "Individual";		
		privilege.customerClasses[1] = "Business";
		
		document.docTepl = "AutoBatchAddDateChang";
		document.equipType = "Kiosk";
		document.purchaseType = "Transfer";
		
		//license year info		
		ly.status = "Active";
		ly.locationClass = "03 - Lakes Offices";
		ly.licYear = Integer.toString(DateFunctions.getYearAfterCurrentYear(3));//(Integer.valueOf(lm.getFiscalYear(schema))+3)+"";
		ly.productName = privilege.name;
		ly.productCode = privilege.code;
		
		ly.copyFromYear = ly.licYear;
		ly.newLicYear = Integer.toString(DateFunctions.getYearAfterCurrentYear(13));//(Integer.valueOf(lm.getFiscalYear(schema))+13)+"";
		ly.numOfYearToAdd = "1";
		
		//license year 1 info
		temply.productName = privilege.name;
		temply.productCode = privilege.code;
		temply.locationClass = ly.locationClass;
		temply.sellFromDate = DateFunctions.getDateAfterToday(360, "EEE MMM dd yyyy");
		temply.sellFromTime = "11:01";
		temply.sellFromAmPm = "AM";
		temply.validFromAmPm = "AM";
		temply.validToAmPm = "PM";
		
		temply.sellToDate = DateFunctions.getDateAfterToday(360, "EEE MMM dd yyyy");
		temply.sellToTime = "11:00";
		temply.sellToAmPm = "AM";
		
		//license year 2 info
		temply2.productName = privilege.name;
		temply2.productCode = privilege.code;
		temply2.locationClass = ly.locationClass;
		temply2.sellFromDate = DateFunctions.getDateAfterToday(360, "EEE MMM dd yyyy");
		temply2.sellFromTime = "10:00";
		temply2.sellFromAmPm = "AM";
		
		temply2.sellToDate = DateFunctions.getDateAfterToday(360, "EEE MMM dd yyyy");
		temply2.sellToTime = "11:00";
		temply2.sellToAmPm = "AM";
		
		temply2.validFromDate = DateFunctions.getDateAfterToday(361, "EEE MMM dd yyyy");
		temply2.validFromTime = "11:01";
		temply2.validFromAmPm = "AM";
		
		temply2.validToDate = DateFunctions.getDateAfterToday(360, "EEE MMM dd yyyy");
		temply2.validToTime = "11:00";
		temply2.validToAmPm = "AM";
		temply2.locationClass = ly.locationClass;
		temply2.validFromAmPm = "AM";
		temply2.validToAmPm = "PM";
		
		//error message		
		errorMsg11 = "Invalid time format, please enter time in format: HH:MM"; //inconsistent with spec
		errorMsg12 = "Invalid time format, please enter time in format: HH:MM"; //inconsistent with spec
		errorMsg13 = privilege.code + " - " +  privilege.name + " : The Valid From Date & Time must not be later than the Valid To Date & Time. Please change your entries.";//inconsistent with spec
	}
	
	/**
	 * verify the warning message on batch add license year page after click OK button. start/end from batch add licenese year page.
	 * @param expectMsg
	 */
	private void verifyWarningMsg(String expectMsg){				
		String currentMsg = priLicYearBatchAddPg.getWarningMessage();

		if (!currentMsg.contains(expectMsg)){
			pass = false;
			logger.error("The  expect warning message is: " + expectMsg);
			logger.error("The current warning message is: " + currentMsg);
		}
	}
	
	/**
	 * update license year single record on batch add license year page,click OK button, and then get the warning message.
	 * @param where
	 * @param value
	 * 
	 */
	private void editLicenseYearInfoOnBatchAddPg(String where, String value){
		int licYearIndex = priLicYearBatchAddPg.getLicenseYearIndex(privilege.code, privilege.name, ly.locationClass);
		
		if (where.equalsIgnoreCase("sellFromDate")){
			priLicYearBatchAddPg.setSellFromDate(value, licYearIndex);			
		}else if (where.equalsIgnoreCase("sellFromTime")){
			priLicYearBatchAddPg.setSellFromTime(value, licYearIndex);
		}else if (where.equalsIgnoreCase("sellFromAmPm")){
			priLicYearBatchAddPg.selectSellFromAmPm(value, licYearIndex);			
		}else if (where.equalsIgnoreCase("sellToDate")){
			priLicYearBatchAddPg.setSellToDate(value, licYearIndex);
		}else if (where.equalsIgnoreCase("sellToTime")){
			priLicYearBatchAddPg.setSellToTime(value, licYearIndex);
		}else if (where.equalsIgnoreCase("sellToAmPm")){
			priLicYearBatchAddPg.selectSellToAmPm(value, licYearIndex);
		}else if (where.equalsIgnoreCase("validFromDate")){
			priLicYearBatchAddPg.setValidFromDate(value, licYearIndex);
		}else if (where.equalsIgnoreCase("validFromTime")){
			priLicYearBatchAddPg.setValidFromTime(value, licYearIndex);
		}else if (where.equalsIgnoreCase("validFromAmPm")){
			priLicYearBatchAddPg.selectValidFromAmPm(value, licYearIndex);
		}else if (where.equalsIgnoreCase("validToDate")){
			priLicYearBatchAddPg.setValidToDate(value, licYearIndex);
		}else if (where.equalsIgnoreCase("validToTime")){
			priLicYearBatchAddPg.setValidToTime(value, licYearIndex);
		}else if (where.equalsIgnoreCase("validToAmPm")){
			priLicYearBatchAddPg.selectValidToAmPm(value, licYearIndex);
		}else{
			throw new ErrorOnDataException("edit license year function DON'T support this input value: " + where);
		}
	}
	
	/**
	 * select license or document record and then click OK button to submit the changes.
	 * @param isLicRecord  true: the changes will be made to license year record; false: the changes will be made to print document record
	 */
	private void selectUpdateRecords(boolean isLicRecord){
		int index;
		
		if (isLicRecord){
			index = priLicYearBatchAddPg.getLicenseYearCheckBoxIndex(privilege.code, privilege.name, ly.locationClass);
		}else{
			index = priLicYearBatchAddPg.getDocumentCheckBoxIndex(document.docTepl, document.equipType);
		}
		priLicYearBatchAddPg.selectCheckBox(index);
		priLicYearBatchAddPg.clickOKButton();
		priLicYearBatchAddPg.waitLoading();
	}
	
	/**
	 * make search on license year batch add page, and then verify the warning message based on different Date values for license year record.
	 * start/end at license year batch add page.
	 * @param ly
	 * @param where
	 * @param value
	 * @param isLicRecord
	 * @param errorMsg
	 */
	private void verifyLicenseYearWarningMsg(LicenseYear ly, String where, String value, String errorMsg){
		lm.searchPrivilegeLicenseYearOnBatchAddPg(ly);
		this.editLicenseYearInfoOnBatchAddPg(where, value);
		this.selectUpdateRecords(true);
		this.verifyWarningMsg(errorMsg); //match spec
	}
}
