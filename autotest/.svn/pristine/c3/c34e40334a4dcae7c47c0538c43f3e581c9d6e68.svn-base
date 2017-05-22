package com.activenetwork.qa.awo.pages.web.hf;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.Timer;

/**
 * 
 * @Description:
 * @Preconditions:
// * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jun 15, 2013
 */
public class HFEducationInfomationRequiredPage extends  HFHeaderBar {

	private static HFEducationInfomationRequiredPage _instance = null;

	public static HFEducationInfomationRequiredPage getInstance() {
		if (null == _instance)
			_instance = new HFEducationInfomationRequiredPage();

		return _instance;
	}

	protected HFEducationInfomationRequiredPage() {
	}

	private String licenseYearLabel = "License Year";
	private String quantityLabel = "Quantity";
	private String eduNumIdRegx = "Aedunum_\\d+";
	private String countryIdRegx = "AcountryGrp_-\\d+";
	private String stateIdRegx = "AstateGrp_-\\d+";
	
	protected Property[] skipStepLink() {
		return Property.concatPropertyArray(this.a(), ".href", "/eduHFreq.do?skipEdu=true");
	}
	
	protected Property[] yesAttest() {
		return Property.toPropertyArray(".id", new RegularExpression("LhfEduReqKit_quest_\\d+_\\d+_layout_quest_\\d+_\\d+_\\d+", false));
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "eduForm");
	}

	public void clickYesAttest(){
		browser.clickGuiObject(yesAttest());
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
	
	public String getEduSectionTitle(){
		return browser.getObjectText(".className", "edu_section_title");
	}
	
	public String getEduSectionSubTitle(){
		return browser.getObjectText(".className", "edu_section_subtitle");
	}
	
	public boolean isEduNumTextFieldDisplayed(){
		return browser.checkHtmlObjectDisplayed(".id", new RegularExpression(eduNumIdRegx, false));
	}
	
	public String getEduNumLabel(){
		return browser.getObjectText(Property.atList(Property.toPropertyArray(".className", "eduNumGroup"), Property.toPropertyArray(".className", "groupLabel")));
	}
	
	public void setEduNum(String eduNum){
		browser.setTextField(".id", new RegularExpression(eduNumIdRegx, false), eduNum);
	}
	
	public String getEduNum(){
		return browser.getTextFieldValue(".id", new RegularExpression(eduNumIdRegx, false));
	}
	
	public boolean isCountryDDLDisplayed(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression(countryIdRegx, false));
	}
	
	public String getCountryLabel(){
		return browser.getObjectText(Property.atList(Property.toPropertyArray(".id", "countryGrp"), Property.toPropertyArray(".className", "groupLabel")));
	}

	public void selectCountry(String country){
		browser.selectDropdownList(".id", new RegularExpression(countryIdRegx, false), country);
	}
	
	public String getCountry(){
		return browser.getDropdownListValue(".id", new RegularExpression(countryIdRegx, false));
	}
	
	public List<String> getCountries(){
		return browser.getDropdownElements(".id", new RegularExpression(countryIdRegx, false));
	}
	
	public boolean isStateDDLExists(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression(stateIdRegx, false));
	}
	
	public String getStateLabel(){
		return browser.getObjectText(Property.atList(Property.toPropertyArray(".id", "stateGrp"), Property.toPropertyArray(".className", "groupLabel")));
	}
	
	public void selectState(String state){
		browser.selectDropdownList(".id", new RegularExpression(stateIdRegx, false), state);
	}
	
	public String getState(){
		return browser.getDropdownListValue(".id", new RegularExpression(stateIdRegx, false));
	}
	
	public List<String> getStates(){
		return browser.getDropdownElements(".id", new RegularExpression(stateIdRegx, false));
	}

	public void syncStateSelectingCountry(String country, boolean disappeared){
		String previousStates = getStates().toString();
		String currentStates = "";

		selectCountry(country);

		long maxWaitTime=OrmsConstants.FILE_DIALOG_LONG_SLEEP*20;
		boolean finishSync=false;
		Timer time = new Timer();

		do{
			if(!disappeared){
				currentStates = getStates().toString();
				finishSync = !previousStates.equals(currentStates);
			}else{
				finishSync = !isStateDDLExists();
			}

		}while(time.diffLong() < maxWaitTime && !finishSync);
		if(!finishSync) {
			throw new ItemNotFoundException("Syn States timed out in "+maxWaitTime+" ms");
		}
	}
	
	public boolean isSubmitButtonDisplayed(){
		return browser.checkHtmlObjectDisplayed(".id", "submitForm_submitForm");
	}
	
	public void clickSubmit(){
		browser.clickGuiObject(".id", "submitForm_submitForm");
	}
	
	public boolean isCancelLinkDisplayed(){
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", new RegularExpression("Cancel", false));
	}
	
	public void clickCancel(){
//		browser.clickGuiObject(Property.atList(Property.toPropertyArray(".className", "cancelLink"), Property.toPropertyArray(".class", "Html.A")), true, 0);
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public boolean isReturnToProductDetailsLinkDisplayed(){
		return browser.checkHtmlObjectDisplayed(".id", "returnLink");
	}
	
	public void clickReturnToProductDetails(){
		browser.clickGuiObject(".id", "returnLink");
	}
	
	public void clickItemsInCart(){
		browser.clickGuiObject(Property.atList(Property.toPropertyArray(".id", "shoppingcart"), Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("/viewShoppingCart\\.do", false))), true, 0);
	}
	
	public void setupEducationInfo(Education edu){
		if(edu!=null){
			setEduNum(edu.educationNum);
			if(StringUtil.notEmpty(edu.country)){
				if(StringUtil.notEmpty(edu.state)){
					if(!edu.country.equals(this.getCountry())){
						syncStateSelectingCountry(edu.country, false);
					}
					selectState(edu.state);
				}else{
					selectCountry(edu.country);
				}
			}else logger.info("No need to setup education country.");
		}else logger.info("Edu is null!");
	}
	
	/**
	 * Verify education number, country and state
	 * @param edu
	 */
	public void verifyEducationInfo(Education edu){
		boolean result = MiscFunctions.compareResult("Edu number", edu.educationNum, getEduNum());
		result &= MiscFunctions.compareResult("Country", edu.country, getCountry());
		result &= MiscFunctions.compareResult("State", edu.state, getState());
		if(!result){
			throw new ErrorOnPageException("Failed to verify all education related check points. Please find detauls infomation from previous logs.");
		}
		logger.info("Successfully verify all education related check points.");
	}
	
	public boolean isErrorMsgExist(String msg) {
		return browser.checkHtmlObjectExists(".classname", new RegularExpression("error_(heading|item)", false), ".text", new RegularExpression(msg, false));
	}
	
	public void verifyErrorMsgExist(String msg, boolean isExist) {
		String info = isExist ? " " : " not ";
		if (this.isErrorMsgExist(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + info + "exist!");
		}
		logger.info("The message: " + msg + " does " + info + "exist!");
	}
	
	public void clickSkipStepLink() {
		browser.clickGuiObject(skipStepLink());
	}
	
	public void verifyPrdInfo(PrivilegeInfo priv){
		boolean result = MiscFunctions.compareResult("Prd name", priv.name, getProductName());
		result &= MiscFunctions.compareResult("License year", priv.licenseYear, getLicenseYear());
		result &= MiscFunctions.compareResult("Quantity", priv.qty, getQuantity());
		if(!result){
			throw new ErrorOnPageException("Failed to verify all privilege info in Education infomation page.");
		}else logger.info("Successfully verify all privilege info in Education infomation page.");
	}
	
	public void verifyProdName(String prodName){
		String prodNameFromUI = getProductName();
		if(prodName.equalsIgnoreCase(prodNameFromUI)){
			logger.info("Successfully verify prod name:"+prodName);
		}else throw new ErrorOnPageException("Failed to verify prod name", prodName, prodNameFromUI);
	}
}


