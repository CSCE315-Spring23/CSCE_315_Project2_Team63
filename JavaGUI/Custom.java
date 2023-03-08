import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class Custom {
    private JFrame frame;
    private JPanel panel;
    ArrayList<JCheckBox> styles = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> bases = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> proteins = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> vegetables = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> toppings = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> extras = new ArrayList<JCheckBox>();

    //private arrayList(JCheckBox);
    private JButton addButton;

    public ArrayList<String> to_return = new ArrayList<String>();

    public Custom() {
        frame = new JFrame("Order");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JPanel stylePanel = new JPanel();
        stylePanel.setBorder(BorderFactory.createTitledBorder("Style"));
        styles.add(new JCheckBox("Bowl"));
        styles.add(new JCheckBox("Burrito"));
        styles.add(new JCheckBox("Tacos"));
        for (JCheckBox style : styles) {
            stylePanel.add(style);
        }
        panel.add(stylePanel);

        JPanel basePanel = new JPanel();
        basePanel.setBorder(BorderFactory.createTitledBorder("Base"));
        bases.add(new JCheckBox("white rice"));
        bases.add(new JCheckBox("brown rice"));
        bases.add(new JCheckBox("pinto beans"));
        bases.add(new JCheckBox("black beans"));
        for (JCheckBox base : bases) {
            basePanel.add(base);
        }
        panel.add(basePanel);

        JPanel proteinPanel = new JPanel();
        proteinPanel.setBorder(BorderFactory.createTitledBorder("Protein"));
        proteins.add(new JCheckBox("chicken"));
        proteins.add(new JCheckBox("ground beef"));
        proteins.add(new JCheckBox("steak"));
        proteins.add(new JCheckBox("veggie"));
        for (JCheckBox protein : proteins) {
            proteinPanel.add(protein);
        }
        panel.add(proteinPanel);

        JPanel vegetablePanel = new JPanel();
        vegetablePanel.setBorder(BorderFactory.createTitledBorder("Vegetables"));
        vegetables.add(new JCheckBox("spring mix"));
        vegetables.add(new JCheckBox("romaine lettuce"));
        vegetables.add(new JCheckBox("iceburg lettuce"));
        for (JCheckBox vegetable : vegetables) {
            vegetablePanel.add(vegetable);
        }
        panel.add(vegetablePanel);

        JPanel toppingPanel = new JPanel();
        toppingPanel.setBorder(BorderFactory.createTitledBorder("Toppings"));
        toppings.add(new JCheckBox("mozzarella cheese"));
        toppings.add(new JCheckBox("mixed cheese"));
        toppings.add(new JCheckBox("corn salsa"));
        toppings.add(new JCheckBox("pico de gallo"));
        toppings.add(new JCheckBox("onions"));
        toppings.add(new JCheckBox("jalapeno peppers"));
        toppings.add(new JCheckBox("black olives"));
        toppings.add(new JCheckBox("cilantro"));
        toppings.add(new JCheckBox("sour cream"));
        toppings.add(new JCheckBox("red sauce"));
        toppings.add(new JCheckBox("salsa verde"));
        toppings.add(new JCheckBox("ranch"));
        toppings.add(new JCheckBox("jalapeno ranch"));
        toppings.add(new JCheckBox("chipotle sauce"));
        toppings.add(new JCheckBox("italian dressing"));
        toppings.add(new JCheckBox("lime juice"));
        toppings.add(new JCheckBox("lime"));
        for (JCheckBox topping : toppings) {
            toppingPanel.add(topping);
        }
        toppingPanel.revalidate();
        toppingPanel.repaint();
        panel.add(toppingPanel);

        JPanel extraPanel = new JPanel();
        extraPanel.setBorder(BorderFactory.createTitledBorder("Extras"));
        extras.add(new JCheckBox("guacamole"));
        extras.add(new JCheckBox("queso"));
        for (JCheckBox extra : extras) {
            extraPanel.add(extra);
        }
        extraPanel.revalidate();
        extraPanel.repaint();
        panel.add(extraPanel);

        addButton = new JButton("Add to Order");

addButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

        to_return.add("Custom");

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

        System.out.print(to_return);
    }
});

panel.add(addButton);
panel.revalidate();
panel.repaint();
frame.add(panel);
frame.setVisible(true);
}

public static void main(String[] args) {
new Custom();
}
}