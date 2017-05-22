package com.activenetwork.qa.awo.pages.web.hf;

import java.util.List;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Privilege Inventory Fulfillment Page. It will be shown when purchase a privilege with an inventory setup, before add to cart.
 * @author Lesley Wang
 * @Date  Aug 28, 2013
 */
public class HFPrivInventoryFulfillmentPage extends HFHeaderBar {
	private static HFPrivInventoryFulfillmentPage _instance = null;

	public static HFPrivInventoryFulfillmentPage getInstance() {
		if (null == _instance)
			_instance = new HFPrivInventoryFulfillmentPage();

		return _instance;
	}
	
	protected HFPrivInventoryFulfillmentPage() {
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] privFulfillmentFormProp() {
		return Property.toPropertyArray(".id", "PrivilegeFulfillmentKit_form");
	}
	
	protected Property[] pageTitleDivProp() {
		return Property.toPropertyArray(".id", "pagetitle");
	}
	
	protected Property[] headerTextDivProp() {
		return Property.toPropertyArray(".id", "titleDescription");
	}
	
	protected Property[] privInfoDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "privileges");
	}
	
	protected Property[] privNameDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "control FLOW");
	}
	
	protected Property[] privLicYearDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "underlineGrey");
	}
	
	protected Property[] fulfillMethodSectionTextDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "enterDirective");
	}
	
	protected Property[] fulfilledByMailMethodRadioBtnProp() {
		return Property.toPropertyArray(".id", "LPrivilegeFulfillmentKit_methodsLayout_m_1");
	}
	
	protected Property[] fulfilledByMailMethodLabelProp() {
		return Property.toPropertyArray(".class", "Html.label", ".for", "LPrivilegeFulfillmentKit_methodsLayout_m_1");
	}
	
	protected Property[] invOnHandMethodRadioBtnProp() {
		return Property.toPropertyArray(".id", "LPrivilegeFulfillmentKit_methodsLayout_m_2");
	}

	protected Property[] invOnHandMethodLabelProp() {
		return Property.toPropertyArray(".class", "Html.label", ".for", "LPrivilegeFulfillmentKit_methodsLayout_m_2");
	}
	
	protected Property[] invOnHandMethodDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "m_2");
	}
	
	protected Property[] groupLabelSpanProp() {
		return Property.toPropertyArray(".class", "Html.Span", ".className", "groupLabel");
	}
	
	protected Property[] invOnHandMethodAttrsDivProp() {
		return Property.toPropertyArray(".id", "PrivilegeFulfillmentKit_m_2_attrs");
	}
	
	protected Property[] continueBtnProp() {
		return Property.toPropertyArray(".id", "submitForm_submitForm");
	}
	
	protected Property[] returnToDetailsLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("/privilegePurchaseDetails\\.do\\?privId=\\d+", false));
	}

	protected Property[] invOnHandTextFieldProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("^Atag_\\d+_(-)?\\d+", false));
	}
	
	protected Property[] invOnHandTextFieldsDivProp(int index) {
		return Property.toPropertyArray(".id", "tag_" + index);
	}
	
	protected Property[] invOnHandTextFieldLabelProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "counter attributeField");
	}
	
	protected Property[] topErrorMsgProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "msg error");
	}
	
	protected Property[] itemErrorMsgProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "error_item");
	}
	
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.privFulfillmentFormProp());
	}
	
	public String getPageTitle() {
		return browser.getObjectText(this.pageTitleDivProp());
	}
	
	public String getHeaderText() {
		return browser.getObjectText(this.headerTextDivProp());
	}
	
	public String getPrivName() {
		return browser.getObjectText(Property.atList(this.privInfoDivProp(), this.privNameDivProp()));
	}
	
	public String getPrivLicYear() {
		String text = browser.getObjectText(Property.atList(this.privInfoDivProp(), this.privLicYearDivProp()));
		text = text.split(":")[1].trim(); // Licence Year: 2013
		return text;
	}
	
	public String getSectionText() {
		return browser.getObjectText(this.fulfillMethodSectionTextDivProp());
	}
	
	public void selectFulfilledByMailMethodRadioBtn() {
		browser.selectRadioButton(this.fulfilledByMailMethodRadioBtnProp(), 0);
	}
	
	public boolean isFulfilledByMailMethodRadioBtnExist() {
		return browser.checkHtmlObjectExists(this.fulfilledByMailMethodRadioBtnProp());
	}
	
	public boolean isFulfilledByMailMethodRadioBtnSelected() {
		return browser.isRadioButtonSelected(this.fulfilledByMailMethodRadioBtnProp());
	}
	
	public String getFulfilledByMailMethodRadioBtnLabel() {
		return browser.getObjectText(this.fulfilledByMailMethodLabelProp());
	}
	
	public void selectInvOnHandMethodRadioBtn() {
		browser.selectRadioButton(this.invOnHandMethodRadioBtnProp(), 0);
	}
	
	public boolean isInvOnHandMethodRadioBtnExist() {
		return browser.checkHtmlObjectExists(this.invOnHandMethodRadioBtnProp());
	}
	
	public String getInvOnHandMethodRadioBtnLabel() {
		return browser.getObjectText(this.invOnHandMethodLabelProp());
	}
	
	public String getInvOnHandMethodText() {
		return browser.getObjectText(Property.atList(this.invOnHandMethodDivProp(), this.groupLabelSpanProp()));
	}
	
	public boolean isInvOnHandMethodAttrsDisplayed() {
		IHtmlObject[] objs = browser.getHtmlObject(this.invOnHandMethodAttrsDivProp());
		if (objs.length < 1) {
			throw new ErrorOnPageException("Can't find Inventory On Hand method attributes");
		}
		String style = objs[0].style("visibility");
		boolean hidden = StringUtil.notEmpty(style) && style.equalsIgnoreCase("hidden");
		Browser.unregister(objs);
		return !hidden;
	}
	
	public int getNumOfInvTypeNumTextFields() {
		IHtmlObject[] objs = browser.getHtmlObject(this.invOnHandTextFieldProp());
		return objs.length;
	}
	
	public String getInvTypeNumTextFieldLabel(int index) {
		return browser.getObjectText(Property.atList(this.invOnHandTextFieldsDivProp(index), this.invOnHandTextFieldLabelProp()));
	}
	
	public String getInvTypeNumTextFieldValue(int index) {
		IHtmlObject[] objs = browser.getTextField(this.invOnHandTextFieldProp());
		if (objs.length < 1 || objs.length <= index) {
			throw new ObjectNotFoundException("Can't find inventory on hand text fields!");
		}
		String value = objs[index].getAttributeValue("value");
		Browser.unregister(objs);
		return value;
	}
	
	public boolean isInvTypeNumTextFieldDisabled(int index) {
		IHtmlObject[] objs = browser.getTextField(this.invOnHandTextFieldProp());
		if (objs.length < 1 || objs.length <= index) {
			throw new ObjectNotFoundException("Can't find inventory on hand text fields!");
		}
		boolean isDisabled = objs[index].getAttributeValue(".isDisabled").equalsIgnoreCase("true");
		Browser.unregister(objs);
		return isDisabled;
	}
	
	public void verifyInvTypeNumTextFieldDisabled(int index, boolean isDisabled){
		boolean fromUI = isInvTypeNumTextFieldDisabled(index);
		if(fromUI!=isDisabled){
			throw new ErrorOnPageException("Inventory type number text field should be "+(isDisabled?"disabled":"abled"));
		}else logger.info("Successfully verify Inventory type number text field is "+(isDisabled?"disabled":"abled"));
	}
	public void setInvTypeNumTextFieldValue(String value, int index) {
		browser.setTextField(this.invOnHandTextFieldProp(), value, true, index);
	}
	
	public void verifyInvTypeNumTextFieldValue(String value, int index) {
		String actual = this.getInvTypeNumTextFieldValue(index);
		if (!value.equalsIgnoreCase(actual)) {
			throw new ErrorOnPageException("Inventory type #" + index + " value is wrong!", value, actual);
		}
		logger.info("Verify inventory type  #" + index + " value correctly as " + value);
	}
	
	public String getContinueBtnText() {
		return browser.getObjectText(this.continueBtnProp());
	}
	
	public void clickContinueBtn() {
		browser.clickGuiObject(this.continueBtnProp());
	}
	
	public boolean isReturnToDetailsLinkExist() {
		return browser.checkHtmlObjectExists(this.returnToDetailsLinkProp());
	}
	
	public void clickReturnToDetailsLink() {
		browser.clickGuiObject(this.returnToDetailsLinkProp());
	}
	
	public String getTopErrorMsg() {
		return browser.getObjectText(this.topErrorMsgProp());
	}
	
	public List<String> getItemErrorMsgs() {
		return browser.getObjectsText(this.itemErrorMsgProp());
	}
}
