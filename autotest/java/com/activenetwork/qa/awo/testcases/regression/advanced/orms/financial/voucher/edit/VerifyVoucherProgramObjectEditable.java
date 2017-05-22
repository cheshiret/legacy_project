/**
 *
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.voucher.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VoucherProgram;
import com.activenetwork.qa.awo.pages.orms.financeManager.voucher.FinMgrVoucherProgramDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This test case contains the scenario:
 *              1.verify the object cannot be edited on the voucher program detail page
 * @Preconditions:
 * @SPEC:Edit Voucher Program.UCS,Voucher Programs.UIS
 * @Task#:AUTO-762
 *
 * @author szhou
 * @Date Dec 19, 2011
 */
public class VerifyVoucherProgramObjectEditable extends FinanceManagerTestCase {
	private VoucherProgram program = new VoucherProgram();

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);
		fnm.gotoVouchersPage();
		fnm.gotoVoucherProgramPage();

		// add a new voucher program
		fnm.addNewVoucherProgram(program);

		// edit voucher program
		fnm.searchAndViewVoucherProgramDetails(program.programId);
		this.verifyScheduleObjectCannotEdit();

		fnm.logoutFinanceManager();
	}



	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		// initialize Voucher program info
		program.programeType = "Gift Card";
		program.programeName = "Program for Gift Card test"
				+ DateFunctions.getCurrentTime();
		program.lineOfBusiness = "Parks";
		program.createLocation = "Mississippi Department of Wildlife, Fisheries, and Parks";
		program.startDate = DateFunctions.formatDate(DateFunctions
				.getDateAfterToday(-5), "EEE MMM d yyyy");
		program.endDate = DateFunctions.formatDate(DateFunctions
				.getDateAfterToday(10), "EEE MMM d yyyy");
		program.account = "";
		program.programeStatus = "Active";
	}

	private void verifyScheduleObjectCannotEdit() {
		FinMgrVoucherProgramDetailsPage detailsPg = FinMgrVoucherProgramDetailsPage
				.getInstance();

		logger
				.info("Verify object in the edit voucher program page could not be edit.");

		// verify Voucher Program ID cannot edit
		if (detailsPg.isVoucherProgramIDObjectEditable()) {
			throw new ItemNotFoundException(
					"Voucher Program ID could not be edited .");
		}

		// verify Program Type cannot edit
		if (detailsPg.isProgramTypeObjectEditable()) {
			throw new ItemNotFoundException(
					"Program Type could not be edited .");
		}

		// verify Voucher Program Name cannot edit
		if (detailsPg.isVoucherProgramNameObjectEditable()) {
			throw new ItemNotFoundException(
					"Voucher Program Name could not be edited .");
		}

		// verify Line of Business cannot edit
		if (detailsPg.isLineOfBusinessObjectEditable()) {
			throw new ItemNotFoundException(
					"Line of Business could not be edited .");
		}

		// verify Location for Creation (Revenue Location) cannot edit
		if (detailsPg.isLocationObjectEditable()) {
			throw new ItemNotFoundException(
					"Location for Creation could not be edited .");
		}

	}

}
