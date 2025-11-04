package org.example.maridone.core;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class UserAccount {

    @Id
    @Column(unique = true, nullable = false)
    @Size(min = 4, max = 30)
    private String username;

    //status
    //password_hash

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id",nullable = false)
    private Employee employee;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
