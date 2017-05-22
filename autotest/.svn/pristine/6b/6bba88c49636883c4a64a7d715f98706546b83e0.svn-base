package com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram.EarnParameter;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
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
public class InvMgrLoyaltyProgramDetailsPage extends InvMgrLoyaltyProgramDetailsCommonPage {
	
	private static InvMgrLoyaltyProgramDetailsPage _instance = null;
	
	private InvMgrLoyaltyProgramDetailsPage() {}
	
	public static InvMgrLoyaltyProgramDetailsPage getInstance() {
		if(_instance == null) _instance = new InvMgrLoyaltyProgramDetailsPage();
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(programName());
	}
	
	private Property[] programName() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyVO-\\d+\\.programName", false));
	}
	
	private Property[] description() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyVO-\\d+\\.programDescription", false));
	}
	
	private Property[] productCategory() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyEarnParamView-\\d+\\.productGroupCat", false));
	}
	
	private Property[] earnGroup() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyEarnParamView-\\d+\\.earnGroup", false));
	}
	
	private Property[] pointType() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyEarnParamView-\\d+\\.pointType", false));
	}
	
	private Property[] method() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyEarnParamView-\\d+\\.earnMethod", false));
	}
	
	private Property[] remove() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Remove");
	}
	
	private Property[] add() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add");
	}
	
	private Property[] associatedProduct() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyVO-\\d+\\.associatedProduct", false));
	}
	
	private Property[] effectiveStartDate() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyVO-\\d+\\.programStartDate_ForDisplay", false));
	}
	
	private Property[] effectiveEndDate() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyVO-\\d+\\.programEndDate_ForDisplay", false));
	}
	
	private Property[] ok() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "OK");
	}
	
	private Property[] cancel() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Cancel");
	}
	
	private Property[] apply() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Apply");
	}
	
	private Property[] earnSchedule() {
		return Property.toPropertyArray(".class", "Html.SPAN", ".text", "Earn Schedules");
	}
	
	private Property[] redeemSchedule() {
		return Property.toPropertyArray(".class", "Html.SPAN", ".text", "Redeem Schedules");
	}
	
	public void setProgramName(String name) {
		browser.setTextField(programName(), name);
	}
	
	public String getProgramName() {
		return browser.getObjectText(programName());
	}
	
	public void setDescription(String desc) {
		browser.setTextArea(description(), desc);
	}
	
	public String getDescription() {
		return browser.getObjectText(description());
	}
	
	public void selectProductCategory(String category, int index) {
		browser.selectDropdownList(productCategory(), category, index);
	}
	
	public String getProductCategory(int index) {
		return browser.getDropdownListValue(productCategory(), index);
	}
	
	public void selectEarnGroup(String group, int index) {
		browser.selectDropdownList(earnGroup(), group, index);
	}
	
	public String getEarnGroup(int index) {
		return browser.getDropdownListValue(earnGroup(), index);
	}
	
	public void selectPointType(String type, int index) {
		browser.selectDropdownList(pointType(), type, index);
	}
	
	public String getPointType(int index) {
		return browser.getDropdownListValue(pointType(), index);
	}
	
	public void selectMethod(String method, int index) {
		browser.selectDropdownList(method(), method, index);
	}
	
	public String getMethod(int index) {
		return browser.getDropdownListValue(method(), index);
	}
	
	public boolean isRemoveExists() {
		return browser.checkHtmlObjectExists(remove());
	}
	
	public void clickRemove(int index) {
		browser.clickGuiObject(remove(), index);
	}
	
	public int getRemoveButtonsNum() {
		IHtmlObject objs[] = browser.getHtmlObject(remove());
		if(objs.length < 1) return 0;
		int num = objs.length;
		
		Browser.unregister(objs);
		return num;
	}
	
	public void clickAdd() {
		browser.clickGuiObject(add());
	}
	
	public void selectAssociatedProduct(String product) {
		browser.selectDropdownList(associatedProduct(), product);
	}
	
	public String getAssociatedProduct() {
		return browser.getDropdownListValue(associatedProduct(), 0);
	}
	
	public void setEffectiveStartDate(String startDate) {
		browser.setCalendarField(effectiveStartDate(), startDate);
	}
	
	public String getEffectiveStartDate() {
		return browser.getTextFieldValue(effectiveStartDate());
	}
	
	public void setEffectiveEndDate(String endDate) {
		browser.setCalendarField(effectiveEndDate(), endDate);
	}
	
	public String getEffectiveEndDate() {
		return browser.getTextFieldValue(effectiveEndDate());
	}
	
	public void clickOK() {
		browser.clickGuiObject(ok());
	}
	
	public void clickCancel() {
		browser.clickGuiObject(cancel());
	}
	
	public void clickApply() {
		browser.clickGuiObject(apply());
	}
	
	public void clickEarnSchedule() {
		browser.clickGuiObject(earnSchedule());
	}
	
	public void clickRedeemSchedule() {
		browser.clickGuiObject(redeemSchedule());
	}
	
	public void setLoyaltyProgram(LoyaltyProgram lp) {
		while(this.isRemoveExists()) {
			this.clickRemove(0);
			ajax.waitLoading();
			this.waitLoading();
		}
		
		for(int i = 0; i < lp.getEarnParameters().size() - 1; i ++) {
			this.clickAdd();
			ajax.waitLoading();
		}
		
		//DEFECT-61881 won't fix, so have to add/remove parameters then set name and description
		this.setProgramName(lp.getName());
		if(!StringUtil.isEmpty(lp.getDescription())) this.setDescription(lp.getDescription());
		
		for(int i = 0; i < lp.getEarnParameters().size(); i ++) {
			this.selectProductCategory(lp.getEarnParameters().get(i).getProducateCategory(), i);
			ajax.waitLoading();
			this.selectEarnGroup(lp.getEarnParameters().get(i).getEarnGroup(), i);
			this.selectPointType(lp.getEarnParameters().get(i).getPointType(), i);
			this.selectMethod(lp.getEarnParameters().get(i).getMethod(), i);
		}
		
		this.selectAssociatedProduct(lp.getAssociatedProduct());
		this.setEffectiveStartDate(lp.getEffectiveStartDate());
		this.setEffectiveEndDate(lp.getEffectiveEndDate());
	}
	
	public LoyaltyProgram getLoyaltyProgramDetailsInfo() {
		LoyaltyProgram lp = new LoyaltyProgram();
		
		lp.setId(this.getProgramID());
		lp.setName(this.getProgramLoyaltyName());
		lp.setDescription(this.getDescription());
		int earnParametersSize = this.getRemoveButtonsNum();
		earnParametersSize = earnParametersSize == 0 ? 1 : earnParametersSize;
		
		List<EarnParameter> parameters = new ArrayList<EarnParameter>();
		for(int i = 0; i < earnParametersSize; i ++) {
			EarnParameter param = lp.new EarnParameter();
			
			param.setProducateCategory(this.getProductCategory(i));
			param.setEarnGroup(this.getEarnGroup(i));
			param.setPointType(this.getPointType(i));
			param.setMethod(this.getMethod(i));
			
			parameters.add(param);
		}
		lp.setEarnParameters(parameters);
		
		lp.setAssociatedProduct(this.getAssociatedProduct());
		lp.setEffectiveStartDate(this.getEffectiveStartDate());
		lp.setEffectiveEndDate(this.getEffectiveEndDate());
		
		return lp;
	}
	
	public boolean compareLoyaltyProgramDetailsInfo(LoyaltyProgram expected) {
		boolean result = true;
		LoyaltyProgram actual = this.getLoyaltyProgramDetailsInfo();
		
		result &= MiscFunctions.compareResult("Program ID", expected.getId(), actual.getId());
		result &= MiscFunctions.compareResult("Program Name", expected.getName(), actual.getName());
		result &= MiscFunctions.compareResult("Description", expected.getDescription(), actual.getDescription());
		result &= MiscFunctions.compareResult("Earn Parameters size", expected.getEarnParameters().size(), actual.getEarnParameters().size());
		
		if(expected.getEarnParameters().size() == actual.getEarnParameters().size()) {
			for(int i = 0; i < expected.getEarnParameters().size(); i ++) {
				result &= MiscFunctions.compareResult("Earn Paramter " + i + " - Product Category", expected.getEarnParameters().get(i).getProducateCategory(), actual.getEarnParameters().get(i).getProducateCategory());
				result &= MiscFunctions.compareResult("Earn Paramter " + i + " - Earn Group", expected.getEarnParameters().get(i).getEarnGroup(), actual.getEarnParameters().get(i).getEarnGroup());
				result &= MiscFunctions.compareResult("Earn Paramter " + i + " - Point Type", expected.getEarnParameters().get(i).getPointType(), actual.getEarnParameters().get(i).getPointType());
				result &= MiscFunctions.compareResult("Earn Paramter " + i + " - Method", expected.getEarnParameters().get(i).getMethod(), actual.getEarnParameters().get(i).getMethod());
			}
		}
		
		result &= MiscFunctions.compareResult("Associated Product", expected.getAssociatedProduct(), actual.getAssociatedProduct());
		result &= MiscFunctions.compareResult("Effective Start Date", expected.getEffectiveStartDate(), actual.getEffectiveStartDate());
		result &= MiscFunctions.compareResult("Effective End Date", expected.getEffectiveEndDate(), actual.getEffectiveEndDate());
		
		return result;
	}
	
	public void verifyLoyaltyProgramDetailsInfo(LoyaltyProgram expected) {
		if(!compareLoyaltyProgramDetailsInfo(expected)) throw new ErrorOnPageException("Loyalty Program(ID=" + expected.getId() + ") details info is NOT correct.");
		logger.info("Loyalty Program(ID=" + expected.getId() + ") details info is correct.");
	}
}
