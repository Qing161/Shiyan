package org.example.ConDb;

import org.example.people_class.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Teacher_db {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/EXPERIMENT";
    private static final String USER = "root";
    private static final String PASSWORD = "q3231423581";

    private List<Teacher> teacher_list=new ArrayList<>();

    public Teacher_db() {
        con();
    }

    private void con() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM teacher;";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
//                System.out.println(resultSet.getString("tid"));
//                System.out.println(resultSet.getString("tname"));
//                System.out.println(resultSet.getString("tage"));
//                System.out.println(resultSet.getString("tsex"));

                String sid=resultSet.getString("tid");
                String sname=resultSet.getString("tname");
                int sage=resultSet.getInt("tage");
                char ssex=resultSet.getString("tsex").charAt(0);

                String password=resultSet.getString("psw");
                Teacher s=new Teacher(sid,sname,sage,ssex,password);
                teacher_list.add(s);
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
            String sql = "SELECT *FROM teacher;";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (resultSet.getString("tid").equals(username)) {
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

    public List<Teacher> getTeacher_list() {
        return teacher_list;
    }
    
}
