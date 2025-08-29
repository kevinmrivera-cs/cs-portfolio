/**
 * This class collects employee info
 */
public class Employee {
    private String fullName;
    private String startDate;
    private boolean isMinor;
    private int callOuts;
    private int lates;
    private int leaveEarly;
    private int currentAttWriteUp;

    public Employee(String[] employeeInfo) {
        this.fullName = employeeInfo[0];
        //this.startDate = employeeInfo[1];
        //this.isMinor = Boolean.parseBoolean(employeeInfo[3]);
        callOuts = 0;
        lates = 0;
        leaveEarly = 0;
        currentAttWriteUp = 0;
    }

    //Copy constructor
    public Employee(Employee employee) {
        this.fullName = employee.fullName;
        this.startDate = employee.startDate;
        this.isMinor = employee.isMinor;
    }

    //These are the getter methods
    public String getStartDate() {return startDate;}

    public boolean isMinor() {return isMinor;}

    public String getFullName() {return fullName;}

    //These are the setter methods
    public void setFullName(String fullName) {this.fullName = fullName;}

    public void setStartDate(String startDate) {this.startDate = startDate;}

    public void setMinor(boolean isminor) {isMinor = isminor;}

    @Override
    public String toString() {
        return "Name: " + fullName + "\nStart date: " + startDate + "\nLates amount : " + lates + "\nCall outs amount: " +
                callOuts + "Current attendance write up: " + currentAttWriteUp;
    }
}
