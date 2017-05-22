/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.pos;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.Property;

/**
 * @Description:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Mar 17, 2014
 */
public class OrmsPOSEnterItemPage extends OrmsPage{
	private static OrmsPOSEnterItemPage _instance = null;
	
	private OrmsPOSEnterItemPage(){}
	
	public static OrmsPOSEnterItemPage getInstance(){
		if(null==_instance){
			_instance = new OrmsPOSEnterItemPage();
		}
		return _instance;
	}
	
	protected Property[] addToReturnListButtonPrp(){
		return Property.addToPropertyArray(a(), ".text", "Add to Return List");
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(this.addToReturnListButtonPrp());
	}

}
