SELECT * FROM orderhistory;

-- Show sum up to 1 million
SELECT SUM(total) AS total_total FROM orderhistory;

-- Find the maximum total in the total column
SELECT MAX(total) FROM orderhistory;

