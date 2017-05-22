/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.residstatusdeclaration;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.HFCustomerProperty;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFResidStatusValidationFailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the UI of the Validation fail page when login account with override residency
 * @Preconditions: 
 * @SPEC:  (Web) Residency Status Declaration Error page - UI - Caused by Override [TC:062244]
 * @Task#: Auto-1624
 * 
 * @author Lesley Wang
 * @Date  Apr 19, 2013
 */
public class VerifyResidStatusValidFailPage_Override extends HFSKWebTestCase {
	private HFResidStatusValidationFailPage failPg = HFResidStatusValidationFailPage.getInstance();
	private String failPgSubTitle_CAN, failPgSubTitle_NON, failPgInfo, pageTitle, commonInfo;
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	
	@Override
	public void execute() {
		// Go to LM to override the customer resident
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCustomerDetails(cus.lName, cus.fName);
		custDetailsPg.editCustResidOverride(cus.residencyOverride);
		lm.logOutLicenseManager();
		
		hf.signIn(url, cus.email, cus.password);		
		
		// Select Canadian Resident status
		hf.gotoResidStatusDeclaPg();			
		hf.selectResidStatusAndProceed(RESID_STATUS_CAN, null);
		cus.residencyOverride = "Saskatchewan Resident";
		this.verifyFailPageUI(RESID_STATUS_CAN, cus.residencyOverride, failPgSubTitle_CAN);		
		hf.gotoContactPgFromResidFailPg(); // verify Contact our call center link exist
		
		// Select Non Resident status
		hf.gotoResidStatusDeclaPg();			
		hf.selectResidStatusAndProceed(RESID_STATUS_NON, cus.identifier);
		this.verifyFailPageUI(RESID_STATUS_NON + " - " + cus.identifier.identifierType, cus.residencyOverride, failPgSubTitle_NON);	
		
		// Select SK resident status
		hf.gotoResidStatusDeclaPg();			
		hf.selectResidStatusAndProceed(RESID_STATUS_SK, null);
		this.verifyPrdCategoryPgExist();
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "nonmohcan0001@overridetest.com"; // with CAN address
		cus.password = "asdfasdf";
		cus.fName = "Web_OverrideSK";
		cus.lName = "HF_CAN";
		cus.residencyStatus = RESID_STATUS_CAN;
		cus.residencyOverride = RESIDENCY_OVERRIDE_RESIDENT;//"Resident Only";
		cus.identifier.identifierType = IDENT_TYPE_OTHER;
		cus.identifier.identifierNum = new DecimalFormat("00000").format(new Random().nextInt(99999));
		cus.identifier.state = "Yukon";
		
		// Login info for LM
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = "SK Contract";
		login.location = "SK Admin/SASK";
		
		pageTitle = "Residency Status Validation Fail";
		commonInfo = "Hit Proceed to continue as ";
//		failPgSubTitle = ".*cannot verify you as a ";
		failPgSubTitle_CAN = WebConfiguration.getInstance().getPropertiesValue(Conf.HFCustomer_prop, HFCustomerProperty.ResidenceCheck2);//confi.getHFCustomerProperty("failureMsg.residencyDeclaration.resStatusDecl.residencyCheck.2.UWP_RD_0001_HFSK"); //"Your residency status could not be verified.*";
		failPgSubTitle_NON = WebConfiguration.getInstance().getPropertiesValue(Conf.HFCustomer_prop, HFCustomerProperty.ResidenceCheck3);//confi.getHFCustomerProperty("failureMsg.residencyDeclaration.resStatusDecl.residencyCheck.3.UWP_RD_0001_HFSK");
		failPgInfo = "Important.*qualify as ";
	}
	
	private void verifyFailPageUI(String status, String statusOverride, String failPgSubTitle) {
		boolean result = true;

		result &= MiscFunctions.containString("Page Title", failPg.getPageTitle(), pageTitle);
//		result &= MiscFunctions.matchString("Page Sub Title", failPg.getSubtitleErrorMsg(), failPgSubTitle + status + ".");
//		result &= MiscFunctions.matchString("Page Sub Title", failPg.getSubtitleErrorMsg(), failPgSubTitle + ".*");
		result &= MiscFunctions.compareResult("Page Sub Title", failPgSubTitle, failPg.getSubtitleErrorMsg());
		result &= MiscFunctions.matchString("Page Importain Info", failPg.getImportantInfoMsg(), failPgInfo + statusOverride + ".");
		result &= MiscFunctions.matchString("Page Common Info", failPg.getSubInfoMsg(), commonInfo + statusOverride + ".");
		if (!result) {
			throw new ErrorOnPageException("The Validataion Fail Page UI displayed wrongly.");
		}
		logger.info("---Verify Validation Fail Page UI correctly.");
	}
	
	private void verifyPrdCategoryPgExist() {
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage.getInstance();
		if (!catListPg.exists()) {
			throw new ErrorOnPageException("Category List page should be shown!");
		}
		logger.info("---Verify Category List Page shown correctly!");
	}

}
