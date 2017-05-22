/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import java.util.HashMap;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrEditVehicleDetailsPage.java
 * @Date:Mar 21, 2011
 * @Description:
 * @author asun
 */
public class LicMgrEditVehicleDetailsPage extends LicMgrVehicleProductCommonPage {

	private static LicMgrEditVehicleDetailsPage instance=null;
	
	protected LicMgrEditVehicleDetailsPage(){}
	
	public static LicMgrEditVehicleDetailsPage getInstance(){
		if(instance == null){
			instance=new LicMgrEditVehicleDetailsPage();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV",".id", "VehicleRTIDetailsTabPanel");
	}
	
	/**select vehicle status*/
	public void selectStatus(String status){
		RegularExpression regx=new RegularExpression("VehicleRTISummaryView-\\d+\\.active",false);
		browser.selectDropdownList(".id", regx, status);
	}
	
	/**View change history*/
	public void clickViewChangeHistory(){
		browser.clickGuiObject(".class", "Html.A",".text","View Change History");
	    ajax.waitLoading();
	}
	
	/**click 'Pricing' link*/
	public void clickPricingTab(){
		browser.clickGuiObject(".class", "Html.SPAN",".text","Pricing");
		ajax.waitLoading();
	}
	
	/**click 'Store Assignments' link*/
	public void clickStoreAssignmentsTab(){
		browser.clickGuiObject(".class", "Html.SPAN",".text","Agent Assignments");
	    ajax.waitLoading();
	}
	
	/**click 'Print Documents' link*/
	public void clickPrintDocumentsTab(){
		browser.clickGuiObject(".class", "Html.SPAN",".text","Print Documents");
	}
	
	/**click 'Contractor Fees' link*/
	public void clickContractorFeesTab(){
		browser.clickGuiObject(".class", "Html.SPAN",".text","Contractor Fees");
	    ajax.waitLoading();
	}

	@Override
	public void setVehicleInfo(VehicleRTI vehicle) {
		this.selectStatus(vehicle.getStatus());
		super.setVehicleInfo(vehicle);
	}
	
	public String getProductID(){
		String productID = "";
		Property[] p = new Property[2];
		p[0] = new Property(".class","Html.SPAN");
		p[1] = new Property(".text", "Product ID");
		IHtmlObject[] objs = browser.getHtmlObject(p);
		if(objs.length<1){
			throw new ItemNotFoundException("Product ID SPAN Object is not fond.");
		}				
		
		Property[] subP = new Property[1];
		subP[0] = new Property(".class","Html.INPUT.text");		
		productID = browser.getTextFieldValue(subP, objs[0]);
		
		Browser.unregister(objs);
		return productID;		
	}
	
	public String getVehicleStatus(){
		return browser.getDropdownListValue(".id", new RegularExpression("VehicleRTISummaryView-\\d+\\.active",false), 0);
	}
	
	public String getVehicleCode(){
		return browser.getTextFieldValue(".id", new RegularExpression("VehicleRTISummaryView-\\d+\\.code",false));
	}
	
	public String getVehicleName(){
		return browser.getTextFieldValue(".id", new RegularExpression("VehicleRTISummaryView-\\d+\\.name",false));
	}
	
	public String getProductGroup(){
		return browser.getDropdownListValue(".id", new RegularExpression("AddModifyVehicleRTIUIModel-\\d+\\.productGroup",false), 0);
	}
	
	public String getVehicleType(){
		return browser.getDropdownListValue(".id", new RegularExpression("VehicleRTISummaryView-\\d+\\.vehicleType",false), 0);
	}
	
	public HashMap<String,Boolean> getCustomerClass(){
		HashMap<String,Boolean> customerClassInfo = new HashMap<String,Boolean>();
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Customer Class.*",false));
		
		if(objs.length<1){
			throw new ItemNotFoundException("Customer Class Table not found.");
		}
		
		Property[] checkBoxProperty = new Property[1];
		checkBoxProperty[0] = new Property(".id",new RegularExpression("VehicleRTIDetailedSummaryView-\\d+\\.customerClasses_\\d+",false));
		IHtmlObject[] checkBoxObjs = browser.getCheckBox(checkBoxProperty, objs[0]);
				
		for(int i=0; i<checkBoxObjs.length; i++){
			IHtmlObject[] textObjs = browser.getHtmlObject(".className","trailing",".for", checkBoxObjs[i].id());
			if(textObjs.length<1){
				throw new ItemNotFoundException("Customer Class Text object not found.");
			}
			
			ICheckBox checkBoxObj = (ICheckBox)checkBoxObjs[i];
			customerClassInfo.put(textObjs[0].text(), checkBoxObj.isSelected());			
			
			Browser.unregister(textObjs);
		}
		
		Browser.unregister(checkBoxObjs);
		Browser.unregister(objs);
		
		return customerClassInfo;
	}
	
	public String getValidToDate(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("^ValidDateCalculationTypeSelectionHolder-\\d+\\.selectedValidDateCalculationType",false), 0);
	}
	
	public String getValidMonths(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("RelativeVehicleRegistrationValidDateCalcView-\\d+\\.validMonths",false));
	}
	
	public String getValidToMonth(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("^FixedVehicleRegistrationValidDateCalcView-\\d+\\.validToMonth",false),0);
	}
	
	public String getValidToDay(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("^FixedVehicleRegistrationValidDateCalcView-\\d+\\.validToDay",false));
	}
	
	public String getValidYears(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("^FixedVehicleRegistrationValidDateCalcView-\\d+\\.validYears",false));
	}
	
	public String getCycleStartYear(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("^FixedVehicleRegistrationValidDateCalcView-\\d+\\.cycleStartYear",false));
	}
	
	public String getAdvanceRenewalDays(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("^VehicleRegistrationProductValidToDateCalcView-\\d+\\.advanceRenewalDays",false));
	}
	
	public String getLateRenewal(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("^RelativeVehicleRegistrationValidDateCalcView-\\d+\\.lateRenewal",false));
	}
	
	public String getLateRenewalUnit(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("^RelativeVehicleRegistrationValidDateCalcView-\\d+\\.lateRenewalUnit",false),0);
	}
	
	public HashMap<String,Boolean> getBoatUseTypes(){
		HashMap<String,Boolean> boatUseTypes = new HashMap<String,Boolean>();
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", 
				new RegularExpression(" Boat Use Types.*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Boat Use Types Object not found.");
		}
		
		Property[] checkBoxProperty = new Property[1];
		checkBoxProperty[0] = new Property(".id",new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[401\\]_\\d+",false));
		IHtmlObject[] checkBoxObjs = browser.getCheckBox(checkBoxProperty, objs[0]);
		
		for(int i=0; i<checkBoxObjs.length; i++){
			IHtmlObject[] textObjs = browser.getHtmlObject(".className","trailing",".for", checkBoxObjs[i].id());
			if(textObjs.length<1){
				throw new ItemNotFoundException("Customer Class Text object not found.");
			}
			
			ICheckBox checkBoxObj = (ICheckBox)checkBoxObjs[i];
			boatUseTypes.put(textObjs[0].text(), checkBoxObj.isSelected());
			
			Browser.unregister(textObjs);
		}
		
		Browser.unregister(checkBoxObjs);
		Browser.unregister(objs);
		
		return boatUseTypes;
	}
	
	public String getMinLengthOfFt(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[402\\]_feet",false));
	}
	
	public String getMinLengthOfIn(){
		return browser.getTextFieldValue(".id",
				new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[402\\]_inches",false));
	}
	
	public String getMaxLengthOfFt(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[403\\]_feet",false));
	}
	
	public String getMaxLengthOfIn(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[403\\]_inches",false));		
	}
	
	/**
	 * Compare vehicle info from edit detail page.
	 * Note: use this method the customer class info should initial all info,
	 * and boat use type should be initial all info
	 * @param expectVehicle
	 */
	public boolean compareVehicleInfo(VehicleRTI expectVehicle){
		boolean result = true;
		String actualValue = "";
		
		actualValue = this.getVehicleStatus();
		if(!actualValue.equalsIgnoreCase(expectVehicle.getStatus())){
			result = false;
			logger.error("Expect vehicle status should be " + expectVehicle.getStatus() 
					+ ", but actually is " + actualValue);
		}
		
		actualValue = this.getVehicleCode();
		if(!actualValue.equalsIgnoreCase(expectVehicle.getPrdCode())){
			result = false;
			logger.error("Expect vehicle code should be " + expectVehicle.getPrdCode() 
					+ ", but actually is " + actualValue);
		}
		
		actualValue = this.getVehicleName();
		if(!actualValue.equalsIgnoreCase(expectVehicle.getPrdName())){
			result = false;
			logger.error("Expect vehicle name should be " + expectVehicle.getPrdName()
					+ ", but actually is " + actualValue);
		}
		
		actualValue = this.getProductGroup();
		if(!actualValue.equalsIgnoreCase(expectVehicle.getPrdGroup())){
			result = false;
			logger.error("Expect product group should be " + expectVehicle.getPrdGroup()
					+ ", but actually is " + actualValue);
		}else {
			logger.info("Vehicle group is correct.");
		}
		
		actualValue = this.getVehicleType();
		if(!actualValue.equalsIgnoreCase(expectVehicle.getVehicleType())){
			result = false;
			logger.error("Expect vehicle type should be " + expectVehicle.getVehicleType()
					+ ", but actually is " + actualValue);
		}
		
		HashMap<String, Boolean> actualCustClass = this.getCustomerClass();
		if(!actualCustClass.equals(expectVehicle.getCustClass())){
			result = false;
			logger.error("Customer class info is not correct in page.");
		}
		
		if(this.isValidToDateExists()){
			actualValue = this.getValidToDate();
			if(!actualValue.equalsIgnoreCase(expectVehicle.getValidToDate())){
				result = false;
				logger.error("Expect valid to date should be " + expectVehicle.getValidToDate()
						+ ", but actually is " + actualValue);
			}
		}
		
		if(this.isValidMonthsExists()){
			actualValue = this.getValidMonths();
			if(!actualValue.equalsIgnoreCase(expectVehicle.getValidMonths())){
				result = false;
				logger.error("Expect valid months should be " + expectVehicle.getValidMonths()
						+ ", but actually is " + actualValue);
			}
		}
				
		if(this.isValidToMonthExists()){
			actualValue = this.getValidToMonth();
			if(!actualValue.equalsIgnoreCase(expectVehicle.getMonth())){
				result = false;
				logger.error("Expect valid to month should be " + expectVehicle.getMonth()
						+ ", but actually is " + actualValue);
			}
		}
		
		if(this.isValidToDayExists()){
			actualValue = this.getValidToDay();
			if(!actualValue.equalsIgnoreCase(expectVehicle.getDay())){
				result = false;
				logger.error("Expect valid to day should be " + expectVehicle.getDay()
						+ ", but actually is " + actualValue);
			}
		}
		
		if(this.isValidYearsExists()){
			actualValue = this.getValidYears();
			if(!actualValue.equalsIgnoreCase(expectVehicle.getValidYears())){
				result = false;
				logger.error("Expect valid years should be " + expectVehicle.getValidYears()
						+ ", but actually is " + actualValue);
			}
		}
		
		if(this.isCycleStartYearExists()){
			actualValue = this.getCycleStartYear();
			if(!this.getCycleStartYear().equalsIgnoreCase(expectVehicle.getCycleStartYear())){
				result = false;
				logger.error("Expect cycle start year should be " + expectVehicle.getCycleStartYear()
						+ ", but actually is " + actualValue);
			}
		}
		
		if(this.isAdvRenewalDaysExists()){
			actualValue = this.getAdvanceRenewalDays();
			if(!this.getAdvanceRenewalDays().equalsIgnoreCase(expectVehicle.getAdvanceRenewalDays())){
				result = false;
				logger.error("Expect advanced renewal Days should be " + expectVehicle.getAdvanceRenewalDays()
						+ ", but actually is " + actualValue);
			}
		}
		
		if(this.isLateRenewalExist()){
			actualValue = this.getLateRenewal();
			if(!actualValue.equalsIgnoreCase(expectVehicle.getLateRenewal())){
				result = false;
				logger.error("Expect late renewal should be " + expectVehicle.getLateRenewal()
						+ ", but actually is " + actualValue);
			}
		}
		
		if(this.isLateRenUnitExist()){
			actualValue = this.getLateRenewalUnit();
			if(!actualValue.equalsIgnoreCase(expectVehicle.getLateRenewUnit())){
				result = false;
				logger.error("Expect late renewal unit should be " + expectVehicle.getLateRenewUnit()
						+ ", but actually is " + actualValue);
			}
		}
		
		if(this.isBoatUseTypCheckBoxExist()){
			HashMap<String, Boolean> acutalBoatUseTypes = this.getBoatUseTypes();
			if(!acutalBoatUseTypes.equals(expectVehicle.getBoatUseTyp())){
				result = false;
				logger.error("Boat use types is not correct in page.");
			}
		}
		
		if(this.isMinLengthExist()){
			int expectMinLenthOfFt = Integer.parseInt(expectVehicle.getMinLenthOfFt()) + Integer.parseInt(expectVehicle.getMinLenthOfIn())/12;
			actualValue = this.getMinLengthOfFt();
			if(!actualValue.equalsIgnoreCase(String.valueOf(expectMinLenthOfFt))){
				result = false;
				logger.error("Expect min length of FT should be " + expectMinLenthOfFt
						+ ", but actually is " + actualValue);
			}
			
			int expectMinLenthOfIn = Integer.parseInt(expectVehicle.getMinLenthOfIn())%12;
			actualValue = this.getMinLengthOfIn();
			if(!actualValue.equalsIgnoreCase(String.valueOf(expectMinLenthOfIn))){
				result = false;
				logger.error("Expect min length of IN should be " + expectMinLenthOfIn
						+ ", but actually is " + actualValue);
			}
		}
		
		if(this.isMaxLengthExist()){
			int expectMaxLenthOfFt = Integer.parseInt(expectVehicle.getMaxLenthOfFt()) + Integer.parseInt(expectVehicle.getMaxLenthOfIn())/12;
			actualValue = this.getMaxLengthOfFt();
			if(!actualValue.equalsIgnoreCase(String.valueOf(expectMaxLenthOfFt))){
				result = false;
				logger.error("Expect max length of FT should be " + expectMaxLenthOfFt
						+ ", but actually is " + actualValue);
			}
			int expectMaxLenthOfIn = Integer.parseInt(expectVehicle.getMaxLenthOfIn())%12;
			actualValue = this.getMaxLengthOfIn();
			if(!actualValue.equalsIgnoreCase(String.valueOf(expectMaxLenthOfIn))){
				result = false;
				logger.error("Expect max length of IN should be " + expectMaxLenthOfIn
						+ ", but actually is " + actualValue);
			}
		}
		
		return result;
		
	}

}
