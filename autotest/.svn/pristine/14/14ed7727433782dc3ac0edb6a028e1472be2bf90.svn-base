/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.giftcard;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RefundInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsRefundDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This script is used to verify issue refund to gift card worked for H&F
 * @Preconditions:make sure privilege and customer exists, and issue refund to gift card feature assigned for license manager
 * @SPEC:<Issue Approved Refund to Gift Card >
 * @Task#:Auto-928
 * 
 * @author ssong
 * @Date  Mar 20, 2012
 */
public class IssueRefundToGiftCard extends LicenseManagerTestCase{

	private String voucherProgramName = "";
	private FinanceManager fnm = FinanceManager.getInstance();
	private LoginInfo loginFm = new LoginInfo();
	
	@Override
	public void execute() {
		//login finance manager check given voucher program available
		fnm.loginFinanceManager(loginFm);
		fnm.checkVoucherProgramAvailable(voucherProgramName,DateFunctions.getToday(), 365);
		fnm.logoutFinanceManager();
		
		lm.checkPrivilegesExist(schema, privilege.code);
		
		lm.loginLicenseManager(login);
		//make a privilege order and void the order to issue refund into a gift card
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String ordNum = lm.processOrderCart(pay,false);
		if(ordNum.contains(" ")){
			ordNum = ordNum.split(" ")[0];
		}
		lm.gotoPrivilegeOrderDetailPage(ordNum);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		pay.issueGiftCard = true;
		lm.processOrderCart(pay,false);
		lm.logOutLicenseManager();

		fnm.loginFinanceManager(loginFm);
		fnm.gotoRefundsPage();
		fnm.gotoRefundDetails(ordNum);
		
		this.verifyRefundIssueInfo();
		fnm.logoutFinanceManager();
	}
	
	private void verifyRefundIssueInfo(){
		OrmsRefundDetailsPage detailPg = OrmsRefundDetailsPage.getInstance();
		
		RefundInfo refund = detailPg.getRefundIssueInfo();
		
		if(!refund.status.equalsIgnoreCase("Issued")){
			throw new ErrorOnPageException("Refund Status Not Correct","Issued",refund.status);
		}
		if(!refund.refundType.equalsIgnoreCase(voucherProgramName)){
			throw new ErrorOnPageException("Refund Type Not Correct",voucherProgramName,refund.refundType);
		}
		//compare the last 4 digits of gift card
		if(!refund.issuedToGiftCardNum.substring(refund.issuedToGiftCardNum.length()-4).equalsIgnoreCase(pay.giftCardNum.substring(pay.giftCardNum.length()-4))){
			throw new ErrorOnPageException("Refund issued To Gift Card Num Not Correct",pay.giftCardNum,refund.issuedToGiftCardNum);
		}
		String currentUser = DataBaseFunctions.getLoginUserName(login.userName);
		if(!refund.issuedUser.replaceAll(", ",",").equalsIgnoreCase(currentUser)){
			throw new ErrorOnPageException("Refund issued user Not Correct",currentUser,refund.issuedUser);
		}
		if(!refund.issueChanel.equalsIgnoreCase(OrmsConstants.SALESCHAN_NAME_FIELD)){
			throw new ErrorOnPageException("Refund issued channel Not Correct",OrmsConstants.SALESCHAN_NAME_FIELD,refund.issueChanel);
		}
		String agent = login.location.split("/")[1];
		if(!refund.issuedLocation.equalsIgnoreCase(agent)){
			throw new ErrorOnPageException("Refund issued location Not Correct",agent,refund.issuedLocation);
		}
		String giftCardNum = lm.getGiftCardNumByOrdNum(schema, refund.issuedGiftCardOrd);
		if(!giftCardNum.equals(pay.giftCardNum)){
			throw new ErrorOnPageException("Refund issued To Gift Card Num Not Correct",pay.giftCardNum,giftCardNum);
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/RefundTest";
		
		//initialize login finance manager loginInfo
		loginFm.url = AwoUtil.getOrmsURL(env);
		loginFm.contract = "MS Contract";
		loginFm.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		loginFm.userName = TestProperty.getProperty("orms.fnm.user");
		loginFm.password = TestProperty.getProperty("orms.fnm.pw");

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		voucherProgramName = "TESTGIFTCARD";//"GIFT 4 PARKS";
		
		privilege.code = "COP";
		privilege.name = "CalculateOrderPrice";
		privilege.purchasingName = "COP-CalculateOrderPrice";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "36 - Test Transaction";
		privilege.operateNote = "";
		
		cust.customerClass = "Individual";
		cust.fName = "QA-Basic22";
		cust.lName = "TEST-Basic22";
		cust.dateOfBirth = "19880612";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000022";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		pay.giftCardNum = "954002610123";
	}
}
