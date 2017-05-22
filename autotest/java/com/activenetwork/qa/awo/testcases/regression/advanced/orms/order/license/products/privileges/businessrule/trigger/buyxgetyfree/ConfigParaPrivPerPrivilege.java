package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.businessrule.trigger.buyxgetyfree;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrValidFromDateTime;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class ConfigParaPrivPerPrivilege extends LicenseManagerTestCase{
	private String switchLocation, paraterPrivCount;
	private PrivilegeInfo privilegeInfo = new PrivilegeInfo();
	private OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
	private LicMgrValidFromDateTime validFromDateTime = LicMgrValidFromDateTime
			.getInstance();
	public void execute() {
		lm.checkPrivilegesExist(schema, privilegeInfo.code);
		lm.loginLicenseManager(login);
		
		lm.switchLocationInHomePage(switchLocation);
		lm.gotoAddItemPgFromCustomerTopMenu(cust);
		this.addPrivilegeItem(privilegeInfo);
		lm.goToCart();
		
		this.verifyPrivilegeAndCount(privilegeInfo.purchasingName, Integer.valueOf(privilegeInfo.qty)+1);//buy 4 and get another 1 for free
		this.verifyParaterPrivFree();
		
		lm.cancelCart();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		switchLocation="HF HQ Role - Auto-WAL-MART";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "Jan 01 1981";
		cust.fName = "QA-Jasmine1";
		cust.lName = "TEST-Jasmine1";
		cust.identifier.identifierType = "MDWFP #";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		privilegeInfo.code = "IXN";
		privilegeInfo.name = "InacParaPriv";
		privilegeInfo.purchasingName = privilegeInfo.code+"-"+privilegeInfo.name;
		privilegeInfo.licenseYear = lm.getFiscalYear(schema);
		privilegeInfo.qty = "4";
		privilegeInfo.validFromDate = String.valueOf(DateFunctions.getCurrentDate());
		privilegeInfo.validFromTime = "2:00";
	}
	
	private void addPrivilegeItem(PrivilegeInfo privilegeInfo){
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage
				.getInstance();
		LicMgrValidFromDateTime validFromDateTime = LicMgrValidFromDateTime
				.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget.getInstance(true);
		logger.info("Add privilege item - " + privilegeInfo.purchasingName + ".");

		addItemPg.addProductToCart(privilegeInfo.purchasingName,
				privilegeInfo.licenseYear, privilegeInfo.qty);
		Object page = browser.waitExists(confirmDialogWidget,validFromDateTime,addItemPg);
		
		if(page == confirmDialogWidget){
			confirmDialogWidget.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(validFromDateTime,addItemPg);
			if(page == validFromDateTime){
				this.verifyDateTimeObjectCount(Integer.valueOf(paraterPrivCount));
				if (privilegeInfo.validFromTime != null
						&& privilegeInfo.validFromTime.length() > 0) {
					for(int i=0;i<Integer.valueOf(paraterPrivCount);i++){
						validFromDateTime.setValidFromDateTime(privilege.validFromDate,i);
						validFromDateTime.setValidFromTime(privilegeInfo.validFromTime,i);
						validFromDateTime.selectAmPm(privilegeInfo.validFromAmPm,i);
					 }
				}
				validFromDateTime.clickOK();
				ajax.waitLoading();
				addItemPg.waitLoading();
			}
	 }
	}
	
	public void verifyPrivilegeAndCount(String expectedPriName, int count){
		int priCount = cartPg.getPriviQtyInCartPg(expectedPriName,privilegeInfo.licenseYear);
		if(priCount !=count){
			throw new ErrorOnPageException(expectedPriName + "ccount",count,priCount);
		}else{
			logger.info(expectedPriName +" number is correct");
		}
	}
	
	private void verifyDateTimeObjectCount(int expectedCount){
		if(validFromDateTime.getVaildDateTimeObject()!=expectedCount){
			throw new ErrorOnPageException("vaild date time object",expectedCount,validFromDateTime.getVaildDateTimeObject());
		}else{
			logger.info("date time object count correct");
		}
	}
	
	private void verifyParaterPrivFree(){
		String stateFee = cartPg.getFeeAmountValue("State Fee");
		String vendorFee = cartPg.getFeeAmountValue("Vendor Fee");
		String tranFee = cartPg.getFeeAmountValue("Transaction Fee");
		double total = Double.parseDouble(stateFee)+Double.parseDouble(vendorFee)+Double.parseDouble(tranFee);
		double acutalTotal = Double.parseDouble(cartPg.getTotalPriceAmount());
		
		if(Math.abs(total*4-acutalTotal)>0.0001){
			throw new ErrorOnPageException("Buy 4 with 1 for free not correct");
		}
	}
}

