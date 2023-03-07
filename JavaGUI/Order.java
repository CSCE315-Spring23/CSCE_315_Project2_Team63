package CSCE_315_Project2_Team63.JavaGUI;
import java.sql.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

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

        // Buttons for each menu item
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

        // Close button to terminate the program
        JButton close = new JButton("Close");
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.dispose(); //closes window
                System.exit(0); //terminates program
            }   
        });


        bowls.addActionListener(new ActionListener() {
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
                        String sqlStatement = "SELECT * FROM menu WHERE food LIKE '%bowl%';";
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


        burritos.addActionListener(new ActionListener() {
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



        
        panel.add(close);
        panel.add(bowls);
        panel.add(burritos);
        panel.add(tacos);
        panel.add(sides_and_drinks);

        // Add frame to panel to show on screen
        frame.add(panel, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);

        // Listeners
        // Listens when close is activated in frame
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                System.exit(0); 
            }
        });
    }


    // connect run all functions

    // close
}
