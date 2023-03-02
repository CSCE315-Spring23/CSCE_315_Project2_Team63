SELECT * FROM orderhistory;

-- Show sum up to 1 million
SELECT SUM(total) AS total_total FROM orderhistory;

-- Find the maximum total in the total column
SELECT MAX(total) FROM orderhistory;

-- Find the minimum total in the total column
SELECT MIN(total) FROM orderhistory;

-- 
SELECT sales.date, sales.total_sales, orderhistory.order_history FROM sales LEFT JOIN orderhistory ON sales.date = orderhistory.date;
