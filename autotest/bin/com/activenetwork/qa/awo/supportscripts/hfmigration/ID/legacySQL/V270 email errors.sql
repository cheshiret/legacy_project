SELECT C.cu_state_customer_id, C.cu_first_name, C.cu_middle_name, C.cu_last_name, c.cu_dob  FROM Customer C
WHERE C.cu_home_email_addr in ('124@comcast.net',
'125@comcast.net',
'120@comcast.net',
'127@comcast.net',
'7125@comcast.net',
'1123@comcast.net',
'5124@comcast.net',
'5125@comcast.net',
'4123@comcast.net');

