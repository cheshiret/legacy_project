package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.uesfee.marina;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: 
 * @Preconditions: 
 * @SPEC:  Add Fee Schedule - UI Requirements for Assignment Selection for Slip [TC:047801] 
 * @Task#: Auto-1333
 * 
 * @author Jane
 * @Date  Jan 25, 2013
 */
public class VerifyAssignSelectionUI extends FinanceManagerTestCase{
	private FeeScheduleData schedule = new FeeScheduleData();
	private String facilityID, dock, slip;
	private FinMgrFeeSchDetailPage detailsPage = FinMgrFeeSchDetailPage.getInstance();
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoFeeScheduleDetailPageByAddNew(schedule);
		
		selectDockAndVerifyProdList(dock);
		selectAllAndVerifyProdList();
		selectPrdAndVerifyDockList(slip);
		
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		facilityID = "552818";//Mayo River State Park
		
		schedule.location = fnm.getFacilityName(facilityID, schema);
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		
		dock="DockForSlipUseFee";
		slip="SUS-Slip Use Fee Status";
	}
	
	private void selectDockAndVerifyProdList(String dock) {
		logger.info("Select dock "+dock+" for new slip use fee.");
		
		detailsPage.selectDock(dock);
		detailsPage.waitLoading();
		List<String> slipsUI=detailsPage.getAssignProductElements();
		
		List<String> slips=fnm.getSlipsByParkIDAndDockName(schema, facilityID, dock);
		if(slipsUI.size()-1!=slips.size())
			throw new ErrorOnPageException("Product slip belongs to dock "+dock+" displayed not correctly.", slips, slipsUI);
		
		for(int i=1;i<slipsUI.size();i++) {
			String slip=slipsUI.get(i).split("-")[1].trim();
			if(!slips.contains(slip))
				throw new ErrorOnPageException("Product slip belongs to dock "+dock+" displayed not correctly.", slips, slipsUI);
		}
		
		logger.info("Product slip belongs to dock "+dock+" displayed correctly after select dock.");
	}
	
	private void selectAllAndVerifyProdList() {
		logger.info("Select dock as All for new slip use fee.");
		
		detailsPage.selectDock("All");
		detailsPage.waitLoading();
		List<String> slipsUI=detailsPage.getAssignProductElements();
		
		List<String> slips=fnm.getSlipsNotContainNSSCByParkID(schema, facilityID);
		if(slipsUI.size()-1!=slips.size())
			throw new ErrorOnPageException("Product slip belongs to facility "+facilityID+" displayed not correctly.", slips, slipsUI);
		for(int i=1;i<slipsUI.size();i++) {
			String slip=slipsUI.get(i).split("-")[1].trim();
			if(!slips.contains(slip))
				throw new ErrorOnPageException("Product slip belongs to facility "+facilityID+" displayed not correctly.", slips, slipsUI);
		}
		
		logger.info("Product slip belongs to facility "+facilityID+" displayed correctly after select dock as All.");
	}

	private void selectPrdAndVerifyDockList(String slip) {
		logger.info("Select slip "+slip+" for new slip use fee.");
		
		detailsPage.selectAssignProduct(slip);
		detailsPage.waitLoading();
		String dockUI=detailsPage.getAssignLoop();
		if(!dockUI.equals(dock))
			throw new ErrorOnPageException("Slip's dock displayed uncorrectly.", dock, dockUI);
		
		logger.info("Slip's dock displayed correctly after select slip as"+slip);
	}
}
