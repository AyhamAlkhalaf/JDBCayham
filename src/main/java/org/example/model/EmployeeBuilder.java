package org.example.model;

import java.util.Date;
//يقوم بانشاء اوبجكت وارجاعه
public class EmployeeBuilder {
    private int id;
    private String name;
    private boolean gander;
    private Date birthDate;
    private double salary;

    public EmployeeBuilder id(int id){
        this.id = id ;
        return this;
    }
    public EmployeeBuilder name(String name){
        this.name = name ;
        return this;
    }


    public EmployeeBuilder gender(boolean gander){
        this.gander = gander ;
        return this;
    }


    public EmployeeBuilder birthDate(Date date){
        this.birthDate = date ;
        return this;
    }

    public EmployeeBuilder salary(double salary){
        this.salary = salary ;
        return this;
    }

    public Employee build(){
        return  new Employee(id,name,gander,birthDate,salary);


    }




}
