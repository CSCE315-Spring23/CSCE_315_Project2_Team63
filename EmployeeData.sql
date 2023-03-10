-- Selects all entries from table
SELECT * FROM employeedata; 


-- Prints all the avaliable servers and cashier
SELECT emp_id, emp_name
FROM employeedata
WHERE position IN ('Server', 'Cashier') AND clock_in_out_status = 'In';

-- Prints employees whose payrate is more than $12  
SELECT emp_id, emp_name, position, clock_in_out_status
FROM employeedata
WHERE pay_rate > 12;

-- 
SELECT employeedata.emp_name, employeedata.clock_in_out_status FROM employeedata JOIN orderhistory ON employeedata.emp_id = orderhistory.emp_id;