alter session set current_schema=global;
update d_user_auth set pwd_change_date=sysdate+360 where login like 'qa-auto-%';
commit;