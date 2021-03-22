package com.leogersen.alfornoapi.domain.restaurant;

import com.leogersen.alfornoapi.domain.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Table(name = "restaurant")
public class Restaurant extends User {

    @NotEmpty(message = "O campo CNPJ não pode estar vazio")
    @Pattern(regexp = "[0-9]{14}", message = "O CNPJ possui formato inválido")
    @Column(length = 14, nullable = false)
    private String cnpj;

    @Size(max = 80)
    private String logo;

    //@UploadConstraint(acceptTypes = FileType.PNG, message = "O arquivo é inválido")
   private transient MultipartFile logoFile;
   // TODO: Configurar upload

    @NotNull(message = "O campo taxa de entrega não pode estar vazio")
    @Min(0)
    @Max(120)
    private BigDecimal deliveryTax;

    @NotNull(message = "O campo tempo de entrega não pode estar vazio")
    @Min(0)
    @Max(120)
    private Integer deliveryTime;

    //@ToString.Exclude
    //private Set<RestaurantCategory> categories = new HashSet<>(0);

    //@OneToMany(mappedBy = "restaurant")
    //private Set<MenuItem> MenuItem = new HashSet<>(0);



}

