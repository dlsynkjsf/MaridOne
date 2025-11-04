package org.example.maridone.payroll;

import jakarta.persistence.*;

@Entity
public class EarningsLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long earningsId;



}
