/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoiceTransmissionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Nov 7, 2012
 */
public class LicMgrEFTReportingDetailPage extends LicMgrCommonTopMenuPage{
		private static LicMgrEFTReportingDetailPage instance=null;
		private LicMgrEFTReportingDetailPage(){
		}

		public static LicMgrEFTReportingDetailPage getInstance() {
			if(instance ==null) {
				instance = new LicMgrEFTReportingDetailPage();
			}
			return instance;
		}

		public boolean exists() {
			// using transmission records view list as page mark.
			return browser.checkHtmlObjectExists(".id","eFTInvoiceTransmissionViewList");
		}

		public void clickBack(){
			browser.clickGuiObject(".class", "Html.A", ".text", "Back");
		}
		
		public void clickInvoiceNum(String invoiceNum){
			browser.clickGuiObject(".class", "Html.A", ".text", invoiceNum);
		}
		
		private IHtmlTable getTable(){
			IHtmlObject[] objs = browser.getTableTestObject(".id", "eFTInvoiceTransmissionViewList");
			if(objs.length < 1){
				throw new ItemNotFoundException("Can't find EFT Invoice Transmission View List table");
			}
			
			IHtmlTable table = (IHtmlTable)objs[0];
			Browser.unregister(objs);
			return table;
		}
		
		public EFTInvoiceTransmissionInfo getInfoByInvoiceNum(String invoiceNum){
			logger.info("Get record by given invoice number:"+invoiceNum);
			EFTInvoiceTransmissionInfo transmissionInfo = new EFTInvoiceTransmissionInfo();
			IHtmlTable table = this.getTable();
			int row = table.findRow(0, invoiceNum);
			if(row < 0){
				throw new ItemNotFoundException("Can't find record by given invoice number:"+invoiceNum);
			}
			List<String> rowList = table.getRowValues(row);
			transmissionInfo.setInvoiceAmount(this.setAmount(rowList.get(3)));
			transmissionInfo.setSale(this.setAmount(rowList.get(4)));
			transmissionInfo.setVoidPendingDocReturn(this.setAmount(rowList.get(5)));
			transmissionInfo.setChargedVoids(this.setAmount(rowList.get(6)));
			transmissionInfo.setCreditedVoids(this.setAmount(rowList.get(7)));
			transmissionInfo.setDeductedVendorFee(this.setAmount(rowList.get(8)));
			transmissionInfo.setStoreEFTAdj(this.setAmount(rowList.get(9)));
			transmissionInfo.setDepositAdj(this.setAmount(rowList.get(10)));
			transmissionInfo.setPaymentApplied(this.setAmount(rowList.get(11)));
			return transmissionInfo;
		}
		
		private String setAmount(String amount){
			if(amount.startsWith("\\(")){// negative number
				amount = "-"+amount;
			}
			return amount.replaceAll("(\\$)|(\\()|(\\))", StringUtil.EMPTY);
		}
		
		public String getAgentDetailInfo(String invoiceNum){
			IHtmlTable table = this.getTable();
			int row = table.findRow(0, invoiceNum);
			if(row < 0){
				throw new ItemNotFoundException("Can't find record by given invoice number:"+invoiceNum);
			}
			
			return browser.getHtmlObject(".class", "Html.A", ".text", "More")[row-1].getProperty(".title");
		}
		
		public void verifyDetailInfo(EFTInvoiceTransmissionInfo expectInfo, String expectAgentInfo){
			EFTInvoiceTransmissionInfo actualInfo = this.getInfoByInvoiceNum(expectInfo.getInvoiceId());
			boolean result = true;
			// actualInfo.get(0) is invoice number, no need to verify
			String actulaAgentInfo = this.getAgentDetailInfo(expectInfo.getInvoiceId());
			result &= MiscFunctions.compareResult("Agent Info", expectAgentInfo, actulaAgentInfo);// expect like:"Store Info|Agent: 1061-GEORGE P COSSAR"
			result &= MiscFunctions.compareResult("Invoice Amount", Double.valueOf(expectInfo.getInvoiceAmount()), Double.valueOf(actualInfo.getInvoiceAmount()));
			result &= MiscFunctions.compareResult("Sale", Double.valueOf(expectInfo.getSale()), Double.valueOf(actualInfo.getSale()));
			result &= MiscFunctions.compareResult("Voids Pending Doc Return", Double.valueOf(expectInfo.getVoidPendingDocReturn()), Double.valueOf(actualInfo.getVoidPendingDocReturn()));
			result &= MiscFunctions.compareResult("Charged Voids", Double.valueOf(expectInfo.getChargedVoids()), Double.valueOf(actualInfo.getChargedVoids()));
			result &= MiscFunctions.compareResult("Credited Voids", Double.valueOf(expectInfo.getCreditedVoids()), Double.valueOf(actualInfo.getCreditedVoids()));
			result &= MiscFunctions.compareResult("Deducted Vendor Fees", Double.valueOf(expectInfo.getDeductedVendorFee()), Double.valueOf(actualInfo.getDeductedVendorFee()));
			result &= MiscFunctions.compareResult("Store EFT Adjustments", Double.valueOf(expectInfo.getStoreEFTAdj()), Double.valueOf(actualInfo.getStoreEFTAdj()));
			result &= MiscFunctions.compareResult("Deposit Adjustment", Double.valueOf(expectInfo.getDepositAdj()), Double.valueOf(actualInfo.getDepositAdj()));
			result &= MiscFunctions.compareResult("Payments Applied", Double.valueOf(expectInfo.getPaymentApplied()), Double.valueOf(actualInfo.getPaymentApplied()));
			if(!result){
				throw new ErrorOnDataException("---Check logs above.");
			}
		}

		public String getTotalAmount(){
			IHtmlTable table = this.getTable();
			return table.getCellValue(table.rowCount()-1, 3);
		}
}
