package com.activenetwork.qa.awo.pages.orms.operationManager;

import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @author eliang
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OpMgrChangeCollectionStatusPage extends OperationsManagerPage{
	
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OpMgrChangeCollectionStatusPage _instance = null;
	
	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OpMgrChangeCollectionStatusPage(){
		
	}
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OpMgrChangeCollectionStatusPage getInstance() throws PageNotFoundException {
		if (null== _instance){
			_instance = new OpMgrChangeCollectionStatusPage();
		}
		return _instance;
	}
	
	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",new RegularExpression("Change Collection Status Collection Status Good Standing\\s?Doubtful Debt\\s?Uncollectible Debt Reason",false)); //update by pzhu
	}
	
	/**
	 * Select "Good Standing" as collection status
	 */
	public void selectGoodStanding(){
		browser.selectDropdownList(".id", "collStatus", "Good Standing",true);
	}
	
	/**
	 * Select "Doubtful Debt" as collection status
	 */
	public void selectDoubtfulDebt(){
		browser.selectDropdownList(".id", "collStatus", "Doubtful Debt",true);
	}
	
	/**
	 * Select "Uncollectible Debt" as collection status
	 */
	public void selectUncollectibleDebt(){
		browser.selectDropdownList(".id", "collStatus", "Uncollectible Debt",true);
	}
	
	/**
	 * Type the reason of the changing collection status
	 * @param changeReason
	 */
	public void setChangeReason(String changeReason){
		browser.setTextArea(".id", "reasonCollStatus", changeReason);
	}
	
	/**
	 * Click the "Change Collection Status" button
	 */
	public void clickChangeCollectionStatus(){
		browser.clickGuiObject(".class", "Html.A",".href",new RegularExpression("ChangeCollectionStatusEdit",false));
	}
	
	/**
	 * Select the change collection status: Good Standing, Doubtful Debt and Uncollectible Debt
	 */
	public void changeCollectionStatus(String status){
		browser.selectDropdownList(".id", "collStatus", status, true);
		browser.setTextArea(".id", "reasonCollStatus", "The collection status is set as "+status+".");
		browser.clickGuiObject(".class", "Html.A",".href",new RegularExpression("ChangeCollectionStatusEdit",false));
	}
}
