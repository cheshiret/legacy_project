package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.EducationDeferralRecords;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName 
 * @Date:Mar 30, 2012
 * @Description:
 * @author Swang
 */
public class LicMgrViewEducationDeferralRecordsPage extends DialogWidget {
	private static LicMgrViewEducationDeferralRecordsPage instance=null;

	private LicMgrViewEducationDeferralRecordsPage(){
		super("View Education Deferral Records");
	}

	public static LicMgrViewEducationDeferralRecordsPage getInstance(){
		if(instance==null){
			instance=new LicMgrViewEducationDeferralRecordsPage();
		}
		return instance;
	}

	public IHtmlObject[] getEduDeferralRecordsTable(){
		return browser.getTableTestObject(Property.toPropertyArray(".id", new RegularExpression("grid_\\d+", false)),this.getWidget()[0]);
	}
    
	/**
	 * Get all education deferral records
	 * @return List<EducationDeferralRecords>
	 */
	public List<EducationDeferralRecords> getEduDeferralRecords(){
		List<EducationDeferralRecords> eduDeferralRecords = new ArrayList<EducationDeferralRecords>();
		IHtmlObject[] objs = this.getEduDeferralRecordsTable();
		if(objs.length<1){
			throw new ObjectNotFoundException("Education Deferral Records Table object can't be found.");
		}

		IHtmlTable table = (IHtmlTable)objs[0];
		for(int i=1; i<table.rowCount(); i++){
			EducationDeferralRecords eduDeferralRecord = new EducationDeferralRecords();
			eduDeferralRecord.eduType = table.getCellValue(i, 0);
			eduDeferralRecord.orderNum = table.getCellValue(i, 1);
			eduDeferralRecord.privilegeNum = table.getCellValue(i, 2);
			eduDeferralRecord.privilegeStatus = table.getCellValue(i, 3);
			eduDeferralRecord.deferralStatus = table.getCellValue(i, 4);
			eduDeferralRecords.add(eduDeferralRecord);
		}
        Browser.unregister(objs);
		return eduDeferralRecords;
	}
	
	/**
	 * Get the education deferral records match gave order number
	 * @return List<EducationDeferralRecords>
	 */
	public List<EducationDeferralRecords> getEduDeferrafsadflRecordsByOrderNum(String orderNum){
		List<EducationDeferralRecords> returnEduDeferralRecords = new ArrayList<EducationDeferralRecords>();
		List<EducationDeferralRecords> eduDeferralRecords = this.getEduDeferralRecords();

		for(int i=0; i<eduDeferralRecords.size(); i++){
			if(eduDeferralRecords.get(i).orderNum.equals(orderNum)){
				returnEduDeferralRecords.add(eduDeferralRecords.get(i));
			}
		}

		return returnEduDeferralRecords;
	}

	public void clickOK(){
		clickButtonByText("OK");
	}
}

