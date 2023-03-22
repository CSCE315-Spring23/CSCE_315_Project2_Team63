import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.DefaultListModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.text.DecimalFormat;

/** Creates a class of order2 that is the primary window for the server side of the POS. The window is comprised primarily of buttons for each menu item, and should create a textfield
 * that keeps track of the total sum of the order currently in receiptArea, date, employeeID, and name of customers. Order2 implements Actionlistener is carries the class creation behind Custom.java 
 * which is a seperate ActionListener window 
 * that organizes custom order creations. 
 */
public class Order2 extends JFrame implements ActionListener {
    public static Connection conn2 = null;
    public static Connection conn = null;
    private static JTextArea receiptArea;
    private static JTextField employeeID, date, name;
    private static JLabel employee_label, date_label, name_label, total_price;
    private static JButton cancel,custom;
    private static String itemList = "";
    private static JPanel buttonPanel = new JPanel(new GridLayout(4, 5));;

    // hashMap is a HashMap that dynamically creates menu items with listed recipes or inventory slots that are used to make them. 
    public static HashMap<String, String> hashMap = new HashMap<String, String>(); //{{
        
    //     //BURRITO:
    //     put("burrito_seasoned-beef", "0.175:black beans,0.127:spring mix,0.1005:mozzarella cheese,0.09375:pico de gallo,0.1875:onions,0.09924:jalapeno peppers,0.08724:cilantro,0.155:sour cream,0.105:ranch,0.115:chipotle sauce,0.115:black olives,0.089:lime,0.109:italian dressing,1.0:large tortilla,1.0:paper bags,1.0:aluminum foil,1.0:gloves,0.098:lime juice,0.155:ground beef");
    //     put("burrito_marinated-steak", "0.175:black beans,0.127:spring mix,0.1005:mozzarella cheese,0.09375:pico de gallo,0.1875:onions,0.09924:jalapeno peppers,0.08724:cilantro,0.155:sour cream,0.105:ranch,0.115:chipotle sauce,0.115:black olives,0.089:lime,0.109:italian dressing,1.0:large tortilla,1.0:paper bags,1.0:aluminum foil,1.0:gloves,0.098:lime juice,0.155:steak");
    //     put("burrito_grilled-vegetable-medley", "0.175:black beans,0.127:spring mix,0.1005:mozzarella cheese,0.09375:pico de gallo,0.1875:onions,0.09924:jalapeno peppers,0.08724:cilantro,0.155:sour cream,0.105:ranch,0.115:chipotle sauce,0.115:black olives,0.089:lime,0.109:italian dressing,1.0:large tortilla,1.0:paper bags,1.0:aluminum foil,1.0:gloves,0.098:lime juice,0.155:fajita vegetables");
    //     put("burrito_chili-rubbed-chicken", "0.175:black beans,0.127:spring mix,0.1005:mozzarella cheese,0.09375:pico de gallo,0.1875:onions,0.09924:jalapeno peppers,0.08724:cilantro,0.155:sour cream,0.105:ranch,0.115:chipotle sauce,0.115:black olives,0.089:lime,0.109:italian dressing,1.0:large tortilla,1.0:paper bags,1.0:aluminum foil,1.0:gloves,0.098:lime juice,0.155:chicken");

    //     //TACO:
    //     put("tacos_seasoned-beef", "0.375:pinto beans,0.327:romaine lettuce,0.1875:mixed cheddar,0.09375:pico de gallo,0.1875:onions,0.09924:jalapeno peppers,0.08724:cilantro,0.355:sour cream,0.305:jalapeno ranch,0.215:red sauce,0.315:black olives,0.119:lime,0.109:italian dressing,3.0:small tortilla,1.0:paper bags,3.0:aluminum foil,1.0:gloves,0.098:lime juice,0.355:ground beef");
    //     put("tacos_marinated-steak", "0.375:pinto beans,0.327:romaine lettuce,0.1875:mixed cheddar,0.09375:pico de gallo,0.1875:onions,0.09924:jalapeno peppers,0.08724:cilantro,0.355:sour cream,0.305:jalapeno ranch,0.215:red sauce,0.315:black olives,0.119:lime,0.109:italian dressing,3.0:small tortilla,1.0:paper bags,3.0:aluminum foil,1.0:gloves,0.098:lime juice,0.355:steak");
    //     put("tacos_grilled-vegetable-medley", "0.375:pinto beans,0.327:romaine lettuce,0.1875:mixed cheddar,0.09375:pico de gallo,0.1875:onions,0.09924:jalapeno peppers,0.08724:cilantro,0.355:sour cream,0.305:jalapeno ranch,0.215:red sauce,0.315:black olives,0.119:lime,0.109:italian dressing,3.0:small tortilla,1.0:paper bags,3.0:aluminum foil,1.0:gloves,0.098:lime juice,0.355:fajita vegetables");
    //     put("tacos_chili-rubbed-chicken", "0.375:pinto beans,0.327:romaine lettuce,0.1875:mixed cheddar,0.09375:pico de gallo,0.1875:onions,0.09924:jalapeno peppers,0.08724:cilantro,0.355:sour cream,0.305:jalapeno ranch,0.215:red sauce,0.315:black olives,0.119:lime,0.109:italian dressing,3.0:small tortilla,1.0:paper bags,3.0:aluminum foil,1.0:gloves,0.098:lime juice,0.355:chicken");
    
    //     //BOWLS:
    //     put("bowl_seasoned-beef", "0.175:brown rice,0.175:white rice,0.127:iceburg lettuce,0.1005:mozzarella cheese,0.1005:mixed cheddar,0.09375:pico de gallo,0.1875:onions,0.09924:jalapeno peppers,0.08724:cilantro,0.155:sour cream,0.105:ranch,0.115:chipotle sauce,0.115:black olives,0.089:lime,0.109:italian dressing,1.0:cardboard bowls + tops,1.0:paper bags,1.0:gloves,1.0:bags of cutlery,0.098:lime juice,0.155:ground beef");
    //     put("bowl_marinated-steak", "0.175:brown rice,0.175:white rice,0.127:iceburg lettuce,0.1005:mozzarella cheese,0.1005:mixed cheddar,0.09375:pico de gallo,0.1875:onions,0.09924:jalapeno peppers,0.08724:cilantro,0.155:sour cream,0.105:ranch,0.115:chipotle sauce,0.115:black olives,0.089:lime,0.109:italian dressing,1.0:cardboard bowls + tops,1.0:paper bags,1.0:gloves,1.0:bags of cutlery,0.098:lime juice,0.155:steak");
    //     put("bowl_grilled-vegetable-medley", "0.175:brown rice,0.175:white rice,0.127:iceburg lettuce,0.1005:mozzarella cheese,0.1005:mixed cheddar,0.09375:pico de gallo,0.1875:onions,0.09924:jalapeno peppers,0.08724:cilantro,0.155:sour cream,0.105:ranch,0.115:chipotle sauce,0.115:black olives,0.089:lime,0.109:italian dressing,1.0:cardboard bowls + tops,1.0:paper bags,1.0:gloves,1.0:bags of cutlery,0.098:lime juice,0.155:fajita vegetables");
    //     put("bowl_chili-rubbed-chicken", "0.175:brown rice,0.175:white rice,0.127:iceburg lettuce,0.1005:mozzarella cheese,0.1005:mixed cheddar,0.09375:pico de gallo,0.1875:onions,0.09924:jalapeno peppers,0.08724:cilantro,0.155:sour cream,0.105:ranch,0.115:chipotle sauce,0.115:black olives,0.089:lime,0.109:italian dressing,1.0:cardboard bowls + tops,1.0:paper bags,1.0:gloves,1.0:bags of cutlery,0.098:lime juice,0.155:chicken");
    
    //     //CHIPS:
    //     put("chips-and-guac","0.09375:nacho chips,0.0625:guacamole,2.0:sauce containers");
    //     put("chips-and-salsa","0.09375:nacho chips,0.0625:corn salsa,0.0625:salse verde,2.0:sauce containers");
    //     put("chips-and-queso","0.09375:nacho chips,0.0625:queso,2.0:sauce containers");

    //     put("fountain-drink","1.0:fountain drink cups");
    //     put("eat-the-rainbow","0.175:brown rice,0.175:white rice,0.09375:pico de gallo,0.1875:onions,0.09924:jalapeno peppers,0.08724:cilantro,0.155:sour cream,0.105:ranch,0.115:chipotle sauce,0.115:black olives,0.089:lime,0.109:italian dressing,1.0:large tortilla,1.0:paper bags");

    // }};
    
    public static Vector<Double> quant = new Vector<Double>();
    public static Vector<String> ingredient = new Vector<String>();
    public static ArrayList<ArrayList<String>> custom_order_list= new ArrayList<>();
    public static ArrayList<String> to_return = new ArrayList<>();

    private static JButton checkoutButton;
    private static double total = 0;
    private static double complete_total = 0;
    private static String prev_day = "N/A";
    private static ArrayList<ArrayList<String> > custom_list = new ArrayList<ArrayList<String> >();

    /**
    This Java class implements a Point of Sale (POS) system using the Java Swing GUI framework.
    The system allows a user to create and cancel orders, and checkout with a customer's name, employee ID, and date.
    The system also keeps a record of all orders and their total price, which is stored in a PostgreSQL database.
    The GUI components include a text area for displaying the receipt, text fields for entering employee ID, customer name and date,
    and buttons for selecting items, canceling orders and checking out.
    The class contains a public Order2 constructor which creates the GUI and adds all the necessary components to it.
    The STATICactionPerformed method is called when any of the buttons are clicked and performs the corresponding actions.
    The PostgreSQL database connection is established using the JDBC API.
    This POS system is designed to be user-friendly and intuitive, allowing for quick and easy transactions in a retail setting.
    */
    public Order2() {
        super("Point of Sale");
        // Create text area for displaying the receipt
        receiptArea = new JTextArea(10, 30);
        employeeID = new JTextField(10);
        name = new JTextField(10);
        date = new JTextField(10);

        employee_label = new JLabel("Employee ID: ");
        date_label = new JLabel("Date(XXXX-XX-XX): ");
        name_label = new JLabel("Customer name: ");
        total_price = new JLabel("Total price: $0.00");
        
        receiptArea.setEditable(false);



        custom = new JButton("Custom - $10");
        custom.addActionListener(e -> Order2.STATICactionPerformed(e));

        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> Order2.STATICactionPerformed(e));

        cancel = new JButton("Cancel Order");
        cancel.addActionListener(e -> Order2.STATICactionPerformed(e));
    
        // Add components to the frame
        buttonPanel.add(custom);
        runMenuAndButton();
    
        JPanel checkoutPanel = new JPanel(new FlowLayout());

        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(receiptArea, BorderLayout.CENTER);
        
        checkoutPanel.add(total_price);
        getContentPane().add(checkoutPanel, BorderLayout.SOUTH);

        checkoutPanel.add(checkoutButton);
        getContentPane().add(checkoutPanel, BorderLayout.SOUTH);

        checkoutPanel.add(employee_label);
        checkoutPanel.add(employeeID);
        getContentPane().add(checkoutPanel, BorderLayout.SOUTH);

        checkoutPanel.add(date_label);
        checkoutPanel.add(date);
        getContentPane().add(checkoutPanel, BorderLayout.SOUTH);

        checkoutPanel.add(name_label);
        checkoutPanel.add(name);
        getContentPane().add(checkoutPanel, BorderLayout.SOUTH);

        checkoutPanel.add(cancel);
        getContentPane().add(checkoutPanel, BorderLayout.SOUTH);
    
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
    This method is called when an action is performed, such as a button being clicked.
    If the source of the event is the checkoutButton, it gets the employee ID, date, and customer name
    and checks if they are empty. If they are not empty, it sets the total price and receipt area to
    default values. Then, it connects to a PostgreSQL database using JDBC driver and inserts the
    employee ID, date, customer name, itemList, and total into a table named "orderhistory".
    If the current date is different from the previous date, it inserts the previous day and complete
    total into a table named "sales". It also updates the inventory by connecting to the same database
    and calling updateInventory(). Then, it sets itemList, total, and receipt area to default values.
    */
    public static void STATICactionPerformed(ActionEvent e) {
        // Our conditional for the buttons and only allows for it to pass if every slot is filled: day, customer, and employee.
        if(e.getSource() == checkoutButton)
        {
            String employee = employeeID.getText();
            String day = date.getText();
            String customer = name.getText();

            if (prev_day.equals("N/A")) {
                prev_day = day;
            }

            if(employee.isEmpty() || day.isEmpty() || customer.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter into all text entries");
            } else {
                total_price.setText("Total price: $0.00");
                receiptArea.setText("");
            }
            
            // connect to data base to excecute inserting into orderhistory table and adding to sales table.
            try {
                Class.forName("org.postgresql.Driver");
                conn2 = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63",
                    "csce315331_team_63_master", "WFHD");

                Statement stmt = conn2.createStatement();
                stmt.executeUpdate("INSERT INTO orderhistory (emp_id, date, customer_name, order_history, total) VALUES ('" + employee + "', '" + day + "', '" + customer + "', '" + itemList + "', '" + total + "');");

                if(!(prev_day.equals(day))){
                    System.out.println("if total: " + total);
                    System.out.println("if: " + complete_total);
                    stmt.executeUpdate("INSERT INTO sales (date, total_sales) VALUES ('" + prev_day + "', '" + complete_total + "')");
                    prev_day = day;
                    complete_total = total;
                }
                else {
                    complete_total += total;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                System.err.println(e.getClass().getName()+": "+e2.getMessage());
                System.exit(0);
            }
        
            itemList = "";
            total = 0;
            total_price.setText("Total price: $0.00");
            receiptArea.setText("");
            prev_day = day;

            try{
                // 1. Load the JDBC driver
                Class.forName("org.postgresql.Driver");

                // 2. Create a connection to the database
                String url = "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63";
                String username = "csce315331_team_63_master";
                String password = "WFHD";
                Connection connection = DriverManager.getConnection(url, username, password);

                for(int i = 0; i < ingredient.size(); i++) {

                    Statement statement = connection.createStatement();
                    updateInventory(statement, quant.get(i), ingredient.get(i));
    
                }
                
                for(int i = 0; i < custom_list.size(); i++) {
                    for(int j = 1; j < custom_list.get(i).size(); j++) {
                        if(custom_list.get(i).get(j).equals("bowl") || custom_list.get(i).get(j).equals("burrito") || custom_list.get(i).get(j).equals("tacos")) {
    
                            j++;
                        }
                        Double custom_stock = 0.0;

                        if(custom_list.get(i).get(j).equals("brown rice")) {custom_stock = 0.175;}
                        if(custom_list.get(i).get(j).equals("white rice")) {custom_stock = 0.175;}
                        if(custom_list.get(i).get(j).equals("mozzarella cheese")) {custom_stock = 0.1005;}
                        if(custom_list.get(i).get(j).equals("mixed cheddar")) {custom_stock = 0.1005;}
                        if(custom_list.get(i).get(j).equals("pico de gallo")) {custom_stock = 0.09375;}
                        if(custom_list.get(i).get(j).equals("onions")) {custom_stock = 0.1875;}
                        if(custom_list.get(i).get(j).equals("jalapeno peppers")) {custom_stock = 0.09924;}
                        if(custom_list.get(i).get(j).equals("cilantro")) {custom_stock = 0.08724;}
                        if(custom_list.get(i).get(j).equals("sour cream")){custom_stock = .155;} 
                        if(custom_list.get(i).get(j).equals("ranch")){custom_stock = .105;} 
                        if(custom_list.get(i).get(j).equals("chipotle sauce")){custom_stock = .115;}
                        if(custom_list.get(i).get(j).equals("black olives")){custom_stock = .115;} 
                        if(custom_list.get(i).get(j).equals("lime")){custom_stock = .089;} 
                        if(custom_list.get(i).get(j).equals("italian dressing")){custom_stock = .109;}
                        if(custom_list.get(i).get(j).equals("lime juice")) {custom_stock = 0.0980;}
                        if(custom_list.get(i).get(j).equals("chicken")) {custom_stock = 0.155;}
                        if(custom_list.get(i).get(j).equals("steak")) {custom_stock = 0.155;}
                        if(custom_list.get(i).get(j).equals("ground beef")) {custom_stock = 0.155;}
                        if(custom_list.get(i).get(j).equals("guacamole")) {custom_stock = 0.105;}
                        if(custom_list.get(i).get(j).equals("queso")) {custom_stock = 0.105;}
                        if(custom_list.get(i).get(j).equals("iceburg lettuce")) {custom_stock = 0.135;}
                        if(custom_list.get(i).get(j).equals("red sauce")) {custom_stock = 0.109;}
                        if(custom_list.get(i).get(j).equals("fajita vegetables")) {custom_stock = 0.122;}
                        if(custom_list.get(i).get(j).equals("corn salsa")) {custom_stock = 0.0989;}
                        if(custom_list.get(i).get(j).equals("salse verde")) {custom_stock = 0.0989;}
                        if(custom_list.get(i).get(j).equals("black beans")) {custom_stock = 0.1002;}
                        if(custom_list.get(i).get(j).equals("spring mix")) {custom_stock = 0.1002;}
                        if(custom_list.get(i).get(j).equals("pinto beans")) {custom_stock = 0.0897;}
                        if(custom_list.get(i).get(j).equals("romaine lettuce")) {custom_stock = 0.1897;}
                        if(custom_list.get(i).get(j).equals("spring mix")) {custom_stock = 0.1897;}

                        Statement statement = connection.createStatement();
                        updateInventory(statement,custom_stock, custom_list.get(i).get(j));
                    }
                }
                custom_list = new ArrayList<ArrayList<String>>(); 
                connection.close();
            }
            catch (ClassNotFoundException e2) {
                System.out.println("JDBC driver not found");
            } catch (SQLException e2) {
                System.out.println("Database connection error: " + e2.getMessage());
            }

            quant = new Vector<Double>();
            ingredient = new Vector<String>();
            
            //run current state of inventory:
            addCurrentstateOfInventory(date.getText());
        }
        else if(e.getSource() == cancel)
        {
            total = 0;
            total_price.setText("Total price: $0.00");
            receiptArea.setText("");
            employeeID.setText("");
            date.setText("");
            name.setText("");
            itemList = "";
            quant = new Vector<Double>();
            ingredient = new Vector<String>();
            custom_list = new ArrayList<ArrayList<String>>();
        }
        else if(e.getSource() == custom)
        {
            String item = "Custom";
            double price = 10.00; // up to you
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);

            new Custom(u -> Order2.onUpdate(u));
        }
        else
        {
            JButton clickedButton = (JButton) e.getSource(); 
            String buttonText = clickedButton.getText(); 
            String itemName = buttonText.substring(0,buttonText.indexOf(" -")).trim();
            double price = Double.parseDouble(buttonText.substring(buttonText.indexOf("$")+1).trim());
            System.out.println(itemName);
            System.out.println(price);
            System.out.println("HASHMAP: ");

            if(hashMap.containsKey(itemName))
            {
                String recipe = hashMap.get(itemName);
                String[] arr = recipe.split(",");
                for (String item : arr) {

                    double num = Double.parseDouble(item.substring(0,item.indexOf(":")).trim());
                    String invent = item.substring(item.indexOf(":")+1).trim();
                    quant.add(num);
                    ingredient.add(invent);
                }
                total += price;
                itemList += itemName + ", ";
                String receiptLine = String.format("%s\t$%.2f\n", itemName, price);
                String total_p = String.format("%.2f", total);
                total_price.setText("Total price: $" + total_p);
                receiptArea.append(receiptLine);

            }
            else
            {
                System.out.print("item doesn't exist");
            }
        }
    }

    //method that goes through the hashmap and menu, any element in menu that is not in hashmap, it creates a new button for that

    public static void onUpdate(ArrayList<String> updatedList) {
        System.out.println("adding, " + updatedList);
        custom_list.add(updatedList);
        System.out.println("all custom orders: " + custom_list);
    }

    public static void updateInventory(Statement stat, Double stock, String item) {
        try {

            String query2 = "SELECT quantity FROM inventory_table WHERE item='"+item+"';";
            
            ResultSet result = stat.executeQuery(query2);
            

            String current = null;
            if (result.next()) {
                current = result.getString("quantity");
            }

            Double current2 = Double.parseDouble(current);

            stock = current2-stock;

            if(stock < 0) {

                stock = 0.0;
            }

            String query = "UPDATE inventory_table SET quantity="+stock+" WHERE item='"+item+"';";
            int rowsAffected = stat.executeUpdate(query);
            System.out.println("Rows affected: " + rowsAffected);

            // 5. Close the resources
            stat.close();
        } 
        catch (SQLException e2) {
            System.out.println("Database connection error: " + e2.getMessage());
        }
    }

    public static void runMenuAndButton() 
    {

      try {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63",
            "csce315331_team_63_master", "WFHD");
      } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
      }
      //JOptionPane.showMessageDialog(null,"Opened database successfully");

      try
      {
        ArrayList<String> menuItemNum = new ArrayList<>();
        ArrayList<String> menuName = new ArrayList<>();
        ArrayList<String> menuCost = new ArrayList<>();
        ArrayList<String> ingridentList = new ArrayList<>();

        //create a statement object
        Statement stmt = conn.createStatement();
        //String sqlStatement = ;
        //send statement to DBMS
        ResultSet result = stmt.executeQuery("SELECT * FROM menu2 ORDER BY itemnum;"); 
        while (result.next()) 
        {
            menuItemNum.add(result.getString("itemnum"));
            menuName.add(result.getString("food"));
            menuCost.add(result.getString("price"));
            ingridentList.add(result.getString("ingridents"));
        }
        for(int i = 0; i < menuItemNum.size(); i++) 
        {
            if(!menuName.get(i).toLowerCase().contains("custom") && !(hashMap.containsKey(menuName.get(i))))
            {
                String buttonLabel = menuName.get(i) + " - $" + menuCost.get(i);
                String buttonName = menuName.get(i);
                JButton button = new JButton(buttonLabel);
                button.setName(buttonName);
                hashMap.put(menuName.get(i),ingridentList.get(i));
                button.addActionListener(e -> Order2.STATICactionPerformed(e));
                buttonPanel.add(button);
            }
        }

      } 
      catch (Exception e)
      {
        JOptionPane.showMessageDialog(null,"Error accessing Database.");
      }
    }

    public static void DeleteAndRepopulate()
    {
        //empty out the button panel, empty out the hashmap, call runMenuAndButton();
        buttonPanel.removeAll();
        hashMap.clear(); 
        runMenuAndButton();
    }

    public static void addCurrentstateOfInventory(String date)
    {
        try {

            String quantity = "";
            String item = "";

            Class.forName("org.postgresql.Driver");
            conn2 = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63",
                "csce315331_team_63_master", "WFHD");
            
            // create a statement object
            Statement stmt = conn2.createStatement();
            String sql = "SELECT * FROM inventory_table ORDER BY item_id;";                    // execute the statement

            ResultSet result = stmt.executeQuery(sql);
            
            while (result.next()) {
                quantity += (result.getString("quantity")) + ":";
                item += (result.getString("item")) + ":";
            }

            PreparedStatement stmt2 = conn2.prepareStatement("SELECT * FROM inventory_status WHERE date = ?");
            stmt2.setString(1, date);
            ResultSet result2 = stmt2.executeQuery();
    
            if (result2.next()) {
                // Entry already exists, update quantity and item columns
                stmt2 = conn2.prepareStatement("UPDATE inventory_status SET quantity = ?, item = ? WHERE date = ?");
                stmt2.setString(1, quantity);
                stmt2.setString(2, item);
                stmt2.setString(3, date);
                stmt2.executeUpdate();
                System.out.println("Inventory status updated");
            } else {
                // Entry does not exist, insert a new row
                stmt2 = conn2.prepareStatement("INSERT INTO inventory_status (date, quantity, item) VALUES (?, ?, ?)");
                stmt2.setString(1, date);
                stmt2.setString(2, quantity);
                stmt2.setString(3, item);
                stmt2.executeUpdate();
                System.out.println("Inventory status added");
            }
            
            try {
              conn2.close();
              JOptionPane.showMessageDialog(null,"Connection Closed.");
            } catch(Exception e2) {
              JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
            }
          } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error updating item: " + ex.getMessage());
        }
    }
    
    // public static void addButton(String n, String p, String entry)
    // {
    //     System.out.println("IN ORDER2 METHOD");
    //     n = n.trim();
    //     p = p.trim();
    //     entry = entry.trim();

    //     System.out.println(n);
    //     System.out.println(p);
    //     System.out.println(entry);

    //     if(!(hashMap.containsKey(n)))
    //     {   
    //         System.out.println("new item added");
    //         hashMap.put(n,entry);

    //         Set<String> keys = hashMap.keySet();

    //         // Iterate over the keys and print each one
    //         for (String key : keys) {
    //             System.out.println(key);
    //         }

    //         System.out.println("After button in HashMap");
    //         String buttonLabel = n + " - $" + p;
    //         String buttonName = n;
    //         JButton button = new JButton(buttonLabel);
    //         button.setName(buttonName);
    //         button.addActionListener(e -> Order2.STATICactionPerformed(e));
    //         System.out.println("Before putting the button");
    //         buttonPanel.add(button);
    //         System.out.println("After putting the button");
    //     }
    //     else
    //     {
    //         System.out.println("Item already exist");
    //     }
    // }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


 
}