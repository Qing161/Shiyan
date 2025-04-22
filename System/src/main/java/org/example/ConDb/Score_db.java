package org.example.ConDb;

import org.example.Other_class.Score;
import org.example.Other_class.Shiyan;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Score_db {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/EXPERIMENT";
    private static final String USER = "root";
    private static final String PASSWORD = "q3231423581";
    private List<Shiyan> shiyan_list=new ArrayList<>();
    List<String> list = new ArrayList<>();
    private List<Score> score_list=new ArrayList<>();

    public List<String> getList() {
        return list;
    }

    public void con(String student_id, String test_id) {
        Connection connection = null;
        String insertSql="INSERT INTO score (sid, grade,id_test) VALUES (?, null, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, student_id);
            pstmt.setString(2, test_id);
            int rowsAffected = pstmt.executeUpdate(); // 使用 executeUpdate
            System.out.println("插入成功，受影响的行数: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace(); // 输出错误信息
        }
    }
    public List<Shiyan> getShiyan_list() {
        return shiyan_list;
    }
    public HashMap<String,String> complete_list=new HashMap<>();

    public HashMap<String, String> getComplete_list() {
        return complete_list;
    }
    public Score_db(){
        complete_number();
        setScore_listt();
    }

    public boolean complete(String student_id, String test_id){
        Connection connection = null;
        try {
            // 加载JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 创建连接
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT id_test FROM score where id_test in (select id_test from score where sid='"+student_id+"');";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
//                System.out.println(resultSet.getString("sid"));
//                System.out.println(resultSet.getString("id_test"));
                list.add(resultSet.getString("id_test"));
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
        if(list.contains(test_id)){
            return true;
        }
        return false;
    }

    public void complete_number(){
        Connection connection = null;
        try {
            // 加载JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 创建连接
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT id_test, COUNT(DISTINCT sid) AS count_complete FROM score GROUP BY id_test;";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
//                System.out.println(resultSet.getString("id_test"));
                complete_list.put(resultSet.getString("id_test"),resultSet.getString("count_complete"));

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



    //**********************************************WXY********************************************
    public List<Score> getscore_list() {
        return score_list;
    }

    public void updategrade(String sid ,String id_test,int grade) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stem = conn.createStatement();
        stem.executeUpdate("update score set grade = " + grade +" where sid = '" + sid + "' and id_test = '" + id_test+"';");
        try {
            if (conn != null) {
                conn.close();
                System.out.println("数据库连接已关闭。");
            }
        } catch (SQLException e) {
            System.out.println("关闭连接时出错: " + e.getMessage());
        }
    }

    public void updatecomment(String sid ,String id_test,String comment) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stem = conn.createStatement();
        String updatesql = "update score set saying = '"+ comment +"' where sid = '" + sid + "' and id_test = '" + id_test+"';";
        stem.executeUpdate(updatesql);
        try {
            if (conn != null) {
                conn.close();
                System.out.println("数据库连接已关闭。");
            }
        } catch (SQLException e) {
            System.out.println("关闭连接时出错: " + e.getMessage());
        }
    }

    public void setScore_listt() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT * FROM score;";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String sid = resultSet.getString("sid");
                int grade = resultSet.getInt("grade");
                String id_test=resultSet.getString("id_test");
                String saying=resultSet.getString("saying");

                Score s=new Score(id,id_test,sid,grade,saying);
                score_list.add(s);
            }


        }catch (SQLException e) {
            System.out.println("数据库连接失败: " + e.getMessage());
        }
        finally {
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
    //*******************************************************************************************

//    public static void main(String[] args) {
//        Score_db s=new Score_db();
//        s.complete_number();
//        System.out.println(s.complete_list);
//    }
}
