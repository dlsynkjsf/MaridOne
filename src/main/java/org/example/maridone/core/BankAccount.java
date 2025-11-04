package org.example.maridone.core;

import jakarta.persistence.*;

@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bankId;

    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "bank_name", nullable = false)
    private String bankName;



}
