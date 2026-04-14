import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Home extends JFrame{
    JButton b1,b2;
    JLabel l1,l2;

    Home(){

        b1 = new JButton("Customer Login");
        b2 = new JButton("Admin Login");

        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                new customer_register_login();
                dispose();
            }
        });
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new admin_login();
                dispose();
            }
        });
    }
}
