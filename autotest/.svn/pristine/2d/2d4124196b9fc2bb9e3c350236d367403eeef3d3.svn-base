package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeReportCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Dec 20, 2011
 */
public class LicMgrReportCategoriesConfigurationPage extends LicMgrProductConfigurationPage {
	
	private static LicMgrReportCategoriesConfigurationPage _instance = null;
	
	protected LicMgrReportCategoriesConfigurationPage() {}
	
	public static LicMgrReportCategoriesConfigurationPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrReportCategoriesConfigurationPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".className", new RegularExpression("selected( last)?", false), ".text", "Report Categories");
	}
	
	public boolean checkReportCategoryExists(String identifier) {
		return this.checkIdentifierExists(identifier, "displaycategorylist");
	}
	
	/**
	 * Get Id by description
	 * @param description
	 * @return id
	 */
	public String getReportCategoryIdByDescription(String description){
		String record="";
		IHtmlObject [] objs = browser.getTableTestObject(".id", "displaycategorylist");
		if(objs.length ==0) {
			throw new ObjectNotFoundException("Report Categories table object can't be found.");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		int row=table.findRow(1, description);
		if(row<0){
			logger
			.info("The given description type cannot be found !");
		}else{
			record = table.getCellValue(row, 0);
		}
	
		Browser.unregister(objs);
		return record;
	}
	
	/**
	 * This method used to get report category list information by category id
	 * @param id
	 * @return
	 */
	public List<String> getReportCategoryInfoById(String id){
		List<String> values=null;
		IHtmlObject [] objs = browser.getTableTestObject(".id", "displaycategorylist");
		if(objs.length ==0) {
			throw new ObjectNotFoundException("Report Categories table object can't be found.");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		int row=table.findRow(0, id);
		if(row<0){
			logger
			.info("The given id cannot be found !");
		}else{
			values=table.getRowValues(row);
		}
		
		Browser.unregister(objs);
		return values;
	}
	
	/**
	 * The method used to get report category info from specific row
	 * 
	 * @param identifier
	 *            ---one cell value in your expected parse row.
	 * @param colName
	 *            ---the column name for your given identifier
	 * @return
	 */
	public List<String> getReportCategoryInfo(String identifier, String expectColName) {
		return this.getRowInfo("displaycategorylist", identifier, expectColName);
	}
	
	/**
	 * Verify whether the report category brief information is correct with expected or not
	 * @param expectedDisplayCategory
	 */
	public void verifyReportCategoryInfo(PrivilegeReportCategory expectedDisplayCategory) {
		boolean pass = true;
		if(!this.checkReportCategoryExists(expectedDisplayCategory.displayOrder)) {
			throw new ErrorOnPageException("Add Report Category Failed.");
		}
		List<String> rowInfo = this.getReportCategoryInfo(expectedDisplayCategory.description, "Description");
		if(rowInfo==null||rowInfo.size()<1){
			pass &= false;
			logger.error("Add Report Category Failed.");
		}
		if(!rowInfo.get(0).matches("\\d+")){
			pass &= false;
			logger.error("Report Category ID "+rowInfo.get(0)+" not correct.");
		}
		if(!rowInfo.get(1).equalsIgnoreCase(expectedDisplayCategory.description)){
			pass &= false;
			logger.error("Report Category Description "+rowInfo.get(1)+" not correct.");
		}
		if(!rowInfo.get(2).equalsIgnoreCase(expectedDisplayCategory.displayOrder)){
			pass &= false;
			logger.error("Report Category Display Order "+rowInfo.get(2)+" not correct.");
		}
		if(!rowInfo.get(3).equalsIgnoreCase(expectedDisplayCategory.groupNum)){
			pass &= false;
			logger.error("Report Category Group Number "+rowInfo.get(3)+" not correct.");
		}
		
		if(!pass) {
			throw new ErrorOnPageException("Report Category brief information is incorrect.");
		} else {
			logger.info("Verify Report Category brief informatio correctly.");
		}
	}
}
