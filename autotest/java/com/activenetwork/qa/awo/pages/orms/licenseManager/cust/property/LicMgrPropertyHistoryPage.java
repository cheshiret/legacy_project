package com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jan 20, 2014
 */
public class LicMgrPropertyHistoryPage extends LicMgrCommonTopMenuPage{
	static class SingletonHolder {
		protected static LicMgrPropertyHistoryPage _instance = new LicMgrPropertyHistoryPage();
	}

	protected LicMgrPropertyHistoryPage() {}

	public static LicMgrPropertyHistoryPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] propertyListTable(){
		return Property.concatPropertyArray(table(), ".id", "auditlog_LIST");
	}

	protected Property[] facilityPrdChangeHistoryListTR(){
		return Property.concatPropertyArray(tr(), ".className", "oddRow");
	}

	protected Property[] returnToPropertyDetailsButton(){
		return Property.concatPropertyArray(a(), ".text", "Return to Property Details");
	}
	/** Page Object Property Definition End */

	public boolean exists() {
		return browser.checkHtmlObjectExists(propertyListTable());
	}

	public void clickReturnToPropertyDetailsButton(){
		browser.clickGuiObject(returnToPropertyDetailsButton());
	}

	public List<ChangeHistory> getPropertyHistoryData() {
		List<ChangeHistory> list = new ArrayList<ChangeHistory>();
		IHtmlObject[] objs = browser.getHtmlObject(propertyListTable());
		if(objs.length>0){
			IHtmlTable table = (IHtmlTable) objs[0];
			for (int i = 1; i < table.rowCount(); i++) {
				ChangeHistory changeHistory = new ChangeHistory();
				changeHistory.changeDate = table.getCellValue(i, 0).trim();
				changeHistory.object = table.getCellValue(i, 1).trim();
				changeHistory.action = table.getCellValue(i, 2).trim();
				changeHistory.field = table.getCellValue(i, 3).trim();
				changeHistory.oldValue = table.getCellValue(i, 4).trim();
				changeHistory.newValue = table.getCellValue(i, 5).trim();
				changeHistory.user = table.getCellValue(i, 6).replaceAll("\\s+", "").trim();
				changeHistory.location = table.getCellValue(i, 7).trim();
				list.add(changeHistory);
			}
			Browser.unregister(table);
			Browser.unregister(objs);
			return list;
		}else throw new ObjectNotFoundException("Can't find property history list objects.");
	}

	public void verifyPropertyHistoryInfo(List<ChangeHistory> changeHistory) {
		List<ChangeHistory> ch = this.getPropertyHistoryData();
		boolean result = true;
		int chRecordsFound = 0;
		for(int i=0; i<changeHistory.size(); i++){
			for(int j=0; j<ch.size(); j++){
				if(changeHistory.get(i).field.equals(ch.get(j).field)){
					logger.info("Verify change history from Field:"+changeHistory.get(i).field);
					chRecordsFound++;
					result &= MiscFunctions.startWithString(i+"-Date", ch.get(j).changeDate, DateFunctions.formatDate(changeHistory.get(i).changeDate, "E MMM d yyyy"));
					result &= MiscFunctions.compareResult(i+"-Object", changeHistory.get(i).object, ch.get(j).object);
					result &= MiscFunctions.compareResult(i+"-Action", changeHistory.get(i).action, ch.get(j).action);
					result &= MiscFunctions.compareResult(i+"-Field", changeHistory.get(i).field, ch.get(j).field);
					result &= MiscFunctions.compareResult(i+"-Old Value", changeHistory.get(i).oldValue, ch.get(j).oldValue);
					result &= MiscFunctions.compareResult(i+"-New Value", changeHistory.get(i).newValue, ch.get(j).newValue);
					result &= MiscFunctions.compareResult(i+"-User", changeHistory.get(i).user, ch.get(j).user);
					result &= MiscFunctions.compareResult(i+"-Location", changeHistory.get(i).location, ch.get(j).location);
					break;
				}
			}
		}
		if(chRecordsFound!=changeHistory.size()){
			throw new ErrorOnPageException("Not all change history records are found.");
		}
		if(!result){
			throw new ErrorOnPageException("Not all change history check points are passed in property history page. Please check the details from previous logs.");
		}
		logger.info("All change history check points are passed in property history page.");

	}
}
