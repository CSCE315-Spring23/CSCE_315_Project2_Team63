-- Prints every entry from table

SELECT * FROM sales;


-- Gets largest sales amount

SELECT MAX(total_sales)
FROM sales;


-- Gets the total sum of the sales

SELECT SUM(total_sales)
FROM sales;


-- Prints game days
SELECT * FROM sales
WHERE total_sales > 80000;