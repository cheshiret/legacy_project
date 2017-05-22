package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;

public class OrmsSystemErrorWidget extends DialogWidget {
	
	private static OrmsSystemErrorWidget instance = null;

	private OrmsSystemErrorWidget() {
		super("(System )?Error");
	}

	public static OrmsSystemErrorWidget getInstance() {
		if (instance == null) {
			instance = new OrmsSystemErrorWidget();
		}
		return instance;
	}
	
	public String getMessage() {
		return browser.getObjectText(".class","Html.SPAN",".className","messagebox_text").trim();
	}
}
