package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.unlockedpriv;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFConfirmationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFCurrentLicensesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOUnlockedPrivTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:Purchase single unlocked privilege and view it in Shopping cart page, payment confirmation page, order history/details page, license history/details page
 * @Preconditions:
 * 1. Make sure the privileges and the hunts exists.
 * 2. Make sure the hunt only set species sub type, date period
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=920
 * d_hf_add_privilege_prd:id=2190
 * d_hf_add_pricing:id=3082
 * d_hf_assi_pri_to_store:id=1440
 * d_hf_add_prvi_license_year:id=2210
 * d_hf_add_qty_control:id=1420
 * d_hf_add_hunt:id=30
 * d_hf_assign_priv_to_hunt:id=30
 * d_hf_add_hunt_license_year:id=50
 * d_hf_add_species:id=30
 * d_hf_add_date_period:id=30
 * @SPEC:
 * Unlocked Privilege (single record) displaying on Shopping Cart page [TC:068247]
 * Unlocked Privilege displaying on Order History/Order Details page (My Account) [TC:068248]
 * Unlocked Privilege displaying on License History page (My Account) [TC:068249]
 *  
 * @author Lesley Wang
 * @Date  Aug 20, 2013
 */
public class Purchase_SingleOrdItem_HuntWithSpeSubTypeAndDatePeriod extends
		HFMOUnlockedPrivTestCase {
	private HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
	private HFConfirmationPage confirmPg = HFConfirmationPage.getInstance();
	private HFOrderHistoryPage orderHistoryPg = HFOrderHistoryPage.getInstance();
	private HFOrderDetailsPage ordDetailsPg = HFOrderDetailsPage.getInstance();
	private HFCurrentLicensesListPage licListPg = HFCurrentLicensesListPage.getInstance();
	private HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
	private DatePeriodInfo dateInfoWithoutCat;
	
	@Override
	public void execute() {
		new InvalidateCutPrivilegesFunction().execute(new Object[] {loginLM, cus, privilege.licenseYear, searchStatus});
		this.prepareUnlockedPriv(fileName, privilege, cus.custNum, hunt);
		
		hf.invokeURL(url);
		
		// Purchase an unlocked privilege and verify hunt info in shopping cart page and confirmation page
		hf.makePrivilegeOrderToCart(cus, privilege, hunt, false);
		shoppingCartPg.verifyPrivOrdItemInfo(privilege);
		String ordNum = hf.checkOutCartToConfirmationPage(pay);
		confirmPg.verifyPrivOrdItemInfo(privilege);
		
		// Verify Hunt info in Order history page
		hf.gotoOrdHistPgFromOrdConfirmPg();
		orderHistoryPg.verifyHuntInfo(ordNum, privilege);
		
		// Verify Hunt info in Order Details page
		privilege.privilegeId = hf.getPrivilegeNumByOrdNum(schema, ordNum);
		hf.gotoOrderDetailsPgFromOrdHistPg(privilege.privilegeId, ordNum);
		ordDetailsPg.verifyHuntInfo(privilege);
		
		// Verify Hunt info in License History page
		hf.gotoCurrentLicencesListPgFromMyAcctPanel();
		licListPg.verifyHuntInfo(privilege.privilegeId, hunt);
		
		// Verify hunt info in License Details page
		hf.gotoLicDetailsPgFromCurtLicListPg(privilege.privilegeId);
		licDetailsPg.verifyHuntInfo(hunt);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Customer Info
		cus.fName = "ULPriv02_FN"; // d_web_hf_signupaccount, id=920
		cus.lName = "ULPriv02_LN";
		cus.dateOfBirth = "01/01/"+ DateFunctions.getYearAfterCurrentYear(-16);
		cus.custNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.identifier.identifierType = hf.getIdenTypeName(schema, IDEN_CONSERVATION_ID, false, false).replace("Number", "#");
		cus.identifier.identifierNum = cus.custNum;
		
		// Privilege Info
		privilege.code = "MOI";
		privilege.name = "HFMO HuntLic004";
		privilege.licenseYear = hf.getFiscalYear(schema); //Integer.toString(DateFunctions.getCurrentYear());
		privilege.validFromDate = DateFunctions.getToday("EEE MMM dd yyyy", DataBaseFunctions.getContractTimeZone(schema));
		
		// hunt date period - current license year, 1 date info with from and to date but without category
		dateInfoWithoutCat = this.initializeHuntDPWithoutCategory(privilege.licenseYear);
		
		// Hunt info: only set species sub type, date period
		hunt = new HuntInfo();
		hunt.setHuntCode("H03");
		hunt.setDescription("HFMO Hunt003");
		hunt.setSpecie("DucksAuto");
		hunt.setSpecieSubType("STDesB");
		hunt.setHuntDatePeriodInfo(dateInfoWithoutCat.getCode() + " - " + dateInfoWithoutCat.getDescription());
		hunt.setDatePeriodInfo(dateInfoWithoutCat);
		hunt.setLicYear(privilege.licenseYear);
		hunt.setPointTypeCode(hf.getPointTypeCode(schema, "Deer"));	
		hunt.setNumOfTagQty("1");
				
		privilege.setHuntInfo(hunt);
		
		// import file name
		fileName = "Purchase_SingleOrdItem_HuntWithSpeSubTypeAndDP";					

	}

}
