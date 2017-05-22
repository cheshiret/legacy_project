package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:@Description: set up education info when add privilege item to order cart
 *               when business rule 'IF parameter EDUCATION Type not on file, Customer must PROVIDE parameter EDUCATION type information in order to purchase' is violate.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Mar 8, 2012
 */
public class LicMgrConfirmEducationWidget extends DialogWidget {

	private static LicMgrConfirmEducationWidget instance = null;

	public static LicMgrConfirmEducationWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrConfirmEducationWidget();
		}
		return instance;
	}

	public void setEduNumber(String eduNumber){
		browser.setTextField(".id", new RegularExpression("CustomerEducationView-\\d+\\.educationNumber", false), eduNumber, true);
	}
	
	public void selectEduState(String state){
		browser.selectDropdownList(".id", new RegularExpression("CustomerEducationView-\\d+\\.state", false), state, true);
	}

	public void selectEduCountry(String country){
		browser.selectDropdownList(".id", new RegularExpression("CustomerEducationView-\\d+\\.country", false), country, true);
	}
	
	public void setUpEduConfirmInfo(String eduNumber, String state, String country){
		if(null != eduNumber){
			this.setEduNumber(eduNumber);
		}
		if(null != state){
			this.selectEduState(state);
		}
		if(null != country){
			this.selectEduCountry(country);	
		}
	}
}
