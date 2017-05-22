package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodHuntAssignmentInfo;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrDatePeriodHuntsAssignmentListPage extends LicMgrDatePeriodsDetailPage {
	private static LicMgrDatePeriodHuntsAssignmentListPage _instance = null;
	
	private LicMgrDatePeriodHuntsAssignmentListPage(){}
	
	public static LicMgrDatePeriodHuntsAssignmentListPage getInstance(){
		if(null ==_instance ){
			_instance = new LicMgrDatePeriodHuntsAssignmentListPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".id", "AssignmentGrid_LIST");
	}
	
	public void selectFilterStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue",false), status);
	}
	
	public List<String> getFilterOption(){
		return browser.getDropdownElements(".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue",false));
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A",".text","Go");
	}

	public IHtmlObject[] getHuntAssignListTableObject(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "AssignmentGrid_LIST");

		if(objs.length<1){
			throw new ItemNotFoundException("Did not found hunt assignment list table object.");
		}
		
		return objs;
	}
	
	public void selectHuntInfoCheckBox(String huntInfo){
		PagingComponent turningPage = new PagingComponent();
		IHtmlObject[] objs = null;
		IHtmlTable table = null;
		
		do {
			objs = getHuntAssignListTableObject();
			table = (IHtmlTable)objs[0];
			int row = table.findRow(2, huntInfo);
			if(row>0){
				browser.selectCheckBox(".id", new RegularExpression("HuntView-\\d+\\.assign",false), row-1);
				break;
			}else{
				if(!turningPage.nextExists()){
					throw new ItemNotFoundException("Did not found the hunt assignment info, which hunt info = " + huntInfo );
				}
			}
			
		} while(turningPage.clickNext());
		
		Browser.unregister(objs);
	}
	
	public void selectHuntInfoCheckBox(int index){
		browser.selectCheckBox(".id", new RegularExpression("HuntView-\\d+\\.assign",false), index);
	}
	
	public void selectAllHuntsInfoCheckBox(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("( )?Assigned( )?Hunt( )?Species( )?Species.*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found assignment list table head row.");
		}
		browser.selectCheckBox(".class", "Html.INPUT.checkbox", 0, true, objs[0]);
	}
	
	public void clickAssign(){
		browser.clickGuiObject(".class", "Html.A",".text","Assign");
	}
	
	public void clickUnassign(){
		browser.clickGuiObject(".class", "Html.A",".text","Unassign");
	}
	
	public int getAssignmentCount(){
		int count = -1;
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Hunts(\\(\\d+\\))?", false), 
				".id", new RegularExpression("DetailsTab_\\d+",false)));
		String text = objs[0].text();
		if(text.contains("(")){
			count = Integer.valueOf(text.split("\\(")[1].split("\\)")[0]);
		}else{
			count = 0;
		}
		Browser.unregister(objs);
		return count;
	}
	
	public boolean checkHuntInfoIsExisting(String huntInfo){
		PagingComponent turningPage = new PagingComponent();
		IHtmlObject[] objs = null;
		IHtmlTable table = null;
		boolean isExisting = false;
		
		do {
			objs = getHuntAssignListTableObject();
			table = (IHtmlTable)objs[0];
			int row = table.findRow(2, huntInfo);
			if(row>0){
				isExisting = true;
				break;
			}
			
		} while(turningPage.clickNext());
		
		// go back to first page, make sure next action will be correct.
		turningPage.clickFirst();

		Browser.unregister(objs);
		return isExisting;
	}
	
	public DatePeriodHuntAssignmentInfo getDatePeriodHuntAssignmentInfo(String huntInfo){
		PagingComponent turningPage = new PagingComponent();
		DatePeriodHuntAssignmentInfo assinmentInfo = new DatePeriodHuntAssignmentInfo();
		IHtmlObject[] objs = null;
		IHtmlTable table = null;
		
		do {
			objs = getHuntAssignListTableObject();
			table = (IHtmlTable)objs[0];
			int row = table.findRow(2, huntInfo);
			if(row>0){
				assinmentInfo.setAssignStatus(table.getCellValue(row, 1));
				assinmentInfo.setHunt(table.getCellValue(row, 2));
				assinmentInfo.setSpecies(table.getCellValue(row, 3));
				assinmentInfo.setSpeciesSubType(table.getCellValue(row, 4));
				assinmentInfo.setWeapon(table.getCellValue(row, 5));
				assinmentInfo.setHuntLocation(table.getCellValue(row, 6));
				assinmentInfo.setDatePeriod(table.getCellValue(row, 7));
				
				break;
			}else{
				if(!turningPage.nextExists()){
					throw new ItemNotFoundException("Did not found the hunt assignment info, which hunt info = " + huntInfo );
				}
			}
			
		} while(turningPage.clickNext());
		
		Browser.unregister(objs);
		return assinmentInfo;
	}
	
	public boolean compareDatePeriodHuntAssginmentInfo(DatePeriodHuntAssignmentInfo expAssignmentInfo){
		boolean result = true;
		DatePeriodHuntAssignmentInfo actAssignmentInfo = this.getDatePeriodHuntAssignmentInfo(expAssignmentInfo.getHunt());
		
		result &= MiscFunctions.compareResult("Date Period Hunt Assignment Status", expAssignmentInfo.getAssignStatus(), actAssignmentInfo.getAssignStatus());
		result &= MiscFunctions.compareResult("Date Period Hunt Assignment Hunt Info", expAssignmentInfo.getHunt(), actAssignmentInfo.getHunt());
		result &= MiscFunctions.compareResult("Date Period Hunt Assignment Species", expAssignmentInfo.getSpecies(), actAssignmentInfo.getSpecies());
		result &= MiscFunctions.compareResult("Date Period Hunt Assignment Species Sub-Type", expAssignmentInfo.getSpeciesSubType(), actAssignmentInfo.getSpeciesSubType());
		result &= MiscFunctions.compareResult("Date Period Hunt Assignment Weapon", expAssignmentInfo.getWeapon(), actAssignmentInfo.getWeapon());
		result &= MiscFunctions.compareResult("Date Period Hunt Assignment Hunt Location", expAssignmentInfo.getHuntLocation(), actAssignmentInfo.getHuntLocation());
		result &= MiscFunctions.compareResult("Date Period Hunt Assignment Date Period", expAssignmentInfo.getDatePeriod(), actAssignmentInfo.getDatePeriod());
		
		return result;
	}
	
	private List<String> getAssignmentColumnListValue(String columnName){
		PagingComponent turningPage = new PagingComponent();
		List<String> columnValues = new ArrayList<String>();
		IHtmlObject[] objs;
		IHtmlTable table;

		do{
			objs = this.getHuntAssignListTableObject();
			table = (IHtmlTable)objs[0];
			int column = table.findColumn(0, columnName);

			List<String> values = table.getColumnValues(column);
			values.remove(0);

			columnValues.addAll(values);
		}while(turningPage.clickNext());
		
		turningPage.clickFirst();
		Browser.unregister(objs);
		return columnValues;
	}
	
	public List<String> getAssignedStatusColumnListValue(){
		return this.getAssignmentColumnListValue("Assigned");
	}
	
	public List<String> getDatePeriodColumnListValue(){
		return this.getAssignmentColumnListValue("Date Period");
	}

	public boolean hasNext() {
		boolean exist = browser.checkHtmlObjectEnabled(".class","Html.A",".text", "Next");
		if(exist) {
			clickNext();
			ajax.waitLoading();
		}
		
		return exist;
	}
	
	public void clickNext(){
		browser.clickGuiObject(".class","Html.A",".text", "Next");
	}
	
	public void clickFirst(){
		browser.clickGuiObject(".class","Html.A",".text", "First");
	}
}
