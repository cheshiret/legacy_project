package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.OutfitterAllocation;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations.LicMgrAddOutfitterAllocationsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations.LicMgrOutfitterAllocationsListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add Outfitter Allocations function
 * 
 * @author Lesley Wang
 * @Date  Sep 26, 2013
 */
public class AddOutfitterAllocationsFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private Data<OutfitterAllocation> outfitterAlloc;
	private String contract, location;
	private boolean loggedin;
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
		
		// Add outfitter allocations
		lm.gotoAllocationTypeListPg();
		lm.gotoOutfitterAllocationsListPg();
		int baseLicenseYear = Integer.parseInt(OutfitterAllocation.LicenseYear.getStrValue(outfitterAlloc));
		for (int i = numOfYearsAfterCurrent; i < numOfYears+numOfYearsAfterCurrent; i++) {
			OutfitterAllocation.LicenseYear.setValue(outfitterAlloc, String.valueOf(baseLicenseYear + i));
			//Jane[2014-5-15]:For AB contract, Allocation Type has parameter Hunt Location, 
			//We need to add outfitter allocation for different locations, assume that for each license year, we will setup different locations
			String huntLocations = OutfitterAllocation.Location.getStrValue(outfitterAlloc);
			String[] qtyArr = (String[])outfitterAlloc.get(OutfitterAllocation.Quantities);
			if(huntLocations.contains(",")){
				String[] locations = huntLocations.split(",");
				String[] qtys = qtyArr[i].split(",");
				for(int k=0; k<locations.length; k++) {
					OutfitterAllocation.Location.setValue(outfitterAlloc, locations[k]);
					OutfitterAllocation.Quantities.setValue(outfitterAlloc, new String[]{qtys[k]});
					lm.addOutfitterAllocations(outfitterAlloc);
					newAddValue += this.verifyOutfitterAllocations(OutfitterAllocation.AllocationType.getStrValue(outfitterAlloc), OutfitterAllocation.LicenseYear.getStrValue(outfitterAlloc));
				}
			}else {
				lm.addOutfitterAllocations(outfitterAlloc);
				newAddValue += this.verifyOutfitterAllocations(OutfitterAllocation.AllocationType.getStrValue(outfitterAlloc), OutfitterAllocation.LicenseYear.getStrValue(outfitterAlloc));
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
		
		outfitterAlloc = (Data<OutfitterAllocation>)param[1];
		numOfYearsAfterCurrent = (int)param[2];
		numOfYears = (int)param[3];
	}
	
	private String verifyOutfitterAllocations(String type, String year) {
		LicMgrAddOutfitterAllocationsWidget addWidget = LicMgrAddOutfitterAllocationsWidget.getInstance();
		LicMgrOutfitterAllocationsListPage outfitterAllocationsPg = LicMgrOutfitterAllocationsListPage.getInstance();
		
		if (addWidget.exists()) {
			String msg = addWidget.getErrorMsg();
			addWidget.clickCancel();
			ajax.waitLoading();
			outfitterAllocationsPg.waitLoading();
			throw new ErrorOnPageException("Fail to add outfitter allocations for " + type + " " + year + " due to " + msg);
		}
		return "Successfully add outfitter allocations for " + type + " " + year;
	}
}
