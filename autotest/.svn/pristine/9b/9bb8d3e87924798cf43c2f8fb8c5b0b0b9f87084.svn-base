package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrVehicleLienListPage extends LicMgrVehicleTitleDetialsInfoPage {

	private static LicMgrVehicleLienListPage _instance = null;

	private LicMgrVehicleLienListPage() {
	}

	public static LicMgrVehicleLienListPage getInstance() {
		if (_instance == null) {
			_instance = new LicMgrVehicleLienListPage();
		}
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "lienList");
	}
	/**
	 * click add lien.
	 */
	public void clickAddLien(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Lien");
	}
	/**
	 * click lien id.
	 * @param id
	 */
	public void clickLienId(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	public String getActiveLienId(){
		String lienId = "";
		IHtmlObject[] objs = this.getLienListObject();
		IHtmlTable table = (IHtmlTable)objs[0];

		int row = table.findRow(2, OrmsConstants.ACTIVE_STATUS);
		if(row>0){
			lienId = table.getRowValues(row).get(1);
		} else {
			logger.info("There isn't active lien.");
		}
		Browser.unregister(objs);
		return lienId;
	}
	
	private IHtmlObject[] getLienListObject(){
		IHtmlObject[] objs = browser.getTableTestObject(".id","lienList");
		if(objs.length<1){
			throw new ItemNotFoundException("No lien table exist");
		}
		return objs;
	}

	public LienInfo getLienInfoByID(String lienID){
		IHtmlObject[] objs = this.getLienListObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		LienInfo lien = new LienInfo();
		int row = table.findRow(1, lienID);
		if(row>0){
			List<String> rowValue = table.getRowValues(row);
			lien.setLienId(rowValue.get(1));
			lien.setStauts(rowValue.get(2));
			lien.setDateOfLien(rowValue.get(3));
			lien.setLienAmount(rowValue.get(4).replaceAll("\\$", ""));
			lien.setDateOfRelease(rowValue.get(5));
			LienCompanyDetailsInfo companyInfo = new LienCompanyDetailsInfo();
			companyInfo.setCompanyInfo(rowValue.get(6));
			lien.setLienCompanyDetailsInfo(companyInfo);
		} else {
			logger.info("There isn't lien info which ID is:"+lienID);
		}
		Browser.unregister(objs);
		return lien;
	}
	
	/**
	 * get Lien id
	 * @param lienInfo
	 */
	public String getLienId(LienInfo lienInfo){
		String lienId = "";
		IHtmlObject[] objs = this.getLienListObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		for(int i = 1; i<table.rowCount(); i++){
			if(table.getRowValues(i).get(2).equals(lienInfo.getStauts())&& table.getRowValues(i).get(3).equals(lienInfo.getDateOfLien())
					&& table.getRowValues(i).get(4).equals(lienInfo.getLienAmount()) && table.getRowValues(i).get(5).equals(lienInfo.getDateOfRelease())
					&& table.getRowValues(i).get(6).equals(lienInfo.getLienCompanyDetailsInfo().lienCompanyName)){
				lienId = table.getRowValues(i).get(1);
				break;
			}
		}
		Browser.unregister(objs);
		return lienId;
	}
	
	public void selectLienItemCheckBox(String lienId){
		this.selectLienRadioBtn(1, lienId);
	}
	/**
	 * click Release lien.
	 */
	public void clickReleaseLien(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Release Lien");
	}
	/**
	 * click reactivate lien.
	 */
	public void clickReactivateLien(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Reactivate Lien");
	}
	
	public void selectLienItem(int index){
		browser.clickGuiObject(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems",false), index);
	}

	/**
	 * reactivate lien.
	 * @param lienId
	 */
	public void reactivateLien(String lienId){
		this.selectLienItemCheckBox(lienId);
		this.clickReactivateLien();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void selectActiveLien(){
		this.selectLienRadioBtn(2, OrmsConstants.ACTIVE_STATUS);
	}
	
	private void selectLienRadioBtn(int col_num,String value){
		IHtmlObject[] objs = this.getLienListObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		for(int i = 1;i<table.rowCount();i++){
			if(table.getCellValue(i,col_num).equals(value)){
				this.selectLienItem(i);
			}
		}
		Browser.unregister(objs);
	}
	/**
	 * release lien.
	 */
	public void releaseLien(){
		this.selectActiveLien();
		this.clickReleaseLien();
		ajax.waitLoading();
	}

	/**
	 * Verify lien list info.
	 * @param expectLien
	 * @param actualLien
	 */
	public boolean verifyLienInfo(LienInfo expectLien){
		LienInfo actualLien = this.getLienInfoByID(expectLien.getLienId());

		boolean result = true;
		result &= MiscFunctions.compareResult("Status", expectLien.getStauts(), actualLien.getStauts());
		result &= MiscFunctions.compareResult("Date of Lien", expectLien.getDateOfLien(), actualLien.getDateOfLien());
		result &= MiscFunctions.compareResult("Lien Amount", Double.valueOf(expectLien.getLienAmount()), Double.valueOf(actualLien.getLienAmount()));
		result &= MiscFunctions.compareResult("Date of Release", expectLien.getDateOfRelease(), actualLien.getDateOfRelease());
		result &= MiscFunctions.compareResult("Lien Company", expectLien.getLienCompanyDetailsInfo().getCompanyInfo(), actualLien.getLienCompanyDetailsInfo().getCompanyInfo());
		return result;
	}
}
