package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.datacollection.legacy.orms.TitleInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrAddVehicleLienWidget extends DialogWidget{
	private static LicMgrAddVehicleLienWidget _instance = null;

	protected LicMgrAddVehicleLienWidget() {

	}

	public static LicMgrAddVehicleLienWidget getInstance() {
		if (_instance == null) {
			_instance = new LicMgrAddVehicleLienWidget();
		}
		return _instance;
	}
	
	public boolean exists() {
		boolean flag1 = super.exists();
		boolean flag2 = browser.checkHtmlObjectExists(".class", "Html.TD", ".text", new RegularExpression("^Lien Details.*", false));
		return flag1 && flag2;
	}
	
	public void clickOK(){
		super.clickOK();
//		browser.clickGuiObject(".class", "Html.A",".text",new RegularExpression("OK|k",false), 1);
	}
	
	public void setVehicleValue(String value){
		browser.setTextField(".id", new RegularExpression("AddVehicleLienDialog-\\d+\\.vehicleValue:CURRENCY_INPUT",false), value, true);
	}

	/**
	 * setTitleInfo
	 * @param titleInfo
	 */
	public void setLineInfo(TitleInfo titleInfo){
		//this.setVehicleValue(titleInfo.boatValue);
		if(null != titleInfo.lienInfo && !StringUtil.isEmpty(titleInfo.lienInfo.getDateOfLien())){
			this.setDateOfLien(titleInfo.lienInfo.getDateOfLien());
		}
		this.removeFocus();
		
		if(null != titleInfo.lienInfo && !StringUtil.isEmpty(titleInfo.lienInfo.getLienAmount())){
			this.setLienAmount(titleInfo.lienInfo.getLienAmount());
		}
	}
	/**
	 * set date of lien
	 */
	public void setDateOfLien(String lineDate){
		browser.setTextField(".id", new RegularExpression("TitleLienView-\\d+\\.lienDate_ForDisplay",false),lineDate);
	}
	/**
	 * set lien amount
	 * @param amount
	 */
	public void setLienAmount(String amount){
		browser.setTextField(".id", new RegularExpression("TitleLienView-\\d+\\.amount:ZERO_TO_NULL",false),amount);
	}
	/**
	 * click Add line details.
	 */
	public void clickAddLineDetails(){
		browser.clickGuiObject(".class", "Html.A", ".text","...");
	}
	
	 /**
     * remove focus.
     */
    public void removeFocus(){
    	browser.clickGuiObject(".class", "Html.TD", ".text", "Title Details");
    }
	
}
