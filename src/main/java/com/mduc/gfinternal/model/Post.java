package com.mduc.gfinternal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "TEXT")
    private String title;
    private String titleImage;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
}
