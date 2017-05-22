package com.activenetwork.qa.awo.pages.orms.financeManager.eftconfiguration;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTConfigurationScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
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
public class FinMgrEFTConfigScheduleDetailPage extends FinanceManagerPage{

	private static FinMgrEFTConfigScheduleDetailPage _instance = null;
	protected FinMgrEFTConfigScheduleDetailPage() {
	}
	
	public static FinMgrEFTConfigScheduleDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrEFTConfigScheduleDetailPage();
		}
		return _instance;
	}

	public boolean exists() {
		return (browser.checkHtmlObjectExists(".class", "Html.Div", ".id", "EFTConfigScheduleDetails")
			&& browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Deactivate"));
	}
	
	public void clickDeactivate(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	
	public String getID(){
		String text = browser.getObjectText(".class", "Html.SPAN", ".text", new RegularExpression("^ID\\d+$", false));
		return StringUtil.getSubString(text, "ID");
	}
	
	public String getStatus(){
		String text = browser.getObjectText(".class", "Html.SPAN", ".text", new RegularExpression("^Status", false));
		return StringUtil.getSubString(text, "Status");
	}
	
	public String getLocation(){
		String text = browser.getObjectText(".class", "Html.SPAN", ".text", new RegularExpression("^Location", false));
		return StringUtil.getSubString(text, "Location");
	}

	private String prefix = "EFTConfigScheduleView-\\d+.";
	public String getEffectiveDate(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"effectiveDate_ForDisplay", false));
	}
	
	public String getPaymentGrp(){
		String text = browser.getObjectText(".class", "Html.SPAN", ".text", new RegularExpression("^Payment Group", false));
		return StringUtil.getSubString(text, "Payment Group");
	}
	
	public String getPaymentType(){
		String text = browser.getObjectText(".class", "Html.SPAN", ".text", new RegularExpression("^Payment Type", false));
		return StringUtil.getSubString(text, "Payment Type");
	}

	public void selectTransmitInvoice(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"invoiceTransmissionEnabled", false));
	}

	public void unselectTransmitInvoice(){
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"invoiceTransmissionEnabled", false));
	}
	
	public boolean checkTransmitInvoiceSelected(){
		return browser.isCheckBoxSelected(".id", new RegularExpression(prefix+"invoiceTransmissionEnabled", false));
	}
	
	public void setInvoiceTransStartDate(String date){
		browser.setTextField(".id", new RegularExpression(prefix+"invoiceTransmissionStartDate_ForDisplay", false), date);
	}
	
	public String getInvoiceTransStartDate(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"invoiceTransmissionStartDate_ForDisplay", false));
	}
	
	public void selectTransmitRemittance(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"remittanceTransmissionEnabled", false));
	}

	public void unselectTransmitRemittance(){
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"remittanceTransmissionEnabled", false));
	}
	
	public boolean checkTransmitRemittanceSelected(){
		return browser.isCheckBoxSelected(".id", new RegularExpression(prefix+"remittanceTransmissionEnabled", false));
	}
	
	public void setRemittanceTransStartDate(String date){
		browser.setTextField(".id", new RegularExpression(prefix+"remittansTransmissionStartDate_ForDisplay", false), date);
	}	
	public String getRemittanceTransStartDate(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"remittansTransmissionStartDate_ForDisplay", false));
	}
	
	public void selectIncludeDepositAdj(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"includeDepositAdjInd", false));
	}

	public void unselectIncludeDepositAdj(){
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"includeDepositAdjInd", false));
	}
	
	public boolean checkIncludeDepositAdjSelected(){
		return browser.isCheckBoxSelected(".id", new RegularExpression(prefix+"includeDepositAdjInd", false));
	}
	
	public void setDepositAdjStore(String depositAdjStore){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"depositAdjStore", false), depositAdjStore);
	}
	public String getDepositAdjStore(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"depositAdjStore", false)).toString();
	}		
	
	public void selectDeductVendorFee(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"deductVendorFeeInd", false));
	}

	public void unselectDeductVendorFee(){
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"deductVendorFeeInd", false));
	}

	public boolean checkDeductVendorFeeSelected(){
		return browser.isCheckBoxSelected(".id", new RegularExpression(prefix+"deductVendorFeeInd", false));
	}
	
	public String getActivationDate(){
		String text = browser.getObjectText(".class", "Html.SPAN", ".text", new RegularExpression("^Activation Date", false));
		return StringUtil.getSubString(text, "Activation Date");
	}
	
	public String getActivationUser(){
		String text = browser.getObjectText(".class", "Html.SPAN", ".text", new RegularExpression("^Activation User", false));
		return StringUtil.getSubString(text, "Activation User");
		
	}

	public String getActivationLoc(){
		String text = browser.getObjectText(".class", "Html.SPAN", ".text", new RegularExpression("^Activation Location", false));
		return StringUtil.getSubString(text, "Activation Location");
	}
	
	public EFTConfigurationScheduleInfo getEFTConfigScheduleDetailInfo(){
		EFTConfigurationScheduleInfo detailInfo = new EFTConfigurationScheduleInfo();
		detailInfo.id = this.getID();
		detailInfo.status = this.getStatus();
		detailInfo.location = this.getLocation();
		detailInfo.effectiveDate = this.getEffectiveDate();
		detailInfo.paymentGrp = this.getPaymentGrp();
		detailInfo.paymentType = this.getPaymentType();
		detailInfo.invoiceTransEnabled = this.checkTransmitInvoiceSelected();
		detailInfo.invoiceTransDate = this.getInvoiceTransStartDate();
		detailInfo.remittanceTransEnabled = this.checkTransmitRemittanceSelected();
		detailInfo.remittanceTransDate = this.getRemittanceTransStartDate();
		detailInfo.includeDepositAdj = this.checkIncludeDepositAdjSelected();
		detailInfo.depositAdjStore = this.getDepositAdjStore();
		detailInfo.deductVendorFee = this.checkDeductVendorFeeSelected();
		return detailInfo;
	}
	
	/**
	 * Verify whether EFT Configuration Schedule is correct or not
	 * @param expectInfo
	 */
	public boolean verifyScheduleInfo(EFTConfigurationScheduleInfo expectInfo){
		EFTConfigurationScheduleInfo actualInfo = this.getEFTConfigScheduleDetailInfo();
		boolean result = true;
		logger.info("Verify whether EFT Configuration Schedule is correct or not");
		result &= MiscFunctions.compareResult("location", expectInfo.location, actualInfo.location);
		result &= MiscFunctions.compareResult("payment group", expectInfo.paymentGrp, actualInfo.paymentGrp);
		result &= MiscFunctions.compareResult("payment type", expectInfo.paymentType, actualInfo.paymentType);
		result &= MiscFunctions.compareResult("status", expectInfo.status, actualInfo.status);
		result &= MiscFunctions.compareResult("deposit adjustment", expectInfo.includeDepositAdj, actualInfo.includeDepositAdj);
		result &= MiscFunctions.compareResult("deduct vendor fee", expectInfo.deductVendorFee, actualInfo.deductVendorFee);
		result &= MiscFunctions.compareResult("effective date", expectInfo.effectiveDate, actualInfo.effectiveDate);
		result &= MiscFunctions.compareResult("transmit invoice", expectInfo.invoiceTransEnabled, actualInfo.invoiceTransEnabled);

		// if transmit invoices has not been selected, invoice transmission start date should not be set up
		if(!expectInfo.invoiceTransEnabled){
			if(!StringUtil.isEmpty(actualInfo.invoiceTransDate)){
				logger.error("--Transmit Invoices has not been selected, so Invoice Transmission Start Date should be blank. But now its value is"+actualInfo.invoiceTransDate);
				result &= false;
			}
		} else {
			result &= MiscFunctions.compareResult("Invoice Transmission Date", expectInfo.invoiceTransDate, actualInfo.invoiceTransDate);
		}

		result &= MiscFunctions.compareResult("transmit remittance", expectInfo.remittanceTransEnabled, actualInfo.remittanceTransEnabled);
		
		// if transmit remittance has not been selected, remittance transmission start date should not be set up
		if(!expectInfo.remittanceTransEnabled){
			if(!StringUtil.isEmpty(actualInfo.remittanceTransDate)){
				logger.error("--Transmit Remittances has not been selected, so Remittance Transmission Start Date should be blank. But now its value is"+actualInfo.remittanceTransDate);
				result &= false;
			}
		} else {
			result &= MiscFunctions.compareResult("Remittance Transmission Date", expectInfo.remittanceTransDate, actualInfo.remittanceTransDate);
		}

		logger.info("Verify deposit adjustment store...");
		// if include deposit adjustment has not been selected, deposit adjustment store should not be set up
		if(!expectInfo.includeDepositAdj){
			if(!StringUtil.isEmpty(actualInfo.depositAdjStore)){
				logger.error("--Incluede Deposit Adjustment has not been selected, so Deposit Adjustment Store should be blank. But now its value is:"+actualInfo.depositAdjStore+".");
				result &= false;
			}
		} else {
			result &= MiscFunctions.compareResult("Deposit Adjustment Store", expectInfo.depositAdjStore, actualInfo.depositAdjStore);
		}
		return result;
	}
}
