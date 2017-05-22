package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.licensedetails;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.Harvest;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseDetailsPage;
import com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Verify Harvest Reports section on License Details page
 * @Preconditions:
 * 1. Make sure the two harvest document templates exist, d_hf_add_print_doc_temp, id=170, 180
 * 2. Make sure the privilege has added the print documents,  d_hf_add_print_doc, id=290, 300
 * @SPEC: Harvest Reports section - Tag numbers displaying [TC:046896]
 * @Task#: Auto-1719
 * 
 * @author Lesley Wang
 * @Date  Jun 20, 2013
 */
public class VerifyHarvestReports extends HFMOWebTestCase {
	private HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
	private String pageTitle, sectionTitle, description, dateFormat;
	private Harvest harvest1, harvest2;
	@Override
	public void execute() {
		// make a privilege with harvest document in Web
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.makePrivilegeOrderToCart(cus, privilege);
		cus.orderNum = hf.checkOutCart(pay);
		privilege.privilegeId = hf.getPrivilegeNumByOrdNum(schema, cus.orderNum);
		
		// View License details page
		this.setHarvestNums(privilege.privilegeId);
		hf.gotoLicDetailsPg(privilege.privilegeId, true);
		this.verifyPageTitleAndCaption();
		this.verifyHarvestReports();
		
		// Complete the first harvest report
		harvest1.dateOfKill = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-1), dateFormat);
		harvest1.confirmationNum = hf.fillHarvestReportFromLicDetailsPg(harvest1);
		harvest1.createdDateTime = DateFunctions.getToday(dateFormat);
		this.verifyHarvestReports();
		hf.signOut();
		
		// Invalid license in LM
		new InvalidateCutPrivilegesFunction().execute(new Object[] {loginLM, cus, privilege.licenseYear, privilege.searchStatus});
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.dateOfBirth = "01/01/1986"; //d_web_hf_signupaccount, id=740
		cus.identifier.identifierType = hf.getIdenTypeName(schema, IDEN_GREENCARD_NUM_ID, false, true);
		cus.identifier.identifierNum = "LicDet04";
		cus.identifier.country = "Canada";

		// Login info for LM
		loginLM.location = "MO Mod 1 - Auto/ACADEMY SPORTS & OUTDOORS(Store Loc)"; // The role name is temporary and need to change after MO release

		// Privilege Info
		privilege.name = "HFMO License001";
		privilege.code = "MOA";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = Integer.toString(DateFunctions.getCurrentYear());
		
		pageTitle = "Hunt & Fish Permit Details\\s*Details of the permit including the Order and Harvest Reports. Print this page for your records.";
		sectionTitle = "Harvest Reports";
		description = "Click the above link to report a harvest using this tag.";
		dateFormat = "E MMM dd yyyy";
				
		harvest1 = new Harvest();
		harvest2 = new Harvest();
		
		//Sara[10/49/2013], HFMO, AMS
		pay.payType = Payment.PAY_VISA;
		pay.creditCardNumber = "4112344112344113"; 
	}

	/** Get Harvest numbers from DB */
	private void setHarvestNums(String priNum) {
		List<String> nums = hf.getHarvestNumsByPriNum(schema, privilege.privilegeId);
		harvest1.harvestNum = nums.get(0);
		harvest2.harvestNum = nums.get(1);
	}
	
	private void verifyPageTitleAndCaption() {
		String actual = licDetailsPg.getPageTitleAndCaption();
		if (!actual.matches(pageTitle)) {
			throw new ErrorOnPageException("Page title for license with harvest report is wrong!", pageTitle, actual);
		}
		logger.info("---Verify Page title for license with harvest report correctly!");
	}
	
	private void verifyHarvestReports() {
		boolean result = true;
		result &= MiscFunctions.compareString("Harvest Reports Section ttile", sectionTitle, licDetailsPg.getHarvestReportsTitle());
		result &= licDetailsPg.compareHarvestReports(description, harvest1, harvest2);
		if (!result) {
			throw new ErrorOnPageException("Harvest Reports info are wrong!");
		}
		logger.info("---Verify Harvest Reports info correctly!");
	}
}
