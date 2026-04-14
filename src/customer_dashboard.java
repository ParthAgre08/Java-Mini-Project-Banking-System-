import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class customer_dashboard extends JFrame{
    JLabel l,l1, l2, l3;
    JButton b1, b2, b3, b4;

    String username;
    String account_type;
    double balance;

    customer_dashboard(String username){
        l = new JLabel("Customer Dashboard");
        this.username = username;

        l1 = new JLabel();
        l2 = new JLabel();
        l3 = new JLabel();

        b1 = new JButton("Check Balance");
        b2 = new JButton("Add Money");
        b3 = new JButton("Withdraw Money");
        b4 = new JButton("Logout");

        setLayout(new FlowLayout());
        add(l);
        add(l1);
        add(l2);
        add(l3);

        add(b1);
        add(b2);
        add(b3);
        add(b4);


        //  FETCH DATA FROM DATABASE
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bank",
                    "root",
                    "123456789"
            );

            String query = "SELECT account_type, balance FROM users WHERE username=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                account_type = rs.getString("account_type");
                balance = rs.getDouble("balance");

                // SET DATA TO LABELS
                l1.setText("Hello, " + username);
                l2.setText("Account Type: " + account_type);
                l3.setText("Balance: ₹" + balance);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Button Actions

        b1.addActionListener(e -> {
            new checkBalance(username,balance);
            dispose();
        });

        b2.addActionListener(e -> {
            new depositMoney(username,balance,account_type);
            dispose();
        });

        b3.addActionListener(e -> {
            new withdrawMoney(username,balance,account_type);
            dispose();
        });

        b4.addActionListener(e -> {
            new customer_register_login();
            dispose();
        });

        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
