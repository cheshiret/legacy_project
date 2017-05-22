package com.activenetwork.qa.awo.pages.orms.common.ticket;

import com.activenetwork.qa.awo.pages.orms.common.OrmsFeesPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;

public class OrmsTicketLotteryApplicationFeesPage extends OrmsFeesPage {
	
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsTicketLotteryApplicationFeesPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsTicketLotteryApplicationFeesPage() {
//		fees = new ArrayList();
	}
	
	/**
	 * @return The unique instance of this class.
	 */
	public static OrmsTicketLotteryApplicationFeesPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsTicketLotteryApplicationFeesPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return this.checkLastTrailValueIs("Lottery Application Fees");
	}
	
	public IHtmlObject[] getRAFeeObjects() {
		return browser.getTextField(this.raTransactionFee());
	}
	
	/**
	 * Adjust any RA fee value to the given amount
	 * @param newAmount
	 * @return - an Array of adjusted RA fees: ra fee schedule;original amount
	 */
	public String[] adjustRAFees(double newAmount) {
		IHtmlObject[] raFeeObjs=getRAFeeObjects();
		
		String[] toReturn=new String[raFeeObjs.length];
		
		for(int i=0;i<raFeeObjs.length;i++) {
			
			String raFeeSchedule=getFeeScheduleFromFeeInputId(raFeeObjs[i].getAttributeValue(".id"));
			toReturn[i]=raFeeSchedule;
			((IText) raFeeObjs[i]).setText(newAmount);
			
		}
		
		Browser.unregister(raFeeObjs);
		
		return toReturn;
	}
	
	public String adjustRAFee(double newAmount) {
		IHtmlObject[] raFeeObjs=getRAFeeObjects();
		
		String raFeeSchedule="";
		
		if(raFeeObjs.length>0) {
			
			raFeeSchedule=getFeeScheduleFromFeeInputId(raFeeObjs[0].getAttributeValue(".id"));
			
			((IText) raFeeObjs[0]).setText(newAmount);
			
		}
		
		Browser.unregister(raFeeObjs);
		
		return raFeeSchedule;
	}

}
