package com.simform.EmployeeManagementSystem.controller;

import com.simform.EmployeeManagementSystem.dto.*;
import com.simform.EmployeeManagementSystem.entity.*;
import com.simform.EmployeeManagementSystem.service.*;
import jakarta.servlet.http.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/employees")
public class EmpController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String home(Model model) {
        List<EmpDTO> allEmp = employeeService.getAllEmp().stream().map(employee -> modelMapper.map(employee, EmpDTO.class)).collect(Collectors.toList());
        model.addAttribute("emp", allEmp);
        model.addAttribute("employeeService", employeeService);
        log.info("Employee details fetched succesfully !");

        return "index";
    }

    @GetMapping("/add")
    public String addEmpForm() {
        return "add_emp";
    }

    @PostMapping("/register")
    public String empRegister(@ModelAttribute Employee emp, @RequestParam(value = "submitButton") String submitButton, HttpSession session) {
        if (submitButton != null) session.setAttribute("msg", "Employee Added Successfully !");
        employeeService.addEmployee(emp);
        log.info("Employee details added succesfully !");
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Employee empById = employeeService.getEmpById(id);
        model.addAttribute("emp", empById);
        log.info("Employee details feteched succesfully for update of this id :" + id);
        return "edit";
    }

    @PostMapping("/update")
    public String updateEmp(@ModelAttribute Employee emp, HttpSession session) {
        employeeService.addEmployee(emp);
        session.setAttribute("msg", "Employee Data updated Successfully!");
        log.warn("Employee details updated succesfully of this id :" + emp.getId());
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable int id, HttpSession session) {
        employeeService.deleteEmp(id);
        session.setAttribute("msg", "Employee Data deleted Successfully!");
        return "redirect:/employees";
    }
}
