-- Prints every entry from table

SELECT * FROM sales;


-- Gets largest sales amount

SELECT MAX(total_sales)
FROM sales;


-- Gets the total sum of the sales

SELECT SUM(total_sales)
FROM sales;


-- 
SELECT * FROM sales
WHERE game_day='yes';