/*
 * Created on Oct 19, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.operationManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.BulletinInfo;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OpMgrBulletinSearchPage extends OperationsManagerPage {

	static private OpMgrBulletinSearchPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OpMgrBulletinSearchPage()
	{}
	
	static public OpMgrBulletinSearchPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OpMgrBulletinSearchPage();
		}

		return _instance;
	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {

		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Add New Bulletin");
	}

	/**Click add new bulletin button*/
	public void clickAddNewBulletin() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New Bulletin",
				true);
	}

	/**Select search criteria*/
	public void selectSearch(String criteria) {
		browser.selectDropdownList(".id", "BulletinSearchCriteria.search",
				criteria, true);
	}

	/**
	 * Input search name
	 * @param name
	 */
	public void setSearchName(String name) {
		browser.setTextField(".id", "BulletinSearchCriteria.searchValue", name,
				true);
	}

	/**
	 * Select search date including start date and end date
	 * @param date
	 */
	public void selectDate(String date) {
		browser.selectDropdownList(".id", "BulletinSearchCriteria.date", date,
				true);
	}

	/**
	 * Input the start date
	 * @param from
	 */
	public void setDateFrom(String from) {
		browser.setTextField(".id",
				"BulletinSearchCriteria.startdate_ForDisplay", from, true);
	}

	/**
	 * Input the end date
	 * @param to
	 */
	public void setDateTo(String to) {
		browser.setTextField(".id",
				"BulletinSearchCriteria.enddate_ForDisplay", to, true);
	}

	/**
	 * Select display what kind of bulletin, active or Inactive, all
	 * @param status
	 */
	public void selectShow(String status) {
		browser.selectDropdownList(".id", "BulletinSearchCriteria.show",
				status, true);
	}

	/**Select which kind of application*/
	public void selectApp(String app) {
		browser.selectDropdownList(".id", "BulletinSearchCriteria.application",
				app, true);
	}

	/**Select which kind of priority*/
	public void selectPriority(String priority) {
		browser.selectDropdownList(".id", "BulletinSearchCriteria.priority",
				priority, true);
	}

	/**Select specific bulletin*/
	public void selectSpecCheckBox(int index) {
		browser.selectCheckBox(".id", "row_" + index + "_checkbox");
	}

	/**Click activate button*/
	public void clickActivateButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate", true);
	}

	/**Click Deactivate button*/
	public void clickDeactivateButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate", true);
	}

	/**Click Go button*/
	public void clickGO() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false), true);
	}

	/**
	 * Set bulletin criteria
	 * @param bulle---The data colletion of bulletin
	 */
	public void setBulletinCriteria(BulletinInfo bulle) {
		if (bulle.headline.length()>0) {
			this.selectSearch("Headline");
			this.setSearchName(bulle.headline);
		}

		if (bulle.location.length()>0) {
			this.selectSearch("Location");
			this.setSearchName(bulle.location);
		}

		if (bulle.author.length()>0) {
			this.selectSearch("Author");
			this.setSearchName(bulle.author);
		}

		if (bulle.startdatefrom.length()>0 || bulle.startdateto.length()>0) {
			this.selectDate("Start Date");
			if (bulle.startdatefrom.length()>0) {
				this.setDateFrom(bulle.startdatefrom);
			}

			if (bulle.startdateto.length()>0) {
				this.setDateTo(bulle.startdateto);
			}
		}

		if (bulle.enddatefrom.length()>0 || bulle.enddateto.length()>0) {
			this.selectDate("End Date");

			if (bulle.enddatefrom.length()>0) {
				this.setDateFrom(bulle.enddatefrom);
			}
			if (bulle.enddateto.length()>0) {
				this.setDateTo(bulle.enddateto);
			}
		}

		if (bulle.status.length()>0) {
			this.selectShow(bulle.status);
		}

		if (bulle.application.length()>0) {
			this.selectApp(bulle.application);
		}

		if (bulle.priority.length()>0) {
			this.selectPriority(bulle.priority);
		}
	}

	/** Retrive all bulletin info in the bulletin search page */
	public List<List<String>> retriveBulletininfo() {
		List<List<String>> bulletininfo =new ArrayList<List<String>>();
		List<String> bulletininforow = null;
		
		do {
			RegularExpression reg = new RegularExpression("^ ?Bulletin ID.*", false);
			IHtmlObject[] bulletintable = browser.getTableTestObject(".text",reg);
			IHtmlTable bulletinTableGrid = (IHtmlTable) bulletintable[0];
			
			if(bulletinTableGrid.getRowValues(0)!=null){
				bulletininforow=bulletinTableGrid.getRowValues(0);
				bulletininfo.add(bulletininforow);
				
				for (int row = 1; row < bulletinTableGrid.rowCount(); row++) {
					bulletininforow = bulletinTableGrid.getRowValues(row);
					bulletininfo.add(bulletininforow);
				}
				Browser.unregister(bulletintable);
			} else throw new ErrorOnDataException("Data is null");	
		} while (gotoNext());
		
		return bulletininfo;
	}
	
	/**Retrive first page bulletin info in the bulletin search page */
	public List<List<String>> retriveFirstPageBulletininfo() {
		List<List<String>> bulletininfo =new ArrayList<List<String>>();
		List<String> bulletininforow = null;
		
		RegularExpression reg = new RegularExpression("^ ?Bulletin ID.*", false);
		IHtmlObject[] bulletintable = browser.getTableTestObject(".text",reg);
		IHtmlTable bulletinTableGrid = (IHtmlTable) bulletintable[0];
		
		if(bulletinTableGrid.getRowValues(0)!=null){
			bulletininforow=bulletinTableGrid.getRowValues(0);
			bulletininfo.add(bulletininforow);
			
			for (int row = 1; row < bulletinTableGrid.rowCount(); row++) {
				bulletininforow = bulletinTableGrid.getRowValues(row);
				bulletininfo.add(bulletininforow);
			}
			Browser.unregister(bulletintable);
		} else throw new ErrorOnDataException("Data is null");	
		
		return bulletininfo;
	}

	/**
	 * If "Next" button is avaliable, click next button in bulletin search page.
	 *
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".text", new RegularExpression("Next",false));
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			(objs[0]).click();
		}

		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
	}

	/**Get specific column's number*/
	public int getColNum(String colname) {
		List<List<String>> reservinfo = new ArrayList<List<String>>();
		List<String> reservinforow =new ArrayList<String>();
		int colcount = getTableColCount();
		
		reservinfo = retriveBulletininfo();
		reservinforow = reservinfo.get(0);
		for (int col = 0; col < colcount; col++) {
			if (reservinforow.get(col).toString().trim().equalsIgnoreCase(
					colname)) {
				return col;
			}
		}
		return -1;
	}

	/**get table's all columns*/
	public int getTableColCount() {
		RegularExpression reg = new RegularExpression("^Bulletin ID Headline Location*", false);
		IHtmlObject[] bulletintable = browser.getTableTestObject(".text", reg);
		IHtmlTable bulletinTableGrid = (IHtmlTable) bulletintable[0];
		int colcount = bulletinTableGrid.columnCount();

		Browser.unregister(bulletintable);
		return colcount;
	}

	/**This method manager bulletion in the bulletin search page, include activate and deactivate
	 * @param action---activate or deactivate
	 * */
	public void manageBulletin(String action) {
		logger.info("Activate or deactivate for bulletin");
		selectSpecCheckBox(0);

		if (action.equalsIgnoreCase("Activate")) {
			clickActivateButton();
		} else if (action.equalsIgnoreCase("Deactivate")) {
			clickDeactivateButton();
		} else
			throw new RuntimeException("can not find the action");
		this.waitLoading();
	}
	
	/*This method click the first bulletin ID in bulletin search result page, this is useful for search one result and want to goto the detail page*/
	public void clickFirstbulletinId() {	
		Property[] pro = new Property[1];
		pro[0] = new Property(".className","searchResult");
		
		browser.clickGuiObject(Property.atList(pro,Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("\\d+",false))));
	}
}
