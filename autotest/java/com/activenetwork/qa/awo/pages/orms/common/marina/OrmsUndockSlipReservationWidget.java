package com.activenetwork.qa.awo.pages.orms.common.marina;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 11, 2013
 */
public class OrmsUndockSlipReservationWidget extends DialogWidget {
	private static OrmsUndockSlipReservationWidget _instance = null;
	
	private OrmsUndockSlipReservationWidget() {
		super("UnDock Slip Reservation");
	}
	
	public static OrmsUndockSlipReservationWidget getInstance() {
		if(_instance == null) {
			_instance = new OrmsUndockSlipReservationWidget();
		}
		
		return _instance;
	}
	
	public String getArrivalDate() {
		return null;
	}
	
	public String getDepartureDate() {
		return null;
	}
	
	public void setExpectedDockingDate(String dockingDate) {
		browser.setTextField(".id", new RegularExpression("MarinaTemporarilyUndockDialog-\\d+\\.expectedDockingDate_ForDisplay", false), dockingDate, 0, IText.Event.ONCHANGE, IText.Event.LOSEFOCUS);
	}
}
