package com.employeepayroll;

import java.io.*;
import java.nio.file.*;
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

    public static void main(String Args[]) throws IOException {
        System.out.println("Welcome to Employee PayRoll Servie");
        ArrayList<EmployeeData> employeePayRollList = new ArrayList<>();
        EmployeePayRollService employeePayRollService = new EmployeePayRollService(employeePayRollList);
        Scanner consoleInput = new Scanner(System.in);
        employeePayRollService.readEmployeePayRollData(consoleInput);
        employeePayRollService.writeEmployeeDataToConsole();
        employeePayRollService.writeEmployeeDataToFile();
    }

    public void writeEmployeeDataToConsole() {
        System.out.println("Employee details are :" + employeeDataArrayList);
    }

    public int writeEmployeeDataToFile() throws IOException {
        int numberOfEntries = 0;
        Path employeePayRollDirectory = Paths.get("E:\\Eclipse_Practise\\EmployeePayRoll\\src\\main\\java\\com\\employeepayroll");
        File fileObject = employeePayRollDirectory.toFile();
        File[] listOfFiles = fileObject.listFiles();
        if (Files.isDirectory(employeePayRollDirectory)) {
            String newFile = employeePayRollDirectory + "/" + "EmployeePayRollDDetails.txt";
            Path newFilePath = Paths.get(newFile);
            Files.deleteIfExists(newFilePath);
            Files.createFile(newFilePath);
            FileWriter writer = new FileWriter(String.valueOf(newFilePath));
            for (EmployeeData obj : employeeDataArrayList) {
                writer.write("Employee Id : " + obj.ID + System.lineSeparator());
                writer.write("Employee Name : " + obj.name + System.lineSeparator());
                writer.write("Employee Salary : " + obj.salary + System.lineSeparator());
                writer.write("------------------------------------------------------------------------------");
                numberOfEntries = numberOfEntries + 1;
            }
            writer.close();
        }
        return numberOfEntries;
    }
}
