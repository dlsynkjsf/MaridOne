package org.example.maridone.payroll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DisputeRequestDto {

    @NotBlank(message = "subject is required")
    private String subject;
    @NotBlank(message = "Reason is required")
    @Size(min = 5, message = "Reason too short")
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
