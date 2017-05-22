/*
 * $Id: InvMgrCampingUnitPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.campingUnits;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class InvMgrCampingUnitPage extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrCampingUnitPage
	 * Generated     : Jun 20, 2005 3:33:13 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/06/20
	 */

	private static InvMgrCampingUnitPage _instance = null;

	public static InvMgrCampingUnitPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrCampingUnitPage();
		}

		return _instance;
	}

	protected InvMgrCampingUnitPage() {

	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A", ".text", "Camping Units", ".href", new RegularExpression("CampingUnitList.do", false)));
	}

	/**Click Add new combo*/
	public void clickAddNewCombo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New Combo", true);
	}

	/**Select page name*/
	public void selectPageName(String name) {
		browser.selectDropdownList(".id", "page_name", name);
	}
	
	/**
	 * Select combo name
	 * @param index
	 */
	public void selectComboName(int index){
	  browser.selectCheckBox(".id","row_"+index+"_checkbox");
	}
	
	/**
	 * Select combo check box via combo ID 
	 * @param comoID
	 */
	public void selectComboCheckBox(String comoID){
		  browser.selectCheckBox(".class","Html.INPUT.checkbox",".value",comoID);
		}
	
	/**
	 * Select combo name check boxs via combo name 
	 * @param combos
	 */
	public void selectComboNameCheckBoxs(String[] combos){
		if(null ==combos || combos.length<0){
			return;
		}else{
			for(int i=0; i<combos.length; i++){
				selectComboCheckBox(combos[i]);
			}
		}
	}
	
	public void clickDelete(){
	  browser.clickGuiObject(".class","Html.A",".text","Delete");
	}
	
	public int getComboNameRow(String comboName){
		  IHtmlObject[] comboTable = browser.getTableTestObject(".class","Html.TABLE",".text",new RegularExpression("(^Combo ID Combo Name Equip.Req)|(COMBO ID COMBO NAME EQUIP.REQ).*",false));
	      IHtmlTable table = (IHtmlTable) comboTable[0];
	     int comboRow = table.findRow(2, comboName);
	      Browser.unregister(comboTable);
	      return comboRow;
	}
	
    /**
	 * Get warning message
	 * @return
	 */
	public String getWarningMessage(){
		String warningMessage = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".className", "message msgerror");
		if(objs.length>0){
			warningMessage =objs[0].getProperty(".text").toString();
		}else throw new ObjectNotFoundException("Object can't find.");
		
		Browser.unregister(objs);
		return warningMessage;
	}
	
	/**
	 * Click Combo ID 
	 * @param comboID
	 */
    public void clickComboID(String comboID){
    	browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("\"CampingComboView\".+\""+comboID+"\"", false));
    }	
    
	/** Click the button 'View Change Request Items'*/
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}

	public void turnToFirstPage(){
		this.clickFirst();
		ajax.waitLoading();
		this.waitLoading();
	}
	public void turnToLastPage(){
		this.clickLast();
		ajax.waitLoading();
		this.waitLoading();
	}
	String firstButtonPattern = "(\\s?)+First$";
	String lastButtonPattern = "^Last.*";
	String previoustButtonPattern = "^Previous.*";
	String nextButtonPattern = "^Next.*";
	public boolean isButtonExist(String buttonPattern){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression(buttonPattern, false));
	}
	
	public void clickButton(String buttonPattern){
		if(this.isButtonExist(buttonPattern)){
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(buttonPattern, false));
		}
	}
	
	public void clickFirst(){
		this.clickButton(firstButtonPattern);
	}
	
	public void clickLast(){
		this.clickButton(lastButtonPattern);
	}
	
	public void clickPrevious(){
		this.clickButton(previoustButtonPattern);
	}
	
	public void clickNext(){
		this.clickButton(nextButtonPattern);
	}
	
	public void turnToSiteExistPage(String siteId){
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		int rowIndex = 0;
		
		int i = 0;
		do {
			if(i!=0){
				this.clickNext();
				ajax.waitLoading();
				this.waitLoading();
			}
			objs = browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("(^Combo ID Combo Name Equip.Req)|(COMBO ID COMBO NAME EQUIP.REQ).*",false));;
			table = (IHtmlTable)objs[0];
			
			rowIndex = table.findRow(1, siteId);
			i +=1;
		} while(rowIndex < 1);
		
		Browser.unregister(objs);
	}
}
