/*
 * Created on Sep 21, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData.DiscountSchdInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author QA
 *
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsDiscountPage extends OrmsPage {
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsDiscountPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsDiscountPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsDiscountPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsDiscountPage();
		}

		return _instance;
	}

	private RegularExpression discount_dropdown_Id = new RegularExpression("(^discounts.*selected$)|(SlipOrderItemDiscountsInfo-\\d+\\.baseDiscountId)",
			false);
	private RegularExpression apply_unit_type = new RegularExpression(
			"(manual.discounts.unit_type.0.*)|(DiscountScheduleVO-\\d+\\.unitType)", false);
	RegularExpression rate_id = new RegularExpression(
			"(manual.discounts.overrideAmount.0.*)|(DiscountScheduleVO-\\d+\\.overrideAmount)", false);

	/** Determine if the associated web object exists */
	public boolean exists() {
		// return browser.checkTestObjectExists(".class","Html.A",".text","OK");
		boolean exist=browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id",
				discount_dropdown_Id)||browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^Applied Discounts.*",false));
		return exist;

	}

	/** Click the "OK" link */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A",".text","OK");
		ajax.waitLoading();
		if(this.exists()){
			browser.clickGuiObject(".class", "Html.A",".text","OK");
		}
//		Property[] properties=Property.toPropertyArray(".class", "Html.DIV", ".className", new RegularExpression("(cart )?formSubmitButton",false),".text","OK");
//		browser.focusOn(properties, 0);
//		if(browser.checkHtmlObjectExists(properties)){
//			browser.clickGuiObject(properties);
//		}
	}

	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	/**
	 * Get discount type
	 *
	 * @param subString
	 * @return
	 */
	public int getDiscountType(String subString) {
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.SELECT",
				".id", discount_dropdown_Id);
		IHtmlObject[] objs2 = objs1[0].getChildren();
		int toReturn = -1;

		for (int i = 0; i < objs2.length; i++) {

			IHtmlObject map = (IHtmlObject) objs2[i];

			if (map.getProperty(".text").toString().indexOf(subString) != -1) {
				return i;
			}

		}
		Browser.unregister(objs1);
		Browser.unregister(objs2);
		return toReturn;
	}

	/**
	 * Apply additional discount by discount name
	 *
	 * @param disct
	 */
	public void applyAdditionalDiscout(String disct) {
		browser.selectDropdownList(".id", new RegularExpression("discounts.teir2.selected|SlipOrderItemDiscountsInfo-\\d+\\.additionalDiscountId",false), disct);
		ajax.waitLoading();
	}

	/**
	 * Apply additonal discount by the index of additonal discount
	 *
	 * @param index
	 */
	public void applyAdditionalDiscout(int index) {
		browser.selectDropdownList(".id", discount_dropdown_Id, index);
	}

	public void selectDiscountApplyUnitType(String useFeeApply, int index) {
		browser.selectDropdownList(".id",apply_unit_type,useFeeApply,index);
	}

	/**
	 * Input use fee
	 *
	 * @param useFee
	 * @param index
	 */
	public void setRate(String rate, int index) {
		browser.setTextField(".id", rate_id, rate,index);
	}

	/**
	 * Apply Base Discount
	 *
	 * @param disct
	 */
	public void applyBaseDiscount(String disct) {
		browser.selectDropdownList(".id",discount_dropdown_Id,disct);
		ajax.waitLoading();
	}
	
	public boolean isDiscountExist(String disct){
		List<String> discount=browser.getDropdownElements(".id",discount_dropdown_Id);
		if(discount.contains(disct)){
			return true;
		}else{
			return false;
		}
		
	}

	/**
	 * Set up basic discount
	 * @param index
	 * @param useFee
	 */
	public void setupBasicDiscount(DiscountSchdInfo discount, int index) {
		this.applyBaseDiscount(discount.discountName);
		this.selectDiscountApplyUnitType(discount.unitType, index);
		this.setRate(discount.amount.toString(), index);
	}

}
