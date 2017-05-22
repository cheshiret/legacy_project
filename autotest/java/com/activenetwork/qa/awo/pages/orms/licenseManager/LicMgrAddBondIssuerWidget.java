/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;


import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrBondIssuerInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  2011-9-14
 */
public class LicMgrAddBondIssuerWidget extends DialogWidget {

	private static LicMgrAddBondIssuerWidget _instance;
	
	protected LicMgrAddBondIssuerWidget () {
		
		super("Add Bond Issuer");
	}
	
	public static LicMgrAddBondIssuerWidget getInstance() {
		
		if(null==_instance) {
			_instance=new LicMgrAddBondIssuerWidget();
		}
		
		return _instance;
	}

	public void setBusinessNm(String businessNm){
		RegularExpression regx = new RegularExpression(".*businessName$", false);
		browser.setTextField(".id", regx, businessNm);
	}

	public void setContactAdr(String contactAddress){
		RegularExpression regx = new RegularExpression(".*address$", false);
		browser.setTextField(".id", regx, contactAddress);
	}

	public void setState(String state){
		RegularExpression regx = new RegularExpression(".*state$", false);
		browser.selectDropdownList(".id", regx, state);
	}

	public void setCityOrTown(String city){
		RegularExpression regx = new RegularExpression(".*city$", false);
		browser.setTextField(".id", regx, city);
	}

	public void setZipCode(String zipCd){
		RegularExpression regx = new RegularExpression(".*zipCode$", false);
		browser.setTextField(".id", regx, zipCd);
	}

	public void setCountry(String country){
		RegularExpression regx = new RegularExpression(".*country$", false);
		browser.selectDropdownList(".id", regx, country);
	}

	public void setContactFirstNm(String firstName){
		RegularExpression regx = new RegularExpression(".*contactFirstName$", false);
		browser.setTextField(".id", regx, firstName);
	}

	public void setContactLastNm(String lastName){
		RegularExpression regx = new RegularExpression(".*contactLastName$", false);
		browser.setTextField(".id", regx, lastName);
	}

	public void setPhone(String phone){
		RegularExpression regx = new RegularExpression(".*contactPhone$", false);
		browser.setTextField(".id", regx, phone);
	}

	public void setEmail(String email){
		RegularExpression regx = new RegularExpression(".*contactEmail$", false);
		browser.setTextField(".id", regx, email);
	}
	
	public void addNewBondIssuer(LicMgrBondIssuerInfo bondIssuerInfo) {
		this.setBusinessNm(bondIssuerInfo.businessNm);
		this.setCityOrTown(bondIssuerInfo.cityOrTown);
		this.setContactAdr(bondIssuerInfo.contactAddress);
		this.setContactFirstNm(bondIssuerInfo.firstName);
		this.setContactLastNm(bondIssuerInfo.lastName);
		this.setCountry(bondIssuerInfo.country);
		this.setEmail(bondIssuerInfo.email);
		this.setPhone(bondIssuerInfo.phone);
		this.setState(bondIssuerInfo.state);
		ajax.waitLoading();
		this.setZipCode(bondIssuerInfo.zipCd);
	}

}
