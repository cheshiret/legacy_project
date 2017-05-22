package com.activenetwork.qa.awo.supportscripts.qasetup.unused;


import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.UserStoppedScriptException;

public class AddUseFeeSchedule extends FinanceManagerTestCase {
	private String msg = "";
	private String contract = "";
	private String previousContract = "";
	private FeeScheduleData fd = new FeeScheduleData();
	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
	private String feeSchId = "";
	private String testSuite = "basic";

	/**
	 * This script can not add Group use fee schedule for site, please use 'AddGroupUseFeeSchedule'
	 */
	public void execute() {
		int start = 0;
		int end = 999;
		boolean loggedIn = false;
		int counter = 0;
		int failed = 0;

		while (!dpIter.dpDone()) {
			if (counter < start	|| counter > end	|| !dpIter.dpString("testSuite")
					.equalsIgnoreCase(testSuite)) {
				counter++;
				dpIter.dpNext();
				continue;
			}

			initializeDataPool();// initialize the data pool data
			try {
				System.out.println("Adding for '"+fd.location+"' on dp#"+counter);
				if (!contract.equalsIgnoreCase(previousContract)) {
					loggedIn = false;
				}
				if (!loggedIn) {
					fnm.loginFinanceManager(login);
					fnm.gotoFeeMainPage();
					loggedIn = true;
				}

				feeSchId = fnm.addNewFeeSchedule(fd);
				feeMainPg.changeScheduleStatus(feeSchId, "Active");

				previousContract = contract;
				System.out.println("Use fee schedule "+feeSchId+" created successfully.");
			} catch (UserStoppedScriptException e) {
				throw e;
			} catch (Exception e) {
				failed++;
				e.printStackTrace();
				loggedIn = false;
				msg +="#"+counter+": facility - "+fd.location +" failed due to "+e.getMessage()+".\n";
			} finally {
				counter++;
				dpIter.dpNext();
			}
		}

		if (failed > 0) {
			logger.info("The following records failed:\n" + msg);
			throw new TestCaseFailedException("Failed: total " + failed
					+ " of " + (counter + 1) + " records failed.");
		} else {
			System.out.println("---All records passed!");
		}
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		dpFileName = AwoUtil.generateDatapoolPath(this.getClass());

		fd.isGroupRate = false;//can not add Group use fee schedule by using this script
		fd.feeType = "Use Fee";
	}

	public void initializeDataPool() {
		contract = dpIter.dpString("contract");
		login.contract = contract + " Contract";
		login.location = dpIter.dpString("roleLocation");

		fd.locationCategory = dpIter.dpString("locationCategory");
		fd.location = dpIter.dpString("facilityName");
		fd.productCategory = dpIter.dpString("prodCategory");
		fd.loop = dpIter.dpString("loop");
		fd.productGroup = dpIter.dpString("prodGroup");
		fd.product = dpIter.dpString("product");
		fd.effectDate = dpIter.dpString("effectDate");
		fd.startInv = dpIter.dpString("startInv");
		fd.endInv = dpIter.dpString("endInv");

		//		fd.isGroupRate = dpIter.dpBoolean("isGroupRate");// boolean
		fd.nightlyRate = dpIter.dpString("nightlyRate");
		fd.weeklyRate = dpIter.dpString("weeklyRate");
		fd.monthlyRate = dpIter.dpString("monthlyRate");
		fd.isFullStayMultiunit = dpIter.dpBoolean("isFullStay");// boolean
		fd.unitQuantity = dpIter.dpString("unitQuantity");
		fd.custRate = dpIter.dpString("custRate");
		fd.monRate = dpIter.dpString("monRate");
		fd.tuesRate = dpIter.dpString("tuesRate");
		fd.wedRate = dpIter.dpString("wedRate");
		fd.thurRate = dpIter.dpString("thurRate");
		fd.friRate = dpIter.dpString("friRate");
		fd.satRate = dpIter.dpString("satRate");
		fd.sunRate = dpIter.dpString("sunRate");

		// Fees by Event/Holiday
		fd.isAddEventHoliday = dpIter.dpBoolean("isEventHoliday");// boolean
		if(fd.isAddEventHoliday) {
			fd.holidayName = dpIter.dpString("holidayName").split("\\|");
			fd.holidayRate = dpIter.dpString("holidayRate").split("\\|");
			fd.holidayInvStart = dpIter.dpString("holidayInvStart").split("\\|");
			fd.holidayInvEnd = dpIter.dpString("holidayInvEnd").split("\\|");
		}
	}
}
