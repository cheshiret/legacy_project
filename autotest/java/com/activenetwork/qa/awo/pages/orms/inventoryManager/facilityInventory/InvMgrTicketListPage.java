/*
 * Created on Mar 5, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityInventory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
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
public class InvMgrTicketListPage extends InventoryManagerPage {
  private static InvMgrTicketListPage _instance = null;
	

	public static InvMgrTicketListPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrTicketListPage();
		}

		return _instance;
	}

	protected InvMgrTicketListPage() {
	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
	  return browser.checkHtmlObjectExists(".class","Html.SELECT",".id","search_inv_stat");
	}
	
	public void setStartDate(String sDate){
	  browser.setTextField(".id","search_inv_fromdate_ForDisplay",sDate);
	}
	
	public void setEndDate(String eDate){
	  browser.setTextField(".id","search_inv_todate_ForDisplay",eDate);
	}
	
	public void selectStatus(String status){
	  browser.selectDropdownList(".id","search_inv_stat",status); 
	}
	
	public void clickSearch(){
	  browser.clickGuiObject(".class","Html.A",".text","Search");
	}
	
	public List<String> getStatus(){
	  List<String> list=new ArrayList<String>();
	  
	  IHtmlObject[] comboTable = browser.getTableTestObject(".text",new RegularExpression("^Inv ID Created Date/Time Status Tour Tour Date Tour Time.*",false));
      IHtmlTable comboTableGrid = (IHtmlTable) comboTable[0];
      
      int row=comboTableGrid.rowCount();
      int column=comboTableGrid.columnCount();
      int col=0;
      
      for(int j=0;j<column;j++){
        if(null!=comboTableGrid.getCellValue(0,j)){
           if(comboTableGrid.getCellValue(0,j).toString().equals("Status")){
              col=j;
              break;
           }
        }
     }
      
      for(int i=0;i<row-1;i++){
        if(null!=comboTableGrid.getCellValue(i+1,col)){
          list.add(i,comboTableGrid.getCellValue(i+1,col).toString());
        }
        
      }
      Browser.unregister(comboTable);
      
      return list;
   }
}
