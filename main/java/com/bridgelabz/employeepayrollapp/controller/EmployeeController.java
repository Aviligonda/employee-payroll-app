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

    //UC1
    @PostMapping("/addemployee")
    public EmployeeModel addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.addEmployee(employeeDTO);
    }

    //UC2
    @GetMapping("/getallemployees")
    public List<EmployeeModel> getAllEmployeeData() {
        return employeeService.getAllEmployeeData();
    }

    //UC3
    @PutMapping("/updateemployee/{id}")
    public EmployeeModel updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployeeDetails(id, employeeDTO);
    }

    //UC4
    @DeleteMapping("/deleteemployee/{id}")
    public EmployeeModel deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    //UC5
    @PostMapping("/login")
    public Response login(@RequestParam String emailId, @RequestParam String password) {
        return employeeService.login(emailId, password);
    }


}
