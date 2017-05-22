package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt.licenseyear;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrEditHuntLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntLicYearListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the edit license year for hunt, and verify the new added license year info in license year detail 
 * page and list page
 * @LinkSetUp:  d_hf_add_hunt:id=680(CODE='HLY5')
 * 				d_hf_add_lottery_prd:id=210(CODE='HP')
 * 				d_hf_assi_hunts_to_lottery:id=130
 * @SPEC:[TC:050378] Edit Privilege Lottery Hunt License Year Popup - UI 
 *		 [TC:044961] Edit Privilege Lottery Hunt License Year - business rule 
 * @Task#: Auto-2064
 * @author Phoebe Chen
 * @Date  February 24, 2014
 */
public class VerifyUI_Edit extends LicenseManagerTestCase{
	private LicenseYear ly = new LicenseYear();
	private LicMgrHuntLicYearListPage huntLicYearListPg = LicMgrHuntLicYearListPage.getInstance();
	private LicMgrEditHuntLicYearWidget huntLicYearWidget = LicMgrEditHuntLicYearWidget.getInstance();
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
		lm.addHuntLicenseYear(ly);

		huntLicYearListPg.searchLicenseYear(ly.status, StringUtil.EMPTY, ly.assignedProd);
		ly.id = huntLicYearListPg.getHuntLicYearID(ly.assignedProd, ly.licYear);
		
		lm.gotoHuntLicenseYearWigetFromListPg(ly.id);
		//Verify the ui for add hunt license year widget 
		verifyEditLicenseYearWidgetUI();
		
		lm.gotoHuntLicenseYearListPageFromEditWidget();
		lm.logOutLicenseManager();
	}
	
	private void verifyEditLicenseYearWidgetUI() {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("hunt license year id editable:", false, huntLicYearWidget.isIdTextFieldEditable());
		passed &= MiscFunctions.compareResult("hunt license year status editable:", true, huntLicYearWidget.isStatusTextFieldEditable());
		passed &= MiscFunctions.compareResult("hunt license year product category editable:", false, huntLicYearWidget.isProductCategoryEditable());
		passed &= MiscFunctions.compareResult("hunt license year assigned product editable:", false, huntLicYearWidget.isAssignedProductEditable());
		passed &= MiscFunctions.compareResult("hunt license year location class editable:", false, huntLicYearWidget.isLocationClassEditable());
		passed &= MiscFunctions.compareResult("hunt license year license year editable:", false, huntLicYearWidget.isLicenseYearEditable());
		passed &= MiscFunctions.compareResult("hunt license year sell from date editable:", true, huntLicYearWidget.isSellFromDateEditable());
		passed &= MiscFunctions.compareResult("hunt license year sell from hour minute editable:", true, huntLicYearWidget.isSellFromHourMinuteEditable());
		passed &= MiscFunctions.compareResult("hunt license year sell from AM/PM editable:", true, huntLicYearWidget.isSellFromAPMEditable());
		passed &= MiscFunctions.compareResult("hunt license year sell to date editable:", true, huntLicYearWidget.isSellToDateEditable());
		passed &= MiscFunctions.compareResult("hunt license year sell to hour minute editable:", true, huntLicYearWidget.isSellToHourMinuteEditable());
		passed &= MiscFunctions.compareResult("hunt license year sell to AM/PM editable:", true, huntLicYearWidget.isSellToAPMEditable());
		if(!passed){
			throw new ErrorOnPageException("Hunt license year editabl status may not correct, please check the log above!");
		}
		logger.info("The editabl status for add hunt license year is correct!");
	}

	
	private void inactiveAllLicenseYear(){
		huntLicYearListPg.searchLicenseYear(ACTIVE_STATUS, StringUtil.EMPTY, StringUtil.EMPTY);
		List<String> ids = huntLicYearListPg.getAllLicenseYearIds();
		lm.desctiveHuntLicenseYears(ids);
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		TimeZone timezone = DataBaseFunctions.getContractTimeZone(schema);
		//login information
		String facility = lm.getFacilityName("1", schema);//Mississippi Department of Wildlife, Fisheries, and Parks
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facility;
		
		//Set up hunt code
		hunt.setHuntCode("HLY5");
		
		//Set up license year info
		ly.prdCategory = "Lottery";
		ly.assignedProd = "HP - Hunt product";
		ly.locationClass =  "01 - MDWFP Headquarters";
		ly.status = ACTIVE_STATUS;
		
		ly.licYear = String.valueOf(DateFunctions.getCurrentYear());
		
		ly.sellFromDate = DateFunctions.getToday(timezone);
		ly.sellFromTime = "11:11"; 
		ly.sellFromAmPm = "AM";
	
		ly.sellToDate = DateFunctions.getDateAfterToday(300, timezone);
		ly.sellToTime = "11:59";
		ly.sellToAmPm = "PM";
		
	}
}
