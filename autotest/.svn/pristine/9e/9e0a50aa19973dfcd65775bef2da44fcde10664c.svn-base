package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Hunt License Year list page, Admin(drop down list)-->Lotteries --- > Hunts --click--> huntId --> License Year tab
 * 
 * @author Lesley Wang
 * @Date  Aug 19, 2013
 */
public class LicMgrHuntLicYearListPage extends LicMgrHuntDetailPage {
	private static LicMgrHuntLicYearListPage _instance = null;
	
	protected LicMgrHuntLicYearListPage(){}
	
	public static LicMgrHuntLicYearListPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrHuntLicYearListPage();
		}
		
		return _instance;
	}

	/** Page Object Property Begin */
	protected Property[] huntLYTableProp() {
		return Property.toPropertyArray(".class", "Html.Table", ".id", "productLicenseYearGrid");
	}
	
	protected Property[] addLYBtnProp() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Add Licen(s|c)e Year", false));
	}
	/** Page Object Property END */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(huntLYTableProp());
	}
	
	public void clickAddLicenseYear() {
		browser.clickGuiObject(this.addLYBtnProp()); 
	}
	
	public boolean isAddLicenseYearButtonExist(){
		return browser.checkHtmlObjectExists(this.addLYBtnProp());
	}
	
	public boolean isHuntLicenseYearIdLinkExist(String id){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", id);
	}
	
	private IHtmlObject[] getHuntLicYearTable() {
		IHtmlObject[] objs = browser.getHtmlObject(this.huntLYTableProp());
		if (objs.length < 1) {
			throw new ErrorOnPageException("Can't find hunt license year table");
		}
		return objs;
	}
	
	public String getHuntLicYearID(String prod, String licYear) {
		IHtmlObject[] objs = getHuntLicYearTable();
		IHtmlTable grid = (IHtmlTable) objs[0];
		String id = "";
		prod = prod.replaceAll(" - ", "-");
		for (int i = 0; i < grid.rowCount(); i++) {
			if (grid.getCellValue(i, 3).equalsIgnoreCase(prod) && 
					grid.getCellValue(i, 5).equalsIgnoreCase(licYear)) {
				id = grid.getCellValue(i, 0);
				break;
			}
		}
		Browser.unregister(objs);
		return id;
	}
	
	protected Property[] statusFilterDropDownList() {
		return Property.toPropertyArray(".id", new RegularExpression("LicenseYearFilter-\\d+\\.status",false));
	}
	
	protected Property[] proCatFilterDropDownList() {
		return Property.toPropertyArray(".id", new RegularExpression("LicenseYearFilter-\\d+\\.productCategory",false));
	}
	
	protected Property[] assignedPrdFilterDropDownList() {
		return Property.toPropertyArray(".id", new RegularExpression("LicenseYearFilter-\\d+\\.productID",false));
	}
	
	public Property[] goButton(){
		return Property.addToPropertyArray(this.a(), ".text", "Go");
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(this.statusFilterDropDownList(), status);
	}
	
	public void selectProdCat(String category){
		browser.selectDropdownList(this.proCatFilterDropDownList(), category);
	}
	
	public void selectAssignedPro(String product){
		browser.selectDropdownList(this.assignedPrdFilterDropDownList(), product);
	}
	
	public void clickGo(){
		browser.clickGuiObject(this.goButton());
	}
	
	public void setUpFilter(String status, String proCat, String assPrd){
		if(StringUtil.notEmpty(status)){
			this.selectStatus(status);
		}
		if(StringUtil.notEmpty(proCat)){
			this.selectProdCat(proCat);
		}
		if(StringUtil.notEmpty(assPrd)){
			this.selectAssignedPro(assPrd);
		}
	}
	
	public void searchLicenseYear(String status, String proCat, String assPrd){
		this.setUpFilter(status, proCat, assPrd);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public List<String> getAllLicenseYearIds() {
		IHtmlObject[] objs = getHuntLicYearTable();
		IHtmlTable grid = (IHtmlTable) objs[0];
		List<String> ids = grid.getColumnValues(0);
		ids.remove(0); // remove first row which is title id
		Browser.unregister(objs);
		return ids;
	}
	
	public void clickLicenseYearId(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	public String getLicenseYearId(String status, String prd, String licYear){
		this.searchLicenseYear(status, StringUtil.EMPTY, prd);
		return this.getHuntLicYearID(prd, licYear);
	}
	
	public List<String> getLicenseYearInfoInList(String id){
		IHtmlObject[] objs = getHuntLicYearTable();
		IHtmlTable grid = (IHtmlTable) objs[0];
		int row = grid.findRow(0, id);
		List<String> info = grid.getRowValues(row);
		Browser.unregister(objs);
		return info;
	}
	
	public void verifyLicenseYearInfo(LicenseYear expLy){
		List<String> actInfo = this.getLicenseYearInfoInList(expLy.id);
		boolean passed = true;
		passed &= MiscFunctions.compareResult("hunt license year status:", expLy.status,	actInfo.get(1));
		passed &= MiscFunctions.compareResult("hunt license year Product Category:", expLy.prdCategory,	actInfo.get(2));
		passed &= MiscFunctions.compareResult("hunt license year privilege lottery product:", expLy.assignedProd.replaceAll(" ", ""),	actInfo.get(3).replaceAll(" ", ""));
		passed &= MiscFunctions.compareResult("hunt license year location class:", expLy.locationClass,	actInfo.get(4));
		passed &= MiscFunctions.compareResult("hunt license year license year:", expLy.licYear,	actInfo.get(5));
		passed &= MiscFunctions.compareResult("hunt license year sell from date/time:", expLy.sellFromDate,	actInfo.get(6));
		passed &= MiscFunctions.compareResult("hunt license year sell to date/time:", expLy.sellToDate,	actInfo.get(7));
		if(!passed){
			throw new ErrorOnPageException("Hunt license year information may not correct in list page, please check the log above!");
		}
		logger.info("The information for hunt license year is correct in list page!");
	}
}
