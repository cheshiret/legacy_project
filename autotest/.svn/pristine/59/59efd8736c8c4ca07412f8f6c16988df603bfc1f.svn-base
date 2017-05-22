/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ActionFailedException;

/**
 * @Description:This case is used to verify 'cancel editing privilege'.
 * @Preconditions:
 * @SPEC:Edit Privilege Product.doc
 * @Task#:AUTO-670
 * 
 * @author asun
 * @Date  Aug 3, 2011
 */
public class CancelWhenEditingPrivilegeDetails extends LicenseManagerTestCase {

	private String nameBeforeEdit;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		nameBeforeEdit=this.getPrivilegeName(privilege.code);
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		this.verifyCancel(privilege.code, nameBeforeEdit);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		privilege.code="A8";
	}
	/**
	 * Verify cancel when edit privilege name.
	 * @param code
	 * @param nameBeforeEdit
	 */
	public void verifyCancel(String code,String nameBeforeEdit){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();
		logger.info("Verify cancel when edit privilege name.");
		privilegeDetailsPg.setName(nameBeforeEdit+"-Auto");
		privilegeDetailsPg.clickCancel();
		privilegeListPage.waitLoading();
		String nameAfterCancelEdit=this.getPrivilegeName(code);
		if(!nameAfterCancelEdit.equals(nameBeforeEdit)){
			throw new ActionFailedException("Cancel Editing Privilege failed...");
		}
	}
	
	public String getPrivilegeName(String code){
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();
	    return privilegeListPage.getPrivilegeListInfoByColumnName(code, "Privilege Product Name");
	}

}
