package org.example;

import org.example.ConDb.Manager_db;
import org.example.ConDb.Student_db;
import org.example.ConDb.Teacher_db;
import org.example.Manager_window.Manager_win;
import org.example.Student_Window.Student_win;
import org.example.Teacher_window.Teacher_win;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.*;

public class Login_win extends JFrame{
    private JLabel l_name, l_type, l_password;
    private JTextField t_name;
    private JComboBox<String> c_type;
    private JPasswordField p_password;
    private JButton b_login, b_reset, b_register;
    Student_db student_db=new Student_db();
    Manager_db manager_db=new Manager_db();
    Teacher_db teacher_db=new Teacher_db();
    private static String id;

    //请完成类的设计

    Font labelFont = new Font("仿宋", Font.PLAIN, 20);
    Font textFieldFont = new Font("仿宋", Font.PLAIN, 18);
    Font buttonFont = new Font("仿宋", Font.BOLD, 20);

    public Login_win(){
        this.setSize(840, 500);
        this.setTitle("实验管理系统-登录");
//    	this.setLocation(200, 200);
        this.setLocationRelativeTo(null); // 让窗口居中
        init();
        this.setVisible(true);
    }

    public void init(){
        this.setLayout(null);  //自定义布局
//        this.getContentPane().setBackground(Color.getHSBColor(225,225,225));  // 设置 JFrame 的背景颜色

        l_name = new JLabel("账号：", JLabel.CENTER);
        l_name.setFont(labelFont);
        l_type = new JLabel("用户类别：", JLabel.CENTER);
        l_type.setFont(labelFont);
        l_password = new JLabel("密码：", JLabel.CENTER);
        l_password.setFont(labelFont);

        t_name = new JTextField();
        t_name.setFont(textFieldFont);
        c_type = new JComboBox<String>();
        c_type.addItem("学生");
        c_type.addItem("教师");
        c_type.addItem("管理员");
        c_type.setFont(textFieldFont);
        p_password = new JPasswordField();

        b_login = new JButton("登录");
        b_login.setFont(buttonFont);

        b_login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                char[] passwordArray = p_password.getPassword();
                String temp_password = new String(passwordArray);
                String temp_name = t_name.getText();
                id = t_name.getText();
                String type = (String) c_type.getSelectedItem();
//				System.out.println(temp_name+temp_password);
                if (temp_name.isEmpty() || temp_password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入用户名和密码！");
                    return;
                }
                if (Objects.equals(type, "学生")) {
                    if (student_db.Login(temp_name, temp_password)) {
                        if (student_db.Login(temp_name, temp_password)) {
                            System.out.println(type);
                            EventQueue.invokeLater(() -> {
                                Student_win frame = new Student_win(id);
                                frame.setVisible(true);
                            });
                        }
                        dispose();
                    } else {
                        System.out.println("用户名或密码错误！");
                        JOptionPane.showMessageDialog(null, "用户名或密码错误！");
                    }
                } else if (Objects.equals(type, "管理员")) {
                    if (manager_db.Login(temp_name, temp_password)) {
                        if (manager_db.Login(temp_name, temp_password)) {
                            System.out.println(type);
                            EventQueue.invokeLater(() -> {
                                Manager_win frame = new Manager_win(id);

                                frame.setVisible(true);
                            });
                        }
                        dispose();
                    } else {
                        System.out.println("用户名或密码错误！");
                        JOptionPane.showMessageDialog(null, "用户名或密码错误！");
                    }
                }else{
                    if (teacher_db.Login(temp_name, temp_password)) {
                        if (teacher_db.Login(temp_name, temp_password)) {
                            System.out.println(type);
                            EventQueue.invokeLater(() -> {
                                Teacher_win frame = new Teacher_win(id);
                                frame.setVisible(true);
                            });
                        }
                        dispose();
                    } else {
                        System.out.println("用户名或密码错误！");
                        JOptionPane.showMessageDialog(null, "用户名或密码错误！");
                    }
                }
            }
        });

        b_reset = new JButton("重置");
        b_reset.setFont(buttonFont);
        b_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

//        b_register = new JButton("注册");
//        b_register.setFont(buttonFont);
//
//        b_register.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // TODO Auto-generated method stub
//                new RegisterFrame();
//            }
//        });

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 2, 5, 5));
        p.add(l_name);
        p.add(t_name);
        p.add(l_type);
        p.add(c_type);
        p.add(l_password);
        p.add(p_password);

        p.setBounds(5, 5, 400, 160);
        p.setLocation(230,100);
        this.add(p);

        p = new JPanel();
        p.setLayout(new GridLayout(1, 3, 5, 5));
//        p.add(b_reset);
        p.add(b_login);
//        p.add(b_register);
        p.setBounds(5, 190, 400, 35);
        p.setLocation(230,300);
        this.add(p);
    }


    public static void main(String[] args) {
        Login_win l=new Login_win();
    }
}

