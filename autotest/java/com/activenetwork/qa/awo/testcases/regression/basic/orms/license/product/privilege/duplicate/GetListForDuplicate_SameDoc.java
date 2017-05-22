package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.duplicate;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrReplacePrivSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrGetListForDuplicate;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: Verify whether privilege product can be duplicate AND is grouped by document template in replacement page
 * @Preconditions: Need an existing customer
 * @SPEC: <<Get Privilege List for Duplicate.doc>>
 * @Task#: AUTO-881
 * 
 * @author qchen
 * @Date  Apr 17, 2012
 */
public class GetListForDuplicate_SameDoc extends LicMgrGetListForDuplicate {
	private LicMgrDocumentInfo docInfo = new LicMgrDocumentInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		privilege.code = "GLD";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		//precondition#1: create privilege to make it can be duplicated
		this.createPrivilegeProduct();
		this.initialPrintDocumentInfo();
		//deactivate all existing print document records
		lm.deactivateAllPrivilegeDocumentAssignment();
		lm.addPrintDocumentForProduct(docInfo);
		lm.logOutLicenseManager();
		
		login.location = "HF HQ Role/WAL-MART";
		lm.loginLicenseManager(login);
		//precondition#2: make the new created privilege to cart - Document Template = 'DocTempl_GetList4Reprint', QTY=3
		privilege.qty = "3";
		//precondition#3: make second existing privilege to cart - Document Template = 'DocTempl_GetList4Reprint', QTY=2
		PrivilegeInfo privilege2 = this.prepare2PrivilegeInfo();
		//precondition#4: make third existing privilege to cart - Document Template is different from above, QTY=2
		PrivilegeInfo privilege3 = this.prepare3PrivilegeInfo();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege, privilege2, privilege3);
		String orderNum = lm.processOrderCart(pay);
		
		//go to 'Replacement Privileges' list page to verify whether the privileges group by Document or not
		lm.gotoAddItemPgFromCustomerTopMenu(cust);
		lm.gotoReplacePrivAddItemPgFromOrgiAddPg();
		this.verifyPrivilegeGroupByDocument(orderNum);
		
		//complete the duplicated process to ensure the privilege really can be duplicated
		String privilegeNums[] = lm.getPrivilegeNumsByOrdNum(schema, orderNum);
		lm.replacePrivilegeToCartFromCustomerTopMenuByPrivilegeNums(cust, privilegeNums[0]);
		orderNum = lm.processOrderCart(pay);

		// clean up
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	private void initialPrintDocumentInfo() {
		docInfo.prodType = "Privilege";
		docInfo.prodCode = privilege.code;
		docInfo.printOrd = "1";
		docInfo.docTepl = "DocTempl_GetList4Reprint";
		docInfo.licYearFrom = "All";
		docInfo.effectiveFromDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		docInfo.effectiveToDate = DateFunctions.getDateAfterGivenDay(docInfo.effectiveFromDate, 1);
		docInfo.fulfillMethod = "Printed Immediately";
		docInfo.equipType = "All";
		docInfo.purchaseTypes = new String[]{"Transfer", "Original", "Duplicate"};
		docInfo.configVariables = new String[]{"Automation"};
	}
	
	private PrivilegeInfo prepare2PrivilegeInfo() {
		PrivilegeInfo p = new PrivilegeInfo();
		
		p.code = "DSD";
		p.name = "GetList4DuplicateSameDoc";
		p.purchasingName = p.code + "-" + p.name;
		p.licenseYear = lm.getFiscalYear(schema);
		p.qty = "2";
		
		return p;
	}
	
	private PrivilegeInfo prepare3PrivilegeInfo() {
		PrivilegeInfo p = new PrivilegeInfo();
		
		p.code = "COP";
		p.name = "CalculateOrderPrice";
		p.purchasingName = p.code + "-" + p.name;
		p.licenseYear = lm.getFiscalYear(schema);
		p.qty = "2";
		
		return p;
	}
	
	private void verifyPrivilegeGroupByDocument(String ordNum) {
		LicMgrReplacePrivSaleAddItemPage replacePage = LicMgrReplacePrivSaleAddItemPage.getInstance();
		
		List<String> namesOnPage = replacePage.getPrivilegeNames(ordNum);
		List<String> docPool = new ArrayList<String>();
		
		String doc = "";
		for(int i = 0; i < namesOnPage.size(); i ++) {
			doc = lm.getProductDocumentTemplate(schema, namesOnPage.get(i).split("-")[0].trim(), OrmsConstants.REPLACEMENT_PURCHASE_TYPE_ID);
			if(i == 0) {
				docPool.add(doc);
			}
			
			if(docPool.get(docPool.size() - 1).equals(doc)) {//if the current privilege's document is the same with last one's, do nothing
				continue;
			} else if(!docPool.contains(doc)) {//if the current privilege's document is different with last one's; and the doc pool doesn't contains it, add it to pool
				docPool.add(doc);
			} else {//if the current privilege's document is the same with one of pool but not last, it means the privilege list sorting is wrong
				throw new ErrorOnPageException("The " + (i+1) + " privilege instance - " + namesOnPage.get(i) + " violate the 'Privilege Instances Grouping by Document' rule.");
			}
		}
	}
}
