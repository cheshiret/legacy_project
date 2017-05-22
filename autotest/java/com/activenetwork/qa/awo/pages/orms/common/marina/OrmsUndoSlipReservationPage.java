package com.activenetwork.qa.awo.pages.orms.common.marina;

import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsUndoReservationPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsUndoSlipReservationPage extends OrmsUndoReservationPage{

	static private OrmsUndoSlipReservationPage _instance = null;
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsUndoSlipReservationPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsUndoSlipReservationPage();
		}

		return _instance;
	}
	
	/**
	 * Undo checkout
	 * @param resID
	 * @throws PageNotFoundException
	 */
	public void undoCheckOut(String resID) throws PageNotFoundException {
		this.setResNum(resID);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
		this.selectOrder(resID);
		this.clickUndoCheckout();
		ajax.waitLoading();
	}
	
	/**Click Go button*/
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	public void setResNum(String resID) {
		browser.setTextField(".id", new RegularExpression("MarinaHomeOrderSearchCriteria-\\d+\\.orderNumber", false), resID);
	}
	
	/**Select one order number*/
	public void selectOrder(String resID) {
		IHtmlObject[] objs = browser.getHtmlObject(".text", resID, ".class","Html.TD");
		if (objs.length < 1) {
			throw new RuntimeException("object not founded");
		}

		IHtmlObject tr = (IHtmlObject)objs[0].getParent();
		browser.selectRadioButton(Property.toPropertyArray(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems",false)), true,0, tr);
		
		Browser.unregister(objs);
		Browser.unregister(tr);
	}
	
	public String getErrorMessage() {
		String err = null;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "message msgerror");
		if(objs.length<1)
			return err;
		err = objs[0].text();
		return err;
	}
	
}
