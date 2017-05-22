package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.RegistrationInfo;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @author ssong
 * @date Dec 6, 2011
 */
public class LicMgrVehicleRegistrationsPage extends LicMgrVehicleDetailPage{
	
	private static LicMgrVehicleRegistrationsPage _instance = null;
	
	protected LicMgrVehicleRegistrationsPage(){
		
	}
	
	public static LicMgrVehicleRegistrationsPage getInstance(){
		if(_instance == null){
			_instance = new LicMgrVehicleRegistrationsPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text",new RegularExpression("^Registration ID Status Customer.*",false));
	}
	
	public List<RegistrationInfo> getVehicleRegistrations(){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^Registration ID Status Customer.*",false));
		IHtmlTable grid = (IHtmlTable)objs[objs.length-1];

		RegistrationInfo reg;
		List<RegistrationInfo> regs = new ArrayList<RegistrationInfo>();
		
		for(int i=1;i<grid.rowCount();i++){
			reg = new RegistrationInfo();
			reg.id = grid.getCellValue(i, 0);
			reg.status = grid.getCellValue(i, 1);
			reg.customer = grid.getCellValue(i, 2);
			reg.product = grid.getCellValue(i, 3);
			reg.validFromDate = grid.getCellValue(i, 4);
			reg.validToDate = grid.getCellValue(i, 5);
			reg.numOfDuplicates = grid.getCellValue(i, 6);
			regs.add(reg);
		}
		Browser.unregister(objs);
		return regs;
	}

	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public boolean verifyVehicleRegistrationInfo(RegistrationInfo reg){
		List<RegistrationInfo> registrations = this.getVehicleRegistrations();
		
		if(null != registrations && registrations.size()>0){
			int i=0;
			for(; i<registrations.size(); i++){
				String status = registrations.get(i).status;
				String customer = registrations.get(i).customer.replaceAll("\\s+", "");
				String product = registrations.get(i).product;
				String validFrom = registrations.get(i).validFromDate;
				String validTo = registrations.get(i).validToDate;
				String duplicateNum = registrations.get(i).numOfDuplicates;
				
				if(reg.status.equals(status)
				&& reg.customer.replaceAll("\\s+", "").equals(customer)//
				&& reg.product.equals(product)
				&& (!StringUtil.isEmpty(reg.validFromDate) && DateFunctions.compareDates(reg.validFromDate, validFrom)==0)
				&& (!StringUtil.isEmpty(reg.validToDate) && DateFunctions.compareDates(reg.validToDate, validTo)==0)
				&& reg.numOfDuplicates.equals(duplicateNum)){
					logger.info("Registration detail info has been verified successfully.");
					return true;
				}
			}
		}else{
			throw new ErrorOnPageException("Could not find any vehicle Registration info on page.");
		}
	
		return false;
	}
	
	/**
	 * Click registration ID link
	 * @param regisID
	 * @author Lesley Wang
	 * @date Jun 11, 2012
	 */
	public void clickRegID(String regisID) {
		browser.clickGuiObject(".class", "Html.A", ".text", regisID);
	}
	/**
	 * verify the owner customer exist.
	 * @param custInfo
	 */
	public void verifyActiveOwnerCustExist(String custInfo,String status){
		boolean isExist = false;
		List<RegistrationInfo> registrations = this.getVehicleRegistrations();
		if(null != registrations && registrations.size()>0){
			for(int i =0;i<registrations.size();i++){
				if(custInfo.equals(registrations.get(i).customer)&& status.equals(registrations.get(i).status)){
					isExist = true;
				}
			}
		}
		if(!isExist){
			throw new ErrorOnDataException("No customer info exist.");
		}else{
			logger.info("The register customer info exist.");
		}
	}
}
