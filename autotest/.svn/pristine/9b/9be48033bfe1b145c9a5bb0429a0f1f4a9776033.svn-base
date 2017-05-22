/**
 *
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @ScriptName LicMgrPrivilegeBusinessRulePage.java
 * @Date:Mar 7, 2011
 * @Description:
 * @author asun
 */
public class LicMgrPrivilegeBusinessRulePage extends
		LicMgrPrivilegeProductDetailsPage {

	private String[] colName = {"ID", "Status", "Effective Date"};

	private static LicMgrPrivilegeBusinessRulePage instance = null;

	private LicMgrPrivilegeBusinessRulePage() {
	}

	public static LicMgrPrivilegeBusinessRulePage getInstance() {
		if (instance == null) {
			instance = new LicMgrPrivilegeBusinessRulePage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(Property.concatPropertyArray(table(),".id", "PrivilegeProductListBar"));
	}

	public boolean isRuleExisting(String rule) {
		boolean flag = true;
		RegularExpression regx = new RegularExpression(
				"privilegeProductRuleGrid_row_\\d+", false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", regx, ".text", rule);
		if (objs.length < 1) {
			flag = false;
		}
		Browser.unregister(objs);
		return flag;
	}

	public void clickAddBusinessRule() {
		browser
				.clickGuiObject(".class", "Html.A", ".text",
						"Add Business Rule");
	}

	public void selectActiveCheckBox() {
		RegularExpression regx = new RegularExpression(
				"PrivilegeProductRuleFilter-\\d+\\.statuses_\\d+", false);
		browser.selectCheckBox(".id", regx, 0);
	}

	public void unSelectActiveCheckBox() {
		RegularExpression regx = new RegularExpression(
				"PrivilegeProductRuleFilter-\\d+\\.statuses_\\d+", false);
		browser.unSelectCheckBox(".id", regx, 0);
	}

	public void selectInactiveCheckBox() {
		RegularExpression regx = new RegularExpression(
				"PrivilegeProductRuleFilter-\\d+\\.statuses_\\d+", false);
		browser.selectCheckBox(".id", regx, 1);
	}

	public void unSelectInactiveCheckBox() {
		RegularExpression regx = new RegularExpression(
				"PrivilegeProductRuleFilter-\\d+\\.statuses_\\d+", false);
		browser.unSelectCheckBox(".id", regx, 1);
	}

	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}

	public void clickRuleId(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
	}

	private IHtmlObject[] getPrivRuleTables() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"privilegeProductRuleGrid");
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the business rule table");
		}
		return objs;
	}
		
	public List<String> getRuleIdsByStatus(boolean isActive) {
		List<String> list = new ArrayList<String>();
		String bRulestatus = isActive ? "Active" : "Inactive";
		IHtmlObject[] objs = this.getPrivRuleTables();
		
		IHtmlTable table = (IHtmlTable) objs[0];
		int rowCount = table.rowCount();
		for (int i = 1; i < rowCount; i++) {
			String value = table.getCellValue(i, 0);
			if (value.trim().matches("^[0-9]+$")) {
				String status = table.getCellValue(i, 1);

				if (!status.equalsIgnoreCase(bRulestatus)) {
					continue;
				}
				String id = table.getCellValue(i, 0);
				list.add(id);
			}
		}
		Browser.unregister(objs);
		return list;
	}

	public String getRuleIdByEffectiveDate(String date) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"privilegeProductRuleGrid");
		IHtmlTable grid = (IHtmlTable) objs[0];
		int row = grid.findRow(2, date + " 00:00:00");
		String id = grid.getCellValue(row, 0);
		Browser.unregister(objs);
		return id;
	}

	public void showAllBusinessRule() {
		this.selectActiveCheckBox();
		this.selectInactiveCheckBox();
		clickGoAndRefreshPg();
	}

	public void showActiveBusinessRule() {
		selectActiveCheckBox();
		unSelectInactiveCheckBox();
		clickGoAndRefreshPg();
	}

	public void showInactiveBusinessRule() {
		unSelectActiveCheckBox();
		selectInactiveCheckBox();
		clickGoAndRefreshPg();
	}

	public void clickGoAndRefreshPg() {
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}

	public PrivilegeBusinessRule getRuleInfoFromBusinessRulePg(
			PrivilegeBusinessRule info, int index) {
		IHtmlObject[] objs = this.getPrivRuleTables();

		IHtmlTable table = (IHtmlTable) objs[0];
		int rulerow = table.findRow(0, info.name);
		if (rulerow < 0) {
			throw new ItemNotFoundException("No Business Rule :" + info.name + " is found");
		}

		int rowCount = table.rowCount();
		
		if (info.parameters[index].purchaseType.matches("Duplicate|Replacement")) {
			info.parameters[index].purchaseType = DataBaseFunctions.getTranslatableLabelValue("translatable.replacement", this.getContract());
		}
		
		
		for (int i = rulerow+1; i < rowCount; i++) {
			String status = table.getCellValue(i, 1);
			String effectivedate = table.getCellValue(i, 2).split(" ")[0];
			String para = table.getCellValue(i, 3);
			String locationClass = table.getCellValue(i, 5);
			
			//Vivian [20140630] due to PCR 4143
			if(StringUtil.isEmpty(info.locationClass)){
				info.locationClass = "All";
			}
			
			if(info.isTheOnlyOneRule)  {
			// this branch is for multi rule. In this condition, we can only get text like "Privilege Products:	Multi	, Match Privilege License Year: No" in column "Parameters and Values".
			// so, we just assume only one avilable rule in list, and only get the 1st record.
				if (status.equals(info.status)
						&& DateFunctions.compareDates(effectivedate,
								info.parameters[index].effectiveDate) == 0
						&& locationClass.equalsIgnoreCase(info.locationClass)) {

					PrivilegeBusinessRule rule = new PrivilegeBusinessRule();
					rule.ruleCategory = info.ruleCategory;// added by Jane
					rule.name = info.name;
					rule.id = table.getCellValue(i, 0);
					rule.status = table.getCellValue(i, 1);
					rule.effectiveDate = table.getCellValue(i, 2).split(" ")[0];
					rule.paramValue = table.getCellValue(i, 3);
					rule.workflowAction = table.getCellValue(i, 4);
					rule.locationClass = table.getCellValue(i, 5);

					return rule;
				}
				
			}else{
				if (status.equals(info.status)
						&& info.matchParamAndValues(index, para)
						&& DateFunctions.compareDates(effectivedate,
								info.parameters[index].effectiveDate) == 0
						&& locationClass.equalsIgnoreCase(info.locationClass)) {

					PrivilegeBusinessRule rule = new PrivilegeBusinessRule();
					rule.ruleCategory = info.ruleCategory;// added by Jane
					rule.name = info.name;
					rule.id = table.getCellValue(i, 0);
					rule.status = table.getCellValue(i, 1);
					rule.effectiveDate = table.getCellValue(i, 2).split(" ")[0];
					rule.paramValue = table.getCellValue(i, 3);
					rule.workflowAction = table.getCellValue(i, 4);
					rule.locationClass = table.getCellValue(i, 5);

					return rule;
				}
			}
		}
		return null;
	}

	/**
	 * This method is used to verify business rule records in a table display
	 * sorted order
	 */
	public boolean verifyBusinessRuleRecordsDisplaySort(List<String> values) {
		return this.verifyTableRecordsDisplaySort(".id",
				"PrivilegeProductListBar", 0, values, true);
	}

	public List<String> getAllRecordsByColName(int index){
		List<String> list = new ArrayList<String>();
		IHtmlObject[] objs = this.getPrivRuleTables();

		IHtmlTable table = (IHtmlTable) objs[0];

		// get col num by column name
		int col = table.findColumn(0, colName[index]);
		if(col < 0){
			throw new ErrorOnPageException("Could not get column num by column name "+colName+".");
		}

		int rowCount = table.rowCount();

		for(int i=1;i<rowCount;){
			if(table.getCellValue(i, 0).startsWith("Privilege|Customer|Suspension|Education") || table.getCellValue(i, index).length()<1){
				i = i + 2;
			}else{
				list.add(table.getCellValue(i, index));
				i++;
			}
		}

		return list;
	}
}
