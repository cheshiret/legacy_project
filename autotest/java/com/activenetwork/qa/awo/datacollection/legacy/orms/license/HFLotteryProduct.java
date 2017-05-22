/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.LotteryParameterInfo;
import com.activenetwork.qa.testapi.datacollection.Data;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Nov 2, 2012
 */
public class HFLotteryProduct {

	private String code;
	private String description;
	private String legalName;
	private String status;
	private String[] species;
	private String[] customerClass;
	private String[] defaultAllowedApplications;
	private String minChoices;
	private String maxChoices;
	private String algorithm;
	private String displayCategory;
	private String displaySubCategory;
	private String reportCategory;
	private String pricingNote;
	private String pointTypeForPurchase;
	private boolean displayPointCodeInSalesFlow;
	private boolean allowPointsAndHuntsInOneApplication;
	private String[] pointsAllowedFor;
	private String minPointsAllowed;
	private String maxPointsAllowed;
	private String notesForPointTypeForPurchaseInSalesFlow;
	private List<SalesFlowDisplaySetting> displaySetting = new ArrayList<SalesFlowDisplaySetting>();
	
	// Lottery parameters info, by Lesley
	private List<Data<LotteryParameterInfo>> parameters = new ArrayList<Data<LotteryParameterInfo>>();
		
	//for Purchase process
	private String purchasingName;
	private String licenseYear;
	private String huntCode;
	private List<String> huntCodes = new ArrayList<String>();
	private String applicantType;
	private List<String> pointTypes = new ArrayList<String>();
	private List<String[]> groupMembers = new ArrayList<String[]>();//Group Members:custID+DOB
	
	public static class SalesFlowDisplaySetting{
		public SalesFlowDisplaySetting(String setting, String display)
		{
			this.setting = setting;
			this.displayOrder = display;
		}
		public String setting;
		public String displayOrder;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getSpecies() {
		return species;
	}
	public void setSpecies(String[] species) {
		this.species = species;
	}
	public String[] getCustomerClass() {
		return customerClass;
	}
	public void setCustomerClass(String[] customerClass) {
		this.customerClass = customerClass;
	}
	public String[] getDefaultAllowedApplications() {
		return defaultAllowedApplications;
	}
	public void setDefaultAllowedApplications(String[] defaultAllowedApplications) {
		this.defaultAllowedApplications = defaultAllowedApplications;
	}
	public String getMinChoices() {
		return minChoices;
	}
	public void setMinChoices(String minChoices) {
		this.minChoices = minChoices;
	}
	public String getMaxChoices() {
		return maxChoices;
	}
	public void setMaxChoices(String maxChoices) {
		this.maxChoices = maxChoices;
	}
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	public String getDisplayCategory() {
		return displayCategory;
	}
	public void setDisplayCategory(String displayCategory) {
		this.displayCategory = displayCategory;
	}
	public String getDisplaySubCategory() {
		return displaySubCategory;
	}
	public void setDisplaySubCategory(String displaySubCategory) {
		this.displaySubCategory = displaySubCategory;
	}
	public String getReportCategory() {
		return reportCategory;
	}
	public void setReportCategory(String reportCategory) {
		this.reportCategory = reportCategory;
	}
	public String getPricingNote() {
		return pricingNote;
	}
	public void setPricingNote(String pricingNote) {
		this.pricingNote = pricingNote;
	}
	public String getPointTypeForPurchase() {
		return pointTypeForPurchase;
	}
	public void setPointTypeForPurchase(String pointTypeForPurchase) {
		this.pointTypeForPurchase = pointTypeForPurchase;
	}
	public boolean isDisplayPointCodeInSalesFlow() {
		return displayPointCodeInSalesFlow;
	}
	public void setDisplayPointCodeInSalesFlow(boolean displayPointCodeInSalesFlow) {
		this.displayPointCodeInSalesFlow = displayPointCodeInSalesFlow;
	}
	public boolean isAllowPointsAndHuntsInOneApplication() {
		return allowPointsAndHuntsInOneApplication;
	}
	public void setAllowPointsAndHuntsInOneApplication(
			boolean allowPointsAndHuntsInOneApplication) {
		this.allowPointsAndHuntsInOneApplication = allowPointsAndHuntsInOneApplication;
	}
	public String getNotesForPointTypeForPurchaseInSalesFlow() {
		return notesForPointTypeForPurchaseInSalesFlow;
	}
	public void setNotesForPointTypeForPurchaseInSalesFlow(
			String notesForPointTypeForPurchaseInSalesFlow) {
		this.notesForPointTypeForPurchaseInSalesFlow = notesForPointTypeForPurchaseInSalesFlow;
	}
	public List<SalesFlowDisplaySetting> getDisplaySetting() {
		return displaySetting;
	}
	public void setDisplaySetting(List<SalesFlowDisplaySetting> displaySetting) {
		this.displaySetting = displaySetting;
	}
	public String getPurchasingName() {
		return purchasingName;
	}
	public void setPurchasingName(String purchasingName) {
		this.purchasingName = purchasingName;
	}
	public String getLicenseYear() {
		return licenseYear;
	}
	public void setLicenseYear(String licenseYear) {
		this.licenseYear = licenseYear;
	}
	public String getHuntCode() {
		return huntCode;
	}
	public void setHuntCode(String huntCode) {
		this.huntCode = huntCode;
	}
	public List<String> getHuntCodes() {
		return huntCodes;
	}
	public void setHuntCodes(List<String> huntCodes) {
		this.huntCodes = huntCodes;
	}
	public String getApplicantType() {
		return applicantType;
	}
	public void setApplicantType(String applicantType) {
		this.applicantType = applicantType;
	}

	public List<Data<LotteryParameterInfo>> getParameters() {
		return parameters;
	}
	public void setParameters(List<Data<LotteryParameterInfo>> parameters) {
		this.parameters = parameters;
	}
	public void setParameters(String paramDes, String paramValue, boolean isPrint) {
		Data<LotteryParameterInfo> param = new Data<LotteryParameterInfo>();
		LotteryParameterInfo.Description.setValue(param, paramDes);
		LotteryParameterInfo.Value.setValue(param, paramValue);
		LotteryParameterInfo.IsPrintParam.setValue(param, isPrint);
		this.parameters = new ArrayList<Data<LotteryParameterInfo>>();
		this.parameters.add(param);
	}
	public String[] getPointsAllowedFor() {
		return pointsAllowedFor;
	}
	public void setPointsAllowedFor(String[] pointsAllowedFor) {
		this.pointsAllowedFor = pointsAllowedFor;
	}
	/**
	 * @return the minPointsAllowed
	 */
	public String getMinPointsAllowed() {
		return minPointsAllowed;
	}
	/**
	 * @param minPointsAllowed the minPointsAllowed to set
	 */
	public void setMinPointsAllowed(String minPointsAllowed) {
		this.minPointsAllowed = minPointsAllowed;
	}
	/**
	 * @return the maxPointsAllowed
	 */
	public String getMaxPointsAllowed() {
		return maxPointsAllowed;
	}
	/**
	 * @param maxPointsAllowed the maxPointsAllowed to set
	 */
	public void setMaxPointsAllowed(String maxPointsAllowed) {
		this.maxPointsAllowed = maxPointsAllowed;
	}
	public List<String> getPointTypes() {
		return pointTypes;
	}
	public void setPointTypes(List<String> pointTypes) {
		this.pointTypes = pointTypes;
	}
	public List<String[]> getGroupMembers() {
		return groupMembers;
	}
	public void setGroupMembers(List<String[]> groupMembers) {
		this.groupMembers = groupMembers;
	}
}
