package com.leogersen.alfornoapi.domain.restaurant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "restaurant_category")
public class RestaurantCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(max = 20)
    private String name;

    @NotEmpty
    @Size(max = 50)
    private String image;

    @ManyToMany(mappedBy = "categories")
    private Set<Restaurant> restaurants = new HashSet<>();
}
