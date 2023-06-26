package com.simform.EmployeeManagementSystem.service;

import com.simform.EmployeeManagementSystem.entity.*;
import com.simform.EmployeeManagementSystem.repository.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.context.request.*;

import java.util.*;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void addEmployee(Employee emp) {
        employeeRepository.save(emp);
    }

    public List<Employee> getAllEmp() {
        return employeeRepository.findAll();
    }

    public Employee getEmpById(int id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        return emp.orElse(null);
    }

    public void removeVerificationMessageFromSession() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            session.removeAttribute("msg");
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteEmp(int id) {
        employeeRepository.deleteById(id);
    }

}
