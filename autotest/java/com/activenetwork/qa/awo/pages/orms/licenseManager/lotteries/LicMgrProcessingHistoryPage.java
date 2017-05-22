package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
/**
 * This is the changed history page for privilege lottery schedule
 * @author pchen
 *
 */
public class LicMgrProcessingHistoryPage extends LicMgrLotteriesCommonPage{
	private static LicMgrProcessingHistoryPage _instance= null;
	private LicMgrProcessingHistoryPage (){}
	
	public static LicMgrProcessingHistoryPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrProcessingHistoryPage();
		}
		
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","PrivilegeLotteryScheduleChangeHistory");
	}
	
	/**
	 * Click return to hunt details button
	 */
	public void clickReturnToLotteryScheduleDetails(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Return to Lottery Schedule Details",true);
	}
	
	/**
	 * Get all the records in history list table
	 * @return
	 */
	public List<ChangeHistory> getChangeListTableInfo(){
		List<ChangeHistory> list = new ArrayList<ChangeHistory>();
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		int rows;
		int columns;
		
		do{
		objs = browser.getTableTestObject(".id", "ConfigurableChangeList_LIST");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find history records of hunt table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page LicMgrHuntHistoryPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
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
		Browser.unregister(objs);
		}while(gotoNext());
		return list;
	}
	
	/**
	 * If "Next" button is available, click it
	 */
	public boolean gotoNext() {
		IHtmlObject[] pageTables = browser.getHtmlObject(".class","Html.TABLE", ".id", "ConfigurableChangeList_LIST");
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Next", pageTables[0]);
		boolean hasNext = false;

		if (objs.length > 0) {
			hasNext = true;
			objs[0].click();
		}
		Browser.unregister(objs);
		Browser.unregister(pageTables);
		ajax.waitLoading();
		this.waitLoading();

		return hasNext;
	}
	

}
