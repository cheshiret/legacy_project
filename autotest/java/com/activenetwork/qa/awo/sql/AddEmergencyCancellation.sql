alter session set current_schema=live_VA;
INSERT INTO O_TRAN_REASON_CODE_USAGE(id,reason_id,usage_id) (select get_sequence('O_TRAN_REASON_CODE_USAGE'), ID,1 from O_TRAN_REASON_CODE where name = 'Emergency Cancellation');
commit;



alter session set current_schema=live_SC;
update x_prop set value = 'true' where NAME = 'ReasonReqForTransfer';
INSERT INTO O_TRAN_REASON_CODE_USAGE (select get_sequence('O_TRAN_REASON_CODE_USAGE'), ID,2 from O_TRAN_REASON_CODE where name = 'Emergency Cancellation');

update x_prop set value = 'true' where NAME = 'ReasonReqForDateChg';
INSERT INTO O_TRAN_REASON_CODE_USAGE (select get_sequence('O_TRAN_REASON_CODE_USAGE'), ID,3 from O_TRAN_REASON_CODE where name = 'Emergency Cancellation');
commit;