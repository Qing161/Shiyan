package org.example.people_class;

public class Student extends Person {
    private String sdept;

    public String getSdept() {
        return sdept;
    }

    public Student(String id, String name, int age, char sex, String sdept,String password){
        this.setId(id);
        this.setAge(age);
        this.setName(name);
        this.setSex(sex);
        this.sdept=sdept;
        this.setPassword(password);
    }

}
