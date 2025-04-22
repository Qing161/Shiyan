package org.example.Manager_window;

import org.example.ConDb.Teacher_db;
import org.example.people_class.Teacher;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Manager_teacher_xinxi extends JFrame {
    Font labelFont = new Font("仿宋", Font.PLAIN, 20);
    Font textFieldFont = new Font("仿宋", Font.PLAIN, 18);
    Font buttonFont = new Font("仿宋", Font.BOLD, 20);

    Teacher_db t=new Teacher_db();
    List<Teacher> teacher_list=t.getTeacher_list();

    public Manager_teacher_xinxi(String id){
        setTitle("教师信息");
        setSize(400, 500);
        setLocation(800, 200);
        init(id);
        setVisible(true);
    }

    public void init(String id){
        JPanel p=new JPanel();
//        p.setBackground(new Color(205,205,203));
        p.setBounds(0, 0, 380, 400);
////        p.setLocation(270,350);
        p.setLayout(new GridLayout(6,1));

        for(Teacher t:teacher_list){
            if(t.getId().equals(id)){
                System.out.println(t.getId());

                JLabel l1=new JLabel("教师ID："+t.getId());
                JLabel l2=new JLabel("教师姓名："+t.getName());
                JLabel l3=new JLabel("教师性别："+t.getSex());
                JLabel l4=new JLabel("教师年龄："+t.getAge());
                JLabel l5=new JLabel("教师密码："+t.getPassword());

                l1.setFont(labelFont);
                l2.setFont(labelFont);
                l3.setFont(labelFont);
                l4.setFont(labelFont);
                l5.setFont(labelFont);

                p.add(l1);
                p.add(l2);
                p.add(l3);
                p.add(l4);
                p.add(l5);
            }
        }
        this.add(p);

        JButton j_b=new JButton("修改信息");
        j_b.setFont(buttonFont);
        this.add(j_b, BorderLayout.SOUTH);
    }

//    public static void main(String[] args) {
//        Manager_teacher_xinxi s=new Manager_teacher_xinxi("2313");
//    }
}
