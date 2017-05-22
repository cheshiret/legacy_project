package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.edu;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationRequiredPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Verify cancel link function when no any date entry or selection, all education number, country and state are filled, has error message
 * @Preconditions:
 * d_web_hf_signupaccount, id=750, hfmo_00016@webhftest.com, Edu2_FN, Edu2_LN
 * d_hf_add_privilege_prd, id=2040,  MOB, HFMO License002
 * 
 * @SPEC: Education Information page - 'Cancel' Link [TC:049366] 
 * @Task#: Auto-1763
 * 
 * @author Swang
 * @Date  Jul 2, 2013
 */
public class CancelLinkValidation extends HFMOWebTestCase {
	private HFEducationInfomationRequiredPage eduInfoRequiredPg = HFEducationInfomationRequiredPage.getInstance();
	private Education edu1, edu2, edu3;
	private String errorMes, popupMesExpected, popupMesFromUI;

	public void execute() {
		hf.signIn(url, cus.email, cus.password);

		//Verify "Cancel" link

		//Check point 1: without any date entry or selection. 
		//Result: no popup, after go back to education info page from product details page, all display as initial 
		hf.makePrivilegeOrder(cus, privilege, eduInfoRequiredPg);
		hf.cancelToProductDetailsPage();
		hf.addPrivilegeFromPrdDetailsPg(privilege, eduInfoRequiredPg);
		eduInfoRequiredPg.verifyEducationInfo(edu1);

		//Check point 2: with education number, country and state. 
		//Result: have popup, after click cancel button, will stay in education info page and all the entry data and selections are retained
		eduInfoRequiredPg.setupEducationInfo(edu2);
		popupMesFromUI = hf.cancelToUpdateEdu(false);
//		verifyMesOnPopupWindow(popupMesExpected, popupMesFromUI);
		eduInfoRequiredPg.verifyEducationInfo(edu2);

		//Result: have popup, after click ok button, will go to product details page. go back to education details page, the new change is lost and all previous retained entry data and selection displayed.
		popupMesFromUI = hf.cancelToUpdateEdu(true);
		hf.addPrivilegeFromPrdDetailsPg(privilege, eduInfoRequiredPg);
		eduInfoRequiredPg.verifyEducationInfo(edu1);

		//Check point 3: with error message when education number is blank
		//Result: no popup, after go back to education info page, error messages are missing and all the entry data and selections are retained
		hf.updateEdu(edu3);
		eduInfoRequiredPg.verifyErrorMsgExist(errorMes, true);
		hf.cancelToProductDetailsPage();
		hf.addPrivilegeFromPrdDetailsPg(privilege, eduInfoRequiredPg);
		eduInfoRequiredPg.verifyErrorMsgExist(errorMes, false);
		eduInfoRequiredPg.verifyEducationInfo(edu1);

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "hfmo_00015@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.fName = "Edu2_FN";
		cus.lName = "Edu2_LN";
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_CONSERVATION_ID, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);

		//Privilege parameters
		privilege.name = "HFMO License002";
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
		edu2.educationNum = "abcd";
		edu2.country = "Canada";
		edu2.state = "Ontario";

		edu3 = new Education();
		edu3.educationNum = StringUtil.EMPTY;
		edu3.country = edu2.country;
		edu3.state = edu2.state;

		//Message parameters
		errorMes = "We need you to correct or provide more information\\. Please see each marked section below\\.";
		
		//Sara[02272014] Per James,  Windows Internet Explore dialog is pretty much static message from Browser and will never change?
//		popupMesExpected = "Are you sure you want to navigate away from this page\\? ?Your data will not be saved\\. ?Press OK to continue, or Cancel to stay on the current page\\.";
	}

//	private void verifyMesOnPopupWindow(String expectedValue, String actualValue){
//		if(!actualValue.matches(expectedValue)){
//			throw new ErrorOnPageException("Message on popup window is wrong!", expectedValue, actualValue);
//		}
//		logger.info("Successfully verify message on popup window.");
//	}
}

