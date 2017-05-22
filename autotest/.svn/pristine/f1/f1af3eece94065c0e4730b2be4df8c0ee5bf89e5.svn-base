package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductViewChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrEditVehicleDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: This case is used to verify vehicle change history, change vehicle name.
 * @Preconditions:
 * @SPEC: View Vehicle RT Product Info Change History.doc
 * @Task#: Auto-760

 * @author VZhang1
 * @Date Dec 5, 2011
 */

public class ViewVehicleProductChangeHistory extends LicenseManagerTestCase{

	private Random r = new Random();
	private ChangeHistory history=new ChangeHistory();
	private LicMgrEditVehicleDetailsPage vehicleDetailPg = LicMgrEditVehicleDetailsPage.getInstance();
	private LicMgrProductViewChangeHistoryPage historyPage = LicMgrProductViewChangeHistoryPage.getInstance();
	private VehicleRTI vehicleRTI = new VehicleRTI();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVehicleSearchListPageFromTopMenu();
		//add vehicle product
		lm.addVehicleProduct(vehicleRTI);
		
		lm.gotoVehicleProductDetailsPageFromListPage(vehicleRTI.getPrdCode());
		vehicleRTI.setPrdName("History" + DateFunctions.getCurrentTime());
		//edit vehicle info, edit vehicle name
		lm.editVehicleProduct(vehicleRTI);
		
		lm.gotoVehicleProductDetailsPageFromListPage(vehicleRTI.getPrdCode());
		vehicleRTI.setPrdId(vehicleDetailPg.getProductID());
		//go to vehicle change history page
		lm.gotoVehicleChangeHistoryPageFromDetailPage();
		history.newValue = vehicleRTI.getPrdName();
		//verify change history record info
		this.verifyHistoryRecordInfo(history);
		//verify vehicle product info
		this.verifyProductInfo(vehicleRTI);
		
		//clean up
		lm.gotoVehicleDetailPageFromChangeHistoryPage();
		lm.changeVehicleProductStatus("Inactive");	
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vehicleRTI.setPrdCode("E" + r.nextInt(99));
		vehicleRTI.setPrdName("Auto" + DateFunctions.getCurrentTime());
		vehicleRTI.setStatus("Active");
		vehicleRTI.setPrdGroup("Registration");
		vehicleRTI.setVehicleType("Boat");
		HashMap<String, Boolean> custClass = new HashMap<String, Boolean>();
		custClass.put("Individual", true);
		custClass.put("Business", false);
		vehicleRTI.setCustClass(custClass);
		vehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		vehicleRTI.setValidMonths("2");
		vehicleRTI.setAdvanceRenewalDays("1");
		vehicleRTI.setLateRenewal("1");
	    vehicleRTI.setLateRenewUnit("Day");
	    HashMap<String, Boolean> boatUseTyp = new HashMap<String, Boolean>();
	    boatUseTyp.put("Personal Pleasure", true);
	    boatUseTyp.put("Rental Lease", false);
	    boatUseTyp.put("Commercial Fishing", true);
	    boatUseTyp.put("Commercial Pleasure", false);
	    boatUseTyp.put("Agency", false);
	    boatUseTyp.put("Other", false);
	    vehicleRTI.setBoatUseTyp(boatUseTyp);
	    vehicleRTI.setMinLenthOfFt("7");
	    vehicleRTI.setMinLenthOfIn("5");
	    vehicleRTI.setMaxLenthOfFt("20");	  
	    vehicleRTI.setMaxLenthOfIn("14");	
	    
	    history.object = "Vehicle RT Product";
	    history.action = "Update";
	    history.field = "Name";
	    history.oldValue = vehicleRTI.getPrdName();
	    history.user = login.userName;
	    history.location = login.location.split("/")[1].trim();
	    history.changeDate = DateFunctions.getToday("E MMM d yyyy");
	}
	
	private void verifyHistoryRecordInfo(ChangeHistory expectHistory){
		List<ChangeHistory> historyList= new ArrayList<ChangeHistory>();
		ChangeHistory historyOnPage = new ChangeHistory();
		
		logger.info("Verify vehicle change history info.");
		
		historyList = historyPage.getChangeHistoryInfo();
		historyOnPage = historyList.get(0);
		
		if(!historyOnPage.equals(expectHistory)){
			throw new ErrorOnPageException("History record is wrong.");
		}				
	}
	
	private void verifyProductInfo(VehicleRTI expectVehicle){
		logger.info("Verify product info.");
		
		String actualValue = "";
		//Object ID should equal vehicle product ID
		actualValue = historyPage.getProductID();
		if(!actualValue.equalsIgnoreCase(expectVehicle.getPrdId())){
			throw new ErrorOnPageException("Object ID is not correct, Object should equal to the vehicle product ID '"
					+ expectVehicle.getPrdId() + "', but acutally is " + actualValue);
		}
		
		//verify vehicle code
		actualValue = historyPage.getProductCode();
		if(!actualValue.equalsIgnoreCase(expectVehicle.getPrdCode())){
			throw new ErrorOnPageException("Product code should is " + expectVehicle.getPrdCode() 
					+ ", but acutally is " + actualValue);
		}
		
		//verify vehicle name
		actualValue = historyPage.getProductName();
		if(!actualValue.equalsIgnoreCase(expectVehicle.getPrdName())){
			throw new ErrorOnPageException("Product name should is " + expectVehicle.getPrdName() 
					+ ", but acutally is " + actualValue);
		}
		
		//verify vehicle status
		actualValue = historyPage.getProductStatus();
		if(!actualValue.equalsIgnoreCase(expectVehicle.getStatus())){
			throw new ErrorOnPageException("Product status should is " + expectVehicle.getStatus() 
					+ ", but acutally is " + actualValue);
		}
	}

}
