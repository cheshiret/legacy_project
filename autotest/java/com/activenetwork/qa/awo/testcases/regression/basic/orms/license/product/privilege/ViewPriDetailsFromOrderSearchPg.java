package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;
/**  
 * @Description:  View the privilege details page.
 * @Preconditions: The customer searched in system.
 * @SPEC:  VView privilege details page
 * @Task#: Auto-871
 * @author jwang8  
 * @Date  MAR 6, 2012    
 */
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class ViewPriDetailsFromOrderSearchPg extends LicenseManagerTestCase{

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String orderNum = lm.processOrderCart(pay, false);
		if(orderNum.contains(" ")){
			orderNum = orderNum.split(" ")[0];
		}
		privilege.privilegeId = lm.getPrivilegeNumByOrdNum(schema, orderNum);
		lm.gotoOrderPageFromOrdersQuickSearch(orderNum);
		lm.gotoPrivilegeDetailFromOrderPg(privilege.privilegeId);
		//Verify the privilege detail info.
		this.verifyPrivilegeDetailsInfo(privilege, cust);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS",env);
		
		privilege.purchasingName = "VPH-HisPrivilegeOrder";
		privilege.licenseYear = lm.getFiscalYear(schema);		
		privilege.qty = "1";
		privilege.status = "Active";
		privilege.creationPrice ="$4.00";
		privilege.numOfDuplicates = "0";
		privilege.numOfReprints = "0";
		
		cust.lName = "Test-ViewPriDetail";
		cust.fName ="QA-ViewPriDetail";
		cust.identifier.identifierType = "MDWFP #";//"Green Card";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName , cust.fName , schema);//"123456789";
		cust.identifier.country = "Canada";
		cust.customerClass = "Individual";
		cust.residencyStatus="Non Resident";
		cust.phoneContact = "4088144589";
		cust.email = "jas@sina.com";
	}
	
	/**
	 * Verify the privilege details info
	 * @param expectedPriInfo- the privilege info
	 * @param priCustomer- the privilege customer info.
	 */
	public void verifyPrivilegeDetailsInfo(PrivilegeInfo expectedPriInfo, Customer priCustomer){
		LicMgrPrivilegeItemDetailPage detailPg = LicMgrPrivilegeItemDetailPage.getInstance();
		boolean pass = detailPg.comparePrivilegeDetailsInfo(expectedPriInfo, priCustomer);
		if(!pass){
			throw new ErrorOnPageException("The privilege detail info have some error");
		}else{
			logger.info("Privilege detail info are all correct");
		}
		
	}


}
