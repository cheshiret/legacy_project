package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author phoebe
 * @Date  Feb 11, 2014
 */
public class LicMgrQuotaLicenseYearsListPage extends LicMgrQuotaDetailsPage{
	private static LicMgrQuotaLicenseYearsListPage _instance = null;
	
	private LicMgrQuotaLicenseYearsListPage() {}
	
	public static LicMgrQuotaLicenseYearsListPage getInstance() {
		if(_instance == null) {
			_instance = new LicMgrQuotaLicenseYearsListPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(licenseYearTable());
	}
	
	protected Property[] addLicenseYearQuotaBtn() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Add Licen(s|c)e Year Quota", false));
	}
	
	protected Property[] statusDropdownList() {
		return Property.toPropertyArray(".id", new RegularExpression("LicenseYearFilter-\\d+\\.status", false));
	}
	
	protected Property[] licenseYearDropdownList(){
		return Property.toPropertyArray(".id", new RegularExpression("LicenseYearFilter-\\d+\\.licenseYear", false));
	}
	
	protected Property[] goBtn(){
		return Property.concatPropertyArray(this.a(), ".text", "Go");
	}
	
	protected Property[] licenseYearTable(){
		return Property.concatPropertyArray(this.table(), ".id", "productLicenseYearGrid");
	}
	
	public void clickAddLicenseYearQuota(){
		browser.clickGuiObject(this.addLicenseYearQuotaBtn());
	}
	
	public void selectStatus(String status){
		if(StringUtil.notEmpty(status)){
			browser.selectDropdownList(this.statusDropdownList(), status);
		}else{
			browser.selectDropdownList(this.statusDropdownList(), 0);
		}
	}
	
	public void selectLicenseYear(String year){
		if(StringUtil.notEmpty(year)){
			browser.selectDropdownList(this.licenseYearDropdownList(), year);
		}else{
			browser.selectDropdownList(this.licenseYearDropdownList(), 0);
		}
	}
	
	public void clickGo(){
		browser.clickGuiObject(this.goBtn());
	}

	public void clickIdLink(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	public void setUpSearchCritial(String status, String year){
		this.selectStatus(status);
		this.selectLicenseYear(year);
	}
	
	public void searchByYear(String year){
		this.searchLicenseYear(StringUtil.EMPTY, year);
	}
	
	public void searchByStatus(String status){
		this.searchLicenseYear(status, StringUtil.EMPTY);
	}
	
	public void searchLicenseYear(String status, String year){
		this.setUpSearchCritial(status, year);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public int getLicenseYearRowIndex(String licenseYear) {
		IHtmlObject[] objs = browser.getHtmlObject(licenseYearTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(2, licenseYear);
		Browser.unregister(objs);
		return row;
	}
	
	public List<String> getStatusOfAllLicenseYearInList(){
		return this.getColumnValue(1);
	}
	
	public List<String> getLicenseYearOfAllLicenseYearInList(){
		return this.getColumnValue(2);
	}
	
	public List<String> getColumnValue(int colNum){
		IHtmlObject[] objs = browser.getHtmlObject(licenseYearTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> values = table.getColumnValues(colNum);
		Browser.unregister(objs);
		return values;
	}
	
	public List<List<String>> getResults(){
		IHtmlObject[] objs = browser.getHtmlObject(licenseYearTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		List<List<String>> values = new ArrayList<List<String>>();
		int nums = table.rowCount();
		for(int i=1; i< nums; i++){
			values.add(table.getRowValues(i));
		}
		Browser.unregister(objs);
		return values;
	}
	
	public boolean isLicenseYearExist(String year){
		IHtmlObject[] objs = browser.getHtmlObject(licenseYearTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		boolean found = table.findRows(2, year).length>0;
		Browser.unregister(objs);
		return found;
	}
	
	public void verifyLicenseYearsDisplayOrNot(boolean isfound, String... years){
		IHtmlObject[] objs = browser.getHtmlObject(licenseYearTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		String yearStr = "";
		boolean found = true;
		for(String year:years){
			found &= (table.findRows(2, year).length>0)?(isfound?true:false):(isfound?false:true);
			yearStr += year;
		}
		if(!found){
			throw new ErrorOnPageException("Some years " + (isfound?"not ":"") + "found in page:"+yearStr);
		}
		Browser.unregister(objs);
		logger.info("All license year " + (isfound?" ":"not ") + "found!");
	}
	
	public void verifyLicenseYearFound(String... years){
		this.verifyLicenseYearsDisplayOrNot(true, years);
	}
	
	public void verifyLicenseYearNotFound(String... years){
		this.verifyLicenseYearsDisplayOrNot(false, years);
	}
	
	public String getLicenseYearId(String year){
		IHtmlObject[] objs = browser.getHtmlObject(licenseYearTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRows(2, year)[0];
		String id = table.getCellValue(row, 0);
		Browser.unregister(objs);
		return id;
	}	
}
