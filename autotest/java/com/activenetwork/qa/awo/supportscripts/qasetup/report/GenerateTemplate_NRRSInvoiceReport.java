package com.activenetwork.qa.awo.supportscripts.qasetup.report;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.keywords.orms.VenueManager;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: this script used to create report template for NRRS invoice report in advanced suite
 * @precondition: this script should be run after add transaction fee schedule script
 * @author ssong
 * @Date Aug 09, 2012
 */

public class GenerateTemplate_NRRSInvoiceReport extends ResourceManagerTestCase{

	private LoginInfo loginVm = new LoginInfo();
	private VenueManager vm = VenueManager.getInstance();
	private FinanceManager fnm = FinanceManager.getInstance();
	private TicketInfo ticket = new TicketInfo();
	private String sufix = "_TicketDeliveryMethod";//can not changed, must be consistent with test case which use this template
	
	@Override
	public void execute() {
		preparePartnerInvoice();
		
		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, templatesPath);
		
		rd.reportName = "NRRS Invoice Detail Report";
		rd.reportFormat = "XLS";
		rm.selectOneRpt(rd.group, rd.reportName);
		String firstEmailSubject = rd.emailSubject;
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.runSpecialRpt(rd, templatesPath);
		String saveAsName = rd.reportName.replaceAll(" ", "")+sufix;
		rm.getReportFromMailBox(host, username, password, templatesPath,rd.fileName, firstEmailSubject);
		rm.getReportFromMailBox(host, username, password, templatesPath,saveAsName, rd.emailSubject);
		
		
		
		rm.logoutResourceManager();
	}

	private void preparePartnerInvoice() {
		vm.loginVenueManager(loginVm);
		
		vm.makeTicketOrderToCart(ticket);
		vm.selectCustomerFromOrderCart(cust);
		vm.processOrderCart(pay);
		
		ticket.deliveryMethod = "Print at Home";
		vm.makeTicketOrderToCart(ticket);
		vm.selectCustomerFromOrderCart(cust);
		vm.processOrderCart(pay);
		
		ticket.deliveryMethod = "Mail Out";
		vm.makeTicketOrderToCart(ticket);
		vm.selectCustomerFromOrderCart(cust);
		vm.processOrderCart(pay);
		
		vm.logoutVenueManager();
		
		fnm.loginFinanceManager(login);
		fnm.gotoPartnerInvoicePage();
		String num = fnm.runPartnerInvoice("NRRS",DateFunctions.getDateAfterToday(-1),DateFunctions.getToday(), false);
		wrapInvoiceNum(num);
		fnm.logoutFinanceManager();		
	}

	private void wrapInvoiceNum(String num){
		String fromDate = DateFunctions.getDateAfterToday(-1,"MM-dd-yyyy");
		String toDate = DateFunctions.getToday("MM-dd-yyyy");
		StringBuilder sb = new StringBuilder();
		sb.append("#").append(num);
		sb.append(" - ").append(fromDate).append(" - ").append(toDate).append(" - ");
		sb.append(rd.locationIDNoValidate);
		rd.invoice = sb.toString();
		fnm.writeQADB("NRRSInvoice_Ticket", rd.invoice);
	}
	 
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "Financial Reports";
		rd.reportName = "NRRS Invoice Report";
		rd.locationIDNoValidate = "NRRS";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "EMAIL";
		rd.fileName = rd.reportName.replaceAll(" ", "")+sufix;
		
		loginVm.url = login.url;
		loginVm.userName = TestProperty.getProperty("orms.vm.user");
		loginVm.password = TestProperty.getProperty("orms.vm.pw");
		
		loginVm.contract = login.contract;
		loginVm.location = "NRRS - Venue Supervisor/VOYAGEURS NATIONAL PARK TOURS";
		loginVm.station = "Ash River VC";
		
		cust.fName = TestProperty.getProperty("app.cust.fname");
		cust.lName = TestProperty.getProperty("app.cust.lname");
		cust.email=vm.getNextEmail();
		
		ticket.tour = "Discovery Cruise";
		ticket.startDate = DateFunctions.getDateAfterToday(2);
		ticket.types = new String[1];
		ticket.typeNums = new String[1];
		ticket.types[0] = "Adult";
		ticket.typeNums[0] = "1";
		ticket.quantity = "1";
		ticket.deliveryMethod = "Will Call";
	}

}
