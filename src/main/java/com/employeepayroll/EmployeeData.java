package com.employeepayroll;

public class EmployeeData {
    public String name;
    public double salary;
    public int ID;

    public EmployeeData(String name, double salary, int ID) {
        this.name = name;
        this.salary = salary;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "EmployeeData{" + "name='" + name + '\'' + ", salary=" + salary + ", ID=" + ID + '}';
    }
}
