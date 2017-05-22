package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @Description: Privilege Select Hunt page, displayed when purchase a leftover privilege. 
 * @author Jane Wang
 * @Date  Apr 22, 2014
 */
public class LicMgrPrivilegeSelectHuntPage extends LicMgrTopMenuPage {
	
	private static LicMgrPrivilegeSelectHuntPage _instance = null;
	
	private LicMgrPrivilegeSelectHuntPage() {}
	
	public static LicMgrPrivilegeSelectHuntPage getInstance() {
		if(_instance == null) {
			_instance = new LicMgrPrivilegeSelectHuntPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(huntFilterTable());
	}
	
	public static String TABLE_COLNAME_HUNTCODEDES = "Hunt Code/Description";
	public static String TABLE_COLNAME_SPECIES = "Species";
	public static String TABLE_COLNAME_WEAPON = "Weapon";
	public static String TABLE_COLNAME_DATEPERIOD = "Date Period";
	public static String TABLE_COLNAME_HUNTLOCATION = "Hunt Location";
	public static String TABLE_COLNAME_QUOTA = "Quota";
	
	protected Property[] huntFilterTable(){
		return Property.concatPropertyArray(table(), ".text", new RegularExpression("^Hunt Filters.*", false));
	}
	
	protected Property[] huntCode(){                           
		return Property.toPropertyArray(".id", new RegularExpression("HuntSearchCriteria-\\d+\\.code", false));
	}
	
	protected Property[] huntDescription(){                           
		return Property.toPropertyArray(".id", new RegularExpression("HuntSearchCriteria-\\d+\\.description", false));
	}
	
	protected Property[] huntDate(){                           
		return Property.toPropertyArray(".id", new RegularExpression("HuntSearchCriteria-\\d+\\.huntDate_ForDisplay", false));
	}
	
	protected Property[] showAvailableOnly(){                           
		return Property.toPropertyArray(select(), new RegularExpression("HuntSearchCriteria-\\d+\\.showAvailable", false));
	}
	
	protected Property[] errMsg() {
		return Property.toPropertyArray(".id", "NOTSET");
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void clickReset() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Reset");
	}
	
	private IHtmlObject[] getAvailableHuntListTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "availableHuntList");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find Available Hunt list table object.");
		}
		
		return objs;
	}
	
	/**
	 * Privilege Requiring Hunt Available for Sale as Lefover/OTC Limited or Unlimited Sale 
	 * User should select one hunt 
	 * @param code
	 */
	public void selectHunt(String code) {
		IHtmlObject tableObjs[] = this.getAvailableHuntListTableObject();
		IHtmlTable table = (IHtmlTable)tableObjs[0];
		int colIndex = -1;
		int rowIndex = -1;
		colIndex = table.findColumn(0, "Hunt Code/Description");
		rowIndex = table.findRow(colIndex, new RegularExpression(code, false));
		browser.selectRadioButton(".type", "radio", rowIndex - 1);
		Browser.unregister(tableObjs);
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void setHuntCode(String code) {
		browser.setTextField(huntCode(), code);
	}
	
	public void setHuntDescription(String desc) {
		browser.setTextField(huntDescription(), desc);
	}
	
	public void searchHunt(String code) {
		this.clickReset();
		ajax.waitLoading();
		this.setHuntCode(code);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchHunt(String code, String desc) {
		this.clickReset();
		ajax.waitLoading();
		this.setHuntCode(code);
		this.setHuntDescription(desc);
		this.clickGo();
		ajax.waitLoading();
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	/**
	 * This is a simple method to get first hunt info from UI
	 * @return
	 */
	public HuntInfo getHuntInfo() {
		HuntInfo huntInfo = new HuntInfo();
		IHtmlObject tableObjs[] = this.getAvailableHuntListTableObject();
		IHtmlTable table = (IHtmlTable)tableObjs[0];
		
		if(table.rowCount()-1<=0)
			throw new ItemNotFoundException("Could not get any hunt on page.");
		huntInfo.setDescription(table.getCellValue(1, table.findColumn(0, TABLE_COLNAME_HUNTCODEDES)));
		String info = table.getCellValue(1, table.findColumn(0, TABLE_COLNAME_SPECIES));
		huntInfo.setSpecie(info.split("-")[0]);
		if(info.contains("-"))
			huntInfo.setSpecieSubType(info.split("-")[1]);
		huntInfo.setWeaponDes(table.getCellValue(1, table.findColumn(0, TABLE_COLNAME_WEAPON)));
		DatePeriodInfo datePeriodInfo = new DatePeriodInfo();
		datePeriodInfo.setDescription(table.getCellValue(1, table.findColumn(0, TABLE_COLNAME_DATEPERIOD)));
		huntInfo.setDatePeriodInfo(datePeriodInfo);
		huntInfo.setHuntLocationInfo(table.getCellValue(1, table.findColumn(0, TABLE_COLNAME_HUNTLOCATION)));
		huntInfo.setHuntQuotaDescription(table.getCellValue(1, table.findColumn(0, TABLE_COLNAME_QUOTA)));
		Browser.unregister(tableObjs);
		
		return huntInfo;
	}
	
	public String getErrorMessage() {
		return browser.getObjectText(Property.atList(errMsg()));
	}
	
	public boolean checkHuntExisted(String huntCode, String huntDes) {
		logger.info("Check hunt "+huntCode+" exist on page or not.");
		int index = -1;
		
		searchHunt(huntCode);
		IHtmlObject tableObjs[] = this.getAvailableHuntListTableObject();
		IHtmlTable table = (IHtmlTable)tableObjs[0];
		index = table.findRow(table.findColumn(0, TABLE_COLNAME_HUNTCODEDES), new RegularExpression(huntCode+".*"+huntDes, false));
		Browser.unregister(tableObjs);
		if(index<0)
			return false;
		else
			return true;
	}
	
	public String getHuntQuota(String huntCode) {
		logger.info("Check hunt "+huntCode+" exist on page or not.");
		int index = -1;
		
		searchHunt(huntCode);
		IHtmlObject tableObjs[] = this.getAvailableHuntListTableObject();
		IHtmlTable table = (IHtmlTable)tableObjs[0];
		index = table.findRow(table.findColumn(0, TABLE_COLNAME_HUNTCODEDES), new RegularExpression(huntCode+".*", false));
		
		if(index<0)
			throw new ItemNotFoundException("Could not find hunt "+huntCode);
		
		String quota = table.getCellValue(index, table.findColumn(0, TABLE_COLNAME_QUOTA));
		Browser.unregister(tableObjs);
		return quota;
	}
}
