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
 * 2: there is no license year info for the new license year we want to batch add based on the value of(current year+9);
 * 3: there is one active fulfillment document with template(AutoBatchAddDateChang), equipment type(Kiosk) which license yeas is (current year+3).
 * @Description: verify PrintFrom and PrintTo (error message display)for Batch Add License Year search changes
 * @Preconditions:
 * 1.License Info of privilege: Sell from&to date/time and Valid from&to date/time MUST has been set up.(NOT BLANK!!)
 * 2.Fulfill document Info of privilege: Effective from&to date/time and Print from&to date/time MUST has been set up.(NOT BLANK!!)
 * @SPEC: Add License Year for Privileges in Batch
 * @Task#:AUTO-725
 * 
 * @author bzhang
 * @Date  Aug 23, 2011
 */
public class VerifyPrintDateTime extends LicenseManagerTestCase {
	private LicenseYear ly = new LicenseYear();
	private LicenseYear temply = new LicenseYear();
	private LicenseYear temply2 = new LicenseYear();

	private String  errorMsg15, errorMsg17, errorMsg19 ;
	private LicMgrPrivilegeLicenseYearBatchAddPage priLicYearBatchAddPg = LicMgrPrivilegeLicenseYearBatchAddPage.getInstance();
	private boolean pass = true;
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeLicenseYearBatchAddPageFromTopMenu();
		
		//1 Verify Print From Date is not a valid date format
		this.verifyDocumentWarningMsg(ly, "PrintFrom", "abcd", errorMsg15);
		
		//2 Verify Print To Date is not a valid date format
		this.verifyDocumentWarningMsg(ly, "PrintTo", "abcd", errorMsg17);
	
		//3 verify Print From Date is blank while Print To is date format
		this.verifyDocumentWarningMsg(ly, "PrintFrom", "", errorMsg15);
		
		//4 verify Print To is blank with Print From is date format
		this.verifyDocumentWarningMsg(ly, "PrintTo", "", errorMsg17);
		
		//5 print to earlier than print from
		lm.searchPrivilegeLicenseYearOnBatchAddPg(ly);
		this.editDocumentInfoOnBatchAddPg("PrintFrom", DateFunctions.getDateAfterToday(2));
		this.editDocumentInfoOnBatchAddPg("PrintTo", DateFunctions.getDateAfterToday(1));
		this.selectUpdateRecords(false);
		this.verifyWarningMsg(errorMsg19); 

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
		ly.licYear = (DateFunctions.getCurrentYear()+3)+"";
		ly.productName = privilege.name;
		ly.productCode = privilege.code;
		
		ly.copyFromYear = ly.licYear;
		ly.newLicYear = (DateFunctions.getCurrentYear()+9)+"";
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
		errorMsg15 = privilege.code + " - " +  privilege.name + ": The Print From Date is required. Please enter the Print From Date using the format Ddd Mmm dd yyyy.";  //expect msg
		errorMsg17 = privilege.code + " - " +  privilege.name + ": The Print To Date is required. Please enter the Print To Date using the format Ddd Mmm dd yyyy.";  //expect msg
		errorMsg19 = privilege.code + " - " +  privilege.name + ": The Print From Date must not be later than the Print To Date. Please change your entries."; //expect msg
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
	
	/**
	 * update print document's print from and print to data.
	 * @param where
	 * @param value
	 */
	private void editDocumentInfoOnBatchAddPg(String where, String value){
		int documentIndex = priLicYearBatchAddPg.getDocumentIndex(document.docTepl, document.equipType); //get the row number info to do update
		
		if (where.equalsIgnoreCase("PrintFrom")){
			priLicYearBatchAddPg.setPrintFromDate(value, documentIndex);			
		}else if (where.equalsIgnoreCase("PrintTo")){
			priLicYearBatchAddPg.setPrintToDate(value, documentIndex);		
		}else{
			throw new ErrorOnDataException("edit document info function DON'T support this input value: " + where);
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
	 * make search on license year batch add page, and then verify the warning message based on different Date values for document record.
	 * start/end at license year batch add page.
	 * @param ly
	 * @param where
	 * @param value
	 * @param errorMsg
	 */
	private void verifyDocumentWarningMsg(LicenseYear ly, String where, String value, String errorMsg){
		lm.searchPrivilegeLicenseYearOnBatchAddPg(ly);
		this.editDocumentInfoOnBatchAddPg(where, value);
		this.selectUpdateRecords(false);
		this.verifyWarningMsg(errorMsg); 
	}
}
