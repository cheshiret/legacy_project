package com.activenetwork.qa.awo.pages.orms.common.marina;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description: This is the slip contract details page, we can get to this page by click contract ID in slip order detail page or select 'Slip Contract'
 * from top menu, search the contract and click contract id
 * @author pchen
 * @Date  June 04, 2013
 */
public class OrmsSlipContractDetailsPage extends OrmsPage {

	private static OrmsSlipContractDetailsPage _instance = null;
	
	protected OrmsSlipContractDetailsPage() {}
	
	public static OrmsSlipContractDetailsPage getInstance() {
		if(_instance == null) {
			_instance = new OrmsSlipContractDetailsPage();
		}
		
		return _instance;
	}
	
	protected Property[] chargesLabelLinkProp(){
		return Property.concatPropertyArray(span(), ".text","Charges");
	}
	
	protected Property[] errorMessageProp(){
		return Property.concatPropertyArray(div(), ".className","message msgerror");
	}
	
	protected Property[] okButtonProp(){
		return Property.concatPropertyArray(a(), ".text", "OK");
	}
	
	protected Property[] numberOfSlipReservationsDivProp(){
		return Property.concatPropertyArray(div(), ".text",new RegularExpression("^# of Slip Reservations.*",false));
	}
	
	protected Property[] phoneDivProp(){
		return Property.concatPropertyArray(div(), ".text",new RegularExpression("^Phone.*",false));
	}
	
	protected Property[] addNewResSpanProp(){
		return Property.concatPropertyArray(span(), ".text", "Add New Res");
	}
	
	protected Property[] addNewResButtonProp(){
		return Property.concatPropertyArray(a(), ".text", "Add New Res");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "slipContractDetailsTabs");
	}
	
	public void clickAddNewRes(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New Res");
	}
	
	public void clickCharges(){
		browser.clickGuiObject(this.chargesLabelLinkProp());
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(this.errorMessageProp());
	}
	
	public void clickOk(){
		browser.clickGuiObject(this.okButtonProp());
	}
	
	public boolean chargeLabelIsExisting(){
		return browser.checkHtmlObjectDisplayed(this.chargesLabelLinkProp());
	}
	
	public int getNumberOfSlipReservations(){
		String text = browser.getObjectText(this.numberOfSlipReservationsDivProp());
		int number = Integer.valueOf(text.replaceAll("# of Slip Reservations", "").trim());
		
		return number;
	}
	
	public String getPhone(){
		String text = browser.getObjectText(this.phoneDivProp());
		return text.replaceAll("Phone", "").trim();
	}
	
	public boolean checkAddNewResButtonIsDisplay(){
		return browser.checkHtmlObjectDisplayed(this.addNewResSpanProp());
	}
	
	public boolean checkAddNewResButtonIsEnabled(){
		return browser.checkHtmlObjectEnabled(this.addNewResButtonProp());
	}
	
}
