package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo.ReservationHistory;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrApplicationOrderHistoryPage extends LicMgrLotteriesCommonPage{
	private static LicMgrApplicationOrderHistoryPage _instance= null;
	private LicMgrApplicationOrderHistoryPage (){}
	
	public static LicMgrApplicationOrderHistoryPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrApplicationOrderHistoryPage();
		}
		
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","privilegeLotteryOrderHisList");
	}
	
	protected Property[] okBtn() {
		return Property.addToPropertyArray(this.a(), ".text", new RegularExpression("ok",false));
	}
	
	
	/**
	 * Click ok button
	 */
	public void clickOK(){
		browser.clickGuiObject(okBtn());
	}
	
	public ReservationHistory getOrderHistoryByTransaction(String transaction) {
		int toReturn = 0;
		int controller = 0;
		
		IHtmlObject objs[] =browser.getHtmlObject(".class", "Html.TABLE", ".id", "privilegeLotteryOrderHisList");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find reservation history table object.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		for(int row = 0; row < table.rowCount(); row++ ){
			if(table.getCellValue(row, 1).equalsIgnoreCase(transaction)) {
				controller++;
				toReturn = row;
				break;
			}
		}
		if(controller == 0) {
			throw new ItemNotFoundException("The given transaction type can not be found!");
		}
		
		ReservationHistory info = new ReservationHistory();
		info.dateTime = table.getCellValue(toReturn, table.findColumn(0, "Date & Time"));
		info.transType = table.getCellValue(toReturn, table.findColumn(0, "Transaction"));
		info.info = table.getCellValue(toReturn, table.findColumn(0, "Information at Time of Transaction"));
		info.transLocation = table.getCellValue(toReturn, table.findColumn(0, "Transaction Location"));
		info.user = table.getCellValue(toReturn, table.findColumn(0, "User"));	
		
		Browser.unregister(objs);
		return info;
	}
	

}
