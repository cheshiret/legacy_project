/*
 * Created on Mar 4, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.campingUnits;

import com.activenetwork.qa.awo.datacollection.legacy.CampingUnit;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvMgrCampingUnitDetailsPage extends InventoryManagerPage {
  
  private static InvMgrCampingUnitDetailsPage _instance = null;

	public static InvMgrCampingUnitDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrCampingUnitDetailsPage();
		}

		return _instance;
	}

	protected InvMgrCampingUnitDetailsPage() {

	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
//		RegularExpression rex = new RegularExpression("^Name.*Description.*Equipment Required Max Camping Units.* Assigned Sites and Loops$", false);
		return browser.checkHtmlObjectExists(".class","Html.A",".text","Camp Unit Combo Details");
//		RegularExpression rex = new RegularExpression(".*Name.*Description.*Equipment Required.*Max Camping Units.*Applies To.*", false);
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".text",rex);
	}
	
	/** set camping name */
	public void setCampingName(String cName){
	   browser.setTextField(".id","CampingComboView.name",cName);
	}
	
	/** set description */
	public void setDescription(String description){
	   browser.setTextArea(".id","CampingComboView.description",description);
	}
	
	/**
	 *                      Boat  FifthWheel  RV/Motorhome  PopUp  Tent  Trailer  Caravan/CamperVan PowerBoat
	 * @param typeIndex_1:  0     1           2             3      4     5        6                 7
	 * @param typeIndex_2:  2     4           7             8      9     10       12                14
	 * @param choiseIndex: 1--allowed 2--Allow for special 3--not allowed
	 */
	public void selectCampingUnits(int typeIndex_1, int typeIndex_2, int choiseIndex){
	   browser.selectRadioButton(".id","row_"+typeIndex_1+"_radiogroup",".value",typeIndex_2+"_"+choiseIndex);
	}
	
	/**
	 * Select radio of 'Applies To' section
	 * @param index  0---Whole Facility, 1---Assigned Sites and Loops  
	 */
	public void selectAppliesTo(int index){
		   browser.selectRadioButton(".id","CampingComboView.entireFacility",index);
	}
	
	/** set camping unit */
	public void setupCampingUnit(CampingUnit camp){
		if(null!=camp.name && camp.name.length()>0){
			this.setCampingName(camp.name);
		}
		if(null!=camp.description && camp.description.length()>0){
			this.setDescription(camp.description);
		}
		if(camp.isEquipReq){
			this.selectEquipReqCheckBox();
		}
		if(null!=camp.maxOfAllCombined && camp.maxOfAllCombined.length()>0){
			this.setMaxOfAllCombined(camp.maxOfAllCombined);
		}
		if(null!=camp.maxOfSpecialCombined && camp.maxOfSpecialCombined.length()>0){
			this.setMaxOfSpecialCombined(camp.maxOfSpecialCombined);
		}
		if(camp.boatIndex!=-1){
			this.selectCampingUnits(0,2,camp.boatIndex);
		}
		if(camp.fifthWheelIndex!=-1){
			this.selectCampingUnits(1,4,camp.fifthWheelIndex);
		}
		if(camp.rvOrMotorhomeIndex!=-1){
			this.selectCampingUnits(2,7,camp.rvOrMotorhomeIndex);
		}
		if(camp.popUpIndex!=-1){
			this.selectCampingUnits(3,8,camp.popUpIndex);
		}
		if(camp.tentIndex!=-1){
			this.selectCampingUnits(4,9,camp.tentIndex);
		}
		if(camp.trailerIndex!=-1){
			this.selectCampingUnits(5,10,camp.trailerIndex);
		}
		if(camp.caravanIndex!=-1){
			this.selectCampingUnits(6,12,camp.caravanIndex);
		}
		if(camp.powerBoatIndex!=-1){
			this.selectCampingUnits(7,14,camp.powerBoatIndex);
		}
		if(camp.isAssignedSitesAndLoops){
			this.selectAppliesTo(1);
		}
	}
	
	/** click ok button */
	public void clickOK(){
	    browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	/** click Apply button */
	public void clickApply(){
	    browser.clickGuiObject(".class","Html.A",".text","Apply");
	}
	
	/**
	 * Get comboID 
	 * @return
	 */
	public String getComboID(){
		String comboID = "";
  		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Configure Unit Combos.*",false));
		if(objs.length>0){
//			ITable table = (ITable)objs[1];   //for debug case "testcases.regression.basic.orms.inventory.AddCampingUnitCombo"
			IHtmlTable table = (IHtmlTable)objs[0];
			if(table.rowCount()>0){
				comboID = table.getCellValue(0, 2).split("ID")[1].trim();
			}else throw new ErrorOnDataException("Table row count less than 0.");

		}else throw new ItemNotFoundException("Object doesn't exist.");
		
  		Browser.unregister(objs);
		return comboID;
	}
	
	/** Click camping units tab */
	public void clickCampingUnitsTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Camping Units");
	}
	
	/** Select the check box "Equipment Required" */
	public void selectEquipReqCheckBox(){
		browser.clickGuiObject(".id", "CampingComboView.equipmentRequired");
	}
	
	/**
	 * Set 'Max of All Combined'
	 * @param maxOfAllCombined
	 */
	public void setMaxOfAllCombined(String maxOfAllCombined){
		browser.setTextField(".id","CampingComboView.maxTotal",maxOfAllCombined);
	}
	
	/**
	 * Set 'Max of Special Combined'
	 * @param maxOfSpecialCombined
	 */
	public void setMaxOfSpecialCombined(String maxOfSpecialCombined){
		browser.setTextField(".id","CampingComboView.maxSpecial",maxOfSpecialCombined);
	}
	
	/** Click site */
	public void clickSites(){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("\"CampingUnitSites.do.*", false));
	}
	
	/** Click loops */
	public void clickLoops(){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("\"CampingUnitLoops.do.*", false));
	}
	
	/**
	 * Check button 'View Change Request Items' exist or not
	 * @return
	 */
	public boolean checkViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", rex);
	}
	
	/**
	 * Check warning message exist or not
	 * @param textValue
	 * @return
	 */
	public boolean checkWarningMessage(String textValue){
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", textValue);
	}
	
	/** Click the button 'View Change Request Items' */
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}
}
