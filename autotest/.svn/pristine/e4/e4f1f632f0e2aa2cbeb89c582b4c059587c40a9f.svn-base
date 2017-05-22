package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Dec 19, 2011
 */
public class LicMgrAddEFTScheduleWidget extends DialogWidget {
	
	public static LicMgrAddEFTScheduleWidget _instance = null;
	
	protected LicMgrAddEFTScheduleWidget() {}
	
	public static LicMgrAddEFTScheduleWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrAddEFTScheduleWidget();
		}
		
		return _instance;
	}
	
	/**
	 * Select EFT frequency value
	 * @param frequency
	 */
	public void selectEFTFrequency(String frequency) {
		browser.selectDropdownList(".id", new RegularExpression("EFTScheduleView-\\d+\\.eftFrequency", false), frequency);
	}
	
	public void setName(String name) {
		browser.setTextField(".id", new RegularExpression("EFTScheduleView-\\d+\\.name", false), name);
	}
	
	/**
	 * Set transmission day offset value
	 * @param transDayOffset
	 */
	public void setTransmissionDayOffset(String transDayOffset) {
		browser.setTextField(".id", new RegularExpression("EFTScheduleView-\\d+\\.transDayOffset", false), transDayOffset);
	}
	
	/**
	 * Select start day value
	 * @param startDay
	 */
	public void selectStartDay(String startDay) {
		browser.selectDropdownList(".id", new RegularExpression("EFTScheduleView-\\d+\\.startDay", false), startDay);
	}
	
	/**
	 * Select start month value
	 * @param startMonth
	 */
	public void selectStartMonth(String startMonth) {
		browser.selectDropdownList(".id", new RegularExpression("EFTScheduleView-\\d+\\.startMonth", false), startMonth);
	}
	
	/**
	 * Set invoice day offset value
	 * @param invoiceDayOffset
	 */
	public void setInvoiceDayOffset(String invoiceDayOffset) {
		browser.setTextField(".id", new RegularExpression("EFTScheduleView-\\d+\\.invoiceDayOffset", false), invoiceDayOffset);
	}
	
	public boolean checkStatusDropDownEnable(){
		return browser.checkHtmlObjectEnabled(".id",new RegularExpression("EFTScheduleView-\\d+.status", false));
	}
	
	public String getSelectedStatus(){
		return browser.getDropdownListValue(".id",new RegularExpression("EFTScheduleView-\\d+.status", false));
	}
	
	/**
	 * Setup EFT schedule info in Add EFT Schedule widget
	 * @param schedule
	 */
	public void setupEFTScheduleInfo(EFTScheduleInfo schedule) {
		this.selectEFTFrequency(schedule.frequency);
		ajax.waitLoading();
		if(!StringUtil.isEmpty(schedule.name)) {
			setName(schedule.name);
		}
		if(!StringUtil.isEmpty(schedule.startMonth)) {
			this.selectStartMonth(schedule.startMonth);
			ajax.waitLoading();
		}
		if(!StringUtil.isEmpty(schedule.startDay)) {
			this.selectStartDay(schedule.startDay);
		}
		if(!StringUtil.isEmpty(schedule.invoiceDayOffset)) {
			this.setInvoiceDayOffset(schedule.invoiceDayOffset);
		}
		this.setTransmissionDayOffset(schedule.transmissionDayOffset);
	}
	
	/**
	 * Get error message
	 * @return
	 */
	public String getErrorMessage() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".className", "message msgerror");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find error message object.");
		}
		String msg = objs[0].getProperty(".text");
		Browser.unregister(objs);
		
		return msg;
	}
}
