package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAddVehicleLienDetailsWidget extends DialogWidget{
	private static LicMgrAddVehicleLienDetailsWidget _instance = null;

	protected LicMgrAddVehicleLienDetailsWidget() {
          super("Add Lien");
	}

	public static LicMgrAddVehicleLienDetailsWidget getInstance() {
		if (_instance == null) {
			_instance = new LicMgrAddVehicleLienDetailsWidget();
		}
		return _instance;
	}
	/**
	 * set Date of lien.
	 * @param Lien
	 */
	public void setDateOfLien(String Lien){
		browser.setTextField(".id", new RegularExpression("TitleLienView-\\d+\\.lienDate_ForDisplay",false), Lien);
	}
	/**
	 * set Lien amount.
	 * @param amount
	 */
	public void setLienAmount(String amount){
		browser.setTextField(".id", new RegularExpression("TitleLienView-\\d+\\.amount:CURRENCY_INPUT",false), amount);
	}
	/**
	 * click add lien company name.
	 */
	public void clickAddLienCompanyName(){
		browser.clickGuiObject(".class", "Html.A", ".text", "...");
	}
	/**
	 * set Lien Info
	 * @param lien
	 */
    public void setLienInfo(LienInfo lien){
    	this.setDateOfLien(lien.getDateOfLien());
    	this.removeFocus();
    	this.setLienAmount(lien.getLienAmount());
    }
    /**
     * remove focus.
     */
    public void removeFocus(){
    	browser.clickGuiObject(".class", "Html.TD", ".text", "Lien Details");
    }
    /**
     * get error message.
     * @return
     */
    public String getErrorMessage(){
    	return browser.getObjectText(".id","NOTSET");
    }
}
