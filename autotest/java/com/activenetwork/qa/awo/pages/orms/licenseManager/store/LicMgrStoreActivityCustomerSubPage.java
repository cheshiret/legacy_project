/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrStoreDaliySalesActivityCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author ssong
 * @Date  Aug 7, 2012
 */
public class LicMgrStoreActivityCustomerSubPage extends LicMgrStoreDaliySalesActivityCommonPage {
	
	private static LicMgrStoreActivityCustomerSubPage _instance = null;
	
	protected LicMgrStoreActivityCustomerSubPage() {}
	
	public static LicMgrStoreActivityCustomerSubPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreActivityCustomerSubPage();
		}
		
		return _instance;
	}
	
	protected Property[] goButtonPrp(){
		return Property.addToPropertyArray(a(), ".text", "Go");
	}
	
	protected Property[] listTablePrp(){
		return Property.toPropertyArray(".id","LocationDailyDSalesList_LIST");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "LocationDailyDSalesList",
											".text",new RegularExpression("^Time.*",false));		
	}
	
	public void setEnterDate(String enterDate){
		browser.setCalendarField(".id",new RegularExpression("LocationDailySalesReportCriteria-\\d+.enterDate_ForDisplay", false), enterDate);
	}
	
	public void clickFirstGo(){
		browser.clickGuiObject(".class","Html.A",".text","Go",0);
	}
	
	public void setOrderNum(String ordNum){
		browser.setTextField(".id",new RegularExpression("LocationDailySalesReportCriteria-\\d+.ordNum", false), ordNum);
	}
	
	public void setPmtRfdVoucherId(String id){
		browser.setTextField(".id",new RegularExpression("LocationDailySalesReportCriteria-\\d+.pmtRefundVoucherID", false), id);
	}
	
	public void selectPaymentType(String opton){
		browser.selectDropdownList(".id",new RegularExpression("LocationDailySalesReportCriteria-\\d+.pmtTypeID", false), opton);
	}
	
	public void clickPrintByCustomerRpt(){
		browser.clickGuiObject(".class","Html.A",".text","Print Activity by Customer Report");
	}
	
	public void clickSecondGo(){
		browser.clickGuiObject(this.goButtonPrp(),1);
	}
	
	public boolean checkWhetherHaveSearchResultExisting(){
		IHtmlObject[] objs = this.getDetailListTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		
		int row = table.rowCount();
		boolean hasSearchResult;
		if(row>1){
			hasSearchResult = true;
		}else{
			hasSearchResult = false;
		}
		
		Browser.unregister(objs);
		return hasSearchResult;
	}
	
	public void searchOrderDetailInfoByOrderNumber(String orderNum){
		this.setOrderNum(orderNum);
		this.clickSecondGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private IHtmlObject[] getDetailListTableObject(){
		IHtmlObject[] objs = browser.getTableTestObject(this.listTablePrp());
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get detail list table object.");
		}
		return objs;
	}
	
	public List<Payment> getPaymentDetailInfo(){
		List<Payment> payments = new ArrayList<Payment>();
		IHtmlObject[] objs = this.getDetailListTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		
		for(int i=1;i<table.rowCount();i++){
			Payment payment = new Payment();
			payment.orderNum = table.getCellValue(i, 1);
			payment.paymentIDAndType = table.getCellValue(i, 2);
			payment.customer = table.getCellValue(i, 3);
			payment.product = table.getCellValue(i, 4);
			payment.feeTranTypeAndAllocationTranType =  table.getCellValue(i, 5);
			payment.collectUser = table.getCellValue(i, 6);
			payment.amount = table.getCellValue(i, 7);
			
			payments.add(payment);
		}
		Browser.unregister(objs);
		return payments;
	}
	
	public void comparePaymentDetailInfo(List<Payment> payments){
		logger.info("Compate payment detail info.");
		List<Payment> paymentsFromUI = this.getPaymentDetailInfo();
		boolean result = true;
		if(payments.size() != paymentsFromUI.size()){
			throw new ErrorOnPageException("Payment detail info size not correct, expect size is " + payments.size() 
					+ "; But actually size is " + paymentsFromUI.size());
		}
		int count = 0;
		for(int i=0;i<payments.size();i++){
			Payment payment = payments.get(i);
			for(int j=0;j<paymentsFromUI.size();j++){
				Payment paymentFromUI = paymentsFromUI.get(j);
				if(paymentFromUI.feeTranTypeAndAllocationTranType.replaceAll("\\s+", "").equalsIgnoreCase(payment.feeTranTypeAndAllocationTranType.replaceAll("\\s+", "")) &&
						paymentFromUI.paymentIDAndType.replaceAll("\\s+", "").equalsIgnoreCase(payment.paymentIDAndType.replaceAll("\\s+", ""))){
					result &= MiscFunctions.compareResult("Order Number", payment.orderNum, paymentFromUI.orderNum);
					result &= MiscFunctions.compareResult("Payment/Refund ID/Voucher ID", payment.paymentIDAndType.replaceAll("\\s+", ""), paymentFromUI.paymentIDAndType.replaceAll("\\s+", ""));
					result &= MiscFunctions.compareResult("Customer#/Customer Name or Business Name", payment.customer.replaceAll("\\s+", ""), paymentFromUI.customer.replaceAll("\\s+", ""));
					result &= MiscFunctions.compareResult("Product", payment.product.replaceAll(" - ", "-"), paymentFromUI.product.replaceAll(" - ", "-"));
					result &= MiscFunctions.compareResult("Fee Transaction Type/Allocation Transaction Type", payment.feeTranTypeAndAllocationTranType.replaceAll("\\s+", ""), paymentFromUI.feeTranTypeAndAllocationTranType.replaceAll("\\s+", ""));
					result &= MiscFunctions.compareResult("User", payment.collectUser.replaceAll("\\s+", ""), paymentFromUI.collectUser.replaceAll("\\s+", ""));
					result &= MiscFunctions.compareResult("Amount", payment.amount.replaceAll("\\s+|\\$|\\(|\\)", ""), paymentFromUI.amount.replaceAll("\\s+|\\$|\\(|\\)", ""));
					count++;
					break;
				}
			}
		}
		if(count != payments.size()){
			throw new ErrorOnPageException("Payment detail info Fee Transaction Type/Allocation Transaction Type column not match expect list info."); 
		}
		if(!result){
			throw new ErrorOnPageException("Payment detail info not correct, please check log.");
		}
		
	}
}
