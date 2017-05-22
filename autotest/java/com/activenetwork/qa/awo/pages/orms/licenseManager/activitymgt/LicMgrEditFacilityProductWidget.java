package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.FacilityPrdAttr;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
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
 * @Date Jan 10, 2014
 */
public class LicMgrEditFacilityProductWidget extends DialogWidget {
	static class SingletonHolder {
		protected static LicMgrEditFacilityProductWidget _instance = new LicMgrEditFacilityProductWidget();
	}

	protected LicMgrEditFacilityProductWidget() {
		super("Facility Name:.*");
	}

	public static LicMgrEditFacilityProductWidget getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] createModifyFacilityPrdGroupBar() {
		return Property.concatPropertyArray(table(), ".id",
				"CreateModifyFacilityProductGroupBar");
	}

	protected Property[] facilityPrdCode() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"FacilityProductView-\\d+.productCode", false));
	}

	protected Property[] facilityPrdName() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"FacilityProductView-\\d+.name", false));
	}

	protected Property[] facilityPrdStatus() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"FacilityProductView-\\d+.entityStatus", false));
	}

	protected Property[] facilityPrdDes() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"FacilityProductView-\\d+.facilityProductDesc", false));
	}

	protected Property[] facilityPrdType() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"FacilityProductView-\\d+.productGroup", false));
	}

	protected Property[] capacity() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"FacilityProductView-\\d+.maxCapacity", false));
	}

	protected Property[] adaCompliance() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"FacilityProductView-\\d+.handicappedAccessible", false));
	}

	protected Property[] facilityPrdGroupBar() {
		return Property.toPropertyArray(".id", "facilityProductGroupBar");
	}

	protected Property[] addNew() {
		return Property.concatPropertyArray(a(), ".text", "Add New");
	}

	protected Property[] ok() {
		return Property.concatPropertyArray(a(), ".text", "OK");
	}

	protected Property[] cancel() {
		return Property.concatPropertyArray(a(), ".text", "Cancel");
	}

	/** Page Object Property Definition END */

	public boolean exists() {
		boolean flag1 = super.exists();
		boolean flag2 = browser
				.checkHtmlObjectExists(createModifyFacilityPrdGroupBar());
		return flag1 && flag2;
	}

	public void setFacilityPrdCode(String prdCode) {
		browser.setTextField(facilityPrdCode(), prdCode);
	}

	public String getFacilityPrdCode() {
		return browser.getTextFieldValue(facilityPrdCode());
	}

	public void setFacilityPrdName(String prdName) {
		browser.setTextField(facilityPrdName(), prdName);
	}

	public String getFacilityPrdName() {
		return browser.getTextFieldValue(facilityPrdName());
	}

	public void selectFacilityPrdStatus(String status) {
		browser.selectDropdownList(facilityPrdStatus(), status);
	}

	public String getFacilityPrdStatus() {
		return browser.getDropdownListValue(facilityPrdStatus(), 0);
	}

	public void setFacilityPrdDesc(String prdDesc) {
		browser.setTextArea(facilityPrdDes(), prdDesc);
	}

	public String getFacilityPrdDesc() {
		return browser.getTextAreaValue(facilityPrdDes());
	}

	public void selectFacilityPrdType(String prdType) {
		browser.selectDropdownList(facilityPrdType(), prdType);
	}

	public void selectFacilityPrdType(int index) {
		browser.selectDropdownList(facilityPrdType(), index);
	}
	
	public String getFacilityPrdType() {
		return browser.getDropdownListValue(facilityPrdType(), 0);
	}

	public List<String> getFacilityPrdTypes() {
		return browser.getDropdownElements(facilityPrdType());
	}

	public void clickAddNew() {
		browser.clickGuiObject(Property.atList(facilityPrdGroupBar(), addNew()));
	}

	public void setCapacity(String capacity) {
		browser.setTextField(capacity(), capacity);
	}

	public String getCapacity() {
		return browser.getTextFieldValue(capacity());
	}

	public void selectHandicappedAccessible(String handicappedAccessible) {
		browser.selectDropdownList(adaCompliance(), handicappedAccessible);
	}

	public String getHandicappedAccessible() {
		return browser.getDropdownListValue(adaCompliance(), 0);
	}

	public void clickOK() {
		browser.clickGuiObject(Property.atList(
				createModifyFacilityPrdGroupBar(), ok()));
	}

	public void clickCancel() {
		browser.clickGuiObject(Property.atList(
				createModifyFacilityPrdGroupBar(), cancel()));
	}

	public boolean isAddNewExisted() {
		return browser.checkHtmlObjectExists(Property.atList(
				facilityPrdGroupBar(), addNew()));
	}

	public void verifyAddNewExisted(boolean existed) {
		boolean fromUI = isAddNewExisted();
		if (existed != fromUI) {
			throw new ErrorOnPageException("Add new button should "
					+ (existed ? "" : "not ") + "exist.");
		}
		logger.info("Successfully verify add new button "
				+ (existed ? "" : "displays ") + "doesn't exist.");
	}

	public void updateFacilityPrdInfo(Data<FacilityPrdAttr> fpa) {
		setFacilityPrdCode(fpa.stringValue(FacilityPrdAttr.prdCode));
		setFacilityPrdName(fpa.stringValue(FacilityPrdAttr.prdName));
		selectFacilityPrdStatus(fpa.stringValue(FacilityPrdAttr.prdStatus));
		setFacilityPrdDesc(fpa.stringValue(FacilityPrdAttr.prdDesc));
		selectFacilityPrdType(fpa.stringValue(FacilityPrdAttr.prdType));
		setCapacity(fpa.stringValue(FacilityPrdAttr.capacity));
		selectHandicappedAccessible(fpa
				.stringValue(FacilityPrdAttr.handicappedAccessible));
	}

	public void VerifyFacilityPrdInfo(Data<FacilityPrdAttr> fpa) {
		boolean result = MiscFunctions.compareResult("Facility Prd Code",
				fpa.stringValue(FacilityPrdAttr.prdCode), getFacilityPrdCode());
		result &= MiscFunctions.compareResult("Facility Prd Name",
				fpa.stringValue(FacilityPrdAttr.prdName), getFacilityPrdName());
		result &= MiscFunctions.compareResult("Facility Prd Status",
				fpa.stringValue(FacilityPrdAttr.prdStatus),
				getFacilityPrdStatus());
		result &= MiscFunctions.compareResult("Facility Prd description",
				fpa.stringValue(FacilityPrdAttr.prdDesc), getFacilityPrdDesc());
		result &= MiscFunctions.compareResult("Facility Prd Type",
				fpa.stringValue(FacilityPrdAttr.prdType), getFacilityPrdType());
		result &= MiscFunctions.compareResult("Capacity",
				fpa.stringValue(FacilityPrdAttr.capacity), getCapacity());
		result &= MiscFunctions.compareResult("Handicapped Accessible",
				fpa.stringValue(FacilityPrdAttr.handicappedAccessible),
				getHandicappedAccessible());

		if (!result) {
			throw new ErrorOnPageException(
					"Failed to verify all facility product info in Edit faclity product widget. Please check the details from previous logs.");
		}
		logger.info("Successfully verify all facility product info in Edit faclity product widget.");
	}
}
