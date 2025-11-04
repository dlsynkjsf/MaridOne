package org.example.maridone.leave;


import jakarta.persistence.*;

@Entity
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long balanceId;


}
