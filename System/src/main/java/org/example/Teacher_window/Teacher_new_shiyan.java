package org.example.Teacher_window;

import org.example.ConDb.Shiyan_db;
import org.example.ConDb.Teacher_db;
import org.example.Other_class.Shiyan;
import org.example.Student_Window.Student_change;
import org.example.people_class.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Teacher_new_shiyan extends JFrame {
    Font labelFont = new Font("仿宋", Font.PLAIN, 20);
    Font textFieldFont = new Font("仿宋", Font.PLAIN, 18);
    Font buttonFont = new Font("仿宋", Font.BOLD, 20);

    private JLabel l_testid,l_name,l_tid,l_start,l_end,l_year,l_moon,l_day,l_year2,l_moon2,l_day2;
    private JLabel l_class;
    private JButton b_create,b_reset;
    private JTextField t_testid,t_name,t_tid,t_class;
    private JComboBox<String> com_year,com_moon,com_day,com_year2,com_moon2,com_day2;

    Shiyan_db experiment_db;
    List<Shiyan> Experiment_list;

    Teacher_db teacher_db;
    List<Teacher> teacher_list;

    public Teacher_new_shiyan(){
        setSize(520,385);
        setTitle("新建实验界面");
        setLocationRelativeTo(null);
        init();
        setVisible(true);
    }
    public void init(){
        experiment_db = new Shiyan_db();
        Experiment_list = experiment_db.getShiyan_list();

        teacher_db = new Teacher_db();
        teacher_list = teacher_db.getTeacher_list();

        l_testid = new JLabel("实验编号：", SwingConstants.RIGHT);
        l_class=new JLabel("实验班级：", SwingConstants.RIGHT);
        l_name = new JLabel("实验名称：",SwingConstants.RIGHT);
        l_tid = new JLabel("负责老师编号：",SwingConstants.RIGHT);
        l_start = new JLabel("开始时间:",SwingConstants.RIGHT);
        l_end = new JLabel("结束时间:",SwingConstants.RIGHT);
        l_year = new JLabel("年");
        l_moon = new JLabel("月");
        l_day = new JLabel("日");
        l_year2 = new JLabel("年");
        l_moon2 = new JLabel("月");
        l_day2 = new JLabel("日");

        l_testid.setFont(labelFont);
        l_name.setFont(labelFont);
        l_tid.setFont(labelFont);
        l_class.setFont(labelFont);

        l_year.setFont(labelFont);
        l_moon.setFont(labelFont);
        l_day.setFont(labelFont);
        l_year2.setFont(labelFont);
        l_moon2.setFont(labelFont);
        l_day2.setFont(labelFont);

        com_year = new JComboBox<String>();
        com_year.addItem("2024");
        com_year.addItem("2025");
        com_moon = new JComboBox<String>();
        com_day = new JComboBox<String>();

        com_year.setFont(textFieldFont);
        com_moon.setFont(textFieldFont);
        com_day.setFont(textFieldFont);

        com_moon.addItem("01");
        com_moon.addItem("02");
        com_moon.addItem("03");
        com_moon.addItem("04");
        com_moon.addItem("05");
        com_moon.addItem("06");
        com_moon.addItem("07");
        com_moon.addItem("08");
        com_moon.addItem("09");
        com_moon.addItem("10");
        com_moon.addItem("11");
        com_moon.addItem("12");

        com_day.addItem("01");
        com_day.addItem("02");
        com_day.addItem("03");
        com_day.addItem("04");
        com_day.addItem("05");
        com_day.addItem("06");
        com_day.addItem("07");
        com_day.addItem("08");
        com_day.addItem("09");
        com_day.addItem("10");
        com_day.addItem("11");
        com_day.addItem("12");
        com_day.addItem("13");
        com_day.addItem("14");
        com_day.addItem("15");
        com_day.addItem("16");
        com_day.addItem("17");
        com_day.addItem("18");
        com_day.addItem("19");
        com_day.addItem("20");
        com_day.addItem("21");
        com_day.addItem("22");
        com_day.addItem("23");
        com_day.addItem("24");
        com_day.addItem("25");
        com_day.addItem("26");
        com_day.addItem("27");
        com_day.addItem("28");
        com_day.addItem("29");
        com_day.addItem("30");
        com_day.addItem("31");

        com_year2 = new JComboBox<String>();
        com_year2.addItem("2024");
        com_year2.addItem("2025");
        com_moon2 = new JComboBox<String>();
        com_day2 = new JComboBox<String>();

        com_day2.setFont(textFieldFont);
        com_moon2.setFont(textFieldFont);
        com_year2.setFont(textFieldFont);

        com_moon2.addItem("01");
        com_moon2.addItem("02");
        com_moon2.addItem("03");
        com_moon2.addItem("04");
        com_moon2.addItem("05");
        com_moon2.addItem("06");
        com_moon2.addItem("07");
        com_moon2.addItem("08");
        com_moon2.addItem("09");
        com_moon2.addItem("10");
        com_moon2.addItem("11");
        com_moon2.addItem("12");

        com_day2.addItem("01");
        com_day2.addItem("02");
        com_day2.addItem("03");
        com_day2.addItem("04");
        com_day2.addItem("05");
        com_day2.addItem("06");
        com_day2.addItem("07");
        com_day2.addItem("08");
        com_day2.addItem("09");
        com_day2.addItem("10");
        com_day2.addItem("11");
        com_day2.addItem("12");
        com_day2.addItem("13");
        com_day2.addItem("14");
        com_day2.addItem("15");
        com_day2.addItem("16");
        com_day2.addItem("17");
        com_day2.addItem("18");
        com_day2.addItem("19");
        com_day2.addItem("20");
        com_day2.addItem("21");
        com_day2.addItem("22");
        com_day2.addItem("23");
        com_day2.addItem("24");
        com_day2.addItem("25");
        com_day2.addItem("26");
        com_day2.addItem("27");
        com_day2.addItem("28");
        com_day2.addItem("29");
        com_day2.addItem("30");
        com_day2.addItem("31");


        b_create = new JButton("创建");
        b_reset = new JButton("清空");

        b_create.setFont(buttonFont);
        b_reset.setFont(buttonFont);

        t_testid = new JTextField();
        t_name = new JTextField();
        t_tid = new JTextField();
        t_class=new JTextField();

        setLayout(null);

        JPanel p = new JPanel();

        p.setLayout(new GridLayout(4,2,5,5));
        p.setBounds(5,5,480,180);
        p.add(l_testid);
        p.add(t_testid);
        p.add(l_name);
        p.add(t_name);
        p.add(l_tid);
        p.add(t_tid);
        p.add(l_class);
        p.add(t_class);
        add(p);



        p = new JPanel();
        p.setLayout(new GridLayout(2,7,5,5));
        p.setBounds(5,200,510,80);
        p.add(l_start);
        p.add(com_year);
        p.add(l_year);
        p.add(com_moon);
        p.add(l_moon);
        p.add(com_day);
        p.add(l_day);

        p.add(l_end);
        p.add(com_year2);
        p.add(l_year2);
        p.add(com_moon2);
        p.add(l_moon2);
        p.add(com_day2);
        p.add(l_day2);
        add(p);

        p = new JPanel();
        p.setLayout(new GridLayout(1,2,5,5));
        p.setBounds(5,300,500,40);
        p.add(b_reset);
        p.add(b_create);
        add(p);

        b_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = true;
                boolean p = false;
                String id_test = t_testid.getText();
                String name = t_name.getText();
                String start_time = (String)com_year.getSelectedItem()+com_moon.getSelectedItem()+com_day.getSelectedItem();
                String end_time = (String)com_year2.getSelectedItem()+com_moon2.getSelectedItem()+com_day2.getSelectedItem();
                String tid = t_tid.getText();
                if(id_test.equals("")||tid.equals("")){
                    JOptionPane.showMessageDialog(null,"实验编号和负责老师编号不能为空！");
                }else{
                    for(int i = 0; i<Experiment_list.size(); i++){
                        if(id_test.equals(Experiment_list.get(i).getId())){
                            b = false;
                        }
                    }
                    for(int i = 0; i<teacher_list.size(); i++){
                        if(tid.equals(teacher_list.get(i).getId())){
                            p = true;
                        }
                    }
                    if(!b){
                        JOptionPane.showMessageDialog(null,"实验编号重复！");
                    }if(!p){
                        JOptionPane.showMessageDialog(null,"没有此老师！");
                    }
                    if(b&&p){
                        Shiyan experiment = new Shiyan(id_test, name, start_time, end_time,0, tid);
                        Shiyan_db experiment_db = new Shiyan_db();
                        try {
                            experiment_db.add(experiment);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        JOptionPane.showMessageDialog(null, "恭喜您新建成功！");
                        new Teacher_win(tid);
                        dispose();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        Teacher_new_shiyan t=new Teacher_new_shiyan();
    }
}
