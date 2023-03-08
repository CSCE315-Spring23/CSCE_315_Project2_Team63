import javax.swing.*;
import java.awt.event.*;

// @author: Ayo Fatoye

public class HomePage extends JFrame {

    public HomePage() {
        super("Home Page");
        setSize(300, 200);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton serverButton = new JButton("Server");
        
        serverButton.addActionListener(new ActionListener() {
            // @param e the ActionEvent object generated when the button is click 
            // when the server button is clicked, create a new Order object and display the Order window.
            public void actionPerformed(ActionEvent e) {
                System.out.println("Da Server button triggers instance of a lovely order screen window by Daniel and Connor");
                Order _order = new Order();
                // _order.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                // _order.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                _order.setVisible(true);
                _order.server();
                // _order.ManagerDashboard();
            }
        });

        JButton managerButton = new JButton("Manager");
        managerButton.addActionListener(new ActionListener() {
            // @param e the ActionEvent object generate when the button is clicked 
            public void actionPerformed(ActionEvent e) {
                System.out.println("Server button triggers instance of a lovely manager window by Ravish");
                
                ManagerFile file = new ManagerFile();
                // file.setVisible(true);
                file.ManagerDashboard();
            }
        });
        // add the buttons to the content pane
        JPanel contentPane = new JPanel();
        contentPane.add(serverButton);
        contentPane.add(managerButton);

        setContentPane(contentPane);
    }

    // 
    // public static void main(String[] args) {
    //     HomePage homePage = new HomePage();
    //     homePage.setVisible(true);
    // }
}
