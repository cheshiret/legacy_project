/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.pos;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author ssong
 * @Date  Jan 24, 2014
 */
public class OrmsOpeningPassAllocationsWidget extends DialogWidget {
	private static OrmsOpeningPassAllocationsWidget _instance = null;
	
	private OrmsOpeningPassAllocationsWidget() {
		super("Opening Pass Allocations");
	}
	
	public static OrmsOpeningPassAllocationsWidget getInstance() {
		if(_instance == null) {
			_instance = new OrmsOpeningPassAllocationsWidget();
		}
		
		return _instance;
	}
	
	protected Property[] passName(){
		return Property.concatPropertyArray(input("text"),".id","FinSessionOpeningPassAllocationSearchCriteria.passName");
	}
	
	protected Property[] search(){
		return Property.concatPropertyArray(a(),".text","Search");
	}
	
	protected Property[] passNumCheckBox(){
		return Property.concatPropertyArray(input("checkbox"),".id",new RegularExpression("GenericGrid-\\d+\\.selectedItems", false));
	}
	
	protected Property[] allocateOpeningPass(){
		return Property.concatPropertyArray(a(),".text",new RegularExpression("Allocate Opening Passes|Confirm Allocations",false));
	}
	
	public void setPassName(String passName){
		browser.setTextField(passName(), passName);
	}
	
	public void clickSearch(){
		clickButtonByText("Search");
	}
	
	public int getPosPassNumIndex(String passNum){
		IHtmlObject[] objs = browser.getTableTestObject(".id","grid");
		ITable grid = (ITable)objs[0];
		
		int qty = grid.findRow(1, passNum);
		Browser.unregister(objs);
		
		return qty;
	}
	
	public void selectPassNum(String...passNums){
		for(String tmp:passNums){
			int rowIndex = getPosPassNumIndex(tmp);
			browser.selectCheckBox(passNumCheckBox(),rowIndex-1);
		}
	}
	
	public void clickAllocateOpenningPass(){
		browser.clickGuiObject(allocateOpeningPass());
	}
	
	public void allocateOpeningPass(String passName,String...passNum){
		setPassName(passName);
		clickSearch();
		ajax.waitLoading();
		selectPassNum(passNum);
		clickAllocateOpenningPass();
		ajax.waitLoading();
	}
	
}
