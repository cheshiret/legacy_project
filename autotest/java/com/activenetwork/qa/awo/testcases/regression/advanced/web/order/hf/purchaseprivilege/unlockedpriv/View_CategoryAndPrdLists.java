package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.unlockedpriv;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoryPrdListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOUnlockedPrivTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: View Unlocked Privilege on Category list page and product list page
 * @Preconditions: 
 * Make sure the customer, the privilege, the hunt exist.
 * Make sure there are at least one privilege with license year=next year exists.
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=860
 * d_hf_add_privilege_prd:id=2160
 * d_hf_add_pricing:id=3052
 * d_hf_assi_pri_to_store:id=1410
 * d_hf_add_prvi_license_year:id=2140
 * d_hf_add_qty_control:id=1390
 * d_hf_add_hunt:id=10
 * d_hf_assign_priv_to_hunt:id=10
 * d_hf_add_hunt_license_year:id=10

 * @SPEC: 
 * Unlocked Privilege setup [TC:067959]
 * Unlocked Privilege displaying on product category and product list page [TC:068245]
 * @Task#: Auto-1833
 * 
 * @author Lesley Wang
 * @Date  Aug 5, 2013
 */
public class View_CategoryAndPrdLists extends HFMOUnlockedPrivTestCase {
	private HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage.getInstance();
	private HFLicenseCategoryPrdListPage prdListPg = HFLicenseCategoryPrdListPage.getInstance();
	private PrivilegeInfo secPriForFilter = new PrivilegeInfo();
	private String privLinkText;
	
	public void execute() {
		this.prepareUnlockedPriv(fileName, privilege, cus.custNum, hunt);
		
		// Check the privilege in HFMO site
		hf.invokeURL(url);
		hf.gotoLicenseCategoriesListPg(cus, false);
		hf.filterPrivilegeInLicenseCategoriesListPg(privilege.licenseYear);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name, hunt.getDescription(), hunt.getNumOfTagQty(), true);
		catListPg.verifyPrivilegeLinkText(privilege.code, privilege.name, hunt.getDescription(), hunt.getNumOfTagQty(), privLinkText);
		
		// Filter the privilege by category name and check in Category list page
		this.filterPrivAndVerifyPrivInCategoryListPg(privilege.displayCategory, StringUtil.EMPTY, StringUtil.EMPTY, true);
		this.filterPrivAndVerifyPrivInCategoryListPg(secPriForFilter.displayCategory, StringUtil.EMPTY, StringUtil.EMPTY, false);
			
		// Filter the privilege by type and check in Category list page
		this.filterPrivAndVerifyPrivInCategoryListPg(StringUtil.EMPTY, privilege.displaySubCategory, StringUtil.EMPTY, true);
		this.filterPrivAndVerifyPrivInCategoryListPg(StringUtil.EMPTY, secPriForFilter.displaySubCategory, StringUtil.EMPTY, false);
		
		// Filter by license year and check the privilege
		this.filterPrivAndVerifyPrivInCategoryListPg(StringUtil.EMPTY, StringUtil.EMPTY, privilege.licenseYear, true);
		this.filterPrivAndVerifyPrivInCategoryListPg(StringUtil.EMPTY, StringUtil.EMPTY, secPriForFilter.licenseYear, false);
		
		// Filter by All conditions
		this.filterPrivAndVerifyPrivInCategoryListPg(privilege.displayCategory, privilege.displaySubCategory, privilege.licenseYear, true);
		
		// Check the privilege in Product list page by clicking Category name
		hf.searchLicensetoLicenseListPg(privilege.displayCategory, privilege.displaySubCategory, privilege.licenseYear);
		prdListPg.verifyPrivilegeExist(privilege.code, privilege.name, hunt.getDescription(), hunt.getNumOfTagQty(), true);
		prdListPg.verifyPrivilegeLinkText(privilege.code, privilege.name, hunt.getDescription(), hunt.getNumOfTagQty(), privLinkText);
		
		// Filter the privilege and check in Product list page
		this.filterPrivAndVerifyPrivInProductListPg(privilege.displaySubCategory, StringUtil.EMPTY, true);
		this.filterPrivAndVerifyPrivInProductListPg(secPriForFilter.displaySubCategory, StringUtil.EMPTY, false);
		
		this.filterPrivAndVerifyPrivInProductListPg(StringUtil.EMPTY, privilege.licenseYear, true);
		this.filterPrivAndVerifyPrivInProductListPg(StringUtil.EMPTY, secPriForFilter.licenseYear, false);
		
		hf.signOut();
	}
	
	public void wrapParameters(Object[] param) {
		// Customer Info
		cus.fName = "IdentMode02_FN"; // d_web_hf_signupaccount, id=860
		cus.lName = "IdentMode02_LN";
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-16);
		cus.custNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.identifier.identifierType = hf.getIdenTypeName(schema, IDEN_CONSERVATION_ID, false, false).replace("Number", "#");
		cus.identifier.identifierNum = cus.custNum;
		
		// Privilege Info
		privilege.licenseYear = hf.getFiscalYear(schema); //Integer.toString(DateFunctions.getCurrentYear());
		privilege.code = "MOF";
		privilege.name = "HFMO HuntLic001";
		privilege.displayCategory = "Fishing";
		privilege.displaySubCategory = "Annual";
		
		// Privilege Info for filter
		secPriForFilter.licenseYear = String.valueOf(DateFunctions.getYearAfterGivenYear(1, privilege.licenseYear)); //Integer.toString(DateFunctions.getYearAfterCurrentYear(1));
		secPriForFilter.displayCategory = "Hunting";
		secPriForFilter.displaySubCategory = "Other"; //"Trip"; Sara[12102013] No Trip sub category now, so change it to Other after confirm with Lesley
		
		// Hunt Info: hunt LY, hunt code
		hunt = new HuntInfo();
		hunt.setHuntCode("H01");
		hunt.setDescription("HFMO Hunt001");
		hunt.setSpecie("Ducks");
		hunt.setPointTypeCode(hf.getPointTypeCode(schema, "Deer"));	
		hunt.setNumOfTagQty("1");
		
		// Login Info for LM
		loginLM.location = "MO Admin/MO Department of Conservation";
		
		privLinkText = privilege.name + "[#" + privilege.code + "] - " + hunt.getDescription() + "(# of tags: " + hunt.getNumOfTagQty() + " )";
		
		fileName = "ViewUnlockedPri_CatPrdList";
	}
	
	private void filterPrivAndVerifyPrivInCategoryListPg(String category, String type, String licYear, boolean exp) {
		catListPg.filterPrivilege(category, type, licYear);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name, hunt.getDescription(), hunt.getNumOfTagQty(), exp);
		if (exp) {
			catListPg.verifyPrivilegeLinkText(privilege.code, privilege.name, hunt.getDescription(), hunt.getNumOfTagQty(), privLinkText);
		}
		catListPg.clearSearch();
	}
	
	private void filterPrivAndVerifyPrivInProductListPg(String type, String licYear, boolean exp) {
		prdListPg.filterPrivilege(type, StringUtil.EMPTY, licYear);
		prdListPg.verifyPrivilegeExist(privilege.code, privilege.name, hunt.getDescription(), hunt.getNumOfTagQty(), exp);
		if (exp) {
			prdListPg.verifyPrivilegeLinkText(privilege.code, privilege.name, hunt.getDescription(), hunt.getNumOfTagQty(), privLinkText);
		}
		prdListPg.clearSearch();
	}
}
