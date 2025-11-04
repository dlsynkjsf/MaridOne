package org.example.maridone.core;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.example.maridone.enums.EmploymentStatus;
import org.example.maridone.enums.Position;
import org.example.maridone.embeddable.Address;

import java.time.LocalDate;
import java.util.List;

@Table(name = "employee")
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_id", unique = true, nullable = false)
    private Long employeeId;


    @NotBlank
    @Column(name="first_name", nullable = false, length = 30)
    @Size(max = 30)
    private String firstName;

    @Column(name = "middle_name",nullable = true, length = 30)
    @Size(max = 30)
    private String middleName;

    @NotBlank
    @Column(name="last_name", nullable = false,  length = 30)
    @Size(max = 30)
    private String lastName;

    @Past
    @Column(name ="birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @NotBlank
    @Column(name = "email", unique = true, nullable = false, length = 254)
    @Email
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "position", nullable = false)
    @Enumerated(EnumType.STRING)
    private Position position;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BankAccount> bankAccounts;

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
