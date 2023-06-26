package com.simform.EmployeeManagementSystem.repository;

import com.simform.EmployeeManagementSystem.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
