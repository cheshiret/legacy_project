package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.batcheditlicenseyear;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrBatchEditLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description:
 * 1. Verify error message for Sell From Date/Time: left blank, valid date & time.
 * 2. Verify error message for Sell To Date/Time: left blank, valid date & time.
 * 3. Verify error message when Sell From Date is greater than Sell To Date
 * 4. Verify error message for Valid From Date/Time: valid date & time.
 * 5. Verify error message for Sell From Date/Time: valid date & time.
 * 6. Verify error message when Valid From Date is greater than Valid To Date
 * 7. verify no fields are edited after click Cancel button
 * @Preconditions: Privilege a1a must have at least has ONE privilege license year record(current year +3)
 * @SPEC: Edit License Year for Privileges in Batch
 * @Task#: AUTO-726
 * 
 * @author SWang
 * @Date  Aug 25, 2011
 */
public class ValidateLicenseYearFields extends LicenseManagerTestCase{
	private LicMgrBatchEditLicenseYearPage batchEditLicenseYearPg = LicMgrBatchEditLicenseYearPage.getInstance();
	private String[] invalidDates = new String[] { "VerifyDate", "1/0/2015"};
	private String str0, str1, str2, str4, str5, newerSellToDate, editedSellToDate;

	public void execute() {
		//login in 
		lm.loginLicenseManager(login);
		lm.gotoBatchEditLicenseYearPg();

		//Check error message when no existing privilege product found
		this.checkErrorMesWhenNoExistingPrivilegeProductFound();

		batchEditLicenseYearPg.checkAtLeastOnePrivilegeLicenseYearExist();
		privilege.index = batchEditLicenseYearPg.selectPrivilegeProductCheckBox(privilege.code, privilege.name, false);

		//Check error message for privilege license year record
		//Sell From Date
		privilege.licYear.sellFromDate = " ";
		this.checkErrorMsgForPrivilegeLicenseYearFields(str1);
		batchEditLicenseYearPg.verifyInvalidSellFromDate(invalidDates, privilege.index);

		//Sell From Time
		privilege.licYear.sellFromDate = DateFunctions.getDateAfterToday(3);
		privilege.licYear.sellFromTime= " ";
//		this.checkErrorMsgForPrivilegeLicenseYearFields(str2); // Lesley[20131108]: update due to no error message when time is blank in 3.05. 
		
		//Sell To Date
		privilege.licYear.sellFromTime= "12:00";
		privilege.licYear.sellToDate = " ";
		this.checkErrorMsgForPrivilegeLicenseYearFields(str1);
		batchEditLicenseYearPg.verifyInvalidSellToDate(invalidDates, privilege.index);

		//Sell To Time
		privilege.licYear.sellToDate = DateFunctions.getDateAfterToday(5);
		privilege.licYear.sellToTime = " ";
//		this.checkErrorMsgForPrivilegeLicenseYearFields(str2);

		//Sell From Date/Time is greater than Sell To Date/Time
		privilege.licYear.sellToTime = "11:59";
		privilege.licYear.sellToDate = DateFunctions.getDateAfterToday(2);
		this.checkErrorMsgForPrivilegeLicenseYearFields(str4);

		//Valid From Date 
		batchEditLicenseYearPg.verifyInvalidValidFromDate(invalidDates, privilege.index);

		//Valid To Date
		batchEditLicenseYearPg.verifyInvalidValidToDate(invalidDates, privilege.index);

		//Valid From Date is greater than Valid To Date
		privilege.licYear.sellToDate = DateFunctions.getDateAfterToday(3);
		privilege.licYear.validToDate = DateFunctions.getDateAfterToday(2);
		this.checkErrorMsgForPrivilegeLicenseYearFields(str5);

		privilege.licYear.sellToDate = DateFunctions.getDateAfterToday(5);
		batchEditLicenseYearPg.clickGo();
		ajax.waitLoading();
		editedSellToDate = batchEditLicenseYearPg.getSellToDate(privilege.index);
		
		//Verify Cancel Batch Edit License Year
		this.verifyCancelEditLicenseYear();

		//Logout 
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		//Privilege information
		privilege.code = "a1a";
		privilege.name = "BatchEditLicenseYearTest6";
		privilege.status = "Active";
		privilege.customerClasses = new String[]{"Individual"};
		privilege.displayCategory = "bELYDisplayCategory2";
		privilege.displaySubCategory = "BELYDisplaySubCategory";
		
		//Privileges License Years information
		privilege.licYear.sellFromDate = DateFunctions.getDateAfterToday(3);
		privilege.licYear.sellFromTime= "12:00";
		privilege.licYear.sellToDate = DateFunctions.getDateAfterToday(5);
		privilege.licYear.sellToTime = "11:59";
		privilege.licYear.validFromDate = DateFunctions.getDateAfterToday(3);
		privilege.licYear.validFromTime= "12:00";
		privilege.licYear.validToDate = DateFunctions.getDateAfterToday(5);
		privilege.licYear.validToTime= "11:59";
		
		str0 = "No Privilege Product found for editing.";
//		str1 = privilege.code+" - "+privilege.name+" : The Sell From Date & Time is required. Please enter the Sell From Date & Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided.";
		str1 = "Date value required";
		str2 = "Invalid time format, please enter time in format: HH:MM";
//		str3 = privilege.code+" - "+privilege.name+" : The Sell To Date & Time is required. Please enter the Sell To Date & Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided.";
		str4 = privilege.code+" - "+privilege.name+" : The Sell From Date & Time must not be later than the Sell To Date & Time. Please change your entries.";
		str5 = privilege.code+" - "+privilege.name+" : The Valid From Date & Time must not be later than the Valid To Date & Time. Please change your entries.";
	
	}

	private void checkErrorMesWhenNoExistingPrivilegeProductFound(){
		batchEditLicenseYearPg.gotoPrivilegeLicenseYearGridWithYearToEdit( String.valueOf(DateFunctions.getCurrentYear()-10));
		batchEditLicenseYearPg.verifyErrorMsg(str0);
		batchEditLicenseYearPg.gotoPrivilegeLicenseYearGridWithYearToEdit( String.valueOf(DateFunctions.getCurrentYear()+3));
	}

	private void checkErrorMsgForPrivilegeLicenseYearFields(String expectErrorMsg){
		batchEditLicenseYearPg.setBatchEditLicenseYearFields(privilege);
		batchEditLicenseYearPg.clickOK();
		ajax.waitLoading();
		batchEditLicenseYearPg.verifyErrorMsg(expectErrorMsg);
	}

	private void verifyCancelEditLicenseYear(){
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();
		newerSellToDate = DateFunctions.getDateAfterGivenDay(editedSellToDate, 1);
		batchEditLicenseYearPg.setSellToDate(newerSellToDate, privilege.index);

		//Cancel editing license year
		batchEditLicenseYearPg.clickCancel();
		ajax.waitLoading();
		privilegeListPage.waitLoading();
		lm.gotoBatchEditLicenseYearPg();
		batchEditLicenseYearPg.gotoPrivilegeLicenseYearGridWithYearToEdit(String.valueOf(DateFunctions.getCurrentYear()+3));

		//Verify No field be edited
		String actualSellToDate = batchEditLicenseYearPg.getSellToDate(privilege.index);
		if(!actualSellToDate.equals(editedSellToDate) || actualSellToDate.equals(newerSellToDate)){
			throw new ErrorOnDataException("Privilege License Year data should not be changed after canceling.Actual date is:"+actualSellToDate+", and actual date should be:"+editedSellToDate);
		}
	}
}
