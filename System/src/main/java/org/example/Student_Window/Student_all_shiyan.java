package org.example.Student_Window;

import org.example.ConDb.Score_db;
import org.example.ConDb.Shiyan_db;
import org.example.Other_class.Shiyan;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Student_all_shiyan extends JFrame {
    Font labelFont = new Font("仿宋", Font.PLAIN, 20);
    Font textFieldFont = new Font("仿宋", Font.PLAIN, 18);
    Font buttonFont = new Font("仿宋", Font.BOLD, 20);
    Shiyan_db shiyan_db=new Shiyan_db();
    private List<Shiyan> shiyan_list=shiyan_db.getShiyan_list();

    public Student_all_shiyan(String id){
        setTitle("实验列表");
        setSize(600, 400);
        setLocation(700, 300);
        init(id);
        setVisible(true);
    }

    public void init(String id){
        int size=shiyan_list.size();
        Object[][] shiyanList = new Object[size][5];
        int i=0;
        Score_db score_db=new Score_db();
        for(Shiyan s:shiyan_list){
            shiyanList[i][0]=s.getTitle();
            shiyanList[i][1]=s.getEnd_time();
            if(score_db.complete(id,s.getId())){
                shiyanList[i][2]="已完成";
            }else {
                shiyanList[i][2]="未完成";
            }
            i++;
        }
        Object[] table_name= { "实验名称","结束时间","完成情况"};
        JTable table=new JTable(shiyanList,table_name);
        JScrollPane pane = new JScrollPane(table);
        this.add(pane);
    }
//
//    public static void main(String[] args) {
//        All_shiyan a=new All_shiyan();
//    }
}
