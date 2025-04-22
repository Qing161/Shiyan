package org.example.Manager_window;

import org.example.ConDb.Shiyan_db;
import org.example.ConDb.Student_db;
import org.example.ConDb.Teacher_db;
import org.example.Other_class.Shiyan;
import org.example.Teacher_window.Teacher_new_shiyan;
import org.example.people_class.Student;
import org.example.people_class.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Manager_win extends JFrame {
    private static int which=0;
    JLabel l_name, l_category;
    JButton s_xinxi;
    JComboBox<String> c_category;

    Font labelFont = new Font("仿宋", Font.PLAIN, 20);
    Font textFieldFont = new Font("仿宋", Font.PLAIN, 18);
    Font buttonFont = new Font("仿宋", Font.BOLD, 20);


    private final JList<String> taskList = new JList<>();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
//    private final List<String> tasks = new ArrayList<>();
    Student_db student_db=new Student_db();
    List<Student> studentList=student_db.getstudent_list();
    Teacher_db teacher_db=new Teacher_db();
    List<Teacher> teacherList=teacher_db.getTeacher_list();

    Shiyan_db shiyan_db=new Shiyan_db();
    List<Shiyan> shiyan_list = shiyan_db.getShiyan_list();


    public Manager_win(String id) {
        JLabel l1 = new JLabel("您好:" + id+"管理员", JLabel.LEFT);
        l1.setBounds(2, 2, 200, 20);
        l1.setFont(labelFont);
        JButton b_s=new JButton("刷新界面");
        b_s.setFont(buttonFont);
        b_s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shiyan_db=new Shiyan_db();
                student_db=new Student_db();
                teacher_db=new Teacher_db();
                listModel.clear();
                updateTaskList();
            }
        });


        add(l1);

        l_name = new JLabel("   ", JLabel.LEFT);

        s_xinxi = new JButton("个人信息");
        s_xinxi.setFont(buttonFont);

        JPanel p=new JPanel();
        p.add(l_name);

        setTitle("管理员界面");
        setSize(840, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        taskList.setModel(listModel);
        taskList.setFont(labelFont);

        add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1,4));
        JButton b_0=new JButton("页面更改");
        JButton b_1=new JButton("授权管理");
        JButton b_2=new JButton("人员信息");
        JButton b_3=new JButton("创建课程");
        JButton b_4=new JButton("实验列表");

        b_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager_all_shiyan a=new Manager_all_shiyan();
            }
        });

        b_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = taskList.getSelectedIndices();
                if(which%2==0&&which!=0){
                    JOptionPane.showMessageDialog(null,"请更改页面后再点击！");
                    return;
                }
                for (int i:selectedIndices){
                    System.out.println(i);
                    if(i<teacherList.size()){
                        int j=0;
                        for(Teacher t:teacherList){
                            if(j==i){
                                System.out.println(t.getId());
                                JOptionPane.showMessageDialog(null,"给教师 "+t.getId()+" 授权");
                            }
                            j++;
                        }
                    }else {
                        int j=0;
                        for(Student s:studentList){
                            if(j==(i-teacherList.size())){
                                System.out.println(s.getId());
                                JOptionPane.showMessageDialog(null,"给学生 "+s.getId()+" 授权");
                            }
                            j++;
                        }

                    }
                }

            }
        });



        b_3.addActionListener(this::search);
        b_0.addActionListener(this::change);
        b_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = taskList.getSelectedIndices();
                if(which%2==0&&which!=0){
                    JOptionPane.showMessageDialog(null,"请更改页面后再点击！");
                    return;
                }
                for (int i:selectedIndices){
                    System.out.println(i);
                    if(i<teacherList.size()){
                        int j=0;
                        for(Teacher t:teacherList){
                            if(j==i){
                                System.out.println(t.getId());
                                Manager_teacher_xinxi tea=new Manager_teacher_xinxi(t.getId());
                            }
                            j++;
                        }
                    }else {
                        int j=0;
                        for(Student s:studentList){
                            if(j==(i-teacherList.size())){
                                System.out.println(s.getId());
                                Manager_student_xinxi stu=new Manager_student_xinxi(s.getId());
                            }
                            j++;
                        }

                    }
                }

            }
        });

        b_0.setFont(buttonFont);
        b_1.setFont(buttonFont);
        b_2.setFont(buttonFont);
        b_3.setFont(buttonFont);
        b_4.setFont(buttonFont);

        buttonPanel.add(b_0);
        buttonPanel.add(b_1);
        buttonPanel.add(b_2);
        buttonPanel.add(b_3);
        buttonPanel.add(b_4);
        buttonPanel.add(b_s);

        add(buttonPanel, BorderLayout.SOUTH);
        add(p, BorderLayout.NORTH);
        updateTaskList();
    }


    private void search(ActionEvent e) {
        Teacher_new_shiyan t=new Teacher_new_shiyan();
    }
    private void change(ActionEvent e){
        listModel.clear();
        updateTaskList();
        which++;
        System.out.println(which);
    }

    public void updateTaskList() {
        if(which%2==0||which==0){
            teacherList=teacher_db.getTeacher_list();
            studentList=student_db.getstudent_list();
            for (Teacher t:teacherList){
                listModel.addElement("教师："+t.getId()+" "+t.getName());
            }
            for (Student s:studentList){
                listModel.addElement("学生："+s.getId()+" "+s.getName()+" "+s.getSdept());
            }
        }else {
            shiyan_list=shiyan_db.getShiyan_list();
            for (Shiyan sh:shiyan_list){
                listModel.addElement("实验："+sh.getId()+" "+sh.getTitle()+" 截止时间："+sh.getEnd_time());
            }
        }

    }
}