package com.leogersen.alfornoapi.domain.restaurant;

import com.leogersen.alfornoapi.domain.user.User;
import com.leogersen.alfornoapi.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

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

    private BigDecimal rating = BigDecimal.valueOf(5.0);

    @NotNull(message = "O campo tempo de entrega não pode estar vazio")
    @Min(0)
    @Max(120)
    private Integer deliveryTime;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "restaurant_has_category",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_category_id"))
    @ToString.Exclude
    private Set<RestaurantCategory> categories = new HashSet<>(0);
    
    @OneToMany(mappedBy = "restaurant")
    private Set<MenuItem> menuItems = new HashSet<>(0);



    /* TODO: set Logo File Name

     public void setLogoFileName() {
        if (getId() == null) {
            throw new IllegalStateException("É preciso primeiro gravar o registro");

        }

        this.logo = String.format("%04d_restaurant.%s", getId(), FileType.of(logoFile.getContentType()).getExtension());
    }

    */

     public ArrayList<Integer> getCategoriesAsId() {
       ArrayList<Integer> categoriesId = new ArrayList<Integer>();

       for(RestaurantCategory category : categories){
            categoriesId.add(category.getId());
        };
        return categoriesId;

    } 

        public Set<MenuItem> getMenuItemsAsObject() {
        return menuItems;
        }


    public void deliveryTimeCalculator(String cep) {

    }









}

