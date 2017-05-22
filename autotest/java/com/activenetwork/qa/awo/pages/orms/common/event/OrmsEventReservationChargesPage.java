/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.event;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.Property;
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
public class OrmsEventReservationChargesPage extends OrmsEventDetailPage{
	private static OrmsEventReservationChargesPage _instance = null;
	
	protected OrmsEventReservationChargesPage (){}
	
	public static OrmsEventReservationChargesPage getInstance(){
		if(null == _instance){
			_instance = new OrmsEventReservationChargesPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return 	browser.checkHtmlObjectExists(".id","eventResChargesUI");
	}
	
	/**Select Reservation in the resCharges Tab*/
	public void selectResInResChargesTab(String res) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",".id", new RegularExpression("selectedReservation|I_EventReservationOrderView-\\d+\\.id",false));
//		String s = ((ISelect)objs[0]).get.getProperty(".text").toString();
//		int i = s.indexOf(res);
//		int j = s.indexOf(")", i);
//		String item = s.substring(i, j + 1);
//		((ICheckBox)objs[0]).select();
		ISelect dropMenu=(ISelect) objs[0];
		dropMenu.select(new RegularExpression("^"+res+" \\(.+\\)$",false));
		Browser.unregister(objs);
//		Property[] p = new Property[3];
//		p[0] = new Property(".class", "Html.A");
//		p[1] = new Property(".text", res);
//		p[2] = new Property(".id", new RegularExpression("(CampingOrderView.orderName|I_EventReservationOrderView.orderName)",false));
//		browser.searchObjectWaitExists(p, Browser.SLEEP);
	}
	
	public Property [] selectReservation(){
		return Property.toPropertyArray(".id","selectedReservation");
	}
	

	/**Get reservation Charges Table*/
	public IHtmlObject[] getResChargesTable() {
		return browser.getTableTestObject(".id",new RegularExpression("grid_[0-9]+", false));
	}
	
	/**Click MovePOSSale in the reservation Charges tab*/
	public void clickResChargesTabMovePOSSale() {
		browser.clickGuiObject(".text", "Move POS Sale");
	}
	
	/**Click add to cart in the reservation charges tab*/
	public void clickResChargesTabAddToCart() {
		browser.clickGuiObject(".href", new RegularExpression(
				"\"addReservationChargesToCart\"", false), ".text","Add To Cart");
	}
	/**Select reservation from the drop down list*/
	public void selectResFromDropDownList(String resNUm) {
		IHtmlObject[] objs = browser.getDropdownList(selectReservation());
		if(objs.length == 0) {
			throw new ItemNotFoundException("Can't find any Selected Reservation privilege drop down list objects.");
		}
		((ISelect)objs[0]).select(new RegularExpression(resNUm, false));
		Browser.unregister(objs);

	}

	/**
	 * This method executes the work flows of checking existence of a POS order in the Reservation charge Tab. 
	 * The work flow starts from the event details page and ends at  event details page.
	 * @param posID - the pos's id to checked
	 * @return true if the pos exist in the reservation charge tab and false otherwise
	 */
	public boolean isPOSSalesExistInReservationCharge(String posSaleNum) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",new RegularExpression("grid_[0-9]+", false));;	
		int index = ((IHtmlTable)objs[objs.length-1]).findRow(1, posSaleNum) -1;
		Browser.unregister(objs);
		if(0>index)
			return false;
		else
			return true;
		
	}
	public boolean isPosExistInSpecificResCharge(String resNum,String posSaleNum,boolean expIsExisting) {
		selectResFromDropDownList(resNum);
		ajax.waitLoading();
		boolean actualIsExisting=this.isPOSSalesExistInReservationCharge(posSaleNum);
		return 	MiscFunctions.compareResult("Verify POS Info Is Existing", expIsExisting, actualIsExisting);
	}
	
	/**
	 * Move Reservation charge
	 * @param res
	 * @param pos
	 */
	public void moveResCharge(String res, String pos) {
		selectResInResChargesTab(res);
		IHtmlObject[] objs = this.getResChargesTable();
		int row =((IHtmlTable)objs[objs.length-1]).findRow(1, pos);
		
		Browser.unregister(objs);
		browser.selectCheckBox(".id", new RegularExpression("((POS)?OrderView.id)|(GenericGrid-\\d+\\.selectedItems)", false),row-1);
		this.clickResChargesTabMovePOSSale();
	}
}
