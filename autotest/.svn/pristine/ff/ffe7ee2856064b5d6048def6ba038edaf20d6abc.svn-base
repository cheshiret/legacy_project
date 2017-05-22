ALTER SESSION SET CURRENT_SCHEMA=live_SC;

declare
        cnt             VARCHAR(30);
        sql_stmt        VARCHAR(1024);
    BEGIN
--update faclity to support pos fin session
 	select COUNT(*) into cnt from D_LOC_ATTR_VALUE where attr_id='90651' and loc_id=10231;
       IF cnt=0 THEN
          insert into D_LOC_ATTR_VALUE values(get_SEQUENCE('D_LOC_ATTR_VALUE'),10231,90651,'User');
	else
	  update d_loc_attr_value set value='Station' where attr_id=90651 and loc_id=10231;
        END IF;
        
  	select COUNT(*) into cnt from D_LOC_ATTR_VALUE where attr_id='8320' and loc_id=10231;
       IF cnt=0 THEN
          insert into D_LOC_ATTR_VALUE values(get_SEQUENCE('D_LOC_ATTR_VALUE'),10231,8320,'User/Station Allocations and Reconciliation');
	else
	  update d_loc_attr_value set value='User/Station Allocations and Reconciliation' where attr_id=8320 and loc_id=10231;
        END IF;
	select COUNT(*) into cnt from D_LOC_ATTR_VALUE where attr_id='8318' and loc_id=10231;
       IF cnt=0 THEN
          insert into D_LOC_ATTR_VALUE values(get_SEQUENCE('D_LOC_ATTR_VALUE'),10231,8318,'Y');
	else
	  update d_loc_attr_value set value='Y' where attr_id=8318 and loc_id=10231;
        END IF;
    END;
/
COMMIT;