package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeeModel;
import com.bridgelabz.employeepayrollapp.util.Response;

import java.util.List;

public interface IEmployeeService {
    EmployeeModel addEmployee(EmployeeDTO employeeDTO, Long departmentId);

    List<EmployeeModel> getAllEmployeeData(String token);

    EmployeeModel updateEmployeeDetails(Long id, EmployeeDTO employeeDTO, String token, Long departmentId);

    EmployeeModel deleteEmployee(Long id, String token);

    Response login(String emailId, String password);
}
