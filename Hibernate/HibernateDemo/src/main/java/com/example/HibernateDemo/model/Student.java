package com.example.HibernateDemo.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(
            name = "fullName",
            nullable = false,
            length = 10
    )
    String name;

    @Column(
            name = "Email",
            nullable = false,
            unique = true,
            length = 50,
            insertable = true,
            updatable = true
    )
    String email;

    @Column(name = "Date_Of_Birth")
    LocalDate dob;

    @Lob
    String description;

    @Transient
    String displayName;

    @Column(precision = 5,scale = 2)
    Long percentage;

    @Enumerated(EnumType.STRING)
    Status studentStatus;

    @Convert(converter = BooleanToStringConverter.class)
    Boolean isMonitor;


    @Embedded @AttributeOverrides({
            @AttributeOverride(
                    name = "houseNo",
                    column = @Column(name = "current_houseNo")
            ),

            @AttributeOverride(
                    name = "city",
                    column = @Column(name = "current_city")
            ),

            @AttributeOverride(
                    name = "pinCode",
                    column = @Column(name = "current_pinCode")
            )

    })
    Address currentAddress;

    @Embedded @AttributeOverrides({
            @AttributeOverride(
                    name = "houseNo",
                    column = @Column(name = "permanent_houseNo")
            ),

            @AttributeOverride(
                    name = "city",
                    column = @Column(name = "permanent_city")
            ),

            @AttributeOverride(
                    name = "pinCode",
                    column = @Column(name = "permanent_pinCode")
            )

    })
    Address permanentAddress;


    @ElementCollection
    @CollectionTable(
                    name = "student_address",
                    joinColumns = @JoinColumn(name = "student_id")
    )
    Set<Address> addresses;

    @ElementCollection
    @CollectionTable(
            name = "student_skills",
            joinColumns = @JoinColumn(name = "student_id")
    )
    Set<String>skills;

    public Student(Long id, String name, String email, LocalDate dob, String description, String displayName, Long percentage, Status studentStatus, Boolean isMonitor, Address currentAddress, Address permanentAddress, Set<Address> addresses, Set<String> skills) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.description = description;
        this.displayName = displayName;
        this.percentage = percentage;
        this.studentStatus = studentStatus;
        this.isMonitor = isMonitor;
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
        this.addresses = addresses;
        this.skills = skills;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getPercentage() {
        return percentage;
    }

    public void setPercentage(Long percentage) {
        this.percentage = percentage;
    }

    public Status getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(Status studentStatus) {
        this.studentStatus = studentStatus;
    }

    public Boolean getMonitor() {
        return isMonitor;
    }

    public void setMonitor(Boolean monitor) {
        isMonitor = monitor;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

}
