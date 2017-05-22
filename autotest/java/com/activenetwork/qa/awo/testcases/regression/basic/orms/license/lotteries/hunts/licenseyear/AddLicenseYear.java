package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.hunts.licenseyear;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrEditHuntLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntLicYearListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the add license year for hunt, and verify the new added license year info in license year detail 
 * page and list page
 * @LinkSetUp:  d_hf_add_hunt:id=680(CODE='HLY1')
 * 				d_hf_add_lottery_prd:id=210(CODE='HP')
 * 				d_hf_assi_hunts_to_lottery:id=130
 * @SPEC: [TC:044960] Add Privilege Lottery Hunt License Year - data requirement
 * 		  [TC:044959] View Privilege Lottery Hunt License Year List 
 * @Task#: Auto-2064
 * @author Phoebe Chen
 * @Date  February 24, 2014
 */
public class AddLicenseYear extends LicenseManagerTestCase{
	private LicenseYear ly = new LicenseYear();
	private LicMgrHuntLicYearListPage huntLicYearListPg = LicMgrHuntLicYearListPage
			.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		lm.gotoHuntLicYearListFromHuntsListPg(hunt.getHuntCode());
		
		//Inactive the license year already exist for the following adding new license year test
		this.inactiveAllLicenseYear();
		lm.addHuntLicenseYear(ly);

		huntLicYearListPg.searchLicenseYear(ly.status, StringUtil.EMPTY, ly.assignedProd);
		ly.id = huntLicYearListPg.getHuntLicYearID(ly.assignedProd, ly.licYear);
		
		this.verifyHuntLicenseYear();
		
		huntLicYearListPg.verifyLicenseYearInfo(ly);
		
		lm.logOutLicenseManager();
	}

	private void verifyHuntLicenseYear() {
		LicMgrEditHuntLicYearWidget huntLicYearPg = LicMgrEditHuntLicYearWidget.getInstance();
		lm.gotoHuntLicenseYearWigetFromListPg(ly.id);
		huntLicYearPg.verifyLicenseYearInfo(ly);
		lm.gotoHuntLicenseYearListPageFromEditWidget();
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
		hunt.setHuntCode("HLY1");
		
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
		
		ly.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		ly.createLocation = facility;
		ly.createTime = DateFunctions.getToday(timezone);
	}

}
