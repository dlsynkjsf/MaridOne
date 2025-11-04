package org.example.maridone.payroll;


import jakarta.persistence.*;

@Entity
public class PayrollItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemId;
}
