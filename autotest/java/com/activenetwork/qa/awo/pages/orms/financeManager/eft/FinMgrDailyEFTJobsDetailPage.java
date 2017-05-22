/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Jul 17, 2012
 */


public class FinMgrDailyEFTJobsDetailPage extends FinMgrDailyEFTJobsPage {
	static private FinMgrDailyEFTJobsDetailPage _instance = null;

	protected FinMgrDailyEFTJobsDetailPage() {
	}

	static public FinMgrDailyEFTJobsDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrDailyEFTJobsDetailPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text",
				"Daily EFT Job Info");
	}
	
	public void clickPaymentAllocationRecordsTab() {
		//DailyEFTJobDetailsTabs_1078848818
		browser.clickGuiObject(".id", new RegularExpression(
				"DailyEFTJobDetailsTabs_\\d+", false), ".text", "Payment Allocation Records");
	}


	public void clickStoreEFTAdjustmentRecordsTab() {
		browser.clickGuiObject(".id", new RegularExpression(
				"DailyEFTJobDetailsTabs_\\d+", false), ".text", "Store EFT Adjustment Records");
	}
	
	public void clickDepositAdjustmentRecordsTab() {
		browser.clickGuiObject(".id", new RegularExpression(
				"DailyEFTJobDetailsTabs_\\d+", false), ".text", "Deposit Adjustment Records");
	}
	
	public void clickVoucherInternalGCRecordsTab() {
		browser.clickGuiObject(".id", new RegularExpression(
				"DailyEFTJobDetailsTabs_\\d+", false), ".text", "Voucher/Internal GC Records");
	}

	/**
	 * Get job id
	 * @return
	 */
	public String getDailyEFTJobId() {
		//DailyEFTJobSearchView-1921496579.id
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("DailyEFTJobSearchView-\\d+\\.id", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find daily EFT Job ID DIV object.");
		}
		
		String jobId = objs[0].getProperty(".text").split("Job ID")[1].trim();
		Browser.unregister(objs);
		
		return jobId;
	}
	
	
	/**
	 * Get period end date
	 * @return
	 */
	public String getPeriodDate() {
		//DailyEFTJobSearchView-1442999740.periodDate:DATE
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("DailyEFTJobSearchView-\\d+\\.periodDate:DATE", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Period Date DIV object.");
		}
		
		String date = objs[0].getProperty(".text").split("Period Date")[1].trim();
		Browser.unregister(objs);
		
		return date;
	}

}
