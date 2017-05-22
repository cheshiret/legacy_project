package com.activenetwork.qa.testapi.util;

import java.util.LinkedList;

/**
 * This is a FIFO Queue with limited size. If the size limitation is reached, adding new element
 * will cause another element to be removed 
 * @author jdu
 *
 * @param <E>
 */
public class LimitedQueue<E> extends LinkedList<E> {
	private int limit;
	
	public LimitedQueue(int limit) {
		this.limit=limit;
	}

	@Override
	public boolean add(E o) {
		super.add(o);
		while(size() > limit) {
			super.remove();
		}
		return true;
	}

}
