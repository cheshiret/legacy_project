package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import java.util.HashMap;
import java.util.Map.Entry;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosPrintBarcodePage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:This case is used to verify print pos barcode report info from warehouse.
 * @Preconditions:
 * 1. Should have an template in sanity machine
 * 2. These printed POS should have barcode
 * @SPEC:POS Product Print Bar Code.UCS
 * @Task#:Auto-971

 * @author VZhang
 * @Date Mar 28, 2012
 */
public class PrintPOSBarcode extends InventoryManagerTestCase{
	private HashMap<String, String> posInfos = new HashMap<String, String>();
	private String searchType = "",warehouseName = "";
	private String	comparedPath = "", posBarcodeTempPatch ="";
	private boolean correct = true;

	@Override
	public void execute() {
		im.loginInventoryManager(login);
		//goto warehouse search page
		im.gotoWarehousesSearchPg();
		//search warehouse
		im.searchWarehouse(searchType, warehouseName);
		//goto warehouse detail page
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		//goto print pos barcode page
		im.gotoPrintPOSBarcodePage();
		//search pos
		im.searchPOSInventoryFromPOSPrintBarcodePage(null, null, null);
		this.initialPathInfo();
        //print pos barcode
        String fileName = im.printPOSBarcodes(posInfos, comparedPath); // run and download the report
        //verify barcode info
		correct &= im.verifyPdfReport(posBarcodeTempPatch, fileName); // verify report by compare with given template file
		//verify the system shall set print number as null when after printing barcode
		correct &= this.verifyAfterPrintingValueClearValue(posInfos);
		
		if(!correct){
			throw new ErrorOnPageException("Some check point not correct. Please check logger info.");
		}
		
		im.logoutInvManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		searchType = "Warehouse Name";
		warehouseName = "WhousePrintBarcode";
		
	    posInfos.put("Season Pass Additional", "2");
	    posInfos.put("Season Pass Individual", "3");
	    posInfos.put("Swimming Child, Day", "1");	
	}
	
	private void initialPathInfo(){
		String project_path = AwoUtil.PROJECT_PATH;
		if (project_path.contains("\\")) {
			posBarcodeTempPatch = project_path.substring(0, project_path
					.lastIndexOf("\\"))
					+ "\\POSBarcodeTemplates";

		} else {
			String path = project_path.substring(0,
					project_path.lastIndexOf("/")).replace("/", "\\");
			posBarcodeTempPatch = path.substring(0, path.lastIndexOf("\\"))
			+ "\\POSBarcodeTemplates";
		}
		
		comparedPath = logger.getLogPath() + "/ComparedFile";
	}
	
	private boolean verifyAfterPrintingValueClearValue(HashMap<String, String> clearInfo){
		InvMgrPosPrintBarcodePage posPrintBarcodPg = InvMgrPosPrintBarcodePage.getInstance();
		boolean result = true;
		
		logger.info("Verify after printing the sytem shall clear the values.");
		for(Entry<String, String> e: clearInfo.entrySet()){
			boolean isSelected = posPrintBarcodPg.checkBoxIsSelected(e.getKey());
			
			if(isSelected){
				logger.error("The pos product check box should not selected, but actually is selected.");
				result &= false;
			}
			
			String numberOfUI = posPrintBarcodPg.getNumberOfPrint(e.getKey());
			if(!numberOfUI.equals("")){
				logger.error("The print number is not correct. Expect number should be '" + e.getKey() 
						+ "', but actually is '" + numberOfUI + "'");
				result &= false;
			}
		}
		
		return result;		
	}
}
