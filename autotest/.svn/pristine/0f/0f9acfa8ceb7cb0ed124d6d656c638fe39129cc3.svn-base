package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.testapi.ItemNotFoundException;
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
 * @author qchen
 * @Date  Aug 30, 2013
 */
public class LicMgrHuntQuotaListPage extends LicMgrHuntDetailPage {
	private static LicMgrHuntQuotaListPage _instance = null;
	private LicMgrHuntQuotaListPage() {}
	
	public static LicMgrHuntQuotaListPage getInstance() {
		if(_instance == null) _instance = new LicMgrHuntQuotaListPage();
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(quotaListTable());
	}
	
	private Property[] statusDropdownList() {
		return Property.toPropertyArray(".id", new RegularExpression("LicenseYearFilter-\\d+\\.status", false));
	}
	
	public Property[] licenseYearDropdownList() {
		return Property.toPropertyArray(".id", new RegularExpression("LicenseYearFilter-\\d+\\.licenseYear", false));
	}
	
	private Property[] goButton() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false));
	}
	
	public Property[] quotaListTable() {
		return Property.toPropertyArray(".id", "productLicenseYearGrid");
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(statusDropdownList(), status);
	}
	
	public void selectLicenseYear(String licYear) {
		browser.selectDropdownList(licenseYearDropdownList(), licYear);
	}
	
	public void clickGo() {
		browser.clickGuiObject(goButton());
	}
	
	public void searchQuota(String status, String licYear) {
		if(!StringUtil.isEmpty(status)) this.selectStatus(status);
		if(!StringUtil.isEmpty(licYear)) this.selectLicenseYear(licYear);
		
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private static final String ID_COL = "ID";
	private static final String STATUS_COL = "Status";
	private static final String LICENSE_YEAR_COL = "License Year";
	private static final String QUOTA_DESCRIPTION_COL = "Quota Description";
	private static final String QUOTA_TYPE_AND_QUOTA = "Quota Type & Quota";
	
	private IHtmlObject[] getQuotaListTableObjects() {
		IHtmlObject objs[] = browser.getTableTestObject(quotaListTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Quota list table object.");
		
		return objs;
	}
	
	public String getHuntQuotaID(String quotaDesc) {
		IHtmlObject objs[] = this.getQuotaListTableObjects();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int colIndex = table.findColumn(0, QUOTA_DESCRIPTION_COL);
		int rowIndex = table.findRow(colIndex, quotaDesc);
		int idColIndex = table.findColumn(0, ID_COL);
		String id = table.getCellValue(rowIndex, idColIndex);
		Browser.unregister(objs);
		
		return id;
	}
	
	public String getHuntQuotaID(String status, String licYear, String desc) {
		this.searchQuota(status, licYear);
		return this.getHuntQuotaID(desc);
	}
	
	public String getQuotaStatusFilter(){
		return browser.getDropdownListValue(this.statusDropdownList(), 0);
	}
	
	public String getLicenseYearFilter(){
		return browser.getDropdownListValue(this.licenseYearDropdownList(), 0);
	}
	
	public List<String> getHuntQuotaInfoInList(){
		IHtmlObject objs[] = this.getQuotaListTableObjects();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> record = table.getRowValues(1);
		Browser.unregister(objs);
		return record;
	}
	
	
}
