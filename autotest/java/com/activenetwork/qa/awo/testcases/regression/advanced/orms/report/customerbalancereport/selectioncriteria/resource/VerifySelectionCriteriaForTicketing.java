package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.customerbalancereport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifySelectionCriteriaForTicketing extends ResourceManagerTestCase{

	private ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	
	public void execute() {
		//login resource manager 
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rptCriteriaPg.selectProductType(rd.productType);

		this.verifySelectionCriteriaCorrect();
		
		rm.logoutResourceManager();
	}

	private void verifySelectionCriteriaCorrect(){
		logger.info("Verify Drop down list contain correct options.");
		
		if(!rptCriteriaPg.getAllBalanceType().contains("Balance Owing")||!rptCriteriaPg.getAllBalanceType().contains("Less than Min Payment")){
			throw new ErrorOnPageException("Balance Type Drop downlist not correct.");
		}
		if(rptCriteriaPg.checkResStatusExists()){
			throw new ErrorOnPageException("Reservation Status should not be Visible.");
		}
		rd.orderStatus = rptCriteriaPg.getAllOrderStatus();
		if(!rd.orderStatus.contains("All")||!rd.orderStatus.contains("Active")||!rd.orderStatus.contains("Inactive")||!rd.orderStatus.contains("Void")){
			throw new ErrorOnPageException("Order Status Drop downlist not correct.");
		}
		rd.paymentStatus = rptCriteriaPg.getAllPaymentStatus();
		if(!rd.paymentStatus.contains("All")||!rd.paymentStatus.contains("Confirmed")||!rd.paymentStatus.contains("UnConfirmed")){
			throw new ErrorOnPageException("Payment Status Drop downlist not correct.");
		}
		rd.collectionStatus = rptCriteriaPg.getAllCollectionStatus();
		if(!rd.collectionStatus.contains("All")||!rd.collectionStatus.contains("Good Standing")||!rd.collectionStatus.contains("Doubtful Debt")||!rd.collectionStatus.contains("Uncollectible Debt")){
			throw new ErrorOnPageException("Collection Status Drop downlist not correct.");
		}
		rd.dateType = rptCriteriaPg.getAllDateType().replaceAll("#", "");
		if(!rd.dateType.equals("Order Date")){
			throw new ErrorOnPageException("Date Type Drop downlist not correct.");
		}
	}
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "Customer Balance Report";
		rd.productType = "Ticketing";
	}
}
