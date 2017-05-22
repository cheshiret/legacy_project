package com.activenetwork.qa.awo.datacollection.legacy.orms;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 *Script Name: TourTicket.java
 *Generated  :Sep 8, 2010, 2:02:07 PM
 *Description:this data collection is for InvMgrTourTicketsPage
 *@author asun
 */
public class TourTicket {
	public String minIndiv="";
	public String maxIndiv="";
	public String minOrg="";
	public String maxOrg="";
	public List<TicketType> indv_types=new ArrayList<TicketType>();
	public List<TicketType> org_types=new ArrayList<TicketType>();
}
