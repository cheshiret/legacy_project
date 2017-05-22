package com.activenetwork.qa.awo.vpData;

import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 *
 * @author CGuo
 *
 *         this class is used to indicate the Fee Schedule verification data
 *         which is combination of "feeType", "feeAmount" and "feeSchId".
 *
 *         that means fee schedule is considered as same only when feeType is
 *         same, fee amount is equal and fee schedule ID is same.
 *
 */

public class VpFeeSchedule extends VpData {

	public String feeType;

	public double feeAmount;

	public String feeSchId;

	public String startDate;

	public String endDate;

	public VpFeeSchedule() {
		feeType = "";
		feeAmount = 0.0;
		feeSchId = "";
		tested = false;
	}

	public VpFeeSchedule(String feeType, double feeAmount, String feeSchId,
			boolean tested) {
		this.feeType = feeType;
		this.feeAmount = feeAmount;
		this.feeSchId = feeSchId;
		this.tested = tested;
	}

	public boolean compare(Object obj) {
		VpFeeSchedule fd = (VpFeeSchedule) obj;

		return fd.feeType.equals(this.feeType)
				&& ((fd.feeAmount - this.feeAmount) < 0.5)
				&& fd.feeSchId.equals(this.feeSchId)
				&& fd.tested == this.tested;

	}

	/**
	 * Check if given date between the start date & end date
	 */
	public boolean between(String date) {
		return DateFunctions.compareDates(startDate, date) != 1
				&& DateFunctions.compareDates(endDate, date) != -1;
	}

	public boolean afterEndDate(String date) {
		return DateFunctions.compareDates(endDate, date) == -1;
	}
}
