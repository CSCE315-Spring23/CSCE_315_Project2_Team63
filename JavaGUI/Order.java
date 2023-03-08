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

public class Order extends JFrame implements ActionListener {
    public static Connection conn2 = null;
    private JTextArea receiptArea;
    private JTextField employeeID, date, name;
    private JLabel employee_label, date_label, name_label, total_price;
    private JButton chicken_taco_button, steak_taco_button, beef_taco_button, veggie_taco_button;
    private JButton chips_and_guac, chips_and_queso, chips_and_salsa, drink;
    private JButton burrito_steak_button, burrito_beef_button, burrito_veg_button, burrito_chicken_button;
    private JButton bowl_chicken_button, bowl_steak_button, bowl_beef_button, bowl_veg_button, cancel;
    private String itemList = "";

    private JButton checkoutButton;
    private double total = 0;
    private double complete_total = 0;
    // private Boolean isFirst = false;
    // private String date_ = "";

    public Order() {
        super("Order");
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
    
        // Create buttons for adding items and checking out
        chicken_taco_button = new JButton("Chicken Taco - $7.89");
        chicken_taco_button.addActionListener(this);
    
        steak_taco_button = new JButton("Steak Taco - $8.89");
        steak_taco_button.addActionListener(this);
    
        beef_taco_button = new JButton("Beef Taco - $8.79");
        beef_taco_button.addActionListener(this);
    
        veggie_taco_button = new JButton("Veggie Taco - $7.89");
        veggie_taco_button.addActionListener(this);
        
        chips_and_guac = new JButton("Chips-and-guac - $3.69");
        chips_and_guac.addActionListener(this);
    
        chips_and_salsa = new JButton("Chips-and-salsa - $2.19");
        chips_and_salsa.addActionListener(this);
    
        chips_and_queso = new JButton("Chips-and-queso - $3.49");
        chips_and_queso.addActionListener(this);
        
        drink = new JButton("Drink - $2.45");
        drink.addActionListener(this);
    
        burrito_steak_button = new JButton("Steak Burrito - $9.19");
        burrito_steak_button.addActionListener(this);
    
        burrito_beef_button = new JButton("Beef Burrito - $8.99");
        burrito_beef_button.addActionListener(this);
    
        burrito_veg_button = new JButton("Veggie Burrito - $8.29");
        burrito_veg_button.addActionListener(this);
    
        burrito_chicken_button = new JButton("Chicken Burrito - $8.69");
        burrito_chicken_button.addActionListener(this);
    
        bowl_steak_button = new JButton("Steak Bowl- $9.19");
        bowl_steak_button.addActionListener(this);
    
        bowl_beef_button = new JButton("Beef Bowl - $8.99");
        bowl_beef_button.addActionListener(this);
    
        bowl_veg_button = new JButton("Veggie Bowl - $8.29");
        bowl_veg_button.addActionListener(this);
    
        bowl_chicken_button = new JButton("Chicken Bowl - $8.69");
        bowl_chicken_button.addActionListener(this);
    
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(this);

        cancel = new JButton("Cancel Order");
        cancel.addActionListener(this);
    
        // Add components to the frame
        JPanel buttonPanel = new JPanel(new GridLayout(2, 8));
        buttonPanel.add(chips_and_queso);
        buttonPanel.add(chips_and_guac);
        buttonPanel.add(chips_and_salsa);
        buttonPanel.add(chicken_taco_button);
        buttonPanel.add(steak_taco_button);
        buttonPanel.add(beef_taco_button);
        buttonPanel.add(veggie_taco_button);
        buttonPanel.add(drink);
        buttonPanel.add(burrito_chicken_button);
        buttonPanel.add(burrito_beef_button);
        buttonPanel.add(burrito_steak_button);
        buttonPanel.add(burrito_veg_button);
        buttonPanel.add(bowl_beef_button);
        buttonPanel.add(bowl_chicken_button);
        buttonPanel.add(bowl_steak_button);
        buttonPanel.add(bowl_veg_button);
    
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
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chicken_taco_button) {
            String item = "Chicken Taco";
            double price = 7.89;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == burrito_steak_button) {
            String item = "Marinated Steak Burrito";
            double price = 9.19;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == steak_taco_button) {
            String item = "Marinated Steak Taco";
            double price = 8.89;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == drink) {
            String item = "Fountain Drink";
            double price = 2.45;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == beef_taco_button) {
            String item = "Beef Taco";
            double price = 8.79;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == burrito_chicken_button) {
            String item = "Chicken Burrito";
            double price = 8.69;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == veggie_taco_button) {
            String item = "Grilled Vegetable Taco";
            double price = 7.89;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == burrito_beef_button) {
            String item = "Grilled Vegetable Taco";
            double price = 7.89;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == chips_and_guac) {
            String item = "Chips-and-Guac";
            double price = 3.69;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == chips_and_queso) {
            String item = "Chips-and-Queso";
            double price = 3.49;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == chips_and_salsa) {
            String item = "Chips-and-Salsa";
            double price = 2.19;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == burrito_veg_button) {
            String item = "Grilled Vegetable Burrito";
            double price = 8.29;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == bowl_veg_button) {
            String item = "Grilled Vegetable Bowl";
            double price = 8.29;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == bowl_beef_button) {
            String item = "Beef Bowl";
            double price = 8.99;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == bowl_steak_button) {
            String item = "Marinated Steak Bowl";
            double price = 9.19;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == bowl_chicken_button) {
            String item = "Chicken Bowl";
            double price = 8.69;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == checkoutButton) {
            // String prev_day = "";
            // if(isFirst == false) {
            //     prev_day = date.getText();
            // }
            String employee = employeeID.getText();
            String day = date.getText();
            String customer = name.getText();

            try {
                Class.forName("org.postgresql.Driver");
                conn2 = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63",
                    "csce315331_team_63_master", "WFHD");

                Statement stmt = conn2.createStatement();
                stmt.executeUpdate("INSERT INTO orderhistory (emp_id, date, customer_name, order_history, total) VALUES ('" + employee + "', '" + day + "', '" + customer + "', '" + itemList + "', '" + total + "')");
                //stmt.executeUpdate("INSERT INTO sales (date, total_sales) VALUES ('" + day + "', '" + total + "')");

            } catch (Exception e2) {
                e2.printStackTrace();
                System.err.println(e.getClass().getName()+": "+e2.getMessage());
                System.exit(0);
            }
        
            total = 0;
            itemList = "";
            total_price.setText("Total price: $0.00");
            receiptArea.setText("");

            // complete_total += total;

            // if(employee.isEmpty() || day.isEmpty() || customer.isEmpty()){
            //     JOptionPane.showMessageDialog(null, "Please enter into all text entries");
            // } else {
            //     total = 0;
            //     total_price.setText("Total price: $0.00");
            //     receiptArea.setText("");
            // }

            // update sales
            // if(prev_day != day){
            //     try {
            //         Class.forName("org.postgresql.Driver");
            //         connorder = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63", "csce315331_team_63_master", "WFHD");
            //         //create a statement object
            //         Statement stmt = connorder.createStatement();
            //         // stmt.executeUpdate("UPDATE sales (date, total_sales) VALUES ('" + prev_day + "', '" + complete_total + "')");
            //         stmt.executeUpdate("UPDATE sales SET date=")
            //         complete_total = 0;
            //         isFirst = false;
            //     } catch (Exception e2) {
            //         e2.printStackTrace();
            //         JOptionPane.showMessageDialog(null,"Error accessing Database.");
            //         System.exit(0);
            //     }
            // }
            // else {
            //     // previous day
            //     prev_day = day;
            //     isFirst = true;
            // }

        } else if(e.getSource() == cancel) {//
            total = 0;
            total_price.setText("Total price: $0.00");
            receiptArea.setText("");
            employeeID.setText("");
            date.setText("");
            name.setText("");
            itemList = "";
        }
    }

    public static void main(String[] args) {
        new Order();
    }    
}