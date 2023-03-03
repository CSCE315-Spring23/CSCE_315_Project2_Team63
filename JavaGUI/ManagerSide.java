package CSCE_315_Project2_Team63.JavaGUI;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Font;
import java.awt.GridLayout;

public class ManagerSide extends JFrame implements ActionListener {
    static JFrame intialMangOption;
    static JFrame f;
    static JFrame frame2;
    static JFrame frame3;
    static JFrame frame4;

    public static ManagerSide s = new ManagerSide();
    
    public static Connection conn = null;
    public static Connection conn2 = null;
    public static String menuItem = "";
    public static String menuItemString = "";
    public static String menuPrice = "";


    public static void main(String[] args)
    {
        intialMangOption = new JFrame("Manager Options");
        JPanel managerPanel = new JPanel();
    
        JLabel managerTitle = new JLabel("MANAGER DASHBOARD");
        managerTitle.setHorizontalAlignment(JLabel.CENTER); // Center align the label
        managerTitle.setFont(new Font("Arial", Font.BOLD, 24));
        JButton menuSettings = new JButton("MENU SETTINGS");
        JButton inventorySettings = new JButton("INVENTORY SETTINGS");
    
        // Add ActionListener to menuSettings button
        menuSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuChange();
                intialMangOption.setVisible(false);
            }
        });
    
        // Add components to managerPanel using GridLayout
        managerPanel.setLayout(new GridLayout(2, 1, 10, 10));
        managerPanel.add(managerTitle);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(menuSettings);
        buttonPanel.add(inventorySettings);
        managerPanel.add(buttonPanel);
    
        // Add managerPanel to frame
        intialMangOption.getContentPane().add(managerPanel);
    
        // Set frame properties
        intialMangOption.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        intialMangOption.setSize(400, 300);
        intialMangOption.setVisible(true);
    }

    public static void MenuChange()
    {
        runTable();

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
         b.addActionListener(s);
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
                        String sql = "UPDATE menu SET food = '" + name + "', price = " + price + " WHERE itemnum = " + num + ";";
                        // execute the statement
                        int rowsUpdated = stmt.executeUpdate(sql);
                        if (rowsUpdated > 0) {
                          // if the update was successful, close the edit item frame
                          frame2.setVisible(false);
                          
                          // get the updated data and sort it by item number
                          sql = "SELECT * FROM menu ORDER BY itemnum";
                          ResultSet rs = stmt.executeQuery(sql);
                          menuItem = "";
                          menuItemString = "";
                          menuPrice = "";
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
                      String getMaxNumSql = "SELECT MAX(itemnum) AS max_num FROM menu";
                      ResultSet maxNumRs = stmt.executeQuery(getMaxNumSql);
                      int num = 1;
                      if (maxNumRs.next()) {
                          num = maxNumRs.getInt("max_num") + 1;
                      }
                      // write the SQL update statement
                      String sql = "INSERT INTO menu (itemnum, food, price) VALUES (" + num + ", '" + name + "', " + price + ");";
                      // execute the statement
                      int rowsUpdated = stmt.executeUpdate(sql);
                      if (rowsUpdated > 0) {
                      // if the update was successful, close the edit item frame
                      frame3.setVisible(false);
                      
                      // get the updated data and sort it by item number
                      sql = "SELECT * FROM menu ORDER BY itemnum";
                      ResultSet rs = stmt.executeQuery(sql);
                      menuItem = "";
                      menuItemString = "";
                      menuPrice = "";
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
  
          // add the new panel to the frame and display it
          frame3.add(p3);
          frame3.setSize(200, 400);
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
                        String sql = "DELETE FROM menu WHERE itemnum = " + num + ";";
                        // execute the statement
                        int rowsUpdated = stmt.executeUpdate(sql);
                        if (rowsUpdated > 0) {
                          // if the update was successful, close the edit item frame
                          frame4.setVisible(false);
                          
                          // get the updated data and sort it by item number
                          sql = "SELECT * FROM menu ORDER BY itemnum";
                          ResultSet rs = stmt.executeQuery(sql);
                          menuItem = "";
                          menuItemString = "";
                          menuPrice = "";
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
  
        f.setSize(600, 400);
  
        f.show();
  
        //closing the connection
        try {
          conn.close();
          JOptionPane.showMessageDialog(null,"Connection Closed.");
        } catch(Exception e) {
          JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
        }
    }

    public static void runTable() 
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
        ResultSet result = stmt.executeQuery("SELECT * FROM menu ORDER BY itemnum;"); 
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

    // if button is pressed
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (s.equals("Close")) {
            f.dispose();
        }
    }
}