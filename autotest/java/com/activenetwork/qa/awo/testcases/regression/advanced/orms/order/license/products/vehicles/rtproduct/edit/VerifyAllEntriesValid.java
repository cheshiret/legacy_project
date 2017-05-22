/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.rtproduct.edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrEditVehicleDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @ScriptName EditVehicleRTProduct_AllEntriesValid.java
 * @Date:Mar 31, 2011
 * @Description:
 * @author asun
 */
public class VerifyAllEntriesValid extends
		LicenseManagerTestCase {
	
	private List<Exception> list=new ArrayList<Exception>();
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private HashMap<String, Boolean> custClass = new HashMap<String, Boolean>();
    private int count=0;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu("Vehicles");
		if(!lm.verifyProductExistInSys(schema, vehicleRTI.getPrdCode(), vehicleRTI.getPrdName())) {
			lm.addVehicleProduct(vehicleRTI);
		}
		
		try{
			//The Vehicle RT Product Name is not specified, i.e. left null/blank.			
			count++;
			vehicleRTI.setPrdName("");
			lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("The Product Name is required. Please specify the Name.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//At least one Customer Class has not been selected.
			count++;
			initializeVehicleInfo();
			custClass.clear();
//			vehicle.custClass.put("Trapper", false);
			lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("At least one Customer Class must be selected. Please select the Customer Class from the list provided.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Valid to Date \ufffdPurchase Date/Previous Valid To Date plus Valid Months\ufffd has been specified, and Valid Months has not been specified.
			count++;
			initializeVehicleInfo();
			vehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
			vehicleRTI.setValidMonths("");
			lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Valid Months is required. Please specify the Valid Months.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Valid to Date \ufffdPurchase Date/Previous Valid To Date plus Valid Months\ufffd has been specified, and Valid Months has not been specified.
			count++;
			initializeVehicleInfo();
			vehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
			vehicleRTI.setValidMonths("");
			lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Valid Months is required. Please specify the Valid Months.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Valid Months has been specified, and is not an integer value 
			count++;
			initializeVehicleInfo();
			vehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
			vehicleRTI.setValidMonths("1.5");
			lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Valid Months must be a number 1 or greater. Please re-enter the Valid Months.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Valid Months has been specified, and is less than 1
			count++;
			initializeVehicleInfo();
			vehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
			vehicleRTI.setValidMonths("0");
			lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Valid Months must be a number 1 or greater. Please re-enter the Valid Months.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Valid to Date \ufffdFixed Month & Day plus Valid Years\ufffd has been specified, and Month has not been specified.
			count++;
			initializeVehicleInfo();
			vehicleRTI.setValidToDate("Fixed Month & Day plus Valid Years");
//			vehicleRTI.setValidMonths("");
			vehicleRTI.setMonth("");
			lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Month is required. Please specify the Month.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Valid to Date \ufffdFixed Month & Day plus Valid Years\ufffd has been specified, and Day has not been specified.
			count++;
			initializeVehicleInfo();
			vehicleRTI.setValidToDate("Fixed Month & Day plus Valid Years");
			vehicleRTI.setDay("");
			lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Day is required. Please specify the Day.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Month \ufffdJan\ufffd, or \ufffdMar\ufffd, or \ufffdMay\ufffd, or \ufffdJul\ufffd, or \ufffdc\ufffd, or \ufffdOct\ufffd, or \ufffdDec\ufffd has been specified, and the corresponding 
			//Day is not an integer from 1 to 31 inclusive.
			count++;
			initializeVehicleInfo();
			String[] months=new String[]{"Jan","Mar","May","Jul","Jul","Oct","Dec"};	
			String[] days=new String[]{"0","32"};
			this.veryfyDay_Month(vehicleRTI, months, days);
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Month \ufffdApr\ufffd, or \ufffdJun\ufffd, or \ufffdSep\ufffd, or \ufffdNov\ufffd has been specified, and the corresponding 
			//Day is not an integer value from 1 to 30 inclusive.
			count++;
			initializeVehicleInfo();
			String[] months=new String[]{"Apr","Jun","Sep","Nov"};	
			String[] days=new String[]{"0","31"};
			this.veryfyDay_Month(vehicleRTI, months, days);
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Month \ufffdFeb\ufffd has been specified, and the corresponding 
			//Day is not an integer value from 1 to 28 inclusive.
			count++;
			initializeVehicleInfo();
			String[] months=new String[]{"Feb"};	
			String[] days=new String[]{"0","29"};
			this.veryfyDay_Month(vehicleRTI, months, days);
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Valid to Date \ufffdFixed Month & Day plus Valid Years\ufffd has been specified, 
			//and Valid Years has not been specified.
			count++;
			initializeVehicleInfo();
			vehicleRTI.setValidToDate("Fixed Month & Day plus Valid Years");
		    vehicleRTI.setValidYears("");
		    lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Valid Years is required. Please specify the Valid Years.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Valid Years has been specified, and is not an integer 
			//value 1 or greater.
			count++;
			initializeVehicleInfo();
			vehicleRTI.setValidToDate("Fixed Month & Day plus Valid Years");
		    vehicleRTI.setValidYears("1.5");
		    lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Valid Years must be a number 1 or greater. Please re-enter the Valid Years.");
			
			initializeVehicleInfo();
			vehicleRTI.setValidYears("0");
			lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Valid Years must be a number 1 or greater. Please re-enter the Valid Years.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Valid to Date \ufffdFixed Month & Day plus Valid Years\ufffd has been specified, 
			//and Cycle Start Year has not been specified.		
			count++;
			initializeVehicleInfo();
			vehicleRTI.setValidToDate("Fixed Month & Day plus Valid Years");
		    vehicleRTI.setCycleStartYear("");
		    lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Cycle Start Year is required. Please specify the Cycle Start Year.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Cycle Start Year has been specified, and not is a valid four digit year, 
			//or is greater than the Current Year (in Contract Time).
			count++;
			initializeVehicleInfo();
			vehicleRTI.setValidToDate("Fixed Month & Day plus Valid Years");
		    vehicleRTI.setCycleStartYear("333");
		    lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Cycle Start Year must be a valid four-digit year and cannot be greater than the current year. Please re-enter the Cycle Start Year.");
			//greater than the Current Year (in Contract Time).
			initializeVehicleInfo();
			vehicleRTI.setValidToDate("Fixed Month & Day plus Valid Years");
			String thisYear=DateFunctions.getToday().split("/")[2].trim();
			vehicleRTI.setCycleStartYear((Integer.parseInt(thisYear)+1)+"");
			lm.editVehicleProduct(vehicleRTI); 
			this.verifyErrorMessage("Cycle Start Year must be a valid four-digit year and cannot be greater than the current year. Please re-enter the Cycle Start Year.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//The Product Group is Registration and Advance Renewal Days has not been specified.
			count++;
			initializeVehicleInfo();
			vehicleRTI.setPrdGroup("Registration");
			vehicleRTI.setVehicleType("Motor");
			vehicleRTI.setAdvanceRenewalDays("");
			lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Advance Renewal Days is required. Please specify the Advance Renewal Days.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Advance Renewal Days has been specified, and is not an integer 1 or greater.
			count++;
			initializeVehicleInfo();
			vehicleRTI.setPrdGroup("Registration");
			vehicleRTI.setVehicleType("Motor");
			vehicleRTI.setAdvanceRenewalDays("1.5");
			lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Advance Renewal Days must be a number 1 or greater. Please re-enter the Advance Renewal Days.");
			vehicleRTI.setAdvanceRenewalDays("0");
			lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Advance Renewal Days must be a number 1 or greater. Please re-enter the Advance Renewal Days.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Valid to Date \ufffdPurchase Date/Previous Valid To Date plus Valid Months\ufffd has been specified, 
			//and Late Renewal has not been specified.
			count++;
			initializeVehicleInfo();
			vehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
	        vehicleRTI.setLateRenewal("");
	        lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Late Renewal has not been specified. Please specify the Late Renewal.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Late Renewal has been specified, and is not an integer value 0 or greater.
			count++;
			initializeVehicleInfo();
			vehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
	        vehicleRTI.setLateRenewal("-1");
			lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Late Renewal must be a number 0 or greater. Please re-enter the Late Renewal.");
			initializeVehicleInfo();
			vehicleRTI.setLateRenewal("1.5");
			lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("Late Renewal must be a number 0 or greater. Please re-enter the Late Renewal.");
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Late Renewal has been specified, a Late Renewal Unit (\ufffdDays\ufffd or \ufffdMonths\ufffd) has not been specified.
			count++;
			initializeVehicleInfo();
			vehicleRTI.setPrdGroup("Registration");
			vehicleRTI.setVehicleType("Motor");
			vehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
	        vehicleRTI.setLateRenewUnit("");
	        lm.editVehicleProduct(vehicleRTI);
			this.verifyErrorMessage("A Late Renewal Unit ('Days' or 'Months') is required. Please specify the Late Renewal Unit.");
		}catch(Exception e){
			list.add(e);
		}
		
		if(list.size()>0){
			for(Exception e:list){
				e.printStackTrace();
			}
			throw new TestCaseFailedException("Total verifications:"+count+",failed:"+list.size());
		}
		
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//initialize vehicle information
		initializeVehicleInfo();
	}
	
	/**
	 * initialize vehicle information
	 * @Return void
	 */
	private void initializeVehicleInfo(){
		//vehicle information	    
		vehicleRTI.setPrdCode("006");
		vehicleRTI.setPrdName("QA Auto Test");
		vehicleRTI.setPrdGroup("Registration");
		vehicleRTI.setVehicleType("Dealer");
		custClass.clear();
		custClass.put("Individual", true);
		vehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		vehicleRTI.setCustClass(custClass);
		vehicleRTI.setValidMonths("1");
		vehicleRTI.setAdvanceRenewalDays("1");
		vehicleRTI.setLateRenewal("1");
		vehicleRTI.setLateRenewUnit("Day");
		vehicleRTI.setMonth("Feb");
		vehicleRTI.setDay("1");
		vehicleRTI.setValidYears("1");
		vehicleRTI.setCycleStartYear("2001");
	}

	
	/***
	 * 
	 * @param expectMsg
	 * @Return void
	 * @Throws
	 */
	public void verifyErrorMessage(String expectMsg){
		LicMgrEditVehicleDetailsPage editPage=LicMgrEditVehicleDetailsPage.getInstance();
		String msg="";
		ajax.waitLoading();
		msg=editPage.getWarningMessage();
		if(msg.trim().equals(expectMsg)){
			logger.info("verify successfully");
			return;
		}
		logger.error("Expect message:'"+expectMsg+"' is not displayed, the message on page is '" + msg + "'.");
		throw new ErrorOnPageException("Expect message:'"+expectMsg+"' is not displayed, the message on page is '" + msg + "'.");
	}
	
	/**
	 * verify day & month error message. validToDate="Fixed Month & Day plus Valid Years"
	 * Work flow start from CreateNewVehicleProductPage/VehicleProductPage,
	 * End in CreateNewVehicleProductPage
	 * @param vehicle
	 * @param months
	 * @param days
	 * @Return void
	 */
	public void veryfyDay_Month(VehicleRTI vehicle,String[] months,String[] days){
		
		logger.info("verify vehicle product vaild month & days.....");
		vehicle.setValidToDate("Fixed Month & Day plus Valid Years");
		for(String month:months){
			vehicle.setMonth(month);
			for(String day:days){
				vehicle.setDay(day);
				lm.editVehicleProduct(vehicle);
				this.verifyErrorMessage("Month "+vehicle.getMonth()+" and Day "+vehicle.getDay()+" is not a valid combination. Please correct the Month and Day entries.");					
			}
		}
	}

}
