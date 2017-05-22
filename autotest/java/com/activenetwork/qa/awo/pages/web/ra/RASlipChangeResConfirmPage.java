package com.activenetwork.qa.awo.pages.web.ra;

import com.activenetwork.qa.awo.pages.web.uwp.UwpChangeReservationConfirmPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Nov 19, 2013
 */
public class RASlipChangeResConfirmPage extends UwpChangeReservationConfirmPage{
	static class SingletonHolder {
		protected static RASlipChangeResConfirmPage _instance = new RASlipChangeResConfirmPage();
	}

	protected RASlipChangeResConfirmPage() {
	}

	public static RASlipChangeResConfirmPage getInstance() {
		return SingletonHolder._instance;
	}
	
	protected Property[] mesAlert(String alertMesRegx){
	     return Property.concatPropertyArray(div(),".className", "msg alert", ".text", new RegularExpression(alertMesRegx, false));
	}
	
	public boolean isAlertMsgExist(String errorMesRegx) {
		return browser.checkHtmlObjectExists(mesAlert(errorMesRegx));
	}
	
	public void verifyAlertMsgExist(String msg, boolean isExist) {
		String info = isExist ? " " : " not ";
		if (this.isAlertMsgExist(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + info + "exist!");
		}
		logger.info("The message: " + msg + " does " + info + "exist!");
	}
}
