import javax.swing.*;
import java.awt.event.*;

/*
    This class represents a home page GUI that contains buttons to open
    different windows for the user to interact with.
    @author - work-from-home-dads
*/

public class HomePage extends JFrame {

    /**
     * Constructor that creates a home page GUI with two buttons.
     * One button opens a server window, and the other opens a manager window.
     */
    public HomePage() {
        super("Home Page");
        setSize(300, 200);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton serverButton = new JButton("Server");
        
        serverButton.addActionListener(new ActionListener() {
            /**
             * Method that creates an instance of order when the 
             * server button is pressed. Sets the screen visibility to 
             * true.
             * @param e - triggers action on server side
             */
            public void actionPerformed(ActionEvent e) {
                System.out.println("Da Server button triggers instance of a lovely order screen window by Daniel and Connor");
                Order2 _order = new Order2();
                // _order.server();


                // _order.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                // _order.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                _order.setVisible(true);
                // _order.server();
                // _order.ManagerDashboard();
            }
        });

        JButton managerButton = new JButton("Manager");
        managerButton.addActionListener(new ActionListener() {
            /**
             * Create an instance of managerFile when the manager 
             * button is pressed on the main screen.
             * Also calls the managerDashboard function
             * 
             * @param e - triggers action on server side
             */
            public void actionPerformed(ActionEvent e) {
                System.out.println("Server button triggers instance of a lovely manager window by Ravish");
                
                ManagerFile file = new ManagerFile();
                // file.setVisible(true);
                file.ManagerDashboard();
            }
        });

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
