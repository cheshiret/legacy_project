/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.rtproduct.add;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrCreateNewVehiclePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @ScriptName AddVehicleRTProduct.java
 * @Date:Mar 23, 2011
 * @Description:
 * @author asun
 */
public class AddVehicleRTProduct extends LicenseManagerTestCase {

	private int count=0;
	private List<Exception> list=null;
	private String blankCodeMsg="The Product Code is required. Please specify the Code.";
	private String codeMoreThanThreeAlph="The Product Code entered is not valid. Please enter a maximum of 3 alphanumeric characters.";
	private String codeUnique="The Product Code entered already exists. The Code must be unique.";
	private String nameBlank="The Product Name is required. Please specify the Name.";
	private String typeBlank="The Vehicle Type is required. Please specify the Vehicle Type.";
	private String noCustClass="At least one Customer Class must be selected. Please select the Customer Class from the list provided.";
	private String blankValidMonths="Valid Months is required. Please specify the Valid Months.";
	private String nonIntegerMonths="Valid Months must be a number 1 or greater. Please re-enter the Valid Months.";
	private VehicleRTI vehicle = new VehicleRTI();
	HashMap<String,Boolean> custClass = new HashMap<String, Boolean>();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu("Vehicles");
		
		//verify vehicle code
		try{
			//verify code is blank
			count++;
			vehicle.setPrdCode("");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage(blankCodeMsg);
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//verify code is more than 3 alphanumeric characters
			count++;
			vehicle.setPrdCode("1234");
			lm.addVehicleProduct(vehicle);
			this.verifyCodeInvalidMessage();
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//The Vehicle RT Product Code already exists, using case-insensitive matching.
			count++;
			vehicle.setPrdCode(this.getAExistVehicleCodeFromVehicleListPage());
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage(codeUnique);
		}catch(Exception e){
			list.add(e);
		}
		
		//verify vehicle name
		try{
			//The Vehicle RT Product Name is not specified, i.e. left null/blank. 
			count++;
			vehicle.setPrdCode(String.valueOf(DataBaseFunctions.getEmailSequence()).substring(0, 3));
			vehicle.setPrdName("");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage(nameBlank);
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setPrdName("QA Auto Test");
		}
		
		//verify vehicle status
		try{
			//The Vehicle Type is not specified, i.e. left null/blank.
			count++;
			vehicle.setVehicleType(" ");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage(typeBlank);
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setVehicleType("Motor");
		}
		
		//Customer Class
		try{
			//At least one Customer Class has not been selected.
			count++;
			vehicle.setCustClass(null);
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage(noCustClass);
		}catch(Exception e){
			list.add(e);
		}finally{
			custClass.put("Individual", true);
			vehicle.setCustClass(custClass);
		}
		
		//valid Months
		try{
			//The Product Group is Registration and the Valid To 
			//Date is not specified, i.e. left null/blank.
			count++;
			vehicle.setPrdGroup("Registration");
			vehicle.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
			vehicle.setValidMonths("");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage(blankValidMonths);
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Valid Months has been specified, and is not an integer value.
			count++;
			vehicle.setPrdGroup("Registration");
			vehicle.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
			vehicle.setValidMonths("1.5");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage(nonIntegerMonths);
		}catch(Exception e){
			list.add(e);
		}
		
		try{
			//Valid Months has been specified, and is not less than 1.
			count++;
			vehicle.setPrdGroup("Registration");
			vehicle.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
			vehicle.setValidMonths("0");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage(nonIntegerMonths);
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setValidMonths("1");
		}
		
		try{
			//Valid to Date \ufffdFixed Month & Day plus Valid Years\ufffd has been specified, 
			//and Month has not been specified.
			count++;
			vehicle.setPrdGroup("Registration");
			vehicle.setValidToDate("Fixed Month & Day plus Valid Years");
			vehicle.setMonth("");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("Month is required. Please specify the Month.");
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setMonth("Jan");
		}
		
		try{
			//Valid to Date \ufffdFixed Month & Day plus Valid Years\ufffd has been specified,
			//and Day has not been specified.
			count++;
			vehicle.setValidToDate("Fixed Month & Day plus Valid Years");
			vehicle.setDay("");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("Day is required. Please specify the Day.");
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setDay("1");
		}
		
		try{
			//Month \ufffdJan\ufffd, or \ufffdMar\ufffd, or \ufffdMay\ufffd, or \ufffdJul\ufffd, or \ufffdc\ufffd, or \ufffdOct\ufffd, or \ufffdDec\ufffd has been specified, and the corresponding 
			//Day is not an integer from 1 to 31 inclusive.
			count++;
			String[] months=new String[]{"Jan","Mar","May","Jul","Jul","Oct","Dec"};	
			String[] days=new String[]{"0","32"};
			this.verifyDay_Month(vehicle, months, days);
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setDay("1");
		}
		
		try{
			//Month \ufffdApr\ufffd, or \ufffdJun\ufffd, or \ufffdSep\ufffd, or \ufffdNov\ufffd has been specified, and the corresponding 
			//Day is not an integer value from 1 to 30 inclusive.
			count++;
			String[] months=new String[]{"Apr","Jun","Sep","Nov"};	
			String[] days=new String[]{"0","31"};
			this.verifyDay_Month(vehicle, months, days);
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setDay("1");
		}
		
		try{
			//Month \ufffdFeb\ufffd has been specified, and the corresponding 
			//Day is not an integer value from 1 to 28 inclusive.
			count++;
			String[] months=new String[]{"Feb"};	
			String[] days=new String[]{"0","29"};
			this.verifyDay_Month(vehicle, months, days);
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setDay("1");
		}
		
		try{
			//Valid to Date \ufffdFixed Month & Day plus Valid Years\ufffd has been specified, 
			//and Valid Years has not been specified.
			count++;
			vehicle.setValidToDate("Fixed Month & Day plus Valid Years");
		    vehicle.setValidYears("");
		    lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("Valid Years is required. Please specify the Valid Years.");
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setValidYears("1");
		}
		
		try{
			//Valid Years has been specified, and is not an integer 
			//value 1 or greater.
			count++;
			vehicle.setValidToDate("Fixed Month & Day plus Valid Years");
		    vehicle.setValidYears("1.5");
		    lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("Valid Years must be a number 1 or greater. Please re-enter the Valid Years.");
			vehicle.setValidYears("0");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("Valid Years must be a number 1 or greater. Please re-enter the Valid Years.");
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setValidYears("1");
		}
		
		try{
			//Valid to Date \ufffdFixed Month & Day plus Valid Years\ufffd has been specified, 
			//and Cycle Start Year has not been specified.
			count++;
			vehicle.setValidToDate("Fixed Month & Day plus Valid Years");
		    vehicle.setCycleStartYear("");
		    lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("Cycle Start Year is required. Please specify the Cycle Start Year.");
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setValidYears("1");
		}
		
		try{
			//Cycle Start Year has been specified, and not is a valid four digit year, 
			//or is greater than the Current Year (in Contract Time).
			count++;
			vehicle.setValidToDate("Fixed Month & Day plus Valid Years");
		    vehicle.setCycleStartYear("333");
		    lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("Cycle Start Year must be a valid four-digit year and cannot be greater than the current year. Please re-enter the Cycle Start Year.");
			//greater than the Current Year (in Contract Time).
			String thisYear=DateFunctions.getToday().split("/")[2].trim();
			vehicle.setCycleStartYear((Integer.parseInt(thisYear)+1)+"");
			lm.addVehicleProduct(vehicle); 
			this.verifyErrorMessage("Cycle Start Year must be a valid four-digit year and cannot be greater than the current year. Please re-enter the Cycle Start Year.");
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setCycleStartYear("2001");
		}
		
		try{
			//The Product Group is Registration and Advance Renewal Days has not been specified.
			count++;
			vehicle.setPrdGroup("Registration");
			vehicle.setVehicleType("Motor");
			vehicle.setAdvanceRenewalDays("");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("Advance Renewal Days is required. Please specify the Advance Renewal Days.");
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setAdvanceRenewalDays("1");
		}
		
		try{
			//Advance Renewal Days has been specified, and is not an integer 1 or greater.
			count++;
			vehicle.setPrdGroup("Registration");
			vehicle.setVehicleType("Motor");
			vehicle.setAdvanceRenewalDays("1.5");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("Advance Renewal Days must be a number 1 or greater. Please re-enter the Advance Renewal Days.");
			vehicle.setAdvanceRenewalDays("0");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("Advance Renewal Days must be a number 1 or greater. Please re-enter the Advance Renewal Days.");
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setAdvanceRenewalDays("1");
		}
		
		try{
			//Valid to Date \ufffdPurchase Date/Previous Valid To Date plus Valid Months\ufffd has been specified, 
			//and Late Renewal has not been specified.
			count++;
			vehicle.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
	        vehicle.setLateRenewal("");
	        lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("Late Renewal has not been specified. Please specify the Late Renewal.");
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setLateRenewal("1");
		}
		
		try{
			//Late Renewal has been specified, and is not an integer value 0 or greater.
			count++;
			vehicle.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
	        vehicle.setLateRenewal("-1");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("Late Renewal must be a number 0 or greater. Please re-enter the Late Renewal.");
			vehicle.setLateRenewal("1.5");
			lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("Late Renewal must be a number 0 or greater. Please re-enter the Late Renewal.");
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setLateRenewal("1");
		}
		
		try{
			//Late Renewal has been specified, a Late Renewal Unit (\ufffdDays\ufffd or \ufffdMonths\ufffd) has not been specified.
			count++;
			vehicle.setPrdGroup("Registration");
			vehicle.setVehicleType("Motor");
			vehicle.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
	        vehicle.setLateRenewUnit("");
	        lm.addVehicleProduct(vehicle);
			this.verifyErrorMessage("A Late Renewal Unit ('Days' or 'Months') is required. Please specify the Late Renewal Unit.");
		}catch(Exception e){
			list.add(e);
		}finally{
			vehicle.setAdvanceRenewalDays("1");
		}
		
		
		if(list.size()>0){
			for(Exception e:list){
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			throw new TestCaseFailedException("Total Verifications:"+count+",Failed:"+list.size());
		}
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		list=new ArrayList<Exception>();
		
		vehicle.setPrdCode(String.valueOf(DataBaseFunctions.getEmailSequence()).substring(0, 3));
	    vehicle.setPrdName("QA Auto Test");
	    vehicle.setPrdGroup("Registration");
	    vehicle.setVehicleType("Motor");
	    custClass.put("Individual", true);
	    vehicle.setCustClass(custClass);
	    vehicle.setValidMonths("1");
	    vehicle.setAdvanceRenewalDays("1");
	    vehicle.setLateRenewal("1");
	    vehicle.setLateRenewUnit("Day");
	    vehicle.setMonth("Feb");
	    vehicle.setDay("1");
	    vehicle.setValidYears("1");
	    vehicle.setCycleStartYear("2001");
	}
	
	public void verifyCodeInvalidMessage(){
		LicMgrCreateNewVehiclePage vehicleInfoPage=LicMgrCreateNewVehiclePage.getInstance();	
		if(vehicleInfoPage.getVehicleCode().length()>3)
		  this.verifyErrorMessage(codeMoreThanThreeAlph);
	}
	
	/***
	 * Verify error message on CreateVehicleDetailsPage
	 * @param expectmessage
	 * @Return void
	 * @Throws
	 */
	public void verifyErrorMessage(String expectmessage){

		LicMgrCreateNewVehiclePage vehicleInfoPage=LicMgrCreateNewVehiclePage.getInstance();	    
	    String msgOnPage="";
		
	    logger.info("verify error message....");
	    ajax.waitLoading();
	    msgOnPage=vehicleInfoPage.getWarningMessage();
	    
	    if(!msgOnPage.equals(expectmessage)){
	    	throw new ErrorOnDataException("The error message is wrong.",expectmessage,msgOnPage);
	    }
	    logger.info("verify successfully.....");
	}
	
	/***
	 * get the first vehicle code from LicMgrVehiclesListPage
	 * @return
	 * @Return String
	 * @Throws
	 */
	public String getAExistVehicleCodeFromVehicleListPage(){
		LicMgrVehiclesListPage listPage=LicMgrVehiclesListPage.getInstance();
		LicMgrCreateNewVehiclePage vehicleInfoPage = LicMgrCreateNewVehiclePage
		.getInstance();
	    
		logger.info("get the first vehicle code....");
		if(!listPage.exists()){
			vehicleInfoPage.clickVehiclesTab();
			ajax.waitLoading();
			listPage.waitLoading();
		}
		String code=listPage.getTheFirstVehicle().getPrdCode();
	    return code;
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
	public void verifyDay_Month(VehicleRTI vehicle,String[] months,String[] days){
		
		logger.info("verify vehicle product vaild month & days.....");
		vehicle.setValidToDate("Fixed Month & Day plus Valid Years");
		for(String month:months){
			vehicle.setMonth(month);
			for(String day:days){
				vehicle.setDay(day);
				lm.addVehicleProduct(vehicle);
				this.verifyErrorMessage("Month "+vehicle.getMonth()+" and Day "+vehicle.getDay()+" is not a valid combination. Please correct the Month and Day entries.");					
			}
		}
	}
}
