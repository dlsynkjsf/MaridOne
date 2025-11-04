package org.example.maridone.attendance;

import jakarta.persistence.*;

@Entity
public class AttendanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attendanceId;


}
