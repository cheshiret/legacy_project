/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This use case describes the steps taken to view the list of
 *              License Years for a particular Privilege Product.
 * @Preconditions:
 * @SPEC:Use Case Specification:View Privilege License Years
 * @Task#:AUTO-592
 * 
 * @author szhou
 * @Date Jun 10, 2011
 */
public class ViewPrivilegeLicenseYear extends LicenseManagerTestCase {
	private LicenseYear newly_1 = new LicenseYear();
	private LicenseYear newly_2 = new LicenseYear();
	private LicenseYear newly_3 = new LicenseYear();
	private LicenseYear newly_4 = new LicenseYear();
	private LicenseYear searchly = new LicenseYear();
	private String privilegeCode;
	
	private LicMgrPrivilegeLicenseYearPage page;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// goto privilege product license year sub-tab page
		lm.gotoPrivilegeSubTabPage(privilegeCode, "License Year");
		// @Preconditions
		newly_1.id = lm.addLicenseYear(newly_1).id;
		newly_2.id = lm.addLicenseYear(newly_2).id;
		newly_3.id = lm.addLicenseYear(newly_3).id;
		newly_4.id = lm.addLicenseYear(newly_4).id;
		
		newly_1.status="Active";
		newly_2.status="Active";
		newly_3.status="Active";
		newly_4.status="Active";

		// Privilege License Year records with Status of "Active"
		searchly.status = "Active";
		lm.searchPrivilegeLicenseYearByInfo(searchly);
		boolean col = this.verifyColumnValue("Status", searchly.status);
		// Privilege License Year record contains information
		boolean display = this.verifyLicenseYearInformation(newly_1);

		// Privilege License Year records with Status of "Active" and license year
		searchly.licYear = (DateFunctions.getCurrentYear()+2)+"";
		lm.searchPrivilegeLicenseYearByInfo(searchly);
		col &= this.verifyColumnValue("Year", searchly.licYear);
		// Privilege License Year record contains information
		display &= this.verifyLicenseYearInformation(newly_4);

		// Privilege License Year records with Status of "Active" and location class
		searchly.licYear = "";
		searchly.locationClass = "03 - Lakes Offices";
		lm.searchPrivilegeLicenseYearByInfo(searchly);
		col &= this.verifyColumnValue("Location", searchly.locationClass);
		// Privilege License Year record contains information
		display &= this.verifyLicenseYearInformation(newly_3);

		// The list of Privilege License Year records shall be displayed sorted
		searchly.licYear = (DateFunctions.getCurrentYear()+2)+"";
		searchly.locationClass = "";
		lm.searchPrivilegeLicenseYearByInfo(searchly);
		boolean order = verifyLocationClassOrder();

		searchly.licYear = "";
		searchly.locationClass = "All";
		lm.searchPrivilegeLicenseYearByInfo(searchly);
		order &= verifyLicenseYearOrder();

		searchly.licYear = "";
		searchly.locationClass = "";
		lm.searchPrivilegeLicenseYearByInfo(searchly);
		this.inactiveLicenseYear(newly_1.id);
		this.inactiveLicenseYear(newly_2.id);
		this.inactiveLicenseYear(newly_3.id);
		this.inactiveLicenseYear(newly_4.id);
		
		newly_1.status="Inactive";
		newly_2.status="Inactive";
		newly_3.status="Inactive";
		newly_4.status="Inactive";

		searchly.status = "Inactive";
		page = LicMgrPrivilegeLicenseYearPage.getInstance();
		lm.searchPrivilegeLicenseYearByInfo(searchly);
//		col &= this.verifyColumnValue("Status", searchly.status);
		if("Inactive".equalsIgnoreCase(page.getStatusByLicenseYearID(newly_2.id))){
			col &=true;
		}else{
			col &=false;
		}
		// Privilege License Year record contains information
		display &= this.verifyLicenseYearInformation(newly_2);

		if (!col) {
			throw new ActionFailedException("The columun value in the privilege license year page is not correct.");
		}
		if (!display) {
			throw new ActionFailedException("The detail information display on the privilege license year page is not correct.");
		}
		if (!order) {
			throw new ActionFailedException("The order in the privilege license year page is not correct.");
		}

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilegeCode = "NL1";
		newly_1.locationClass = "All";
		newly_1.licYear = (DateFunctions.getCurrentYear()+2)+"";
		newly_1.sellFromDate = DateFunctions.getDateAfterToday(2);
		newly_1.sellFromTime = "12:00";
		newly_1.sellFromAmPm = "AM";
		newly_1.sellToDate = DateFunctions.getDateAfterToday(10);
		newly_1.sellToTime = "12:00";
		newly_1.sellToAmPm = "PM";

		newly_2.locationClass = "All";
		newly_2.licYear = (DateFunctions.getCurrentYear()+3)+"";
		newly_2.sellFromDate = DateFunctions.getDateAfterToday(2);
		newly_2.sellFromTime = "12:00";
		newly_2.sellFromAmPm = "AM";
		newly_2.sellToDate = DateFunctions.getDateAfterToday(10);
		newly_2.sellToTime = "12:00";
		newly_2.sellToAmPm = "PM";

		newly_3.locationClass = "03 - Lakes Offices";
		newly_3.licYear = (DateFunctions.getCurrentYear()+2)+"";
		newly_3.sellFromDate = DateFunctions.getDateAfterToday(2);
		newly_3.sellFromTime = "12:00";
		newly_3.sellFromAmPm = "AM";
		newly_3.sellToDate = DateFunctions.getDateAfterToday(10);
		newly_3.sellToTime = "12:00";
		newly_3.sellToAmPm = "PM";
		newly_3.validFromDate = DateFunctions.getDateAfterToday(2);
		newly_3.validFromTime = "12:00";
		newly_3.validFromAmPm = "AM";
		newly_3.validToDate = DateFunctions.getDateAfterToday(10);
		newly_3.validToTime = "12:00";
		newly_3.validToAmPm = "PM";

		newly_4.locationClass = "04 - Commercial Agent";
		newly_4.licYear = (DateFunctions.getCurrentYear()+2)+"";
		newly_4.sellFromDate = DateFunctions.getDateAfterToday(2);
		newly_4.sellFromTime = "12:00";
		newly_4.sellFromAmPm = "AM";
		newly_4.sellToDate = DateFunctions.getDateAfterToday(10);
		newly_4.sellToTime = "12:00";
		newly_4.sellToAmPm = "PM";
	}

	private void inactiveLicenseYear(String yearId) {
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
				.getInstance();
		LicMgrPrivilegeEditLicenseYearWidget editPg = LicMgrPrivilegeEditLicenseYearWidget
				.getInstance();

		lm.gotoPrivilegeLicenseYearDetailPg(yearId);
		editPg.selectStatus("Inactive");
		editPg.clickOK();
		ajax.waitLoading();
		licenseYearPg.waitLoading();
	}

	private boolean verifyColumnValue(String field, String value) {
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
				.getInstance();
		boolean flag = true;

		List<String> values = null;
		if ("Status".equals(field)) {
			values = licenseYearPg.getColumnValue(1);
		}
		if ("Location".equals(field)) {
			values = licenseYearPg.getColumnValue(2);
		}
		if ("Year".equals(field)) {
			values = licenseYearPg.getColumnValue(3);
		}

		for (int i = 1; i < values.size(); i++) {
			if (!value.equals(values.get(i))) {
				logger.error("Failed to view  License Year Information '"
						+ value + "' on the privilege license year page.");
				flag &= false;
			}
		}

		return flag;
	}

	private boolean verifyLicenseYearOrder() {
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
				.getInstance();

		List<String> values = licenseYearPg.getColumnValue(3);

		for (int i = 1; i < values.size(); i++) {
			if (i + 1 < values.size()
					&& new BigInteger(values.get(i)).compareTo(new BigInteger(
							values.get(i + 1))) != -1) {
				logger
						.error("Failed to view  License Year Order on the privilege license year page.");
				return false;
			}
		}

		return true;
	}

	private boolean verifyLocationClassOrder() {
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
				.getInstance();
		boolean flag = true;
		List<String> values = licenseYearPg.getColumnValue(2);

		for (int i = 1; i < values.size(); i++) {
			if (i + 1 < values.size()
					&& !"All".equals(values.get(i))
					&& !"All".equals(values.get(i + 1))
					&& new BigDecimal(values.get(i).split("-")[0].trim())
							.compareTo(new BigDecimal(values.get(i + 1).split(
									"-")[0].trim())) != -1) {
				logger
						.error("Failed to view  License Year Information on the privilege license year page.");
				flag &= false;
			}
			// if (i + 1 < values.size() && !"All".equals(values.get(i))
			// && "All".equals(values.get(i + 1))) {
			// flag &= true;
			// }
			// if (i + 1 < values.size() && "All".equals(values.get(i))
			// && "All".equals(values.get(i + 1))) {
			// flag &= true;
			// }
			if (i + 1 < values.size() && "All".equals(values.get(i))
					&& !"All".equals(values.get(i + 1))) {
				logger
						.error("Failed to view  Location Class Order on the privilege license year page.");
				flag &= false;
			}
		}

		return flag;
	}

	private boolean verifyLicenseYearInformation(LicenseYear info) {
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();

		logger.info("View License Year list ...");
		if (licenseYearPg.compareLicenseYearRecord(info)) {
			logger.info("View License Year Information on the privilege license year page.");
		} else {
			logger.error("Failed to view '" + info.id + "' License Year Information on the privilege license year page.");
			return false;
		}

		return true;
	}
}
