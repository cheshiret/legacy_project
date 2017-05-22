/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.eft;




import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Sep 3, 2012
 */



public class FinMgrRemittanceDetailPage extends FinMgrRemittancePage {
	static private FinMgrRemittanceDetailPage _instance = null;

	protected FinMgrRemittanceDetailPage() {
	}

	static public FinMgrRemittanceDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrRemittanceDetailPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("^Remittance Info\\s+Remittance Number.*",false));
	}
	
	public void clickTransmissionTab() {

		browser.clickGuiObject(".id", new RegularExpression(
				"EFTREmittanceDetailsPanels_\\d+", false), ".text", "Transmission");
	}
	
	public void clickPaymentAllocationRecordsTab() {

		browser.clickGuiObject(".id", new RegularExpression(
				"EFTREmittanceDetailsPanels_\\d+", false), ".text", "Payment Allocation Records");
	}


	public void clickStoreEFTAdjustmentRecordsTab() {
		browser.clickGuiObject(".id", new RegularExpression(
				"EFTREmittanceDetailsPanels_\\d+", false), ".text", "Store EFT Adjustment Records");
	}
	
	public void clickDepositAdjustmentRecordsTab() {
		browser.clickGuiObject(".id", new RegularExpression(
				"EFTREmittanceDetailsPanels_\\d+", false), ".text", "Deposit Adjustment Records");
	}
	
	public void clickVoucherInternalGCRecordsTab() {
		browser.clickGuiObject(".id", new RegularExpression(
				"EFTREmittanceDetailsPanels_\\d+", false), ".text", "Voucher/Internal GC Records");
	}

	
}
