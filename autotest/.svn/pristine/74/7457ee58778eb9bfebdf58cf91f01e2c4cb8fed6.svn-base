package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityDetail.InvMgrFacilityDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * User has at lease one 'Create/Modify...' or 'Modify...' permission
 * User has at lease one 'Create Change Request... permission
 */
/**  */
public class RequestModeDisplay extends InventoryManagerTestCase{
	AdminManager am = AdminManager.getInstance();
	LoginInfo loginam=new LoginInfo();
	List<RoleInfo> roleList = new ArrayList<RoleInfo>();
	List<String> roleStatus = new  ArrayList<String>();
	RoleInfo role = new RoleInfo();
	RoleInfo role1 = new RoleInfo();
	RoleInfo role2 = new RoleInfo();
	RoleInfo role3 = new RoleInfo();
	public void execute() {
		//login admin manager to assign or unAssign permissions
		am.loginAdminMgr(loginam);	
		am.AssignOrUnassignRoleFeatures(roleList, roleStatus);
		am.logoutAdminMgr();

		//Login inventory manager and verify Mode link, Mode bar and 'Add New' button in facility list and details page
		im.loginInventoryManager(login);
		this.verifyModeLinkBarAndAddNewAvailable(true, false);
		im.switchChangeEffectiveMode();
		this.verifyModeLinkBarAndAddNewAvailable(true, true);

		im.gotoFacilityDetailsPg(inventory.facilityName);
		this.verifyModeLinkBarAndAddNewAvailable(false, true);
		im.switchChangeEffectiveMode();
		this.verifyModeLinkBarAndAddNewAvailable(false, false);
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] param) {
		//login info for admin manager
		loginam.url = AwoUtil.getOrmsURL(env);
		loginam.userName = TestProperty.getProperty("orms.adm.user");
		loginam.password = TestProperty.getProperty("orms.adm.pw");
		loginam.contract = "ID Contract";
		loginam.location = "Administrator/Idaho Contract";

		role.roleName = "Administrator";
		role.feature = "CreateFacility";
		role.application = "InventoryManager";
		roleList.add(role);
		roleStatus.add("Yes");

		role1.roleName = "Administrator";
		role1.feature = "CreateFacilityChangeRequest";
		role1.application = "InventoryManager";
		roleList.add(role1);
		roleStatus.add("Yes");
		
		role2.roleName = "Administrator";
		role2.feature = "EditFacilityAsset";
		role2.application = "InventoryManager";
		roleList.add(role2);
		roleStatus.add("Yes");
		
		role3.roleName = "Administrator";
		role3.feature = "AddAssetToFacility";
		role3.application = "InventoryManager";
		roleList.add(role3);
		roleStatus.add("Yes");

		//login info for inventory manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "ID Contract";
		login.location = "Administrator/Idaho Contract";

		inventory.facilityName = "Bear Lake State Park";
	}

	public void verifyModeLinkBarAndAddNewAvailable(boolean isInvHomePg, boolean isChangeReqMod){
		InvMgrHomePage invHmPg = InvMgrHomePage.getInstance();
		InvMgrFacilityDetailPage inFacilityDetailPg = InvMgrFacilityDetailPage.getInstance();

		if(isInvHomePg){
			if(!isChangeReqMod){
				if(!invHmPg.checkChangeRequestModeLink()){
					throw new ErrorOnDataException("Change request Mode Link should display.");
				}
				if(invHmPg.checkModeBar()){
					throw new ErrorOnDataException("Mode Bar should doesn't display.");
				}
				if(!invHmPg.checkAddNew()){
					throw new ErrorOnDataException("'Add New' button should be available.");
				}
			}else {
				if(!invHmPg.checkChangeImmediateModeLink()){
					throw new ErrorOnDataException("Change Immediate Mode Link should display.");
				}
				if(invHmPg.checkModeBar()){
					throw new ErrorOnDataException("Mode Bar should doesn't display.");
				}
				if(invHmPg.checkAddNew()){
					throw new ErrorOnDataException("'Add New' button should not be available.");
				}
			}
		}else {
			if(!isChangeReqMod){
				if(!inFacilityDetailPg.checkChangeRequestModeLink()){
					throw new ErrorOnDataException("Change Request Mode Link should display.");
				}
				if(inFacilityDetailPg.checkModeBar()){
					throw new ErrorOnDataException("Mode Bar should doesn't display.");
				}	
			}else {
				if(!inFacilityDetailPg.checkChangeImmediateModeLink()){
					throw new ErrorOnDataException("Change Immediate Mode Link should  display.");
				}
				if(!inFacilityDetailPg.checkModeBar()){
					throw new ErrorOnDataException("Mode Bar should display.");
				}
			}
		}
	}
}
