package org.example.people_class;

public class Teacher extends Person{
    public Teacher(String id, String name, int age, char sex, String password) {
        this.setId(id);
        this.setAge(age);
        this.setName(name);
        this.setSex(sex);
        this.setPassword(password);
    }
}
