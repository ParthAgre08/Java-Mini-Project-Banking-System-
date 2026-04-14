import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class withdrawMoney extends JFrame {

    JTextField j1;
    JButton b;

    withdrawMoney(String username,Double balance , String Account_type) {

        JLabel l = new JLabel("Enter Amount to Withdraw:");
        j1 = new JTextField(20);
        b = new JButton("Withdraw");

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

                // 🔥 STEP 1: GET CURRENT BALANCE
                String q1 = "SELECT balance FROM users WHERE username=?";
                PreparedStatement ps1 = con.prepareStatement(q1);
                ps1.setString(1, username);

                ResultSet rs = ps1.executeQuery();

                double current_balance = 0;

                if (rs.next()) {
                    current_balance = rs.getDouble("balance");
                }

                // 🔥 STEP 2: GET WITHDRAW AMOUNT
                double withdraw_amount = Double.parseDouble(j1.getText());

                // 🔥 STEP 3: CHECK CONDITION
                if (current_balance >= withdraw_amount) {

                    // ALLOW WITHDRAW
                    String q2 = "UPDATE users SET balance = balance - ? WHERE username=?";
                    PreparedStatement ps2 = con.prepareStatement(q2);

                    ps2.setDouble(1, withdraw_amount);
                    ps2.setString(2, username);

                    ps2.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Withdrawal Successful");

                    new customer_dashboard(username);
                    dispose();

                } else {
                    // ❌ NOT ENOUGH BALANCE
                    JOptionPane.showMessageDialog(null, "Insufficient Balance ❌");
                }

                rs.close();
                ps1.close();
                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}