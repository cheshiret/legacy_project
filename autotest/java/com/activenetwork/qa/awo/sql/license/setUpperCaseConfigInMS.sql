alter session set current_schema=live_MS;
update X_PROP set value='true' where name like 'UppercaseOutput';
commit;