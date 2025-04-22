package org.example.Student_Window;

import org.example.ConDb.Score_db;
import org.example.ConDb.Shiyan_db;
import org.example.Other_class.Score;
import org.example.Other_class.Shiyan;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Student_each_shiyan extends JFrame {
    Font labelFont = new Font("仿宋", Font.PLAIN, 20);
    Font textFieldFont = new Font("仿宋", Font.PLAIN, 18);
    Font buttonFont = new Font("仿宋", Font.BOLD, 20);
    Shiyan_db shiyan_db=new Shiyan_db();
    private List<Shiyan> shiyan_list=shiyan_db.getShiyan_list();
    Score_db score_db=new Score_db();
    List<Score> scores_saying=score_db.getscore_list();


    public Student_each_shiyan(String id,String sid){
        setTitle("实验详情");
        setSize(400, 500);
        setLocation(800, 200);
        init(id,sid);
        setVisible(true);
    }

    private void init(String id,String sid) {
        this.setLayout(null);  //自定义布局

        JPanel p=new JPanel();
//        p.setBackground(new Color(205,205,203));
        p.setBounds(0, 0, 400, 500);
//        p.setLocation(270,350);
        p.setLayout(new GridLayout(10,1));

        for(Shiyan k:shiyan_list){
            if(k.getId().equals(id)){
//                System.out.println(k.getId());
//                System.out.println(k.getTitle());
//                System.out.println(k.getTeacher());
//                System.out.println(k.getStart_time());
//                System.out.println(k.getEnd_time());
//                System.out.println(k.getCount_complete());

                String saying=" ";
                int grade=0;

                String id_test=k.getId();
                for(Score s:scores_saying){
//                    System.out.println("sid  "+s.getSidid());
//                    System.out.println("id  "+id);
//                    System.out.println("shiyan id_test  "+k.getId());
//                    System.out.println("s.get  "+s.getId_test());
//                    System.out.println("ad;lsajf;ashe");
                    if(id_test.equals(s.getId_test())){
                        saying=s.getSaying();
                        grade=s.getGrade();
                    }
                }

                JLabel l1=new JLabel("实验ID为："+k.getId());
                JLabel l2=new JLabel("实验名称为："+k.getTitle());
                JLabel l3=new JLabel("实验发布者为："+k.getTeacher());
                JLabel l4=new JLabel("实验发布时间为："+k.getStart_time());
                JLabel l5=new JLabel("实验截止时间为："+k.getEnd_time());
                JLabel l6;
                JLabel l7;
                JLabel l8;
                if(score_db.complete(sid,k.getId())){
                    l8=new JLabel("实验完成情况：已完成");
                }else {
                    l8=new JLabel("实验完成情况：未完成");
                }
                if(grade==0){
                    l6=new JLabel("实验最终得分为：未打分");
                    l7=new JLabel("教师评语为：未评价");
                }else {
                    l6=new JLabel("实验最终得分为："+grade);
                    l7=new JLabel("教师评语为："+saying);
                }


                l1.setFont(labelFont);
                l2.setFont(labelFont);
                l3.setFont(labelFont);
                l4.setFont(labelFont);
                l5.setFont(labelFont);
                l6.setFont(labelFont);
                l7.setFont(labelFont);
                l8.setFont(labelFont);


                p.add(l1);
                p.add(l2);
                p.add(l3);
                p.add(l4);
                p.add(l5);
                p.add(l8);
                p.add(l6);
                p.add(l7);

            }
        }


        this.add(p);

    }

//    public static void main(String[] args) {
//        Student_each_shiyan p=new Student_each_shiyan("123","2321");
//    }
}
