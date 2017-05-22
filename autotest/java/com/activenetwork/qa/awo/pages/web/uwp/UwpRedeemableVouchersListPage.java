package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jan 25, 2013
 */
public class UwpRedeemableVouchersListPage extends UwpPage {
	private static UwpRedeemableVouchersListPage _instance = null;

	public static UwpRedeemableVouchersListPage getInstance() {
		if (null == _instance)
			_instance = new UwpRedeemableVouchersListPage();

		return _instance;
	}

	public UwpRedeemableVouchersListPage() {
	}

	public String noRedeemableVoucherMes = "There are no redeemable vouchers.";
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "pagetitle",".text", new RegularExpression("^Redeemable Vouchers.*", false));
	}
	
	public IHtmlObject[] getRedeemableVouchersTable(){
  	IHtmlObject[] objs = browser.getTableTestObject(".id","shoppingitems");
	  	if(objs==null || objs.length<1)
	  	  	throw new ItemNotFoundException("Redeemable Vouchers list can't be found.");
	  	
	  	return objs;
	}

	public List<Voucher> getVouchersInfo(){
	  	IHtmlObject[] objs = this.getRedeemableVouchersTable();
	  	IHtmlTable table = (IHtmlTable)objs[0];
	  	List<Voucher> returnList = new ArrayList<Voucher>();
	  	Voucher vc = null;
	  	
	  	for(int i=3; i<table.rowCount(); i++){
	  		vc = new Voucher();
	  		vc.voucherId = table.getCellValue(i, 0);
	  		vc.amount = table.getCellValue(i, 2).replace("$", "").trim();
	  		returnList.add(vc);
	  	}
	  	Browser.unregister(objs);
	  	return returnList;
	}
	
	public Voucher getVoucherInfo(){
		return getVouchersInfo().get(0);
	}
	
	public String getNoRedeemableVouchersMes(){
		IHtmlObject[] objs = this.getRedeemableVouchersTable();
	  	String message = ((IHtmlTable)objs[0]).getCellValue(3, 0);
	  	
	  	Browser.unregister(objs);
	  	return message;
	}
	
	public void verifyVoucherInfo(Voucher expectedVC){
		boolean result = true;
		
		Voucher actualVC = this.getVoucherInfo();
		result &= MiscFunctions.compareResult("Voucher#", expectedVC.voucherId, actualVC.voucherId);
		result &= MiscFunctions.compareResult("Value", expectedVC.amount, actualVC.amount);
		if(!result){
			throw new ErrorOnDataException("Not all the voucher info are correct. Please find detaisl info from previous logs.");
		}
		logger.info("Successfully verify all voucher info.");
	}
	
	public void verifyNoSpecificRedeemableVoucher(String voucherId){
		
	}
	
	public void verifyNoRedeemableVouchersMes(){
		String actualResult = this.getNoRedeemableVouchersMes();
		if(!actualResult.equals(noRedeemableVoucherMes)){
			throw new ErrorOnDataException("Should have no redeemable vouchers message display.");
		}
		logger.info("Successfylly verify no redeemable vouchers message display.");
	}
}
