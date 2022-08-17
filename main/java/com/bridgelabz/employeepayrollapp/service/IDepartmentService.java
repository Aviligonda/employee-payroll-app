package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.DepartmentDTO;
import com.bridgelabz.employeepayrollapp.model.DepartmentModel;

import java.util.List;

public interface IDepartmentService {
    DepartmentModel addDepartment(DepartmentDTO departmentDTO);

    DepartmentModel updateDepartment(DepartmentDTO departmentDTO, Long id);

    List<DepartmentModel> getAllDepartments();

    DepartmentModel deleteDepartment(Long id);
}
