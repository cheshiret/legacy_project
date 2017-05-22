package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.batcheditlicenseyear;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrBatchEditLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * The System shall consider a Privilege Product as a candidate for the License Year editing in batch if the following conditions are true:
 * The Privilege Product has a Status of "Active"; and
 * The Privilege Product has Revenue Location in the same Chart of Accounts as the known logged-in Location; and
 * At least one Privilege License Year record associated with the Privilege Product exists with a Status of "Active" and with License Year equal to the specified License Year to be edited.
 * @Preconditions:
 * @SPEC: Edit License Year for Privileges in Batch
 * @Task#: AUTO-726
 * 
 * @author SWang
 * @Date  Aug 19, 2011
 */
public class CandidatePrivilegeLicenseYear extends LicenseManagerTestCase{
	private LicMgrBatchEditLicenseYearPage batchEditLicenseYearPg = LicMgrBatchEditLicenseYearPage.getInstance();
	private LicenseYear ly = new LicenseYear();
	private String parkName = "";

	public void execute() {
		//clean up
		lm.deleteAllProductDocFormDBForOneProduct("D1c", "privilege", parkName, schema);
		
		//login in 
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();

		//Add privilege and associated license year with chart-of-account location
		this.checkPrivilegesAndAdd();
		lm.activateOrInactivatePrivilege(privilege.code, "Activate");
		this.checkAndAddPrivilegeLicenseYears();

		//Check license year record displays with active privilege and the associated active license year
		this.checkPrivilegeLicenseYearExist(true);

		//Check license year doesn't display with active privilege and the associated inactive license year
		lm.gotoPrivilegeListPgFromGivePage(batchEditLicenseYearPg);
		lm.activateOrInactivatePrivilegeLicenseYear(privilege.code, ly.id, "Inactivate");
		this.checkPrivilegeLicenseYearExist(false);

		//Check license year doesn't display with inactive privilege and associated inactive license year
		lm.gotoPrivilegeListPgFromGivePage(batchEditLicenseYearPg);
		lm.activateOrInactivatePrivilege(privilege.code, "Inactivate");
		this.checkPrivilegeLicenseYearExist(false);

		//Check license year doesn't display with inactive privilege and associated active license year
		lm.gotoPrivilegeListPgFromGivePage(batchEditLicenseYearPg);
		lm.activateOrInactivatePrivilegeLicenseYear(privilege.code, ly.id, "activate");
		this.checkPrivilegeLicenseYearExist(false);

		//Active privilege
		lm.gotoPrivilegeListPgFromGivePage(batchEditLicenseYearPg);
		lm.activateOrInactivatePrivilege(privilege.code, "activate");

		//Log out 
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		parkName=login.location.split("/")[1];
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//Privilege information
		privilege.code = "D1c";
		privilege.name = "BatchEditLicenseYearTest4";
		privilege.status = "Active";
		privilege.customerClasses = new String[]{"Individual"};
		privilege.displayCategory = "BELYDisplayCategory1";
		privilege.displaySubCategory = "BELYDisplaySubCategory";
//		privilege.reportCategory = "rachel test";
		privilege.displayOrder = "0";

		ly.licYear = String.valueOf(DateFunctions.getCurrentYear());
		ly.locationClass = "15 - Active Phone Sales";
		ly.sellFromDate = DateFunctions.getDateAfterToday(3);
		ly.sellFromTime = "12:00";
		ly.sellToDate = DateFunctions.getDateAfterToday(5);
		ly.sellToTime = "6:00";
	}

	private void checkPrivilegesAndAdd(){
		LicMgrPrivilegesListPage privilegeListPg = LicMgrPrivilegesListPage.getInstance();
		if(!privilegeListPg.verifyPrivilegeProductExist(privilege)){
			lm.addPrivilegeProduct(privilege);
		}
	}

	private void checkAndAddPrivilegeLicenseYears(){
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
		.getInstance();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeLicenseYearPage();
		if(!licenseYearPg.verifyLicenseYearExist(ly)){
			ly = lm.addLicenseYear(ly);
		}else{
			ly.id = licenseYearPg.getLicenseYearId(ly.locationClass, ly.licYear);
		}
		lm.gotoPrivilegeListPgFromGivePage(licenseYearPg);
	}

	private void checkPrivilegeLicenseYearExist(boolean existed){
		lm.gotoBatchEditLicenseYearPg();
		batchEditLicenseYearPg.gotoPrivilegeLicenseYearGridWithYearToEdit(ly.licYear);
		batchEditLicenseYearPg.checkPrivilegeProductRecordExist(ly.licYear, privilege.displayCategory , privilege.name, existed);
	}
}
