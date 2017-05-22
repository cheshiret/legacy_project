package com.activenetwork.qa.awo.vpData;

import java.util.regex.Pattern;

public class VpDeposit extends VpData {

	public String depositId = "", location = "", status = "", user = "",
			numOfPay = "", numOfRef = "", numOfSession = "", total = "",
			createDate = "", totalSession = "", totalOnHand = "";

	public String notes = "";

	public boolean compare(Object obj) {
		VpDeposit vp = (VpDeposit) obj;

		return Pattern.matches(vp.createDate, this.createDate)
				&& Pattern.matches(vp.depositId, this.depositId)
				&& Pattern.matches(vp.location, this.location)
				&& Pattern.matches(vp.notes, this.notes)
				&& Pattern.matches(vp.numOfPay, this.numOfPay)
				&& Pattern.matches(vp.numOfRef, this.numOfRef)
				&& Pattern.matches(vp.numOfSession, this.numOfSession)
				&& Pattern.matches(vp.status, this.status)
				&& Pattern.matches(vp.total, this.total)
				&& Pattern.matches(vp.totalOnHand, this.totalOnHand)
				&& Pattern.matches(vp.totalSession, this.totalSession)
				&& Pattern.matches(vp.user, this.user)
				&& vp.tested == this.tested;
	}
}
