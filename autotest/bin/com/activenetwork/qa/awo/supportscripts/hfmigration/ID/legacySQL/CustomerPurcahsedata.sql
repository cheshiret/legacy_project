SELECT 
count(t.cu_id), c.cu_state_customer_id
FROM TRANSACTION t
join customer c on t.cu_id=c.cu_id
group by c.cu_state_customer_id
order by  count(t.cu_id) desc
/*
208 865560462
205 569091378
193 105679639
188 895521688
186 136586963
184 503596050
181 453339255
180 579385241
180 350767396
176 658805100
176 112906057
176 198911735
175 848813203
175 187611055
174 717338499
174 335667065
*/


select 
count(tr_id) , ag_agent_name 
from TRANSACTION t
group by ag_agent_name 
order by count(tr_id)desc


