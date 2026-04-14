import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class customer_register_login extends JFrame {
    JLabel l ;
    JButton b1,b2;

    customer_register_login(){
        b1 = new JButton("Register");
        b2 = new JButton("Login");
        setLayout(new FlowLayout());
        add(b1);
        add(b2);

        b1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new customer_register();
                dispose();
            }
        });

        b2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               new customer_login();
               dispose();
            }
        });

        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
