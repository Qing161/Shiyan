package org.example.Student_Window;


import org.example.ConDb.Student_db;
import org.example.people_class.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Student_change extends JFrame {
    Font labelFont = new Font("仿宋", Font.PLAIN, 20);
    Font textFieldFont = new Font("仿宋", Font.PLAIN, 18);
    Font buttonFont = new Font("仿宋", Font.BOLD, 20);
    Student_db s=new Student_db();
    List<Student> student_list=s.getstudent_list();
    JTextField lt2,lt3,lt4;

    public Student_change(String id){
        setTitle("更改信息—学生");
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
                JLabel l2=new JLabel("学生姓名：");
                JLabel l3=new JLabel("学生性别：");
                JLabel l4=new JLabel("学生年龄：");
                JLabel l5=new JLabel("学生专业："+s.getSdept());

                lt2=new JTextField(s.getName());
                lt3=new JTextField(s.getSex()+"");
                lt4=new JTextField(s.getAge()+"");

                lt2.setFont(textFieldFont);
                lt3.setFont(textFieldFont);
                lt4.setFont(textFieldFont);

                l1.setFont(labelFont);
                l2.setFont(labelFont);
                l3.setFont(labelFont);
                l4.setFont(labelFont);
                l5.setFont(labelFont);

                p.add(l1);
                p.add(l2);
                p.add(lt2);
                p.add(l3);
                p.add(lt3);
                p.add(l4);
                p.add(lt4);
                p.add(l5);

            }
        }
        JButton j_b=new JButton("确定");
        j_b.setFont(buttonFont);
        j_b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(lt2.getText());
                s.change(id,lt2.getText(),lt3.getText(), Integer.parseInt(lt4.getText()));
                JOptionPane.showMessageDialog(null,"修改成功");
                dispose();
                Student_xinxi st=new Student_xinxi(id);
            }
        });

        JButton j_b1=new JButton("取消");
        j_b1.setFont(buttonFont);
        j_b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.add(p);
        JPanel po=new JPanel(new GridLayout(1,2));
        po.add(j_b1);
        po.add(j_b);
        this.add(po, BorderLayout.SOUTH);
    }

//    public static void main(String[] args) {
//        Student_change s=new Student_change("2320");
//    }
}

