--- Update schema name before run the script 
--- Elena Shyroka, Nov 21, 2012

-- alter session set current_schema=qa1_nov01_global;    -- qa1
-- alter session set current_schema=qa3_nov01_global;   -- qa3 

alter session set current_schema=global;    -- qa2
-- alter session set current_schema=qa4_nov01_global;   -- qa4

--- Enable DBMS_OUTPUT
exec DBMS_OUTPUT.ENABLE (buffer_size => NULL);

SET SERVEROUTPUT ON;
-- Allows entry of & in strings
SET DEFINE OFF;

SET LINESIZE 125;
--SET TERMOUT ON
--SPOOL ormsAddQaUserRoles

DECLARE

env VARCHAR(30);
TYPE t_usr_list IS TABLE OF VARCHAR2(20);
     usr_list        t_usr_list;
    
TYPE user_list IS TABLE OF VARCHAR2(30);
     ulist user_list;
     count1 number(3); 
  
BEGIN
     
    -- Add global users that do not exist in production, this part is for QA only
    IF ra_utility.add_qa_users.get_env_type='qa' 
    THEN 
     --- create new global users list for QA only
    
       ulist:= user_list('qa-auto');  ---  -adm','qa-auto-cm','qa-auto-om','qa-auto-fm');
     --- add global users  
      FOR count1 in ulist.FIRST..ulist.LAST
        LOOP
        ra_utility.add_qa_users.add_global_user(ulist(count1));
        END LOOP; 
    END IF;
    
    DBMS_OUTPUT.PUT_LINE('Begin adding users: ' || TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
    
    -- Compile list of QA logins/users
    SELECT login BULK COLLECT INTO usr_list FROM (
        SELECT login FROM d_user_auth WHERE (
               login LIKE 'qa-auto-%'));
               
    DBMS_OUTPUT.PUT_LINE('Begin adding roles: ' || TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
    -- Loop through list of QA logins/users
    FOR i IN usr_list.FIRST .. usr_list.LAST
    LOOP
        --- DBMS_OUTPUT.PUT_LINE('Adding roles for ' || usr_list(i) || '...');
        IF usr_list(i) LIKE '%-adm' THEN    -- Administrators          
            ra_utility.add_qa_users.add_user_role ('CA',   usr_list(i), 'Administrator - Auto', 1); --California Parks and Recreation
            ra_utility.add_qa_users.add_user_role ('CO',   usr_list(i), 'Administrator - Auto', 1); --Department of Natural Resources (DNR)
            ra_utility.add_qa_users.add_user_role ('CT',   usr_list(i), 'Administrator - Auto', 1); --State of Connecticut
            ra_utility.add_qa_users.add_user_role ('DE',   usr_list(i), 'Administrator - Auto', 1); --State of Delaware
            ra_utility.add_qa_users.add_user_role ('EB',   usr_list(i), 'Administrator - Auto',1);-- East Bay District
            ra_utility.add_qa_users.add_user_role ('ELS',  usr_list(i), 'Administrator - Auto', 1); --ELS
            ra_utility.add_qa_users.add_user_role ('ELS2', usr_list(i), 'Administrator - Auto', 1); --ELS2
            ra_utility.add_qa_users.add_user_role ('FL',   usr_list(i), 'Administrator - Auto', 1); --DEP
            ra_utility.add_qa_users.add_user_role ('IA',   usr_list(i), 'Administrator - Auto', 1); --State of Iowa
            ra_utility.add_qa_users.add_user_role ('ID',   usr_list(i), 'Administrator - Auto', 1); --Idaho Contract
            ra_utility.add_qa_users.add_user_role ('IL',   usr_list(i), 'Administrator - Auto', 1); --Illinois State Parks
            ra_utility.add_qa_users.add_user_role ('IN',   usr_list(i), 'Administrator - Auto', 1); --State of Indiana
            ra_utility.add_qa_users.add_user_role ('KOA',  usr_list(i), 'Administrator - Auto', 1); --KOA
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'Administrator - Auto', 1); --Commonwealth of Kentucky
            ra_utility.add_qa_users.add_user_role ('LA',   usr_list(i), 'Administrator - Auto', 1); --State of Louisiana
            ra_utility.add_qa_users.add_user_role ('LARC', usr_list(i), 'Administrator - Auto', 1); --Larimer County
            ra_utility.add_qa_users.add_user_role ('MA',   usr_list(i), 'Administrator - Auto', 1); --MA Department of Conservation and Recreation
            ra_utility.add_qa_users.add_user_role ('MD',   usr_list(i), 'Administrator - Auto', 1); --State of Maryland
            ra_utility.add_qa_users.add_user_role ('MRV',  usr_list(i), 'Administrator - Auto', 1); --Morgan RV
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'Administrator - Auto', 1); --Mississippi Department of Wildlife, Fisheries, and Parks
            ra_utility.add_qa_users.add_user_role ('MT',   usr_list(i), 'Administrator - Auto', 1); --State of Montana
            ra_utility.add_qa_users.add_user_role ('NC',   usr_list(i), 'Administrator - Auto', 1); -- 'North Carolina State Parks'
            ra_utility.add_qa_users.add_user_role ('NE',   usr_list(i), 'Administrator - Auto', 1); --NGPC
            
            ra_utility.add_qa_users.add_user_role ('NH',   usr_list(i), 'Administrator - Auto', 1); --New Hampshire Contract
            ra_utility.add_qa_users.add_user_role ('NM',   usr_list(i), 'Administrator - Auto',1);-- 'State Of New Mexico'
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Administrator - Auto', 1); --NRRS
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Administrator - Auto', 72600); --Boundary Waters Canoe Area Wilderness (Reservations)
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Administrator - Auto', 72777); --VOYAGEURS NATIONAL PARK TOURS
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Administrator - Auto', 70641); --BEAVER CREEK (IDAHO)
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'Administrator - Auto', 1); --Contract
            ra_utility.add_qa_users.add_user_role ('OR',   usr_list(i), 'Administrator - Auto', 1); --State of Oregon
            ra_utility.add_qa_users.add_user_role ('ORNG', usr_list(i), 'Administrator - Auto', 1); --County of Orange
            ra_utility.add_qa_users.add_user_role ('RAGV', usr_list(i), 'Administrator - Auto', 1); --Government Contracts
            ra_utility.add_qa_users.add_user_role ('RI',   usr_list(i), 'Administrator - Auto', 1); --State of Rhode Island Contract
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'Administrator - Auto', 1); --South Carolina State Park Service
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'Administrator - Auto', 10413); --HICKORY KNOB
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'Administrator - Auto', 10409); --DREHER ISLAND
            ra_utility.add_qa_users.add_user_role ('TRLS', usr_list(i), 'Administrator - Auto', 1); --TRLS
            ra_utility.add_qa_users.add_user_role ('UT',   usr_list(i), 'Administrator - Auto', 1); --State of Utah
            ra_utility.add_qa_users.add_user_role ('VA',   usr_list(i), 'Administrator - Auto', 1); --Virginia Department of Conservation and Recreation
            ra_utility.add_qa_users.add_user_role ('WI',   usr_list(i), 'Administrator - Auto', 1); --Department of Natural Resources (DNR)
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Administrator - Auto', 73494); --ACORN CAMP EAST

      -- Add permission for Support Centre
			INSERT INTO d_user_auth_perm (id, user_auth_id, permission_id) 
      SELECT a.id, a.id, 1 FROM d_user_auth a WHERE a.login = usr_list(i) 
      AND NOT EXISTS (SELECT id FROM d_user_auth_perm WHERE user_auth_id = a.id AND permission_id = 1);

         ELSIF usr_list(i) LIKE '%-cm' THEN    -- Call Centre Agents/Managers
            ra_utility.add_qa_users.add_user_role ('CA',   usr_list(i), 'CA - Call Center Manager - Auto', 804); --CA Call Center
            ra_utility.add_qa_users.add_user_role ('CO',   usr_list(i), 'Call Center Manager - Auto', 822); --CO Call Center
            ra_utility.add_qa_users.add_user_role ('CT',   usr_list(i), 'CT - Call Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('DE',   usr_list(i), 'Call Center Manager - Auto', 784); --Frostburg Call Center
            ra_utility.add_qa_users.add_user_role ('EB',   usr_list(i), 'EB Call Center Manager - Auto',    825);-- EBAY Call Center
            ra_utility.add_qa_users.add_user_role ('ELS2', usr_list(i), 'ELS - Call Center Manager - Auto', 793); --ELS Call Center
            ra_utility.add_qa_users.add_user_role ('FL',   usr_list(i), 'FL - Call Center Manager - Auto', 829); --FL Call Center
            ra_utility.add_qa_users.add_user_role ('IA',   usr_list(i), 'IA - Call Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('ID',   usr_list(i), 'ID - Call Center Manager - Auto', 820); --WI Call Center
            ra_utility.add_qa_users.add_user_role ('IN',   usr_list(i), 'Call Center Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Call Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('LA',   usr_list(i), 'LA - Call Center Manager - Auto', 817); --LA Call Center
            ra_utility.add_qa_users.add_user_role ('LARC', usr_list(i), 'Call Center Manager - Auto', 786); --IN Call Center
            ra_utility.add_qa_users.add_user_role ('MA',   usr_list(i), 'MA - Call Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('MD',   usr_list(i), 'Call Center Manager - Auto', 784); --Frostburg Call Center
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Call Center Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('MT',   usr_list(i), 'MT - Call Center Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('NC',   usr_list(i), 'AWO - Call Center Manager - Auto', 784); --Frostburg Call Center
            ra_utility.add_qa_users.add_user_role ('NE',   usr_list(i), 'NE - Call Manager - Auto', 818); --NE Call Center
            ra_utility.add_qa_users.add_user_role ('NH',   usr_list(i), 'NH - Call Center Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('NM',   usr_list(i), 'Call Center Manager - Auto', 784); --Frostburg Call Center
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Call Center Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'Call Center Manager - Auto', 765); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('OR',   usr_list(i), 'OR - Call Manager - Auto', 780); --OR Call Center
            ra_utility.add_qa_users.add_user_role ('ORNG', usr_list(i), 'Call Center Manager - Auto', 804); --CA Call Center
            ra_utility.add_qa_users.add_user_role ('RAGV', usr_list(i), 'Call Center Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('RI',   usr_list(i), 'RI - Call Center Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Call Center Manager - Auto', 820); --WI Call Center
            ra_utility.add_qa_users.add_user_role ('UT',   usr_list(i), 'UT - Call Manager - Auto', 782); --UT Call Center
            ra_utility.add_qa_users.add_user_role ('VA',   usr_list(i), 'VA Call Center Manager - Auto', 826); --VA Call Center
            ra_utility.add_qa_users.add_user_role ('WI',   usr_list(i), 'WI - Call Manager - Auto', 820); --WI Call Center

         ELSIF usr_list(i) LIKE '%-fm' THEN    -- Field/Park Users/Supervisors, VenueMgr Users
            -- CA - no Field (Camping)
            ra_utility.add_qa_users.add_user_role ('CA',   usr_list(i), 'CA - Venue Manager Admin - Auto', 120101); --ANO NUEVO
            ra_utility.add_qa_users.add_user_role ('CA',   usr_list(i), 'CA - Venue Manager Admin - Auto', 120102); --HEARST CASTLE
            ra_utility.add_qa_users.add_user_role ('CA',   usr_list(i), 'CA - Venue Manager Admin - Auto', 120273); --MARSHALL GOLD SHP
	    ra_utility.add_qa_users.add_user_role ('CA',   usr_list(i), 'CA - Call Center Manager - Auto', 120003); --ANGEL ISLAND SP
            ra_utility.add_qa_users.add_user_role ('CO',   usr_list(i), 'CRO Field Manager - Auto',            50039);   --- 'RIDGWAY State Park'   'RIDGWAY SRA'
            ra_utility.add_qa_users.add_user_role ('CO',   usr_list(i), 'CRO Field Manager - Auto', 50043); --SYLVAN LAKE
            ra_utility.add_qa_users.add_user_role ('CT',   usr_list(i), 'Park Manager - Auto', 100102); --BLACK ROCK STATE PARK
            ra_utility.add_qa_users.add_user_role ('DE',   usr_list(i), 'Field Supervisor - Auto', 360122); --Trap Pond State Park
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'HF HQ Role - Auto', 155852); --CLARK CREEK NATURAL AREA
            ra_utility.add_qa_users.add_user_role ('FL',   usr_list(i), 'FL-Park Manager - Auto', 281101); --Blue Spring SP
            ra_utility.add_qa_users.add_user_role ('IA',   usr_list(i), 'IA - Park Supervisor - Auto', 610105); --Backbone State Park
            ra_utility.add_qa_users.add_user_role ('ID',   usr_list(i), 'Park Supervisor - Auto', 313031); --Ponderosa State Park
            ra_utility.add_qa_users.add_user_role ('ID',   usr_list(i), 'Park Supervisor - Auto',             311018);--   HEYBURN STATE PARK
            ra_utility.add_qa_users.add_user_role ('ID',   usr_list(i), 'Park Supervisor - Auto',             311011);--   PRIEST LAKE STATE PARK
            ra_utility.add_qa_users.add_user_role ('ID',   usr_list(i), 'Park Supervisor - Auto', 311015); --Farragut State Park
            ra_utility.add_qa_users.add_user_role ('ID',   usr_list(i), 'Park Supervisor - Auto', 311025); --Winchester Lake State Park 
            ra_utility.add_qa_users.add_user_role ('ID',   usr_list(i), 'Park Supervisor - Auto', 315045); --Castle Rocks State Park
            ra_utility.add_qa_users.add_user_role ('IL',   usr_list(i), 'Park Manager - Auto', 454521); --Rock Cut State Park
            ra_utility.add_qa_users.add_user_role ('IN',   usr_list(i), 'Field Supervisor - Auto', 570053); --SHAKAMAK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 90800); --KENTUCKY HORSE PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91818); --LEVI JACKSON STATE PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91913); --PENNYRILE FOREST STATE RESORT PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91831); --YATESVILLE LAKE STATE PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91813); --GREEN RIVER LAKE STATE PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91908); --KENLAKE STATE RESORT PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91903); --CARTER CAVES STATE RESORT PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91833); --CARR CREEK STATE PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91823); --PAINTSVILLE LAKE STATE PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91912); --NATURAL BRIDGE STATE RESORT PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91911); --LAKE CUMBERLAND STATE RESORT PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91832); --NOLIN LAKE STATE PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91812); --GRAYSON LAKE STATE PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91904); --CUMBERLAND FALLS STATE RESORT PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91815); --KINCAID LAKE STATE PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91910); --LAKE BARKLEY STATE RESORT PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91817); --LAKE MALONE STATE PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91820); --MY OLD KENTUCKY HOME STATE PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91915); --ROUGH RIVER STATE RESORT PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91825); --TAYLORSVILLE LAKE STATE PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91909); --KENTUCKY DAM VILLAGE STATE RESORT PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91901); --BARREN RIVER LAKE STATE RESORT PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91803); --BIG BONE LICK STATE PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91917); --BLUE LICKS BATTLEFIELD STATE RESORT PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91805); --COLUMBUS BELMONT STATE PARK
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Park Supervisor - Auto', 91916); --DALE HOLLOW LAKE STATE RESORT PARK
            ra_utility.add_qa_users.add_user_role ('LA',   usr_list(i), 'LA - Park Manager - Auto', 240027); --Chicot State Park
            ra_utility.add_qa_users.add_user_role ('MA',   usr_list(i), 'Field Supervisor & User Admin - Auto', 32613); --Mohawk Trail State Forest
            ra_utility.add_qa_users.add_user_role ('MD',   usr_list(i), 'Field Supervisor - Auto', 380523); --HERRINGTON MANOR STATE PARK
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'HF Administrator - Auto', 1); --Mississippi Department of Wildlife, Fisheries, and Parks
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 154848); --ROOSEVELT STATE PARK
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 154842); --CLARKCO
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'HF HQ Role - Auto', 151189); --LAKE LINCOLN STATE PARK(Store Loc)
            ra_utility.add_qa_users.add_user_role ('MA',   usr_list(i), 'Field Supervisor & User Admin - Auto', 32611); --Massasoit State Park
            ra_utility.add_qa_users.add_user_role ('MA',   usr_list(i), 'Field Supervisor & User Admin - Auto', 32623); --Tolland State Forest
            ra_utility.add_qa_users.add_user_role ('MA',   usr_list(i), 'Field Supervisor & User Admin - Auto', 32602); --Beartown State Forest
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 151818); --TRACE
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 157850); --BUCCANEER STATE PARK
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'HF HQ Role - Auto', 157850); --BUCCANEER STATE PARK
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 154843); --GOLDEN MEMORIAL
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 153824); --HOLMES COUNTY
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 153825); --LEROY PERCY
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 151814); --JP COLEMAN
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 155853); --LAKE LINCOLN
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 155844); --LEFLEURS BLUFF
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 155852); --CLARK CREEK NATURAL AREA
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 155847); --PERCY QUIN
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 151817); --TOMBIGBEE
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 152812); --HUGH WHITE
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 156846); --PAUL B JOHNSON
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 152813); --JOHN W KYLE STATE PARK
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 151831); --LEGION STATE PARK
	    ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Park Manager - Auto', 152811); --GEORGE P COSSAR
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'HF HQ Role - Auto', 156849); --SHEPARD STATE PARK
            ra_utility.add_qa_users.add_user_role ('MT',   usr_list(i), 'MT - Park Supervisor - Auto', 630511); --Cooney
            ra_utility.add_qa_users.add_user_role ('NC',   usr_list(i), 'NC - Field Supervisor - Auto', 552903); -- Jordan Lake State Rec Area
            ra_utility.add_qa_users.add_user_role ('NC',   usr_list(i), 'Administrator - Auto', 552903); -- Jordan Lake State Rec Area[For verify feature assigned]
	    ra_utility.add_qa_users.add_user_role ('NC',   usr_list(i), 'NC - Field Supervisor - Auto', 552818); -- Mayo River State Park
            ra_utility.add_qa_users.add_user_role ('NC',   usr_list(i), 'NC - Field Supervisor - Auto', 552835); -- South Raven Rock State Park
            ra_utility.add_qa_users.add_user_role ('NC',   usr_list(i), 'NC - Field Supervisor - Auto', 552861); -- South Mountains State Park
            ra_utility.add_qa_users.add_user_role ('NC',   usr_list(i), 'NC - Field Supervisor - Auto', 552862); -- Stone Mountain State Park
	    ra_utility.add_qa_users.add_user_role ('NC',   usr_list(i), 'NC - Field Supervisor - Auto', 552865); --Gorges State Park
	    ra_utility.add_qa_users.add_user_role ('NC',   usr_list(i), 'NC - Field Supervisor - Auto', 552834); -- Medoc Mountain State Park
	    ra_utility.add_qa_users.add_user_role ('NC',   usr_list(i), 'NC - Field Supervisor - Auto', 552806); -- Carolina Beach State Park
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 70980); --SANTA CRUZ SCORPION
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Permit Manager Admin - Auto', 72600); --Boundary Waters Canoe Area Wilderness (Reservations)
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 75500); --WAPITI CAMPGROUND
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 71709); --WILLOW
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 74053); --ALLEY SPRING
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 70673); --SUNDANCE
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 70369); --WEST TENSLEEP LAKE
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 73868); --REX HALE
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 73851); --SHELL CREEK
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 70023); --RYAN PARK
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 73798); --SIBLEY LAKE
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 70258); --DOGWOOD
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 70197); --WEST SULLIVAN
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 70679 ); -- SULLIVAN LAKE GROUP CAMPGROUND
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 75070); --DEAD SWEDE
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 70321); --SALMON LA SAC
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 73568); --TANEUM CABIN
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 71698); --PETERSON PRAIRIE
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 70929); --HODGDON MEADOW
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 73389); --SANDY LAKE
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 71579); --LUPINE
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 70760); --CAMP DICK
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 71717); --LOWER LITTLE TRUCKEE
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 70161); --PLASKETT CREEK CAMPGROUND
 	        ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 70195); --EAST SULLIVAN
 	        ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 73898); --PIONEER PARK
 	        ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 73318); --OUTLET (POMONA LAKE)
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 77817); --MAMMOTH CAVE NATIONAL PARK TOURS
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 77814); --ROOSEVELT-VANDERBILT NATIONAL HISTORIC SITES
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 72369); --PEARL HARBOR HISTORIC SITES (USS Arizona)
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 93768); --FIERY FURNACE TOUR - ARCHES
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 77813); --Carlsbad Caverns National Park Tours
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 72270); --BLANCHARD SPRINGS CAVERNS
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 77812); --Frederick Douglass National Historic Site Tours
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 72397); --MESA VERDE NATIONAL PARK
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 72168); --Carrizo Plain National Monument Tours
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 72413); --FIREFLY EVENT
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 72411); --SWEET HOME HERITAGE HIKES
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 72291); --Scotty's Castle - Death Valley National Park
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 79052); --NATIONAL ARCHIVES
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 77815); --INDEPENDENCE NATIONAL HISTORICAL PARK TOUR
            ra_utility.add_qa_users.add_user_role ('CA', usr_list(i), 'CA - Venue Manager Admin - Auto', 120265); --STATE INDIAN MUSEUM
            ra_utility.add_qa_users.add_user_role ('CA', usr_list(i), 'CA - Venue Manager Admin - Auto', 120272); --PETALUMA ADOBE SHP
            ra_utility.add_qa_users.add_user_role ('CA', usr_list(i), 'CA - Venue Manager Admin - Auto', 120274); --SAN JUAN BAUTISTA SHP
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 75652);  -- 'PIONEER '
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 73346); --PLYMOUTH
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 73777); --RED BRIDGE (WA)
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 70196); --NOISY CREEK
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 73773); --MARBLE CREEK
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 74029); --LODGEPOLE (WA)
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 74020); --LITTLE NACHES
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 73799); --LA WIS WIS
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 74028); --WINDY POINT (WA)
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 70945); --OHANAPECOSH CAMPGROUND
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 73511); --HORSE CREEK
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 73494); --ACORN CAMP EAST
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 75387); --ALDER DUNE
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Field Supervisor - Auto', 71003); --ALLEY CREEK CAMP
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 71746); --YOUNG LAKE (SOUTH) CABIN
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Venue Supervisor - Auto', 72777); --VOYAGEURS NATIONAL PARK TOURS
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Permit Manager Admin - Auto', 72600); --Boundary Waters Canoe Area Wilderness (Reservations)
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Permit Manager Admin - Auto', 72356); --PACK CREEK
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Permit Manager Admin - Auto', 75533); -- SALMON RIVER (4 Rivers)
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Permit Manager Admin - Auto', 75535); -- SELWAY RIVER (4 Rivers)
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Permit Manager Admin - Auto', 75536); -- HELLS CANYON - SNAKE RIVER (4 Rivers)
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Permit Manager Admin - Auto', 75534); -- MIDDLE FORK OF THE SALMON (4 Rivers)
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Permit Manager Admin - Auto', 72203); -- Inyo National Forest - Wilderness Permits
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Permit Manager Admin - Auto', 72342); --WOLF ROCK CLIMBING PERMITS
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Permit Manager Admin - Auto', 72202);-- Desolation Wilderness Permit
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Permit Manager Admin - Auto', 72201); --Mt. WHITNEY
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Permit Manager Admin - Auto', 79064); --CABLES ON HALF DOME
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Permit Manager Admin - Auto', 72280); --ENCHANTMENT PERMIT AREA
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'Permit Manager Admin - Auto', 74211); --Pere Marquette National Scenic River Watercraft Permits (Huron Manistee)
            ra_utility.add_qa_users.add_user_role ('NE',   usr_list(i), 'NE - Park Supervisor - Auto', 230077); --Fort Robinson SP
            ra_utility.add_qa_users.add_user_role ('NE',   usr_list(i), 'NE - Park Supervisor - Auto', 230224);-- 'PLATTE RIVER SP'
            ra_utility.add_qa_users.add_user_role ('NE',   usr_list(i), 'NE - Park Supervisor - Auto', 230099); --Lake McConaughy SRA
            ra_utility.add_qa_users.add_user_role ('NE',   usr_list(i), 'NE - Park Supervisor - Auto', 230196); --Windmill SRA
            ra_utility.add_qa_users.add_user_role ('NE',   usr_list(i), 'NE - Park Supervisor - Auto', 230121); --Lake Minatare SRA
            ra_utility.add_qa_users.add_user_role ('NE',   usr_list(i), 'NE - Park Supervisor - Auto', 230275); --Calamus SRA
            ra_utility.add_qa_users.add_user_role ('NE',   usr_list(i), 'NE - Park Supervisor - Auto', 230034); --Branched Oak SRA
            ra_utility.add_qa_users.add_user_role ('NE',   usr_list(i), 'NE - Park Supervisor - Auto', 230042); --Chadron SP
            ra_utility.add_qa_users.add_user_role ('NE',   usr_list(i), 'NE - Park Supervisor - Auto', 230100); --Lake Ogallala SRA
            ra_utility.add_qa_users.add_user_role ('NE',   usr_list(i), 'NE - Park Supervisor - Auto', 230101);-- Lewis  and Clark SRA
            ra_utility.add_qa_users.add_user_role ('NH',   usr_list(i), 'NH - Field Supervisor - Auto', 270065); --PILLSBURY STATE PARK
            ra_utility.add_qa_users.add_user_role ('NH',   usr_list(i), 'NH - Field Supervisor - Auto', 270082); --UMBAGOG LAKE STATE PARK
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'DEC Field User - ORMS LIVE - Auto', 657); --LEWEY LAKE
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'OPR Field User - ORMS LIVE - Auto', 375); --LETCHWORTH STATE PARK
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'DEC Field User - ORMS LIVE - Auto', 574); --FISH CREEK POND CAMPGROUND
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'DEC Field User - ORMS LIVE - Auto', 100); --MONGAUP POND
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'OPR Field User - ORMS LIVE - Auto', 410); --TACONIC (COPAKE FALLS)
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'OPR Field User - ORMS LIVE - Auto', 409); --CLARENCE FAHNESTOCK STATE PARK
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'OPR Field User - ORMS LIVE - Auto', 326); --SEBAGO CABINS
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'OPR Field User - ORMS LIVE - Auto', 755); --CANOE POINT
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'OPR Field User - ORMS LIVE - Auto', 427); --ROBERT MOSES
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'OPR Supervisor - ORMS LIVE - Auto', 326); --SEBAGO CABINS
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'OPR Supervisor - ORMS LIVE - Auto', 90); --BEAVERKILL
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'OPR Supervisor - ORMS LIVE - Auto', 484); --LONG POINT STATE PARK
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'Park Manager - Marina - Auto', 3885); --Allan H. Treman State Marine Park
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'Park Manager - Marina - Auto', 19); --BOWMAN LAKE STATE PARK
            ra_utility.add_qa_users.add_user_role ('OR',   usr_list(i), 'OR - Field Supervisor - Auto', 402126); --Beverly Beach State Park
            ra_utility.add_qa_users.add_user_role ('OR',   usr_list(i), 'OR - Field Supervisor - Auto', 405408); --Wallowa Lake State Park
            ra_utility.add_qa_users.add_user_role ('ORNG', usr_list(i), 'Field Supervisor - Auto', 590437); --ONEILL REGIONAL PARK
            ra_utility.add_qa_users.add_user_role ('RI',   usr_list(i), 'RI - Park Manager - Auto', 252711); --Burlingame State Park
            ra_utility.add_qa_users.add_user_role ('RI',   usr_list(i), 'RI - Park Manager - Auto', 253123); -- George Washington Campground
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10316); --KEOWEE-TOXAWAY SNA
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10219); --LITTLE PEE DEE
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10371); --DEVILS FORK
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10204); --BARNWELL
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10417); --KINGS MOUNTAIN
 	    ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10231); --GOODALE
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10413); --HICKORY KNOB
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10329); --TABLE ROCK
            -- ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field User - Auto', 10201); --AIKEN
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10201); --AIKEN
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10430); --HAMILTON BRANCH
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10228); --SESQUICENTENNIAL
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10120); --MYRTLE BEACH
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10409); --DREHER ISLAND
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10110); --EDISTO BEACH
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10402); --ANDREW JACKSON
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10412); --LAKE GREENWOOD
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10227); --SANTEE
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10107); --COLLETON
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Field Supervisor - Auto', 10308); --CROFT
	    ra_utility.add_qa_users.add_user_role ('SK',   usr_list(i), 'SK - Compliance Field Offices - Auto', 162113); --Ministry of Environment - Hudson Bay(Store Loc)
            ra_utility.add_qa_users.add_user_role ('UT',   usr_list(i), 'UT - Field Supervisor - Auto', 343031); --Antelope Island State Park
            ra_utility.add_qa_users.add_user_role ('VA',   usr_list(i), 'VA Field Supervisor - Auto', 140168); --CLAYTOR LAKE STATE PARK
            ra_utility.add_qa_users.add_user_role ('VA',   usr_list(i), 'VA Field Supervisor - Auto', 140175); --LAKE ANNA STATE PARK
            ra_utility.add_qa_users.add_user_role ('VA',   usr_list(i), 'VA Field Supervisor - Auto', 140187); --NEW RIVER TRAIL STATE PARK
            ra_utility.add_qa_users.add_user_role ('VA',   usr_list(i), 'VA Field Supervisor - Auto', 140185); --WESTMORELAND STATE PARK
            ra_utility.add_qa_users.add_user_role ('WI',   usr_list(i), 'Supervisor - FieldManager - Auto', 60003); --AMNICON FALLS STATE PARK
            ra_utility.add_qa_users.add_user_role ('WI',   usr_list(i), 'Supervisor - FieldManager - Auto', 60039); --ROCK ISLAND STATE PARK
            ra_utility.add_qa_users.add_user_role ('WI',   usr_list(i), 'Supervisor - FieldManager - Auto', 60013); --DEVILS LAKE STATE PARK
            
        ELSIF usr_list(i) LIKE '%-om' THEN    -- Customer Service Agents/Managers
            ra_utility.add_qa_users.add_user_role ('CA',   usr_list(i), 'CA - Customer Service Manager - Auto', 804); --CA Call Center
            ra_utility.add_qa_users.add_user_role ('CO',   usr_list(i), 'Call Center Manager - Auto', 822); --CO Call Center
            ra_utility.add_qa_users.add_user_role ('CT',   usr_list(i), 'CT - Customer Service Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('DE',   usr_list(i), 'Customer Service Manager - Auto', 784); --Frostburg Call Center
            ra_utility.add_qa_users.add_user_role ('EB',   usr_list(i), 'EB Call Center Manager - Auto', 825);-- EBAY Call Center
            ra_utility.add_qa_users.add_user_role ('ELS2', usr_list(i), 'ELS - Customer Service Manager - Auto', 793); --ELS Call Center
            ra_utility.add_qa_users.add_user_role ('FL',   usr_list(i), 'FL - Customer Service Manager - Auto', 829); --FL Call Center
            ra_utility.add_qa_users.add_user_role ('IA',   usr_list(i), 'IA - Customer Service Manager - Auto', 820); --WI Call Center
            ra_utility.add_qa_users.add_user_role ('ID',   usr_list(i), 'ID - Customer Service Manager - Auto', 820); --WI Call Center
            ra_utility.add_qa_users.add_user_role ('IL',   usr_list(i), 'Customer Service Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('IN',   usr_list(i), 'Customer Service Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('KY',   usr_list(i), 'KY - Customer Service Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('KS',   usr_list(i), 'Customer Service Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('LA',   usr_list(i), 'LA - Customer Service Manager - Auto', 817); --LA Call Center
            ra_utility.add_qa_users.add_user_role ('LARC', usr_list(i), 'Call Center Manager - Auto', 786); --IN Call Center
            ra_utility.add_qa_users.add_user_role ('LARC', usr_list(i), 'Call Center Manager - Auto', 710102); --Carter Lake
            ra_utility.add_qa_users.add_user_role ('MA',   usr_list(i), 'MA - Customer Service Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('MD',   usr_list(i), 'Customer Service Manager - Auto', 784); --Frostburg Call Center
            ra_utility.add_qa_users.add_user_role ('MRV',   usr_list(i), 'MRV - Customer Service Manager - Auto', 804); --CA Call Center
            ra_utility.add_qa_users.add_user_role ('MS',   usr_list(i), 'MS - Customer Service Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('MT',   usr_list(i), 'MT - Customer Service Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('NC',   usr_list(i), 'AWO - Customer Service Manager - Auto',  784); --Frostburg Call Center
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Customer Service Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Customer Service Manager - Auto', 71746); --YOUNG LAKE (SOUTH) CABIN
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Customer Service Manager - Auto', 72777); --VOYAGEURS NATIONAL PARK TOURS
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Customer Service Manager - Auto', 72600); --Boundary Waters Canoe Area Wilderness (Reservations)
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Customer Service Manager - Auto', 77817); --MAMMOTH CAVE NATIONAL PARK TOURS
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Customer Service Manager - Auto', 71709); --WILLOW
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Customer Service Manager - Auto', 93768); --FIERY FURNACE TOUR - ARCHES
            ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Customer Service Manager - Auto', 72203); --Inyo National Forest - Wilderness Permits
	    ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Customer Service Manager - Auto', 70023); --RYAN PARK
  ra_utility.add_qa_users.add_user_role ('NRSO', usr_list(i), 'NRRS - Customer Service Manager - Auto', 73494); --ACORN CAMPGROUND
            ra_utility.add_qa_users.add_user_role ('NE',   usr_list(i), 'NE - Customer Service Manager - Auto', 818); --NE Call Center
            ra_utility.add_qa_users.add_user_role ('NH',   usr_list(i), 'NH - Customer Service Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('NM',   usr_list(i), 'Customer Service Manager - Auto', 784); --Frostburg Call Center
            ra_utility.add_qa_users.add_user_role ('NY',   usr_list(i), 'Customer Service Manager - Auto', 765); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('OR',   usr_list(i), 'OR - Call Manager - Auto', 780); --OR Call Center
            ra_utility.add_qa_users.add_user_role ('ORNG', usr_list(i), 'Customer Service Manager - Auto', 804); --CA Call Center
            ra_utility.add_qa_users.add_user_role ('RAGV', usr_list(i), 'Customer Service Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('RI',   usr_list(i), 'RI - Customer Service Manager - Auto', 821); --NY Call Center
            ra_utility.add_qa_users.add_user_role ('SC',   usr_list(i), 'SC - Customer Service Manager - Auto', 820); --WI Call Center
            ra_utility.add_qa_users.add_user_role ('UT',   usr_list(i), 'UT - Call Manager - Auto', 782); --UT Call Center
            ra_utility.add_qa_users.add_user_role ('VA',   usr_list(i), 'VA Call Center Manager - Auto', 826); --VA Call Center
            -- WI - no Customer Service
         ELSE
          DBMS_OUTPUT.PUT_LINE('Unknown user type : ''' || usr_list(i) || '''');

        END IF;

     END LOOP;

    DBMS_OUTPUT.PUT_LINE('End adding roles: ' || TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
    DBMS_OUTPUT.PUT_LINE('Begin update: ' || TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));

    DBMS_OUTPUT.PUT_LINE('Updating user details...');
    
    ra_utility.add_qa_users.update_user_details ('qa-auto%', 'QA-Auto', 'Test-Auto', 'qaormstest@reserveamerica.com');
       
    DBMS_OUTPUT.PUT_LINE('End update: ' || TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'));
         
END; 

