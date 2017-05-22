package com.activenetwork.qa.awo.vpData;

import java.util.regex.Pattern;

public class VpFinSession extends VpData {

	public String finId = "", depositId = "", userOrStation = "", status = "",
			numOfPay = "", amount = "", openDate = "", openUser = "",
			closeDate = "", closeUser = "", depositDate = "", depositUser = "";

	public String totalAmount = "", totalOnHand = "";

	public String notes = "";

	public boolean compare(Object obj) {
		VpFinSession vp = (VpFinSession) obj;

		return Pattern.matches(vp.amount, this.amount)
				&& Pattern.matches(vp.closeDate, this.closeDate)
				&& Pattern.matches(vp.closeUser, this.closeUser)
				&& Pattern.matches(vp.depositDate, this.depositDate)
				&& Pattern.matches(vp.depositId, this.depositId)
				&& Pattern.matches(vp.depositUser, this.depositUser)
				&& Pattern.matches(vp.finId, this.finId)
				&& Pattern.matches(vp.notes, this.notes)
				&& Pattern.matches(vp.numOfPay, this.numOfPay)
				&& Pattern.matches(vp.openDate, this.openDate)
				&& Pattern.matches(vp.openUser, this.openUser)
				&& Pattern.matches(vp.status, this.status)
				&& Pattern.matches(vp.userOrStation, this.userOrStation)
				&& Pattern.matches(vp.totalAmount, this.totalAmount)
				&& Pattern.matches(vp.totalOnHand, this.totalOnHand)
				&& vp.tested == this.tested;

	}

}
