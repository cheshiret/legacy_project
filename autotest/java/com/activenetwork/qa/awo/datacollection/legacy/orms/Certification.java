/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;

/**
 * @ScriptName Certification.java
 * @Date:Feb 14, 2011
 * @Description:Used by Customer Certification in LM
 * @author asun
 */
public class Certification implements Comparable<Certification> {

	public String id = "";

	public String status = "";

	public String type = "";

	public String num = "";

	public String effectiveFrom = "";

	public String effectiveTo = "";
	
	public String creationLocation = "";
	
	public String creationDateTime = "";
	
	public String creationUser = "";

	@Override
	public int compareTo(Certification o) {
		return this.num.compareTo(o.num);
	}

	@Override
	public int hashCode() {
		return this.num.hashCode();
	}
}
