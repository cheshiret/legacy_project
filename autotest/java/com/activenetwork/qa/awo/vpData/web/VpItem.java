/*
 * Created on Jun 29, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.vpData.web;

/**
 * @author jchen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class VpItem {
	public double campingFee;

	public double reservationFee;

	public double taxes;

	public double discount;

	public String reservationNo;

	public String status;

	public String siteInfo;

	public String arrival;

	public String departure;

	public String unit;

	public int length;

	public String toString() {
		return reservationNo + ":" + status + ":" + siteInfo + ":" + arrival
				+ ":" + departure + ":" + length + ":" + unit + ":"
				+ campingFee + ":" + discount + ":" + reservationFee + ":"
				+ taxes;

	}

	public boolean compare(VpItem item) {
		return departure.equals(item.departure)
				&& arrival.equals(item.arrival)
				&& siteInfo.equals(item.siteInfo) && length == item.length
				&& unit.equals(item.unit) && campingFee == item.campingFee
				&& reservationFee == item.reservationFee && taxes == item.taxes
				&& discount == item.discount;

	}
}
