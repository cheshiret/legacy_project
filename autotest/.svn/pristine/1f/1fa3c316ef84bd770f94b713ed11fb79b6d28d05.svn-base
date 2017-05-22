package com.activenetwork.qa.awo.pages.orms.venueManager;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:Display the receipts details order.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jasmine 
 * @Date  Jan 6, 2012
 */
public class VnuMgrReceiptsDetailsPage extends VenueManagerPage{

	private static VnuMgrReceiptsDetailsPage _instance = null;

	private VnuMgrReceiptsDetailsPage() {
	}

	public static VnuMgrReceiptsDetailsPage getInstance() {
		if (null == _instance)
			_instance = new VnuMgrReceiptsDetailsPage();

		return _instance;
	}
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Reprint Receipt");
	}
	/**
	 * get the receipt item table.
	 * @return
	 */
	public IHtmlObject[] getReceiptItemTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE", ".text", new RegularExpression("^Order #.*", false));
		return objs;
	}
	/**
	 * get deliver Method transaction fee lable.
	 * @param tourName
	 */
	public String getDeliverMethodTranFeeLable(String tourName){
		String tranFeeLable = null;
		IHtmlObject[] objs = getReceiptItemTable();
		if(objs.length<1){
			throw new ErrorOnDataException("No Transactions item table exist");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = table.findRow(1, tourName);
		if(rowIndex>-1){
			//tranFeeLable = table.getCellValue(rowIndex +3 , 0);//table.getCellValue(rowIndex +2 , 1), Vivian[2013/12/11]
			tranFeeLable = table.getCellValue(rowIndex + 3, 1);//Quentin[20140326]
			tranFeeLable = tranFeeLable.replaceAll("\\(", "").replaceAll("\\)", "");
		}
		return tranFeeLable;
	}
	/**
	 * verify order level transaction fee deliver method lable.
	 * @param tourName
	 * @param expectedLable
	 */
	public boolean compareOrderLevelTranFeeDeliverMethodLable(String tourName,String expectedLable){
		boolean isEqual = false;
		String actualLable = this.getDeliverMethodTranFeeLable(tourName);
		if(!MiscFunctions.compareResult("Deliver method transaction fee lable", expectedLable, actualLable)){
			logger.error("Deliver method transaction fee lable");
		}else{
			isEqual = true;
			logger.info("Deliver method transaction fee lable display correct");
		}
		return isEqual;
	}
	
}
