SELECT * FROM Hunt_Submittal HS WHERE HS.cu_id IN( select hs_group_leader_cu_id from hunt_submittal a 
group by hs_group_leader_cu_id having count(hs_group_leader_cu_id)=1) AND HS.HST_ID='GL';

hs_group_leader_cu_id <> cu_id

select * from hunt_submittal a where a.hs_group_leader_cu_id = '285983'

SELECT * FROM Hunter_Edu_Type

SELECT DISTINCT C.cu_first_name, 
C.cu_last_name, 
C.cu_state_customer_id, 
C.CU_DOB,
trunc((sysdate-cu_dob)/365.24) AGE
FROM Hunt_Submittal HS
JOIN Customer C on HS.CU_ID=C.CU_ID
JOIN Hunter_Edu on 
WHERE C.cu_dob > to_date('01-JAN-1972')

-- Realtime distribution --
SELECT 
FP.FeePercentID, FC.FeeCategoryName, FP.EffectiveDate, 
FP.FeePercentRate, FP.PercentSplit_WDFW, FP.PercentSplit_AO, 
FP.SavedDate, FP.SavedBy
FROM FeePercent FP
JOIN FeeCategory FC ON FC.FeeCategoryID = FP.FeeCategoryID
ORDER BY FP.FeePercentID;

SELECT * FROM HUNT_CHOICE_STATUS_TYPE

SELECT 
C.cu_state_customer_id,
C.cu_last_name,
C.cu_dob,
HDC.hdc_random_num,
HDC.hdc_points_used
FROM HUNT_DRAW_CUSTOMER HDC
JOIN CUSTOMER C ON HDC.cu_id=C.cu_id
WHERE HDC.hdc_created_date=to_date('30-MAR-2007')

SELECT * FROM HUNT_DRAW
SELECT * FROM HUNT_DRAW_CUSTOMER 
SELECT * FROM HUNT_PERMIT_CATEGORY

SELECT 
C.cu_state_customer_id,
C.cu_last_name,
C.cu_dob,
HDC.hdc_random_num,
HDC.hdc_points_used
FROM HUNT_DRAW_CUSTOMER HDC
JOIN CUSTOMER C ON HDC.cu_id=C.cu_id
JOIN HUNT_SUBMITTAL HS ON HDC.hs_id = HS.hs_id
JOIN HUNT_PERMIT_CATEGORY HPC ON HS.hpc_id=HPC.hpc_id
WHERE HPC.HPC_ID='DE'


