import javax.swing.*;
public class customer_dashboard extends JFrame{
    JLabel l;
    admin_dashboard(){
        l = new JLabel("Customer Dashboard");
        setLayout(new FlowLayout());
        add(l);
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
