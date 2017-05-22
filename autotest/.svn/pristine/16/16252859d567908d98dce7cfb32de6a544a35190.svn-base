/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.pos;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author ssong
 * @Date  Jan 25, 2014
 */
public class OrmsPurchasePOSAdditionalInfoPage extends OrmsPage {
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsPurchasePOSAdditionalInfoPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsPurchasePOSAdditionalInfoPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsPurchasePOSAdditionalInfoPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsPurchasePOSAdditionalInfoPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
//		return browser.checkHtmlObjectExists(passNumInput());
		//Quentin[20140707] Additional Information page for Boat Launch Permit purchase progress has different page mark
		return checkLastTrailValueIs("Additional Information");
	}
	
	protected Property[] passNumInput(){
		return Property.concatPropertyArray(input("text"), ".id",new RegularExpression("CustomerPassView-\\d+\\.passNumber", false));
	}
	
	public boolean isPassNumExists() {
		return browser.checkHtmlObjectExists(passNumInput());
	}
	
	public void setPassNum(String num){
		browser.setTextField(passNumInput(), num);
	}
	
	public void clickOk(){
		browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("OK",false));
	}

	private Property[] permitNum() {
		return Property.toPropertyArray(".id", new RegularExpression("BoatLaunchPermitVO-\\d+\\.permitNumber", false));
	}
	
	private Property[] variableUnitPrice() {
		return Property.toPropertyArray(".id", new RegularExpression("BoatLaunchPermitVO-\\d+\\.variableUnitPrice", false));
	}
	
	public boolean isPermitNumExists() {
		return browser.checkHtmlObjectExists(permitNum());
	}
	
	public void setPermitNum(String num) {
		browser.setTextField(permitNum(), num);
	}
	
	public void setVariableUnitPrice(String price) {
		browser.setTextField(variableUnitPrice(), price);
	}
}
