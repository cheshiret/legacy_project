package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrVendorFinConfigInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.ReturnDocumentOrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.ReturnDocumentOrderInfo.ReturnDocumentOrderItem;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeReturnDocumentPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorFinConfigSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:work flow:  
 * 1. make privilege order, with print document
 * 2. void this privilege order   
 * 3. view return document info
 * 
 * @Preconditions:
 * 1. An existing privilege(pricing, agent assignment, license year, quantity control) named 'CalculateOrderPrice'.
 * 2. select * from X_PROP WHERE NAME Like ('%PrivilegeVoidReversalTransactionCoverage%'), make sure value is '3'       
 * 3. Vendor should has no "Auto Return Voided Documents" at finicial configuation
 *    and void Return Charge Days should be '2'        
 * @SPEC: View Unreturned Voided Privilege Documents [TC:024755]
 * @Task#:AUTO-1314
 * 
 * @author vzhang
 * @Date  Nov 6, 2012
 */

public class ViewUnreturnVoidPrivilegeDocPending extends LicenseManagerTestCase{
	private String vendorNum, storeName, storeID;
	private ReturnDocumentOrderInfo searchReturnDocOrderInfo = new ReturnDocumentOrderInfo();
	private ReturnDocumentOrderInfo returnDocOrderInfo = new ReturnDocumentOrderInfo();
	private LicMgrVendorFinConfigInfo financialConfig = new LicMgrVendorFinConfigInfo();
	private LicMgrPrivilegeReturnDocumentPage privilegeReturnDocumentPg = LicMgrPrivilegeReturnDocumentPage.getInstance();
	private static int FILTER_BY_STATUS = 0;
	private static int FILTER_BY_RECEIPTNUM = 1;
	private static int FILTER_BY_ORDERNUM = 2;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//precondition#1: go to Financial Config page in Vendor Details page, to update 'Void Return Charge Days' as 1(it's the minimum),
		//and set 'Auto-Return' Voided Documents as NO
		lm.gotoVendorDetailsPgFromTopMenu(vendorNum);
		lm.gotoVendorFinConfigSubPage();
		this.editVendorFinancialConfig(prepareVendorFinancialConfigInfo());
		
		//make privilege order with print document
		lm.gotoHomePage();
		lm.switchLocationInHomePage("HF HQ Role - Auto-" + storeName);
		//make privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		//process order with print document
		String orderNum = lm.processOrderCart(pay, true).split(" ")[0];
		System.out.println(orderNum);
		
		//void privilege order
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		
		//initial return document info
		this.initialReturnDocumentInfo(orderNum);
		lm.gotoReturnDocumentPageFromHome();
		
		//search by return status as pending
		searchReturnDocOrderInfo.setReturnStatus(OrmsConstants.PENDING_STATUS);
		lm.searchReturnDocumentOrderInfoFromReturnDocumentListPage(searchReturnDocOrderInfo);
		this.verifyColumnListInfoBySearch(FILTER_BY_STATUS,searchReturnDocOrderInfo.getReturnStatus());
		
		//search by receipt number
		searchReturnDocOrderInfo.setReturnStatus("");
		searchReturnDocOrderInfo.setReceiptNum( returnDocOrderInfo.getReceiptNumInfo().split(",")[0]);
		lm.searchReturnDocumentOrderInfoFromReturnDocumentListPage(searchReturnDocOrderInfo);
		this.verifyColumnListInfoBySearch(FILTER_BY_RECEIPTNUM, searchReturnDocOrderInfo.getReceiptNum());

		//search by order number
		searchReturnDocOrderInfo.setReceiptNum("");
		searchReturnDocOrderInfo.setOrdNum(orderNum);
		lm.searchReturnDocumentOrderInfoFromReturnDocumentListPage(searchReturnDocOrderInfo);
		this.verifyReturnDocumentOrderInfo(returnDocOrderInfo);
		this.verifyColumnListInfoBySearch(FILTER_BY_ORDERNUM, returnDocOrderInfo.getOrdNum());
		
		//search by store id
		searchReturnDocOrderInfo.setOrdNum("");
		searchReturnDocOrderInfo.setStoreID(storeID);
		lm.searchReturnDocumentOrderInfoFromReturnDocumentListPage(searchReturnDocOrderInfo);
		this.verifyColumnListSearchByStoreID(searchReturnDocOrderInfo.getStoreID());
		
		//search by vendor number
		searchReturnDocOrderInfo.setStoreID("");
		searchReturnDocOrderInfo.setVendorNum(vendorNum);
		lm.searchReturnDocumentOrderInfoFromReturnDocumentListPage(searchReturnDocOrderInfo);
		this.verifyColumnListSearchByVendorNum(searchReturnDocOrderInfo.getVendorNum());
		
		//search by return status as pending,receipt number,order number,store id,vendor number
		searchReturnDocOrderInfo.setReturnStatus(OrmsConstants.PENDING_STATUS);
		searchReturnDocOrderInfo.setReceiptNum( returnDocOrderInfo.getReceiptNumInfo().split(",")[0]);
		searchReturnDocOrderInfo.setOrdNum(orderNum);
		searchReturnDocOrderInfo.setStoreID(storeID);
		lm.searchReturnDocumentOrderInfoFromReturnDocumentListPage(searchReturnDocOrderInfo);
		this.verifyColumnListInfoBySearch(FILTER_BY_STATUS,searchReturnDocOrderInfo.getReturnStatus());
		this.verifyColumnListInfoBySearch(FILTER_BY_RECEIPTNUM, searchReturnDocOrderInfo.getReceiptNum());
		this.verifyColumnListInfoBySearch(FILTER_BY_ORDERNUM, returnDocOrderInfo.getOrdNum());
		this.verifyColumnListSearchByStoreID(searchReturnDocOrderInfo.getStoreID());
		this.verifyColumnListSearchByVendorNum(searchReturnDocOrderInfo.getVendorNum());
		
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		storeName = "WAL-MART";
		storeID = lm.getAgentID(schema, storeName);
		vendorNum = "Auto555";
		
		privilege.code = "COP";
		privilege.name = "CalculateOrderPrice";
		privilege.purchasingName = "COP-CalculateOrderPrice";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-Basic14";
		cust.lName = "TEST-Basic14";
		cust.dateOfBirth = "19880608";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000014";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
	}
	
	private LicMgrVendorFinConfigInfo prepareVendorFinancialConfigInfo() {
		financialConfig.voidReturnChargeDays = "1";// don't change!
		financialConfig.autoReturnVoidedDoc = "No";
		
		return financialConfig;
	}
	
	/**
	 * Edit vendor financial config info
	 */
	private void editVendorFinancialConfig(LicMgrVendorFinConfigInfo vendorFinConfigInfo) {
		LicMgrVendorFinConfigSubPage financialConfigPage = LicMgrVendorFinConfigSubPage.getInstance();
		
		logger.info("Edit vendor fianncial config - 'Void Return Charge Days' as " + vendorFinConfigInfo.voidReturnChargeDays + " and 'Auto-Return Voided Documents' as " + vendorFinConfigInfo.autoReturnVoidedDoc);
		financialConfigPage.editFinancialConfigInfo(vendorFinConfigInfo);
		financialConfigPage.clickSave();
		ajax.waitLoading();
		financialConfigPage.waitLoading();
	}
	
	private void initialReturnDocumentInfo(String orderNum){
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		returnDocOrderInfo.setOrdNum(orderNum);
		returnDocOrderInfo.setReceiptNumInfo(lm.getReceiptNumsByOrderNum(schema, orderNum));
		
		returnDocOrderInfo.setReturnDueDate(DateFunctions.getDateAfterToday(Integer.valueOf(financialConfig.voidReturnChargeDays)));
		returnDocOrderInfo.setCustomerInfo(cust.fName, cust.lName);
		returnDocOrderInfo.setPurchaseDate(DateFunctions.getToday(timeZone));
		returnDocOrderInfo.setPurchasedLocation(storeID, storeName);
		returnDocOrderInfo.setVoidedDate(returnDocOrderInfo.getPurchaseDate());
		returnDocOrderInfo.setVoidedLocation(storeID, storeName);
		returnDocOrderInfo.setReturnStatus(OrmsConstants.PENDING_STATUS);
		
		List<ReturnDocumentOrderItem> returnDocOrdItemList = new ArrayList<ReturnDocumentOrderItem>();
		ReturnDocumentOrderItem returnDocOrdItem = new ReturnDocumentOrderItem();
		returnDocOrdItem.setPrivilegeNum(lm.getPrivilegeNumByOrdNum(schema, orderNum));
		returnDocOrdItem.setProductInfo(privilege.code,privilege.name);
		returnDocOrdItem.setLicenseYear(privilege.licenseYear );
		returnDocOrdItem.setTransactionType(OrmsConstants.ORIGINAL_PURCHASE_TYPE);
		returnDocOrdItem.setValidFromDate(returnDocOrderInfo.getPurchaseDate());
		returnDocOrdItem.setValidToDate("");
		returnDocOrdItemList.add(returnDocOrdItem);
		
		returnDocOrderInfo.setReturnDocumentOrderItems(returnDocOrdItemList);
	}
	
	private void verifyReturnDocumentOrderInfo(ReturnDocumentOrderInfo expReturnDocOrdInfo){
		logger.info("Verify return document order info.");
		boolean result = privilegeReturnDocumentPg.compareReturnDocumentOrderInfo(expReturnDocOrdInfo);
		if(!result){
			throw new ErrorOnPageException("The return document order info is not correct, please check log.");
		}else logger.info("The return document order info is correct");
	}
	
	private void verifyColumnListInfoBySearch(int searchBy, String expFiterValue){
		List<String> actColumnListValue = new ArrayList<String>();
		boolean result = true;
		switch(searchBy){
		case 0:
			actColumnListValue = privilegeReturnDocumentPg.getReturnDocumentOrderStatusListValue();
			break;
		case 1:
			actColumnListValue = privilegeReturnDocumentPg.getReceiptListValue();
			break;
		case 2: 
			actColumnListValue = privilegeReturnDocumentPg.getOrdNumListValue();
			break;
		}
		
		if(actColumnListValue.size()<1){
			throw new ErrorOnPageException("Should existing filter result.");
		}
		
		if(FILTER_BY_RECEIPTNUM == searchBy){
			for(String actValue : actColumnListValue){
				result &= MiscFunctions.compareResult("Column Value by Filter",true, actValue.contains(expFiterValue));
			}
		}else{
			for(String actValue : actColumnListValue){
				result &= MiscFunctions.compareResult("Column Value by Filter", expFiterValue, actValue);
			}
		}
		
		if(!result){
			throw new ErrorOnPageException("Filter result is not correct.");
		}logger.info("Filter result is correct.");
		
	}
	
	private void verifyColumnListSearchByStoreID(String filterStoreID){
		List<String> actPurchasedLocationListValue = privilegeReturnDocumentPg.getPurchasedLocationListValue();
		List<String> actVoidedLocationListValue = privilegeReturnDocumentPg.getVoidedLocationListValue();
		
		if(actPurchasedLocationListValue.size()<1 || actVoidedLocationListValue.size()<1){
			throw new ErrorOnPageException("Should existing filter result.");
		}
		
		for(int i=0; i<actPurchasedLocationListValue.size(); i++){
			if(!actPurchasedLocationListValue.get(i).startsWith(filterStoreID) 
					&& !actVoidedLocationListValue.get(i).startsWith(filterStoreID)){
				throw new ErrorOnPageException("Filter result is not correct. " +
						"Should filter the list which either to Purchased location or voided location is same as the store ID " + filterStoreID);
			}logger.info("Filter result is correct.");
		}
	}
	
	private void verifyColumnListSearchByVendorNum(String filterVendorNum){
		List<String> actPurchasedLocationListValue = privilegeReturnDocumentPg.getPurchasedLocationListValue();
		List<String> actVoidedLocationListValue = privilegeReturnDocumentPg.getVoidedLocationListValue();
		
		if(actPurchasedLocationListValue.size()<1 || actVoidedLocationListValue.size()<1){
			throw new ErrorOnPageException("Should existing filter result.");
		}
		
		for(int i=0; i<actPurchasedLocationListValue.size(); i++){
			String actPurchaseLocationName = actPurchasedLocationListValue.get(i).split(" - ")[1].trim();
			String actVenodrByPurchaseLocationName = lm.getVendorNameAndNumByStoreName(schema, actPurchaseLocationName)[1];
			String actVoidedLocationName = actVoidedLocationListValue.get(i).split(" - ")[1].trim();
			String actVenodrByVoidedLocationName = lm.getVendorNameAndNumByStoreName(schema, actVoidedLocationName)[1];
			if(!actVenodrByPurchaseLocationName.equals(filterVendorNum) 
					&& !actVenodrByVoidedLocationName.equals(filterVendorNum)){
				throw new ErrorOnPageException("Filter result is not correct. " +
						"Should filter the list which either to Purchased location or voided location is same as the vendor number " + filterVendorNum);
			}logger.info("Filter result is correct.");
		}
	}

}
