/*
 * Created on Jun 29, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.vpData.web;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpMemberProfile;

/**
 * @author jchen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class VpOrderDetails {
	public String equipType = "";

	public String equipLength = "";

	public String numOccupant = "";

	public String numVehicles = "";

	public String primaryOccupant = ""; //member or other

	public UwpMemberProfile other = new UwpMemberProfile();

	public String rateType = ""; //family or group

	public String discount = "";

	public String discountCode = "";

	public String toString() {
		return equipType + ":" + equipLength + ":" + numOccupant + ":"
				+ numVehicles;
	}
}
