package com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.AuditAttr;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jan 14, 2014
 */
public class LicMgrAuditsPage extends LicMgrPropertyDetailsPage{
	static class SingletonHolder {
		protected static LicMgrAuditsPage _instance = new LicMgrAuditsPage();
	}

	protected LicMgrAuditsPage() {}

	public static LicMgrAuditsPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] auditGridTable(){
		return Property.concatPropertyArray(table(), ".id", "landPropertyAuditGrid");
	}

	protected Property[] addAudit(){
		return Property.concatPropertyArray(a(), ".text", "Add Audit");
	}

	protected Property[] auditID(String auditID){
		return Property.concatPropertyArray(a(), ".text", auditID);
	}
	/** Page Object Property Definition End */


	public boolean exists() {
		return browser.checkHtmlObjectExists(auditGridTable());
	}

	public void clickAddAudit(){
		browser.clickGuiObject(addAudit());
	}

	public void clickAuditID(String auditID){
		browser.clickGuiObject(auditID(auditID));
	}

	public boolean isAuditExisted(Data<AuditAttr> audit){
		List<Data<AuditAttr>> auditList = getAuditData();
		boolean existed=true;
		for(int i=0; i<auditList.size(); i++){
			existed = true;
			existed &= audit.stringValue(AuditAttr.auditID).equals(auditList.get(i).stringValue(AuditAttr.auditID));
			existed &= audit.stringValue(AuditAttr.auditStatus).equals(auditList.get(i).stringValue(AuditAttr.auditStatus));
			existed &= audit.stringValue(AuditAttr.lName).equals(auditList.get(i).stringValue(AuditAttr.lName));
			existed &= audit.stringValue(AuditAttr.fName).equals(auditList.get(i).stringValue(AuditAttr.fName));
			existed &= audit.stringValue(AuditAttr.custID).equals(auditList.get(i).stringValue(AuditAttr.custID));
			existed &= audit.stringValue(AuditAttr.documentType).equals(auditList.get(i).stringValue(AuditAttr.documentType));
			if(StringUtil.notEmpty(audit.stringValue(AuditAttr.documentDate)) && StringUtil.notEmpty(auditList.get(i).stringValue(AuditAttr.documentDate))){
				existed &= DateFunctions.compareDates(audit.stringValue(AuditAttr.documentDate), auditList.get(i).stringValue(AuditAttr.documentDate))==0;
			}else existed &= audit.stringValue(AuditAttr.documentDate).equals(auditList.get(i).stringValue(AuditAttr.documentDate));
			
			if(StringUtil.notEmpty(audit.stringValue(AuditAttr.documentDate)) && StringUtil.notEmpty(auditList.get(i).stringValue(AuditAttr.contactDate))){
				existed &= DateFunctions.compareDates(audit.stringValue(AuditAttr.contactDate), auditList.get(i).stringValue(AuditAttr.contactDate))==0;
			}else existed &= audit.stringValue(AuditAttr.contactDate).equals(auditList.get(i).stringValue(AuditAttr.contactDate));
			existed &= audit.stringValue(AuditAttr.auditYear).equals(auditList.get(i).stringValue(AuditAttr.auditYear));
			if(existed)
				break;
		}

		return existed;
	}

	public void verifyAuditExisted(Data<AuditAttr> audit, boolean existed){
		boolean resultFromUI = isAuditExisted(audit);
		if(resultFromUI!=existed){
			throw new ErrorOnPageException("Audit which id="+audit.stringValue(AuditAttr.auditID)+" should "+(existed?"":"not ")+"exist.");
		}
		logger.info("Successfully verify audit which id="+audit.stringValue(AuditAttr.auditID)+(existed?" exists":" doesn't exist "));
	}

	public List<Data<AuditAttr>> getAuditData() {
		List<Data<AuditAttr>> list = new ArrayList<Data<AuditAttr>>();
		IHtmlObject[] objs = browser.getHtmlObject(auditGridTable());
		if(objs.length>0){
			IHtmlTable table = (IHtmlTable) objs[0];
			for (int i = 1; i < table.rowCount(); i++) {
				Data<AuditAttr> audit = new Data<AuditAttr>();
				audit.put(AuditAttr.auditID, table.getCellValue(i, 0).trim());
				audit.put(AuditAttr.auditStatus, table.getCellValue(i, 1).trim());
				audit.put(AuditAttr.cust, table.getCellValue(i, 2).trim());
				audit.put(AuditAttr.lName, audit.stringValue(AuditAttr.cust).split(",")[0].trim());
				audit.put(AuditAttr.custID, audit.stringValue(AuditAttr.cust).split(" ")[1].trim());
				audit.put(AuditAttr.fName, audit.stringValue(AuditAttr.cust).split(",")[1].split(audit.stringValue(AuditAttr.custID))[0].trim());
				audit.put(AuditAttr.documentType, table.getCellValue(i, 3).trim());
				audit.put(AuditAttr.documentDate, table.getCellValue(i, 4).trim());
				audit.put(AuditAttr.contactDate, table.getCellValue(i, 5).trim());
				audit.put(AuditAttr.auditYear, table.getCellValue(i, 6).trim());
				list.add(audit);
			}
			Browser.unregister(table);
			Browser.unregister(objs);
			return list;
		}else throw new ObjectNotFoundException("Can't find audit grid table.");
	}

	public void verifyAuditInfo(List<Data<AuditAttr>> audit) {
		List<Data<AuditAttr>> auditList = this.getAuditData();
		boolean result = MiscFunctions.compareResult("Size of list", audit.size(), auditList.size());
		if(!result){
			throw new ErrorOnPageException("The size of list is different.");
		}else{
			for(int i=0; i<audit.size(); i++){
				result &= MiscFunctions.compareResult(i+"-Audit ID", audit.get(i).stringValue(AuditAttr.auditID), auditList.get(i).stringValue(AuditAttr.auditID));
				result &= MiscFunctions.compareResult(i+"-Audit Status", audit.get(i).stringValue(AuditAttr.auditStatus), auditList.get(i).stringValue(AuditAttr.auditStatus));
				result &= MiscFunctions.compareResult(i+"-Customer Last Name", audit.get(i).stringValue(AuditAttr.lName), auditList.get(i).stringValue(AuditAttr.lName));
				result &= MiscFunctions.compareResult(i+"-Customer First Name", audit.get(i).stringValue(AuditAttr.fName), auditList.get(i).stringValue(AuditAttr.fName));
				result &= MiscFunctions.compareResult(i+"-Customer ID", audit.get(i).stringValue(AuditAttr.custID), auditList.get(i).stringValue(AuditAttr.custID));
				result &= MiscFunctions.compareResult(i+"-Document Type", audit.get(i).stringValue(AuditAttr.documentType), auditList.get(i).stringValue(AuditAttr.documentType));
				result &= MiscFunctions.compareResult(i+"-Contact Date", audit.get(i).stringValue(AuditAttr.contactDate), auditList.get(i).stringValue(AuditAttr.contactDate));
				result &= MiscFunctions.compareResult(i+"-Audit Year", audit.get(i).stringValue(AuditAttr.auditYear), auditList.get(i).stringValue(AuditAttr.auditYear));
			}
			if(!result){
				throw new ErrorOnPageException("Not all check points are passed in Audit list page. Please check the details from previous logs.");
			}
			logger.info("All check points are passed in Audit list page.");
		}
	}
}
