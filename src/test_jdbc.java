import java.sql.*;

public class test_jdbc {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded ✅");
        } catch (Exception e) {
            System.out.println("Error ❌");
            e.printStackTrace();
        }
    }
}
