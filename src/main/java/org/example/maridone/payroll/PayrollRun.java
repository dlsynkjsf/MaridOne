package org.example.maridone.payroll;

import jakarta.persistence.*;

@Entity
public class PayrollRun {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long runId;

}
