/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @ScriptName CustomerAddress.java
 * @Date:Dec 30, 2010
 * @Description:
 * @author asun
 */
public class Address {
	
	
	public String addrType="";
	
	public String address="";
	
	public String supplementalAddr="";
	
	public String city="";
	
	public String state="";
	
	public String county="";
	
	public String country="";
	
	public String zip="";
	
	public String status="";
	
	public boolean isValidate=false;
	
	
	
	@Override
	public boolean equals(Object obj) {
		boolean result = true;
		if(! (obj instanceof Address)){
			result &= false;
		}
		Address addr=(Address)obj;
		result &= MiscFunctions.compareString("Address Type", addr.addrType, this.addrType);
		result &= MiscFunctions.compareString("Street Address", addr.address, this.address);
		result &= MiscFunctions.compareString("Address City", addr.city, this.city);
		result &= MiscFunctions.compareString("Address Country", addr.country, this.country);
		result &= MiscFunctions.compareString("Address State", addr.state, this.state);
		result &= MiscFunctions.compareString("Address County", addr.county, this.county);
		result &= MiscFunctions.compareString("Address Zip", addr.zip.replaceAll(" +", ""), this.zip.replaceAll(" +", ""));
		result &= MiscFunctions.compareString("Address Status", addr.status, this.status);
		
//		if(!this.address.equalsIgnoreCase(addr.address)){
//			System.out.println("Expect address should be " + addr.address + ", but actually is " + this.address);
//			result &= false;
//		}
//		if(!this.addrType.equalsIgnoreCase(addr.addrType)){
//			System.out.println("Expect address type should be " + addr.addrType + ", but actually is " + this.addrType);
//			result &= false;
//		}	
//		if(!this.city.equalsIgnoreCase(addr.city)){
//			System.out.println("Expect city should be " + addr.city + ", but actually is " + this.city);
//			result &= false;
//		}
		
//		if(!this.country.equalsIgnoreCase(addr.country)){
//			System.out.println("Expect country should be " + addr.country + ", but actually is " + this.country);
//			result &= false;
//		}
		
//		if(!this.county.equalsIgnoreCase(addr.county)){
//			System.out.println("Expect county should be " + addr.county + ", but actually is " + this.county);
//			result &= false;
//		}
//		
//		if(!this.state.equalsIgnoreCase(addr.state)){
//			System.out.println("Expect state should be " + addr.state + ", but actually is " + this.state);
//			result &= false;
//		}
//		
//		if(!this.status.matches(addr.status)){
//			System.out.println("Expect status should be " + addr.status + ", but actually is " + this.status);
//			result &= false;
//		}
		return result;
	}
	
	public String toString() {
		String addr = this.address + " " + this.city + ", " + 
				(StringUtil.notEmpty(this.state) ? (this.state + ", ") : "") + 
				(StringUtil.notEmpty(this.zip) ? (this.zip + ", ") : "") + 
				this.country;
		return addr;
	}

}
