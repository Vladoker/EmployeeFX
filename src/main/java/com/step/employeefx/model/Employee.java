package com.step.employeefx.model;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {

    private SimpleStringProperty name,surname,address,date,sex;
    
    private SimpleDoubleProperty salary;


    public Employee(String name, String surname, String sex, String date, String address, double salary) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.address = new SimpleStringProperty(address);
        this.sex = new SimpleStringProperty(sex);
        this.salary = new SimpleDoubleProperty(salary);
        this.date = new SimpleStringProperty(date);
    }
   

    public String getName(){
        return name.get();
    }
    public String getSurname(){
        return surname.get();
    }
    public String getSex() {
        return sex.get();
    }
    public double getSalary(){
        return this.salary.get();
    }
    public String getAddress(){
        return this.address.get();
    }
    public String getDate(){
        return date.get();
    }

    
    public String toString() {
        return "Name: " + name.get() + "\r\nSurname: " + surname.get() + "\r\nSex: " + sex.get() + "\r\nAddress: " + address.get() + "\r\nSalary: " + salary.get() +
               "\r\nDate: " + date.get();
    }


    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Employee)) return false;

        Employee temp = (Employee) obj;

        return this.name.equals(temp.getName())
            && this.surname.get().equals(temp.getSurname())
            && this.sex.get() == temp.getSex();
    }
    @Override
    public int hashCode() {
        int result = 22;
        result = 228 * result + name.hashCode();
        result = 228 * result + surname.hashCode();
        result = 228 * result + sex.hashCode();
        return result;
    }

   
}