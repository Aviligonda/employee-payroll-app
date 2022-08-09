package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeeModel;

import java.util.List;

public interface IEmployeeService {
    EmployeeModel addEmployee(EmployeeDTO employeeDTO);

    List<EmployeeModel> getAllEmployeeData();

    EmployeeModel updateEmployeeDetails(Long id, EmployeeDTO employeeDTO);
}
