package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTReturnTransactionInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @Description: This is EFT returns job transaction detail page search/list page:FM/LM --->EFT ---> Returns Jobs ----> Return Job detail page --->transaction Detail
 * @author pchen
 * @Date  September 5, 2012
 */
public class FinMgrReturnJobTransactionDetailPage extends FinMgrReturnsJobPage {
	static private FinMgrReturnJobTransactionDetailPage _instance = null;
	private String prefix = "EFTReturnTransactionView-\\d+\\.";

	protected FinMgrReturnJobTransactionDetailPage() {
	}

	static public FinMgrReturnJobTransactionDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrReturnJobTransactionDetailPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text",
				"EFT Return Transaction Info");
	}
	/**
	 * Get eft return id
	 * @return
	 */
	public String getEFTReturnId() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"id", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String returnId = objs[0].getProperty(".text").split("EFT Return ID")[1].trim();
		Browser.unregister(objs);
		return returnId;
	}
	/**
	 * Get reference number
	 * @return
	 */
	public String getReferanceNum() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"referenceNum", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String referenceNum = objs[0].getProperty(".text").split("Reference #")[1].trim();
		Browser.unregister(objs);
		return referenceNum;
	}
	/**
	 * Get return job id
	 * @return
	 */
	public String getReturnJobId() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"eftReturnJobId", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String eftReturnJobId = objs[0].getProperty(".text").split("EFT Return Job ID")[1].trim();
		Browser.unregister(objs);
		return eftReturnJobId;
	}
	/**
	 * Get immediate destination
	 * @return
	 */
	public String getImmediateDestination() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"immediateDest", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String immediateDest = objs[0].getProperty(".text").split("Immediate Destination")[1].trim();
		Browser.unregister(objs);
		return immediateDest;
	}
	/**
	 * Get company identification
	 * @return
	 */
	public String getCompanyIdentification() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"companyIdentifier", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String companyIdentifier = objs[0].getProperty(".text").split("Company Identification")[1].trim();
		Browser.unregister(objs);
		return companyIdentifier;
	}
	/**
	 * Get Standard Entry Class Code
	 * @return
	 */
	public String getStandardEntryClassCode() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"standardEntryClassCode", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String standardEntryClassCode = objs[0].getProperty(".text").split("Standard Entry Class Code")[1].trim();
		Browser.unregister(objs);
		return standardEntryClassCode;
	}
	/**
	 * Get company entry description
	 * @return
	 */
	public String getCompanyEntryDescription() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"companyEntryDescription", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String companyIdentifier = objs[0].getProperty(".text").split("Company Entry Description")[1].trim();
		Browser.unregister(objs);
		return companyIdentifier;
	}
	/**
	 * Get company effective entry date
	 * @return
	 */
	public String getEffectiveEntryDate() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"effectiveEntryDate:DATE", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String effectiveEntryDate = objs[0].getProperty(".text").split("Effective Entry Date")[1].trim();
		Browser.unregister(objs);
		return effectiveEntryDate;
	}
	/**
	 * Get originating dfi identification
	 * @return
	 */
	public String getOriginatingDFIIdentification() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"originalDFIIdentifier", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String originalDFIIdentifier = objs[0].getProperty(".text").split("Originating DFI Identification")[1].trim();
		Browser.unregister(objs);
		return originalDFIIdentifier;
	}
	/**
	 * Get rdfi transit routing/aba num
	 * @return
	 */
	public String getRDFITransitRoutingABANum() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".text",
				new RegularExpression("^RDFI Transit Routing/ABA #.*", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String companyIdentifier = objs[0].getProperty(".text").split("RDFI Transit Routing/ABA #")[1].trim();
		Browser.unregister(objs);
		return companyIdentifier;
	}
	/**
	 * Get check digit
	 * @return
	 */
	public String getCheckDigit() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"transitRoutingCheckDigit", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String transitRoutingCheckDigit = objs[0].getProperty(".text").split("Check Digit")[1].trim();
		Browser.unregister(objs);
		return transitRoutingCheckDigit;
	}
	
	/**
	 * Get original entry trance number
	 * @return
	 */
	public String getOriginalEntryTraceNum() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"originalEntryTraceNumber", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String originalEntryTraceNumber = objs[0].getProperty(".text").split("Original Entry Trace #")[1].trim();
		Browser.unregister(objs);
		return originalEntryTraceNumber;
	}
	/**
	 * Get transaction code
	 * @return
	 */
	public String getTransactionCode() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"transactionCode", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String transactionCode = objs[0].getProperty(".text").split("Transaction Code")[1].trim();
		Browser.unregister(objs);
		return transactionCode;
	}
	/**
	 * Get transit routing number
	 * @return
	 */
	public String getTransitRountingNum() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".text",
				new RegularExpression("^Transit Routing #.*", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String transitRoutingNum = objs[0].getProperty(".text").split("Transit Routing #")[1].trim();
		Browser.unregister(objs);
		return transitRoutingNum;
	}
	/**
	 * Get account number
	 * @return
	 */
	public String getAccount() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"maskedDfiAccountNumber", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String maskedDfiAccountNumber = objs[0].getProperty(".text").split("Account #")[1].trim();
		Browser.unregister(objs);
		return maskedDfiAccountNumber;
	}
	/**
	 * Get amount
	 * @return
	 */
	public String getAmount() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"amount:CURRENCY", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String amount = objs[0].getProperty(".text").split("Amount")[1].trim();
		Browser.unregister(objs);
		return amount;
	}
	/**
	 * Get return reason code
	 * @return
	 */
	public String getReturnReasonCode() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"returnReasonCode", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String[] text = objs[0].getProperty(".text").split("Return Reason Code");
		String returnReasonCode;
		if(text.length < 2){
			returnReasonCode = "";
		}else{
			returnReasonCode = text[1].trim();
		}		
		Browser.unregister(objs);
		return returnReasonCode;
	}
	/**
	 * Get individual id 
	 * @return
	 */
	public String getIndividualId() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"individualIdetificationNumber", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String[] text = objs[0].getProperty(".text").split("Individual ID");
		String individualIdetificationNumber;
		if(text.length < 2){
			individualIdetificationNumber = "";
		}else{
			individualIdetificationNumber = text[1].trim();
		}
		Browser.unregister(objs);
		return individualIdetificationNumber;
	}
	
	/**
	 * Get individual name
	 * @return
	 */
	public String getIndividualName() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"individualName", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String individualName = objs[0].getProperty(".text").split("Individual Name")[1].trim();
		Browser.unregister(objs);
		return individualName;
	}
	/**
	 * Get addenda trance num
	 * @return
	 */
	public String getAddendaTranceNum() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"addendaTraceNumber", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String addendaTraceNumber = objs[0].getProperty(".text").split("Addenda Trace #")[1].trim();
		Browser.unregister(objs);
		return addendaTraceNumber;
	}
	/**
	 * Get addenda information
	 * @return
	 */
	public String getAddendaInformation() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"addendaInfo", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		
		String addendaInfo = objs[0].getProperty(".text").replace("Addenda Information","").trim();
		Browser.unregister(objs);
		return addendaInfo;
	}
	/**
	 * Get eft transmission id
	 * @return
	 */
	public String getEFTTransmissionId() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"matchingEFTTransmissionId", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String matchingEFTTransmissionId = objs[0].getProperty(".text").split("EFT Transmission ID")[1].trim();
		Browser.unregister(objs);
		return matchingEFTTransmissionId;
	}
	/**
	 * Get manually match value
	 * @return
	 */
	public String getManuallyMatched() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"manuallyMatched:BOOLEAN_YESNO", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String manuallyMatched = objs[0].getProperty(".text").split("Manually Matched")[1].trim();
		Browser.unregister(objs);
		return manuallyMatched;
	}
	/**
	 * Get transmission date
	 * @return
	 */
	public String getDateTime() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"matchingDate", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String matchingDate = objs[0].getProperty(".text").split("Date/Time")[1].trim();
		Browser.unregister(objs);
		return matchingDate;
	}
	/**
	 * Get location
	 * @return
	 */
	public String getLocation() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"matchingLocation", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String matchingLocation = objs[0].getProperty(".text").split("Location")[1].trim();
		Browser.unregister(objs);
		return matchingLocation;
	}
	/**
	 * Get user
	 * @return
	 */
	public String getUser() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"matchingUser", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String matchingUser = objs[0].getProperty(".text").split("User")[1].trim();
		Browser.unregister(objs);
		return matchingUser;
	}
	/**
	 * Get note
	 * @return
	 */
	public String getNotes() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"manuallyMatchedNote", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String[] text = objs[0].getProperty(".text").split("Notes");
		String manuallyMatchedNote;
		if(text.length < 2){
			manuallyMatchedNote = "";
		}else{
			manuallyMatchedNote = text[1];
		}
		Browser.unregister(objs);
		return manuallyMatchedNote;
	}
	/**
	 * Click back button
	 */
	public void clickBack(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Back");
	}
    /**
     * Get all transaction info
     * @return
     */
	public EFTReturnTransactionInfo getTransctionInfo() {
		EFTReturnTransactionInfo transactionInfo = new EFTReturnTransactionInfo();
		transactionInfo.setReturnId(this.getEFTReturnId());
		transactionInfo.setReferenceNum(this.getReferanceNum());
		transactionInfo.setReturnJobId(this.getReturnJobId());
		transactionInfo.setImmediateDestination(this.getImmediateDestination());
		transactionInfo.setCompanyIdentification(this.getCompanyIdentification());
		transactionInfo.setStandardEntryClassCode(this.getStandardEntryClassCode());
		transactionInfo.setCompanyEntryDescription(this.getCompanyEntryDescription());
		transactionInfo.setEffectiveEntryDate(this.getEffectiveEntryDate());
		transactionInfo.setOriginationDFIIdentification(this.getOriginatingDFIIdentification());
		transactionInfo.setRDFITransitRoutingOrABANum(this.getRDFITransitRoutingABANum());
		transactionInfo.setCheckDigit(this.getCheckDigit());
		transactionInfo.setOriginalEntryTranceNum(this.getOriginalEntryTraceNum());
		transactionInfo.setTransactionCode(this.getTransactionCode());
		transactionInfo.setTransitRoutingNum(this.getTransitRountingNum());
		transactionInfo.setAccountNum(this.getAccount());
		transactionInfo.setAmount(this.getAmount());
		transactionInfo.setReturnReasonCode(this.getReturnReasonCode());
		transactionInfo.setIndividualId(this.getIndividualId());
		transactionInfo.setIndividualName(this.getIndividualName());
		transactionInfo.setAddendaTranceNum(this.getAddendaTranceNum());
		transactionInfo.setAddendaInformation(this.getAddendaInformation());
		transactionInfo.setEftTransmissionId(this.getEFTTransmissionId());
		transactionInfo.setManuallyMatched(this.getManuallyMatched());
		return transactionInfo;
	}

}
