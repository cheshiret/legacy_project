package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 27, 2011
 */
public class LicMgrVehicleContractFeesPage extends LicMgrEditVehicleDetailsPage {
	
	private static LicMgrVehicleContractFeesPage _instance = null;
	
	protected LicMgrVehicleContractFeesPage() {}
	
	public static LicMgrVehicleContractFeesPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrVehicleContractFeesPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "ProductContractorFeeGrid");
	}
	
	public RaFeeScheduleData getFeeSchInfo(String scheduleID) {
		RaFeeScheduleData schedule = new RaFeeScheduleData();

		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"ProductContractorFeeGrid");
		IHtmlTable table = (IHtmlTable) objs[0];
		
		int row = table.findRow(0, scheduleID);
		schedule.feeId = table.getCellValue(row, 0);
		schedule.activeStatus = table.getCellValue(row, 1);
		schedule.product = table.getCellValue(row, 2);
		schedule.productGroup = table.getCellValue(row, 3);
		schedule.locationClass = table.getCellValue(row, 4);
		schedule.licenseYearFrom = table.getCellValue(row, 5);
		schedule.licenseYearTo = table.getCellValue(row, 6);
		schedule.vehicleType =  table.getCellValue(row, 7);
		schedule.effectDate = table.getCellValue(row, 8);
		schedule.tranType = table.getCellValue(row, 9);
		schedule.baseRate = table.getCellValue(row, 10);
		schedule.acctCode = table.getCellValue(row,11);
		
		Browser.unregister(objs);

		return schedule;
	}
	
	public void verifyScheduleListInfo(RaFeeScheduleData schedule) {
		logger.info("Start to Verify fee schedule list information.");

		RaFeeScheduleData actually = this.getFeeSchInfo(schedule.feeId);

		if (!schedule.feeId.equalsIgnoreCase(actually.feeId)) {
			throw new ItemNotFoundException("Fee Schedule Id " + actually.feeId
					+ " not same with given value " + schedule.feeId);
		}
		if (!schedule.activeStatus.equalsIgnoreCase(actually.activeStatus)) {
			throw new ItemNotFoundException("Active Status "
					+ actually.activeStatus + " not same with given value "
					+ schedule.activeStatus);
		}
		if(!schedule.product.equalsIgnoreCase("All")){
			schedule.product = schedule.product.split("-")[1];
		}
		if (!schedule.product.equalsIgnoreCase(actually.product)) {
			throw new ItemNotFoundException("Assignment Product "
					+ actually.product + " not same with given value "
					+ schedule.product);
		}
		if (!schedule.productGroup
				.equalsIgnoreCase(actually.productGroup)) {
			throw new ItemNotFoundException("Product Group "
					+ actually.productGroup + " not same with given value "
					+ schedule.productGroup);
		}
		
		if (schedule.locationClass != null
				&& !schedule.locationClass.equals("")) {
			if (actually.locationClass.contains("-") && !schedule.locationClass
					.equalsIgnoreCase(actually.locationClass.split("-")[1])) {
				throw new ItemNotFoundException("Location Class "
						+ actually.locationClass
						+ " not same with given value "
						+ schedule.locationClass);
			}else if(!actually.locationClass.contains("-") && !schedule.locationClass
					.equalsIgnoreCase(actually.locationClass)){
				throw new ItemNotFoundException("Location Class "
						+ actually.locationClass
						+ " not same with given value "
						+ schedule.locationClass);
			}
		}
		if (!schedule.vehicleType
				.equalsIgnoreCase(actually.vehicleType)) {
			throw new ItemNotFoundException("Vehicle Type "
					+ actually.vehicleType + " not same with given value "
					+ schedule.vehicleType);
		}
		if (!schedule.effectDate.equalsIgnoreCase(DateFunctions.formatDate(
				actually.effectDate, "EEE MMM d yyyy"))) {
			throw new ItemNotFoundException("Effective date "
					+ actually.effectDate + " not same with given value "
					+ schedule.effectDate);
		}
		if (schedule.tranType != null && !schedule.tranType.equals("")) {
			if (!schedule.tranType.equalsIgnoreCase(actually.tranType)) {
				throw new ItemNotFoundException("Selected Transaction Type "
						+ actually.tranType + " not same with given value "
						+ schedule.tranType);
			}
		}
		if (schedule.baseRate != null && !schedule.baseRate.equals("")) {
			if (!schedule.baseRate.equalsIgnoreCase(actually.baseRate)) {
				throw new ItemNotFoundException("Base Rate "
						+ actually.baseRate + " not same with given value "
						+ schedule.baseRate);
			}
		}
		if (schedule.licenseYearFrom != null
				&& !schedule.licenseYearFrom.equals("")) {
			if (!schedule.licenseYearFrom
					.equalsIgnoreCase(actually.licenseYearFrom)) {
				throw new ItemNotFoundException("License Year From "
						+ actually.licenseYearFrom
						+ " not same with given value "
						+ schedule.licenseYearFrom);
			}
		}
		if (schedule.licenseYearTo != null
				&& !schedule.licenseYearTo.equals("")) {
			if (!schedule.licenseYearTo
					.equalsIgnoreCase(actually.licenseYearTo)) {
				throw new ItemNotFoundException("License Year To "
						+ actually.licenseYearTo
						+ " not same with given value "
						+ schedule.licenseYearTo);
			}
		}
		if (schedule.acctCode != null && !schedule.acctCode.equals("")) {
			if (!schedule.acctCode.contains(actually.acctCode.substring(actually.acctCode.indexOf("-")+1))) {
				throw new ItemNotFoundException("Account Code "
						+ actually.acctCode + " not same with given value "
						+ schedule.acctCode);
			}
		}

	}
}
