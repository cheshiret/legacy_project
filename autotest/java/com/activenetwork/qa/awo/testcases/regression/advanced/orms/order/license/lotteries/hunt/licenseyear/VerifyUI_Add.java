package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt.licenseyear;

import java.util.ArrayList;
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
 * @LinkSetUp:  d_hf_add_hunt:id=680(CODE='HLY2')
 * 				d_hf_add_lottery_prd:id=210(CODE='HP')
 * 				d_hf_assi_hunts_to_lottery:id=130
 * @SPEC: [TC:050339] Add Privilege Lottery Hunt License Year Popup - UI
 *        [TC:044960] Add Privilege Lottery Hunt License Year - data requirement 
 * @Task#: Auto-2064
 * @author Phoebe Chen
 * @Date  February 24, 2014
 */
public class VerifyUI_Add extends LicenseManagerTestCase{
	private LicenseYear ly = new LicenseYear();
	private LicMgrHuntLicYearListPage huntLicYearListPg = LicMgrHuntLicYearListPage.getInstance();
	private LicMgrAddHuntLicYearWidget huntLicYearWidget = LicMgrAddHuntLicYearWidget.getInstance();
	private List<String> expLYForSelect = new ArrayList<String>();
	private List<String> expPDForSelect = new ArrayList<String>();
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
		
		this.goToAddLicenseYearWidget();
		
		//Verify the ui for add hunt license year widget 
		verifyAddLicenseYearWidgetUI();
		
		//Verify the default value for add hunt license year widget
		verifyDefaultValueInAddHuntLicenseYearWidget();
		
		//Verify the value available for assigned product and license year
		verifyAvailableValueInLicenseYearWidget();
		
		//Cancel from add hunt license year
		this.cancelFromAddHuntLicenseYear();
		//Verify no hunt license year has been added
		huntLicYearListPg.searchLicenseYear(ly.status, StringUtil.EMPTY, ly.assignedProd);
		verifyNoHuntLicenseYearFound();
		
		lm.logOutLicenseManager();
	}
	
	private void verifyAvailableValueInLicenseYearWidget() {
		boolean passed = true;
		passed &= verifyList(expPDForSelect, huntLicYearWidget.getAssignedProductElement());
		passed &= verifyList(expLYForSelect, huntLicYearWidget.getLicenseYearElement());
		if(!passed){
			throw new ErrorOnPageException("Available value for assigned product and license year may not correct, please check the log above!");
		}
		logger.info("Available value for assigned product and license year  is correct!");
	}
	
	private boolean verifyList(List<String> expList, List<String> actList){
		if(!expList.equals(actList)){
			logger.info("list value is not correct,expect:" +expList.toString() + ",but actually is:"+actList.toString() );
			return false;
		}
		return true;
	}

	private void verifyNoHuntLicenseYearFound() {
		int num = huntLicYearListPg.getAllLicenseYearIds().size();
		if(num > 0){
			throw new ErrorOnPageException("Some hunt license year has been added after click cancel!");
		}
		logger.info("No hunt license year has been added after click cancel!");
	}

	private void cancelFromAddHuntLicenseYear() {
		huntLicYearWidget.clickCancel();
		ajax.waitLoading();
		huntLicYearListPg.waitLoading();
	}

	private void verifyDefaultValueInAddHuntLicenseYearWidget() {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Default value for status:", ACTIVE_STATUS, huntLicYearWidget.getStatus());
		passed &= MiscFunctions.compareResult("Default value for location class:", "All", huntLicYearWidget.getLocationClass());
		passed &= MiscFunctions.compareResult("Default value for sell from hour/minute:", "12:00", huntLicYearWidget.getSellFromHourMinute());
		passed &= MiscFunctions.compareResult("Default value for sell from APM:", "AM", huntLicYearWidget.getSellFromAPM());
		passed &= MiscFunctions.compareResult("Default value for sell to hour/minute:", "11:59", huntLicYearWidget.getSellToHourMinute());
		passed &= MiscFunctions.compareResult("Default value for sell to APM:", "PM", huntLicYearWidget.getSellToAPM());
		if(!passed){
			throw new ErrorOnPageException("Default value for sell from/to hour/minute and AM/PM may not correct, please check the log above!");
		}
		logger.info("The Default value for sell from/to hour/minute and AM/PM is correct!");
	}

	private void verifyAddLicenseYearWidgetUI() {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("hunt license year id editable:", false, huntLicYearWidget.isIdTextFieldEditable());
		passed &= MiscFunctions.compareResult("hunt license year status editable:", false, huntLicYearWidget.isStatusTextFieldEditable());
		passed &= MiscFunctions.compareResult("hunt license year product category editable:", true, huntLicYearWidget.isProductCategoryEditable());
		passed &= MiscFunctions.compareResult("hunt license year assigned product editable:", true, huntLicYearWidget.isAssignedProductEditable());
		passed &= MiscFunctions.compareResult("hunt license year location class editable:", true, huntLicYearWidget.isLocationClassEditable());
		passed &= MiscFunctions.compareResult("hunt license year license year editable:", true, huntLicYearWidget.isLicenseYearEditable());
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
	
	private void goToAddLicenseYearWidget(){
		huntLicYearListPg.clickAddLicenseYear();
		huntLicYearWidget.waitLoading();
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
	
		ly.sellToDate = DateFunctions.getDateAfterToday(300, timezone);
		ly.sellToTime = "11:59";
		ly.sellToAmPm = "PM";
		
		expPDForSelect.add(StringUtil.EMPTY);
		expPDForSelect.add(ly.assignedProd);
		this.setupExpectLicenseYearCanBeSelect();
	}

	private void setupExpectLicenseYearCanBeSelect() {
		int year = DateFunctions.getCurrentYear();
		expLYForSelect.add(StringUtil.EMPTY);
		for(int i=0; i<11; i++){
			expLYForSelect.add(String.valueOf(year-1));
			year++;
		}
	}

}
