package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee.marina.list;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: 
 * @Preconditions: 
 * 		Create a list in Inventory Manager
 * @SPEC:  
 * 		Add Fee Schedule - UI Requirements for Assignment Selection for List [TC:043378] 
 * 
 * @Task#: Auto-1333
 * 
 * @author Jane
 * @Date  Jan 28, 2013
 */
public class VerifyAssignSelectionUI extends FinanceManagerTestCase{
	private FeeScheduleData schedule = new FeeScheduleData();
	private String facilityID, list, prdCategory;
	private FinMgrFeeSchDetailPage detailsPage = FinMgrFeeSchDetailPage.getInstance();
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoFeeScheduleDetailPageByAddNew(schedule);
		
		selectProductAndVerifyProdCategory(list);
		selectAllAndVerifyProdList();
		selectProductAndVerifyProdCategory(list);
		
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		facilityID = "552903";//Jordan Lake State Rec Area
		String facility = fnm.getFacilityName(facilityID, schema);
		
		schedule.activeStatus = OrmsConstants.NO_STATUS;
		schedule.location = facility;
		schedule.locationCategory = "Park";
		schedule.productCategory = "List";
		schedule.feeType = "Transaction Fee";
		
		list = "ListForAddFeeSche";
		prdCategory="Slip";
	}
	
	private void selectProductAndVerifyProdCategory(String list) {
		logger.info("Select list "+list+" for new list transaction fee.");
		
		detailsPage.selectAssignProduct(list);
		detailsPage.waitLoading();
		String prdCategoryUI=detailsPage.getAssignProdCategory();
		
		if(StringUtil.isEmpty(prdCategoryUI) || !prdCategoryUI.equals(prdCategory))
			throw new ErrorOnPageException("Product category was not correctly after select product.", prdCategory, prdCategoryUI);
		logger.info("Verify product category was correct after select product.");
	}
	
	private void selectAllAndVerifyProdList() {
		logger.info("Select product category as All for new list transaction fee.");
		
		detailsPage.selectAssignProdCategory("All");
		detailsPage.waitLoading();
		List<String> listUI=detailsPage.getAssignProductElements();
		
		List<String> lists=fnm.getListsByParkID(schema, facilityID);
		if(listUI.size()-1!=lists.size())
			throw new ErrorOnPageException("Product list belongs to facility "+facilityID+" displayed not correctly.", lists, listUI);
		
		for(int i=1;i<listUI.size();i++) {
			String list=listUI.get(i);
			if(!lists.contains(list))
				throw new ErrorOnPageException("Product list belongs to facility "+facilityID+" displayed not correctly.", lists, listUI);
		}
		
		logger.info("Product list belongs to facility "+facilityID+" displayed correctly after select dock as All.");
	}
}
