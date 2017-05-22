package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoicingInfo;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  May 10, 2012
 */
public class FinMgrInvoiceDetailPage extends FinMgrInvoicePage {

	private static FinMgrInvoiceDetailPage _instance = null;

	protected FinMgrInvoiceDetailPage() {
	}

	public static FinMgrInvoiceDetailPage getInstance(){
		if (null == _instance) {
			_instance = new FinMgrInvoiceDetailPage();
		}
		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^Actions\\s+Hold Transmission\\s+Release Hold",false)); 
		//browser.checkHtmlObjectExists(".id", new RegularExpression("eftinvoicetrsmsList", false));
	}
	
	public void clickMarkAsPaid(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Mark as Paid", true);
	}
	
	public void clickBack(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Back");
	}
	public void clickHoldTransmission(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Hold Transmission");
		ajax.waitLoading();
	}
	public void clickReleaseHold(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Release Hold");
		ajax.waitLoading();
	}
	

	public void clickTransmissionTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Transmissions");
		ajax.waitLoading();
	}
	

	public void clickPaymentAllocationTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Payment Allocation Records");
	}
	
	public void clickStoreAdjustmentTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Store EFT Adjustment Records");
	}
	
	public void clickDepositAdjustmentTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Deposit Adjustment Records");
	}
	

	public String getInvoiceNum(){
		String text = browser.getObjectText(".class", "Html.Div", ".id", new RegularExpression("EFTInvoiceView-\\d+\\.id", false));
		return StringUtil.getSubString(text, "Invoice Number").trim();
	}
	
	public String getStatus(){
		String text = browser.getObjectText(".class", "Html.Div", ".id", new RegularExpression("EFTInvoiceView-\\d+\\.status:CB_TO_NAME", false));
		return StringUtil.getSubString(text, "Invoice Status").trim();
	}
	
	public String getInvoiceDate(){
		String text = browser.getObjectText(".class", "Html.Div", ".id", new RegularExpression("EFTInvoiceView-\\d+\\.invoiceDate", false));
		return StringUtil.getSubString(text, "Invoice Date").trim();
	}
	
	public String getPeriodEndDate(){
		String text = browser.getObjectText(".class", "Html.Div", ".id", new RegularExpression("EFTInvoiceView-\\d+\\.periodDate", false));
		return StringUtil.getSubString(text, "Period End Date").trim();
	}
	
	public String getEFTInvoiceJobID(){
		String text = browser.getObjectText(".class", "Html.Div", ".id", new RegularExpression("EFTInvoiceView-\\d+\\.invoicingJobID", false));
		return StringUtil.getSubString(text, "EFT Invoicing Job ID").trim();
	}
	
	public String getInvoiceType(){
		String text = browser.getObjectText(".class", "Html.Div", ".id", new RegularExpression("EFTInvoiceView-\\d+\\.invoiceType:CB_TO_NAME", false));
		return StringUtil.getSubString(text, "Invoice Type").trim();
	}
	
	public String getInvoiceAmount(){
		String text = browser.getObjectText(".class", "Html.Div", ".id", new RegularExpression("EFTInvoiceView-\\d+\\.amount:CURRENCY", false));
		return StringUtil.getSubString(text, "Invoice Amount").trim();
	}
	
	public String getVendorInfo(){
		String text = browser.getObjectText(".class", "Html.Div", ".id", new RegularExpression("EFTInvoiceView-\\d+\\.vendor", false));
		return StringUtil.getSubString(text, "Vendor").trim();
	}
	
	public String getStoreInfo(){
		String text = browser.getObjectText(".class", "Html.Div", ".id", new RegularExpression("EFTInvoiceView-\\d+\\.store", false));
		return StringUtil.getSubString(text, "Agent").trim();
	}
	
	public String getAccount(){
		String text = browser.getObjectText(".class", "Html.Div", ".id", new RegularExpression("EFTInvoiceView-\\d+\\.account", false));
		return StringUtil.getSubString(text, "Account").trim();
	}
	
	public String getInvoiceGrp(){
		String text = browser.getObjectText(".class", "Html.Div", ".text", new RegularExpression("^Invoice Group.*", false));
		return StringUtil.getSubString(text, "Invoice Group").trim();
	}
	
	public EFTInvoicingInfo getInvoiceInfo(){
		EFTInvoicingInfo invoiceInfo = new EFTInvoicingInfo();
		invoiceInfo.setInvoiceNum(this.getInvoiceNum());
		invoiceInfo.setStatus(this.getStatus());
		invoiceInfo.setInvoiceType(this.getInvoiceType());
		invoiceInfo.setPeriodDate(this.getPeriodEndDate());
		invoiceInfo.setInvoiceDate(this.getInvoiceDate());
		invoiceInfo.setInvoiceJobId(this.getEFTInvoiceJobID());
		invoiceInfo.setAmount(this.getInvoiceAmount());
		String[] vendorInfo = this.getVendorInfo().split("\\-");
		invoiceInfo.setVendorNum(vendorInfo[0].trim());
		invoiceInfo.setVendorName(vendorInfo[1].trim());
		String[] agentInfo = this.getStoreInfo().split("\\-");
		invoiceInfo.setAgentNum(agentInfo[0].trim());
		invoiceInfo.setAgentName(agentInfo[1].trim());
		invoiceInfo.setInvoiceGrouping(this.getInvoiceGrp());
		return invoiceInfo;
	}
	
	public void clickVoucherInternalGCRecordsLabel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Voucher/Internal GC Records");
	}
}
