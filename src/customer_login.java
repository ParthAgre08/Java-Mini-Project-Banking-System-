import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class customer_login extends JFrame {
    JLabel l1, l2, l3, l4, l5;
    JTextField j1, j2;
    JButton b;
    String username;

    customer_login() {
        l1 = new JLabel("Customer Login \n\n");
        l2 = new JLabel("Enter Username :- ");
        j1 = new JTextField(20);
        username = j1.getText();
        l3 = new JLabel("Enter Password :- ");
        j2 = new JTextField(20);
        b = new JButton("\n\nClick to Login");
        l4 = new JLabel("");


        setLayout(new FlowLayout());
        add(l1);
        add(l2);
        add(j1);
        add(l3);
        add(j2);
        add(b);
        add(l4);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    //Load Driver
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    //Create Connection
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/bank",
                            "root",
                            "123456789"
                    );

                    String query = "SELECT * FROM users WHERE username=? AND password=?";
                    PreparedStatement ps = con.prepareStatement(query);

                    ps.setString(1, j1.getText());
                    ps.setString(2, j2.getText());

                    //The result is stored in rs
                    //Think of ResultSet like a table returned from database
                    ResultSet rs = ps.executeQuery();

/*
                [ BEFORE FIRST ROW ]  →  Row1 → Row2 → Row3
                           ↑
                         pointer
                         */

                    /*  rs.next()

                    Moves pointer forward by 1 row
                    Returns:
                    true → if row exists
                    false → if no row
                        */
                    if (rs.next()) {
                        System.out.println("Login Successful");
                        new customer_dashboard(username);
                        dispose();
                    } else {
                        System.out.println("Invalid Credentials");
                        l4.setText("Inavalid Credentials please Re-Login");
                    }

                    rs.close();
                    ps.close();
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
