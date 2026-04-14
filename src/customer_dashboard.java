import javax.swing.*;
import java.awt.*;
public class customer_dashboard extends JFrame{
    JLabel l;
    customer_dashboard(){
        l = new JLabel("Customer Dashboard");
        setLayout(new FlowLayout());
        add(l);
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
