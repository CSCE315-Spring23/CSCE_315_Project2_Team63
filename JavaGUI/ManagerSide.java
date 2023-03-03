package CSCE_315_Project2_Team63.JavaGUI;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class ManagerSide extends JFrame implements ActionListener {
    static JFrame f;
    static JFrame frame2;
    
    public static Connection conn = null;
    public static Connection conn2 = null;
    public static String menuItem = "";
    public static String menuItemString = "";
    public static String menuPrice = "";

    public static void main(String[] args)
    {

      runTable();

      // create a new frame
      f = new JFrame("DB GUI");
      // create a object
      ManagerSide s = new ManagerSide();
      // create a panel
      JPanel p = new JPanel();

      JLabel heading = new JLabel("MENU");

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
        ResultSet result = stmt.executeQuery("SELECT * FROM menu;"); 
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