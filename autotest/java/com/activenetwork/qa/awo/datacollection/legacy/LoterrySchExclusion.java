/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy;

/**
 * @author eliang
 * 
 */
public class LoterrySchExclusion {

	public String participatingLocation = "";

	public String ExclusionsStartDate = "";

	public String ExclusionsEndDate = "";

	public LoterrySchExclusion() {

	}

	public LoterrySchExclusion(String participatingLocation,
			String ExclusionsStartDate, String ExclusionsEndDate) {
		this.participatingLocation = participatingLocation;
		this.ExclusionsStartDate = ExclusionsStartDate;
		this.ExclusionsEndDate = ExclusionsEndDate;
	}

}
