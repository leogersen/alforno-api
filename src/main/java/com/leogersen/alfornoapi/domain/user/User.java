package com.leogersen.alfornoapi.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public class User implements Serializable {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private String phone;

    public void encryptPassword() {
        // Encrypt Password
    }

}
