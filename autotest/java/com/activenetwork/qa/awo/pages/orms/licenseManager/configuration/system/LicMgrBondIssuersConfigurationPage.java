package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrBondIssuerInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrSystemConfigurationPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Dec 16, 2011
 */
public class LicMgrBondIssuersConfigurationPage extends LicMgrSystemConfigurationPage {
	private static LicMgrBondIssuersConfigurationPage _instance = null;
	
	protected LicMgrBondIssuersConfigurationPage() {}
	
	public static LicMgrBondIssuersConfigurationPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrBondIssuersConfigurationPage();
		}

		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "bondIssuerGrid");
	}
	
	public void clickBusinessNmLink(String businessNm) {
		browser.clickGuiObject(".class", "Html.A", ".text", businessNm, true);
	}
	
	public IHtmlTable getBondIssuersList() {

		IHtmlObject objs[] = browser.getTableTestObject(".id", "bondIssuerGrid");

		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Bond Issuers List.");
		}

		IHtmlTable table = (IHtmlTable)objs[0];

		return table;
	}

	public void verifyBondIssuerInList(LicMgrBondIssuerInfo bondIssuerInfo) {

		IHtmlTable table = this.getBondIssuersList();

		String bondIssuer = table.getCellValue(bondIssuerInfo.businessNm).get(0).trim();
		
		if("".equals(bondIssuer)) {
			throw new ItemNotFoundException("Can't find the Bond Issuer.");
		}
		
		int row = table.findRow(0, bondIssuerInfo.businessNm);

		for (int j = 1; j < table.columnCount(); j++) {
			String cellValue = table.getCellValue(row, j);
			if(j == 1) {
				if(!bondIssuerInfo.activeBonds.equals(cellValue)) {
					logger.error("Active Bonds Held is wrong!The expect is:" + bondIssuerInfo.activeBonds);
					throw new ErrorOnPageException("Active Bonds Held is wrong!The expect is:" + bondIssuerInfo.activeBonds);
				}
			}
			
			if(j == 2) {
				if(!bondIssuerInfo.activeAmountHeld.equals(cellValue)) {
					logger.error("Active Amount Held is wrong!The expect is:" + bondIssuerInfo.activeAmountHeld);
					throw new ErrorOnPageException("Active Amount Held is wrong!The expect is:" + bondIssuerInfo.activeAmountHeld);
				}
			}
			if(j == 3) {
				if(!(bondIssuerInfo.lastName + ", " + bondIssuerInfo.firstName).equals(cellValue)) {
					logger.error("Contact Name Held is wrong!The expect is:" + (bondIssuerInfo.lastName + ", " + bondIssuerInfo.firstName));
					throw new ErrorOnPageException("Contact Name is wrong!The expect is:" + (bondIssuerInfo.lastName + ", " + bondIssuerInfo.firstName));
				}
			}

			if(j == 4) {
				if(!bondIssuerInfo.phone.equals(cellValue)) {
					logger.error("Contact Phone # is wrong!The expect is:" + bondIssuerInfo.phone);
					throw new ErrorOnPageException("Contact Phone # is wrong!The expect is:" + bondIssuerInfo.phone);
				}
			}

			if(j == 5) {
				if(!bondIssuerInfo.email.equals(cellValue)) {
					logger.error("Contact Email Address is wrong!The expect is:" + bondIssuerInfo.email);
					throw new ErrorOnPageException("Contact Email Address is wrong!The expect is:" + bondIssuerInfo.email);
				}
			}

			if(j == 6) {
				if(!(bondIssuerInfo.contactAddress + ", " + bondIssuerInfo.cityOrTown + ", " + bondIssuerInfo.shortforSate).equals(cellValue)) {
					logger.error("Contact Address is wrong!The expect is:" + (bondIssuerInfo.contactAddress + ", " + bondIssuerInfo.cityOrTown + ", " + bondIssuerInfo.shortforSate));
					throw new ErrorOnPageException("Contact Address is wrong!The expect is:" + (bondIssuerInfo.contactAddress + ", " + bondIssuerInfo.cityOrTown + ", " + bondIssuerInfo.shortforSate));
				}
			}
		}
	}
	
	// Check if the bond issuer with the given business name exists
	public boolean doesTheBondIssuerExist(String businessNm) {
		IHtmlTable table = this.getBondIssuersList();
		boolean doesExist = !table.getCellValue(businessNm).isEmpty();
		logger.info("The bond issuer with the business name " + businessNm + " exists? " + doesExist);
		return doesExist;	
	}
}
