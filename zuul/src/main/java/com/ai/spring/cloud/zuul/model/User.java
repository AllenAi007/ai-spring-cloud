package com.ai.spring.cloud.zuul.model;

public class User implements Cloneable {

    private String username;
    private String password;
    private Course course;

    private int[] classRoom;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Course course = new Course();
        course.setName("Math");
        course.setScore(88.25);
        int[] a = new int[2];
        a[0] = 10;
        a[1] = 20;
        course.setA(a);
        User user = new User("Zhang San", "ABC123");
        user.course = course;
        user.classRoom = new int[4];
        user.classRoom[0] = 1;
        user.classRoom[1] = 1;
        user.classRoom[2] = 1;
        user.classRoom[3] = 1;
        System.out.println("Before clone");

        User cloneUser = (User) user.clone();
        cloneUser.course.setName("Article");
        user.classRoom = new int[2];

        System.out.println("after clone");

    }
}
