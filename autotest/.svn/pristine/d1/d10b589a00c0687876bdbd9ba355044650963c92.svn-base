
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store.assignment;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct.SalesFlowDisplaySetting;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteriesProductListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrProductInactiveAssignmentsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:1. Check lottery product exist or not. If not, create new. 
 * 				2. assign lottery product to location class "06-State Parks Agent";
 * 				3. get all assignments of lottery product for Store "WAL-MART". Check lottery product "1ST" is in the list.
 * 				4. unassign product "1ST" from "WAL-MART".
 * 				5. get assignment from inactive assignments list. check previous assignment is in the inactive assignments list. 
 * @Preconditions:
 * 				1. DB record for lottery(addAlgorithmForHFLottery.sql):
 * 						INSERT INTO P_LOTTERY_PRV_ALGO_CFG (ID, ALGO_ID, ALGO_TYPE_ID, MIN_RANDOM_RANGE, DISPLAY_ORDER, ACTIVE_IND, DELETED_IND) values(get_sequence('P_LOTTERY_PRV_ALGO_CFG'), 1, 1, 999999999,1,1,0)
 * 				2. Features of creating lottery product for role 'HF Administrator'.(D_ASSIGN_FEATURE: ID=3020)
 * @SPEC:Store Set Up changes for H&F big game [TC:046733]
 * @Task#:AUTO-1244(303feature)
 * 
 * @author pzhu
 * @Date  Nov 2, 2012
 */

public class ViewLotteryProductAssignment extends LicenseManagerTestCase{
	
	private String[] locationClass;
	private String storeName;
	private String vendorName;
	private String prdType;
	private String assignmentID;

	private String[][] features ={
			{"CreateModifyPrivilegeLotteryProduct",	"Create Modify Privilege Lottery Product"},
			}; 
	
	@Override
	public void execute() {
		//Check roles/features.
		lm.checkRolesFeatures("HF Administrator", this.features, LICENSE_MANAGER, schema);
		
		lm.loginLicenseManager(login);
		
		// Lottery product preparation:check active whether lottery product existed or not, if not add a new.
		lm.gotoLotteriesProductListPgFromTopMenu();
		
		boolean isExist = this.checkLotteryProductExist(lotteryPrd.getCode());

		if(!isExist){
			logger.warn("Cannot find lottery product, adding.....");
			lm.addLotteryProduct(lotteryPrd);
		}else{
			logger.info("Found lottery product, continue...");
		}
		
		//Step 1: assign lottery product to location class "06-State Parks Agent";
		lm.gotoLotteryProductDetailsPageFromProductListPage(lotteryPrd.getCode());
		lm.assignLotteryProductToStoresThruLocationClass(locationClass);
		lm.gotoStoreProductAssignmentPage(storeName, vendorName);
		
		//Step 2: get all assignments of lottery product for Store "WAL-MART"
		List<ProductStoreAssignment> lotteryRecords = lm.getStoreProductAssignmentList(this.prdType, true);
		
		//Check point 1: check lottery product "1ST" is in the list.
		this.checkAssignedLotteryProducts(lotteryRecords);
		
		
		//Step 3: unassign product "1ST" from "WAL-MART"
		this.unassignPrdFromStore(lotteryPrd.getCode());
		
		//Step 4: get assignment from inactive assignments list. 
		ProductStoreAssignment inactiveRecord = this.getInactiveAssignment(this.assignmentID, this.prdType);
		
		//Check point 2: check previous assignment is in the inactive assignments list.
		this.checkInactiveAssignmentRecord(inactiveRecord);
		
		
		lm.logOutLicenseManager();				
	}

	
	/**
	 * @param code
	 */
	private boolean checkLotteryProductExist(String code) {
		LicMgrLotteriesProductListPage lotteriesProductListPg = LicMgrLotteriesProductListPage.getInstance();
		
		return lotteriesProductListPg.checkLotteryProduct(lotteryPrd.getCode());
		
	}


	/**
	 * @param inactiveRecord
	 */
	private void checkInactiveAssignmentRecord(
			ProductStoreAssignment inactiveRecord) {
		boolean result = true;
		result &= inactiveRecord.assignID.equalsIgnoreCase(this.assignmentID);
		result &= inactiveRecord.productCode.equalsIgnoreCase(this.lotteryPrd.getCode());
		result &= inactiveRecord.productName.equalsIgnoreCase(this.lotteryPrd.getDescription());
		result &= inactiveRecord.assignStatus.equalsIgnoreCase(INACTIVE_STATUS);
		
		if(!result)
		{
			throw new ErrorOnPageException("Check inactive assignment record failed...Please check manually, assignment ID is "+this.assignmentID, this.assignmentID, StringUtil.ObjToString(inactiveRecord));
		}else{
			logger.info("Check inactive assignment record Passed!!!");
		}
	
	}


	private ProductStoreAssignment getInactiveAssignment(String id, String prdType) {
		LicMgrStoreProductAssignmentsPage storeAssignPage=LicMgrStoreProductAssignmentsPage.getInstance();
		LicMgrProductInactiveAssignmentsWidget pg = LicMgrProductInactiveAssignmentsWidget.getInstance();
		
		logger.info("Getting inactive assignment id of -->"+id);
		
		storeAssignPage.clickViewInactiveAssignments();
		ajax.waitLoading();
		ProductStoreAssignment  record= pg.getProductInactiveAssignmentInfo(id, prdType);
		
		pg.clickOK();
		ajax.waitLoading();
		storeAssignPage.waitLoading();
		
		return record;
		
		
	}

	/**
	 * @param code
	 */
	private void unassignPrdFromStore(String code) {
		LicMgrStoreProductAssignmentsPage assignPg = LicMgrStoreProductAssignmentsPage.getInstance();
		logger.info("unassgin lottery product-->"+code+" from store-->"+this.storeName);
		assignPg.assginAndUnassignProduct(code, false);
		
	}

	/**
	 * @param lotteryRecords
	 */
	private void checkAssignedLotteryProducts(
			List<ProductStoreAssignment> lotteryRecords) {
		
		boolean found = true;
		boolean result = false;
		for (ProductStoreAssignment record: lotteryRecords)
		{
			if(record.productCode.equalsIgnoreCase(this.lotteryPrd.getCode()))
			{
				found &= record.displayCategory.equalsIgnoreCase(this.lotteryPrd.getDisplayCategory());
				found &= record.displaySubCategory.equalsIgnoreCase(this.lotteryPrd.getDisplaySubCategory());
				found &= record.assignStatus.equalsIgnoreCase(YES_STATUS);
				
				if(found)
				{
					this.assignmentID = record.assignID;
					result = true;
					break;
				}else{
					throw new ErrorOnPageException("Check lottery product assignment failed...", StringUtil.ObjToString(this.lotteryPrd), StringUtil.ObjToString(record));
				}
			}
		}
		
		if(!result)
		{
			throw new ErrorOnPageException("Cannot find assignment record of which the prd code is -->"+this.lotteryPrd.getCode());
		}else{
			logger.info("Check lottery product assignemt Passed!!!");
		}
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		lotteryPrd.setCode("1ST");
		lotteryPrd.setDescription("viewStoreAssignment");
		
		lotteryPrd.setSpecies(new String[]{"Ducks","Pet"});
		lotteryPrd.setCustomerClass(new String[]{"Individual"});
		lotteryPrd.setMinChoices("5");
		lotteryPrd.setMaxChoices("10");
		lotteryPrd.setAlgorithm("Draw Lottery");
		lotteryPrd.setDisplayCategory("Hunting");
		lotteryPrd.setDisplaySubCategory("Annual");
		lotteryPrd.setReportCategory("Resident Licenses");
		List<SalesFlowDisplaySetting> settings = new ArrayList<SalesFlowDisplaySetting>();
		settings.add(new SalesFlowDisplaySetting("Weapon", "1"));
		lotteryPrd.setDisplaySetting(settings);
		
		this.locationClass = new String[]{"06-State Parks Agent"};
		this.storeName = "WAL-MART";
		this.vendorName = "Auto Vendor";
		this.prdType = "Lottery";

	}
	

}
