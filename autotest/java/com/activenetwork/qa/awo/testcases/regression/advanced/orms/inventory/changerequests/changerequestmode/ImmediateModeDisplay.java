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
 * 1. User has no 'Create/Modify...' or 'Modify...' permission
 * 2. User has at least one 'Create Change Request...' permission
 */
public class ImmediateModeDisplay extends InventoryManagerTestCase{
	AdminManager am = AdminManager.getInstance();
	LoginInfo loginam=new LoginInfo();
	List<RoleInfo> roleList = new ArrayList<RoleInfo>();
	List<String> roleStatus = new  ArrayList<String>();
	RoleInfo role_1 = new RoleInfo();
	RoleInfo role_2 = new RoleInfo();
	RoleInfo role_3 = new RoleInfo();
	RoleInfo role_4 = new RoleInfo();
	RoleInfo role_5 = new RoleInfo();

	public void execute() {
		//login admin manager to assign or unAssign permissions
		am.loginAdminMgr(loginam);	
		am.AssignOrUnassignRoleFeatures(roleList, roleStatus);
		am.logoutAdminMgr();

		//Login inventory manager and verify Mode link, Mode bar and 'Add New' button in facility list and details page
		im.loginInventoryManager(login);
		this.verifyModeLinkBarAndAddNewAvailable(true);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		this.verifyModeLinkBarAndAddNewAvailable(false);
		im.logoutInvManager();

		//login admin manager to assign or unAssign permissions
		roleStatus.clear();
		roleStatus.add("Yes");
		roleStatus.add("Yes");
		roleStatus.add("Yes");
		roleStatus.add("Yes");
		roleStatus.add("Yes");
		am.loginAdminMgr(loginam);	
		am.AssignOrUnassignRoleFeatures(roleList, roleStatus);
		am.logoutAdminMgr();
	}

	public void wrapParameters(Object[] param) {
		//login info for admin manager
		loginam.url = AwoUtil.getOrmsURL(env);
		loginam.userName = TestProperty.getProperty("orms.adm.user");
		loginam.password = TestProperty.getProperty("orms.adm.pw");
		loginam.contract = "ID Contract";
		loginam.location = "Administrator/Idaho Contract";

		role_1.roleName = "Administrator - Auto";
		role_1.feature = "Create%";
		role_1.application = "InventoryManager";
		roleList.add(role_1);
		roleStatus.add("No");

		role_2.roleName = "Administrator - Auto";
		role_2.feature = "Modify%";
		role_2.application = "InventoryManager";
		roleList.add(role_2);
		roleStatus.add("No");

		role_3.roleName = "Administrator - Auto";
		role_3.feature = "CreateFacilityChangeRequest";
		role_3.application = "InventoryManager";
		roleList.add(role_3);
		roleStatus.add("Yes");
		
		role_4.roleName = "Administrator - Auto";
		role_4.feature = "EditFacilityAsset";
		role_4.application = "InventoryManager";
		roleList.add(role_4);
		roleStatus.add("No");
		
		role_5.roleName = "Administrator - Auto";
		role_5.feature = "AddAssetToFacility";
		role_5.application = "InventoryManager";
		roleList.add(role_5);
		roleStatus.add("No");

		//login info for inventory manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "ID Contract";
		login.location = "Administrator/Idaho Contract";

		inventory.facilityName = "Bear Lake State Park";
	}

	public void verifyModeLinkBarAndAddNewAvailable(boolean isInvHomePg){
		InvMgrHomePage invHmPg = InvMgrHomePage.getInstance();
		InvMgrFacilityDetailPage inFacilityDetailPg = InvMgrFacilityDetailPage.getInstance();

		if(isInvHomePg){
			if(invHmPg.checkChangeRequestModeLink() || invHmPg.checkChangeImmediateModeLink()){
				throw new ErrorOnDataException("Mode Link should doesn't display.");
			}
			if(invHmPg.checkModeBar()){
				throw new ErrorOnDataException("Mode Bar should doesn't display.");
			}
			if(invHmPg.checkAddNew()){
				throw new ErrorOnDataException("'Add New' button should not be available.");
			}
		}else {
			if(inFacilityDetailPg.checkChangeImmediateModeLink()){
				throw new ErrorOnDataException("Change Immediate Mode Link should doesn't display.");
			}
			if(inFacilityDetailPg.checkChangeRequestModeLink()){
				throw new ErrorOnDataException("Change Request Mode Link should doesn't display.");
			}
			if(!inFacilityDetailPg.checkModeBar()){
				throw new ErrorOnDataException("Mode Bar should display.");
			}
		}
	}
}
