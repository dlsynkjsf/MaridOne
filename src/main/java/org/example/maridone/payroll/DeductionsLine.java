package org.example.maridone.payroll;
import jakarta.persistence.*;


public class DeductionsLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long deductionsId;
}
