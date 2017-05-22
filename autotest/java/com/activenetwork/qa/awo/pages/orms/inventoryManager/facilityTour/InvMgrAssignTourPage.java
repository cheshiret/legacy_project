/*
 * Created on Mar 5, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.TourInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class InvMgrAssignTourPage extends InventoryManagerPage {

	private static InvMgrAssignTourPage _instance = null;

	public static InvMgrAssignTourPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrAssignTourPage();
		}
		return _instance;
	}

	protected InvMgrAssignTourPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id",
				"search_first_dropdown");
	}

	public void selectTourType(String type) {
		browser.selectDropdownList(".id", "search_first_dropdown", type);
	}

	public void selectTourCategory(String category) {
		browser.selectDropdownList(".id", "search_second_dropdown", category);
	}

	public void setSearchValue(String value) {
		browser.setTextField(".id", "search_input", value);
	}

	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");

	}

	public void selectAll() {
		browser.selectCheckBox(".name", "all_slct");
	}

	public void clickAssign() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Assign");
	}

	public void clickRemove() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove");
	}

	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply Recommended Order");//Quentin[20131106]
	}

	public void searchTourCode(String code) {
		selectTourType("All Tours");
		selectTourCategory("Tour Code");
		setSearchValue(code);
		clickSearch();
		waitLoading();
	}

	public String getAssignStatus() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Tour Code Tour Name Tour Type Active",
						false));
		String[] temp = objs[0].getProperty(".text").toString().split(" ");
		Browser.unregister(objs);

		String status = temp[temp.length - 5];

		return status;
	}

	/** Click on Tour Details link. */
	public void clickTourDetails() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Tour Details");
	}

	/**
	 * Parse and return the tours table
	 * 
	 * @return - the list of TourInfo
	 */
	public List<TourInfo> parseToursDetailsTable() {
		List<TourInfo> tours = new ArrayList<TourInfo>();
		IHtmlObject tourTab[] = browser.getTableTestObject(".text",
				new RegularExpression("^Tour Code Tour Name Tour Type Active",
						false));

		int rowNum = ((IHtmlTable) tourTab[0]).rowCount();
		int colNum = ((IHtmlTable) tourTab[0]).columnCount();

		for (int row = 0; row < rowNum; row++) {
			TourInfo tour = new TourInfo();
			for (int col = 0; col < colNum; col++) {
				String titleName[] = new String[colNum];
				titleName[col] = ((IHtmlTable) tourTab[0])
						.getCellValue(0, col).trim();

				if (row >= 1) {
					if (titleName[col].length() > 0) {
						if (titleName[col].equals("Tour Code")) {
							tour.tourCode = ((IHtmlTable) tourTab[0])
									.getCellValue(row, col).toString().trim();
						}
						if (titleName[col].equals("Tour Name")) {
							tour.tourName = ((IHtmlTable) tourTab[0])
									.getCellValue(row, col).toString().trim();
						}
						if (titleName[col].equals("Tour Type")) {
							tour.tourType = ((IHtmlTable) tourTab[0])
									.getCellValue(row, col).toString().trim();
						}
						if (titleName[col].equals("Active")) {
							tour.isActive = ((IHtmlTable) tourTab[0])
									.getCellValue(row, col).toString().trim();
						}
						if (titleName[col].equals("Assigned")) {
							tour.isAssigned = ((IHtmlTable) tourTab[0])
									.getCellValue(row, col).toString().trim();
						}
						if (titleName[col].equals("Description")) {
							tour.description = ((IHtmlTable) tourTab[0])
									.getCellValue(row, col).toString().trim();
						}
					}
				}
			}
			if (tour.tourCode != null && !tour.tourCode.equals("")) {
				tours.add(tour);
			}
		}
		Browser.unregister(tourTab);
		return tours;
	}
	
	

	public void searchTour(String type, String category, String value) {
		this.selectTourCategory(category);
		this.selectTourType(type);
		this.setSearchValue(value);
		this.clickSearch();
		this.waitLoading();
	}

	public List<String> getRecommendedOrder() {
		List<String> list = null;
		IHtmlObject[] objs = browser.getTextField(".name",
				new RegularExpression("^recommendedOrder.*", false));
		if (objs.length > 0) {
			list = new ArrayList<String>();
			for (IHtmlObject obj : objs) {
				list.add(obj.getProperty(".text"));
			}
		}
		Browser.unregister(objs);
		return list;
	}

	public void setRecommendedOrder(List<String> list) throws Exception {
		IHtmlObject[] objs = browser.getTextField(".name",
				new RegularExpression("^recommendedOrder.*", false));
		if (list.size() == objs.length) {
			for (int i = 0; i < list.size(); i++) {
				IText textfield = (IText) objs[i];
				textfield.setText(list.get(i));
			}
			Browser.unregister(objs);
		} else {
			Browser.unregister(objs);
			throw new Exception("Order not match with the text input.");
		}

	}

	public String getErrorMessage() {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.DIV",".className",new RegularExpression("^message.*", false));
		String msg = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return msg;
	}

	/**
	 * judge if the search results in current page are assigned
	 */
	public boolean isAllAssignedTour() {
		List<TourInfo> list = this.parseToursDetailsTable();
		for(TourInfo tour:list){
			if(tour.isAssigned.equalsIgnoreCase("No"))
				return false;
		}
		return true;
	}
	
	/**
	 * judge if the search results in current page are not assigned
	 */
	public boolean isAllNotAssignedTour(){
		List<TourInfo> list = this.parseToursDetailsTable();
		for(TourInfo tour:list){
			if(tour.isAssigned.equalsIgnoreCase("Yes"))
				return false;
		}
		return true;
	}
	
	public boolean isThisTourExist(String tourcode){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A",".text",tourcode);
		if(objs[0]==null){
			return false;
		}
	    return true;
	}
	
	public boolean isNextbuttonDisable(){
		boolean flag=false;
		flag = !browser.checkHtmlObjectExists(".class","Html.A",".text","Next");
		return flag;
	}
	
	public void clickNextButton(){
		browser.clickGuiObject(".class", "Html.DIV",".text","Next");
	}
	
	public boolean isFirstButtonDisabled(){
		boolean flag=false;
		flag = !browser.checkHtmlObjectExists(".class","Html.A",".text","First");
		return flag;
	}
	
	public void clickFirstButton(){
		browser.clickGuiObject(".class", "Html.DIV",".text","First");
	}
	
	public int getTourRow(String tourCode){
		int row=0;
		RegularExpression regx=new RegularExpression("^Tour Code Tour Name.*",false);
		IHtmlObject[] objs=browser.getTableTestObject(".text", regx);
		if(objs.length<1){
			throw new ObjectNotFoundException("Tour search result table is not found.");
		}
		
		IHtmlTable table=(IHtmlTable)objs[0];
		row=table.findRow(1, tourCode);
		Browser.unregister(objs);
		return row;
	}
	
	public void selectTour(String tourCode){
		RegularExpression regx=new RegularExpression("row_\\d+_checkbox",false);
		int row=this.getTourRow(tourCode);
		if(row<=0){
			throw new ItemNotFoundException("No search result is found: " + tourCode);
		}
		browser.selectCheckBox(".id", regx, row-1);
		
	}
	
	public static void main(String[] args){
		InvMgrAssignTourPage page=InvMgrAssignTourPage.getInstance();
		
		System.out.println(page.getTourRow("No_FeeSch"));
	}
	
}
