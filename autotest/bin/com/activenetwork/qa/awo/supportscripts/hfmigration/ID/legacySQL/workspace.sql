SELECT CR.it_docid, C.cu_first_name, C.cu_last_name, C.cu_state_customer_id, C.CU_DOB FROM CUSTOMER_REPORTING CR
JOIN Customer C on C.CU_ID=CR.CU_ID
WHERE CR.catch_date > TO_DATE('01-JAN-2009') AND C.CU_State_Customer_ID='22090002546'; 

SELECT C.cu_first_name, C.cu_last_name, C.cu_state_customer_id, C.CU_DOB FROM Customer C
WHERE C.cu_id='1589780'

SELECT * FROM CUSTOMER_REPORTING CR WHERE CR.season_id='qXs9ckDQ1MsT65a'

SELECT * FROM reporting_season RS WHERE RS.season_id='qXs9ckDQ1MsT65a'

select rs.* from customer_reporting cr, reporting_season rs where cr.cu_id = (select c.cu_id from customer c where c.cu_state_customer_id='22090002546')
and cr.season_id = rs.season_id;

SELECT * FROM Hunter_Edu_Type

select 
cu_state_customer_id,
cu_last_name,
cu_dob,
trunc((sysdate-cu_dob)/365.24) age 
from CUSTOMER where   trunc((sysdate-cu_dob)/365.24)>13 and   trunc((sysdate-cu_dob)/365.24)<=15;

SELECT * FROM Hunt_Submittal HS WHERE HS.hs_confirmation_no='1518183'
SELECT * FROM HUNT_AREA
SELECT * FROM HUNT_UNIT
SELECT * FROM HUNT_CHOICE
SELECT * FROM HUNT_GROUP_LIMIT
SELECT * FROM HUNT_RULE HR WHERE HR.ls_id='7'
WHERE HR.HR_YOUTH_FLAG !='N'

SELECT * FROM LICENSE_SEASON LS WHERE LS.ls_id='7'

SELECT * FROM Hunt_Submittal_Type


SELECT C.cu_first_name, 
C.cu_last_name, 
C.cu_state_customer_id, 
C.CU_DOB,
trunc((sysdate-cu_dob)/365.24) AGE,
--LS.LS_YEAR YEAR,
HS.hpc_id Species,
--H.HU_NAME HUNTNAME,
HS.hs_confirmation_no Confirmation
FROM Hunt_Submittal HS
JOIN Customer C on HS.CU_ID=C.CU_ID
JOIN Hunt_Rule HR ON HR.LS_ID=HS.ls_id
--JOIN License_Season LS ON HS.ls_id=LS.LS_ID
--JOIN HUNT H ON LS.LS_ID=H.LS_ID
WHERE trunc((sysdate-C.cu_dob)/365.24)>13 AND trunc((sysdate-C.cu_dob)/365.24)<=15 AND HR.HR_SENIOR_FLAG ='Y'


SELECT * FROM Hunt_Submittal HS WHERE HS.hst_id='GL' and (cu_state_customer_id=25490031366 or cu_state_customer_id= 22300004834)
SELECT * FROM Hunt_Submittal HS WHERE (cu_state_customer_id=25490031366 or cu_state_customer_id= 22300004834)


SELECT * FROM Hunt_Submittal HS WHERE HS.hst_id='GL' and ch_id=4

AND HS.hs_group_leader_cu_id IS NULL AND HS.sp_id='DE';

SELECT * FROM Customer
WHERE CU_ID='257838'

SELECT cu_state_customer_id, cu_id FROM Hunt_Submittal where hs_group_id in (select hs_group_id from Hunt_Submittal HS WHERE HS.hst_id='GL'   
GROUP BY HS.hs_group_id
HAVING COUNT(HS.hs_group_id)>1) and hst_id='GL';

SELECT * FROM CUSTOMER_POINTS
WHERE CU_ID='257838'

SELECT * FROM HUNT_BONUS_POINTS
WHERE CU_ID='257838'

SELECT * FROM WINNER2
WHERE cu_id='32117'
/*_STAGE WS*/

SELECT * FROM WINNER_STAGE WS
WHERE Ws.cu_state_customer_id='22600022973'
cu_id

SELECT h_id, 
hri_id, 
st, 
hpc_id, 
sp_id, 
ic_id, 
itt_id, 
h_hunt, 
hri_status_ind, 
hri_status_effective_date, 
hri_created_by, 
hri_created_date, 
ls_id FROM HUNT_REQUIRED_ITEM
WHERE h_hunt='12';

SELECT * FROM Hunts
WHERE h_hunt='9901'

SELECT * FROM HUNT_REQUIRED_ITEM
WHERE H_ID='8HR79QSU45TRZWM'


SELECT * FROM HUNT_SUBMITTAL

SELECT * FROM ITEM/*_CATALOG*/
WHERE it_id=

SELECT * FROM TRANSACTION
WHERE tr_id='50024003098674'
