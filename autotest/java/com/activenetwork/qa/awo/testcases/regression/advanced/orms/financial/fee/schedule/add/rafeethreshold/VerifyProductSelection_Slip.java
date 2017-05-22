
/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafeethreshold;


import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;



/**
 * @Description:1. Goto adding new RA Fee threshold schedule
				2. Go to fee detail page. check default value and sort of Product selection.
 * 
 * @Preconditions: feature 'CreateModifyRAFees' has been assigned to role 'Administrator-Auto'
 * @SPEC:
 * @Task#:AUTO-1334
 * TC042165: Assignment Selection - Product
 * @author pzhu
 * @Date  Nov 26, 2012
 */

public class VerifyProductSelection_Slip extends FinanceManagerTestCase {
	private ScheduleData schedule = new ScheduleData();
	
	private FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
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
		
		fnm.gotoAddRaFeeThresholdPg(schedule);
		//check point 1:
		this.verifyProductSelection();

		fnm.logoutFinanceManager();

	}

	/**
	 * 
	 */
	private void verifyProductSelection() {
		
		String defaultValue = detailPg.getProduct();
		
		if("All".equalsIgnoreCase(defaultValue))
		{
			logger.info("Check default value of product selection passed!!!");
		}else{
			throw new ErrorOnPageException("Check default value of product selection Failed!!!", "All", defaultValue);
		}
		
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

	}

}
