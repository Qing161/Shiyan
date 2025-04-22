package org.example.Teacher_window;

import org.example.ConDb.Shiyan_db;
import org.example.Other_class.Shiyan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Teacher_win extends JFrame {
    Font labelFont = new Font("仿宋", Font.PLAIN, 20);
    Font textFieldFont = new Font("仿宋", Font.PLAIN, 18);
    Font buttonFont = new Font("仿宋", Font.BOLD, 20);

    private JButton b_new,b_delete,b_score;
    private JPanel p;
    private JTextField t_testid;
    JScrollPane jspane;
    private JTable table;
    Shiyan_db experiment_db;
    List<Shiyan> Experiment_list;


    public Teacher_win(String tid){
        setSize(840,500);
        setTitle("实验管理界面");
        setLocationRelativeTo(null);
        init(tid);
        setVisible(true);
    }
    public void init(String tid){
        experiment_db = new Shiyan_db();
        Experiment_list = experiment_db.getShiyan_list();

        b_new = new JButton("新建");
        b_delete = new JButton("删除");
        b_score = new JButton("成绩");

        b_new.setFont(buttonFont);
        b_delete.setFont(buttonFont);
        b_score.setFont(buttonFont);

        t_testid = new JTextField();
        t_testid.setFont(textFieldFont);

        //菜单
        JMenu usermanager=new JMenu("查看");
        JMenuItem Experiment=new JMenuItem("实验");
        JMenuItem Student=new JMenuItem("完成学生");
        usermanager.add(Experiment);
        usermanager.add(Student);
        JMenu scoremanager=new JMenu("成绩管理");
        JMenuItem input=new JMenuItem("录入");
        scoremanager.add(input);
        JMenuBar mb=new JMenuBar();
        mb.add(usermanager);
        mb.add(scoremanager);
        setJMenuBar(mb);

        //新建按钮
        b_new.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Teacher_new_shiyan();
            }
        });

        //删除按钮
        b_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id_test = (String) t_testid.getText();
                boolean b = false;
                for(int i = 0; i<Experiment_list.size(); i++){
                    if(id_test.equals(Experiment_list.get(i).getId())){
                        b = true;
                    }
                }
                if(b){
                    try {
                        experiment_db.delete(id_test);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    new Teacher_win(tid);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"没有此实验！");
                }
            }
        });

        //成绩按钮
        b_score.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Teacher_add_score(tid);
            }
        });


        Object[] title = {"实验编号","实验名称","开始时间","结束时间","完成人数"};
        Object[][] List = new Object[10][5];
        int i = 0;
        for(Shiyan m:Experiment_list) {
            List[i][0] = m.getId();
            List[i][1] = m.getTitle();
            List[i][2] = m.getStart_time();
            List[i][3] = m.getEnd_time();
            List[i][4] = m.getCount_complete();
            i++;
        }
        table = new JTable(List,title);
        jspane = new JScrollPane(table);
        setLayout(null);
        jspane.setBounds(10,5,810,360);
        add(jspane);

        //自定义
        p = new JPanel();
        p.setLayout(new GridLayout(1,4,5,5));
        p.add(t_testid);
        p.add(b_delete);
        p.add(b_new);
        p.add(b_score);
        p.setBounds(10,380,800,50);
        add(p,BorderLayout.SOUTH);

    }

//    public static void main(String[] args) {
//        Teacher_win t=new Teacher_win();
//    }
}
