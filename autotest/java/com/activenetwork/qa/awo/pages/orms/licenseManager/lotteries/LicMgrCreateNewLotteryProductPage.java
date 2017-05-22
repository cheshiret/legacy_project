/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;




import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Nov 2, 2012
 */


public class LicMgrCreateNewLotteryProductPage extends
LicMgrLotteryProductCommonPage {

	private static LicMgrCreateNewLotteryProductPage instance = null;

	private LicMgrCreateNewLotteryProductPage() {
	}

	public static LicMgrCreateNewLotteryProductPage getInstance() {
		if (instance == null) {
			instance = new LicMgrCreateNewLotteryProductPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("PrivilegeLotteryUI-\\d+",false));
	}

	
	public void setCode(String code){
		RegularExpression regx=new RegularExpression(prefix+"productCode",false);
		browser.setTextField(".id", regx, code, true);
	}
		

	@Override
	public void setupProductInfo(HFLotteryProduct prd) {
		setCode(prd.getCode());
		super.setupProductInfo(prd);
	}


}
