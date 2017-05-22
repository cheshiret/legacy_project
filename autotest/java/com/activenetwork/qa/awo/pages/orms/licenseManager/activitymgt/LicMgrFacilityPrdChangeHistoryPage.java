package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrActivityMGTCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;

public class LicMgrFacilityPrdChangeHistoryPage extends LicMgrActivityMGTCommonPage {
	static class SingletonHolder {
		protected static LicMgrFacilityPrdChangeHistoryPage _instance = new LicMgrFacilityPrdChangeHistoryPage();
	}

	protected LicMgrFacilityPrdChangeHistoryPage() {
	}

	public static LicMgrFacilityPrdChangeHistoryPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] facilityPrdChangeHistoryList(){
		return Property.concatPropertyArray(table(), ".id", "facilityProductChangeHistory_LIST");
	}

	protected Property[] facilityPrdChangeHistoryListTR(){
		return Property.concatPropertyArray(tr(), ".className", "oddRow");
	}

	protected Property[] returnToFacilityPrdListButton(){
		return Property.concatPropertyArray(a(), ".text", "Return to Facility Product List");
	}
	/** Page Object Property Definition End */

	public boolean exists() {
		return browser.checkHtmlObjectExists(facilityPrdChangeHistoryList());
	}

	public void clickReturnToFacilityPrdListButton(){
		browser.clickGuiObject(returnToFacilityPrdListButton());
	}

	public List<ChangeHistory> getFacilityPrdChangeHistoryData() {
		List<ChangeHistory> list = new ArrayList<ChangeHistory>();
		IHtmlObject[] objs = browser.getHtmlObject(facilityPrdChangeHistoryList());
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
		}else throw new ObjectNotFoundException("Can't find activity facility prd change history result grid list objects.");
	}

	public void verifyFacilityPrdChangeHistoryPgInfo(List<ChangeHistory> changeHistory) {
		List<ChangeHistory> ch = this.getFacilityPrdChangeHistoryData();
		int chRecordsFound = 0;
		boolean result = MiscFunctions.compareResult("Size of list", ch.size(), changeHistory.size());
		if(!result){
			throw new ErrorOnPageException("The size of list is different.");
		}else{
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
		}
		if(chRecordsFound!=changeHistory.size()){
			throw new ErrorOnPageException("Not all change history records are found.");
		}
		if(!result){
			throw new ErrorOnPageException("Not all change history check points are passed in facility prd change history page. Please check the details from previous logs.");
		}
		logger.info("All change history check points are passed in faclity prd change history page.");

	}
}
