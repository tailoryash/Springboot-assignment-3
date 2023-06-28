package com.simform.EmployeeManagementSystem.service;

import com.simform.EmployeeManagementSystem.entity.*;
import com.simform.EmployeeManagementSystem.exception.*;
import com.simform.EmployeeManagementSystem.repository.*;
import jakarta.servlet.http.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.context.request.*;

import java.util.*;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void addEmployee(Employee emp) {
        employeeRepository.save(emp);
    }

    public List<Employee> getAllEmp() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees == null) {
            log.error("Not Getting List of Employee.");
            throw new UserNotFoundException();
        } else {
            return employees;
        }
    }

    public Employee getEmpById(int id) {
        try {
            Employee emp = employeeRepository.findById(id).stream().findFirst().get();
            return emp;
        } catch (NoSuchElementException e) {
            log.error("User not found with id : " + id);
            throw new UserNotFoundException();
        }
    }


    public void deleteEmp(int id) {
        Employee empById = getEmpById(id);

        employeeRepository.deleteById(id);
        log.info("Employee details deleted successfully of this id :" + id);
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

}


