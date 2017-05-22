package com.activenetwork.qa.testdriver.selenium.dialog;

import com.activenetwork.qa.testapi.interfaces.dialog.IPrintDialog;

public class PrintDialog extends Dialog implements IPrintDialog{

	public PrintDialog(String attributes) {
		super(attributes);
	}
	
	public PrintDialog() {
		this("[TITLE:Print; CLASS:#32770]");
	}
	
	@Override
	public void clickCancel(){
		this.clickButton("Cancel");
	}
	
	@Override
    public void clickPrint(){
    	this.clickButton("&Print");
    }
}
