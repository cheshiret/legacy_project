/*
 * Created on Jan 4, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.datacollection.legacy;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DiscountData {
  	public String name = "";
  	public String description = "";
  	public String rateType = "";
  	public List<String> feeTypes = new ArrayList<String>();
  	public List<String> behaviors = new ArrayList<String>(); // define when calculate discount
  	public String rateUnit = "";
  	public String promoCode = ""; 
  	public String promoDescription = "";
  	public String maxUsagePerCust = "";
}
