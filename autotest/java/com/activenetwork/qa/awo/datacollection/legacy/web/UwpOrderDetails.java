/*
 * Created on Mar 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.datacollection.legacy.web;

/**
 * @author jchen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class UwpOrderDetails {
	public String equipType = "";

	public String equipLength = "";

	public String numOccupant = "";

	public String numVehicles = "";

	public String primaryOccupant = ""; //member or other

	public UwpMemberProfile other = new UwpMemberProfile();

	public String rateType = ""; //family or group

	public String discount = "";

	public String discountCode = "";
}
