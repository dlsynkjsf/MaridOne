package org.example.maridone.leave;

import jakarta.persistence.*;

@Entity
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long requestId;


}
