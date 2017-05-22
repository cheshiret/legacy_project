package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.edu;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationAddedPage;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationRequiredPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) Verify validation error message, navigational link "Return to Product Details" function in education info required page
 * @Preconditions:
 * d_web_hf_signupaccount, id=760, hfmo_00017@webhftest.com, Edu3_FN, Edu3_LN
 * d_hf_add_privilege_prd, id=2050, MOC, HFMO License003
 * @SPEC: 
 * Education Information page - Validation Error [TC:047159]
 * Education Information page - Navigational Link [TC:047155]  
 * @Task#: Auto-1762
 * 
 * @author Swang
 * @Date  Jul 2, 2013
 */
public class ErrorMesValidation extends HFMOWebTestCase {
	private HFEducationInfomationRequiredPage eduInfoRequiredPg = HFEducationInfomationRequiredPage.getInstance();
	private HFEducationInfomationAddedPage eduInfoAddPg = HFEducationInfomationAddedPage.getInstance();
	private Education edu1, edu2, edu3;
	private String errorMes1, errorMes2, successfulMes;

	public void execute() {
		//Lookup account
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();

		//Verify navigational "Return to Product Details" link without any date entry or selection
		hf.makePrivilegeOrder(cus, privilege, eduInfoRequiredPg);
		hf.returnToProductDetailsPage();
		hf.addPrivilegeFromPrdDetailsPg(privilege, eduInfoRequiredPg);
		eduInfoRequiredPg.verifyEducationInfo(edu1);

		//Verify error message without any data entry or selection, click 'Submit' button
		hf.updateEdu(null);
		eduInfoRequiredPg.verifyErrorMsgExist(errorMes1, true);
		eduInfoRequiredPg.verifyErrorMsgExist(errorMes2, true);
		eduInfoRequiredPg.verifyEducationInfo(edu1);

		//Verify navigational "Return to Product Details" link with error message
		hf.returnToProductDetailsPage();
		hf.addPrivilegeFromPrdDetailsPg(privilege, eduInfoRequiredPg);
		eduInfoRequiredPg.verifyErrorMsgExist(errorMes1, false);
		eduInfoRequiredPg.verifyErrorMsgExist(errorMes2, false);

		//Verify error message when leave education number blank, update country and state with different value
		hf.updateEdu(edu2);
		eduInfoRequiredPg.verifyErrorMsgExist(errorMes1, true);
		eduInfoRequiredPg.verifyErrorMsgExist(errorMes2, true);
		eduInfoRequiredPg.verifyEducationInfo(edu2);

		//Verify navigational "Return to Product Details" link with education number, country and state
		eduInfoRequiredPg.setupEducationInfo(edu3);
		hf.returnToProductDetailsPage();
		hf.addPrivilegeFromPrdDetailsPg(privilege, eduInfoRequiredPg);
		eduInfoRequiredPg.verifyErrorMsgExist(errorMes1, false);
		eduInfoRequiredPg.verifyErrorMsgExist(errorMes2, false);
		eduInfoRequiredPg.verifyEducationInfo(edu1); //edu3, Per DEFECT-58660 

		//Successfully add education without confirm the order, actually it doesn't added to the account, so no clear need
		hf.updateEdu(edu3);
		eduInfoAddPg.verifyMsgExist(successfulMes, true);
		eduInfoAddPg.verifyEducationInfo(edu3);

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "hfmo_00017@webhftest.com";
		cus.fName = "Edu3_FN";
		cus.lName = "Edu3_LN";
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_CONSERVATION_ID, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);

		//Privilege parameters
		privilege.name = "HFMO LICENSE003";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.qty = "1";

		//Education parameters
		edu1 = new Education();
		edu1.educationNum = StringUtil.EMPTY;
		//Sara[12/09/2013] This is a low priority PCR confirmed with Ranjita.
		//This should be the value query "select name from d_ref_country where DSCR = 'United States of America'", not DSCR "United States of America";
		edu1.country = "United States";
		edu1.state = "Missouri";

		edu2 = new Education();
		edu2.educationNum = StringUtil.EMPTY;
		edu2.country = "Canada";
		edu2.state = "Ontario";

		edu3 = new Education();
		edu3.educationNum = new DecimalFormat("000000").format(new Random().nextInt(999999));
		edu3.country = edu1.country;
		edu3.state = edu1.state;

		//Message parameters
		errorMes1 = "We need you to correct or provide more information\\. Please see each marked section below\\.";
		errorMes2 = "Education Number is required\\.";
		successfulMes = "Your Education information has been successfully submitted\\. Once you have completed your order, an education record will be added to your account\\.";
	}
}
