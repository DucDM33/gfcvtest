package com.mduc.gfinternal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "contact")
public class Contact extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name ="name")
    private String name;
    @Column(name ="email")
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;
    @Lob
    @Column(name = "message", length = 1500)
    private String message;
    @Column(name ="contact")
    private boolean contact;
}
