ALTER SESSION SET CURRENT_SCHEMA=live_MS;

BEGIN

insert into x_prop (id, name, namespace, type, value ) values( get_sequence('x_prop'), 'ShowProductClass', 'Contract', 'Boolean', 'true' );
insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 4, 'Auto Test Class' );  
insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 5, 'AUto Test SubClass' );

END;
/
COMMIT;

ALTER SESSION SET CURRENT_SCHEMA=live_NRRS;

BEGIN

insert into x_prop (id, name, namespace, type, value ) values( get_sequence('x_prop'), 'ShowProductClass', 'Contract', 'Boolean', 'true' );
insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 4, 'Auto Test Class' );  
insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 5, 'AUto Test SubClass' );
insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 4, 'Test Class' );-- add option to drop down list.
insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 5, 'Test SubClass' );-- add option to drop down list.
END;
/
COMMIT; 