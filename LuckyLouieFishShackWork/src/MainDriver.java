import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.Scanner;


import java.nio.charset.StandardCharsets;

public class MainDriver {
    public static void main(String []args) {
        EmployeeList employeeList = new EmployeeList();
        getEmployeeInfo(employeeList);
        for(int i = 0; i < employeeList.getListSize(); i ++) {
            System.out.println(employeeList.getCertainEmployee(i).getFullName());
            System.out.println();
        }
    }

    private static void getEmployeeInfo(EmployeeList employeeList) {
        try (InputStream in = MainDriver.class
                .getClassLoader()
                .getResourceAsStream("LuckyLouieEmployees.txt")) {

            if (in == null) {
                throw new FileNotFoundException("Resource not found: LuckyLouieEmployees.txt");
            }

            try (Scanner inputStream = new Scanner(in, StandardCharsets.UTF_8)) {
                while (inputStream.hasNextLine()) {
                    String lineScan = inputStream.nextLine().trim();
                    String[] employeeInfo = lineScan.split("\\s*<>\\s*");
                    employeeList.addEmployee(new Employee(employeeInfo));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading employees: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
