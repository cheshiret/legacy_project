alter session set current_schema=live_ms;
--update role to assignable for 'Administrator - Auto' role

SET SERVEROUTPUT ON;

declare
  roleid number;
  sql_stmt VARCHAR2(1024);
  rf_count number(3);
  CURSOR assigned_id_cur is select id from x_role where name like '%Auto';  
  assigned_id assigned_id_cur%rowtype;
  
begin
  select id into roleid from x_role where name='Administrator - Auto';    
              OPEN assigned_id_cur; 
                LOOP
                 FETCH assigned_id_cur INTO assigned_id;
                   DBMS_OUTPUT.PUT_LINE('Check role '||assigned_id.id||' assigned to role '||roleid); 
                sql_stmt := 'SELECT count(*) FROM x_role_role where role_id='||assigned_id.id||' and assignable_role_Id='||roleid; 
                EXECUTE IMMEDIATE sql_stmt INTO rf_count;                            
                   -- if not assigned, insert assignable role                
               IF rf_count=0 THEN
                sql_stmt:= 'INSERT INTO x_role_role VALUES('||assigned_id.id||','||roleid||')';
                 EXECUTE IMMEDIATE sql_stmt;                                
                   DBMS_OUTPUT.PUT_LINE('Add assignable role '|| assigned_id.id || ' for role '||roleid ||' successful'); 
              END IF;      
                 EXIT WHEN assigned_id_cur%NOTFOUND;
                END LOOP; 
              CLOSE assigned_id_cur;
end;
/
commit;


alter session set current_schema=live_RA;

SET SERVEROUTPUT ON;

declare
	roleid number;
	cnt number(3);
  sql_stmt varchar2(1024);
  cursor id_cur is select id from x_role where name in ('PublicWebSupport Admin','Admin.do User','MarketingSpotMgr User','PhotoTool User','UserManagementCallCenter(Internal)');
  id_rec id_cur%rowtype;
  
begin
	select id into roleid from x_role where name='Administrator - Auto';
      open id_cur;
        loop
          fetch id_cur into id_rec;
             DBMS_OUTPUT.PUT_LINE('Fetch role id '||id_rec.id); 
          sql_stmt := 'select count(*) from x_role_role where assignable_role_Id='||roleid||'and role_id='||id_rec.id;
           EXECUTE IMMEDIATE sql_stmt INTO cnt;     
          if cnt=0 then
              sql_stmt:= 'INSERT INTO x_role_role VALUES('||id_rec.id||','||roleid||')';
              EXECUTE IMMEDIATE sql_stmt;                                
              DBMS_OUTPUT.PUT_LINE('Add assignable role '||id_rec.id || ' for role '||roleid ||' successful'); 
          end if;
          exit when id_cur%notfound;
        end loop;
      close id_cur;
end;
/
commit;