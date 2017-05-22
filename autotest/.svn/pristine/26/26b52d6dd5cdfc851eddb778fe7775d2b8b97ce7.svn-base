package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jul 11, 2013
 */
public class HFEducationDeclarePage extends  HFHeaderBar {

	private static HFEducationDeclarePage _instance = null;

	public static HFEducationDeclarePage getInstance() {
		if (null == _instance)
			_instance = new HFEducationDeclarePage();

		return _instance;
	}

	protected HFEducationDeclarePage() {
	}

	private String licenseYearLabel = "Licence Year";
	private String quantityLabel = "Quantity";
	private String eduDeclareAttrsID = "hfEduReqKit_eduquest_parent_attrs"; // Lesley[20130902]: change from "hfEduReqKit_eduDeclare_attrs" due to page change;
	
	protected Property[] eduTypeDeclarDiv(String eduTypeDes) {
		return Property.concatPropertyArray(this.div(), ".id", new RegularExpression("hfEduReqKit_quest_sgrp_\\d+_attrs", false), 
				".text", new RegularExpression("^"+eduTypeDes+".*", false));
	}
	
	protected Property[] eduTypeDeclarValueDiv(int index) {
		return Property.concatPropertyArray(this.div(), ".id", new RegularExpression("quest_" + index + "_\\d+", false));
	}
	
	protected Property[] eduTypeDeclarTitleDiv(String eduTypeDes) {
		return Property.concatPropertyArray(this.div(), ".id", new RegularExpression("quest_sgrp_\\d+_title", false), 
				".text", new RegularExpression("^"+eduTypeDes+".*", false));
	}
	
	protected Property[] eduTypeDeclarTitleDiv(int index) {
		return Property.concatPropertyArray(this.div(), ".id", new RegularExpression("quest_sgrp_" + index + "_title", false));
	}
	
	protected Property[] attestCheckBox() {
		return Property.toPropertyArray(".id", new RegularExpression("LhfEduReqKit_quest_\\d+_\\d+_layout_quest_\\d+_\\d+_\\d+", false));
	}
	
	protected Property[] groupLabelSpan() {
		return Property.toPropertyArray(".className", "groupLabel");
	}
	
	protected Property[] questionTitleDiv() {
		return Property.toPropertyArray(".className", "questionTitle");
	}
	
	protected Property[] errorItemDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "error_item");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "eduForm") && browser.checkHtmlObjectExists(".id", eduDeclareAttrsID);
	}

	public String getPageTitle(){
		return browser.getObjectText(".id", "pagetitle");
	}

	public String getProductName(){
		return browser.getObjectText(".className", "prodname").trim();
	}

	public String getLicenseYear(){
		return browser.getObjectText(".className", "prod_attr", ".text", new RegularExpression("^"+licenseYearLabel+".*", false)).split(":")[1].trim();
	}

	public String getQuantity(){
		return browser.getObjectText(".className", "prod_attr", ".text", new RegularExpression("^"+quantityLabel+".*", false)).split(":")[1].trim();
	}

	public String getEduSectionTitle(int index) {
		return browser.getObjectText(Property.atList(this.eduTypeDeclarTitleDiv(index), this.groupLabelSpan()));
	}
	
	public String getEduSectionTitle(){
//		return browser.getObjectText(".className", "edu_section_title");
		return browser.getObjectText(Property.atList(Property.toPropertyArray(".id", new RegularExpression("quest_sgrp_\\d+_title", false)), Property.toPropertyArray(".className", "groupLabel")));
	}

	public String getEduSectionSubTitle(int index) {
		return browser.getObjectText(Property.atList(this.eduTypeDeclarTitleDiv(index), this.questionTitleDiv()));
	}
	
	public String getEduSectionSubTitle(){
//		return browser.getObjectText(".className", "edu_section_subtitle");
		return browser.getObjectText(".className", "questionTitle");
	}

	public String getEduDeclareValue(int index) {
		return browser.getObjectText(Property.atList(this.eduTypeDeclarValueDiv(index), this.groupLabelSpan()));
	}
	
	public String getEduDeclareValue(){
//		return browser.getObjectText(Property.atList(Property.toPropertyArray(".id", eduDeclareAttrsID), Property.toPropertyArray(".className", "groupcard")));
		return browser.getObjectText(Property.atList(Property.toPropertyArray(".id", new RegularExpression("quest_\\d+_\\d+", false)), Property.toPropertyArray(".className", "groupLabel")));
	}

	public boolean isAttestAndProceedButtonDisplayed(){
		return browser.checkHtmlObjectDisplayed(".id", "submitForm_submitForm");
	}

	public void checkAttest() {
		browser.selectCheckBox(attestCheckBox());
	}
	
	public void checkAttest(String eduTypeDes) {
		browser.selectCheckBox(Property.atList(this.eduTypeDeclarDiv(eduTypeDes), this.attestCheckBox()));
	}
	
	public void unCheckAttest(String eduTypeDes) {
		IHtmlObject[] objs = browser.getHtmlObject(this.eduTypeDeclarDiv(eduTypeDes));
		browser.unSelectCheckBox(this.attestCheckBox(), 0, objs[0]);
		Browser.unregister(objs);
	}
	
	public boolean isAttestCheckboxExist(String eduTypeDes) {
		return browser.checkHtmlObjectExists(Property.atList(this.eduTypeDeclarDiv(eduTypeDes), this.attestCheckBox()));
	}
	
	public void clickAttestAndProceed(){
		browser.clickGuiObject(".id", "submitForm_submitForm");
	}

	public boolean isIDoNotAttestLinkDisplayed(){
		return browser.checkHtmlObjectDisplayed(Property.atList(Property.toPropertyArray(".className", "cancelLink"), Property.toPropertyArray(".class", "Html.A")));
	}

	public void clickIDoNotAttest(){
		browser.clickGuiObject(Property.atList(Property.toPropertyArray(".className", "cancelLink"), Property.toPropertyArray(".class", "Html.A")), true, 0);
	}

	public boolean isReturnToProductDetailsLinkDisplayed(){
		return browser.checkHtmlObjectDisplayed(".id", "returnLink");
	}

	public void clickReturnToProductDetails(){
		browser.clickGuiObject(".id", "returnLink");
	}
	
	public boolean isEduTypeDeclarationExist(String eduTypeDes) {
		return browser.checkHtmlObjectExists(this.eduTypeDeclarTitleDiv(eduTypeDes));
	}
	
	public String getEduTypeErrorMsg(String eduDes) {
		return browser.getObjectText(Property.atList(this.eduTypeDeclarDiv(eduDes), this.errorItemDiv()));
	}
	
	public void verifyEduTypeErrorMsg(String eduDes, String expMsg) {
		String actual = this.getEduTypeErrorMsg(eduDes);
		if (!expMsg.equalsIgnoreCase(actual)) {
			throw new ErrorOnPageException("The error message is wrong for the edu type:" + eduDes, expMsg, actual);
		}
		logger.info("Verify error message correctly for edu type:" + eduDes);
	}
}


