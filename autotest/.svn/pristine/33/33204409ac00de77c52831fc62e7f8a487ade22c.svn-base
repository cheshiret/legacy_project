package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:This case is used to verify add new lottery details UI info
 * @Preconditions: 
 * @SPEC: Adding a New Lottery [TC:043079]
 * @Task#:Auto-1344

 * @author VZhang
 * @Date Dec 24, 2012
 */

public class VeifyAddNewLotteryUI extends InventoryManagerTestCase{
	private LotteryDetailsPage lotteryDetailPg = LotteryDetailsPage.getInstance();

	@Override
	public void execute() {
		// Goto Lottery Details page
		im.loginInventoryManager(login);
		im.gotoAddNewLottery(lottery);
		//verify lottery id, name, coverage info
		this.verifyBasicInfo();
		//verify product category info
		this.verifyProductCategoryInfo();
		
		this.selectProductCategory();
		//verify product group info
		this.verifyProductGroup();
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		lottery.location = "Jones Lake State Park";
		lottery.locationCategory = "Park";
		lottery.productCategory = "Slip";
		lottery.productGroup = "Full attributes";
	}
	
	private void verifyBasicInfo(){
		boolean result = true;
		String actLotteryID = lotteryDetailPg.getLotteryId();
		String actLotteryName = lotteryDetailPg.getLotteryName();
		String actLotteryCoverage = lotteryDetailPg.getLotteryCoverage();
		
		result &= MiscFunctions.compareResult("Lottery ID", "New", actLotteryID);
		result &= MiscFunctions.compareResult("Lottery Name", "", actLotteryName);
		result &= MiscFunctions.compareResult("Lottery Coverage", lottery.location, actLotteryCoverage);
		
		if(!result){
			throw new ErrorOnPageException("Lottery ID or Name Or Coverage info not correct, please check log.");
		}else{
			logger.info("Lottery ID or Name Or Coverage info is correct.");
		}	
	}
	
	private void verifyProductCategoryInfo(){
		String defaultValue = lotteryDetailPg.getProductCategory();
		if(!defaultValue.equals("Permit")){
			throw new ErrorOnPageException("Product Category default value should be Permit.");
		}else logger.info("Product Category default value is Permit.");
		
		List<String> prdCategoryElements = lotteryDetailPg.getProductCategoryElements();
		if(prdCategoryElements.size()!=4){
			throw new ErrorOnPageException("Product Category elements not correct.");
		}
		
		if(!prdCategoryElements.contains("Permit")){
			throw new ErrorOnPageException("Product Category elements should contain Permit.");
		}else logger.info("Product Category elements contain Permit.");
		
		if(!prdCategoryElements.contains("Site")){
			throw new ErrorOnPageException("Product Category elements should contain Site.");
		}else logger.info("Product Category elements contain Site.");
		
		if(!prdCategoryElements.contains("Ticket")){
			throw new ErrorOnPageException("Product Category elements should contain Ticket.");
		}else logger.info("Product Category elements contain Ticket.");
		
		if(!prdCategoryElements.contains("Slip")){
			throw new ErrorOnPageException("Product Category elements should contain Slip.");
		}else logger.info("Product Category elements contain Slip.");
		
	}
	
	private void selectProductCategory(){
		lotteryDetailPg.selectProCategory(lottery.productCategory);
	}
	
	private void verifyProductGroup(){
		//verify product group default value should be All
		String defaultValue = lotteryDetailPg.getProductGroups().get(0);
		if(!defaultValue.equals("All")){
			throw new ErrorOnPageException("Product Group default value should be All, But actullay is " + defaultValue);
		}else logger.info("Product Group default value is All.");
		//when product group is All, the Add button should be disabled
		this.verifyAddPrdGroupIsDisable(true);
		
		//select product group, not All
		lotteryDetailPg.selectProGroups(lottery.productGroup);
		lotteryDetailPg.waitLoading();
		//All is not the applicable product group, the Add button should enabled
		this.verifyAddPrdGroupIsDisable(false);
		
		//after click Add new Product group, could add new product group
		int prdGroupDropdownListObjLengthOrg = lotteryDetailPg.getPrdGroupDropDownListObjectLength();
		int prdGroupRemoveObjLengthOrg = lotteryDetailPg.getPrdGroupRemoveObjectLength();
		lotteryDetailPg.clickAddNewPrdGroups();
		int prdGroupDropdownListObjLengthPre = lotteryDetailPg.getPrdGroupDropDownListObjectLength();
		int prdGroupRemoveObjLengthPrd = lotteryDetailPg.getPrdGroupRemoveObjectLength();
		this.comparePrdGroupObjLength(prdGroupDropdownListObjLengthPre, prdGroupDropdownListObjLengthOrg+1);
		this.comparePrdGroupObjLength(prdGroupRemoveObjLengthPrd, prdGroupRemoveObjLengthOrg+1);
		
		//could click remove button, remove product group
		lotteryDetailPg.clickRemovePrdGroups(0);
		prdGroupDropdownListObjLengthPre = lotteryDetailPg.getPrdGroupDropDownListObjectLength();
		prdGroupRemoveObjLengthPrd = lotteryDetailPg.getPrdGroupRemoveObjectLength();
		this.comparePrdGroupObjLength(prdGroupDropdownListObjLengthPre, prdGroupDropdownListObjLengthOrg);
		this.comparePrdGroupObjLength(prdGroupRemoveObjLengthPrd, prdGroupRemoveObjLengthOrg);
		
		//new product group is All, system will remove specific product group, and keeping All as option
		lotteryDetailPg.clickAddNewPrdGroups();
		lotteryDetailPg.selectPrdGroups("All",1);
		lotteryDetailPg.waitLoading();
		prdGroupDropdownListObjLengthPre = lotteryDetailPg.getPrdGroupDropDownListObjectLength();
		prdGroupRemoveObjLengthPrd = lotteryDetailPg.getPrdGroupRemoveObjectLength();
		this.comparePrdGroupObjLength(prdGroupDropdownListObjLengthPre, prdGroupDropdownListObjLengthOrg);
		this.comparePrdGroupObjLength(prdGroupRemoveObjLengthPrd, prdGroupRemoveObjLengthOrg);		
		this.verifyAddPrdGroupIsDisable(true);
	}
	
	private void verifyAddPrdGroupIsDisable(boolean expDisable){
		boolean actDisabled = lotteryDetailPg.checkAddPrdGroupsIsDisabled();
		boolean result = MiscFunctions.compareResult("Add Product Group is Disabled", expDisable, actDisabled);
		if(!result){
			throw new ErrorOnPageException("Product Goup Add button disabled info is not correct.");
		}else logger.info("Product Goup Add button disabled info is correct.");
	}
	
	private void comparePrdGroupObjLength(int exp, int act){
		if(exp != act){
			throw new ErrorOnPageException("The product group object length not correct. " +
					"expect length is " + exp + ", but actullay length is " + act);
		}else logger.info("The product group object length is correct.");
	}

}
