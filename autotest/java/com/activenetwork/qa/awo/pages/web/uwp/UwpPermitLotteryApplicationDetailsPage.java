package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Feb 17, 2012
 */
public class UwpPermitLotteryApplicationDetailsPage extends UwpPage {
	private static UwpPermitLotteryApplicationDetailsPage _instance = null;
	
	private UwpPermitLotteryApplicationDetailsPage() {}
	
	public static UwpPermitLotteryApplicationDetailsPage getInstance() {
		if(null == _instance) {
			_instance = new UwpPermitLotteryApplicationDetailsPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".className", "contenthdr", ".text", "Lottery Application");
	}
	
	public String getLotteryApplicationStatus() {
		return getLotteryApplicationTableObject().getCellValue(2, 0).split("Status:")[1].trim();
	}
	
	public String getLotteryName() {
		return getLotteryApplicationTableObject().getCellValue(0, 1).split("Lottery:")[1].trim();
	}
	
	public String getLotteryApplicationNum() {
		return getLotteryApplicationTableObject().getCellValue(0, 0).split("Application #:")[1].trim();
	}
	
	public String getFacilityName() {
		return getLotteryApplicationTableObject().getCellValue(1, 1).split("Facility:")[1].trim();
	}
	
	/**
	 * Get Lottery Application table object
	 * @return
	 */
	private IHtmlTable getLotteryApplicationTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".className", "contable splitPanel");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find Lottery Application details info table object.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		return table;
	}
	
	public void clickAcceptReservation() {
		browser.clickGuiObject(".className", "all continueshop", ".text", "Accept Reservation", true);
	}
	
	public void clickDeclineReservation() {
		browser.clickGuiObject(".className", "all continueshop", ".text", "Decline Reservation", true);
	}
}
