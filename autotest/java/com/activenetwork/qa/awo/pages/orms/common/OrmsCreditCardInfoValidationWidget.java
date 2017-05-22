package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;

/**
 * @Description:
 * Title is Credit Card Info Validation.
 * Text:The Credit Card has passed validation.
 * There isn't any button on this widget.
 * 
 * @author nding1
 * @Date  Sep 10, 2013
 *
 */
public class OrmsCreditCardInfoValidationWidget extends DialogWidget {
	private static OrmsCreditCardInfoValidationWidget instance = null;

	private OrmsCreditCardInfoValidationWidget() {
		super("Credit Card Info Validation");// title is Credit Card Info Validation.
	}

	public static OrmsCreditCardInfoValidationWidget getInstance() {
		if (instance == null) {
			instance = new OrmsCreditCardInfoValidationWidget();
		}
		return instance;
	}
}
