package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.unlockedpriv;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOUnlockedPrivTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Purchase an unlocked privilege which has quantity control setup as below and verify the quantity control works well
 * Yes (Regardless of License Year), min quantity - 1; max quantity - 2; max allowed - 3 
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=940
 * d_hf_add_privilege_prd:id=2180
 * d_hf_add_pricing:id=3072
 * d_hf_assi_pri_to_store:id=1430
 * d_hf_add_prvi_license_year:id=2190,2200
 * d_hf_add_qty_control:id=1410
 * d_hf_add_hunt:id=70
 * d_hf_assign_priv_to_hunt:id=70
 * d_hf_add_hunt_license_year:id=110,120
 * d_hf_add_species:id=30
 * 
 * @SPEC: Unlocked Privileg sale flow - quantity control [TC:068032]
 * @Task#: Auto-1833
 * 
 * @author Lesley Wang
 * @Date  Aug 13, 2013
 */
public class Purchase_MultAllowed_RegardlessOfLicYear extends
		HFMOUnlockedPrivTestCase {

	private HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage.getInstance();
	private PrivilegeInfo priv2;
	private HuntInfo hunt2, hunt3;
	
	@Override
	public void execute() {
		// Clean Up - invalid all privileges and deactivate all unlocked privileges
		lm.loginLicenseManager(loginLM);
		lm.invalidateAllPrivilegesPerCustomer(cus, new String[] {privilege.licenseYear, priv2.licenseYear}, searchStatus, privilege.operateReason, privilege.operateNote);
		lm.deactivateAllUnlockedPrivilege(cus);
		lm.logOutLicenseManager();	
		
		// Import 6 unlocked privilege records for the same privilege and same hunt: 3 records for current license year, 3 for next license year 
		this.prepareUnlockedPriv(fileName, new PrivilegeInfo[] {privilege, privilege, privilege, priv2, priv2, priv2}, 
				cus.custNum, new HuntInfo[] {hunt, hunt2, hunt3, hunt, hunt2, hunt3});

		hf.invokeURL(url);
		
		// Purchase one record of current LY and one of next LY, verify no unlocked privileges under current LY or next LY
		hf.makePrivilegeOrderToCart(cus, privilege, hunt, false);
		hf.makePrivilegeOrderToCart(cus, priv2, hunt, true);
		hf.gotoPrdCategoryListPgFromCart();
		this.verifyUnlockedPrivExist(privilege.licenseYear, privilege.code, privilege.name, false, hunt, hunt2, hunt3);	
		this.verifyUnlockedPrivExist(priv2.licenseYear, priv2.code, priv2.name, false, hunt, hunt2, hunt3);
		
		// Abandon cart and purchase 1 of current LY
		hf.gotoShoppingCartPgFromHeaderBar();
		hf.abandonCart();
		hf.makePrivilegeOrderToCart(cus, privilege, hunt, true);
		hf.gotoPrdCategoryListPgFromCart();
		this.verifyUnlockedPrivExist(privilege.licenseYear, privilege.code, privilege.name, false, hunt);	
		this.verifyUnlockedPrivExist(privilege.licenseYear, privilege.code, privilege.name, true, hunt2, hunt3);	
		this.verifyUnlockedPrivExist(priv2.licenseYear, priv2.code, priv2.name, true, hunt, hunt2, hunt3);
		
		// purchase 1 record of next LY and verify no records for current LY or next LY
		hf.makePrivilegeOrderToCart(cus, priv2, hunt, true);
		hf.gotoPrdCategoryListPgFromCart();
		this.verifyUnlockedPrivExist(privilege.licenseYear, privilege.code, privilege.name, false, hunt, hunt2, hunt3);	
		this.verifyUnlockedPrivExist(priv2.licenseYear, priv2.code, priv2.name, false, hunt, hunt2, hunt3);
		
		// Check out cart and verify 2 records of current LY and 2 records of next LY shown
		hf.gotoShoppingCartPgFromHeaderBar();
		hf.checkOutCart(pay);
		hf.gotoLicenseCategoriesListPg();
		this.verifyUnlockedPrivExist(privilege.licenseYear, privilege.code, privilege.name, false, hunt);	
		this.verifyUnlockedPrivExist(privilege.licenseYear, privilege.code, privilege.name, true, hunt2, hunt3);	
		this.verifyUnlockedPrivExist(priv2.licenseYear, priv2.code, priv2.name, false, hunt);
		this.verifyUnlockedPrivExist(priv2.licenseYear, priv2.code, priv2.name, true, hunt2, hunt3);
		
		// Purchase 1 record of current LY, verify no records are shown for both LY
		hf.makePrivilegeOrderToCart(cus, privilege, hunt2, true);
		hf.gotoPrdCategoryListPgFromCart();
		this.verifyUnlockedPrivExist(privilege.licenseYear, privilege.code, privilege.name, false, hunt, hunt2, hunt3);	
		this.verifyUnlockedPrivExist(priv2.licenseYear, priv2.code, priv2.name, false, hunt, hunt2, hunt3);
		
		// Check out cart and verify no records of current LY or next LY shown (max allowed 3)
		hf.gotoShoppingCartPgFromHeaderBar();
		hf.checkOutCart(pay);
		hf.gotoLicenseCategoriesListPg();
		this.verifyUnlockedPrivExist(privilege.licenseYear, privilege.code, privilege.name, false, hunt, hunt2, hunt3);	
		this.verifyUnlockedPrivExist(priv2.licenseYear, priv2.code, priv2.name, false, hunt, hunt2, hunt3);
	
		hf.signOut();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Customer Info
		cus.fName = "ULPriv04_FN"; // d_web_hf_signupaccount, id=940
		cus.lName = "ULPriv04_LN";
		cus.dateOfBirth = "01/01/"+ DateFunctions.getYearAfterCurrentYear(-16);
		cus.custNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.identifier.identifierType = hf.getIdenTypeName(schema, IDEN_CONSERVATION_ID, false, false).replace("Number", "#");
		cus.identifier.identifierNum = cus.custNum;
		
		// Hunt info
		hunt = new HuntInfo();
		hunt.setHuntCode("H07");
		hunt.setDescription("HFMO Hunt007");
		hunt.setPointTypeCode(hf.getPointTypeCode(schema, "Deer"));	
		hunt.setNumOfTagQty("1");
		
		hunt2 = new HuntInfo();
		hunt2.setHuntCode(hunt.getHuntCode());
		hunt2.setDescription(hunt.getDescription());
		hunt2.setPointTypeCode(hunt.getPointTypeCode());	
		hunt2.setNumOfTagQty("2");
		
		hunt3 = new HuntInfo();
		hunt3.setHuntCode(hunt.getHuntCode());
		hunt3.setDescription(hunt.getDescription());
		hunt3.setPointTypeCode(hunt.getPointTypeCode());	
		hunt3.setNumOfTagQty("3");
		
		// Privilege Info
		privilege.licenseYear = hf.getFiscalYear(schema); //Integer.toString(DateFunctions.getCurrentYear());
		privilege.code = "MOH";
		privilege.name = "HFMO HuntLic003";
		
		priv2 = new PrivilegeInfo();
		priv2.licenseYear = Integer.toString(DateFunctions.getYearAfterGivenYear(1, privilege.licenseYear));
		priv2.code = privilege.code;
		priv2.name = privilege.name;
		
		fileName = "PurchaseUnlockedPri_MultAllowed_RegardlessOfLY";
	
	}
	
	public void verifyUnlockedPrivExist(String ly, String privCode, String privName, boolean exp, HuntInfo...hunts) {
		catListPg.filterPrivilege(StringUtil.EMPTY, StringUtil.EMPTY, ly);
		for (HuntInfo hunt : hunts) {
			catListPg.verifyPrivilegeExist(privCode, privName, hunt.getDescription(), hunt.getNumOfTagQty(), exp);
		}
	}
}
