SELECT hs.cu_id, hs.hpc_id,
             (SELECT nvl(SUM(hs2.hs_points), 0)
                FROM hunt_submittal hs2
               WHERE hs2.hpc_id = hs.hpc_id AND hs2.cu_id = hs.cu_id
                 AND hs2.hs_status_ind <> 'I' AND hs2.hs_created_date >
                     (SELECT nvl(MAX(hs3.hs_created_date), DATE '1900-01-01')
                        FROM washington.hunt_submittal hs3
                       WHERE hs3.hpc_id = hs.hpc_id
                         AND hs3.cu_id = hs.cu_id
                         AND hs3.hs_status_ind = 'S')) AS points
       FROM washington.hunt_submittal hs

SELECT * FROM washington.hunt_submittal hs
