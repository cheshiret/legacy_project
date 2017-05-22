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
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case contains the scenario:
 *              1. verify select creation of the RA fee schedule for facility level
 * @Preconditions:
 * @SPEC:Add RA Fee Schedule.UCS,DEFECT-37437
 * @Task#:AUTO-1362
 * 
 * @author szhou
 * @Date  Nov 15, 2012
 */
public class VerifyRAFeeSchdSelectCreationForPOS_Facility extends FinanceManagerTestCase{
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private FinMgrRaFeeSchDetailPage detailPg = FinMgrRaFeeSchDetailPage.getInstance();
	private List<String> subCategory=new ArrayList<String>();
	private List<String> posType=new ArrayList<String>();
	private List<String> subscriptionType=new ArrayList<String>();
	private List<String> donationType=new ArrayList<String>();
	
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
	    this.verifyRAFeeScheduleCreationForPOS();
	    
	    //logout finance manager
	    fnm.logoutFinanceManager();
	}
	
	private void verifyRAFeeScheduleCreationForPOS(){
		FinMgrRaFeeSchMainPage schMainPg = FinMgrRaFeeSchMainPage.getInstance();
		logger.info("Verify RA fee schedule creation for POS.");
		
		// verify product sub category
		this.verifyProductSubCategoryInPOSRA(subCategory);
		// verify product,product group,transaction type(POS Sale/Subscription Sale/Donation)
		this.verifyProductAndTransType();
		// verify location class
		this.verifyLocationClassDefault();
		// verify license year
		this.verifyLicenseYear();
		
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
	  	schedule.locationCategory = "Park";
	  	schedule.location = "Clarkco";
	  	schedule.productCategory="POS";
	  	
	  	subCategory.add("POS Sale");
	  	subCategory.add("Subscription Sale");
	  	subCategory.add("Donation");
	  	
	  	posType.add("Purchase POS");
	  	posType.add("Add POS item");
	  	posType.add("Purchase Consumable");
	  	
	  	subscriptionType.add("Purchase Subscription");
	  	
	  	donationType.add("Make Donation");
		
	}
	
	public void verifyProductSubCategoryInPOSRA(List<String> subValue){
		logger.info("Verify product sub category on RA fee schedule detail page.");
		
		List<String> values=detailPg.getAllProductSubCategory();
		
		if(values==null||values.size()!=3){
			throw new ErrorOnDataException("Product Sub Category value size is not correct.");
		}
		for(String value:subCategory){
			if(!values.contains(value)){
				throw new ErrorOnDataException("Product Sub Category don't contain the expect value:"+value+".");
			}
		}
		String defaultValue=values.get(0);
		if(!"POS Sale".equals(defaultValue)){
			throw new ErrorOnDataException("Default value is "+defaultValue+";But actually default value should be 'POS Sale'.");
		}
		
	}
	
	private void verifyProduct(String sub,String location, boolean isRevenue){
		List<String> productGrp=detailPg.getAllProductGroup();
//		List<String> product=detailPg.getAllProduct();
		List<String[]> productGrpDB=fnm.getProductGroupNameUsingFeeSchdDetailPgFromDB(schema, "4", sub);
//		List<String[]> productDB=this.getProductListForFacilityLevel(location, "4", sub, schema);
		
		if(productGrp==null||(productGrp.size()-1)!=productGrpDB.size()){
			throw new ErrorOnDataException("Product group size is not correct.");
		}
		if(!productGrp.contains("All") ||! "All".equalsIgnoreCase(productGrp.get(0))){
			throw new ErrorOnDataException("Product group is not contain 'All' or the default value is not 'All'.");
		}

//		if(product==null||(product.size()-1)!=productDB.size()){
//			throw new ErrorOnDataException("Product size is not correct.");
//		}
//		if(!product.contains("All") ||! "All".equalsIgnoreCase(product.get(0))){
//			throw new ErrorOnDataException("Product is not contain 'All' or the default value is not 'All'.");
//		}
	}
	
	private List<String[]> getProductListForFacilityLevel(String location,String productCategory, String productSubCategory,String schema){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		
		db.resetSchema(schema);
		logger.info("Get product name which using fee schedule detail page from DB..");
		
		String sql = "select PRD_NAME from P_PRD "+
		             "where P_PRD.PRODUCT_CAT_ID="+productCategory+" AND P_PRD.ACTIVE_IND=1 AND P_PRD.PRD_SUBCAT_ID="+productSubCategory+
		             " AND (P_PRD.REV_LOC_ID = "+location+" or (P_PRD.REV_LOC_ID is null and P_PRD.PRD_ID in (select P_PRD_LOC.PRD_ID from P_PRD_LOC where LOC_ID = "
				     +location+")))";
		
		String[] colNames = { "PRD_NAME" };

		List<String[]> values = db.executeQuery(sql, colNames);

		return values;
		
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
	
	public void verifyProductAndTransType(){
		// POS Sale
		logger.info("Verify product, product group, transaction type on RA fee schedule detail page when product sub category choose 'POS Sale'.");
		
		this.verifyProduct("1", "154842", false);// 1:POS sale
		this.verifyTransType(posType);
		
		// Subscription Sale
		logger.info("Verify product, product group, transaction type on RA fee schedule detail page when product sub category choose 'Subscription Sale'.");
		
		detailPg.selectPrdSubCategory("Subscription Sale");
		this.verifyProduct("2", "154842", true);//2:Subscription Sale
		this.verifyTransType(subscriptionType);
		
		// Donation
		logger.info("Verify product, product group, transaction type on RA fee schedule detail page when product sub category choose 'Donation'.");
		
		detailPg.selectPrdSubCategory("Donation");
		this.verifyProduct("3", "154842", true);//3: Donation
		this.verifyTransType(donationType);
	}
	
	private void verifyLocationClassDefault(){
		logger.info("Verify location class on RA fee schedule detail page.");
		
		String value=detailPg.getLocationClass();
		
	    if("".equals(value)||!"All".equals(value)){
	    	throw new ErrorOnDataException("Default value is "+value+";But actually default value should be 'All'.");
	    }
	}
	
	private void verifyLicenseYear(){
    	logger.info("Verify license year on RA fee schedule detail page.");
    	
    	List<String> value=detailPg.getLicenseYearFromOptions();
    	if(value==null||(value.size()-1)!=10){
			throw new ErrorOnDataException("License year from value size is not correct.");
		}
    	String defaultValue=value.get(0);
		if(!"All".equals(defaultValue)){
			throw new ErrorOnDataException("Default value is "+defaultValue+";But actually default value should be 'All'.");
		}
	}
}
