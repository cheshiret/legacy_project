package com.activenetwork.qa.awo.datacollection;

import com.activenetwork.qa.awo.datacollection.datadefinition.PayAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.PayTypeAttr;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class Payment {
	public static Data<PayAttr> visa() {
		Data<PayAttr> pay=new Data<PayAttr>();
		pay.put(PayAttr.PAY_TYPE, PayTypeAttr.VISA);
		setDefaultCreditCardInfo(pay);
		return pay;
	}
	
	private static void setDefaultCreditCardInfo(Data<PayAttr> pay) {
		pay.put(PayAttr.CARD_HOLDER, "QA Automation");
		pay.put(PayAttr.EXPIRE_MON,"12");
		pay.put(PayAttr.EXPIRE_YEAR,Integer.toString(DateFunctions.getYearAfterCurrentYear(5)));
	}
}
