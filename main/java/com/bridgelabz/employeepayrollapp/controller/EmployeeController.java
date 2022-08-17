package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeeModel;
import com.bridgelabz.employeepayrollapp.service.IEmployeeService;
import com.bridgelabz.employeepayrollapp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;

    @PostMapping("/addemployee")
    public EmployeeModel addEmployee(@RequestBody EmployeeDTO employeeDTO,
                                     @RequestParam Long departmentId) {
        return employeeService.addEmployee(employeeDTO, departmentId);
    }

    @GetMapping("/getallemployees")
    public List<EmployeeModel> getAllEmployeeData(@RequestHeader String token) {
        return employeeService.getAllEmployeeData(token);
    }

    @PutMapping("/updateemployee/{id}")
    public EmployeeModel updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO
            , @RequestHeader String token, @RequestParam Long departmentId) {
        return employeeService.updateEmployeeDetails(id, employeeDTO, token, departmentId);
    }

    @DeleteMapping("/deleteemployee/{id}")
    public EmployeeModel deleteEmployee(@PathVariable Long id
            , @RequestHeader String token) {
        return employeeService.deleteEmployee(id, token);
    }

    @PostMapping("/login")
    public Response login(@RequestParam String emailId,
                          @RequestParam String password) {
        return employeeService.login(emailId, password);
    }


}
