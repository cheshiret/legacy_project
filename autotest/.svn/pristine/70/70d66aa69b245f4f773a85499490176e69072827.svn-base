package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;

/**
 * @Description: HF Privilege Lottery Application Details page
 * 
 * @author Lesley Wang
 * @Date  Feb 12, 2014
 */
public class HFLotteryDetailsPage extends HFHeaderBar {
	private static HFLotteryDetailsPage _instance = null;

	public static HFLotteryDetailsPage getInstance() {
		if (null == _instance)
			_instance = new HFLotteryDetailsPage();

		return _instance;
	}
	
	protected HFLotteryDetailsPage() {
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] lotteryDetailsForm() {
		return Property.concatPropertyArray(this.form(), ".id", "PrivilegeLotteryDetailsKit_form");
	}
	
	protected Property[] pointsGroupRadioBtn() {
		return Property.concatPropertyArray(this.input("radio"), ".id", "LPrivilegeLotteryDetailsKit_detailsLayout_pointsGroup");
	}
	
	protected Property[] pointIndividualRadioBtn() {
		return Property.concatPropertyArray(this.input("radio"), ".id", "LPrivilegeLotteryDetailsKit_pointPurchaseLayout_P_1");
	}
	
	protected Property[] pointGroupLeaderRadioBtn() {
		return Property.concatPropertyArray(this.input("radio"), ".id", "LPrivilegeLotteryDetailsKit_pointPurchaseLayout_P_3");
	}
	
	protected Property[] lotteryApplyForADrawRadioBtn() {
		return Property.concatPropertyArray(this.input("radio"), ".id", "LPrivilegeLotteryDetailsKit_detailsLayout_lotteryGroup");
	}
	
	protected Property[] individualRadioBtn() {
		return Property.concatPropertyArray(this.input("radio"), ".id", "LPrivilegeLotteryDetailsKit_enterLotLayout_L_1");
	}
	
	protected Property[] groupLeaderRadioBtn() {
		return Property.concatPropertyArray(this.input("radio"), ".id", "LPrivilegeLotteryDetailsKit_enterLotLayout_L_3");
	}
	
	protected Property[] continueBtn() {
		return Property.toPropertyArray(".class", "Html.button", ".text", "Continue", ".id", "submitForm_submitForm");
	}
	
	protected Property[] pageTitle() {
		return Property.concatPropertyArray(div(), ".id", "pagetitle");
	}
	/** Page Object Property Definition End */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(lotteryDetailsForm());
	}
	
	public void selectLotteryApplyForADraw(){
		browser.selectRadioButton(lotteryApplyForADrawRadioBtn(), 0);
	}
	public void selectIndividual() {
		browser.selectRadioButton(this.individualRadioBtn(), 0);
	}
	
	public void selectGroupLeader() {
		browser.selectRadioButton(this.groupLeaderRadioBtn(), 0);
	}
	
	public void clickContinue() {
		browser.clickGuiObject(this.continueBtn());
	}
	
	public boolean isIndividualTypeSelected() {
		return browser.isRadioButtonSelected(this.individualRadioBtn());
	}
	
	public boolean isGroupLeaderTypeSelected() {
		return browser.isRadioButtonSelected(this.groupLeaderRadioBtn());
	}
	
	public void selectSubmitWithPoints() {
		browser.selectRadioButton(pointsGroupRadioBtn(), 0);
	}
	
	public void selectPointIndividual() {
		browser.selectRadioButton(this.pointIndividualRadioBtn(), 0);
	}
	
	public void selectPointGroupLeader() {
		browser.selectRadioButton(this.pointGroupLeaderRadioBtn(), 0);
	}
	
	public String getPageTitle(){
		return browser.getObjectText(pageTitle());
	}
	
	public void verifyLotteryName(String lotteryName){
		String pageTitle = getPageTitle();
		if(pageTitle.endsWith(lotteryName)){
			logger.info("Successfylly verify lottery name:"+lotteryName);
		}else throw new ErrorOnPageException("Failed to verify lottery name because "+pageTitle+" doesn't end with "+lotteryName);
	}
}
