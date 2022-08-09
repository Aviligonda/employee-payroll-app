package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeeModel;

public interface IEmployeeService {
    EmployeeModel addEmployee(EmployeeDTO employeeDTO);
}
