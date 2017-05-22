package com.activenetwork.qa.awo.supportscripts.support.inventorymgr;

import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuotaTypeInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.awo.supportscripts.AbstractSingleDatapoolSupportCase;
import com.activenetwork.qa.awo.util.DatapoolUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.AutomationLogger;

/**
 *
 * @author fliu
 * @Date  March 13, 2012
 */
// TODO: Move the logMsg to super class
public class AddQuotaType extends AbstractSingleDatapoolSupportCase {
	private String facilityName;
	private QuotaTypeInfo quotaTypeInfo;
	private String previousContract;
	private boolean loggedIn;
	private InventoryManager im;
	private InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
	private InvMgrHomePage homePage = InvMgrHomePage.getInstance();
	private static AutomationLogger logger = AutomationLogger.getInstance();

	@Override
	protected void initRange() {
		startpoint = 1;
		endpoint = 6;
	}

	@Override
	protected void readDataPool(IDatapoolIterator dpIter) {
		facilityName = dpIter.dpString("facilityName");
		quotaTypeInfo = DatapoolUtil.fill(QuotaTypeInfo.class, dpIter);
	}

	@Override
	public void execute(Orms orms) {
		try {
			LoginInfo loginInfo = orms.loginInfo;

			if (!loginInfo.contract.equals(previousContract) && loggedIn) {
				im.logoutInvManager();
				loggedIn = false;
			}

			if (!loggedIn) { // Logged in
				im = orms.loginInventoryManager();
				previousContract = loginInfo.contract;
				loggedIn = true;

			}

			if(!homePage.exists()){
				topMenu.clickHome();
				homePage.waitLoading();
			}

			im.gotoFacilityDetailsPg(facilityName);
			im.gotoEntranceListPage("Entrance Set-up");
			im.gotoQuotaTypeListPage();

			if (!im.verifyQuotaTypeExist(quotaTypeInfo.quotaTypeCode)) {
				// add quota type on Quota Type Detail Page

				im.addNewQuotaType(quotaTypeInfo);

				if(im.verifyQuotaTypeExist(quotaTypeInfo.quotaTypeCode)){
					setResult("Success");
				}
				else{
					logger.error("The quota type does not exist!");
					throw new ItemNotFoundException("The quota type " + quotaTypeInfo.quotaTypeCode + " does not exist!");
				}

			} else {
				setResult("Success -- Same quotaType already exists");
			}


		} catch (Exception e) {
			setResult("Create the new quota type failed. Error -- " + e.getMessage());

			logger.error(e);
			// Plz comment out this line when you debugging
			loggedIn = false;

			throw new RuntimeException(e);
		}
	}
}
