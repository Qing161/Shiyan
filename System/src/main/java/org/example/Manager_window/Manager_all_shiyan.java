package org.example.Manager_window;


import org.example.ConDb.Shiyan_db;
import org.example.Other_class.Shiyan;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Manager_all_shiyan extends JFrame {
    Font labelFont = new Font("仿宋", Font.PLAIN, 20);
    Font textFieldFont = new Font("仿宋", Font.PLAIN, 18);
    Font buttonFont = new Font("仿宋", Font.BOLD, 20);
    Shiyan_db shiyan_db=new Shiyan_db();
    private List<Shiyan> shiyan_list=shiyan_db.getShiyan_list();

    public Manager_all_shiyan(){
        setTitle("实验列表");
        setSize(600, 400);
        setLocation(700, 300);
        init();
        setVisible(true);
    }

    public void init(){

        int size=shiyan_list.size();
        Object[][] shiyanList = new Object[size][6];
        int i=0;
        for(Shiyan s:shiyan_list){
            shiyanList[i][0]=s.getId();
            shiyanList[i][1]=s.getTitle();
            shiyanList[i][2]=s.getStart_time();
            shiyanList[i][3]=s.getEnd_time();
            shiyanList[i][4]=s.getCount_complete();
            shiyanList[i][5]=s.getTeacher();
            i++;
        }
        Object[] table_name= { "实验id","实验名称","发布时间","结束时间","完成人数","发布教师"};
        JTable table=new JTable(shiyanList,table_name);
        JScrollPane pane = new JScrollPane(table);
        this.add(pane);
    }
//
//    public static void main(String[] args) {
//        Manager_all_shiyan a=new Manager_all_shiyan();
//    }
}
