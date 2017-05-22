package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege;

import java.io.File;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.PDFParser;

/**
 * @Description: Purchase a privilege with inventory type in web, and select to input the inventory number on hand to finish the purchase.
 * 1. Firstly purchase the inventory type product in LM to obtain the inventory number;
 * 2. Then purchase the privilege in web and input the inventory number.
 * 3. On Confirmation page, print the order and check the inventory number on the print file  
 * @Preconditions:
 * 1. SQL about setup fulfillment method for web, setup by updateXPROP.sql:
 * select * from x_prop where name like 'ApplicableInventoryFulfillmentMethods' and namespace='PublicWeb' and value='1,2';
 * (1 - fulfilled by mail, 2 - inventory on hand)
 * 2. Inventory type "ConvPack Type" exists, create and allocate inventories for the type
 * 3. Product "SIA - HFSK InventoryProd001" exists, and set as below:
 * Product Group: Inventory
 * Inventory type: ConvPack Type
 * Inventory Quantity: 2
 * Assign to store
 * 4. Product "SIB - HFSK InventoryPriv001" exists, and set as below:
 * Product Group: Privileges
 * Inventory type: ConvPack Type
 * Qty Type: Fixed
 * Inventory Quantity: 1
 * Assign to web
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=980
 * d_hf_priv_invtype_licyear:id=80
 * d_hf_add_allo_pri_inv:id=80
 * d_hf_add_privilege_prd:id=2220, 2230
 * d_hf_add_pricing:id=3112,3122
 * d_hf_assi_pri_to_store:id=1470,1480
 * d_hf_add_prvi_license_year:id=2260,2270
 * d_hf_add_qty_control:id=1450,1460
 * d_hf_add_print_doc:id=390
 * @SPEC:
 * Convenience Pack setup [TC:099301]
 * Convenience Pack - Licence with inventory type sales flow - Print Immediately [TC:101707]
 * @Task#: Auto-1865
 * 
 * @author Lesley Wang
 * @Date  Aug 29, 2013
 */
public class PurchasePrivWithInvType_InvOnHand extends HFSKWebTestCase {
	private PrivilegeInfo inventoryProd = new PrivilegeInfo();
	private String path, fullFileName;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(loginLM);
		
		// Clean up all privileges for the customer
		lm.invalidatePrivilegePerCustomer(cus, privilege);
		
		// Purchase the inventory type product in LM to obtain the inventory number
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, inventoryProd);
		String invOrdNum = lm.processOrderCart(pay,false);
		if(invOrdNum.contains(" ")){
			invOrdNum = invOrdNum.split(" ")[0];
		}
		privilege.inventoryNum = lm.getPrivilegeInventoryNumBySoldOrdNum(schema, invOrdNum);
		lm.logOutLicenseManager();
		
		// Purchase the privilege in web and print the order to verify inventory number on print file
		cus.residencyStatus = RESID_STATUS_SK;
		hf.invokeURL(url);
		hf.makePrivilegeOrderToCart(cus, privilege, false);
		String privOrd = hf.checkOutCartToConfirmationPage(pay);
		fullFileName = hf.printPrivilegesOnConfirmationPg(path);
		this.verifyPrivInvNumInPrintFile(privOrd, privilege.invType, privilege.inventoryNum);
		
		// Clean up to void the inventory type order to release the inventory
		lm.loginLicenseManager(loginLM);
		lm.reversePrivilegeOrderToCleanUp(privOrd, voidReserveReason, privilege.operateNote, pay);
		lm.logOutLicenseManager();
	}

	
	@Override
	public void wrapParameters(Object[] param) {
		// Customer info
		cus.fName = "ConvPack01_FN"; // d_web_hf_signupaccount, id=980
		cus.lName = "ConvPack01_LN";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "-01-01";
		cus.identifier.id = OrmsConstants.IDEN_RCMP_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, true, false);
		cus.identifier.identifierNum = "1R9Y4x4153";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK + " - " + cus.identifier.identifierType;
		
		// Privilege info
		privilege.invType = "Con#";
		privilege.inventoryQty = "1";
		privilege.name = "HFSK InventoryPriv001";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.qty = "1";		
		
		// Update inventory type to make the type alias shown as the label for the inventory type on print file
		hf.updatePrivInvType(schema, "ConvPack Type", "1", privilege.invType);
		
		// Inventory type product info
		inventoryProd.name = "HFSK InventoryProd001";
		inventoryProd.code = "SIA";
		inventoryProd.purchasingName = inventoryProd.code + "-" + inventoryProd.name;
		inventoryProd.licenseYear = privilege.licenseYear;
		
		// Login info for LM
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		// Download print file path
		path =logger.getLogPath();
	}

	/** Verify the inventory type alias and the number are displayed on the print file */
	private void verifyPrivInvNumInPrintFile(String ordNum, String invType, String invNum) {
		String privNum = hf.getPrivilegeNumByOrdNum(schema, ordNum);
		String msg = invType + ":";
		File file = new File(this.fullFileName);
		
		if(!file.exists()) {
			throw new ItemNotFoundException("Can't find print privileges PDF file - " + this.fullFileName);
		}

		List<String> lineContents = PDFParser.getPDFContentInOrder(file.getAbsolutePath());
		boolean found=false;
		for(int i = 0; i < lineContents.size(); i++) {
			String line = lineContents.get(i);
			logger.info("Text line in PDF is -->"+line);
			if(line.toUpperCase().contains(privNum))
			{
				found = lineContents.get(i+1).trim().equalsIgnoreCase(msg) && lineContents.get(i+2).trim().equalsIgnoreCase(invNum);
				break;
			}
		}
		
		if (!found) {
			throw new ErrorOnPageException("The inventory number info " + msg + invNum + " is not displayed in print file!");
		}
		
		logger.info("---Successfully verify the inventory number info in print file!");
	}
}
