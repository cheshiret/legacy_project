package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear.batchadd;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearBatchAddPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Prerequisite:
 * 1: there is a privilege product code(AT2), name (Auto Batch Privilege Prod) exist..
 * 2: there is one license year setup for this product(current year+3)[make sure no license year for current year+8],.
 * 3: there is one print documents setup for this product(current year+3) with template name "AutoBatchAddPri", equipment type "Internet Sales"(goto Admin->configures add if there is no such template)
 * @Description:
 * verify batch add license year page search result based on Privilege Product Status
 * @Preconditions:
 * @SPEC: Add License Year for Privileges in Batch
 * @Task#:AUTO-725
 *
 * @author bzhang
 * @Date  Aug 25, 2011
 */
public class VerifySchResultPriStatus extends LicenseManagerTestCase {
	private LicenseYear ly = new LicenseYear();
	private boolean pass = true;
	LicMgrPrivilegeLicenseYearBatchAddPage priLicYearBatchAddPg = LicMgrPrivilegeLicenseYearBatchAddPage.getInstance();

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeLicenseYearBatchAddPageFromTopMenu();

		//1 verify active privilege with active license year and fulfillment record exist on batch add page
		lm.searchPrivilegeLicenseYearOnBatchAddPg(ly);
		priLicYearBatchAddPg.verifyLicYearRecordExist(ly, true);
		priLicYearBatchAddPg.verifyFulFillMentDocumentExist(document.docTepl, document.equipType, true);

		//2 verify inactive privilege product's active license year record and fulfillment record don't exist on batch add page
		privilege.status = "Inactive";
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.editPrivilegeInfo(privilege);

		lm.gotoPrivilegeLicenseYearBatchAddPageFromTopMenu();
		lm.searchPrivilegeLicenseYearOnBatchAddPg(ly);
		priLicYearBatchAddPg.verifyLicYearRecordExist(ly, false);
		priLicYearBatchAddPg.verifyFulFillMentDocumentExist(document.docTepl, document.equipType, false);

		//clean up, active privilege status.
		privilege.status = "Active";
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code, false);
		lm.editPrivilege(privilege);
		lm.logOutLicenseManager();

		if(!pass){
			throw new ErrorOnPageException("search result for different privilege product status failed, please refer to log file for more details info");
		}

	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		//privileges info
		privilege.status = "Active";
		privilege.code = "AT2";
		privilege.name = "Auto Batch Privilege Prod";
		privilege.displayCategory = "Freshwater Fishing";
		privilege.displaySubCategory = "Annual";
		privilege.customerClasses = new String[2];
		privilege.customerClasses[0] = "Individual";
		privilege.customerClasses[1] = "Business";

		//license year info
		this.resetLicneseYearInfo();

		//fulfillment document info
		document.docTepl = "AutoBatchAddPri";
		document.equipType = "Internet Sales";
		document.purchaseType = "Transfer";
	}

	private void resetLicneseYearInfo(){
		ly.status = "Active";
		ly.locationClass = "03 - Lakes Offices";
		ly.licYear = (DateFunctions.getCurrentYear()+3)+"";

		ly.productCode = privilege.code;
		ly.productName = privilege.name;

		ly.copyFromYear = (DateFunctions.getCurrentYear()+3)+"";
		ly.newLicYear = (DateFunctions.getCurrentYear()+8)+"";
		ly.numOfYearToAdd = "1";
	}
}
