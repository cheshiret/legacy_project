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

-- USE "SELECT * FROM HUNT_PERMIT_CATEGORY" FOR HPC_ID.
