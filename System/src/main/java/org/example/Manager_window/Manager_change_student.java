package org.example.Manager_window;


import org.example.ConDb.Student_db;
import org.example.people_class.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Manager_change_student extends JFrame {
    Font labelFont = new Font("仿宋", Font.PLAIN, 20);
    Font textFieldFont = new Font("仿宋", Font.PLAIN, 18);
    Font buttonFont = new Font("仿宋", Font.BOLD, 20);
    Student_db s=new Student_db();
    List<Student> student_list=s.getstudent_list();
    JTextField lt2,lt3,lt4,lt1,lt5;

    public Manager_change_student(String id){
        setTitle("更改信息-学生");
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

                JLabel l1=new JLabel("学生学号：");
                JLabel l2=new JLabel("学生姓名：");
                JLabel l3=new JLabel("学生性别：");
                JLabel l4=new JLabel("学生年龄：");
                JLabel l5=new JLabel("学生专业：");

                lt1=new JTextField(s.getId());
                lt2=new JTextField(s.getName());
                lt3=new JTextField(s.getSex()+"");
                lt4=new JTextField(s.getAge()+"");
                lt5=new JTextField(s.getSdept());

                lt1.setFont(textFieldFont);
                lt2.setFont(textFieldFont);
                lt3.setFont(textFieldFont);
                lt4.setFont(textFieldFont);
                lt5.setFont(textFieldFont);

                l1.setFont(labelFont);
                l2.setFont(labelFont);
                l3.setFont(labelFont);
                l4.setFont(labelFont);
                l5.setFont(labelFont);

                p.add(l1);
                p.add(lt1);
                p.add(l2);
                p.add(lt2);
                p.add(l3);
                p.add(lt3);
                p.add(l4);
                p.add(lt4);
                p.add(l5);
                p.add(lt5);
            }
        }
        JButton j_b=new JButton("确定");
        j_b.setFont(buttonFont);
        j_b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(lt2.getText());
                s.change_main(id,lt1.getText(),lt2.getText(),lt3.getText(), Integer.parseInt(lt4.getText()),lt5.getText());
                JOptionPane.showMessageDialog(null,"修改成功");
                dispose();
                Manager_student_xinxi stu=new Manager_student_xinxi(lt1.getText());
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
//        Manager_change_student s=new Manager_change_student("2323");
//    }
}


