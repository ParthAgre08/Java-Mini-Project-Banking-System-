import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class depositMoney extends JFrame {

    JTextField j1;
    JButton b;

    depositMoney(String username,Double balance , String Account_type) {

        JLabel l = new JLabel("Enter Amount to Deposit:");
        j1 = new JTextField(20);
        b = new JButton("Deposit");

        setLayout(new FlowLayout());

        add(l);
        add(j1);
        add(b);

        b.addActionListener(e -> {
            try {
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/bank",
                        "root",
                        "123456789"
                );

                String query = "UPDATE users SET balance = balance + ? WHERE username=?";
                PreparedStatement ps = con.prepareStatement(query);

                ps.setDouble(1, Double.parseDouble(j1.getText()));
                ps.setString(2, username);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Money Deposited");

                new customer_dashboard(username);
                dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setSize(300,200);
        setVisible(true);
    }
}