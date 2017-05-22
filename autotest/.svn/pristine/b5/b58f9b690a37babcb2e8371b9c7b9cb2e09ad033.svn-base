/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.voucher;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This script is used to verify look up voucher program and calculate amount correct when apply voucher as payment
 * @Preconditions: below setup was did by support script
 * 1.  prepare a voucher program named 'TESTPRIVILEGE' for privilege to generate voucher
 * 2.  assign 'ApplyQualifyingVoucherPayment' and 'ApplyNonQualifyingVoucherPayment' feature from admin mgr
 * 3.  make sure customer and privilege exists
 * @SPEC:<Lookup Voucher Program for Creation> AND <Calculate Voucher Amount to Apply as Payment>
 * @Task#:AUTO-928
 * 
 * @author ssong
 * @Date  Feb 29, 2012
 */
public class PurchasePrivilegeByVoucher extends LicenseManagerTestCase{
	
	private Voucher voucher = new Voucher();
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
		//make a privilege order and void the order to get voucher refund info
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String ordNum = lm.processOrderCart(pay,false).split(" ")[0];
		
		lm.gotoPrivilegeOrderDetailPage(ordNum);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		pay.issueToVoucher = true;
		lm.processOrderToOrderSummary(pay);
		retrieveVoucherAndFinishOrder();
		//verify voucher program look up correct by verify given voucher program was used
		if(!voucher.voucherProgrameName.equals(voucherProgramName)){
			throw new ErrorOnPageException("Voucher Program Used Not correct",voucherProgramName,voucher.voucherProgrameName);
		}
		
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		this.payByVocherAndVerifyCalculateAmount();
		ordNum = lm.processOrderCart(pay,false).split(" ")[0];
		lm.verifyOrderStatus(ordNum, ORD_STATUS_ACTIVE, schema);
		
		lm.logOutLicenseManager();		
	}

	private void payByVocherAndVerifyCalculateAmount(){
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		pay.voucherID = voucher.voucherId+" : "+voucher.amount;

		logger.info("Pay by Voucher and calculate voucher amount.");
		double balance = Double.parseDouble(voucher.amount.replaceAll("\\$", "")) - cartPg.getAmountOwing();
		cartPg.selectVoucherAsPayment(pay.voucherID);
		double balanceFromUI = Double.parseDouble(cartPg.getRedeemedVoucherBalance());
		
		if(balance>0){
			if(Math.abs(balance-balanceFromUI)>0.000001){
				throw new ErrorOnPageException("Voucher Balance calculate not correct",String.valueOf(balance),String.valueOf(balanceFromUI));
			}
		}else{
			if(Math.abs(balanceFromUI-0)>0.000001){
				throw new ErrorOnPageException("Voucher Balance calculate not correct",String.valueOf(0),String.valueOf(balanceFromUI));
			}
			double amountOwing = cartPg.getAmountOwing();
			if(Math.abs(balance+amountOwing)>0.0000001){
				throw new ErrorOnPageException("After pay by voucher,the amount owing not correct",String.valueOf(balance),String.valueOf(amountOwing));
			}
		}
	}
	
	private void retrieveVoucherAndFinishOrder(){
		OrmsOrderSummaryPage ordSumPg = OrmsOrderSummaryPage.getInstance();
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		
		voucher = ordSumPg.getVoucherInfo();
		ordSumPg.clickFinishOrder();
		homePg.waitLoading();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		//initialize login finance manager loginInfo
		loginFm.url = AwoUtil.getOrmsURL(env);
		loginFm.contract = "MS Contract";
		loginFm.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		loginFm.userName = TestProperty.getProperty("orms.fnm.user");
		loginFm.password = TestProperty.getProperty("orms.fnm.pw");

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		voucherProgramName = "TESTPRIVILEGE";
		
		privilege.code = "COP";
		privilege.name = "CalculateOrderPrice";
		privilege.purchasingName = "COP-CalculateOrderPrice";
		privilege.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
		privilege.qty = "2";
		privilege.operateReason = "36 - Test Transaction";
		privilege.operateNote = "";
		
		cust.customerClass = "Individual";
		cust.fName = "QA-Basic90";
		cust.lName = "TEST-Basic90";
		cust.dateOfBirth = "19880310";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "111190";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
//		pay.setType("personal check"); //For credit card can not be processed
	}

}
