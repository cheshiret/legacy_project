
--Internet submittals by category--
SELECT * FROM HUNT_SUBMITTAL
WHERE HS_CREATED_BY ='INTERLIC' AND HPC_ID='DE' AND
HS_CREATED_DATE >= to_date('09-NOV-2009');

--IVR submittals by category--
SELECT * FROM HUNT_SUBMITTAL
WHERE HS_CREATED_BY LIKE'IVR %' AND HPC_ID='DE' AND
HS_CREATED_DATE >= to_date('09-NOV-2009');

--Submittals by Customer with 800* WILD IDs--
SELECT * FROM HUNT_SUBMITTAL
WHERE CU_STATE_CUSTOMER_ID LIKE '800%' AND HS_CREATED_DATE >= to_date('09-NOV-2009');

--Submittals by Customer with WILD ID < 11 --
SELECT * FROM HUNT_SUBMITTAL a WHERE LENGTH(a.cu_state_customer_id) < 11 AND HS_CREATED_DATE >= to_date('09-NOV-2009');

--Customer with multiple active submittals for same draw category (Active only).
SELECT * FROM HUNT_SUBMITTAL WHERE cu_state_customer_id in (SELECT a.cu_state_customer_id FROM HUNT_SUBMITTAL a WHERE a.hs_status_ind = 'A' 
GROUP BY a.cu_state_customer_id HAVING count(a.hs_status_ind) > 1 ) AND  hs_status_ind = 'A' 
ORDER BY cu_state_customer_id;

--Hunt application with "Group Member" with no "Group Leader" ID.
SELECT * FROM Hunt_Submittal HS WHERE HS.hst_id='GM' AND HS.hs_group_leader_cu_id IS NULL AND HS.hpc_id='DE';

--Hunt application with "Group Leader" with no "Group Member" ID.
SELECT * FROM Hunt_Submittal HS WHERE HS.cu_id IN( select hs_group_leader_cu_id FROM hunt_submittal a 
GROUP BY hs_group_leader_cu_id HAVING COUNT(hs_group_leader_cu_id)=1) AND HS.HST_ID='GL';

--Special Hunt Application with 'Group Member' with their own WILD ID entered into the 'Group Leader' box.
SELECT * FROM Hunt_Submittal HS WHERE HS.hst_id='GM' AND HS.cu_id=HS.hs_group_leader_cu_id;

--Special Hunt Application with 'Individual' with their own WILD ID entered into the 'Group Leader' box.
SELECT * FROM Hunt_Submittal HS WHERE HS.hst_id='I' AND HS.cu_id=HS.hs_group_leader_cu_id;

--Special Hunt Application with 'Group Members' with other 'Group Members' entered into the 'Group Leader' box.
SELECT * FROM Hunt_Submittal HS WHERE HS.hst_id='GM' AND HS.cu_id!=HS.hs_group_leader_cu_id;

--Negative aged customer.
SELECT C.cu_first_name, 
C.cu_last_name, 
C.cu_state_customer_id, 
C.CU_DOB,
trunc((sysdate-cu_dob)/365.24) AGE
FROM Hunt_Submittal HS
JOIN Customer C on HS.CU_ID=C.CU_ID
WHERE trunc((sysdate-C.cu_dob)/365.24)<1
