package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.purchaseorder;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderItemInfo;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsOnlineReportProcessingPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrPOSPurchaseOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrPurchaseOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.PDFParser;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Print purchase order and verify.
 * @Preconditions:Need a purchase order.
 * @SPEC:TC036507
 * @Task#:Auto-1110
 * @LinkSetUp:  d_inv_add_pos:id=50(product='test123')
                d_inv_assign_postosupplier:id=50(suppliername='456',posname='test123')
 * @author nding1
 * @Date  Jun 28, 2012
 */
public class Print extends InventoryManagerTestCase {
	private POSPurchaseOrderInfo purchaseOrderInfo = new POSPurchaseOrderInfo();
	private ReportData rd = new ReportData();
	private String fileName;
	
    public void execute(){
    	im.loginInventoryManager(login);
    	// select warehouse setup
    	im.gotoWarehousesSearchPg();
		//search warehouse
		im.searchWarehouse("Warehouse Name", "WhousePrintBarcode");
    	// search warehouse and go to warehouse detail page
    	im.gotoWarehouseDetailPgThroughWarehouseName("WhousePrintBarcode");
    	// select POS Purchase Order
    	im.gotoPOSPurchaseOrderPg();
    	// make a purchase order and get PO#
    	purchaseOrderInfo.searchPONum = im.createPOSPurchaseOrder(purchaseOrderInfo);

    	// search order
    	this.searchAndGotoDetailPage();
    	this.printPOSPurchaseOrder();
    	this.verifyPrintResult();
	    im.logoutInvManager();
    }

    public void wrapParameters(Object[] args) {
    	login.url = AwoUtil.getOrmsURL(env);
 	    login.contract = "MS Contract";
 	    login.location="Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		purchaseOrderInfo.supplierName = "456";
		purchaseOrderInfo.purchaseDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		purchaseOrderInfo.shippingDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		purchaseOrderInfo.paymentTerms = "Net 10";
		purchaseOrderInfo.paymentMethod = "Cash";
		purchaseOrderInfo.shippingMethod = "UPS";
		
		// set up order item info
		ArrayList<POSPurchaseOrderItemInfo> itemInfoList = new ArrayList<POSPurchaseOrderItemInfo>();
		POSPurchaseOrderItemInfo orderItemInfo = new POSPurchaseOrderItemInfo();
		orderItemInfo.productName = "test123";
		orderItemInfo.ordered = "5";
		orderItemInfo.supplierProductCode = "123";
		orderItemInfo.productDescription = "purchaser order";
		orderItemInfo.costPerUnit = "2.03";
		orderItemInfo.subTotalForItem = this.calculateCost(orderItemInfo.costPerUnit, orderItemInfo.ordered);
		itemInfoList.add(orderItemInfo);
		purchaseOrderInfo.orderItemInfo = itemInfoList;
		this.calculateTotal();
		
		rd.reportName = "POS Purchase Order Form";
    }
	
	/**
	 * Calculate sub total for each order item.
	 * @param costPerUnit
	 * @param quantity
	 * @return
	 */
	private String calculateCost(String costPerUnit, String quantity){
		logger.info("Calculate sub total for each order item.");
		BigDecimal price = new BigDecimal(costPerUnit).setScale(2);
		BigDecimal count = new BigDecimal(quantity).setScale(2);
		String subTotal = price.multiply(count).setScale(2).toString();
		return subTotal;
	}
	
	/**
	 * Calculate sub total and total for order.
	 */
	private void calculateTotal(){
		BigDecimal subTotal = BigDecimal.ZERO;
		BigDecimal total = BigDecimal.ZERO;
		logger.info(" Calculate sub total and total for order.");
		
		for(int i=0; i<purchaseOrderInfo.orderItemInfo.size(); i++){
			subTotal = subTotal.add(new BigDecimal(purchaseOrderInfo.orderItemInfo.get(i).subTotalForItem));
		}
		purchaseOrderInfo.subTotal = subTotal.setScale(2).toString();
		
		if(null != purchaseOrderInfo.discount && !"".equals(purchaseOrderInfo.discount)){
			total = subTotal.subtract(subTotal.multiply(new BigDecimal(purchaseOrderInfo.discount)));
		} else {
			total = subTotal;
		}
		purchaseOrderInfo.total = total.setScale(2).toString();
	}
	
    /**
     * Search purchase order by PO # and go to details page.
     * @param poNum
     */
    private void searchAndGotoDetailPage(){
    	InvMgrPOSPurchaseOrderSearchPage posPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
    	
    	logger.info("Search purchase order by PO # and go to details page.");
		posPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);
		im.gotoPOSPurchaseOrderDetailPage(purchaseOrderInfo.searchPONum);
    }
    
    /**
     * Print POS Purchase order
     */
    private void printPOSPurchaseOrder(){
    	InvMgrPurchaseOrderDetailsPage detailPage = InvMgrPurchaseOrderDetailsPage.getInstance();
		OrmsOnlineReportProcessingPage onlineRptPg = OrmsOnlineReportProcessingPage.getInstance();
		
		logger.info("Pring POS purchase order.");
		detailPage.clickPrint();
		ajax.waitLoading();
		onlineRptPg.waitLoading();

		File file = new File(comparedPath);
		if (!file.exists()) {
			boolean success = file.mkdir();
			if (!success) {
				throw new RuntimeException("Failed to create directory " + comparedPath);
			}
		}
		fileName = file.getAbsolutePath() + "\\" + rd.reportName.replaceAll(" ", "") + "_FM.pdf";
		im.downloadReport(fileName);
		//onlineRptPg.close();
		detailPage.waitLoading();
    }

    /**
     * Print purchase order and verify.
     */
    private void verifyPrintResult(){
    	boolean correct = true;
		logger.info("Verify PO#.");
		
		// verify PO#
		List<String> poNumList = PDFParser.getPDFContentInRow(fileName, purchaseOrderInfo.searchPONum);
		if(poNumList.size() != 1){
			throw new ErrorOnPageException("The PO# of this report should be "+purchaseOrderInfo.searchPONum);
		}

		// Verify order item info
		for(int i=0; i<purchaseOrderInfo.orderItemInfo.size(); i++){
			POSPurchaseOrderItemInfo orderItemInfo = purchaseOrderInfo.orderItemInfo.get(i);
			List<String> orderItemInfoList = PDFParser.getPDFContentInRow(fileName, orderItemInfo.productName);
			if(orderItemInfoList.size() != 1){
				throw new ErrorOnPageException("Number of order item which product name is "+purchaseOrderInfo.orderItemInfo.get(i).productName+" is not correct.");
			} else {
				logger.info("Verify order item info when product name is:"+orderItemInfo.productName);
				String[] orderItemInfoUI = orderItemInfoList.get(0).split(";");
//				if(!orderItemInfo.supplierProductCode.equals(orderItemInfoUI[1].trim())){
//					correct &= false;
//					logger.error("---Supplier product code is NOT correct. Expect:"+orderItemInfo.supplierProductCode+", actual:"+orderItemInfoUI[1]);
//				}

//				if(!orderItemInfo.productDescription.equals(orderItemInfoUI[2])){
//					correct &= false;
//					logger.error("---Product Description is NOT correct. Expect:"+orderItemInfo.productDescription+", actual:"+orderItemInfoUI[2]);
//				}

				if(!orderItemInfo.ordered.equals(orderItemInfoUI[1].trim())){
					correct &= false;
					logger.error("---Number of ordered is NOT correct. Expect:"+orderItemInfo.ordered+", actual:"+orderItemInfoUI[3]);
				}

				if(!orderItemInfo.costPerUnit.equals(orderItemInfoUI[2].replaceAll("\\$", "").trim())){
					correct &= false;
					logger.error("---Supplier unit cost is NOT correct. Expect:"+orderItemInfo.costPerUnit+", actual:"+orderItemInfoUI[4]);
				}

				if(!orderItemInfo.subTotalForItem.equals(orderItemInfoUI[3].replaceAll("\\$", "").trim())){
					correct &= false;
					logger.error("---Sub total of order item is NOT correct. Expect:"+orderItemInfo.subTotalForItem+", actual:"+orderItemInfoUI[5]);
				}
			}
		}
		
		// verify sub total
		List<String> subTotalList = PDFParser.getPDFContentInRow(fileName, "SubTotal");//[SubTotal;$5.63;]
		if(subTotalList.size() != 1){
			throw new ErrorOnPageException("There should be only one sub total info, but actual there is(are) "+subTotalList.size());
		} else {
			String subtotalUI = subTotalList.get(0).split(";")[1].replaceAll("\\$", "");
			if(!purchaseOrderInfo.subTotal.equals(subtotalUI)){
				correct &= false;
				logger.error("Sub total is NOT correct. Expect:"+purchaseOrderInfo.subTotal+", actual:"+subtotalUI);
			}
		}

		// verify total
		List<String> totalList = PDFParser.getPDFContentInRow(fileName, "Total");
		if(totalList.size() != 1){
			throw new ErrorOnPageException("There should be only one total info, but actual there is(are) "+subTotalList.size());
		} else {
			String totalUI = totalList.get(0).split(";")[1].replaceAll("\\$", "");
			if(!purchaseOrderInfo.total.equals(totalUI)){
				correct &= false;
				logger.error("---Total is NOT correct. Expect:"+purchaseOrderInfo.total+", actual:"+totalUI);
			}
		}
	  	if(!correct){
	  		throw new ErrorOnPageException("---Report Content is Not Correct.");
		}
    }
}
