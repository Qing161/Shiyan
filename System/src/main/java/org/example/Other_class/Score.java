//WXY
package org.example.Other_class;

public class Score {
    int id;
    String id_test,sid;
    int grade;
    String saying;

    public Score(int id, String id_test, String sid, int grade, String saying) {
        this.id = id;
        this.id_test = id_test;
        this.sid = sid;
        this.grade = grade;
        this.saying=saying;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_test() {
        return id_test;
    }

    public void setId_test(String id_test) {
        this.id_test = id_test;
    }

    public String getSidid() {
        return sid;
    }

    public void setSidid(String sidid) {
        this.sid = sidid;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSaying() {
        return saying;
    }
}

