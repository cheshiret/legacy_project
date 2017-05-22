package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.rafee;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify the default value and options and sort order of drop down lists.
 * 1.Product Category
 * 2.Applicable Product Category
 * 3.Marina Rate Type
 * @Preconditions:
 * @SPEC: The order of each list presented containing the options [TC:042261]
 * @Task#: Auto-1462
 * @author nidng1
 * @Date Feb 20, 2013
 */
public class VerifyDropDownList extends FinanceManagerTestCase{
	private FinMgrRaFeeSchMainPage listPage = FinMgrRaFeeSchMainPage.getInstance();
	private List<String> expectPrdCateList = new ArrayList<String>();
	private List<String> expectAppPrdCateList = new ArrayList<String>();
	private List<String> expectMRateTypeList = new ArrayList<String>();

	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		
		// 1. Product Category
		List<String> prdCategoryList = listPage.getProductCategory();
		this.verifyDropDownList("Product Category", prdCategoryList, expectPrdCateList);
		
		// 2. Applicable Product Category
		List<String> appPrdCategoryList = listPage.getAppProductCategory();
		this.verifyDropDownList("Applicable Product Category", appPrdCategoryList, expectAppPrdCateList);

		
		// 3. Marina Rate Type
		this.selectSlip();
		if(!listPage.isMarinaRateTypeExist()){
			throw new ErrorOnPageException("When product category is Slip, Marin Rate Type drop down list should exist, but now it is not exist.");
		} else {
			List<String> mRateTypeList = listPage.getMarinaRateType();
			this.verifyDropDownList("Marina Rate Type", mRateTypeList, expectMRateTypeList);
		}
		fnm.logoutFinanceManager();
	}
	
	private void selectSlip(){
		listPage.selectProductCategory("Slip");
		ajax.waitLoading();
		listPage.waitLoading();
	}
	
	/**
	 * Verify size, options and sort order of drop down list.
	 */
	private void verifyDropDownList(String listName, List<String> actualList, List<String> expectList){
		if(StringUtil.EMPTY.equals(actualList.get(0))){// if the first option is blank, remove it.
			actualList.remove(0);
		}
		if(MiscFunctions.compareResult("Size of "+listName+" List", expectList.size(), actualList.size())){
			boolean result = true;
			for(int i=0; i<expectList.size(); i++){
				result &= MiscFunctions.compareResult("No. "+(i+1)+listName, expectList.get(i), actualList.get(i));
			}
			if(!result){
				throw new ErrorOnPageException("Options in "+listName+" is not correct. Please check logs above.");
			}
		} else {
			throw new ErrorOnPageException("Size of "+listName+" is not correct. Please check logs above.");
		}
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";

		// Don't change the order.
		expectPrdCateList.add("Activity");
		expectPrdCateList.add("Event");
		expectPrdCateList.add("Facility");
		expectPrdCateList.add("GiftCard");
		expectPrdCateList.add("List");
		expectPrdCateList.add("Lottery");
		expectPrdCateList.add("Membership");
		expectPrdCateList.add("POS");
		expectPrdCateList.add("Permit");
		expectPrdCateList.add("Privilege");
		expectPrdCateList.add("Service");
		expectPrdCateList.add("Site");
		expectPrdCateList.add("Slip");
		expectPrdCateList.add("Supply");
		expectPrdCateList.add("Ticket");
		expectPrdCateList.add("VehicleRTI");

		// Don't change the order.
		expectAppPrdCateList.add("All");
		expectAppPrdCateList.add("POS");
		expectAppPrdCateList.add("Permit");
		expectAppPrdCateList.add("Privilege");
		expectAppPrdCateList.add("Site");
		expectAppPrdCateList.add("Slip");
		expectAppPrdCateList.add("Ticket");

		// Don't change the order.
		expectMRateTypeList.add("All");
		expectMRateTypeList.add("Seasonal");
		expectMRateTypeList.add("Lease");
		expectMRateTypeList.add("Transient");
	}
}
