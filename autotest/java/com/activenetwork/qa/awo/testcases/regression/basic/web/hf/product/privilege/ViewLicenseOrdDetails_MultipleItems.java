package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: View License Order Details page when the order has multiple order items for different products, license years, quantities
 * @Preconditions:
 * @SPEC: 
 * Order Details - Order items Details and Grouping/Sorting [TC:050519]
 * Order Details section - all active order items [TC:050349]
 * @Task#: Auto-1720, Auto-1721
 * 
 * @author Lesley Wang
 * @Date  Jun 7, 2013
 */
public class ViewLicenseOrdDetails_MultipleItems extends HFSKWebTestCase {
	private String ordDate;
	private String[] priNums;
	private PrivilegeInfo priReplace, priDiffYear, priDiffPrd, priDiffPrd2;
	private HFOrderDetailsPage ordDetailsPg = HFOrderDetailsPage.getInstance();
	private HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
	private boolean isValidDatesHide;
	
	@Override
	public void execute() {
		// In LM, make a order with multiple order items, including different products, license years, quantities, status
		this.prepareLicenseOrders();
		this.setPrivilegeNums(cus.orderNum);
		
		// View the license order details page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoOrderDetailsPg(privilege.privilegeId, cus.orderNum);
		ordDetailsPg.verifyLicenseOrderDetails(isValidDatesHide, cus.orderNum, ordDate, privilege, priReplace, priDiffYear, priDiffPrd, priDiffPrd2);
		this.verifyLicNumLinks();
		hf.signOut();
		
		// In LM, void the order
		lm.loginLicenseManager(loginLM);
		lm.voidPrivilegeOrderToCleanUp(cus.orderNum, voidReserveReason, privilege.operateNote, pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "vieworderdetails@test.com"; //d_web_hf_signupaccount, id=590
		cus.password = "asdfasdf";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "0101";
		cus.residencyStatus = RESID_STATUS_SK + " - " + IDENT_TYPE_RCMP;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "OrdDet01";
		cus.identifier.state = "Ontario";
		
		// Login info for LM
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		TimeZone timezone =  DataBaseFunctions.getContractTimeZone(schema);
		ordDate = DateFunctions.getToday("E MMM dd yyyy", timezone);
		
		// Privilege Info
		privilege.name = "HFSK License001";
		privilege.code = "SKA";
		privilege.purchasingName = privilege.code + "-"  +privilege.name;
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.validFromDate = ordDate;
		privilege.creationPrice = hf.getPriCustPrice(schema, privilege.code, 
				OrmsConstants.ORIGINAL_PURCHASE_TYPE_ID, "1");
		
		priReplace = new PrivilegeInfo();
		priReplace.name = privilege.name;
		priReplace.code = privilege.code;
		priReplace.purchasingName = privilege.purchasingName;
		priReplace.licenseYear = privilege.licenseYear;
		priReplace.validFromDate = ordDate;
		priReplace.creationPrice = hf.getPriCustPrice(schema, priReplace.code, 
				OrmsConstants.REPLACEMENT_PURCHASE_TYPE_ID, "1");
		priReplace.status = "Replacement";
		
		priDiffYear = new PrivilegeInfo();
		priDiffYear.name = privilege.name;
		priDiffYear.code = privilege.code;
		priDiffYear.purchasingName = privilege.purchasingName;
		priDiffYear.licenseYear = Integer.toString((Integer.valueOf(privilege.licenseYear) + 1));
		priDiffYear.validFromDate = ordDate;
		priDiffYear.creationPrice = privilege.creationPrice;
		
		priDiffPrd = new PrivilegeInfo();
		priDiffPrd.name = "HFSK License006";
		priDiffPrd.code = "SKF";
		priDiffPrd.purchasingName = priDiffPrd.code + "-"  +priDiffPrd.name;
		priDiffPrd.qty = "2";
		priDiffPrd.validFromDate = ordDate;
		priDiffPrd.validToDate = DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(ordDate, 200), "E MMM dd yyyy");
		priDiffPrd.licenseYear = privilege.licenseYear;
		priDiffPrd.creationPrice = hf.getPriCustPrice(schema, priDiffPrd.code, 
				OrmsConstants.ORIGINAL_PURCHASE_TYPE_ID, "1");
		
		priDiffPrd2 = new PrivilegeInfo();
		priDiffPrd2.validFromDate = priDiffPrd.validFromDate;
		priDiffPrd2.validToDate = priDiffPrd.validToDate;
		
		pay.payType = "Cash*";
		
		isValidDatesHide = this.isValidDatesHide(url);
	}

	private void prepareLicenseOrders() {
		lm.loginLicenseManager(loginLM);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cus, privilege);
		String orgOrd = lm.processOrderCart(pay);
		privilege.privilegeId = lm.getPrivilegeNumByOrdNum(schema, orgOrd);
	
		lm.replacePrivilegeToCartFromCustomerTopMenuByPrivilegeNums(cus, privilege.privilegeId);
		lm.addMorePrivilegeFromCartToCart(privilege, priDiffYear, priDiffPrd);
		cus.orderNum = lm.processOrderCart(pay);	
		lm.logOutLicenseManager();
	}
	
	private void setPrivilegeNums(String ordNum) {
		priNums = hf.getPrivilegeNumsByOrdNum(schema, cus.orderNum);
		priReplace.privilegeId = priNums[0];
		privilege.privilegeId = priNums[1];
		priDiffYear.privilegeId = priNums[2];
		priDiffPrd.privilegeId = priNums[3];
		priDiffPrd2.privilegeId = priNums[4];
	}
	
	private void verifyLicNumLinks() {
		boolean result = true;
		for (int i = 0; i<priNums.length; i++) {
			hf.gotoLicDetailsPgFromOrdDetailsPg(priNums[i]);
			result &= MiscFunctions.compareString("license number", priNums[i], licDetailsPg.getLicenseNum());
			hf.gotoOrderDetailsPgFromLicDetailsPg(cus.orderNum);
		}
		if (!result) {
			throw new ErrorOnPageException("License Number links on order details page are wrong!");
		}
		logger.info("---Verify License Number links on order details page correctly!");
	}
}
