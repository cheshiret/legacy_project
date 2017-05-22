package com.activenetwork.qa.awo.pages.orms;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsUnknownSystemErrorPage extends OrmsPage {
	private static OrmsUnknownSystemErrorPage _instance=null;
	
	public static OrmsUnknownSystemErrorPage getInstance() {
		if(null==_instance) {
			_instance=new OrmsUnknownSystemErrorPage();
		}
		
		return _instance;
	}
	
	protected OrmsUnknownSystemErrorPage() {}
	
	
	@Override
	public boolean exists() {
//		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".id","error");
//		Property[] p2=Property.toPropertyArray(".class","Html.TD",".text","Unknown System Error Occurred.");
		RegularExpression pattern=new RegularExpression("Unknown System Error Occurred.",false);
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class","Html.DIV",".id","error",".text",pattern));
	}

}
