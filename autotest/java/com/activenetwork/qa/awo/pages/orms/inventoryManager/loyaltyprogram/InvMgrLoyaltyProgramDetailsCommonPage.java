package com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram;

import com.activenetwork.qa.testapi.util.Property;
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
 * @Date  Mar 17, 2014
 */
abstract class InvMgrLoyaltyProgramDetailsCommonPage extends InvMgrLoyaltyProgramCommonPage {
	
	private Property[] programID() {
		return Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression("^Program ID", false));
	}
	
	private Property[] name() {
		return Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression("^Name", false));
	}
	
	private Property[] coverage() {
		return Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression("^Coverage", false));
	}
	
	private String getAttributeValueByName(String name, Property properties[]) {
		String text = browser.getObjectText(properties).replace(name, StringUtil.EMPTY).trim();
		
		return text;
	}
	
	public String getProgramID() {
		return getAttributeValueByName("Program ID", programID());
	}
	
	public String getProgramLoyaltyName() {
		return getAttributeValueByName("Name", name());
	}
	
	public String getCoverage() {
		return getAttributeValueByName("Coverage", coverage());
	}
}
