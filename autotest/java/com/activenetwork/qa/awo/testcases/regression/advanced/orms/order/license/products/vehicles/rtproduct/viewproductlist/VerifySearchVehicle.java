/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.rtproduct.viewproductlist;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @ScriptName ViewProductList_WarningMessages.java
 * @Date:Apr 2, 2011
 * @Description:
 * @author asun
 */
public class VerifySearchVehicle extends LicenseManagerTestCase {
    
	private Map<String,Boolean> status=null;
	private Map<String,Boolean> groups=null;
	private Map<String,Boolean> types=null;
	private String msg=null;
	private VehicleRTI vR1=new VehicleRTI();
	private VehicleRTI vR2=new VehicleRTI();
	private VehicleRTI vR3=new VehicleRTI();
	private VehicleRTI vT1=new VehicleRTI();
	private VehicleRTI vI1=new VehicleRTI();
	private AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
	private String codes="";
	
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu("Vehicles");
		
		//verify warning message when no vehicle displayed by specified criteria	
		try{
			lm.searchVehicleProduct(status, groups, types);
			codes=this.activateAllIactiveVehicles(schema);
			lm.searchVehicleProductWithNoResults(status, groups, types);
			this.verifyErrorMessage(msg);
		}finally{
			this.inActivateVehicles(codes);
		}
		//verify search result 
		status.put("Active", true);
		status.put("InActive", true);
		groups.put("Registration", true);
		groups.put("Title", true);
		groups.put("Inspection", true);
		types.put("Boat", true);
		types.put("Motor", true);
		types.put("Dealer", true);
		lm.searchVehicleProduct(status, groups, types);
		this.verifyGroup(vR1,vT1,vI1);
		this.verifyVehicleSortASC_DifferentType_SameGroup(vR2,vR3);
		this.verifyVehicleSortASC_SameType(vR1, vR2);
		this.verifyTypeCodeNameStatusDisplayed();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location =  "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema=TestProperty.getProperty(env+".db.schema.prefix")+"MS";
		
		status=new HashMap<String,Boolean>();
		status.put("Active", false);
		status.put("InActive", true);
		
		groups=new HashMap<String,Boolean>();
		groups.put("Registration", false);
		groups.put("Title", false);
		groups.put("Inspection", false);
		
		types=new HashMap<String,Boolean>();
		types.put("Boat", false);
		types.put("Motor", false);
		types.put("Dealer", false);
		
		msg="No Vehicle RT Product to display.";
		
		vR1.setPrdCode("R1");
		vR1.setVehicleType("Motor");
		vR1.setPrdGroup("Registration");
		
		vR2.setPrdCode("R2");
		vR2.setVehicleType("Motor");
		vR2.setPrdGroup("Registration");
		
		vR3.setPrdCode("C07");
		vR3.setVehicleType("Motor");
		vR3.setPrdGroup("Registration");
		
		vT1.setPrdCode("F05");
		vT1.setPrdGroup("Title");
		
		vI1.setPrdCode("I1");
		vI1.setPrdGroup("Inspection");
	}
	
	/**
	 * verify warning message on vehicle product list page
	 * @param expectMsg
	 * @Return void
	 */
	public void verifyErrorMessage(String expectMsg){
		LicMgrVehiclesListPage page=LicMgrVehiclesListPage.getInstance();
		logger.info("verify warning message on page...");
		String msgOnPage=page.getWarningMessage();
		if(!msgOnPage.trim().equals(expectMsg)){
			logger.error("the expected message:'"+expectMsg+"' is not found!");
			throw new ErrorOnPageException("the expected message:'"+expectMsg+"' is not found!");
		}
		logger.info("verify warning message successfully....");
	}
	
	
	public void verifyGroup(VehicleRTI... vehicles){
		LicMgrVehiclesListPage page=LicMgrVehiclesListPage.getInstance();
	    String[] groups=new String[vehicles.length];
		
	    logger.info("Verify vehicle group..........");
	    for(int i=0;i<groups.length;i++){
	    	groups[i]=page.getGroupFromPage(vehicles[i].getPrdCode());
	    	if(!groups[i].trim().equalsIgnoreCase(vehicles[i].getPrdGroup())){
	    		logger.error("the vehicle which code is "+
	    				vehicles[i].getPrdCode()+" should be group in "+vehicles[i].getPrdGroup());
	    		throw new ErrorOnPageException("the vehicle which code is "+
	    				vehicles[i].getPrdCode()+" should be group in "+vehicles[i].getPrdGroup());
	    	}
		}
	    logger.info("verify successfully.....");
	}
	/**
	 * when their group are same,the type of the 1st vehicle type 
	 * should be less than the 2nd vehicle.
	 * @param firstVehicle
	 * @param secondVehicle
	 * @Return void
	 */
	public void verifyVehicleSortASC_DifferentType_SameGroup(VehicleRTI firstVehicle,VehicleRTI secondVehicle){
		LicMgrVehiclesListPage page=LicMgrVehiclesListPage.getInstance();
		
		logger.info("verify vehicle type is sorted by ascending ");
		if(!firstVehicle.getPrdGroup().equalsIgnoreCase(secondVehicle.getPrdGroup())){
			throw new ErrorOnDataException("Both those two vehicles should have same group");
		}
		String[] codes=new String[]{firstVehicle.getPrdCode(),secondVehicle.getPrdCode()};
		Arrays.sort(codes);
		int firstVehicle_row=page.getVehicleProductRow(codes[0]);
		int secondVehicle_row=page.getVehicleProductRow(codes[1]);
		if(firstVehicle_row>secondVehicle_row){
			throw new ErrorOnPageException("verify sorting failed....");
		}
		logger.info("Verify successfully");
	}
	
	/**
	 * when their group are same,verify the code of the 1st vehicle type 
	 * should be less than the 2nd vehicle.
	 * @param firstVehicle
	 * @param secondVehicle
	 * @Return void
	 */
	public void verifyVehicleSortASC_SameType(VehicleRTI firstVehicle,VehicleRTI secondVehicle){
		LicMgrVehiclesListPage page=LicMgrVehiclesListPage.getInstance();
		
		logger.info("verify vehicle type is sorted by ascending ");
		if(!firstVehicle.getPrdGroup().equalsIgnoreCase(secondVehicle.getPrdGroup())){
			throw new ErrorOnDataException("Both those two vehicles should have same group");
		}
		
		if(!firstVehicle.getVehicleType().equalsIgnoreCase(secondVehicle.getVehicleType())){
			throw new ErrorOnDataException("Both those two vehicles should have same group");
		}
		String[] codes=new String[]{firstVehicle.getPrdCode(),secondVehicle.getPrdCode()};
		Arrays.sort(codes);
		int firstVehicle_row=page.getVehicleProductRow(codes[0]);
		int secondVehicle_row=page.getVehicleProductRow(codes[1]);
		if(firstVehicle_row>secondVehicle_row){
			throw new ErrorOnPageException("verify sorting failed....");
		}
		logger.info("Verify successfully");
	}
	/**
	 * verify vehicle code,type,status,type are displayed.
	 * @Return void
	 */
	public void verifyTypeCodeNameStatusDisplayed(){
		LicMgrVehiclesListPage page=LicMgrVehiclesListPage.getInstance();
	    List<VehicleRTI> list = page.getVehicles();
	    logger.info("verify vehicle code,type,status,type are displayed.");
	    for(VehicleRTI vehicle:list){
	    	if(vehicle.getPrdCode().length()>3||vehicle.getPrdName().length()<0||vehicle.getStatus().length()<0||vehicle.getVehicleType().length()<0){
	    		throw new ErrorOnPageException("verify vehicle whicch code="+vehicle.getPrdCode()+",neme="+vehicle.getPrdName()+" failed");
	    	}
	    }
	    logger.info("verify successfully.");
	}
	
	/**
	 * activate all inactive vehicles in LicMgrVehiclesListPage
	 * @param schema
	 * @Return String
	 */
	public String activateAllIactiveVehicles(String schema){
		LicMgrVehiclesListPage page=LicMgrVehiclesListPage.getInstance();
		List<VehicleRTI> list=page.getVehicles("Inactive");
		if(list.size()==0)return null;
		StringBuffer codes=new StringBuffer("'");
		for(int i=0;i<list.size();i++){
			codes.append(list.get(i).getPrdCode());
			if(i!=(list.size()-1)){
				codes.append("','");
			}else{
				codes.append("'");
			}
		}
		logger.info("Activate vehicles:"+codes);
		String sql="Update P_PRD set ACTIVE_IND=1 where PRD_CD in ("+codes.toString()+")";
		db.resetSchema(schema);
		db.executeUpdate(sql);
		return codes.toString();
	}
	/**
	 * Inactivate vehicles product by code like "'1','1','1'"
	 * @param codes
	 * @Return void
	 */
	public void inActivateVehicles(String codes){
		String[] tmp=null;//added by pzhu
		if(codes==null||codes.trim().length()<1){
			return;
		}
		
		tmp = codes.split(",");

		for (String s : tmp) {
			if (!s.matches("(,?'[0-9a-zA-Z]{1,3})+'")) {
				throw new ErrorOnDataException("Data Format Error !");
			}
		}
		String sql="Update P_PRD set ACTIVE_IND=0 where PRD_CD in ("+codes+")";
		db.resetSchema(schema);
		db.executeUpdate(sql);
	}
}
