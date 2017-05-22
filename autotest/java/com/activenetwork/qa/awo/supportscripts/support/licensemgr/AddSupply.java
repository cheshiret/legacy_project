package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import java.util.Arrays;
import java.util.List;

import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.SupplyInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrCreateNewSupplyPage;
import com.activenetwork.qa.awo.supportscripts.AbstractSingleDatapoolSupportCase;
import com.activenetwork.qa.awo.util.DatapoolUtil;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 *
 * @author Reed Wang
 *
 */
// TODO: Create another super class to move try-catch block there?
// TODO: We close browser when exception occurs, so the screenshot couldn't be correctly captured, solve this.
public class AddSupply extends AbstractSingleDatapoolSupportCase {

	private SupplyInfo supply;
	private String previousContract;
	private boolean loggedIn;
	private LicenseManager lm;
	private static AutomationLogger logger = AutomationLogger.getInstance();

	@Override
	protected void initRange() {
		startpoint = 0;
		endpoint = 0;
	}

	@Override
	protected void readDataPool(IDatapoolIterator dpIter) {
		supply = DatapoolUtil.fill(SupplyInfo.class, dpIter);
		String availableToApps = dpIter.dpString("availableToApp");
		if (StringUtil.notEmpty(availableToApps)) {
			List<String> apps = Arrays.asList(availableToApps.split(","));
			if (!apps.contains(LicMgrCreateNewSupplyPage.APPS_ALL)) {
				supply.availableToApp.put(LicMgrCreateNewSupplyPage.APPS_ALL,
						false);
				for (String app : apps) {
					supply.availableToApp.put(app, true);
				}
			}
		}
	}

	@Override
	protected void execute(Orms orms) {
		try {
			LoginInfo loginInfo = orms.loginInfo;

			if (!loginInfo.contract.equals(previousContract) && loggedIn) {
				lm.logOutLicenseManager();
				loggedIn = false;
			}

			if (!loggedIn) {
				lm = orms.loginLicenseManager();
				previousContract = loginInfo.contract;
				loggedIn = true;

				lm.gotoSupplySearchListPageFromTopMenu();
			}

			lm.addSupplyProudct(supply);

			setResult("Success");

		} catch (Exception e) {
			setResult("Error -- " + e.getMessage());

			logger.error(e);

			loggedIn = false;

			throw new RuntimeException(e);
		}
	}
}
