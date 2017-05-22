package com.activenetwork.qa.awo.vpData;

/**
 * 
 * @author CGuo
 *
 * The abstract class vpData should be implemented by any class that is intended to be
 * a verification data which maybe is the combination of multiple data.
 * 
 * all the sub-class will have to implement the equal method.
 * 
 */

public abstract class VpData {

	/**
	 * @param obj: the object to campare
	 * @return true if the "obj" is same with "this", otherwise return false
	 */

	public boolean tested = false;

	public abstract boolean compare(Object obj);

}
