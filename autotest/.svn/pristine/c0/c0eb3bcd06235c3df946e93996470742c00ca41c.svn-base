package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.privilegeinventory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventoryType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrAssignUnassignInventoryTypeWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrPrivilegeInventoryTypePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify unassign privilege product to one privilege inventory type
 * @Preconditions:
 * @SPEC:Unassign Privilege Inventory type from Privilege Product.UIS
 * @Task#:Auto-879

 * @author VZhang
 * @Date Mar 12, 2012
 */
public class UnassignPrivilegeToInventoryType extends LicenseManagerTestCase{
	private PrivilegeInventoryType priInventoryType = new PrivilegeInventoryType(); 

	@Override
	public void execute() {
		//check and clear up env
		List<String[]> inventoryTypeInfo = lm.queryPrivilegeInventoryTyeByCode(schema, priInventoryType.inventoryType);
		if(inventoryTypeInfo.size()>0){
			lm.deletePrivilegeInventoryTypeInfoByID(schema, inventoryTypeInfo.get(0)[0]);
		}
		
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeInventoryTypePgFromTopMenu();
		//add privilege inventory type
		lm.addPrivilegeInventoryTypeInfo(priInventoryType.inventoryType);
		priInventoryType.id = lm.queryPrivilegeInventoryTyeByCode(schema, priInventoryType.inventoryType).get(0)[0];
		//assign privilege to inventory type
		lm.assignPrivilegeProductToInventoryType(priInventoryType.id, priInventoryType.privilegeProducts);
		
		lm.unassignPrivilegeProductToInventoryType(priInventoryType.id, priInventoryType.privilegeProducts);				
		lm.gotoAssignUnAssignInventoryTypePage(priInventoryType.id);
		//verify unassignment info from assign/unassig page
		this.verifyInventoryTypeUnassignmentInfoFromAssignUnassignPage(priInventoryType.privilegeProducts);
		
		lm.gotoInventoryTypePgFromAssignUnassignInventoryTypePage();
		//verify inventory type info from inventory type list page
		priInventoryType.privilegeProducts.clear();
		this.verifyInventoryTypeListInfo(priInventoryType);		
		
		//go to privilege detail page
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		//verify inventory type info from privilege detail page
		priInventoryType.inventoryType = "None";
		this.verifyInventoryTypeFromPrivilegeDetailPage(priInventoryType.inventoryType);

		//clear up
		lm.deletePrivilegeInventoryTypeInfoByID(schema, priInventoryType.id);	
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		priInventoryType.inventoryType = "Unassign Inventory Type";
		priInventoryType.status = "Active";
		priInventoryType.privilegeProducts = new ArrayList<String>();
		priInventoryType.privilegeProducts.add("144 - 3DAYALLGAME/ARCHPRIM/CROS");
				
		privilege.code = "144";	
	}
	
	private void verifyInventoryTypeListInfo(PrivilegeInventoryType expectInventoryTypeInfo){
		LicMgrPrivilegeInventoryTypePage inventoryTypePg = LicMgrPrivilegeInventoryTypePage.getInstance();
		logger.info("Verify product assignment info from inventory type list.");
		
		boolean result = inventoryTypePg.verifyInventoryTypeInfo(expectInventoryTypeInfo);		
		if(!result){
			throw new ErrorOnPageException("Inventory type info is not correct from list, please check logger info.");
		}else {
			logger.info("Inventory type info is correct from list page.");
		}
	}
	
	private void verifyInventoryTypeUnassignmentInfoFromAssignUnassignPage(List<String> privilegeInfos){
		LicMgrAssignUnassignInventoryTypeWidget assignUnassignInventoryPg = LicMgrAssignUnassignInventoryTypeWidget.getInstance();
		
		logger.info("Verify inventory type unassignment info from assgin/unassign inventory type page.");
		
		for(int i=0; i<privilegeInfos.size(); i++){
			boolean actulSelected = assignUnassignInventoryPg.checkPrivilegeProductWhetherSelected(privilegeInfos.get(i));
			if(actulSelected){
				throw new ErrorOnDataException("Expect this privilege info should not be selected, " +
						"but actually is selected. Privilege Info = " + privilegeInfos.get(i));
			}else {
				logger.info("Privilege product unassign from inventory type info is correct.");
			}	
		}	
	}
	
	private void verifyInventoryTypeFromPrivilegeDetailPage(String expectInventoryType){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		
		logger.info("Verify inventory type info from privilege detail page.");
		String actualValue = privilegeDetailsPg.getInventoryType();
		if(!actualValue.equals(expectInventoryType)){
			throw new ErrorOnDataException("Inventory type info is not correct in privilege detail page.",
					expectInventoryType, actualValue);
		}else {
			logger.info("Inventory type info is correct in privilege detail page.");
		}
	}
}
