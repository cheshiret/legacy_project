package com.activenetwork.qa.awo.supportscripts.support.financemgr;

import com.activenetwork.qa.awo.datacollection.legacy.feeData.D_GroupUseFee;
import com.activenetwork.qa.awo.datacollection.legacy.feeData.IncrementsOrRangeFeeData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeesTabs;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description : Functional Test Script
 * 
 * @author QA
 */
public class AddGroupUseFeeSchedule extends SupportCase {
	/**
	 * Script Name : <b>AddGroupUseFeeSchedule</b> Generated : <b>Feb 6, 2010
	 * 12:36:50 AM</b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2010/02/06
	 * @author dsui
	 */

	LoginInfo login = new LoginInfo();
	FinanceManager fin = FinanceManager.getInstance();
	D_GroupUseFee fd = new D_GroupUseFee();
	boolean loggined = false;

	public void wrapParameters(Object[] param) {
		startpoint = 77; // the start point in the datapool
		endpoint = 77; // the end point in the datapool

		// Initialize login informaiton
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = "qa-auto-adm";
		login.password = "auto1234";
		// login.contract="CA Contract";
		// login.location="Administrator/California Parks and Recreation";

		dataSource = casePath + "/" + caseName;

		logMsg = new String[3];
		logMsg[0] = "index";
		logMsg[1] = "id";
		logMsg[2] = "result";

	}

	public void execute() {

		// Add group fee schedule
		String id = this.setUpFeeSchedule(login, fd);

		// Activate fee schedule
		fin.activeOrDeactiveFeeSchedule(id, true);

		if (id.length() > 0) {
			logMsg[1] = id;
			logMsg[2] = "Success";
		}
	}

	public void getNextData() {
		this.fd = new D_GroupUseFee();
		logMsg[0] = cursor + "";
		logMsg[1] = "Unknown";
		logMsg[2] = "Failed due to error";

		login.contract = dpIter.dpString("Contract");
		login.location = dpIter.dpString("logLocation");

		fd.locationCategory = dpIter.dpString("Location Category");
		fd.location = dpIter.dpString("Location").toString().toUpperCase();

		fd.productCategory = dpIter.dpString("Product Category");
		fd.feeType = dpIter.dpString("Fee Type");
		fd.loop = dpIter.dpString("Loop").trim().toString();
		fd.rateType = dpIter.dpString("Rate Type");

		fd.productGroup = dpIter.dpString("Product Group").trim().toString();
		fd.product = dpIter.dpString("Product").replaceAll(" - ", "-");
		fd.attrType = dpIter.dpString("attributeType");
		fd.effectDate = dpIter.dpString("Effective Date");
		fd.startDate = dpIter.dpString("Start Date");
		fd.endDate = dpIter.dpString("End Date");

		fd.season = dpIter.dpString("Season").trim();

		fd.salesChannel = dpIter.dpString("Sales Channel").trim();

		fd.inOutState = dpIter.dpString("In Out State").trim();

		fd.unitOfStay = dpIter.dpString("Unit Of Stay");

		fd.baseRate = dpIter.dpString("AnyUnit_Default").replace('$', ' ')
				.trim();
		if (fd.baseRate == null || fd.baseRate.length() < 1)
			fd.baseRate = "0";

		fd.monRate = dpIter.dpString("Monday Fee").replace('$', ' ').trim();
		fd.tuesRate = dpIter.dpString("Tuesday Fee").replace('$', ' ').trim();
		fd.wedRate = dpIter.dpString("Wednesday Fee").replace('$', ' ').trim();
		fd.thurRate = dpIter.dpString("Thursday Fee").replace('$', ' ').trim();
		fd.friRate = dpIter.dpString("Friday Fee").replace('$', ' ').trim();
		fd.satRate = dpIter.dpString("Saturday Fee").replace('$', ' ').trim();
		fd.sunRate = dpIter.dpString("Sunday Fee").replace('$', ' ').trim();

		fd.acctCode = dpIter.dpString("Account Code");
		fd.occupantIncrementOrRange = dpIter
				.dpString("OccupantIncrementsRanges");

		String ir = dpIter.dpString("IncrementRange_ForEveryOcc");
		if (ir != null && ir.length() > 0) {
			String[] tokens = ir.split("\\|");
			fd.ir = new IncrementsOrRangeFeeData[tokens.length];
			for (int i = 0; i < tokens.length; i++) {

				// String[] s
				// =RegularExpression.getMatches(tokens[i],"\\d+(\\.\\d{0,2})?");
				String[] s = tokens[i].split("\\;");
				fd.ir[i] = new IncrementsOrRangeFeeData();
				for (int j = 0; j < s.length; j++) {
					// switch(j)
					// {
					// case 0:fd.ir[i].occIncrementRange = s[j];continue;
					// case 1:fd.ir[i].defaultRate=s[j];continue;
					// case 2:fd.ir[i].monRate=s[j];continue;
					// case 3:fd.ir[i].tuesRate=s[j];continue;
					// case 4:fd.ir[i].wedRate=s[j];continue;
					// case 5:fd.ir[i].thurRate=s[j];continue;
					// case 6:fd.ir[i].friRate=s[j];continue;
					// case 7:fd.ir[i].satRate=s[j];continue;
					// case 8:fd.ir[i].sunRate=s[j]; continue;
					// default: continue;
					// }
					if (s[j].contains("Increment") || s[j].contains("Range")) {
						fd.ir[i].occIncrementRange = s[j].split("\\:")[1];
					}
					if (s[j].contains("Price")) {
						fd.ir[i].defaultRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Mon")) {
						fd.ir[i].monRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Tue")) {
						fd.ir[i].tuesRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Wed")) {
						fd.ir[i].wedRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Thu")) {
						fd.ir[i].thurRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Fri")) {
						fd.ir[i].friRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Sat")) {
						fd.ir[i].satRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Sun")) {
						fd.ir[i].sunRate = s[j].split("\\:")[1];
					}
				}
			}
		}

		fd.vehicleIncrementOrRange = dpIter.dpString("VehicleIncrementsRanges");
		String ivehicle = dpIter.dpString("IncrementRange_ForEveryVehi");
		if (ivehicle != null && ivehicle.length() > 0) {
			String[] tokens = ivehicle.split("\\|");
			fd.ivehicle = new IncrementsOrRangeFeeData[tokens.length];
			for (int i = 0; i < tokens.length; i++) {
				// String[] s = RegularExpression.getMatches(tokens[i],
				// "\\d+(\\.\\d{0,2})?");
				String[] s = tokens[i].split("\\;");
				fd.ivehicle[i] = new IncrementsOrRangeFeeData();
				for (int j = 0; j < s.length; j++) {
					// switch (j) {
					// case 0:
					// fd.ivehicle[i].vehiIncrementRange = s[j];
					// continue;
					// case 1:
					// fd.ivehicle[i].defaultRate = s[j];
					// continue;
					// case 2:
					// fd.ivehicle[i].monRate = s[j];
					// continue;
					// case 3:
					// fd.ivehicle[i].tuesRate = s[j];
					// continue;
					// case 4:
					// fd.ivehicle[i].wedRate = s[j];
					// continue;
					// case 5:
					// fd.ivehicle[i].thurRate = s[j];
					// continue;
					// case 6:
					// fd.ivehicle[i].friRate = s[j];
					// continue;
					// case 7:
					// fd.ivehicle[i].satRate = s[j];
					// continue;
					// case 8:
					// fd.ivehicle[i].sunRate = s[j];
					// continue;
					// default:
					// continue;
					// }
					if (s[j].contains("Increment") || s[j].contains("Range")) {
						fd.ivehicle[i].vehiIncrementRange = s[j].split("\\:")[1];
					}
					if (s[j].contains("Price")) {
						fd.ivehicle[i].defaultRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Mon")) {
						fd.ivehicle[i].monRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Tue")) {
						fd.ivehicle[i].tuesRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Wed")) {
						fd.ivehicle[i].wedRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Thu")) {
						fd.ivehicle[i].thurRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Fri")) {
						fd.ivehicle[i].friRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Sat")) {
						fd.ivehicle[i].satRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Sun")) {
						fd.ivehicle[i].sunRate = s[j].split("\\:")[1];
					}
				}
			}
		}
	}

	/**
	 * Set up fee schedule
	 * 
	 * @param login
	 * @param fd
	 * @return
	 * @throws ItemNotFoundException
	 */
	public String setUpFeeSchedule(LoginInfo login, D_GroupUseFee fd)
			throws ItemNotFoundException {
		FinMgrFeeMainPage fmFeeMainPg = FinMgrFeeMainPage.getInstance();
		FinMgrFeesTabs fmFeeTab = FinMgrFeesTabs.getInstance();

		if (!loggined || (loggined && !fmFeeTab.exists())) {
			fin.loginFinanceManager(login);
			loggined = true;
			fin.gotoFeeMainPage();
		} else {
			fmFeeTab.clickFees();
			fmFeeMainPg.waitLoading();
		}
		fin.addNewGroupUseFeeSchedules(fd);
		return fd.feeSchId;
	}

}
