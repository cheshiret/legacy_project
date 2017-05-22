/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.customer;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Update customer profile and verify the message and the updated info
 * @Preconditions:
 * @SPEC:Confirmation Message - Update Customer Profile [TC:048225]
 * @Task#: Auto-1621
 * 
 * @author Lesley Wang
 * @Date  Apr 25, 2013
 */
public class UpdateCustomerProfile extends HFSKWebTestCase {
	private HFUpdateAccountPage updAcctPg = HFUpdateAccountPage.getInstance();
	private HFAccountOverviewPage acctViewPg = HFAccountOverviewPage.getInstance();
	private String msg;
	
	@Override
	public void execute() {
//		hf.invokeURL(url);
		hf.signIn(url, cus.email, cus.password);
		
		// update customer name and verify
		hf.updateProfile(cus);
		acctViewPg.verifyTopMsg(msg);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		updAcctPg.verifyAccountInfo(cus);

		// Reset customer info for next test
		this.resetCustomerInfo();
		hf.updateProfile(cus);
		acctViewPg.verifyTopMsg(msg);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		updAcctPg.verifyAccountInfo(cus);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "updatecustprofile@test.com";
		cus.password = "asdfasdf";
		
		long seq = DateFunctions.getCurrentTime();
		cus.fName = "FN"+ seq;
		cus.lName = "LN" + seq;
		cus.mName = "Middle" + seq;
		cus.suffix = "JR";
		cus.dateOfBirth = DateFunctions.formatDate(DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday(), -18 * 12), "yyyy-MM-dd");
		cus.custGender = "F";
		cus.eyeColor = "Grey";
		cus.hairColor = "Grey";
		cus.heightFt = "6";
		cus.heightIn = "1";
		cus.setDefaultSKMailingAddress();
		cus.mailAddrAsPhy = false;
		cus.setDefaultCanadaAddress();
		cus.hPhone = "8"+String.valueOf(seq).substring(0, 9);
		cus.bPhone = cus.hPhone;
		cus.workExtension = new DecimalFormat("0000").format(new Random().nextInt(9999));
		cus.mPhone = cus.hPhone;
		cus.fax = cus.hPhone;
		cus.phoneContact = "Home Phone";
		cus.contactTime = "Business Hour - Morning";
		
		msg = "Your profile is updated successfully.";
	}

	/** Reset customer info for next test */
	private void resetCustomerInfo() {
		cus.suffix = "SR";
		cus.custGender = "M";
		cus.eyeColor = "Black";
		cus.hairColor = "Black";
		cus.heightFt = "5";
		cus.heightIn = "3";
		cus.setDefaultCanadaMailingAddress();
		cus.mailAddrAsPhy = true;
		cus.phoneContact = "Work Phone";
		cus.contactTime = "Evening";
	}
}
