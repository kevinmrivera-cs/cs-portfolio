/**
 * This class collects all employee info into a list
 */

import java.util.ArrayList;
import java.util.List;

public class EmployeeList {
    private List<Employee> employeeList = new ArrayList<>();
    private int listSize = employeeList.size();

    public int getListSize() {
        return listSize;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        listSize += 1;
    }

    public void deleteEmployee(int index) {
        employeeList.remove(index);
        listSize -= 1;
    }

    public Employee getCertainEmployee(int i) {
        return employeeList.get(i);
    }

    @Override
    public String toString() {
        String message = "";

        for(int i = 0; i < listSize; i++) {
            message += employeeList.get(i).toString() + "\n\n";
        }
        return message;
    }
}
