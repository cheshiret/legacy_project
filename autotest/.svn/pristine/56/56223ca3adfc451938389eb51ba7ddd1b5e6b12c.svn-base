package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify the privileges are displayed in correct way according to the business rule in Privilege List Page
 * @Preconditions: Case will prepare the needed 4 privileges by itself
 * @SPEC: <<View Privilege Product List.doc>>, <<Privilege Product Management.doc>>
 * @Task#: AUTO-671
 * 
 * @author qchen
 * @Date  Sep 7, 2011
 */
public class View_VerifyBusinessRule extends LicenseManagerTestCase {
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
		System.out.println(DISPLAYSUBCATEGORIES.size());
		
		//2.Pre-condition: check whether the 4 privileges exist in ORMS; if not, adding 4 privilege products
		this.checkAndAddPrivilege(privilege, privilege1, privilege2, privilege3);

		/**
		 * Verify Privilege List Display business rule
		 */
		//3. the list of privileges shall be displayed by the Display Category in ascending order of the Display Category's Display Order
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		List<String[]> actualDisplayCategories = privilegeListPg.getDisplayCategoriesAndOrders();
		this.verifyCategoriesIsDisplayedInAscendingOrder(DISPLAYCATEGORIES, actualDisplayCategories);
		
		//4. the list of privileges shall be displayed by the Display Sub-Category in ascending order of the Display Sub-Category's Display Order
		List<String[]> actualDisplaySubCategories = privilegeListPg.getDisplaySubCategoriesOrders(privilege1.displayCategory);
		this.verifyCategoriesIsDisplayedInAscendingOrder(DISPLAYSUBCATEGORIES, actualDisplaySubCategories);
		
		//5. the list of privileges shall be displayed by the Privilege Product's Display Order in ascending order
		//update privilege display order
		lm.editDisplayOrderForPrivilege(privilege.code, privilege.displayOrder);
		lm.editDisplayOrderForPrivilege(privilege1.code, privilege1.displayOrder);
		lm.editDisplayOrderForPrivilege(privilege2.code, privilege2.displayOrder);
		lm.editDisplayOrderForPrivilege(privilege3.code, privilege3.displayOrder);
		this.verifyPrivilegeIsDiplayedAscending(privilege1, privilege2, privilege3);
		
		//6. the list of privileges shall be displayed by the Privilege Product Code in ascending order
		//update the privilege display order to the same one
		privilege2.displayOrder = privilege1.displayOrder;
		privilege3.displayOrder = privilege1.displayOrder;
		lm.editDisplayOrderForPrivilege(privilege2.code, privilege2.displayOrder);
		lm.editDisplayOrderForPrivilege(privilege3.code, privilege3.displayOrder);
		this.verifyPrivilegeIsDiplayedAscending(privilege3, privilege2, privilege1);
		
		//7. for each privilege product, the system shall display the following info: Privilege code, privilege name, privilege status, Display Category and its corresponding Display Order,
		// Display Sub-Category and its corresponding Display Order and Display Order of the Privilege Product
		this.verifyPrivilegeGridEachColumnDisplaysCorrectly(privilege, privilege1, privilege2, privilege3);
		
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
		LicMgrProductPage productPage = LicMgrProductPage.getInstance();
		
		for(int i = 0; i < privileges.length; i ++) {
			//if privilege exists
			if(lm.checkPrivilegeExisted(privileges[i].code, schema)) {
				lm.gotoPrivilegeSearchListPageFromTopMenu();
				lm.gotoPrivilegeDetailsPageFromProductListPage(privileges[i].code);
				privilegeDetailPg.getPrivilegeInfo(privileges[i]);
				if(privileges[i].status.equalsIgnoreCase("Inactive")) {
					//if privilege's status is 'Inactive', update the status
					privilegeDetailPg.selectPrivilegeStatus("Active");
				}
				privilegeDetailPg.clickOk();
				ajax.waitLoading();
				productPage.waitLoading();
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
	 * Verify the Display Categories and Display Sub-Categories are displayed by their corresponding display orders in ascending order in privilege list page
	 * @param map
	 * @param actualCategoriesOrders
	 */
	private void verifyCategoriesIsDisplayedInAscendingOrder(Map<String, Integer> map, List<String[]> actualCategoriesOrders) {
		//Allow Display Sub-Categories display blank, 
		//if Display Sub-Categories is blank, Display Sub-Categories will be display last in Categories list
		int actualSubCategorySize = actualCategoriesOrders.size() - 1;
		String lastDisplaySubCategory = actualCategoriesOrders.get(actualSubCategorySize)[0];
		//If the last Display Sub-Categories is blank, will be remove
		if(null == lastDisplaySubCategory || lastDisplaySubCategory.length() < 1){
			actualCategoriesOrders.remove(actualSubCategorySize);
		}
		
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
	
	/**
	 * Verify the privileges are displayed with its display order in ascending order
	 * @param privileges
	 */
	private void verifyPrivilegeIsDiplayedAscending(PrivilegeInfo... privileges) {
		for(int i = 0; i < privileges.length - 1; i ++) {
			if(privilegeListPg.getPrivilegeRowNum(privileges[i].code) < privilegeListPg.getPrivilegeRowNum(privileges[i + 1].code)) {
				logger.info("Within each Display Sub-Category grouping, the privileges are really displayed by the Privilege Product's Display Order in ascending order.");
			} else {
				throw new ActionFailedException("Privilege-" + privileges[i].code + " is displayed in wrong order.");
			}
		}
	}
	
	/**
	 * Verify the privileges' each column is displayed correctly
	 * @param privileges
	 */
	private void verifyPrivilegeGridEachColumnDisplaysCorrectly(PrivilegeInfo... privileges) {
		boolean result = true;
		for(PrivilegeInfo privilege:privileges) {
			logger.info("Verify privilege(Code#=" + privilege.code + ") information are displayed correctly.");
			result = true;
			String rowValues[] = privilegeListPg.getPrivilegeRowValues(privilege.code);
			if(!privilege.name.equalsIgnoreCase(rowValues[1])) {
				logger.error("Privilege Product Name is displayed wrongly.");
				result &= false;
			}
			if(!privilege.status.equalsIgnoreCase(rowValues[2])) {
				logger.error("Privilege Product Status is displayed wrongly.");
				result &= false;
			}
			if(Integer.parseInt(privilege.displayOrder) != Integer.parseInt(rowValues[3])) {
				logger.error("Privilege Product Display Order is displayed wrongly.");
				result &= false;
			}
			if(!result) {
				throw new ActionFailedException("The privilege product(Code#= " + privilege.code + ") is displayed wrongly.");
			}
			logger.info("Privilege product information are displayed correctly.");
		}
	}
}
