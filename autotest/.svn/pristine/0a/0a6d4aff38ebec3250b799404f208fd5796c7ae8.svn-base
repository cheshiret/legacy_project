package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear;

import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:(P) Verify no Valid From Date/Time and Valid To Date/Time in License Year list, Add and edit license year widget when product type is "Inventory"
 * @LinkSetUp:
 * d_hf_add_privilege_prd:id=2580 --Inventory,ConvPack Type.
 * @SPEC:
 * Add Privilege License Year - Product Group of Inventory [TC:100344]
 * Edit Privilege License Year - Product Group of Inventory [TC:100365]
 * View License Year - Product Group of Inventory [TC:100343]
 * @Task#:AUTO-2050
 * 
 * @author SWang
 * @Date  Jan 25, 2014
 */
public class VerifyValidFromToDateWithInventoryPrdType extends LicenseManagerTestCase {
	private LicMgrPrivilegeAddLicYearWidget addYearWidget = LicMgrPrivilegeAddLicYearWidget.getInstance();
	private LicMgrPrivilegeEditLicenseYearWidget editYearWidget = LicMgrPrivilegeEditLicenseYearWidget.getInstance();
	private LicMgrPrivilegeLicenseYearPage licenseYearList = LicMgrPrivilegeLicenseYearPage.getInstance();
	private String validFromDateTime, validToDateTime, sellFromDateTime;
	private LicenseYear ly = new LicenseYear();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeLicenseYearPage();

		//Check no Valid From Date and From Time, no Valid To Date and To Time field

		//Review
		noValidFromDateTimeAndValidToDateTimed(licenseYearList);

		//Add
		lm.gotoAddPrivLicenseYearWidgetFromLicenseYearList();
		noValidFromDateTimeAndValidToDateTimed(addYearWidget);
		lm.gotoLicenseYearListFromAddPrivLicenseYearWidget();

		//Edit
		String licenseYearID = licenseYearList.getLicenseYearId(ly.status, ly.locationClass, ly.licYear);
		if(StringUtil.isEmpty(licenseYearID)){
			lm.addLicenseYear(ly);
		}
		lm.gotoPrivilegeLicenseYearDetailPg(licenseYearID);
		noValidFromDateTimeAndValidToDateTimed(editYearWidget);
		lm.gotoLicenseYearListFromEditPrivLicenseYearWidget();

		//Logout
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "SK Contract";
		login.location = "SK Admin - Auto/SASK";

		privilege = new PrivilegeInfo();
		privilege.code = "SU3";
		privilege.name = "PrivilegeForSetup3";

		validFromDateTime = "Valid From Date/Time";
		validToDateTime = "Valid To Date/Time";
		sellFromDateTime = "Sell From Date/Time";

		ly.status = "Active";
		ly.licYear = (DateFunctions.getCurrentYear()+1)+"";
		ly.locationClass = "All";
		ly.sellFromDate = DateFunctions.getDateAfterToday(2);
		ly.sellToDate = DateFunctions.getDateAfterToday(365);
		ly.sellFromTime = "11:00";
		ly.sellToTime = "12:00";
		ly.sellFromAmPm = "AM";
		ly.sellToAmPm = "PM";
	}

	private void noValidFromDateTimeAndValidToDateTimed(Page page){
		boolean result = true;

		if(page==addYearWidget){
			logger.info("Verify Valid From Date/Time and Valid To Date/Time in add license year widget.");
			result &= MiscFunctions.compareResult("Valid From Date", false, addYearWidget.isValidFromDateExsit());
			result &= MiscFunctions.compareResult("Valid From Time", false, addYearWidget.isValidFromTimeExsit());
			result &= MiscFunctions.compareResult("Valid From AmPm", false, addYearWidget.isValidFromAmPmExsit());
			result &= MiscFunctions.compareResult("Valid To Date", false, addYearWidget.isValidToDateExsit());
			result &= MiscFunctions.compareResult("Valid From Time", false, addYearWidget.isValidToTimeExsit());
			result &= MiscFunctions.compareResult("Valid From AmPm", false, addYearWidget.isValidToAmPmExsit());
		}else if(page==editYearWidget){
			logger.info("Verify Valid From Date/Time and Valid To Date/Time in edit license year widget.");
			result &= MiscFunctions.compareResult("Valid From Date", false, editYearWidget.isValidFromDateExsit());
			result &= MiscFunctions.compareResult("Valid From Time", false, editYearWidget.isValidFromTimeExsit());
			result &= MiscFunctions.compareResult("Valid From AmPm", false, editYearWidget.isValidFromAmPmExsit());
			result &= MiscFunctions.compareResult("Valid To Date", false, editYearWidget.isValidToDateExsit());
			result &= MiscFunctions.compareResult("Valid To Time", false, editYearWidget.isValidToTimeExsit());
			result &= MiscFunctions.compareResult("Valid To AmPm", false, editYearWidget.isValidToAmPmExsit());
		}else if(page==licenseYearList){
			logger.info("Verify Valid From Date/Time and Valid To Date/Time in license year list.");
			List<Boolean> results = licenseYearList.isLicenseYearColumnExist(Arrays.asList(sellFromDateTime, validFromDateTime, validToDateTime));
			result &= MiscFunctions.compareResult("Valid From Date/Time and Valid To Date/From", Arrays.asList(true,false,false).toString(), results.toString());
		}else throw new ErrorOnPageException("Can't find matched page.");

		if(!result){
			throw new ErrorOnPageException("Failed to verify Valid From Date/Time and Valid To Date/Time ");
		}
		logger.info("Successfully verify Valid From Date/Time and Valid To Date/Time");
	}
}
