/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.marina;

import java.util.List;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Jan 6, 2014
 */
public class OrmsSlipContractReservationPage extends OrmsSlipContractDetailsPage {
	private static OrmsSlipContractReservationPage _instance = null;
	
	protected OrmsSlipContractReservationPage() {}
	
	public static OrmsSlipContractReservationPage getInstance(){
		if(null == _instance){
			_instance = new OrmsSlipContractReservationPage();
		}
		
		return _instance;
	}
	
	protected Property[] reservationListTableProp(){
		return Property.concatPropertyArray(table(), ".id",new RegularExpression("grid_\\d+\\_LIST",false),".text",
				new RegularExpression("^( )?Reservation #( )?Invoice #.*",false));
	}
	
	protected Property[] reservationNumLinkProp(String resNum){
		return Property.concatPropertyArray(a(), ".text",resNum);
	}
	
	protected Property[] reservationNumColumnHeaderLinkProp(){
		return Property.concatPropertyArray(a(), ".text","Reservation #");
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(this.reservationListTableProp());
	}
	
	private IHtmlObject[] getReservationListTableObject(){
		IHtmlObject[] objs = browser.getHtmlObject(this.reservationListTableProp());
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found Reservation List Table Object.");
		}
		
		return objs;
	}
	
	private int getColumnNum(String columnName){
		IHtmlObject[] objs = this.getReservationListTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		
		int col = table.findColumn(0, columnName);
		Browser.unregister(objs);
		return col;
	}
	
	public int getReservationColumnNum(){
		int reservationColumn = this.getColumnNum("Reservation #");
		return reservationColumn;
	}
	
	
	private void clickTDLinkByReservationNum(String resNum,String columnName){
		IHtmlObject[] objs = this.getReservationListTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		
		int resColumn = this.getReservationColumnNum();
		int row = table.findRow(resColumn, resNum);
		int col = this.getColumnNum(columnName);
		if(row<1 || col<0){
			throw new ItemNotFoundException("Did not found Reservation row which reservation number = " + resNum 
					+ "; Or did not column by column name = " + columnName);
		}
		
		IHtmlObject[] trObjs = browser.getHtmlObject(tr(), table);
		IHtmlObject[] tdObjs = browser.getHtmlObject(td(), trObjs[row]);
		browser.clickGuiObject(a(), true, 0, tdObjs[col]);
		Browser.unregister(trObjs);
		Browser.unregister(tdObjs);
		Browser.unregister(objs);
	}
	
	
	public void clickChargeNumLinkByReservationNum(String resNum){
		this.clickTDLinkByReservationNum(resNum, "# Of Charges");
	}
	
	public void clickInvoidNumLinkByReservationNum(String resNum){
		this.clickTDLinkByReservationNum(resNum, "Invoice #");
	}
	
	public void clickReservationNumLink(String resNum){
		browser.clickGuiObject(this.reservationNumLinkProp(resNum));
	}
	
	public List<String> getReservationNumListValue(){
		IHtmlObject[] objs = this.getReservationListTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		
		int resColumn = this.getReservationColumnNum();
		List<String> value = table.getColumnValues(resColumn);
		
		Browser.unregister(objs);
		value.remove(0);
		return value;
	}
	
	public void clickReservationNumColumnHeaderLink(){
		browser.clickGuiObject(this.reservationNumColumnHeaderLinkProp());
	}
	
}
