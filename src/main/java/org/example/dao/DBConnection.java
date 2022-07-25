package org.example.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



// كلاس الاتصال
public class DBConnection {
    private static final String HOST = "localhost";
    // الهوست المحلي الافتراضي
    private static final int PORT = 3306;
    // بورت معترف عليه في الداتا بيس
    private static final String DB_NAME = "jdbcayham";
    //اسم قاعدة البيانات
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    // باي ديفولت القيم الافتراضية
    private static Connection connection;
    //متغير الاتصال وهو كلاس جاهز من جافا

    public static Connection getConnection() {
        try {
            // الوضع الاصلي للمتيريات تبع ال فورمات   jdbc:mysql://localhost:3306/jdbcayham
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DB_NAME), USERNAME, PASSWORD);
            // متغير %س نضع قيمته بعد السترينغ و%دو %اس يعني قم بتعويض هذه الرموز وضع مكانها الهوست والبورت والديبي
            //قمنا بلمتغيرات من اجل سهولت تغيير قيمها
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return connection;
    }
}



















//public class DepartmentHandler {
//    private static final String dbUrl = "jdbc:mysql://localhost:3306/jdbcayham";
//    private static final String dbClass = "com.mysql.jdbc.Driver";
//
//
//    Connection con;
//    Statement  stat;
//    String query;
//
//    public void Connect() {
//        System.out.printf("connect()......");
//        try {
//            Class.forName(dbClass).newInstance();
//            con = DriverManager.getConnection(dbUrl,"root","root");
//            stat = con.createStatement();
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }catch (Exception  e){
//            e.printStackTrace();
//        }
//    }
//
//    public  static  Connection get


