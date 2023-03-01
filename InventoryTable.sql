-- displays all items in inventory_table
SELECT * FROM inventory_table;

-- shows the total quantity of items from each vendor
SELECT vendor_name, SUM(quantity) AS total_quantity
FROM inventory_table
GROUP BY vendor_name;