/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author ssong
 * @Date  Aug 5, 2014
 */
public class LicMgrQuotaTransferListPage extends LicMgrQuotaCommonPage{

	private static LicMgrQuotaTransferListPage _instance = null;
	
	private LicMgrQuotaTransferListPage() {}
	
	public static LicMgrQuotaTransferListPage getInstance() {
		if(_instance == null) {
			_instance = new LicMgrQuotaTransferListPage();
		}
		
		return _instance;
	}
	
	protected Property[] addQuotaTransfer(){
		return Property.addToPropertyArray(a(), ".text", "Add Quota Transfer");
	}
	
	protected Property[] statusDropDown(){
		return Property.addToPropertyArray(select(), ".id", new RegularExpression("HuntQuotaTransferFilter-\\d+\\.status", false));
	}
	
	protected Property[] goBtn(){
		return Property.addToPropertyArray(a(), ".text", "Go");
	}
	
	protected Property[] quotaTransferTable(){
		return Property.concatPropertyArray(this.table(), ".id", "huntQuotaTransferGrid");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(addQuotaTransfer());
	}
	
	public void clickAddQuotaTransfer(){
		browser.clickGuiObject(addQuotaTransfer());
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(statusDropDown(), status);
	}
	
	public void clickGo(){
		browser.clickGuiObject(goBtn());
	}
	
	public void filterByStatus(String status){
		selectStatus(status);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}
	
	public void clickIdLink(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	public List<String> getAllQuotaTransferIds(){
		IHtmlObject[] objs = browser.getTableTestObject(quotaTransferTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> ids = table.getColumnValues(0);
		
		Browser.unregister(objs);
		return ids;
	}
	
	public String getQuotaTransferID(String description) {
		IHtmlObject[] objs = browser.getHtmlObject(quotaTransferTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(2, description);
		String id = table.getCellValue(row, 0);
		Browser.unregister(objs);
		return id;
	}
	
}
