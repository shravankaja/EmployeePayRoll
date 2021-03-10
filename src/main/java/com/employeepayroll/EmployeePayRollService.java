package com.employeepayroll;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class EmployeePayRollService {
    public int noOfEntries;
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
        employeePayRollService.readEmployeeDataFromFile();
        employeePayRollService.numberOfEntries();
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
            if (!Files.exists(newFilePath)) {
                Files.createFile(newFilePath);
                //FileWriter writer = new FileWriter(String.valueOf(newFilePath));
                BufferedWriter out = new BufferedWriter(
                        new FileWriter(String.valueOf(newFilePath)));
                for (EmployeeData obj : employeeDataArrayList) {
                    out.write("Employee Id : " + obj.ID + System.lineSeparator());
                    out.write("Employee Name : " + obj.name + System.lineSeparator());
                    out.write("Employee Salary : " + obj.salary + System.lineSeparator());
                    out.write("--EndOfRecord--" + System.lineSeparator());
                    numberOfEntries = numberOfEntries + 1;
                }
                out.close();
            } else if (Files.exists(newFilePath)) {
                for (EmployeeData obj : employeeDataArrayList) {
                    appendStrToFile(String.valueOf(newFilePath), "-" + System.lineSeparator());
                    appendStrToFile(String.valueOf(newFilePath), "Employee Id : " + obj.ID + System.lineSeparator());
                    appendStrToFile(String.valueOf(newFilePath), "Employee Name : " + obj.name + System.lineSeparator());
                    appendStrToFile(String.valueOf(newFilePath), "Employee Salary : " + obj.salary + System.lineSeparator());
                    appendStrToFile(String.valueOf(newFilePath), "--EndOfRecord--");
                    numberOfEntries = numberOfEntries + 1;
                }
            }
        }

        return numberOfEntries;
    }

    public static void appendStrToFile(String fileName, String str) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(str);
            out.close();
        } catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }


    public int readEmployeeDataFromFile() throws FileNotFoundException {
        int numberOfEntries = 0;
        int count = 0;
        Path employeePayRollDirectory = Paths.get("E:\\Eclipse_Practise\\EmployeePayRoll\\src\\main\\java\\com\\employeepayroll");
        File fileObject = employeePayRollDirectory.toFile();
        File[] listOfFiles = fileObject.listFiles();
        if (Files.isDirectory(employeePayRollDirectory)) {
            String newFile = employeePayRollDirectory + "/" + "EmployeePayRollDDetails.txt";
            Path newFilePath = Paths.get(newFile);
            if (Files.exists(newFilePath)) {
                File myObj = new File(String.valueOf(newFilePath));
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.equals("--EndOfRecord---")) count = count + 1;
                    System.out.println(data);
                }
                myReader.close();
            }
        }
        noOfEntries=count+1;
        return numberOfEntries = count + 1;
    }
    public void numberOfEntries() {
        System.out.println("Total No of records : "+noOfEntries);
    }


}
