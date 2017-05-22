package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Jul 5, 2012
 */
public class LicMgrVehicleChangeHistoryPage extends LicMgrCommonTopMenuPage {

	private static LicMgrVehicleChangeHistoryPage _instance = null;
	protected LicMgrVehicleChangeHistoryPage(){}

	public static LicMgrVehicleChangeHistoryPage getInstance(){
		if(_instance == null){
			_instance = new LicMgrVehicleChangeHistoryPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id","vehiclehistory");
	}
	
	private IHtmlTable getHistoryTable(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.Table", ".id", "ConfigurableChangeList_LIST");
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find change history table.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
//		Browser.unregister(objs);
		return table;
	}
	
	
	/**
	 * Get all change history.
	 * @return
	 */
	public List<ChangeHistory> getchangeHistory(){
		List<ChangeHistory> historyList = new ArrayList<ChangeHistory>();
		PagingComponent turnPage = new PagingComponent();
		
		this.getHistoryInCurrentPage(historyList);
		while(turnPage.nextExists()){
			turnPage.clickNext();
			ajax.waitLoading();
			this.getHistoryInCurrentPage(historyList);
		}
		return historyList;
	}
	
	private void getHistoryInCurrentPage(List<ChangeHistory> historyList){
		ChangeHistory history;
		IHtmlTable table = this.getHistoryTable();
		for(int i=1; i<table.rowCount(); i++){
			history = new ChangeHistory();
			history.changeDate = table.getCellValue(i, 0);
			history.object = table.getCellValue(i, 1);
			history.action = table.getCellValue(i, 2);
			history.field = table.getCellValue(i, 3);
			history.oldValue = table.getCellValue(i, 4);
			history.newValue = table.getCellValue(i, 5);
			history.user = table.getCellValue(i, 6);
			history.location = table.getCellValue(i, 7);
			historyList.add(history);
		}
	}
	
	public void clickRtnToVehicleDetail(){
		browser.clickGuiObject(".class", "Html.A",".text","Return to Vehicle Details");
	}
}
