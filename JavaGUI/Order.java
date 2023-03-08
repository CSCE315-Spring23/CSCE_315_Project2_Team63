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
    private JButton rainbow, custom;
    private JButton burrito_steak_button, burrito_beef_button, burrito_veg_button, burrito_chicken_button;
    private JButton bowl_chicken_button, bowl_steak_button, bowl_beef_button, bowl_veg_button, cancel;
    private String itemList = "";

    Vector<Double> quant = new Vector<Double>();
    Vector<String> ingredient = new Vector<String>();
    public ArrayList<ArrayList<String>> custom_order_list= new ArrayList<>();
    ArrayList<String> to_return = new ArrayList<>();

    private JButton checkoutButton;
    private double total = 0;
    private double complete_total = 0;
    private String prev_day = "N/A";
    private ArrayList<ArrayList<String> > custom_list = new ArrayList<ArrayList<String> >();
    // private String date_ = "";

    public Order() {
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
    
        // Create buttons for adding items and checking out
        chicken_taco_button = new JButton("tacos_chili-rubbed-chicken - $7.89");
        chicken_taco_button.addActionListener(this);
    
        steak_taco_button = new JButton("tacos-marinated-steak - $8.89");
        steak_taco_button.addActionListener(this);
    
        beef_taco_button = new JButton("tacos_seasoned-beef - $8.79");
        beef_taco_button.addActionListener(this);
    
        veggie_taco_button = new JButton("tacos_grilled-vegetable-medley - $7.89");
        veggie_taco_button.addActionListener(this);
        
        chips_and_guac = new JButton("chips-and-guac - $3.69");
        chips_and_guac.addActionListener(this);
    
        chips_and_salsa = new JButton("chips-and-salsa - $2.19");
        chips_and_salsa.addActionListener(this);
    
        chips_and_queso = new JButton("chips-and-queso - $3.49");
        chips_and_queso.addActionListener(this);
        
        drink = new JButton("fountain-drink - $2.45");
        drink.addActionListener(this);
    
        burrito_steak_button = new JButton("burrito_marinated-steak - $9.19");
        burrito_steak_button.addActionListener(this);
    
        burrito_beef_button = new JButton("burrito_seasoned-beef - $8.99");
        burrito_beef_button.addActionListener(this);
    
        burrito_veg_button = new JButton("burrito_grilled-vegetable-medley - $8.29");
        burrito_veg_button.addActionListener(this);
    
        burrito_chicken_button = new JButton("burrito_chili-rubbed-chicken - $8.69");
        burrito_chicken_button.addActionListener(this);
    
        bowl_steak_button = new JButton("bowl_marinated-steak - $9.19");
        bowl_steak_button.addActionListener(this);
    
        bowl_beef_button = new JButton("bowl_seasoned-beef - $8.99");
        bowl_beef_button.addActionListener(this);
    
        bowl_veg_button = new JButton("bowl_grilled-vegetable-medley - $8.29");
        bowl_veg_button.addActionListener(this);
    
        bowl_chicken_button = new JButton("bowl_chili-rubbed-chicken - $8.69");
        bowl_chicken_button.addActionListener(this);

        custom = new JButton("Custom");
        custom.addActionListener(this);

        rainbow = new JButton("eat-the-rainbow - $20");
        rainbow.addActionListener(this);
    
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(this);

        cancel = new JButton("Cancel Order");
        cancel.addActionListener(this);
    
        // Add components to the frame
        JPanel buttonPanel = new JPanel(new GridLayout(3, 6));
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
        buttonPanel.add(rainbow);
        buttonPanel.add(custom);
    
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

            ingredient.add("pinto beans");
            quant.add(0.375);

            ingredient.add("romaine lettuce");
            quant.add(0.327);

            ingredient.add("mixed cheddar");
            quant.add(0.1875);

            ingredient.add("pico de gallo");
            quant.add(0.09375);

            ingredient.add("onions");
            quant.add(0.1875);

            ingredient.add("jalapeno peppers");
            quant.add(0.09924);

            ingredient.add("cilantro");
            quant.add(0.08724);

            ingredient.add("sour cream");
            quant.add(0.355);

            ingredient.add("jalapeno ranch");
            quant.add(0.305);

            ingredient.add("red sauce");
            quant.add(0.215);

            ingredient.add("black olives");
            quant.add(0.315);

            ingredient.add("lime");
            quant.add(0.119);

            ingredient.add("italian dressing");
            quant.add(0.109);

            ingredient.add("small tortilla");
            quant.add(3.0);

            ingredient.add("paper bags");
            quant.add(1.0);

            ingredient.add("aluminum foil");
            quant.add(3.0);

            ingredient.add("gloves");
            quant.add(1.0);

            ingredient.add("lime juice");
            quant.add(0.0980);

            ingredient.add("chicken");
            quant.add(0.355);

            String item = "Chicken Taco";
            double price = 7.89;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == burrito_steak_button) {
            ingredient.add("black beans");
            quant.add(0.175);

            ingredient.add("spring mix");
            quant.add(0.127);

            ingredient.add("mozzarella cheese");
            quant.add(0.1005);

            ingredient.add("pico de gallo");
            quant.add(0.09375);

            ingredient.add("onions");
            quant.add(0.1875);

            ingredient.add("jalapeno peppers");
            quant.add(0.09924);

            ingredient.add("cilantro");
            quant.add(0.08724);

            ingredient.add("sour cream");
            quant.add(0.155);

            ingredient.add("ranch");
            quant.add(0.105);

            ingredient.add("chipotle sauce");
            quant.add(0.115);

            ingredient.add("black olives");
            quant.add(0.115);

            ingredient.add("lime");
            quant.add(0.089);

            ingredient.add("italian dressing");
            quant.add(0.109);

            ingredient.add("large tortilla");
            quant.add(1.0);

            ingredient.add("paper bags");
            quant.add(1.0);

            ingredient.add("aluminum foil");
            quant.add(1.0);

            ingredient.add("gloves");
            quant.add(1.0);

            ingredient.add("lime juice");
            quant.add(0.0980);

            ingredient.add("steak");
            quant.add(0.155);

            String item = "Marinated Steak Burrito";
            double price = 9.19;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == steak_taco_button) {
            ingredient.add("pinto beans");
            quant.add(0.375);

            ingredient.add("romaine lettuce");
            quant.add(0.327);

            ingredient.add("mixed cheddar");
            quant.add(0.1875);

            ingredient.add("pico de gallo");
            quant.add(0.09375);

            ingredient.add("onions");
            quant.add(0.1875);

            ingredient.add("jalapeno peppers");
            quant.add(0.09924);

            ingredient.add("cilantro");
            quant.add(0.08724);

            ingredient.add("sour cream");
            quant.add(0.355);

            ingredient.add("jalapeno ranch");
            quant.add(0.305);

            ingredient.add("red sauce");
            quant.add(0.215);

            ingredient.add("black olives");
            quant.add(0.315);

            ingredient.add("lime");
            quant.add(0.119);

            ingredient.add("italian dressing");
            quant.add(0.109);

            ingredient.add("small tortilla");
            quant.add(3.0);

            ingredient.add("paper bags");
            quant.add(1.0);

            ingredient.add("aluminum foil");
            quant.add(3.0);

            ingredient.add("gloves");
            quant.add(1.0);

            ingredient.add("lime juice");
            quant.add(0.0980);

            ingredient.add("steak");
            quant.add(0.355);
            String item = "Marinated Steak Taco";
            double price = 8.89;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == drink) {

            ingredient.add("fountain drink cups");
            quant.add(1.0);

            String item = "Fountain Drink";
            double price = 2.45;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == beef_taco_button) {
            ingredient.add("pinto beans");
            quant.add(0.375);

            ingredient.add("romaine lettuce");
            quant.add(0.327);

            ingredient.add("mixed cheddar");
            quant.add(0.1875);

            ingredient.add("pico de gallo");
            quant.add(0.09375);

            ingredient.add("onions");
            quant.add(0.1875);

            ingredient.add("jalapeno peppers");
            quant.add(0.09924);

            ingredient.add("cilantro");
            quant.add(0.08724);

            ingredient.add("sour cream");
            quant.add(0.355);

            ingredient.add("jalapeno ranch");
            quant.add(0.305);

            ingredient.add("red sauce");
            quant.add(0.215);

            ingredient.add("black olives");
            quant.add(0.315);

            ingredient.add("lime");
            quant.add(0.119);

            ingredient.add("italian dressing");
            quant.add(0.109);

            ingredient.add("small tortilla");
            quant.add(3.0);

            ingredient.add("paper bags");
            quant.add(1.0);

            ingredient.add("aluminum foil");
            quant.add(3.0);

            ingredient.add("gloves");
            quant.add(1.0);

            ingredient.add("lime juice");
            quant.add(0.0980);

            ingredient.add("ground beef");
            quant.add(0.355);

            String item = "Beef Taco";
            double price = 8.79;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == burrito_chicken_button) {
            ingredient.add("black beans");
            quant.add(0.175);

            ingredient.add("spring mix");
            quant.add(0.127);

            ingredient.add("mozzarella cheese");
            quant.add(0.1005);

            ingredient.add("pico de gallo");
            quant.add(0.09375);

            ingredient.add("onions");
            quant.add(0.1875);

            ingredient.add("jalapeno peppers");
            quant.add(0.09924);

            ingredient.add("cilantro");
            quant.add(0.08724);

            ingredient.add("sour cream");
            quant.add(0.155);

            ingredient.add("ranch");
            quant.add(0.105);

            ingredient.add("chipotle sauce");
            quant.add(0.115);

            ingredient.add("black olives");
            quant.add(0.115);

            ingredient.add("lime");
            quant.add(0.089);

            ingredient.add("italian dressing");
            quant.add(0.109);

            ingredient.add("large tortilla");
            quant.add(1.0);

            ingredient.add("paper bags");
            quant.add(1.0);

            ingredient.add("aluminum foil");
            quant.add(1.0);

            ingredient.add("gloves");
            quant.add(1.0);

            ingredient.add("lime juice");
            quant.add(0.0980);

            ingredient.add("chicken");
            quant.add(0.155);

            String item = "Chicken Burrito";
            double price = 8.69;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == veggie_taco_button) {
            ingredient.add("pinto beans");
            quant.add(0.375);

            ingredient.add("romaine lettuce");
            quant.add(0.327);

            ingredient.add("mixed cheddar");
            quant.add(0.1875);

            ingredient.add("pico de gallo");
            quant.add(0.09375);

            ingredient.add("onions");
            quant.add(0.1875);

            ingredient.add("jalapeno peppers");
            quant.add(0.09924);

            ingredient.add("cilantro");
            quant.add(0.08724);

            ingredient.add("sour cream");
            quant.add(0.355);

            ingredient.add("jalapeno ranch");
            quant.add(0.305);

            ingredient.add("red sauce");
            quant.add(0.215);

            ingredient.add("black olives");
            quant.add(0.315);

            ingredient.add("lime");
            quant.add(0.119);

            ingredient.add("italian dressing");
            quant.add(0.109);

            ingredient.add("small tortilla");
            quant.add(3.0);

            ingredient.add("paper bags");
            quant.add(1.0);

            ingredient.add("aluminum foil");
            quant.add(3.0);

            ingredient.add("gloves");
            quant.add(1.0);

            ingredient.add("lime juice");
            quant.add(0.0980);

            ingredient.add("fajita vegetables");
            quant.add(0.355);


            String item = "Grilled Vegetable Taco";
            double price = 7.89;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == burrito_beef_button) {
            ingredient.add("black beans");
            quant.add(0.175);

            ingredient.add("spring mix");
            quant.add(0.127);

            ingredient.add("mozzarella cheese");
            quant.add(0.1005);

            ingredient.add("pico de gallo");
            quant.add(0.09375);

            ingredient.add("onions");
            quant.add(0.1875);

            ingredient.add("jalapeno peppers");
            quant.add(0.09924);

            ingredient.add("cilantro");
            quant.add(0.08724);

            ingredient.add("sour cream");
            quant.add(0.155);

            ingredient.add("ranch");
            quant.add(0.105);

            ingredient.add("chipotle sauce");
            quant.add(0.115);

            ingredient.add("black olives");
            quant.add(0.115);

            ingredient.add("lime");
            quant.add(0.089);

            ingredient.add("italian dressing");
            quant.add(0.109);

            ingredient.add("large tortilla");
            quant.add(1.0);

            ingredient.add("paper bags");
            quant.add(1.0);

            ingredient.add("aluminum foil");
            quant.add(1.0);

            ingredient.add("gloves");
            quant.add(1.0);

            ingredient.add("lime juice");
            quant.add(0.0980);

            ingredient.add("ground beef");
            quant.add(0.155);
            String item = "Grilled Vegetable Taco";
            double price = 7.89;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == chips_and_guac) {
            ingredient.add("nacho chips");
            quant.add(0.09375);

            ingredient.add("guacamole");
            quant.add(0.0625);

            ingredient.add("sauce containers");
            quant.add(2.0);

            String item = "Chips-and-Guac";
            double price = 3.69;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == chips_and_queso) {
            ingredient.add("nacho chips");
            quant.add(0.09375);

            ingredient.add("queso");
            quant.add(0.0625);

            ingredient.add("sauce containers");
            quant.add(2.0);

            String item = "Chips-and-Queso";
            double price = 3.49;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == chips_and_salsa) {
            ingredient.add("nacho chips");
            quant.add(0.09375);

            ingredient.add("corn salsa");
            quant.add(0.0625);

            ingredient.add("salse verde");
            quant.add(0.0625);

            ingredient.add("sauce containers");
            quant.add(2.0);
            String item = "Chips-and-Salsa";
            double price = 2.19;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == burrito_veg_button) {
            ingredient.add("black beans");
            quant.add(0.175);

            ingredient.add("spring mix");
            quant.add(0.127);

            ingredient.add("mozzarella cheese");
            quant.add(0.1005);

            ingredient.add("pico de gallo");
            quant.add(0.09375);

            ingredient.add("onions");
            quant.add(0.1875);

            ingredient.add("jalapeno peppers");
            quant.add(0.09924);

            ingredient.add("cilantro");
            quant.add(0.08724);

            ingredient.add("sour cream");
            quant.add(0.155);

            ingredient.add("ranch");
            quant.add(0.105);

            ingredient.add("chipotle sauce");
            quant.add(0.115);

            ingredient.add("black olives");
            quant.add(0.115);

            ingredient.add("lime");
            quant.add(0.089);

            ingredient.add("italian dressing");
            quant.add(0.109);

            ingredient.add("large tortilla");
            quant.add(1.0);

            ingredient.add("paper bags");
            quant.add(1.0);

            ingredient.add("aluminum foil");
            quant.add(1.0);

            ingredient.add("gloves");
            quant.add(1.0);

            ingredient.add("lime juice");
            quant.add(0.0980);

            ingredient.add("fajita vegetables");
            quant.add(0.155);
            String item = "Grilled Vegetable Burrito";
            double price = 8.29;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == bowl_veg_button) {
            ingredient.add("brown rice");
            quant.add(0.175);

            ingredient.add("white rice");
            quant.add(0.175);

            ingredient.add("iceburg lettuce");
            quant.add(0.127);

            ingredient.add("mozzarella cheese");
            quant.add(0.1005);

            ingredient.add("mixed cheddar");
            quant.add(0.1005);

            ingredient.add("pico de gallo");
            quant.add(0.09375);

            ingredient.add("onions");
            quant.add(0.1875);

            ingredient.add("jalapeno peppers");
            quant.add(0.09924);

            ingredient.add("cilantro");
            quant.add(0.08724);

            ingredient.add("sour cream");
            quant.add(0.155);

            ingredient.add("ranch");
            quant.add(0.105);

            ingredient.add("chipotle sauce");
            quant.add(0.115);

            ingredient.add("black olives");
            quant.add(0.115);

            ingredient.add("lime");
            quant.add(0.089);

            ingredient.add("italian dressing");
            quant.add(0.109);

            ingredient.add("cardboard bowls + tops");
            quant.add(1.0);

            ingredient.add("paper bags");
            quant.add(1.0);

            ingredient.add("gloves");
            quant.add(1.0);

            ingredient.add("bags of cutlery");
            quant.add(1.0);

            ingredient.add("lime juice");
            quant.add(0.0980);

            ingredient.add("fajita vegetables");
            quant.add(0.155);
            String item = "Grilled Vegetable Bowl";
            double price = 8.29;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == bowl_beef_button) {
            ingredient.add("brown rice");
            quant.add(0.175);

            ingredient.add("white rice");
            quant.add(0.175);

            ingredient.add("iceburg lettuce");
            quant.add(0.127);

            ingredient.add("mozzarella cheese");
            quant.add(0.1005);

            ingredient.add("mixed cheddar");
            quant.add(0.1005);

            ingredient.add("pico de gallo");
            quant.add(0.09375);

            ingredient.add("onions");
            quant.add(0.1875);

            ingredient.add("jalapeno peppers");
            quant.add(0.09924);

            ingredient.add("cilantro");
            quant.add(0.08724);

            ingredient.add("sour cream");
            quant.add(0.155);

            ingredient.add("ranch");
            quant.add(0.105);

            ingredient.add("chipotle sauce");
            quant.add(0.115);

            ingredient.add("black olives");
            quant.add(0.115);

            ingredient.add("lime");
            quant.add(0.089);

            ingredient.add("italian dressing");
            quant.add(0.109);

            ingredient.add("cardboard bowls + tops");
            quant.add(1.0);

            ingredient.add("paper bags");
            quant.add(1.0);

            ingredient.add("gloves");
            quant.add(1.0);

            ingredient.add("bags of cutlery");
            quant.add(1.0);

            ingredient.add("lime juice");
            quant.add(0.0980);

            ingredient.add("ground beef");
            quant.add(0.155);


            String item = "Beef Bowl";
            double price = 8.99;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == bowl_steak_button) {
            ingredient.add("brown rice");
            quant.add(0.175);

            ingredient.add("white rice");
            quant.add(0.175);

            ingredient.add("iceburg lettuce");
            quant.add(0.127);

            ingredient.add("mozzarella cheese");
            quant.add(0.1005);

            ingredient.add("mixed cheddar");
            quant.add(0.1005);

            ingredient.add("pico de gallo");
            quant.add(0.09375);

            ingredient.add("onions");
            quant.add(0.1875);

            ingredient.add("jalapeno peppers");
            quant.add(0.09924);

            ingredient.add("cilantro");
            quant.add(0.08724);

            ingredient.add("sour cream");
            quant.add(0.155);

            ingredient.add("ranch");
            quant.add(0.105);

            ingredient.add("chipotle sauce");
            quant.add(0.115);

            ingredient.add("black olives");
            quant.add(0.115);

            ingredient.add("lime");
            quant.add(0.089);

            ingredient.add("italian dressing");
            quant.add(0.109);

            ingredient.add("cardboard bowls + tops");
            quant.add(1.0);

            ingredient.add("paper bags");
            quant.add(1.0);

            ingredient.add("gloves");
            quant.add(1.0);

            ingredient.add("bags of cutlery");
            quant.add(1.0);

            ingredient.add("lime juice");
            quant.add(0.0980);

            ingredient.add("steak");
            quant.add(0.155);
            String item = "Marinated Steak Bowl";
            double price = 9.19;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == bowl_chicken_button) {
            ingredient.add("brown rice");
            quant.add(0.175);

            ingredient.add("white rice");
            quant.add(0.175);

            ingredient.add("iceburg lettuce");
            quant.add(0.127);

            ingredient.add("mozzarella cheese");
            quant.add(0.1005);

            ingredient.add("mixed cheddar");
            quant.add(0.1005);

            ingredient.add("pico de gallo");
            quant.add(0.09375);

            ingredient.add("onions");
            quant.add(0.1875);

            ingredient.add("jalapeno peppers");
            quant.add(0.09924);

            ingredient.add("cilantro");
            quant.add(0.08724);

            ingredient.add("sour cream");
            quant.add(0.155);

            ingredient.add("ranch");
            quant.add(0.105);

            ingredient.add("chipotle sauce");
            quant.add(0.115);

            ingredient.add("black olives");
            quant.add(0.115);

            ingredient.add("lime");
            quant.add(0.089);

            ingredient.add("italian dressing");
            quant.add(0.109);

            ingredient.add("cardboard bowls + tops");
            quant.add(1.0);

            ingredient.add("paper bags");
            quant.add(1.0);

            ingredient.add("gloves");
            quant.add(1.0);

            ingredient.add("bags of cutlery");
            quant.add(1.0);

            ingredient.add("lime juice");
            quant.add(0.0980);

            ingredient.add("chicken");
            quant.add(0.155);
            String item = "Chicken Bowl";
            double price = 8.69;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == rainbow) {
            String item = "Eat-the-rainbow";
            double price = 20;
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);
        } else if (e.getSource() == custom) {
            
            String item = "Custom";
            double price = 10.00; // up to you
            total += price;
            itemList += item + ", ";
            String receiptLine = String.format("%s\t$%.2f\n", item, price);
            String total_p = String.format("%.2f", total);
            total_price.setText("Total price: $" + total_p);
            receiptArea.append(receiptLine);

            new Custom(this::onUpdate);
            // !HAVE ONE FOR EAT THE RAINBOW OPTION!
        } else if (e.getSource() == checkoutButton) {
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

            for(int i = 0; i < ingredient.size(); i++) {

                updateInventory(quant.get(i), ingredient.get(i));

            }

            for(int i = 0; i < custom_list.size(); i++) {

                for(int j = 1; j < custom_list.get(i).size(); j++) {

                    if(custom_list.get(i).get(j).equals("bowl") || custom_list.get(i).get(j).equals("burrito") || custom_list.get(i).get(j).equals("tacos")) {

                        j++;
                    }

                    Double custom_stock = 0.0;


                    if(custom_list.get(i).get(j).equals("brown rice")) {
                        custom_stock = 0.175;
                    }

                    if(custom_list.get(i).get(j).equals("white rice")) {
                        custom_stock = 0.175;

                    }

                    if(custom_list.get(i).get(j).equals("mozzarella cheese")) {
                        custom_stock = 0.1005;
                    }

                    if(custom_list.get(i).get(j).equals("mixed cheddar")) {
                        custom_stock = 0.1005;
                    }

                    if(custom_list.get(i).get(j).equals("pico de gallo")) {
                        custom_stock = 0.09375;
                    }

                    if(custom_list.get(i).get(j).equals("onions")) {
                        custom_stock = 0.1875;
                    }

                    if(custom_list.get(i).get(j).equals("jalapeno peppers")) {
                        custom_stock = 0.09924;
                    }

                    if(custom_list.get(i).get(j).equals("cilantro")) {
                        custom_stock = 0.08724;
                    }

                    if (custom_list.get(i).get(j).equals("sour cream")){
                        custom_stock = .155;
                    } 
                    if(custom_list.get(i).get(j).equals("ranch")){
                        custom_stock = .105;
                    } 
                    if(custom_list.get(i).get(j).equals("chipotle sauce")){
                        custom_stock = .115;
                    }
                    if(custom_list.get(i).get(j).equals("black olives")){
                        custom_stock = .115;
                    } 
                    if(custom_list.get(i).get(j).equals("lime")){
                        custom_stock = .089;
                    } 
                    if(custom_list.get(i).get(j).equals("italian dressing")){
                        custom_stock = .109;
                    }

                    if(custom_list.get(i).get(j).equals("lime juice")) {

                        custom_stock = 0.0980;
                    }

                    if(custom_list.get(i).get(j).equals("chicken")) {

                        custom_stock = 0.155;
                    }

                    if(custom_list.get(i).get(j).equals("steak")) {

                        custom_stock = 0.155;
                    }

                    if(custom_list.get(i).get(j).equals("ground beef")) {

                        custom_stock = 0.155;
                    }

                    if(custom_list.get(i).get(j).equals("guacamole")) {

                        custom_stock = 0.105;
                    }
                    
                    if(custom_list.get(i).get(j).equals("queso")) {

                        custom_stock = 0.105;
                    }

                    if(custom_list.get(i).get(j).equals("iceburg lettuce")) {

                        custom_stock = 0.135;
                    }

                    if(custom_list.get(i).get(j).equals("red sauce")) {

                        custom_stock = 0.109;
                    }

                    if(custom_list.get(i).get(j).equals("fajita vegetables")) {

                        custom_stock = 0.122;
                    }

                    if(custom_list.get(i).get(j).equals("corn salsa")) {

                        custom_stock = 0.0989;
                    }

                    if(custom_list.get(i).get(j).equals("salse verde")) {

                        custom_stock = 0.0989;
                    }

                    if(custom_list.get(i).get(j).equals("black beans")) {

                        custom_stock = 0.1002;
                    }

                    if(custom_list.get(i).get(j).equals("spring mix")) {

                        custom_stock = 0.1002;
                    }

                    if(custom_list.get(i).get(j).equals("pinto beans")) {

                        custom_stock = 0.0897;
                    }

                    if(custom_list.get(i).get(j).equals("romaine lettuce")) {

                        custom_stock = 0.1897;
                    }

                    if(custom_list.get(i).get(j).equals("spring mix")) {

                        custom_stock = 0.1897;
                    }

                    updateInventory(custom_stock, custom_list.get(i).get(j));
                    
                }
            }
            
            quant = new Vector<Double>();
            ingredient = new Vector<String>();
            custom_list = new ArrayList<ArrayList<String>>();


        } else if(e.getSource() == cancel) {//
            total = 0;
            total_price.setText("Total price: $0.00");
            receiptArea.setText("");
            employeeID.setText("");
            date.setText("");
            name.setText("");
            itemList = "";
            quant = new Vector<Double>();
            ingredient = new Vector<String>();
            custom_list = new ArrayList<ArrayList<String> >();
        }
    }

    private void onUpdate(ArrayList<String> updatedList) {
        System.out.print("adding, " + updatedList);
        custom_list.add(updatedList);
        System.out.print("all custom orders: " + custom_list);
    }

    public void updateInventory(Double stock, String item) {

        try {
            // 1. Load the JDBC driver
            Class.forName("org.postgresql.Driver");

            // 2. Create a connection to the database
            String url = "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_63";
            String username = "csce315331_team_63_master";
            String password = "WFHD";
            Connection connection = DriverManager.getConnection(url, username, password);

            // 3. Create a statement object
            Statement statement = connection.createStatement();

            // 4. Execute the query
            
            String query2 = "SELECT quantity FROM inventory_table WHERE item='"+item+"';";
            
            ResultSet result = statement.executeQuery(query2);
            

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
            int rowsAffected = statement.executeUpdate(query);
            System.out.println("Rows affected: " + rowsAffected);

            // 5. Close the resources
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e2) {
            System.out.println("JDBC driver not found");
        } catch (SQLException e2) {
            System.out.println("Database connection error: " + e2.getMessage());
        }
    }
 
}