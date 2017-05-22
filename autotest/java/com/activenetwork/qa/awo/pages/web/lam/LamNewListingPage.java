package com.activenetwork.qa.awo.pages.web.lam;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


public abstract class LamNewListingPage extends LamTopMenuBar {
	public void clickNextStep() {
		browser.clickGuiObject(".id","search");
	}

	public void waitForMapAPin(){

		Property aPin[] = new Property[2];
		aPin[0] = new Property(".class","Html.IMG");
//		aPin[1] = new Property(".src", new RegularExpression(".*qa\\.reserveamerica\\.com/images/maps/markerA.png$",false));
		aPin[1] = new Property(".src", new RegularExpression("images/maps/markerAttraction.png$",false));
		browser.waitExists(aPin);
	}
}
