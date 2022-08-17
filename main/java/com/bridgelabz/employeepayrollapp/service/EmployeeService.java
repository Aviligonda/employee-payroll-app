package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.exception.EmployeeNotFoundException;
import com.bridgelabz.employeepayrollapp.model.DepartmentModel;
import com.bridgelabz.employeepayrollapp.model.EmployeeModel;
import com.bridgelabz.employeepayrollapp.repository.DepartmentRepository;
import com.bridgelabz.employeepayrollapp.repository.EmployeeRepository;
import com.bridgelabz.employeepayrollapp.util.Response;
import com.bridgelabz.employeepayrollapp.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    MailService mailService;
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public EmployeeModel addEmployee(EmployeeDTO employeeDTO, Long departmentId) {
        Optional<DepartmentModel> isDepartment = departmentRepository.findById(departmentId);
        if (isDepartment.isPresent()) {
            EmployeeModel employeeModel = new EmployeeModel(employeeDTO);
            employeeModel.setDepartment(isDepartment.get());
            employeeModel.setRegisterDate(LocalDateTime.now());
            employeeRepository.save(employeeModel);
            String body = "Employee added Successfully with Employee id is :" + employeeModel.getId();
            String subject = "Employee Registration Successfully";
            mailService.send(employeeDTO.getEmailId(), body, subject);
            return employeeModel;
        } else {
            throw new EmployeeNotFoundException(400, "Department is not found");
        }
    }

    @Override
    public List<EmployeeModel> getAllEmployeeData(String token) {
        Long empId = tokenUtil.decodeToken(token);
        Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(empId);
        if (isEmployeePresent.isPresent()) {
            List<EmployeeModel> getEmployees = employeeRepository.findAll();
            if (getEmployees.size() > 0) {
                return getEmployees;
            } else {
                throw new EmployeeNotFoundException(400, "No Employees Is There");
            }
        }
        throw new EmployeeNotFoundException(400, "Employee Is Not Found");
    }

    @Override
    public EmployeeModel updateEmployeeDetails(Long id, EmployeeDTO employeeDTO, String token, Long departmentId) {
        Long empId = tokenUtil.decodeToken(token);
        Optional<DepartmentModel> isDepartment = departmentRepository.findById(departmentId);
        if (isDepartment.isPresent()) {
            Optional<EmployeeModel> isEmpPresent = employeeRepository.findById(empId);
            if (isEmpPresent.isPresent()) {
                Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(id);
                if (isEmployeePresent.isPresent()) {
                    isEmployeePresent.get().setFirstName(employeeDTO.getFirstName());
                    isEmployeePresent.get().setLastName(employeeDTO.getLastName());
                    isEmployeePresent.get().setAge(employeeDTO.getAge());
                    isEmployeePresent.get().setSalary(employeeDTO.getSalary());
                    isEmployeePresent.get().setCompanyName(employeeDTO.getCompanyName());
                    isEmployeePresent.get().setUpdatedDate(LocalDateTime.now());
                    isEmployeePresent.get().setDepartment(isDepartment.get());
                    employeeRepository.save(isEmployeePresent.get());
                    String body = "Employee Updated Successfully with Employee id is :" + isEmployeePresent.get().getId();
                    String subject = "Employee Updated Successfully..";
                    mailService.send(employeeDTO.getEmailId(), body, subject);
                    return isEmployeePresent.get();
                } else {
                    throw new EmployeeNotFoundException(400, "Employee is Not Found");
                }
            }
            throw new EmployeeNotFoundException(400, "Wrong token");
        }
        throw new EmployeeNotFoundException(400, "Department is not found with this ID");
    }

    @Override
    public EmployeeModel deleteEmployee(Long id, String token) {
        Long empId = tokenUtil.decodeToken(token);
        Optional<EmployeeModel> isEmpPresent = employeeRepository.findById(empId);
        if (isEmpPresent.isPresent()) {
            Optional<EmployeeModel> deleteEmployee = employeeRepository.findById(id);
            if (deleteEmployee.isPresent()) {
                employeeRepository.delete(deleteEmployee.get());
                String body = "Employee Deleted Successfully with Employee id is :" + isEmpPresent.get().getId();
                String subject = "Employee Deleted..";
                mailService.send(isEmpPresent.get().getEmailId(), body, subject);
                return deleteEmployee.get();
            } else {
                throw new EmployeeNotFoundException(400, "Employee is Not Found");
            }
        }
        throw new EmployeeNotFoundException(400, "Token is wrong");
    }

    @Override
    public Response login(String emailId, String password) {
        Optional<EmployeeModel> isEmailPresent = employeeRepository.findByEmailId(emailId);
        if (isEmailPresent.isPresent()) {
            if (isEmailPresent.get().getPassword().equals(password)) {
                String token = tokenUtil.createToken(isEmailPresent.get().getId());
                return new Response(200, "LoginSuccess", token);
            } else {
                throw new EmployeeNotFoundException(400, "Password is wrong");
            }
        }
        throw new EmployeeNotFoundException(400, "Employee is not found");
    }

}
