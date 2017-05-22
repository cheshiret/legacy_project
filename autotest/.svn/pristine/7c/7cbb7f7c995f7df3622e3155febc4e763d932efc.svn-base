/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang7
 * @Date  Mar 4, 2012
 */
public class LicMgrTitleVehicleDetailsPage extends LicMgrCommonTopMenuPage {
	private String vehicle_prefix = "VehicleDetailView-";
	private String attri_prefix = "AttributeValuesWrapper-";
	
	private static LicMgrTitleVehicleDetailsPage instance=null;
	
	public static LicMgrTitleVehicleDetailsPage getInstance(){
		if(instance==null){
			instance=new LicMgrTitleVehicleDetailsPage();
		}
		return instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV",".id", "vehicle_Info");
	}
	
	public void setSerialNum(String num){
		browser.setTextField(".id", new RegularExpression(vehicle_prefix+"\\d+\\.serialNum", false), num);
	}
	
	public void setManufacturerName(String name){
		browser.setTextField(".id", new RegularExpression(vehicle_prefix+"\\d+\\.manufacturerName", false), name);
	}
	
	public void setModelYear(String year){
		browser.setTextField(".id", new RegularExpression(vehicle_prefix+"\\d+\\.modelYear", false), year);
	}

	public void setHorsePower(String power){
		browser.setTextField(".id", new RegularExpression(vehicle_prefix+"\\d+\\.horsePower", false), power);
	}

	public void selectFuelType(String fuelType){
		browser.selectDropdownList(".id", new RegularExpression(attri_prefix+"\\d+\\.attr\\[5019\\]", false), fuelType);
	}
	
	/**
	 * Select motor fuel for motor
	 * @param fuel
	 */
	public void selectMotorFuel(String fuel){
		browser.selectDropdownList(".id", new RegularExpression(attri_prefix+"\\d+\\.attr\\[5019\\]", false), fuel);
	}
	
	/**
	 * Set motor value for motor (Title section)
	 * @param motorValue
	 */
	public void setMotorValue(String motorValue){
		browser.setTextField(".id", new RegularExpression("AddVehicleUI-\\d+\\.vehicleValue\\:CURRENCY_INPUT", false), motorValue);
	}
	
	/**
	 * 
	 */
	public void clickAddLienCompanyInfo() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("Lien Company Name.*", false));
		if(objs.length<1)
			throw new ErrorOnPageException("Could not find lien company info DIV on page.");
		browser.clickGuiObject(".class", "Html.A", ".text", "...", true, 0, objs[0]);
	}
	
	/**
	 * Set date of lien for motor lien details
	 * @param dateOfLien
	 */
	public void setDateOfLien(String dateOfLien){
		browser.setCalendarField(".id", new RegularExpression("TitleLienView-\\d+\\.lienDate_ForDisplay", false), dateOfLien);
	}
	
	public void setLienAmount(String amount){
		browser.setTextField(".id",new RegularExpression("TitleLienView-\\d+.amount:ZERO_TO_NULL", false), amount);
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	/**
	 * 
	 * @param motor
	 */
	public void setupMotorDetails(MotorInfo motor){
		LicMgrVehicleLienCompanyDetailsWidget lienCompanyWidget = LicMgrVehicleLienCompanyDetailsWidget.getInstance();
		
		setSerialNum(motor.getSerialNum());
		setManufacturerName(motor.getManufacturerName());
		setModelYear(motor.getModelYear());	
		setHorsePower(motor.getHorsePower());
		selectMotorFuel(motor.getMotorFuel());
		
		if(null != ((MotorInfo)motor).coOwners&& ((MotorInfo)motor).coOwners.size()>0){
			this.setVehicleCoOwnerInfo(((MotorInfo)motor).coOwners);
		}
	
		setMotorValue(motor.title.getMotorValue());
		if(!StringUtil.isEmpty(motor.title.lienInfo.getDateOfLien())){
			setDateOfLien(motor.title.lienInfo.getDateOfLien());
			this.removeFocus();
			setLienAmount(motor.title.lienInfo.getLienAmount());
		}
		
		if(null != motor.title.lienInfo.getLienCompanyDetailsInfo()){
			clickAddLienCompanyInfo();
			ajax.waitLoading();
			lienCompanyWidget.waitLoading();
			lienCompanyWidget.setLienCompanyDetails(motor.title.lienInfo.getLienCompanyDetailsInfo());
			lienCompanyWidget.clickOK();
			ajax.waitLoading();
			this.waitLoading();
		}		
	}
	
	/**
	 * Set multiple coowner info
	 * @param fName
	 * @param index
	 */
	public void setCoOwnerFirstName(String fName, int index){
		browser.setTextField(".id", new RegularExpression("VehicleCoownerView-\\d+\\.firstName", false), fName, index);
	}

	/**
	 * Set multiple coowner info
	 * @param lName
	 * @param index
	 */
	public void setCoOwnerLastName(String lName, int index){
		browser.setTextField(".id", new RegularExpression("VehicleCoownerView-\\d+\\.lastName", false), lName, index);
	}
	
	/**
	 * Set multiple coowner info
	 * @param mName
	 * @param index
	 */
	public void setCoOwnerMidName(String mName, int index){
		browser.setTextField(".id", new RegularExpression("VehicleCoownerView-\\d+\\.middleName", false), mName, index);
	}
	
	/**
	 * Set multiple coowner info
	 * @param suffix
	 * @param index
	 */
	public void setCoOwnerSuffix(String suffix, int index){
		browser.selectDropdownList(".id", new RegularExpression("VehicleCoownerView-\\d+\\.suffix", false), suffix, index);
	}
	
	/**
	 * Set multiple coowner info
	 * @param dateOfBirth
	 * @param index
	 */
	public void setCoOwnerDateOfBirth(String dateOfBirth, int index){
		browser.setTextField(".id", new RegularExpression("VehicleCoownerView-\\d+\\.dateOfBirth_ForDisplay", false), dateOfBirth, index);
	}
	
	/**
	 * Set multiple coowner info
	 * @param businessName
	 * @param index
	 */
	public void setCoOwnerBusinessName(String businessName, int index){
		browser.setTextField(".id", new RegularExpression("VehicleCoownerView-\\d+\\.businessName", false), businessName, index);
	}
	
	/**
	 * Set multiple coowner info
	 * @param identificationType
	 * @param index
	 */
	public void setCoOwnerIdentificationType(String identificationType, int index){
		browser.selectDropdownList(".id", new RegularExpression("VehicleCoownerView-\\d+\\.identificationType", false), identificationType, index);
	}
	
	/**
	 * Set multiple coowner info
	 * @param identification
	 * @param index
	 */
	public void setCoOwnerIdentification(String identification, int index){
		browser.setTextField(".id", new RegularExpression("VehicleCoownerView-\\d+\\.identification", false), identification, index);
	}
	
	/**
	 * Set multiple coowner info
	 * @param index
	 * @return
	 */
	public boolean isCoOwnerIdentificationStateExisted(int index){
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("VehicleCoownerView-\\d+\\.stateProvince", false));
		int size = objs.length;
		Browser.unregister(objs);
		if(size > index)
			return true;
		return false;
	}
	
	/**
	 * Set multiple coowner info
	 * @param state
	 * @param index
	 */
	public void selectCoOwnerIdentificationState(String state, int index){
		browser.selectDropdownList(".id", new RegularExpression("VehicleCoownerView-\\d+\\.stateProvince", false), state, index);
	}

	
	/**
	 * Click Add button for adding co-owner
	 */
	public void clickAddButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}
	
	/**
	 * Setup all of the co-owners information 
	 * @param coOwner List
	 */
	public void setVehicleCoOwnerInfo(List<OwnerInfo> ownerList){
		for(int i =0;i<ownerList.size();i++){
			this.setVehicleCoOwner(ownerList.get(i),i);
			if(i<ownerList.size()-1){
				clickAddButton();
				ajax.waitLoading();
				waitLoading();
			}
			
		}
	}
	
	/**
	 * set vehicle coOwner info.
	 * @param coOwner
	 */
	public void setVehicleCoOwner(OwnerInfo coOwner,int index){
		setCoOwnerFirstName(coOwner.firstName, index);
		setCoOwnerLastName(coOwner.lastName,index);
		if("" != coOwner.midName)
			setCoOwnerMidName(coOwner.midName,index);
		if("" != coOwner.suffix)
			setCoOwnerSuffix(coOwner.suffix, index);
		if("" != coOwner.dateOfBirth)
			setCoOwnerDateOfBirth(coOwner.dateOfBirth, index);
		if("" != coOwner.businessName)
			setCoOwnerBusinessName(coOwner.businessName, index);
		if("" != coOwner.identifierType){
			setCoOwnerIdentificationType(coOwner.identifierType, index);
			ajax.waitLoading();
			this.waitLoading();
		}
		if("" != coOwner.identifierNum)
			setCoOwnerIdentification(coOwner.identifierNum, index);
		if(isCoOwnerIdentificationStateExisted(index) && "" != coOwner.identifierState)
			selectCoOwnerIdentificationState(coOwner.identifierState, index);
		
	}
	
	 /**
     * remove focus.
     */
    public void removeFocus(){
    	browser.clickGuiObject(".class", "Html.TD", ".text", "Title Details");
    }
	

}
