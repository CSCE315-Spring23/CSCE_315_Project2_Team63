package CSCE_315_Project2_Team63.JavaGUI;
import java.sql.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// implements ActionListener
public class Order extends JFrame {
    // main
    public static void main(String[] args){
        // Frame and panel for button layout
        JFrame frame = new JFrame("Order History");
        JPanel panel = new JPanel(new FlowLayout());

        // Buttons for each menu item
        JButton bowls = new JButton("Bowls");
        JButton burritos = new JButton("Burritos");
        JButton tacos = new JButton("Tacos");
        JButton sides_and_drinks = new JButton("Sides&Drinks");

        // Add buttons to panel for layout purposes
        panel.add(bowls);
        panel.add(burritos);
        panel.add(tacos);
        panel.add(sides_and_drinks);

        // Close button to terminate the program
        JButton close = new JButton("Close");
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.dispose(); //closes window
                System.exit(0); //terminates program
            }   
        });

        panel.add(close);

        // Add frame to panel to show on screen
        frame.add(panel);
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
