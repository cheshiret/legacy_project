package com.activenetwork.qa.awo.pages.orms.licenseManager.fileimports;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.FileImportInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrFileImportsListCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;

/**
 * @Description: Unlocked Privileges Imports page in LM.  
 * @author Lesley Wang
 * @Date  Aug 5, 2013
 */
public class LicMgrUnlockedPriImportsPage extends LicMgrFileImportsListCommonPage {
	private static LicMgrUnlockedPriImportsPage instance=null;
	
	protected LicMgrUnlockedPriImportsPage(){}
	
	public static LicMgrUnlockedPriImportsPage getInstance(){
		if(instance == null){
			instance=new LicMgrUnlockedPriImportsPage();
		}
		return instance;
	}
	/** Page Object Property Definition Begin */
	protected Property[] unlockedPriImportTable() {
		return Property.toPropertyArray(".class", "Html.Table", ".id", "HFUnlockedPrivilegeImportsUI");
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.unlockedPriImportTable());
	}
	
//	private List<String> getUnlockedPriImportResult(int index) {
//		return this.getFileImportResult(index, this.unlockedPriImportTable());
//	}
//	
//	public List<String> getFirstUnlockedPriImportResult() {
//		return this.getUnlockedPriImportResult(1);
//	}
	
	public String getUnlockedPriImportID(int index) {
		return this.getFileImportID(index, this.unlockedPriImportTable());
	}
	
	public String getFirstUnlockedPriImportID() {
		return this.getUnlockedPriImportID(1);
	}
	
	public boolean compareUnlockedPriImportResult(FileImportInfo fileImport) {
		return this.compareFileImportResult(this.unlockedPriImportTable(), fileImport);
	}
	
	public void verifyUnlockedPriImportResult(FileImportInfo fileImport) {
		if (!this.compareUnlockedPriImportResult(fileImport)) {
			throw new ErrorOnPageException("Unlocked Privilege Import result is wrong!");
		}
		logger.info("---Successfully verify unlocked privilege import result!");
	}
	
	public void waitForImportCompleted(String importID) {
		this.waitForImportCompleted(importID, this.unlockedPriImportTable());
	}
}
