package org.example.maridone.payroll;


import jakarta.persistence.*;

@Entity
public class PayrollItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private long itemId;
}
