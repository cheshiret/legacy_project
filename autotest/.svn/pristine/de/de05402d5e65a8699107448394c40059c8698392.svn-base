/*
 * $Id: FinMgrTaxDetailPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.financeManager.tax;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Tax;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author CGuo
 */
public class FinMgrTaxDetailPage extends FinanceManagerPage {
//IBrowser browser = Browser.getInstance();
	/**
	 * Script Name   : FinMgrTaxDetailPage
	 * Generated     : Dec 16, 2004 5:47:11 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/12/16
	 */
 
  	/** A handle to the unique Singleton instance. */
	private static FinMgrTaxDetailPage _instance = null;

	/**
	 * @return The unique instance of this class.
	 */
	public static FinMgrTaxDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrTaxDetailPage();
		}

		return _instance;
	}
	
	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrTaxDetailPage() {
	}

	/** Check this page is exists */
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", "Tax Detail");
		return browser.checkHtmlObjectExists(".id", "TaxTypeView.name");//Quentin[20131015] 3.05 UI changes
	}

	/** This method is used to wait ajax refresh */
	public void searchAndWaitExists() {
	  	browser.searchObjectWaitExists(".class", "Html.SPAN", ".text", "Tax Detail");
	}
	
	/** Click Ok Button */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/** Click Apply Button */
	public void clickApply() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	  	waitLoading();
	}

	/**
	 * Input tax name
	 * @param taxName
	 */
	public void setTaxName(String taxName) {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "TaxTypeView.name");
		logger.info(objs.length);
		browser.setTextField(".id", "TaxTypeView.name", taxName,objs.length-1);
		Browser.unregister(objs);
	}
	/**
	 * Input tax code
	 * @param taxCode
	 */
	public void setTaxCode(String taxCode) {
	  	browser.setTextField(".id", "TaxTypeView.taxCode", taxCode);
	}

	/**
	 * Input tax description
	 * @param description
	 */
	public void setDescription(String description) {
		browser.setTextArea(".id", "TaxTypeView.description", description);
	}

	/**
	 * Select rate type from drop down list
	 * @param item
	 */
	public void selectRate(String item) {
		browser.selectDropdownList(".id", "TaxTypeView.rateType", item);
		ajax.waitLoading();
	}

	/** Deselect Attribute Fee */
	public void deselectAttrFee() {
		//HtmlObject[] objs = browser.getCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "12");
		//String checked = objs[0].getProperty(".checked").toString();
		//Browser.unregister(objs);
		
		if(this.checkAttributeFeeSelected()){
			browser.unSelectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value","12");
		}
	}

	/** Select Attribute Fee */
	public void selectAttrFee() {
//	  	IHtmlObject[] objs = browser.getCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "12");
//		String checked = getCheckedStatus(objs[0]);
//	  	Browser.unregister(objs);
//	  	if(checked.equalsIgnoreCase("false")){
		  	browser.selectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "12");
		  	ajax.waitLoading();
//		}
	}
	public boolean getCheckedStatus(IHtmlObject obj) //added by pzhu for selenium getting status.
	{
		return ((ICheckBox)obj).isSelected();
//		if(null==tmp){
//			return "false";
//		}else{
//			return tmp;
//			
//		}
			
	}

	/** Deselect Entrance Day Use Fee */
	public void deselectEntranceDayUseFee() {
	  /*	HtmlObject[] objs = browser.getCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "6");
		String checked = getCheckedStatus(objs[0]);
		Browser.unregister(objs);*/
		//if(checked.equalsIgnoreCase("true")){
		if(this.checkEntranceDayUseFeeSelected()){
		  	browser.unSelectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "6");
		}
	}
	
	/** Select Entrance Day Use Fee */
	public void selectEntranceDayUseFee() {
//	  	IHtmlObject[] objs = browser.getCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "6");
//		String checked = getCheckedStatus(objs[0]);
//		Browser.unregister(objs);
//		if(checked.equalsIgnoreCase("false")){
		  	browser.selectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "6");
		  	ajax.waitLoading();
//		}
	}

	/** Deselect Pos Fee */
	public void deselectPosFee() {
	  /*	HtmlObject[] objs = browser.getCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "13");
		String checked = getCheckedStatus(objs[0]);
		Browser.unregister(objs);*/
		//if(checked.equalsIgnoreCase("true")){
		if(this.checkPosFeeSelected()){
		  	browser.unSelectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "13");
		}
	}

	/** Select Pos Fee */
	public void selectPosFee() {
//	  	IHtmlObject[] objs = browser.getCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "13");
//		String checked = getCheckedStatus(objs[0]);
//		Browser.unregister(objs);
//		if(checked.equalsIgnoreCase("false")){
		  	browser.selectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "13");
		  	ajax.waitLoading();
//		}
	}

	/** Deselect Transaction Fee */
	public void deselectTransactionFee() {
	  /*	HtmlObject[] objs = browser.getCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "4");
		String checked = getCheckedStatus(objs[0]);
		Browser.unregister(objs);
		if(checked.equalsIgnoreCase("true")){*/
		if(this.checkTransactionFeeSelected()){
		  	browser.unSelectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "4");
		}
	}

	/** Select Transaction Fee */
	public void selectTransactionFee() {
//	  	IHtmlObject[] objs = browser.getCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "4");
//		String checked = getCheckedStatus(objs[0]);
//		Browser.unregister(objs);
//		if(checked.equalsIgnoreCase("false")){
		  	browser.selectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "4");
		  	ajax.waitLoading();
//		}
	}

	/** Deselect Use Fee */
	public void deselectUseFee() {
	  /*	HtmlObject[] objs = browser.getCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "2");
		String checked = getCheckedStatus(objs[0]);
		Browser.unregister(objs);
		if(checked.equalsIgnoreCase("true")){*/
		if(this.checkUseFeeSelected()){
		  	browser.unSelectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "2");
		}
	}

	/** Select Use Fee */
	public void selectUseFee() {
//	  	IHtmlObject[] objs = browser.getHtmlObject(".id", "TaxTypeView.applicableFeeTypes", ".value", "2");
//		String checked = getCheckedStatus(objs[0]);
//		Browser.unregister(objs);
//		if(checked.equalsIgnoreCase("false")){
		  	browser.selectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "2");
		  	ajax.waitLoading();
//		}
	}
	
	public void selectActivityFee(){
		browser.selectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "39");
	  	ajax.waitLoading();
	}
	
	
	
	public void deselectActivityFee(){
		if(this.checkActivityFeeSelected()){
			browser.unSelectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "39");
		}
	}
	
	public void deselectVendorFee(){
		if(this.checkVendorFeeSelected()){
			browser.unSelectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "36");
		}
	}
	//add by Summer. Date:2014/6/12 Reason: add privilege fee and privilege lottery fee
	public void deselectPrivilegeFee(){
		if(this.checkVendorFeeSelected()){
			browser.unSelectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "34");
		}
	}
	public void deselectPrivilegeLotteryFee(){
		if(this.checkVendorFeeSelected()){
			browser.unSelectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "37");
		}
	}
	
	public void selectVendorFee(){
		browser.selectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "36");
	  	ajax.waitLoading();
	}
	//add by Summer. Date:2014/6/12 Reason: add privilege fee and privilege lottery fee
	public void selectPrivilegeFee(){
		browser.selectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "34");
	  	ajax.waitLoading();
	}
	public void selectPrivilegeLotteryFee(){
		browser.selectCheckBox(".id", "TaxTypeView.applicableFeeTypes", ".value", "37");
	  	ajax.waitLoading();
	}
	public boolean checkFeeTypesSelected(String textValue){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.Span", ".text", textValue); //Lesley[20131121] change from DIV to Span in 3.05.00
		if(objs.length<1){
			throw new ErrorOnPageException("No element exist");
		}
		boolean isChecked = browser.isCheckBoxSelected(".id", "TaxTypeView.applicableFeeTypes", objs[0]);
		Browser.unregister(objs);
		return isChecked;
	}
	/**
	 * check attribute fee select or not.
	 * @return
	 */
	public boolean checkAttributeFeeSelected(){
		return this.checkFeeTypesSelected("Attribute Fee");
	}
	/**
	 * check entrance day use fee selected or not.
	 * @return
	 */
	public boolean checkEntranceDayUseFeeSelected(){
		return this.checkFeeTypesSelected("Entrance-DayUse Fee");
	}
	/**
	 * check pos fee selected or not.
	 * @return
	 */
	public boolean checkPosFeeSelected(){
		return this.checkFeeTypesSelected("POS Fee");
	}
	/**
	 * check transaction selected or not.
	 * @return
	 */
	public boolean checkTransactionFeeSelected(){
		return this.checkFeeTypesSelected("Transaction Fee");
	}
	/**
	 * Check use fee selected or not.
	 * @return
	 */
	public boolean checkUseFeeSelected(){
		return this.checkFeeTypesSelected("Use Fee");
	}
	
	/**
	 * Check activity fee selected or not.
	 * @return
	 */
	public boolean checkActivityFeeSelected(){
		return this.checkFeeTypesSelected("Activity Fee");
	}
	/**
	 * Check vendor fee selected or not.
	 * @return
	 */
	public boolean checkVendorFeeSelected(){
		return this.checkFeeTypesSelected("Vendor Fee");
	}

	/**
	 * This method is used to enter all tax details info
	 * @param tax
	 */
	public void enterAllTaxDetails(Tax tax) {
		String[] feeType = null;
		//taxName and taxType must set.
	  	setTaxName(tax.taxName);
	  	if(null != tax.taxCode && tax.taxCode.length() > 0) {
		  	setTaxCode(tax.taxCode);
	  	}
	  	if(null != tax.taxDescription && tax.taxDescription.length() > 0) {
		  	setDescription(tax.taxDescription);
	  	}
	  	selectRate(tax.taxRateType);	
	  	
		for(int i = 0; i < tax.feeTypes.size(); i++) {
		 if(!tax.feeTypes.toString().contains(",")) {
			 feeType = tax.feeTypes.toString().replaceAll("\\[","").split("]");
		 }else{
			 for(int j = 0; j < tax.feeTypes.size(); j++){
				 feeType = tax.feeTypes.toString().replaceAll("\\[|\\]","").split(",");//You have to make the fees splited with ",". 	
			 }
		 }
		for(int k = 0; k < feeType.length; k++) {	
		if (feeType[k].trim().equalsIgnoreCase("Attribute Fee")) {
			selectAttrFee();
		} else if (feeType[k].trim().equalsIgnoreCase("Entrance-DayUse Fee")) {
			selectEntranceDayUseFee();
		} else if (feeType[k].trim().equalsIgnoreCase("POS Fee")) {
			selectPosFee();
		} else if (feeType[k].trim().equalsIgnoreCase("Transaction Fee")) {
			selectTransactionFee();
		} else if (feeType[k].trim().equalsIgnoreCase("Use Fee")) {
			selectUseFee();
		}  else if (feeType[k].trim().equalsIgnoreCase("Activity Fee")) {
			this.selectActivityFee();
		}else if (feeType[k].trim().equalsIgnoreCase("Vendor Fee")) {
			this.selectVendorFee();
		}else if (feeType[k].trim().equalsIgnoreCase("Privilege Fee")) {
			this.selectPrivilegeFee();
		}else if (feeType[k].trim().equalsIgnoreCase("Privilege Lottery Fee")) {
			this.selectPrivilegeLotteryFee();
		}
		else {
			throw new ItemNotFoundException("the fee Type is unknown");
		}
  	  }
    }
}
	/**
	 * edit tax exited
	 */
	public void editTax(Tax tax) {
		String[]feeType = null;
		if(!tax.taxName.equals(tax.newTaxName)) {
			setTaxName(tax.newTaxName);  
		}
		setTaxCode(tax.taxCode);
		setDescription(tax.taxDescription);
		selectRate(tax.taxRateType);
        
		//deselect all feeType,in order to reset fee type
		deselectAttrFee();
		deselectEntranceDayUseFee();
		deselectPosFee();
		deselectTransactionFee();
		deselectUseFee();
		deselectActivityFee();
		deselectVendorFee();
		
		for(int i = 0; i < tax.feeTypes.size(); i++) {
			 if(!tax.feeTypes.toString().contains(",")) {
				 feeType = tax.feeTypes.toString().replaceAll("\\[","").split("]");
			 }else{
				 for(int j = 0;j < tax.feeTypes.size(); j++){
					 feeType = tax.feeTypes.toString().replaceAll("\\[|\\]","").split(",");//You have to make the fees splited with ",". 	
				 }
			 }
			for(int k = 0; k < feeType.length; k++){	
			if (feeType[k].equals("Attribute Fee")) {
				selectAttrFee();
			} else if (feeType[k].equals("Entrance-DayUse Fee")) {
				selectEntranceDayUseFee();
			} else if (feeType[k].equals("POS Fee")) {
				selectPosFee();
			} else if (feeType[k].equals("Transaction Fee")) {
				selectTransactionFee();
			} else if (feeType[k].equals("Use Fee")) {
				selectUseFee();
			} else if (feeType[k].trim().equalsIgnoreCase("Activity Fee")) {
				this.selectActivityFee();
			}else if (feeType[k].trim().equalsIgnoreCase("Vendor Fee")) {
				this.selectVendorFee();
			} 
			
			else {
				throw new ItemNotFoundException("the fee Type is unknown");
			}
	  	  }
		}
	}
	
	/**
	 * Get Tax Name from text field
	 * @return tax name
	 */
	public String getTaxName() {
	  	String taxName = browser.getTextFieldValue(".id","TaxTypeView.name");
		return taxName;
	}
	/**
	 * Get Tax Name after modifing or create
	 * @return tax name
	 */
	public String getTaxNameAfterCreateOrModify() {
//		HtmlObject[] obj = browser.getTableTestObject(".text", new RegularExpression("Update Tax Name.*", false));
//		String taxName = ((ITable)obj[0]).getCellValue(0, 1).split("Tax Description County")[0].split("Tax Name")[1].trim();
//	  	Browser.unregister(obj);
		
//		return taxName;
		
		return browser.getObjectText(".class", "Html.INPUT.text", ".id", "TaxTypeView.name");
	}
	
	/**
	 * Get Tax Code from text field
	 * @return tax code
	 */
	public String getTaxCode() {
	  	return browser.getTextFieldValue(".id", "TaxTypeView.taxCode");
	}

	/**
	 * Get Tax Description from text area
	 * @return tax description
	 */
	public String getTaxDescription() {
	  	IHtmlObject[] objs = browser.getTextArea(".id", "TaxTypeView.description");
	  	String desc = objs[0].getProperty(".value").toString();
	  	Browser.unregister(objs);
	  	return desc;
	}
	
	/**
	 * Get Tax rate type from drop down list
	 * @return rate type
	 */
	public String getRateType(){
	  	return browser.getDropdownListValue(".id", "TaxTypeView.rateType", 0);
	}
	
	/**
	 * Get all checked fee types
	 * @return a vector contain all checked fee type
	 */
	public List<String> getCheckedFeeTypes() {
	  	ArrayList<String> feeTypes = new ArrayList<String>();
	  	IHtmlObject[] temp = browser.getCheckBox(".id", "TaxTypeView.applicableFeeTypes");
	  	List<IHtmlObject> objs = new ArrayList<IHtmlObject>();
	  	
	  	for(int i = 0; i<temp.length; i++) {
	  		if(((ICheckBox)temp[i]).isSelected()) {
	  			objs.add(temp[i]);
	  		}
	  	}
	  	
		for(int i=0;i<objs.size();i++) {
			String value = objs.get(i).getProperty(".value").toString();
			if(value.equals("12")) {
			  	feeTypes.add("Attribute Fee");
			}else if(value.equals("6")) {
				feeTypes.add("Entrance-DayUse Fee");
			}else if(value.equals("13")) {
				feeTypes.add("POS Fee");
			}else if(value.equals("4")) {
				feeTypes.add("Transaction Fee");
			}else if(value.equals("2")) {
				feeTypes.add("Use Fee");
			}else if(value.equals("39")) {
				feeTypes.add("Activity Fee");
			}else if(value.equals("36")) {
				feeTypes.add("Vendor Fee");
			}
		}
		Browser.unregister(temp);
	  	return feeTypes;
	}
	
	/**
	 * This method is used to verify tax details is same with given tax
	 * @param tax
	 */
	public void verifyTaxDetail(Tax tax) {
		if(StringUtil.notEmpty(tax.taxName)){
			if(!tax.taxName.equalsIgnoreCase(this.getTaxName())) {
			  	  throw new ItemNotFoundException("Tax Name " + getTaxName() + " is not Correct!");
			  	}
		}
	  	if(StringUtil.notEmpty(tax.newTaxName)){
	  		MiscFunctions.compareResult("tax new name", tax.newTaxName, this.getTaxName());
	  	}
	  	if(!tax.taxCode.equalsIgnoreCase(this.getTaxCode())) {
	  	  throw new ItemNotFoundException("Tax Code " + getTaxCode() + " is not Correct!");
	  	}
	  	if(!tax.taxDescription.equalsIgnoreCase(this.getTaxDescription())) {
	  	  throw new ItemNotFoundException("Tax Description " + getTaxDescription() + " is not Correct!");
	  	}
	  	if(!tax.taxRateType.equalsIgnoreCase(this.getRateType())) {
	  	  throw new ItemNotFoundException("Tax Rate Type " + getRateType() + " is not Correct");
	  	}
	  	if(tax.feeTypes != null && this.getCheckedFeeTypes() != null) {
	  	  if(tax.feeTypes.size() != getCheckedFeeTypes().size()) {
	  	    throw new ItemNotFoundException("Checked Fee Type num " + getCheckedFeeTypes().size() + " not Correct!");
	  	  }
	  	  for(int i=0;i<tax.feeTypes.size();i++) {
	  	    if(!tax.feeTypes.get(i).toString().equalsIgnoreCase(getCheckedFeeTypes().get(i).toString())) {
	  	      throw new ItemNotFoundException("Checked Fee Type " + getCheckedFeeTypes().get(i).toString() + " not Correct!");
	  	    }
	  	  }
	  	}else if((getCheckedFeeTypes() == null && tax.feeTypes != null) || (getCheckedFeeTypes() != null && tax.feeTypes == null)) {
	  	  throw new ItemNotFoundException("Checked Fee Type num " + getCheckedFeeTypes().size() + " not Correct!");
	  	}
	}
	
}
