package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.pos.capehatterasorvpermitsales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovCapeHattterasORVPermitSalePage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: (DEFECT-63093->UWP-1419) Verify Cape Hattteras ORV Permit Sale Page UI
 * @Preconditions:
 * 1.Setup facility product group category as "Permit"
 * 2.Configure attribute "EventTicketLottery" id=8462 to 'YES' for this facility
 *   INSERT INTO D_LOC_ATTR_VALUE(ID,LOC_ID,ATTR_ID,VALUE) VALUES ( GET_SEQUENCE('D_LOC_ATTR_VALUE'),70853,8462,'/showPage.do?name=common&commonPath=/htm/CapeHatteras_Landing.html');
 * @LinkSetUp:
 * d_inv_new_facility:id=140
 * d_inv_add_mpos:id=390,400,410,420
 * d_fin_assi_pos:id=80,90,100,110
 * @SPEC:
 * Cape Hatteras ORV Permit Sale page - Delivery Method [TC:108745]
 * Cape Hatteras ORV Permit Sale Page - Effective Date [TC:108752]
 * Cape Hatteras ORV Permit Sale Page - Driver License Information [TC:108753]
 * Cape Hatteras ORV Permit Sale Page - Vehicle Plate Information [TC:108754]
 * Cape Hatteras ORV Permit Sale Page - Vehicle Details Information [TC:108755]
 * @Task#:AUTO-2123
 * 
 * @author SWang
 * @Date  Apr 15, 2014
 */
public class UICheck extends RecgovTestCase {
	private String commonSchema, pageTitle, startDateNote, canada, unitedStates, algeria, mexico, selectOne, other, spaceString;
	private List<String> permitOffices, countries, countries_VehiclePlate, countriesFromUI, canadaRelatedStates, unitedStateRelatedStates, vehicleMake, vehicleColor;

	public void execute() {
		//Go to came hatteras off-road vehicle permit details page
		web.invokeURL(url);	
		web.gotoViewAsListPage(bd);
		web.gotoCapeHatterasOffRoadVehiclePermitDetailsPg(bd.park);

		verifyCapeHatterasOrvPermitSalePgUI();
	}

	public void wrapParameters(Object[] param) {
		bd.whereTextValue = "Cape Hatteras National Seashore";
		bd.parkId = DataBaseFunctions.getFacilityID(bd.whereTextValue, schema);
		bd.interestInValue = UwpUnifiedSearch.PERMITSANDWILDERNESS_INSTERETED_IN;
		bd.contractCode = "NRSO";
		bd.isUnifiedSearch=isUnifiedSearch();
		commonSchema = DataBaseFunctions.getSchemaName("COMMON", env);
		
		permitOffices = new ArrayList<String>();
		countries = new ArrayList<String>();
		countries_VehiclePlate= new ArrayList<String>();
		countriesFromUI = new ArrayList<String>();
		canadaRelatedStates=new ArrayList<String>();
		unitedStateRelatedStates = new ArrayList<String>();
		vehicleMake = new ArrayList<String>();
		vehicleColor = new ArrayList<String>();
		
		pageTitle = "Cape Hatteras Off-Road Vehicle Permits Select the type of permit you would like to purchase and complete the form (All Fields are Mandatory). PLEASE NOTE: A $4.50 processing fee will be charged in addition to the permit fee. This charge will appear in the shopping cart upon checkout.";
		startDateNote = ".*Your permit will be valid for 7 days starting from your selected start date\\. Weekly ORV permits may only be reserved 60 days in advance of your arrival\\. Please allow 14 days for delivery if you select Standard Shipping as delivery method\\.";
		
		permitOffices.add("Select one...");
		permitOffices.add("Bodie Island Permit Office");
		permitOffices.add("Hatteras Island Permit Office");
		permitOffices.add("Ocracoke Island Permit Office");
		
		selectOne = "Select one...";
		canada = "Canada";
		unitedStates = "United States";
		algeria = "Algeria";
		mexico = "Mexico";
		other = "Other (Specify)";
		spaceString = " 23423  ";
		
		countries = web.getCountries(commonSchema);
		countries_VehiclePlate = Arrays.asList(new String[]{"United States", "Canada", "Mexico"});
		canadaRelatedStates.add(selectOne);
		canadaRelatedStates.addAll(web.getContractRelatedStates(commonSchema, canada));
		unitedStateRelatedStates.add(selectOne);
		unitedStateRelatedStates.addAll(web.getContractRelatedStates(commonSchema, unitedStates));
		vehicleMake.add(selectOne);
		vehicleMake.addAll(web.getVehicleMakes(schema));
		vehicleMake.add(other);
		vehicleColor.add(selectOne);
		vehicleColor.addAll(web.getVehicleColors(schema));
		vehicleColor.add(other);
	}

	public void verifyCapeHatterasOrvPermitSalePgUI(){
		RecgovCapeHattterasORVPermitSalePage capeHattterasORVPermitSalePg = RecgovCapeHattterasORVPermitSalePage.getInstance();
		boolean result = MiscFunctions.compareResult("Page title", pageTitle, capeHattterasORVPermitSalePg.getPageTitle());
		
		//Start date
        result &= MiscFunctions.compareResult("Start date is disabled", true, capeHattterasORVPermitSalePg.isStartDateDisabled());
        capeHattterasORVPermitSalePg.clickWeeklyORVPermitRadio();
        result &= MiscFunctions.compareResult("Start date is abled", false, capeHattterasORVPermitSalePg.isStartDateDisabled());
        capeHattterasORVPermitSalePg.clickAnnualOrvPermitRadio();
        result &= MiscFunctions.compareResult("Start date is disabled", true, capeHattterasORVPermitSalePg.isStartDateDisabled());
        result &= MiscFunctions.compareResult("Start date note", true, capeHattterasORVPermitSalePg.isFormFieldWrapperExist(startDateNote));
        
        //Delivery Method
		result &= MiscFunctions.compareResult("Standard Shipping note", true, capeHattterasORVPermitSalePg.isStandardShippingNoteExist());
		capeHattterasORVPermitSalePg.clickPickUpAtParkRadio();
		result &= MiscFunctions.compareResult("Permit Office", permitOffices.toString(), capeHattterasORVPermitSalePg.getPermitOffices().toString());

		//Driver License
		countriesFromUI = capeHattterasORVPermitSalePg.getDriverLicenseCountries();
		result &= MiscFunctions.compareResult("The default Driver License Country", unitedStates, countriesFromUI.get(0));
		result &= MiscFunctions.compareResult("The third Driver License Country", StringUtil.EMPTY, countriesFromUI.get(2));
		countriesFromUI.remove(countriesFromUI.get(2));
		result &= MiscFunctions.compareResult("Driver License Country", countries.toString(), countriesFromUI.toString());
		result &= MiscFunctions.compareResult("The default Driver License State", selectOne, capeHattterasORVPermitSalePg.getDriverLicenseState());
		result &= MiscFunctions.compareResult("Driver License State", unitedStateRelatedStates.toString(), capeHattterasORVPermitSalePg.getDriverLicenseStates().toString());
		result &= MiscFunctions.compareResult("Driver License state input", false, capeHattterasORVPermitSalePg.isDriverLicenseStateInputExist());
		capeHattterasORVPermitSalePg.selectDriverLicenseCountry(algeria);
		result &= MiscFunctions.compareResult("Driver License state input", true, capeHattterasORVPermitSalePg.isDriverLicenseStateInputExist());
		capeHattterasORVPermitSalePg.selectDriverLicenseCountry(canada);
		capeHattterasORVPermitSalePg.clickAlertMsgToRefreshPg();
		result &= MiscFunctions.compareResult("Driver License State", canadaRelatedStates.toString(), capeHattterasORVPermitSalePg.getDriverLicenseStates().toString());
		
		//Vehicle Plate
		countriesFromUI.clear();
		countriesFromUI = capeHattterasORVPermitSalePg.getVehiclePlateCountries();
		result &= MiscFunctions.compareResult("The default Vehicle Plate Country", unitedStates, countriesFromUI.get(0));
		result &= MiscFunctions.compareResult("The third Vehicle Plate Country", StringUtil.EMPTY, countriesFromUI.get(2));
		countriesFromUI.remove(countriesFromUI.get(2));
		result &= MiscFunctions.compareResult("Vehicle Plate Country", countries_VehiclePlate.toString(), countriesFromUI.toString()); //DEFECT-63093 
		result &= MiscFunctions.compareResult("The default Vehicle Plate State", selectOne, capeHattterasORVPermitSalePg.getVehiclePlateState());
		result &= MiscFunctions.compareResult("Vehicle Plate State", unitedStateRelatedStates.toString(), capeHattterasORVPermitSalePg.getVehiclePlateStates().toString());
		result &= MiscFunctions.compareResult("Vehicle Plate state input", false, capeHattterasORVPermitSalePg.isVehiclePlateStateInputExist());
		capeHattterasORVPermitSalePg.selectVehiclePlateCountry(mexico);
		capeHattterasORVPermitSalePg.clickAlertMsgToRefreshPg();
		result &= MiscFunctions.compareResult("Vehicle Plate state input", true, capeHattterasORVPermitSalePg.isVehiclePlateStateInputExist());
		capeHattterasORVPermitSalePg.selectVehiclePlateCountry(canada);
		capeHattterasORVPermitSalePg.clickAlertMsgToRefreshPg();
		result &= MiscFunctions.compareResult("Vehicle Plate State", canadaRelatedStates.toString(), capeHattterasORVPermitSalePg.getVehiclePlateStates().toString());
		
		//Vehicle Details
		result &= MiscFunctions.compareResult("The default Vehicle Make", selectOne, capeHattterasORVPermitSalePg.getVehicleMake());
		result &= MiscFunctions.compareResult("Vehicle Make", vehicleMake.toString(), capeHattterasORVPermitSalePg.getVehicleMakes().toString());
		result &= MiscFunctions.compareResult("Vehicle Make Input", false, capeHattterasORVPermitSalePg.isVehicleMakeInputExist());
		capeHattterasORVPermitSalePg.selectVehicleMake(other);
		result &= MiscFunctions.compareResult("Vehicle Make Input", true, capeHattterasORVPermitSalePg.isVehicleMakeInputExist());
		result &= MiscFunctions.compareResult("The default Vehicle Color", selectOne, capeHattterasORVPermitSalePg.getVehicleColor());
		result &= MiscFunctions.compareResult("Vehicle Color", vehicleColor.toString(), capeHattterasORVPermitSalePg.getVehicleColors().toString());
		result &= MiscFunctions.compareResult("Vehicle Color Input", false, capeHattterasORVPermitSalePg.isVehicleColorInputExist());
		capeHattterasORVPermitSalePg.selectVehicleColor(other);
		result &= MiscFunctions.compareResult("Vehicle Color Input", true, capeHattterasORVPermitSalePg.isVehicleColorInputExist());
		
//		//Text field 
//		capeHattterasORVPermitSalePg.selectDriverLicenseCountry(algeria);
//		capeHattterasORVPermitSalePg.selectVehiclePlateCountry(mexico);
//		capeHattterasORVPermitSalePg.setDriverLicenseNum(spaceString);
//		capeHattterasORVPermitSalePg.setDriverLicenseStateInput(spaceString);
//		capeHattterasORVPermitSalePg.setVehiclePlateNum(spaceString);
//		capeHattterasORVPermitSalePg.setVehiclePlateStateInput(spaceString);
//		capeHattterasORVPermitSalePg.setOwnerName(spaceString);
//		capeHattterasORVPermitSalePg.setVehicleYear(spaceString);
//		capeHattterasORVPermitSalePg.setVehicleMakeInput(spaceString);
//		capeHattterasORVPermitSalePg.setVehicleColorInput(spaceString);
//		capeHattterasORVPermitSalePg.makeVideo();
//		capeHattterasORVPermitSalePg.clickAddToShoppingCart();
//		
//		result &= MiscFunctions.compareResult("Driver License Number", spaceString.trim(), capeHattterasORVPermitSalePg.getDriverLicenseNum());
//		result &= MiscFunctions.compareResult("Driver License state input", spaceString.trim(), capeHattterasORVPermitSalePg.getDriverLicenseStateInput());
//		result &= MiscFunctions.compareResult("Vehicle plate number", spaceString.trim(), capeHattterasORVPermitSalePg.getVehiclePlateNum());
//		result &= MiscFunctions.compareResult("Vehicle plate state input", spaceString.trim(), capeHattterasORVPermitSalePg.getVehiclePlateStateInput());
//		result &= MiscFunctions.compareResult("Owner name", spaceString.trim(), capeHattterasORVPermitSalePg.getOwnerName());
//		result &= MiscFunctions.compareResult("Vehicle year", spaceString.trim(), capeHattterasORVPermitSalePg.getVehicleYear());
//		result &= MiscFunctions.compareResult("Vehicle make input", spaceString.trim(), capeHattterasORVPermitSalePg.getVehicleMakeInput());
//		result &= MiscFunctions.compareResult("Vehicle color input", spaceString.trim(), capeHattterasORVPermitSalePg.getVehicleColorInput());
		
		if(!result){
			throw new ErrorOnPageException("Failed to verify all check points in Cape Hatteras ORV Permit sale page. Please find the details from previous logs.");
		}else logger.info("Successfully verify all check points in Cape Hatteras ORV permit sale page.");
	}
}
