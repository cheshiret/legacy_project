/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case contains the scenario:
 *              1. verify select creation of the RA fee schedule 
 *             
 * @Preconditions:
 * @SPEC:Add RA Fee Schedule.UCS
 * @Task#:AUTO-761
 * 
 * @author szhou
 * @Date  Dec 6, 2011
 */
public class VerifyRAFeeSchdSelectCreationForService extends FinanceManagerTestCase{

	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private FinMgrRaFeeSchDetailPage detailPg = FinMgrRaFeeSchDetailPage.getInstance();
	private List<String> transType=new ArrayList<String>();
	
	@Override
	public void execute() {
		//login finance manager
	    fnm.loginFinanceManager(login);
	    fnm.gotoFeeMainPage();
	    fnm.gotoRaFeeSchedulePage();
	    
	    //go to RA fee schedule detail page
	    fnm.gotoRAFeeScheduleDetailPgByAddNew(schedule.location,schedule.locationCategory);
	    detailPg.selectPrdCategory(schedule.productCategory);
		detailPg.waitLoading();
		
		//verify RA Fee Schedule Creation
	    this.verifyRAFeeScheduleCreationForService();
	    
	    //logout finance manager
	    fnm.logoutFinanceManager();
	}
	
	private void verifyRAFeeScheduleCreationForService(){
		FinMgrRaFeeSchMainPage schMainPg = FinMgrRaFeeSchMainPage.getInstance();
		
		logger.info("Verify RA fee schedule creation for Service.");
		// verify product,product group
		this.verifyProduct();
		// verify transaction type
		this.verifyTransType(transType);
		
		detailPg.clickCancel();
	    schMainPg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
	  	login.contract = "MS Contract";
	  	login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
	  	schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
	  	
	  	 //initialize schedule data
	  	schedule.locationCategory = "Contract";
	  	schedule.location = "Mississippi Department of Wildlife, Fisheries, and Parks";
	  	schedule.productCategory="Service";
	  	
	  	transType.add("Apply Convenience Fee");
		
	}
	
	private void verifyProduct(){
		List<String> productGrp=detailPg.getAllProductGroup();
		List<String> product=detailPg.getAllProduct();
		List<String[]> productGrpDB=fnm.getProductGroupNameUsingFeeSchdDetailPgFromDB(schema, "12", "");
		List<String[]> productDB=fnm.getProductNameUsingFeeSchdDetailPgFromDB(schema, "12", "", "", "");
		
		if(productGrp==null||(productGrp.size()-1)!=productGrpDB.size()){
			throw new ErrorOnDataException("Product group size is not correct.");
		}
		if(!productGrp.contains("All") ||! "All".equalsIgnoreCase(productGrp.get(0))){
			throw new ErrorOnDataException("Product group is not contain 'All' or the default value is not 'All'.");
		}
//		for(String[] group:productGrpDB){
//			if(!productGrp.contains(group[0])){
//				throw new ErrorOnDataException("Product group don't contain the expect value:"+group[0]+".");
//			}
//		}
		if(product==null||(product.size()-1)!=productDB.size()){
			throw new ErrorOnDataException("Product size is not correct.");
		}
//		for(String[] prd:productDB){
//			if(!product.contains(prd[0])){
//				throw new ErrorOnDataException("Product don't contain the expect value:"+prd[0]+".");
//			}
//		}
	}
	
	private void verifyTransType(List<String> type){
		List<String> transType=detailPg.getAllTransactionType();
		if(transType==null||transType.size()!=type.size()){
			throw new ErrorOnDataException("Transaction type value size is not correct.");
		}
		for(String value:type){
			if(!transType.contains(value)){
				throw new ErrorOnDataException("Transaction type don't contain the expect value:"+value+".");
			}
		}
		if(transType.contains("All")){
			throw new ErrorOnDataException("Transaction type contain 'All'.");
		}
	}

}
