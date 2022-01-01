package com.charles.zlbbs.domain.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String linkUrl;

    private Integer priority = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdTime;
}
