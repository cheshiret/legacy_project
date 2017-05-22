alter session set current_schema=live_MS;

update ALL_D_EDUCATION_TYPE set VERIFIABLE_IND=1 where contract = 'MS';

commit;