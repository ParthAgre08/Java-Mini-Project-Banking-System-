import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class customer_register extends JFrame {

    /*
    Name
Mobile Number
Email
Address
Account Type (Savings / Current)
Initial Balance
Username
Password
);
    */
    JTextField j1,j2,j3,j4,j5,j6;
    JRadioButton r1,r2;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    JButton b ;
    String account_type ="";
    String username;

    customer_register(){
        l1 = new JLabel("Create Account ");

        l2 = new JLabel("Enter name :- ");
        j1 = new JTextField(20);

        l3 = new JLabel("Enter Mobile number:-  ");
        j2 = new JTextField(20);

        l4 = new JLabel("Enter Email:-  ");
        j3 = new JTextField(20);

        l5 = new JLabel("Select Account Type :- ");
        r1 = new JRadioButton("Saving");
        r2 = new JRadioButton("Current");
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        l6 = new JLabel("Entered Initial Bank Balance Amount :- ");
        j4 = new JTextField(20);

        l7 = new JLabel("Enter username :- ");
        j5 = new JTextField(20);
        username = j5.getText();

        l8 = new JLabel("Enter password :- ");
        j6 = new JTextField(20);

        b = new JButton("Create");

        setLayout(new FlowLayout());
        add(l1);
        add(l2);
        add(j1);
        add(l3);
        add(j2);
        add(l4);
        add(j3);
        add(l5);
        add(r1);
        add(r2);
        add(l6);
        add(j4);
        add(l7);
        add(j5);
        add(l8);
        add(j6);
        add(b);


//        b.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ae){
//                if(r1.isSelected()) {
//                    account_type = "Saving";
//
//                }
//                else if(r2.isSelected()) {
//                    account_type = "Current";
//                }
//            }
//        });


        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(r1.isSelected()) {
                    account_type = "Saving";

                }
                else if(r2.isSelected()) {
                    account_type = "Current";
                }
                try {
                    //Load Driver
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    //Create Connection
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/bank",
                            "root",
                            "123456789"
                    );

                    String query = "INSERT INTO users (name,mobile,email,account_type,balance,username,password) VALUES (?,?,?,?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(query);

                    ps.setString(1, j1.getText());   // name
                    ps.setString(2, j2.getText());   // mobile
                    ps.setString(3, j3.getText());   // email
                    ps.setString(4, account_type);   // account_type
                    ps.setDouble(5, Integer.parseInt(j4.getText()));   // balance
                    ps.setString(6, username);   // username
                    ps.setString(7, j6.getText());   // password


                    //The result is stored in rs
                    //Think of ResultSet like a table returned from database
                    int row = ps.executeUpdate();

                    if (row > 0) {
                        System.out.println("register Successful");
                        new customer_dashboard(username);
                        dispose();
                    } else {
                        System.out.println("Registration Failed ");
                        l4.setText("Registration Failed");
                    }
                    ps.close();
                    con.close();


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            });

        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
