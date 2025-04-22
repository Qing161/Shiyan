package org.example.Manager_window;

import org.example.ConDb.Student_db;
import org.example.people_class.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Manager_student_xinxi extends JFrame {
    Font labelFont = new Font("仿宋", Font.PLAIN, 20);
    Font textFieldFont = new Font("仿宋", Font.PLAIN, 18);
    Font buttonFont = new Font("仿宋", Font.BOLD, 20);
    Student_db s=new Student_db();
    List<Student> student_list=s.getstudent_list();

    public Manager_student_xinxi(String id){
        setTitle("学生信息");
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

        for(Student s:student_list){
            if(s.getId().equals(id)){
                System.out.println(s.getId());

                JLabel l1=new JLabel("学生学号："+s.getId());
                JLabel l2=new JLabel("学生姓名："+s.getName());
                JLabel l3=new JLabel("学生性别："+s.getSex());
                JLabel l4=new JLabel("学生年龄："+s.getAge());
                JLabel l5=new JLabel("学生专业："+s.getSdept());
                JLabel l6=new JLabel("学生密码："+s.getPassword());

                l1.setFont(labelFont);
                l2.setFont(labelFont);
                l3.setFont(labelFont);
                l4.setFont(labelFont);
                l5.setFont(labelFont);
                l6.setFont(labelFont);

                p.add(l1);
                p.add(l2);
                p.add(l3);
                p.add(l4);
                p.add(l5);
                p.add(l6);
            }
        }
        JButton j_b=new JButton("修改信息");
        j_b.setFont(buttonFont);
        j_b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_change_student c=new Manager_change_student(id);
                dispose();
            }
        });

        this.add(p);
        this.add(j_b, BorderLayout.SOUTH);
    }

//    public static void main(String[] args) {
//        Manager_student_xinxi s=new Manager_student_xinxi("2320");
//    }
}
