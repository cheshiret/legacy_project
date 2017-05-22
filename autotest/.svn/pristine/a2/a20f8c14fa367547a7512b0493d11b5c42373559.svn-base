/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrPrivilegesPage.java
 * @Date:Mar 4, 2011
 * @Description:this page is used for search privileges
 * @author asun
 */
public class LicMgrPrivilegesListPage extends LicMgrProductPage {

	private static LicMgrPrivilegesListPage instance = null;

	private LicMgrPrivilegesListPage() {}

	public static LicMgrPrivilegesListPage getInstance() {
		if (instance == null) {
			instance=new LicMgrPrivilegesListPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".id", "privilegeProductGrid");
	}
	
	public void clickAddPrivilegeButton(){
		browser.clickGuiObject(".class", "Html.A",".text", new RegularExpression("Add (Privilege|Licence) Product", false));
		ajax.waitLoading();
	}
	
	public boolean checkBatchAddLicenseYeaButtonAble(){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text","Batch Add License Year");
	}
	
	public void clickBatchAddLicYearButton(){
		browser.clickGuiObject(".class", "Html.A",".text","Batch Add License Year");
		ajax.waitLoading();
	}
	
	public boolean checkBatchEditLicenseYeaButtonAble(){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text","Batch Edit License Year");
	}
	
	public void clickBatchEditLicenseYearButton(){
		browser.clickGuiObject(".class", "Html.A",".text","Batch Edit License Year");
		ajax.waitLoading();
	}
	
	public boolean isThisPrivilegeExist(String code){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text",code);		
	}
	
	public void clickPrivilegeCode(String code){
		browser.clickGuiObject(".class", "Html.A", ".text", code, true);
	}
	
	public void selectAllStatus(){
		RegularExpression regx=new RegularExpression("PrivilegeProductFilter-\\d+\\.statuses_\\d+",false);
        IHtmlObject[] objs=browser.getHtmlObject(".id", regx);
        for(int i=0;i<objs.length;i++){
        	browser.selectCheckBox(".id", regx,i);
        }
        Browser.unregister(objs);
	}
	
	public void unSelectAllStatuses() {
		RegularExpression regx=new RegularExpression("PrivilegeProductFilter-\\d+\\.statuses_\\d+",false);
        IHtmlObject[] objs=browser.getHtmlObject(".class","Html.INPUT.checkbox",".id", regx);
        for(int i=0;i<objs.length;i++){
        	browser.unSelectCheckBox(".id", regx,i);
        }
        Browser.unregister(objs);
	}
	
	public void selectStatus(String status) {
		int index = 0;
		if(status.equalsIgnoreCase("Active")) {
			index = 0;
		} else {
			index = 1;
		}
		browser.selectCheckBox(".class", "Html.INPUT.checkbox", ".id", new RegularExpression("PrivilegeProductFilter-\\d+\\.statuses_\\d+",false), index);
	}
	
	public void selectAllDisplayCategories() {
		RegularExpression regx=new RegularExpression("PrivilegeProductFilter-\\d+\\.displayCategories_\\d+",false);
        IHtmlObject[] objs=browser.getHtmlObject(".id", regx);
        for(int i=0;i<objs.length;i++){
        	browser.selectCheckBox(".id", regx,i);
        }
        Browser.unregister(objs);
	}
	
	public void unSelectAllDisplayCategories() {
		RegularExpression regx=new RegularExpression("PrivilegeProductFilter-\\d+\\.displayCategories_\\d+",false);
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.INPUT.checkbox", ".id", regx);
        for(int i=0;i<objs.length;i++){
        	browser.unSelectCheckBox(".id", regx, i);
        }
        Browser.unregister(objs);
	}
	
	/**
	 * Select a specific display category
	 * @param displayCategory
	 */
	public void selectDisplayCategory(String displayCategory) {
//		Property property[] = new Property[3];
//		property[0] = new Property(".class", "Html.LABEL");
//		property[1] = new Property(".className", "trailing");
//		property[2] = new Property(".text", displayCategory);
//		HtmlObject labelObjs[] = browser.getHtmlObject(property);
//		if(labelObjs.length < 1) {
//			throw new ObjectNotFoundException("Can't find Display Category - " + displayCategory);
//		}
//		String idValue = labelObjs[0].getProperty("htmlFor");
//		browser.selectCheckBox(".id", idValue);
//		Browser.unregister(labelObjs);
		IHtmlObject divObjs[] = browser.getHtmlObject(".class", "Html.SPAN", ".text", displayCategory);
		if(divObjs.length < 1) {
			throw new ObjectNotFoundException("Can't find Display Category - " + displayCategory);
		}
		browser.selectCheckBox(".id", new RegularExpression("PrivilegeProductFilter-\\d+\\.displayCategories_\\d+", false), 0, divObjs[0]);
		Browser.unregister(divObjs);
		
	}
	
	public void selectDisplayCategories(String... displayCategories) {
		for(String displayCategory:displayCategories) {
			this.selectDisplayCategory(displayCategory);
		}
	}
	
	public void selectAllDisplaySubCategories() {
		RegularExpression regx=new RegularExpression("PrivilegeProductFilter-\\d+\\.displaySubCategories_\\d+",false);
        IHtmlObject[] objs=browser.getHtmlObject(".id", regx);
        for(int i=0;i<objs.length;i++) {
        	browser.selectCheckBox(".id", regx,i);
        }
        Browser.unregister(objs);
	}
	
	public void unSelectAllDisplaySubCategories() {
		RegularExpression regx=new RegularExpression("PrivilegeProductFilter-\\d+\\.displaySubCategories_\\d+",false);
        IHtmlObject[] objs=browser.getHtmlObject(".id", regx);
        for(int i=0;i<objs.length;i++){
        	browser.unSelectCheckBox(".id", regx,i);
        }
        Browser.unregister(objs);
	}
	
	/**
	 * Select a specific display sub category
	 * @param displaySubCategory
	 */
	public void selectDisplaySubCategory(String displaySubCategory) {
		Property property[] = new Property[3];
		property[0] = new Property(".class", "Html.LABEL");
		property[1] = new Property(".className", "trailing");
		property[2] = new Property(".text", displaySubCategory);
		IHtmlObject labelObjs[] = browser.getHtmlObject(property);
		if(labelObjs.length < 1) {
			throw new ObjectNotFoundException("Can't find Display Sub Category - " + displaySubCategory);
		}
		String idValue = labelObjs[0].getProperty("htmlFor");
		browser.selectCheckBox(".id", idValue);
		Browser.unregister(labelObjs);
	}
	
	public void selectDisplaySubCategories(String... displaySubCategories) {
		for(String displaySubCategory:displaySubCategories) {
			this.selectDisplaySubCategory(displaySubCategory);
		}
	}
	
	public void clickSearchCriteria(String criteria){
		IHtmlObject[] topObjs = browser.getHtmlObject(".class", "Html.SPAN", ".text", criteria);
		browser.selectCheckBox(".class","Html.INPUT.checkbox",0, topObjs[0]);
		ajax.waitLoading();
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class","Html.A",".text","Go");
		ajax.waitLoading();
	}
	
	public void clickEditDisplayOrder(){
		browser.clickGuiObject(".class","Html.A",".text","Edit Display Order");
		ajax.waitLoading();
	}
	
	public String getPrivilegeListInfoByColumnName(String privilegeCode, String colName){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "privilegeProductGrid");		
		IHtmlTable privilegeListTable = (IHtmlTable) objs[0];	
		int row = privilegeListTable.findRow(0, privilegeCode);
		int col = privilegeListTable.findColumn(0, colName);
		String value= privilegeListTable.getCellValue(row, col);
		
		Browser.unregister(objs);
		return value;
	}
	
	/**
	 * Get the all display categories and their corresponding display orders displayed in the privileges list page by default order
	 * @return
	 */
	public List<String[]> getDisplayCategoriesAndOrders() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TR", ".className", new RegularExpression("^(|oddrow )label_title labelgroup   initialized parent", false));
		if(objs.length < 1) {
			throw new ObjectNotFoundException("There is not any display category found in privilege list page.");
		}
		
		List<String[]> list = new ArrayList<String[]>();
		int childrenLength = objs[0].getChildren().length;
		for(int i = 0; i < objs.length; i++) {
			String[] displayCategoriesOrders = new String[2];
			displayCategoriesOrders[0] = objs[i].getChildren()[childrenLength - 4].getProperty(".text").trim();
			displayCategoriesOrders[1] = objs[i].getChildren()[childrenLength - 1].getProperty(".text").trim();
			list.add(displayCategoriesOrders);
		}
		
		Browser.unregister(objs);
		return list;
	}
	
	/**
	 * Get all display sub categories which are of a display category
	 * @param displayCategory
	 * @return
	 */
	public List<String[]> getDisplaySubCategoriesOrders(String displayCategory) {
		Property property[] = new Property[3];
		property[0] = new Property(".class", "Html.TR");
		property[1] = new Property(".className", new RegularExpression("^(|oddrow )label_title labelgroup   initialized parent", false));
		property[2] = new Property(".text", new RegularExpression("^" + displayCategory, false));
		IHtmlObject parentDisplayCategoryObjs[] = browser.getHtmlObject(property);
		if(parentDisplayCategoryObjs.length < 1) {
			throw new ObjectNotFoundException("Can't find parent Display Category object.");
		}
		
		String parentID = parentDisplayCategoryObjs[0].getProperty(".id").trim();
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TR", ".className", new RegularExpression("^(|oddrow )label_title labelgroup child-of-" + parentID + " child initialized parent", false));
		List<String[]> list = new ArrayList<String[]>();
		int childrenLength = objs[0].getChildren().length;
		for(int i = 0; i < objs.length; i ++) {
			String displaySubCategoriesOrders[] = new String[2];
			displaySubCategoriesOrders[0] = objs[i].getChildren()[childrenLength - 4].getProperty(".text").trim();
			displaySubCategoriesOrders[1] = objs[i].getChildren()[childrenLength - 1].getProperty(".text").trim();
			list.add(displaySubCategoriesOrders);
		}
		
		Browser.unregister(parentDisplayCategoryObjs);
		Browser.unregister(objs);
		return list;
	}
	
	/**
	 * Get privilege product's display order
	 * @param code
	 * @return
	 */
	public int getPrivilegeRowNum(String code) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "privilegeProductGrid");
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int row = table.findRow(0, code);
		
		Browser.unregister(objs);
		return row;
	}
	
	public boolean checkProductRecordExist(String prodCode) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "privilegeProductGrid");
		IHtmlTable table = (IHtmlTable)objs[0];
		int codeColNum = table.findColumn(0, "Privilege Product Code");
		int privilegeRowNum = table.findRow(codeColNum, prodCode);
		
		Browser.unregister(objs);
		
		return privilegeRowNum > 0;
	}
	
	
	/**
	 * Get privilege row values
	 * @param privilegeCode
	 * @return
	 */
	public String[] getPrivilegeRowValues(String privilegeCode) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "privilegeProductGrid");
		IHtmlTable table = (IHtmlTable)objs[0];

		int codeColNum = table.findColumn(0, "Privilege Product Code");
		if(codeColNum ==-1){
			codeColNum = table.findColumn(0, "Licence Product Code");
		}
		int nameColNum = table.findColumn(0, "Privilege Product Name");
		if(nameColNum ==-1){
			nameColNum = table.findColumn(0, "Licence Product Name");
		}
		int statusColNum = table.findColumn(0, "Privilege Product Status");
		if(statusColNum==-1){
			statusColNum = table.findColumn(0, "Licence Product Status");
		}
		int displayOrderColNum = table.findColumn(0, "Display Order");
		int privilegeRowNum = table.findRow(codeColNum, privilegeCode);
		String rowValues[] = new String[table.columnCount()];
		rowValues[codeColNum] = table.getCellValue(privilegeRowNum, codeColNum);
		rowValues[nameColNum] = table.getCellValue(privilegeRowNum, nameColNum);
		rowValues[statusColNum] = table.getCellValue(privilegeRowNum, statusColNum);
		rowValues[displayOrderColNum] = table.getCellValue(privilegeRowNum, displayOrderColNum);

		Browser.unregister(objs);
		return rowValues;
	}
	
	/**
	 * verify whether the given privilege product exist in the privilege list page or not based on the given privilege info.
	 * @param privilege
	 * @return
	 */
	public boolean verifyPrivilegeProductExist(PrivilegeInfo privilege){
		IHtmlObject objs[] = browser.getTableTestObject(".id", "privilegeProductGrid");
		IHtmlTable table = (IHtmlTable)objs[0];
		boolean flag = false;

		int codeColNum = table.findColumn(0, "Privilege Product Code");
		int nameColNum = table.findColumn(0, "Privilege Product Name");
//		int statusColNum = table.findColumn(0, "Privilege Product Status");
//		int displayOrder= table.findColumn(0, "Display Order");
		
		logger.info("start checking whether the given privilege exist in the privilege list page or not with privilege code="
				+privilege.code+" privilege name="+privilege.name);
		for (int i = 0; i < table.rowCount(); i ++){
			if (table.getCellValue(i, codeColNum).equalsIgnoreCase(privilege.code) &&
				table.getCellValue(i, nameColNum).equalsIgnoreCase(privilege.name) 
//				&& table.getCellValue(i, statusColNum).equals(privilege.status) 
//				&& table.getCellValue(i, displayOrder).equals(privilege.displayOrder)
				){
				flag = true;
				break;
			}
		}
		
		Browser.unregister(objs);
		
		return flag;
	}
	
	/**
	 * Get privilege specific column values by column name
	 * @param columnName - Privilege Product Code/Privilege Product Name/Privilege Product Status/Display Order
	 * @return
	 */
	public List<String> getPrivilegeColumnValues(String columnName) {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TR", ".className", new RegularExpression("^(|oddrow )child-of-privilegeProductGrid_row_\\d+ initialized", false));
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find any privilege records.");
		}
		
		List<String> columnValues = new ArrayList<String>();
		int childrenLength = objs[0].getChildren().length;
		int index = 0;
		if(columnName.equalsIgnoreCase("Privilege Product Code")) {
			index = childrenLength - 4;
		} else if(columnName.equalsIgnoreCase("Privilege Product Name")) {
			index = childrenLength - 3;
		} else if(columnName.equalsIgnoreCase("Privilege Product Status")) {
			index = childrenLength - 2;
		} else if(columnName.equalsIgnoreCase("Display Order")) {
			index = childrenLength - 1;
		} else {
			throw new ObjectNotFoundException("Unknown column name. Please check it.");
		}
		
		for(IHtmlObject o : objs) {
			columnValues.add(o.getChildren()[index].getProperty(".text").trim());
		}
		
		Browser.unregister(objs);
		return columnValues;
	}
	
	/**
	 * Compare expected privilege info with actual
	 * @param expectedPrivilege
	 * @return
	 */
	public boolean comparePrivilegeInfo(PrivilegeInfo expectedPrivilege) {
		String rowValues[] = this.getPrivilegeRowValues(expectedPrivilege.code);
		boolean result = true;
		
		if(!rowValues[0].equals(expectedPrivilege.code)) {
			logger.error("The expected Privilege Product Code is " + expectedPrivilege.code + ", but the actual value is " + rowValues[0]);
			result &= false;
		}
		if(!rowValues[1].equals(expectedPrivilege.name)) {
			logger.error("The expected Privilege Product Name is " + expectedPrivilege.name + ", but the actual value is " + rowValues[1]);
			result &= false;
		}
		if(!rowValues[2].equals(expectedPrivilege.status)) {
			logger.error("The expected Privilege Product Status is " + expectedPrivilege.status + ", but the actual value is " + rowValues[2]);
			result &= false;
		}
		if(rowValues[3].length() > 0) {
			if(Integer.parseInt(rowValues[3].trim()) != Integer.parseInt(expectedPrivilege.displayOrder.trim())) {
				logger.error("The expected Privilege Display Order is " + expectedPrivilege.displayOrder + ", but the actual value is " + rowValues[3]);
				result &= false;
			}
		}
		
		return result;
	}

	/**
	 * 
	 * Get privilege  product code by product name
	 * 
	 * @param name
	 * @return
	 */
	public String getPrivilegeCdByName(String name){
		IHtmlObject[] objs=browser.getTableTestObject(".id", "privilegeProductGrid");
		String id = "";
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find privilege list table with id=privilegeProductGrid");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		int row=table.findRow(1, name);
		id=table.getCellValue(row, 0);
		Browser.unregister(objs);
		return id;
	}
	
	public void searchPrivilegeByAllStatus() {
		this.selectAllStatus();
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
}
