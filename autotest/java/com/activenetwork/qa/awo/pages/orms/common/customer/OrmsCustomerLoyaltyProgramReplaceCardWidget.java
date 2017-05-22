package com.activenetwork.qa.awo.pages.orms.common.customer;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Apr 16, 2014
 */
public class OrmsCustomerLoyaltyProgramReplaceCardWidget extends DialogWidget {
	private static OrmsCustomerLoyaltyProgramReplaceCardWidget _instance = null;
	
	private OrmsCustomerLoyaltyProgramReplaceCardWidget() {
		super("Replace Card");
	}
	
	public static OrmsCustomerLoyaltyProgramReplaceCardWidget getInstance() {
		if(_instance == null) _instance = new OrmsCustomerLoyaltyProgramReplaceCardWidget();
		return _instance;
	}
	
	private Property[] programName() {
		return Property.toPropertyArray(".id", "MembershipCardReplaceInfo.programName");
	}
	
	private Property[] existingCardNumber() {
		return Property.toPropertyArray(".id", "MembershipCardReplaceInfo.oldCardNumber");
	}
	
	private Property[] customerName() {
		return Property.toPropertyArray(".id", "MembershipCardReplaceInfo.customerName");
	}
	
	private Property[] newCardNumber() {
		return Property.toPropertyArray(".id", "MembershipCardReplaceInfo.newCardNumber");
	}
	
	private Property[] notes() {
		return Property.toPropertyArray(".id", "MembershipCardReplaceInfo.notes");
	}
	
	private Property[] errorMsg() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "message msgerror");
	}
	
	public boolean isProgramNameEditable() {
		return browser.checkHtmlObjectEnabled(programName());
	}
	
	public String getProgramName() {
		return browser.getTextFieldValue(programName());
	}
	
	public boolean isExistingCardNumberEditable() {
		return browser.checkHtmlObjectEnabled(existingCardNumber());
	}
	
	public String getExistingCardNumber() {
		return browser.getTextFieldValue(existingCardNumber());
	}
	
	public boolean isCustomerNameEditable() {
		return browser.checkHtmlObjectEnabled(customerName());
	}
	
	public String getCustomerName() {
		return browser.getTextFieldValue(customerName());
	}
	
	public boolean isNewCardNumberEditable() {
		return browser.checkHtmlObjectEnabled(newCardNumber());
	}
	
	public void setNewCardNumber(String newCardNum) {
		browser.setTextField(newCardNumber(), newCardNum);
	}
	
	public String getNewCardNumber() {
		return browser.getTextFieldValue(newCardNumber());
	}
	
	public boolean isNotesEditable() {
		return browser.checkHtmlObjectEnabled(notes());
	}
	
	public void setNotes(String notes) {
		browser.setTextArea(notes(), notes);
	}
	
	public String getNotes() {
		return browser.getTextAreaValue(notes());
	}
	
	public boolean isErrorMsgExists() {
		return browser.checkHtmlObjectExists(errorMsg());
	}
	
	public String getErrorMsg() {
		return browser.getObjectText(errorMsg());
	}
	
	public LoyaltyProgram getLoyaltyProgramInfo() {
		LoyaltyProgram lp = new LoyaltyProgram();
		
		lp.setName(this.getProgramName());
		lp.setCardNumber(this.getExistingCardNumber());
		lp.setCustomerName(this.getCustomerName());
		//TODO to get other info
		
		return lp;
	}
	
	public boolean compareLoyaltyProgramInfo(LoyaltyProgram expected) {
		LoyaltyProgram actual = this.getLoyaltyProgramInfo();
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Loyalty Program Name", expected.getName(), actual.getName());
		result &= MiscFunctions.compareResult("Loyalty Program Existing Card Number", expected.getCardNumber(), actual.getCardNumber());
		result &= MiscFunctions.compareResult("Loyalty Program Customer Name", expected.getCustomerName(), actual.getCustomerName());
		//TODO compare other info
		
		return result;
	}
	
	public void verifyLoyaltyProgram(LoyaltyProgram expected) {
		if(!compareLoyaltyProgramInfo(expected)) throw new ErrorOnPageException("Loyalty Program(#=" + expected.getCardNumber() + ") info is NOT correct.");
		logger.info("Loyalty Program(#=" + expected.getCardNumber() + ") info is correct.");
	}
}
