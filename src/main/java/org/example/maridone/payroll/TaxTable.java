package org.example.maridone.payroll;

import jakarta.persistence.*;

@Entity
public class TaxTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taxIdd;

}
