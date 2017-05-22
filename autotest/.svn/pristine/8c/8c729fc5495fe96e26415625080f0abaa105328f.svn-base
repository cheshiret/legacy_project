/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.residstatusdeclaration;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.HFCustomerProperty;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFResidStatusValidationFailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify the UI of the Validation fail page
 * @Preconditions: 
 * 1. Make sure the SK Residency verifier service running:
 * 1) It shows 'Running' on both app servers in System Manager;
 * AND
 * 2) It shows in /opt/instances/qa1/orms/resource/orms/
 * verifier.properties:
 * verifier.sk-residency.externalserviceoffline=false
 * 2. Make sure the customer has no override status in LM
 * @SPEC:  (Web) Residency Status Declaration Error page - UI - Validation fail [TC:062243]
 * @Task#: Auto-1624
 * 
 * @author Lesley Wang
 * @Date  Apr 19, 2013
 */
public class VerifyResidStatusValidFailPage_NoOverride extends HFSKWebTestCase {
    private WebConfiguration confi = WebConfiguration.getInstance();
	private HFResidStatusValidationFailPage failPg = HFResidStatusValidationFailPage.getInstance();
	private String failPgSubTitle_CAN, failPgSubTitle_NON, failPgSubTitle_SK, failPgInfo, failPgInfo_CAN, failPgInfo_SK, failPgInfo_NON, pageTitle, commonInfo;
	
	@Override
	public void execute() {
		hf.updateCustHFProfileAttr(schema, cus.email, GENDER_ID, cus.custGender);
		hf.updateCustLastName(schema, cus.email, cus.lName);
		
		hf.signIn(url, cus.email, cus.password);		
		
		// Select Canadian Resident status
		hf.gotoResidStatusDeclaPg();			
		hf.selectResidStatusToPrdCategoryPg(RESID_STATUS_CAN);
		this.verifyFailPageUI(RESID_STATUS_CAN, "MOH account with SK address", failPgSubTitle_CAN);
		
		// Select Non Resident status
		hf.gotoResidDeclarPgFromResidFailPg();
		hf.selectResidStatusToPrdCategoryPg(RESID_STATUS_NON);
		this.verifyFailPageUI(RESID_STATUS_NON, "MOH account with SK address", failPgSubTitle_NON);
		
		// Update the account's gender info as null in DB and select SK Resident,  verify the fail page
		hf.updateCustHFProfileAttr(schema, cus.email, GENDER_ID, StringUtil.EMPTY);
		hf.gotoResidStatusDeclaPg();			
		hf.selectResidStatusToPrdCategoryPg(RESID_STATUS_SK);
		this.verifyFailPageUI(RESID_STATUS_SK, "MOH account missing gender info", failPgSubTitle_SK);
		hf.updateCustHFProfileAttr(schema, cus.email, GENDER_ID, cus.custGender);
		
		// Delete the account's last name info in DB and select SK Resident,  verify the fail page	
		hf.updateCustLastName(schema, cus.email, StringUtil.EMPTY);
		hf.gotoResidDeclarPgFromResidFailPg();
		hf.selectResidStatusToPrdCategoryPg(RESID_STATUS_SK);
		this.verifyFailPageUI(RESID_STATUS_SK, "MOH account missing last name info", failPgSubTitle_SK);
		hf.updateCustLastName(schema, cus.email, cus.lName);
		
		// Select SK Resident status and pass the validation
		hf.gotoResidDeclarPgFromResidFailPg();
		hf.selectResidStatusToPrdCategoryPg(RESID_STATUS_SK);
		this.verifyPrdCategoryPgExist();
		hf.signOut();
		
		//Sara[4/1/2014], Per Aida, verifier.sk-residency.externalserviceoffline=true,this is the same setting as in production. We will not turn this ON unless the client requests it to be turn on in production.
		// Sign in with a non MOH account  with SK address and select SK resident, verify the fail page
//		cus.email = "nonmohsk0001@test.com";
//		hf.signIn(url, cus.email, cus.password);
//		hf.gotoResidStatusDeclaPg();			
//		hf.selectResidStatusToPrdCategoryPg(RESID_STATUS_SK);
//		this.verifyFailPageUI(RESID_STATUS_SK, "non MOH account with SK address", failPgSubTitle_SK);
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "mohsk0001@test.com"; // with SK address
		cus.password = "asdfasdf";
		cus.custGender = "F";
		cus.lName = "LYNCH";
		
		pageTitle = "Residency Status Validation Fail";
		commonInfo = "If information in your account is not correct.*update your account.*" +
				"If you selected a wrong residency status.*go back and try again.*" +
				"contact our call center for help.*qualified as a ";
//		failPgSubTitle = ".*cannot verify you as a ";
		failPgSubTitle_CAN = WebConfiguration.getInstance().getPropertiesValue(Conf.HFCustomer_prop, HFCustomerProperty.ResidenceCheck2);//confi.getHFCustomerProperty("failureMsg.residencyDeclaration.resStatusDecl.residencyCheck.2.UWP_RD_0001_HFSK"); //"Your residency status could not be verified.*";
		failPgSubTitle_NON = WebConfiguration.getInstance().getPropertiesValue(Conf.HFCustomer_prop, HFCustomerProperty.ResidenceCheck3);//confi.getHFCustomerProperty("failureMsg.residencyDeclaration.resStatusDecl.residencyCheck.3.UWP_RD_0001_HFSK");
		failPgSubTitle_SK = WebConfiguration.getInstance().getPropertiesValue(Conf.HFCustomer_prop, HFCustomerProperty.ResidenceCheck1);//confi.getHFCustomerProperty("failureMsg.residencyDeclaration.resStatusDecl.residencyCheck.1.UWP_RD_0001_HFSK");
		failPgInfo = "Important.*qualify as a ";
		failPgInfo_CAN = ".*home address has to be in Canada but not in Saskatchewan.*";
		failPgInfo_SK = ".*Name, Date of Birth, and Gender information has to match your health card.*";
		failPgInfo_NON = ".*home address cannot be in Canada.*";
	}
	
	private void verifyFailPageUI(String status, String msg, String failPgSubTitle) {
		boolean result = true;
		String expImportInfo = failPgInfo + status;
		
		result &= MiscFunctions.containString("Page Title", failPg.getPageTitle(), pageTitle);
//		result &= MiscFunctions.matchString("Page Sub Title", failPg.getSubtitleErrorMsg(), failPgSubTitle + status + ".*");
		result &= MiscFunctions.compareResult("Page Sub Title", failPgSubTitle, failPg.getSubtitleErrorMsg()); //matchString
//		if (status.equals(RESID_STATUS_SK)) {
//			expImportInfo += failPgInfo_SK;
//		} else if (status.equals(RESID_STATUS_CAN)){
//			expImportInfo += failPgInfo_CAN;
//		} else {
//			expImportInfo += failPgInfo_NON;
//		}
//		result &= MiscFunctions.matchString("Page Important Info", failPg.getImportantInfoMsg(), expImportInfo);
		result &= MiscFunctions.matchString("Page Common Info", failPg.getSubInfoMsg(), commonInfo + status + ".*");
		if (!result) {
			throw new ErrorOnPageException("The Validataion Fail Page UI displayed wrongly for " + msg);
		}
		logger.info("---Verify Validation Fail Page UI correctly for " + msg);
	}
	
	private void verifyPrdCategoryPgExist() {
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage.getInstance();
		if (!catListPg.exists()) {
			throw new ErrorOnPageException("Category List page should be shown!");
		}
		logger.info("---Verify Category List Page shown correctly!");
	}
}
