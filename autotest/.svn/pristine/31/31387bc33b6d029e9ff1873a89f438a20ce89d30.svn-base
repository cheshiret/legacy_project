package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  May 30, 2012
 */
public class LicMgrVehicleOrderSubPage extends LicMgrVehicleDetailPage{

	private static LicMgrVehicleOrderSubPage _instance = null;
	
	protected LicMgrVehicleOrderSubPage(){
		
	}
	
	public static LicMgrVehicleOrderSubPage getInstance(){
		if(_instance == null){
			_instance = new LicMgrVehicleOrderSubPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^( )*Order #.*",false));
	}
	
	public List<OrderInfo> getOrderInfo(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^( )*Order #.*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Can't find order info table...");
		}
		IHtmlTable orderTable = (IHtmlTable)objs[1];
		OrderInfo orderInfo = new OrderInfo();
		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
		for(int i=1; i<orderTable.rowCount(); i++){
			orderInfo.orderNum = orderTable.getCellValue(i, 0);
			orderInfo.receiptNum = orderTable.getCellValue(i, 1);
			orderInfo.orderDate = orderTable.getCellValue(i, 2);
			orderInfo.customer = orderTable.getCellValue(i, 3);
			orderInfo.saleLocation = orderTable.getCellValue(i, 4);
			orderInfo.orderPrice = orderTable.getCellValue(i, 5);
			orderInfo.balance = orderTable.getCellValue(i, 6);
			orderInfo.productPurchaseType = orderTable.getCellValue(i, 7);
			orderInfoList.add(orderInfo);
		}
		return orderInfoList;
	}
	
	public void clickOrdNum(String ordNum){
		browser.clickGuiObject(".class", "Html.A", ".text", ordNum);
	}
	/**
	 * get vehicle order info by order number.
	 * @param orderNum
	 * @return
	 */
	public String getVehicleOrderPurchaseTypeInfoByOrderNum(String orderNum){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^( )*Order #.*",false));
		String status = "";
		if(objs.length<1){
			throw new ItemNotFoundException("Can't find order info table...");
		}
		IHtmlTable orderTable = (IHtmlTable)objs[1];
		int row = orderTable.findRow(0, orderNum);
		if(row>0){
			status = orderTable.getCellValue(row, 7);
		}else{
			throw new ErrorOnDataException("Order number:"+orderNum+" is not exist");
		}
		Browser.unregister(objs);
		return status;
	}
	/**
	 * verify the order procudt name and purchase type.
	 * @param orderNum
	 * @param ProPurchaseType
	 */
	public boolean verifyProductPurchaseType(String orderNum,String ProPurchaseType){
		String status = this.getVehicleOrderPurchaseTypeInfoByOrderNum(orderNum).replace(" ", StringUtil.EMPTY);
		
		boolean result;
		result = MiscFunctions.compareResult("Product/Purchase Type", ProPurchaseType, status);
		return result;
	}
}
