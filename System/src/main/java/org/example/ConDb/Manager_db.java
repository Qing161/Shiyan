package org.example.ConDb;

import java.sql.*;

public class Manager_db {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/EXPERIMENT";
    private static final String USER = "root";
    private static final String PASSWORD = "q3231423581";

    public boolean Login(String username, String password) {
        Connection connection = null;
        try {
            // 加载JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 创建连接
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT *FROM manager;";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (resultSet.getString("mid").equals(username)) {
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
}
