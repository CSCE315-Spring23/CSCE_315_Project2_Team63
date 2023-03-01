-- Selecting all entries in the menu table
SELECT * FROM menu;

-- Selecting all entries less than $9 
SELECT food from menu where price<9;

-- Selecting all entries in range from 2 to 12
SELECT food from menu where itemNum>1 AND itemNum<13;

-- Finding the price of a particular meals
SELECT PRICE from menu where food = 'chips-and-guac';