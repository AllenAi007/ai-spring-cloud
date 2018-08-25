package com.ai.spring.cloud.zuul.model;

import java.util.Calendar;

public class Employee implements Cloneable {

    public String name;
    public int salary;
    public Calendar employedDate;

    public Employee(String name, int salary, Calendar employedDate) {
        super();
        this.name = name;
        this.salary = salary;
        this.employedDate = employedDate;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", salary=" + salary
                + ", employedDate=" + employedDate.getTime() + "]";
    }

    //implements clone()
    public Employee clone() throws CloneNotSupportedException {
        Employee cloned = (Employee) super.clone();
//        cloned.employedDate = (Calendar) employedDate.clone(); //clone this object
        return cloned;
    }

    /**
     * @param args
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        Calendar now = Calendar.getInstance();
        Employee original = new Employee("Mike", 5000, now);

        Employee copy = (Employee) original.clone();
        copy.name = "Kite";
        copy.salary = 8000;
        copy.employedDate.set(2010, 01, 01);

        System.out.println("Original-> " + original.toString());
        System.out.println("Copy-> " + copy.toString());
    }


}
