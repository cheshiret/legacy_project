package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.newsale;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsPaymentDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:check point: make a payment for order work flow(using credit card)
 * @Preconditions:
 * @SPEC:Make Payment
 * @Task#:AUTO-880
 * 
 * @author szhou
 * @Date Mar 16, 2012
 */
public class MakePaymentUseCreditCard extends LicenseManagerTestCase {
	private FinanceManager fnm = FinanceManager.getInstance();
	private LoginInfo loginfFnm = new LoginInfo();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// purchase a privilege
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		// set payment
		String orderNum = lm.processOrderCart(pay);
		if(orderNum.contains(" ")){
        	orderNum = orderNum.split(" ")[0];
        }
		// verify payment info from DB
		Payment payFromDB = lm.getPaymentInfoByOrderID(schema, orderNum);
		this.setPaymentInfo(payFromDB,orderNum);
		this.verifyPayment(payFromDB,pay);
		lm.logOutLicenseManager();
		
		// go to payment page in the finance Manager
		fnm.loginFinanceManager(loginfFnm, false);
		fnm.gotoPaymentsPage();
		fnm.searchToPaymentDetailPg("Order Number", orderNum);
		// verify payment detail info
		this.verifyPaymentInfo(pay);
		fnm.logoutFinanceManager();
	}

	private void verifyPayment(Payment payInfoDB, Payment payInfo) {
		logger.info("Start to verify payment info from DB...");
		if (!payInfoDB.status.equalsIgnoreCase(payInfo.status)) {
			throw new ErrorOnDataException("Payment Status is not correct.",
					payInfo.status, payInfoDB.status);
		}
		if (!payInfoDB.salesChanl.equalsIgnoreCase(payInfo.salesChanl)) {
			throw new ErrorOnDataException("Payment Channel is not correct.",
					payInfo.salesChanl, payInfoDB.salesChanl);
		}
		if (!payInfoDB.collectUser.equalsIgnoreCase(payInfo.collectUser)) {
			throw new ErrorOnDataException(
					"Payment collect User is not correct.",
					payInfo.collectUser, payInfoDB.collectUser);
		}
		if (!payInfoDB.collectLocation
				.equalsIgnoreCase(payInfo.collectLocation)) {
			throw new ErrorOnDataException(
					"Payment collect Location is not correct.",
					payInfo.collectLocation, payInfoDB.collectLocation);
		}
		if (!payInfoDB.payType.equalsIgnoreCase(payInfo.payType)) {
			throw new ErrorOnDataException("Payment Type is not correct.",
					payInfo.payType, payInfoDB.payType);
		}
	}

	private void verifyPaymentInfo(Payment pay) {
		OrmsPaymentDetailsPage detailPg = OrmsPaymentDetailsPage.getInstance();
		detailPg.verifyPaymentDetails(pay, false);
	}

	private void setPaymentInfo(Payment payment,String order){
		pay.status="Received";//Pending
		pay.salesChanl="FieldMgr";
		pay.collectUser=login.userName;
		pay.collectLocation=login.location.split("\\/")[1];
		pay.paymentID=payment.paymentID;
		pay.amount=payment.amount;
		pay.belongOrderNum=order;
		pay.customer=cust.fName+", "+cust.lName;
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		loginfFnm.url = AwoUtil.getOrmsURL(env);
		loginfFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginfFnm.password = TestProperty.getProperty("orms.fnm.pw");
		loginfFnm.contract = "MS Contract";
		loginfFnm.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		privilege.name = "TestPrivilege";
		privilege.purchasingName = "TDP-TestPrivilege";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		cust.customerClass = "Individual";
		cust.dateOfBirth = "19880312";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "111195";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		cust.fName="TEST-Basic95";
		cust.lName="QA-Basic95";
	}

}
