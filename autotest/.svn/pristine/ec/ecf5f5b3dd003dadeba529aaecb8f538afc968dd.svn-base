package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.page.HtmlPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author swang5
 * @Date  Jul 14, 2011
 */
public class OrmsAffectedReservationsPopupPage  extends HtmlPopupPage {
	private static OrmsAffectedReservationsPopupPage _instance;
	
	protected OrmsAffectedReservationsPopupPage () {
		super("title",new RegularExpression("Affected Reservations.*",false));
	}
	
	public static OrmsAffectedReservationsPopupPage getInstance() {
		if(null==_instance) {
			_instance=new OrmsAffectedReservationsPopupPage();
		}
		
		return _instance;
	}
	public void clickOK() {
		popup.clickGuiObject(".class","Html.BUTTON",".text","OK");
	}
	
	public void clickCancel() {
		popup.clickGuiObject(".class","Html.BUTTON",".text","Cancel");
	}
	
	private void selectAffectedReservations(String affectedReservations, int index) {
		String value = "affected_res_instr_rd_"+index;
		IHtmlObject[] objs=popup.getRadioButton(".id", value);
		if(objs.length<1) {
			Browser.unregister(objs);
			objs=popup.getRadioButton(".id", value,".value", affectedReservations);
		}
		if(objs.length>0) {
			((IRadioButton)objs[0]).select();
		}
	}
	
	public void selectHonorReservations() {
		selectAffectedReservations("Honor Reservations", 0);
	}
	
	public void selectCancelReservations() {
		selectAffectedReservations("Cancel Reservations", 1);
	}
	
	public void selectCancelReservationWithFullRefund() {
		selectAffectedReservations("Cancel Reservations With Full Refund", 2);
	}
}
