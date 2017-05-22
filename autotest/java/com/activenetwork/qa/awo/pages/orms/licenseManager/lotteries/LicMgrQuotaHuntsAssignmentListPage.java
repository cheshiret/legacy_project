package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @Description: Quota assign hunt list page in License Manager: Lotteries -> Quota -> Click quota id --> click 'Hunt(*)' label
 * 
 * @author Phoebe Chen
 * @Date  Feb 14, 2014
 */
public class LicMgrQuotaHuntsAssignmentListPage extends LicMgrQuotaDetailsPage{
private static LicMgrQuotaHuntsAssignmentListPage _instance = null;
	
	private LicMgrQuotaHuntsAssignmentListPage(){}
	
	public static LicMgrQuotaHuntsAssignmentListPage getInstance(){
		if(null ==_instance ){
			_instance = new LicMgrQuotaHuntsAssignmentListPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".id", "huntQuotaAssignmentListGrid_LIST");
	}
	
	protected Property[] statusDropdownList() {
		return Property.toPropertyArray( ".id", new RegularExpression("HuntQuotaAssignmentFilter-\\d+\\.assignmentStatus",false));
	}
	protected Property[] goBtn() {
		return Property.concatPropertyArray(this.a(), ".text", "Go");
	}
	protected Property[] huntListTable() {
		return Property.concatPropertyArray(this.table(), ".id", "huntQuotaAssignmentListGrid_LIST");
	}
	protected Property[] huntCheckBox(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntQuotaAssignmentVO-\\d+\\.selected", false));
	}
	protected Property[] assignBtn(){
		return Property.concatPropertyArray(this.a(), ".text", "Assign");
	}
	
	public List<String> getFilterOption(){
		return browser.getDropdownElements(statusDropdownList());
	}
	
	public void selectFilterStatus(String status){
		browser.selectDropdownList(this.statusDropdownList(), status);
	}
	
	public void clickGo(){
		browser.clickGuiObject(goBtn());
	}

	public IHtmlObject[] getHuntAssignListTableObject(){
		IHtmlObject[] objs = browser.getTableTestObject(huntListTable());
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
				browser.selectCheckBox(huntCheckBox(), row-1);
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
		browser.selectCheckBox(huntCheckBox(), index);
	}
	
	public void selectAllHuntsInfoCheckBox(){
		IHtmlObject[] objs = getHuntAssignListTableObject();
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found assignment list table head row.");
		}
		browser.selectCheckBox(".class", "Html.INPUT.checkbox", 0, true, objs[0]);
	}
	
	public void clickAssign(){
		browser.clickGuiObject(assignBtn());
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
		
		
		Browser.unregister(objs);
		return isExisting;
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
	
	public List<String> getHuntColumnListValue(){
		return this.getAssignmentColumnListValue("Hunt");
	}
	
	public List<String> getAssignedStatusColumnListValue(){
		return this.getAssignmentColumnListValue("Assignment Status");
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
	
	public void searchHuntByStatus(String status){
		this.selectFilterStatus(status);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public String getHuntStatus(String huntInfo){
		PagingComponent turningPage = new PagingComponent();
		IHtmlObject[] objs = null;
		IHtmlTable table = null;
		int row = -1;
		do {
			objs = getHuntAssignListTableObject();
			table = (IHtmlTable)objs[0];
			row = table.findRow(2, huntInfo);
			if(row>0){
				break;
			}
			
		} while(turningPage.clickNext());
		String status = table.getCellValue(row, 1);
		Browser.unregister(objs);
		return status;
	}
	
	public boolean isHuntAssigned(String huntInfo){
		boolean assigned = false;
		String status = this.getHuntStatus(huntInfo);
		if(status.equalsIgnoreCase("Assigned")){
			assigned = true;
		}else if(status.equalsIgnoreCase("Unassigned")){
			assigned = false;
		}
		return assigned;
	}
	
	public void verifyHuntShowOrNot(boolean isShow, String huntInfo){
		boolean actShow = this.checkHuntInfoIsExisting(huntInfo);
		if(!(actShow==isShow)){
			throw new ErrorOnPageException("The hunt show status is not the same as expect", (isShow?"shown":"not shown"), (isShow?"not shown":"shown"));
		}
		logger.info("The hunt:" + huntInfo + " show status is correct as "+ (isShow?"shown":"not shown"));
	}
	
	public void verifyHuntAssignedOrNot(boolean isAssigned, String huntInfo){
		this.searchHuntByStatus("All Hunts");
		boolean actStatus = this.isHuntAssigned(huntInfo);
		if(! (actStatus==isAssigned)){
			throw new ErrorOnPageException("The hunt status is not the same as expect", (isAssigned?"Assigned":"Unassigned"), (isAssigned?"Unassigned":"Assigned"));
		}
		logger.info("The hunt:" + huntInfo + " status is correct as "+ (isAssigned?"Assigned":"Unassigned"));
	}
	
	public void assignHuntToQuota(String... huntInfo){
		for(String hunt:huntInfo){
			this.selectHuntInfoCheckBox(hunt);
		}
		this.clickAssign();
		ajax.waitLoading();
		this.waitLoading();
	}
}
