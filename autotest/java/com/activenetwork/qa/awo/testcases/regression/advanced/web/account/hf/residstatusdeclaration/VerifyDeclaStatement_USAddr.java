/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.residstatusdeclaration;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.HFCustomerProperty;
import com.activenetwork.qa.awo.pages.web.hf.HFResidStatusValidationFailPage;
import com.activenetwork.qa.awo.pages.web.hf.HFResidencyStatusDeclarationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: Check the display of the declaration statement when select different status.
 * @Preconditions: The setup sql "SetupResidentStatusIdentifierQualifier.sql" has been run
 * @SPEC:
 * 	(Web) Residency Status Declaration page - Qualifer - Declaration required [TC:062195]
 * 	(Web) Residency Status Declaration page - Qualifier - Declaration Not required [TC:062506]
 * @Task#: Auto-1623
 * 
 * @author Lesley Wang
 * @Date  Apr 18, 2013
 */
public class VerifyDeclaStatement_USAddr extends HFSKWebTestCase{
	private HFResidencyStatusDeclarationPage resStatusPg = HFResidencyStatusDeclarationPage.getInstance();
	private HFResidStatusValidationFailPage failPg = HFResidStatusValidationFailPage.getInstance();
	private String[] residStatus;
	private String errMsg, failPgSubTitle, failPgInfo;
	
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);		
		hf.gotoResidStatusDeclaPg();	
		
		// Verify the display of Identifier info fields and declaration statement
		this.selectStatusWithoutIdentAndVerifyInfo();
		this.selectStatusWithIdentAndVerifyInfo();
		
		// Verify the error messages when select one status with declaration but not check
		hf.selectResidStatusToPrdCategoryPg(residStatus[0], cus.identifier);
		resStatusPg.verifyDeclarStatErrorMsg(residStatus[0], IDENT_TYPE_CAF, errMsg);
		
		// Verify Residency status validation fail when select one status other Canada and without declaration
		cus.identifier.identTypeShortNm = IDENT_TYPE_RCMP; // IDENT_TYPE_FL
		hf.selectResidStatusToPrdCategoryPg(residStatus[0], cus.identifier);
		failPg.verifyErrorMsgs(failPgSubTitle, failPgInfo);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "nonmohus0001@test.com"; // with US address
		cus.password = "asdfasdf";
		cus.identifier.identifierType = IDENT_TYPE_CAF;
		cus.identifier.identifierNum = new DecimalFormat("00000").format(new Random().nextInt(99999));
		cus.identifier.state = "Quebec";
		cus.identifier.isDeclarRequired = false;
		
		residStatus = new String[] {RESID_STATUS_SK, RESID_STATUS_CAN, RESID_STATUS_NON};
		errMsg = ".*acknowledge.*statement";
//		failPgSubTitle = "Your residency status could not be verified.*";
		failPgSubTitle = WebConfiguration.getInstance().getPropertiesValue(Conf.HFCustomer_prop, HFCustomerProperty.ResidenceCheck18);//WebConfiguration.getInstance().getHFCustomerProperty("failureMsg.residencyDeclaration.resStatusDecl.residencyCheck.18.UWP_RD_0001_HFSK");
		failPgSubTitle = failPgSubTitle.replaceAll("\\s+", " ").replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
//		failPgSubTitle = ".*cannot verify you as a " + residStatus[0] + " - " + IDENT_TYPE_RCMP + ".*";
//		failPgInfo = "Important.*qualify as a " + residStatus[0] + " - " + IDENT_TYPE_RCMP + ".*home address has to be in Saskatchewan, Canada.*";
	}

	private void selectStatusWithoutIdentAndVerifyInfo() {
		boolean result = true;
		for (String status : residStatus) {
			resStatusPg.selectResidentStatus(status);
			result = MiscFunctions.compareResult("Identifier Info input fields and declaration statement shown", 
					false, resStatusPg.isIdentInfoInputFieldsExist(status));
		}
		if (!result) {
			throw new ErrorOnPageException("There should be no identifier info input fields or declaration statement for account with US address");
		}
		logger.info("---Verify no identifier info input fields or declaration statement for account with US address");
	}
	
	private void selectStatusWithIdentAndVerifyInfo() {
		boolean result = true;
		resStatusPg.selectResidentStatus(residStatus[0], IDENT_TYPE_RCMP);
		result &= resStatusPg.compareIdentAndDeclarInfoExist(residStatus[0], IDENT_TYPE_RCMP, true, false, true, false, null);
		
		resStatusPg.selectResidentStatus(residStatus[0], IDENT_TYPE_CAF);
		result &= resStatusPg.compareIdentAndDeclarInfoExist(residStatus[0], IDENT_TYPE_CAF, true, false, true, true, ".*certify.*Saskatchewan resident.*");
		
		resStatusPg.selectResidentStatus(residStatus[1], IDENT_TYPE_CANDL);
		result &= resStatusPg.compareIdentAndDeclarInfoExist(residStatus[1], IDENT_TYPE_CANDL, true, false, true, true, ".*certify.*" + residStatus[1] + ".*");
		
		resStatusPg.selectResidentStatus(residStatus[1], IDENT_TYPE_FL);
		result &= resStatusPg.compareIdentAndDeclarInfoExist(residStatus[1], IDENT_TYPE_FL, true, false, false, false, null);
		
		resStatusPg.selectResidentStatus(residStatus[2], IDENT_TYPE_PASSPORT);
		result &= resStatusPg.compareIdentAndDeclarInfoExist(residStatus[2], IDENT_TYPE_PASSPORT, true, true, false, false, null);
		
		resStatusPg.selectResidentStatus(residStatus[2], IDENT_TYPE_OTHER);
		result &= resStatusPg.compareIdentAndDeclarInfoExist(residStatus[2], IDENT_TYPE_OTHER, true, true, true, false, null);
		
		if (!result) {
			throw new ErrorOnPageException("Identifier info and declaration statement are not shown correctly for the account with US address! Check logger error!");
		}
		logger.info("--Verify Identifier info and declaration statement are shown correctly for account with US address");
	}
}
