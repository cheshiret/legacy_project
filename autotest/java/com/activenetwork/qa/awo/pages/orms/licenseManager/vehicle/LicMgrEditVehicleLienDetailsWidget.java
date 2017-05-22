package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrEditVehicleLienDetailsWidget extends DialogWidget{
	private static LicMgrEditVehicleLienDetailsWidget _instance = null;

	protected LicMgrEditVehicleLienDetailsWidget() {
          super("Edit Lien");
	}

	public static LicMgrEditVehicleLienDetailsWidget getInstance() {
		if (_instance == null) {
			_instance = new LicMgrEditVehicleLienDetailsWidget();
		}
		return _instance;
	}
	/**
	 * set date of lien.
	 * @param daetofLien
	 */
	public void setDateofLien(String daetofLien){
		browser.setTextField(".id", new RegularExpression("TitleLienView-\\d+\\.lienDate_ForDisplay",false), daetofLien);
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
	 * edit lien info.
	 * @param dateOfLien
	 * @param amount
	 */
	public void editLienInfo(String dateOfLien,String amount){
		this.setDateofLien(dateOfLien);
		this.removeFocus();
		this.setLienAmount(amount);
	}
	/**
	 * remove foucs.
	 */
	public void removeFocus(){
		browser.clickGuiObject(".class", "Html.TD", ".text", "Lien Details");
	}
	private String getSpanText(String labelName){
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.SPAN");
		p[1] = new Property(".className", "inputwithrubylabel");
		p[2] = new Property(".text", new RegularExpression("^"+labelName+".*", false));
		String text = browser.getObjectText(p);
		return StringUtil.getSubString(text, labelName);
	}
	
	private String getDateofLien(){
		return browser.getTextFieldValue(".id", new RegularExpression("TitleLienView-\\d+.lienDate_ForDisplay", false));
	}
	
	private String getAmount(){
		return browser.getTextFieldValue(".id", new RegularExpression("TitleLienView-\\d+.amount:CURRENCY_INPUT", false)).trim().replaceAll("\\$", "");
	}
	
	public LienInfo getLienDetailInfo(){
		LienInfo lien = new LienInfo();
		lien.setLienId(this.getSpanText("Lien ID").trim());
		lien.setStauts(this.getSpanText("Status").trim());
		lien.setDateOfLien(this.getDateofLien());
		lien.setLienAmount(this.getAmount());
		LienCompanyDetailsInfo comInfo = new LienCompanyDetailsInfo();
		comInfo.lienCompanyName = this.getSpanText("Lien Company Name *").trim();
		comInfo.address = this.getSpanText("Address");
		comInfo.city = this.getSpanText("City").trim();
		comInfo.state = this.getSpanText("State").trim();
		comInfo.zip = this.getSpanText("ZIP/Postal").trim();
		comInfo.contactPhone = this.getSpanText("Contact Phone").trim();
		lien.setLienCompanyDetailsInfo(comInfo);
		lien.setCreationDateTime(this.getSpanText("Creation Date/Time"));
		lien.setCreationUser(this.getSpanText("Creation User"));
		return lien;
	}
	
	/**
	 * Verify lien list info.
	 * @param expectLien
	 * @param actualLien
	 */
	public boolean verifyLienInfo(LienInfo expectLien){
		LienInfo actualLien = this.getLienDetailInfo();
		boolean result = true;
		result &= MiscFunctions.compareResult("Status", expectLien.getStauts(), actualLien.getStauts());
		result &= MiscFunctions.compareResult("Date of Lien", expectLien.getDateOfLien(), actualLien.getDateOfLien());
		result &= MiscFunctions.compareResult("Lien Amount", Double.valueOf(expectLien.getLienAmount()), Double.valueOf(actualLien.getLienAmount()));
		result &= MiscFunctions.compareResult("Creation User", expectLien.getCreationUser().replaceAll("\\s+", StringUtil.EMPTY), actualLien.getCreationUser().replaceAll("\\s+", StringUtil.EMPTY));
		
		LienCompanyDetailsInfo expectCompanyInfo = expectLien.getLienCompanyDetailsInfo();
		LienCompanyDetailsInfo actualCompanyInfo = actualLien.getLienCompanyDetailsInfo();
		result &= MiscFunctions.compareResult("Lien Company - Name", expectCompanyInfo.lienCompanyName, actualCompanyInfo.lienCompanyName);
		result &= MiscFunctions.compareResult("Lien Company - Address", expectCompanyInfo.address, actualCompanyInfo.address);
		result &= MiscFunctions.compareResult("Lien Company - City", expectCompanyInfo.city, actualCompanyInfo.city);
		result &= MiscFunctions.compareResult("Lien Company - State", expectCompanyInfo.state, actualCompanyInfo.state);
		result &= MiscFunctions.compareResult("Lien Company - ZIP", expectCompanyInfo.zip, actualCompanyInfo.zip);
		result &= MiscFunctions.compareResult("Lien Company - Contact Phone", expectCompanyInfo.contactPhone, actualCompanyInfo.contactPhone);
		return result;
	}
}
