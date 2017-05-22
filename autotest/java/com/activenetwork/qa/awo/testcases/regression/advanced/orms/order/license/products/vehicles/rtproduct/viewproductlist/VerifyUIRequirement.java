/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.rtproduct.viewproductlist;

import java.util.HashMap;
import java.util.Map;

import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @ScriptName ProductList_UIRequirement.java
 * @Date:Apr 29, 2011
 * @Description:
 * @author asun
 */
public class VerifyUIRequirement extends LicenseManagerTestCase {

	private Map<String,Boolean> status=null;
	private Map<String,Boolean> groups=null;
	private Map<String,Boolean> types=null;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu("Vehicles");
        this.selectStatus_Types_Groups(status, groups, types);
        this.verifySelectStatus_Types_Groups();
        lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
        
		status=new HashMap<String,Boolean>();
		status.put("Active", true);
		status.put("InActive", true);
		
		groups=new HashMap<String,Boolean>();
		groups.put("Registration", true);
		groups.put("Title", true);
		groups.put("Inspection", true);
		
		types=new HashMap<String,Boolean>();
		types.put("Boat", true);
		types.put("Motor", true);
		types.put("Dealer", true);
	}
	
	
	public void selectStatus_Types_Groups(Map<String,Boolean> status,Map<String,Boolean> groups,Map<String,Boolean> types){
		LicMgrVehiclesListPage page=LicMgrVehiclesListPage.getInstance();
		logger.info("select status,groups,types.....");
		page.selectVehicleStatus(status);
		page.selectVehicleGroup(groups);
		page.selectVehicleType(types);
	}
	
	public void verifySelectStatus_Types_Groups(){
		LicMgrVehiclesListPage page=LicMgrVehiclesListPage.getInstance();
	   
		logger.info("Verify Status,Groups,Types selected");
		String msg="Verify:";
		if(page.isStatusSelected_Active()!=status.get("Active")){
			msg+="Active,";
	    }
		
		if(page.isStatusSelected_InActive()!=status.get("InActive")){
			msg+="InActive,";
		}
		
		if(page.isGroupSelected_Inspection()!=groups.get("Registration")){
			msg+="Registration,";
		}
		
		if(page.isGroupSelected_Inspection()!=groups.get("Inspection")){
			msg+="Inspection,";
		}
		
        if(page.isGroupSelected_Title()!=groups.get("Title")){
			msg+="Title,";
		}
        
        if(page.isVehicleTypSelected_Boat()!=types.get("Boat")){
        	msg+="Boat,";
        }
        
        if(page.isVehicleTypSelected_Motor()!=types.get("Motor")){
        	msg+="Motor,";
        }
        
        if(page.isVehicleTypSelected_Dealer()!=types.get("Dealer")){
        	msg+="Dealer";
        }
        
        if(!msg.equals("Verify:")){
        	throw new ErrorOnPageException(msg+" Failed !");
        }
        logger.info("verify successfully..");
	}
	

}
