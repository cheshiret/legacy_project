/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.marina;

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
public class OrmsSlipContractChargePage extends OrmsSlipContractDetailsPage{
	private static OrmsSlipContractChargePage _instance = null;
	
	protected OrmsSlipContractChargePage(){}
	
	public static OrmsSlipContractChargePage getInstance(){
		if(null == _instance){
			_instance = new OrmsSlipContractChargePage();
		}
		return _instance;
	}
	
	protected Property[] chargeListTableProp(){
		return Property.concatPropertyArray(table(),".id",new RegularExpression("grid_\\d+",false),".text",
				new RegularExpression("^( )?POS Sale #( )?Reservation #.*",false));
	}
	
	protected Property[] addToCartButtonProp(){
		return Property.concatPropertyArray(a(), ".text","Add to Cart");
	}
	
	protected Property[] removePOSSaleButtonProp(){
		return Property.concatPropertyArray(a(), ".text","Move POS Sale");
	}
	
	protected Property[] posSaleNumLinkProp(String posSaleNum){
		return Property.concatPropertyArray(a(), ".text",posSaleNum);
	}
	
	protected Property[] reservationNumLinkProp(String resNum){
		return Property.concatPropertyArray(a(), ".text",resNum);
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(this.chargeListTableProp());
	}
	
	public void clickAddToCart(){
		browser.clickGuiObject(this.addToCartButtonProp());
	}
	
	public void clickRemovePOSSale(){
		browser.clickGuiObject(this.removePOSSaleButtonProp());
	}
	
	public IHtmlObject[] getChargeListTableObject(){
		IHtmlObject[] objs = browser.getHtmlObject(this.chargeListTableProp());
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found Charge List Table object.");
		}
		
		return objs;
	}
	
	public void selectCheckBoxByPOSSaleNum(String posSaleNum){
		IHtmlObject[] objs = this.getChargeListTableObject();
		
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		
		int col = table.findColumn(0, "POS Sale #");
		int index = table.findRow(col, posSaleNum);
		
		if(index<1){
			throw new ItemNotFoundException("Did not found the pos sale info row, pos sale # = " + posSaleNum);
		}
		
		browser.selectCheckBox(select(), index, objs[objs.length-1]);
		Browser.unregister(objs);
	}
	
	public void addPOSSaleToCart(String posSaleNum){
		this.selectCheckBoxByPOSSaleNum(posSaleNum);
		this.clickAddToCart();
	}
	
	public void removePOSSale(String posSaleNum){
		this.selectCheckBoxByPOSSaleNum(posSaleNum);
		this.clickRemovePOSSale();
	}
	
	public void clickPOSSaleNum(String posSaleNum){
		browser.clickGuiObject(this.posSaleNumLinkProp(posSaleNum));
	}
	
	public void clickReservationNumLink(String resNum){
		browser.clickGuiObject(this.reservationNumLinkProp(resNum));
	}

}
