package com.activenetwork.qa.awo.datacollection.datadefinition;

import com.activenetwork.qa.testapi.datacollection.DataAttribute;

public enum PayAttr implements DataAttribute{
	PAY_TYPE_GRP(Integer.class),PAY_TYPE(PayTypeAttr.class),FLEXIBLE(Boolean.class),PIN(Long.class),AMOUNT(Double.class),
	//CHECK
	CHECK_NUMBMER,CHECK_NAME,CHECK_DATE,
	//CREDIT CARD
	CREDIT_CARD_NUMBER,EXPIRE_MON,EXPIRE_YEAR,CARD_HOLDER,CVV,F_NAME,L_NAME,ZIP,
	//GIFT CARD
	GIFTCARD_NUMBER,GIFTCARD_SEC_CODE,
	//REFUND OPTION
	ISSUE_TO_VOUCHER(Boolean.class),ISSUE_CASH(Boolean.class),ISSUE_GIFTCARD(Boolean.class)
	;
	private Class<?> type;
	private PayAttr(){
		type=String.class;
	}
	private PayAttr(Class<?> type) {
		this.type=type;
	}
	
	@Override
	public Class<?> getType() {
		return type;
	}
	
	
}
