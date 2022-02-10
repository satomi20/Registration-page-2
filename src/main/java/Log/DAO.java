package Log;

import java.sql.*;

public class DAO {

    public static boolean login(String email, String password){
        boolean status=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/daniel", "root", "VanDarkholme1488");

            String query = "select * from users where email=? and password=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs=ps.executeQuery();
            status=rs.next();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return status;

    }
    public static boolean saveUser(User user){
        boolean set = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/daniel", "root", "VanDarkholme1488");
            String query = "insert into users(name, email, password) values (?, ?, ?)";
            PreparedStatement pt = connection.prepareStatement(query);
            pt.setString(1, user.getName());
            pt.setString(2, user.getEmail());
            pt.setString(3, user.getPassword());

            pt.executeUpdate();
            set = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    public static boolean isAdmin(String email, String password) {

        boolean check = false;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/daniel", "root", "VanDarkholme1488");
            pst = connection.prepareStatement("SELECT * FROM users WHERE email =? and password =? and role='admin';");
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            check = rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check;
    }

}
