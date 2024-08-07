package com.mduc.gfinternal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class Job extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "position")
    private String position;
    @Lob
    @Column(name = "description", length = 1500)
    private String description;
    @Lob
    @Column(name = "requirements", length = 1500)
    private String requirements;
    @Lob
    @Column(name = "other", length = 1500)
    private String other;
    @Column(name = "salary")
    private String salary;
    @Lob
    @Column(name = "benefit", length = 1500)
    private String benefit;
    @Column(name = "deadline")
    private String deadline;
    @Column(name = "working_time")
    private String workingTime;
    @Column(name = "quantity")
    private Integer quantity;
    @Lob
    @Column(name = "interview_format", length = 1500)
    private String interviewFormat;
    @Lob
    @Column(name = "location", length = 1500)
    private String location;
    @Column(name="expired")
    private Boolean expired;

}
