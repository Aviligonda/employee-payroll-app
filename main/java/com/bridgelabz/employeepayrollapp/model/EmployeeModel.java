package com.bridgelabz.employeepayrollapp.model;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Employee")
@Data
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private long salary;
    private String companyName;
    private String department;
    private String emailId;
    private String password;
    private LocalDateTime registerDate;
    private LocalDateTime updatedDate;

    public EmployeeModel(EmployeeDTO employeeDTO) {
        this.firstName = employeeDTO.getFirstName();
        this.lastName = employeeDTO.getLastName();
        this.age = employeeDTO.getAge();
        this.salary = employeeDTO.getSalary();
        this.companyName = employeeDTO.getCompanyName();
        this.department = employeeDTO.getDepartment();
        this.emailId = employeeDTO.getEmailId();
        this.password = employeeDTO.getPassword();
    }

    public EmployeeModel() {

    }
}
