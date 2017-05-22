Create table test_execution_details
(
case_id int,
execution_id varchar(31),
start_time varchar(15),
end_time varchar(15),
test_runner varchar(15),
duration int,
result tinyint(3),
tool tinyint(3),
status tinyint(3),
build varchar(31),
exception varchar(255),
session_id varchar(128)
)