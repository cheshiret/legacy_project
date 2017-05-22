package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.harvest;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.Harvest;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerHarvestPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: System creates the "Pending" Harvest record associated with the known Document Instance.
 * @Preconditions:1. Need an existing privilege with pricing, agent assignment, license year, quantity control and pricing document(Harvest document template);
 *                2. Need an existing customer;
 *                3. Need a Harvest document template with Species(6 - Rails/Gallinules) and Hunting Season(17 - Hunting).
 * @SPEC: Process Pending Harvest.doc
 * @Task#: AUTO-872
 * 
 * @author nding1
 * @Date  Mar 15, 2012
 */
public class ProcessPendingHarvest extends LicenseManagerTestCase {
	private Harvest harvest = new Harvest();
	private Harvest actualHarvest = new Harvest();
	private LicMgrCustomerHarvestPage harvestPage = LicMgrCustomerHarvestPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//1. make a privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		harvest.orderNum = lm.processOrderCart(pay);
		lm.gotoPrivilegeOrderDetailPage(harvest.orderNum);
		harvest.privilegeNum = LicMgrPrivilegeOrderDetailsPage.getInstance().getAllPrivilegesNum().trim();
		
		//2. goto harvest tab page
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Harvest");
		actualHarvest = harvestPage.getHarvestListInfo(harvest.privilegeNum);
		try{
			this.setHarvestNum();
			harvestPage.verifyHarvestInfo(harvest, actualHarvest);
		} catch (Exception e){
			throw new ErrorOnPageException(e);
		}finally{
			// deactivate order
			lm.gotoHomePage();
			lm.searchAndInvalidatePrivilegeOrder(harvest.orderNum, privilege.operateReason, privilege.operateNote);
		}
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/HUGH WHITE";
		login.station = "Gate house 1 AM";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		//customer info
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jan 05 1980";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "529876542";
		cust.residencyStatus = "Non Resident";
		
		//privilege product info
		privilege.code = "960";
		privilege.purchasingName = "960-ProcessPendingHarvest";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		
		//harvest info
		harvest.licenseYearPrivilege = "(" + privilege.licenseYear + ")" + privilege.purchasingName;
		harvest.createdDateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		harvest.status = OrmsConstants.PENDING_STATUS;
		harvest.species = "6 - Rails/Gallinules";
		harvest.season = "17 - Hunting";
		harvest.harvestNum = "";
	}
	
	/**
	 * Set up harvest number.
	 */
	private void setHarvestNum(){
		String first2 = this.getID(harvest.species);
		String second2 = this.getID(harvest.season);
		String third2 = privilege.licenseYear.substring(2,4);
		harvest.harvestNum = first2+second2+third2+"\\d{7}";
	}
	
	/**
	 * get ID from species and season
	 * @param str
	 * @return
	 */
	private String getID(String str){
		String id = str.split("-")[0].trim();
		if(id.length()<2){
			id = "0"+id;
		}
		return id;
	}
}
