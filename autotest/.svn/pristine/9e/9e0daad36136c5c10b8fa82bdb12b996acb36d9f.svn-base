/*
 * Created on Sep 11, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.financial;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AdjustmentInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author QA
 *This page is Deposit Detail Page
 */
public class OrmsDepositDetailPage extends OrmsPage {

  /**
   * protected constructor prevent construct instance directly with this method
   *
   */
	protected OrmsDepositDetailPage() {
	}

	private static OrmsDepositDetailPage _instance = null;

	/**
	 * Singleton pattern to get the unique instance of this class
	 * @return
	 */
	public static OrmsDepositDetailPage getInstance() {
		if (null == _instance)
			_instance = new OrmsDepositDetailPage();
		return _instance;
	}

	/**
	 * return whether page mark exists
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".text", "Deposits", ".class",
				"Html.A");
	}

	/**
	 * Click Last button goto the last page
	 *
	 */
	public void clickGoLast() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Last");
	}

	/**
	 * @return Last Button exists
	 */
	public boolean checkLastExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Last");
	}
	
	public void clickCancel(){
	   browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	public void selectSearchBy(String searchItem){
	   browser.selectDropdownList(".id","searchBy",searchItem);
	}
	
	public void setSearchValue(String searchValue){
	   browser.setTextField(".id","criteria",searchValue);
	}
	
	public void clickGo(){
	   browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("^Search$", false));
	}

	/**
	 * Include a finSession and create a new deposit with or without adjustment
	 * @param finID
	 * @param cashTotalOnHand
	 * @param nonCashTotalOnHand
	 * @param note
	 */
	public void createNewDeposit(String finID, String cashTotalOnHand,
			String[] nonCashTotalOnHand, String note) {
		RegularExpression rex = new RegularExpression("Fin Sess ID\\s+(\\s|Included\\s)+Status\\s+Adjusted.*", false);
		PagingComponent turnPage = new PagingComponent();

		int index = 0;
		if(!"".equals(finID)){
			do{
				IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", rex);
				IHtmlTable tableGrid=(IHtmlTable)objs[objs.length-1];
				for (int i = 0; i < tableGrid.rowCount(); i++) {
					if (tableGrid.getCellValue(i, 1).toString().equals(finID)) {
						index = i;
						break;
					}
				}
				if(index == 0 && turnPage.nextExists()){
					turnPage.clickNext();
					this.exists();
				}
				Browser.unregister(objs);
			}while(index == 0 && turnPage.lastExists());
		}else{
		  index = 1;
		}

		clickOneCheckBox(Integer.valueOf(index).toString());
		clickIncludeInDeposit();
		waitLoading();
		if (!StringUtil.isEmpty(note)) {
			setNote(note);
		}
		
		if (!StringUtil.isEmpty(cashTotalOnHand)) {
			setCashTotalOnHand(cashTotalOnHand);
			waitLoading();
		}
		
		if (null != nonCashTotalOnHand && nonCashTotalOnHand.length > 0) {
			setNonCashTotalOnHand(nonCashTotalOnHand);
			waitLoading();
		}
		clickCreateDeposit();
	}

	/**
	 * Use RegularExpression to match specific table and return deposit Id 
	 * @return
	 */
	public String getDepositID() {
		String depositID = "";
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("Create Deposit.*",false));
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		depositID = text.substring(text.indexOf("Deposit ID") + "Deposit ID".length(), text.indexOf("Location")).trim();

		return depositID;
	}

	/**
	 * Select one finSession checkBox
	 * @param index--mark the finSession index
	 */
	public void clickOneCheckBox(String index) {
		String rex = "sessionCheck_" + index;
		browser.clickGuiObject(".class", "Html.INPUT.checkbox", ".id", rex);
	}

	/**
	 * 
	 *Click Include In Deposit Button
	 */
	public void clickIncludeInDeposit() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Include In Deposit");
	}

	/**
	 * Click Deposit Details link
	 *
	 */
	public void clickDepositDetails() {
		browser.clickGuiObject(".text", "Deposit Details", ".class", "Html.A");
	}

	/**
	 * Click Create deposit Button
	 *
	 */
	public void clickCreateDeposit() {
//		browser.clickGuiObject(".text", "Create Deposit", ".href",
//				"javascript:%20checkPIN();", true);
		browser.clickGuiObject(".class","Html.A",".text","Create Deposit");
	}

	/**
	 * Click Home Link
	 *
	 */
	public void clickHome() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Home");
	}

	/**
	 * Click Deposits Link
	 *
	 */
	public void clickDeposits() {
		browser.clickGuiObject(".text", "Deposits", ".class", "Html.A");
	}

	/**
	 * Input Cash On Hand Value
	 * @param cashTotalOnHand
	 */
	public void setCashTotalOnHand(String cashTotalOnHand) {
		browser.setTextField(".id", "totalHand_1_81052_80802", cashTotalOnHand);
		browser.clickGuiObject(".class", "Html.FORM", ".id", "e_Form");
		waitLoading();
	}
	
	public void setTrackNum(String num){
	    browser.setTextField(".id","trackingNumber",num);
	}

	/**
	 * Input Non Cash Total On Hand Value
	 * @param nonCashTotalOnHand
	 */
	public void setNonCashTotalOnHand(String[] nonCashTotalOnHand) {
		if(!StringUtil.isEmpty(nonCashTotalOnHand[0])){
			setPersonalCheckTotalOnHand(nonCashTotalOnHand[0]);			
		}
		if(nonCashTotalOnHand.length>1&&!StringUtil.isEmpty(nonCashTotalOnHand[1])){
			setMoneyOrderTotalOnHand(nonCashTotalOnHand[1]);
		}
		if(nonCashTotalOnHand.length>2&&!StringUtil.isEmpty(nonCashTotalOnHand[2])){
			setCertifiedCheckTotalOnHand(nonCashTotalOnHand[2]);
		}
		if(nonCashTotalOnHand.length>3&&!StringUtil.isEmpty(nonCashTotalOnHand[3])){
			setTravellersCheckTotalOnHand(nonCashTotalOnHand[3]);
		}
	}

	/**
	 * Input peronal check on Hand Value
	 * @param nonCashTotalOnHand
	 */
	public void setPersonalCheckTotalOnHand(String nonCashTotalOnHand) {
		browser.setTextField(".id", "totalHand_2_81055_80802",
				nonCashTotalOnHand);
		browser.clickGuiObject(".class", "Html.FORM", ".id", "e_Form");
		waitLoading();
	}

	/**
	 * Input Money Order On Hand Value
	 * @param nonCashTotalOnHand
	 */
	public void setMoneyOrderTotalOnHand(String nonCashTotalOnHand) {
		browser.setTextField(".id", "totalHand_2_81057_80802",
				nonCashTotalOnHand);
		browser.clickGuiObject(".class", "Html.FORM", ".id", "e_Form");
		waitLoading();
	}

	/**
	 * Input Certified check On Hand Value
	 * @param nonCashTotalOnHand
	 */
	public void setCertifiedCheckTotalOnHand(String nonCashTotalOnHand) {
		browser.setTextField(".id", "totalHand_2_81058_80802",
				nonCashTotalOnHand);
		browser.clickGuiObject(".class", "Html.FORM", ".id", "e_Form");
		waitLoading();
	}

	/**
	 * Input Travellers Check Total Value
	 * @param nonCashTotalOnHand
	 */
	public void setTravellersCheckTotalOnHand(String nonCashTotalOnHand) {
		browser.setTextField(".id", "totalHand_2_81056_80802",
				nonCashTotalOnHand);
		browser.clickGuiObject(".class", "Html.FORM", ".id", "e_Form");
		waitLoading();
	}

	/**
	 * Input Note Information
	 * @param note
	 */
	public void setNote(String note) {
		browser.setTextArea(".id", "depositNote", note);
	}
	/**
	 * Get the Deposite status 
	 * @param depositID -- deposit ID
	 * @return -- deposit status
	 */
	public String getDepositStatus(String depositID){
	    IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^Deposit ID\\s*Tracking No",false));
	    
	    String status=objs[0].getProperty(".text").toString().split(depositID)[1].trim().split(" ")[0];
	    Browser.unregister(objs);
	    
	    return status;
	}
	
	/**
	 * This method used to parse note table to get note info
	 * @return all note info
	 */
	public String retrieveNoteInfo(){
		String note = "";
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^Notes:.*",false));
		note = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return note;
	}
	
	/**
	 * This method used to verify adjustment note info correct
	 * @param adjust
	 */
	public void verifyNoteInfo(AdjustmentInfo adjust){
		Browser.sleep(OrmsConstants.SHORT_SLEEP_BEFORE_CHECK);
		String note = this.retrieveNoteInfo();
		adjust.adjustUser = adjust.adjustUser.replaceAll(",", ", ");
		if(!note.contains(adjust.adjustUser)){
			throw new ErrorOnPageException("Adjust User not correct.");
		}
		
		String adjustNote = note.split(adjust.adjustUser)[2].split("Adjustment")[1].trim();
		String adjustDate = DateFunctions.formatDate(note.split(adjust.adjustUser+" - ")[2].split(" ")[0]);
		if(!adjustDate.equalsIgnoreCase(adjust.reconcileDate)){
			throw new ErrorOnPageException("Adjustment Date Not Correct.");
		}
		if(!adjustNote.matches(adjust.adjustmentID+" manually reconciled: "+adjust.note)){
			throw new ErrorOnPageException("Adjustment Note Format Not Correct.");
		}
	}
	
	public void clickAdjustmentsTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Adjustments");
	}
	
	/**
	 * Get deposit adjustment.
	 * @return like 0.74  or -0.74
	 */
	public String getDepositAdjustment() {
		String trValue = browser.getObjectText(".class", "HTML.TR", ".text", new RegularExpression("^Deposit Sub-Total",false));
		String[] trValue1 = trValue.split(" ");
		String depositAdj = trValue1[trValue1.length - 1];
		if(depositAdj.startsWith("\\(")){
			depositAdj = depositAdj.replaceAll("(\\()|(\\))|(\\$)", StringUtil.EMPTY);
			depositAdj = "-" + depositAdj;
		}
		return depositAdj;
	}
}
