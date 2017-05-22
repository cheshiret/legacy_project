package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description:verify all warning message when we edit the info of the privilege license year
 * @Preconditions:add a license year
 * @SPEC:Edit Privilege License Year
 * @Task#:AUTO-588
 * 
 * @author eliang
 * @Date  May 27, 2011
 */
public class VerifyEditLicenseYearWarningMessage extends LicenseManagerTestCase{
	private LicenseYear ly = new LicenseYear();
//	private LicenseYear ly = new LicenseYear();
	private LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
	private LicMgrPrivilegeEditLicenseYearWidget editYearPg = LicMgrPrivilegeEditLicenseYearWidget.getInstance();
	
	private boolean pass = true;
	private String errorMsg1,errorMsg2,errorMsg3,errorMsg4;
	
	private String id;
		
	public void execute() {
		lm.loginLicenseManager(login);
		
		//goto privilege product license year sub-tab page
		lm.gotoPrivilegeSubTabPage("NL1", "License Year");
		id = lm.addLicenseYear(ly).id; 
		System.out.println("Id:"+id);
		
//		//add another privilege		
//		String idForEdit = lm.addLicenseYear(ly).id;
//		System.out.println("idForEdit:"+idForEdit);
		
		logger.info("Go to license year page and edit, verify invalid date/time");
		this.gotoLicenseYearId(ly.locationClass,ly.licYear);
		
		//verify invalid date/time
		String sellFromDate="", sellToDate="",validFromDate="", validToDate = "";
		sellFromDate=ly.sellFromDate;
		sellToDate=ly.sellToDate;
		validFromDate=ly.validFromDate;
		validToDate=ly.validToDate;
		
		ly.sellFromDate = "20111132";
		ly.sellToDate = "20111133";
		ly.validFromDate = "20111134";
		ly.validToDate = "20111135";
		id = this.editLicenseYearDetail(ly);
		this.gotoLicenseYearId(ly.locationClass,ly.licYear);
		
		if(DateFunctions.compareDates(editYearPg.getSellFromDate(), sellFromDate)==0
				&&DateFunctions.compareDates(editYearPg.getSellToDate(), sellToDate)==0
				&&DateFunctions.compareDates(editYearPg.getValidFromDate(), validFromDate)==0
				&&DateFunctions.compareDates(editYearPg.getValidToDate(), validToDate)==0){
			throw new ErrorOnDataException("Invalidate date/time could be set up on edit license year page");
		}
		
		//update and verify the warning messages
		ly=this.reSetLicenseYearInfo(ly);
		ly.sellFromDate = "";
		id = this.editLicenseYearDetail(ly);
		this.verifyErrorMsg(errorMsg1);
		
		ly.sellFromDate = DateFunctions.getDateAfterToday(6);
		ly.sellToDate = "";
		id = this.editLicenseYearDetail(ly);
		this.verifyErrorMsg(errorMsg2);
		
		ly.sellFromDate = DateFunctions.getDateAfterToday(3);
		ly.sellToDate = DateFunctions.getDateAfterToday(2);
		id = this.editLicenseYearDetail(ly);
		this.verifyErrorMsg(errorMsg3);
		
		ly=this.reSetLicenseYearInfo(ly);
		ly.sellFromDate = DateFunctions.getDateAfterToday(2);
		ly.sellToDate = DateFunctions.getDateAfterToday(3);
		ly.validFromDate = DateFunctions.getDateAfterToday(5);
		ly.validToDate = DateFunctions.getDateAfterToday(4);
		id = this.editLicenseYearDetail(ly);
		this.verifyErrorMsg(errorMsg4);
	
		// Could not display warning message5 when edit an exited license year 
//		ly.sellFromDate = DateFunctions.getDateAfterToday(2);
//		ly.sellToDate = DateFunctions.getDateAfterToday(5);
//		ly.validFromDate = DateFunctions.getDateAfterToday(6);
//		ly.validToDate = DateFunctions.getDateAfterToday(9);
//		ly.sellFromTime = "11:00";
//		ly.sellToTime = "12:00";
//		ly.sellFromAmPm = "AM";
//		ly.sellToAmPm = "PM";
//		this.editLicenseYearDetail(ly);
//		this.verifyErrorMsg(errorMsg5);
		
		editYearPg.clickCancel();
		ajax.waitLoading();
		
		//inactive the license year
		licenseYearPg.waitLoading();
		this.inactiveLicenseYear(id);
		
		if(!pass){
			throw new ErrorOnPageException("Error Message Not Correct,please check Error Log.");
		}
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		ly.licYear = (DateFunctions.getCurrentYear()+1)+"";
		ly.locationClass = "01 - MDWFP Headquarters";
		ly.sellFromDate = DateFunctions.getDateAfterToday(2);
		ly.sellToDate = DateFunctions.getDateAfterToday(5);
		ly.validFromDate = DateFunctions.getDateAfterToday(6);
		ly.validToDate = DateFunctions.getDateAfterToday(9);
		ly.sellFromTime = "11:00";
		ly.sellToTime = "12:00";
		ly.sellFromAmPm = "AM";
		ly.sellToAmPm = "PM";
		ly.validFromTime = "11:00";
		ly.validToTime = "12:00";
		ly.validFromAmPm = "AM";
		ly.validToAmPm = "PM";
		
//		lyForEdit.licYear=ly.licYear;
//		lyForEdit.locationClass = ly.locationClass;
//		lyForEdit.sellFromDate = DateFunctions.getDateAfterToday(6);
//		lyForEdit.sellToDate = DateFunctions.getDateAfterToday(9);
//		lyForEdit.validFromDate = DateFunctions.getDateAfterToday(12);
//		lyForEdit.validToDate = DateFunctions.getDateAfterToday(15);
//		lyForEdit.sellFromTime = "11:00";
//		lyForEdit.sellToTime = "12:00";
//		lyForEdit.sellFromAmPm = "AM";
//		lyForEdit.sellToAmPm = "PM";
//		lyForEdit.validFromTime = "11:00";
//		lyForEdit.validToTime = "12:00";
//		lyForEdit.validFromAmPm = "AM";
//		lyForEdit.validToAmPm = "PM";
		
		
		errorMsg1="The Sell From Date & Time is required. Please enter the Sell From Date & Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided.";
		errorMsg2="The Sell To Date & Time is required. Please enter the Sell To Date & Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided.";
//		errorMsg1="Sell From Date/Time : Date value required";
//		errorMsg2="Sell To Date/Time : Date value required";
		errorMsg3="The Sell From Date & Time must not be later than the Sell To Date & Time. Please change your entries.";
		errorMsg4="The Valid From Date & Time must not be later than the Valid To Date & Time. Please change your entries.";
//		errorMsg5="Another active Privilege License Year record "+id+" already exists for the same Privilege Product with the same License Year and Location Class. Duplicate active records are not allowed.";
	}

	public void gotoLicenseYearId(String locClass, String year){
		String id = licenseYearPg.getLicenseYearId(locClass, year);
		licenseYearPg.clickLicenseYear(id);
		ajax.waitLoading();
	}
	public void inactiveLicenseYear(String id){
		licenseYearPg.clickLicenseYear(id);
		ajax.waitLoading();
		editYearPg.selectStatus("Inactive");
		editYearPg.clickOK();
		ajax.waitLoading();
	}
	
	public String editLicenseYearDetail(LicenseYear ly){
		editYearPg.setLicenseYearInfo(ly);
		editYearPg.clickOK();
		ajax.waitLoading();
		LicenseYear newLy = licenseYearPg.getLicenseYearInfo(ly.locationClass, ly.licYear);
		return newLy.id;
	}
	
	public void verifyErrorMsg(String expectMsg){
		Object page = browser.waitExists(editYearPg,licenseYearPg);
		if(page == licenseYearPg){
			throw new ErrorOnPageException("Expect Page is Widget Page.");
		}
		String actualMsg = editYearPg.getWaringMsg();
		if(actualMsg == null||!actualMsg.contains(expectMsg)){
			pass &= false;
			logger.error("Error Msg Not Correct,Expect Msg is '"+expectMsg+"',actual Msg is '"+actualMsg);
		}
	}
	
	public LicenseYear reSetLicenseYearInfo(LicenseYear ly){
		ly.sellFromDate = DateFunctions.getDateAfterToday(6);
		ly.sellToDate = DateFunctions.getDateAfterToday(9);
		ly.validFromDate = DateFunctions.getDateAfterToday(12);
		ly.validToDate = DateFunctions.getDateAfterToday(15);
		ly.validFromTime = "11:00";
		ly.validToTime = "12:00";
		ly.validFromAmPm = "AM";
		ly.validToAmPm = "PM";
		
		return ly;
	}
}
