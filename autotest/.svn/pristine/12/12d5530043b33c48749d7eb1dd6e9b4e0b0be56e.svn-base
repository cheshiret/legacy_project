--Add Financial Configuration Default values(Add EFT Default values):
--First to check the Weekly and Daily EFT Schedule Whether existing at current system, if not please add Weekly and Daily EFT Schedule firstly.
--Then use generate Weekly EFT Schedul and Daily EFT schedule to update approciate schedule as below
alter session set current_schema=live_MS;
DECLARE
    WEEKLY_NUM NUMBER;
    DAILY_NUM NUMBER;
BEGIN
    select count(*) into WEEKLY_NUM from f_eft_schedule where f_eft_schedule.eft_frequency = 2;
     if WEEKLY_NUM<1 then
    insert into f_eft_schedule (ID, EFT_FREQUENCY, START_MONTH, START_DAY, INVOICE_DAY_OFFSET, TRANS_DAY_OFFSET, CREATE_USER_ID, CREATE_LOC_ID, CREATE_DATETIME, NAME, STATUS_ID,UPDATE_USER_ID, UPDATE_LOC_ID, UPDATE_DATETIME) VALUES ( GET_SEQUENCE('f_eft_schedule'), 2,0, 3, 0, 2, 70001, 1, '03-NOV-12', 'Weekly', 1 , null, null, null); commit;
    end if;
    insert into f_vendor_fin_conf_default (id, label, eft_type, eft_schdl,eft_failure_enforce, void_rtn_charge_days, rtn_voided_doc, rpt_notifications, rpt_notification_emails, deleted_ind)
select 101, 'Weekly EFT', 2, f_eft_schedule.id, 3, 1, '1', '1;2', 'AO.QAOrmstest@activenetwork.com;noreply@reserveamerica.com', 0 from f_eft_schedule where f_eft_schedule.eft_frequency = 2;commit;
     select count(*) into DAILY_NUM from f_eft_schedule where f_eft_schedule.eft_frequency = 1;
     IF DAILY_NUM < 1 THEN
     insert into f_eft_schedule (ID, EFT_FREQUENCY, START_MONTH, START_DAY, INVOICE_DAY_OFFSET, TRANS_DAY_OFFSET, CREATE_USER_ID, CREATE_LOC_ID, CREATE_DATETIME, NAME, STATUS_ID,UPDATE_USER_ID, UPDATE_LOC_ID, UPDATE_DATETIME) -- Full Hookup
VALUES ( GET_SEQUENCE('f_eft_schedule'), 1,0, 3, 0, 2, 70001, 1, '03-NOV-12', 'Daily', 1 , null, null, null);commit;
     END IF;
     insert into f_vendor_fin_conf_default (id, label, eft_type, eft_schdl,eft_failure_enforce, void_rtn_charge_days, rtn_voided_doc, rpt_notifications, rpt_notification_emails, deleted_ind)
select 102, 'Daily EFT', 2, f_eft_schedule.id, 3, 1, '2', '1;2', 'AO.QAOrmstest@activenetwork.com;noreply@reserveamerica.com', 0 from f_eft_schedule where f_eft_schedule.eft_frequency = 1;


--3. add 'No EFT' default value
insert into f_vendor_fin_conf_default (id, label, eft_type, eft_schdl,eft_failure_enforce, void_rtn_charge_days, rtn_voided_doc, rpt_notifications, rpt_notification_emails, deleted_ind)
values(1103, 'No EFT', 1, null, null, null, 0, null, null, 0);
END;
/
commit;