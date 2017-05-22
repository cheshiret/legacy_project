package com.activenetwork.qa.awo.datacollection.legacy;

import java.util.Comparator;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.SlipFeeCustomDuration;

public class ComparatorCustomDuration implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		SlipFeeCustomDuration duration1 = (SlipFeeCustomDuration)o1;
		SlipFeeCustomDuration duration2 = (SlipFeeCustomDuration)o2;
		
		if(Integer.parseInt(duration1.customDuration) > Integer.parseInt(duration2.customDuration)) {
			return -1;
		} else if(Integer.parseInt(duration1.customDuration) < Integer.parseInt(duration2.customDuration)) {
			return 1;
		}
		
		return 0;
	}
}
