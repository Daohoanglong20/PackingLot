package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Employee {
    private int eID;
    private String account;
    private String department;
    private String address;
    private String birthdate;
    private String email;
    private String name;
    private String phone;
    private boolean sex;
    private String password;


    public Employee(int eID, String account, String department, String address, String birthdate, String email,
                    String name, String phone, boolean sex) {

        this.eID = eID;
        this.account = account;
        this.department = department;
        this.address = address;
        this.birthdate = birthdate;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
    }

    public Employee(String account, String department, String address, String birthdate, String email,
                    String name, String phone, boolean sex) {

        this.account = account;
        this.department = department;
        this.address = address;
        this.birthdate = birthdate;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
    }

    public Employee(int eID, String account, String department, String address, String birthdate, String email, String name, String phone, boolean sex, String password) {
        this.eID = eID;
        this.account = account;
        this.department = department;
        this.address = address;
        this.birthdate = birthdate;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.password = password;
    }

    public Employee(String account, String department, String address, String birthdate, String email, String name, String phone, boolean sex, String password) {
        this.account = account;
        this.department = department;
        this.address = address;
        this.birthdate = birthdate;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.password = password;
    }

    public Employee() {

    }

}
