package com.activenetwork.qa.awo.pages.orms.financeManager.eftconfiguration;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTConfigurationScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Jun 4, 2012
 */
public class FinMgrCreateEFTConfigSchedulePage extends FinanceManagerPage{

	private static FinMgrCreateEFTConfigSchedulePage _instance = null;
	protected FinMgrCreateEFTConfigSchedulePage() {
	}
	
	public static FinMgrCreateEFTConfigSchedulePage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrCreateEFTConfigSchedulePage();
		}
		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.Div", ".id", "EFTConfigScheduleDetails");
	}

	private String prefix = "EFTConfigScheduleView-\\d+.";
	/**
	 * Input location, and select location from auto complete drop down list.
	 * @param loc
	 */
	public void setLocation(String loc){
		Property[] pro = Property.toPropertyArray(".class", "Html.LI", ".text", new RegularExpression(loc + " \\(\\d+\\)",false));
		browser.setTextField(".id", new RegularExpression("createLocationSelector", false), loc);
		browser.searchObjectWaitExists(pro,10);
		browser.clickGuiObject(pro, true);
		ajax.waitLoading();
	}
	
	public void setEffectiveDate(String date){
		browser.setTextField(".id", new RegularExpression(prefix+"effectiveDate_ForDisplay", false), date);
	}

	public void setPaymentGrp(String paymentGrp){
		browser.selectDropdownList(".id",  new RegularExpression(prefix+"paymentGroup", false), paymentGrp);
	}
	
	public void setPaymentType(String paymentType){
		browser.selectDropdownList(".id",  new RegularExpression(prefix+"paymentType", false), paymentType);
	}
	
	public void selectTransmitInvoice(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"invoiceTransmissionEnabled", false));
	}

	public void unselectTransmitInvoice(){
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"invoiceTransmissionEnabled", false));
	}
	
	public boolean checkTransmitInvoiceSelected(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression(prefix+"invoiceTransmissionEnabled", false));
	}
	
	public void setInvoiceTransStartDate(String date){
		browser.setTextField(".id", new RegularExpression(prefix+"invoiceTransmissionStartDate_ForDisplay", false), date);
	}
	
	public void selectTransmitRemittance(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"remittanceTransmissionEnabled", false));
	}

	public void unselectTransmitRemittance(){
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"remittanceTransmissionEnabled", false));
	}
	
	public boolean checkTransmitRemittanceSelected(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression(prefix+"remittanceTransmissionEnabled", false));
	}
	
	public void setRemittanceTransStartDate(String date){
		browser.setTextField(".id", new RegularExpression(prefix+"remittansTransmissionStartDate_ForDisplay", false), date);
	}
	
	public void selectIncludeDepositAdj(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"includeDepositAdjInd", false));
	}

	public void unselectIncludeDepositAdj(){
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"includeDepositAdjInd", false));
	}
	
	public void setDepositAdjStore(String depositAdjStore){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"depositAdjStore", false), depositAdjStore);
	}
	
	public void selectDeductVendorFee(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"deductVendorFeeInd", false));
	}

	public void unselectDeductVendorFee(){
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"deductVendorFeeInd", false));
	}
	
	public void setupScheduleInfo(EFTConfigurationScheduleInfo scheduleInfo){
		if(null != scheduleInfo.location){
			setLocation(scheduleInfo.location);
		}
		
		if(null != scheduleInfo.effectiveDate){
			setEffectiveDate(scheduleInfo.effectiveDate);
		}
		
		if(null != scheduleInfo.paymentGrp){
			setPaymentGrp(scheduleInfo.paymentGrp);
			ajax.waitLoading();
		}
		
		if(null != scheduleInfo.paymentType){
			setPaymentType(scheduleInfo.paymentType);
		}
		
		if(scheduleInfo.invoiceTransEnabled){
			selectTransmitInvoice();
		} else {
			unselectTransmitInvoice();
		}
		ajax.waitLoading();
		if(checkTransmitInvoiceSelected()){
			if(null == scheduleInfo.invoiceTransDate){
				throw new ErrorOnPageException("Invoice Transmission Start Date should not be null.");
			}
			setInvoiceTransStartDate(scheduleInfo.invoiceTransDate);
		}

		if(scheduleInfo.remittanceTransEnabled){
			selectTransmitRemittance();
		} else {
			unselectTransmitRemittance();
		}
		ajax.waitLoading();
		if(checkTransmitRemittanceSelected()){
			if(null == scheduleInfo.remittanceTransDate){
				throw new ErrorOnPageException("Remittance Transmission Start Date should not be null.");
			}
			setRemittanceTransStartDate(scheduleInfo.remittanceTransDate);
		}
		
		if(scheduleInfo.includeDepositAdj){
			selectIncludeDepositAdj();
		} else {
			unselectIncludeDepositAdj();
		}
		ajax.waitLoading();
		if(scheduleInfo.includeDepositAdj){
			if(StringUtil.isEmpty(scheduleInfo.depositAdjStore)) {
				throw new ErrorOnPageException("Deposit Adjustment Store should not be null or blank.");
			}
			setDepositAdjStore(scheduleInfo.depositAdjStore);
		}

		if(scheduleInfo.deductVendorFee){
			selectDeductVendorFee();
		} else {
			unselectDeductVendorFee();
		}
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
}
