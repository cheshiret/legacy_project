/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.common.UwpShoppingCartCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Apr 12, 2013
 */
public class HFShoppingCartPage extends UwpShoppingCartCommonPage {
	private static HFShoppingCartPage _instance = null;

	public static HFShoppingCartPage getInstance() {
		if (null == _instance)
			_instance = new HFShoppingCartPage();

		return _instance;
	}

	protected HFShoppingCartPage() {
	}
	
	private Property[] feeValue(){
		return Property.concatPropertyArray(td(), ".className", "feeValue");
	}

	private Property[] privilegeRow(String privName, String ly){
		return Property.concatPropertyArray(tr(), ".text", new RegularExpression(privName.replace("(", "\\(").replace(")", "\\)") + " Licen(s|c)e Year: "+ly, false));
	}
	
	protected Property[] shoppingItemTR(String itemName) {
		return Property.concatPropertyArray(tr(), ".text", new RegularExpression(".*"+itemName+".*", false));
	}
	
	protected Property[] shoppingItemInfoTD() {
		return Property.concatPropertyArray(td(), ".className", "itemInformation");
	}
	
	protected Property[] lotteryCodeAndName(String lotteryCode, String lotteryName) {
		return Property.concatPropertyArray(span(), ".className", "textSpan", ".text", lotteryCode+ " - " +lotteryName);
	}
	
	private IHtmlObject[] getShoppingItemTable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TABLE", ".id", "table1");
		if (objs.length < 1) {
			throw new ErrorOnPageException("Can't find shopping cart item table");
		}
		return objs;
	}
	
	public boolean isPrivItemExist(String privName, String ly) {
		IHtmlObject[] objs = this.getShoppingItemTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(1, new RegularExpression(privName+" Licen(s|c)e Year: "+ly, false)); 
		boolean exist = row >= 0;
		Browser.unregister(objs);
		return exist;
	}
	
	public String getPrivFeeValue(String privName, String ly){
		IHtmlObject[] objs = browser.getHtmlObject(privilegeRow(privName, ly));
		if(objs.length<1){
			throw new ErrorOnPageException("Can't find top object that privName:"+privName+" and ly:"+ly);
		}
		IHtmlObject[] objs1 = browser.getHtmlObject(feeValue(), objs[objs.length-1]);
		if(objs1.length<1){
			throw new ErrorOnPageException("Can't find child fee value objects.");
		}
		
		String value = objs1[0].text();
		Browser.unregister(objs, objs1);
		return value;
	}
	
	public void verifyPrivilegesExist(PrivilegeInfo... privs) {
		boolean result = true;
		for (PrivilegeInfo priv : privs) {
			if (this.isPrivItemExist(priv.name, priv.licenseYear)) {
				logger.info(priv.name + " exists on shopping cart page.");
			} else {
				result = false;
				logger.error(priv.name + "doesn't exist on shopping cart page.");
			}
		}
		
		if (!result) {
			throw new ErrorOnPageException("Not all privielges exist on shopping cart page...");
		}
		logger.info("All privileges exist on shopping cart page.");
	}
	
	public String getPrivItemInfo(PrivilegeInfo priv) {
		IHtmlObject[] objs = this.getShoppingItemTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		String reg = priv.name+" Licen(s|c)e Year: "+priv.licenseYear +
				(StringUtil.isEmpty(priv.authPrivNum) ? "" : " Auth\\. #: "+priv.authPrivNum) +
				(StringUtil.isEmpty(priv.outfitterPermitNum) ? "" : " Outfitter Permit: "+priv.outfitterPermitNum);
		int row = table.findRow(1, new RegularExpression(reg, false));
		if (row < 0) {
			throw new ErrorOnPageException("Can't find the privilege with name=" + priv.name + 
					", license year=" + priv.licenseYear +
					", auth num=" + priv.authPrivNum +
					", outfitter permit=" + priv.outfitterPermitNum);
		}
		String info = table.getCellValue(row, 2);
		Browser.unregister(objs);
		return info;
	}
	
	public String getPrivItemInfoByPrivNameAndLY(String privName, String ly) {
		PrivilegeInfo priv = new PrivilegeInfo();
		priv.name = privName;
		priv.licenseYear = ly;
		return this.getPrivItemInfo(priv);
	}
	
	public void verifyPrivOrdItemInfo(PrivilegeInfo... privs) {
		boolean result = true;
		
		for (int i = 0; i < privs.length; i++) {
			PrivilegeInfo priv = privs[i];
			String itemInfo = this.getPrivItemInfo(priv);
			String expHuntInfo;
			if (priv.hunts == null) {
				expHuntInfo = "Quantity: " + priv.qty;
				for (int j = 1; j <= Integer.valueOf(priv.qty); j++) {
					expHuntInfo += " Item #"+j+": "+priv.validFromDate + (StringUtil.notEmpty(priv.validToDate) ? " - "+priv.validToDate : "");
				}
			} else {
				expHuntInfo = "Quantity: " + priv.hunts.size();
				for (int j = 0; j < priv.hunts.size(); j++) {
					HuntInfo hunt = priv.hunts.get(j);
					String info = "";
					if (!expHuntInfo.contains(hunt.getDescription())) {
						info = " " + hunt.getDescription() + " [# " + hunt.getHuntCode() + "] " + hunt.getSpecie() + 
								(StringUtil.notEmpty(hunt.getSpecieSubType()) ? " - "+hunt.getSpecieSubType() : "") + 
								(StringUtil.notEmpty(hunt.getWeaponDes()) ? " / "+hunt.getWeaponDes() : "");
					}
					info += " Item #"+(j+1)+": "+priv.validFromDate + (StringUtil.notEmpty(priv.validToDate) ? " - "+priv.validToDate : "");
					info += "# of Tags: " + hunt.getNumOfTagQty();
					expHuntInfo += info;
				}
			}
			
			result &= MiscFunctions.compareString("The hunt info of " + priv.name + " " + priv.licenseYear, expHuntInfo, itemInfo);
		}
		
		if (!result) {
			throw new ErrorOnPageException("Hunt Info on Shopping cart page is wrong!");
		}
		logger.info("Privilege order item or Hunt Info on shopping cart page is correct!");
	}
	
	public void verifyPrivOrdItemInfo(PrivilegeInfo priv, String numOfItem) {
		boolean result = true;
		HuntInfo hunt = priv.hunts.get(0);
		String itemInfo = this.getPrivItemInfoByPrivNameAndLY(priv.name, priv.licenseYear);
		String expHuntInfo = "Quantity: " + numOfItem;
		expHuntInfo += " " + hunt.getDescription() + " [# " + hunt.getHuntCode() + "] " + hunt.getSpecie() + 
				(StringUtil.notEmpty(hunt.getSpecieSubType()) ? " - "+hunt.getSpecieSubType() : "") + 
				(StringUtil.notEmpty(hunt.getWeaponDes()) ? " / "+hunt.getWeaponDes() : "");
		for(int i=0; i<Integer.parseInt(numOfItem); i++){
			expHuntInfo += " Item #"+(i+1)+": "+priv.validFromDate + (StringUtil.notEmpty(priv.validToDate) ? " - "+priv.validToDate : "");
		}
		result &= MiscFunctions.compareString("The hunt info of " + priv.name + " " + priv.licenseYear, expHuntInfo, itemInfo);

		if (!result) {
			throw new ErrorOnPageException("Hunt Info on Shopping cart page is wrong!");
		}
		logger.info("Hunt Info on shopping cart page is correct!");
	}
	
    /**
     * Check timer is shown or not 
     * @return
     */
    public boolean isTimershown(){
    	return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "bestbefore") ||
    			browser.checkHtmlObjectExists(".class", "Html.Span", ".id", new RegularExpression("countdown|expired", false)); 
    }
    
    /**
     * Verify timer is shown or not
     * @param existing
     */
    public void verifyTimerExistingOrNot(boolean existing){
    	boolean actualResult = this.isTimershown();
    	if(actualResult!=existing){
    		throw new ErrorOnDataException("Timer should"+(existing?"":" not")+" shown.");
    	}
    	logger.info("Successfully verify timer is"+(existing?"":" not")+" shown.");
    }
    
    /**
     * Verify each order items fee values status, have fee value or not
     * @param feeValues
     * @param privileges
     */
    public void verifyOrderItemsFeeValueExistedOrNot(String[] feeValues, PrivilegeInfo... privileges){
		logger.info("In Shopping Cart page, check one order item has fee value, the other doesn't have.");
		boolean result = MiscFunctions.compareResult("Size of compared two Array", feeValues.length, privileges.length);
		if(result){
			for(int i=0; i<privileges.length; i++){
				if(StringUtil.isEmpty(feeValues[i])){
					result &= MiscFunctions.compareResult(privileges[i].name+" doesn't have fee value", StringUtil.EMPTY, getPrivFeeValue(privileges[i].name, privileges[i].licenseYear));
				}else result = MiscFunctions.matchString(privileges[i].name+" has fee value", getPrivFeeValue(privileges[i].name, privileges[i].licenseYear), feeValues[i]);
			}
		}
		if(!result){
			throw new ErrorOnPageException("Not all order itmes fee values are correct in shopping cart page. Please check details from previous logs.");
		}
	}
    
    public String getShoppingItemInfo(String itemName) {
    	return browser.getObjectText(Property.atList(this.shoppingItemTR(itemName), this.shoppingItemInfoTD()));
    }
    
    public void verifyShoppingItemInfo(String itemName, String itemInfo) {
    	String actInfo = this.getShoppingItemInfo(itemName);
    	if (!actInfo.matches(itemInfo)) {
    		throw new ErrorOnPageException("Shopping Item Info for " + itemName + " is wrong!", itemInfo, actInfo);
    	}
    	logger.info("Successfully verify shopping item info for " + itemName);
    }
    
    public void verifyLotteryHuntChoices(String lotteryDes, String... huntCodes) {
    	String itemInfo = "";
    	for (String huntCode : huntCodes) {
    		itemInfo += ".*"+huntCode+".*";
    	}
    	this.verifyShoppingItemInfo(lotteryDes, itemInfo);
    }
    
    public boolean isLotteryCodeAndNameExist(String lotteryCode, String lotteryName) {
    	return browser.checkHtmlObjectExists(lotteryCodeAndName(lotteryCode, lotteryName));
    }
    
    public void verifyLotteryCodeAndName(String lotteryCode, String lotteryName, boolean existed) {
    	boolean resultFromUI = isLotteryCodeAndNameExist(lotteryCode, lotteryName);
    	if(existed==resultFromUI){
    		logger.info("Successfully verify lottery code:"+lotteryCode+" and lottery name:"+lotteryName+(existed?" exists":" doesn't exist"));
    	}else throw new ErrorOnPageException("Failed to verify lottery code:"+lotteryCode+" and lottery name:"+lotteryName+(existed?" exists":" doesn't exist"));
    }
}
