package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify the privileges are displayed in correct way according to the UI Requirements in Privilege List Page
 * @Preconditions: 
 * @SPEC: <<View Privilege Product List.doc>>, <<Privilege Product Management.doc>>
 * @Task#: AUTO-671
 * 
 * @author qchen
 * @Date  Sep 7, 2011
 */
public class View_VerifyUIRequirement extends LicenseManagerTestCase {
	private LicMgrPrivilegesListPage privilegeListPg = LicMgrPrivilegesListPage.getInstance();
	private Map<String, Integer> DISPLAYCATEGORIES = new HashMap<String, Integer>();
	private Map<String, Integer> DISPLAYSUBCATEGORIES = new HashMap<String, Integer>();
	private PrivilegeInfo privilege1 = new PrivilegeInfo();
	private PrivilegeInfo privilege2 = new PrivilegeInfo();
	private PrivilegeInfo privilege3 = new PrivilegeInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//1. Get the display categories and their corresponding display orders
		DISPLAYCATEGORIES = lm.getDisplayCategoriesAndOrders();
		DISPLAYSUBCATEGORIES = lm.getDisplaySubCategoriesAndOrders();
		
		//2.Pre-condition: check whether the 4 privileges exist in ORMS; if not, adding 4 privilege products
		this.checkAndAddPrivilege(privilege, privilege1, privilege2, privilege3);
		
		/**
		 * Verify UI Requirements
		 */
		//3. Display Category, where the User shall be able to select a subset of the valid Privilege Product Categories that they wish to be displayed
		this.verifyDisplayCategoryFilter(privilege.displayCategory);
		
		//4. Display Sub-Category, where the User shall be able to select a subset of the valid Privilege Product Display Sub-Categories that they wish to be displayed
		this.verifyDisplaySubCategoryFilter(privilege1.displayCategory, privilege.displaySubCategory, privilege1.displaySubCategory);
		
		//5. Privilege Product Status, where the User shall be able to select "Active" or "Inactive". If no option is select, it shall mean that there is no filter based on the Status
		this.verifyStatusFilter("Active");
		this.verifyStatusFilter("Inactive");
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		privilege.code = "VP0";
		privilege.name = "ViewPrivilegeList";
		privilege.status = "Active";
		privilege.productGroup = "Privileges";
		privilege.displayOrder = "1";
		privilege.customerClasses = new String[]{"Individual", "Business"};
		
		privilege1.code = "VPC";
		privilege1.name = "ViewPrivilegeList1";
		privilege1.status = "Active";
		privilege1.productGroup = "Privileges";
		privilege1.displayOrder = "1";
		privilege1.customerClasses = new String[]{"Individual", "Business"};
		
		privilege2.code = "VPB";
		privilege2.name = "ViewPrivilegeList2";
		privilege2.status = "Active";
		privilege2.productGroup = "Privileges";
		privilege2.displayOrder = "2";
		privilege2.customerClasses = new String[]{"Individual", "Business"};
		
		privilege3.code = "VPA";
		privilege3.name = "ViewPrivilegeList3";
		privilege3.status = "Active";
		privilege3.productGroup = "Privileges";
		privilege3.displayOrder = "3";
		privilege3.customerClasses = new String[]{"Individual", "Business"};
	}
	
	/**
	 * Check whether the privilege products exist or not. if yes, update their statuses to "Active"; if not, add new privilege products
	 * @param privileges
	 */
	private void checkAndAddPrivilege(PrivilegeInfo... privileges) {
		LicMgrPrivilegeProductDetailsPage privilegeDetailPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();
		
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		for(int i = 0; i < privileges.length; i ++) {
			//if privilege exists
			if(lm.checkPrivilegeExisted(privileges[i].code, schema)) {
				lm.gotoPrivilegeDetailsPageFromProductListPage(privileges[i].code);
				privilegeDetailPg.getPrivilegeInfo(privileges[i]);
				if(privileges[i].status.equalsIgnoreCase("Inactive")) {
					//if privilege's status is 'Inactive', update the status
					privilegeDetailPg.selectPrivilegeStatus("Active");
				}
				ajax.waitLoading();
				privilegeDetailPg.clickOk();
				ajax.waitLoading();
				privilegeListPage.waitLoading();
			} else {
				if(privilege.displayCategory.length() == 0) {
					//set the 1 same display category value to these 4 privileges
					for(Iterator<Map.Entry<String, Integer>> it = DISPLAYCATEGORIES.entrySet().iterator(); it.hasNext();) {
						Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>)it.next();
						privilege.displayCategory = entry.getKey();
						privilege1.displayCategory = privilege.displayCategory;
						privilege2.displayCategory = privilege.displayCategory;
						privilege3.displayCategory = privilege.displayCategory;
						break;
					}
					//set 1 display sub-category value to first privilege, and set another value to the reset 3 privileges
					for(Iterator<Map.Entry<String, Integer>> it = DISPLAYSUBCATEGORIES.entrySet().iterator(); it.hasNext();) {
						Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>)it.next();
						privilege.displaySubCategory = entry.getKey();
						entry = (Map.Entry<String, Integer>)it.next();
						privilege1.displaySubCategory = entry.getKey();
						privilege2.displaySubCategory = privilege1.displaySubCategory;
						privilege3.displaySubCategory = privilege1.displaySubCategory;
						break;
					}
				}
				lm.gotoPrivilegeSearchListPageFromTopMenu();
				lm.addPrivilegeProduct(privileges[i]);
			}
		}
	}
	
	/**
	 * Verify Display Category filter works correctly
	 * @param displayCategories
	 */
	private void verifyDisplayCategoryFilter(String... displayCategories) {
		privilegeListPg.unSelectAllDisplayCategories();
		privilegeListPg.selectDisplayCategories(displayCategories);
		privilegeListPg.clickGo();
		
		logger.info("Verify whether Display Category Filter works correctly.");
		List<String[]> actualDisplayCategories = privilegeListPg.getDisplayCategoriesAndOrders();
		if(actualDisplayCategories.size() != displayCategories.length) {
			throw new ActionFailedException("Display Category Filter works wrongly.");
		}
		this.verifyCategoriesIsDisplayedInAscendingOrder(DISPLAYCATEGORIES, actualDisplayCategories);
		logger.info("Display Category Filter really works correctly.");
	}
	
	/**
	 * Verify Display Sub-Category filter works correctly
	 * @param displayCategory
	 * @param displaySubCategories
	 */
	private void verifyDisplaySubCategoryFilter(String displayCategory, String... displaySubCategories) {
		privilegeListPg.selectAllDisplayCategories();
		privilegeListPg.unSelectAllDisplaySubCategories();
		privilegeListPg.selectDisplaySubCategories(displaySubCategories);
		privilegeListPg.clickGo();
		privilegeListPg.waitLoading();
		
		logger.info("Verify whether Display Sub Category Filter works correctly.");
		List<String[]> actualDisplaySubCategories = privilegeListPg.getDisplaySubCategoriesOrders(displayCategory);
		if(actualDisplaySubCategories.size() != displaySubCategories.length) {
			throw new ActionFailedException("Display Sub Category Filter works wrongly.");
		}
		this.verifyCategoriesIsDisplayedInAscendingOrder(DISPLAYSUBCATEGORIES, actualDisplaySubCategories);
		logger.info("Display Sub Category Filter really works correctly.");
	}
	
	/**
	 * Verify Status filter works correctly
	 * @param expectedStatus
	 */
	private void verifyStatusFilter(String expectedStatus) {
		logger.info("Verify whether Status Filter works correctly.");
		privilegeListPg.selectAllDisplayCategories();
		privilegeListPg.selectAllDisplaySubCategories();
		privilegeListPg.unSelectAllStatuses();
		privilegeListPg.selectStatus(expectedStatus);
		privilegeListPg.clickGo();
		logger.info("Verify Status Filter where the status is selected as '" + expectedStatus + "'.");
		List<String> statuses = privilegeListPg.getPrivilegeColumnValues("Privilege Product Status");
		for(int i = 0; i < statuses.size(); i ++) {
			if(!statuses.get(i).equalsIgnoreCase(expectedStatus)) {
				throw new ActionFailedException("Row " + i + " privilege's status violates the value set in Status Filter - " + expectedStatus);
			}
		}
		logger.info("Status Filter works correctly.");
	}
	
	/**
	 * Verify the Display Categories and Display Sub-Categories are displayed by their corresponding display orders in ascending order in privilege list page
	 * @param map
	 * @param actualCategoriesOrders
	 */
	private void verifyCategoriesIsDisplayedInAscendingOrder(Map<String, Integer> map, List<String[]> actualCategoriesOrders) {
		for(int i = 0; i < actualCategoriesOrders.size(); i ++) {
			if(map.get(actualCategoriesOrders.get(i)[0]) == Integer.parseInt(actualCategoriesOrders.get(i)[1])) {
				logger.info("The Display (Sub) Category's display order is displayed correctly.");
			} else {
				throw new ActionFailedException("The display order of Display (Sub) Category-" + actualCategoriesOrders.get(i)[0] + " is wrong with it in congiration page.");
			}
		}
		
		for(int i = 0; i < actualCategoriesOrders.size() - 1; i ++) {
			if(map.get(actualCategoriesOrders.get(i)[0]) < map.get(actualCategoriesOrders.get(i + 1)[0])) {
				logger.info("The privileges list is really displayed grouped by Display (Sub) Category in ascending order of the Display Category's Display Order.");
			} else {
				throw new ActionFailedException(actualCategoriesOrders.get(i + 1)[0] + " is displayed in wrong order.");
			}
		}
	}
}
