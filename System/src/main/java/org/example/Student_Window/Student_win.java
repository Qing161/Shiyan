package org.example.Student_Window;

import org.example.ConDb.Score_db;
import org.example.ConDb.Shiyan_db;
import org.example.Other_class.Shiyan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Student_win extends JFrame {
    JLabel l_name, l_category;
    JButton s_xinxi;
    JComboBox<String> c_category;

    Font labelFont = new Font("仿宋", Font.PLAIN, 20);
    Font textFieldFont = new Font("仿宋", Font.PLAIN, 18);
    Font buttonFont = new Font("仿宋", Font.BOLD, 20);

    private final JList<String> taskList = new JList<>();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
//    private final List<String> tasks = new ArrayList<>();

    Shiyan_db shiyan_db=new Shiyan_db();
    private final List<Shiyan> shiyan_list = shiyan_db.getShiyan_list();


    public Student_win(String id) {
        JLabel l1 = new JLabel("您好:" + id+ "同学", JLabel.LEFT);
        l1.setBounds(2, 2, 200, 20);
        l1.setFont(labelFont);

        add(l1);

        l_name = new JLabel("   ", JLabel.LEFT);

        s_xinxi = new JButton("个人信息");
        s_xinxi.setFont(buttonFont);

        JPanel p=new JPanel();
        p.add(l_name);

        setTitle("实验界面-学生");
        setSize(840, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        taskList.setModel(listModel);
        taskList.setFont(labelFont);

        add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1,4));
        JButton b_1=new JButton("完成实验");
        JButton b_2=new JButton("个人信息");
        JButton b_3=new JButton("查询实验");
        JButton b_4=new JButton("所有实验");

        b_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student_all_shiyan a=new Student_all_shiyan(id);
            }
        });

        b_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 创建文件选择器
//                JFileChooser fileChooser = new JFileChooser();
//                int returnValue = fileChooser.showOpenDialog(Student_win.this); // 显示选择文件对话框
//
//                // 检查用户是否选择了文件
//                if (returnValue == JFileChooser.APPROVE_OPTION) {
//                    // 获取选择的文件
//                    java.io.File selectedFile = fileChooser.getSelectedFile();
//                    System.out.println("选择的文件: " + selectedFile.getAbsolutePath());
//                }

                int[] selectedIndices = taskList.getSelectedIndices();
                for (int i : selectedIndices) {
                    int j=0;
                    for (Shiyan shiyan : shiyan_list) {
                        if(j==i){
                            JOptionPane.showMessageDialog(null, "确定提交："+shiyan.getTitle()+"实验？");
//                            Student_each_shiyan s=new Student_each_shiyan(shiyan.getId());
                            Score_db s=new Score_db();
                            s.con(id,shiyan.getId());
                            JOptionPane.showMessageDialog(null, "你已完成："+shiyan.getTitle()+"!");
                        }
                        j++;
                    }

                }
            }
        });

        //    private void addTask(ActionEvent e) {
//        String taskName = JOptionPane.showInputDialog(this, "请输入任务名称:");
//        if (taskName != null && !taskName.isEmpty()) {
////            tasks.add(taskName);
//            updateTaskList();
//        }
//    }

//    private void deleteSelectedTasks(ActionEvent e) {
//        int[] selectedIndices = taskList.getSelectedIndices();
//        for (int i : selectedIndices) {
////            tasks.remove(i);
//        }
//        updateTaskList();
//    }

        b_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = taskList.getSelectedIndices();
                for (int i : selectedIndices) {
                    int j=0;
                    for (Shiyan shiyan : shiyan_list) {

//                listModel.addElement(shiyan.getTitle()+"       "+shiyan.getEnd_time()+"截至！！！");
                        if(j==i){
//                    JOptionPane.showMessageDialog(null, "你在查询"+shiyan.getTitle()+"!");
                            Student_each_shiyan s=new Student_each_shiyan(shiyan.getId(),id);
                        }
                        j++;
                    }

                }
            }
        });

        b_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student_xinxi s=new Student_xinxi(id);
            }
        });

        b_1.setFont(buttonFont);
        b_2.setFont(buttonFont);
        b_3.setFont(buttonFont);
        b_4.setFont(buttonFont);

        buttonPanel.add(b_1);
        buttonPanel.add(b_2);
        buttonPanel.add(b_3);
        buttonPanel.add(b_4);


        add(buttonPanel, BorderLayout.SOUTH);
        add(p, BorderLayout.NORTH);
        updateTaskList();
    }




    private void updateTaskList() {
        listModel.clear();
        //*********************************************************************
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedNow = sdf.format(now);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String dateTimeString1 = formattedNow;


        //*********************************************************************

        for (Shiyan shiyan : shiyan_list) {

            //***********************************************************************
            String dateTimeString2 = shiyan.getEnd_time();

            LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeString1, formatter);
            LocalDateTime dateTime2 = LocalDateTime.parse(dateTimeString2, formatter);

            if (dateTime1.isBefore(dateTime2)) {
//                System.out.println("第一个日期早于第二个日期");
                listModel.addElement(shiyan.getTitle()+"       请在"+shiyan.getEnd_time()+"前提交");
            } else if (dateTime1.isAfter(dateTime2)) {
                System.out.println("第一个日期晚于第二个日期");
                listModel.addElement(shiyan.getTitle()+"       "+shiyan.getEnd_time()+",实验已截止！！");
            } else {
                System.out.println("两个日期相等");
                listModel.addElement(shiyan.getTitle()+"       "+shiyan.getEnd_time()+"马上截止！！！");
            }
            //***********************************************************************

        }
    }
}