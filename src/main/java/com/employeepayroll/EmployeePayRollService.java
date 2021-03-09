package com.employeepayroll;

import java.util.*;

public class EmployeePayRollService {

    private ArrayList<EmployeeData> employeeDataArrayList;

    public EmployeePayRollService(ArrayList<EmployeeData> employeeDataArrayList) {
        this.employeeDataArrayList = employeeDataArrayList;
    }

    public void readEmployeePayRollData(Scanner consoleInput) {
        System.out.println("Enter Employee Name :");
        String name = consoleInput.nextLine();
        System.out.println("Enter Employee Salary :");
        double salary = consoleInput.nextDouble();
        System.out.println("Enter Employee Id :");
        int id = consoleInput.nextInt();
        employeeDataArrayList.add(new EmployeeData(name, salary, id));
    }

    public static void main(String Args[]) {
        System.out.println("Welcome to Employee PayRoll Servie");
        ArrayList<EmployeeData> employeePayRollList = new ArrayList<>();
        EmployeePayRollService employeePayRollService = new EmployeePayRollService(employeePayRollList);
        Scanner consoleInput = new Scanner(System.in);
        employeePayRollService.readEmployeePayRollData(consoleInput);
        employeePayRollService.writeEmployeeDataToConsole();
    }

    public void writeEmployeeDataToConsole() {
        System.out.println("Employee details are :" + employeeDataArrayList);
    }
}
