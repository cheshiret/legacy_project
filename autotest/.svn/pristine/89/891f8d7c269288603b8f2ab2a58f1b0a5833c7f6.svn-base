package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: Harvest Report Filed page, shown after report a harvest successfully
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Jun 20, 2013
 */
public class HFHarvestReportFiledPage extends HFHeaderBar {
	private static HFHarvestReportFiledPage _instance = null;

	public static HFHarvestReportFiledPage getInstance() {
		if (null == _instance)
			_instance = new HFHarvestReportFiledPage();

		return _instance;
	}
	
	protected HFHarvestReportFiledPage() {
	}
	
	private Property[] getHarvestConfirmAttrsRootDivProp() {
		return Property.toPropertyArray(".id", "HarvestConfirmReportKit_rootGroup_attrs");
	}
	
	private Property[] getConfirmNumTextFieldProp() {
		return Property.toPropertyArray(".id", new RegularExpression("^AconfirmNumber_\\d+", false));
	}
	
	private Property[] getDoneBtnProp() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Done");
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.getHarvestConfirmAttrsRootDivProp());
	}
	
	public String getConfirmationNum() {
		return browser.getTextFieldValue(this.getConfirmNumTextFieldProp());
	}
	
	public void clickDone() {
		browser.clickGuiObject(this.getDoneBtnProp());
	}
}
