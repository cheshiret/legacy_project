/*
 * Created on Jun 29, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.vpData.web;

import java.util.Vector;

/**
 * @author jchen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class VpContractFees {
	public Vector<VpItem> vpItems;

	public String contract;

	public double campingFee;

	public double discount;

	public double reservationFee;

	public double taxes;

	public double total;

	public double minimum;

	public double amountPaid;

	public String toString() {
		String str = "";
		if (vpItems != null)
			for (int i = 0; i < vpItems.size(); i++)
				str += vpItems.elementAt(i) + ":";
		str += campingFee + ":";
		str += discount + ":";
		str += reservationFee + ":";
		str += taxes + ":";
		str += total + ":";
		str += minimum + ":";

		return str;
	}

	public boolean compare(VpContractFees fee) {
		boolean flag = true;
		for (int i = 0; i < vpItems.size(); i++) {
			VpItem item = (VpItem) vpItems.elementAt(i);
			flag = flag && item.compare(fee.vpItems.elementAt(i));
		}
		return flag && contract.equals(fee.contract)
				&& discount == fee.discount
				&& reservationFee == fee.reservationFee && taxes == fee.taxes
				&& total == fee.total && minimum == fee.minimum;

	}
}
