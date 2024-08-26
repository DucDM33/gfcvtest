package com.mduc.gfinternal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CurriculumVitae {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="name")
    private String name;
    @Column(name= "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name ="birthday")
    private String birthday;
    @Column(name = "cv_url")
    private String cvUrl;
    @Column(name ="job_id")
    private Integer jobId;
}
