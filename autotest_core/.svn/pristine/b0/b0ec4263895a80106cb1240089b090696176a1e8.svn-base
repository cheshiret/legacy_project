package com.activenetwork.qa.testapi.util;

import com.activenetwork.qa.testapi.page.Page;

/**
 * Keep track of the last 3 Html page visited during the test execution.
 * @author jdu
 *
 */
public class PageTrack {
	/**
	 * Keep track of the last 3 html pages visited
	 */
	private static LimitedQueue<Page> pagesVisited=new LimitedQueue<Page>(3);
		
	public static void addAVisitedPage(Page pg) {
		pagesVisited.add(pg);
	}
	
	public static Page[] listPagesVisisted() {
		return pagesVisited.toArray(new Page[0]);
	}
	
	public static Page getLastPageVisited() {
		if(pagesVisited.size()>0) 
			return pagesVisited.getLast();
		else
			return null;
	}

}
