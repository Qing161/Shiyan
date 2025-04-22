package org.example.Other_class;

public class Shiyan {
    private String id;
    private String title;
    private String start_time;
    private String end_time;
    private int count_complete;
    private String teacher;

    public Shiyan(String id, String title, String start_time, String end_time,int count_complete, String teacher){
        this.id=id;
        this.title=title;
        this.start_time=start_time;
        this.end_time=end_time;
        this.count_complete=count_complete;
        this.teacher=teacher;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public int getCount_complete() {
        return count_complete;
    }

    public String getTeacher() {
        return teacher;
    }
}
