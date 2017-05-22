package com.activenetwork.qa.awo.datacollection.legacy;

import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * For handle Customer pass
 * @author eliang
 *
 */
public class CustPass {
	public String passType = "";

	public String passNum = "";
	
	public String expiryDate = "";
	
	public String createDate = "";
	
	public String fileImport = "";
	
	public String fulfilledStatus = "";

	public String notes = "";
	
	public String holderName = "";
	
	public boolean proofShown = false;
	

	public CustPass(String passType, String passNum, String notes) {
		this.passType = passType;
		this.passNum = passNum;
		this.notes = notes;
	}

	public CustPass() {

	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result
				+ ((holderName == null) ? 0 : holderName.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((passNum == null) ? 0 : passNum.hashCode());
		result = prime * result
				+ ((passType == null) ? 0 : passType.hashCode());
		result = prime * result + (proofShown ? 1231 : 1237);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustPass other = (CustPass) obj;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (DateFunctions.diffBetween(expiryDate,other.expiryDate)!=0)
			return false;
		if (holderName == null) {
			if (other.holderName != null)
				return false;
		} else if (!holderName.equals(other.holderName))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (passNum == null) {
			if (other.passNum != null)
				return false;
		} else if (!passNum.equals(other.passNum))
			return false;
		if (passType == null) {
			if (other.passType != null)
				return false;
		} else if (!passType.equals(other.passType))
			return false;
		if (proofShown != other.proofShown)
			return false;
		return true;
	}

}
