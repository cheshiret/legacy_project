package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.closure;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrInventoryAuditLogsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: It is to verify the action when active a closure, the check point including:
 *                1.The status has been update to active for closure;
 *                2.The search critical does not change after active the closure;
 *                3.System creates an audit log about Change to active closure on Admin manager.
 * @LinkSetUp:  None
 * @SPEC:[TC:045536] Activate closure schedule  
 * @Task#: Auto-1898
 * @author Phoebe Chen
 * @Date  November 11, 2013
 */
public class ActiveSlipClosure extends InventoryManagerTestCase {
	private String facilityID;
	private TimeZone tz;
	InvMgrClosuresPage inClosurePg = InvMgrClosuresPage.getInstance();
	private LoginInfo loginAdm = new LoginInfo();
	private AuditLogInfo  inventoryLogsClosureDeactive = new AuditLogInfo();
	private AdminManager adm = AdminManager.getInstance();
	private AdminMgrInventoryAuditLogsPage invAuditLogPage = AdminMgrInventoryAuditLogsPage.getInstance();
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoClosurePage();
		
		closure.closureID = im.createClosure(closure);
		inventoryLogsClosureDeactive.actionDetails = "Closure ID: " + closure.closureID + ", Closure Type: " + closure.type;
		inventoryLogsClosureDeactive.searchValue = inventoryLogsClosureDeactive.actionDetails.substring(0, 20);
		inClosurePg.searchByClosureInfo(closure);
		im.activeOrDeactiveClosure("Activate", closure.closureID);
		this.verifyClosureIsActived();
		this.verifyPreviousSearchCriterial();
		
		im.logoutInvManager();
		
		//goto Admin Manager to verify if the Audit Log correct
		adm.loginAdminManager(loginAdm, true);
		adm.gotoInventoryAuditLogsPage();
		//Verify audit for closure deactive
		invAuditLogPage.searchLogs(inventoryLogsClosureDeactive);
		invAuditLogPage.verifyInventoryAuditLogsDetailInfo(inventoryLogsClosureDeactive, true);
		adm.logoutAdminMgr();
	}
	
	private void verifyClosureIsActived() {
		if(!inClosurePg.isClosureActive(closure.closureID)){
			throw new ErrorOnPageException("The clousre:" + closure.closureID + " should be active, but it is inactive");
		}
	}

	private void verifyPreviousSearchCriterial() {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Start Date:", closure.startDate, inClosurePg.getSCStartDate());
		passed &= MiscFunctions.compareResult("Closure Type:", closure.type, inClosurePg.getSCClosureType());
		passed &= MiscFunctions.compareResult("Product Category:", closure.productCategory, inClosurePg.getSCPrdCategoryValues());
		if(!passed){
			throw new ErrorOnPageException("Previouse search criterial has been changed on page, please check log for details.");
		}
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
	    login.contract = "NC Contract";
	    login.location="Administrator/North Carolina State Parks";
	    schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NC";
	    tz = DataBaseFunctions.getContractTimeZone(schema);
	    
	    facilityID = "552834";
	    String facility = im.getFacilityName(facilityID, schema); 
	    closure.productCategory = "Slip";
	    closure.type="Maintenance";
	    closure.startDate=DateFunctions.getDateAfterToday(-2);
	    closure.endDate=DateFunctions.getDateAfterToday(5, tz);
	    closure.status=OrmsConstants.INACTIVE_STATUS;
	    closure.occurencePattern = "Monthly";
	    closure.dayOfMonth = "2";
	    closure.comment="Regression Test";
	    closure.recurring = OrmsConstants.YES_STATUS;
	    
	    //Admin manager login info
		loginAdm.url = AwoUtil.getOrmsURL(env);
		loginAdm.userName = TestProperty.getProperty("orms.adm.user");
		loginAdm.password = TestProperty.getProperty("orms.adm.pw");
		loginAdm.contract = "NC Contract";
		loginAdm.location = "Administrator/North Carolina State Parks";
		
		inventoryLogsClosureDeactive.searchType = "Action Details";
		inventoryLogsClosureDeactive.stratDate = DateFunctions.getToday(tz);
		inventoryLogsClosureDeactive.endDate = inventoryLogsClosureDeactive.stratDate;
		inventoryLogsClosureDeactive.actionType = "Activate";
		inventoryLogsClosureDeactive.dateTime = inventoryLogsClosureDeactive.stratDate;
		inventoryLogsClosureDeactive.logArea = "Closure";
		inventoryLogsClosureDeactive.action = "Activate Closure";
		inventoryLogsClosureDeactive.affectedLocation = facility;
		inventoryLogsClosureDeactive.userName = login.userName;
		inventoryLogsClosureDeactive.application = "Inventory";
	}

}

