/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.marina;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:https://orms-torqa3.dev.activenetwork.com/SwitchLogic.do: SlipDetailPage->Charges Tab
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author sborjigin
 * @Date  May 9, 2014
 */
public class OrmsSlipReservationChargesPage extends OrmsSlipReservationDetailsPage {

	private static OrmsSlipReservationChargesPage _instance = null;

	private OrmsSlipReservationChargesPage() {
	}

	public static OrmsSlipReservationChargesPage getInstance() {
		if (_instance == null) {
			_instance = new OrmsSlipReservationChargesPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".text", "Move POS Sale");
	}
	
	protected Property[] posSaleCheckBox(){
		return Property.toPropertyArray(".id",new RegularExpression("^(POS)?OrderView(-\\d+)?.id",false),".class","Html.SELECT.checkbox");
	}
	protected Property[] movePosSale(){
		return Property.toPropertyArray(".text","Move POS Sale");
	}
	protected Property[] slipChargesTable(){
		return Property.toPropertyArray(".id",new RegularExpression("grid_[0-9]+", false));
	}
	/**Get Slip Charges Table*/
	public IHtmlObject[] getSlipChargesTable() {
		return browser.getTableTestObject(slipChargesTable());
	}
	
	/**
	 * Select Pos from slip charges tab
	 * @param ordNum
	 */
	public void selectPOSFromSlipChargesTab(String ordNum) {
		IHtmlObject[] objs = this.getSlipChargesTable();	
		int index = ((IHtmlTable)objs[objs.length-1]).findRow(1, ordNum) -1;
		
		Browser.unregister(objs);
		browser.selectCheckBox(posSaleCheckBox(), index);
	}
	public void clickMovePosSaleButton() {
		browser.clickGuiObject(movePosSale());
	}
	/**
	 * This method executes the work flows of moving given POS sale Number
	 */
	
	/**
	 * Move Slip Charges
	 * @param ordNum
	 */
	public void moveSlipCharge(String ordNum) {
		this.selectPOSFromSlipChargesTab(ordNum);
		this.clickMovePosSaleButton();
		ajax.waitLoading();
	}

}
