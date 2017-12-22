package common;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;

public class Command {
    private Gson gson = new Gson();

    public String getAll(ArrayList<Employee> employees){
        return gson.toJson(employees);
    }

    public String getSortedEmployees(ArrayList<Employee> employees, Message message) {

        if (message.getOrder().equals(EOrder.ASCENDING))
            employees = sortEmployeesAscendingOrder(employees,message.getFieldName());
        else if(message.getOrder().equals(EOrder.DESCENDING))
            employees = sortEmployeesDescendingOrder(employees,message.getFieldName());

        return gson.toJson(employees);
    }

    private ArrayList<Employee> sortEmployeesAscendingOrder(ArrayList<Employee> employees, EFieldName sortBy) {

        switch (sortBy.toString()) {
            case "name":
                employees.sort(Comparator.comparing(Employee::getName));
                break;
            case "surname":
                employees.sort(Comparator.comparing(Employee::getSurname));
                break;
            case "salary":
                employees.sort(Comparator.comparing(Employee::getSalary));
                break;
        }

        return employees;
    }

    private ArrayList<Employee> sortEmployeesDescendingOrder(ArrayList<Employee> employees, EFieldName sortBy) {

        switch (sortBy.toString()) {
            case "name":
                employees.sort(Comparator.comparing(Employee::getName).reversed());
                break;
            case "surname":
                employees.sort(Comparator.comparing(Employee::getSurname).reversed());
                break;
            case "salary":
                employees.sort(Comparator.comparing(Employee::getSalary).reversed());
                break;
        }

        return employees;
    }
}
