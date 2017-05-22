package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductViewChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case verify the steps to view the Change History of a
 *                   particular Privilege Product.
 * @Preconditions:
 * @SPEC:Use Case Specification: View Privilege Product Info Change History
 * @Task#:AUTO-651
 * 
 * @author szhou
 * @Date Jul 20, 2011
 */
public class ViewPrivilegeChangeHistoryInfo extends LicenseManagerTestCase {
	PagingComponent paging = new PagingComponent();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// privilege preparation:check privilege is existed, if not add a new
		// privilege
		lm.gotoProductSearchListPageFromTopMenu("Privileges");
		if (!lm.checkPrivilegeExisted(privilege.code, schema)) {
			lm.addPrivilegeProduct(privilege);// add a new privilege
		}

		// go to privilege detail page and edit privilege info
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		this.getPrivilegeInfo();// get privilege id
		this.prepareUpdatePrivilegeInfo();
		// edit privilege info
		lm.editPrivilege(privilege);

		// go to change history detail page
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeChangeHistoryDetailPage();

		// verify product Info
		this.verifyProductInfo(privilege.privilegeId);
		// verify sorting by Date & Time
		this.verifySortingByDate();
		// verify log record
		this.verifyRecordInfo(privilege, false);
		// verify end of list of log records
		this.verifyRecordInfo(privilege, true);

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		privilege.code = "s29";
		privilege.name = "ViewPrivilegeChange";
		privilege.customerClasses = new String[] { "Individual" };
		privilege.createLocation = login.location.split("/")[1].trim();

	}

	private void getPrivilegeInfo() {
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();

		privilege.privilegeId = privilegeDetailsPg.getProductID();
		privilege.oldValue = privilegeDetailsPg.getProductName();
		privilege.changeField = "Name";
	}

	private void prepareUpdatePrivilegeInfo() {
		String seq = DataBaseFunctions.getSeqNumber("SEQ_NUM");
		privilege.name = "ViewPrivilegeChange" + seq;
		privilege.changeValue = privilege.name;
		privilege.updateDate = DateFunctions.formatDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)), "EEE MMM dd yyyy");
	}

	private void verifyProductInfo(String id) {
		LicMgrProductViewChangeHistoryPage historyPage = LicMgrProductViewChangeHistoryPage
				.getInstance();

		// Object ID is equal to the Privilege Product ID of the selected
		// Privilege Product
		if (!id.equals(historyPage.getProductID())) {
			throw new ErrorOnPageException("Object ID is not equal to" + id);
		}

		// System retrieves the Log records where the Log Object is
		// "Privilege Product"
		List<String> objects = historyPage.getColumnValue("Object");
		for (int i = 1; i < objects.size(); i++) {
			if (!"Privilege Product".equals(objects.get(i))) {
				throw new ErrorOnPageException(
						"Log Object is not equal to 'Privilege Product'.");
			}
		}

	}

	private void verifySortingByDate() {
		LicMgrProductViewChangeHistoryPage historyPage = LicMgrProductViewChangeHistoryPage
				.getInstance();

		List<String> dates = historyPage.getColumnValue("Date/Time");
		for (int i = 1; i < dates.size(); i++) {
			if (i + 1 < dates.size()
					&& DateFunctions.compareDates(dates.get(i), dates
							.get(i + 1)) == -1) {
				throw new ErrorOnPageException(
						"The Log records found shall be displayed sorted in descending order of the Date & Time.");
			}
		}

	}

	private void verifyRecordInfo(PrivilegeInfo privilege, boolean isLast) {
		LicMgrProductViewChangeHistoryPage historyPage = LicMgrProductViewChangeHistoryPage
				.getInstance();
		if (isLast && paging.lastExists(true)) { //Lesley[20140305] update due to a lot of history record for the privilege and need to go to the last page firstly
			paging.clickLast();
			historyPage.waitLoading();
		} else if (!isLast && paging.previousExists()) {
			paging.clickPrevious();
			historyPage.waitLoading();
		}
		historyPage.compareRecordInfo(privilege, isLast);
	}
}
