package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.orderdetails;

import java.text.DecimalFormat;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderDetailsPage;
import com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Verify Order Details Page UI.
 * @Preconditions:
 * @SPEC:
 * Order Details page UI [TC:050343]
 * Order Details - Verify Order Number and Order Date[TC:050345]
 * Order Details page - 'Previous' link [TC:050351]
 * @Task#: Auto-1720, Auto-1721
 * 
 * @author Lesley Wang
 * @Date  Jun 5, 2013
 */
public class VerifyLicenseOrderDetailsUI extends HFMOWebTestCase {
	private String pageTitle, caption, ordDate, feeMsg;
	private HFOrderDetailsPage ordDetailsPg = HFOrderDetailsPage.getInstance();
	
	@Override
	public void execute() {
		// Login in with an account to purchase a license order 
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.makePrivilegeOrderToCart(cus, privilege);
		cus.orderNum = hf.checkOutCartToConfirmationPage(pay);
		privilege.privilegeId = hf.getPrivilegeNumByOrdNum(schema, cus.orderNum);
		
		// Verify order details UI
		hf.gotoOrdHistPgFromOrdConfirmPg();
		hf.gotoOrderDetailsPgFromOrdHistPg(privilege.privilegeId, cus.orderNum);
		this.verifyOrderDetailsUI();
		
		// Verify Print This Page button
		ordDetailsPg.printThisPage(true);
		
		// Verify Previous links
		hf.gotoLicDetailsPgFromOrdDetailsPg(true);
		hf.gotoOrderDetailsPgFromLicDetailsPg(cus.orderNum);
		hf.gotoLicDetailsPgFromOrdDetailsPg(false);
		
		hf.signOut();
		
		// Invalid the order in LM in Web
		new InvalidateCutPrivilegesFunction().execute(new Object[] {loginLM, cus, privilege.licenseYear, searchStatus});
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "vieworderdetailsui@test.com"; // d_web_hf_signupaccount id=480
		cus.password = "asdfasdf";
		cus.dateOfBirth = "01/01/1986";
		cus.lName = "Web_OrdDetail00";
		cus.fName = "HF_OrdDetail00";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_USDRIVERSLICENSE_NUM_ID, true, false);
		cus.identifier.identifierNum = "OrdDet00";
		cus.identifier.state = "Missouri";
		
		// Login info for LM
		loginLM.location = "MO Admin/MO Department of Conservation";
		ordDate = DateFunctions.getToday("E MMM dd yyyy");
			
		// Privilege Info
		privilege.name = "HFMO License001";
		privilege.code = "MOA";
		privilege.licenseYear = Integer.toString(DateFunctions.getCurrentYear());
		privilege.validFromDate = ordDate;
		privilege.status = null;
		privilege.creationPrice = hf.getPriCustPrice(schema, privilege.code, OrmsConstants.ORIGINAL_PURCHASE_TYPE_ID, "1");
		Double pri = Double.valueOf(privilege.creationPrice);
		privilege.creationPrice = new DecimalFormat("0.00").format(pri);
		
		pageTitle = "Order Details";
		caption = "Details of the permits and fees. Print this page for your records.*";
		feeMsg = "*Mailing and convenience fees are not displayed above. The total displayed may not exactly match the amount you paid.";
	
		searchStatus = new String[] {"Active"};
	}

	private void verifyOrderDetailsUI() {
		boolean result = true;
		result &= MiscFunctions.matchString("Order Details Page Title and caption", ordDetailsPg.getPageTitleAndCaption(), pageTitle+" ?"+caption);
		result &= MiscFunctions.compareString("Order Number", cus.orderNum, ordDetailsPg.getOrderNum());
		result &= MiscFunctions.compareString("Order Date", ordDate, ordDetailsPg.getOrderDate());
		result &= MiscFunctions.compareString("License Name", privilege.name, ordDetailsPg.getLicName());
		result &= MiscFunctions.compareString("License Year", privilege.licenseYear, ordDetailsPg.getLicYear());
		result &= MiscFunctions.compareResult("License Price", privilege.creationPrice, ordDetailsPg.getLicPrice());
		result &= MiscFunctions.compareString("License Qty", privilege.qty, ordDetailsPg.getLicQty());
		result &= MiscFunctions.compareResult("Total Price", privilege.creationPrice, ordDetailsPg.getLicTotalPrice());
		result &= MiscFunctions.compareString("License Number", privilege.privilegeId, ordDetailsPg.getLicNum());
		if (!this.isValidDatesHide(url)) {
			result &= MiscFunctions.compareString("License Valid From Date", privilege.validFromDate, 
					DateFunctions.formatDate(ordDetailsPg.getLicValidFromDate(), "E MMM dd yyyy"));
			result &= MiscFunctions.compareString("License Valid To Date", privilege.validToDate, 
					DateFunctions.formatDate(ordDetailsPg.getLicValidToDate(), "E MMM dd yyyy"));
		}
		result &= MiscFunctions.compareString("License Status", privilege.status, ordDetailsPg.getLicStatus());
		result &= MiscFunctions.compareResult("License Number link", true, ordDetailsPg.isLicNumLinkExist(privilege.privilegeId));
		result &= MiscFunctions.compareString("Fee message", feeMsg, ordDetailsPg.getFeeMsg());
		if (!result) {
			throw new ErrorOnPageException("Order Details UI is wrong! Check logger error!");
		}
		logger.info("---Verify Order Details UI correctly!");
	}
}
