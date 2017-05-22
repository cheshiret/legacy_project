package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrSystemConfigurationPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrLienCompanyChangeHistoryPage extends LicMgrSystemConfigurationPage{
	
	private static LicMgrLienCompanyChangeHistoryPage _instance = null;
	
	protected LicMgrLienCompanyChangeHistoryPage(){}
	
	public static LicMgrLienCompanyChangeHistoryPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrLienCompanyChangeHistoryPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".id", "lienCompanyHistory_LIST");
	}
	
	public IHtmlTable getChangeHistoryTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "lienCompanyHistory_LIST");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found lien company history table.");
		}
		
		IHtmlTable tableObjs = (IHtmlTable) objs[0];
//		Browser.unregister(objs);
		return tableObjs;
	}
	
	public List<ChangeHistory> getChangeHistoryLists(){
		IHtmlTable tableObjs = this.getChangeHistoryTable();
		List<ChangeHistory> historyList = new ArrayList<ChangeHistory>();
		
		for(int i=1; i<tableObjs.rowCount(); i++){
			ChangeHistory changeHistory = new ChangeHistory();
			
			changeHistory.changeDate = tableObjs.getCellValue(i, 0);
			changeHistory.object =  tableObjs.getCellValue(i, 1);
			changeHistory.action = tableObjs.getCellValue(i, 2);
			changeHistory.field =  tableObjs.getCellValue(i, 3);
			changeHistory.oldValue =  tableObjs.getCellValue(i, 4);
			changeHistory.newValue =  tableObjs.getCellValue(i, 5);
			changeHistory.user = tableObjs.getCellValue(i, 6);
			changeHistory.location = tableObjs.getCellValue(i, 7);
			
			historyList.add(changeHistory);
		}
		
		return historyList;
	}
	
	public void clickReturnToLienCompanyDetails(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Return To Lien Company Details");
	}
	
	public String getLienCompanyAddrID(){
		return browser.getTextFieldValue(".id", new RegularExpression("LienCompanyView-\\d+\\.id",false));
	}
	
	public String getLienCompanyName(){
		return browser.getTextFieldValue(".id", new RegularExpression("LienCompanyView-\\d+\\.lienCompanyName\\.name",false));
	}
	
	public String getAddress(){
		return browser.getTextFieldValue(".id", new RegularExpression("LienCompanyView-\\d+\\.address",false));
	}
	
	public String getCity(){
		return browser.getTextFieldValue(".id", new RegularExpression("LienCompanyView-\\d+\\.city",false));
	}
	
	public String getState(){
		return browser.getTextFieldValue(".id", new RegularExpression("LienCompanyView-\\d+\\.state\\.name",false));
	}
	
	public String getZip(){
		return browser.getTextFieldValue(".id", new RegularExpression("LienCompanyView-\\d+\\.zip",false));
	}
	
	public LienCompanyDetailsInfo getLienCompanyInfo(){
		LienCompanyDetailsInfo lienCompany = new LienCompanyDetailsInfo();
		
		lienCompany.lienCompanyAddrID = this.getLienCompanyAddrID();
		lienCompany.lienCompanyName = this.getLienCompanyName();
		lienCompany.address = this.getAddress();
		lienCompany.city = this.getCity();
		lienCompany.state = this.getState();
		lienCompany.zip = this.getZip();
		
		return lienCompany;
	}
	
	public void compareLienCompanyInfo(LienCompanyDetailsInfo expLienCompany){
		logger.info("Compare lien company info at history page.");
		LienCompanyDetailsInfo actLienCompany = this.getLienCompanyInfo();
		boolean result = true;

		result &= MiscFunctions.compareResult("Lien Company ID", expLienCompany.lienCompanyAddrID, actLienCompany.lienCompanyAddrID);
		result &= MiscFunctions.compareResult("Lien Company Name", expLienCompany.lienCompanyName, actLienCompany.lienCompanyName);
		result &= MiscFunctions.compareResult("Lien Company Address", expLienCompany.address, actLienCompany.address);
		result &= MiscFunctions.compareResult("Lien Company City", expLienCompany.city, actLienCompany.city);
		result &= MiscFunctions.compareResult("Lien Company State", expLienCompany.state, actLienCompany.state);
		result &= MiscFunctions.compareResult("Lien Company Zip", expLienCompany.zip, actLienCompany.zip);
		
		if(!result){
			throw new ErrorOnDataException("The lien company info is not correct at history page, please chekc log.");
		}
	}
	

}
