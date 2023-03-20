import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class Custom extends JFrame {

    // initialize window and overall panel
    private JFrame frame;
    private JPanel panel;

    // initialize interactive parts
    ArrayList<JCheckBox> styles = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> bases = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> proteins = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> vegetables = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> toppings = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> extras = new ArrayList<JCheckBox>();

    private JButton addButton, cancelButton;

    // list of strings that will keep track of selected items
    public ArrayList<String> to_return = new ArrayList<String>();
    private UpdateListener updateListener;

    public interface UpdateListener {
        void onUpdate(ArrayList<String> updatedList);
    }

    public Custom(UpdateListener updateListener) { // default constructor 

        // set close operation
        

        // window setup
        frame = new JFrame("Order");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);

        // this panel will hold each of the ingredient panels
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));
        panel.setSize(500, 600);

        // style options
        JPanel stylePanel = new JPanel();
        stylePanel.setBorder(BorderFactory.createTitledBorder("Style"));
        styles.add(new JCheckBox("Bowl"));
        styles.add(new JCheckBox("Burrito"));
        styles.add(new JCheckBox("Tacos"));
        for (JCheckBox style : styles) {
            stylePanel.add(style);
        }
        stylePanel.revalidate();
        stylePanel.repaint();
        panel.add(stylePanel);

        // base options
        JPanel basePanel = new JPanel();
        basePanel.setBorder(BorderFactory.createTitledBorder("Base"));
        bases.add(new JCheckBox("White Rice"));
        bases.add(new JCheckBox("Brown Rice"));
        bases.add(new JCheckBox("Pinto Beans"));
        bases.add(new JCheckBox("Black Beans"));
        for (JCheckBox base : bases) {
            basePanel.add(base);
        }
        basePanel.revalidate();
        basePanel.repaint();
        panel.add(basePanel);

        // protein options
        JPanel proteinPanel = new JPanel();
        proteinPanel.setBorder(BorderFactory.createTitledBorder("Protein"));
        proteins.add(new JCheckBox("Chicken"));
        proteins.add(new JCheckBox("Ground Beef"));
        proteins.add(new JCheckBox("Steak"));
        proteins.add(new JCheckBox("Veggie"));
        for (JCheckBox protein : proteins) {
            proteinPanel.add(protein);
        }
        proteinPanel.revalidate();
        proteinPanel.repaint();
        panel.add(proteinPanel);

        // vegetable options
        JPanel vegetablePanel = new JPanel();
        vegetablePanel.setBorder(BorderFactory.createTitledBorder("Vegetables"));
        vegetables.add(new JCheckBox("Spring Mix"));
        vegetables.add(new JCheckBox("Romaine Lettuce"));
        vegetables.add(new JCheckBox("Iceburg Lettuce"));
        for (JCheckBox vegetable : vegetables) {
            vegetablePanel.add(vegetable);
        }
        vegetablePanel.revalidate();
        vegetablePanel.repaint();
        panel.add(vegetablePanel);

        // topping options
        JPanel toppingPanel = new JPanel();
        toppingPanel.setBorder(BorderFactory.createTitledBorder("Toppings"));
        toppings.add(new JCheckBox("Mozzarella Cheese"));
        toppings.add(new JCheckBox("Mixed Cheese"));
        toppings.add(new JCheckBox("Corn Salsa"));
        toppings.add(new JCheckBox("Pico de Gallo"));
        toppings.add(new JCheckBox("Onions"));
        toppings.add(new JCheckBox("Jalapeno Peppers"));
        toppings.add(new JCheckBox("Black Olives"));
        toppings.add(new JCheckBox("Cilantro"));
        toppings.add(new JCheckBox("Sour Cream"));
        toppings.add(new JCheckBox("Red Sauce"));
        toppings.add(new JCheckBox("Salsa Verde"));
        toppings.add(new JCheckBox("Ranch"));
        toppings.add(new JCheckBox("Jalapeno Ranch"));
        toppings.add(new JCheckBox("Chipotle Sauce"));
        toppings.add(new JCheckBox("Italian Dressing"));
        toppings.add(new JCheckBox("Lime Juice"));
        toppings.add(new JCheckBox("Lime"));
        for (JCheckBox topping : toppings) {
            toppingPanel.add(topping);
        }
        toppingPanel.revalidate();
        toppingPanel.repaint();
        panel.add(toppingPanel);

        // extra options
        JPanel extraPanel = new JPanel();
        extraPanel.setBorder(BorderFactory.createTitledBorder("Extras"));
        extras.add(new JCheckBox("Guacamole"));
        extras.add(new JCheckBox("Queso"));
        for (JCheckBox extra : extras) {
            extraPanel.add(extra);
        }
        extraPanel.revalidate();
        extraPanel.repaint();
        panel.add(extraPanel);

        addButton = new JButton("Add to Order");
        cancelButton = new JButton("Cancel Order");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



addButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

        to_return.add("Custom");

        // loop through each list of buttons, push selected items to string
        for(JCheckBox style : styles) {
            if (style.isSelected()) {
                to_return.add(style.getText());
            }
        }

        for(JCheckBox base : bases) {
            if (base.isSelected()) {
                to_return.add(base.getText());
            }
        }

        for(JCheckBox protein : proteins) {
            if (protein.isSelected()) {
                to_return.add(protein.getText());
            }
        }

        for(JCheckBox vegetable : vegetables) {
            if (vegetable.isSelected()) {
                to_return.add(vegetable.getText());
            }
        }

        for(JCheckBox topping : toppings) {
            if (topping.isSelected()) {
                to_return.add(topping.getText());
            }
        }

        for(JCheckBox extra : extras) {
            if (extra.isSelected()) {
                to_return.add(extra.getText());
            }
        }

        updateListener.onUpdate(to_return);
        frame.dispose();
    }
}

);

panel.add(addButton);
panel.revalidate();
panel.repaint();
frame.add(panel);
frame.setVisible(true);
}
}