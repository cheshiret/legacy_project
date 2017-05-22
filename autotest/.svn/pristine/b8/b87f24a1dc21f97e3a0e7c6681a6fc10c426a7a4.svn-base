package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Vehicle;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrVehicleTitleHistoryPage extends LicMgrVehicleProductCommonPage{
	private static LicMgrVehicleTitleHistoryPage _instance = null;

	protected LicMgrVehicleTitleHistoryPage() {
	}

	public static LicMgrVehicleTitleHistoryPage getInstance() {
		if (_instance == null) {
			_instance = new LicMgrVehicleTitleHistoryPage();
		}
		return _instance;
	}
	
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "vehicleRegistrationHistoryGrid");
	}
	/**
	 * get title history table
	 */
	public IHtmlObject[] getTitleHistoryTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "vehicleRegistrationHistoryGrid");
		if(objs.length<1){
			throw new ErrorOnDataException("No history record table exist");
		}
		return objs;
	}
	
	/**
	 * get title history table.
	 * @return
	 */
	
	public List<OrderHistory> getTitleHistoryTransactionInfo(){
		List<OrderHistory> list = new ArrayList<OrderHistory>();
		OrderHistory orderHistory = null;
		IHtmlObject[] objs = this.getTitleHistoryTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		for(int i =1;i<table.rowCount();i++){
			orderHistory = new OrderHistory();
			orderHistory.transaction = table.getCellValue(i, 1);
			orderHistory.infoTransaction = table.getCellValue(i,2);
			orderHistory.transactionLocation = table.getCellValue(i,3);
			orderHistory.user = table.getCellValue(i, 4);
			list.add(orderHistory);
		}
		Browser.unregister(objs);
		return list;
	}
	/**
	 * compare title history info.
	 * @param expectdOrder
	 * @param vehicle
	 * @return
	 */
	public boolean compareTitileHistoryInfo(OrderInfo expectdOrder,Vehicle vehicle){
		boolean isEqual = true;
		List<OrderHistory> historyList = this.getTitleHistoryTransactionInfo();
		for(int i =0;i<expectdOrder.transactionList.size();i++){
			for(int j =0;j<historyList.size();j++){
				
				if(expectdOrder.transactionList.get(i).equals(historyList.get(j).transaction)){
					
					String expectedTranInof = this.getTitleTransanctionInfo(expectdOrder.transactionList.get(i), vehicle, expectdOrder.orderNum, expectdOrder.receiptNum);
					if(!historyList.get(j).infoTransaction .contains(expectedTranInof)){
						isEqual &= false;
						logger.error("the expected value is "+this.getTitleTransanctionInfo(expectdOrder.transactionList.get(i), vehicle, expectdOrder.orderNum, expectdOrder.receiptNum)+" but actual value is"+ historyList.get(j).infoTransaction);
					}
					if(!expectdOrder.transactionLocation.equals(historyList.get(j).transactionLocation)){
						
						isEqual &= false;
						logger.error("the expected value is "+expectdOrder.transactionLocation+" but actual value is"+ historyList.get(j).transactionLocation);
					}
					if(!expectdOrder.transactionUser.trim().replaceAll("\\s+", StringUtil.EMPTY).equals(historyList.get(j).user.trim().replaceAll("\\s+", StringUtil.EMPTY))){
						isEqual &= false;
						logger.error("the expected value is "+expectdOrder.creationUser+" but actual value is"+ historyList.get(j).user);
					}
				}
			}
		}
		return isEqual;
	}
	/**
	 * get transaction info.
	 * @param transaction
	 * @param vehicle
	 * @param orderNum
	 * @param receiptNum
	 * @return
	 */
	public String getTitleTransanctionInfo(String transaction,Vehicle vehicle,String orderNum,String receiptNum){
		String expectedTransactionInfo = "";
		
			if(transaction.equals("Title Vehicle")){
				expectedTransactionInfo = "Order: "+orderNum+", Receipt: "+receiptNum;
			}
			if(transaction.equals("Set Title To Transferable")){
				expectedTransactionInfo = "Order: "+orderNum+", Receipt: "+receiptNum;
			}
			if(transaction.equals("Surrender Title")){
				expectedTransactionInfo = "";
			}
			if(transaction.equals("Reactivate Title")){
				expectedTransactionInfo = "";
			}
			if(transaction.equals("Edit Title")){
				expectedTransactionInfo = "Boat Value:";
			}
			if(transaction.equals("Add Lien")){
				expectedTransactionInfo = "Lien ID: "+vehicle.title.lienInfo.getLienId()+", "+ vehicle.title.lienInfo.getLienCompanyDetailsInfo().lienCompanyName+" Date of Lien: "+DateFunctions.formatDate(vehicle.title.lienInfo.getDateOfLien(), "EEE MMM d yyyy")+" Lien Amount: $"+vehicle.title.lienInfo.getLienAmount()+".00"; 
			}
			if(transaction.equals("Release Lien")){
				expectedTransactionInfo = "Lien ID: "+vehicle.title.lienInfo.getLienId()+" Date of Release: " + DateFunctions.formatDate(vehicle.title.lienInfo.getDateOfRelease(), "MM-dd-yyyy");
			}
			if(transaction.equals("Reactivate Lien")){
				expectedTransactionInfo ="Lien ID: "+vehicle.title.lienInfo.getLienId();
			}
		return  expectedTransactionInfo;
	}

}
