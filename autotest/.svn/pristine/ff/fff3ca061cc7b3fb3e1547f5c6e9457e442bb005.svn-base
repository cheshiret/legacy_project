/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.ChoiceFee;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date Jul 20, 2011
 */
public abstract class LicMgrProductPricingCommonWidget extends DialogWidget {

	protected final RegularExpression statusRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.active", false);

	protected final RegularExpression locClassRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.locationClassID", false);

	protected final RegularExpression licYearFromRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.licenseYearFrom", false);

	protected final RegularExpression licYearToRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.licenseYearTo", false);

	protected final RegularExpression effectiveFromDateRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.effectiveStartDate_ForDisplay", false);

	protected final RegularExpression effectiveToDateRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.effectiveEndDate_ForDisplay", false);

	protected final RegularExpression vendorFeeRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.vendorFee", false);

	protected final RegularExpression stateTranFeeRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.stateTransFee", false);

	protected final RegularExpression stateVendorFeeRegx = new RegularExpression("ProductPricingView-\\d+\\.stateVendorFee", false);
	
	protected final RegularExpression stateFeeRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.stateFee", false);

	protected final RegularExpression transFeeRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.transFee", false);

	protected final RegularExpression stateFee_splitByRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.feeSchdSplitType", false);

	protected final RegularExpression transFee_splitByRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.transFeeSchdSplitType", false);

	protected final RegularExpression stateFeeSplitIntoRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.feeSchdacctSplitNum", false);

	protected final RegularExpression transactionFeeSplitIntoRegex = new RegularExpression(
			"ProductPricingView-\\d+\\.transFeeSchdacctSplitNum", false);

	protected final RegularExpression accountRegex = new RegularExpression(
			"FeeScheduleSplitView-\\d+\\.account", false);

	protected final RegularExpression accountVelueRegex = new RegularExpression(
			"FeeScheduleSplitView-\\d+\\.value", false);

	protected final RegularExpression purchaseTypeRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.purchaseType", false);

	protected final RegularExpression stateFee_SplitIntoRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.feeSchdacctSplitNum", false);

	protected final RegularExpression transFee_SplitIntoRegx = new RegularExpression(
			"ProductPricingView-\\d+\\.transFeeSchdacctSplitNum", false);

	protected final RegularExpression keepPreviousPriceDuringChangeIfHigherRegx = new RegularExpression("HFProductPricingDetailUI-\\d+\\.keptPreviousPrice", false);
	protected final RegularExpression calculateHoldingFeeBasedOnSelectedChoicesRegx = new RegularExpression("HFProductPricingDetailUI-\\d+\\.basedOnChoices", false);
	protected final RegularExpression holdingFeeRegx = new RegularExpression("ProductPricingView-\\d+\\.holdingFee", false);
	protected final RegularExpression holdingFeeAccountRegx = new RegularExpression("ProductPricingView-\\d+\\.holdingFeeAccnt", false);
	
	protected Property[] choiceRangeField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression("PrivilegeLotteryProductPricingFeesBean-\\d+\\.range", false));
	}
	
	protected Property[] choiceVendorFeeField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression("PrivilegeLotteryProductPricingFeesBean-\\d+\\.vendorFee", false));
	}
	
	protected Property[] choiceStateFeeField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression("PrivilegeLotteryProductPricingFeesBean-\\d+\\.stateFee", false));
	}
	
	protected Property[] choiceTransFeeField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression("PrivilegeLotteryProductPricingFeesBean-\\d+\\.transFee", false));
	}
	
	protected Property[] addRangeLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Add Range");
	}
	
	protected Property[] vendorFeeLabel() {
		return Property.concatPropertyArray(this.label(), ".for", vendorFeeRegx);
	}

	protected Property[] stateFeeLabel() {
		return Property.concatPropertyArray(this.label(), ".for", stateFeeRegx);
	}

	protected Property[] transFeeLabel() {
		return Property.concatPropertyArray(this.label(), ".for", transFeeRegx);
	}

	protected Property[] stateTransFeeLabel() {
		return Property.concatPropertyArray(this.label(), ".for", stateTranFeeRegx);
	}

	protected Property[] stateVendorFeeLabel() {
		return Property.concatPropertyArray(this.label(), ".for", stateVendorFeeRegx);
	}

	protected Property[] holdingFeeLabel() {
		return Property.concatPropertyArray(this.label(), ".for", holdingFeeRegx);
	}
	
	protected LicMgrProductPricingCommonWidget(String title) {
		super(title);
	}

	public String getEffectiveFromDate() {
		return browser.getTextFieldValue(".id", this.effectiveFromDateRegx);
	}

	public String getStatus() {
		return browser.getDropdownListValue(".id", this.statusRegx, 0);
	}

	public void selectLicenseYearFrom(String year) {
		browser.selectDropdownList(".id", this.licYearFromRegx, year);
	}

	public void selectLicenseYearTo(String year) {
		browser.selectDropdownList(".id", this.licYearToRegx, year);
	}

	public void setEffectiveFromDate(String fromDate) {
		browser.setTextField(".id", this.effectiveFromDateRegx, fromDate);
	}

	public void setEffectiveToDate(String toDate) {
		browser.setTextField(".id", this.effectiveToDateRegx, toDate);
	}

	public void setVendorFee(String fee) {
		browser.setTextField(".id", this.vendorFeeRegx, fee);
	}

	public void setStateTransFee(String fee) {
		browser.setTextField(".id", this.stateTranFeeRegx, fee);
	}

	public void setStateVendorFee(String fee) {
		browser.setTextField(".id", stateVendorFeeRegx, fee);
	}
	
	public void setStateFee(String fee) {
		browser.setTextField("id", this.stateFeeRegx, fee, 0);
	}

	public void setTransFee(String fee) {
		browser.setTextField("id", this.transFeeRegx, fee, 0);
	}

	public void selectSplitByForStateFee(String byWhat) {
		setSplitBy(byWhat, true);
	}

	public void selectSplitByForTransFee(String byWhat) {
		setSplitBy(byWhat, false);
	}

	public void selectSplitIntoForStateFee(String num) {
		browser.selectDropdownList(".id", this.stateFee_SplitIntoRegx, num);
	}

	public List<String> getAccountsForStateFee() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"FeeScheduleSplitGrid");
		Property[] property = new Property[] { new Property(".id",
				this.accountRegex) };

		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"can't find table with id=FeeScheduleSplitGrid");
		}
		List<String> list=browser.getDropdownElements(property, objs[0]);
		Browser.unregister(objs);
		return list;
	}

	public List<String> getAccountsForTransFee() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"transFeeScheduleSplitGrid");
		Property[] property = new Property[] { new Property(".id",
				this.accountRegex) };

		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't fint table with id=transFeeScheduleSplitGrid");
		}

		List<String> list=browser.getDropdownElements(property,objs[0]);
		Browser.unregister(objs);
		return list;
	}

	public void selectSplitIntoForTransFee(String num) {
		browser.selectDropdownList(".id", this.transFee_SplitIntoRegx, num);
	}

	/**
	 * select Account and value for State fee
	 * 
	 * @param account
	 * @param value
	 *            value for current account
	 * @param index
	 */
	public void selectAccountForStateFee(String account, String value, int index) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"FeeScheduleSplitGrid");
		Property[] property = new Property[] { new Property(".id",
				this.accountRegex) };

		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"can't find table with id=FeeScheduleSplitGrid");
		}
		browser.selectDropdownList(property, account, true, index, objs[0]);
		IHtmlObject[] textFields = browser.getTextField(
				new Property[] { new Property(".id", this.accountVelueRegex) },
				objs[0]);
		if (index < textFields.length - 1) {
			browser.setTextField(".id", this.accountVelueRegex, value, false,
					index, objs[0]);
		}

		Browser.unregister(objs);
		Browser.unregister(textFields);
	}

	public String getSumOfAccountValueForStateFee() {
		double sum = 0d;
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"FeeScheduleSplitGrid");
		Property[] property = new Property[] {new Property(".id",
				this.accountVelueRegex) };

		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"can't find table with id=FeeScheduleSplitGrid");
		}
		
		IHtmlObject[] textFieldObjs=browser.getTextField(property,objs[0]);
		if(textFieldObjs.length<1){
			throw new ObjectNotFoundException("Can't find account value text field for state fee.");

		}

		for(int i=0;i<textFieldObjs.length;i++){
			sum+=Double.parseDouble(browser.getTextFieldValue(new Property[]{new Property(".id", this.accountVelueRegex)},i,objs[0]).trim());

		}

		ajax.waitLoading();
		Browser.unregister(objs);
		Browser.unregister(textFieldObjs);
		return String.valueOf(sum);
	}

	public String getSumOfAccountValueForTransFee() {
		double sum = 0d;
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"transFeeScheduleSplitGrid");
		Property[] property = new Property[] { new Property(".id",
				this.accountVelueRegex) };

		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"can't find table with id=FeeScheduleSplitGrid");
		}

		IHtmlObject[] textFeilds = browser.getTextField(property, objs[0]);
		if (textFeilds.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find account value text field for state fee...");
		}
		for (int i = 0; i < textFeilds.length; i++) {
			sum += Double.parseDouble(browser
					.getTextFieldValue(
							new Property[] { new Property(".id",
									this.accountVelueRegex) }, i, objs[0])
					.trim());
		}

		ajax.waitLoading();
		Browser.unregister(objs);
		return String.valueOf(sum);
	}

	/**
	 * select account and value for transaction fee
	 * 
	 * @param account
	 * @param value
	 * @param index
	 */
	public void selectAccountForTransFee(String account, String value, int index) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"transFeeScheduleSplitGrid");
		Property[] property = new Property[] { new Property(".id",
				this.accountRegex) };

		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't fint table with id=transFeeScheduleSplitGrid");
		}
		ajax.waitLoading();
		browser.selectDropdownList(property, account, true, index, objs[0]);
		IHtmlObject[] textFields = browser.getTextField(
				new Property[] { new Property(".id", this.accountVelueRegex) },
				objs[0]);
		if (index < textFields.length - 1) {
			browser.setTextField(".id", this.accountVelueRegex, value, true,
					index, objs[0]);
		}
		Browser.unregister(objs);
		Browser.unregister(textFields);
	}
	
	/**
	 * set split by for State fee/transaction fee
	 * 
	 * @param byWhat
	 * @param index
	 *            0-state fee; 1-transaction fee
	 * 
	 */
	private void setSplitBy(String byWhat, boolean isStatefee) {
		Property[] property = new Property[] { new Property(".id",
				isStatefee ? this.stateFee_splitByRegx
						: this.transFee_splitByRegx) };

		if (byWhat.equalsIgnoreCase("Percent")) {
			browser.selectRadioButton(property, true, 0);
		} else if (byWhat.equalsIgnoreCase("Amount")) {
			browser.selectRadioButton(property, true, 1);
		}
	}

	public boolean isChoiceRangeExist(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(this.choiceRangeField());
		boolean result = objs.length > index;
		Browser.unregister(objs);
		return result;
	}
	
	public void setChoiceRange(String range, int index) {
		browser.setTextField(this.choiceRangeField(), range, index);
	}
	
	public void setChoiceVendorFee(String fee, int index) {
		browser.setTextField(this.choiceVendorFeeField(), fee, index);
	}
	
	public void setChoiceStateFee(String fee, int index) {
		browser.setTextField(this.choiceStateFeeField(), fee, index);
	}
	
	public void setChoiceTransFee(String fee, int index) {
		browser.setTextField(this.choiceTransFeeField(), fee, index);
	}
	
	public void setChoiceFee(ChoiceFee fee, int index) {
		if (this.isChoiceRangeExist(index)) {
			this.setChoiceRange(fee.range, index);
		}
		this.setChoiceVendorFee(fee.vendorFee, index);
		this.setChoiceStateFee(fee.stateFee, index);
		this.setChoiceTransFee(fee.transFee, index);
	}
	
	public void clickAddRange() {
		browser.clickGuiObject(addRangeLink());
	}
	public boolean isKeepPreviousPriceDuringChangeIfHigherExists() {
		return browser.checkHtmlObjectExists(".id", keepPreviousPriceDuringChangeIfHigherRegx);
	}
	
	public void selectKeepPreviousPriceDuringChangeIfHigher(boolean yes) {
		browser.selectRadioButton(".id", keepPreviousPriceDuringChangeIfHigherRegx, yes ? 0 : 1);
	}
	
	public boolean isCalculateHoldingFeeBasedOnSelectedChoicesExists() {
		return browser.checkHtmlObjectExists(".id", calculateHoldingFeeBasedOnSelectedChoicesRegx);
	}
	
	public void selectCalculateHoldingFeeBasedOnSelectedChoices(boolean yes) {
		browser.selectRadioButton(".id", calculateHoldingFeeBasedOnSelectedChoicesRegx, yes ? 0 : 1);
	}
	
	public void setHoldingFee(String fee) {
		browser.setTextField(".id", holdingFeeRegx, fee);
	}
	
	public void selectHoldingFeeAccount(String account) {
		browser.selectDropdownList(".id", holdingFeeAccountRegx, account);
	}
	
	public void selectHoldingFeeAccount(int index) {
		browser.selectDropdownList(".id", holdingFeeAccountRegx, index);
	}
	
	public boolean isHoldingFeeAccountExist() {
		return browser.checkHtmlObjectExists(".id", holdingFeeAccountRegx);
	}
	
	/**
	 * Get the error message displayed at the top of page
	 * 
	 * @return
	 */
	public String getErrorMsg() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id",
				"NOTSET");
		String msg = "";
		if (objs.length > 0) {
			msg = objs[0].getProperty(".text").trim();
		}

		Browser.unregister(objs);
		return msg;
	}

	public void verifyEffectiveFromDateInValid(String expectMsg) {
		boolean isvaild = verifyAutomaticDateCorrection((IText)browser.getTextField(".id", this.effectiveFromDateRegx)[0], OrmsConstants.INVALID_DATES);

		if (!isvaild) {
			if (!expectMsg.equals(this.getErrorMsg())) {
				throw new ErrorOnPageException("The right message should be '"
						+ expectMsg + "'");
			}
		}
	}

	public void verifyEffectiveToDateInvalid(String expectMsg) {
		boolean isvaild = verifyAutomaticDateCorrection((IText)browser.getTextField(".id", effectiveToDateRegx)[0], OrmsConstants.INVALID_DATES);
		if (!isvaild) {
			if (!expectMsg.equals(this.getErrorMsg())) {
				throw new ErrorOnPageException("The right message should be '"
						+ expectMsg + "'");
			}
		}
	}
	
	/**
	 * Verify whether the Location Class's options are displayed with the "All" option as the first option in the list,
	 * followed by the Location Classes defined for the Contract sorted by the code in ascending order
	 */
	public void verifyLocationClassDropdownList() {
		List<String> options=browser.getDropdownElements(".id", this.locClassRegx);
		if(!options.get(0).equalsIgnoreCase("All")) {
			throw new ErrorOnPageException("The first option should be 'All'.");
		}
		options.remove(0);
		for(int i = 0; i < options.size() - 1; i++) {
			if(Integer.parseInt(options.get(i).split("-")[0].trim()) > Integer.parseInt(options.get(i + 1).split("-")[0].trim())) {
				throw new ErrorOnPageException("Location Class should be sorted in Ascending order.");
			}
		}
	}
	
	public void verifyLicenseYearFrom(){
		List<String> options=browser.getDropdownElements(".id", this.licYearFromRegx);
		List<String> newOptions=new ArrayList<String>();
        int year=DateFunctions.getCurrentYear();
		if(!options.get(0).equalsIgnoreCase("ALL")){
			throw new ErrorOnPageException("The first option of License year should be 'ALL'");
		}
		options.remove(0);
		Collections.sort(options);
		
		for(int i=0;i<10;i++){
			newOptions.add(String.valueOf(year));
			year++;
		}
		
		for(int i=0;i<options.size();i++){
			if(!options.get(i).equals(newOptions.get(i))){
				throw new ErrorOnPageException("Those options in license year from should sorted in ASC");
			}
		}
	}
	
	public void verifyLicenseYearTo(){
		this.selectLicenseYearFrom(String.valueOf(DateFunctions.getCurrentYear()));
		ajax.waitLoading();
		int year=DateFunctions.getCurrentYear();
		List<String> options=browser.getDropdownElements(".id", this.licYearToRegx);
		List<String> newOptions=new ArrayList<String>();
	
		for(int i=0;i<10;i++){
			newOptions.add(String.valueOf(year));
			year++;
		}
		Collections.sort(options);
		for(int i=0;i<options.size();i++){
			if(!options.get(i).equals(newOptions.get(i))){
				throw new ErrorOnPageException("Those options in license year from should sorted in ASC");
			}
		}
	}
	
	public boolean isAccountsForStateFeeSortedByASC(){
		List<String> list=this.getAccountsForStateFee();
		List<String> listForSort=new ArrayList<String>();
//		for(String account:list){
//			listForSort.add(account.split("-")[0].trim());
//		}
		listForSort.addAll(list);
		Collections.sort(listForSort);
//		for(int i=0;i<list.size();i++){
//			if(!list.get(i).matches(listForSort.get(i)+"(\\s)?-.*")){
//				return false;
//			}
//		}
		return list.equals(listForSort);
	}
	
	public boolean isAccountsForTransFeeSortedByASC(){
		List<String> list=this.getAccountsForTransFee();
		List<String> listForSort=new ArrayList<String>();
//		for(String account:list){
//			listForSort.add(account.split("-")[0].trim());
//		}
		listForSort.addAll(list);
		Collections.sort(listForSort);
//		for(int i=0;i<list.size();i++){
//			if(!list.get(i).matches(listForSort.get(i)+"-.*")){
//				return false;
//			}
//		}
		return list.equals(listForSort);
	}
	
	public String getVendorFeeLabel() {
		return browser.getObjectText(this.vendorFeeLabel());
	}

	public String getStateFeeLabel() {
		return browser.getObjectText(this.stateFeeLabel());
	}

	public String getTransFeeLabel() {
		return browser.getObjectText(this.transFeeLabel());
	}

	public String getStateTransFeeLabel() {
		return browser.getObjectText(this.stateTransFeeLabel());
	}

	public String getStateVendorFeeLabel() {
		return browser.getObjectText(this.stateVendorFeeLabel());
	}

	public String getHoldingFeeLabel() {
		return browser.getObjectText(this.holdingFeeLabel());
	}
	
	
}
