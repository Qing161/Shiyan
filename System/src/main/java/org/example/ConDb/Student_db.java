package org.example.ConDb;

import org.example.people_class.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Student_db {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/EXPERIMENT";
    private static final String USER = "root";
    private static final String PASSWORD = "q3231423581";
    private List<Student> student_list=new ArrayList<>();

    public Student_db() {
        con();
    }

    private void con() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM student;";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
//                System.out.println(resultSet.getString("sid"));
//                System.out.println(resultSet.getString("sname"));
//                System.out.println(resultSet.getString("sage"));
//                System.out.println(resultSet.getString("ssex"));

                String sid=resultSet.getString("sid");
                String sname=resultSet.getString("sname");
                int sage=resultSet.getInt("sage");
                char ssex=resultSet.getString("ssex").charAt(0);
                String sdept=resultSet.getString("sdept");
                String password=resultSet.getString("psw");
                Student s=new Student(sid,sname,sage,ssex,sdept,password);
                student_list.add(s);
            }


        } catch (ClassNotFoundException e) {
            System.out.println("JDBC驱动未找到: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("数据库连接失败: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("数据库连接已关闭。");
                }
            } catch (SQLException e) {
                System.out.println("关闭连接时出错: " + e.getMessage());
            }
        }
    }

    public boolean Login(String username, String password) {
        Connection connection = null;
        try {
            // 加载JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 创建连接
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT *FROM student;";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (resultSet.getString("sid").equals(username)) {
                    if(resultSet.getString("psw").equals(password)){
                        return true;
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC驱动未找到: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("数据库连接失败: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("数据库连接已关闭。");
                }
            } catch (SQLException e) {
                System.out.println("关闭连接时出错: " + e.getMessage());
            }
        }
        return false;
    }

    public void change(String id,String name,String sex,int age){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            String sql = "SELECT * FROM student;";
            String sql="UPDATE student set sname='"+name+"' , ssex='"+sex+"' , sage='"+age+"' where sid='"+id+"';";
            Statement statement = connection.createStatement();

            int resultSet = statement.executeUpdate(sql);


        } catch (ClassNotFoundException e) {
            System.out.println("JDBC驱动未找到: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("数据库连接失败: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("数据库连接已关闭。");
                }
            } catch (SQLException e) {
                System.out.println("关闭连接时出错: " + e.getMessage());
            }
        }
    }
    public void change_main(String id,String new_id,String name,String sex,int age,String dept){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            String sql = "SELECT * FROM student;";
            String sql="UPDATE student set sname='"+name+"' , ssex='"+sex+"' , sage='"+age+"',sdept='"+dept+"',sid='"+new_id+"' where sid='"+id+"';";
            Statement statement = connection.createStatement();

            int resultSet = statement.executeUpdate(sql);


        } catch (ClassNotFoundException e) {
            System.out.println("JDBC驱动未找到: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("数据库连接失败: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("数据库连接已关闭。");
                }
            } catch (SQLException e) {
                System.out.println("关闭连接时出错: " + e.getMessage());
            }
        }
    }

    public List<Student> getstudent_list() {
        return student_list;
    }

//    public static void main(String[] args) {
//        Student_db s=new Student_db();
//        s.change("2320","hhh","h",13);
//    }
}
