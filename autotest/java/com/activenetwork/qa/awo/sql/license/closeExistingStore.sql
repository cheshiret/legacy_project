--Close existing store which location was 'NY Call Center', run this sql in order to add a Store which location is 'NY Call Center'

ALTER SESSION SET CURRENT_SCHEMA=live_MS;

BEGIN

update D_STORE set status=4 where loc_id in (select id from d_loc where name ='NY Call Center');

END;
/
COMMIT; 