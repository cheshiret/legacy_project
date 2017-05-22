package com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt;

import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 8, 2012
 */
public class OrmsPOSInventoryManagementPage extends OrmsPOSCommonPage {
	
private static OrmsPOSInventoryManagementPage _instance = null;
	
	private OrmsPOSInventoryManagementPage() {}
	
	public static OrmsPOSInventoryManagementPage getInstance(){ 
		if(null == _instance){
			_instance = new OrmsPOSInventoryManagementPage();
		}
		return _instance;
	}
	
    public boolean exists() {                                           
	  return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "posinvlist_LIST");
	}
    
    public void setProductName(String name){
    	browser.setTextField(".id", new RegularExpression("(FacilityWarehouse|FacWah)POSSearchCriteria.prdName",false), name);
    	//FacilityWarehousePOSSearchCriteria.prdName
    }
    
    public void setProductGroup(String group){
    	browser.setTextField(".id", "FacWahPOSSearchCriteria.prdGrp", group);
    }
    
    public void selectProductClass(String prdClass){
    	if(StringUtil.isEmpty(prdClass)){
    		browser.selectDropdownList(".id", "FacWahPOSSearchCriteria.prdClass", 0);
    	}else{
    		browser.selectDropdownList(".id", "FacWahPOSSearchCriteria.prdClass", prdClass);
    	}
    }
    
    public void selectProductSubClass(String subClass){
    	if(StringUtil.isEmpty(subClass)){
    		browser.selectDropdownList(".id", "FacWahPOSSearchCriteria.prdSubClass", 0);
    	}else{
    		browser.selectDropdownList(".id", "FacWahPOSSearchCriteria.prdSubClass", subClass);
    	}
    }
    
    public void selectInvType(String invType){
    	if(StringUtil.isEmpty(invType)){
    		browser.selectDropdownList(".id", "FacWahPOSSearchCriteria.invType", 0);
    	}else{
    		browser.selectDropdownList(".id", "FacWahPOSSearchCriteria.invType", invType);
    	}
    }
    
    public void setQtyOnHand(String qty){
    	browser.setTextField(".id", "FacWahPOSSearchCriteria.qtyOnHandForInput", qty);
    }
    
    public void clickGo(){
    	browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$", false),false,0,this.getTransactionFrame()[0]);
    }
    
    public void clickAdjustInventory(){
    	browser.clickGuiObject(".class", "Html.A", ".text", "Adjust Inventory");
    }
    
    public void clickRequestStockTransfer(){
    	browser.clickGuiObject(".class", "Html.A", ".text", "Request Stock Transfer");
    }
    
    public void clickStockTransfer(){
    	browser.clickGuiObject(".class", "Html.A", ".text", "Stock Transfers");
    }
    
    public void clickInventoryTracking(){
    	browser.clickGuiObject(".class", "Html.A", ".text", "Inventory Tracking");
    }
    
    public void selectProductID(String id){
    	browser.selectRadioButton(".value", id);
    }
    
    public void clickAllocateSerializePos(){
    	browser.clickGuiObject(".class","Html.A",".text","Allocate Serialized POS");
    }
    
    public void searchPosInvByName(String name){
    	this.setProductName(name);
    	this.clickGo();
    	ajax.waitLoading();
    	this.waitLoading();
    }
    
    public IHtmlObject[] getPOSInvMgtTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "posinvlist_LIST");
		if(objs.length<1) {
			throw new ItemNotFoundException("Could not get POS inventory on page.");
		}
		return objs;
	}
    
    /**
	 * Get pos QtyOnHand by pos name
	 * @param name
	 * @return
	 */
	public String getQtyOnHandByName(String name){
		IHtmlObject[] objs = getPOSInvMgtTable();
		IHtmlTable invTable  = (IHtmlTable)objs[0];
		int row = invTable.rowCount();
		if(row < 1){
			throw new ErrorOnPageException("There is no pos inventory info by name "+name);
		}
		int col = invTable.findColumn(0, "Qty On Hand");
		String qtyOnHand = invTable.getCellValue(1, col);
		Browser.unregister(objs);
		return qtyOnHand;
	}
	
	/**
	 * 
	 * @param product
	 * @param qtyOnHand
	 */
	public void verifyQtyOnHand(String product, String qtyOnHand){
		searchPosInvByName(product);
		String qtyOnHandUI = getQtyOnHandByName(product);
		if(StringUtil.compareNumStrings(qtyOnHand, qtyOnHandUI) != 0){
			throw new ErrorOnPageException("POS Qty On Hand display un-correctly on Pos Product Setup page.",
					qtyOnHand,
					qtyOnHandUI);
		}
		logger.info("POS Qty On Hand display correctly on Pos Product Setup page.");
	}
	
	public void clickQtyPendingAdjustment(String name){
		IHtmlObject[] objs = getPOSInvMgtTable();
		IHtmlObject[] tbodys = browser.getHtmlObject(".class", "Html.TBODY", objs[0]);
		IHtmlObject[] trs = browser.getHtmlObject(".class", "Html.TR", tbodys[0]);
		IHtmlObject[] tds = null;
		IHtmlTable invTable  = (IHtmlTable)objs[0];
		int row = invTable.rowCount();
		boolean found = false;
		if(row < 1){
			throw new ErrorOnPageException("There is no pos inventory info by name "+name);
		}
		
		for(int i=1; i<row; i++)
		{
			if(invTable.getCellValue(i, invTable.findColumn(0, "Product Name")).equalsIgnoreCase(name))
			{				
				found = true;
				tds = browser.getHtmlObject(".class", "Html.TD", trs[i-1]);
				int seq = invTable.findColumn(0, "Qty Pending Adjustment");
				browser.clickGuiObject(".class", "Html.A",true, 0, tds[seq]);
			}
		}
		
		Browser.unregister(objs);
		Browser.unregister(tbodys);
		Browser.unregister(trs);
		Browser.unregister(tds);
		
		if(!found)
		{
			throw new ErrorOnPageException("Cannot find link 'Qty Pending Adjustment' of POS product-->"+name);
		}
	}
}
