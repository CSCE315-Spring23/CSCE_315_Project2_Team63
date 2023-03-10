//package CSCE_315_Project2_Team63.JavaGUI.Manager;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

// public class ManagerFile extends JFrame{

public class ManagerFile implements ActionListener{

    static JFrame intialMangOption;
    static JFrame f;
    static JFrame frame2;
    static JFrame frame3;
    static JFrame frame4;

    public static ManagerFile s = new ManagerFile();
    
    public static Connection conn = null;
    public static Connection conn2 = null;
    public static String menuItem = "Item Id: " + '\n';
    public static String menuItemString = "Menu Item: " + '\n';
    public static String menuPrice = "Price: " + '\n';

    public static String inventoryItemNum = "Item Id: " + '\n';
    public static String inventoryItem = "Item: " + '\n';
    public static String inventoryQuantity = "Quantity: " + '\n';
    public static String inventoryPrice = "Price: " + '\n';
    public static String inventoryVendorName = "Vendor Name: " + '\n';
    public static String inventoryUnit = "Unit: " + '\n';
    public static String inventoryExp_date = "Expiration Date: " +'\n';

    public static String hashmapEntry = "";

    public ManagerFile()
    {
      System.out.print("Manager Process");
    }

    public static void ManagerDashboard()
    {
        intialMangOption = new JFrame("Manager Options");
        JPanel managerPanel = new JPanel();
    
        JLabel managerTitle = new JLabel("MANAGER DASHBOARD");
        managerTitle.setHorizontalAlignment(JLabel.CENTER); // Center align the label
        managerTitle.setFont(new Font("Arial", Font.BOLD, 24));
        JButton menuSettings = new JButton("MENU SETTINGS");
        JButton inventorySettings = new JButton("INVENTORY SETTINGS");
        JButton salesReport = new JButton("SALES REPORT");
        JButton restockReport = new JButton("RESTOCK REPORT");

        // Add ActionListener to menuSettings button
        menuSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuChange();
                //intialMangOption.setVisible(false);
            }
        });

        inventorySettings.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              InventoryChange();
              //intialMangOption.setVisible(false);
          }
      });
    
        salesReport.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              salesReport();
          }
      });

        restockReport.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              restockReport();
          }
      });
        // Add components to managerPanel using GridLayout
        managerPanel.setLayout(new GridLayout(2, 1, 10, 10));
        managerPanel.add(managerTitle);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.add(menuSettings);
        buttonPanel.add(inventorySettings);
        buttonPanel.add(salesReport);
        buttonPanel.add(restockReport);
        managerPanel.add(buttonPanel);
    
        // Add managerPanel to frame
        intialMangOption.getContentPane().add(managerPanel);
    
        // Set frame properties
        intialMangOption.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        intialMangOption.setSize(400, 300);
        intialMangOption.setVisible(true);
    }

    public static void InventoryChange()
    {
      inventoryItemNum = "Item Id: " + '\n';
      inventoryItem = "Item: " + '\n';
      inventoryQuantity = "Quantity: " + '\n';
      inventoryPrice = "Price: " + '\n';
      inventoryVendorName = "Vendor Name: " + '\n';
      inventoryUnit = "Unit: " + '\n';
      inventoryExp_date = "Expiration Date: " +'\n';
      runInventoryTable();
      f = new JFrame("Inventory");

      JPanel p = new JPanel();
  
      JLabel heading = new JLabel("INVENTORY:  ");
      heading.setHorizontalAlignment(JLabel.CENTER); // Center align the label
      heading.setFont(new Font("Arial", Font.BOLD, 18));

      JTextArea t = new JTextArea(inventoryItemNum);
      t.setEditable(false);
      JTextArea t2 = new JTextArea(inventoryItem);
      t2.setEditable(false);
      JTextArea t3 = new JTextArea(inventoryQuantity);
      t3.setEditable(false);
      JTextArea t4 = new JTextArea(inventoryPrice);
      t4.setEditable(false);
      JTextArea t5 = new JTextArea(inventoryVendorName);
      t5.setEditable(false);
      JTextArea t6 = new JTextArea(inventoryUnit);
      t6.setEditable(false);
      JTextArea t7 = new JTextArea(inventoryExp_date);
      t7.setEditable(false);

      JButton b2 = new JButton("Restock");
      JButton b3 = new JButton("Add");
      JButton b4 = new JButton("Delete");
      JButton b = new JButton("Close");


      //Restock:
      b2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frame2 = new JFrame("restock item");
            // create a new panel for editing menu items
            JPanel p2 = new JPanel();
            p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
    
            // create labels and text fields for input
            JLabel itemNumLabel = new JLabel("Item Number:");
            JTextField itemNumField = new JTextField(10);
            itemNumField.setMaximumSize(itemNumField.getPreferredSize());
            

            JLabel Quantity = new JLabel("Order amount:");
            JTextField QuantityInput = new JTextField(10);
            QuantityInput.setMaximumSize(QuantityInput.getPreferredSize());

            JLabel Vendor = new JLabel("Vendor Name:");
            JTextField VendorInput = new JTextField(10);
            VendorInput.setMaximumSize(VendorInput.getPreferredSize());

            JLabel Price = new JLabel("Price:");
            JTextField PriceInput = new JTextField(10);
            PriceInput.setMaximumSize(PriceInput.getPreferredSize());

            JLabel Exp_data = new JLabel("Expiration Date:");
            JTextField Exp_Input = new JTextField(10);
            Exp_Input.setMaximumSize(Exp_Input.getPreferredSize());

            JButton confirmButton = new JButton("Confirm Changes");
            confirmButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                  String s = e.getActionCommand();
                  if (s.equals("Restock")) {
                    frame2.setVisible(true);
                  } else if (s.equals("Confirm Changes")) {
                    // get the values entered by the user
                    String num = itemNumField.getText();
                    String Inv_quantity = QuantityInput.getText();
                    String Inv_vendor = VendorInput.getText();
                    String Inv_price = PriceInput.getText();
                    String Inv_exp = Exp_Input.getText();
                  
                    try {
                      Class.forName("org.postgresql.Driver");
                      conn2 = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63",
                          "csce315331_team_63_master", "WFHD");
                      
                      // create a statement object
                      Statement stmt = conn2.createStatement();
                      // write the SQL update statement
                      String sql = "UPDATE inventory_table SET quantity = '" + Inv_quantity + "', vendor_name = '" + Inv_vendor + "', price = '" + Inv_price + "', expiration_date = '" + Inv_exp + "' WHERE item_id = " + num + ";";
                      // execute the statement
                      int rowsUpdated = stmt.executeUpdate(sql);
                      if (rowsUpdated > 0) {
                        // if the update was successful, close the edit item frame
                        frame2.setVisible(false);
                        
                        // get the updated data and sort it by item number
                        sql = "SELECT * FROM inventory_table";
                        ResultSet rs = stmt.executeQuery(sql);
                        inventoryItemNum = "Item Num: " + '\n';
                        inventoryItem = "Item: " + '\n';
                        inventoryQuantity = "Quantity: " + '\n';
                        inventoryPrice = "Price: " + '\n';
                        inventoryVendorName = "Vendor Name: " + '\n';
                        inventoryUnit = "Unit: " + '\n';
                        inventoryExp_date = "Expiration Date: " +'\n';
                        while (rs.next()) {
                          inventoryItemNum +=  rs.getString("item_id")+"\n";
                          inventoryItem += rs.getString("item")+"\n";
                          inventoryQuantity += rs.getString("quantity")+"\n";
                          inventoryPrice +=  rs.getString("price")+"\n";
                          inventoryVendorName += rs.getString("vendor_name")+"\n";
                          inventoryUnit += rs.getString("units")+"\n";
                          inventoryExp_date += rs.getString("expiration_date")+"\n";
                        }
                        // update the GUI JTextAreas to reflect the changes
                        t.setText(inventoryItemNum);
                        t2.setText(inventoryItem);
                        t3.setText(inventoryQuantity);
                        t4.setText(inventoryPrice);
                        t5.setText(inventoryVendorName);
                        t6.setText(inventoryUnit);
                        t7.setText(inventoryExp_date);
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
                  
                }
            });


            
            // add labels and text fields to the panel
            p2.add(itemNumLabel);
            p2.add(itemNumField);
            p2.add(Quantity);
            p2.add(QuantityInput);
            p2.add(Price);
            p2.add(PriceInput);
            p2.add(Vendor);
            p2.add(VendorInput);
            p2.add(Exp_data);
            p2.add(Exp_Input);
            p2.add(confirmButton);
    
            // add the new panel to the frame and display it
            frame2.add(p2);
            frame2.setSize(300, 500);
            frame2.show();
            
        }
      });
      

      //ADD:
      b3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frame3 = new JFrame("add item");
            // create a new panel for editing menu items
            JPanel p3 = new JPanel();
            p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
    
            // create labels and text fields for input
            JLabel ItemName = new JLabel("Item Name:");
            JTextField ItemInput = new JTextField(20);
            ItemInput.setMaximumSize(ItemInput.getPreferredSize());            

            JLabel Quantity = new JLabel("Order amount:");
            JTextField QuantityInput = new JTextField(10);
            QuantityInput.setMaximumSize(QuantityInput.getPreferredSize());

            JLabel Vendor = new JLabel("Vendor Name:");
            JTextField VendorInput = new JTextField(10);
            VendorInput.setMaximumSize(VendorInput.getPreferredSize());

            JLabel Price = new JLabel("Price:");
            JTextField PriceInput = new JTextField(10);
            PriceInput.setMaximumSize(PriceInput.getPreferredSize());

            JLabel Units = new JLabel("Units:");
            JTextField UnitInput = new JTextField(10);
            UnitInput.setMaximumSize(UnitInput.getPreferredSize());

            JLabel Exp_data = new JLabel("Expiration Date:");
            JTextField Exp_Input = new JTextField(10);
            Exp_Input.setMaximumSize(Exp_Input.getPreferredSize());

            JButton confirmButton = new JButton("Confirm Changes");
            confirmButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                  String s = e.getActionCommand();
                  if (s.equals("Add")) {
                    frame3.setVisible(true);
                  } else if (s.equals("Confirm Changes")) {
                    // get the values entered by the user
                    
                    String Inv_quantity = QuantityInput.getText();
                    String Inv_vendor = VendorInput.getText();
                    String Inv_price = PriceInput.getText();
                    String Inv_exp = Exp_Input.getText();
                    String Inv_name = ItemInput.getText();
                    String Inv_units = UnitInput.getText();
                  
                    try {
                      Class.forName("org.postgresql.Driver");
                      conn2 = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63",
                          "csce315331_team_63_master", "WFHD");
                      
                      // create a statement object
                      Statement stmt = conn2.createStatement();

                      Random rand = new Random();
                      int num = rand.nextInt(900000) + 100000;

                      // write the SQL update statement
                      String sql = "INSERT INTO inventory_table (item_id, item, quantity, price, vendor_name, units, expiration_date) " + "VALUES (" + num + ", '" + Inv_name + "', '" + Inv_quantity + "', '" + Inv_price + "', '" + Inv_vendor + "', '" + Inv_units + "', '" + Inv_exp + "')";                      // execute the statement
                      int rowsUpdated = stmt.executeUpdate(sql);
                      if (rowsUpdated > 0) {
                        // if the update was successful, close the edit item frame
                        frame3.setVisible(false);
                        
                        // get the updated data and sort it by item number
                        sql = "SELECT * FROM inventory_table";
                        ResultSet rs = stmt.executeQuery(sql);
                        inventoryItemNum = "Item Num: " + '\n';
                        inventoryItem = "Item: " + '\n';
                        inventoryQuantity = "Quantity: " + '\n';
                        inventoryPrice = "Price: " + '\n';
                        inventoryVendorName = "Vendor Name: " + '\n';
                        inventoryUnit = "Unit: " + '\n';
                        inventoryExp_date = "Expiration Date: " +'\n';
                        while (rs.next()) {
                          inventoryItemNum +=  rs.getString("item_id")+"\n";
                          inventoryItem += rs.getString("item")+"\n";
                          inventoryQuantity += rs.getString("quantity")+"\n";
                          inventoryPrice +=  rs.getString("price")+"\n";
                          inventoryVendorName += rs.getString("vendor_name")+"\n";
                          inventoryUnit += rs.getString("units")+"\n";
                          inventoryExp_date += rs.getString("expiration_date")+"\n";
                        }
                        // update the GUI JTextAreas to reflect the changes
                        t.setText(inventoryItemNum);
                        t2.setText(inventoryItem);
                        t3.setText(inventoryQuantity);
                        t4.setText(inventoryPrice);
                        t5.setText(inventoryVendorName);
                        t6.setText(inventoryUnit);
                        t7.setText(inventoryExp_date);
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
                  
                }
            });


            
            // add labels and text fields to the panel
            p3.add(ItemName);
            p3.add(ItemInput);
            p3.add(Quantity);
            p3.add(QuantityInput);
            p3.add(Price);
            p3.add(PriceInput);
            p3.add(Vendor);
            p3.add(VendorInput);
            p3.add(Units);
            p3.add(UnitInput);
            p3.add(Exp_data);
            p3.add(Exp_Input);
            p3.add(confirmButton);
            // add the new panel to the frame and display it
            frame3.add(p3);
            frame3.setSize(300, 500);
            frame3.show();
            
        }
      });

      //DELETE:
      b4.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frame4 = new JFrame("delete item");
            // create a new panel for editing menu items
            JPanel p4 = new JPanel();
            p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));
    
            // create labels and text fields for input
            JLabel ItemNum = new JLabel("Item Num:");
            JTextField ItemNumInput = new JTextField(20);
            ItemNumInput.setMaximumSize(ItemNumInput.getPreferredSize());            

            JButton confirmButton = new JButton("Confirm Changes");
            confirmButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                  String s = e.getActionCommand();
                  if (s.equals("Delete")) {
                    frame4.setVisible(true);
                  } else if (s.equals("Confirm Changes")) {
                    // get the values entered by the user
                    
                    String Inv_ItemNum = ItemNumInput.getText();
                  
                    try {
                      Class.forName("org.postgresql.Driver");
                      conn2 = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63",
                          "csce315331_team_63_master", "WFHD");
                      
                      // create a statement object
                      Statement stmt = conn2.createStatement();

                      // write the SQL delete statement
                      String sql = "DELETE FROM inventory_table WHERE item_id = " + Inv_ItemNum + ";";                    // execute the statement
                      int rowsUpdated = stmt.executeUpdate(sql);
                      if (rowsUpdated > 0) {
                        // if the update was successful, close the edit item frame
                        frame4.setVisible(false);
                        
                        // get the updated data and sort it by item number
                        sql = "SELECT * FROM inventory_table";
                        ResultSet rs = stmt.executeQuery(sql);
                        inventoryItemNum = "Item Num: " + '\n';
                        inventoryItem = "Item: " + '\n';
                        inventoryQuantity = "Quantity: " + '\n';
                        inventoryPrice = "Price: " + '\n';
                        inventoryVendorName = "Vendor Name: " + '\n';
                        inventoryUnit = "Unit: " + '\n';
                        inventoryExp_date = "Expiration Date: " +'\n';
                        while (rs.next()) {
                          inventoryItemNum +=  rs.getString("item_id")+"\n";
                          inventoryItem += rs.getString("item")+"\n";
                          inventoryQuantity += rs.getString("quantity")+"\n";
                          inventoryPrice +=  rs.getString("price")+"\n";
                          inventoryVendorName += rs.getString("vendor_name")+"\n";
                          inventoryUnit += rs.getString("units")+"\n";
                          inventoryExp_date += rs.getString("expiration_date")+"\n";
                        }
                        // update the GUI JTextAreas to reflect the changes
                        t.setText(inventoryItemNum);
                        t2.setText(inventoryItem);
                        t3.setText(inventoryQuantity);
                        t4.setText(inventoryPrice);
                        t5.setText(inventoryVendorName);
                        t6.setText(inventoryUnit);
                        t7.setText(inventoryExp_date);
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
                  
                }
            });


            
            // add labels and text fields to the panel
            p4.add(ItemNum);
            p4.add(ItemNumInput);
            p4.add(confirmButton);
    
            // add the new panel to the frame and display it
            frame4.add(p4);
            frame4.setSize(300, 500);
            frame4.show();
            
        }
      });


      // add actionlistener to button
       b.addActionListener((ActionListener) s);
       p.add(heading);
       p.add(t);
       p.add(t2);
       p.add(t3);
       p.add(t4);
       p.add(t5);
       p.add(t6);
       p.add(t7);
       p.add(b);
       p.add(b2);
       p.add(b3);
       p.add(b4);


       f.add(p);
  
       f.setSize(1000, 800);
 
       f.show();
 
       //closing the connection
       try {
         conn.close();
         JOptionPane.showMessageDialog(null,"Connection Closed.");
       } catch(Exception e) {
         JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
       }

    }

    public static void MenuChange()
    {

        menuItem = "Item Id: " + '\n';
        menuItemString = "Menu Item: " + '\n';
        menuPrice = "Price: " + '\n';
        runMenuTable();

        // create a new frame
        f = new JFrame("Menu");
        
        // create a panel
        JPanel p = new JPanel();
  
        JLabel heading = new JLabel("MENU:  ");
        heading.setHorizontalAlignment(JLabel.CENTER); // Center align the label
        heading.setFont(new Font("Arial", Font.BOLD, 18));
  
        JTextArea t = new JTextArea(menuItem);
        t.setEditable(false);
        JTextArea t2 = new JTextArea(menuItemString);
        t2.setEditable(false);
        JTextArea t3 = new JTextArea(menuPrice);
        t3.setEditable(false);
        JButton b2 = new JButton("Edit");
        JButton b3 = new JButton("Add");
        JButton b4 = new JButton("Delete");
        JButton b = new JButton("Close");
        
  
        // add actionlistener to button
         b.addActionListener((ActionListener) s);
         p.add(heading);
         p.add(t);
         p.add(t2);
         p.add(t3);
         p.add(b);
         p.add(b2);
         p.add(b3);
         p.add(b4);
  
  
         //EDIT
      b2.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              frame2 = new JFrame("edit item");
              // create a new panel for editing menu items
              JPanel p2 = new JPanel();
              p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
      
              // create labels and text fields for input
              JLabel itemNumLabel = new JLabel("Item Number:");
              JTextField itemNumField = new JTextField(10);
              itemNumField.setMaximumSize(itemNumField.getPreferredSize());
              JLabel menuNameLabel = new JLabel("Menu Name:");
              JTextField menuNameField = new JTextField(20);
              menuNameField.setMaximumSize(menuNameField.getPreferredSize());
              JLabel menuPriceLabel = new JLabel("Menu Price:");
              JTextField menuPriceField = new JTextField(10);
              menuPriceField.setMaximumSize(menuPriceField.getPreferredSize());
  
              JButton confirmButton = new JButton("Confirm Changes");
              confirmButton.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) 
                  {
                    String s = e.getActionCommand();
                    if (s.equals("Edit")) {
                      frame2.setVisible(true);
                    } else if (s.equals("Confirm Changes")) {
                      // get the values entered by the user
                      String num = itemNumField.getText();
                      String name = menuNameField.getText();
                      String price = menuPriceField.getText();
                    
                      try {
                        Class.forName("org.postgresql.Driver");
                        conn2 = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63",
                            "csce315331_team_63_master", "WFHD");
                        
                        // create a statement object
                        Statement stmt = conn2.createStatement();
                        // write the SQL update statement
                        String sql = "UPDATE menu2 SET food = '" + name + "', price = " + price + " WHERE itemnum = " + num + ";";
                        Order2.DeleteAndRepopulate();
                        // execute the statement
                        int rowsUpdated = stmt.executeUpdate(sql);
                        if (rowsUpdated > 0) {
                          // if the update was successful, close the edit item frame
                          frame2.setVisible(false);
                          
                          // get the updated data and sort it by item number
                          sql = "SELECT * FROM menu2 ORDER BY itemnum";
                          ResultSet rs = stmt.executeQuery(sql);
                          menuItem = "Item Id: " + '\n';
                          menuItemString = "Menu Item: "+'\n';
                          menuPrice = "Price: " + '\n';
                          while (rs.next()) {
                            menuItem += rs.getString("itemnum") + "\n";
                            menuItemString += rs.getString("food") + "\n";
                            menuPrice += rs.getString("price") + "\n";
                          }
                          // update the GUI JTextAreas to reflect the changes
                          t.setText(menuItem);
                          t2.setText(menuItemString);
                          t3.setText(menuPrice);
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
                    
                  }
              });
  
  
              
              // add labels and text fields to the panel
              p2.add(itemNumLabel);
              p2.add(itemNumField);
              p2.add(menuNameLabel);
              p2.add(menuNameField);
              p2.add(menuPriceLabel);
              p2.add(menuPriceField);
              p2.add(confirmButton);
      
              // add the new panel to the frame and display it
              frame2.add(p2);
              frame2.setSize(200, 400);
              frame2.show();
              
          }
        });
  
  
      //ADD
      b3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
          frame3 = new JFrame("add item");
          // create a new panel for editing menu items
          JPanel p3 = new JPanel();
          p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
  
          // create labels and text fields for input
          JLabel menuNameLabel = new JLabel("Menu Name:");
          JTextField menuNameField = new JTextField(20);
          menuNameField.setMaximumSize(menuNameField.getPreferredSize());
          JLabel menuPriceLabel = new JLabel("Menu Price:");
          JTextField menuPriceField = new JTextField(10);
          menuPriceField.setMaximumSize(menuPriceField.getPreferredSize());
  
          JButton confirmButton = new JButton("Confirm Changes");
          confirmButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) 
              {
                  String s = e.getActionCommand();
                  if (s.equals("Add")) {
                  frame3.setVisible(true);
                  } else if (s.equals("Confirm Changes")) {
                  // get the values entered by the user
                  String name = menuNameField.getText();
                  String price = menuPriceField.getText();
                  
                  try {
                      Class.forName("org.postgresql.Driver");
                      conn2 = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63",
                          "csce315331_team_63_master", "WFHD");
                      
                      // create a statement object
                      Statement stmt = conn2.createStatement();
  
                                  // get the max itemnum number from the menu table
                      String getMaxNumSql = "SELECT MAX(itemnum) AS max_num FROM menu2";
                      ResultSet maxNumRs = stmt.executeQuery(getMaxNumSql);
                      int num = 1;
                      if (maxNumRs.next()) {
                          num = maxNumRs.getInt("max_num") + 1;
                      }
                      // write the SQL update statement
                      String sql = "INSERT INTO menu2 (itemnum, food, price, ingridents) VALUES (" + num + ", '" + name + "', " + price + ", '" + hashmapEntry + "');";
                      // execute the statement
                      Order2.runMenuAndButton();
                      // 
                      hashmapEntry = "";
                      
                      int rowsUpdated = stmt.executeUpdate(sql);
                      if (rowsUpdated > 0) {
                      // if the update was successful, close the edit item frame
                      frame3.setVisible(false);
                      
                      // get the updated data and sort it by item number
                      sql = "SELECT * FROM menu2 ORDER BY itemnum";
                      ResultSet rs = stmt.executeQuery(sql);
                      menuItem = "Item Id: " + '\n';
                      menuItemString = "Menu Item: "+'\n';
                      menuPrice = "Price: " + '\n';
                      while (rs.next()) {
                          menuItem += rs.getString("itemnum") + "\n";
                          menuItemString += rs.getString("food") + "\n";
                          menuPrice += rs.getString("price") + "\n";
                      }
                      // update the GUI JTextAreas to reflect the changes
                      t.setText(menuItem);
                      t2.setText(menuItemString);
                      t3.setText(menuPrice);
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
                  
              }
          });
  
  
          
          // add labels and text fields to the panel
          p3.add(menuNameLabel);
          p3.add(menuNameField);
          p3.add(menuPriceLabel);
          p3.add(menuPriceField);
          p3.add(confirmButton);
          JPanel p3a = new JPanel();
          inventoryCheckbox(p3a);
  
          // add the new panel to the frame and display it
          frame3.setLayout(new GridLayout(2, 1));
          frame3.add(p3);
          frame3.add(p3a);
          frame3.setSize(600, 800);
          frame3.show();
          
      }
      });
  
  
      //DELETE
      b4.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              frame4 = new JFrame("delete item");
              // create a new panel for editing menu items
              JPanel p4 = new JPanel();
              p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));
      
              // create labels and text fields for input
              JLabel itemNumLabel = new JLabel("Item Number:");
              JTextField itemNumField = new JTextField(10);
              itemNumField.setMaximumSize(itemNumField.getPreferredSize());
  
  
              JButton confirmButton = new JButton("Confirm Changes");
              confirmButton.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) 
                  {
                    String s = e.getActionCommand();
                    if (s.equals("Delete")) {
                      frame2.setVisible(true);
                    } else if (s.equals("Confirm Changes")) {
                      // get the values entered by the user
                      String num = itemNumField.getText();
                    
                      try {
                        Class.forName("org.postgresql.Driver");
                        conn2 = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63",
                            "csce315331_team_63_master", "WFHD");
                        
                        // create a statement object
                        Statement stmt = conn2.createStatement();
                        // write the SQL update statement
                        String sql = "DELETE FROM menu2 WHERE itemnum = " + num + ";";

                        Order2.DeleteAndRepopulate();

                        // execute the statement
                        int rowsUpdated = stmt.executeUpdate(sql);
                        if (rowsUpdated > 0) {
                          // if the update was successful, close the edit item frame
                          frame4.setVisible(false);
                          
                          // get the updated data and sort it by item number
                          sql = "SELECT * FROM menu2 ORDER BY itemnum";
                          ResultSet rs = stmt.executeQuery(sql);
                          menuItem = "Item Id: " + '\n';
                          menuItemString = "Menu Item: "+'\n';
                          menuPrice = "Price: " + '\n';
                          while (rs.next()) {
                            menuItem += rs.getString("itemnum") + "\n";
                            menuItemString += rs.getString("food") + "\n";
                            menuPrice += rs.getString("price") + "\n";
                          }
                          // update the GUI JTextAreas to reflect the changes
                          t.setText(menuItem);
                          t2.setText(menuItemString);
                          t3.setText(menuPrice);
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
                    
                  }
              });
  
  
              
              // add labels and text fields to the panel
              p4.add(itemNumLabel);
              p4.add(itemNumField);
              p4.add(confirmButton);
      
              // add the new panel to the frame and display it
              frame4.add(p4);
              frame4.setSize(200, 400);
              frame4.show();
              
          }
        });
      
  
        
        f.add(p);
  
        f.setSize(800, 500);
  
        f.show();
  
        //closing the connection
        try {
          conn.close();
          JOptionPane.showMessageDialog(null,"Connection Closed.");
        } catch(Exception e) {
          JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
        }
    }

    public static void runMenuTable() 
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
        //create a statement object
        Statement stmt = conn.createStatement();
        //String sqlStatement = ;
        //send statement to DBMS
        ResultSet result = stmt.executeQuery("SELECT * FROM menu2 ORDER BY itemnum;"); 
        while (result.next()) 
        {
          menuItem +=  result.getString("itemnum")+"\n";
          menuItemString += result.getString("food")+"\n";
          menuPrice += result.getString("price")+"\n";
        }
      } 
      catch (Exception e)
      {
        JOptionPane.showMessageDialog(null,"Error accessing Database.");
      }
    }

    public static void runInventoryTable() 
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
        //create a statement object
        Statement stmt = conn.createStatement();
        //String sqlStatement = ;
        //send statement to DBMS
        ResultSet result = stmt.executeQuery("SELECT * FROM inventory_table;"); 
        while (result.next()) 
        {
          inventoryItemNum +=  result.getString("item_id")+"\n";
          inventoryItem += result.getString("item")+"\n";
          inventoryQuantity += result.getString("quantity")+"\n";
          inventoryPrice +=  result.getString("price")+"\n";
          inventoryVendorName += result.getString("vendor_name")+"\n";
          inventoryUnit += result.getString("units")+"\n";
          inventoryExp_date += result.getString("expiration_date")+"\n";
        }
      } 
      catch (Exception e)
      {
        JOptionPane.showMessageDialog(null,"Error accessing Database.");
      }
    }

    public static void inventoryCheckbox(JPanel buttonPanel)
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
        ArrayList<String> inventory_values = new ArrayList<>();

        //create a statement object
        Statement stmt = conn.createStatement();
        //String sqlStatement = ;
        //send statement to DBMS
        ResultSet result = stmt.executeQuery("SELECT * FROM inventory_table;"); 
        while (result.next()) 
        {
          inventory_values.add(result.getString("item"));
        }
        int numItems = inventory_values.size();
        int numCols = 2; // Number of columns in the grid
        int numRows = (numItems + numCols - 1) / numCols; // Calculate number of rows needed
        buttonPanel.setLayout(new GridLayout(numRows, numCols));
        
        ArrayList<JTextField> textFields = new ArrayList<JTextField>();
        ArrayList<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
        
        for (int i = 0; i < numItems; i++) {
            String checkboxName = inventory_values.get(i);
            JCheckBox checkbox = new JCheckBox(checkboxName);
            JTextField textField = new JTextField();
            textField.setColumns(10);
            textField.setVisible(false);
            textFields.add(textField);
            checkboxes.add(checkbox);
            final int index = i;
            checkbox.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    textFields.get(index).setVisible(true);
                } else {
                    textFields.get(index).setVisible(false);
                }
            });
            buttonPanel.add(checkbox);
            buttonPanel.add(textField);
        }
        
        JButton confirmButton = new JButton("Confirm Inventory Item");
        confirmButton.addActionListener(e -> {
          for (int i = 0; i < numItems; i++) {
              JCheckBox checkbox = checkboxes.get(i);
              JTextField textField = textFields.get(i);
              if (checkbox.isSelected()) {
                  String checkboxName = checkbox.getText();
                  String textFieldValue = textField.getText();
                  hashmapEntry += textFieldValue + ":" + checkboxName +",";
              }
              textField.setText(""); // reset text field to empty string
              checkbox.setSelected(false); // uncheck checkbox
              checkbox.setEnabled(false); // disable checkbox
          }
          hashmapEntry = hashmapEntry.trim().substring(0,hashmapEntry.length()-1);
          System.out.println(hashmapEntry);
      });
        buttonPanel.add(confirmButton);
      } 
      catch (Exception e)
      {
        JOptionPane.showMessageDialog(null,"Error accessing Database.");
      }
    }

    public static void salesReport()
    {
      JFrame salesFrame = new JFrame("Sales Report");
      // create a new panel for editing menu items
      JPanel p2 = new JPanel();
      p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
  
      // create a label and text field for starting date
      JLabel startLabel = new JLabel("Starting Date: ");
      JTextField startDateField = new JTextField(10);
      JPanel startPanel = new JPanel();
      startPanel.add(startLabel);
      startPanel.add(startDateField);
  
      // create a label and text field for ending date
      JLabel endLabel = new JLabel("Ending Date: ");
      JTextField endDateField = new JTextField(10);
      JPanel endPanel = new JPanel();
      endPanel.add(endLabel);
      endPanel.add(endDateField);
  
      // create a confirm button
      JButton confirmButton = new JButton("Confirm");
  
      // create text areas for displaying orders and total
      JTextArea allOrderArea = new JTextArea(10, 30);
      allOrderArea.setEditable(false);
      JScrollPane allOrderScroll = new JScrollPane(allOrderArea);
      JTextArea totalArea = new JTextArea(1, 30);
      totalArea.setEditable(false);
      JScrollPane totalScroll = new JScrollPane(totalArea);
  
      // add components to the panel
      p2.add(startPanel);
      p2.add(endPanel);
      p2.add(confirmButton);
      p2.add(allOrderScroll);
      p2.add(totalScroll);
  
      // add the panel to the frame
      salesFrame.getContentPane().add(p2);
  
      // set the size and make the frame visible
      salesFrame.setSize(400, 400);
      salesFrame.setVisible(true);

        confirmButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) 
          {
              // retrieve starting and ending dates
              String startDate = startDateField.getText();    
              String endDate = endDateField.getText();    

              try {
                  Class.forName("org.postgresql.Driver");
                  conn2 = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63",
                      "csce315331_team_63_master", "WFHD");

                  // create a statement object
                  Statement stmt = conn2.createStatement();
                  // write the SQL query to retrieve order history between specified dates
                  String sql = "SELECT order_history, total FROM orderhistory WHERE date >= '" + startDate + "' AND date <= '" + endDate + "'";
                  ResultSet rs = stmt.executeQuery(sql);

                  // display order history in the allOrder text area
                  allOrderArea.setText("");
                  while (rs.next()) {
                      String orderHistory = rs.getString("order_history");
                      String price = rs.getString("total");
                      allOrderArea.append(orderHistory + " : " + price + "\n");
                  }

                  // retrieve and display total in the total text area
                  rs = stmt.executeQuery("SELECT SUM(total) AS total FROM orderhistory WHERE date >= '" + startDate + "' AND date <= '" + endDate + "'");
                  if (rs.next()) {
                      double total = rs.getDouble("total");
                      totalArea.setText("Total: $" + String.format("%.2f", total));
                  } else {
                      totalArea.setText("Total: $0.00");
                  }

                  try {
                      conn2.close();
                      JOptionPane.showMessageDialog(null,"Connection Closed.");
                  } catch(Exception e2) {
                      JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
                  }
              } catch (Exception ex) {
                  JOptionPane.showMessageDialog(null, "Error retrieving");
              }  
          }
      });
    }

    public static void restockReport() {
      JFrame restockFrame = new JFrame("Restock Report");
      
      // create a new panel for editing menu items
      JPanel p2 = new JPanel();
      p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
  
      // add a label saying "Restock Report"
      JLabel titleLabel = new JLabel("Restock Needed");
      titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      p2.add(titleLabel);
  
      try {
          Class.forName("org.postgresql.Driver");
          conn2 = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63",
                  "csce315331_team_63_master", "WFHD");
  
          // create a statement object
          Statement stmt = conn2.createStatement();
  
          // write the SQL query to retrieve inventory items with quantity less than 20.0
          String sql = "SELECT item, quantity FROM inventory_table WHERE CAST(quantity AS DOUBLE PRECISION) < 20.0";
          ResultSet rs = stmt.executeQuery(sql);
  
          // display items that need restocking in the text area
          JTextArea textArea = new JTextArea();
          textArea.setEditable(false);
          while (rs.next()) {
              String item = rs.getString("item");
              String quantity = rs.getString("quantity");
              textArea.append(item + " : " + quantity + "\n");
          }
          p2.add(textArea);
  
          try {
              conn2.close();
              JOptionPane.showMessageDialog(null, "Connection Closed.");
          } catch (Exception e2) {
              JOptionPane.showMessageDialog(null, "Connection NOT Closed.");
          }
      } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "Error retrieving inventory items.");
      }
  
      restockFrame.getContentPane().add(p2);
      restockFrame.pack();
      restockFrame.setSize(400, restockFrame.getHeight());
      restockFrame.setVisible(true);
  }

    // if button is pressed
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (s.equals("Close")) {
            f.dispose();
        }
    }
}
