package com.netcracker.model;


public class Person {


    private String lastName;
    private String firsName;
    private int age;
    private int salaryLevel;

    private String email;
    private String placeOfWork;

    public void personSet(Person person) {
        this.lastName = person.lastName;
        this.firsName = person.firsName;
        this.age = person.age;
        this.salaryLevel = person.salaryLevel;
        this.email = person.email;
        this.placeOfWork = person.placeOfWork;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", firsName='" + firsName + '\'' +
                ", age=" + age +
                ", salaryLevel=" + salaryLevel +
                ", email='" + email + '\'' +
                ", placeOfWork='" + placeOfWork + '\'' +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(int salaryLevel) {
        this.salaryLevel = salaryLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }
}
