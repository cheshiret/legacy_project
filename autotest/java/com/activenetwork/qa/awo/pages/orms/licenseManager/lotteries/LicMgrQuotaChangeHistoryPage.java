package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: 
 * @Preconditions:
 * @SPEC: 
 * @LinkSetUp:
 * @Task#: 
 * 
 * @author Lesley Wang
 * @Date  Jan 27, 2014
 */
public class LicMgrQuotaChangeHistoryPage extends LicMgrLotteriesCommonPage {
	private static LicMgrQuotaChangeHistoryPage _instance = null;

	private LicMgrQuotaChangeHistoryPage() {
	}

	public static LicMgrQuotaChangeHistoryPage getInstance() {
		if (_instance == null) {
			_instance = new LicMgrQuotaChangeHistoryPage();
		}
		return _instance;
	}

	protected Property[] quotaHistoryTopTable() {
		return Property.concatPropertyArray(this.table(), ".id", "Hunt Quota Change History");
	}
	
	protected Property[] quotaHistoryTable() {
		return Property.concatPropertyArray(this.table(), ".id", "ConfigurableChangeList_LIST");
	}
	
	protected Property[] quotaInfoSpan(String label) {
		return Property.concatPropertyArray(this.span(), ".text", new RegularExpression("^"+label+".*", false));
	}
	
	protected Property[] nextLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Next");
	}
	
	protected Property[] returnToQuotaDetailsBtn() {
		return Property.concatPropertyArray(this.a(), ".text", "Return to Quota Details");
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(quotaHistoryTopTable());
	}
	
	public void clickNext() {
		browser.clickGuiObject(nextLink());
	}
	
	public boolean gotoNext() {
		boolean exists = browser.checkHtmlObjectExists(nextLink());
		if(exists) {
			this.clickNext();
			waitLoading();
		}
		return exists;
	}
	
	private String getQuotaInfo(String label) {
		IHtmlObject[] objs = browser.getHtmlObject(this.quotaInfoSpan(label));
		String text = browser.getTextFieldValue(this.input("text"), objs[0]);
		Browser.unregister(objs);
		return text;
	}
	
	public String getQuotaID() {
		return this.getQuotaInfo("Quota ID");
	}
	
	public String getQuotaDescription() {
		return this.getQuotaInfo("Quota Description");
	}
	
	private IHtmlObject[] getHistoryTable() {
		IHtmlObject[] objs = browser.getHtmlObject(this.quotaHistoryTable());
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't find the history table object");
		}
		return objs;
	}
	
	public List<ChangeHistory> getChangeHistoryInfo() {
		List<ChangeHistory> list = new ArrayList<ChangeHistory>();
		IHtmlObject[] objs = null;
		IHtmlTable table = null;
		do{
			objs = this.getHistoryTable();
			table = (IHtmlTable) objs[0];
			for (int i = 1; i < table.rowCount(); i++) {
				ChangeHistory history = new ChangeHistory();
				history.changeDate = table.getCellValue(i, 0);
				history.object = table.getCellValue(i, 1);
				history.action = table.getCellValue(i, 2);
				history.field = table.getCellValue(i, 3);
				history.oldValue = table.getCellValue(i, 4);
				history.newValue = table.getCellValue(i, 5);
				history.user = table.getCellValue(i, 6);
				history.location = table.getCellValue(i, 7);
				list.add(history);
			}
		}while(this.gotoNext());
		
		Browser.unregister(objs);
		return list;
	}
	
	public void verifyChangeHistoryInfo(List<ChangeHistory> histories) {
		boolean result = true;
		
		List<ChangeHistory> actualHistories = this.getChangeHistoryInfo();
		for (int i=0; i < histories.size(); i++) {
			actualHistories.get(i).changeDate = DateFunctions.formatDate(actualHistories.get(i).changeDate, "E MMM dd yyyy");
			actualHistories.get(i).user = actualHistories.get(i).user.replace(", ", ",");
			result &= actualHistories.get(i).equals(histories.get(i));
		}
		
		if (!result) {
			throw new ErrorOnPageException("Chagne History info is wrong!");
		}
		logger.info("Verify change history info correctly!");
	}
	
	public void clickReturnToQuotaDetails() {
		browser.clickGuiObject(returnToQuotaDetailsBtn());
	}
}
