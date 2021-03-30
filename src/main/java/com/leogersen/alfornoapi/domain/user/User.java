package com.leogersen.alfornoapi.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.leogersen.alfornoapi.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public class User implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "O nome é obrigatório")
    private String name;

    @NotEmpty(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail é inválido")
    @Size(max = 80, message = "O e-mail é muito grande")
    private String email;

    @JsonIgnore
    @NotEmpty(message = "A senha é obrigatória")
    @Size(max = 80, message = "A senha é muito grande")
    private String password;

    @NotEmpty(message = "O telefone é obrigatório")
    @Pattern(regexp ="[0-9]{10,14}", message = "O telefone possui formato inválido")
    private String phone;

    @NotEmpty(message = "O campo CEP não pode estar vazio")
    @Pattern(regexp = "[0-9]{8}", message = "O CEP possui formato inválido")
    @Column(length = 8)
    private String cep;

    @NotEmpty(message = "O nome da rua não pode estar vazio")
    private String street;

    @NotEmpty(message = "O nome do bairro não pode estar vazio")
    private String district;

    @NotNull(message = "O número é obrigatório")
    private Integer number;

    private String complement;



    public String getFormattedCep() {
        return cep.substring(0, 5) + "-" + cep.substring(5);
    }


    public void encryptPassword() {
        this.password = StringUtils.encrypt(this.password);
    }

}
