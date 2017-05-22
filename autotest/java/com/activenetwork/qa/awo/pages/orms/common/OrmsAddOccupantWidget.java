package com.activenetwork.qa.awo.pages.orms.common;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Nov 18, 2013
 */
public class OrmsAddOccupantWidget extends DialogWidget {

	protected OrmsAddOccupantWidget(){
		super("Add Occupants");
	}
	
	private static OrmsAddOccupantWidget _instance = null;
	
	public static OrmsAddOccupantWidget getInstance(){
		if(_instance == null){
			_instance = new OrmsAddOccupantWidget();
		}
		return _instance;
	}

	protected Property[] numberOfNewOccupants() {
		return Property.toPropertyArray(".id",new RegularExpression("DropdownExt-\\d+.selectedValue",false));
	}
	
	private String prefix = "campingInfoContainer_Occupant_\\d+_occupant-row:";
	protected Property[] occupantType(int index) {
		return Property.toPropertyArray(".id",new RegularExpression(prefix+index+"_otherOccType",false));
	}
	
	protected Property[] firstName(int index) {
		return Property.toPropertyArray(".class","Html.INPUT",".id",new RegularExpression(prefix+index+"_otherOccFName",false));
	}

	protected Property[] lastName(int index) {
		return Property.toPropertyArray(".class","Html.INPUT",".id",new RegularExpression(prefix+index+"_otherOccLName",false));
	}

	protected Property[] addOccupantsButton() {
		return Property.toPropertyArray(".class","Html.A",".text", "OK");
	}

	/**
	 * After select this number, wait for several minutes, then the Occupant Type and Last Name and First Name will be displayed
	 * @param number
	 */
	public void selectNumberOfNewOccupants(String number){
		IHtmlObject[] table = browser.getHtmlObject(".id", new RegularExpression("checkBoxPanelBar_Occupant_\\d+", false));
		browser.selectDropdownList(numberOfNewOccupants(), number, true, table[1]);
	}
	
	public void selectOccupantType(int index, String type){
		browser.selectDropdownList(occupantType(index), type);
	}
	
	public void setFirstName(int index, String fName){
		browser.setTextField(firstName(index), fName);
	}

	public void setLastName(int index, String lName){
		browser.setTextField(lastName(index), lName);
	}
	
	public void clickAddOccupants(){
		browser.clickGuiObject(addOccupantsButton(), 1);
	}
	
	/**
	 * 
	 * @param numberOfOccupants
	 * @param occupantTypes
	 * @param fNames
	 * @param lNames
	 */
	public void addOccupants(List<String> occupantTypes, List<String> fNames, List<String> lNames){
		int number;
		if(occupantTypes==null||occupantTypes.size()<1){
			number = fNames.size();
		}else{
			number = occupantTypes.size();
		}
		
		//After select this number, wait for several seconds, then the Occupant Type and Last Name and First Name will be displayed
		this.selectNumberOfNewOccupants(String.valueOf(number));
		ajax.waitLoading();
		this.waitLoading();
		
		for(int i=1; i<number+1; i++){
			int index = i+2;//[Shane]20140630, the start index is 3 in 30601 
			if(occupantTypes!=null&&occupantTypes.size()>0){
				selectOccupantType(index, occupantTypes.get(i-1));
			}
			setFirstName(index, fNames.get(i-1));
			setLastName(index, lNames.get(i-1));
		}
		
		this.clickAddOccupants();
		ajax.waitLoading();
	}
	
	protected Property[] occupantQty(int index) {
		return Property.toPropertyArray(".id",new RegularExpression(prefix+index+"_otherOccQty",false));
	}
	
	public void setOccupantQty(int index, String qty){
		browser.setTextField(occupantQty(index), qty);
	}
	
	public void addOccupants(List<String> occupantTypes, List<String> qty){
		int number = occupantTypes.size();
		//After select this number, wait for several seconds, then the Occupant Type and Last Name and First Name will be displayed
		this.selectNumberOfNewOccupants(number+"");
		ajax.waitLoading();
		this.waitLoading();
		
		for(int i=1; i<number+1; i++){
			int index = i+2;//[Shane]20140630, the start index is 3 in 30601 
			setOccupantQty(index, qty.get(i-1));
			selectOccupantType(index, occupantTypes.get(i-1));
		}
		
		this.clickAddOccupants();
		ajax.waitLoading();
	}
	
}