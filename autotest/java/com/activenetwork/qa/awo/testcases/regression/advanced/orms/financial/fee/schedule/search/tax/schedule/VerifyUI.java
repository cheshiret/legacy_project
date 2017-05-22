package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.tax.schedule;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * 1.Check the drop down list of Product Category.
 * 2.Select Slip as Product Category and check the drop down list of Marina Rate Type.
 * @Preconditions:
 * @SPEC: Search Tax Schedule - UI Requirement [TC:049971]
 * @Task#: Auto-1442
 * 
 * @author nding1
 * @Date  Feb 6 2013
 */
public class VerifyUI extends FinanceManagerTestCase {
	private FinMgrTaxSchedulePage schedulePg = FinMgrTaxSchedulePage.getInstance();
	private List<String> expectPrdCateList = new ArrayList<String>();
	private List<String> expectMRateTypeList = new ArrayList<String>();

	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoTaxMainPage();
		fnm.gotoTaxSchedulePage();
		
		// 1.Check the drop down list of Product Category.
		List<String> prdCategoryList = schedulePg.getProductCategory();
		this.verifyOptions("Product Category", expectPrdCateList, prdCategoryList);
		
		// 2.Select Slip as Product Category and check the drop down list of Marina Rate Type.
		this.selectSlip();
		
		// Marina Rate Type should exist, and contains "All", "Lease", "Seasonal" and "Transient"
		// (alpha-numeric sorting in ascending order)
		if(!schedulePg.isMarinaRateTypeExist()){
			throw new ErrorOnPageException("When product category is Slip, Marin Rate Type drop down list should exist.");
		} else {
			List<String> mRateTypeList = schedulePg.getMarinaRateType();
			this.verifyOptions("Marina Rate Type", expectMRateTypeList, mRateTypeList);
		}
	}
	
	private void verifyOptions(String field, List<String> expectList, List<String> actualList){
		if(StringUtil.EMPTY.equals(actualList.get(0))){// if the first option is blank, remove it.
			actualList.remove(0);
		}
		if(MiscFunctions.compareResult("Size of List", expectList.size(), actualList.size())){
			boolean result = true;
			for(int i=0; i<expectList.size(); i++){
				result = MiscFunctions.compareResult("No. "+(i+1)+field, expectList.get(i), actualList.get(i));
			}
			if(!result){
				throw new ErrorOnPageException("Please check logs above.");
			}
		} else {
			throw new ErrorOnPageException("Size of "+field+" is not correct, please check log above.");
		}
	}

	private void selectSlip(){
		logger.info("Select Slip as Product Category");
		schedulePg.selectProductCategory("Slip");
		ajax.waitLoading();
		schedulePg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";

		expectPrdCateList.add("Activity");
		expectPrdCateList.add("Event");
		expectPrdCateList.add("Facility");
		expectPrdCateList.add("List");
		expectPrdCateList.add("POS");
		expectPrdCateList.add("Permit");
		expectPrdCateList.add("Site");
		expectPrdCateList.add("Slip");
		
		// Don't change the order.
		expectMRateTypeList.add("All");
		expectMRateTypeList.add("Lease");
		expectMRateTypeList.add("Seasonal");
		expectMRateTypeList.add("Transient");
	}
}
