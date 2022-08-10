package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeeModel;
import com.bridgelabz.employeepayrollapp.util.Response;

import java.util.List;

public interface IEmployeeService {
    EmployeeModel addEmployee(EmployeeDTO employeeDTO);

    List<EmployeeModel> getAllEmployeeData();

    EmployeeModel updateEmployeeDetails(Long id, EmployeeDTO employeeDTO);

    EmployeeModel deleteEmployee(Long id);

    Response login(String emailId, String password);
}
