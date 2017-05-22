package com.activenetwork.qa.awo.pages.orms.licenseManager.fileimports;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.FileImportInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrFileImportsListCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;

/**
 * @Description: Point Imports List Page in License Manager. Admin -> File Imports -> Point Imports
 * 
 * @author Lesley Wang
 * @Date  Sep 29, 2013
 */
public class LicMgrPointImportsPage extends LicMgrFileImportsListCommonPage {
	private static LicMgrPointImportsPage instance=null;
	
	protected LicMgrPointImportsPage(){}
	
	public static LicMgrPointImportsPage getInstance(){
		if(instance == null){
			instance=new LicMgrPointImportsPage();
		}
		return instance;
	}
	/** Page Object Property Definition Begin */
	protected Property[] pointImportTable() {
		return Property.concatPropertyArray(table(), ".id", "HFPointImportsUI");
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.pointImportTable());
	}
	
	public String getPointImportID(int index) {
		return this.getFileImportID(index, this.pointImportTable());
	}
	
	public String getFirstPointImportID() {
		return this.getPointImportID(1);
	}
	
	public boolean comparePointImportResult(FileImportInfo fileImport) {
		return this.compareFileImportResult(this.pointImportTable(), fileImport);
	}
	
	public void verifyPointImportResult(FileImportInfo fileImport) {
		if (!this.comparePointImportResult(fileImport)) {
			throw new ErrorOnPageException("Point Import result is wrong!");
		}
		logger.info("---Successfully verify Point import result!");
	}
	
	public void waitForImportCompleted(String importID) {
		this.waitForImportCompleted(importID, this.pointImportTable());
	}
}
