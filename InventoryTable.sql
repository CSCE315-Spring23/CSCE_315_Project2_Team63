-- displays all items in inventory_table
SELECT * FROM inventory_table;

-- shows the total quantity of items from each vendor
SELECT vendor_name, SUM(quantity) AS total_quantity
FROM inventory_table
GROUP BY vendor_name;

-- shows all items purchased for greater than the average price of all items
SELECT *
FROM inventory_table
WHERE price > (SELECT AVG(price) FROM inventory_table);