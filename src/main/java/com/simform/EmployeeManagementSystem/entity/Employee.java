package com.simform.EmployeeManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EMP_SYSTEM", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String department;
    private int salary;
}
