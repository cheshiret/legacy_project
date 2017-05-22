/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.event;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:
 * @Task#:
 * 
 * @author Vivian
 * @Date  Apr 29, 2014
 */
public class OrmsEventChargesPage extends OrmsEventDetailPage{
	
	static private OrmsEventChargesPage _instance = null;
	
	protected OrmsEventChargesPage() {}
	
	static public OrmsEventChargesPage getInstance(){
		if(null == _instance){
			_instance = new OrmsEventChargesPage();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return 	browser.checkHtmlObjectExists(".href", new RegularExpression(
				"\"addEventChargesToCart\"", false), ".text", new RegularExpression("Add To Cart", false));
	}
	
	/**Click MovePOSSale in the event charges tab*/
	public void clickEventChargesTabMovePOSSale() {
		browser.clickGuiObject(".href", new RegularExpression(
				"\"moveEventCharges\"", false), ".text", "Move POS Sale");
	}

	/**Click Add to cart in the event charges tab*/
	public void clickEventChargesTabAddToCart() {
		browser.clickGuiObject(".href", new RegularExpression(
				"\"addEventChargesToCart\"", false), ".text", "Add To Cart");
	}
	
	/**Get Event Charges Table*/
	public IHtmlObject[] getEventChargesTable() {
		return browser.getTableTestObject(".id",new RegularExpression("grid_[0-9]+", false));
	}
	
	/**
	 * Select Pos from event charges tab
	 * @param ordNum
	 */
	public void selectPOSFromEventChargesTab(String ordNum) {
		IHtmlObject[] objs = this.getEventChargesTable();	
		int index = ((IHtmlTable)objs[objs.length-1]).findRow(1, ordNum) -1;
		
		Browser.unregister(objs);
		RegularExpression pattern=new RegularExpression("^(POS)?OrderView(-\\d+)?.id",false);
		browser.selectCheckBox(".id", pattern, index);
	}
	
	/**
	 * This method executes the work flows of checking existence of a POS order in the Event charge Tab. 
	 * The work flow starts from the event details page and ends at  event details page.
	 * @param posID - the pos's id to checked
	 * @return true if the pos exist in the event charge tab and false otherwise
	 */
	public boolean isPOSSalesExistInEventCharge(String posSaleNum) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",new RegularExpression("grid_[0-9]+", false));;	
		int index = ((IHtmlTable)objs[objs.length-1]).findRow(1, posSaleNum) -1;
		Browser.unregister(objs);
		if(0>index)
			return false;
		else
			return true;
		
	}



	/**
	 * Move Event Charges
	 * @param ordNum
	 */
	public void moveEventCharge(String ordNum) {
		this.selectPOSFromEventChargesTab(ordNum);
		this.clickEventChargesTabMovePOSSale();
		ajax.waitLoading();
	}	
	
	public boolean isPOSSalesCheckBoxEnableFromEventChargeTab(String posSaleNum){
		IHtmlObject[] objs = this.getEventChargesTable();	
		int index = ((IHtmlTable)objs[objs.length-1]).findRow(1, posSaleNum) -1;
		
		RegularExpression pattern=new RegularExpression("^(POS)?OrderView(-\\d+)?.id",false);
		IHtmlObject[] checkBoxObjs = browser.getCheckBox(".id", pattern);
		boolean isEnable = checkBoxObjs[index].isEnabled();
		
		Browser.unregister(checkBoxObjs);
		Browser.unregister(objs);
		return isEnable;
	}

}
