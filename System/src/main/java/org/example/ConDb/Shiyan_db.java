package org.example.ConDb;

import org.example.Other_class.Shiyan;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shiyan_db {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/EXPERIMENT";
    private static final String USER = "root";
    private static final String PASSWORD = "q3231423581";
    private List<Shiyan> shiyan_list=new ArrayList<>();
    Score_db score_db=new Score_db();
    public HashMap<String,String> complete_list=score_db.getComplete_list();
    List<String> one_teacher_test=new ArrayList<>();

    public Shiyan_db() {
        con();
        true_complete_count();
    }

    private void con() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM test;";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String title=resultSet.getString("title");
                String start_time=resultSet.getString("start_time");
                String end_time=resultSet.getString("end_time");
                String id=resultSet.getString("id_test");
                String tid=resultSet.getString("tid");
                int complete_count=resultSet.getInt("count_complete");
                Shiyan s=new Shiyan(id,title,start_time,end_time,complete_count,tid);
                shiyan_list.add(s);
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
    //******************************************WXY********************************************
    public void add(Shiyan experiment) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stem = conn.createStatement();
        stem.executeUpdate("insert into test values('"+experiment.getId()+"','"+experiment.getTitle()+"','"+experiment.getStart_time()+"','"+experiment.getEnd_time()+"',0,'"+experiment.getTeacher()+"')");
        try {
            if (conn != null) {
                conn.close();
                System.out.println("数据库连接已关闭。");
            }
        } catch (SQLException e) {
            System.out.println("关闭连接时出错: " + e.getMessage());
        }
    }

    public void delete(String id_test) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stem = conn.createStatement();
        stem.executeUpdate("delete from test where id_test =  "+ id_test+" ");
        try {
            if (conn != null) {
                conn.close();
                System.out.println("数据库连接已关闭。");
            }
        } catch (SQLException e) {
            System.out.println("关闭连接时出错: " + e.getMessage());
        }
    }
//************************************************************************************************
    public List<Shiyan> getShiyan_list() {
        return shiyan_list;
    }


    public void shiyan_complete_count(String id_test,String complete_count){
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql="update test set count_complete="+complete_count+" where id_test='"+id_test+"';";
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

    public void true_complete_count() {
        try{
            for (HashMap.Entry<String, String> entry : complete_list.entrySet()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
                shiyan_complete_count(entry.getKey(),entry.getValue());
            }
        } finally {
            System.out.println("完成人数计算失败");
        }
    }

    public void teacher_shiyan(String tid){
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT id_test FROM test WHERE tid='"+tid+"';";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id_test"));
                one_teacher_test.add(resultSet.getString("id_test"));
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
    public static void main(String[] args) {
        Shiyan_db s=new Shiyan_db();
//        s.shiyan_complete_count("124","2");
        s.teacher_shiyan("T20050520");

    }
}
