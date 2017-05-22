/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.common.UwpConfirmationCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
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
 * @Date  Apr 17, 2013
 */
public class HFConfirmationPage extends UwpConfirmationCommonPage {
	private static HFConfirmationPage _instance = null;

	public static HFConfirmationPage getInstance() {
		if (null == _instance)
			_instance = new HFConfirmationPage();

		return _instance;
	}

	public HFConfirmationPage() {
	}
	
	/**Page Object Property Definition Begin*/
	protected Property[] printPrivilegesProp() {
		return Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("/printHFLicense.*", false));
	}
	protected Property[] lotteryCodeAndName(String lotteryCode, String lotteryName) {
		return Property.concatPropertyArray(span(), ".className", "textSpan", ".text", lotteryCode+ " - " +lotteryName);
	}
	/**Page Object Property Definition END*/
	
	private IHtmlObject[] getShoppingItemTable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TABLE", ".id", "shoppingitems1");
		if (objs.length < 1) {
			throw new ErrorOnPageException("Can't find shopping item table");
		}
		return objs;
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
					expHuntInfo += " Item #"+j+": "+DateFunctions.formatDate(priv.validFromDate, "E MMM dd yyyy") + (StringUtil.notEmpty(priv.validToDate) ? " - "+priv.validToDate : "");
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
			throw new ErrorOnPageException("Hunt Info on Confirmation page is wrong!");
		}
		logger.info("Privilege order item or Hunt Info on Confirmation page is correct!");
	}
	
	public void clickPrintPrivileges() {
		browser.clickGuiObject(this.printPrivilegesProp());
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
