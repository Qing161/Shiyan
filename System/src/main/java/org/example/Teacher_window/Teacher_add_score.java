package org.example.Teacher_window;

import org.example.ConDb.Score_db;
import org.example.Other_class.Score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Teacher_add_score extends JFrame{
    Font labelFont = new Font("仿宋", Font.PLAIN, 20);
    Font textFieldFont = new Font("仿宋", Font.PLAIN, 18);
    Font buttonFont = new Font("仿宋", Font.BOLD, 20);

    private JButton b_score,b_release;
    private JPanel p;
    private JLabel l_testid,l_sid,l_grade,l_comment;
    private JTextField t_comment,t_sid,t_testid;
    private JComboBox<Integer> com_grade;

    JScrollPane jspane;
    private JTable table;

    Score_db score_db;

    public Teacher_add_score(String tid){
        setSize(520,460);
        setTitle("教师发布成绩界面");
        setLocationRelativeTo(null);
        init(tid);
        setVisible(true);
    }

    public void init(String tid){
        System.out.println(tid);
        l_testid = new JLabel("实验编号:",SwingConstants.CENTER);
        l_sid = new JLabel("学生编号:",SwingConstants.CENTER);
        l_grade = new JLabel("成绩:",SwingConstants.CENTER);
        l_comment = new JLabel("评语:",SwingConstants.CENTER);

        l_testid.setFont(labelFont);
        l_grade.setFont(labelFont);
        l_sid.setFont(labelFont);
        l_comment.setFont(labelFont);

        t_comment = new JTextField();
        t_comment.setFont(textFieldFont);
        t_sid = new JTextField();
        t_sid.setFont(textFieldFont);
        t_testid = new JTextField();
        t_testid.setFont(textFieldFont);

        com_grade = new JComboBox<Integer>();

        com_grade.setFont(textFieldFont);

        b_score = new JButton("添加成绩");
        b_release = new JButton("刷新");

        b_score.setFont(buttonFont);
        b_release.setFont(buttonFont);

        for(int i = 0; i<100;i++){
            com_grade.addItem(i);
        }

        score_db = new Score_db();
        List<Score> scoreList;
        scoreList = score_db.getscore_list();

        b_score.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sid = t_sid.getText();
                String id_test = t_testid.getText();
                int grade = (int) com_grade.getSelectedItem();
                String comment = t_comment.getText();
                try {
                    score_db.updategrade(sid,id_test,grade);
                    score_db.updatecomment(sid,id_test,comment);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null,"添加成功！");
                dispose();
            }
        });

        b_release.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Teacher_add_score(tid);
                dispose();
            }
        });

        Object[] title = {"编号","实验编号","学生编号","成绩"};
        Object[][] List = new Object[10][4];
        int j = 0;
        for(Score n:scoreList) {
            System.out.println(n.getId());
            List[j][0] = n.getId();
            List[j][1] = n.getId_test();
            List[j][2] = n.getSidid();
            List[j][3] = n.getGrade();
            j++;
        }
        table = new JTable(List,title);
        jspane = new JScrollPane(table);
        setLayout(null);
        jspane.setBounds(5,5,500,200);
        add(jspane);

        p = new JPanel();
        p.setLayout(new GridLayout(5,2,5,5));
        p.add(l_testid);
        p.add(t_testid);
        p.add(l_sid);
        p.add(t_sid);
        p.add(l_grade);
        p.add(com_grade);
        p.add(l_comment);
        p.add(t_comment);
        p.add(b_score);
        p.add(b_release);
        p.setBounds(5,220,500,200);
        add(p);

    }

    public static void main(String[] args) {
        Teacher_add_score t=new Teacher_add_score("T20050520");
    }
}
