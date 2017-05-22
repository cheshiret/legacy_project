package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.unlockedpriv;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOUnlockedPrivTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Purchase an unlocked privilege which has quantity control setup as No Multiple Allowed, verify the quantity control works well
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=930
 * d_hf_add_privilege_prd:id=2160
 * d_hf_add_pricing:id=3061
 * d_hf_assi_pri_to_store:id=1410
 * d_hf_add_prvi_license_year:id=2140,2150
 * d_hf_add_qty_control:id=1390
 * d_hf_add_hunt:id=10
 * d_hf_assign_priv_to_hunt:id=10
 * d_hf_add_hunt_license_year:id=10,20
 * @SPEC: Unlocked Privilege sale flow - quantity control [TC:068032]
 * @Task#: Auto-1833
 * 
 * @author Lesley Wang
 * @Date  Aug 13, 2013
 */
public class Purchase_NoMultAllowed extends
		HFMOUnlockedPrivTestCase {
	
	private HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage.getInstance();
	private PrivilegeInfo priv2;
	private HuntInfo hunt2;
	
	@Override
	public void execute() {
		// Import 3 unlocked privilege records for the same privilege and same hunt: 2 records for current license year, 1 for next license year 
		this.prepareUnlockedPriv(fileName, new PrivilegeInfo[] {privilege, privilege, priv2}, cus.custNum, new HuntInfo[] {hunt, hunt2, hunt});

		hf.invokeURL(url);
		hf.makePrivilegeOrderToCart(cus, privilege, hunt, false);
		hf.gotoPrdCategoryListPgFromCart();
		
		// Verify other 2 records are not shown after purchase one unlocked privilege
		catListPg.filterPrivilege(StringUtil.EMPTY, StringUtil.EMPTY, privilege.licenseYear);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name, hunt.getDescription(), hunt.getNumOfTagQty(), false);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name, hunt2.getDescription(), hunt2.getNumOfTagQty(), false);
		
		catListPg.filterPrivilege(StringUtil.EMPTY, StringUtil.EMPTY, priv2.licenseYear);
		catListPg.verifyPrivilegeExist(priv2.code, priv2.name, hunt.getDescription(), hunt.getNumOfTagQty(), false);
		
		// Abandon order cart and verify the 3 records are all shown.
		hf.gotoShoppingCartPgFromHeaderBar();
		hf.abandonCart();
		hf.gotoLicenseCategoriesListPg();
		catListPg.filterPrivilege(StringUtil.EMPTY, StringUtil.EMPTY, privilege.licenseYear);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name, hunt.getDescription(), hunt.getNumOfTagQty(), true);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name, hunt2.getDescription(), hunt2.getNumOfTagQty(), true);
		
		catListPg.filterPrivilege(StringUtil.EMPTY, StringUtil.EMPTY, priv2.licenseYear);
		catListPg.verifyPrivilegeExist(priv2.code, priv2.name, hunt.getDescription(), hunt.getNumOfTagQty(), true);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Customer Info
		cus.fName = "ULPriv03_FN"; // d_web_hf_signupaccount, id=930
		cus.lName = "ULPriv03_LN";
		cus.dateOfBirth = "01/01/"+ DateFunctions.getYearAfterCurrentYear(-16);
		cus.custNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.identifier.identifierType = hf.getIdenTypeName(schema, IDEN_CONSERVATION_ID, false, false).replace("Number", "#");
		cus.identifier.identifierNum = cus.custNum;
		
		// Hunt info
		hunt = new HuntInfo();
		hunt.setHuntCode("H01");
		hunt.setDescription("HFMO Hunt001");
		hunt.setSpecie("Ducks");
		hunt.setPointTypeCode(hf.getPointTypeCode(schema, "Deer"));	
		hunt.setNumOfTagQty("1");
		
		hunt2 = new HuntInfo();
		hunt2.setHuntCode("H01");
		hunt2.setDescription("HFMO Hunt001");
		hunt2.setSpecie("Ducks");
		hunt2.setPointTypeCode(hunt.getPointTypeCode());	
		hunt2.setNumOfTagQty("2");
		
		// Privilege Info
		privilege.licenseYear = hf.getFiscalYear(schema); //Integer.toString(DateFunctions.getCurrentYear());
		privilege.code = "MOF";
		privilege.name = "HFMO HuntLic001";
		
		priv2 = new PrivilegeInfo();
		priv2.licenseYear = Integer.toString(DateFunctions.getYearAfterCurrentYear(1));
		priv2.code = privilege.code;
		priv2.name = privilege.name;
		
		
		fileName = "PurchaseUnlockedPri_NoMultAllowed";
	}

}
