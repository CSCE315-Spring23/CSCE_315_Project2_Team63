-- Selects all entries from table
SELECT * FROM employeedata; 


-- Prints all the avaliable servers and cashier
SELECT emp_id, emp_name
FROM employeedata
WHERE position IN ('Server', 'Cashier') AND clock_in_out_status = 'In';