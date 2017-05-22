/*
 * Created on Jan 12, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.distribution;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author Ssong
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrRecipientScheduleDetailPage extends FinanceManagerPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRecipientScheduleDetailPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrRecipientScheduleDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrRecipientScheduleDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrRecipientScheduleDetailPage();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Distribution Recipient Schedule Details");
	}

	/**
	 * select distribution coverage
	 * 
	 * @param coverage
	 */
	public void selectCoverage(String coverage) {
		browser.selectDropdownList(".id", new RegularExpression("coverage_location_id|SlipDistribRecipientScheduleView-\\d+\\.coverageLocation", false), coverage);
		waitLoading();
	}

	/**
	 * 
	 * @return selected distribution coverage
	 */
	public String getCoverage() {
		return browser.getDropdownListValue(".id", new RegularExpression("coverage_location_id|SlipDistribRecipientScheduleView-\\d+\\.coverageLocation", false), 0);
	}

	/**
	 * Select revenue location by given value
	 * 
	 * @param value
	 */
	public void selectLocation(String value) {
//		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage.getInstance();
		if (null != value && !"".equals(value)) {
			if (!value.equalsIgnoreCase("All")) {
				browser.selectRadioButton(".id", new RegularExpression("revloc_id|SlipDistribRecipientScheduleView-\\d+\\.allOrSpecLoc", false), ".value", new RegularExpression("specific|SPECIFIC_LOC", false));
				//comments by Jane:If you want to handle findLocationPage, please update in your keyword
//				findLocPg.waitExists();// add by pzhu
//				ajax.waitLoading();
			} else {
				browser.selectRadioButton(".id", new RegularExpression("revloc_id|SlipDistribRecipientScheduleView-\\d+\\.allOrSpecLoc", false), ".value", new RegularExpression("0|ALL_REVENUE_LOC", false));
			}
		}
	}

	/**
	 * Get selected revenue location
	 * 
	 * @return
	 */
	public String getLocation() {
		IHtmlObject[] objs = browser.getRadioButton(".id", new RegularExpression("revloc_id|SlipDistribRecipientScheduleView-\\d+\\.allOrSpecLoc", false));
		String value = "";

		for (IHtmlObject o : objs) {
			if (((IRadioButton) o).isSelected()) {
				value = o.getProperty(".value").toString();
			}
		}
		Browser.unregister(objs);
		if (value.equalsIgnoreCase("0") || value.equalsIgnoreCase("ALL_REVENUE_LOC")) {
			return "All";
		} else {
			String temp = parseDetailTable();
			return temp.substring(
					temp.indexOf("Location Name") + "Location Name".length(),//updated by pzhu
					temp.indexOf("Recipient Type")).trim();
		}
	}

	/**
	 * Select given recipient type from drop down list
	 * 
	 * @param recipientType
	 */
	public void selectRecipientType(String recipientType) {
		browser.selectDropdownList(".id", new RegularExpression("recipient_type|SlipDistribRecipientScheduleView-\\d+\\.rcpntType", false), recipientType);
		waitLoading();
	}

	/**
	 * 
	 * @return selected Recipient Type
	 */
	public String getRecipientType() {
		return browser.getDropdownListValue(".id", new RegularExpression("recipient_type|SlipDistribRecipientScheduleView-\\d+\\.rcpntType", false), 0);
	}

	/**
	 * Select Recipient by given value
	 * 
	 * @param value
	 */
	public void selectRecipient(String value) {
//		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage.getInstance();
		if (null != value && !"".equals(value)) {
			if (!value.equalsIgnoreCase("All")) {
				browser.selectRadioButton(".id", new RegularExpression("recipient_ref_id|SlipDistribRecipientScheduleView-\\d+\\.allOrSpecRecpt", false), 
						".value", new RegularExpression("specific|SPECIFIC_RECIPIENT", false));
				// commented by Jane: Move the wait findLocationPage in your own keyword
//				browser.waitExists(findLocPg);// add by pzhu
			} else {
				browser.selectRadioButton(".id", new RegularExpression("recipient_ref_id|SlipDistribRecipientScheduleView-\\d+\\.allOrSpecRecpt", false),  
						".value", new RegularExpression("0|ALL_RECIPIENTS", false));
			}
		}
	}

	/**
	 * 
	 * @return selected recipient
	 */
	public String getRecipient() {
		IHtmlObject[] objs = browser.getRadioButton(".id", new RegularExpression("recipient_ref_id|SlipDistribRecipientScheduleView-\\d+\\.allOrSpecRecpt", false));
		String value = "";

		for (IHtmlObject o : objs) {
			if (((IRadioButton) o).isSelected()) {
				value = o.getProperty(".value").toString();
			}
		}
		Browser.unregister(objs);
		if (value.equalsIgnoreCase("0") || value.equalsIgnoreCase("ALL_RECIPIENTS")) {
			return "All";
		} else {
			String temp = parseDetailTable();
			return temp.substring(
					temp.indexOf("Name/Code") + "Name/Code".length(),
					temp.indexOf("Recipient Permit")).trim();
		}
	}

	/**
	 * Select Recipient Permit by given value
	 * 
	 * @param value
	 */
	public void selectRecipientPermit(String value) {
//		FinMgrRecipientScheduleFindPermitPage findPermitPg = FinMgrRecipientScheduleFindPermitPage.getInstance();
		if (null != value && !"".equals(value)) {
			if (!value.equalsIgnoreCase("All")) {
				browser.selectRadioButton(".id", new RegularExpression("recipient_permit_id|SlipDistribRecipientScheduleView-\\d+\\.allOrSpecRcptPermit", false), 
						".value", new RegularExpression("specific|SPECIFIC_RECPT_PERMIT", false));
//				findPermitPg.waitExists();
				ajax.waitLoading();
			} else {
				browser.selectRadioButton(".id", new RegularExpression("recipient_permit_id|SlipDistribRecipientScheduleView-\\d+\\.allOrSpecRcptPermit", false), 
						".value", new RegularExpression("0|ALL_RECPT_PERMITS", false));
			
			}
		}
	}

	/**
	 * 
	 * @return selected recipient permit
	 */
	public String getRecipientPermit() {
		IHtmlObject[] objs = browser.getRadioButton(".id", new RegularExpression("recipient_permit_id|SlipDistribRecipientScheduleView-\\d+\\.allOrSpecRcptPermit", false));
		String value = "";

		for (IHtmlObject o : objs) {
			if (((IRadioButton) o).isSelected()) {
				value = o.getProperty(".value").toString();
			}
		}
		Browser.unregister(objs);

		if (value.equalsIgnoreCase("0") || value.equalsIgnoreCase("ALL_RECPT_PERMITS")) {
			return "All";
		} else {
			String temp = parseDetailTable();
			temp = temp.substring(temp.indexOf("Recipient Permit ID")
					+ "Recipient Permit ID".length());
			return temp.substring(0, temp.indexOf("Revenue Location")).trim();
		}
	}

	/**
	 * Select Product category from drop down list
	 * 
	 * @param prdCategory
	 */
	public void selectPrdCategory(String prdCategory) {
		browser.selectDropdownList(".id", new RegularExpression("prd_grp_cat_id|SlipDistribRecipientScheduleView-\\d+\\.prdCat", false), prdCategory);
		waitLoading();
	}

	/**
	 * 
	 * @return selected product category
	 */
	public String getPrdCategory() {
		return browser.getDropdownListValue(".id", new RegularExpression("prd_grp_cat_id|SlipDistribRecipientScheduleView-\\d+\\.prdCat", false), 0);
	}
	
	public void selectAppPrdCategory(String appPrdCategory){
		browser.selectDropdownList(".id", "assignment_applicablePrdCat", appPrdCategory);
	}

	/**
	 * Select product group by given group
	 * 
	 * @param group
	 */
	public void selectPrdGrp(String group) {
		browser.selectDropdownList(".id", new RegularExpression("assignment_prodgroup|SlipDistribRecipientScheduleView-\\d+\\s.prdGrp", false), group);
		waitLoading();
	}
	
	/**
	 * 
	 * @return selected product group
	 */
	public String getPrdGrp() {
		return browser.getDropdownListValue(".id", new RegularExpression("assignment_prodgroup|SlipDistribRecipientScheduleView-\\d+\\.prdGrp", false), 0);
	}

	/**
	 * Select product by given product
	 * 
	 * @param product
	 */
	public void selectProduct(String product) {
		if (null != product && !"".equals(product)) {
			if (!product.equalsIgnoreCase("All")) {
				browser.selectRadioButton(".id", new RegularExpression("select_product|SlipDistribRecipientScheduleView-\\d+\\.allOrSpecProd", false),  
						".value", new RegularExpression("specific|SPECIFIC_PROD", false));
				ajax.waitLoading();
				waitLoading();
				browser.selectDropdownList(".id", new RegularExpression("assignment_product|SlipDistribRecipientScheduleView-\\d+\\.product", false), product);
				waitLoading();
			} else {
				browser.selectRadioButton(".id", new RegularExpression("select_product|SlipDistribRecipientScheduleView-\\d+\\.allOrSpecProd", false), 
						".value", new RegularExpression("all|ALL_PRODUCTS", false));
			}
		}
	}

	/**
	 * 
	 * @return selected product
	 */
	public String getProduct() {
		IHtmlObject[] objs = browser.getRadioButton(".id", new RegularExpression("select_product|SlipDistribRecipientScheduleView-\\d+\\.allOrSpecProd", false));
		String value = "";

		for (IHtmlObject o : objs) {
			if (((IRadioButton) o).isSelected()) {
				value = o.getProperty(".value").toString();
			}
		}
		Browser.unregister(objs);
		if (value.equalsIgnoreCase("all") || value.equalsIgnoreCase("ALL_PRODUCTS")) {
			return "All";
		}else {
			return browser.getDropdownListValue(".id", new RegularExpression("assignment_product|SlipDistribRecipientScheduleView-\\d+\\.product", false), 0);
		}
	}

	/**
	 * Input effective date
	 * 
	 * @param date
	 */
	public void setEffectDate(String date) {
		browser.setTextField(".id", new RegularExpression("effective_date_ForDisplay|SlipDistribRecipientScheduleView\\.effectiveDate_ForDisplay", false), date, 0, IText.Event.LOSEFOCUS);
//		browser.clickGuiObject(".class", "Html.TD", ".text","Effective Date");
	}

	/**
	 * 
	 * @return effective date
	 */
	public String getEffectDate() {
		return browser.getTextFieldValue(".id", new RegularExpression("effective_date_ForDisplay|SlipDistribRecipientScheduleView\\.effectiveDate_ForDisplay", false));
	}

	/**
	 * Select distribution type from drop down list
	 * 
	 * @param type
	 */
	public void selectDistributionType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("distribution_type_id|SlipDistribRecipientScheduleView-\\d+\\.distributionType", false), type);
		waitLoading();
	}

	/**
	 * 
	 * @return selected distribution type
	 */
	public String getDistributionType() {
		return browser.getDropdownListValue(".id", new RegularExpression("distribution_type_id|SlipDistribRecipientScheduleView-\\d+\\.distributionType", false), 0);
	}

	/**
	 * Select transaction type from drop down list
	 * 
	 * @param transType
	 */
	public void selectTransType(String transType) {
		browser.selectDropdownList(".id", new RegularExpression("transaction_type|SlipDistribRecipientScheduleView-\\d+\\.transactionType", false), transType);
		waitLoading();
	}

	/**
	 * 
	 * @return selected transaction type
	 */
	public String getTransType() {
		return browser.getDropdownListValue(".id", new RegularExpression("transaction_type|SlipDistribRecipientScheduleView-\\d+\\.transactionType", false), 0);
	}

	/**
	 * Select Transaction Occurrence from drop down list
	 * 
	 * @param transOcc
	 */
	public void selectTransOcc(String transOcc) {
		browser.selectDropdownList(".id", new RegularExpression("transaction_occurrence|SlipDistribRecipientScheduleView\\.transactionOCC", false), transOcc);
		waitLoading();
	}

	/**
	 * 
	 * @return selected transaction Occurrence
	 */
	public String getTransOcc() {
		if(isTransOccurEnabled())
			return browser.getDropdownListValue(".id", new RegularExpression("transaction_occurrence|SlipDistribRecipientScheduleView\\.transactionOCC", false), 0);
		else
			return StringUtil.EMPTY;
	}

	/**
	 * Select sale channel from drop down list
	 * 
	 * @param saleChannel
	 */
	public void selectSalesChannel(String saleChannel) {
		browser.selectDropdownList(".id", new RegularExpression("sales_channel|SlipDistribRecipientScheduleView-\\d+\\.salesChannel", false), saleChannel);
		waitLoading();
	}

	/**
	 * 
	 * @return selected sale channel
	 */
	public String getSaleChannel() {
		return browser.getDropdownListValue(".id", new RegularExpression("sales_channel|SlipDistribRecipientScheduleView-\\d+\\.salesChannel", false), 0);
	}

	/**
	 * Select ticket category from drop down list
	 * 
	 * @param ticketCategory
	 */
	public void selectTicketCategory(String ticketCategory) {
		browser.selectDropdownList(".id", "sales_category", ticketCategory);
		waitLoading();
	}

	/**
	 * Select apply rate from radio button
	 * 
	 * @param appRate
	 */
	public void selectApplyRate(String appRate) {
		if (appRate.equalsIgnoreCase("Per Order Item")) {
			browser.selectRadioButton(".id", "fee_target", ".value", "2");
		} else if (appRate.equalsIgnoreCase("Per Order")) {
			browser.selectRadioButton(".id", "fee_target", ".value", "1");
		} else {
			throw new ItemNotFoundException("Apply Rate " + appRate
					+ " is not correct");
		}
	}

	/**
	 * Select ticket type form drop down list
	 * 
	 * @param ticketType
	 */
	public void selectTicketType(String ticketType) {
		IHtmlObject[] objs = browser.getDropdownList(".id", "ticket_type");
		ISelect dropdownList = (ISelect)objs[objs.length-1];
		dropdownList.select(ticketType);
		Browser.unregister(objs);
	}

	/**
	 * Select distribution balance
	 */
	public void selectDistributionBalance(int index) {
		browser.selectCheckBox(".id", "distribute_balance", index);
	}

	/**
	 * Select unit from drop down list
	 * 
	 * @param unit
	 */
	public void selectUnit(String unit) {
		if(isUnitEnabled()){
			browser.selectDropdownList(".id", new RegularExpression("distribution_unit|SlipDistribRecipientScheduleView-\\d+\\.distributionUnit", false), unit);
		}
		ajax.waitLoading();
	}
	
	/**
	 * 
	 * @return selected units
	 */
	public String getUnit() {
		return browser.getDropdownListValue(".id", new RegularExpression("distribution_unit|SlipDistribRecipientScheduleView-\\d+\\.distributionUnit", false), 0);
	}

	/**
	 * Input distribution rate
	 * 
	 * @param rate
	 */
	public void setRate(String rate) {
		this.setRate(rate, 0);
	}

	public void setRate(String rate, int index) {
		browser.setTextField(".id", new RegularExpression("distribution_rate|SlipDistribRecipientScheduleView\\.rate", false), rate, index);
	}

	/**
	 * 
	 * @return distribution rate
	 */
	public String getRate() {
		return browser.getTextFieldValue(".id", new RegularExpression("distribution_rate|SlipDistribRecipientScheduleView\\.rate", false));
	}

	/**
	 * This method used to verify Schedule Detail
	 * 
	 * @param schedule
	 */
	public void verifyScheduleDetail(ScheduleData schedule) {
		logger.info("Start to Verify Schedule Detail Info.");

		if (!schedule.scheduleId.equalsIgnoreCase(getRecipientSchID())) {
			throw new ItemNotFoundException("Recipient Schedule Id "
					+ getRecipientSchID() + " not correct.");
		}
		if (!schedule.coverage.equalsIgnoreCase(getCoverage())) {
			throw new ItemNotFoundException("Distribution Coverage "
					+ getCoverage() + " not correct.");
		}
		if (!schedule.revenueLocation.equalsIgnoreCase(getLocation())) {
			throw new ItemNotFoundException("Revenue Location " + getLocation()
					+ " not correct.");
		}
		if (!schedule.recipientType.equalsIgnoreCase(getRecipientType())) {
			throw new ItemNotFoundException("Recipient Type "
					+ getRecipientType() + " not correct.");
		}
		if (!schedule.recipient.equalsIgnoreCase(getRecipient())) {
			throw new ItemNotFoundException("Recipient " + getRecipient()
					+ " not correct.");
		}
		if (!schedule.recipientPermit.equalsIgnoreCase(getRecipientPermit())) {
			throw new ItemNotFoundException("Recipient permit "
					+ getRecipientPermit() + " not correct.");
		}
		if (!schedule.productCategory.equalsIgnoreCase(getPrdCategory())) {
			throw new ItemNotFoundException("Product Category "
					+ getPrdCategory() + " not correct.");
		}
		if (!schedule.productGroup.equalsIgnoreCase(getPrdGrp())) {
			throw new ItemNotFoundException("Product Group " + getPrdGrp()
					+ " not correct.");
		}
		if (!schedule.product.equalsIgnoreCase(getProduct())) {
			throw new ItemNotFoundException("Product " + getProduct()
					+ " not correct.");
		}
		if (!schedule.effectiveDate.equalsIgnoreCase(getEffectDate())) {
			throw new ItemNotFoundException("Effective Date " + getEffectDate()
					+ " not correct.");
		}
		if (!schedule.distributionType.equalsIgnoreCase(getDistributionType())) {
			throw new ItemNotFoundException("Distribution Type "
					+ getDistributionType() + " not correct.");
		}
		if (!schedule.tranType.equalsIgnoreCase(getTransType())) {
			throw new ItemNotFoundException("Transaction Type "
					+ getTransType() + " not correct.");
		}
		if (!schedule.tranOccur.equalsIgnoreCase(getTransOcc())) {
			throw new ItemNotFoundException("Transaction Occurrence "
					+ getTransOcc() + " not correct.");
		}
		if (!schedule.salesChannel.equalsIgnoreCase(getSaleChannel())) {
			throw new ItemNotFoundException("Sales Channel " + getSaleChannel()
					+ " not correct.");
		}
		if (!schedule.unit.equalsIgnoreCase(getUnit())) {
			throw new ItemNotFoundException("Unit " + getUnit()
					+ " not correct.");
		}
		if (!schedule.rate.equalsIgnoreCase(getRate())) {
			throw new ItemNotFoundException("Rate " + getRate()
					+ " not correct.");
		}
	}

	/**
	 * This method used to parse recipient schedule detail table
	 * 
	 * @return all text for this table
	 */
	public String parseDetailTable() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Distribution Coverage Coverage.*",
						false));
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);

		return text;
	}

	/**
	 * Click Apply button
	 * 
	 */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**
	 * Click OK button
	 * 
	 */
	public void clickOk() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**
	 * 
	 * @return new added Recipient schedule Id
	 */
	public String getRecipientSchID() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Update Schedule.*", false));
		String scheduleId = ((IHtmlTable) objs[1]).getCellValue(0, 1).split(
				"Distribution Recipient Schedule ID")[1].trim();
		Browser.unregister(objs);
		return scheduleId;
	}

	public boolean recipientSchIDExists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("^Update Schedule.*", false));
	}

	public String getErrorMessage() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id",
				new RegularExpression(
						"(NOTSET|distribution\\.recipient\\.error\\.*)|(msg\\.distribution\\.recipient.*)", false));
		String errorMessage = objs[0].getProperty(".text");

		Browser.unregister(objs);
		return errorMessage;
	}
	
	public void verifyErrorMessage(String errorMessage) {
		logger.info("Verify the error message");
		String actualMessage =this.getErrorMessage();
		if (!actualMessage.equals(errorMessage)) {
			throw new ErrorOnPageException("Error Message not correct", errorMessage,actualMessage);
		}
	}
	
	public List<String> getAllDistributionItemType() {
		return browser.getDropdownElements(".id", new RegularExpression("distribution_type_id|SlipDistribRecipientScheduleView-\\d+\\.distributionType", false));
	}
	
	public List<String> getAllUnit() {
		return browser.getDropdownElements(".id", new RegularExpression("SlipDistribRecipientScheduleView-\\d+\\.distributionUnit", false)); 
	}
	
	public boolean isUnitEnabled() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.Select", ".id", new RegularExpression("distribution_unit|SlipDistribRecipientScheduleView-\\d+\\.distributionUnit", false));
		String isDisable = objs[0].getProperty("isDisabled");
		Browser.unregister(objs);
		return !Boolean.parseBoolean(isDisable);
	}
	
	public List<String> getAllMarinaRateType() {
		return browser.getDropdownElements(".id", new RegularExpression("SlipDistribRecipientScheduleView-\\d+\\.marinaRateType", false));
	}
	
	public List<String> getAllSalesChannel() {
		return browser.getDropdownElements(".id", new RegularExpression("sales_channel|SlipDistribRecipientScheduleView-\\d+\\.salesChannel", false));
	}
	
	public String getApplicablePrdCategory() {
		return browser.getDropdownListValue(".id", new RegularExpression("assignment_applicablePrdCat", false), 0);
	}
	
	public String getMarinaRateType() {
		return browser.getDropdownListValue(".id", new RegularExpression("SlipDistribRecipientScheduleView-\\d+\\.marinaRateType", false), 0);
	}
	
	public void selectMarinaRateType(String marinaRateType) {
		browser.selectDropdownList(".id", new RegularExpression("SlipDistribRecipientScheduleView-\\d+\\.marinaRateType", false), marinaRateType);
	}
	
	public boolean isTransOccurEnabled() {
		return browser.checkHtmlObjectEnabled(".class", "Html.Select", ".id", new RegularExpression("transaction_occurrence|SlipDistribRecipientScheduleView\\.transactionOCC", false));
	}
}
