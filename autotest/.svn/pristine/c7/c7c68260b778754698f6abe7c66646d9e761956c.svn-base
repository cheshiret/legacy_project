package com.activenetwork.qa.awo.datacollection.legacy;

import java.util.Comparator;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.SlipFee;

public class ComparatorIncrement implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		SlipFee fee1 = (SlipFee) o1;
		SlipFee fee2 = (SlipFee) o2;
		
		if(Integer.parseInt(fee1.increment) > Integer.parseInt(fee2.increment)) {
			return -1;
		} else if(Integer.parseInt(fee1.increment) < Integer.parseInt(fee2.increment)) {
			return 1;
		}
		return 0;
	}

}
