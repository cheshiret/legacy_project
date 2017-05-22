/*
 * Created on Dec 16, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsOtherVouchersPage extends OrmsPage{
  	
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsOtherVouchersPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsOtherVouchersPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsOtherVouchersPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsOtherVouchersPage();
		}

		return _instance;
	}
	
	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.SELECT",".id","VoucherSearchRequest.searchBy");
	}
	
	/**Select search type about the vouchers' type*/
	public void selectVouchersSearchType(String searchType){
	    browser.selectDropdownList(".id","VoucherSearchRequest.searchBy",searchType);
	}
	/**Set the search value*/
	public void setSearchValue(String searchValue){
	    browser.setTextField(".id","VoucherSearchRequest.searchValue",searchValue);
	}
	
	/**Select the date selected item*/
	public void searchDateItem(String dateItem){
	    browser.selectDropdownList(".id","VoucherSearchRequest.searchByDate",dateItem);
	}
	
	/**Set from date about the vouchers*/
	public void setFromDate(String fromDate){
	   browser.setTextField(".id","VoucherSearchRequest.fromDate",fromDate);
	}
	
	/**Set the end date about the vouchers*/
	public void setToDate(String toDate){
	   browser.setTextField(".id","VoucherSearchRequest.toDate",toDate);
	}
	
	/**Click Go link*/
	public void clickGO(){
	   browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("^Search$", false));
	}
	
	/**Selected the searched vouchers*/
	public void selectVouchers(){
	   browser.clickGuiObject(".class","Html.A",".text","Select");
	}
	
	public void searchVouchersByVoucherID(String voucherID){
	   selectVouchersSearchType("Voucher ID");
	   setSearchValue(voucherID);
	   clickGO();
	   waitLoading();
	   selectVouchers();
	}
}
