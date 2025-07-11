package org.example.Student_Window;

import org.example.ConDb.Student_db;
import org.example.people_class.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Student_xinxi extends JFrame {
    Font labelFont = new Font("仿宋", Font.PLAIN, 20);
    Font textFieldFont = new Font("仿宋", Font.PLAIN, 18);
    Font buttonFont = new Font("仿宋", Font.BOLD, 20);
    Student_db s=new Student_db();
    List<Student> student_list=s.getstudent_list();

    public Student_xinxi(String id){
        setTitle("个人信息—学生");
        setSize(400, 500);
        setLocation(800, 200);
        init(id);
        setVisible(true);
    }

    public void init(String id){
        JPanel p=new JPanel();
//        p.setBackground(new Color(205,205,203));
        p.setBounds(0, 0, 400, 500);

////        p.setLocation(270,350);
        p.setLayout(new GridLayout(10,1));

        for(Student s:student_list){
            if(s.getId().equals(id)){
                System.out.println(s.getId());

                JLabel l1=new JLabel("学生学号："+s.getId());
                JLabel l5=new JLabel("学生姓名："+s.getName());
                JLabel l3=new JLabel("学生性别："+s.getSex());
                JLabel l4=new JLabel("学生年龄："+s.getAge());
                JLabel l2=new JLabel("学生班级："+s.getSdept());

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
        JButton j_b=new JButton("修改信息");
        j_b.setFont(buttonFont);
        j_b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student_change s=new Student_change(id);
                dispose();
            }
        });

        this.add(p);
        this.add(j_b, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        Student_xinxi s=new Student_xinxi("2313043507");
    }
}
