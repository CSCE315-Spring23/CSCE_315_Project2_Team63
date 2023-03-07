package JavaGUI.HomePage; //might have to change package for organizations sake

import javax.swing.*;
import java.awt.event.*;

public class HomePage extends JFrame {

    public HomePage() {
        super("Home Page");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton serverButton = new JButton("Server");
        serverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Server button triggers instance of a lovely order screen window by Daniel and Connor");
            }
        });

        JButton managerButton = new JButton("Manager");
        managerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Server button triggers instance of a lovely manager window by Ravish");
            }
        });

        JPanel contentPane = new JPanel();
        contentPane.add(serverButton);
        contentPane.add(managerButton);

        setContentPane(contentPane);
    }

    public static void main(String[] args) {
        HomePage homePage = new HomePage();
        homePage.setVisible(true);
    }
}
