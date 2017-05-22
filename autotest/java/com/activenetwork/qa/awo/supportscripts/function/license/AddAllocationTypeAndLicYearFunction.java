package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.AllocationType;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.AllocationTypeLicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations.LicMgrAddAllocationTypeLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations.LicMgrAddAllocationTypeWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations.LicMgrAllocationTypeLicYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations.LicMgrAllocationTypeListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add Allocation Type and license year function
 * 
 * @author Lesley Wang
 * @Date  Jan 29, 2014
 */
public class AddAllocationTypeAndLicYearFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private String contract, location;
	private boolean loggedin;
	private LicMgrAllocationTypeListPage allocationTypePg = LicMgrAllocationTypeListPage.getInstance();
	private Data<AllocationTypeLicenseYear> allocTypeLY;
	private Data<AllocationType> allocType;
	private int numOfYears = 1;
	private int numOfYearsAfterCurrent = 0;
	
	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedin= false;
		}
		if(login.contract.equals(contract) && loggedin && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedin || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedin= true;
		}
		contract = login.contract;
		location = login.location;
		
		lm.gotoAllocationTypeListPg();
		String type = AllocationType.AllocationType.getStrValue(allocType);
		if (!allocationTypePg.isAllocationTypeExist(type)) {
			// Add allocation type
			lm.addAllocationType(allocType);
			newAddValue = "Allocation Type ID: " + this.verifyAllocationType(type) + " ";
		}
		
		// Add Allocation License Year
		lm.gotoAllocationTypeLicYearPg();
		if (LicMgrAllocationTypeLicYearPage.getInstance().isAddAllocTypeLicYearExist()) {
			int baseLicenseYear = Integer.parseInt(AllocationTypeLicenseYear.LicenseYear.getStrValue(allocTypeLY));
			for (int i = numOfYearsAfterCurrent; i < numOfYears+numOfYearsAfterCurrent; i++) {
				AllocationTypeLicenseYear.LicenseYear.setValue(allocTypeLY, String.valueOf(baseLicenseYear + i));
				//Jane[2014-5-15]:For AB contract, Allocation Type has parameter Hunt Location, 
				//We need to setup allocation type license year for different locations, assume that for each license year, we will setup different locations
				String huntLocations = AllocationTypeLicenseYear.location.getStrValue(allocTypeLY);
				String totoalQuotas = AllocationTypeLicenseYear.TotalQuota.getStrValue(allocTypeLY);
				if(huntLocations.contains(",")){
					String[] locations = huntLocations.split(",");
					String[] quotas = totoalQuotas.split(",");
					for(int k=0; k<locations.length; k++) {
						AllocationTypeLicenseYear.location.setValue(allocTypeLY, locations[k]);
						AllocationTypeLicenseYear.TotalQuota.setValue(allocTypeLY, quotas[k]);
						lm.addAllocationTypeLicYear(allocTypeLY);
						newAddValue += "Successfully add allocation type license year=" + this.verifyAllocationTypeLicYear(allocTypeLY);
					}
				}else {
					lm.addAllocationTypeLicYear(allocTypeLY);
					newAddValue += "Successfully add allocation type license year=" + this.verifyAllocationTypeLicYear(allocTypeLY);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		allocType = (Data<AllocationType>)param[1];
		allocTypeLY = (Data<AllocationTypeLicenseYear>)param[2];
		numOfYearsAfterCurrent = (int)param[3];
		numOfYears = (int)param[4];
	}

	private String verifyAllocationType(String allocationType) {
		LicMgrAddAllocationTypeWidget addAllocationTypesWidget = LicMgrAddAllocationTypeWidget.getInstance();
		
		if (addAllocationTypesWidget.exists()) {
			String msg = addAllocationTypesWidget.getErrorMsg();
			addAllocationTypesWidget.clickCancel();
			ajax.waitLoading();
			allocationTypePg.waitLoading();
			throw new ErrorOnPageException("Fail to add allocation type " + allocationType + " due to " + msg);
		}
		
		String id = allocationTypePg.getAllocationTypeID(allocationType);
		logger.info("Successfully add allocation type " + allocationType + " id=" + id);
		return id;
	}
	
	private String verifyAllocationTypeLicYear(Data<AllocationTypeLicenseYear> allocTypeLY) {
		LicMgrAllocationTypeLicYearPage licYearPg = LicMgrAllocationTypeLicYearPage.getInstance();
		LicMgrAddAllocationTypeLicYearWidget addYearPg = LicMgrAddAllocationTypeLicYearWidget.getInstance();
		
		if (addYearPg.exists()) {
			String msg = addYearPg.getErrorMsg();
			addYearPg.clickCancel();
			throw new ErrorOnPageException("Fail to add allocation type license year due to " + msg);
		}
		String id = licYearPg.getAllocationTypeLicYearID(AllocationTypeLicenseYear.AllocationType.getStrValue(allocTypeLY), 
				AllocationTypeLicenseYear.LicenseYear.getStrValue(allocTypeLY));
		logger.info("Successfully add allocation type license year id=" + id);
		return id;
	}
}
