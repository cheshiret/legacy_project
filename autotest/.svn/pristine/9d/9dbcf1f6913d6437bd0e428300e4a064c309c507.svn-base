/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafeethreshold;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * @Description:1. Goto adding new RA Fee threshold schedule
 * 				2. check product category selection before adding
 * 				3. check product category selection on the detail page.
 * 				4. after added, check status should be inactive.
 * 
 * @Preconditions: feature 'CreateModifyRAFees' has been assigned to role 'Administrator-Auto'
 * @SPEC:
 * @Task#:AUTO-1334
 * TC043676/TC042098: Product Category Selection 
 * @author pzhu
 * @Date  Nov 26, 2012
 */

public class VerifyProductCategorySelection_Slip extends FinanceManagerTestCase {
	private ScheduleData schedule = new ScheduleData();
	
	private FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
	.getInstance();
	private FinMgrRaFeeThresholdsSearchPage searchPg = FinMgrRaFeeThresholdsSearchPage
	.getInstance();
	private FinMgrFeeFindLocationPage findLocationPg = FinMgrFeeFindLocationPage
	.getInstance();
	
	private String[][] features ={
			{"CreateModifyRAFees",	"Create/Modify RA Fees"},
			};


	@Override
	public void execute() {
		
		fnm.checkRolesFeatures("Administrator", this.features, FINANCE_MANAGER, schema);
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeThresholdPage();
		
		this.gotoSelectProductCategory();
		//check point 1:
		this.verifyProductCategorySelection();
		
		
		this.gotoAddThresholdFeeDetailPg();
		//check point 2:
		this.verifyProductCategoryOnDetailPg();
		
		schedule.scheduleId = this.addNewRaFeeThresholdSchedule(schedule);
		//check point 3:
		this.VerifyNewFeeStatus();

		fnm.logoutFinanceManager();

	}

	/**
	 * 
	 */
	private void VerifyNewFeeStatus() {
		if(schedule.scheduleId.matches("\\d+"))
		{
			searchPg.searchBySheduleId(schedule.scheduleId);
			//new fee should be inactive status.
			if(!searchPg.isFeeScheduleActive(schedule.scheduleId))
			{
				logger.info("Check new fee status Passed...");
			}else{
				throw new ErrorOnPageException("Check status of new fee failed....", "inactive", "active");
			}
		}else{
			throw new ErrorOnPageException("Add new fee failed....");
		}
		
	}

	private String addNewRaFeeThresholdSchedule(ScheduleData schedule) {
		
		detailPg.enterAllRAFeeThresholdSched(schedule);
		detailPg.clickApply();
		ajax.waitLoading();
		detailPg.waitLoading();
		
		String scheduleMess = "";
		if(detailPg.checkErrorMessage()){
			scheduleMess=detailPg.getErrorMessage();
			detailPg.clickCancel();
		}else{
			scheduleMess=detailPg.getScheduleId();
			detailPg.clickOk();
		}
		
		searchPg.waitLoading();
		return scheduleMess;
		
	}

	/**
	 * 
	 */
	private void verifyProductCategoryOnDetailPg() {
		List<String> elements = detailPg.getProdCategoryElements();
				
		String selected = detailPg.getProdCategory();
		
		if((selected.equalsIgnoreCase("Slip"))&&(elements.size()==1)&&(elements.get(0).equalsIgnoreCase("Slip")))
		{
			logger.info("Check Product Category selection on detail page passed!!!");
			
		}else{
			throw new ErrorOnPageException("Error when checking Product catetory selection on detail page.....");
		}
		
	}

	/**
	 * 
	 */
	private void gotoAddThresholdFeeDetailPg() {
		
		logger.info("Go to detail page of adding New RA Fee Threshold Schedule.");

		detailPg.selectProdCategory(schedule.productCategory);
		detailPg.waitLoading();		
	}

	/**
	 * 
	 */
	private void verifyProductCategorySelection() {
		
		List<String> elements = detailPg.getProdCategoryElements();
		String[] arrayCode = (String[])elements.toArray(new String[elements.size()]);
				
		boolean result = true;
		
		result &= (!Arrays.toString(arrayCode).toUpperCase().contains("all".toUpperCase()));
		result &= Arrays.toString(arrayCode).toUpperCase().contains("ticket".toUpperCase());
		result &= Arrays.toString(arrayCode).toUpperCase().contains("slip".toUpperCase());
		result &= Arrays.toString(arrayCode).toUpperCase().contains("permit".toUpperCase());
		
		if(!result)
		{
			throw new ErrorOnPageException("Error when checking Product catetory selection, elements on page is "+arrayCode.toString());
		}else{
			logger.info("Check Product Category selection passed!!!");
		}
		
	}

		

	private void gotoSelectProductCategory() {
		

		logger.info("Go to select product category for adding New RA Fee Threshold Schedule.");

		searchPg.clickAddNew();
		findLocationPg.waitLoading();
		findLocationPg.searchByLocationName(schedule.location,
				schedule.locationCategory);
		findLocationPg.selectLocation(schedule.location);
		
		detailPg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NC";
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/"+fnm.getFacilityName("1", schema);//North Carolina State Parks
		

		// initialize schedule data
		schedule.location = fnm.getFacilityName("552818", schema);//Mayo River State Park
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";
	  	schedule.effectiveDate = DateFunctions.getToday();
	  	schedule.startCounter = "0";
	  	schedule.otherRanges = new ArrayList<String>();


	}

}
