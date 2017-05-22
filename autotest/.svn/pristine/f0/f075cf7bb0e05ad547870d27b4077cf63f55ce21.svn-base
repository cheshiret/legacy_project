package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @Description: Report Harvest Step2 page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Jun 20, 2013
 */
public class HFReportHarvestDetailsPage extends HFHeaderBar {
	private static HFReportHarvestDetailsPage _instance = null;

	public static HFReportHarvestDetailsPage getInstance() {
		if (null == _instance)
			_instance = new HFReportHarvestDetailsPage();

		return _instance;
	}
	
	protected HFReportHarvestDetailsPage() {
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.getReportHarvestBtnProp());
	}
	
	private Property[] getReportHarvestBtnProp() {
		return Property.toPropertyArray(".id", "submitForm_submitForm", ".text", "Report a Harvest");
	}
	
	private Property[] getDateOfKillTextFieldProp() {
		return Property.toPropertyArray(".id", new RegularExpression("^Aquest_1000_-\\d+_\\d+", false), 
				".className", new RegularExpression("DatePickerRenderer hasDatepicker( placeholder placeholder)?", false));
	}
	
	public void setDateOfKill(String date) {	
		browser.setTextField(getDateOfKillTextFieldProp(), date, true, 0, IText.Event.LOSEFOCUS);
	}
	
	public void formatAndSetDateOfKill(String date) {
		date = DateFunctions.formatDate(date);
		this.setDateOfKill(date);
	}
	
	public void clickReportHarvestBtn() {
		browser.clickGuiObject(this.getReportHarvestBtnProp());
	}
	
}
