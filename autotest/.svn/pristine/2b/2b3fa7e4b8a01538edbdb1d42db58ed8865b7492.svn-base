package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee.marina.list;

import java.util.ArrayList;
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
 * 
 * @SPEC:  
 * 		Add Fee Schedule - Transaction Type for List [TC:043393] 
 * 		Add Fee Schedule - Fee for List [TC:043394]  
 * 		Add Fee Schedule - Assignment Selection for List, Product Groups [TC:043592]
 * 		Add Fee Schedule - Assignment Selection for List, Product [TC:043415] 
 * @Task#: Auto-1333
 * 
 * @author Jane
 * @Date  Jan 28, 2013
 */
public class VerifyDefaultValues_UI extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData();
	private FinMgrFeeSchDetailPage feeDetailsPg=FinMgrFeeSchDetailPage.getInstance();
	private List<String> transTypes, prdCategories;
	private String feeValue, prdValue;
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		
		verifyDefaultValuesForTransType();
		verifyDefaultValueForFee();
		verifyDefaultValuesForApplicablePrdCategory();
		verifyDefaultValueForProduct();
		
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		String facilityID = "552903";//Jordan Lake State Rec Area
		String facility = fnm.getFacilityName(facilityID, schema);
		
		schedule.activeStatus = OrmsConstants.NO_STATUS;
		schedule.location = facility;
		schedule.locationCategory = "Park";
		schedule.productCategory = "List";
		schedule.feeType = "Transaction Fee";
		
		transTypes = new ArrayList<String>();
		transTypes.add("Add to Waiting List");
		transTypes.add("Add to Transfer List");
		
		feeValue = "Per Transaction";
		
		prdCategories = new ArrayList<String>();
		prdCategories.add("All");
		prdCategories.add("Slip");
		
		prdValue = "All";
	}
	
	private void verifyDefaultValuesForTransType() {
		logger.info("Verify default values for transaction type on page.");
		List<String> transTypesUI = feeDetailsPg.getTransactionTypeList();
		if(transTypesUI.size()!=transTypes.size())
			throw new ErrorOnPageException("Transaction types on page was wrong.", transTypes, transTypesUI);
		
		for(String transType:transTypesUI) {
			if(!transType.contains(transType))
				throw new ErrorOnPageException("Transaction types on page was wrong.", transTypes, transTypesUI);
		}
		
		logger.info("Verify default values for trans types successfully on list transaction fee details page.");
	}
	
	private void verifyDefaultValueForFee() {
		logger.info("Verify default value for fee was "+feeValue);
		int fees=feeDetailsPg.getTransMethodObjectSize();
		
		if(fees!=1)
			throw new ErrorOnPageException("There should be only one fee type on page.");
		
		String feeValueUI=feeDetailsPg.getTransMethod();
		if(StringUtil.isEmpty(feeValueUI) || !feeValueUI.equals(feeValue))
			throw new ErrorOnPageException("Default value for fee was wrong on page.", feeValue, feeValueUI);
		
		logger.info("Verify defaule value for fee successfully on list transaction fee details page.");
	}
	
	private void verifyDefaultValuesForApplicablePrdCategory() {
		logger.info("Verify default values for applicable product category on page.");
		List<String> prdCategoryUI = feeDetailsPg.getAssignProdCategoryElements();
		if(prdCategoryUI.size()!=prdCategories.size())
			throw new ErrorOnPageException("Applicable Product Category on page was wrong.", prdCategories, prdCategoryUI);
		
		for(String prdCategory:prdCategoryUI) {
			if(!prdCategory.contains(prdCategory))
				throw new ErrorOnPageException("Applicable Product Category was wrong.", prdCategories, prdCategoryUI);
		}
		
		logger.info("Verify Applicable Product Category successfully on list transaction fee details page.");
	}
	 
	private void verifyDefaultValueForProduct() {
		logger.info("Verify default value for product was "+prdValue);
		String prdUI=feeDetailsPg.getAssignProduct();
		
		if(StringUtil.isEmpty(prdUI) || !prdUI.equals(prdValue))
			throw new ErrorOnPageException("Default value for product was wrong on page.", prdValue, prdUI);
		
		logger.info("Verify defaule value for product successfully on list transaction fee details page.");
	}

}
