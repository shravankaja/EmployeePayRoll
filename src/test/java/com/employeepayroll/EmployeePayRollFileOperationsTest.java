package com.employeepayroll;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class EmployeePayRollFileOperationsTest {

    @Test
    void givenEntriesOfEmployeesWeShouldGetBackNoOfEntriesHaveBeenSentToFile() throws IOException {
        ArrayList<EmployeeData> employeePayRollList = new ArrayList<>();
        EmployeePayRollService employeePayRollService = new EmployeePayRollService(employeePayRollList);
        Scanner consoleInput = new Scanner(System.in);
        employeePayRollService.readEmployeePayRollData(consoleInput);
        employeePayRollService.writeEmployeeDataToConsole();
        int count = employeePayRollService.writeEmployeeDataToFile();
        Assertions.assertEquals(1, count);
    }
}