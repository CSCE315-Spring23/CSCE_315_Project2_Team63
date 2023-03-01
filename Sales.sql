-- All queries for sales table

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

-- Prints the number of weeks that the table covers

SELECT COUNT(date)/5 FROM sales;
