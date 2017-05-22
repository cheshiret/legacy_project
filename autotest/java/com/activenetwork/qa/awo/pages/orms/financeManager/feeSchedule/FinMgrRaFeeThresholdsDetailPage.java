/*
 * Created on Dec 23, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author Ssong
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrRaFeeThresholdsDetailPage extends FinanceManagerPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRaFeeThresholdsDetailPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrRaFeeThresholdsDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrRaFeeThresholdsDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrRaFeeThresholdsDetailPage();
		}

		return _instance;
	}

	/**
	 * return whether the page mark display in the page
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"RA Fee Threshold Schedule Details");
	}

	/**
	 * Select Product Category from drop down list
	 * 
	 * @param category
	 */
	public void selectProdCategory(String category) {
		browser.selectDropdownList(".id", new RegularExpression("(prd_grp_cat_id)|(SlipRAFeeThresholdScheduleView-\\d+\\.prdGrpCat)",false), category);
	}

	/**
	 * Select assignment loop from drop down list
	 * 
	 * @param loop
	 */
	public void selectAssignmentLoop(String loop) {
		browser.selectDropdownList(".id", "assignment_loop", loop);
	}
	
	public void selectAssignmentDock(String dock) {
		browser.selectDropdownList(".id", new RegularExpression("SlipRAFeeThresholdScheduleView-\\d+\\.dockId",false), dock);
	}

	public void selectDockAreaVenue(String value)
	{
		this.selectAssignmentDock(value);
		ajax.waitLoading();
		this.waitLoading();
	}
	/**
	 * Select product group from drop down list
	 * 
	 * @param group
	 */
	public void selectProductGroup(String group) {
		browser.selectDropdownList(".id", new RegularExpression("(assignment_prodgroup)|(SlipRAFeeThresholdScheduleView-\\d+\\.productGroupId)",false), group);
	}

	/**
	 * Select product from drop down list
	 * 
	 * @param product
	 */
	public void selectProduct(String product) {
		browser.selectDropdownList(".id", new RegularExpression("(assignment_product)|(SlipRAFeeThresholdScheduleView-\\d+\\.prdID)",false), product);
	}

	public void selectProductAndWait(String product)
	{
		this.selectProduct(product);
		ajax.waitLoading();
		this.waitLoading();
	}
	/**
	 * Input effective date value
	 * 
	 * @param date
	 */
	public void setEffectiveDate(String date) {
		browser.setTextField(".id", new RegularExpression("(date_effective_ForDisplay)|(SlipRAFeeThresholdScheduleView-\\d+\\.effectDate_ForDisplay)",false), date);
	}
	
	public void clickEffectiveLabel(){
		browser.clickGuiObject(".class", "Html.LABEL",".text","Effective");
	}

	
	public boolean isFeeIdDisable(String id)
	{
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", id);
	}
	
	public boolean isLocationDisable(String location)
	{
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", new RegularExpression(location,false));
	}
	
	public boolean isProductCategoryDisable()
	{
		return (1 == this.getProdCategoryElements().size());
	}

	public boolean isCurrentCounterDisable()
	{
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text", ".id", new RegularExpression("SlipRAFeeThresholdScheduleView-\\d+\\.currentCounter",false));
		boolean result = false;
		if(objs.length>0)
		{
			String prop = objs[0].getProperty("disabled");
			result = StringUtil.isEmpty(prop)?false:Boolean.parseBoolean(prop);
		
		}else{
			result =false;
		}
		Browser.unregister(objs);
		return result;
	}
	
	/**
	 * Select sales channel from drop down list
	 * 
	 * @param saleChannel
	 */
	public void selectSalesChannel(String saleChannel) {
		browser.selectDropdownList(".id", new RegularExpression("(conditions_channel)|(SlipRAFeeThresholdScheduleView-\\d+\\.salesChannel)",false), saleChannel);
	}

	/**
	 * Select product fee class from drop down list
	 * 
	 * @param productFeeClass
	 */
	public void selectProductFeeClass(String productFeeClass) {
		browser.selectDropdownList(".id", "product_fee_class_id",
				productFeeClass);
	}
	
	public void selectMarinaRateType(String rateType) {
		browser.selectDropdownList(".id", new RegularExpression("SlipRAFeeThresholdScheduleView-\\d+\\.marinaRateType",false),
				rateType);
	}
	
	public String getProductFeeClass() {
		return browser.getDropdownListValue(".id", "product_fee_class_id");
	}

	/**
	 * Select Transaction Type from drop down list
	 * 
	 * @param tranType
	 */
	public void selectTransactionType(String tranType) {
		browser.selectDropdownList(".id", new RegularExpression("(transaction_type)|(SlipRAFeeThresholdScheduleView-\\d+\\.tranType)",false), tranType);
	}

	/**
	 * Select Transaction Occurrence from drop down list
	 * 
	 * @param tranOccurrence
	 */
	public void selectTransactionOccurence(String tranOccurrence) {
		browser.selectDropdownList(".id", new RegularExpression("(transaction_occurance)|(SlipRAFeeThresholdScheduleView-\\d+\\.tranOccur)",false),
				tranOccurrence);
	}

	/**
	 * Input start counter
	 * 
	 * @param startCounter
	 */
	public void setStartCounter(String startCounter) {
		browser.setTextField(".id", new RegularExpression("(rafee_threshd_start_count)|(SlipRAFeeThresholdScheduleView-\\d+\\.startCounterProxy)",false), startCounter);
	}

	/**
	 * Input current counter
	 * 
	 * @param counter
	 */
	public void setCurrentCounter(String counter) {
		browser.setTextField(".id", "rafee_threshd_current_count", counter);
	}

	/**
	 * Input other rate range
	 * 
	 * @param otherRate
	 */
	public void setOtherRateRange(String otherRate) {
		browser.setTextField(".id", "otherrateranges", otherRate);
	}
	
	public void setOtherRateRanges(String otherRate, int i){
		browser.setTextField(".id", new RegularExpression("(otherrateranges)|(ThresholdRate-\\d+\\.qty\\:ZERO_TO_NULL)",false), otherRate,i);
	}
	
	public IHtmlObject[] getRemoveRangeObject(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text","Remove Range");
		return objs;
	}
	
	public void clickRemove(int i){
		browser.clickGuiObject(".class", "Html.A", ".text","Remove Range",i);
	}
	
	public List<String> getOtherRanges(String productCategory){
		List<String> otherRanges = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTextField(".id", 
				new RegularExpression("(otherrateranges)|(ThresholdRate-\\d+\\.qty\\:ZERO_TO_NULL)",false));
		int startIndex = -1;
		if(productCategory.equals("Slip")){
			startIndex = 0;
		}else{
			startIndex = 1;
		}
		if(objs.length>startIndex){
			for(int i=startIndex; i<objs.length; i++){
				String otherRange = objs[i].getProperty(".value");
				otherRanges.add(otherRange);
			}
		}
		Browser.unregister(objs);
		return otherRanges;
	}
	
	public void clearOtherRanges(String productCategory){
		IHtmlObject[] removeObjs = this.getRemoveRangeObject();
		int endIndex = -1;
		if(productCategory.equals("Slip")){
			endIndex = 0;
		}else{
			endIndex = 1;
		}
		for(int i=removeObjs.length; i>endIndex; i--){
			this.clickRemove(i - 1);
			System.out.println(i);
			ajax.waitLoading();
		}
		Browser.unregister(removeObjs);
	}
	
	public void setOtherRanges(List<String> otherRanges,String productCategory){
		this.clearOtherRanges(productCategory);
		for(int i =0; i<otherRanges.size(); i++){
			this.clickAddRange();
			ajax.waitLoading();
			if(productCategory.equals("Slip")){
				this.setOtherRateRanges(otherRanges.get(i), i);
			}else{
				this.setOtherRateRanges(otherRanges.get(i), i+1);
			}
			
		}
	}
	
	/**
	 * Click Apply Button
	 * 
	 */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	/**
	 * Click Ok Button
	 * 
	 */
	public void clickOk() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**
	 * Click Add Range Button
	 * 
	 */
	public void clickAddRange() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Range");
	}

	public String updateEffectiveDateAndApply(String date){
		setEffectiveDate(date);
		this.clickEffectiveLabel();
		this.clickApply();
		ajax.waitLoading();
		
		if(this.exists())
		{
			return this.getErrorMessage();
		}else{
			return null;
		}
	}
	
	
	public String updateCounterAndApply(String value){
		this.setStartCounter(value);
		this.clickApply();
		ajax.waitLoading();
		
		if(this.exists())
		{
			return this.getErrorMessage();
		}else{
			return null;
		}
	}
	
	
	/**
	 * This method used to enter all ra fee threshold schedule details
	 * 
	 * @param sche
	 */
	public void enterAllRAFeeThresholdSched(ScheduleData sche) {
		if (sche.productCategory != null && !sche.productCategory.equals("")){
			selectProdCategory(sche.productCategory);
			waitLoading();
		}
		if (sche.productCategory.equals("Slip")){
			if (sche.dock != null && !sche.dock.equals("")) {
				selectAssignmentDock(sche.dock);
				ajax.waitLoading();
				waitLoading();
			}
		}else {
			if (sche.loop != null && !sche.loop.equals("")) {
				selectAssignmentLoop(sche.loop);
				ajax.waitLoading();
				waitLoading();
			}
		}
		
		if (sche.productGroup != null && !sche.productGroup.equals("")) {
			selectProductGroup(sche.productGroup);
			ajax.waitLoading();
			waitLoading();
		}
		if (sche.product != null && !sche.product.equals("")) {
			selectProduct(sche.product);
			ajax.waitLoading();
			waitLoading();
		}
		if (sche.effectiveDate != null && !sche.effectiveDate.equals("")) {
			setEffectiveDate(sche.effectiveDate);
			this.clickEffectiveLabel();
		}
		if (sche.salesChannel != null && !sche.salesChannel.equals("")) {
			selectSalesChannel(sche.salesChannel);
		}
		if (sche.productFeeClass != null
				&& !sche.productFeeClass.equalsIgnoreCase("")) {
			selectProductFeeClass(sche.productFeeClass);
		}
		if (sche.marinaRateType != null
				&& !sche.marinaRateType.equalsIgnoreCase("")) {
			selectMarinaRateType(sche.marinaRateType);
		}
		if (sche.tranType != null && !sche.tranType.equals("")) {
			selectTransactionType(sche.tranType);
		}
		if (sche.tranOccur != null && !sche.tranOccur.equals("")) {
			selectTransactionOccurence(sche.tranOccur);
		}
		if (sche.startCounter != null && !sche.startCounter.equals("")) {
			setStartCounter(sche.startCounter);
		}
		if(sche.otherRanges != null){
			this.setOtherRanges(sche.otherRanges,sche.productCategory);
		}
	}

	/**
	 * This method used to get schedule Id
	 * 
	 * @return schedule Id
	 */
	public String getScheduleId() {
		String text;
		String schedId;

		RegularExpression rex = new RegularExpression(
				"Update RA Fee Threshold\\W+RA Fee Threshold ID.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		text = objs[0].getProperty(".text").toString();
		schedId = text.substring(
				text.indexOf("RA Fee Threshold ID")
						+ "RA Fee Threshold ID".length(),
				text.indexOf("Location")).trim();
		Browser.unregister(objs);
		return schedId;
	}

	/**
	 * This method used to get product category
	 * 
	 * @return
	 */
	public String getProdCategory() {
		String prodCat = browser.getDropdownListValue(".id", new RegularExpression("(prd_grp_cat_id)|(SlipRAFeeThresholdScheduleView-\\d+\\.prdGrpCat)",false),
				0);
		return prodCat;
	}
	
	public List<String> getProdCategoryElements()
	{		
			return browser.getDropdownElements(".id",
					new RegularExpression("(prd_grp_cat_id)|(SlipRAFeeThresholdScheduleView-\\d+\\.prdGrpCat)",false));
	}

	/**
	 * Get assignment loop
	 * 
	 * @return
	 */
	public String getLoop() {
		String loop = browser.getDropdownListValue(".id", new RegularExpression("assignment_loop|(SlipRAFeeThresholdScheduleView-\\d+\\.dockId)", false), 0);
		return loop;
	}
	
	public String getDock(){
		return browser.getDropdownListValue(".id", new RegularExpression("SlipRAFeeThresholdScheduleView-\\d+\\.dockId",false));
	}

	public List<String> getDockElements()
	{
		return browser.getDropdownElements(".id", new RegularExpression("SlipRAFeeThresholdScheduleView-\\d+\\.dockId",false));
	}
	/**
	 * Get product group value
	 * 
	 * @return
	 */
	public String getProductGroup() {
		String group = browser.getDropdownListValue(".id",
				new RegularExpression("(assignment_prodgroup)|(SlipRAFeeThresholdScheduleView-\\d+\\.productGroupId)",false), 0);
		return group;
	}

	public List<String> getProductGroupElements(){
		return browser.getDropdownElements(".id",
				new RegularExpression("(assignment_prodgroup)|(SlipRAFeeThresholdScheduleView-\\d+\\.productGroupId)",false));
	}
	
	/**
	 * Get assignment product value
	 * 
	 * @return
	 */
	public String getProduct() {
		String product = browser.getDropdownListValue(".id",
				new RegularExpression("(assignment_product)|(SlipRAFeeThresholdScheduleView-\\d+\\.prdID)",false), 0);
		return product;
	}
	
	public List<String> getProductList() {
		List<String> product = browser.getDropdownElements(".id",
				new RegularExpression("(assignment_product)|(SlipRAFeeThresholdScheduleView-\\d+\\.prdID)",false));
		return product;
	}

	/**
	 * Get effective date value
	 * 
	 * @return
	 */
	public String getEffectiveDate() {
		String date = browser.getTextFieldValue(".id",
				new RegularExpression("(date_effective_ForDisplay)|(SlipRAFeeThresholdScheduleView-\\d+\\.effectDate_ForDisplay)",false));
		return date;
	}

	/**
	 * Get sale channel value
	 * 
	 * @return
	 */
	public String getSaleChannel() {
		String channel = browser.getDropdownListValue(".id",
				new RegularExpression("(conditions_channel)|(SlipRAFeeThresholdScheduleView-\\d+\\.salesChannel)",false), 0);
		return channel;
	}
	
	public List<String> getSaleChannelElement() {
		return browser.getDropdownElements(".id",
				new RegularExpression("(conditions_channel)|(SlipRAFeeThresholdScheduleView-\\d+\\.salesChannel)",false));
	}
	
	public String getMarinaRateType() {
		String channel = browser.getDropdownListValue(".id",
				new RegularExpression("SlipRAFeeThresholdScheduleView-\\d+\\.marinaRateType",false), 0);
		return channel;
	}
	
	public List<String> getMarinaRateTypeElement() {
		return browser.getDropdownElements(".id",
				new RegularExpression("SlipRAFeeThresholdScheduleView-\\d+\\.marinaRateType",false));
	}

	/**
	 * Get Transaction Type value
	 * 
	 * @return
	 */
	public String getTranType() {
		String tranType = browser.getDropdownListValue(".id",
				new RegularExpression("(transaction_type)|(SlipRAFeeThresholdScheduleView-\\d+\\.tranType)",false), 0);
		return tranType;
	}
	
	public List<String> getTranTypeElement() {
		return browser.getDropdownElements(".id",
				new RegularExpression("(transaction_type)|(SlipRAFeeThresholdScheduleView-\\d+\\.tranType)",false));
	}

	public List<String> getTranTypeElementsIDs() {
//		return browser.getDropdownElementsValues(".id",
//				new RegularExpression("(transaction_type)|(SlipRAFeeThresholdScheduleView-\\d+\\.tranType)",false));
		return null;
	}
	/**
	 * Get Transaction Occurrence value
	 * 
	 * @return
	 */
	public String getTranOccurrence() {
		String tranType = browser.getDropdownListValue(".id",
				new RegularExpression("(transaction_occurance)|(SlipRAFeeThresholdScheduleView-\\d+\\.tranOccur)",false), 0);
		return tranType;
	}
	
	public List<String> getTranOccurrenceElement() {
		return browser.getDropdownElements(".id",
				new RegularExpression("(transaction_occurance)|(SlipRAFeeThresholdScheduleView-\\d+\\.tranOccur)",false));
	}

	/**
	 * Get Start Counter value
	 * 
	 * @return
	 */
	public String getStartCounter() {
		return browser.getTextFieldValue(".id", new RegularExpression("(rafee_threshd_start_count)|(SlipRAFeeThresholdScheduleView-\\d+\\.startCounterProxy)",false));
	}
	
	public String getCurrentCounter(){
		return browser.getTextFieldValue(".id",
				new RegularExpression("(rafee_threshd_current_count)|(SlipRAFeeThresholdScheduleView-\\d+\\.currentCounter)",false), 0);
	}

	public String getErrorMessage() {
//		IHtmlObject[] objs=browser.getHtmlObject(".id", "NOTSET");
		IHtmlObject[] objs = browser.getHtmlObject(".className", "message msgerror");// Nicole for 306. Feb 13, 2014
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found error message object.");
		}
		
		String error=objs[0].getProperty(".text");
		
		Browser.unregister(objs);
		return error;
	}
	
	public List<String> getErrorMessageList(){
		IHtmlObject[] objs=browser.getHtmlObject(".id", "NOTSET");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found error message object.");
		}
		List<String> errorMessages = new ArrayList<String>();
		for(int i=0; i<objs.length; i++){
			String error=objs[i].getProperty(".text");
			errorMessages.add(error);
		}
		
		Browser.unregister(objs);
		return errorMessages;
	}
	
	public boolean checkErrorMessage() {
		return browser.checkHtmlObjectExists(".id", "NOTSET") || browser.checkHtmlObjectExists(".className", "message msgerror");
	}

	public void verifyThresholdDetailsInfo(ScheduleData schedule) {
		logger.info("Start to verify Threshold Schedule Details.");
		boolean result = true;
		result &= MiscFunctions.compareResult("Product Category", schedule.productCategory, this.getProdCategory());
		if(schedule.productCategory.equals("Slip")){
			result &= MiscFunctions.compareResult("Product Dock", schedule.dock, this.getDock());
			result &= MiscFunctions.compareResult("Marina Rate Type", schedule.marinaRateType, this.getMarinaRateType());
		}else if(schedule.productCategory.equals("Ticket") || schedule.productCategory.equals("Site")){
			result &= MiscFunctions.compareResult("Product Loop", schedule.loop, this.getLoop());
		}
		result &= MiscFunctions.compareResult("Product Group", schedule.productGroup, this.getProductGroup());
//		result &= MiscFunctions.compareResult("Product", schedule.product, this.getProduct());//Quentin[20140710] the Product dropdown list contains 1012 options, so skip go through get the selected option
		result &= MiscFunctions.compareResult("Effective Date", schedule.effectiveDate, this.getEffectiveDate());
		result &= MiscFunctions.compareResult("Sales Channel", schedule.salesChannel, this.getSaleChannel());
		if(schedule.productCategory.equals("Ticket")){
			result &= MiscFunctions.compareResult("Product Fee Class", schedule.productFeeClass, this.getProductFeeClass());
		}
		result &= MiscFunctions.compareResult("Transaction Type", schedule.tranType, this.getTranType());
		result &= MiscFunctions.compareResult("Transaction Occurance", schedule.tranOccur, this.getTranOccurrence());
		result &= MiscFunctions.compareResult("Start Counter", schedule.startCounter, this.getStartCounter());
		result &= MiscFunctions.compareResult("Current Counter", schedule.currentCounter, this.getCurrentCounter());
		List<String> otherRanges = this.getOtherRanges(schedule.productCategory);
		if(schedule.otherRanges != null && schedule.otherRanges.size()>0){
			if(schedule.otherRanges.size() != otherRanges.size()){
				result &= MiscFunctions.compareResult("Other Ranges size", schedule.otherRanges.size(), otherRanges.size());
			}else{
				for(int i=0; i<schedule.otherRanges.size();i++){
					result &= MiscFunctions.compareResult("Other Ranges info", schedule.otherRanges.get(i), otherRanges.get(i));
				}
			}
			
		}else{
			result &= MiscFunctions.compareResult("Other Ranges size", 0, otherRanges.size());
		}

		if (!result) {
			throw new ErrorOnPageException("Threshold Detail info is not correct.");
		}
	}
}
