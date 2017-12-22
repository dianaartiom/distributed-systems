package common;

import java.io.Serializable;

public class Employee implements Serializable {
    private String name;
    private String surname;
    private Double salary;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Double salary) {
        this.name = firstName;
        this.surname = lastName;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                '}';
    }
}
