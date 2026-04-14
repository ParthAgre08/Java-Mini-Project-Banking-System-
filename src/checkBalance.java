import javax.swing.*;
import java.awt.*;

public class checkBalance extends JFrame {

    JLabel l1, l2;
    JButton b;

    checkBalance(String username ,double balance) {

        l1 = new JLabel("Your Current Balance:");
        l2 = new JLabel("₹ " + balance);   // 🔥 directly use passed value
        b = new JButton("Back");

        setLayout(new FlowLayout());

        add(l1);
        add(l2);
        add(b);

        // 🔙 BACK BUTTON
        b.addActionListener(e -> {
            new customer_dashboard(username);
            dispose();
        });

        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}