package com.charles.zlbbs.domain.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"posts"})
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20,unique = true)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdTime;

    @OneToMany(mappedBy = "board")
    private List<Post> posts;
}
