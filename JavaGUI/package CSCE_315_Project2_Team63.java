package CSCE_315_Project2_Team63.JavaGUI;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.DefaultListModel;
import javax.swing.*;
import java.io.*;
import java.util.*;

//TO-DO: create a list for each menu category and make buttons for them.
// when you click the button it should add to an ongoing list that is shown in the middle of the screen and close the existing window
// add a possibile way to increment and decrement each item in the list 
// make a button to confirm order. the confirm button should update the tables sales and order history
// make a function that can show the total sum of the existing order actively

// implements ActionListener
public class Order extends JFrame {
    // main
    public static void main(String[] args){
        // Frame and panel for button layout: Centered with menu items at the top and close and delete at the bottom.
        JFrame frame = new JFrame("Order History");
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));

        Vector<String> food_names;
        Vector<Integer> prices;

        //-------------------- Menu buttons to add items to the list -----------------------
        JButton bowls = new JButton("Bowls");
        JButton burritos = new JButton("Burritos");
        JButton tacos = new JButton("Tacos");
        JButton sides_and_drinks = new JButton("Sides&Drinks");

        // Add buttons to panel with spacing
        buttonContainer.add(Box.createHorizontalGlue());
        buttonContainer.add(bowls);
        buttonContainer.add(Box.createHorizontalStrut(10));
        buttonContainer.add(burritos);
        buttonContainer.add(Box.createHorizontalStrut(10));
        buttonContainer.add(tacos);
        buttonContainer.add(Box.createHorizontalStrut(10));
        buttonContainer.add(sides_and_drinks);
        buttonContainer.add(Box.createHorizontalGlue());

        panel.add(buttonContainer);
        //---------------------------------------------------------------------------------

        // Default list for testing
        JList<String> list = new JList<>();

        //---------- Confirm order and update order history/daily sales tables ------------
        // Active total sum of order bar
        
        //---------------------------------------------------------------------------------
        

        //------------- Delete All button to remove all items to the list -----------------
        // JButton delete_all = new JButton("Delete");
        // delete_all.addActionListener(new ActionListener(){
        //     public void actionPerformed(ActionEvent d){
        //         list.removeAllElements();
        //     }
        // });

        // panel.add(delete_all);
        //---------------------------------------------------------------------------------


        //-------------------- Close button to terminate the program-----------------------
        JButton close = new JButton("Close");
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.dispose(); //closes window
                System.exit(0); //terminates program
            }   
        });
        // Listens when close is activated in frame
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                System.exit(0); 
            }
        });

        panel.add(close);
        //---------------------------------------------------------------------------------

        bowls.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E){
                Connection conn = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63","csce315331_team_63_master", "WFHD");
                } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName()+": "+e.getMessage());
                System.exit(0);
                }

                List<String> foodlist = new ArrayList<>();
                JFrame f = new JFrame();
                JPanel p = new JPanel();

                try{
                    //create a statement object
                    Statement stmt = conn.createStatement();
                    //create an SQL statement
                    //TODO Step 2
                    String sqlStatement = "SELECT * FROM menu WHERE food LIKE '%bowl%';";
                    //send statement to DBMS
                    ResultSet result = stmt.executeQuery(sqlStatement);
                    while (result.next()) {
                        String food = result.getString("food");
                        foodlist.add(food); 
                    }
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Error accessing Database.");
                }

                for (String food : foodlist) {
                    JButton button = new JButton(food);
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            list.add(button);
                        }
                    });
                    p.add(button);
                }

                f.add(p);
                f.add(p, BorderLayout.NORTH);
                f.pack();
                f.setVisible(true);       
            }
        });


        burritos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E){
                Connection conn = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63","csce315331_team_63_master", "WFHD");
                  } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println(e.getClass().getName()+": "+e.getMessage());
                    System.exit(0);
                  }

                String name = "";
                JFrame f = new JFrame();
                JPanel p = new JPanel();

                try{
                    //create a statement object
                    Statement stmt = conn.createStatement();
                    //create an SQL statement
                    //TODO Step 2
                    String sqlStatement = "SELECT * FROM menu WHERE food LIKE '%burrito%';";
                    //send statement to DBMS
                    ResultSet result = stmt.executeQuery(sqlStatement);
                    while (result.next()) {
                        name += result.getString("food")+"\n";
                    }
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Error accessing Database.");
                }

                JTextArea t = new JTextArea(name);

                p.add(t);
                f.add(p);
                f.add(p, BorderLayout.NORTH);
                f.pack();
                f.setVisible(true);       
            }
        });

        tacos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E){
                Connection conn = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63","csce315331_team_63_master", "WFHD");
                  } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println(e.getClass().getName()+": "+e.getMessage());
                    System.exit(0);
                  }

                String name = "";
                JFrame f = new JFrame();
                JPanel p = new JPanel();

                try{
                    //create a statement object
                    Statement stmt = conn.createStatement();
                    //create an SQL statement
                    //TODO Step 2
                    String sqlStatement = "SELECT * FROM menu WHERE food LIKE '%taco%';";
                    //send statement to DBMS
                    ResultSet result = stmt.executeQuery(sqlStatement);
                    while (result.next()) {
                        name += result.getString("food")+"\n";
                    }
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Error accessing Database.");
                }

                JTextArea t = new JTextArea(name);

                p.add(t);
                f.add(p);
                f.add(p, BorderLayout.NORTH);
                f.pack();
                f.setVisible(true);       
            }
        });

        sides_and_drinks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E){
                Connection conn = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63",
                       "csce315331_team_63_master", "WFHD");
                  } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println(e.getClass().getName()+": "+e.getMessage());
                    System.exit(0);
                  }

                  String name = "";
                  JFrame f = new JFrame();
                  JPanel p = new JPanel();

                    try{
                        //create a statement object
                        Statement stmt = conn.createStatement();
                        //create an SQL statement
                        //TODO Step 2
                        String sqlStatement = "SELECT * FROM menu WHERE (food LIKE '%drink%' OR food LIKE '%chip%' OR food LIKE '%rainbow%');";
                        //send statement to DBMS
                        ResultSet result = stmt.executeQuery(sqlStatement);
                        while (result.next()) {
                        name += result.getString("food")+"\n";
                        }
                    } catch (Exception e){
                        JOptionPane.showMessageDialog(null,"Error accessing Database.");
                    }

                    JTextArea t = new JTextArea(name);
                    
                    p.add(t);
                    f.add(p);
                    f.add(p, BorderLayout.NORTH);
                    f.pack();
                    f.setVisible(true);       
            }
        });

        // Add frame to panel to show on screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(list));
        frame.add(panel, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }
}
