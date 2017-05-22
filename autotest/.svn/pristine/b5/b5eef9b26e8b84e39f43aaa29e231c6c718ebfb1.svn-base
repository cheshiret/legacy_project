package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.newsale.noteAlert;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add Alert to privilege order, and then go to order details page, the alert should pop up.
 * @Preconditions:
 * 1.Need a privilege that can be purchased.
 * @SPEC:TC:016671
 * @Task#:AUTO-1273
 * 
 * @author nding1
 * @Date  Oct 18, 2012
 */
public class ViewOrderDetailWithAlert extends LicenseManagerTestCase {
	private NoteAndAlertInfo alertInfo = new NoteAndAlertInfo();
	
	@Override
	public void execute() {
        lm.loginLicenseManager(login);
        
        // purchase privilege
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);

		// add new alert to this order.
		alertInfo.id = lm.addNoteOrAlertForOrder(alertInfo);
		
		// go to order detail page
		lm.gotoHomePage();
		String alert = lm.gotoPrivilegeOrderDetailPage(orderNum);
		
		// verify whether popped up alert is correct or not
		this.verifyAlertInfo(alert);
		
		// clean up
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		login.userName = TestProperty.getProperty("orms.fm.user");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "Aug 12 1986";
		cust.lName = "TEST-TransferRule55";
		cust.fName = "QA-TransferRule55";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.residencyStatus = "Non Resident";

		alertInfo.type = "Alert";// Don't change this data.
		alertInfo.startDate = DateFunctions.getDateAfterToday(-1, timeZone);
		alertInfo.endDate = DateFunctions.getDateAfterToday(2, timeZone);
		alertInfo.text = "Add new alert to privilege order.";
		alertInfo.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		
		privilege.code = "PP1";
		privilege.name = "PriParticular1";
		privilege.purchasingName = privilege.code + "-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Automation test";
	}
	
	private void verifyAlertInfo(String alert){
		boolean result = true;
		String[] actualAlert = alert.split(":")[1].split("\\|");
		result &= MiscFunctions.compareResult("Effective Date", DateFunctions.formatDate(alertInfo.startDate, "dd/MMM/yyyy")+" - "+DateFunctions.formatDate(alertInfo.endDate, "dd/MMM/yyyy"),DateFunctions.formatDate(actualAlert[0].trim().split("-")[0].trim(),"dd/MMM/yyyy") + " - "+DateFunctions.formatDate(actualAlert[0].trim().split("-")[1].trim(),"dd/MMM/yyyy"));
		result &= MiscFunctions.compareResult("Create User", alertInfo.createUser, actualAlert[1].trim());
		//result &= MiscFunctions.compareResult("Text", alertInfo.text, actualAlert[2].trim());
        if(!actualAlert[2].trim().contains(alertInfo.text)){
        	result &= false;
        	logger.info("Expected alertinfo is "+alertInfo.text+"but actual is"+ actualAlert[2].trim() +"could includ");
        	
        }
		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
	}
}
