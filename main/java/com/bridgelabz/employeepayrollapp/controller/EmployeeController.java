package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeeModel;
import com.bridgelabz.employeepayrollapp.service.IEmployeeService;
import com.bridgelabz.employeepayrollapp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/home")
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;

    @PostMapping("/addemployee")
    public EmployeeModel addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO,
                                     @RequestParam Long departmentId) {
        return employeeService.addEmployee(employeeDTO, departmentId);
    }

    @GetMapping("/getallemployees")
    public List<EmployeeModel> getAllEmployeeData(@RequestHeader String token) {
        return employeeService.getAllEmployeeData(token);
    }

    @PutMapping("/updateemployee/{id}")
    public EmployeeModel updateEmployee(@Valid @PathVariable Long id, @RequestBody EmployeeDTO employeeDTO
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

    @GetMapping("/sortingByFirstName")
    public List<EmployeeModel> sortingByFirstName() {
        return employeeService.sorting();
    }

    @GetMapping("/findByCompanyName")
    public List<EmployeeModel> findByCompanyName(@RequestParam String companyName) {
        return employeeService.findByCompanyName(companyName);
    }

    @GetMapping("/findByFirstName")
    public List<EmployeeModel> findByName(@RequestParam String firstName) {
        return employeeService.findByFirstName(firstName);
    }

    @GetMapping("/orderByLatName")
    public List<EmployeeModel> orderByLastName() {
        return employeeService.orderByLastName();
    }
}
