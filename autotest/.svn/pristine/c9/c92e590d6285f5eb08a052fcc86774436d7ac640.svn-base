/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;



import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct.SalesFlowDisplaySetting;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:This page is father class of LicMgrCreateNewLotteryProductPage and LicMgrLotteryProductDetailsPage
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Nov 2, 2012
 */

public abstract class LicMgrLotteryProductCommonPage extends LicMgrLotteriesCommonPage {
	

	protected String prefix = "PrivilegeLotteryView-\\d+\\.";
	
	protected Property[] storeAssignmentsTab() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("(Agent|Issuer|Location) Assignments", false));
	}
	
	protected Property[] contractorFeesTab() {
		return Property.concatPropertyArray(this.a(), ".text", "Contractor Fees");
	}

	protected Property[] pricingTab() {
		return Property.concatPropertyArray(a(), ".text", "Pricing");
	}
	
	protected Property[] huntsTab() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Hunts(\\(\\d+\\))?", false), 
				".id", new RegularExpression("EditPrivilegeLotteryProduct_\\d+", false));
	}

	protected Property[] pointsAllowedForCheckBox(String label) {
		return Property.concatPropertyArray(this.input("checkbox"), ".id", new RegularExpression("PrivilegeLottery(Light)?View-\\d+\\.allowPointsFor"+label, false));
	}
	
	protected Property[] minPointsAllowedTextField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression("PrivilegeLotteryView-\\d+\\.minGroupSizeAllowedForPoints", false));
	}
	
	protected Property[] maxPointsAllowedTextField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression("PrivilegeLotteryView-\\d+\\.maxGroupSizeAllowedForPoints", false));
	}
	
	protected Property[] legalName() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression("PrivilegeLotteryView-\\d+.productLegalName", false));
	}
	
	public void setLegalName(String legalName){
		browser.setTextField(legalName(), legalName);
	}
	
	public void setDescription(String dscr){
		RegularExpression regx=new RegularExpression(this.prefix+"productDesc",false);
		browser.setTextField(".id", regx, dscr, true);
	}
	
	public void setMinChoices(String text){
		RegularExpression regx=new RegularExpression(this.prefix+"minHuntChoices",false);
		browser.setTextField(".id", regx, text, true);
	}

	public void setMaxChoices(String text){
		RegularExpression regx=new RegularExpression(this.prefix+"maxHuntChoices",false);
		browser.setTextField(".id", regx, text, true);
	}
	
	public void selectAlgorithm(String item){
		RegularExpression regx=new RegularExpression(this.prefix+"algorithmConfig",false);
	    browser.selectDropdownList(".id", regx, item);
	}
	
	public void selectDisplayCategory(String item){
		RegularExpression regx=new RegularExpression(this.prefix+"displayCategory",false);
	    browser.selectDropdownList(".id", regx, item);
	}
	
	
	public void selectDisplaySubCategory(String subCategory){
		RegularExpression regx=new RegularExpression(this.prefix+"displaySubCategory",false);
	    browser.selectDropdownList(".id", regx, subCategory);
	}
	
	public void selectReportCategory(String reportcategory){
		RegularExpression regx=new RegularExpression(this.prefix+"displayReportCategory",false);
	    browser.selectDropdownList(".id", regx, reportcategory);
	}
	
	public void setPricingNote(String pricingNote) {
		browser.setTextField(".id", new RegularExpression("PrivilegeLotteryView-\\d+\\.pricingNote", false), pricingNote);
	}
	
	public void selectPointTypeForPurchase(String pointType) {
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeLotteryView-\\d+\\.purchasePointType", false), pointType);
	}
	
	public void selectDisplayPointCodeInSalesFlow() {
		browser.selectCheckBox(".id", new RegularExpression("PrivilegeLotteryView-\\d+\\.pointCodeInSales", false));
	}
	
	public void selectAllowPointsAndHuntsInOneApplication() {
		browser.selectCheckBox(".id", new RegularExpression("PrivilegeLotteryView-\\d+\\.pointHuntInOneApp", false));
	}

	public void selectPointsAllowedFor(String pointsAllowedFor) {
		browser.selectCheckBox(this.pointsAllowedForCheckBox(pointsAllowedFor));
	}
	
	public void selectPointsAllowedFor(String[] pointsAllowedFor) {
		for (String type : pointsAllowedFor) {
			this.selectPointsAllowedFor(type);
			ajax.waitLoading();
		}
	}
	
	public void setMinPointsAllowed(String min) {
		browser.setTextField(minPointsAllowedTextField(), min);
	}
	
	public void setMaxPointsAllowed(String max) {
		browser.setTextField(maxPointsAllowedTextField(), max);
	}
	
	public void setNotesForPointTypeForPurchaseInSalesFlow(String notes) {
		browser.setTextField(".id", new RegularExpression("PrivilegeLotteryView-\\d+\\.pointTypeNotes", false), notes);
	}

	public void selectSalesFlowDisplaySettings(String label){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.LABEL", ".for", new RegularExpression(this.prefix+"salesSettings_\\d+",false));
		String htmlForStr = "";
		int counter = -1;
		for(int i = 0; i < objs.length; i ++) {
			if(null != objs[i].getProperty(".text") && objs[i].getProperty(".text").trim().length() > 0) {
				if(objs[i].getProperty(".text").trim().equalsIgnoreCase(label.trim())) {
					htmlForStr = objs[i].getProperty("for").trim();
					counter = i;
					break;
				}
			}
		}
		
		if(counter == -1) {
			throw new ItemNotFoundException("Can't find label named - " + label);
		}
		browser.selectCheckBox(".id", htmlForStr);
		
		Browser.unregister(objs);
	}
	
	
	public void setDisplayOrder(String label, String value){
		
		IHtmlObject checkBoxObjs[] = browser.getHtmlObject(".class", "Html.LABEL", ".for", new RegularExpression(this.prefix+"salesSettings_\\d+",false));

		int counter = -1;
		
		for(int i = 0; i < checkBoxObjs.length; i ++) {
			if(null != checkBoxObjs[i].getProperty(".text") && checkBoxObjs[i].getProperty(".text").trim().length() > 0) {
				if(checkBoxObjs[i].getProperty(".text").trim().equalsIgnoreCase(label.trim())) {
					counter = i;
					break;
				}
			}
		}
		
		
		RegularExpression regx=new RegularExpression("PrivilegeLotterySalesSettingsView-\\d+\\.displayOrder",false);
		browser.setTextField(".id", regx, value, true, counter);
		//added by pzhu, because sometimes, cannot focus on the editbox after ajax. So we do twice for the same input action...
		browser.setTextField(".id", regx, value, true, counter);
				
		Browser.unregister(checkBoxObjs);

		
		
	}
	
	public void setSalesFlowDisplaySetting(String setting, String displayValue)
	{
		this.selectSalesFlowDisplaySettings(setting);
		ajax.waitLoading();
		this.setDisplayOrder(setting, displayValue);
	}
	
	
	public void selectSpecies(String label){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.LABEL", ".for", new RegularExpression(this.prefix+"species_\\d+",false));
		String htmlForStr = "";
		int counter = -1;
		for(int i = 0; i < objs.length; i ++) {
			if(null != objs[i].getProperty(".text") && objs[i].getProperty(".text").trim().length() > 0) {
				if(objs[i].getProperty(".text").trim().equalsIgnoreCase(label.trim())) {
					htmlForStr = objs[i].getProperty("for").trim();
					counter = i;
					break;
				}
			}
		}
		
		if(counter == -1) {
			throw new ItemNotFoundException("Can't find label named - " + label);
		}
		browser.selectCheckBox(".id", htmlForStr);
		
		Browser.unregister(objs);
	}
	
	
	public void selectSpecies(String species[]) {
		for(String s : species) {
			if(null != s && s.length() > 0) {
				selectSpecies(s);
			}
		}
	}
	
	
	public void selectCustomerClass(String label){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.LABEL", ".for", new RegularExpression(this.prefix+"customerClasses_\\d+",false));
		String htmlForStr = "";
		int counter = -1;
		for(int i = 0; i < objs.length; i ++) {
			if(null != objs[i].getProperty(".text") && objs[i].getProperty(".text").trim().length() > 0) {
				if(objs[i].getProperty(".text").trim().equalsIgnoreCase(label.trim())) {
					htmlForStr = objs[i].getProperty("for").trim();
					counter = i;
					break;
				}
			}
		}
		
		if(counter == -1) {
			throw new ItemNotFoundException("Can't find label named - " + label);
		}
		browser.selectCheckBox(".id", htmlForStr);
		
		Browser.unregister(objs);
	}
	
	public void selectCustomerClass(String classes[]) {
		for(String s : classes) {
			if(null != s && s.length() > 0) {
				selectCustomerClass(s);
			}
		}
	}
	
	
	
	public void selectDefaultAllowedApplications(String label){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.LABEL", ".for", new RegularExpression(this.prefix+"defaultAllowedApplicants_\\d+",false));
		String htmlForStr = "";
		int counter = -1;
		for(int i = 0; i < objs.length; i ++) {
			if(null != objs[i].getProperty(".text") && objs[i].getProperty(".text").trim().length() > 0) {
				if(objs[i].getProperty(".text").trim().equalsIgnoreCase(label.trim())) {
					htmlForStr = objs[i].getProperty("for").trim();
					counter = i;
					break;
				}
			}
		}
		
		if(counter == -1) {
			throw new ItemNotFoundException("Can't find label named - " + label);
		}
		browser.selectCheckBox(".id", htmlForStr);
		
		Browser.unregister(objs);
	}
	
	public void selectDefaultAllowedApplications(String apps[]) {
		for(String s : apps) {
			if(null != s && s.length() > 0) {
				selectDefaultAllowedApplications(s);
			}
		}
	}
	
	public void unSelectAllSpecies(){
		RegularExpression regx=new RegularExpression(this.prefix+"species_\\d+",false);
	    IHtmlObject[] objs=browser.getCheckBox(".id", regx);
	    if(objs.length<1){
	    	throw new ObjectNotFoundException("Can't find species");
	    }
	    for(int i=0;i<objs.length;i++){
	    	browser.unSelectCheckBox(".id", regx, i);
	    }
	    Browser.unregister(objs);
	}
	
	
	
	
	public void clickOk(){
		browser.clickGuiObject(".class", "Html.A",".text","OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A",".text","Cancel");
	}
	
	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A",".text","Apply");
	}
	
	/**
	 * Set up all product details information to create/update a lottery product
	 * @param HFLotteryProduct
	 */
	public void setupProductInfo(HFLotteryProduct prd) {
		this.setDescription(prd.getDescription());
		
		if(StringUtil.notEmpty(prd.getLegalName())){
			setLegalName(prd.getLegalName());
		}
		
		if(prd.getSpecies()!=null&&prd.getSpecies().length>0)
		{
			this.selectSpecies(prd.getSpecies());
		}
		if(prd.getCustomerClass()!=null&&prd.getCustomerClass().length>0)
		{
			this.selectCustomerClass(prd.getCustomerClass());
		}
		
		if(prd.getDefaultAllowedApplications()!=null&&prd.getDefaultAllowedApplications().length>0)
		{
			this.selectDefaultAllowedApplications(prd.getDefaultAllowedApplications());
		}
		
		if(!StringUtil.isEmpty(prd.getMinChoices()))
		{
			this.setMinChoices(prd.getMinChoices());
		}
		
		if(!StringUtil.isEmpty(prd.getMaxChoices()))
		{
			this.setMaxChoices(prd.getMaxChoices());
		}
		
		if(!StringUtil.isEmpty(prd.getAlgorithm()))
		{
			this.selectAlgorithm(prd.getAlgorithm());
			ajax.waitLoading();
		}
		
		if(!StringUtil.isEmpty(prd.getDisplayCategory())) {
			selectDisplayCategory(prd.getDisplayCategory());
		}
		
		if(!StringUtil.isEmpty(prd.getDisplaySubCategory())) {
			selectDisplaySubCategory(prd.getDisplaySubCategory());
		}
		
		if(!StringUtil.isEmpty(prd.getReportCategory())) {
			selectReportCategory(prd.getReportCategory());
		}
	
		if(!StringUtil.isEmpty(prd.getPricingNote())) {
			setPricingNote(prd.getPricingNote());
		}
		
		if(StringUtil.notEmpty(prd.getPointTypeForPurchase())){
			selectPointTypeForPurchase(prd.getPointTypeForPurchase());
			ajax.waitLoading();
			
			if(prd.isDisplayPointCodeInSalesFlow()) {
				this.selectDisplayPointCodeInSalesFlow();
				ajax.waitLoading();
			}
			
			if (prd.isAllowPointsAndHuntsInOneApplication()) {
				this.selectAllowPointsAndHuntsInOneApplication();
				ajax.waitLoading();
			}
			
			if (prd.getPointsAllowedFor() != null && prd.getPointsAllowedFor().length > 0) {
				this.selectPointsAllowedFor(prd.getPointsAllowedFor());
			}
			
			if (StringUtil.notEmpty(prd.getMinPointsAllowed())) {
				this.setMinPointsAllowed(prd.getMinPointsAllowed());
			}
			
			if (StringUtil.notEmpty(prd.getMaxPointsAllowed())) {
				this.setMaxPointsAllowed(prd.getMaxPointsAllowed());
			}
			
			if (StringUtil.notEmpty(prd.getNotesForPointTypeForPurchaseInSalesFlow())) {
				this.setNotesForPointTypeForPurchaseInSalesFlow(prd.getNotesForPointTypeForPurchaseInSalesFlow());
			}
		}
		
		
		
		if(prd.getDisplaySetting().size()>0)
		{
			for(SalesFlowDisplaySetting s: prd.getDisplaySetting())
			{
				this.setSalesFlowDisplaySetting(s.setting, s.displayOrder);
			}
		}
	}
	
	public void clickPricingTab() {
		browser.clickGuiObject(pricingTab());
	}

	public boolean isPricingTabExist() {
		return browser.checkHtmlObjectExists(pricingTab());
	}
	
	public void clickLicenseYearsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Licen(s|c)e Years(\\(\\d+\\)?)", false));
	}
	
	public void clickAgentAssignmentsTab() {
		browser.clickGuiObject(this.storeAssignmentsTab());
	}

	public boolean isStoreAssignmentsTabExist() {
		return browser.checkHtmlObjectExists(storeAssignmentsTab());
	}
	
	public void clickBusinessRulesTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Business Rules");
	}
	
	public void clickQuantityControlsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Quantity Controls");
	}
	
	public void clickParametersTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Parameters");
	}
	
	public void clickTextDisplayTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Text Display");
	}
	
	public void clickHuntsTab() {
		browser.clickGuiObject(huntsTab());
	}
	
	public boolean isHuntsTabExist() {
		return browser.checkHtmlObjectExists(huntsTab());
	}
	
	public void clickContractorFeesTab() {
		browser.clickGuiObject(contractorFeesTab());
	}
	
	public boolean isContractorFeesTabExist() {
		return browser.checkHtmlObjectExists(contractorFeesTab());
	}
}
