package com.charles.zlbbs.domain.entity;

import com.charles.zlbbs.domain.enu.Gender;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(generator ="uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator" )
    @Column(length = 200)
    private String id;

    @Column(length = 50,nullable = false,unique = true)
    private String email;

    @Column(length = 50,nullable = false)
    private String username;

    @Column(length = 200,nullable = false)
    private String password;

    @Column(length = 200)
    private String avatar = "/images/avatar.jpg";

    @Column(length = 200)
    private String signature;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender = Gender.UNKNOWN;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date joinTime;

    private Boolean staff = false;

    private Boolean active = true;

}
