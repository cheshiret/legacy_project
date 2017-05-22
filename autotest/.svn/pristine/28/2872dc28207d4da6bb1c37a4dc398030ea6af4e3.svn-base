package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public abstract class OrmsFeesPage extends OrmsPage {
	protected RegularExpression usefeeIdPattern = new RegularExpression("usefee_Daily/Nightly(_[0-9]+)+", false);
	protected RegularExpression usefeePenaltyIdPattern = new RegularExpression("usefee_penalty(_[0-9]+)+", false);
	protected RegularExpression attrfeeIdPattern=new RegularExpression("usefee_Daily/Nightly(_[0-9]+)+",false);
	protected RegularExpression transfeeIdPattern = new RegularExpression("tranfee(_[0-9]+)+", false);
	protected RegularExpression useFeeTaxIdPattern = new RegularExpression(	"usefee_tax(_[0-9]+)+", false);
	protected RegularExpression usefeeDisctIdPattern = new RegularExpression("usefee_discnt(_[0-9]+)+", false);
	protected RegularExpression attrfeeDisctIdPattern = new RegularExpression("attrfee_discnt(_[0-9]+)+", false);
	protected RegularExpression usefeeDisctRateIdPattern = new RegularExpression("usefee_discnt_[0-9]+_rate_",false);
	protected RegularExpression attrfeeDisctRateIdPattern = new RegularExpression("attrfee_discnt_[0-9]+_rate_",false);
	protected RegularExpression itemTransFeePattern = new RegularExpression("item_tranfee(_[0-9]+)+", false);
	protected RegularExpression ordTransFeePattern = new RegularExpression("order_tranfee(_[0-9]+)+", false);
	protected RegularExpression itemRaFeePattern = new RegularExpression("item_rafee(_[0-9]+)+", false);
	protected RegularExpression ordRaFeePattern = new RegularExpression("order_rafee(_[0-9]+)+", false);
	
	protected Property[] raTransactionFee() {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id",this.itemRaFeePattern);
	}
	/** Determine if the associated web object exists */
//	public boolean exists() {
//		RegularExpression reg = new RegularExpression(".*Reservation Fees$",false);
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",reg);
//	}

	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.TEXTAREA", ".id","fee_adjustment_notes");
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text",new RegularExpression("(Ajustment Notes)|(Adjustment Notes)|(Notes)",false));

	}
	
	/**
	 * Input adjustment notes
	 * @param note
	 */
	public void setAdjustmentNotes(String note) {
		browser.setTextArea(".id", new RegularExpression("fee_adjustment_notes|OrderFeeView-\\d+.adjustmentNotes",false), note);
	}
	
	protected String getFeeTypePattern(String feeType) {
		if (feeType.equalsIgnoreCase("use fee")) {
			feeType = "usefee_(Daily/Nightly|Weekly)|(Custom)(_[0-9]+)+";// regular express
		} else if (feeType.equals("transaction fee")) {
			feeType = "tranfee(_[0-9]+)+";
		} else if (feeType.equalsIgnoreCase("ra fee")) {
			feeType = "rafee(_[0-9]+)+";
		} else if(feeType.equalsIgnoreCase("use fee discount")){
				feeType = "usefee_(discnt)(_[0-9]+)+";
		} else if(feeType.equalsIgnoreCase("ticket fee")){
			feeType = "ticketfee(_[0-9]+)+";	
		} else if(feeType.equalsIgnoreCase("use fee penalty")){ //When cancel reservation,it will cause penalty fee.
			feeType = "usefee_(penalty)(_[0-9]+)+";
		} else if(feeType.equalsIgnoreCase("pos fee")){
			feeType = "posfee(_[0-9]+)+";	
		} else if(feeType.equalsIgnoreCase("pos fee discount")){
			feeType= "discount(_[0-9]+){2}_rate_[0-9]+";
		} else if(feeType.equalsIgnoreCase("attribute fee")){
			feeType = "attrfee_(Daily/Nightly|)(_[0-9]+)+";	
		} else if(feeType.equalsIgnoreCase("permit use fee")){
			feeType = "permitfee(_[0-9]+)+";
		} else if(feeType.equalsIgnoreCase("item ra fee")){
			feeType = "item_rafee(_[0-9]+)+";
		} else if(feeType.equalsIgnoreCase("item transaction fee")){
			feeType = "item_tranfee(_[0-9]+)+";
		} else if(feeType.equalsIgnoreCase("use fee tax")){
			feeType = "usefee_tax_\\d+";
		} else if(feeType.equals("transaction fee tax")){
			feeType = "tranfee_tax_\\d+";
		}else {
			throw new ItemNotFoundException("Unknown Fee Type");
		}
		
		return feeType;
	}
	
	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", false);
	}

	/**
	 * Get RA fees table object
	 * @return--Return the table object
	 */
	public IHtmlObject[] getRAFeesTable() {
		return browser.getTableTestObject(".text", new RegularExpression("^RA Fees.*", false));
	}
	
	/**
	 * Change Res Fee
	 * @param feeType
	 * @param expectedFee
	 * @param newFee
	 * @param feeSchID
	 * @param notes
	 * @return
	 */
	public String processFeeAdjustments(String feeType, double expectedFee,	double newFee, String feeSchID, String notes) {
		
		String result = "";
		boolean feeChanged=false;

		if (feeType.equalsIgnoreCase("ticket fee")) {
			feeType = "ticketfee(_[0-9]+)+";               
		} else if (feeType.equals("item transaction fee")) {
			feeType = "item_tranfee(_[0-9]+)+";             
		} else if (feeType.equalsIgnoreCase("item ra fee")) {
			feeType = "item_rafee(_[0-9]+)+";                   
		} else if(feeType.equalsIgnoreCase("order transaction fee")){
				feeType = "order_tranfee(_[0-9]+)+";           
		} else if(feeType.equalsIgnoreCase("order ra fee")){
			feeType = "order_rafee(_[0-9]+)+";	               
		} else {
			throw new ItemNotFoundException("Unknown Fee Type for fee item");
		}
		feeType=feeType+(feeSchID!=null && feeSchID.length()>0?"_"+feeSchID:"");
		RegularExpression regex = new RegularExpression(feeType, false);

		// Find all Fee
		IHtmlObject[] objs = browser.getHtmlObject(".id", regex, ".class","Html.INPUT.text");
		if (objs.length < 1) {
			result="Skipped - failed to find a fee with schedule id="+feeSchID; 
		} else {
			IHtmlObject guiObj = objs[0];
	
			String s = objs[0].getProperty(".value").toString();
			double actualFee = Double.parseDouble(s);
	
			if (actualFee == expectedFee) {
				((IText)guiObj).setText(newFee+"");
		
				refreshPage();
				browser.sync(2);
		
				feeChanged=true;
				result = "changed fee";
		
                //reload found objects array
//					if(i+1<objs.length) {
//						Browser.unregister(objs);
//						objs = browser.getHtmlObject(".id", regex, ".class","Html.INPUT.text");
//					}
			} else if(actualFee == newFee) {
				result = "Skipped - the actual fee amount "+actualFee+" is same as new amount";
			}else {
				result = "Skipped - the actual fee amount "+actualFee+" is not expected "+expectedFee;
			}
		}

		Browser.unregister(objs);
		
		if (feeChanged) {
			// if fees have changed, add notes and click OK
			refreshPage();
			browser.sync(2);
            
			setAdjustmentNotes(notes);
		} 
		clickOK();
		
		return result;
	}
	
	/**Click Refresh page by clicking form*/
	public void refreshPage() {
		browser.clickGuiObject(".class", "Html.FORM", ".id", "e_Form");
	}
	
	/**
	 * The id is like ticketfee_1412542203_1_146104221_1411220413, the last number is fee schedule id
	 * @param id
	 * @return
	 */
	public String getFeeScheduleFromFeeInputId(String id) {
		String[] tokens=RegularExpression.getMatches(id, "\\d+");
		
		if(tokens.length>0) {
			return tokens[tokens.length-1];
		} else {
			return "";
		}
	}
}
