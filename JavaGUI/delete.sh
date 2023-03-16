#!/bin/bash

# Connect to the database and execute SQL commands
mysql --silent -u csce315331_team_63_master -pWFHD jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63 <<EOF
delete from orderhistory where date = '2023-03-08';
delete from orderhistory where date = '2023-03-09';
delete from inventory_status where date = '2023-03-08';
delete from inventory_status where date = '2023-03-09';
delete from sales where date = '2023-03-08';
delete from sales where date = '2023-03-09';
EOF