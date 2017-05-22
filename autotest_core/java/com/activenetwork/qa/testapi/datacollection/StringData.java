package com.activenetwork.qa.testapi.datacollection;

import java.util.HashMap;
import java.util.Set;

/**
 * This is the root class for all data collection classes
 * @author jdu
 *
 */
public class StringData<E extends Enum<E> & DataAttribute> extends Data<E>{
	private static final long serialVersionUID = 1L;

	/**
	 * construct a Data object with the given keys and all null values
	 * @param keys
	 */
	@SafeVarargs
	public StringData(E... keys) {
		data=new HashMap<E, Object>();
		for(E key:keys) {
			put(key,null);
			setToBeCollected(true);
		}
	}
	
	public StringData(Set<E> keys) {
		data=new HashMap<E, Object>();
		for(E key:keys) {
			put(key,null);
			setToBeCollected(true);
		}
	}
	
	public void put(E key, String value) {
		super.put(key, value);
	}
	
	public String get(E key) {
		return (String) super.get(key);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public StringData<E> getEmptyData() {
		return new StringData<E>(this.getKeys());
	}
	
}
