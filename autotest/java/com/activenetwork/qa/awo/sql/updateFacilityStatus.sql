--Update facility status to 'Production'

ALTER SESSION SET CURRENT_SCHEMA=live_MS;

BEGIN

update d_loc set status_id=1 where id=552818;--Mayo River State Park

END;
/
COMMIT; 