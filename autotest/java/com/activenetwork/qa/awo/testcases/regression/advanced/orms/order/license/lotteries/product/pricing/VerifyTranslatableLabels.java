package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.product.pricing;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAddProductPricingWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryPricingPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the translatable labels in pricing list page, add product pricing page and edit product pricing page 
 * @Preconditions:
 * 1. Make sure the role has the features assigned:
 * View Privilege Lottery Product Pricing
 * Create/Modify Privilege Lottery Product Pricing
 * 2. The translatable labels are in the table "all_x_translation":
 * translatable.vendor -> Vendor
 * @SPEC: 
 * View Product Pricing List - translatable labels [TC:045962]
 * @LinkSetUp:
 * d_hf_add_lottery_prd:id=40
 * @Task#: Auto-2060
 * 
 * @author Lesley Wang
 * @Date  Jan 12, 2014
 */
public class VerifyTranslatableLabels extends LicenseManagerTestCase {
	private String code, labelKeys;
	private List<String> oriFeeLabels, updateFeeLabels;
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private LicMgrLotteryPricingPage pricingPg = LicMgrLotteryPricingPage.getInstance();
	private LicMgrAddProductPricingWidget addProdPringWidget = LicMgrAddProductPricingWidget	.getInstance();
	private int vendorLabelIndex;
	@Override
	public void execute() {
		try {
			// Get the original labels for vendor fee, state fee, transaction fee, holding fee, state transaction fee, state vendor fee
			oriFeeLabels = this.getPricingLabels();
			
			// Update the labels
			this.updatePricingLabels(updateFeeLabels);
			
			// Verify the update labels in UI
			this.verifyPricingLabels(updateFeeLabels);
		} finally {
			if (oriFeeLabels.size() > 0) {
				// Change back the labels in DB
				this.updatePricingLabels(oriFeeLabels);
			}
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "SK Contract";
		login.location = "SK Admin - Auto/SASK";
		
		code = "SL1";
		oriFeeLabels = new ArrayList<String> (); 
		updateFeeLabels = new ArrayList<String> ();
		updateFeeLabels.add("StateAuto");
		updateFeeLabels.add("StateTransAuto");
		updateFeeLabels.add("StateVendorAuto");
		updateFeeLabels.add("TransactionAuto");
		updateFeeLabels.add("VendorAuto");
		
		vendorLabelIndex = 4;
		
		labelKeys = "'translatable.statefee', 'translatable.statetransfee', 'translatable.statevendorfee', " +
				"'translatable.transactionfee', 'translatable.vendor'";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
	}

	private List<String> getPricingLabels() {
		db.resetSchema(schema);
		String sql = "select LABEL_VALUE from X_TRANSLATION where LABEL_KEY in (" + labelKeys + ") order by LABEL_KEY";
		logger.info("Execute query: " + sql);
		List<String> result = db.executeQuery(sql, "LABEL_VALUE");
		return result;
	}
	
	private void updatePricingLabels(List<String> labels) {
		db.resetSchema(schema);
		String[] keys = labelKeys.split(",");
		String sql = "";
		for (int i = 0; i < labels.size(); i++) {
			sql = "update X_TRANSLATION set LABEL_VALUE = '" + labels.get(i) + "' where LABEL_KEY =" + keys[i];
			logger.info("Execute query: " + sql);
			db.executeUpdate(sql);
		}
	}
	
	private void verifyPricingLabels(List<String> labels) {
		Browser.sleep(SLEEP);
		labels.set(vendorLabelIndex, labels.get(vendorLabelIndex) + " Fee");
		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(code);
		this.verifyLabelsInPricingList(labels);

		pricingPg.clickAddProductPricing();
		ajax.waitLoading();
		addProdPringWidget.waitLoading();
		this.verifyLabelsInAddEditPricingPage(labels);
		addProdPringWidget.clickCancel();
		ajax.waitLoading();
		pricingPg.waitLoading();
		lm.logOutLicenseManager();
	}
	
	private void verifyLabelsInPricingList(List<String> labels) {
		List<String> actualLabels = pricingPg.getPricingTableLabels();
		boolean result = true;
		for (int i = 0; i < labels.size(); i++) {
			String label = labels.get(i);
			if (!actualLabels.contains(label)) {
				result &= false;
				logger.error(label + " should be shown in pricing list!");
			}
		}
		if (!result) {
			throw new ErrorOnPageException("Labels in pricing list page are wrong!");
		}
		logger.info("Verify labels in pricing list page correctly!");
	}
	
	private void verifyLabelsInAddEditPricingPage(List<String> labels) {
		boolean result = true;
		// Same order as labelKeys.
		result &= MiscFunctions.compareString("State Fee Label", labels.get(0), addProdPringWidget.getStateFeeLabel());
		result &= MiscFunctions.compareString("State Transaction Fee Label", labels.get(1), addProdPringWidget.getStateTransFeeLabel());
		result &= MiscFunctions.compareString("State Vendor Fee Label", labels.get(2), addProdPringWidget.getStateVendorFeeLabel());
		result &= MiscFunctions.compareString("Transaction Fee Label", labels.get(3), addProdPringWidget.getTransFeeLabel());
		result &= MiscFunctions.compareString("Vendor Fee Label", labels.get(4), addProdPringWidget.getVendorFeeLabel());
		if (!result) {
			throw new ErrorOnPageException("Labels in add/edit pricing page are wrong!");
		}
		logger.info("Verify labels in add/edit pricing page correctly!");
	}
}
