package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteAndAlertListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteOrAlertDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 *(1) CreateModify%, Modify% ---Un-assign 
 *(2) AllowNoteAlertIncludeConfirmationLetter ---Un-assign 
 *(3) CreateNoteAlertChangeRequest ---Assign 
 * Result: 'Include in Confirmation Letter' check box can be edited in existed note(alert)details page.
 * @author QA
 */

public class VerifyConfirmationLetterCheckboxAvailable extends InventoryManagerTestCase{
	AdminManager adm = AdminManager.getInstance();
	LoginInfo loginam=new LoginInfo();

	RoleInfo role_1 = new RoleInfo();
	RoleInfo role_2 = new RoleInfo();
	RoleInfo role_3 = new RoleInfo();
	RoleInfo role_4 = new RoleInfo();
	List<RoleInfo> roleList = new ArrayList<RoleInfo>();
	List<String> roleStatus = new  ArrayList<String>();

	public void execute(){
		//Login admin manager to unAssign the permissions
		adm.loginAdminMgr(loginam);	
		adm.AssignOrUnassignRoleFeatures(roleList, roleStatus);
		adm.logoutAdminMgr();

		//Login inventory manager to verify 'Include in Confirmation Letter'check box
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoNotesAndAlertsPg();
		inventory.alertID = this.getFirstNoteAlertID();
		//Verify 'Include in Confirmation Letter' is available or not
		//1.Exist
		im.gotoNoteOrAlertDetaiPg(inventory, false);
		im.switchChangeEffectiveMode();
		this.verifyIncludeInConfirmationLetterAvailable();
		//2.New
		im.gotoNoteOrAlertDetaiPg(null, true);
		this.verifyIncludeInConfirmationLetterAvailable();
		im.logoutInvManager();

		//Login admin manager to unAssign the permissions
		roleStatus.clear();
		roleStatus.add("Yes");
		roleStatus.add("Yes");
		roleStatus.add("Yes");
		roleStatus.add("Yes");
		adm.loginAdminMgr(loginam);	
		adm.AssignOrUnassignRoleFeatures(roleList, roleStatus);
		adm.logoutAdminMgr();

		//login inventory manager and delete added note(alert)
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoNotesAndAlertsPg();
		im.deleteNotesOrAlerts(inventory, inventory.alertID);
	}

	public void wrapParameters(Object[] args) {
		//Login info for admin manager
		loginam.url = AwoUtil.getOrmsURL(env);
		loginam.userName = TestProperty.getProperty("orms.adm.user");
		loginam.password = TestProperty.getProperty("orms.adm.pw");
		loginam.contract="SC Contract";
		loginam.location="Administrator/South Carolina State Park Service";

		role_1.roleName = "Administrator";
		role_1.feature = "CreateModify%";
		role_1.application = "InventoryManager";
		roleList.add(role_1);
		roleStatus.add("No");

		role_2.roleName = "Administrator";
		role_2.feature = "Modify%";
		role_2.application = "InventoryManager";
		roleList.add(role_2);
		roleStatus.add("No");

		role_3.roleName = "Administrator";
		role_3.feature = "AllowNoteAlertIncludeConfirmationLetter";
		role_3.application = "InventoryManager";
		roleList.add(role_3);
		roleStatus.add("No");

		role_4.roleName = "Administrator";
		role_4.feature = "CreateNoteAlertChangeRequest";
		role_4.application = "InventoryManager";
		roleList.add(role_4);
		roleStatus.add("Yes");

		//Login info for inventory manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
	}

	public void verifyIncludeInConfirmationLetterAvailable(){
		InvMgrNoteOrAlertDetailPage noteAndAlertDetailPg = InvMgrNoteOrAlertDetailPage.getInstance();
		InvMgrNoteAndAlertListPage noteAlertListPg = InvMgrNoteAndAlertListPage.getInstance();
		if(!noteAndAlertDetailPg.checkIncludeInConfirmationLetterAvailable()){
			throw new ErrorOnDataException("Include in Confirmation Letter' check box should be available.");
		}

		noteAndAlertDetailPg.clickNoteAlertTab();
		noteAlertListPg.waitLoading();
	}
	
	private String getFirstNoteAlertID(){
		InvMgrNoteAndAlertListPage noteAndAlert = InvMgrNoteAndAlertListPage
		.getInstance();
		return noteAndAlert.getAlertIDWithLoops();
	}
}

