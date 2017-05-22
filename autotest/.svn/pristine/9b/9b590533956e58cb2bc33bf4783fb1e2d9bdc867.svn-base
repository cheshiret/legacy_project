package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt.licenseyear;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntLicYearListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the add license year for hunt, and verify the new added license year info in license year detail 
 * page and list page
 * @LinkSetUp:  d_hf_add_hunt:id=680(CODE='HLY3')
 * 				d_hf_add_lottery_prd:id=210(CODE='HP')
 * 				d_hf_assi_hunts_to_lottery:id=130
 * @SPEC:[TC:050341] Add Privilege Lottery Hunt License Year - Error Messages 
 * @Task#: Auto-2064
 * @author Phoebe Chen
 * @Date  February 25, 2014
 */
public class VerifyErrorMsg_Add  extends LicenseManagerTestCase{
	private LicenseYear ly = new LicenseYear();
	private LicMgrHuntLicYearListPage huntLicYearListPg = LicMgrHuntLicYearListPage.getInstance();
	private LicMgrAddHuntLicYearWidget huntLicYearWidget = LicMgrAddHuntLicYearWidget.getInstance();
	private TimeZone timezone;
	private String errMsg_assignPdEmpty, errMsg_licenseYearEmptry, errMsg_sellFromDateEmpty, errMsg_sellFromTimeFormatWrong, 
	errMsg_sellToDateEmpty, errMsg_sellToTimeFormatWrong, errMsg_sellToEarlierToSellFrom, errMsg_duplicateLicenseYearInfo; 
	private boolean passed = true;
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt license year list page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		lm.gotoHuntLicYearListFromHuntsListPg(hunt.getHuntCode());
		
		//Inactive the license year already exist for the following adding new license year test
		this.inactiveAllLicenseYear();
		
		//Add a hunt license year for the verify of duplicate error message
		lm.addHuntLicenseYear(ly);
		
		//The assigned product is empty, verify the error message
		ly.assignedProd = StringUtil.EMPTY;
		lm.addHuntLicenseYear(ly);
		passed &= MiscFunctions.compareResult("Error message for assigned product is emptry:", errMsg_assignPdEmpty, huntLicYearWidget.getErrorMsg());
		
		//The license year is empty, verify the error message
		ly.assignedProd = "HP - Hunt product"; 
		ly.licYear = StringUtil.EMPTY;
		this.setHuntLicenseYearInfoAndClickOk();
		passed &= MiscFunctions.compareResult("Error message for license year is emptry:", errMsg_licenseYearEmptry, huntLicYearWidget.getErrorMsg());
		
		//The sell from date is empty, verify the error message
		ly.licYear = String.valueOf(DateFunctions.getCurrentYear());
		ly.sellFromDate = StringUtil.EMPTY;
		this.setHuntLicenseYearInfoAndClickOk();
		passed &= MiscFunctions.compareResult("Error message for sell from date is emptry:", errMsg_sellFromDateEmpty, huntLicYearWidget.getErrorMsg());
		
		//The sell from time format is wrong, verify the error message
		ly.sellFromDate = DateFunctions.getToday(timezone);
		ly.sellFromTime = "11/11";
		this.setHuntLicenseYearInfoAndClickOk();
		passed &= MiscFunctions.compareResult("Error message for sell from date format is wrong:", errMsg_sellFromTimeFormatWrong, huntLicYearWidget.getErrorMsg());
		
		//The sell to date is empty, verify the error message
		ly.sellFromTime = "11:11";
		ly.sellToDate = StringUtil.EMPTY;
		this.setHuntLicenseYearInfoAndClickOk();
		passed &= MiscFunctions.compareResult("Error message for sell to date is emptry:", errMsg_sellToDateEmpty, huntLicYearWidget.getErrorMsg());
		
		//The sell to time format is wrong, verify the error message
		ly.sellToDate = DateFunctions.getDateAfterToday(300, timezone);
		ly.sellToTime = "11.59";
		this.setHuntLicenseYearInfoAndClickOk();
		passed &= MiscFunctions.compareResult("Error message for sell to date is emptry:", errMsg_sellToTimeFormatWrong, huntLicYearWidget.getErrorMsg());
		
		//The sell to time is earlier then sell from date, verify the error message
		ly.sellToTime = "11:59";
		ly.sellFromDate = DateFunctions.getDateAfterToday(10, timezone);
		ly.sellToDate = DateFunctions.getDateAfterToday(5, timezone);
		this.setHuntLicenseYearInfoAndClickOk();
		passed &= MiscFunctions.compareResult("Error message for sell to date is earlier than sell from date:", errMsg_sellToEarlierToSellFrom, huntLicYearWidget.getErrorMsg());
		
		//Duplicate hunt license year info
		ly.sellFromDate =DateFunctions.getToday(timezone);
		DateFunctions.getDateAfterToday(300, timezone);
		this.setHuntLicenseYearInfoAndClickOk();
		passed &= MiscFunctions.compareResult("Error message for duplicate hunt license year info:", errMsg_duplicateLicenseYearInfo, huntLicYearWidget.getErrorMsg());
		
		lm.gotoHuntLicenseYearListPageFromAddWidget();
		
		if(!passed){
			throw new ErrorOnPageException("Not all the error message is correct, please check the log above!");
		}
		logger.info("Error message for adding hunt license year are all correct!");
		
		lm.logOutLicenseManager();
	}

	private void inactiveAllLicenseYear(){
		huntLicYearListPg.searchLicenseYear(ACTIVE_STATUS, StringUtil.EMPTY, StringUtil.EMPTY);
		List<String> ids = huntLicYearListPg.getAllLicenseYearIds();
		lm.desctiveHuntLicenseYears(ids);
	}
	
	public void setHuntLicenseYearInfoAndClickOk(){
		huntLicYearWidget.setHuntLicYearInfo(ly);
		huntLicYearWidget.clickOKAndWait();
		ajax.waitLoading();
		huntLicYearWidget.waitLoading();
	}
	

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		timezone = DataBaseFunctions.getContractTimeZone(schema);
		//login information
		String facility = lm.getFacilityName("1", schema);//Mississippi Department of Wildlife, Fisheries, and Parks
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facility;
		
		//Set up hunt code
		hunt.setHuntCode("HLY2");
		
		//Set up license year info
		ly.prdCategory = "Lottery";
		ly.assignedProd = "HP - Hunt product";
		ly.locationClass =  "01 - MDWFP Headquarters";
		ly.status = ACTIVE_STATUS;
		
		ly.licYear = String.valueOf(DateFunctions.getCurrentYear());
		
		ly.sellFromDate = DateFunctions.getToday(timezone);
		ly.sellFromTime = "11:11"; 
		ly.sellFromAmPm = "AM";
	
		ly.sellToDate = DateFunctions.getDateAfterToday(20, timezone);
		ly.sellToTime = "11:59";
		ly.sellToAmPm = "PM";
		
				
		errMsg_assignPdEmpty = "The Assigned Product is required. Please enter the Assigned Product in the fields provided.";
		errMsg_licenseYearEmptry = "The License Year is required. Please enter the License Year in the fields provided.";
//		errMsg_sellFromDateEmpty = "Sell From Date/Time : Date value required";
		errMsg_sellFromDateEmpty = "The Sell From Date & Time is required. Please enter the Sell From Date & Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the fields provided.";
		errMsg_sellFromTimeFormatWrong = "Sell From Date/Time : Invalid time format, please enter time in format: HH:MM";
//		errMsg_sellToDateEmpty = "Sell To Date/Time : Date value required";
		errMsg_sellToDateEmpty = "The Sell To Date & Time is required. Please enter the Sell To Date & Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the fields provided.";
		errMsg_sellToTimeFormatWrong = "Sell To Date/Time : Invalid time format, please enter time in format: HH:MM";
		errMsg_sellToEarlierToSellFrom = "The Sell From Date & Time must not be later than the Sell To Date & Time. Please change your entries.";
//		errMsg_duplicateLicenseYearInfo = "An Active License Year record with the same License Year, Location Class and Sell From/To Dates & Time already exists. Duplicate active records are not allowed.";
		errMsg_duplicateLicenseYearInfo = "For the Lottery Product Hunt License Year being added, an existing 'active' Lottery Product Hunt License Year Record with the " +
				"same Hunt, Product, Location Class, and License Year has overlapping Sell From Date/Time and Sell To Date/Time period (i.e there is Date & Time that falls " +
				"between periods for both license year records). Please change your entries.";
		
	}
}
