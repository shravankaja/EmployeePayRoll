package com.employeepayroll;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class EmployeePayRollFileOperationsTest {

	@Test
	void givenEntriesOfEmployeesWeShouldGetBackNoOfEntriesHaveBeenSentToFile() throws IOException {
		System.out.println("Welcome to Employee PayRoll Servie");
		ArrayList<EmployeeData> employeePayRollList = new ArrayList<>();
		EmployeePayRollService employeePayRollService = new EmployeePayRollService(employeePayRollList);
		Scanner consoleInput = new Scanner(System.in);
		employeePayRollService.readEmployeePayRollData(consoleInput);
		employeePayRollService.writeEmployeeDataToConsole();
		employeePayRollService.writeEmployeeDataToFile();
		int count = employeePayRollService.writeEmployeeDataToFile();
		Assertions.assertEquals(2, count);
	}

	@Test
	void givenFileToReadWeShouldBeAbleToObtainNoofEntries() throws FileNotFoundException {
		ArrayList<EmployeeData> employeePayRollList = new ArrayList<>();
		EmployeePayRollService employeePayRollService = new EmployeePayRollService(employeePayRollList);
		Scanner consoleInput = new Scanner(System.in);
		int count = employeePayRollService.readEmployeeDataFromFile();
		Assertions.assertEquals(2, count);
	}
}