--agent state count business_state_code
select 
count(a.ag_id), a.ag_business_state_code
from agent a group by a.ag_business_state_code;

--agent count based on status 
/*
I:Inactive
T: Terminated
A: Active
S:Suspended
*/
select 
count(a.ag_id), a.ag_status_ind
from agent a group by a.ag_status_ind
 
--agents having different business and billing state
select 
ag_id, 
ag_name,
a.ag_business_street,
a.ag_business_state_code,
a.ag_billing_street,
a.ag_billing_state_code
from agent a where 
a.ag_business_state_code != a.ag_billing_state_code


--agents having different business and billing street address
select 
ag_id, 
ag_name,
a.ag_business_street,
a.ag_business_state_code,
a.ag_billing_street,
a.ag_billing_state_code
from agent a where 
a.ag_business_street!=a.ag_billing_street






