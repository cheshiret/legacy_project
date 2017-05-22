package com.activenetwork.qa.awo.pages.orms.common.marina;

import java.util.List;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Nov 22, 2012
 */
public class OrmsSlipAutoSelectDiscountPage extends OrmsPage {

	private static OrmsSlipAutoSelectDiscountPage _instance = null;
	
	private OrmsSlipAutoSelectDiscountPage() {}
	
	public static OrmsSlipAutoSelectDiscountPage getInstance() {
		if(_instance == null) {
			_instance = new OrmsSlipAutoSelectDiscountPage();
		}
		
		return _instance;
	}
	
	private String prefix = "SlipOrderItemDiscountsInfo-\\d+\\.";
	
	protected Property[] baseDiscountDropdownList() {  
		return Property.toPropertyArray(".id",new RegularExpression(prefix + "baseDiscountId",false));
	}
	
	protected Property[] additionalDiscountDropdownList() {  
		return Property.toPropertyArray(".id",new RegularExpression(prefix + "additionalDiscountId",false));
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("SlipOrderItemDiscountsInfo-\\d+\\.(baseDiscountId|additionalDiscountId)", false));
	}
	
	public void selectDiscountSchedule(String name) {
		browser.selectDropdownList(baseDiscountDropdownList(), name);
	}
	
	public void selectAdditionalDiscountSchedule(String name){
		browser.selectDropdownList(additionalDiscountDropdownList(), name);
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public List<String> getBaseDiscountElements(){
		return browser.getDropdownElements(baseDiscountDropdownList());
	}
	
	public List<String> getAdditionalDiscountElements(){
		return browser.getDropdownElements(additionalDiscountDropdownList());
	}
	
}
